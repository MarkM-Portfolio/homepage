/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test that is called by an ant script to kick off the selenium tests.
 * @author Robert Campion
 */
public class DOHTestController extends DOHTestRunner {
	
	public DOHTestController() {
		super();
	}
	
	/**
	 * Start the selenium session and run the DOH tests
	 * @param seleniumHost
	 * @param seleniumPort
	 * @param browser
	 * @param webSite
	 */
	private void runTest(String seleniumUrl, DesiredCapabilities capability, String runnerUrl, String JSTimeOut, String reportDir) throws Exception{
		WebDriver driver = new RemoteWebDriver(new URL(seleniumUrl), capability);
		try {
			runDOHSeleniumTests(driver, runnerUrl, reportDir, capability.getBrowserName(), Integer.parseInt(JSTimeOut));
		} finally {
			driver.quit();
		}
	}
	
	@Test(groups = {"firefox"}, description = "Run DOH tests in firefox")
	@Parameters({"seleniumUrl", "runnerUrl", "JSTimeOut", "firefoxVer", "reportDir"})
	public void testFireFox(String seleniumUrl, String runnerUrl, String JSTimeOut, String browserVersion, String reportDir) throws Throwable {
		if(!browserVersion.equals("none")) {
			DesiredCapabilities caps = DesiredCapabilities.firefox();
			caps.setVersion(browserVersion);
			runTest(seleniumUrl, caps, runnerUrl, JSTimeOut, reportDir);
		}
	}
	
	@Test(groups = {"iexplore"}, description = "Run DOH tests in IE8")
	@Parameters({"seleniumUrl", "runnerUrl", "JSTimeOut", "ieVer", "reportDir"})
	public void testIE(String seleniumUrl, String runnerUrl, String JSTimeOut, String browserVersion, String reportDir) throws Throwable {
		
		if(!browserVersion.equals("none")) {
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setVersion(browserVersion);
			runTest(seleniumUrl, caps, runnerUrl, JSTimeOut, reportDir);
		}
	}
	
	@Test(groups = {"chrome"}, description = "Run DOH tests in chrome")
	@Parameters({"seleniumUrl", "runnerUrl", "JSTimeOut", "chromeVer", "reportDir"})
	public void testChrome(String seleniumUrl, String runnerUrl, String JSTimeOut, String browserVersion, String reportDir) throws Throwable {
		if(!browserVersion.equals("none")) {
			DesiredCapabilities caps = DesiredCapabilities.chrome();
			caps.setVersion(browserVersion);
			runTest(seleniumUrl, caps, runnerUrl, JSTimeOut, reportDir);
		}
	}
}