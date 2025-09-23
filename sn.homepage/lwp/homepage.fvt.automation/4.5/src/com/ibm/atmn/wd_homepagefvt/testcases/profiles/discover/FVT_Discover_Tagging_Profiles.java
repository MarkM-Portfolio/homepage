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

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.FVT_ProfilesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.FVT_ProfilesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Discover_Tagging_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	User testUser1;
	User testUser2;
	
	
	@Test
	public void testProfilesPersonSelftagged_Multiple () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPersonSelftagged_Multiple");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		testUser1 = userAllocator.getUser();
		testUser2 = userAllocator.getUser();
		Login(testUser1);
	
		//Add multiple tags
		ProfilesAddMultipleTags();			
		
		//Logout of profiles
		Logout();	
		
		//VerifyNewsStory(" tagged themselves with "+LinkName+","+LinkName+".","Discover","People", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPersonSelftagged_Multiple");
	}
	
	@Test (dependsOnMethods = { "testProfilesPersonSelftagged_Multiple" })
	public void testProfilesPersonSelftagged_Single () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPersonSelftagged_Single");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Add tag
		ProfilesAddATag();			
		
		//Logout of profiles
		Logout();	
		
		//driver.close();
		
		//VerifyNewsStory(" tagged themselves with "+FVT_ProfilesData.ProfilesTag+".","Discover","People", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPersonSelftagged_Single");
	}
	
	@Test (dependsOnMethods = { "testProfilesPersonSelftagged_Single" })
	public void testProfilesPersonTagged_Multiple () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPersonTagged_Multiple");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Search for user
		SearchForUser(testUser2.getEmail());
		
		//Select user's profile
		clickLink(FVT_ProfilesObjects.SelectProfile);
		
		//Add multiple tags
		ProfilesAddMultipleTags();			
		
		//Logout of profiles
		Logout();	
		
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPersonTagged_Multiple");
	}

	@Test (dependsOnMethods = { "testProfilesPersonTagged_Multiple" })
	public void testProfilesPersonTagged_Single () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPersonTagged_Single");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Search for user
		SearchForUser(testUser2.getEmail());
		
		//Select user's profile
		clickLink(FVT_ProfilesObjects.SelectProfile);
		
		//Add tag
		ProfilesAddATag();		
		
		//Logout of profiles
		Logout();	
		
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPersonTagged_Single");
	}
	
}
