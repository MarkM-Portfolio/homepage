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

package com.ibm.atmn.wd_homepagefvt.testcases.wikis.imfollowing.people;

import org.junit.*;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisData;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.Data;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.wikis.FVT_WikisMethods;

	/*
 	* This is the FVT Public Wikis Testcases
	* Created By: Johann Ott
	* Date: 28/11/2011
	*/ 

public class FVT_ImFollowing_People_Priv_Comm_Wikis extends FVT_WikisMethods{
	
		User testUser1;
		User testUser2;
		User testUser3;
		
		private static String PrivateWikiCommunity = "";
	
		@Test
		public void testLoginUsers() throws Exception {
			
			
			System.out.println("INFO: Start of Wikis FVT_Level_2 testLoginUsers");
		
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
			
			System.out.println("INFO: End of Wikis FVT_Level_2 testLoginUsers");
			
		}
		
	@Test (dependsOnMethods = { "testLoginUsers" })
		public void testFollowUser() throws Exception {
			
			
			System.out.println("INFO: Start of Wikis FVT_Level_2 testFollowUser");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentProfiles);
	
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			FollowPerson(testUser1.getEmail());
					
			System.out.println("INFO: End of Wikis FVT_Level_2 testFollowUser");
			
		}

		
		@Test (dependsOnMethods = { "testFollowUser" })
		public void testCreateANewWiki_PrivateComm () throws Exception {
			System.out.println("INFO: Start of testCreateANewWiki_PrivateComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);	
			Login(testUser1);
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			// Created a new community with Private access

			//Now Get the DateTime
			String DateTimeStamp = CommonMethods.genDateBasedRandVal();
			
			//Create a moderated community
			PrivateWikiCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser3.getEmail());
				
			//Add the wiki widget
			AddWidgetToOverview("Wikis");
							
			//Logout of Wiki
			LogoutAndClose();
			
			verifyNewsStory_ThreeFilters(testUser2,"A "+PrivateWikiCommunity+" community wiki was created.","I'm Following","Communities","People","Wikis", false);
 	
			System.out.println("INFO: End of testCreateANewWiki_PrivateComm");
		}
		
		
		@Test (dependsOnMethods = { "testCreateANewWiki_PrivateComm" })
		public void testCreateANewWikiPage_PrivateComm () throws Exception {
			System.out.println("INFO: Start of testCreateANewWikiPage_PrivateComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);	
			Login(testUser1);		
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
			//Go to Private community
			clickLink("link=" + PrivateWikiCommunity);
			
			//Go to wikis
			clickLink(FVT_CommunitiesObjects.LeftNavWiki);
			
			//Select rootpage to create child pages
			AddAPeer(FVT_WikisData.New_Peer_Page_For_Priv_Comm_Wiki, FVT_WikisData.New_Content_For_Priv_Comm_Wiki);
			
			//Add a new Peer pages to the wiki homepage
			AddAChild(FVT_WikisData.New_Child_Page_For_Priv_Comm_Wiki, FVT_WikisData.New_Content_For_Priv_Comm_Wiki);
			
			//Verify pages have been created
			VerifyPageExists(FVT_WikisData.New_Peer_Page_For_Priv_Comm_Wiki);
			VerifyPageExists(FVT_WikisData.New_Child_Page_For_Priv_Comm_Wiki);
			
			//Logout of Wiki
			LogoutAndClose();	
			
			verifyNewsStory_ThreeFilters(testUser2," created a wiki page named "+FVT_WikisData.New_Peer_Page_For_Priv_Comm_Wiki+" in the "+PrivateWikiCommunity+" wiki.","I'm Following","Communities","People","Wikis", false);
		  	
			System.out.println("INFO: End of testCreateANewWikiPage_PrivateComm");
		}
		
		@Test (dependsOnMethods = { "testCreateANewWikiPage_PrivateComm" })
		public void testEditAWikiPage_PrivateComm () throws Exception {
			System.out.println("INFO: Start of testEditAWikiPage_PrivateComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);	
			Login(testUser1);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(PrivateWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Priv_Comm_Wiki);
		
			//Edit the current page
			clickLink(WikisObjects.Edit_Button);
			
			//Validate UI of wiki editor
			ValidateWikiEditMode();
			
			//Validate tagging UI
			ValidateTaggingUIInEditMode();
			
			//Edit page title in Edit mode
			driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).clear();
			driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).type(FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki);
			
			//Add some content in CKEditor
			AddContentintheEditorforanExistingPage(FVT_WikisData.New_Content_For_Public_Wiki);
			
			//Verify that new page title is correct
			Assert.assertTrue("FAIL: Changed page title isn't correct", driver.isTextPresent(FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki));
			
			//Verify that content was added correctly
			//VerifyAddedContentInHomepage(FVT_WikisData.New_Content_For_Public_Wiki, CommonData.IC_LDAP_Username450);
			
			//Logout of Wiki
			LogoutAndClose();	
		  	
			verifyNewsStory_ThreeFilters(testUser2," edited the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki+" in the "+PrivateWikiCommunity+" wiki.","I'm Following","Communities","People","Wikis", false);
			
			System.out.println("INFO: End of testEditAWikiPage_PrivateComm");
		}
		
		//@Test (dependsOnMethods = { "testEditAWikiPage_PrivateComm" })
		public void testAddATagToWiki_PrivateComm () throws Exception {
			System.out.println("INFO: Start of testAddATagToWiki_PrivateComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);	
			Login(testUser1);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(PrivateWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Go to index
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki);
			
			//Select 'Add a tag' link
			OpenTaggingEditor();
			
			//Add new tag to page
			AddATag(FVT_WikisData.Tag_For_Public_Wiki);
			
			//Verify tag has been added
			Assert.assertTrue("FAIL: Tag hasn't been added correctly", driver.isTextPresent(Data.Tag_For_Public_Wiki)); 
			
			//Logout of Wiki
			LogoutAndClose();
			
			verifyNewsStory_ThreeFilters(testUser2," tagged the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki+" with "+FVT_WikisData.Tag_For_Public_Wiki+" in the "+PrivateWikiCommunity+" wiki.","I'm Following","Communities","People","Wikis", false);
		  	
			System.out.println("INFO: End of testAddATagToWiki_PrivateComm");
		}
		
		@Test (dependsOnMethods = { "testEditAWikiPage_PrivateComm" })
		public void testRecommendAWiki_PrivateComm () throws Exception {
			System.out.println("INFO: Start of testRecommendAWiki_PrivateComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);	
			Login(testUser1);		
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(PrivateWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Go to index
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki);
			
			//Like the current page
			LikeCurrentPage();
						
			//Logout of Wiki
			LogoutAndClose();	
			
			verifyNewsStory_ThreeFilters(testUser2," liked the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki+" in the "+PrivateWikiCommunity+" wiki.","I'm Following","Communities","People","Wikis", false);
		  	
			System.out.println("INFO: End of testRecommendAWiki_PrivateComm");
		}
		
		@Test (dependsOnMethods = { "testEditAWikiPage_PrivateComm" })
		public void testAddCommentToWiki_PrivateComm () throws Exception {
			System.out.println("INFO: Start of testAddCommentToWiki_PrivateComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);	
			Login(testUser1);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(PrivateWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Go to index
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki);
			
			//Open Comments Page
			clickLink(FVT_WikisObjects.Comments_Tab);
			
			//Add a comment
			AddAComment(FVT_WikisData.Comment_For_Priv_Comm_Wiki);
	
			//Verify added comment
			VerifyCommentIsAdded(FVT_WikisData.Comment_For_Priv_Comm_Wiki);
			
			//Logout of Wiki
			LogoutAndClose();
			
			verifyNewsStory_ThreeFilters(testUser2," commented on the wiki page Re: "+FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki+" in the "+PrivateWikiCommunity+" wiki.","I'm Following","Communities","People","Wikis", false);
		  	
			System.out.println("INFO: End of testAddCommentToWiki_PrivateComm");
		}
		
		@Test (dependsOnMethods = { "testAddCommentToWiki_PrivateComm" })
		public void testEditCommentWiki_PrivateComm () throws Exception {
			System.out.println("INFO: Start of testEditCommentWiki_PrivateComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);	
			Login(testUser1);		
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(PrivateWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Go to index
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki);
			
			//Open Comments Page
			clickLink(FVT_WikisObjects.Comments_Tab);
			
			//Add a comment
			editComment(FVT_WikisData.Comment_For_Priv_Comm_Wiki);
	
			//Verify added comment
			VerifyCommentIsAdded(FVT_WikisData.Comment_For_Priv_Comm_Wiki);
			
			//Logout of Wiki
			LogoutAndClose();	
			
			verifyNewsStory_ThreeFilters(testUser2,"updated the wiki page Re: "+FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki+" in the "+PrivateWikiCommunity+" wiki.","I'm Following","Communities","People","Wikis", false);
		  	
			System.out.println("INFO: End of testEditCommentWiki_PrivateComm");
		}
		
}
	
		