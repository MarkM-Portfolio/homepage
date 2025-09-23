/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2013                                          */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wd_homepagefvt.tasks.common;

import java.awt.Toolkit;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.AssertJUnit;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Function;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.waffle.log.LogManager;
import com.ibm.atmn.wd_homepagefvt.config.SetUpMethods;
import com.ibm.atmn.waffle.core.Element;
import com.ibm.atmn.waffle.core.RCLocationExecutor;
import com.ibm.atmn.waffle.core.TestConfiguration.BrowserType;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.files.FilesObjects;

public abstract class CommonMethods extends SetUpMethods {
	
	private static Logger log = LoggerFactory.getLogger(LogManager.class); 

	/*
	 * Created By: Conor Pelly Date: 07/06/2011 Purpose: Set of common methods that are used across all components
	 */

	protected void clickLink(String selector) {

		driver.getFirstElement(selector).click();
	}

	protected void LoadComponent(String ComponentName) {

		driver.load(testConfig.getBrowserURL() + ComponentName);
		sleep(3000);
		
		// Making sure the browser is in Full Screen mode (IE has issues if not in full screen mode)
		driver.maximiseWindow();
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		if (testConfig.browserIs(BrowserType.FIREFOX)) {
			//driver.typeNative(Keys.F11);
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Double width = toolkit.getScreenSize().getWidth();
			Double height = toolkit.getScreenSize().getHeight();
			Dimension dim = new Dimension(width.intValue(), height.intValue());
			wd.manage().window().setSize(dim);
			wd.manage().window().setPosition(new Point(0, 0));
		}
		//driver.getSingleElement(CommonObjects.Login_Link).doubleClick();
		if (testConfig.browserIs(BrowserType.IE)) {
			sleep(2000);
			//WebDriver wd = (WebDriver) driver.getBackingObject();
			wd.navigate().to("javascript:document.getElementById('overridelink').click()");
		}

	}

	protected void Login(String userName, String password) {

		smartSleep(CommonObjects.USERNAME_FIELD);
		smartSleep(CommonObjects.Password_FIELD);
		driver.getSingleElement(CommonObjects.USERNAME_FIELD).type(userName);
		driver.getSingleElement(CommonObjects.Password_FIELD).type(password);
		driver.getSingleElement(CommonObjects.Login_Button).click();
		sleep(4000);
		if (driver.getPageSource().contains("Loading")){
			sleep(5000);
		}

	}

	protected void Logout(){

		WebDriver wd = (WebDriver) driver.getBackingObject();

		//JavascriptExecutor js = (JavascriptExecutor) wd;

		//js.executeScript("var as = dijit.byId('headerUserName');var lotusUser = dojo.query('.lotusUser');dojo.addClass(lotusUser, 'lotusHover');",(Object) null);

		//Method to log out of Connections
		//driver.getFirstElement(CommonObjects.UserDropdown).click();
		//driver.getFirstElement(CommonObjects.Logout_Link).click();
		sleep(5000);

		wd.navigate().to("javascript:document.getElementById('logoutLink').click()");
		//sleep(2000);
		//WebDriver wd = (WebDriver) driver.getBackingObject();

		//Actions builder = new Actions(wd);

		//builder.moveToElement((WebElement) driver.getFirstElement(CommonObjects.UserDropdown).getBackingObject()).click().moveToElement((WebElement) driver.getFirstElement(CommonObjects.Logout_Link).getBackingObject()).click().build().perform();

	}
	protected void LogoutAndClose(){

		WebDriver wd = (WebDriver) driver.getBackingObject();

		//JavascriptExecutor js = (JavascriptExecutor) wd;

		//js.executeScript("var as = dijit.byId('headerUserName');var lotusUser = dojo.query('.lotusUser');dojo.addClass(lotusUser, 'lotusHover');",(Object) null);

		//Method to log out of Connections
		//driver.getFirstElement(CommonObjects.UserDropdown).click();
		//driver.getFirstElement(CommonObjects.Logout_Link).click();

		sleep(5000); // 

		wd.navigate().to("javascript:document.getElementById('logoutLink').click()");

		driver.close();
		//sleep(2000);
		//WebDriver wd = (WebDriver) driver.getBackingObject();

		//Actions builder = new Actions(wd);

		//builder.moveToElement((WebElement) driver.getFirstElement(CommonObjects.UserDropdown).getBackingObject()).click().moveToElement((WebElement) driver.getFirstElement(CommonObjects.Logout_Link).getBackingObject()).click().build().perform();

	}

	protected void typeNativeInCkEditor(String text) {

		sleep(15000);
		typeNativeInCkEditor(text,"0");
	}

	protected void typeNativeInCkEditor(String text,String Index) {

		WebDriver wd = (WebDriver) driver.getBackingObject();
		//Actions builder = new Actions(wd);
		//WebElement wdElement =  (WebElement) driver.getSingleElement("css=iframe").getBackingObject();
		//builder.doubleClick(wdElement).build().perform();
		
		Element element = driver.getFirstElement("css=iframe:nth("+Index+")");
		element.doubleClick();
		sleep(1000);

		Actions writer = new Actions(wd);
		writer.sendKeys(text).build().perform();
		sleep(1000);

		// Select main page frame in case context was switched to CKEditor iframe
		wd.switchTo().defaultContent();
	}

	protected boolean isTextPresent(String text) {

		return driver.isTextPresent(text);
	}

	private void activateBannerForLink(String link) {

		Map<String, String> banner_fixed_anchors = getBannerLinksMap();
		String element = banner_fixed_anchors.get(link);

		driver.getSingleElement(element).hover();
	}

	/** Simple Method for checking for errors on a page */
	public void CheckForErrorsOnPage() {

		//Assert.assertFalse("FAIL: Error message is displaying", sel.isTextPresent("We have encountered a problem"));
		AssertJUnit.assertFalse("FAIL: Error message is displaying", driver.getPageSource().contains("An error has occurred, contact your system Administrator"));
		AssertJUnit.assertFalse("FAIL: Error message is displaying", driver.getPageSource().contains("Page not found"));
		AssertJUnit.assertFalse("FAIL: Error message is displaying", driver.getPageSource().contains("Page Not Found"));
		//Assert.assertFalse("FAIL: Error message is displaying", sel.isTextPresent("An error occurred while displaying content."));
	}

	public void clickBannerLink(String link) {

		activateBannerForLink(link);
		clickLink(link);
	}

	/*
	 * public void CreateCommunity(String CommName, String NameofComm, String CommTag, String TagForComm) {
	 * 
	 * clickLink(CommonObjects.StartButton); sel.type(CommName, NameofComm); sleep(500); sel.type(CommTag, TagForComm);
	 * sleep(500); sel.click(CommonObjects.SaveButton); sleep(1000); //Wait for text Overview to appear before
	 * continuing try { int i = 0; while (i < 60) { if (sel.isTextPresent("Community Actions")) { sleep(1000); i = i +
	 * 1; } break; } sleep(1000); } catch (Exception e) { sleep(2000); } }
	 */

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



	public void Login(User testUser1) {

		/*
		 * Code for checking that the login form is present - will loop for 120 seconds and then will fail if the login
		 * form has not appear before the timeout.
		 */

		String userName = "Username Not Allocated";

		String userPref = getUsersLoginPreference();

		// Get the preference here
		if (userPref.toString().equalsIgnoreCase("uid")) {
			System.out.println("UID chosen as login method.");
			userName = testUser1.getUid();
		}
		else if (userPref.toString().equalsIgnoreCase("display")) {
			System.out.println("DisplayName chosen as login method.");
			userName = testUser1.getDisplayName();
		}
		else if (userPref.toString().equalsIgnoreCase("email")) {
			System.out.println("EMAIL chosen as login method.");
			userName = testUser1.getEmail();
		}
		else {
			throw new InvalidParameterException("This is not a valid users_login_preference: " + userPref);
		}

		Login(userName, testUser1.getPassword());

	}



	public void assertAllTextPresent(ArrayList<String> assertList) {

		for (String text : assertList) {
			AssertJUnit.assertTrue("FAIL: assertList text '" + text + "' not found", driver.isTextPresent(text));
		}
	}

	public void assertAllTextNotPresent(ArrayList<String> assertList) {

		for (String text : assertList) {
			AssertJUnit.assertFalse("FAIL: Illegal text found: '" + text + "'", driver.isTextPresent(text));
		}
	}

	public void assertAllTextPresentWithinElement(String locator, ArrayList<String> assertList) {

		String innerHTML = driver.getSingleElement(locator).getText();

		for (String text : assertList) {
			AssertJUnit.assertTrue("FAIL: assertList text '" + text + "' not found", innerHTML.indexOf(text) >= 0);
		}
	}

	public Element getActiveElement(String selector) {
		Element visibleElement = null;
		List<Element> elements = driver.getElements(selector);
		for (Element e : elements) {
			if (e.isVisible()) {
				visibleElement = e;
				break;
			}
		}
		return visibleElement;
	}

	public WebElement getWhenVisible(By locator, int timeout) {
		WebDriver wd = (WebDriver) driver.getBackingObject();
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(wd, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	public void clickWhenReady(By locator, int timeout) {
		WebDriver wd = (WebDriver) driver.getBackingObject();
		WebDriverWait wait = new WebDriverWait(wd, timeout);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}

	
	
//	public void smartSleep(String selector){
		/*
		 * The method is to replace sleep() in the code
		 * This should be used if there is a need to wait a bit
		 * longer for an object to appear.
		 
		int zzz;
		for(zzz=0; zzz < 60; zzz++){
			if (driver.isElementPresent(selector)){
				//if the element is present then break the loop
				break;
			}
			sleep(500);
			zzz++;
			break;
		}
		Reporter.log("INFO: Smart Sleep waited for: " +selector);
	} */

//	public void smartSleepText(String selector){
		/*
		 * The method is too replace sleep() in the code
		 * This should be used if there is a need to wait a bit
		 * longer for an object to appear.
		 
		int zzz;
		for(zzz=0; zzz < 60; zzz++){
			if (driver.isTextPresent(selector)){
				//if the element is present then break the loop
				break;	
			}
			sleep(500);
			zzz++;
			break;
		}
		Reporter.log("INFO: Smart Sleep waited for: " +selector);
	}
	*/
	
	public void communitySmartSleep(String component, String event){
		
	// Function to deal with delay in community events appearing under owner/member
	
	// If event is not present, wait for 1 minute and refresh the page
		if(!driver.isElementPresent(event)){
			
			for(int x = 0; x <10; x++){
				sleep(60000);
				clickLink(component);
				
				if(driver.isElementPresent(event)){
					clickLink(event);
					break;
				}				
			}
		}
	
	// If the link is present click it
		else{
			clickLink(event);
		}
	}
	
	public boolean smartSleep(final String locator){
		log.debug("Entering fluentWaitPresent. Timeout is 30 seconds. Polling every 1 second.");
		Wait<RCLocationExecutor> wait = new FluentWait<RCLocationExecutor>(driver)
			.withTimeout(30, TimeUnit.SECONDS)
			.pollingEvery(1, TimeUnit.SECONDS)
			.ignoring(ElementNotFoundException.class);

        boolean foo = wait.until(new Function<RCLocationExecutor, Boolean>() 
        {
                public Boolean apply(RCLocationExecutor driver)
                {
                	return CommonMethods.this.driver.isElementPresent(locator);
                }
        });
        return foo;              
    }; 
    
    public boolean smartSleepText(final String text){
		log.debug("Entering fluentWaitTextPresent. Timeout is 30 seconds. Polling every 1 second.");
		Wait<RCLocationExecutor> wait = new FluentWait<RCLocationExecutor>(this.driver)
			.withTimeout(30, TimeUnit.SECONDS)
			.pollingEvery(1, TimeUnit.SECONDS)
			.ignoring(ElementNotFoundException.class);

        boolean foo = wait.until(new Function<RCLocationExecutor, Boolean>() 
        {
                public Boolean apply(RCLocationExecutor driver)
                {
                	return driver.isTextPresent(text);
                }
        });

        return foo;              
    }; 

}
