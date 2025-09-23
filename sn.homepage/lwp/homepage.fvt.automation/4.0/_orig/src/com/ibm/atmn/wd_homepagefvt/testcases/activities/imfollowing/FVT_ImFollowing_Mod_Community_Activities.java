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
import static org.testng.AssertJUnit.*;

import com.ibm.atmn.homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wdbase.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;


public class FVT_ImFollowing_Mod_Community_Activities extends FVT_ActivitiesMethods {
		

		FormInputHandler typist;
	
		private static String ModeratedCommunity = "";
		private static String ModeratedCommunityActivity = "";	
		private static String ModeratedActivityEntry = "";	
		private static String ModActivityToDo= "";
		
	
	@Test
	public void testCreateModComm() throws Exception {
		
	
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a moderated community
		ModeratedCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+101, FVT_CommunitiesObjects.fullUserSearchIdentifier);
		
		//Verify that community, description, image and theme are correct
		//VerifyCommunityDetails("Moderated", CommunitiesData.CommunityName+DateTimeStamp, CommunitiesData.CommunityHandle+DateTimeStamp);
		
		// Create a new activity within this community
		
		//CustomizeCommunity();
		
		//ModeratedCommunityActivity = CreateInitialCommunityActivity(DateTimeStamp);
		
		//Logout
		Logout();
		
		//driver.close();
		
		//VerifyNewsStory(" created a new activity named "+ModeratedCommunityActivity+".","Discover","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateModComm");
		
		
	}
	
	@Test (dependsOnMethods = { "testCreateModComm" })
	public void testFollowModCommunity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowPublicActivity");
	
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_CommunitiesObjects.PublicCommunityView);
			
			clickLink("link="+ModeratedCommunity);
			
			clickLink(FVT_CommunitiesObjects.FollowCommunity);
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowPublicActivity");
			
		}
	
	@Test (dependsOnMethods = { "testFollowModCommunity" })
	public void testCreateActivity_ModComm() throws Exception {
		
	
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivity_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
		clickLink(FVT_CommunitiesObjects.PublicCommunityView);
		
		clickLink("link="+ModeratedCommunity);

		// Create a new activity within this community
		
		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		CustomizeCommunity();
		
		ModeratedCommunityActivity = CreateInitialCommunityActivity(DateTimeStamp);
		
		//Logout
		Logout();
		
		//driver.close();
		
		//VerifyNewsStory(" created a new activity named "+ModeratedCommunityActivity+".","Discover","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivity_ModComm");
		
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateActivity_ModComm" })
	public void testFollowModCommunityActivity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowPublicActivity");
	
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_CommunitiesObjects.PublicCommunityView);
			
			clickLink("link="+ModeratedCommunity);
				
			clickLink("link="+ModeratedCommunityActivity);
						
			clickLink(FVT_ActivitiesObjects.FollowingActions);

			clickLink(FVT_ActivitiesObjects.FollowActivity);
					
			clickLink(FVT_HomepageObjects.Home);
						
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			filterBy("Communities");
			
			Thread.sleep(2000);
			
			assertTrue("Text not present!",driver.isTextPresent(" created an activity named "+ModeratedCommunityActivity+"."));
			
			filterBy("Activities");
			
			Thread.sleep(2000);
			
			assertTrue("Text is present!",driver.isTextPresent(" created an activity named "+ModeratedCommunityActivity+"."));
			
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowPublicActivity");
			
		}
	
	@Test (dependsOnMethods = { "testFollowModCommunityActivity" })
	public void testCreateActivityEntry_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityEntry_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + ModeratedCommunityActivity);
		
		// Created a new activity entry
		ModeratedActivityEntry = addEntryToActivity_Basic(ModeratedCommunityActivity +" Entry",typist, false);

		//Logout
		Logout();
		
		driver.close();
		
		verifyImFollowingNewsStory(" created the "+ModeratedActivityEntry+" entry in the "+ModeratedCommunityActivity+" activity.","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityEntry_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityEntry_ModComm" })
	public void testCreateActivityComment_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityComment_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Open Community
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Expand activity entry
		clickLink(FVT_ActivitiesObjects.CommActivityOutline_More_ExpandDescription);
		
		// Created a new activity entry comment
		addCommentToActivityEntry(typist, false);

		//Logout
		Logout();
		
		driver.close();
		
		verifyImFollowingNewsStory("commented on the "+ModeratedActivityEntry+" entry thread in the "+ModeratedCommunityActivity+" activity.","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityComment_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityComment_ModComm" })
	public void testEditActivityComment_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testEditActivityComment_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + ModeratedCommunityActivity);
		
		//Expand activity entry
		clickLink(FVT_ActivitiesObjects.CommActivityOutline_More_ExpandDescription);
		
		// Created a new activity entry comment
		editCommentToActivityEntry(typist, false);

		//Logout
		Logout();
		
		driver.close();
		
		verifyImFollowingNewsStory("updated the "+ModeratedActivityEntry+" entry thread in the "+ModeratedCommunityActivity+" activity:","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testEditActivityComment_ModComm");
		
	}
	
	
	
	@Test (dependsOnMethods = { "testCreateActivityComment_ModComm" })
	public void testCreateActivityToDo_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityToDo_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + ModeratedCommunityActivity);
		
		
		// Create a new to do
		ModActivityToDo = addToDoToActivity(ModeratedCommunityActivity +" To-Do",typist, false, false);

		//Logout
		Logout();
		
		driver.close();
		
		verifyImFollowingNewsStory(" created a to-do item named "+ModActivityToDo+" in the "+ModeratedCommunityActivity+" activity.","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityToDo_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityToDo_ModComm" })
	public void testMarkToDoAsCompleted_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkToDoAsCompleted_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavActivities);
		
		//Go to community activity
		clickLink("link=" + ModeratedCommunityActivity);
		
		//Go to Activities To Do Items
		clickLink(FVT_ActivitiesObjects.ToDoItems);
		
		// Mark to do as complete	
		//mouseOverAndWait(FVT_ActivitiesObjects.MarkToDoCompleted);
		clickLink(FVT_ActivitiesObjects.MarkToDoCompleted);

		//Logout
		Logout();
		
		driver.close();
		
		verifyImFollowingNewsStory("completed their own "+ModActivityToDo+" to-do item in the "+ModeratedCommunityActivity+" activity.","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkToDoAsCompleted_ModComm");
		
	}
	
}