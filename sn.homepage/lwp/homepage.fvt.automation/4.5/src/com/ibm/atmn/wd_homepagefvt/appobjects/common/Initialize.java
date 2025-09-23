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

package com.ibm.atmn.wd_homepagefvt.appobjects.common;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.Selenium;

import junit.framework.TestCase;
import org.junit.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Initialize extends TestCase{

	protected Selenium selenium;
		
	public Initialize() {
		super();
	}

	public Initialize(String name) {
		//super(name);
	}
	@Before
	public void setUp() throws InterruptedException {
		selenium = new DefaultSelenium(CommonObjects.TestServer, Integer.parseInt(CommonObjects.SeleniumPort), CommonObjects.TestBrowser, CommonObjects.TestURL);
		selenium.start();
		
        //Open the URL and the timeout is set to 90 seconds.
        /*try 
        {
            selenium.open(CommonObjects.ComponentName1);
            selenium.waitForPageToLoad(CommonObjects.browserTimeout);
        } catch (Exception e) 
        {
        	selenium.open(CommonObjects.ComponentName1);
        	selenium.waitForPageToLoad(CommonObjects.browserTimeout);
        }
		//Focus & maximize the main window
		selenium.setTimeout(CommonObjects.browserTimeout);
		selenium.windowFocus();
		selenium.windowMaximize();

		//Click the Login link
		selenium.click(CommonObjects.Login_Link);
		Thread.sleep(1500);
		
		if (selenium.isElementPresent("name=overridelink")){
				selenium.click("name=overridelink");
				selenium.setTimeout("3000");
		}*/
	}
		
	public void Login(String User_Name, String Password) throws Exception {
		/*Code for checking that the login form is present - will loop for 30 seconds and then 
		will fail if the login form has not appear before the timeout.*/
		
		for (int second = 0;; second++) {
			if (second >= 120) Assert.fail("Username field has not appeared after 60 seconds");
				try { 
					if (selenium.isElementPresent(CommonObjects.USERNAME_FIELD)){					
						selenium.type(CommonObjects.USERNAME_FIELD, User_Name);
						Thread.sleep(500);
						selenium.type(CommonObjects.Password_FIELD, Password);
						Thread.sleep(500);
						selenium.click(CommonObjects.Login_Button);
						Thread.sleep(Long.parseLong(CommonObjects.browserSleep));
						break;
					}
				} catch (Exception e) {}
				
		}
		
		/*if (selenium.isTextPresent("Try Again")){
			selenium.open("wikis/home?lang=en_GB");
		}*/
	}
	
	public void Logout() throws Exception {
		//Method to log out of the Wiki
		selenium.click(CommonObjects.Logout_Link);
		Thread.sleep(3000);
	}
	
	//Generate a random number from date & time
	public static String genDateBasedRandVal(){
    	//Create format class
    	SimpleDateFormat tmformat = new SimpleDateFormat("MMDDHHmmss");

    	return tmformat.format(new Date());
    }
	
	@After
	public void tearDown() {
		String BrowserName = selenium.getEval("navigator.appName");
		
		if (BrowserName.equals("Microsoft Internet Explorer"))
		{
			selenium.captureScreenshot("c:\\temp\\SeleniumScreenshots\\WikisApp"+genDateBasedRandVal()+".jpg");
			selenium.deleteAllVisibleCookies();
			selenium.close();
			selenium.stop();
		}
		else
		{
			selenium.captureEntirePageScreenshot("c:\\temp\\SeleniumScreenshots\\WikisApp"+genDateBasedRandVal()+".jpg","");
			selenium.deleteAllVisibleCookies();
			selenium.close();
			selenium.stop();
		}
	}
	
}
