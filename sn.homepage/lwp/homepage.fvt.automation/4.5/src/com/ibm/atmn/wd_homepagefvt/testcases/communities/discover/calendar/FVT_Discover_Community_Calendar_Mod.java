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


public class FVT_Discover_Community_Calendar_Mod extends FVT_CommunitiesMethods{
	/*
	 * This is a functional test for the Communities Component of IBM Connections
	 * Created By: Johann Ott
	 */

	//User testUser1;
	//User testUser2;
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String ModeratedCommunity = "";
	private static String CalenderEvent = "";
	private static String RepeatingCalenderEvent = "";
	
	
	@Test
	public void testCreateCalenderEventPart1_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreateCalenderEventPart1_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		Login(testUser1);
		//testUser1 = userAllocator.getUser();
		//testUser2 = userAllocator.getUser();
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		ModeratedCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());

		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateCalenderEventPart1_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateCalenderEventPart1_ModComm" })
	public void testCreateCalenderEventPart2_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreateCalenderEventPart2_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser2);
		
		//Login(testUser2);
		
		clickLink(FVT_CommunitiesObjects.ImAMember);
		
		communitySmartSleep(FVT_CommunitiesObjects.ImAMember, "link="+ModeratedCommunity);
				
		clickLink(FVT_CommunitiesObjects.FollowCommunity);
		
		assertTrue("User could not follow community!",driver.isTextPresent("Stop Following this Community"));
		
		//Logout
		LogoutAndClose();
	
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateCalenderEventPart2_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateCalenderEventPart2_ModComm" })
	public void testCreateCalenderEventPart3_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreateCalenderEventPart3_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);

		//Login(testUser1);
		
		clickLink("link="+ModeratedCommunity);
		
		AddWidgetToOverview("Events");
		
		CalenderEvent = createCalendarEntry(ModeratedCommunity+" Calender Event" ,false ,false, null);
		
		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		LogoutAndClose();
		
		//Verify NewsStory
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.CREATE_COMMUNITY_CALENDAR_EVENT, CalenderEvent, ModeratedCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateCalenderEventPart3_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateCalenderEventPart3_ModComm" })
	public void testEventEntryUpdated_ModComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testEventEntryUpdated_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavEvents);
		
		// Update Event
		clickLink("link=" + CalenderEvent);
		
		updateCalendarEntry(CalenderEvent, false, false);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		LogoutAndClose();
		
		//Verify NewsStory
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.UPDATE_COMMUNITY_CALENDAR_EVENT, CalenderEvent, ModeratedCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, true);

		
		System.out.println("INFO: End of Communities FVT_Level_2 testEventEntryUpdated_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testEventEntryUpdated_ModComm" })
	public void testEventCommentCreated_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testEventCommentCreated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser2);
		//Login(testUser2);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAMember, FVT_CommunitiesObjects.LeftNavEvents);
		
		if(!isTextPresent(CalenderEvent)){
			clickLink("css=[title='Show Past Events']");
			}
		//
		clickLink("link="+CalenderEvent);
		
		// Comment on Event
		AddACommentToEventEntry();

		//VerifyNewsStory_SameUser("","All Updates","All",true);
		
		//VerifyNewsStory_SameUser("","All Updates","Communities",true);
		
		//Logout
		LogoutAndClose();
		
		//Verify NewsStory
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.COMMENT_COMMUNITY_CALENDAR_EVENT, CalenderEvent, ModeratedCommunity, testUser2.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testEventCommentCreated_PublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testEventCommentCreated_PublicComm" })
	public void testSeriesEntryCreated_ModComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testSeriesEntryCreated_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + ModeratedCommunity);
		
		if(!driver.isElementPresent(FVT_CommunitiesObjects.LeftNavEvents))
		{
			AddWidgetToOverview("Events");
		}
		
		//Start repeating event
		RepeatingCalenderEvent = createCalendarEntry(ModeratedCommunity+" Repeating Calender Event" ,true,false, null, "link=" + ModeratedCommunity);
		
		//
		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		LogoutAndClose();
		
		//Verify NewsStory
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.CREATE_COMMUNITY_CALENDAR_REPEATING_EVENT, RepeatingCalenderEvent, ModeratedCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, true);
	
		System.out.println("INFO: End of Communities FVT_Level_2 testSeriesEntryCreated_ModComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testSeriesEntryCreated_ModComm" })
	public void testSeriesEntryUpdated_ModComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testSeriesEntryUpdated_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavEvents);
		
		// Update Event
		clickLink("link=" + RepeatingCalenderEvent);
		
		updateCalendarEntry(RepeatingCalenderEvent, true, true);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		LogoutAndClose();
		
		//Verify NewsStory
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.UPDATE_COMMUNITY_CALENDAR_REPEATING_EVENT, RepeatingCalenderEvent, ModeratedCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testSeriesEntryUpdated_ModComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testSeriesEntryUpdated_ModComm" })
	public void testSeriesEntryInstanceUpdated_ModComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testSeriesEntryInstanceUpdated_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavEvents);
		
		// Update Event
		clickLink("link=" + RepeatingCalenderEvent);
		
		updateCalendarEntry(RepeatingCalenderEvent, true, false);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		LogoutAndClose();
		
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.UPDATE_COMMUNITY_CALENDAR_EVENT_INSTANCE, RepeatingCalenderEvent, ModeratedCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testSeriesEntryInstanceUpdated_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testSeriesEntryInstanceUpdated_ModComm" })
	public void testEventEntryCommentDeleted_ModComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testEventEntryCommentDeleted_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavEvents);
		
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
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.COMMENT_COMMUNITY_CALENDAR_EVENT, CalenderEvent, ModeratedCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testEventEntryCommentDeleted_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testEventEntryCommentDeleted_ModComm" })
	public void testEventEntryDeleted_ModComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testEventEntryDeleted_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavEvents);
		
		if(!isTextPresent(CalenderEvent)){
			clickLink("css=[title='Show Past Events']");
			}
		
		// Update Event
		clickLink("link=" + CalenderEvent);
		
		// Delete event
		smartSleep(FVT_CommunitiesObjects.MoreActions);
		
		clickLink(FVT_CommunitiesObjects.MoreActions);
		
		clickLink(FVT_CommunitiesObjects.DeleteEvent);
		
		clickLink(FVT_CommunitiesObjects.DeleteEventConfirm);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		LogoutAndClose();
		
		//Verify NewsStory
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.CREATE_COMMUNITY_CALENDAR_REPEATING_EVENT, RepeatingCalenderEvent, ModeratedCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testEventEntryDeleted_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testEventEntryDeleted_ModComm" })
	public void testSeriesEntryInstanceDeleted_ModComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testSeriesEntryInstanceDeleted_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavEvents);
		
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
		
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.UPDATE_COMMUNITY_CALENDAR_EVENT_INSTANCE, RepeatingCalenderEvent, ModeratedCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testSeriesEntryInstanceDeleted_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testSeriesEntryInstanceDeleted_ModComm" })
	public void testSeriesEntryDeleted_ModComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testSeriesEntryDeleted_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		openCommunity(ModeratedCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavEvents);
		
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
		String DiscoverNewstory = replaceNewsStory(NewsStoryData.CREATE_COMMUNITY_CALENDAR_REPEATING_EVENT, RepeatingCalenderEvent, ModeratedCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2,DiscoverNewstory, FVT_NewsData.DISCOVER_VIEW, CommonObjects.ComponentCommunities, false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testSeriesEntryDeleted_ModComm");
		
	}
	
}
