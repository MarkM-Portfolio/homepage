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
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;
import static org.testng.AssertJUnit.*;

public class FVT_ImFollowing_Tags_PrivateCommunities extends FVT_CommunitiesMethods{
	/*
	 * This is a functional test for the Communities Component of IBM Connections
	 * Created By: Adrian Strock
	 * Date: 08/19/2011
	 */
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String PrivateCommunity = "";
	private static String PrivateCommBookmark = "";
	private static String PrivateCommFeed = "";

	
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
	public void testCreatePrivateComm() throws Exception {
			
			
			System.out.println("INFO: Start of Communities FVT_Level_2 testCreatePrivateComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			// Created a new community with private access
	
			//Now Get the DateTime
			String DateTimeStamp = CommonMethods.genDateBasedRandVal();
			
			//Create a private community
			PrivateCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
	
			//Logout
			LogoutAndClose();
			
			verifyImFollowingTagsNewsStory(testUser2, " created a community named "+PrivateCommunity+".","Communities","automation",false, false);
		
			System.out.println("INFO: End of Communities FVT_Level_2 testCreatePrivateComm");
			
		}
	
	
	@Test (dependsOnMethods = { "testCreatePrivateComm" })
	public void testCreateFeed_PrivateComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreateFeed_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PrivateCommunity);
		
		// Create feed
		PrivateCommFeed = AddFeedsWidgetToOverview( PrivateCommunity + " Feed");

		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " added the "+PrivateCommFeed+" feed to the "+PrivateCommunity+" community.","Communities","automation",false, false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateFeed_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreatePrivateComm" })
	public void testAddBookmark_PrivateComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testAddBookmark_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PrivateCommunity);
		
		// Add Bookmark
		PrivateCommBookmark = AddBookmarkInCommunity( PrivateCommunity + " Bookmark");

		//Logout
		LogoutAndClose();
		
		verifyImFollowingTagsNewsStory(testUser2, " added the "+PrivateCommBookmark+" bookmark to the "+PrivateCommunity+" community.","Communities","automation",false, false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testAddBookmark_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddBookmark_PrivateComm" })
	public void testUpdateBookmark_PrivateComm() throws Exception {
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testUpdateBookmark_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PrivateCommunity);
		
		// Add Bookmark
		EditBookmark();

		//Logout
		Logout();
		
		driver.close();
		
		verifyImFollowingTagsNewsStory(testUser2, " updated the "+PrivateCommBookmark+" bookmark in the "+PrivateCommunity+" community.","Communities","automation",false, false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testUpdateBookmark_PrivateComm");
		
	}
	
}
