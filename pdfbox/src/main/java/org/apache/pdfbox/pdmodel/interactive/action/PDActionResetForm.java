package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSDictionary;

/**
 * @author Timur Kamalov
 */
public class PDActionResetForm extends PDAction {

	/**
	 * This type of action this object represents.
	 */
	public static final String SUB_TYPE = "ResetForm";

	/**
	 * Default constructor.
	 */
	public PDActionResetForm()
	{
		action = new COSDictionary();
		setSubType(SUB_TYPE);
	}

	/**
	 * Constructor.
	 *
	 * @param a The action dictionary.
	 */
	public PDActionResetForm(COSDictionary a)
	{
		super(a);
	}

}
