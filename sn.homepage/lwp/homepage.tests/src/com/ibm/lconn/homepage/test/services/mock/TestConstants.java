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

package com.ibm.lconn.homepage.test.services.mock;

import com.ibm.lconn.homepage.model.User;

public class TestConstants {
	public static final User USER_1=new User();
	public static final User USER_2=new User();
	public static final String REPOSITORY_LOCATION="test-repository";
	{
		reset();
	}
	
	public static void reset(){
		USER_1.setId("ID111111-1111-1111-1111-111111111111");
		USER_1.setExternalId("EXTID111-1111-1111-1111-111111111111");
		USER_1.setDisplayname("User1 Name Lorenzo Notarfonzo");
		USER_1.setMail("user1.lorenzo.notarfonzo@ie.ibm.com");
		USER_1.setMailLower("user1.lorenzo.notarfonzo@ie.ibm.com");
		
		USER_2.setId("22222222-2222-2222-2222-222222222222");
		USER_2.setExternalId("EXTID222-2222-2222-2222-222222222222");
		USER_2.setDisplayname("User2 Name Guo Du");
		USER_2.setMail("user2.guodu@ie.ibm.com");
		USER_1.setMailLower("user2.guodu@ie.ibm.com");
	}
}
