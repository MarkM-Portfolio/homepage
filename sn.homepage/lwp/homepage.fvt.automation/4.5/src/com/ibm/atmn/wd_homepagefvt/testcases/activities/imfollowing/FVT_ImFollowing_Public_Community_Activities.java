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

package com.ibm.atmn.wd_homepagefvt.testcases.activities.imfollowing;




import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;

import static org.testng.AssertJUnit.*;

public class FVT_ImFollowing_Public_Community_Activities extends FVT_ActivitiesMethods {
		
		User testUser1;
		User testUser2;
		User testUser3;

		FormInputHandler typist;
		
		private static String PublicCommunity = "";	
		private static String PublicCommunityActivity = "";	
		private static String PublicActivityEntry = "";
		private static String PublicActivityPrivateEntry = "";
		private static String PublicActivityToDo= "";
		private static String PublicActivityPrivateToDo= "";
		

	@Test
	public void testCreatePublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreatePublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		Reporter.log("testUser1 "+testUser1.getDisplayName());
		Reporter.log("testUser2 "+testUser2.getDisplayName());
		Reporter.log("testUser3 "+testUser3.getDisplayName());
		
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser3.getEmail());
		
		//Logout
		Logout();
		
		//VerifyNewsStory(" created a new activity named "+PublicCommunityActivity+".","Discover","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreatePublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreatePublicComm" })
	public void testFollowPublicCommunity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowPublicCommunity");
	
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_CommunitiesObjects.PublicCommunityView);
			
			clickLink("link="+PublicCommunity);
			
			clickLink(FVT_CommunitiesObjects.FollowCommunity);
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowPublicCommunity");
			
		}
	
	
	@Test (dependsOnMethods = { "testFollowPublicCommunity" })
	public void testCreateActivity_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivity_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		clickLink(FVT_CommunitiesObjects.PublicCommunityView);
		
		clickLink("link="+PublicCommunity);
		
		CustomizeCommunity();
		
		// Create a new activity within this community
		PublicCommunityActivity = CreateInitialCommunityActivity(DateTimeStamp);
		
		//Logout
		Logout();
		
		//VerifyNewsStory(" created a new activity named "+PublicCommunityActivity+".","Discover","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivity_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivity_PublicComm" })
	public void testFollowPublicCommunityActivity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowPublicCommunityActivity");
	
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_CommunitiesObjects.PublicCommunityView);
			
			clickLink("link="+PublicCommunity);
				
			clickLink("link="+PublicCommunityActivity);
			
			sleep(2000);
						
			clickLink(FVT_ActivitiesObjects.FollowingActions);
			
			sleep(2000);

			clickLink(FVT_ActivitiesObjects.FollowActivity);
					
			clickLink(FVT_HomepageObjects.Home);
			clickLink(FVT_HomepageObjects.Updates);			
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			filterBy("Communities");
			
			Thread.sleep(2000);
			
			assertTrue("Text not present!",driver.isTextPresent(" created an activity named "+PublicCommunityActivity+"."));
			
			filterBy("Activities");
			
			Thread.sleep(2000);
			
			assertTrue("Text is present!",driver.isTextPresent(" created an activity named "+PublicCommunityActivity+"."));
			
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowPublicCommunityActivity");
			
		}
	

	@Test (dependsOnMethods = { "testFollowPublicCommunityActivity" })
	public void testCreateActivityEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		// Created a new activity entry
		PublicActivityEntry = addEntryToActivity_Basic(PublicCommunityActivity +" Entry",typist, false);
		PublicActivityEntry = PublicCommunityActivity +" Entry";
		
		//Logout
		LogoutAndClose();

		String activityNewsStory = replaceNewsStory(NewsStoryData.CREATE_ACTIVITY_ENTRY, PublicActivityEntry, PublicCommunityActivity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2, activityNewsStory, FVT_NewsData.ACTIVITIES, true);		
		//verifyImFollowingNewsStory(testUser2," created the "+PublicActivityEntry+" entry in the "+PublicCommunityActivity+" activity.","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityEntry_PublicComm" })
	public void testCreateActivityPrivateEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityPrivateEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		// Created a new activity entry
		addEntryToActivity_Basic(PublicCommunityActivity +" Private Entry",typist, true);
		PublicActivityPrivateEntry = PublicCommunityActivity +" Private Entry";
		
		//Logout
		LogoutAndClose();

		String activityNewsStory = replaceNewsStory(NewsStoryData.CREATE_ACTIVITY_ENTRY, PublicActivityPrivateEntry, PublicCommunityActivity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2, activityNewsStory, FVT_NewsData.ACTIVITIES, false);			
		//verifyImFollowingNewsStory(testUser2," created the "+PublicActivityPrivateEntry+" entry in the "+PublicCommunityActivity+" activity.","Activities", false);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityPrivateEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityPrivateEntry_PublicComm" })
	public void testCreateActivityComment_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityComment_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		//Expand activity entry
		clickLink(FVT_ActivitiesObjects.CommActivityOutline_More_ExpandDescription);
		
		// Created a new activity entry comment
		addCommentToActivityEntry(typist, false);

		//Logout
		LogoutAndClose();
		
		String activityNewsStory = replaceNewsStory(NewsStoryData.COMMENT_ON_ACTIVITY, PublicActivityEntry, PublicCommunityActivity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2, activityNewsStory, FVT_NewsData.ACTIVITIES, true);		
		//verifyImFollowingNewsStory(testUser2,"commented on the "+PublicActivityEntry+" entry thread in the "+PublicCommunityActivity+" activity.","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityComment_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityComment_PublicComm" })
	public void testEditActivityComment_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testEditActivityComment_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		//Expand activity entry
		clickLink(FVT_ActivitiesObjects.CommActivityOutline_More_ExpandDescription);
		
		// Created a new activity entry comment
		editCommentToActivityEntry(typist, false);

		//Logout
		LogoutAndClose();
		
		String activityNewsStory = replaceNewsStory(NewsStoryData.UPDATE_ACTIVITY_ENTRY, PublicActivityEntry, PublicCommunityActivity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2, activityNewsStory, FVT_NewsData.IM_FOLLOWING_VIEW, FVT_NewsData.COMMUNITIES, true);
		sleep(500);
		verifyNewsStory_TwoFilters(testUser2, activityNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW, FVT_NewsData.ALL, FVT_NewsData.ACTIVITIES, false);
		//verifyImFollowingNewsStory(testUser2,"updated the "+PublicActivityEntry+" entry thread in the "+PublicCommunityActivity+" activity.","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testEditActivityComment_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testEditActivityComment_PublicComm" })
	public void testCreateActivityPrivateComment_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityPrivateComment_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		//Expand activity entry
		clickLink(FVT_ActivitiesObjects.CommActivityOutline_More_ExpandDescription);
		
		// Created a new activity entry private comment
		addCommentToActivityEntry(typist, true);

		//Logout
		LogoutAndClose();
		
		String activityNewsStory = replaceNewsStory(NewsStoryData.COMMENT_ON_ACTIVITY, PublicActivityEntry, PublicCommunityActivity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2, activityNewsStory, FVT_NewsData.ACTIVITIES, false);	
		//verifyImFollowingNewsStory(testUser2,"commented on the "+PublicActivityEntry+" entry thread in the "+PublicCommunityActivity+" activity.","Activities", false);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityPrivateComment_PublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateActivityPrivateComment_PublicComm" })
	public void testCreateActivityToDo_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityToDo_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		
		// Create a new to do
		addToDoToActivity(PublicCommunityActivity +" To-Do", typist, false, false, testUser1);
		PublicActivityToDo = PublicCommunityActivity +" To-Do";

		//Logout
		LogoutAndClose();
		
		String activityNewsStory = replaceNewsStory(NewsStoryData.CREATE_TODO_ITEM, PublicActivityToDo, PublicCommunityActivity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2, activityNewsStory, FVT_NewsData.ACTIVITIES, true);			
		//verifyImFollowingNewsStory(testUser2," created a to-do item named "+PublicActivityToDo+" in the "+PublicCommunityActivity+" activity.","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityToDo_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityToDo_PublicComm" })
	public void testMarkToDoAsCompleted_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkToDoAsCompleted_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		//Go to Activities To Do Items
		clickLink(FVT_ActivitiesObjects.ToDoItems);
		
		// Mark to do as complete	
		//mouseOverAndWait(FVT_ActivitiesObjects.MarkToDoCompleted);
		clickLink(FVT_ActivitiesObjects.MarkToDoCompleted);

		//Logout
		LogoutAndClose();
		
		String activityNewsStory = replaceNewsStory(NewsStoryData.COMPLETE_TODO_ITEM, PublicActivityToDo, PublicCommunityActivity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2, activityNewsStory, FVT_NewsData.ACTIVITIES, true);			
		//verifyImFollowingNewsStory(testUser2,"completed their own "+PublicActivityToDo+" to-do item in the "+PublicCommunityActivity+" activity.","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkToDoAsCompleted_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testMarkToDoAsCompleted_PublicComm" })
	public void testCreateActivityPrivateToDo_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityToDo_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		
		// Create a new to do
		addToDoToActivity(PublicCommunityActivity +" Private to-Do", typist, true, false, testUser1);
		PublicActivityPrivateToDo = PublicCommunityActivity +" Private to-Do";
		
		//Logout
		LogoutAndClose();

		String activityNewsStory = replaceNewsStory(NewsStoryData.CREATE_TODO_ITEM, PublicActivityPrivateToDo, PublicCommunityActivity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2, activityNewsStory, FVT_NewsData.ACTIVITIES, false);		
		//verifyImFollowingNewsStory(testUser2," created a to-do item named "+PublicActivityPrivateToDo+" in the "+PublicCommunityActivity+" activity.","Activities", false);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityToDo_PublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateActivityPrivateToDo_PublicComm" })
	public void testMarkPrivateToDoAsCompleted_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkPrivateToDoAsCompleted_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		//Go to Activities To Do Items
		clickLink(FVT_ActivitiesObjects.ToDoItems);
		
		// Mark to do as complete	
		//mouseOverAndWait(FVT_ActivitiesObjects.MarkToDoCompleted);
		clickLink(FVT_ActivitiesObjects.MarkToDoCompleted);

		//Logout
		LogoutAndClose();
		
		String activityNewsStory = replaceNewsStory(NewsStoryData.COMPLETE_TODO_ITEM, PublicActivityPrivateToDo, PublicCommunityActivity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2, activityNewsStory, FVT_NewsData.ACTIVITIES, false);			
		//verifyImFollowingNewsStory(testUser2,"completed their own "+PublicActivityPrivateToDo+" to-do item in the "+PublicCommunityActivity+" activity.","Activities", false);
		 
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkPrivateToDoAsCompleted_PublicComm");
		
	}
	
	
	
}