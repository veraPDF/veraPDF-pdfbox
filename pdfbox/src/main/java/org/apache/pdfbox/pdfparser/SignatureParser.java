package org.apache.pdfbox.pdfparser;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.pdfbox.cos.*;

import java.io.IOException;

import static org.apache.pdfbox.util.Charsets.ISO_8859_1;

/**
 * Class is extension of BaseParser for parsing of digital signature
 * dictionaries and it can calculate byte range of digital signature.
 *
 * @author Sergey Shemyakov
 */
public class SignatureParser extends BaseParser {

	private long byteRangeLength1 = -1;
	private long byteRangeOffset2 = -1;

	private static final Log LOGGER = LogFactory.getLog(SignatureParser.class);

	/**
	 * Constructor.
	 *
	 * @param stream The COS stream to read the data from.
	 * @throws IOException If there is an error reading the input stream.
	 */
	public SignatureParser(COSStream stream, COSDocument document) throws IOException {
		super(stream);
		this.document = document;
	}

	/**
	 * Parses signature dictionary and calculates offsets of beginning and
	 * ending of /Contents hex string.
	 *
	 * @param dictionaryBeginning offset of beginning of signature dictionary in
	 *                            bytes.
	 * @throws IOException if some problem with input stream occurs.
	 */
	public void parseSignatureDictionary(long dictionaryBeginning) throws IOException {
		this.pdfSource.seek(dictionaryBeginning);
		this.readExpectedChar('<');
		this.readExpectedChar('<');
		this.skipSpaces();
		//COSDictionary obj = new COSDictionary();
		boolean done = false;

		while(!done) {
			this.skipSpaces();
			char c = (char)this.pdfSource.peek();
			if(c == 62) {
				done = true;
			} else if(c == 47) {
				this.parseSignatureDictionaryNameValuePair();
			} else {
				LOGGER.warn("Invalid dictionary, found: \'" + c + "\' but expected: \'/\'");
				//if(this.readUntilEndOfCOSDictionary()) {	Actually we have no need to make sure stream is correctly positioned if we got invalid dictionary,
				//}											also that should not be reached
			}
		}

		this.readExpectedChar('>');
		this.readExpectedChar('>');
	}

	private void parseSignatureDictionaryNameValuePair() throws IOException {
		COSName key = parseCOSName();
		skipSpaces();
		parseSignatureDictionaryValue(key.compareTo(COSName.CONTENTS) == 0);
		skipSpaces();
		if (((char) pdfSource.peek()) == 'd') {
			// if the next string is 'def' then we are parsing a cmap stream
			// and want to ignore it, otherwise throw an exception.
			String potentialDEF = readString();
			if (!potentialDEF.equals(DEF)) {
				pdfSource.rewind(potentialDEF.getBytes(ISO_8859_1).length);
			}
			else {
				skipSpaces();
			}
		}
	}

	private void parseSignatureDictionaryValue(boolean isContentsHexString) throws IOException
	{
		skipSpaces();
		long numOffset1 = pdfSource.getPosition();
		COSBase number = parseDirObject();
		long numOffset2 = pdfSource.getPosition();
		skipSpaces();
		if (!isDigit() && !isContentsHexString) {
			return;
		} else if (!isDigit() && isContentsHexString) {
			byteRangeLength1 = numOffset1;
			byteRangeOffset2 = numOffset2;
			return;
		}
		long genOffset = pdfSource.getPosition();
		COSBase generationNumber = parseDirObject();
		skipSpaces();
		readExpectedChar('R');
		if (!(number instanceof COSInteger)) {
			throw new IOException("expected number, actual=" + number + " at offset " + numOffset1);
		}
		if (!(generationNumber instanceof COSInteger)) {
			throw new IOException("expected number, actual=" + number + " at offset " + genOffset);
		}
		if(!isContentsHexString) {
			return;
		}
		COSObjectKey key = new COSObjectKey(((COSInteger) number).longValue(),
				((COSInteger) generationNumber).intValue());
		long keyOffset = this.document.getXrefTable().get(key);
		pdfSource.seek(keyOffset);
		parseSignatureDictionaryValue(true);	// Recursive parsing to get to the contents hex string itself
	}

	/**
	 * @return second value in byte range array - byte offset of beginning of
	 * /Contents hex string
	 */
	public long getByteRangeLength1() {
		return byteRangeLength1;
	}

	/**
	 * @return third value in byte range array - byte offset of ending of
	 * /Contents hex string
	 */
	public long getByteRangeOffset2() {
		return byteRangeOffset2;
	}
}
