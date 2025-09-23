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

package com.ibm.lconn.homepage.services;

import java.util.Locale;

import com.ibm.json.java.JSONObject;

public interface INewsService {
	
	/**
	 * 
	 * @param personId
	 * @param locale
	 * @return JSONObject - JSON representing subscriptions
	 * @throws ServiceException
	 */
	JSONObject getTagSubscriptions(String personId, Locale locale) throws ServiceException;
	
	/**
	 * 
	 * @param personId
	 * @param tag
	 * @throws ServiceException
	 */
	void addTagSubscription(String personId, String tag, Locale locale) throws ServiceException;

	/**
	 * 
	 * @param personId
	 * @param tag
	 * @throws ServiceException
	 */
	void removeTagSubscription(String personId, String tagId, Locale locale, String tag) throws ServiceException;
}
