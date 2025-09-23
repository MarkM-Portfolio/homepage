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

import org.junit.Assert;
import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisData;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.wikis.FVT_WikisMethods;

	/*
 	* This is the FVT Public Wikis Testcases
	* Created By: Johann Ott
	* Date: 28/11/2011
	*/ 

public class FVT_ImFollowing_People_Pub_Stand_Wikis extends FVT_WikisMethods{
	
	
		User testUser1;
		User testUser2;
		User testUser3;

	
		//Create a public wiki
	
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
		
			System.out.println("INFO: End of Wikis FVT_Level_2 testLoginUsers");
			
		}
		
	@Test (dependsOnMethods = { "testLoginUsers" })
		public void testFollowUser() throws Exception {
			
			
			System.out.println("INFO: Start of Wikis FVT_Level_2 testFollowUser");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentProfiles);
			
			//Login(testUser1);
			Login(testUser2);
			
			FollowPerson(testUser1.getEmail());
					
			System.out.println("INFO: End of Wikis FVT_Level_2 testFollowUser");
			
		}
		
		
		@Test (dependsOnMethods = { "testFollowUser" })
		public void testCreateANewPublicWiki () throws Exception {
			System.out.println("INFO: Start of testCreateANewPublicWiki");	
			
			//Load the component		
			LoadComponent(CommonObjects.ComponentWikis);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(testUser1);			
			
			//Click Start a Wiki button
			clickLink(WikisObjects.Start_New_Wiki_Button);
	
			//Create a new public wiki
			typeInTextField(FVT_WikisObjects.Wiki_Name_Field, FVT_WikisData.CI_Box_Public_Wiki);
	
			//Click Save button
			clickLink(FVT_WikisObjects.Save_Button);
	
			//Verify homepage UI
			verifyNewHomePageUI(FVT_WikisData.CI_Box_Public_Wiki, testUser1.getDisplayName(), "");
				
			//Logout of Wiki
		  	LogoutAndClose();
			
			//
			verifyNewsStory_TwoFilters(testUser2," created a wiki named "+FVT_WikisData.CI_Box_Public_Wiki+".","I'm Following","Wikis","People", true);
			
			System.out.println("INFO: End of testCreateANewPublicWiki");
		}
		
		
		@Test (dependsOnMethods = { "testCreateANewPublicWiki" })
		public void testAddPagesToPublicWiki () throws Exception {
			System.out.println("INFO: Start of testAddPagesToPublicWiki");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);	
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(FVT_WikisData.CI_Box_Public_Wiki);
	
			//Select rootpage to create child pages
			AddAPeer(FVT_WikisData.New_Peer_Page_For_Public_Wiki, FVT_WikisData.New_Content_For_Public_Wiki_Peer);
			
			//Add a new Peer pages to the wiki homepage
			AddAChild(FVT_WikisData.New_Child_Page_For_Public_Wiki, FVT_WikisData.New_Content_For_Public_Wiki_Child);
			
			//Verify pages have been created
			VerifyPageExists(FVT_WikisData.New_Peer_Page_For_Public_Wiki);
			VerifyPageExists(FVT_WikisData.New_Child_Page_For_Public_Wiki);
			 
			//Logout of Wiki
		  	LogoutAndClose();
			
			//
			verifyNewsStory_TwoFilters(testUser2," created a wiki page named "+FVT_WikisData.New_Peer_Page_For_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.","I'm Following","Wikis","People", true);
				
			System.out.println("INFO: End of testAddPagesToPublicWiki");
		}
		
		
		//Edit a page
		
		@Test (dependsOnMethods = { "testAddPagesToPublicWiki" })
		public void testEditPageInPublicWiki () throws Exception {
			System.out.println("INFO: Start of testEditPageInPublicWiki");	
	
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);		
						
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(FVT_WikisData.CI_Box_Public_Wiki);
	
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Public_Wiki);
		
			//Edit the current page
			clickLink(WikisObjects.Edit_Button);
			
			//Validate UI of wiki editor
			ValidateWikiEditMode();
			
			//Validate tagging UI
			ValidateTaggingUIInEditMode();
			
			//Edit page title in Edit mode
			driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).clear();
			driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).type(FVT_WikisData.New_Peer_Page_For_Public_Wiki2);
			
			//Add some content in CKEditor
			AddContentintheEditorforanExistingPage(FVT_WikisData.New_Content_For_Public_Wiki);
			
			//Verify that new page title is correct
			//Assert.assertTrue("FAIL: Changed page title isn't correct", driver.isElementPresent(Data.New_Peer_Page_For_Public_Wiki2));
			
			//Verify that content was added correctly
			//VerifyAddedContentInHomepage(FVT_WikisData.New_Content_For_Public_Wiki, CommonData.IC_LDAP_Username450);
			
			//Logout of Wiki
		  	LogoutAndClose();
		  	
		  	verifyNewsStory_TwoFilters(testUser2," edited the wiki page "+FVT_WikisData.New_Peer_Page_For_Public_Wiki2+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.","I'm Following","Wikis","People", true);

			System.out.println("INFO: End of testEditPageInPublicWiki");
		}
		
		
		//Add a tag
		
		@Test (dependsOnMethods = { "testAddPagesToPublicWiki" })
		public void testAddATagToPublicWiki() throws Exception {
			System.out.println("INFO: Start of testAddATagToPublicWiki");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);	
						
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(FVT_WikisData.CI_Box_Public_Wiki);
			
			//Select 'Add a tag' link
			OpenTaggingEditor();
			
			//Add new tag to page
			AddATag(FVT_WikisData.Tag_For_Public_Wiki);
			
			//Verify tag has been added
			Assert.assertTrue("FAIL: Tag hasn't been added correctly", driver.isTextPresent(FVT_WikisData.Tag_For_Public_Wiki)); 
			
			//Logout of Wiki
		  	LogoutAndClose();
		  	
		  	verifyNewsStory_TwoFilters(testUser2," tagged the wiki page Welcome to "+FVT_WikisData.CI_Box_Public_Wiki+" with "+FVT_WikisData.Tag_For_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.","I'm Following","Wikis","People", false);

			System.out.println("INFO: End of testAddATagToPublicWiki");
		}
		
		
		//Recommend a page
		
		@Test (dependsOnMethods = { "testAddPagesToPublicWiki" })
		public void testRecommendAPageForPublicWiki () throws Exception {
			System.out.println("INFO: Start of testRecommendAPageForPublicWiki");	
	
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);	
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(FVT_WikisData.CI_Box_Public_Wiki);
	
			//Like Wiki Page
			LikeCurrentPage();
		
			//Logout of Wiki
		  	LogoutAndClose();
		  	
		  	verifyNewsStory_TwoFilters(testUser2," liked the wiki page Welcome to "+FVT_WikisData.CI_Box_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.","I'm Following","Wikis","People", true);
		  	
			System.out.println("INFO: End of testRecommendAPageForPublicWiki");
		}
		
		//Add a comment
		
		@Test (dependsOnMethods = { "testAddPagesToPublicWiki" })
		public void testAddCommentToPublicWiki () throws Exception {
			System.out.println("INFO: Start of testAddCommentToPublicWiki");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);	
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(FVT_WikisData.CI_Box_Public_Wiki);
			
			//Open Comments Page
			clickLink(FVT_WikisObjects.Comments_Tab);
			
			//Add a comment
			AddAComment(FVT_WikisData.Comment_For_Public_Wiki);
	
			//Verify added comment
			VerifyCommentIsAdded(FVT_WikisData.Comment_For_Public_Wiki);
			
			//Logout of Wiki
		  	LogoutAndClose();
		  	
		  	verifyNewsStory_TwoFilters(testUser2," commented on the wiki page Welcome to "+FVT_WikisData.CI_Box_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.","I'm Following","Wikis","People", true);

			System.out.println("INFO: End of testAddCommentToPublicWiki");
		}
		
		@Test (dependsOnMethods = { "testAddCommentToPublicWiki" })
		public void testEditCommentPublicWiki () throws Exception {
			System.out.println("INFO: Start of testEditCommentPublicWiki");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);	
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(FVT_WikisData.CI_Box_Public_Wiki);
			
			//Open Comments Page
			clickLink(FVT_WikisObjects.Comments_Tab);
			
			//Add a comment
			editComment(FVT_WikisData.Comment_For_Public_Wiki);
	
			//Verify added comment
			VerifyCommentIsAdded(FVT_WikisData.Comment_For_Public_Wiki);
			
		  	//Logout of Wiki
		  	LogoutAndClose();
		  
		  	verifyNewsStory_TwoFilters(testUser2,"updated the wiki page Re: Welcome to "+FVT_WikisData.CI_Box_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.","I'm Following","Wikis","People", true);

			System.out.println("INFO: End of testEditCommentPublicWiki");
		}
		
		

		
}
	
		