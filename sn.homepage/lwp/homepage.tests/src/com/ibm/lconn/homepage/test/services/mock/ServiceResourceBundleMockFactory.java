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

import org.easymock.EasyMock;

import com.ibm.lconn.homepage.utils.ResourceBundle;

public class ServiceResourceBundleMockFactory {

	public static String expectGetString(ResourceBundle resourceBundle, String key,Object... params) throws Exception{
		EasyMock.expect(resourceBundle.getString(key, params)).andReturn(key.toUpperCase());
		return key.toUpperCase();
	}
}
