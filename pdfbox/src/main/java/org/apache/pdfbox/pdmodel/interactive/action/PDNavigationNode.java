package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSBase;
import org.apache.pdfbox.cos.COSDictionary;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.common.COSObjectable;
import org.apache.pdfbox.pdmodel.common.PDDestinationOrAction;

/**
 * @author Sergey Shemyakov
 */
public class PDNavigationNode implements COSObjectable {

    private COSDictionary object;

    public PDNavigationNode(COSDictionary object) {
        this.object = object;
    }

    @Override
    public COSBase getCOSObject() {
        return this.object;
    }

    public PDDestinationOrAction getNA() {
        return getAction(COSName.NA);
    }

    public PDDestinationOrAction getPA() {
        return getAction(COSName.PA);
    }

    public PDNavigationNode getNext() {
        return getNavigationNode(COSName.NEXT);
    }

    public PDNavigationNode getPrev() {
        return getNavigationNode(COSName.PREV);
    }

    private PDDestinationOrAction getAction(COSName key) {
        COSBase action = this.object.getDictionaryObject(key);
        if (action != null && action instanceof COSDictionary) {
            return PDActionFactory.createAction((COSDictionary) action);
        }
        return null;
    }

    private PDNavigationNode getNavigationNode(COSName key) {
        COSBase node = this.object.getDictionaryObject(key);
        if (node != null && node instanceof COSDictionary) {
            return new PDNavigationNode((COSDictionary) node);
        }
        return null;
    }
}
