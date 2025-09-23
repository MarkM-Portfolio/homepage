/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.web.taglib.jsp;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JspFunctions {

	private static String CLASS_NAME = JspFunctions.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	private static final BigDecimal MINS_IN_HOUR = new BigDecimal(60);
	
	public static boolean matches(String value, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(value);
		return m.matches();
	}
	
	/**
	 * Converts a given timezone offset value in the form of a string 
	 * like 1, 5.5, -6.0 in decimal hours, to an integer value in minutes.
	 * 
	 * Does everything to avoid throwing exceptions so that the worst case
	 * is that the return value is incorrectly zero but UI still displays, 
	 * rather than resulting in a nasty UI exception.
	 * 
	 * @param offset the offset
	 * 
	 * @return the integer value of the offset in minutes
	 */
	public static int getTimezoneOffsetAsMinutes(String offset) {
		int result = 0;
		
		if(offset != null && !offset.equals("")) {
			try{	
				BigDecimal bdOff = new BigDecimal(Double.parseDouble(offset));
				
				BigDecimal[] parts = bdOff.divideAndRemainder(BigDecimal.ONE);
				BigDecimal bdResult = parts[0].multiply(MINS_IN_HOUR);
				bdResult = bdResult.add(parts[1].multiply(MINS_IN_HOUR));
				result = bdResult.intValue();
			} catch (NumberFormatException nfe) {
				if(logger.isLoggable(Level.FINEST)) {
					// TODO externalize and make severe after 3.0
					logger.logp(Level.FINEST, CLASS_NAME, "getTimezoneOffsetAsMinutes", 
							"Provided offset: {0} is not valid. Offset of zero will be used", offset);
				}
			} catch (Throwable t) {
				if(logger.isLoggable(Level.FINEST)) {
					// TODO externalize and make severe after 3.0
					logger.logp(Level.FINEST, CLASS_NAME, "getTimezoneOffsetAsMinutes", 
							"Unexpected exception when converting offset", t);
				}	
			}
		} else {
			if(logger.isLoggable(Level.FINEST)) {
				logger.logp(Level.FINEST, CLASS_NAME, "getTimezoneOffsetAsMinutes", 
						"No offset provided.");
			}			
		}
		
		return result;
	}
}
