package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSDictionary;

/**
 * @author Timur Kamalov
 */
public class PDActionNOP extends PDAction {

	/**
	 * This type of action this object represents.
	 */
	public static final String SUB_TYPE = "NOP";

	/**
	 * Default constructor.
	 */
	public PDActionNOP()
	{
		action = new COSDictionary();
		setSubType(SUB_TYPE);
	}

	/**
	 * Constructor.
	 *
	 * @param a The action dictionary.
	 */
	public PDActionNOP(COSDictionary a)
	{
		super(a);
	}

}
