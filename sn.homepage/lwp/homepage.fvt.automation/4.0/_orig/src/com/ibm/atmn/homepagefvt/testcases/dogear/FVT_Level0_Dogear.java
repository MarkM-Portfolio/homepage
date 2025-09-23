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

package com.ibm.atmn.homepagefvt.testcases.dogear;


import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.tasks.dogear.FVT_DogearMethods;
import static org.testng.AssertJUnit.*;

public class FVT_Level0_Dogear extends FVT_DogearMethods{
	/*
	 * This is a functional test for the News Component of IBM Connections
	 * Created By: Conor Pelly
	 * Date: 07/05/2011
	 */

	//Log into News and then logout
	
	public void testOpenDogearComponent () throws Exception {
		System.out.println("INFO: Start of Dogear BVT_Level_O Test");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentDogear);
			
		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);
		
		assertTrue("Fail: Bookmarks is not open", sel.isTextPresent("Public Bookmarks"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//Logout of Wiki
		Logout();	
	  	
		System.out.println("INFO: End of Dogear BVT_Level_O Test");
	}

}