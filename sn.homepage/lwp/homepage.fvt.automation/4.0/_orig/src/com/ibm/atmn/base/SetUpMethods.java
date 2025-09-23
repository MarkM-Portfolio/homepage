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

package com.ibm.atmn.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.Reporter;

import sun.misc.BASE64Decoder;

public class SetUpMethods {

	/** Selenium proxy object */
	protected SeleniumController sel;
	protected SuiteConfigManager sConfig;
	protected RunConfigManager rConfig = RunConfigManager.getInstance();

	public SetUpMethods() {

	}

	protected void masterBeforeSuiteSetup(ITestContext context) {

		// loadConfigProperties(context);
	}

	// Generate a random number from date & time
	public static String genDateBasedRandVal() {

		// Create format class
		SimpleDateFormat tmformat = new SimpleDateFormat("DDDHHmmss");

		return Thread.currentThread().getId() + tmformat.format(new Date());
	}

	public static String stamp(String start) {

		return start + genDateBasedRandVal();
	}

	public static void sleep(int duration) {

		try {
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			return;
		}
	}

	public String getBrowserEnvironmentAbsoluteFilePath(File dir, String fileName) {

		return new FileIOHandler().getAbsoluteFilePath(sConfig.browserEnvironmentIsUnix, sConfig.browserEnvironmentIsWindows, sConfig.browserEnvironmentIsMac, dir, fileName);
	}

	//called by @beforeMethod (CommonMethods)
	public void masterSetUp(ITestContext context) {

		sConfig = SuiteConfigManager.getInstance();
		sel = SeleniumController.getInstance();
		try {
			sConfig.loadConfigProperties(context);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sel.newSelenium(sConfig.serverHost, Integer.parseInt(sConfig.serverPort), sConfig.browserStartCommand, sConfig.browserURL);
		sel.start();
	}

	//called by @afterMethod (CommonMethods)
	public void masterTearDown() {

		sel.deleteAllVisibleCookies();
		sel.close();
		sel.stop();
		if (sConfig.browserIsGoogleChrome) {
			// The chrome browser windows were being left open so this code should now close the browser windows
			try {
				String ProcessToKill = "C:\\SeleniumServer\\KillChrome.exe";
				Runtime.getRuntime().exec(ProcessToKill);
			} catch (Exception e) {
			}
		}
	}

	public static void takeScreenshot(String methodName) {

		String filepath;
		String screenshot;
		SeleniumController sel = SeleniumController.getInstance();
		SuiteConfigManager sConfig = SuiteConfigManager.getInstance();
		RunConfigManager rConfig = RunConfigManager.getInstance();

		if (sConfig.browserIsFirefox) {
			screenshot = sel.captureEntirePageScreenshotToString("background=#CCFFDD");
		}
		else {
			screenshot = sel.captureScreenshotToString();
		}

		//decode
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] decodedScreenshot = null;
		try {
			decodedScreenshot = decoder.decodeBuffer(screenshot);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//determine filepath
		FileIOHandler fileHandler = new FileIOHandler();

		String fileName = Thread.currentThread().getId() + "." + SetUpMethods.genDateBasedRandVal() + ".png";
		filepath = fileHandler.getAbsoluteFilePath(rConfig.localIsUnix, rConfig.localIsWindows, rConfig.localIsMac, sConfig.screenshotsDir, fileName);

		//write file
		fileHandler.writeRawDataToFile(filepath, decodedScreenshot);

		//Get relative URL for file
		URL imageURL = null;
		try {
			imageURL = sConfig.screenshotsDir.toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		//get address from browser
		String location = "";
		try {
			location = sel.getLocation();
		} catch (Exception e) {
			e.printStackTrace();
		}

		//Report
		String linkToImage = "<a href=" + imageURL + "/" + fileName + ">Screenshot for method: " + methodName + "</a>";
		String linkToAUT = "<a href=" + location + ">AUT Address</a>";
		Reporter.log(linkToImage + " @ " + linkToAUT);
	}
}
