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
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Discover_Photo_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	User testUser1;
	User testUser2;
	
	
	
	@Test
	public void testProfilesPhotoUpdated_Create () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPhotoUpdated_Create");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
		
		testUser1 = userAllocator.getUser();
		testUser2 = userAllocator.getUser();
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Add tag
		uploadPhoto("Desert.jpg");		
		
		//Logout of profiles
		LogoutAndClose();
		
		String ProfileNewsStory = replaceNewsStory(NewsStoryData.PROFILE_INFO_CHANGED, null, null, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2, ProfileNewsStory, "Discover", FVT_NewsData.PROFILES, true);
		//verifyNewsStory("The following people updated their profile information:","Discover","Profiles", true);

		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPhotoUpdated_Create");
	}
	
	//@Test (dependsOnMethods = { "testProfilesPhotoUpdated_Create" })
	public void testProfilesPhotoUpdated_UpdateExisting () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPhotoUpdated_UpdateExisting");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Update photo
		uploadPhoto("Koala.jpg");
		
		//Logout of profiles
		LogoutAndClose();
		
		String ProfileNewsStory = replaceNewsStory(NewsStoryData.MULTI_PROFILE_CHANGE, null, null, testUser1.getDisplayName());
		
		verifyNewsStory(ProfileNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.PROFILES, true);
		//verifyNewsStory("The following people updated their profile information:","Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPhotoUpdated_UpdateExisting");
	}
	
	//@Test (dependsOnMethods = { "testProfilesPhotoUpdated_UpdateExisting" })
	public void testProfilesPhotoUpdated_RemoveExisting () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPhotoUpdated_RemoveExisting");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Remove Photo
		removePhoto();		
		
		//Logout of profiles
		LogoutAndClose();
		
		String ProfileNewsStory = replaceNewsStory(NewsStoryData.MULTI_PROFILE_CHANGE, null, null, testUser1.getDisplayName());
		
		verifyNewsStory(ProfileNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.PROFILES, true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPhotoUpdated_RemoveExisting");
	}
	
	
}
