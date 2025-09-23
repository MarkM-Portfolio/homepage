/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.model;

import java.util.Locale;

import com.ibm.lconn.homepage.utils.ResourceBundle;

public enum Category {
	ACTIVITIES, BLOGS, COMMUNITIES, DOGEAR, PROFILES, WIKI, FILES, SAND, OTHER;

	private static final String MESSAGE_KEY = "category.%s.name";

	public String toLocalizedString(ResourceBundle catalogServicesUIBundle, Locale locale) {	
		return catalogServicesUIBundle.getString(String.format(MESSAGE_KEY, this.name().toLowerCase(java.util.Locale.ENGLISH)), locale);
	}

}
