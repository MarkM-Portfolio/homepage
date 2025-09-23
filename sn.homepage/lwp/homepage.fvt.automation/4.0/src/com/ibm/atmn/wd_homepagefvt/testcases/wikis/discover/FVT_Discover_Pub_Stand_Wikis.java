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


import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.Data;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisData;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.wikis.FVT_WikisMethods;

	/*
 	* This is the FVT Public Wikis Testcases
	* Created By: Johann Ott
	* Date: 28/11/2011
	*/ 

public class FVT_Discover_Pub_Stand_Wikis extends FVT_WikisMethods{
	
		
		User testUser1;
		User testUser2;
		User testUser3;
		
		//Create a public wiki
	
		@Test
		public void testCreateANewPublicWiki () throws Exception {
			System.out.println("INFO: Start of testCreateANewPublicWiki");	
			
			//Load the component		
			LoadComponent(CommonObjects.ComponentWikis);
				
			testUser1 = userAllocator.getUser(this);
			testUser2 = userAllocator.getUser(this);
			testUser3 = userAllocator.getUser(this);
			
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
				
			//Logout
			LogoutAndClose();
			
			//
			verifyNewsStory(testUser2, " created a wiki named "+FVT_WikisData.CI_Box_Public_Wiki+".","Discover","Wikis", true);

			System.out.println("INFO: End of testCreateANewPublicWiki");
		}
		
		
		//Add a page
		
		@Test (dependsOnMethods = { "testCreateANewPublicWiki" })
		public void testAddPagesToPublicWiki () throws Exception {
			System.out.println("INFO: Start of testAddPagesToPublicWiki");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
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
			 
			//Logout
			LogoutAndClose();
			
			//
			verifyNewsStory(testUser2, " created a wiki page named "+FVT_WikisData.New_Peer_Page_For_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.","Discover","Wikis", true);
				
			System.out.println("INFO: End of testAddPagesToPublicWiki");
		}
		
		
		//Edit a page
		
		@Test (dependsOnMethods = { "testAddPagesToPublicWiki" })
		public void testEditPageInPublicWiki () throws Exception {
			System.out.println("INFO: Start of testEditPageInPublicWiki");	
	
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(testUser1);		
						
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(FVT_WikisData.CI_Box_Public_Wiki);
	
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Public_Wiki);
		
			//Edit the current page
			clickLink(WikisObjects.Edit_Button);
			
			//Validate UI of wiki editor
			//ValidateWikiEditMode();
			
			//Validate tagging UI
			//ValidateTaggingUIInEditMode();
			
			//Edit page title in Edit mode
			driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).clear();
			driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).type(FVT_WikisData.New_Peer_Page_For_Public_Wiki2);
			
			//Add some content in CKEditor
			//AddContentintheEditorforanExistingPage(FVT_WikisData.New_Content_For_Public_Wiki);
			
			clickLink(WikisObjects.Save_and_Close_Link);
			//Verify that new page title is correct
			//Assert.assertTrue("FAIL: Changed page title isn't correct", driver.isElementPresent(Data.New_Peer_Page_For_Public_Wiki2));
			
			//Verify that content was added correctly
			//VerifyAddedContentInHomepage(FVT_WikisData.New_Content_For_Public_Wiki, CommonData.IC_LDAP_Username450);
			
			//Logout
			LogoutAndClose();
		  	
			verifyNewsStory(testUser2, " edited the wiki page "+FVT_WikisData.New_Peer_Page_For_Public_Wiki2+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.","Discover","Wikis", true);

			System.out.println("INFO: End of testEditPageInPublicWiki");
		}
		
		
		//Add a tag
		
		//@Test (dependsOnMethods = { "testAddPagesToPublicWiki" })
		public void testAddATagToPublicWiki() throws Exception {
			System.out.println("INFO: Start of testAddATagToPublicWiki");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(testUser1);	
						
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(FVT_WikisData.CI_Box_Public_Wiki);
			
			//Select 'Add a tag' link
			OpenTaggingEditor();
			
			//Add new tag to page
			AddATag(FVT_WikisData.Tag_For_Public_Wiki);
			
			//Verify tag has been added
			Assert.assertTrue("FAIL: Tag hasn't been added correctly", driver.isTextPresent(Data.Tag_For_Public_Wiki)); 
			
			//Logout
			LogoutAndClose();
		  	
			verifyNewsStory(testUser2, " tagged the wiki page Welcome to "+FVT_WikisData.CI_Box_Public_Wiki+" with "+FVT_WikisData.Tag_For_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.","Discover","Wikis", false);

			System.out.println("INFO: End of testAddATagToPublicWiki");
		}
		
		
		//Like a page
		
		@Test (dependsOnMethods = { "testAddPagesToPublicWiki" })
		public void testRecommendAPageForPublicWiki () throws Exception {
			
			System.out.println("INFO: Start of testRecommendAPageForPublicWiki");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(testUser1);	
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(FVT_WikisData.CI_Box_Public_Wiki);
	
			//Like Wiki Page
			LikeCurrentPage();
			
		  	//Logout of Wiki
		  	Logout();

		  	driver.close();
		  	
			verifyNewsStory(testUser2, " liked the wiki page Welcome to "+FVT_WikisData.CI_Box_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.","Discover","Wikis", true);
		  	
			System.out.println("INFO: End of testRecommendAPageForPublicWiki");
		}
		
		//Add a comment
		
		@Test (dependsOnMethods = { "testAddPagesToPublicWiki" })
		public void testAddCommentToPublicWiki () throws Exception {
			System.out.println("INFO: Start of testAddCommentToPublicWiki");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(testUser1);	
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(FVT_WikisData.CI_Box_Public_Wiki);
			
			//Open Comments Page
			clickLink(FVT_WikisObjects.Comments_Tab);
			
			//Add a comment
			AddAComment(FVT_WikisData.Comment_For_Public_Wiki);
	
			//Verify added comment
			VerifyCommentIsAdded(FVT_WikisData.Comment_For_Public_Wiki);
			
			//Logout
			LogoutAndClose();
		  	
			verifyNewsStory(testUser2, " commented on the wiki page Welcome to "+FVT_WikisData.CI_Box_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.","Discover","Wikis", true);

			System.out.println("INFO: End of testAddCommentToPublicWiki");
		}
		
		
		//Delete a page
		
		@Test (dependsOnMethods = { "testAddCommentToPublicWiki" ,"testRecommendAPageForPublicWiki","testEditPageInPublicWiki"})
		public void testDeletePage () throws Exception {
			System.out.println("INFO: Start of testDeletePage");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(testUser1);
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(FVT_WikisData.CI_Box_Public_Wiki);
			
			//Open Page Index to see all available pages
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Open newly created child page
			clickLink("//a[contains(@title, '"+FVT_WikisData.New_Child_Page_For_Public_Wiki+"')]");
			
			//Delete specified page
			deletePage(FVT_WikisData.New_Child_Page_For_Public_Wiki);
			
			//Open Page Index to see all available pages
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Verify wiki page has been deleted successfully
			Assert.assertTrue("FAIL: Wiki page still exists", !driver.isTextPresent(Data.New_Child_Page_For_Public_Wiki));
			
			//Logout
			LogoutAndClose();
		  	
		  	System.out.println("INFO: End of testDeletePage");
		}
		
		
		//Delete wiki
		
		@Test (dependsOnMethods = { "testDeletePage" })
		public void testDeletePublicWiki () throws Exception {
			System.out.println("INFO: Start of testDeletePublicWiki");	
	
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(testUser1);			
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(FVT_WikisData.CI_Box_Public_Wiki);
	
			//Delete Wiki
			deleteThisWiki(testUser1.getDisplayName());
	
			//Verify wiki has been deleted successfully
			Assert.assertTrue("FAIL: Wiki still exists", !verifyWikiExists(FVT_WikisData.CI_Box_Public_Wiki, "Public"));
	
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of testDeletePublicWiki");
		}
		
}
	
		