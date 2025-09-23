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

import java.util.logging.Logger;


/**
 * Validate that the text passed matches validation criteria if specified
 *  
 * Validation Criteria allowed (format "minlength=M,maxlength=N")
 *  minlength=M
 *  maxlength=N
 * @author blooby
 *
 */
public class ValidatorForText implements IValidator {
	
	private static String CLASS_NAME = ValidatorForText.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	public static String MINLENGTH="minlength";
	public static String MAXLENGTH="maxlength";
	
	HighwayAdminRequest adminUtils = null;
	
	// take the admin utils on construction
	public ValidatorForText(HighwayAdminRequest adminUtils) {
		this.adminUtils = adminUtils;
	}

	// supports validation of the kind "max=m,min=n"
	@Override
	public boolean validate(SettingDetails details) {
		
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "validate", details.getValidation());
		}
		
		Integer minLength= null;
		Integer maxLength = null;

		// parse validation
		if (details.getValidation() != null) {
			String[] params = details.getValidation().split(",", 2);
			if ((params!=null) && (params.length>0)) {
				
				for (String param : params) {
					String parts[] = param.split("=",2);
					if ((parts!=null) && (parts.length>1)) {
						
						String name=parts[0].trim();
						if (name.equals(MINLENGTH)) {
							minLength = new Integer(parts[1].trim());
						} else if (name.equals(MAXLENGTH)) {
							maxLength = new Integer(parts[1].trim());
						}
					}
				}
				
			}
		}
		
		boolean valid = true;
		if (minLength != null) {
			if (details.getValue().length() < minLength) {
				String context = minLength.toString();
				adminUtils.addErrorToDetails("text.value.too.short", details, context);
				valid = false;
			}
		}
		if (maxLength != null) {
			if (details.getValue().length() > maxLength) {
				String context = maxLength.toString();
				adminUtils.addErrorToDetails("text.value.too.long", details, context);
				valid = false;
			}
		}
		
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "validate", new Object[] {minLength,maxLength,details.getValue()});
		}
		
		return valid;
	}
	

}
