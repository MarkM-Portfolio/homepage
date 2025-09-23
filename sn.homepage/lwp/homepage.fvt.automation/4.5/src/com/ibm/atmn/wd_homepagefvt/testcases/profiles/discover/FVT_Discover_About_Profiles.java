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
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;

public class FVT_Discover_About_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	User testUser1;
	
	
	
	@Test
	public void testAboutUpdated_AddContent () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testAboutUpdated_AddContent");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
		
		testUser1 = userAllocator.getUser(this);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Add content to About
		updateAbout();
		
		//Logout of profiles
		LogoutAndClose();
		
		//verifyNewsStory(testUser1.getDisplayName()+"'s profile information changed.",FVT_NewsObjects.Discover,FVT_NewsData.PROFILES, true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testAboutUpdated_AddContent");
	}
	
	//@Test
	public void testAboutUpdated_RemoveContent () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testAboutUpdated_RemoveContent");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Remove content from About
		
		
		//Logout of profiles
		LogoutAndClose();
		
		String ProfileNewsStory = replaceNewsStory(NewsStoryData.MULTI_PROFILE_CHANGE, null, null, testUser1.getDisplayName());
		
		verifyNewsStory(ProfileNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.PROFILES, true);
		//verifyNewsStory("The following people updated their profile information:","Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testAboutUpdated_RemoveContent");
	}
	
	//@Test
	public void testAboutUpdated_EditContent () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testAboutUpdated_EditContent");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Edit content from About
		updateAbout();
		
		//Logout of profiles
		LogoutAndClose();
		
		String ProfileNewsStory = replaceNewsStory(NewsStoryData.MULTI_PROFILE_CHANGE, null, null, testUser1.getDisplayName());
		
		verifyNewsStory(ProfileNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.PROFILES, true);
		//verifyNewsStory("The following people updated their profile information:","Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testAboutUpdated_EditContent");
	}
	
	//@Test
	public void testAboutUpdated_OtherFieldsUpdated () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testAboutUpdated_EditContent");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Update other fields
		
		
		
		//Logout of profiles
		LogoutAndClose();
		
		String ProfileNewsStory = replaceNewsStory(NewsStoryData.MULTI_PROFILE_CHANGE, null, null, testUser1.getDisplayName());
		
		verifyNewsStory(ProfileNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.PROFILES, true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testAboutUpdated_EditContent");
	}
	
	
}
