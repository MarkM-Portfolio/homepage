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

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;
import com.ibm.atmn.waffle.extensions.user.User;


public class FVT_Discover_Community_Calendar_Private extends FVT_CommunitiesMethods{
	/*
	 * This is a functional test for the Communities Component of IBM Connections
	 * Created By: Johann Ott
	 */

	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String PrivateCommunity = "";
	private static String CalenderEvent = "";
	private static String RepeatingCalenderEvent = "";
	
	
	@Test
	public void testCreateCalenderEventPart1_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreateCalenderEvent_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a private community
		PrivateCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());

		//Logout
		Logout();
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateCalenderEvent_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateCalenderEventPart1_PrivateComm" })
	public void testCreateCalenderEventPart2_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreateCalenderEvent_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser2);
		
		//Login(testUser2);
		
		clickLink("link=I'm a Member");
		
		clickLink("link="+PrivateCommunity);
		
		clickLink(FVT_CommunitiesObjects.FollowCommunity);
		
		assertTrue("User could not follow community!",driver.isTextPresent("Stop Following this Community"));
		
		//Logout
		Logout();
	
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateCalenderEvent_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateCalenderEventPart2_PrivateComm" })
	public void testCreateCalenderEventPart3_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreateCalenderEvent_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);

		//Login(testUser1);
		
		clickLink("link="+PrivateCommunity);
		
		AddWidgetToOverview("Calendar");
		
		CalenderEvent = createCalendarEntry(PrivateCommunity+" Calender Event" ,false,false, null);
		
		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		Logout();
		
		//driver.close();
		
		//VerifyNewsStory(testUser2,"","Discover","Communities",true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateCalenderEvent_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateCalenderEventPart3_PrivateComm" })
	public void testEventEntryUpdated_PrivateComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testEventEntryUpdated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PrivateCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavCalendar);
		
		// Update Event
		clickLink("link=" + CalenderEvent);
		
		updateCalendarEntry(CalenderEvent, false, false);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		Logout();
		
		//driver.close();
		
		//VerifyNewsStory(testUser2,"","Discover","Communities",true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testEventEntryUpdated_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testEventEntryUpdated_PrivateComm" })
	public void testEventCommentCreated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testEventCommentCreated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser2);
		//Login(testUser2);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PrivateCommunity, FVT_CommunitiesObjects.ImAMember, FVT_CommunitiesObjects.LeftNavCalendar);
		
		//
		clickLink("link="+CalenderEvent);
		
		// Comment on Event
		AddACommentToEventEntry();

		//VerifyNewsStory_SameUser("","All Updates","All",true);
		
		//VerifyNewsStory_SameUser("","All Updates","Communities",true);
		
		//Logout
		Logout();
		
		//driver.close();
		
		//VerifyNewsStory(testUser1,"","Discover","Communities",true);
		
		//driver.close();
		
		//VerifyNewsStory(testUser1,"","My Notifications","Communities",true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testEventCommentCreated_PrivateComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateCalenderEventPart3_PrivateComm" })
	public void testSeriesEntryCreated_PrivateComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testSeriesEntryCreated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to public community
		clickLink("link=" + PrivateCommunity);
		
		//Start repeating event
		RepeatingCalenderEvent = createCalendarEntry(PrivateCommunity+" Repeating Calender Event" ,true,false, null);
		
		//
		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		Logout();
		
		//driver.close();
		
		//VerifyNewsStory(testUser2,"","Discover","Communities",true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testSeriesEntryCreated_PrivateComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testSeriesEntryCreated_PrivateComm" })
	public void testSeriesEntryUpdated_PrivateComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testSeriesEntryUpdated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PrivateCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavCalendar);
		
		// Update Event
		clickLink("link=" + RepeatingCalenderEvent);
		
		updateCalendarEntry(RepeatingCalenderEvent, true, true);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		Logout();
		
		//driver.close();
		
		//VerifyNewsStory(testUser2,"","Discover","Communities",true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testSeriesEntryUpdated_PrivateComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testSeriesEntryUpdated_PrivateComm" })
	public void testSeriesEntryInstanceUpdated_PrivateComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testSeriesEntryInstanceUpdated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PrivateCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavCalendar);
		
		// Update Event
		clickLink("link=" + RepeatingCalenderEvent);
		
		updateCalendarEntry(RepeatingCalenderEvent, true, false);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		Logout();
		
		//driver.close();
		
		//VerifyNewsStory(testUser2,"","Discover","Communities",true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testSeriesEntryInstanceUpdated_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testSeriesEntryInstanceUpdated_PrivateComm" })
	public void testEventEntryCommentDeleted_PrivateComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testEventEntryCommentDeleted_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PrivateCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavCalendar);
		
		// Update Event
		clickLink("link=" + CalenderEvent);
		
		// Delete comment
		clickLink(FVT_CommunitiesObjects.DeleteComment);
		
		clickLink(FVT_CommunitiesObjects.DeleteConfirm);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		Logout();
		
		//driver.close();
		
		//VerifyNewsStory(testUser2,"","Discover","Communities",true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testEventEntryCommentDeleted_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testEventEntryCommentDeleted_PrivateComm" })
	public void testEventEntryDeleted_PrivateComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testEventEntryDeleted_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PrivateCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavCalendar);
		
		// Update Event
		clickLink("link=" + CalenderEvent);
		
		// Delete event
		clickLink(FVT_CommunitiesObjects.MoreActions);
		
		clickLink(FVT_CommunitiesObjects.DeleteEvent);
		
		clickLink(FVT_CommunitiesObjects.DeleteEventConfirm);

		//VerifyNewsStory_SameUser("","Discover","All",true);
		
		//VerifyNewsStory_SameUser("","Discover","Communities",true);
		
		//Logout
		Logout();
		
		//driver.close();
		
		//VerifyNewsStory(testUser2,"","Discover","Communities",true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testEventEntryDeleted_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testEventEntryDeleted_PrivateComm" })
	public void testSeriesEntryInstanceDeleted_PrivateComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testSeriesEntryInstanceDeleted_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PrivateCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavCalendar);
		
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
		Logout();
		
		//driver.close();
		
		//VerifyNewsStory(testUser2,"","Discover","Communities",true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testSeriesEntryInstanceDeleted_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testSeriesEntryInstanceDeleted_PrivateComm" })
	public void testSeriesEntryDeleted_PrivateComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testSeriesEntryDeleted_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community 
		openCommunity(PrivateCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavCalendar);
		
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
		Logout();
		
		//driver.close();
		
		//VerifyNewsStory(testUser2,"","Discover","Communities",true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testSeriesEntryDeleted_PrivateComm");
		
	}
	
}
