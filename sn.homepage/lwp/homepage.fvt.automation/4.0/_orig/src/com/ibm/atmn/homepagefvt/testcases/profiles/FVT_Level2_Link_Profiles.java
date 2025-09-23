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




import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Level2_Link_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	
	private static String LinkName="";
	
	@Test
	public void testProfile_addLink () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfile_addLink");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Add Link
		LinkName = ProfilesAddLink();
		
		//Logout of profiles
		Logout();	
		
		VerifyNewsStory(" added a related link to their profile: "+LinkName+".","Discover","People", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfile_addLink");
	}
	
	@Test (dependsOnMethods = { "testProfile_addLink" })
	public void testProfile_removeLink () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfile_removeLink");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Remove Link
		ProfilesRemoveLink();	
		
		//Logout of profiles
		Logout();	
		
		VerifyNewsStory(" removed a related link to their profile: "+LinkName+".","Discover","People", false);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfile_removeLink");
	}
	
	
}
