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

package com.ibm.atmn.wd_homepagefvt.testcases.communities.discover;


import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;


public class FVT_Discover_PublicCommunities extends FVT_CommunitiesMethods{
	/*
	 * This is a functional test for the Communities Component of IBM Connections
	 * Created By: Johann Ott
	 */
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String PublicCommunity = "";
	private static String PublicCommBookmark = "";
	private static String PublicCommFeed = "";
	
	
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testLoginUsers");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);

		Login(testUser3);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
	
		System.out.println("INFO: End of Communities FVT_Level_2 testLoginUsers");
		
	}
	
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testCreatePublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreatePublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser3.getEmail());

		//Logout
		LogoutAndClose();
		
		verifyNewsStory(testUser2, " created a community named "+PublicCommunity+".","Discover","Communities", true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreatePublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreatePublicComm" })
	public void testAddBookmark_PublicComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testAddBookmark_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		// Add Bookmark
		PublicCommBookmark = AddBookmarkInCommunity( PublicCommunity + " Bookmark");

		//Logout
		LogoutAndClose();
		
		verifyNewsStory(testUser2, " added the "+PublicCommBookmark+" bookmark to the "+PublicCommunity+" community.","Discover","Communities", true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testAddBookmark_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreatePublicComm" })
	public void testCreateFeed_PublicComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreateFeed_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		// Create feed
		PublicCommFeed = AddFeedsWidgetToOverview( PublicCommunity + " Feed");

		//Logout
		LogoutAndClose();
		
		verifyNewsStory(testUser2, " added the "+PublicCommFeed+" feed to the "+PublicCommunity+" community.","Discover","Communities", true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateFeed_PublicComm");
		
	}
	

	//@Test (groups = "broken")
	public void testParentItemRename_PublicComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreateFeed_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		// Edit community and rename it 
		
		// Create feed
		PublicCommFeed = AddFeedsWidgetToOverview( PublicCommunity + " Feed");

		//Logout
		LogoutAndClose();
		
		//VerifyNewsStory(DateTimeStamp, "Communities", "Moderated");
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateFeed_PublicComm");
		
	}
	
}
