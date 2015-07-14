package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSDictionary;

/**
 * @author Timur Kamalov
 */
public class PDActionSetState extends PDAction {

	/**
	 * This type of action this object represents.
	 */
	public static final String SUB_TYPE = "SetState";

	/**
	 * Default constructor.
	 */
	public PDActionSetState()
	{
		action = new COSDictionary();
		setSubType(SUB_TYPE);
	}

	/**
	 * Constructor.
	 *
	 * @param a The action dictionary.
	 */
	public PDActionSetState(COSDictionary a)
	{
		super(a);
	}

}
