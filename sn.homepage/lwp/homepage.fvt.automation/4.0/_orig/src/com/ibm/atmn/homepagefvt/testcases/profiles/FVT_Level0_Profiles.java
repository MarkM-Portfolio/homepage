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

package com.ibm.atmn.homepagefvt.testcases.profiles;


import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.tasks.profiles.FVT_ProfilesMethods;

import static org.testng.AssertJUnit.*;

public class FVT_Level0_Profiles extends FVT_ProfilesMethods{
	/*
	 * This is a functional test for the News Component of IBM Connections
	 * Created By: Conor Pelly
	 * Date: 07/05/2011
	 */

	//Log into News and then logout
	
	public void testOpenProfilesComponent () throws Exception {
		System.out.println("INFO: Start of Profiles BVT_Level_O Test");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);
		
		assertTrue("Fail: Profiles is not open", sel.isTextPresent("Use Your Profile To Connect with People"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//Logout of Wiki
		Logout();	
	  	
		System.out.println("INFO: End of Profiles BVT_Level_O Test");
	}

}
