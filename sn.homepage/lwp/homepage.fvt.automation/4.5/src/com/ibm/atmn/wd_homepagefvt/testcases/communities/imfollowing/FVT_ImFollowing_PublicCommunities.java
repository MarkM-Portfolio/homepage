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

package com.ibm.atmn.wd_homepagefvt.testcases.communities.imfollowing;


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


public class FVT_ImFollowing_PublicCommunities extends FVT_CommunitiesMethods{
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
		
		Login(testUser2);
		
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
		Logout();
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreatePublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreatePublicComm" })
	public void testFollowPublicCommunity() throws Exception {
			
			
			System.out.println("INFO: Start of Communities FVT_Level_2 testFollowPublicCommunity");
	
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_CommunitiesObjects.PublicCommunityView);
			
			sleep(1000);
			
			clickLink("link="+PublicCommunity);
			
			clickLink(FVT_CommunitiesObjects.FollowCommunity);
			
			//clickLink(FVT_HomepageObjects.Home);
			
			//clickLink(FVT_HomepageObjects.ImFollowing);
			
			//filterBy("Communities");
			
			//Thread.sleep(2000);
			
			//assertTrue("Text not present!",driver.isTextPresent(" created a community named "+PublicCommunity+"."));
			assertTrue("Text not present!",driver.isTextPresent("You are following this community and will receive updates about community content."));
			
			System.out.println("INFO: End of Communities FVT_Level_2 testFollowPublicCommunity");
			
		}
	
	@Test (dependsOnMethods = { "testFollowPublicCommunity" })
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
		
		String CommunityNewsStory = replaceNewsStory(NewsStoryData.ADD_COMMUNITY_FEED, PublicCommFeed, PublicCommunity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2,CommunityNewsStory,FVT_NewsData.COMMUNITIES, true);			
		//verifyNewsStory(testUser2, " added the "+PublicCommFeed+" feed to the "+PublicCommunity+" community.","I'm Following","Communities", true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateFeed_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testFollowPublicCommunity" })
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
		
		String CommunityNewsStory = replaceNewsStory(NewsStoryData.ADD_COMMUNITY_BOOKMARK, PublicCommBookmark, PublicCommunity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2,CommunityNewsStory,FVT_NewsData.COMMUNITIES, true);		
		//verifyNewsStory(testUser2, " added the "+PublicCommBookmark+" bookmark to the "+PublicCommunity+" community.","I'm Following","Communities", true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testAddBookmark_PublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testAddBookmark_PublicComm" })
	public void testUpdateBookmark_PublicComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testUpdateBookmark_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		// Update Bookmark
		EditBookmark();
		
		//Logout
		LogoutAndClose();
		
		String CommunityNewsStory = replaceNewsStory(NewsStoryData.UPDATE_COMMUNITY_BOOKMARK, PublicCommBookmark, PublicCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(testUser2, CommunityNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW, FVT_NewsData.ALL, false);
		verifyNewsStory(testUser2, CommunityNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW, FVT_NewsData.COMMUNITIES, true);
		//verifyNewsStory(testUser2, " updated the "+PublicCommBookmark+" bookmark in the "+PublicCommunity+" community.","I'm Following","Communities", true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testUpdateBookmark_PublicComm");
		
	}
	
	
}
