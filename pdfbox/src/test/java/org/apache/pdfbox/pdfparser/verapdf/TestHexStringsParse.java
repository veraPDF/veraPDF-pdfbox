package org.apache.pdfbox.pdfparser.verapdf;

import org.apache.pdfbox.cos.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Evgeniy Muravitskiy
 */
public class TestHexStringsParse extends BaseTest{

	@Test
	public void testOddHexString() throws IOException, URISyntaxException {
		COSString string = getCosString("6-1-6-t01-fail-a.pdf");
		Assert.assertTrue(string.getHexCount() % 2 != 0);
	}

	@Test
	public void testIsHexSymbols() throws IOException, URISyntaxException {
		COSString string = getCosString("6-1-6-t01-fail-b.pdf");
		Assert.assertTrue(string.getHexCount() % 2 == 0);
		Assert.assertTrue(string.isHex());
		Assert.assertFalse(string.isContainsOnlyHex());
	}

	private COSString getCosString(String name) throws IOException, URISyntaxException {
		COSDocument doc = getCosDocument(name);
		int targetObjectIndex = 17; // number of object is 19 0 obj
		COSDictionary dictionary = (COSDictionary) doc.getObjects().get(targetObjectIndex).getObject();
		return (COSString) dictionary.getItem(COSName.TITLE);
	}
}
