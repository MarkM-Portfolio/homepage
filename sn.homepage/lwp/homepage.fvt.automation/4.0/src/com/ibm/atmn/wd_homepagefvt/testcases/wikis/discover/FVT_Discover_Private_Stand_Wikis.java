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
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisData;
import com.ibm.atmn.wd_homepagefvt.tasks.wikis.FVT_WikisMethods;

	/*
 	* This is the FVT Private Wikis Testcases
	* Created By: Johann Ott
	* Date: 28/11/2011
	*/ 

public class FVT_Discover_Private_Stand_Wikis extends FVT_WikisMethods{
	
	//Create a private wiki and add an Owner, Editor & Reader
	
	User testUser1;
	User testUser2;
	//User testUser3;
	
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Wikis FVT_Level_2 testLoginUsers");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentWikis);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		Login(testUser2);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Wikis FVT_Level_2 testLoginUsers");
		
	}
	
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testCreateANewPrivateWiki () throws Exception {
		System.out.println("INFO: Start of testCreateANewPrivateWiki");	

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter readers username & password
		Login(testUser1);			
		
		//Create private wiki with specified name
		createPrivateWikiWithUsers(FVT_WikisData.CI_Box_Private_Wiki,testUser2.getEmail());

		//Verify homepage UI of newly created wiki
		verifyNewHomePageUI(FVT_WikisData.CI_Box_Private_Wiki, testUser1.getDisplayName(), "");
		
		//Logout
		LogoutAndClose();
	  	
		verifyNewsStory(testUser2, " created a wiki named "+FVT_WikisData.CI_Box_Private_Wiki+".","Discover","Wikis", false);
	  	
		System.out.println("INFO: End of testCreateANewPrivateWiki");
	}
	
	//Add a page
	
	@Test (dependsOnMethods = { "testCreateANewPrivateWiki" })
	public void testAddPagesToPrivateWiki () throws Exception {
		System.out.println("INFO: Start of testAddPagesToPrivateWiki");
			
		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter creators username & password
		Login(testUser1);			
		
		//Open newly created Wiki
		clickLink("link="+FVT_WikisData.CI_Box_Private_Wiki);

		//Select rootpage to create child pages
		AddAPeer(FVT_WikisData.New_Peer_Page_For_Private_Wiki, FVT_WikisData.New_Content_For_Private_Wiki_Peer);
		
		//Add a new Peer pages to the wiki homepage
		AddAChild(FVT_WikisData.New_Child_Page_For_Private_Wiki, FVT_WikisData.New_Content_For_Private_Wiki_Child);
		
		//Verify pages have been created
		VerifyPageExists(FVT_WikisData.New_Peer_Page_For_Private_Wiki);
		VerifyPageExists(FVT_WikisData.New_Child_Page_For_Private_Wiki);
		 
		//Logout
		LogoutAndClose();
		
		verifyNewsStory(testUser2, " created a wiki page named "+FVT_WikisData.New_Peer_Page_For_Private_Wiki+" in the "+FVT_WikisData.CI_Box_Private_Wiki+" wiki.","Discover","Wikis", false);
			
		System.out.println("INFO: End of testAddPagesToPrivateWiki");
	}
	
	//Edit a page
	@Test (dependsOnMethods = { "testAddPagesToPrivateWiki" })
	public void testEditPageInPrivateWiki () throws Exception {
		System.out.println("INFO: Start of testEditPageInPrivateWiki");	

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter creators username & password
		Login(testUser1);			
		
		//Open newly created Wiki
		clickLink("link="+FVT_WikisData.CI_Box_Private_Wiki);

		//Open Page Index to see all available pages
		clickLink(WikisObjects.Index_Link);
		
		//Open newly created peer page
		clickLink("//a[contains(@title, '"+FVT_WikisData.New_Child_Page_For_Private_Wiki+"')]");
		
		//Edit the current page
		clickLink(WikisObjects.Edit_Button);
		
		//Validate UI of wiki editor
		ValidateWikiEditMode();
		
		//Validate tagging UI
		ValidateTaggingUIInEditMode();
		
		//Edit page title in Edit mode
		driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).clear();
		driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).type(FVT_WikisData.New_Child_Page_For_Private_Wiki2);
		
		//Add some content in CKEditor
		AddContentintheEditorforanExistingPage(FVT_WikisData.New_Content_For_Private_Wiki);
		
		//Verify that new page title is correct
		//Assert.assertTrue("FAIL: Changed page title isn't correct", driver.isTextPresent(WikisObjects.WikiHomePageTitleField));
		
		//Verify that content was added correctly
		//VerifyAddedContentInHomepage(FVT_WikisData.New_Content_For_Private_Wiki);
		
		//Logout
		LogoutAndClose();
	  	
		verifyNewsStory(testUser2, " edited the wiki page "+FVT_WikisData.New_Child_Page_For_Private_Wiki2+" in the "+FVT_WikisData.CI_Box_Private_Wiki+" wiki.","Discover","Wikis", false);
	  	
		System.out.println("INFO: End of testEditPageInPrivateWiki");
	}
		
	//Add a tag
	@Test (dependsOnMethods = { "testEditPageInPrivateWiki" })
	public void testAddATagToPrivateWiki() throws Exception {
		System.out.println("INFO: Start of testAddATagToPrivateWiki");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter creators username & password
		Login(testUser1);			
				
		//Open newly created Wiki
		clickLink("link="+FVT_WikisData.CI_Box_Private_Wiki);
		
		//Select 'Add a tag' link
		OpenTaggingEditor();
		
		//Add new tag to page
		AddATag(FVT_WikisData.Tag_For_Private_Wiki);
		
		//Verify tag has been added
		Assert.assertTrue("FAIL: Tag hasn't been added correctly", driver.isTextPresent(FVT_WikisData.Tag_For_Private_Wiki));
		
		//Logout
		LogoutAndClose();
	  	
		verifyNewsStory(testUser2, " tagged the wiki page Welcome to "+FVT_WikisData.CI_Box_Public_Wiki+" with "+FVT_WikisData.Tag_For_Private_Wiki+" in the "+FVT_WikisData.CI_Box_Private_Wiki+" wiki.","Discover","Wikis", false);
	  	
		System.out.println("INFO: End of testAddATagToPrivateWiki");
	}
	
	//Recommend a page
	@Test (dependsOnMethods = { "testAddATagToPrivateWiki" })
	public void testRecommendAPageOfPrivateWiki () throws Exception {
		System.out.println("INFO: Start of testRecommendAPageOfPrivateWiki");	

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter creators username & password
		Login(testUser1);			
		
		//Open newly created Wiki
		clickLink("link="+FVT_WikisData.CI_Box_Private_Wiki);

		//Like Wiki Page
		LikeCurrentPage();
		
		//Logout
		LogoutAndClose();

		verifyNewsStory(testUser2, " liked the wiki page Welcome to "+FVT_WikisData.CI_Box_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Private_Wiki+" wiki.","Discover","Wikis", false);
	  	
		System.out.println("INFO: End of testRecommendAPageOfPrivateWiki");
	}
	
	//Add a comment
	@Test (dependsOnMethods = { "testRecommendAPageOfPrivateWiki" })
	public void testAddCommentToPrivateWiki () throws Exception {
		System.out.println("INFO: Start of testAddCommentToPrivateWiki");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter creators username & password
		Login(testUser1);			
				
		//Open newly created Wiki
		clickLink("link="+FVT_WikisData.CI_Box_Private_Wiki);
		
		//Open Comments Page
		clickLink(WikisObjects.Comments_Tab);
		
		//Add a comment
		AddAComment(FVT_WikisData.Comment_For_Private_Wiki);

		//Verify added comment
		VerifyCommentIsAdded(FVT_WikisData.Comment_For_Private_Wiki);
		
		//Logout
		LogoutAndClose();
	  	
		verifyNewsStory(testUser2, " commented on the wiki page Welcome to "+FVT_WikisData.CI_Box_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Private_Wiki+" wiki.","Discover","Wikis", false);
	  	
		System.out.println("INFO: End of testAddCommentToPrivateWiki");
	}
	
	//Delete a page
	@Test (dependsOnMethods = { "testAddCommentToPrivateWiki" })
	public void testDeletePageAsOwner () throws Exception {
		System.out.println("INFO: Start of testDeletePageAsOwner");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter owners username & password
		Login(testUser1);			
				
		//Open newly created Wiki
		clickLink("link="+FVT_WikisData.CI_Box_Private_Wiki);
		
		//Open Page Index to see all available pages
		clickLink(WikisObjects.Index_Link);
		
		//Open newly created child page
		clickLink("//a[contains(@title, '"+FVT_WikisData.New_Child_Page_For_Private_Wiki+"')]");
		
		//Delete specified page
		deletePage(FVT_WikisData.New_Child_Page_For_Private_Wiki);
		
		//Open Page Index to see all available pages
		clickLink(WikisObjects.Index_Link);
		
		//Verify wiki page has been deleted successfully
		Assert.assertTrue("FAIL: Wiki page still exists", !driver.isTextPresent(FVT_WikisData.New_Child_Page_For_Private_Wiki));
		
		//Logout
		LogoutAndClose();
	  	
	  	System.out.println("INFO: End of testDeletePageAsOwner");
	}
	
	//Delete private wiki as owner
	@Test (dependsOnMethods = { "testDeletePageAsOwner" })
	public void testDeletePrivateWikiAsOwner () throws Exception {
		System.out.println("INFO: Start of testDeletePrivateWikiAsOwner");	

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter owners username & password
		Login(testUser1);			
				
		//Open newly created Wiki
		clickLink("link="+FVT_WikisData.CI_Box_Private_Wiki);

		//Recommend the current page
		deleteThisWiki(testUser1.getDisplayName());
		
		//Verify wiki has been deleted successfully
		Assert.assertTrue("FAIL: Wiki still exists", !verifyWikiExists(FVT_WikisData.CI_Box_Private_Wiki, "Private"));
		
	  	//Logout of Wiki
	  	Logout();
	  	
		System.out.println("INFO: End of testDeletePrivateWikiAsOwner");
	}
		
}
	
		