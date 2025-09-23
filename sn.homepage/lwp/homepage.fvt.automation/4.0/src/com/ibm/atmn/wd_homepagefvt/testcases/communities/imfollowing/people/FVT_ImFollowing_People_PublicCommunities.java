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
import static org.testng.AssertJUnit.*;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;


public class FVT_ImFollowing_People_PublicCommunities extends FVT_CommunitiesMethods{
	/*
	 * This is a functional test for the Communities Component of IBM Connections
	 * Created By: Johann Ott
	 */

	
	private static String PublicCommunity = "";
	private static String PublicCommBookmark = "";
	private static String PublicCommFeed = "";
	
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
	public void testCreatePublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreatePublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser3.getEmail());

		//Logout
		Logout();
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreatePublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreatePublicComm" })
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
	public void testCreateFeed_PublicComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreateFeed_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		// Create feed
		PublicCommFeed = AddFeedsWidgetToOverview( PublicCommunity + " Feed");

		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_TwoFilters(testUser2," added the "+PublicCommFeed+" feed to the "+PublicCommunity+" community.","I'm Following","Communities","People", true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateFeed_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testFollowUser" })
	public void testAddBookmark_PublicComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testAddBookmark_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		// Add Bookmark
		PublicCommBookmark = AddBookmarkInCommunity( PublicCommunity + " Bookmark");

		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_TwoFilters(testUser2," added the "+PublicCommBookmark+" bookmark to the "+PublicCommunity+" community.","I'm Following","Communities","People", true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testAddBookmark_PublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testAddBookmark_PublicComm" })
	public void testUpdateBookmark_PublicComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testUpdateBookmark_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicCommunity);
		
		// Update Bookmark
		EditBookmark();
		
		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_TwoFilters(testUser2," updated the "+PublicCommBookmark+" bookmark in the "+PublicCommunity+" community.","I'm Following","Communities","People", true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testUpdateBookmark_PublicComm");
		
	}
	
	
}
