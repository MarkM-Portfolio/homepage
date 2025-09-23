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

package com.ibm.atmn.wd_homepagefvt.testcases.communities.mynotifications;


import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;


public class FVT_MyNot_Communities extends FVT_CommunitiesMethods{
	/*
	 * This is a functional test for the Communities Component of IBM Connections
	 * Created By: Johann Ott
	 */
	User testUser1;
	User testUser2;
	User testUser3;
	User testUser4;
	User testUser5;
	User testUser6;
	User testUser7;
	User testUser8;
	
	
	private static String PublicCommunity = "";
	private static String PublicCommunity2 = "";
	private static String PublicEventCommunity = "";
	private static String CalendarEvent = "";
	private static String ModCommunity = "";
	
	//@Test
	public void testInviteToJoinComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT testInviteToJoinComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead,testUser2.getEmail());

		InviteMembersToExistingCommunity(FVT_CommunitiesObjects.InviteCommunityMembersTypeAhead,testUser1.getDisplayName(), FVT_CommunitiesObjects.selectedUserIdentifier_Invite, FVT_CommunitiesObjects.fullUserSearchIdentifier_Invite);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_INVITE_FROM_ME, PublicCommunity, null, testUser2.getDisplayName() );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_INVITE_FOR_ME, PublicCommunity, null, testUser1.getDisplayName() );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(testUser1,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.COMMUNITIES, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(testUser2,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.COMMUNITIES, true);
		
		System.out.println("INFO: End of Communities FVT testInviteToJoinComm");
		
	}
	
	
	//@Test
	public void testRequestToJoinComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT testRequestToJoinComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		testUser3 = userAllocator.getUser(this);
		testUser4 = userAllocator.getUser(this);
		
		Login(testUser3);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with mod access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		ModCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
		
		//Logout
		LogoutAndClose();
		
		LoadComponent(CommonObjects.ComponentCommunities);
		
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser4);	
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		requestToJoin(ModCommunity);
		
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_REQUEST_FROM_ME, ModCommunity, null, testUser4.getDisplayName() );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_REQUEST_FOR_ME, ModCommunity, null, testUser3.getDisplayName() );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(testUser4,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.COMMUNITIES, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(testUser3,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.COMMUNITIES, true);
		
		System.out.println("INFO: End of Communities FVT testRequestToJoinComm");
		
	}
	
	//@Test
	public void testAddMemberToComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT testAddMemberToComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		testUser5 = userAllocator.getUser(this);
		testUser6 = userAllocator.getUser(this);
		
		Login(testUser5);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicCommunity2 = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser6.getEmail());

		AddMembersToExistingCommunity("Members",FVT_CommunitiesObjects.AddMembersToExistingTypeAheadWithoutCom,testUser1.getDisplayName(), FVT_CommunitiesObjects.Add_To_ExistingMembers_Textfield_TypeaheadWithoutCom, FVT_CommunitiesObjects.fullUserSearchIdentifier1);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_ADD_MEMBER_FROM_ME, PublicCommunity2, null, testUser6.getDisplayName() );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_ADD_MEMBER_FOR_ME, PublicCommunity2, null, testUser5.getDisplayName() );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(testUser5,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.COMMUNITIES, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(testUser6,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.COMMUNITIES, true);
		
		System.out.println("INFO: End of Communities FVT testAddMemberToComm");
		
	}
	
	//@Test (dependsOnMethods = { "testAddMemberToComm" })
	public void testRemoveMemberToComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT testRemoveMemberToComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser5);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		openCommunity(PublicCommunity2, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavMembers);
	
		RemoveMembersToExistingCommunity(testUser6.getDisplayName());
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_REMOVE_MEMBER_FROM_ME, PublicCommunity2, null, testUser6.getDisplayName() );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_REMOVE_MEMBER_FOR_ME, PublicCommunity2, null, testUser5.getDisplayName() );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(testUser5,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.COMMUNITIES, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(testUser6,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.COMMUNITIES, true);
		
		System.out.println("INFO: End of Communities FVT testRemoveMemberToComm");
		
	}
	
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testLoginUsers");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		testUser7 = userAllocator.getUser(this);
		testUser8 = userAllocator.getUser(this);
		
		Login(testUser8);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
	
		System.out.println("INFO: End of Communities FVT_Level_2 testLoginUsers");
		
	}
	
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testInviteToEvent() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT testInviteToEvent");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser7);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicEventCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser8.getEmail());

		AddWidgetToOverview("Events");
				
		CalendarEvent = createCalendarEntry(PublicEventCommunity+" Calender Event" ,false, true,testUser8.getDisplayName());
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_EVENT_INVITE_FROM_ME, CalendarEvent, PublicEventCommunity, testUser8.getDisplayName() );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_EVENT_INVITE_FOR_ME, CalendarEvent, PublicEventCommunity, testUser7.getDisplayName() );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(testUser7,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.COMMUNITIES, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(testUser8,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.COMMUNITIES, true);
		
		System.out.println("INFO: End of Communities FVT testInviteToEvent");
		
	}
	
	
}
