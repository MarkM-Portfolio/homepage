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

import java.util.Enumeration;
import java.util.Locale;

public interface ResourceBundle {

	public String getString(String key, Object... params);
	public String getString(String key, Locale locale, Object... params);
	public Enumeration<String> getKeys();
}
