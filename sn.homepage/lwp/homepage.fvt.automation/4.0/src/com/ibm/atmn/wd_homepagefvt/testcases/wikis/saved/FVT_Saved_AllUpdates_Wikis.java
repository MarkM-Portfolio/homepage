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

package com.ibm.atmn.wd_homepagefvt.testcases.wikis.saved;

import org.junit.Assert;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;



import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.Data;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisData;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.wikis.FVT_WikisMethods;

//****************************************
//NEEDS TO BE UPDATED
//****************************************

public class FVT_Saved_AllUpdates_Wikis extends FVT_WikisMethods {

		
	@Test 
	public void testMarkingWikisStoriesAsSaved_AllUpdates() throws Exception {
			
			
		System.out.println("INFO: Start of Communities Level 2 testMarkingWikisStoriesAsSaved_AllUpdates");	
		
		//Create wiki news story
		
		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
			
		//Login as a user to create the wiki (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
		
		//Click Start a Wiki button
		clickLink(WikisObjects.Start_New_Wiki_Button);

		//Create a new public wiki
		typeInTextField(FVT_WikisObjects.Wiki_Name_Field, FVT_WikisData.CI_Box_Public_Wiki);

		//Click Save button
		clickLink(FVT_WikisObjects.Save_Button);

		//Verify homepage UI
		verifyNewHomePageUI(FVT_WikisData.CI_Box_Public_Wiki, CommonData.IC_LDAP_Username450, "");
			
		//Logout of Wiki
		Logout();	
		
		//Follow Wiki
		
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Log in
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Go to public wikis
		clickLink(FVT_WikisObjects.Public_Wikis_Tab);
		
		//Go to public Forums
		clickLink("link="+FVT_WikisData.CI_Box_Public_Wiki);
		
		//Follow Wiki
		clickLink(FVT_WikisObjects.FollowWiki);
		clickLink(FVT_WikisObjects.Menu_Item_2);
	
		//Logout
		Logout();
			
		//
		
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		clickLink(FVT_HomepageObjects.Discover);
			
		filterBy("All Updates");
			
		//cautiousFocus("css=div.lotusPostContent:contains('created a new wiki named "+FVT_WikisData.CI_Box_Public_Wiki+".')");

		clickLink(FVT_HomepageObjects.SaveThis);
		
		//
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('css=div.lotusPostContent:contains('created a new wiki named "+FVT_WikisData.CI_Box_Public_Wiki+".')");
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
			
		//
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created a new wiki named "+FVT_WikisData.CI_Box_Public_Wiki+"."));
			
		//
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new wiki named "+FVT_WikisData.CI_Box_Public_Wiki+".')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//
		
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new wiki named "+FVT_WikisData.CI_Box_Public_Wiki+".')");

		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		//Logout
		Logout();
			
			
		System.out.println("INFO: End of Wikis FVT_Level_2 testMarkingWikisStoriesAsSaved_AllUpdates");
			
	}
	
	
	
	
	@Test (dependsOnMethods = { "testMarkingWikisStoriesAsSaved_AllUpdates" })
	public void testMarkingWikisStoriesAsSaved2_AllUpdates() throws Exception {
		
		
		System.out.println("INFO: Start of Wikis FVT_Level_2 testMarkingWikisStoriesAsSavedPart2");
	
		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter creators username & password
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);		
					
		//Open newly created Wiki
		OpenWikiFromMyWikisTab(FVT_WikisData.CI_Box_Public_Wiki);
	
		//Edit the current page
		clickLink(WikisObjects.Edit_Button);
		
		//Validate UI of wiki editor
		ValidateWikiEditMode();
		
		//Validate tagging UI
		ValidateTaggingUIInEditMode();
		
		//Edit page title in Edit mode
		//driver.type(WikisObjects.Page_Name_Textfield_In_Editor, FVT_WikisData.New_Peer_Page_For_Public_Wiki2);
		
		//Add some content in CKEditor
		AddContentintheEditorforanExistingPage(FVT_WikisData.New_Content_For_Public_Wiki);
		
		//Verify that new page title is correct
		//Assert.assertTrue("FAIL: Changed page title isn't correct", driver.getText(WikisObjects.WikiHomePageTitleField).equals(Data.New_Peer_Page_For_Public_Wiki2));
		
		//Verify that content was added correctly
		//VerifyAddedContentInHomepage(FVT_WikisData.New_Content_For_Public_Wiki, CommonData.IC_LDAP_Username450);
		
	  	//Logout of Wiki
	  	Logout();
		
		//Save the wiki news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.ImFollowing);

		filterBy("All Updates");

		//cautiousFocus("css=div.lotusPostContent:contains('edited the wiki page Welcome in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')");

		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the story saved
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('edited the wiki page Welcome in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')");
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));			
		
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("edited the wiki page Welcome in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki."));
		
		//Delete the wiki news story
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('edited the wiki page Welcome in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));
		
		//Check that the wiki news story is visible again
		
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('edited the wiki page Welcome in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')");

		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		Logout();
		
		System.out.println("INFO: End of Wikis FVT_Level_2 testMarkingWikisStoriesAsSaved2_AllUpdates");
		
	}
	
	@Test (dependsOnMethods = { "testMarkingWikisStoriesAsSaved2_AllUpdates" })
	public void testMarkingWikisStoriesAsSaved3_AllUpdates() throws Exception {
		
		
		System.out.println("INFO: Start of Wikis FVT_Level_2 testMarkingWikisStoriesAsSaved3_AllUpdates");
	
		//Create wiki news story
		
		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter creators username & password
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);		
					
		//Go to public wikis
		clickLink(FVT_WikisObjects.Public_Wikis_Tab);
		
		//Open wiki
		clickLink("link="+FVT_WikisData.CI_Box_Public_Wiki);
		
		//Edit the current page
		clickLink(WikisObjects.Edit_Button);
		
		//Validate UI of wiki editor
		ValidateWikiEditMode();
		
		//Validate tagging UI
		ValidateTaggingUIInEditMode();
		
		//Edit page title in Edit mode
		//driver.type(WikisObjects.Page_Name_Textfield_In_Editor, FVT_WikisData.New_Peer_Page_For_Public_Wiki2);
		
		//Add some content in CKEditor
		AddContentintheEditorforanExistingPage(FVT_WikisData.New_Content_For_Public_Wiki);
		
		//Verify that new page title is correct
		//Assert.assertTrue("FAIL: Changed page title isn't correct", driver.getText(WikisObjects.WikiHomePageTitleField).equals(Data.New_Peer_Page_For_Public_Wiki2));
		
		//Verify that content was added correctly
		//VerifyAddedContentInHomepage(FVT_WikisData.New_Content_For_Public_Wiki, CommonData.IC_LDAP_Username450);
		
	  	//Logout of Wiki
	  	Logout();
		
		//Save the wiki news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains(' edited the wiki page Welcome in your "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')");

		clickLink(FVT_HomepageObjects.SaveThis);
	
		//Check that the story saved
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		//cautiousFocus("css=div.lotusPostContent:contains(' edited the wiki page Welcome in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')");
	
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(" edited the wiki page Welcome in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki."));
		
		//Delete the wiki news story
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains(' edited the wiki page Welcome in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again

		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains(' edited the wiki page Welcome in your "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')");

		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		//Logout
		Logout();
		
		
		System.out.println("INFO: End of Wikis FVT_Level_2 testMarkingWikisStoriesAsSaved3_AllUpdates");
		
	}
	

}