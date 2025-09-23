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




import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;

import static org.testng.AssertJUnit.*;

public class FVT_ImFollowing_Private_Community_Activities extends FVT_ActivitiesMethods {
		
	
		User testUser1;
		User testUser2;
	
		FormInputHandler typist;
	
		
		private static String PrivateCommunity = "";	
		private static String PrivateCommunityActivity = "";	
		private static String PrivateActivityEntry = "";
		private static String PrivateActivityToDo= "";
		
		@Test
		public void testLoginUsers() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testLoginUsers");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			testUser1 = userAllocator.getUser(this);
			testUser2 = userAllocator.getUser(this);
		
			Login(testUser2);
			
			sleep(1000);
			
			//Logout
			LogoutAndClose();
			
			
			System.out.println("INFO: End of Activities FVT_Level_2 testLoginUsers");
			
		}
		
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testCreatePrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreatePrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
				
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
		// Created a new community with private access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a private community
		PrivateCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
		
		//Logout
		Logout();
		
		//VerifyNewsStory(" created a new activity named "+PrivateCommunityActivity+".","Discover","Activities", false);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreatePrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreatePrivateComm" })
	public void testFollowPrivateCommunity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowPrivateCommunity");
	
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_CommunitiesObjects.ImAMember);
			
			clickLink("link="+PrivateCommunity);
						
			clickLink(FVT_CommunitiesObjects.FollowCommunity);
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowPrivateCommunity");
			
		}
	
	
	@Test(dependsOnMethods = { "testFollowPrivateCommunity" })
	public void testCreateActivity_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivity_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
		// Created a new community with private access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		clickLink("link="+PrivateCommunity);
	
		CustomizeCommunity();
		
		// Create a new activity within this community
		PrivateCommunityActivity = CreateInitialCommunityActivity(DateTimeStamp);
		
		//Logout
		LogoutAndClose();
		
		verifyNewsStory(testUser2, " created an activity named "+PrivateCommunityActivity+".","Discover","Activities", false);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivity_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivity_PrivateComm" })
	public void testFollowPrivateCommunityActivity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowPrivateCommunityActivity");
	
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_CommunitiesObjects.ImAMember);
			
			clickLink("link="+PrivateCommunity);
				
			clickLink("link="+PrivateCommunityActivity);
						
			clickLink(FVT_ActivitiesObjects.FollowingActions);

			clickLink(FVT_ActivitiesObjects.FollowActivity);
					
			clickLink(FVT_HomepageObjects.Home);
						
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			filterBy("Communities");
			
			Thread.sleep(2000);
			
			assertTrue("Text not present!",driver.isTextPresent(" created an activity named "+PrivateCommunityActivity+"."));
			
			filterBy("Activities");
			
			Thread.sleep(2000);
			
			assertTrue("Text is present!",driver.isTextPresent(" created an activity named "+PrivateCommunityActivity+"."));
			
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowPrivateCommunityActivity");
			
		}
	

	@Test (dependsOnMethods = { "testFollowPrivateCommunityActivity" })
	public void testCreateActivityEntry_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityEntry_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + PrivateCommunityActivity);
		
		// Created a new activity entry
		PrivateActivityEntry = addEntryToActivity_Basic(PrivateCommunityActivity +" Entry",typist, false);

		//Logout
		LogoutAndClose();
		
		verifyImFollowingNewsStory(testUser2," created the "+PrivateActivityEntry+" entry in the "+PrivateCommunityActivity+" activity.","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityEntry_PrivateComm");
		
	}
	
	
	
	@Test (dependsOnMethods = { "testCreateActivityEntry_PrivateComm" })
	public void testCreateActivityComment_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityPrivate_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + PrivateCommunityActivity);
		
		//Expand activity entry
		clickLink(FVT_ActivitiesObjects.CommActivityOutline_More_ExpandDescription);
		
		// Created a new activity entry comment
		addCommentToActivityEntry(typist, false);

		//Logout
		LogoutAndClose();
		
		verifyImFollowingNewsStory(testUser2,"commented on the "+PrivateActivityEntry+" entry thread in the "+PrivateCommunityActivity+" activity.","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityComment_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityComment_PrivateComm" })
	public void testEditActivityComment_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testEditActivityComment_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + PrivateCommunityActivity);
		
		//Expand activity entry
		clickLink(FVT_ActivitiesObjects.CommActivityOutline_More_ExpandDescription);
		
		// Created a new activity entry comment
		editCommentToActivityEntry(typist, false);

		//Logout
		LogoutAndClose();
		
		verifyImFollowingNewsStory(testUser2," updated the "+PrivateActivityEntry+" entry thread in the "+PrivateCommunityActivity+" activity.","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testEditActivityComment_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testEditActivityComment_PrivateComm" })
	public void testCreateActivityToDo_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityToDo_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + PrivateCommunityActivity);
		
		
		// Create a new to do
		PrivateActivityToDo = addToDoToActivity(PrivateCommunityActivity +" Entry",typist, false, false, testUser1);

		//Logout
		LogoutAndClose();
		
		verifyImFollowingNewsStory(testUser2," created a to-do item named "+PrivateActivityToDo+" in the "+PrivateCommunityActivity+" activity.","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityToDo_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityToDo_PrivateComm" })
	public void testMarkToDoAsCompleted_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkToDoAsCompleted_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + PrivateCommunityActivity);
		
		//Go to Activities To Do Items
		clickLink(FVT_ActivitiesObjects.ToDoItems);
		
		// Mark to do as complete	
		//mouseOverAndWait(FVT_ActivitiesObjects.MarkToDoCompleted);
		clickLink(FVT_ActivitiesObjects.MarkToDoCompleted);

		//Logout
		LogoutAndClose();
		
		verifyImFollowingNewsStory(testUser2,"completed their own "+PrivateActivityToDo+" to-do item in the "+PrivateCommunityActivity+" activity.","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkToDoAsCompleted_PrivateComm");
		
	}
	
	/*
	
	
	@Test(dependsOnMethods = { "testFollowPrivateCommunity" })
	public void testRemoveUserAsMember_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivity_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
		// Created a new community with private access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		clickLink("link="+PrivateCommunity);
	
		clickLink(FVT_CommunitiesObjects.LeftNavOption3);
		
		clickLink("link=Remove");
		
		//Logout
		Logout();
		
		
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivity_PrivateComm");
		
	}
	
	*/
	
}