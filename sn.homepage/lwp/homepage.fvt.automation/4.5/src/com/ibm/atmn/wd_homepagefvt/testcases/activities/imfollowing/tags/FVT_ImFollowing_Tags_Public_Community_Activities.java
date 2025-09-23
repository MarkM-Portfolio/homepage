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

package com.ibm.atmn.wd_homepagefvt.testcases.activities.imfollowing.tags;

/*
 *  TTT Link = Notes://CAMDB01/85257863004CBF81/A3B1F5A7FAF7FB158525703C006F870C/4DF7715F1652A958852579AC0048F714
 *  Last updated on build : Release v4.5.0.0 build SND4.5_20130204-2049
 * 
 */


import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;

import static org.testng.AssertJUnit.*;

public class FVT_ImFollowing_Tags_Public_Community_Activities extends FVT_ActivitiesMethods {
		
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
	public void testFollowTag() throws Exception {
				
				
		System.out.println("INFO: Start of Activities FVT_Level_2 testFollowTag");
				
		typist = new FormInputHandler(driver);
				
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentNews);
				
		Login(testUser2);
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
				
		//Follow Tag	
		followTag(CommonData.AutomationTag);
				
		System.out.println("INFO: End of Activities FVT_Level_2 testFollowTag");
				
	}	

	@Test (dependsOnMethods = { "testFollowTag" })
	public void testCreatePublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreatePublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser1.getEmail());
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreatePublicComm");
		
	}
	

	@Test (dependsOnMethods = { "testCreatePublicComm" })
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
		
		sleep(1000);
		
		clickLink("link="+PublicCommunity);
		
		sleep(1000);
		
		CustomizeCommunity();
		
		// Create a new activity within this community
		PublicCommunityActivity = CreateInitialCommunityActivity(DateTimeStamp);
		
		//Logout
		LogoutAndClose();
		
		Reporter.log("User2 = "+testUser2.getDisplayName()+" | created an activity named "+PublicCommunityActivity+".");
		
		verifyImFollowingTagsNewsStory(testUser2, " created an activity named "+PublicCommunityActivity+".","Activities","automation", true, true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivity_PublicComm");
		
	}
	
	
	
	
	@Test (dependsOnMethods = { "testCreateActivity_PublicComm" })
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
		addEntryToActivity_Basic(PublicCommunityActivity +" Entry",typist, false);

		PublicActivityEntry = PublicCommunityActivity +" Entry";
		
		Reporter.log("Debug ::>  created the "+PublicActivityEntry+" entry in the "+PublicCommunityActivity+" activity.");
		
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, "created the "+PublicActivityEntry+" entry in the "+PublicCommunityActivity+" activity.","Activities","automation", true, true);
		
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
		Reporter.log("Debuging :>  created the "+PublicActivityPrivateEntry+" entry in the "+PublicCommunityActivity+" activity.");
		
		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " created the "+PublicActivityPrivateEntry+" entry in the "+PublicCommunityActivity+" activity.","Activities","automation", true, false);
		
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
		
		// Checking that the comment isn't present on page , look at above TTT link
		verifyImFollowingTagsNewsStory(testUser2, "commented the "+PublicActivityEntry+" entry in the "+PublicCommunityActivity+" activity.","Activities","automation", true, false);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityComment_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityComment_PublicComm" })
	public void testEditActivityComment_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testEditActivityComment_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		smartSleepText("Communities");
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
		
		// Checking that the comment isn't present on page , look at above TTT link
		verifyImFollowingTagsNewsStory(testUser2, "updated the "+PublicActivityEntry+" entry thread in the "+PublicCommunityActivity+" activity.","Activities","automation", true, false);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testEditActivityComment_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testEditActivityComment_PublicComm" })
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
		PublicActivityToDo = addToDoToActivityTags(PublicCommunityActivity +" To-Do", CommonData.AutomationTag, typist, false, false, testUser1);

		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " created a to-do item named "+PublicActivityToDo+" in the "+PublicCommunityActivity+" activity.","Activities","automation", true, true);
		
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
		
		verifyImFollowingTagsNewsStory(testUser2, "completed their own "+PublicActivityToDo+" to-do item in the "+PublicCommunityActivity+" activity.","Activities","automation", true, true);
		
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
		addToDoToActivityTags(PublicCommunityActivity +" to-Do",CommonData.AutomationTag, typist, false, false, testUser1);
		PublicActivityPrivateToDo = PublicCommunityActivity +" to-Do";

		Reporter.log("Debug ::>   created a to-do item named "+PublicActivityPrivateToDo+" in the "+PublicCommunityActivity+" activity.");

		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, "created a to-do item named "+PublicActivityPrivateToDo+" in the "+PublicCommunityActivity+" activity.","Activities","automation", true, true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityToDo_PublicComm");
		
	}
	
	
}