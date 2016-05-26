package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSDictionary;

/**
 * @author Timur Kamalov
 */
public class PDActionRendition extends PDAction {

	/**
	 * This type of action this object represents.
	 */
	public static final String SUB_TYPE = "Rendition";

	/**
	 * Default constructor.
	 */
	public PDActionRendition()
	{
		action = new COSDictionary();
		setSubType(SUB_TYPE);
	}

	/**
	 * Constructor.
	 *
	 * @param a The action dictionary.
	 */
	public PDActionRendition(COSDictionary a)
	{
		super(a);
	}


}
