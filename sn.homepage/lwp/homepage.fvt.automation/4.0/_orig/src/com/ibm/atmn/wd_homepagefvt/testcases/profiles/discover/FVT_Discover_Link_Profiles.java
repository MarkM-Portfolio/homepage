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

package com.ibm.atmn.wd_homepagefvt.testcases.profiles.discover;




import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Discover_Link_Profiles extends FVT_ProfilesMethods{
	
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
		
		driver.close();
		
		verifyNewsStory("The following people added "+LinkName+" as a related link to their profile:","Discover","Profiles", true);
		
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
		
		driver.close();
		
		verifyNewsStory("The following people removed "+LinkName+" as a related link to their profile:","Discover","Profiles", false);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfile_removeLink");
	}
	
	
}
