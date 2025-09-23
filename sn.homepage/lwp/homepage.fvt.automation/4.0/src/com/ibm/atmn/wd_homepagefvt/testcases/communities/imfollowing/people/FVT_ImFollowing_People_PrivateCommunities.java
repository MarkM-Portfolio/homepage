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

package com.ibm.atmn.wd_homepagefvt.testcases.communities.imfollowing.people;


import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;
import static org.testng.AssertJUnit.*;

public class FVT_ImFollowing_People_PrivateCommunities extends FVT_CommunitiesMethods{
	/*
	 * This is a functional test for the Communities Component of IBM Connections
	 * Created By: Adrian Strock
	 * Date: 08/19/2011
	 */

	
	private static String PrivateCommunity = "";
	private static String PrivateCommBookmark = "";
	private static String PrivateCommFeed = "";
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testLoginUsers");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentProfiles);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		Login(testUser1);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser3);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Communities FVT_Level_2 testLoginUsers");
		
	}
	
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testCreatePrivateComm() throws Exception {
			
			
			System.out.println("INFO: Start of Communities FVT_Level_2 testCreatePrivateComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(testUser1);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			// Created a new community with private access
	
			//Now Get the DateTime
			String DateTimeStamp = CommonMethods.genDateBasedRandVal();
			
			//Create a private community
			PrivateCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser3.getEmail());
	
			//Logout
			Logout();
		
			System.out.println("INFO: End of Communities FVT_Level_2 testCreatePrivateComm");
			
		}
	
	
	@Test (dependsOnMethods = { "testCreatePrivateComm" })
	public void testFollowUser() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testFollowUser");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentProfiles);
			
		Login(testUser2);
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		FollowPerson(testUser1.getEmail());
				
		System.out.println("INFO: End of Communities FVT_Level_2 testFollowUser");
		
	}
	
	
	@Test (dependsOnMethods = { "testFollowUser" })
	public void testCreateFeed_PrivateComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreateFeed_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PrivateCommunity);
		
		// Create feed
		PrivateCommFeed = AddFeedsWidgetToOverview( PrivateCommunity + " Feed");

		// Logout
		LogoutAndClose();
		
		verifyNewsStory_TwoFilters(testUser2," added the "+PrivateCommFeed+" feed to the "+PrivateCommunity+" community.","I'm Following","Communities","People", false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateFeed_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testFollowUser" })
	public void testAddBookmark_PrivateComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testAddBookmark_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PrivateCommunity);
		
		// Add Bookmark
		PrivateCommBookmark = AddBookmarkInCommunity( PrivateCommunity + " Bookmark");

		// Logout
		LogoutAndClose();
		
		verifyNewsStory_TwoFilters(testUser2," added the "+PrivateCommBookmark+" bookmark to the "+PrivateCommunity+" community.","I'm Following","Communities","People", false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testAddBookmark_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddBookmark_PrivateComm" })
	public void testUpdateBookmark_PrivateComm() throws Exception {
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testUpdateBookmark_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PrivateCommunity);
		
		// Add Bookmark
		EditBookmark();

		// Logout
		LogoutAndClose();
		
		verifyNewsStory_TwoFilters(testUser2," updated the "+PrivateCommBookmark+" bookmark in the "+PrivateCommunity+" community.","I'm Following","Communities","People", false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testUpdateBookmark_PrivateComm");
		
	}
	
}
