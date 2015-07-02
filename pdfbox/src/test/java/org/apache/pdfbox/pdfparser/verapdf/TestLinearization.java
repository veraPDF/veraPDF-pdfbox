package org.apache.pdfbox.pdfparser.verapdf;

import org.apache.pdfbox.cos.COSDocument;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Evgeniy Muravitskiy
 */
public class TestLinearization extends BaseTest{

	@Test
	public void testIsLinearized() throws IOException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-3-t02-fail-a.pdf");
		Assert.assertTrue(doc.isLinearized());
	}

	@Test
	public void testDifferentTrailersDictionaries() throws IOException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-3-t02-fail-a.pdf");
		Assert.assertNotEquals(doc.getFirstPageTrailer(), doc.getLastTrailer());
	}

	@Test
	public void testTrailerDictionary() throws IOException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-2-t02-pass-a.pdf");
		Assert.assertEquals(doc.getFirstPageTrailer(), doc.getLastTrailer());
	}
}
