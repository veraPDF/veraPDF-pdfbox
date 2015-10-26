package org.apache.pdfbox.pdfparser.verapdf;

import org.apache.pdfbox.cos.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author Evgeniy Muravitskiy
 */
public class TestStreamParsing extends BaseTest {

	@Test
	public void testStreamSurroundings() throws IOException, URISyntaxException {
		COSDocument actual = getCosDocument("test_6_1_7_t01.pdf");
		COSObjectKey key = new COSObjectKey(4, 0);
		COSObject object = actual.getObjectFromPool(key);
		COSStream stream = (COSStream) object.getObject();
		Assert.assertFalse(stream.isStreamKeywordCRLFCompliant());
		Assert.assertTrue(stream.isEndstreamKeywordEOLCompliant());
	}

	@Test
	public void testEndStreamSurroundings() throws IOException, URISyntaxException {
		COSDocument actual = getCosDocument("test_6_1_7_t01.pdf");
		COSObjectKey key = new COSObjectKey(6, 0);
		COSObject object = actual.getObjectFromPool(key);
		COSStream stream = (COSStream) object.getObject();
		Assert.assertFalse(stream.isEndstreamKeywordEOLCompliant());
		Assert.assertTrue(stream.isStreamKeywordCRLFCompliant());
	}

	@Test
	public void testLength() throws IOException, URISyntaxException {
		COSDocument actual = getCosDocument("test_6_1_7_t02.pdf");
		checkLength(actual, 6);
		checkLength(actual, 4);
	}

	private void checkLength(COSDocument actual, long number) throws IOException {
		COSObjectKey key = new COSObjectKey(number, 0);
		COSObject object = actual.getObjectFromPool(key);
		COSStream stream = (COSStream) object.getObject();
		COSNumber length = (COSNumber) stream.getDictionaryObject(COSName.LENGTH);
		Assert.assertNotEquals(stream.getOriginLength().longValue(), length.longValue());
	}
}
