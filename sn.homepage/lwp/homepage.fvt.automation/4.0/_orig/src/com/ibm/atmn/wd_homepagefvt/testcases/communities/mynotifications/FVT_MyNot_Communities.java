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
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
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
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+101, FVT_CommunitiesObjects.fullUserSearchIdentifier);

		InviteMembersToExistingCommunity(FVT_CommunitiesObjects.InviteCommunityMembersTypeAhead,CommonData.IC_LDAP_UserName451_Typeahead, FVT_CommunitiesObjects.selectedUserIdentifier_Invite, FVT_CommunitiesObjects.fullUserSearchIdentifier_Invite);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_INVITE_FROM_ME, PublicCommunity, null, CommonData.IC_LDAP_Username_Fullname451 );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_INVITE_FOR_ME, PublicCommunity, null, CommonData.IC_LDAP_Username_Fullname450 );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username450,CommonData.IC_LDAP_Password450,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.COMMUNITIES, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username451,CommonData.IC_LDAP_Password451,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.COMMUNITIES, true);
		
		System.out.println("INFO: End of Communities FVT testInviteToJoinComm");
		
	}
	
	
	//@Test
	public void testRequestToJoinComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT testRequestToJoinComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with mod access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		ModCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+101, FVT_CommunitiesObjects.fullUserSearchIdentifier);
		
		//Logout
		LogoutAndClose();
		
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		requestToJoin(ModCommunity);
		
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_REQUEST_FROM_ME, ModCommunity, null, CommonData.IC_LDAP_Username_Fullname451 );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_REQUEST_FOR_ME, ModCommunity, null, CommonData.IC_LDAP_Username_Fullname451 );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username451,CommonData.IC_LDAP_Password451,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.COMMUNITIES, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username450,CommonData.IC_LDAP_Password450,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.COMMUNITIES, true);
		
		System.out.println("INFO: End of Communities FVT testRequestToJoinComm");
		
	}
	
	//@Test
	public void testAddMemberToComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT testAddMemberToComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicCommunity2 = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+101, FVT_CommunitiesObjects.fullUserSearchIdentifier);

		AddMembersToExistingCommunity("Members",FVT_CommunitiesObjects.AddMembersToExistingTypeAheadWithoutCom,CommonData.IC_LDAP_UserName451_Typeahead, FVT_CommunitiesObjects.Add_To_ExistingMembers_Textfield_TypeaheadWithoutCom, FVT_CommunitiesObjects.fullUserSearchIdentifier1);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_ADD_MEMBER_FROM_ME, PublicCommunity2, null, CommonData.IC_LDAP_Username_Fullname451 );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_ADD_MEMBER_FOR_ME, PublicCommunity2, null, CommonData.IC_LDAP_Username_Fullname450 );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username450,CommonData.IC_LDAP_Password450,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.COMMUNITIES, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username451,CommonData.IC_LDAP_Password451,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.COMMUNITIES, true);
		
		System.out.println("INFO: End of Communities FVT testAddMemberToComm");
		
	}
	
	//@Test (dependsOnMethods = { "testAddMemberToComm" })
	public void testRemoveMemberToComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT testRemoveMemberToComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		openCommunity(PublicCommunity2, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavMembers);
	
		RemoveMembersToExistingCommunity(CommonData.IC_LDAP_Username_Fullname451);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_REMOVE_MEMBER_FROM_ME, PublicCommunity2, null, CommonData.IC_LDAP_Username_Fullname451 );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_REMOVE_MEMBER_FOR_ME, PublicCommunity2, null, CommonData.IC_LDAP_Username_Fullname450 );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username450,CommonData.IC_LDAP_Password450,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.COMMUNITIES, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username451,CommonData.IC_LDAP_Password451,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.COMMUNITIES, true);
		
		System.out.println("INFO: End of Communities FVT testRemoveMemberToComm");
		
	}
	
	@Test
	public void testInviteToEvent() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT testInviteToEvent");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicEventCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.IC_LDAP_UserName451_Typeahead, FVT_CommunitiesObjects.fullUserSearchIdentifier);

		AddWidgetToOverview("Events");
				
		CalendarEvent = createCalendarEntry(PublicEventCommunity+" Calender Event" ,false, true,CommonData.IC_LDAP_Username_Fullname451);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_EVENT_INVITE_FROM_ME, CalendarEvent, PublicEventCommunity, CommonData.IC_LDAP_Username_Fullname451 );
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_COMMUNITY_EVENT_INVITE_FOR_ME, CalendarEvent, PublicEventCommunity, CommonData.IC_LDAP_Username_Fullname450 );
		
		//verify from me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username450,CommonData.IC_LDAP_Password450,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.COMMUNITIES, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username451,CommonData.IC_LDAP_Password451,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.COMMUNITIES, true);
		
		System.out.println("INFO: End of Communities FVT testInviteToEvent");
		
	}
	
	
}
