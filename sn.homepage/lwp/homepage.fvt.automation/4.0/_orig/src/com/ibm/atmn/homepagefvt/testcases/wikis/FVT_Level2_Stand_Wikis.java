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

package com.ibm.atmn.homepagefvt.testcases.wikis;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.homepagefvt.appobjects.wikis.TestcaseMethods;
import com.ibm.atmn.homepagefvt.appobjects.wikis.Data;

	/*
 	* This is the Wikis Team BVT for Level 2
	* Created By: Conor Pelly
	* Date: 07/01/2011
	*/ 

public class FVT_Level2_Stand_Wikis extends TestcaseMethods{
		/*************************************************************
		 * Test main functionality of public wiki as the wiki creator*
		 * ***********************************************************/
		
		//Create a public wiki
		
		@Test 
		public void testCreateANewPublicWiki () throws Exception {
			System.out.println("INFO: Start of Wikis Test 1");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
				
			//Login as a user to create the wiki (ie. Amy Jones66)
			Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);	
			
			//Click Start a Wiki button
			clickLink(WikisObjects.Start_New_Wiki_Button);
	
			//Create a new public wiki
			typeInTextField(WikisObjects.Wiki_Name_Field, Data.CI_Box_Public_Wiki);
	
			//Click Save button
			clickLink(WikisObjects.Save_Button);
	
			//Verify homepage UI
			verifyNewHomePageUI(Data.CI_Box_Public_Wiki, Data.Wiki_LDAP_Username, "");
				
			//Logout of Wiki
			Logout();	
		  	
			System.out.println("INFO: End of Wikis Test 1");
		}
		
		//Add a page
		
		@Test (dependsOnMethods = { "testCreateANewPublicWiki" })
		public void testAddPagesToPublicWiki () throws Exception {
			System.out.println("INFO: Start of Test 2");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(Data.Wiki_LDAP_Username,Data.Wiki_LDAP_Password);
				
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Public_Wiki);
	
			//Select rootpage to create child pages
			AddAPeer(Data.New_Peer_Page_For_Public_Wiki, Data.New_Content_For_Public_Wiki_Peer);
			
			//Add a new Peer pages to the wiki homepage
			AddAChild(Data.New_Child_Page_For_Public_Wiki, Data.New_Content_For_Public_Wiki_Child);
			
			//Verify pages have been created
			VerifyPageExists(Data.New_Peer_Page_For_Public_Wiki);
			VerifyPageExists(Data.New_Child_Page_For_Public_Wiki);
			 
			//Logout of wiki
			Logout();
				
			System.out.println("INFO: End of Test 2");
		}
		//Edit a page
		
		@Test (dependsOnMethods = { "testAddPagesToPublicWiki" })
		public void testEditPageInPublicWiki () throws Exception {
			System.out.println("INFO: Start of Test 3");	
	
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);		
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Public_Wiki);
	
			//Open newly created peer page
			clickLink("link=" + Data.New_Peer_Page_For_Public_Wiki);
		
			//Edit the current page
			clickLink(WikisObjects.Edit_Button);
			
			//Validate UI of wiki editor
			ValidateWikiEditMode();
			
			//Validate tagging UI
			ValidateTaggingUIInEditMode();
			
			//Edit page title in Edit mode
			sel.type(WikisObjects.Page_Name_Textfield_In_Editor, Data.New_Peer_Page_For_Public_Wiki2);
			
			//Add some content in CKEditor
			AddContentintheEditorforanExistingPage(Data.New_Content_For_Public_Wiki);
			
			//Verify that new page title is correct
			Assert.assertTrue("FAIL: Changed page title isn't correct", sel.getText(WikisObjects.WikiHomePageTitleField).equals(Data.New_Peer_Page_For_Public_Wiki2));
			
			//Verify that content was added correctly
			VerifyAddedContentInHomepage(Data.New_Content_For_Public_Wiki, Data.Wiki_LDAP_Username);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 3");
		}
		
		//Add a comment
		public void testAddCommentToPublicWiki () throws Exception {
			System.out.println("INFO: Start of Test 4");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);		
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Public_Wiki);
			
			//Open Comments Page
			clickObject(WikisObjects.Comments_Tab);
			
			//Add a comment
			AddAComment(Data.Comment_For_Public_Wiki);
	
			//Verify added comment
			VerifyCommentIsAdded(Data.Comment_For_Public_Wiki);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 4");
		}
		
		//Add a tag
		public void testAddATagToPublicWiki() throws Exception {
			System.out.println("INFO: Start of Test 5");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Public_Wiki);
			
			//Select 'Add a tag' link
			OpenTaggingEditor();
			
			//Add new tag to page
			AddATag(Data.Tag_For_Public_Wiki);
			
			//Verify tag has been added
			Assert.assertTrue("FAIL: Tag hasn't been added correctly", sel.isTextPresent(Data.Tag_For_Public_Wiki)); 
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 5");
		}
		
		//Recommend a page
		public void testRecommendAPageForPublicWiki () throws Exception {
			System.out.println("INFO: Start of Test 6");	
	
			int homePageRecommendCount = 0;
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);		
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Public_Wiki);
	
			///Verify that recommendation UI is correct before a user has recommended the page
			verifyRecommendationUI(homePageRecommendCount, false);
			
			//Recommend the current page
			RecommendCurrentPage();
			
			//Increment Recommendation counter
			homePageRecommendCount++;
			
			///Verify that recommendation UI is correct after a user has recommended the page
			verifyRecommendationUI(homePageRecommendCount, true);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 6");
		}
		
		//Delete a page
		public void testDeletePage () throws Exception {
			System.out.println("INFO: Start of Test 7");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);		
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Public_Wiki);
			
			//Open Page Index to see all available pages
			clickLink(WikisObjects.Index_Link);
			
			//Open newly created child page
			clickLink("//a[contains(@title, '"+Data.New_Child_Page_For_Public_Wiki+"')]");
			
			//Delete specified page
			deletePage(Data.New_Child_Page_For_Public_Wiki);
			
			//Open Page Index to see all available pages
			clickLink(WikisObjects.Index_Link);
			
			//Verify wiki page has been deleted successfully
			Assert.assertTrue("FAIL: Wiki page still exists", !sel.isTextPresent(Data.New_Child_Page_For_Public_Wiki));
			
		  	//Logout of Wiki
		  	Logout();
		  	
		  	System.out.println("INFO: End of Test 7");
		}
			
		//Edit wiki title
		public void testEditPublicWiki () throws Exception {
			System.out.println("INFO: Start of Test 8");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Public_Wiki);
	
			//Open Edit Wiki form
			OpenEditWikiForm();
			
			//Recommend the current page
			changeWikiTitle(Data.CI_Box_Public_Wiki2);
			
			//Verify that wiki title has been changed
			validateChangedWikiTitle(Data.CI_Box_Public_Wiki2);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 8");
		}
	
		//Delete wiki
		public void testDeletePublicWiki () throws Exception {
			System.out.println("INFO: Start of Test 9");	
	
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);			
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Public_Wiki2);
	
			//Recommend the current page
			DeleteThisWiki();
	
			//Verify wiki has been deleted successfully
			Assert.assertTrue("FAIL: Wiki still exists", !verifyWikiExists(Data.CI_Box_Public_Wiki2, "Public"));
	
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 9");
		}
	
		/**************************************************************
		 * 
		 * Test main functionality of private wiki as the wiki creator*
		 * ************************************************************/
		
		//Create a private wiki and add an Owner, Editor & Reader
		public void testCreateANewPrivateWiki () throws Exception {
			System.out.println("INFO: Start of Test 10");	
	
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter readers username & password
			Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);		
			
			//Create private wiki with specified name
			createPrivateWikiWithUsers(Data.CI_Box_Private_Wiki);
	
			//Verify homepage UI of newly created wiki
			verifyNewHomePageUI(Data.CI_Box_Private_Wiki, Data.Wiki_LDAP_Username, "");
			
			//Logout of Wiki
		  	Logout();	
		  	
			System.out.println("INFO: End of Test 10");
		}
		
		//Add a page
		public void testAddPagesToPrivateWiki () throws Exception {
			System.out.println("INFO: Start of Test 11");
				
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);
				
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	
			//Select rootpage to create child pages
			AddAPeer(Data.New_Peer_Page_For_Private_Wiki, Data.New_Content_For_Private_Wiki_Peer);
			
			//Add a new Peer pages to the wiki homepage
			AddAChild(Data.New_Child_Page_For_Private_Wiki, Data.New_Content_For_Private_Wiki_Child);
			
			//Verify pages have been created
			VerifyPageExists(Data.New_Peer_Page_For_Private_Wiki);
			VerifyPageExists(Data.New_Child_Page_For_Private_Wiki);
			 
			//Logout of wiki
			Logout();
				
			System.out.println("INFO: End of Test 11");
		}
		
		//Edit a page
		public void testEditPageInPrivateWiki () throws Exception {
			System.out.println("INFO: Start of Test 12");	
	
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);		
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	
			//Open Page Index to see all available pages
			clickLink(WikisObjects.Index_Link);
			
			//Open newly created peer page
			clickLink("//a[contains(@title, '"+Data.New_Child_Page_For_Private_Wiki+"')]");
			
			//Edit the current page
			clickLink(WikisObjects.Edit_Button);
			
			//Validate UI of wiki editor
			ValidateWikiEditMode();
			
			//Validate tagging UI
			ValidateTaggingUIInEditMode();
			
			//Edit page title in Edit mode
			sel.type(WikisObjects.Page_Name_Textfield_In_Editor, Data.New_Child_Page_For_Private_Wiki2);
			
			//Add some content in CKEditor
			AddContentintheEditorforanExistingPage(Data.New_Content_For_Private_Wiki);
			
			//Verify that new page title is correct
			Assert.assertTrue("FAIL: Changed page title isn't correct", sel.getText(WikisObjects.WikiHomePageTitleField).equals(Data.New_Child_Page_For_Private_Wiki2));
			
			//Verify that content was added correctly
			VerifyAddedContentInHomepage(Data.New_Content_For_Private_Wiki, Data.Wiki_LDAP_Username);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 12");
		}
	
		//Add a comment
		public void testAddCommentToPrivateWiki () throws Exception {
			System.out.println("INFO: Start of Test 13");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);		
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
			
			//Open Comments Page
			clickObject(WikisObjects.Comments_Tab);
			
			//Add a comment
			AddAComment(Data.Comment_For_Private_Wiki);
	
			//Verify added comment
			VerifyCommentIsAdded(Data.Comment_For_Private_Wiki);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 13");
		}
	
		//Add a tag
		public void testAddATagToPrivateWiki() throws Exception {
			System.out.println("INFO: Start of Test 14");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
			
			//Select 'Add a tag' link
			OpenTaggingEditor();
			
			//Add new tag to page
			AddATag(Data.Tag_For_Private_Wiki);
			
			//Verify tag has been added
			Assert.assertTrue("FAIL: Tag hasn't been added correctly", sel.isTextPresent(Data.Tag_For_Private_Wiki));
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 14");
		}
	
		//Recommend a page
		public void testRecommendAPageOfPrivateWiki () throws Exception {
			System.out.println("INFO: Start of Test 15");	
	
			int homePageRecommendCount = 0;
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);		
			
			//Open newly created Wiki from My Wikis
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	
			///Verify that recommendation UI is correct before a user has recommended the page
			verifyRecommendationUI(homePageRecommendCount, false);
			
			//Recommend the current page
			RecommendCurrentPage();
			
			//Increment Recommendation counter
			homePageRecommendCount++;
			
			///Verify that recommendation UI is correct after a user has recommended the page
			verifyRecommendationUI(homePageRecommendCount, true);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 15");
		}
	
		/************************************************************
		 * Test main functionality of private wiki as the wiki owner*
		 * **********************************************************/
		
		//Add a page
		public void testAddPagesToPrivateWikiAsOwner () throws Exception {
			System.out.println("INFO: Start of Test 16");
				
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter owners username & password
			Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);
				
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	
			//Select rootpage to create child pages
			AddAPeer(Data.New_Owners_Peer_Page_For_Private_Wiki, Data.New_Content_For_Owners_Private_Wiki_Peer);
			
			//Add a new Peer pages to the wiki homepage
			AddAChild(Data.New_Owners_Child_Page_For_Private_Wiki, Data.New_Content_For_Owners_Private_Wiki_Child);
			
			//Verify pages have been created
			VerifyPageExists(Data.New_Owners_Peer_Page_For_Private_Wiki);
			VerifyPageExists(Data.New_Owners_Child_Page_For_Private_Wiki);
			
			//Logout of wiki
			Logout();
				
			System.out.println("INFO: End of Test 16");
		}
	
		//Edit a page
		public void testEditPageInPrivateWikiAsOwner () throws Exception {
			System.out.println("INFO: Start of Test 17");	
	
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter owners username & password
			Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);		
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	
			//Open newly created peer page
			//clickLink("//a[contains(@title, '"+Data.New_Owners_Peer_Page_For_Private_Wiki+"')]");
			clickLink("link="+Data.New_Owners_Peer_Page_For_Private_Wiki);
			
			//Edit the current page
			clickLink(WikisObjects.Edit_Button);
			
			//Validate UI of wiki editor
			ValidateWikiEditMode();
			
			//Validate tagging UI
			ValidateTaggingUIInEditMode();
			
			//Edit page title in Edit mode
			sel.type(WikisObjects.Page_Name_Textfield_In_Editor, Data.New_Owners_Peer_Page_For_Private_Wiki2);
			
			//Add some content in CKEditor
			AddContentintheEditorforanExistingPage(Data.New_Content_For_Owners_Private_Wiki);
			
			//Verify that new page title is correct
			Assert.assertTrue("FAIL: Changed page title isn't correct", sel.getText(WikisObjects.WikiHomePageTitleField).equals(Data.New_Owners_Peer_Page_For_Private_Wiki2));
			
			//Verify that content was added correctly
			VerifyAddedContentInHomepage(Data.New_Content_For_Owners_Private_Wiki, Data.Wiki_LDAP_Owner_Username);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 17");
		}
	
		//Add a comment
		public void testAddCommentToPrivateWikiAsOwner () throws Exception {
			System.out.println("INFO: Start of Test 18");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter owners username & password
			Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);		
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
			
			//Open Comments Page
			clickObject(WikisObjects.Comments_Tab);
			
			//Add a comment
			AddAComment(Data.Comment_For_Owners_Private_Wiki);
	
			//Verify added comment
			VerifyCommentIsAdded(Data.Comment_For_Owners_Private_Wiki);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 18");
		}
		
		//Add a tag
		public void testAddATagToPrivateWikiAsOwner() throws Exception {
			System.out.println("INFO: Start of Test 19");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter owners username & password
			Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
			
			//Select 'Add a tag' link
			OpenTaggingEditor();
			
			//Add new tag to page
			AddATag(Data.Tag_For_Owners_Private_Wiki);
			
			//Verify tag has been added
			Assert.assertTrue("FAIL: Tag hasn't been added correctly", sel.isTextPresent(Data.Tag_For_Owners_Private_Wiki));
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 19");
		}
	
		//Recommend a page
		public void testRecommendAPageOfPrivateWikiAsOwner () throws Exception {
			System.out.println("INFO: Start of Test 20");	
	
			int homePageRecommendCount = 1;
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter owners username & password
			Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);		
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	
			///Verify that recommendation UI is correct before a user has recommended the page
			verifyRecommendationUI(homePageRecommendCount, false);
			
			//Recommend the current page
			RecommendCurrentPage();
			
			//Increment Recommendation counter
			homePageRecommendCount++;
			
			///Verify that recommendation UI is correct after a user has recommended the page
			verifyRecommendationUI(homePageRecommendCount, true);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 20");
		}
	
		//Delete a page
		public void testDeletePageAsOwner () throws Exception {
			System.out.println("INFO: Start of Test 21");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter owners username & password
			Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);		
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
			
			//Open Page Index to see all available pages
			clickLink(WikisObjects.Index_Link);
			
			//Open newly created child page
			clickLink("//a[contains(@title, '"+Data.New_Owners_Child_Page_For_Private_Wiki+"')]");
			
			//Delete specified page
			deletePage(Data.New_Owners_Child_Page_For_Private_Wiki);
			
			//Open Page Index to see all available pages
			clickLink(WikisObjects.Index_Link);
			
			//Verify wiki page has been deleted successfully
			Assert.assertTrue("FAIL: Wiki page still exists", !sel.isTextPresent(Data.New_Owners_Child_Page_For_Private_Wiki));
			
		  	//Logout of Wiki
		  	Logout();
		  	
		  	System.out.println("INFO: End of Test 21");
		}
		
		/*************************************************************
		 * Test main functionality of private wiki as the wiki editor*
		 * ***********************************************************/
		
		//Add a page
		public void testAddPagesToPrivateWikiAsEditor () throws Exception {
			System.out.println("INFO: Start of Test 22");
				
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter editors username & password
			Login(Data.Wiki_LDAP_Editor_Username, Data.Wiki_LDAP_Editor_Password);
				
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	
			//Select rootpage to create child pages
			AddAPeer(Data.New_Editors_Peer_Page_For_Private_Wiki, Data.New_Content_For_Editors_Private_Wiki_Peer);
			
			//Add a new Peer pages to the wiki homepage
			AddAChild(Data.New_Editors_Child_Page_For_Private_Wiki, Data.New_Content_For_Editors_Private_Wiki_Child);
			
			//Verify pages have been created
			VerifyPageExists(Data.New_Editors_Peer_Page_For_Private_Wiki);
			VerifyPageExists(Data.New_Editors_Child_Page_For_Private_Wiki);
			 
			//Logout of wiki
			Logout();
				
			System.out.println("INFO: End of Test 22");
		}
	
		//Edit a page
		public void testEditPageInPrivateWikiAsEditor () throws Exception {
			System.out.println("INFO: Start of Test 23");	
	
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter editors username & password
			Login(Data.Wiki_LDAP_Editor_Username, Data.Wiki_LDAP_Editor_Password);		
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	
			//Open Page Index to see all available pages
			clickLink(WikisObjects.Index_Link);
			
			//Open newly created peer page
			clickLink("//a[contains(@title, '"+Data.New_Editors_Child_Page_For_Private_Wiki+"')]");
			
			//Edit the current page
			clickLink(WikisObjects.Edit_Button);
			
			//Validate UI of wiki editor
			ValidateWikiEditMode();
			
			//Validate tagging UI
			ValidateTaggingUIInEditMode();
			
			//Edit page title in Edit mode
			sel.type(WikisObjects.Page_Name_Textfield_In_Editor, Data.New_Editors_Child_Page_For_Private_Wiki2);
			
			//Add some content in CKEditor
			AddContentintheEditorforanExistingPage(Data.New_Content_For_Editors_Private_Wiki);
			
			//Verify that new page title is correct
			Assert.assertTrue("FAIL: Changed page title isn't correct", sel.getText(WikisObjects.WikiHomePageTitleField).equals(Data.New_Editors_Child_Page_For_Private_Wiki2));
			
			//Verify that content was added correctly
			VerifyAddedContentInHomepage(Data.New_Content_For_Editors_Private_Wiki, Data.Wiki_LDAP_Editor_Username);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 23");
		}
	
		//Add a comment
		public void testAddCommentToPrivateWikiAsEditor () throws Exception {
			System.out.println("INFO: Start of Test 24");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter editors username & password
			Login(Data.Wiki_LDAP_Editor_Username, Data.Wiki_LDAP_Editor_Password);		
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
			
			//Open Comments Page
			clickObject(WikisObjects.Comments_Tab);
			
			//Add a comment
			AddAComment(Data.Comment_For_Editors_Private_Wiki);
	
			//Verify added comment
			VerifyCommentIsAdded(Data.Comment_For_Editors_Private_Wiki);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 24");
		}
		
		//Add a tag
		public void testAddATagToPrivateWikiAsEditor() throws Exception {
			System.out.println("INFO: Start of Test 25");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter editors username & password
			Login(Data.Wiki_LDAP_Editor_Username, Data.Wiki_LDAP_Editor_Password);
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
			
			//Select 'Add a tag' link
			OpenTaggingEditor();
			
			//Add new tag to page
			AddATag(Data.Tag_For_Editors_Private_Wiki);
			
			//Verify tag has been added
			Assert.assertTrue("FAIL: Tag hasn't been added correctly", sel.isTextPresent(Data.Tag_For_Editors_Private_Wiki));
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 25");
		}
	
		//Recommend a page
		public void testRecommendAPageOfPrivateWikiAsEditor () throws Exception {
			System.out.println("INFO: Start of Test 26");	
	
			int homePageRecommendCount = 2;
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter editors username & password
			Login(Data.Wiki_LDAP_Editor_Username, Data.Wiki_LDAP_Editor_Password);		
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	
			///Verify that recommendation UI is correct before a user has recommended the page
			verifyRecommendationUI(homePageRecommendCount, false);
			
			//Recommend the current page
			RecommendCurrentPage();
			
			//Increment Recommendation counter
			homePageRecommendCount++;
			
			///Verify that recommendation UI is correct after a user has recommended the page
			verifyRecommendationUI(homePageRecommendCount, true);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 26");
		}
		
		/*************************************************************
		 * Test main functionality of private wiki as the wiki reader*
		 * ***********************************************************/
	
		//Add a comment
		public void testAddCommentToPrivateWikiAsReader () throws Exception {
			System.out.println("INFO: Start of Test 27");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter readers username & password
			Login(Data.Wiki_LDAP_Reader_Username, Data.Wiki_LDAP_Reader_Password);		
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
			
			//Open Comments Page
			clickObject(WikisObjects.Comments_Tab);
			
			//Add a comment
			AddAComment(Data.Comment_For_Readers_Private_Wiki);
	
			//Verify added comment
			VerifyCommentIsAdded(Data.Comment_For_Readers_Private_Wiki);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 27");
		}
	
		//Recommend a page
		public void testRecommendAPageOfPrivateWikiAsReader () throws Exception {
			System.out.println("INFO: Start of Test 28");	
	
			int homePageRecommendCount = 3;
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter readers username & password
			Login(Data.Wiki_LDAP_Reader_Username, Data.Wiki_LDAP_Reader_Password);		
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	
			///Verify that recommendation UI is correct before a user has recommended the page
			verifyRecommendationUI(homePageRecommendCount, false);
			
			//Recommend the current page
			RecommendCurrentPage();
			
			//Increment Recommendation counter
			homePageRecommendCount++;
			
			///Verify that recommendation UI is correct after a user has recommended the page
			verifyRecommendationUI(homePageRecommendCount, true);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 28");
		}
	
		/***********************************************************
		 * Verify edit & deletion of private wiki as the wiki owner*
		 * *********************************************************/
		
		//Edit wiki title
		public void testEditPrivateWiki () throws Exception {
			System.out.println("INFO: Start of Test 29");
			
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter creators username & password
			Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	
			//Open Edit Wiki form
			OpenEditWikiForm();
			
			//Recommend the current page
			changeWikiTitle(Data.CI_Box_Private_Wiki2);
			
			//Verify that wiki title has been changed
			validateChangedWikiTitle(Data.CI_Box_Private_Wiki2);
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 29");
		}
		
		//Delete private wiki as owner
		public void testDeletePrivateWikiAsOwner () throws Exception {
			System.out.println("INFO: Start of Test 30");	
	
			//Load the component
			LoadComponent(CommonObjects.ComponentWikis);
			
			//Enter owners username & password
			Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);			
			
			//Open newly created Wiki
			OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki2);
	
			//Recommend the current page
			DeleteThisWiki();
			
			//Verify wiki has been deleted successfully
			Assert.assertTrue("FAIL: Wiki still exists", !verifyWikiExists(Data.CI_Box_Private_Wiki2, "Private"));
			
		  	//Logout of Wiki
		  	Logout();
		  	
			System.out.println("INFO: End of Test 30");
		}
	
}
