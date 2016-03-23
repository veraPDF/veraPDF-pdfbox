package org.apache.pdfbox.pdmodel.graphics.image;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.pdmodel.graphics.PDXObject;
import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;

import java.io.IOException;

/**
 * @author Maksim Bezrukov
 */
public class PDImageXObjectProxy extends PDXObject {

    private PDColorSpace colorSpace;
    private PDResources resources;

    public PDImageXObjectProxy(PDStream stream, PDResources resources) {
        super(stream, COSName.IMAGE);
        this.resources = resources;
    }

    public boolean getInterpolate() {
        return getCOSStream().getBoolean(COSName.INTERPOLATE, false);
    }

    public boolean isStencil() {
        return getCOSStream().getBoolean(COSName.IMAGE_MASK, false);
    }

    public PDColorSpace getColorSpace() throws IOException {
        if (colorSpace == null) {
            COSBase cosBase = getCOSStream().getDictionaryObject(COSName.COLORSPACE, COSName.CS);
            if (cosBase != null) {
                colorSpace = PDColorSpace.create(cosBase, resources);
            } else if (isStencil()) {
                // stencil mask color space must be gray, it is often missing
                return PDDeviceGray.INSTANCE;
            } else {
                // an image without a color space is always broken
                throw new IOException("could not determine color space");
            }
        }
        return colorSpace;
    }

    public PDResources getResources() {
        return resources;
    }
}
