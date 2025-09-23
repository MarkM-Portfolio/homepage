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

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
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
	
	private static String USER_1 = CommonData.IC_LDAP_Username450;
	private static String USER_1_PASS = CommonData.IC_LDAP_Password450;
	private static String USER_1_TYPEAHEAD = CommonData.IC_LDAP_UserName450_Typeahead;
	
	private static String USER_2 = CommonData.IC_LDAP_Username451;
	private static String USER_2_PASS = CommonData.IC_LDAP_Password451;
	private static String USER_2_TYPEAHEAD = CommonData.IC_LDAP_UserName451_Typeahead;

	
	@Test
	public void testCreatePrivateComm() throws Exception {
			
			
			System.out.println("INFO: Start of Communities FVT_Level_2 testCreatePrivateComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			// Created a new community with private access
	
			//Now Get the DateTime
			String DateTimeStamp = CommonMethods.genDateBasedRandVal();
			
			//Create a private community
			PrivateCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.IC_LDAP_UserName451_Typeahead, FVT_CommunitiesObjects.fullUserSearchIdentifier);
	
			//Logout
			Logout();
		
			System.out.println("INFO: End of Communities FVT_Level_2 testCreatePrivateComm");
			
		}
	
	
	@Test (dependsOnMethods = { "testCreatePrivateComm" })
	public void testFollowUser() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testFollowUser");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		FollowPerson(CommonData.IC_LDAP_Username_Fullname450);
				
		System.out.println("INFO: End of Communities FVT_Level_2 testFollowUser");
		
	}
	
	
	@Test (dependsOnMethods = { "testFollowUser" })
	public void testCreateFeed_PrivateComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testCreateFeed_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PrivateCommunity);
		
		// Create feed
		PrivateCommFeed = AddFeedsWidgetToOverview( PrivateCommunity + " Feed");

		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_TwoFilters(USER_2, USER_2_PASS," added the "+PrivateCommFeed+" feed to the "+PrivateCommunity+" community.","I'm Following","Communities","People", true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateFeed_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testFollowPrivateCommunity" })
	public void testAddBookmark_PrivateComm() throws Exception {
		
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testAddBookmark_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PrivateCommunity);
		
		// Add Bookmark
		PrivateCommBookmark = AddBookmarkInCommunity( PrivateCommunity + " Bookmark");

		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_TwoFilters(USER_2, USER_2_PASS," added the "+PrivateCommBookmark+" bookmark to the "+PrivateCommunity+" community.","I'm Following","Communities","People", true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testAddBookmark_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddBookmark_PrivateComm" })
	public void testUpdateBookmark_PrivateComm() throws Exception {
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testUpdateBookmark_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PrivateCommunity);
		
		// Add Bookmark
		EditBookmark();

		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_TwoFilters(USER_2, USER_2_PASS," updated the "+PrivateCommBookmark+" bookmark in the "+PrivateCommunity+" community.","I'm Following","Communities","People", true);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testUpdateBookmark_PrivateComm");
		
	}
	
}
