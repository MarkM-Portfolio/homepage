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

import org.junit.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
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

public class FVT_Discover_Pub_Comm_Wikis extends FVT_WikisMethods{
	
		User testUser1;
		User testUser2;
		//User testUser3;
	
		private static String PublicWikiCommunity = "";

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
		public void testCreateANewWiki_PublicComm () throws Exception {
			System.out.println("INFO: Start of testCreateANewWiki_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(testUser1);				
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			// Created a new community with Public access

			//Now Get the DateTime
			String DateTimeStamp = CommonMethods.genDateBasedRandVal();
			
			//Create a Public community
			PublicWikiCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
				
			//Add the wiki widget
			AddWidgetToOverview("Wikis");
							
			//Logout
			LogoutAndClose();
			
			verifyNewsStory(testUser2,"A "+PublicWikiCommunity+" community wiki was created.","Discover","Wikis", true);
		  	
			System.out.println("INFO: End of testCreateANewWiki_PublicComm");
		}
		
		
		//Create a moderated comm wiki page
		@Test (dependsOnMethods = { "testCreateANewWiki_PublicComm" })
		public void testCreateANewWikiPage_PublicComm () throws Exception {
			System.out.println("INFO: Start of testCreateANewWikiPage_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(testUser1);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(PublicWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Select rootpage to create child pages
			AddAPeer(FVT_WikisData.New_Peer_Page_For_Pub_Comm_Wiki, FVT_WikisData.New_Content_For_Pub_Comm_Wiki);
			
			//Add a new Peer pages to the wiki homepage
			AddAChild(FVT_WikisData.New_Child_Page_For_Pub_Comm_Wiki, FVT_WikisData.New_Content_For_Pub_Comm_Wiki);
			
			//Verify pages have been created
			VerifyPageExists(FVT_WikisData.New_Peer_Page_For_Pub_Comm_Wiki);
			VerifyPageExists(FVT_WikisData.New_Child_Page_For_Pub_Comm_Wiki);
			
				
			//Logout
			LogoutAndClose();
			
			verifyNewsStory(testUser2," created a wiki page named "+FVT_WikisData.New_Peer_Page_For_Pub_Comm_Wiki+" in the "+PublicWikiCommunity+" wiki.","Discover","Wikis", true);
		  	
			System.out.println("INFO: End of testCreateANewWikiPage_PublicComm");
		}
		
		@Test (dependsOnMethods = { "testCreateANewWikiPage_PublicComm" })
		public void testEditAWikiPage_PublicComm () throws Exception {
			
			System.out.println("INFO: Start of testEditAWikiPage_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(testUser1);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(PublicWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Pub_Comm_Wiki);
		
			//Edit the current page
			clickLink(WikisObjects.Edit_Button);
			
			//Validate UI of wiki editor
			ValidateWikiEditMode();
			
			//Validate tagging UI
			ValidateTaggingUIInEditMode();
			
			//Edit page title in Edit mode
			driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).clear();
			driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).type(FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki);
			
			//Add some content in CKEditor
			AddContentintheEditorforanExistingPage(FVT_WikisData.New_Content_For_Public_Wiki);
			
			//Verify that new page title is correct
			Assert.assertTrue("FAIL: Changed page title isn't correct", driver.isTextPresent(FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki));
			
			//Verify that content was added correctly
			//VerifyAddedContentInHomepage(FVT_WikisData.New_Content_For_Public_Wiki, CommonData.IC_LDAP_Username450);
			
				
			//Logout
			LogoutAndClose();
			
			verifyNewsStory(testUser2," edited the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki+" in the "+PublicWikiCommunity+" wiki.","Discover","Wikis", true);
		  	
			System.out.println("INFO: End of testEditAWikiPage_PublicComm");
		}
		
		@Test (dependsOnMethods = { "testEditAWikiPage_PublicComm" })
		public void testAddATagToWiki_PublicComm () throws Exception {
			System.out.println("INFO: Start of testAddATagToWiki_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(testUser1);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(PublicWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Go to index
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki);
			
			//Select 'Add a tag' link
			OpenTaggingEditor();
			
			//Add new tag to page
			AddATag(FVT_WikisData.Tag_For_Public_Wiki);
			
			//Verify tag has been added
			Assert.assertTrue("FAIL: Tag hasn't been added correctly", driver.isTextPresent(FVT_WikisData.Tag_For_Public_Wiki)); 
			
			//Logout
			LogoutAndClose();
			
			verifyNewsStory(testUser2," tagged the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki+" with "+FVT_WikisData.Tag_For_Public_Wiki+" in the "+PublicWikiCommunity+" wiki.","Discover","Wikis", false);
		  	
			System.out.println("INFO: End of testAddATagToWiki_PublicComm");
		}
		
		@Test (dependsOnMethods = { "testEditAWikiPage_PublicComm" })
		public void testRecommendAWiki_PublicComm () throws Exception {
			System.out.println("INFO: Start of testRecommendAWiki_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(testUser1);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(PublicWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Go to index
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki);
			
			//Recommend the current page
			LikeCurrentPage();
						
			//Logout
			LogoutAndClose();
		  	
			verifyNewsStory(testUser2," liked the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki+" in the "+PublicWikiCommunity+" wiki.","Discover","Wikis", true);
			
			System.out.println("INFO: End of testRecommendAWiki_PublicComm");
		}
		
		@Test (dependsOnMethods = { "testEditAWikiPage_PublicComm" })
		public void testAddCommentToWiki_PublicComm () throws Exception {
			System.out.println("INFO: Start of testAddCommentToWiki_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(testUser1);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(PublicWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Go to index
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki);
			
			//Open Comments Page
			clickLink(FVT_WikisObjects.Comments_Tab);
			
			//Add a comment
			AddAComment(FVT_WikisData.Comment_For_Pub_Comm_Wiki);
	
			//Verify added comment
			VerifyCommentIsAdded(FVT_WikisData.Comment_For_Pub_Comm_Wiki);
			
			//Logout
			LogoutAndClose();
			
			verifyNewsStory(testUser2," commented on the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki+" in the "+PublicWikiCommunity+" wiki.","Discover","Wikis", true);
		  	
			System.out.println("INFO: End of testAddCommentToWiki_PublicComm");
		}
		
		@Test (dependsOnMethods = { "testAddCommentToWiki_PublicComm" }, groups = "broken")
		public void testDeleteWikiPage_PublicComm () throws Exception {
			System.out.println("INFO: Start of testDeleteWikiPage_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(testUser1);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(PublicWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Go to index
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Child_Page_For_Pub_Comm_Wiki);
			
			//Delete specified page
			deletePage(FVT_WikisData.New_Child_Page_For_Pub_Comm_Wiki);
			
			//Open Page Index to see all available pages
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Verify wiki page has been deleted successfully
			Assert.assertTrue("FAIL: Wiki page still exists", !driver.isTextPresent(FVT_WikisData.New_Child_Page_For_Mod_Comm_Wiki));
			
			//Logout of Wiki
			Logout();	
		  	
			System.out.println("INFO: End of testDeleteWikiPage_PublicComm");
		}
		
		//@Test (dependsOnMethods = { "testDeleteWikiPage_PublicComm" }, groups = "broken")
		public void testDeleteWiki_PublicComm () throws Exception {
			System.out.println("INFO: Start of testDeleteWiki_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(testUser1);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
			//Go to Public community
			clickLink("link=" + PublicWikiCommunity);
					
			//Recommend the current page
			DeleteCommunityWiki();
	
			//Logout of Wiki
			Logout();	
		  	
			System.out.println("INFO: End of testDeleteWiki_PublicComm");
		}
		
}
	
		