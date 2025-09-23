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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;



import com.ibm.atmn.waffle.core.Element;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.waffle.core.RCLocationExecutor;
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

	private User testUser1;
	private User testUser2;
	private List<Element> list = null;
	private List<WebElement> listw = null;
	private String Wiki = FVT_WikisData.CI_Box_Public_Wiki+genDateBasedRandVal();

	@Test 
	public void testMarkingWikisStoriesAsSaved_AllUpdates() throws Exception {

		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);

		Reporter.log("testUser1 ->  "+testUser1.getDisplayName());
		Reporter.log("testUser2 ->  "+testUser2.getDisplayName());
		Reporter.log("Wiki -> "+Wiki);

		System.out.println("INFO: Start of Communities Level 2 testMarkingWikisStoriesAsSaved_AllUpdates");	

		//Create wiki news story

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);

		//Login as a user to create the wiki (ie. Amy Jones450)		
		Login(testUser1);

		//Click Start a Wiki button
		clickLink(WikisObjects.Start_New_Wiki_Button);


		//Create a new public wiki
		typeInTextField(FVT_WikisObjects.Wiki_Name_Field, Wiki);

		//Click Save button
		clickLink(FVT_WikisObjects.Save_Button);

		//Verify homepage UI
		//verifyNewHomePageUI(Wiki, CommonData.IC_LDAP_Username450, "");
		verifyNewHomePageUI(Wiki, testUser1.getFirstName(), "");

		//Logout of Wiki
		LogoutAndClose();	

		//Follow Wiki

		LoadComponent(CommonObjects.ComponentWikis);

		//Log in
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);

		//Go to public wikis
		clickLink(FVT_WikisObjects.Public_Wikis_Tab);

		//Go to public Forums
		clickLink("link="+Wiki);

		//Follow Wiki
		//clickLink(FVT_WikisObjects.FollowWiki);
		driver.getFirstElement("css=li.lotusFirst a#lconn_core_FollowMenu_0").click();
		driver.getFirstElement(FVT_WikisObjects.Menu_Item_2).click();
		//clickLink(FVT_WikisObjects.Menu_Item_2);

		//Logout
		LogoutAndClose();

		//

		LoadComponent(CommonObjects.ComponentNews);

		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);

		clickLink(FVT_HomepageObjects.Discover);

		filterBy("All Updates");

		//cautiousFocus("css=div.lotusPostContent:contains('created a new wiki named "+Wiki+".')");

		//list = driver.getElements("css=[id^='com_ibm_social_as_item_NewsItem_']:contains('created a wiki named "+Wiki+"')");
		WebDriver wd = (WebDriver) driver.getBackingObject();
		list = driver.getElements("css=li[id^='com_ibm_social_as_item_NewsItem_']");

		listw = wd.findElements(By.cssSelector("li[id^='com_ibm_social_as_item_NewsItem_']"));

		Reporter.log("Before loops");
		WebElement we;

		for(Element e:list){
			e.hover();
			String temp = e.getText();
			Reporter.log(temp);

			//New Lines - testing
			we = (WebElement) e.getBackingObject();
			String newtemp = we.findElement(By.cssSelector("div.lotusPostMore ul.lotusInlinelist li.lotusFirst a")).getText();

			Reporter.log("Save  ->   "+newtemp);
			Reporter.log(testUser1.getDisplayName()+" created a wiki named "+Wiki);

			if(we.getText().contains(testUser1.getDisplayName()+" created a wiki named "+Wiki)){
				Reporter.log("Stuff happens");
				Reporter.log(" -> "+we.getText());
				we.findElement(By.cssSelector("li.lotusFirst a[role='button']")).click();
				break;
			}

		}

		Reporter.log("Loop end");

		clickLink(FVT_HomepageObjects.Home);

		clickLink(FVT_HomepageObjects.Discover);

		filterBy("All Updates");

		list = driver.getElements("css=li[id^='com_ibm_social_as_item_NewsItem_']");
		//cautiousFocus("css=div.lotusPostContent:contains('css=div.lotusPostContent:contains('created a new wiki named "+Wiki+".')");

		Reporter.log("Before for loop to check if link is changed to saved");
		for(Element e:list){
			e.hover();
			Reporter.log("Hovering ...");
			we = (WebElement) e.getBackingObject();
			e.hover();
			Reporter.log(">> "+we.getText());
			if(we.getText().contains(testUser1.getDisplayName()+" created a wiki named "+Wiki)){
				Reporter.log("in if statement");
				e.hover();
				//AssertJUnit.assertTrue("Fail: Link still there!!", we.findElement(By.cssSelector("div.lotusPostMore ul.lotusInlinelist li.lotusFirst a")).getText().contains("Saved"));
				AssertJUnit.assertTrue("Fail: Link still there!!", e.isTextPresent("Saved"));
				break;

			}
		}
		//driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		//AssertJUnit.assertTrue("Fail: Link still there!!", driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").isTextPresent("Saved"));

		//

		clickLink(FVT_HomepageObjects.Saved);

		Reporter.log(testUser2.getDisplayName()+" created a wiki page named Welcome to "+Wiki+" in the "+Wiki+" wiki.");
		//Reporter.log("Get Text -> "+driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent div.lotusPostAction").getText());
		//assertTrue("Fail: text is not present!!", driver.isTextPresent("created a wiki page named Welcome to "+Wiki+" in the "+Wiki+" wiki."));
		AssertJUnit.assertEquals(testUser1.getDisplayName()+" created a wiki named "+Wiki+".", driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent div.lotusPostAction").getText());
		//

		filterBy("All Updates");

		//cautiousFocus("css=div.lotusPostContent:contains('created a new wiki named "+Wiki+".')");

		//driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		driver.getFirstElement("link="+Wiki).hover();
		//clickLink(FVT_NewsObjects.DeleteSavedStory);
		driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).click();

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		//clickLink(FVT_NewsObjects.RemoveSavedStory);
		driver.getFirstElement(FVT_NewsObjects.RemoveSavedStory).click();

		AssertJUnit.assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//

		clickLink(FVT_HomepageObjects.Discover);

		filterBy("All Updates");

		//cautiousFocus("css=div.lotusPostContent:contains('created a new wiki named "+Wiki+".')");

		AssertJUnit.assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));

		//Logout
		LogoutAndClose();


		System.out.println("INFO: End of Wikis FVT_Level_2 testMarkingWikisStoriesAsSaved_AllUpdates");

	}




	@Test (dependsOnMethods = { "testMarkingWikisStoriesAsSaved_AllUpdates" })
	public void testMarkingWikisStoriesAsSaved2_AllUpdates() throws Exception {


		System.out.println("INFO: Start of Wikis FVT_Level_2 testMarkingWikisStoriesAsSavedPart2");

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);

		//Enter creators username & password
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);	
		Login(testUser1);

		//Open newly created Wiki
		OpenWikiFromMyWikisTab(Wiki);

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
		LogoutAndClose();

		//Save the wiki news story

		LoadComponent(CommonObjects.ComponentNews);

		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);

		clickLink(FVT_HomepageObjects.Home);

		clickLink(FVT_HomepageObjects.ImFollowing);

		filterBy("All Updates");

		//cautiousFocus("css=div.lotusPostContent:contains('edited the wiki page Welcome in the "+Wiki+" wiki.')");

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_HomepageObjects.SaveThis);

		//Check that the story saved

		clickLink(FVT_HomepageObjects.Home);

		clickLink(FVT_HomepageObjects.ImFollowing);

		filterBy("All Updates");

		//cautiousFocus("css=div.lotusPostContent:contains('edited the wiki page Welcome in the "+Wiki+" wiki.')");

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		//assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));	
		AssertJUnit.assertTrue("Fail: Link still there!!", driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").isTextPresent("Saved"));

		//Check that the story appears in Saved

		clickLink(FVT_HomepageObjects.Saved);

		Reporter.log("edited the wiki page Welcome to "+Wiki+" in the "+FVT_WikisData.New_Content_For_Public_Wiki+" wiki.");
		AssertJUnit.assertTrue("Fail: text is not present!!", driver.isTextPresent("edited the wiki page Welcome to "+Wiki+" in the "+Wiki+" wiki."));

		//Delete the wiki news story

		filterBy("All Updates");

		//cautiousFocus("css=div.lotusPostContent:contains('edited the wiki page Welcome in the "+Wiki+" wiki.')");

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_NewsObjects.DeleteSavedStory);

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		AssertJUnit.assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));

		//Check that the wiki news story is visible again

		clickLink(FVT_HomepageObjects.ImFollowing);

		filterBy("All Updates");

		//cautiousFocus("css=div.lotusPostContent:contains('edited the wiki page Welcome in the "+Wiki+" wiki.')");

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		AssertJUnit.assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));

		LogoutAndClose();

		System.out.println("INFO: End of Wikis FVT_Level_2 testMarkingWikisStoriesAsSaved2_AllUpdates");

	}

	@Test (dependsOnMethods = { "testMarkingWikisStoriesAsSaved2_AllUpdates" })
	public void testMarkingWikisStoriesAsSaved3_AllUpdates() throws Exception {


		System.out.println("INFO: Start of Wikis FVT_Level_2 testMarkingWikisStoriesAsSaved3_AllUpdates");

		//Create wiki news story

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);

		//Enter creators username & password
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);		
		Login(testUser2);

		//Go to public wikis
		clickLink(FVT_WikisObjects.Public_Wikis_Tab);

		//Open wiki
		clickLink("link="+Wiki);

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
		LogoutAndClose();

		//Save the wiki news story

		LoadComponent(CommonObjects.ComponentNews);

		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);

		clickLink(FVT_HomepageObjects.MyNotifications);

		filterBy("All Updates");

		//cautiousFocus("css=div.lotusPostContent:contains(' edited the wiki page Welcome in your "+Wiki+" wiki.')");

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_HomepageObjects.SaveThis);

		//Check that the story saved

		clickLink(FVT_HomepageObjects.Home);

		clickLink(FVT_HomepageObjects.Discover);

		//cautiousFocus("css=div.lotusPostContent:contains(' edited the wiki page Welcome in the "+Wiki+" wiki.')");

		AssertJUnit.assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));

		//Check that the story appears in Saved

		clickLink(FVT_HomepageObjects.Saved);

		Reporter.log("edited the wiki page Welcome to "+Wiki+" in the "+Wiki+" wiki.");
		//assertTrue("Fail: text is not present!!", driver.isTextPresent("edited the wiki page Welcome "+Wiki+" in the "+Wiki+" wiki."));
		AssertJUnit.assertEquals(testUser2.getDisplayName()+" edited the wiki page Welcome to "+Wiki+" in the "+Wiki+" wiki.", driver.getFirstElement("css=div.lotusPostContent div.lotusPostAction").getText());

		//Delete the wiki news story

		filterBy("All Updates");

		//cautiousFocus("css=div.lotusPostContent:contains(' edited the wiki page Welcome in the "+Wiki+" wiki.')");

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_NewsObjects.DeleteSavedStory);

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		AssertJUnit.assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again

		clickLink(FVT_HomepageObjects.MyNotifications);

		filterBy("All Updates");

		//cautiousFocus("css=div.lotusPostContent:contains(' edited the wiki page Welcome in your "+Wiki+" wiki.')");

		AssertJUnit.assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));

		//Logout
		LogoutAndClose();


		System.out.println("INFO: End of Wikis FVT_Level_2 testMarkingWikisStoriesAsSaved3_AllUpdates");

	}


}