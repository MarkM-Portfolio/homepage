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

package com.ibm.atmn.homepagefvt.testcases.wikis;


import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.wikis.Data;
import com.ibm.atmn.homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.homepagefvt.tasks.wikis.FVT_WikisMethods;



	/*
 	* This is the Wiki Teams BVT
	* Created By: Conor Pelly
	* Date: 07/01/2011
	*/ 

public class FVT_Level0_Wikis extends FVT_WikisMethods{
		/*************************************************************
		 * Test main functionality of public wiki as the wiki creator*
		 * ***********************************************************/
		
		//Create a public wiki
		
		public void testCreateANewPublicWiki () throws Exception {
			System.out.println("INFO: Start of BVT_Level0_Wikis Test");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
				
			//Login as a user (ie. Amy Jones66)
			Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);
			
			//Check for any error messages
			CheckForErrorsOnPage();
			
			//Click Start a Wiki button
			clickLink(WikisObjects.Start_New_Wiki_Button);
	
			//Create a new public wiki
			typeInTextField(WikisObjects.Wiki_Name_Field, Data.Level_0_Public_Wiki);
	
			//Click Save button
			clickLink(WikisObjects.Save_Button);
	
			//Verify homepage UI
			verifyNewHomePageUI(Data.Level_0_Public_Wiki, Data.Wiki_LDAP_Username, "");
				
			//Logout of Wiki
			Logout();	
		  	
			System.out.println("INFO: End of BVT_Level0_Wikis Test");
		}
			
}

