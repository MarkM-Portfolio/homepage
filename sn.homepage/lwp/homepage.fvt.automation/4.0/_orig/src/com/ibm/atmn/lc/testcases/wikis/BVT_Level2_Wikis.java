/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential */
/*                                                                   */
/* OCO Source Materials */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2013 */
/*                                                                   */
/* The source code for this program is not published or otherwise */
/* divested of its trade secrets, irrespective of what has been */
/* deposited with the U.S. Copyright Office. */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.lc.testcases.wikis;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.wikis.Data;
import com.ibm.atmn.lc.appobjects.wikis.TestcaseMethods;
import com.ibm.atmn.lc.appobjects.wikis.WikisObjects;

/*
 * This is the Wikis Team BVT for Level 2 Created By: Conor Pelly Date: 07/01/2011
 */

@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_Wikis extends TestcaseMethods {

	/*************************************************************
	 * Test main functionality of public wiki as the wiki creator* *
	 **********************************************************/

	private String CI_Box_Public_Wiki;
	private String CI_Box_Public_Wiki2;

	//private String CI_Box_Private_Wiki;
	//private String CI_Box_Private_Wiki2;

	public BVT_Level2_Wikis() {

		CI_Box_Public_Wiki = stamp(Data.CI_Box_Public_Wiki);
		CI_Box_Public_Wiki2 = stamp(Data.CI_Box_Public_Wiki2);
		//CI_Box_Private_Wiki = stamp(Data.CI_Box_Private_Wiki);
		//CI_Box_Private_Wiki2 = stamp(Data.CI_Box_Private_Wiki2);
	}

	//Create a public wiki
	@Test
	public void createANewPublicWiki() throws Exception {

		System.out.println("INFO: Start of Wikis Test 1");

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);

		//Login as a user to create the wiki (ie. Amy Jones66)
		Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);

		//Check for any error messages
		CheckForErrorsOnPage();

		//Click Start a Wiki button
		clickLink(WikisObjects.Start_New_Wiki_Button);

		//Create a new public wiki
		typeInTextField(WikisObjects.Wiki_Name_Field, CI_Box_Public_Wiki);

		//Click Save button
		clickLink(WikisObjects.Save_Button);

		//Verify homepage UI
		verifyNewHomePageUI(CI_Box_Public_Wiki, Data.Wiki_LDAP_Username, "");

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Wikis Test 1");
	}

	//Add a page
	@Test(dependsOnMethods = { "createANewPublicWiki" })
	public void addPagesToPublicWiki() throws Exception {

		System.out.println("INFO: Start of Test 2");

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);

		//Enter creators username & password
		Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);

		//Open newly created Wiki
		OpenWikiFromMyWikisTab(CI_Box_Public_Wiki);

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

	//Upload file
	@Test(dependsOnMethods = { "addPagesToPublicWiki" })
	public void uploadFileToPublicWiki() throws Exception {

		System.out.println("INFO: Start of Test 3");

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);

		//Enter creators username & password
		Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);

		//Open newly created Wiki
		OpenWikiFromMyWikisTab(CI_Box_Public_Wiki);

		//Generate random file name
		String randomName = genDateBasedRandVal();

		//Upload private file
		uploadAttachment(randomName, ".jpg", "Desert.jpg");

		//verify file has being uploaded correctly
		RenameAttachment(randomName, ".jpg", "Lighthouse.jpg");

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Test 3");
	}

	//Edit a page
	@Test(dependsOnMethods = { "uploadFileToPublicWiki" })
	public void editPageInPublicWiki() throws Exception {

		System.out.println("INFO: Start of Test 3");

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);

		//Enter creators username & password
		Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);

		//Open newly created Wiki
		OpenWikiFromMyWikisTab(CI_Box_Public_Wiki);

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
		AssertJUnit.assertTrue("FAIL: Changed page title isn't correct", sel.getText(WikisObjects.WikiHomePageTitleField).equals(Data.New_Peer_Page_For_Public_Wiki2));

		//Verify that content was added correctly
		VerifyAddedContentInHomepage(Data.New_Content_For_Public_Wiki, Data.Wiki_LDAP_Username);

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Test 3");
	}

	//Add a comment
	@Test(dependsOnMethods = { "editPageInPublicWiki" })
	public void addCommentToPublicWiki() throws Exception {

		System.out.println("INFO: Start of Test 4");

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);

		//Enter creators username & password
		Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);

		//Open newly created Wiki
		OpenWikiFromMyWikisTab(CI_Box_Public_Wiki);

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
	@Test(dependsOnMethods = { "addCommentToPublicWiki" })
	public void addATagToPublicWiki() throws Exception {

		System.out.println("INFO: Start of Test 5");

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);

		//Enter creators username & password
		Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);

		//Open newly created Wiki
		OpenWikiFromMyWikisTab(CI_Box_Public_Wiki);

		//Select 'Add a tag' link
		OpenTaggingEditor();

		//Add new tag to page
		AddATag(Data.Tag_For_Public_Wiki);

		//Verify tag has been added
		AssertJUnit.assertTrue("FAIL: Tag hasn't been added correctly", sel.isTextPresent(Data.Tag_For_Public_Wiki));

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Test 5");
	}

	//Recommend a page
	@Test(dependsOnMethods = { "addATagToPublicWiki" })
	public void recommendAPageForPublicWiki() throws Exception {

		System.out.println("INFO: Start of Test 6");

		int homePageRecommendCount = 0;

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);

		//Enter creators username & password
		Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);

		//Open newly created Wiki
		OpenWikiFromMyWikisTab(CI_Box_Public_Wiki);

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
	@Test(dependsOnMethods = { "recommendAPageForPublicWiki" })
	public void deletePage() throws Exception {

		System.out.println("INFO: Start of Test 7");

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);

		//Enter creators username & password
		Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);

		//Open newly created Wiki
		OpenWikiFromMyWikisTab(CI_Box_Public_Wiki);

		//Open Page Index to see all available pages
		clickLink(WikisObjects.Index_Link);

		//Open newly created child page
		clickLink("//a[contains(@title, '" + Data.New_Child_Page_For_Public_Wiki + "')]");

		//Delete specified page
		deletePage(Data.New_Child_Page_For_Public_Wiki);

		//Open Page Index to see all available pages
		clickLink(WikisObjects.Index_Link);

		//Verify wiki page has been deleted successfully
		AssertJUnit.assertTrue("FAIL: Wiki page still exists", !sel.isTextPresent(Data.New_Child_Page_For_Public_Wiki));

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Test 7");
	}

	//Edit wiki title
	@Test(dependsOnMethods = { "deletePage" })
	public void editPublicWiki() throws Exception {

		System.out.println("INFO: Start of Test 8");

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);

		//Enter creators username & password
		Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);

		//Open newly created Wiki
		OpenWikiFromMyWikisTab(CI_Box_Public_Wiki);

		//Open Edit Wiki form
		OpenEditWikiForm();

		//Recommend the current page
		changeWikiTitle(CI_Box_Public_Wiki2);

		//Verify that wiki title has been changed
		validateChangedWikiTitle(CI_Box_Public_Wiki2);

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Test 8");
	}

	//Delete wiki
	@Test(dependsOnMethods = { "editPublicWiki" })
	public void deletePublicWiki() throws Exception {

		System.out.println("INFO: Start of Test 9");

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);

		//Enter creators username & password
		Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);

		//Open newly created Wiki
		OpenWikiFromMyWikisTab(CI_Box_Public_Wiki2);

		//Recommend the current page
		DeleteThisWiki();

		//Verify wiki has been deleted successfully
		AssertJUnit.assertTrue("FAIL: Wiki still exists", !verifyWikiExists(CI_Box_Public_Wiki2, "Public"));

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Test 9");
		Thread.currentThread().getId();
	}

	/**************************************************************
	 * 
	 * Test main functionality of private wiki as the wiki creator* *
	 ***********************************************************/

	/*
	 * //Create a private wiki and add an Owner, Editor & Reader
	 * 
	 * @Test public void createANewPrivateWiki() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 10");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter readers username & password Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);
	 * 
	 * //Create private wiki with specified name createPrivateWikiWithUsers(Data.CI_Box_Private_Wiki);
	 * 
	 * //Verify homepage UI of newly created wiki verifyNewHomePageUI(Data.CI_Box_Private_Wiki, Data.Wiki_LDAP_Username,
	 * "");
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 10"); }
	 * 
	 * //Add a page
	 * 
	 * @Test public void addPagesToPrivateWiki() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 11");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter creators username & password Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	 * 
	 * //Select rootpage to create child pages AddAPeer(Data.New_Peer_Page_For_Private_Wiki,
	 * Data.New_Content_For_Private_Wiki_Peer);
	 * 
	 * //Add a new Peer pages to the wiki homepage AddAChild(Data.New_Child_Page_For_Private_Wiki,
	 * Data.New_Content_For_Private_Wiki_Child);
	 * 
	 * //Verify pages have been created VerifyPageExists(Data.New_Peer_Page_For_Private_Wiki);
	 * VerifyPageExists(Data.New_Child_Page_For_Private_Wiki);
	 * 
	 * //Logout of wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 11"); }
	 * 
	 * //Edit a page
	 * 
	 * @Test public void editPageInPrivateWiki() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 12");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter creators username & password Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	 * 
	 * //Open Page Index to see all available pages clickLink(WikisObjects.Index_Link);
	 * 
	 * //Open newly created peer page clickLink("//a[contains(@title, '" + Data.New_Child_Page_For_Private_Wiki +
	 * "')]");
	 * 
	 * //Edit the current page clickLink(WikisObjects.Edit_Button);
	 * 
	 * //Validate UI of wiki editor ValidateWikiEditMode();
	 * 
	 * //Validate tagging UI ValidateTaggingUIInEditMode();
	 * 
	 * //Edit page title in Edit mode sel.type(WikisObjects.Page_Name_Textfield_In_Editor,
	 * Data.New_Child_Page_For_Private_Wiki2);
	 * 
	 * //Add some content in CKEditor AddContentintheEditorforanExistingPage(Data.New_Content_For_Private_Wiki);
	 * 
	 * //Verify that new page title is correct AssertJUnit.assertTrue("FAIL: Changed page title isn't correct",
	 * sel.getText(WikisObjects.WikiHomePageTitleField).equals(Data.New_Child_Page_For_Private_Wiki2));
	 * 
	 * //Verify that content was added correctly VerifyAddedContentInHomepage(Data.New_Content_For_Private_Wiki,
	 * Data.Wiki_LDAP_Username);
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 12"); }
	 * 
	 * //Add a comment
	 * 
	 * @Test public void addCommentToPrivateWiki() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 13");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter creators username & password Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	 * 
	 * //Open Comments Page clickObject(WikisObjects.Comments_Tab);
	 * 
	 * //Add a comment AddAComment(Data.Comment_For_Private_Wiki);
	 * 
	 * //Verify added comment VerifyCommentIsAdded(Data.Comment_For_Private_Wiki);
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 13"); }
	 * 
	 * //Add a tag
	 * 
	 * @Test public void addATagToPrivateWiki() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 14");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter creators username & password Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(CI_Box_Private_Wiki);
	 * 
	 * //Select 'Add a tag' link OpenTaggingEditor();
	 * 
	 * //Add new tag to page AddATag(Data.Tag_For_Private_Wiki);
	 * 
	 * //Verify tag has been added AssertJUnit.assertTrue("FAIL: Tag hasn't been added correctly",
	 * sel.isTextPresent(Data.Tag_For_Private_Wiki));
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 14"); }
	 * 
	 * //Recommend a page
	 * 
	 * @Test public void recommendAPageOfPrivateWiki() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 15");
	 * 
	 * int homePageRecommendCount = 0;
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter creators username & password Login(Data.Wiki_LDAP_Username, Data.Wiki_LDAP_Password);
	 * 
	 * //Open newly created Wiki from My Wikis OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	 * 
	 * ///Verify that recommendation UI is correct before a user has recommended the page
	 * verifyRecommendationUI(homePageRecommendCount, false);
	 * 
	 * //Recommend the current page RecommendCurrentPage();
	 * 
	 * //Increment Recommendation counter homePageRecommendCount++;
	 * 
	 * ///Verify that recommendation UI is correct after a user has recommended the page
	 * verifyRecommendationUI(homePageRecommendCount, true);
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 15"); }
	 * 
	 * /************************************************************ Test main functionality of private wiki as the wiki
	 * owner* *********************************************************
	 */

	/*
	 * //Add a page
	 * 
	 * @Test public void addPagesToPrivateWikiAsOwner() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 16");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter owners username & password Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	 * 
	 * //Select rootpage to create child pages AddAPeer(Data.New_Owners_Peer_Page_For_Private_Wiki,
	 * Data.New_Content_For_Owners_Private_Wiki_Peer);
	 * 
	 * //Add a new Peer pages to the wiki homepage AddAChild(Data.New_Owners_Child_Page_For_Private_Wiki,
	 * Data.New_Content_For_Owners_Private_Wiki_Child);
	 * 
	 * //Verify pages have been created VerifyPageExists(Data.New_Owners_Peer_Page_For_Private_Wiki);
	 * VerifyPageExists(Data.New_Owners_Child_Page_For_Private_Wiki);
	 * 
	 * //Logout of wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 16"); }
	 * 
	 * //Edit a page
	 * 
	 * @Test public void editPageInPrivateWikiAsOwner() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 17");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter owners username & password Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	 * 
	 * //Open newly created peer page
	 * //clickLink("//a[contains(@title, '"+Data.New_Owners_Peer_Page_For_Private_Wiki+"')]"); clickLink("link=" +
	 * Data.New_Owners_Peer_Page_For_Private_Wiki);
	 * 
	 * //Edit the current page clickLink(WikisObjects.Edit_Button);
	 * 
	 * //Validate UI of wiki editor ValidateWikiEditMode();
	 * 
	 * //Validate tagging UI ValidateTaggingUIInEditMode();
	 * 
	 * //Edit page title in Edit mode sel.type(WikisObjects.Page_Name_Textfield_In_Editor,
	 * Data.New_Owners_Peer_Page_For_Private_Wiki2);
	 * 
	 * //Add some content in CKEditor AddContentintheEditorforanExistingPage(Data.New_Content_For_Owners_Private_Wiki);
	 * 
	 * //Verify that new page title is correct AssertJUnit.assertTrue("FAIL: Changed page title isn't correct",
	 * sel.getText(WikisObjects.WikiHomePageTitleField).equals(Data.New_Owners_Peer_Page_For_Private_Wiki2));
	 * 
	 * //Verify that content was added correctly VerifyAddedContentInHomepage(Data.New_Content_For_Owners_Private_Wiki,
	 * Data.Wiki_LDAP_Owner_Username);
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 17"); }
	 * 
	 * //Add a comment
	 * 
	 * @Test public void addCommentToPrivateWikiAsOwner() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 18");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter owners username & password Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(Data.CI_Box_Private_Wiki);
	 * 
	 * //Open Comments Page clickObject(WikisObjects.Comments_Tab);
	 * 
	 * //Add a comment AddAComment(Data.Comment_For_Owners_Private_Wiki);
	 * 
	 * //Verify added comment VerifyCommentIsAdded(Data.Comment_For_Owners_Private_Wiki);
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 18"); }
	 * 
	 * //Add a tag
	 * 
	 * @Test public void addATagToPrivateWikiAsOwner() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 19");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter owners username & password Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(CI_Box_Private_Wiki);
	 * 
	 * //Select 'Add a tag' link OpenTaggingEditor();
	 * 
	 * //Add new tag to page AddATag(Data.Tag_For_Owners_Private_Wiki);
	 * 
	 * //Verify tag has been added AssertJUnit.assertTrue("FAIL: Tag hasn't been added correctly",
	 * sel.isTextPresent(Data.Tag_For_Owners_Private_Wiki));
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 19"); }
	 * 
	 * //Recommend a page
	 * 
	 * @Test public void recommendAPageOfPrivateWikiAsOwner() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 20");
	 * 
	 * int homePageRecommendCount = 1;
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter owners username & password Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(CI_Box_Private_Wiki);
	 * 
	 * ///Verify that recommendation UI is correct before a user has recommended the page
	 * verifyRecommendationUI(homePageRecommendCount, false);
	 * 
	 * //Recommend the current page RecommendCurrentPage();
	 * 
	 * //Increment Recommendation counter homePageRecommendCount++;
	 * 
	 * ///Verify that recommendation UI is correct after a user has recommended the page
	 * verifyRecommendationUI(homePageRecommendCount, true);
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 20"); }
	 * 
	 * //Delete a page
	 * 
	 * @Test public void deletePageAsOwner() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 21");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter owners username & password Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(CI_Box_Private_Wiki);
	 * 
	 * //Open Page Index to see all available pages clickLink(WikisObjects.Index_Link);
	 * 
	 * //Open newly created child page clickLink("//a[contains(@title, '" + Data.New_Owners_Child_Page_For_Private_Wiki
	 * + "')]");
	 * 
	 * //Delete specified page deletePage(Data.New_Owners_Child_Page_For_Private_Wiki);
	 * 
	 * //Open Page Index to see all available pages clickLink(WikisObjects.Index_Link);
	 * 
	 * //Verify wiki page has been deleted successfully AssertJUnit.assertTrue("FAIL: Wiki page still exists",
	 * !sel.isTextPresent(Data.New_Owners_Child_Page_For_Private_Wiki));
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 21"); }
	 * 
	 * /************************************************************* Test main functionality of private wiki as the
	 * wiki editor* **********************************************************
	 */

	/*
	 * //Add a page
	 * 
	 * @Test public void addPagesToPrivateWikiAsEditor() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 22");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter editors username & password Login(Data.Wiki_LDAP_Editor_Username, Data.Wiki_LDAP_Editor_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(CI_Box_Private_Wiki);
	 * 
	 * //Select rootpage to create child pages AddAPeer(Data.New_Editors_Peer_Page_For_Private_Wiki,
	 * Data.New_Content_For_Editors_Private_Wiki_Peer);
	 * 
	 * //Add a new Peer pages to the wiki homepage AddAChild(Data.New_Editors_Child_Page_For_Private_Wiki,
	 * Data.New_Content_For_Editors_Private_Wiki_Child);
	 * 
	 * //Verify pages have been created VerifyPageExists(Data.New_Editors_Peer_Page_For_Private_Wiki);
	 * VerifyPageExists(Data.New_Editors_Child_Page_For_Private_Wiki);
	 * 
	 * //Logout of wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 22"); }
	 * 
	 * //Edit a page
	 * 
	 * @Test public void editPageInPrivateWikiAsEditor() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 23");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter editors username & password Login(Data.Wiki_LDAP_Editor_Username, Data.Wiki_LDAP_Editor_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(CI_Box_Private_Wiki);
	 * 
	 * //Open Page Index to see all available pages clickLink(WikisObjects.Index_Link);
	 * 
	 * //Open newly created peer page clickLink("//a[contains(@title, '" + Data.New_Editors_Child_Page_For_Private_Wiki
	 * + "')]");
	 * 
	 * //Edit the current page clickLink(WikisObjects.Edit_Button);
	 * 
	 * //Validate UI of wiki editor ValidateWikiEditMode();
	 * 
	 * //Validate tagging UI ValidateTaggingUIInEditMode();
	 * 
	 * //Edit page title in Edit mode sel.type(WikisObjects.Page_Name_Textfield_In_Editor,
	 * Data.New_Editors_Child_Page_For_Private_Wiki2);
	 * 
	 * //Add some content in CKEditor AddContentintheEditorforanExistingPage(Data.New_Content_For_Editors_Private_Wiki);
	 * 
	 * //Verify that new page title is correct AssertJUnit.assertTrue("FAIL: Changed page title isn't correct",
	 * sel.getText(WikisObjects.WikiHomePageTitleField).equals(Data.New_Editors_Child_Page_For_Private_Wiki2));
	 * 
	 * //Verify that content was added correctly VerifyAddedContentInHomepage(Data.New_Content_For_Editors_Private_Wiki,
	 * Data.Wiki_LDAP_Editor_Username);
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 23"); }
	 * 
	 * //Add a comment
	 * 
	 * @Test public void addCommentToPrivateWikiAsEditor() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 24");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter editors username & password Login(Data.Wiki_LDAP_Editor_Username, Data.Wiki_LDAP_Editor_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(CI_Box_Private_Wiki);
	 * 
	 * //Open Comments Page clickObject(WikisObjects.Comments_Tab);
	 * 
	 * //Add a comment AddAComment(Data.Comment_For_Editors_Private_Wiki);
	 * 
	 * //Verify added comment VerifyCommentIsAdded(Data.Comment_For_Editors_Private_Wiki);
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 24"); }
	 * 
	 * //Add a tag
	 * 
	 * @Test public void addATagToPrivateWikiAsEditor() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 25");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter editors username & password Login(Data.Wiki_LDAP_Editor_Username, Data.Wiki_LDAP_Editor_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(CI_Box_Private_Wiki);
	 * 
	 * //Select 'Add a tag' link OpenTaggingEditor();
	 * 
	 * //Add new tag to page AddATag(Data.Tag_For_Editors_Private_Wiki);
	 * 
	 * //Verify tag has been added AssertJUnit.assertTrue("FAIL: Tag hasn't been added correctly",
	 * sel.isTextPresent(Data.Tag_For_Editors_Private_Wiki));
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 25"); }
	 * 
	 * //Recommend a page
	 * 
	 * @Test public void recommendAPageOfPrivateWikiAsEditor() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 26");
	 * 
	 * int homePageRecommendCount = 2;
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter editors username & password Login(Data.Wiki_LDAP_Editor_Username, Data.Wiki_LDAP_Editor_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(CI_Box_Private_Wiki);
	 * 
	 * ///Verify that recommendation UI is correct before a user has recommended the page
	 * verifyRecommendationUI(homePageRecommendCount, false);
	 * 
	 * //Recommend the current page RecommendCurrentPage();
	 * 
	 * //Increment Recommendation counter homePageRecommendCount++;
	 * 
	 * ///Verify that recommendation UI is correct after a user has recommended the page
	 * verifyRecommendationUI(homePageRecommendCount, true);
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 26"); }
	 * 
	 * /************************************************************* Test main functionality of private wiki as the
	 * wiki reader* **********************************************************
	 */

	/*
	 * //Add a comment
	 * 
	 * @Test public void addCommentToPrivateWikiAsReader() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 27");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter readers username & password Login(Data.Wiki_LDAP_Reader_Username, Data.Wiki_LDAP_Reader_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(CI_Box_Private_Wiki);
	 * 
	 * //Open Comments Page clickObject(WikisObjects.Comments_Tab);
	 * 
	 * //Add a comment AddAComment(Data.Comment_For_Readers_Private_Wiki);
	 * 
	 * //Verify added comment VerifyCommentIsAdded(Data.Comment_For_Readers_Private_Wiki);
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 27"); }
	 * 
	 * //Recommend a page
	 * 
	 * @Test public void recommendAPageOfPrivateWikiAsReader() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 28");
	 * 
	 * int homePageRecommendCount = 3;
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter readers username & password Login(Data.Wiki_LDAP_Reader_Username, Data.Wiki_LDAP_Reader_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(CI_Box_Private_Wiki);
	 * 
	 * ///Verify that recommendation UI is correct before a user has recommended the page
	 * verifyRecommendationUI(homePageRecommendCount, false);
	 * 
	 * //Recommend the current page RecommendCurrentPage();
	 * 
	 * //Increment Recommendation counter homePageRecommendCount++;
	 * 
	 * ///Verify that recommendation UI is correct after a user has recommended the page
	 * verifyRecommendationUI(homePageRecommendCount, true);
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 28"); }
	 * 
	 * /*********************************************************** Verify edit & deletion of private wiki as the wiki
	 * owner* ********************************************************
	 */

	/*
	 * //Edit wiki title
	 * 
	 * @Test public void editPrivateWiki() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 29");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter creators username & password Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(CI_Box_Private_Wiki);
	 * 
	 * //Open Edit Wiki form OpenEditWikiForm();
	 * 
	 * //Recommend the current page changeWikiTitle(CI_Box_Private_Wiki2);
	 * 
	 * //Verify that wiki title has been changed validateChangedWikiTitle(CI_Box_Private_Wiki2);
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 29"); }
	 * 
	 * //Delete private wiki as owner
	 * 
	 * @Test public void deletePrivateWikiAsOwner() throws Exception {
	 * 
	 * System.out.println("INFO: Start of Test 30");
	 * 
	 * //Load the component LoadComponent(CommonObjects.ComponentWikis);
	 * 
	 * //Enter owners username & password Login(Data.Wiki_LDAP_Owner_Username, Data.Wiki_LDAP_Owner_Password);
	 * 
	 * //Open newly created Wiki OpenWikiFromMyWikisTab(CI_Box_Private_Wiki2);
	 * 
	 * //Recommend the current page DeleteThisWiki();
	 * 
	 * //Verify wiki has been deleted successfully AssertJUnit.assertTrue("FAIL: Wiki still exists",
	 * !verifyWikiExists(CI_Box_Private_Wiki2, "Private"));
	 * 
	 * //Logout of Wiki Logout();
	 * 
	 * System.out.println("INFO: End of Test 30"); }
	 */
}
