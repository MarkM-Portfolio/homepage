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

package com.ibm.atmn.wd_homepagefvt.testcases.communities.discover.calendar;


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


public class FVT_Discover_Community_Calendar_Public extends FVT_CommunitiesMethods{
	/*
	 * This is a functional test for the Communities Component of IBM Connections
	 * Created By: Johann Ott
	 */

	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String PublicCommunity = "";
	private static String CalenderEvent = "";
	private static String RepeatingCalenderEvent = "";
	
	
	@Test
	public void testCreateCalenderEventPart1_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreateCalenderEvent_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());

		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateCalenderEvent_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateCalenderEventPart1_PublicComm" })
	public void testCreateCalenderEventPart2_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreateCalenderEvent_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		clickLink(FVT_CommunitiesObjects.ImAMember);
		
		clickLink("link="+PublicCommunity);
		
		clickLink(FVT_CommunitiesObjects.FollowCommunity);
		
		assertTrue("User could not follow community!",driver.isTextPresent("Stop Following this Community"));
		
		//Logout
		LogoutAndClose();
	
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateCalenderEvent_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateCalenderEventPart2_PublicComm" })
	public void testCreateCalenderEventPart3_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreateCalenderEvent_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);

		//Login(testUser1);
		
		clickLink("link="+PublicCommunity);
		
		AddWidgetToOverview("Events");
		
		CalenderEvent = createCalendarEntry(PublicCommunity+" Calender Event" ,false,false, null);
		
		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		LogoutAndClose();
		
		//Verify NewsStory
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.CREATE_COMMUNITY_CALENDAR_EVENT, CalenderEvent, PublicCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateCalenderEvent_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateCalenderEventPart3_PublicComm" })
	public void testEventEntryUpdated_PublicComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testEventEntryUpdated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavEvents);
		
		// Update Event
		clickLink("link=" + CalenderEvent);
		
		updateCalendarEntry(CalenderEvent, false, false);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		LogoutAndClose();
		
		//Verify NewsStory
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.UPDATE_COMMUNITY_CALENDAR_EVENT, CalenderEvent, PublicCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testEventEntryUpdated_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testEventEntryUpdated_PublicComm" })
	public void testEventCommentCreated_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testEventCommentCreated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAMember, FVT_CommunitiesObjects.LeftNavEvents);
		
		//
		clickLink("link="+CalenderEvent);
		
		// Comment on Event
		AddACommentToEventEntry();

		//VerifyNewsStory_SameUser("","All Updates","All",true);
		
		//VerifyNewsStory_SameUser("","All Updates","Communities",true);
		
		//Logout
		LogoutAndClose();
		
		//Verify NewsStory
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.COMMENT_COMMUNITY_CALENDAR_EVENT, CalenderEvent, PublicCommunity, testUser2.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testEventCommentCreated_PublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateCalenderEventPart3_PublicComm" })
	public void testSeriesEntryCreated_PublicComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testSeriesEntryCreated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		//Start repeating event
		RepeatingCalenderEvent = createCalendarEntry(PublicCommunity+" Repeating Calender Event" ,true,false, null, "link=" + PublicCommunity);
		
		//
		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		LogoutAndClose();
		
		//Verify NewsStory
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.CREATE_COMMUNITY_CALENDAR_REPEATING_EVENT, RepeatingCalenderEvent, PublicCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testSeriesEntryCreated_PublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testSeriesEntryCreated_PublicComm" })
	public void testSeriesEntryUpdated_PublicComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testSeriesEntryUpdated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavEvents);
		
		// Update Event
		clickLink("link=" + RepeatingCalenderEvent);
		
		updateCalendarEntry(RepeatingCalenderEvent, true, true);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		LogoutAndClose();
		
		//Verify NewsStory
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.UPDATE_COMMUNITY_CALENDAR_REPEATING_EVENT, RepeatingCalenderEvent, PublicCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testSeriesEntryUpdated_PublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testSeriesEntryUpdated_PublicComm" })
	public void testSeriesEntryInstanceUpdated_PublicComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testSeriesEntryInstanceUpdated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavEvents);
		
		// Update Event
		clickLink("link=" + RepeatingCalenderEvent);
		
		updateCalendarEntry(RepeatingCalenderEvent, true, false);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		LogoutAndClose();
		
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.UPDATE_COMMUNITY_CALENDAR_EVENT_INSTANCE, RepeatingCalenderEvent, PublicCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testSeriesEntryInstanceUpdated_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testSeriesEntryInstanceUpdated_PublicComm" })
	public void testEventEntryCommentDeleted_PublicComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testEventEntryCommentDeleted_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavEvents);
		
		if(!isTextPresent(CalenderEvent)){
			clickLink("css=[title='Show Past Events']");
			}
		
		// Update Event
		clickLink("link=" + CalenderEvent);
		
		// Delete comment
		clickLink(FVT_CommunitiesObjects.DeleteComment);
		
		clickLink(FVT_CommunitiesObjects.DeleteConfirm);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		LogoutAndClose();
		
		//Verify NewsStory
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.COMMENT_COMMUNITY_CALENDAR_EVENT, CalenderEvent, PublicCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testEventEntryCommentDeleted_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testEventEntryCommentDeleted_PublicComm" })
	public void testEventEntryDeleted_PublicComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testEventEntryDeleted_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavEvents);
		
		if(!isTextPresent(CalenderEvent)){
			clickLink("css=[title='Show Past Events']");
			}
		
		// Update Event
		clickLink("link=" + CalenderEvent);
		
		// Delete event
		clickLink(FVT_CommunitiesObjects.MoreActions);
		
		clickLink(FVT_CommunitiesObjects.DeleteEvent);
		
		clickLink(FVT_CommunitiesObjects.DeleteEventConfirm);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		LogoutAndClose();
		
		//Verify NewsStory
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.CREATE_COMMUNITY_CALENDAR_REPEATING_EVENT, RepeatingCalenderEvent, PublicCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testEventEntryDeleted_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testEventEntryDeleted_PublicComm" })
	public void testSeriesEntryInstanceDeleted_PublicComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testSeriesEntryInstanceDeleted_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavEvents);
		
		// Update Event
		clickLink("link=" + RepeatingCalenderEvent);
		
		// Delete event instance
		clickLink(FVT_CommunitiesObjects.MoreActions);
		
		clickLink(FVT_CommunitiesObjects.DeleteEvent);
		
		clickLink(FVT_CommunitiesObjects.DeleteInstanceCheckbox);
		
		clickLink(FVT_CommunitiesObjects.DeleteEventConfirm);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		LogoutAndClose();
		
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.UPDATE_COMMUNITY_CALENDAR_EVENT_INSTANCE, RepeatingCalenderEvent, PublicCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testSeriesEntryInstanceDeleted_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testSeriesEntryInstanceDeleted_PublicComm" })
	public void testSeriesEntryDeleted_PublicComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testSeriesEntryDeleted_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PublicCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavEvents);
		
		// Update Event
		clickLink("link=" + RepeatingCalenderEvent);
		
		// Delete event instance
		clickLink(FVT_CommunitiesObjects.MoreActions);
		
		clickLink(FVT_CommunitiesObjects.DeleteEvent);
		
		clickLink(FVT_CommunitiesObjects.DeleteSeriesCheckbox);
		
		clickLink(FVT_CommunitiesObjects.DeleteEventConfirm);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		LogoutAndClose();
		
		//Verify NewsStory
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.CREATE_COMMUNITY_CALENDAR_REPEATING_EVENT, RepeatingCalenderEvent, PublicCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testSeriesEntryDeleted_PublicComm");
		
	}
	
}
