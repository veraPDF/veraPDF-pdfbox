package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSDictionary;

/**
 * @author Timur Kamalov
 */
public class PDActionGoTo3DView extends PDAction {

	/**
	 * This type of action this object represents.
	 */
	public static final String SUB_TYPE = "GoTo3DView";

	/**
	 * Default constructor.
	 */
	public PDActionGoTo3DView()
	{
		action = new COSDictionary();
		setSubType(SUB_TYPE);
	}

	/**
	 * Constructor.
	 *
	 * @param a The action dictionary.
	 */
	public PDActionGoTo3DView(COSDictionary a)
	{
		super(a);
	}

}
