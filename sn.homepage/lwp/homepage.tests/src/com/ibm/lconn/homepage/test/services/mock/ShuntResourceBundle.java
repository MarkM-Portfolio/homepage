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

package com.ibm.lconn.homepage.test.services.mock;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;

import com.ibm.lconn.homepage.utils.ResourceBundle;

public class ShuntResourceBundle implements ResourceBundle {
	private String STANDARD_STRING = "This is a shunted resource bundle";

	public String getString(String key, Object... params) {

		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				if (i != 0)
					STANDARD_STRING += ",";
				STANDARD_STRING += " {" + i + "}";
			}
			return MessageFormat.format(STANDARD_STRING, params);
		} else {
			return STANDARD_STRING;
		}
	}

	public String getString(String key, Locale locale, Object... params) {
		return getString(key, params);
	}

	public Enumeration<String> getKeys() {
		return new Enumeration<String>() {

			public boolean hasMoreElements() {
				return false;
			}

			public String nextElement() {
				return null;
			}

		};
	}
}
