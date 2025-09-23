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

package com.ibm.atmn.homepagefvt.testcases.activities;




import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.homepagefvt.tasks.activities.FVT_ActivitiesMethods;

import static org.testng.AssertJUnit.*;

public class FVT_Level2_Public_Community_Activities extends FVT_ActivitiesMethods {
		

		private FormInputHandler typist = getFormInputHandler();
		
	
		
		private static String PublicCommunity = "";	
		private static String PublicCommunityActivity = "";	
		private static String PublicActivityEntry = "";
		private static String PublicActivityPrivateEntry = "";
		private static String PublicActivityToDo= "";
		private static String PublicActivityPrivateToDo= "";
		

	@Test
	public void testCreateActivity_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivity_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
			
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+101, FVT_CommunitiesObjects.fullUserSearchIdentifier);
		
		//Verify that community, description, image and theme are correct
		//VerifyCommunityDetails("Public", CommunitiesData.CommunityName+DateTimeStamp, CommunitiesData.CommunityHandle+DateTimeStamp);
	
		
		CustomizeCommunity();
		
		// Create a new activity within this community
		PublicCommunityActivity = CreateInitialCommunityActivity(DateTimeStamp);
		
		//Logout
		Logout();
		
		VerifyNewsStory(" created a new activity named "+PublicCommunityActivity+".","Discover","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivity_PublicComm");
		
	}
	

	@Test (dependsOnMethods = { "testCreateActivity_PublicComm" })
	public void testCreateActivityEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		//Activities Left Nav
		clickLink("link=Activities");
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		// Created a new activity entry
		PublicActivityEntry = addEntryToActivity_Basic(PublicCommunityActivity +" Entry",typist, false);

		//Logout
		Logout();
		
		VerifyNewsStory(" created the "+PublicActivityEntry+" entry in the "+PublicCommunityActivity+" activity.","Discover","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityEntry_PublicComm" })
	public void testCreateActivityPrivateEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityPrivateEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		//Activities Left Nav
		clickLink("link=Activities");
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		// Created a new activity entry
		PublicActivityPrivateEntry = addEntryToActivity_Basic(PublicCommunityActivity +" Private Entry",typist, true);

		//Logout
		Logout();
		
		VerifyNewsStory(" created the "+PublicActivityPrivateEntry+" entry in the "+PublicCommunityActivity+" activity.","Discover","Activities", false);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityPrivateEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityPrivateEntry_PublicComm" })
	public void testCreateActivityComment_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityComment_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		//Activities Left Nav
		clickLink("link=Activities");
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		//Expand activity entry
		clickLink(FVT_ActivitiesObjects.ActivityOutline_More_ExpandDescription);
		
		// Created a new activity entry comment
		addCommentToActivityEntry(typist, false);

		//Logout
		Logout();
		
		VerifyNewsStory(" commented on their own "+PublicActivityEntry+" entry in the "+PublicCommunityActivity+" activity.","Discover","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityComment_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityComment_PublicComm" })
	public void testCreateActivityPrivateComment_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityPrivateComment_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		//Activities Left Nav
		clickLink("link=Activities");
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		//Expand activity entry
		clickLink(FVT_ActivitiesObjects.ActivityOutline_More_ExpandDescription);
		
		// Created a new activity entry private comment
		addCommentToActivityEntry(typist, true);

		//Logout
		Logout();
		
		//VerifyNewsStory(" commented on their own "+PublicActivityEntry+" entry in the "+PublicCommunityActivity+" activity.","Discover","Activities", false);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityPrivateComment_PublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateActivityPrivateComment_PublicComm" })
	public void testCreateActivityToDo_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityToDo_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		//Activities Left Nav
		clickLink("link=Activities");
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		
		// Create a new to do
		PublicActivityToDo = addToDoToActivity(PublicCommunityActivity +" To-Do",typist, false, false);

		//Logout
		Logout();
		
		VerifyNewsStory(" created a to-do item named "+PublicActivityToDo+" in the "+PublicCommunityActivity+" activity.","Discover","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityToDo_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateActivityToDo_PublicComm" })
	public void testMarkToDoAsCompleted_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkToDoAsCompleted_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		//Activities Left Nav
		clickLink("link=Activities");
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		//Go to Activities To Do Items
		clickLink(FVT_ActivitiesObjects.ToDoItems);
		
		// Mark to do as complete	
		mouseOverAndWait(FVT_ActivitiesObjects.MarkToDoCompleted);
		cautiousClick(FVT_ActivitiesObjects.MarkToDoCompleted);

		//Logout
		Logout();
		
		VerifyNewsStory(" completed their own "+PublicActivityToDo+" to-do item in the "+PublicCommunityActivity+" activity.","Discover","Activities", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkToDoAsCompleted_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testMarkToDoAsCompleted_PublicComm" })
	public void testCreateActivityPrivateToDo_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateActivityToDo_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		//Activities Left Nav
		clickLink("link=Activities");
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		
		// Create a new to do
		PublicActivityPrivateToDo = addToDoToActivity(PublicCommunityActivity +" to-Do",typist, true, false);

		//Logout
		Logout();
		
		VerifyNewsStory(" created a to-do item named "+PublicActivityPrivateToDo+" in the "+PublicCommunityActivity+" activity.","Discover","Activities", false);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateActivityToDo_PublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateActivityPrivateToDo_PublicComm" })
	public void testMarkPrivateToDoAsCompleted_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkPrivateToDoAsCompleted_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		//Activities Left Nav
		clickLink("link=Activities");
		
		//Go to community activity
		clickLink("link=" + PublicCommunityActivity);
		
		//Go to Activities To Do Items
		clickLink(FVT_ActivitiesObjects.ToDoItems);
		
		// Mark to do as complete	
		mouseOverAndWait(FVT_ActivitiesObjects.MarkToDoCompleted);
		cautiousClick(FVT_ActivitiesObjects.MarkToDoCompleted);

		//Logout
		Logout();
		
		VerifyNewsStory(" completed their own "+PublicActivityPrivateToDo+" to-do item in the "+PublicCommunityActivity+" activity.","Discover","Activities", false);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkPrivateToDoAsCompleted_PublicComm");
		
	}
	
	
	
}