package org.apache.pdfbox.pdfparser.verapdf;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;

/**
 * @author Evgeniy Muravitskiy
 */
public abstract class BaseTest {

	public static final String FILE_RELATIVE_PATH = "pdfbox/src/test/resources/org/apache/pdfbox/pdfparser/veraPDF-test-suite/";

	/**
	 * Gets {@code COSDocument} from pdf which specified by path and parsed with some extensions.
	 *
	 * @param name name of pdf document
	 * @return {@code COSDocument} of pdf which specified by path
	 * @throws IOException when problems with parsing
	 */
	protected static COSDocument getCosDocument(String name) throws IOException {
		File file = new File(FILE_RELATIVE_PATH + name);
		PDDocument document = PDDocument.load(file, false, true);
		COSDocument doc = document.getDocument();
		document.close();
		return doc;
	}
}
