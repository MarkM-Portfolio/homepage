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
 *  1. refers to a long integer
 *  2. matches validation criteria if specified
 *  
 * Validation Criteria allowed (format "min=M,max=N")
 *  min=M
 *  max=N
 * @author blooby
 *
 */
public class ValidatorForLong implements IValidator {
	
	private static String CLASS_NAME = ValidatorForLong.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	public static String MIN="min";
	public static String MAX="max";
	
	HighwayAdminRequest adminUtils = null;
	
	// take the admin utils on construction
	public ValidatorForLong(HighwayAdminRequest adminUtils) {
		this.adminUtils = adminUtils;
	}


	// supports validation of the kind "max=m,min=n"
	@Override
	public boolean validate(SettingDetails details) {
		
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "validate", details.getValidation());
		}
		
		Long minLong = null;
		Long maxLong = null;

		// parse validation
		if (details.getValidation() != null) {
			String[] params = details.getValidation().split(",", 2);
			if ((params!=null) && (params.length>0)) {
				
				for (String param : params) {
					String parts[] = param.split("=",2);
					if ((parts!=null) && (parts.length>1)) {
						
						String name=parts[0].trim();
						if (name.equals(MIN)) {
							minLong = new Long(parts[1].trim());
						} else if (name.equals(MAX)) {
							maxLong = new Long(parts[1].trim());
						}
					}
				}
				
			}
		}
		
		// check for long
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
		Long longValue = null;
		if (valid) {
			try {
				longValue = Long.valueOf(value);
			} catch (NumberFormatException nfe) {
				valid=false;
				adminUtils.addErrorToDetails("int.value.not.an.integer", details, null);
			}
		}
		
		// check max and min
		if (valid) {
			if (maxLong != null) {
				if (longValue > maxLong) {
					adminUtils.addErrorToDetails("int.value.too.large", details, maxLong.toString());
					valid = false;
				}
			}
			if (minLong != null) {
				if (longValue < minLong) {
					adminUtils.addErrorToDetails("int.value.too.small", details, minLong.toString());
				}
			}
		}
		
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "validate", new Object[] {minLong,maxLong,longValue});
		}
		
		return valid;
	}
	

}
