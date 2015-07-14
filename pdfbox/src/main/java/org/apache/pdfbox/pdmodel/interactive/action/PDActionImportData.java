package org.apache.pdfbox.pdmodel.interactive.action;

import org.apache.pdfbox.cos.COSDictionary;

/**
 * @author Timur Kamalov
 */
public class PDActionImportData extends PDAction {

	/**
	 * This type of action this object represents.
	 */
	public static final String SUB_TYPE = "ImportData";

	/**
	 * Default constructor.
	 */
	public PDActionImportData()
	{
		action = new COSDictionary();
		setSubType(SUB_TYPE);
	}

	/**
	 * Constructor.
	 *
	 * @param a The action dictionary.
	 */
	public PDActionImportData(COSDictionary a)
	{
		super(a);
	}

}
