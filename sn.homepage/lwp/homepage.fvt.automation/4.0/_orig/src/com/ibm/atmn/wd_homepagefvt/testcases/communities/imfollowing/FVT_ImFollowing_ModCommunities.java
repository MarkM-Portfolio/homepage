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

import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;
import static org.testng.AssertJUnit.*;

public class FVT_ImFollowing_ModCommunities extends FVT_CommunitiesMethods{
	/*
	 * This is a functional test for the Communities Component of IBM Connections
	 * Created By: Adrian Strock
	 * Date: 08/19/2011
	 */

	private static String ModeratedCommunity = "";
	private static String ModeratedCommBookmark = "";
	private static String ModeratedCommFeed = "";
	
	@Test
	public void testCreateModComm() throws Exception {
		
		
			System.out.println("INFO: Start of Communities FVT_Level_2 testCreateModComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			// Created a new community with moderated access
	
			//Now Get the DateTime
			String DateTimeStamp = CommonMethods.genDateBasedRandVal();
			
			//Create a moderated community
			ModeratedCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.IC_LDAP_UserName451_Typeahead, FVT_CommunitiesObjects.fullUserSearchIdentifier);
			
			//Logout
			Logout();
		
			System.out.println("INFO: End of Communities FVT_Level_2 testCreateModComm");
			
	}
	
	@Test (dependsOnMethods = { "testCreateModComm" })
	public void testFollowModCommunity() throws Exception {
			
			
			System.out.println("INFO: Start of Communities FVT_Level_2 testFollowModCommunity");
	
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_CommunitiesObjects.PublicCommunityView);
			
			clickLink("link="+ModeratedCommunity);
			
			clickLink(FVT_CommunitiesObjects.FollowCommunity);
			
			clickLink(FVT_HomepageObjects.Home);
			
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			//filterBy("Communities");
			
			//Thread.sleep(2000);
			
			//assertTrue("Text not present!",driver.isTextPresent(" created a community named "+ModeratedCommunity+"."));
			
			//Logout
			Logout();
			
			System.out.println("INFO: End of Communities FVT_Level_2 testFollowModCommunity");
			
		}
	
	@Test (dependsOnMethods = { "testFollowModCommunity" })
	public void testCreateFeed_ModComm() throws Exception {
		
			System.out.println("INFO: Start of Communities FVT_Level_2 testCreateFeed_ModComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			// Go to moderated community
			clickLink("link=" + ModeratedCommunity);
			
			// Create feed
			ModeratedCommFeed = AddFeedsWidgetToOverview( ModeratedCommunity + " Feed");
		
			// Logout
			Logout();
			
			driver.close();
			
			verifyNewsStory(" added the "+ModeratedCommFeed+" feed to the "+ModeratedCommunity+" community.","I'm Following","Communities", true);
			
			System.out.println("INFO: End of Communities FVT_Level_2 testCreateFeed_ModComm");
			
		}
	
	@Test (dependsOnMethods = { "testFollowModCommunity" })
	public void testAddBookmark_ModComm() throws Exception {
			
			
		
			System.out.println("INFO: Start of Communities FVT_Level_2 testAddBookmark_ModComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			//Go to moderated community
			clickLink("link=" + ModeratedCommunity);
			
			// Add Bookmark
			ModeratedCommBookmark = AddBookmarkInCommunity( ModeratedCommunity + " Bookmark");
	
			//Logout
			Logout();
			
			driver.close();
			
			verifyNewsStory(" added the "+ModeratedCommBookmark+" bookmark to the "+ModeratedCommunity+" community.","I'm Following","Communities", true);
			
			System.out.println("INFO: End of Communities FVT_Level_2 testAddBookmark_ModComm");
			
		}
	
	@Test (dependsOnMethods = { "testAddBookmark_ModComm" })
	public void testUpdateBookmark_ModComm() throws Exception {
			
			
		
			System.out.println("INFO: Start of Communities FVT_Level_2 testUpdateBookmark_ModComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			//Go to moderated community
			clickLink("link=" + ModeratedCommunity);
			
			//Edit Bookmark
			EditBookmark();
	
			//Logout
			Logout();
			
			driver.close();
			
			verifyNewsStory(" updated the "+ModeratedCommBookmark+" bookmark in the "+ModeratedCommunity+" community.","I'm Following","Communities", true);
			
			System.out.println("INFO: End of Communities FVT_Level_2 testUpdateBookmark_ModComm");
			
		}
	
	
		
}
