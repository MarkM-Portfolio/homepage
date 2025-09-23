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


package com.ibm.atmn.homepagefvt.testcases.files;


import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.tasks.files.FVT_FilesMethods;

import static org.testng.AssertJUnit.*;

public class FVT_Level0_Files extends FVT_FilesMethods{
	
	/*
	 * This is a functional test for the Files Component of IBM Connections
	 * Created By: Conor Pelly
	 * Date: 07/05/2011
	 */
	
	
	//Log into Files and then logout
	
	public void testOpenFilesComponent () throws Exception {
		System.out.println("INFO: Start of Files BVT_Level_O Test");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
			
		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);
		
		if (sel.isTextPresent("Loading..."))
		{
			Thread.sleep(3000);
		}
		
		assertTrue("Fail: Files is not open", sel.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
				
		//Logout of Connections
		Logout();	
	  	
		System.out.println("INFO: End of Files BVT_Level_O Test");
	}

	
	public void OpenFilesComponent2 () throws Exception {
		System.out.println("INFO: Start of Files Test 2");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
			
		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);
		
		assertTrue("Fail: Files is not open", sel.isTextPresent("My Files"));
				
		//Logout of Connections
		Logout();	
	  	
		System.out.println("INFO: End of Files Test 2");
	}

}
