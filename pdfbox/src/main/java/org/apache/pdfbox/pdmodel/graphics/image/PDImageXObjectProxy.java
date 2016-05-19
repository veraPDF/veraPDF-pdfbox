package org.apache.pdfbox.pdmodel.graphics.image;

import org.apache.pdfbox.cos.COSArray;
import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
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

    /**
     * Returns the Mask Image XObject associated with this image, or null if there is none.
     * @return Mask Image XObject
     */
    public PDImageXObjectProxy getMask() throws IOException
    {
        COSBase mask = getCOSStream().getDictionaryObject(COSName.MASK);
        if (mask instanceof COSArray)
        {
            // color key mask, no explicit mask to return
            return null;
        }
        else
        {
            COSStream cosStream = (COSStream)getCOSStream().getDictionaryObject(COSName.MASK);
            if (cosStream != null)
            {
                // always DeviceGray
                return new PDImageXObjectProxy(new PDStream(cosStream), null);
            }
            return null;
        }
    }

    /**
     * Returns the Soft Mask Image XObject associated with this image, or null if there is none.
     * @return the SMask Image XObject, or null.
     */
    public PDImageXObjectProxy getSoftMask() throws IOException
    {
        COSStream cosStream = (COSStream)getCOSStream().getDictionaryObject(COSName.SMASK);
        if (cosStream != null)
        {
            // always DeviceGray
            return new PDImageXObjectProxy(new PDStream(cosStream), null);
        }
        return null;
    }

    public int getHeight()
    {
        return getCOSStream().getInt(COSName.HEIGHT);
    }

    public int getWidth()
    {
        return getCOSStream().getInt(COSName.WIDTH);
    }

    /**
     * Returns the key of this XObject in the structural parent tree.
     * @return this object's key the structural parent tree
     */
    public int getStructParent()
    {
        return getCOSStream().getInt(COSName.STRUCT_PARENT, 0);
    }

    public int getBitsPerComponent()
    {
        if (isStencil())
        {
            return 1;
        }
        else
        {
            return getCOSStream().getInt(COSName.BITS_PER_COMPONENT, COSName.BPC);
        }
    }

    public PDStream getStream() throws IOException
    {
        return getPDStream();
    }

    public PDResources getResources() {
        return resources;
    }

    /**
     * Returns the metadata associated with this XObject, or null if there is none.
     * @return the metadata associated with this object.
     */
    public PDMetadata getMetadata()
    {
        COSStream cosStream = (COSStream) getCOSStream().getDictionaryObject(COSName.METADATA);
        if (cosStream != null)
        {
            return new PDMetadata(cosStream);
        }
        return null;
    }
}
