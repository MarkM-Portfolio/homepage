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

package com.ibm.atmn.wd_homepagefvt.testcases.wikis.imfollowing.tags;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisData;
import com.ibm.atmn.wd_homepagefvt.tasks.wikis.FVT_WikisMethods;

	/*
 	* This is the FVT Private Wikis Testcases
	* Created By: Johann Ott
	* Date: 28/11/2011
	*/ 

public class FVT_ImFollowing_Tags_Private_Stand_Wikis extends FVT_WikisMethods{
	
	//Create a private wiki and add an Owner, Editor & Reader
	
	@Test
	public void testFollowTag() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowTag");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentNews);
			
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Follow Tag
		followTag(CommonData.AutomationTag);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testFollowTag");
		
	}


	@Test (dependsOnMethods = { "testFollowTag" })
	public void testCreateANewPrivateWiki () throws Exception {
		System.out.println("INFO: Start of testCreateANewPrivateWiki");	

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter readers username & password
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
		
		//Create private wiki with specified name
		createPrivateWikiWithUsers(FVT_WikisData.CI_Box_Private_Wiki,CommonData.IC_LDAP_Username_Fullname451);

		//Verify homepage UI of newly created wiki
		verifyNewHomePageUI(FVT_WikisData.CI_Box_Private_Wiki, CommonData.IC_LDAP_Username450, "");
		
		//Add tag 
		driver.getSingleElement(WikisObjects.Add_Tag_Link).click();
		driver.getSingleElement(WikisObjects.Tag_Textbox).type("automation");
		
		//Logout of Wiki
	  	Logout();	
	  	
	  	driver.close();
	  	
		verifyImFollowingTagsNewsStory(" created a wiki named "+FVT_WikisData.CI_Box_Public_Wiki+".","Wikis", "automation",false, false);
		  	
		System.out.println("INFO: End of testCreateANewPrivateWiki");
	}
	
	@Test (dependsOnMethods = { "testCreateANewPrivateWiki" })
	public void testAddPagesToPrivateWiki () throws Exception {
		System.out.println("INFO: Start of testAddPagesToPrivateWiki");
			
		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter creators username & password
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
			
		//Open newly created Wiki
		clickLink("link="+FVT_WikisData.CI_Box_Private_Wiki);

		//Select rootpage to create child pages
		AddAPeer(FVT_WikisData.New_Peer_Page_For_Private_Wiki, FVT_WikisData.New_Content_For_Private_Wiki_Peer);
		
		//Add a new Peer pages to the wiki homepage
		AddAChild(FVT_WikisData.New_Child_Page_For_Private_Wiki, FVT_WikisData.New_Content_For_Private_Wiki_Child);
		
		//Verify pages have been created
		VerifyPageExists(FVT_WikisData.New_Peer_Page_For_Private_Wiki);
		VerifyPageExists(FVT_WikisData.New_Child_Page_For_Private_Wiki);
		 
		//Logout of wiki
		Logout();
		
		driver.close();
		
		verifyImFollowingTagsNewsStory(" created a wiki page named "+FVT_WikisData.New_Peer_Page_For_Private_Wiki+" in the "+FVT_WikisData.CI_Box_Private_Wiki+" wiki.","Wikis", "automation",false, false);
			
		System.out.println("INFO: End of testAddPagesToPrivateWiki");
	}
	
	//Edit a page
	@Test (dependsOnMethods = { "testAddPagesToPrivateWiki" })
	public void testEditPageInPrivateWiki () throws Exception {
		System.out.println("INFO: Start of testEditPageInPrivateWiki");	

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter creators username & password
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
		
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
		driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).type(FVT_WikisData.New_Child_Page_For_Private_Wiki2);
		
		//Add some content in CKEditor
		AddContentintheEditorforanExistingPage(FVT_WikisData.New_Content_For_Private_Wiki);
	
	  	//Logout of Wiki
	  	Logout();
	  	
	  	driver.close();
	  	
	  	verifyImFollowingTagsNewsStory(" edited the wiki page "+FVT_WikisData.New_Child_Page_For_Private_Wiki2+" in the "+FVT_WikisData.CI_Box_Private_Wiki+" wiki.","Wikis", "automation",false, false);
	  	
		System.out.println("INFO: End of testEditPageInPrivateWiki");
	}
		
	//Add a tag
	@Test (dependsOnMethods = { "testAddPagesToPrivateWiki" })
	public void testAddATagToPrivateWiki() throws Exception {
		System.out.println("INFO: Start of testAddATagToPrivateWiki");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter creators username & password
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
		
		//Open newly created Wiki
		clickLink("link="+FVT_WikisData.CI_Box_Private_Wiki);
		
		//Select 'Add a tag' link
		OpenTaggingEditor();
		
		//Add new tag to page
		AddATag(FVT_WikisData.Tag_For_Private_Wiki);
		
		//Verify tag has been added
		Assert.assertTrue("FAIL: Tag hasn't been added correctly", driver.isTextPresent(FVT_WikisData.Tag_For_Private_Wiki));
		
	  	//Logout of Wiki
	  	Logout();
	  	
	  	driver.close();
	  	
	  	verifyImFollowingTagsNewsStory(" tagged the wiki page Welcome to "+FVT_WikisData.CI_Box_Private_Wiki+" with "+FVT_WikisData.Tag_For_Private_Wiki+" in the "+FVT_WikisData.CI_Box_Private_Wiki+" wiki.","Wikis", "automation",false, false);
	  	
		System.out.println("INFO: End of testAddATagToPrivateWiki");
	}
	
	//Recommend a page
	@Test (dependsOnMethods = { "testAddPagesToPrivateWiki" })
	public void testRecommendAPageOfPrivateWiki () throws Exception {
		System.out.println("INFO: Start of testRecommendAPageOfPrivateWiki");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter creators username & password
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
		
		//Open newly created Wiki
		clickLink("link="+FVT_WikisData.CI_Box_Private_Wiki);

		//Like Wiki Page
		LikeCurrentPage();
		
	  	//Logout of Wiki
	  	Logout();
	  	
	  	driver.close();

	  	verifyImFollowingTagsNewsStory(" liked the wiki page Welcome to "+FVT_WikisData.CI_Box_Private_Wiki+" in the "+FVT_WikisData.CI_Box_Private_Wiki+" wiki.","Wikis", "automation",false, false);
	  	
		System.out.println("INFO: End of testRecommendAPageOfPrivateWiki");
	}
	
	//Add a comment
	@Test (dependsOnMethods = { "testAddPagesToPrivateWiki" })
	public void testAddCommentToPrivateWiki () throws Exception {
		System.out.println("INFO: Start of testAddCommentToPrivateWiki");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter creators username & password
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
		
		//Open newly created Wiki
		clickLink("link="+FVT_WikisData.CI_Box_Private_Wiki);
		
		//Open Comments Page
		clickLink(WikisObjects.Comments_Tab);
		
		//Add a comment
		AddAComment(FVT_WikisData.Comment_For_Private_Wiki);

		//Verify added comment
		VerifyCommentIsAdded(FVT_WikisData.Comment_For_Private_Wiki);
		
	  	//Logout of Wiki
	  	Logout();
	  	
	  	driver.close();
	  	
	  	verifyImFollowingTagsNewsStory(" commented on the wiki page Welcome to "+FVT_WikisData.CI_Box_Private_Wiki+" in the "+FVT_WikisData.CI_Box_Private_Wiki+" wiki.","Wikis", "automation",false, false);
	  	
		System.out.println("INFO: End of testAddCommentToPrivateWiki");
	}
	
	//Edit comment
	@Test (dependsOnMethods = { "testAddCommentToPrivateWiki" })
	public void testEditCommentPrivateWiki () throws Exception {
		System.out.println("INFO: Start of testEditCommentPrivateWiki");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter creators username & password
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
		
		//Open newly created Wiki
		clickLink("link="+FVT_WikisData.CI_Box_Private_Wiki);
		
		//Open Comments Page
		clickLink(WikisObjects.Comments_Tab);
		
		//Add a comment
		editComment(FVT_WikisData.Comment_For_Private_Wiki);

		//Verify added comment
		VerifyCommentIsAdded(FVT_WikisData.Comment_For_Private_Wiki);
		
	  	//Logout of Wiki
	  	Logout();
	  	
	  	driver.close();
	  	
	  	verifyImFollowingTagsNewsStory("updated the comment on the wiki page Re: Welcome to "+FVT_WikisData.CI_Box_Private_Wiki+" in the "+FVT_WikisData.CI_Box_Private_Wiki+" wiki.","Wikis","automation",false, false);
	  	
		System.out.println("INFO: End of testEditCommentPrivateWiki");
	}
	

		
}
	
		