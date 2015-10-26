package org.apache.pdfbox.pdfparser.verapdf;

import org.apache.pdfbox.cos.COSDocument;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Evgeniy Muravitskiy
 */
public class TestHeaderParse extends BaseTest{

	@Test
	public void testStartPositionOfHeader() throws IOException, URISyntaxException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-2-t01-fail-a.pdf");
		Assert.assertEquals(" %PDF-1.4", doc.getHeader());
		Assert.assertEquals(1, doc.getHeaderOffset());
		Assert.assertEquals(246, doc.getHeaderCommentByte1());
		Assert.assertEquals(228, doc.getHeaderCommentByte2());
		Assert.assertEquals(252, doc.getHeaderCommentByte3());
		Assert.assertEquals(223, doc.getHeaderCommentByte4());
	}

	@Test
	public void testVersionOfHeader() throws IOException, URISyntaxException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-2-t01-fail-b.pdf");
		Assert.assertEquals("%PDF-a.4", doc.getHeader());
		Assert.assertEquals(0, doc.getHeaderOffset());
		Assert.assertEquals(246, doc.getHeaderCommentByte1());
		Assert.assertEquals(228, doc.getHeaderCommentByte2());
		Assert.assertEquals(252, doc.getHeaderCommentByte3());
		Assert.assertEquals(223, doc.getHeaderCommentByte4());
	}

	@Test
	public void testContentOfHeader() throws IOException, URISyntaxException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-2-t01-fail-c.pdf");
		Assert.assertEquals("1 0 obj", doc.getHeader());
		Assert.assertEquals(15, doc.getHeaderOffset());
		Assert.assertEquals(80, doc.getHeaderCommentByte1());
		Assert.assertEquals(32, doc.getHeaderCommentByte2());
		Assert.assertEquals(68, doc.getHeaderCommentByte3());
		Assert.assertEquals(70, doc.getHeaderCommentByte4());
	}

	@Test
	public void testCommentLength() throws IOException, URISyntaxException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-2-t02-fail-a.pdf");
		Assert.assertEquals("%PDF-1.4", doc.getHeader());
		Assert.assertEquals(0, doc.getHeaderOffset());
		Assert.assertEquals(-1, doc.getHeaderCommentByte1());
		Assert.assertEquals(-1, doc.getHeaderCommentByte2());
		Assert.assertEquals(-1, doc.getHeaderCommentByte3());
		Assert.assertEquals(-1, doc.getHeaderCommentByte4());
	}

	@Test
	public void testCommentContent() throws IOException, URISyntaxException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-2-t02-fail-b.pdf");
		Assert.assertEquals("%PDF-1.4", doc.getHeader());
		Assert.assertEquals(0, doc.getHeaderOffset());
		Assert.assertEquals(-1, doc.getHeaderCommentByte1());
		Assert.assertEquals(-1, doc.getHeaderCommentByte2());
		Assert.assertEquals(-1, doc.getHeaderCommentByte3());
		Assert.assertEquals(-1, doc.getHeaderCommentByte4());
	}

	@Test
	public void testContentOfComment() throws IOException, URISyntaxException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-2-t02-fail-c.pdf");
		Assert.assertEquals("%PDF-1.4", doc.getHeader());
		Assert.assertEquals(0, doc.getHeaderOffset());
		Assert.assertEquals(246, doc.getHeaderCommentByte1());
		Assert.assertEquals(228, doc.getHeaderCommentByte2());
		Assert.assertEquals(252, doc.getHeaderCommentByte3());
		Assert.assertEquals(110, doc.getHeaderCommentByte4());
	}

	@Test
	public void testValidHeader() throws IOException, URISyntaxException {
		COSDocument doc = getCosDocument("veraPDF test suite 6-1-2-t02-pass-a.pdf");
		Assert.assertEquals("%PDF-1.4", doc.getHeader());
		Assert.assertEquals(0, doc.getHeaderOffset());
		Assert.assertEquals(246, doc.getHeaderCommentByte1());
		Assert.assertEquals(228, doc.getHeaderCommentByte2());
		Assert.assertEquals(252, doc.getHeaderCommentByte3());
		Assert.assertEquals(223, doc.getHeaderCommentByte4());
	}
}
