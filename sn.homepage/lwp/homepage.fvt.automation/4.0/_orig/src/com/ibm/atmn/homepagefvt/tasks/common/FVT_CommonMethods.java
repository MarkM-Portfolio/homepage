/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.homepagefvt.tasks.common;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.AssertJUnit;
import org.testng.ITestContext;

import com.ibm.atmn.base.PageActionMethods;
import com.ibm.atmn.base.UserAllocation;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;

import java.util.*;


public abstract class FVT_CommonMethods extends PageActionMethods {

	/*
	 * Created By: Conor Pelly Date: 07/06/2011 Purpose: Set of common methods that are used across all components
	 */
	public FVT_CommonMethods() {

		super();
	}

	@BeforeSuite(alwaysRun = true)
	protected void beforeSuiteSetup(ITestContext context) {

		masterBeforeSuiteSetup(context);
	}

	@BeforeMethod
	public void setUp(ITestContext context) {

		masterSetUp(context);
	}

	@AfterMethod
	public void tearDown() {

		masterTearDown();
	}

	@AfterClass
	public void classTearDown() {

		System.out.println("TEST INFO: Clearing id from user maps...");
		UserAllocation allocator = UserAllocation.getInstance();
		allocator.clearAllUsers();
	}

	private void activateBannerForLink(String link) {

		Map<String, String> banner_fixed_anchors = getBannerLinksMap();
		String element = banner_fixed_anchors.get(link);

		mouseOverAndWait(element);
	}

	/** Simple Method for checking for errors on a page */
	public void CheckForErrorsOnPage() {

		//Assert.assertFalse("FAIL: Error message is displaying", sel.isTextPresent("We have encountered a problem"));
		AssertJUnit.assertFalse("FAIL: Error message is displaying", sel.isTextPresent("An error has occurred, contact your system Administrator"));
		AssertJUnit.assertFalse("FAIL: Error message is displaying", sel.isTextPresent("Page not found"));
		AssertJUnit.assertFalse("FAIL: Error message is displaying", sel.isTextPresent("Page Not Found"));
		//Assert.assertFalse("FAIL: Error message is displaying", sel.isTextPresent("An error occurred while displaying content."));
	}

	public void clickBannerLink(String link) {

		activateBannerForLink(link);
		clickLink(link);
	}

	public void CreateCommunity(String CommName, String NameofComm, String CommTag, String TagForComm) {

		clickLink(CommonObjects.StartButton);
		sel.type(CommName, NameofComm);
		sleep(500);
		sel.type(CommTag, TagForComm);
		sleep(500);
		sel.click(CommonObjects.SaveButton);
		sleep(1000);
		//Wait for text Overview to appear before continuing
		try {
			int i = 0;
			while (i < 60) {
				if (sel.isTextPresent("Community Actions")) {
					sleep(1000);
					i = i + 1;
				}
				break;
			}
			sleep(1000);
		} catch (Exception e) {
			sleep(2000);
		}
	}

	public void FormSaveButton(String SaveButton) {

		clickLink(SaveButton);
	}

	private Map<String, String> getBannerLinksMap() {

		Map<String, String> banner_fixed_anchors = new HashMap<String, String>();

		banner_fixed_anchors.put(CommonObjects.Banner_Homepage_Base_List_Link, CommonObjects.Banner_Homepage_Base_List_Link);

		banner_fixed_anchors.put(CommonObjects.Banner_Profiles_Base_List_Link, CommonObjects.Banner_Profiles_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Profiles_MyProfile, CommonObjects.Banner_Profiles_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Profiles_EditMyProfile, CommonObjects.Banner_Profiles_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Profiles_MyNetwork, CommonObjects.Banner_Profiles_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Profiles_StatusUpdates, CommonObjects.Banner_Profiles_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Profiles_Directory, CommonObjects.Banner_Profiles_Base_List_Link);

		banner_fixed_anchors.put(CommonObjects.Banner_Communities_Base_List_Link, CommonObjects.Banner_Communities_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Communities_MyCommunities, CommonObjects.Banner_Communities_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Communities_PublicCommunities, CommonObjects.Banner_Communities_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Communities_ImFollowing, CommonObjects.Banner_Communities_Base_List_Link);

		banner_fixed_anchors.put(CommonObjects.Banner_Apps_Base_List_Link, CommonObjects.Banner_Apps_Base_List_Link);

		banner_fixed_anchors.put(CommonObjects.Banner_Apps_Activities, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Apps_ToDoList, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Apps_HighPriorityActivities, CommonObjects.Banner_Apps_Base_List_Link);

		banner_fixed_anchors.put(CommonObjects.Banner_Apps_Blogs, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Apps_LatestEntries, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Apps_PublicBlogsListing, CommonObjects.Banner_Apps_Base_List_Link);

		banner_fixed_anchors.put(CommonObjects.Banner_Apps_Bookmarks, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Apps_Popular, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Apps_PublicBookmarks, CommonObjects.Banner_Apps_Base_List_Link);

		banner_fixed_anchors.put(CommonObjects.Banner_Apps_Files, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Apps_SharedWithMe, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Apps_PinnedFolders, CommonObjects.Banner_Apps_Base_List_Link);

		banner_fixed_anchors.put(CommonObjects.Banner_Apps_Forums, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Apps_ImAnOwnerForums, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Apps_PublicForums, CommonObjects.Banner_Apps_Base_List_Link);

		banner_fixed_anchors.put(CommonObjects.Banner_Apps_Wikis, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Apps_ImAnOwnerWikis, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Apps_PublicWikis, CommonObjects.Banner_Apps_Base_List_Link);

		return banner_fixed_anchors;
	}

	public void LoadComponent(String ComponentName) {

		//Open the URL and the timeout is set to 90 seconds.
		try {
			sel.open(ComponentName);
			sel.waitForPageToLoad(CommonObjects.browserTimeout);
		} catch (Exception e) {
			sel.open(ComponentName);
			sel.waitForPageToLoad(CommonObjects.browserTimeout);
		}
		//Focus & maximize the main window
		sel.setTimeout(CommonObjects.browserTimeout);
		sel.windowFocus();
		sel.windowMaximize();

		//Check for the IE security link before login (need for some components)
		if (sel.isElementPresent("name=overridelink")) {
			sel.click("name=overridelink");
			sel.setTimeout("5000");
		}

		//Click the Login link
		if (sel.isElementPresent(CommonObjects.Login_Link)) {
			sel.click(CommonObjects.Login_Link);
			sleep(1500);
		}
		
		//Check for the IE 8 security link after login has being clicked
		try {
			if (sel.isElementPresent("name=overridelink")) {
				sel.click("name=overridelink");
				sel.setTimeout("3000");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			if (e.getMessage().contains("Permission denied")){
				sleep(1500);
				System.out.println(e.getMessage());				
			}
		}finally{
			sleep(1500);
		}
	}

	public void Login(String User_Name, String Password) {

		/*
		 * Code for checking that the login form is present - will loop for 120 seconds and then will fail if the login
		 * form has not appear before the timeout.
		 */

		for (int second = 0;; second++) {
			if (second >= 200) AssertJUnit.fail("Username field has not appeared after 120 seconds");
			try {
				if (sel.isElementPresent(CommonObjects.USERNAME_FIELD)) {
					sel.type(CommonObjects.USERNAME_FIELD, User_Name);
					sleep(500);
					sel.type(CommonObjects.Password_FIELD, Password);
					sleep(500);
					cautiousClick(CommonObjects.Login_Button);
					sleep(2500);
					break;
				}
			} catch (Exception e) {
			}

		}
		//Wait for text Loading... to appear before continuing
		try {
			int i = 0;
			while (i < 25) {
				if (sel.isTextPresent("Loading...")) {
					sleep(1000);
					i = i + 1;
				}
				break;
			}
		} catch (Exception e) {
			sleep(2000);
		}
		sleep(1500);
	}

	public void Logout() {

		//Method to log out of the Connections
		sel.click(CommonObjects.Logout_Link);
		try {
			int i = 0;
			while (i < 120) {
				if (sel.isTextPresent(CommonObjects.Logout_Link)) {
					sleep(1000);
					i = i + 1;
				}
				break;
			}
			sleep(1000);
		} catch (Exception e) {
			sleep(2000);
		}
	}

	public void Mobile_Login(String User_Name, String Password) {

		/*
		 * Code for checking that the login form is present - will loop for 120 seconds and then will fail if the login
		 * form has not appear before the timeout.
		 */

		for (int second = 0;; second++) {
			if (second >= 200) AssertJUnit.fail("Username field has not appeared after 120 seconds");
			try {
				if (sel.isElementPresent(CommonObjects.Mobile_USERNAME_FIELD)) {
					sel.type(CommonObjects.Mobile_USERNAME_FIELD, User_Name);
					Thread.sleep(500);
					sel.type(CommonObjects.Mobile_Password_FIELD, Password);
					Thread.sleep(500);
					sel.click(CommonObjects.Mobile_Login_Button);
					Thread.sleep(1000);
					break;
				}
			} catch (Exception e) {
			}

		}
		sel.waitForPageToLoad("30000");
		sleep(2000);
	}

	public void StartButton() throws Exception {

		sel.click(CommonObjects.StartButton);
		sel.waitForPageToLoad("10000");
	}

	public void typeNativeInCkEditor(String text) {

		// focus window
		sel.selectWindow("null");

		// focus in CK editor text entry space
		sel.focus("css=iframe:nth(0)");
		sel.selectFrame("index=0");
		sel.click(CommonObjects.CKEditor_Activation_Area1);
		sel.clickAt(CommonObjects.CKEditor_Activation_Area1, "1,1");
		sel.focus(CommonObjects.CKEditor_Activation_Area1);
		sel.clickAt(CommonObjects.CKEditor_Activation_Area2, "1,1");
		sel.click(CommonObjects.CKEditor_Activation_Area2);
		sel.focus(CommonObjects.CKEditor_Activation_Area2);

		// Type text using native keystrokes (typing will occur in focused window)
		typeNative(text);

		// Select main page frame
		sel.selectFrame("relative=top");
	}
}
