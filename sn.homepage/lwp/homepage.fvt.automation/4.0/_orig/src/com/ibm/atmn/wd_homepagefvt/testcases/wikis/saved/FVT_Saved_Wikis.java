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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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



public class FVT_Saved_Wikis extends FVT_WikisMethods {

	
	private static String WikiComment = "";
	
	//@Test 
	public void testMarkingWikisStoriesAsSaved() throws Exception {
			
			
		System.out.println("INFO: Start of Wikis Level 2 testMarkingWikisStoriesAsSaved");	
		
		//Start Wiki
		
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
		//verifyNewHomePageUI(FVT_WikisData.CI_Box_Public_Wiki, CommonData.IC_LDAP_Username450, "");
			
		//Logout of Wiki
		Logout();	
		
		//Follow Wiki
		
		driver.close();
		
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Log in
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Go to wiki
		followWiki(FVT_WikisData.CI_Box_Public_Wiki, "Public");
				
		//Logout
		Logout();
		
		driver.close();
			
		//Save the wikis news story
		
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		clickLink(FVT_HomepageObjects.Discover);
			
		filterBy("Wikis");
	
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String WikiStory = "css=div.lotusPostContent:contains('created a wiki named "+FVT_WikisData.CI_Box_Public_Wiki+".')";
		
		//builder.moveToElement((WebElement) driver.getFirstElement(WikiStory).getBackingObject()).moveToElement((WebElement) Save.getBackingObject()).click().build().perform();	
		builder.moveToElement((WebElement) driver.getFirstElement(WikiStory).getBackingObject()).moveToElement((WebElement) driver.getSingleElement(FVT_HomepageObjects.SaveThis_2).getBackingObject()).click().build().perform();
			
		//Check that the story is saved 
			
		clickLink(FVT_HomepageObjects.Refresh_Discover);
		
		filterBy("Wikis");
				
		driver.getSingleElement("css=div.lotusPostContent:contains('created a wiki named "+FVT_WikisData.CI_Box_Public_Wiki+".')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created a wiki named "+FVT_WikisData.CI_Box_Public_Wiki+"."));
			
		//Delete the news story
		
		filterBy("Wikis");
		
		builder.moveToElement((WebElement) driver.getFirstElement(WikiStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();	
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("Wikis");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created a wiki named "+FVT_WikisData.CI_Box_Public_Wiki+".')").hover();

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Logout
		Logout();
			
			
		System.out.println("INFO: End of Wikis FVT_Level_2 testMarkingWikisStoriesAsSaved");
			
	}
	
	
	
	
	//@Test (dependsOnMethods = { "testMarkingWikisStoriesAsSaved" })
	public void testMarkingWikisStoriesAsSavedPart2() throws Exception {
		
		
		System.out.println("INFO: Start of Wikis FVT_Level_2 testMarkingWikisStoriesAsSavedPart2");
	
		//Create wiki news story
		
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
		driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).clear();
		driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).type(FVT_WikisData.New_Peer_Page_For_Public_Wiki2);
		
		//Add some content in CKEditor
		AddContentintheEditorforanExistingPage(FVT_WikisData.New_Content_For_Public_Wiki);
		
		//Verify that new page title is correct
		//Assert.assertTrue("FAIL: Changed page title isn't correct", driver.getText(WikisObjects.WikiHomePageTitleField).equals(Data.New_Peer_Page_For_Public_Wiki2));
		
		Assert.assertTrue("FAIL: Changed page title isn't correct", driver.getSingleElement(WikisObjects.WikiHomePageTitleField).getText().compareToIgnoreCase(FVT_WikisData.New_Peer_Page_For_Public_Wiki2) == 0);

		
		//Verify that content was added correctly
		VerifyAddedContentInHomepage(FVT_WikisData.New_Content_For_Public_Wiki);
		
	  	//Logout of Wiki
	  	Logout();
	  	
	  	driver.close();
		
		//Save the Wikis news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.AllUpdates);

		filterBy("Wikis");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String WikiEditStory = "css=div.lotusPostContent:contains('edited the wiki page "+FVT_WikisData.New_Peer_Page_For_Public_Wiki2+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')";
		
		builder.moveToElement((WebElement) driver.getFirstElement(WikiEditStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();	

		//cautiousFocus("css=div.lotusPostContent:contains('edited the wiki page Welcome in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')");

		//clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the story saved 
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.AllUpdates);
		
		filterBy("Wikis");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('edited the wiki page "+FVT_WikisData.New_Peer_Page_For_Public_Wiki2+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')").hover();
		
		//cautiousFocus("css=div.lotusPostContent:contains('edited the wiki page Welcome in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')");
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));			
		
		//Check that the story appears in Saved 
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("edited the wiki page "+FVT_WikisData.New_Peer_Page_For_Public_Wiki2+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki."));
		
		//Delete the wiki news story
		
		filterBy("Wikis");
		
		builder.moveToElement((WebElement) driver.getFirstElement(WikiEditStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();	

		//clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));
		
		//Check that the Save this link is visible again 
		
		clickLink(FVT_HomepageObjects.AllUpdates);
		
		filterBy("Wikis");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('edited the wiki page "+FVT_WikisData.New_Peer_Page_For_Public_Wiki2+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')").hover();

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));
		
		Logout();
		
		System.out.println("INFO: End of Wikis FVT_Level_2 testMarkingWikisStoriesAsSavedPart2");
		
	}
	
	//@Test (dependsOnMethods = { "testMarkingWikisStoriesAsSavedPart2" })
	public void testMarkingWikisStoriesAsSavedPart3() throws Exception {
		
		
		System.out.println("INFO: Start of Wikis FVT_Level_2 testMarkingWikisStoriesAsSavedPart3");
	
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
		driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).clear();
		driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).type(FVT_WikisData.New_Peer_Page_For_Public_Wiki2);
		
		//Add some content in CKEditor
		AddContentintheEditorforanExistingPage(FVT_WikisData.New_Content_For_Public_Wiki);
		
		//Verify that new page title is correct
		//Assert.assertTrue("FAIL: Changed page title isn't correct", driver.getText(WikisObjects.WikiHomePageTitleField).equals(Data.New_Peer_Page_For_Public_Wiki2));
		
		Assert.assertTrue("FAIL: Changed page title isn't correct", driver.getSingleElement(WikisObjects.WikiHomePageTitleField).getText().compareToIgnoreCase(Data.New_Peer_Page_For_Public_Wiki2) == 0);

		
		//Verify that content was added correctly
		VerifyAddedContentInHomepage(FVT_WikisData.New_Content_For_Public_Wiki);
		
	  	//Logout of Wiki
	  	Logout();
	  	
	  	driver.close();
		
		//Save the wiki news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("Wikis");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String WikiEditStory2 = "css=div.lotusPostContent:contains('edited the wiki page "+FVT_WikisData.New_Peer_Page_For_Public_Wiki2+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')";
		
		builder.moveToElement((WebElement) driver.getFirstElement(WikiEditStory2).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();	
		
		//cautiousFocus("css=div.lotusPostContent:contains(' edited the wiki page Welcome in your "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')");

		//clickLink(FVT_HomepageObjects.SaveThis);
	
		//Check that the news story saved 
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('edited the wiki page "+FVT_WikisData.New_Peer_Page_For_Public_Wiki2+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')").hover();

		//cautiousFocus("css=div.lotusPostContent:contains(' edited the wiki page Welcome in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')");
	
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Check that the story appears in Saved 
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("edited the wiki page "+FVT_WikisData.New_Peer_Page_For_Public_Wiki2+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki."));
		
		//Delete the wiki news story
		
		filterBy("Wikis");
		
		builder.moveToElement((WebElement) driver.getFirstElement(WikiEditStory2).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();	

		//clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("Wikis");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('edited the wiki page "+FVT_WikisData.New_Peer_Page_For_Public_Wiki2+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')").hover();

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Logout
		Logout();
		
		
		System.out.println("INFO: End of Wikis FVT_Level_2 testMarkingWikisStoriesAsSavedPart3");
		
	}
	
	@Test
	public void testMarkingCommentedWikisStoriesAsSaved() throws Exception {
		
		
		System.out.println("INFO: Start of Wikis Level 2 testMarkingCommentedWikisStoriesAsSaved");	
		
		//Start Wiki
		
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
		//verifyNewHomePageUI(FVT_WikisData.CI_Box_Public_Wiki, CommonData.IC_LDAP_Username450, "");
			
		WikiComment = AddAComment(FVT_WikisData.Comment_For_Public_Wiki);
		
		//Logout of Wiki
		Logout();	
		
		driver.close();
			
		//Save the wikis news story
		
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		clickLink(FVT_HomepageObjects.Discover);
			
		filterBy("Wikis");
	
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String WikiStory = "css=div.lotusPostContent:contains('commented on the wiki page Re: Welcome to "+FVT_WikisData.CI_Box_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')";
		
		//builder.moveToElement((WebElement) driver.getFirstElement(WikiStory).getBackingObject()).moveToElement((WebElement) Save.getBackingObject()).click().build().perform();	
		builder.moveToElement((WebElement) driver.getFirstElement(WikiStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
			
		//cautiousFocus("css=div.lotusPostContent:contains('created a new wiki named "+FVT_WikisData.CI_Box_Public_Wiki+".')");

		//clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the story is saved 
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("Wikis");
		
		//cautiousFocus("css=div.lotusPostContent:contains('css=div.lotusPostContent:contains('created a new wiki named "+FVT_WikisData.CI_Box_Public_Wiki+".')");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('commented on the wiki page Re: Welcome to "+FVT_WikisData.CI_Box_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		filterBy("Wikis");
		
		Thread.sleep(2000);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent("commented on the wiki page Re: Welcome to "+FVT_WikisData.CI_Box_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki."));
			
		assertTrue("Fail: Element is not present!!", driver.isElementPresent(FVT_HomepageObjects.RollUpComment));
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(WikiComment));
		
		//Logout
		Logout();
			
			
		System.out.println("INFO: End of Wikis FVT_Level_2 testMarkingCommentedWikisStoriesAsSaved");
			
	}
	
	
	@Test  (dependsOnMethods = { "testMarkingCommentedWikisStoriesAsSaved" })
	public void testMarkingCommentedWikisStoriesAsSavedPart2() throws Exception {
		
		
		System.out.println("INFO: Start of Wikis Level 2 testMarkingCommentedWikisStoriesAsSavedPart2");	
		
		//Start Wiki
		
		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
			
		//Login as a user to create the wiki (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
		
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
		driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).clear();
		driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).type(FVT_WikisData.New_Peer_Page_For_Public_Wiki2);
		
		//Add some content in CKEditor
		AddContentintheEditorforanExistingPage(FVT_WikisData.New_Content_For_Public_Wiki);
		
		//Verify that new page title is correct
		//Assert.assertTrue("FAIL: Changed page title isn't correct", driver.getText(WikisObjects.WikiHomePageTitleField).equals(Data.New_Peer_Page_For_Public_Wiki2));
		
		Assert.assertTrue("FAIL: Changed page title isn't correct", driver.getSingleElement(WikisObjects.WikiHomePageTitleField).getText().compareToIgnoreCase(Data.New_Peer_Page_For_Public_Wiki2) == 0);

		
		//Verify that content was added correctly
		VerifyAddedContentInHomepage(FVT_WikisData.New_Content_For_Public_Wiki);
		
		//Logout of Wiki
		Logout();	
		
		driver.close();
			
		//Save the wikis news story
		
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//
			
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("Wikis");
				
		driver.getSingleElement("css=div.lotusPostContent:contains('updated the wiki page "+FVT_WikisData.New_Peer_Page_For_Public_Wiki2+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.')").hover();
		
			
		//Check that the story still appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		filterBy("Wikis");
		
		Thread.sleep(2000);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent("commented on the wiki page Re: Welcome to "+FVT_WikisData.CI_Box_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki."));
			
		assertTrue("Fail: Element is not present!!", driver.isElementPresent(FVT_HomepageObjects.RollUpComment));
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(WikiComment));
		
		//Logout
		Logout();
			
			
		System.out.println("INFO: End of Wikis FVT_Level_2 testMarkingCommentedWikisStoriesAsSavedPart2");
			
	}
	

}