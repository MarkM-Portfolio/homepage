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
 * Validate that the text passed -
 *  1. refers to an integer
 *  2. matches validation criteria if specified
 *  
 * Validation Criteria allowed (format "min=M,max=N")
 *  min=M
 *  max=N
 * @author blooby
 *
 */
public class ValidatorForInt implements IValidator {

	private static String CLASS_NAME = ValidatorForInt.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	public static String MIN="min";
	public static String MAX="max";
	
	HighwayAdminRequest adminUtils = null;
	
	// take the admin utils on construction
	public ValidatorForInt(HighwayAdminRequest adminUtils) {
		this.adminUtils = adminUtils;
	}

	// supports validation of the kind "max=m,min=n"
	@Override
	public boolean validate(SettingDetails details) {
		
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "validate", details.getValidation());
		}
		
		Integer minInt = null;
		Integer maxInt = null;

		// parse validation
		if (details.getValidation() != null) {
			String[] params = details.getValidation().split(",", 2);
			if ((params!=null) && (params.length>0)) {
				
				for (String param : params) {
					String parts[] = param.split("=",2);
					if ((parts!=null) && (parts.length>1)) {
						
						String name=parts[0].trim();
						if (name.equals(MIN)) {
							minInt = new Integer(parts[1].trim());
						} else if (name.equals(MAX)) {
							maxInt = new Integer(parts[1].trim());
						}
					}
				}
				
			}
		}
		
		// check for integer
		boolean valid = true;
		String value = details.getValue().trim();
		for (char c : value.toCharArray()) {
			if(c<'0' || c>'9') {
				valid=false;
				adminUtils.addErrorToDetails("int.value.not.an.integer", details, null);
				break;
			}
		}
		
		// create and check the integer
		Integer intValue = null;
		if (valid) {
			try {
				intValue = Integer.valueOf(value);
			} catch (NumberFormatException t) {
				valid=false;
				adminUtils.addErrorToDetails("int.value.not.an.integer", details, null);
			}
		}
		
		// check max and min
		if (valid) {
			if (maxInt != null) {
				if (intValue > maxInt) {
					adminUtils.addErrorToDetails("int.value.too.large", details, maxInt.toString());
					valid = false;
				}
			}
			if (minInt != null) {
				if (intValue < minInt) {
					adminUtils.addErrorToDetails("int.value.too.small", details, minInt.toString());
				}
			}
		}
		
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "validate", new Object[] {minInt,maxInt,intValue});
		}
		
		return valid;
	}
	

}
