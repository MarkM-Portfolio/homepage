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

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;
import static org.testng.AssertJUnit.*;

public class FVT_Discover_PrivateCommunities extends FVT_CommunitiesMethods{
	/*
	 * This is a functional test for the Communities Component of IBM Connections
	 * Created By: Adrian Strock
	 * Date: 08/19/2011
	 */

	
	private static String PrivateCommunity = "";
	private static String PrivateCommBookmark = "";
	private static String PrivateCommFeed = "";

	
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
			PrivateCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+101, FVT_CommunitiesObjects.fullUserSearchIdentifier);
	
			//Logout
			Logout();
			
			driver.close();
			
			verifyNewsStory(" created a community named "+PrivateCommunity+".","Discover","Communities", false);
			
			System.out.println("INFO: End of Communities FVT_Level_2 testCreatePrivateComm");
			
		}
	
	@Test (dependsOnMethods = { "testCreatePrivateComm" })
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
		
		verifyNewsStory(" added the "+PrivateCommBookmark+" bookmark to the "+PrivateCommunity+" community.","Discover","Communities", false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testAddBookmark_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddBookmark_PrivateComm" })
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
		
		verifyNewsStory(" added the "+PrivateCommFeed+" feed to the "+PrivateCommunity+" community.","Discover","Communities", false);
		
		System.out.println("INFO: End of Communities FVT_Level_2 testCreateFeed_PrivateComm");
		
	}
	
}
