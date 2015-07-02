package org.apache.pdfbox.pdfparser.verapdf;

import org.apache.pdfbox.cos.COSDocument;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author Evgeniy Muravitskiy
 */
public class TestHeaderParse extends BaseTest{

	@Test
	public void testStartPositionOfHeader() throws IOException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-2-t01-fail-a.pdf");
		Assert.assertTrue(doc.getNonValidHeader());
		Assert.assertFalse(doc.getNonValidCommentContent());
		Assert.assertFalse(doc.getNonValidCommentLength());
		Assert.assertFalse(doc.getNonValidCommentStart());
	}

	@Test
	public void testVersionOfHeader() throws IOException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-2-t01-fail-b.pdf");
		Assert.assertTrue(doc.getNonValidHeader());
		Assert.assertFalse(doc.getNonValidCommentContent());
		Assert.assertFalse(doc.getNonValidCommentLength());
		Assert.assertFalse(doc.getNonValidCommentStart());
	}

	@Test
	public void testContentOfHeader() throws IOException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-2-t01-fail-c.pdf");
		Assert.assertTrue(doc.getNonValidHeader());
		Assert.assertTrue(doc.getNonValidCommentContent());
		Assert.assertFalse(doc.getNonValidCommentLength());
		Assert.assertFalse(doc.getNonValidCommentStart());
	}

	@Test
	public void testCommentLength() throws IOException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-2-t02-fail-a.pdf");
		Assert.assertFalse(doc.getNonValidHeader());
		Assert.assertFalse(doc.getNonValidCommentContent());
		Assert.assertTrue(doc.getNonValidCommentLength());
		Assert.assertFalse(doc.getNonValidCommentStart());
	}

	@Test
	public void testCommentContent() throws IOException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-2-t02-fail-b.pdf");
		Assert.assertFalse(doc.getNonValidHeader());
		Assert.assertTrue(doc.getNonValidCommentContent());
		Assert.assertFalse(doc.getNonValidCommentLength());
		Assert.assertFalse(doc.getNonValidCommentStart());
	}

	@Test
	public void testContentOfComment() throws IOException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-2-t02-fail-c.pdf");
		Assert.assertFalse(doc.getNonValidHeader());
		Assert.assertTrue(doc.getNonValidCommentContent());
		Assert.assertFalse(doc.getNonValidCommentLength());
		Assert.assertFalse(doc.getNonValidCommentStart());
	}

	@Test
	public void testValidHeader() throws IOException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-2-t02-pass-a.pdf");
		Assert.assertFalse(doc.getNonValidHeader());
		Assert.assertFalse(doc.getNonValidCommentContent());
		Assert.assertFalse(doc.getNonValidCommentLength());
		Assert.assertFalse(doc.getNonValidCommentStart());
	}
}
