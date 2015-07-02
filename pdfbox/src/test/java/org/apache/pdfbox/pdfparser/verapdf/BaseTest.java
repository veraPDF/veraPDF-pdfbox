package org.apache.pdfbox.pdfparser.verapdf;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Evgeniy Muravitskiy
 */
public abstract class BaseTest {

	public static final String FILE_RELATIVE_PATH = "/org/apache/pdfbox/pdfparser/veraPDF-test-suite/";

	/**
	 * Gets {@code COSDocument} from pdf which specified by path and parsed with some extensions.
	 *
	 * @param name name of pdf document
	 * @return {@code COSDocument} of pdf which specified by path
	 * @throws IOException when problems with parsing
	 */
	protected static COSDocument getCosDocument(String name) throws IOException, URISyntaxException {
		File file = new File(getSystemIndependentPath(FILE_RELATIVE_PATH + name));
		PDDocument document = PDDocument.load(file, false, true);
		COSDocument doc = document.getDocument();
		document.close();
		return doc;
	}

	private static String getSystemIndependentPath(String path) throws URISyntaxException {
		URL resourceUrl = ClassLoader.class.getResource(path);
		Path resourcePath = Paths.get(resourceUrl.toURI());
		return resourcePath.toString();
	}
}
