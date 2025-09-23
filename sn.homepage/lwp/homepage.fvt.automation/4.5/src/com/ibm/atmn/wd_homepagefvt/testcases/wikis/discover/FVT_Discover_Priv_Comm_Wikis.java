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

package com.ibm.atmn.wd_homepagefvt.testcases.wikis.discover;

import org.junit.*;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisData;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.wikis.FVT_WikisMethods;

	/*
 	* This is the FVT Public Wikis Testcases
	* Created By: Johann Ott
	* Date: 28/11/2011
	*/ 

public class FVT_Discover_Priv_Comm_Wikis extends FVT_WikisMethods{
	
	User testUser1;
	User testUser2;
	User testUser3;
		
		private static String PrivateWikiCommunity = "";
	
		@Test
		public void testLoginUsers() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testLoginUsers");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			testUser1 = userAllocator.getUser(this);
			testUser2 = userAllocator.getUser(this);
			
			Login(testUser2);
			
			sleep(1000);
			
			//Logout
			LogoutAndClose();
			
			System.out.println("INFO: End of Activities FVT_Level_2 testLoginUsers");
			
		}
		
		@Test (dependsOnMethods = { "testLoginUsers" })
		public void testCreateANewWiki_PrivateComm () throws Exception {
			System.out.println("INFO: Start of testCreateANewWiki_PrivateComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(testUser1);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			// Created a new community with Private access

			//Now Get the DateTime
			String DateTimeStamp = CommonMethods.genDateBasedRandVal();
			
			//Create a moderated community
			PrivateWikiCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
				
			//Add the wiki widget
			AddWidgetToOverview("Wikis");
							
			//Logout
			LogoutAndClose();
			
			//Replace news story string
			String WikiNewsStory = replaceNewsStory(NewsStoryData.CREATE_WIKI_COMMUNITY, PrivateWikiCommunity, null, testUser1.getDisplayName());
			
			verifyNewsStory(testUser2,WikiNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.WIKIS, false);
			//verifyNewsStory(testUser2, "A "+PrivateWikiCommunity+" community wiki was created.","Discover","Wikis", false);
		  	
			System.out.println("INFO: End of testCreateANewWiki_PrivateComm");
		}
		
		
		//Create a Private comm wiki page
		@Test (dependsOnMethods = { "testCreateANewWiki_PrivateComm" })
		public void testCreateANewWikiPage_PrivateComm () throws Exception {
			System.out.println("INFO: Start of testCreateANewWikiPage_PrivateComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(testUser1);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
			//Open wiki in community
			openCommunity(PrivateWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Select rootpage to create child pages
			AddAPeer(FVT_WikisData.New_Peer_Page_For_Priv_Comm_Wiki, FVT_WikisData.New_Content_For_Priv_Comm_Wiki);
			
			//Add a new Peer pages to the wiki homepage
			AddAChild(FVT_WikisData.New_Child_Page_For_Priv_Comm_Wiki, FVT_WikisData.New_Content_For_Priv_Comm_Wiki);
			
			//Verify pages have been created
			VerifyPageExists(FVT_WikisData.New_Peer_Page_For_Priv_Comm_Wiki);
			VerifyPageExists(FVT_WikisData.New_Child_Page_For_Priv_Comm_Wiki);
			
			//Logout
			LogoutAndClose();
			
			//Replace news story string
			String WikiNewsStory = replaceNewsStory(NewsStoryData.CREATE_WIKI_PAGE, FVT_WikisData.New_Child_Page_For_Priv_Comm_Wiki, PrivateWikiCommunity, testUser1.getDisplayName());
						
			verifyNewsStory(testUser2,WikiNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.WIKIS, false);
			//verifyNewsStory(testUser2, " created a wiki page named "+FVT_WikisData.New_Peer_Page_For_Priv_Comm_Wiki+" in the "+PrivateWikiCommunity+" wiki.","Discover","Wikis", false);
		  	
			System.out.println("INFO: End of testCreateANewWikiPage_PrivateComm");
		}
		
		@Test (dependsOnMethods = { "testCreateANewWikiPage_PrivateComm" })
		public void testEditAWikiPage_PrivateComm () throws Exception {
			System.out.println("INFO: Start of testEditAWikiPage_PrivateComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
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
			getActiveElement(WikisObjects.Page_Name_Textfield_In_Editor).clear();
			driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).type(FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki);
			
			//Add some content in CKEditor
			AddContentintheEditorforanExistingPage(FVT_WikisData.New_Content_For_Public_Wiki);
			
			//Verify that new page title is correct
			Assert.assertTrue("FAIL: Changed page title isn't correct", driver.isTextPresent(FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki));
			
			//Verify that content was added correctly
			//VerifyAddedContentInHomepage(FVT_WikisData.New_Content_For_Public_Wiki, CommonData.IC_LDAP_Username450);
			
			//Logout
			LogoutAndClose();
			
			//Replace news story string
			String WikiNewsStory = replaceNewsStory(NewsStoryData.EDIT_WIKI_PAGE, FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki, PrivateWikiCommunity, testUser1.getDisplayName());
			
		  	
			verifyNewsStory(testUser2,WikiNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.WIKIS, false);
			//verifyNewsStory(testUser2, " edited the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki+" in the "+PrivateWikiCommunity+" wiki.","Discover","Wikis", false);
			
			System.out.println("INFO: End of testEditAWikiPage_PrivateComm");
		}
		
		@Test (dependsOnMethods = { "testEditAWikiPage_PrivateComm" })
		public void testAddATagToWiki_PrivateComm () throws Exception {
			System.out.println("INFO: Start of testAddATagToWiki_PrivateComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
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
			Assert.assertTrue("FAIL: Tag hasn't been added correctly", driver.isTextPresent(FVT_WikisData.Tag_For_Public_Wiki)); 
			
			//Logout
			LogoutAndClose();
			
			String WikiNewsStory = replaceNewsStory(NewsStoryData.TAG_WIKI_PAGE, FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki, FVT_WikisData.Tag_For_Private_Wiki, PrivateWikiCommunity, testUser1.getDisplayName());
			
			verifyNewsStory(testUser2,WikiNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.WIKIS, false);
			//verifyNewsStory(testUser2, " tagged the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki+" with "+FVT_WikisData.Tag_For_Public_Wiki+" in the "+PrivateWikiCommunity+" wiki.","Discover","Wikis", false);
		  	
			System.out.println("INFO: End of testAddATagToWiki_PrivateComm");
		}
		
		@Test (dependsOnMethods = { "testEditAWikiPage_PrivateComm" })
		public void testRecommendAWiki_PrivateComm () throws Exception {
			System.out.println("INFO: Start of testRecommendAWiki_PrivateComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
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
						
			//Logout
			LogoutAndClose();
			
			//Replace news story string
			String WikiNewsStory = replaceNewsStory(NewsStoryData.LIKE_WIKI_PAGE, FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki, PrivateWikiCommunity, testUser1.getDisplayName());
			
			
			verifyNewsStory(testUser2,WikiNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.WIKIS, false);
			//verifyNewsStory(testUser2, " liked the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki+" in the "+PrivateWikiCommunity+" wiki.","Discover","Wikis", false);
		  	
			System.out.println("INFO: End of testRecommendAWiki_PrivateComm");
		}
		
		@Test (dependsOnMethods = { "testEditAWikiPage_PrivateComm" })
		public void testAddCommentToWiki_PrivateComm () throws Exception {
			System.out.println("INFO: Start of testAddCommentToWiki_PrivateComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
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
			
			//Logout
			LogoutAndClose();
			
			//Replace news story string
			String WikiNewsStory = replaceNewsStory(NewsStoryData.COMMENT_WIKI_PAGE, FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki, PrivateWikiCommunity, testUser1.getDisplayName());
						
			verifyNewsStory(testUser2,WikiNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.WIKIS, false);
			//verifyNewsStory(testUser2, " commented on the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Priv_Comm_Wiki+" in the "+PrivateWikiCommunity+" wiki.","Discover","Wikis", false);
		  	
			System.out.println("INFO: End of testAddCommentToWiki_PrivateComm");
		}
		
		@Test (dependsOnMethods = { "testAddCommentToWiki_PrivateComm" }, groups = { "broken" })
		public void testDeleteWikiPage_PrivateComm () throws Exception {
			System.out.println("INFO: Start of testDeleteWikiPage_PrivateComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(testUser1);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(PrivateWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Go to index
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Child_Page_For_Priv_Comm_Wiki);
			
			//Delete specified page
			deletePage(FVT_WikisData.New_Child_Page_For_Priv_Comm_Wiki);
			
			//Open Page Index to see all available pages
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Verify wiki page has been deleted successfully
			Assert.assertTrue("FAIL: Wiki page still exists", !driver.isTextPresent(FVT_WikisData.New_Child_Page_For_Mod_Comm_Wiki));
			
			//Logout of Wiki
			Logout();	
		  	
			System.out.println("INFO: End of testDeleteWikiPage_PrivateComm");
		}
		
		//@Test (dependsOnMethods = { "testDeleteWikiPage_PrivateComm" })
		public void testDeleteWiki_PrivateComm () throws Exception {
			System.out.println("INFO: Start of testDeleteWiki_PrivateComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(testUser1);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
			//Go to Private community
			clickLink("link=" + PrivateWikiCommunity);
					
			//Recommend the current page
			DeleteCommunityWiki();
	
			//Logout of Wiki
			Logout();	
		  	
			System.out.println("INFO: End of testDeleteWiki_PrivateComm");
		}
		
}
	
		