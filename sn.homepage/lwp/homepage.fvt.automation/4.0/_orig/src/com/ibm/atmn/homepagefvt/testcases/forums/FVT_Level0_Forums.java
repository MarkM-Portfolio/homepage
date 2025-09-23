/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.homepagefvt.testcases.forums;


import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.tasks.forums.FVT_ForumsMethods;
import static org.testng.AssertJUnit.*;


public class FVT_Level0_Forums extends FVT_ForumsMethods{
	/*
	 * This is a functional test for the News Component of IBM Connections
	 * Created By: Conor Pelly
	 * Date: 07/05/2011
	 */

	//Log into News and then logout
	
	public void testOpenForumsComponent () throws Exception {
		System.out.println("INFO: Start of Forums BVT_Level_O Test");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentForums);
			
		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);
		
		assertTrue("Fail: Forums is not open", sel.isTextPresent("Public Forums"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//Logout of Wiki
		Logout();	
	  	
		System.out.println("INFO: End of Forums BVT_Level_O Test");
	}

}