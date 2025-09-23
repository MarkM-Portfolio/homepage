/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2016                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.orgadmin;

import static java.util.logging.Level.FINER;

import java.util.Locale;
import java.util.logging.Logger;

/**
 * Validate that the text passed refers to a boolean
 * @author blooby
 *
 */
public class ValidatorForBool implements IValidator {
	
	private static String CLASS_NAME = ValidatorForBool.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	private static String ON = "on";
	private static String TRUE = "true";
	private static String FALSE = "false";
	
	public ValidatorForBool() {
		;
	}

	// supports validation of the kind "max=m,min=n"
	@Override
	public boolean validate(SettingDetails details) {
		
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "validate", details.getValidation());
		}
		
		// any null value can be treated as a false
		// on is what checkboxes use, so treat this as a synonym for true
		if ((details.getValue()==null) || (details.getValue().length()==0)) {
			details.setValue(FALSE);
		} else if (details.getValue().equalsIgnoreCase(ON)) {
			details.setValue(TRUE);
		}
		
		// check for boolean
		String value = details.getValue().trim();
		boolean valid =  (value.equalsIgnoreCase(TRUE) || value.equalsIgnoreCase(FALSE));
		
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "validate", valid);
		}
		
		return valid;
	}
	

}
