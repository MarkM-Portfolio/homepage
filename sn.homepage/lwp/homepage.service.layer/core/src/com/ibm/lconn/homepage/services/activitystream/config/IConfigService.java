/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2011, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.activitystream.config;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import com.ibm.json.java.JSONObject;
import com.ibm.lconn.services.gadgets.osapiclient.activitystream.config.UserInfo;

/**
 * Public interface for the activity stream configuration service.
 * 
 * @author Robert Campion
 */

public interface IConfigService {
	/**
	 * Get the activity stream configuration object in its entirety.
	 * @return JSONObject that can be used to construct the activity stream.
	 */
	public JSONObject getConfigObject(Locale locale, UserInfo user);
	
	/*
		* Verifies whether the Connections new UI is enabled based on LCC config settings and a new UI cookie
		* @return true if new UI is enabled (for the user), false otherwise
		*/
	public boolean checkConnectionsNewUIEnabled(HttpServletRequest request);
}
