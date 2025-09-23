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
package com.ibm.lconn.homepage.utils;

import java.util.Locale;

/**
 * 
 * @author Lorenzo Notarfonzo
 *
 */
public interface IUIServiceUtils {

	/**
	 * Checks if a string starts with a % indicating an externalized property.
	 * If so, it looks for that property in the corresponding resource bundle.
	 * If the string does not start with a %, the property key has a null or
	 * empty value, or the key doesn't exist in resource bundle, this method
	 * returns the sent string.
	 */
	public String getGlobalizedMsgFromCatalogUI(String originalMsg, Locale locale); 

}
