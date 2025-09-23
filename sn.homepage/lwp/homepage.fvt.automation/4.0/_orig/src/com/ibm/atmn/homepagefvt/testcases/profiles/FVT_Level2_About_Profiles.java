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


import org.junit.*;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Level2_About_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	
	
	@Ignore 
	public void testAboutUpdated_AddContent () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testAboutUpdated_AddContent");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Add content to About
		UpdateAbout();
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testAboutUpdated_AddContent");
	}
	
	@Ignore 
	public void testAboutUpdated_RemoveContent () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testAboutUpdated_RemoveContent");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Remove content from About
		
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testAboutUpdated_RemoveContent");
	}
	
	@Ignore 
	public void testAboutUpdated_EditContent () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testAboutUpdated_EditContent");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Edit content from About
		
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testAboutUpdated_EditContent");
	}
	
	@Ignore 
	public void testAboutUpdated_OtherFieldsUpdated () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testAboutUpdated_EditContent");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update other fields
		
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testAboutUpdated_EditContent");
	}
	
	
}
