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

package com.ibm.atmn.wd_homepagefvt.testcases.activities.discover;


import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;


public class FVT_Discover_Mod_Community_Activities extends FVT_ActivitiesMethods {
		

		User testUser1;
		User testUser2;
		User testUser3;
	
		FormInputHandler typist;
	
		private static String ModeratedCommunity = "";
		private static String ModeratedCommunityActivity = "";	
		private static String ModeratedActivityEntry = "";	
		private static String ModActivityToDo= "";
		
	
		@Test
		public void testLoginUsers() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testLoginUsers");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			testUser1 = userAllocator.getUser(this);
			testUser2 = userAllocator.getUser(this);
			testUser3 = userAllocator.getUser(this);
			
			Login(testUser2);
			
			sleep(1000);
			
			//Logout
			LogoutAndClose();
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(testUser3);
			
			sleep(1000);
			
			//Logout
			LogoutAndClose();
			
			System.out.println("INFO: End of Activities FVT_Level_2 testLoginUsers");
			
			
			
			
		}
		
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testCreateActivity_ModComm() throws Exception {
		
	
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivity_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a moderated community
		ModeratedCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser3.getEmail());
		
		//Verify that community, description, image and theme are correct
		//VerifyCommunityDetails("Moderated", CommunitiesData.CommunityName+DateTimeStamp, CommunitiesData.CommunityHandle+DateTimeStamp);
		
		// Create a new activity within this community
		
		CustomizeCommunity();
		
		ModeratedCommunityActivity = CreateInitialCommunityActivity(DateTimeStamp);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String ActivityNewsStory = replaceNewsStory(NewsStoryData.CREATE_ACTIVITY, ModeratedCommunityActivity, null, testUser1.getDisplayName());
		
		verifyNewsStory_TwoFilters(testUser2,ActivityNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.ALL, FVT_NewsData.ACTIVITIES, true);
		//verifyNewsStory_TwoFilters(testUser2," created an activity named "+ModeratedCommunityActivity+".","Discover","All","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivity_ModComm");
		
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivity_ModComm" })
	public void testCreateActivityEntry_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityEntry_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Activities
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + ModeratedCommunityActivity);
		
		// Created a new activity entry
		ModeratedActivityEntry = addEntryToActivity_Basic(ModeratedCommunityActivity +" Entry",typist, false);

		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String ActivityNewsStory = replaceNewsStory(NewsStoryData.CREATE_ACTIVITY_ENTRY, ModeratedActivityEntry, ModeratedCommunityActivity, testUser1.getDisplayName());
		
		verifyNewsStory_TwoFilters(testUser2,ActivityNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.ALL, FVT_NewsData.ACTIVITIES, true);
		//verifyNewsStory_TwoFilters(testUser2," created the "+ModeratedActivityEntry+" entry in the "+ModeratedCommunityActivity+" activity.","Discover","All","Activities", true);

		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityEntry_ModComm");
		
		
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityEntry_ModComm" })
	public void testCreateActivityComment_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityComment_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Activities
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + ModeratedCommunityActivity);
		
		sleep(1000);
		//Expand activity entry
		clickLink(FVT_ActivitiesObjects.CommActivityOutline_More_ExpandDescription);
		
		// Created a new activity entry comment
		addCommentToActivityEntry(typist, false);

		//Logout
		sleep(2000);
		LogoutAndClose();
		
		//Replace news story string
		String ActivityNewsStory = replaceNewsStory(NewsStoryData.COMMENT_ON_ACTIVITY, ModeratedActivityEntry, ModeratedCommunityActivity, testUser1.getDisplayName());
		
		verifyNewsStory_TwoFilters(testUser2, ActivityNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.ALL, FVT_NewsData.ACTIVITIES, true);
		//verifyNewsStory_TwoFilters(testUser2,"commented on the "+ModeratedActivityEntry+" entry thread in the "+ModeratedCommunityActivity+" activity.","Discover","All","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityComment_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityComment_ModComm" })
	public void testCreateActivityToDo_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityToDo_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Activities
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + ModeratedCommunityActivity);
		
		// Create a new to do
		ModActivityToDo = addToDoToActivity(ModeratedCommunityActivity +" To-Do",typist, false, false, testUser1);

		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String ActivityNewsStory = replaceNewsStory(NewsStoryData.CREATE_TODO_ITEM, ModActivityToDo, ModeratedCommunityActivity, testUser1.getDisplayName());
		
		verifyNewsStory_TwoFilters(testUser2, ActivityNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.ALL, FVT_NewsData.ACTIVITIES, true);
		//verifyNewsStory_TwoFilters(testUser2," created a to-do item named "+ModActivityToDo+" in the "+ModeratedCommunityActivity+" activity.","Discover","All","Activities", true);

		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityToDo_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityToDo_ModComm" })
	public void testMarkToDoAsCompleted_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkToDoAsCompleted_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Activities
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + ModeratedCommunityActivity);
		
		//Go to Activities To Do Items
		clickLink(FVT_ActivitiesObjects.ToDoItems);
		
		sleep(2000);
		
		// Mark to do as complete	
		//mouseOverAndWait(FVT_ActivitiesObjects.MarkToDoCompleted);
		clickLink(FVT_ActivitiesObjects.MarkToDoCompleted);

		//Logout
		LogoutAndClose();
		

		//Replace news story string
		String ActivityNewsStory = replaceNewsStory(NewsStoryData.COMPLETE_TODO_ITEM, ModActivityToDo, ModeratedCommunityActivity, testUser1.getDisplayName());
		
		verifyNewsStory_TwoFilters(testUser2, ActivityNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.ALL, FVT_NewsData.ACTIVITIES, true);
		//verifyNewsStory_TwoFilters(testUser2,"completed their own "+ModActivityToDo+" to-do item in the "+ModeratedCommunityActivity+" activity.","Discover","All","Activities", true);
	
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkToDoAsCompleted_ModComm");
		
	}
	
}