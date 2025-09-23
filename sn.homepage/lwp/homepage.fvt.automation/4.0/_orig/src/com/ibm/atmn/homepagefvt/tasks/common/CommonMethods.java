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

import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import junit.framework.*;
import org.junit.*;
import org.junit.Assert;


public abstract class CommonMethods extends TestCase {

	/*
	 * Created By: Conor Pelly Date: 07/06/2011 Purpose: Set of common methods that are used across all components
	 */

	public static Selenium selenium;

	/* static variables used to determine where the snapshot should be saved */
	private static boolean os_type_detected = false;
	private static boolean is_unix = false;

	//Generate a random number from date & time
	public static String genDateBasedRandVal() {
		//Create format class
		SimpleDateFormat tmformat = new SimpleDateFormat("MMDDHHmmss");

		return tmformat.format(new Date());
	}

	public static void sleep(int duration) {
		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			return;
		}
	}
	
	
	public CommonMethods() {
		super();
	}

	public CommonMethods(String name) {
		//super(name);
	}

	private void activateBannerForLink(String link) {

		Map<String, String> banner_fixed_anchors = getBannerLinksMap();
		String element = banner_fixed_anchors.get(link);

		mouseOverAndWait(element);

	}

	/** Simple Method for checking for errors on a page */
	public void CheckForErrorsOnPage() {
		//Assert.assertFalse("FAIL: Error message is displaying", selenium.isTextPresent("We have encountered a problem"));
		assertFalse("FAIL: Error message is displaying", selenium.isTextPresent("An error has occurred, contact your system Administrator"));
		assertFalse("FAIL: Error message is displaying", selenium.isTextPresent("Page not found"));
		assertFalse("FAIL: Error message is displaying", selenium.isTextPresent("Page Not Found"));
		//Assert.assertFalse("FAIL: Error message is displaying", selenium.isTextPresent("An error occurred while displaying content."));

	}

	public void clickBannerLink(String link) {

		activateBannerForLink(link);
		clickLink(link);

	}

	/** Click specified link */
	public void clickLink(String link, PageChangeActionHandler... handlers) {

		PageChangeActionHandler pageWaiter;
		//get Handler
		pageWaiter = getPageChangeActionHandler(handlers);

		//Click the link provided
		clickAndWait(link, pageWaiter);

	}

	public static PageChangeActionHandler getPageChangeActionHandler(PageChangeActionHandler... handlers) {

		PageChangeActionHandler pageWaiter;
		//get Handler
		if (handlers.length < 1 || handlers[0] instanceof PageChangeActionHandler == false) {
			pageWaiter = new PageChangeActionHandler();
		} else {
			pageWaiter = handlers[0];
		}

		return pageWaiter;
	}

	public void cautiousClick(String link) {

		PageChangeActionHandler pageWaiter = getPageChangeActionHandler();

		try {
			selenium.click(link);
		} catch (Exception e) {
			System.out.println("WARNING: Element click exception caught: " + e);
			if (pageWaiter.waitForNumberOfPageChanges(1)) {
				cautiousClick(link);
			} else {//no page change
				fail("Missing element or bad selector: " + link);
			}
		}
	}

	public boolean cautiousFocus(String selector) {
		return cautiousFocus(selector, 0);
	}

	public boolean cautiousFocus(String selector, int repetitions) {
		try {
			selenium.focus(selector);
			return true;
		} catch (Exception e) {
			System.out.println("WARNING: Element focus exception caught: " + e);
			if (repetitions < 20) {
				sleep(500);
				return cautiousFocus(selector, repetitions + 1);
			} else {
				return false;
			}
		}
	}

	public static String cautiousGetEval(String script) {
		return cautiousGetEval(script, 0);
	}

	public static String cautiousGetEval(String script, int repetitions) {
		try {
			return CommonMethods.selenium.getEval(script);
		} catch (Exception e) {
			System.out.println("WARNING: getEval exception caught: " + e);
			if (repetitions < 20) {
				sleep(500);
				return cautiousGetEval(script, repetitions + 1);
			} else {
				return "false";
			}
		}
	}

	public static boolean cautiousRunScript(String script) {
		return cautiousRunScript(script, 0);
	}

	public static boolean cautiousRunScript(String script, int repetitions) {
		try {
			CommonMethods.selenium.runScript(script);
			return true;
		} catch (Exception e) {
			System.out.println("WARNING: runScript exception caught: " + e);
			if (repetitions < 20) {
				sleep(500);
				return cautiousRunScript(script, repetitions + 1);
			} else {
				return false;
			}
		}
	}

	private void clickAndWait(String element, PageChangeActionHandler pageWaiter) {

		cautiousClick(element);
		boolean result = pageWaiter.andWait();
		if (result) {
			return;
		} else {
			fail("Click did not result in any detectable change to page contact.");
		}
	}

	public void mouseOverAndWait(String selector, PageChangeActionHandler... handlers) {

		//get Handler
		PageChangeActionHandler pageWaiter = getPageChangeActionHandler(handlers);

		selenium.mouseOver(selector);
		pageWaiter.andWait();
	}

	public void clickAtAndWait(String selector, String coordinates, PageChangeActionHandler... handlers) {

		//get Handler
		PageChangeActionHandler pageWaiter = getPageChangeActionHandler(handlers);

		selenium.clickAt(selector, coordinates);
		pageWaiter.andWait();
	}

	/** Click specified link */
	public void clickLinkBACKUP(String link) {
		selenium.click(link);
		//Check if the text before is appearing in the UI, if so then 
		//continue else loop and check again
		try {
			int i = 0;
			while (i < 60) {
				if (selenium.isTextPresent("Submit Feedback")) {
					sleep(500);
					i = i + 1;
				}
				break;
			}
			sleep(1500);
		} catch (Exception e) {
			sleep(4000);
		}
	}

	public void CreateCommunity(String CommName, String NameofComm, String CommTag, String TagForComm) throws Exception {
		clickLink(CommonObjects.StartButton);
		selenium.type(CommName, NameofComm);
		Thread.sleep(500);
		selenium.type(CommTag, TagForComm);
		Thread.sleep(500);
		selenium.click(CommonObjects.SaveButton);
		Thread.sleep(1000);
		//Wait for text Overview to appear before continuing
		try {
			int i = 0;
			while (i < 60) {
				if (selenium.isTextPresent("Community Actions")) {
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

		banner_fixed_anchors.put(CommonObjects.Banner_Homepage_Base_List_Link,
				CommonObjects.Banner_Homepage_Base_List_Link);

		banner_fixed_anchors.put(CommonObjects.Banner_Profiles_Base_List_Link,
				CommonObjects.Banner_Profiles_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Profiles_MyProfile, CommonObjects.Banner_Profiles_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Profiles_EditMyProfile,
				CommonObjects.Banner_Profiles_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Profiles_MyNetwork, CommonObjects.Banner_Profiles_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Profiles_StatusUpdates,
				CommonObjects.Banner_Profiles_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Profiles_Directory, CommonObjects.Banner_Profiles_Base_List_Link);

		banner_fixed_anchors.put(CommonObjects.Banner_Communities_Base_List_Link,
				CommonObjects.Banner_Communities_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Communities_MyCommunities,
				CommonObjects.Banner_Communities_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Communities_PublicCommunities,
				CommonObjects.Banner_Communities_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Communities_ImFollowing,
				CommonObjects.Banner_Communities_Base_List_Link);

		banner_fixed_anchors.put(CommonObjects.Banner_Apps_Base_List_Link, CommonObjects.Banner_Apps_Base_List_Link);

		banner_fixed_anchors.put(CommonObjects.Banner_Apps_Activities, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Apps_ToDoList, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Apps_HighPriorityActivities,
				CommonObjects.Banner_Apps_Base_List_Link);

		banner_fixed_anchors.put(CommonObjects.Banner_Apps_Blogs, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors.put(CommonObjects.Banner_Apps_LatestEntries, CommonObjects.Banner_Apps_Base_List_Link);
		banner_fixed_anchors
				.put(CommonObjects.Banner_Apps_PublicBlogsListing, CommonObjects.Banner_Apps_Base_List_Link);

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

	public void LoadComponent(String ComponentName) throws Exception {

		//Open the URL and the timeout is set to 90 seconds.
		try {
			selenium.open(ComponentName);
			selenium.waitForPageToLoad(CommonObjects.browserTimeout);
		} catch (Exception e) {
			selenium.open(ComponentName);
			selenium.waitForPageToLoad(CommonObjects.browserTimeout);
		}
		//Focus & maximize the main window
		selenium.setTimeout(CommonObjects.browserTimeout);
		selenium.windowFocus();
		selenium.windowMaximize();

		//Check for the IE security link before login (need for some components)
		if (selenium.isElementPresent("name=overridelink")) {
			selenium.click("name=overridelink");
			selenium.setTimeout("5000");
		}

		//Click the Login link
		if (selenium.isElementPresent(CommonObjects.Login_Link)) {
			selenium.click(CommonObjects.Login_Link);
			Thread.sleep(1500);
		}

		//Check for the IE 8 security link after login has being clicked
		if (selenium.isElementPresent("name=overridelink")) {
			selenium.click("name=overridelink");
			selenium.setTimeout("3000");
		}
	}

	public void Login(String User_Name, String Password) throws Exception {
		/*
		 * Code for checking that the login form is present - will loop for 120 seconds and then will fail if the login
		 * form has not appear before the timeout.
		 */

		for (int second = 0;; second++) {
			if (second >= 200)
				Assert.fail("Username field has not appeared after 120 seconds");
			try {
				if (selenium.isElementPresent(CommonObjects.USERNAME_FIELD)) {
					selenium.type(CommonObjects.USERNAME_FIELD, User_Name);
					Thread.sleep(500);
					selenium.type(CommonObjects.Password_FIELD, Password);
					Thread.sleep(500);
					clickLink(CommonObjects.Login_Button);
					Thread.sleep(1000);
					break;
				}
			} catch (Exception e) {
			}

		}
		//selenium.waitForPageToLoad("60000");
		Thread.sleep(2000);
	}

	public void Logout() throws Exception {
		//Method to log out of the Connections
		clickLink(CommonObjects.Logout_Link);
		//Thread.sleep(3000);
	}

	public void Mobile_Login(String User_Name, String Password) throws Exception {
		/*
		 * Code for checking that the login form is present - will loop for 120 seconds and then will fail if the login
		 * form has not appear before the timeout.
		 */

		for (int second = 0;; second++) {
			if (second >= 200)
				Assert.fail("Username field has not appeared after 120 seconds");
			try {
				if (selenium.isElementPresent(CommonObjects.Mobile_USERNAME_FIELD)) {
					selenium.type(CommonObjects.Mobile_USERNAME_FIELD, User_Name);
					Thread.sleep(500);
					selenium.type(CommonObjects.Mobile_Password_FIELD, Password);
					Thread.sleep(500);
					selenium.click(CommonObjects.Mobile_Login_Button);
					Thread.sleep(1000);
					break;
				}
			} catch (Exception e) {
			}

		}
		selenium.waitForPageToLoad("30000");
		Thread.sleep(2000);
	}

	@Override
	@Before
	public void setUp() throws InterruptedException {
		selenium = new DefaultSelenium(CommonObjects.TestServer, Integer.parseInt(CommonObjects.SeleniumPort),
				CommonObjects.TestBrowser, CommonObjects.TestURL);
		selenium.start();
	}

	public void StartButton() throws Exception {
		selenium.click(CommonObjects.StartButton);
		selenium.waitForPageToLoad("10000");
	}

	@Override
	@After
	public void tearDown() {
		//String BrowserName = selenium.getEval("navigator.userAgent");

		//System.out.println(BrowserName);
		if (CommonObjects.TestBrowser.contains("iexplore")) {
			selenium.captureScreenshot(generateSnapshotFilePath());
			selenium.deleteAllVisibleCookies();
			selenium.close();
			selenium.stop();
		} else if (CommonObjects.TestBrowser.contains("googlechrome")) {
			//The chrome browser windows were being left open so this code should now close the browser windows
			try {
				String ProcessToKill = "C:\\SeleniumServer\\KillChrome.exe";
				Runtime.getRuntime().exec(ProcessToKill);
			} catch (Exception e) {
			}
		} else {
			selenium.captureEntirePageScreenshot(generateSnapshotFilePath(), "");
			selenium.deleteAllVisibleCookies();
			selenium.close();
			selenium.stop();
		}
	}

	private String generateSnapshotFilePath() {
		if (!os_type_detected) {
			String os_name = System.getProperty("os.name");
			if (!os_name.contains("Windows")) {
				is_unix = true;
			}
		}
		File s_dir = is_unix ? new File("/tmp/selenium_screenshots") : new File("C:\\SeleniumServer\\Screenshots");
		if (!s_dir.exists()) {
			s_dir.mkdir();
		}
		return s_dir + File.separator + "BVT" + genDateBasedRandVal() + ".jpg";
	}

	public void typeNativeInCkEditor(String text) {

		// focus window
		selenium.selectWindow("null");

		// focus in CK editor text entry space
		selenium.focus("css=iframe:nth(0)");
		selenium.selectFrame("index=0");
		selenium.click(CommonObjects.CKEditor_Activation_Area1);
		selenium.clickAt(CommonObjects.CKEditor_Activation_Area1, "1,1");
		selenium.focus(CommonObjects.CKEditor_Activation_Area1);
		selenium.clickAt(CommonObjects.CKEditor_Activation_Area2, "1,1");
		selenium.click(CommonObjects.CKEditor_Activation_Area2);
		selenium.focus(CommonObjects.CKEditor_Activation_Area2);

		// Type text using native keystrokes (typing will occur in focused window)
		typeNative(text);

		// Select main page frame
		selenium.selectFrame("relative=top");

	}

	public void typeNative(String text) {
		FormInputHandler typist = getFormInputHandler();
		typist.type(null, text);
	}

	public FormInputHandler getFormInputHandler() {
		FormInputHandler formFiller = new FormInputHandler();
		return formFiller;
	}

	public void assertAllTextPresent(ArrayList<String> assertList) {
		for (String text : assertList) {
			Assert.assertTrue("FAIL: assertList text '" + text + "' not found", selenium.isTextPresent(text));
		}
	}

	public void assertAllTextNotPresent(ArrayList<String> assertList) {
		for (String text : assertList) {
			Assert.assertFalse("FAIL: Illegal text found: '" + text + "'", selenium.isTextPresent(text));
		}
	}

	public void assertAllTextPresentWithinElement(String locator, ArrayList<String> assertList) {
		String innerHTML = selenium.getEval("this.page().findElement(\"" + locator + "\").innerHTML;");

		for (String text : assertList) {
			Assert.assertTrue("FAIL: assertList text '" + text + "' not found", innerHTML.indexOf(text) >= 0);
		}
	}

	/*
	 * Get the URL reference to the file
	 */
	public URL getFileUrl(String filename) {
		String resource = CommonObjects.DATA_FOLDER + '/' + filename;
		URL url = CommonMethods.class.getResource(resource);
		return url;
	}

	/*
	 * Confirm the file exists
	 */
	public void checkFileExists(String value) throws IOException {
		File file = new File(value);
		if (file.exists() == true)
			return;
		throw new IOException("The file does not exist: " + value);
	}

	/*
	 * Creates a file reference and returns the path
	 */
	public String getPathToFile(String filename) throws IOException, URISyntaxException {
		if (filename == null)
			throw new IllegalArgumentException("filename must not be null");
		URL url = getFileUrl(filename);
		String value = url.getFile();
		value = URLDecoder.decode(value, "UTF-8");
		checkFileExists(value);
		return value;
	}

}
