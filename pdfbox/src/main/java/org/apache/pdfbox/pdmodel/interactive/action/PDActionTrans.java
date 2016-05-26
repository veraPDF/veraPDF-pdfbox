package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSDictionary;

/**
 * @author Timur Kamalov
 */
public class PDActionTrans extends PDAction {

	/**
	 * This type of action this object represents.
	 */
	public static final String SUB_TYPE = "Trans";

	/**
	 * Default constructor.
	 */
	public PDActionTrans()
	{
		action = new COSDictionary();
		setSubType(SUB_TYPE);
	}

	/**
	 * Constructor.
	 *
	 * @param a The action dictionary.
	 */
	public PDActionTrans(COSDictionary a)
	{
		super(a);
	}

}
