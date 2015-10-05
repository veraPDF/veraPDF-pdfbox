package org.apache.pdfbox.pdfparser.verapdf;

import org.apache.pdfbox.cos.*;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Evgeniy Muravitskiy
 */
public class TestIndirectObjectsParse extends BaseTest {

	private static final String FILE = "test_6_1_8.pdf";
	private static COSDocument actual;

	@BeforeClass
	public static void setUp() throws IOException, URISyntaxException {
		actual = getCosDocument(FILE);
	}

	@Test
	public void testHeaderFormat() throws IOException {
		for (COSObject object : actual.getObjects()) {
			long objectNumber = object.getObjectNumber();
			boolean isNeededObject = objectNumber == 1 || objectNumber == 2;
			Assert.assertTrue(object.isHeaderFormatComplyPDFA() ^ isNeededObject);
		}
	}

	@Test
	public void testHeaderObjectSurroundings() {
		for (COSObject object : actual.getObjects()) {
			long objectNumber = object.getObjectNumber();
			boolean isNeededObject = objectNumber == 3 || objectNumber == 5;
			Assert.assertTrue(object.isHeaderOfObjectComplyPDFA() ^ isNeededObject);
		}
	}

	@Test
	public void testEndObjectSurroundings() {
		for (COSObject object : actual.getObjects()) {
			long objectNumber = object.getObjectNumber();
			boolean isNeededObject = objectNumber == 4 || objectNumber == 5;
			Assert.assertTrue(object.isEndOfObjectComplyPDFA() ^ isNeededObject);
		}
	}

	@Test
	public void testOffsetSensitivity() throws IOException {
		//COSObject{7,0} has incorrect offset in xref table, but it`s exists like a link in COSObject{6,0}
		COSObjectKey key = new COSObjectKey(7, 0);
		COSObject object = actual.getObjectFromPool(key);
		COSBase base = object.getObject();
		Assert.assertNull(base);
	}

	@AfterClass
	public static void tearDown() throws IOException {
		actual.close();
		actual = null;
	}
}
