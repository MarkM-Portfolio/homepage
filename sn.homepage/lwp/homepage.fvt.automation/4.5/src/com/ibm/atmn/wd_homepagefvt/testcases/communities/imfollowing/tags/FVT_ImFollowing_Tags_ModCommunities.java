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

package com.ibm.atmn.wd_homepagefvt.testcases.communities.imfollowing.tags;


import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;
import static org.testng.AssertJUnit.*;

public class FVT_ImFollowing_Tags_ModCommunities extends FVT_CommunitiesMethods{
	/*
	 * This is a functional test for the Communities Component of IBM Connections
	 * Created By: Adrian Strock
	 * Date: 08/19/2011
	 */
	User testUser1;
	User testUser2;
	User testUser3;

	private static String ModeratedCommunity = "";
	private static String ModeratedCommBookmark = "";
	private static String ModeratedCommFeed = "";
	
	
	@Test 
	public void testFollowTag() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowTag");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentNews);
				
			testUser1 = userAllocator.getUser(this);
			testUser2 = userAllocator.getUser(this);
			testUser3 = userAllocator.getUser(this);
			
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			//Follow Tag
			followTag(CommonData.AutomationTag);
			
			//Logout
			LogoutAndClose();
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testFollowTag");
			
		}
	
	@Test (dependsOnMethods = { "testFollowTag" })
	public void testCreateModComm() throws Exception {
		
		
			System.out.println("INFO: Start of Communities FVT_Level_2 testCreateModComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			// Created a new community with moderated access
	
			//Now Get the DateTime
			String DateTimeStamp = CommonMethods.genDateBasedRandVal();
			
			//Create a moderated community
			ModeratedCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
			
			//Logout
			Logout();
			
			//driver.close();
			
			String ImFollowingTagsNewsStory = replaceNewsStory(NewsStoryData.CREATE_COMMUNITY, ModeratedCommunity, null, testUser1.getDisplayName());
			
			
			
			verifyImFollowingTagsNewsStory(testUser2, ImFollowingTagsNewsStory, FVT_NewsData.TAGS, FVT_CommunitiesData.CommunityTag, false, true);
			//verifyImFollowingTagsNewsStory(" created a community named "+ModeratedCommunity+".","Communities","automation",false, true);
		
			System.out.println("INFO: End of Communities FVT_Level_2 testCreateModComm");
			
	}
	
	@Test (dependsOnMethods = { "testCreateModComm" })
	public void testCreateFeed_ModComm() throws Exception {
		
			System.out.println("INFO: Start of Communities FVT_Level_2 testCreateFeed_ModComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			// Go to moderated community
			clickLink("link=" + ModeratedCommunity);
			
			// Create feed
			ModeratedCommFeed = AddFeedsWidgetToOverview( ModeratedCommunity + " Feed");
		
			//Logout
			LogoutAndClose();
			
			String ImFollowingTagsNewsStory = replaceNewsStory(NewsStoryData.ADD_COMMUNITY_FEED, ModeratedCommFeed, ModeratedCommunity, testUser1.getDisplayName());
			
			verifyImFollowingTagsNewsStory(testUser2, ImFollowingTagsNewsStory, FVT_NewsData.COMMUNITIES, CommonData.AutomationTag, false, true);
			//verifyImFollowingTagsNewsStory(testUser2, " added the "+ModeratedCommFeed+" feed to the "+ModeratedCommunity+" community.","Communities","automation",false, true);
			
			System.out.println("INFO: End of Communities FVT_Level_2 testCreateFeed_ModComm");
			
		}
	
	@Test (dependsOnMethods = { "testCreateModComm" })
	public void testAddBookmark_ModComm() throws Exception {
			
			
		
			System.out.println("INFO: Start of Communities FVT_Level_2 testAddBookmark_ModComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			//Go to moderated community
			clickLink("link=" + ModeratedCommunity);
			
			// Add Bookmark
			ModeratedCommBookmark = AddBookmarkInCommunity( ModeratedCommunity + " Bookmark");
	
			//Logout
			LogoutAndClose();
			
			String ImFollowingTagsNewsStory = replaceNewsStory(NewsStoryData.ADD_COMMUNITY_BOOKMARK, ModeratedCommBookmark, ModeratedCommunity, testUser1.getDisplayName());
			
			verifyImFollowingTagsNewsStory(testUser2, ImFollowingTagsNewsStory, FVT_NewsData.COMMUNITIES, CommonData.AutomationTag, false, true);
			//verifyImFollowingTagsNewsStory(testUser2, " added the "+ModeratedCommBookmark+" bookmark to the "+ModeratedCommunity+" community.","Communities","automation",false, true);
			
			System.out.println("INFO: End of Communities FVT_Level_2 testAddBookmark_ModComm");
			
		}
	
	@Test (dependsOnMethods = { "testAddBookmark_ModComm" })
	public void testUpdateBookmark_ModComm() throws Exception {
			
			
		
			System.out.println("INFO: Start of Communities FVT_Level_2 testUpdateBookmark_ModComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			//Go to moderated community
			clickLink("link=" + ModeratedCommunity);
			
			//Edit Bookmark
			EditBookmark();
	
			//Logout
			LogoutAndClose();
			
			String ImFollowingTagsNewsStory = replaceNewsStory(NewsStoryData.UPDATE_COMMUNITY_BOOKMARK, ModeratedCommBookmark, ModeratedCommunity, testUser1.getDisplayName());
			
			verifyImFollowingTagsNewsStory(testUser2, ImFollowingTagsNewsStory, FVT_NewsData.COMMUNITIES, CommonData.AutomationTag, false, true);
			//verifyImFollowingTagsNewsStory(testUser2, " updated the "+ModeratedCommBookmark+" bookmark in the "+ModeratedCommunity+" community.","Communities","automation",false, true);
			
			System.out.println("INFO: End of Communities FVT_Level_2 testUpdateBookmark_ModComm");
			
		}
	
	
		
}
