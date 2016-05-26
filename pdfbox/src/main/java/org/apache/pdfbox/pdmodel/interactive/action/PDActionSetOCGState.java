package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSDictionary;

/**
 * @author Timur Kamalov
 */
public class PDActionSetOCGState extends PDAction {

	/**
	 * This type of action this object represents.
	 */
	public static final String SUB_TYPE = "SetOCGState";

	/**
	 * Default constructor.
	 */
	public PDActionSetOCGState()
	{
		action = new COSDictionary();
		setSubType(SUB_TYPE);
	}

	/**
	 * Constructor.
	 *
	 * @param a The action dictionary.
	 */
	public PDActionSetOCGState(COSDictionary a)
	{
		super(a);
	}

}
