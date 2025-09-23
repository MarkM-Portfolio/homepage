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


package com.ibm.lconn.homepage.services;

import java.util.Map;



/**
 * 
 * @author Lorenzo Notarfonzo
 *
 */
public interface ISystemMetrics {

	public Map<String,Object> fetchMetrics();

	public Object getMetricValueForKey(String key);
	
	//public String getMetricDescriptionForKey(String key);
	
	public String getMetricKeyName(int ix);
	
	public String[] getMetricKeyNameArray();
	
	public long getLastGetTime();
	
	public boolean setCacheTimeoutMinutes(int newCacheTimeout);
}
