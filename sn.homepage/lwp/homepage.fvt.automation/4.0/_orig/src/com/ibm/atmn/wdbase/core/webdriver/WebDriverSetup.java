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

package com.ibm.atmn.wdbase.core.webdriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.ibm.atmn.wdbase.core.Environment;
import com.ibm.atmn.wdbase.core.TestConfiguration;
import com.ibm.atmn.wdbase.core.TestManager;
import com.ibm.atmn.wdbase.core.TestConfiguration.BrowserType;
import com.ibm.atmn.wdbase.core.config.RunConfiguration;
import com.opera.core.systems.OperaDriver;

/**
 * Handles creation and destruction of web driver instances.
 * 
 */
public class WebDriverSetup {

	protected static WebDriver createDriver(TestConfiguration testConfig) {

		WebDriver driver = null;
		Environment browserEnvironment = testConfig.getBrowserEnvironment();
		if (browserEnvironment.isLocal()) {

			if (testConfig.browserIs(BrowserType.IE)) {
				TestManager.logger.logInfo("Starting Internet Explorer");
				driver = new InternetExplorerDriver();
			}
			else if (testConfig.browserIs(BrowserType.FIREFOX)) {
				TestManager.logger.logInfo("Starting Firefox");
				driver = new FirefoxDriver();
			}
			else if (testConfig.browserIs(BrowserType.CHROME)) {
				initializeChromeDriver();
				TestManager.logger.logInfo("Starting Chrome");
				driver = new ChromeDriver();
			}
			//TODO:Safari
			else if (testConfig.browserIs(BrowserType.OPERA)) {
				TestManager.logger.logInfo("Starting Opera");
				DesiredCapabilities capabilities = DesiredCapabilities.opera();
				capabilities.setCapability("opera.port", -1);
				capabilities.setCapability("opera.profile", "");
				driver = new OperaDriver(capabilities);
			}
			else {
				throw new RuntimeException("WebDriver initialisation is not defined for this BrowserType: " + testConfig.getBrowser());
			}
		}
		else {
			//TODO: remote web driver
			if (testConfig.browserIs(BrowserType.IE)) {
				TestManager.logger.logInfo("Starting Remote Internet Explorer");
				DesiredCapabilities ie = DesiredCapabilities.internetExplorer();
				ie.setBrowserName("internet explorer");
				ie.setPlatform(Platform.WINDOWS);
				ie.setVersion(testConfig.getBrowserVersion());
				try {
					URL url = new URL(testConfig.getServerHost());
					//System.setProperty("webdriver.remote.server", "http://lcrft04.swg.usma.ibm.com:5555/wd/hub");
					driver = new RemoteWebDriver(url, ie);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (testConfig.browserIs(BrowserType.FIREFOX)) {
				TestManager.logger.logInfo("Starting Remote Firefox");
				DesiredCapabilities firefox = DesiredCapabilities.firefox();
				firefox.setBrowserName("firefox");
				firefox.setVersion(testConfig.getBrowserVersion());
				if(testConfig.getBrowserEnvironment().isWindows()){
					firefox.setPlatform(Platform.WINDOWS);
				}else if(testConfig.getBrowserEnvironment().isLinux()){
					firefox.setPlatform(Platform.LINUX);
				}else{
					firefox.setPlatform(Platform.MAC);
				}
				try {
					driver = new RemoteWebDriver(new URL(testConfig.getServerHost()), firefox);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				throw new RuntimeException("RemoteWebDriver initialisation is not defined for this BrowserType: " + testConfig.getBrowser());
			}
		}
		return driver;
	}

	/**
	 * Does all of the setup necessary to accommodate the chromedriver for any supported platform
	 */
	private static void initializeChromeDriver() {

		//File chromeDriverFile;
		String chromeDriverFileName;
		String chromeDriverFileShortName;
		String chromeDriverPath = null;
		InputStream in;
		boolean useBundledChromeDriver = false;

		Environment localEnv = RunConfiguration.getInstance().getLocalEnvironment();

		//TODO: if the chromeDriverPath property is set, attempt to use the local chromedriver
		useBundledChromeDriver = true;
		//		if (patsConfig.checkPropertyExists("chromeDriverPath")) {
		//			chromeDriverPath = localEnv.getProperty("chromeDriverPath");
		//			chromeDriverFile = new File(chromeDriverPath);
		//			if ((!chromeDriverFile.exists()) || (!chromeDriverFile.canExecute())) useBundledChromeDriver = true;
		//		}
		//		else {
		//			useBundledChromeDriver = true;
		//		}

		if (useBundledChromeDriver) {
			// determine which chromedriver binary we should be using
			if (localEnv.isWindows()) {
				chromeDriverFileName = "resources/chromedriver/chromedriver_win.exe";
				chromeDriverFileShortName = "chromedriver_win.exe";
			}
			else if (localEnv.isLinux()) {
				if (localEnv.is64Bit()) {
					chromeDriverFileName = "resources/chromedriver/chromedriver_linux_64";
					chromeDriverFileShortName = "chromedriver_linux_64";
				}
				else {
					chromeDriverFileName = "resources/chromedriver/chromedriver_linux_32";
					chromeDriverFileShortName = "chromedriver_linux_32";
				}
			}
			else {
				chromeDriverFileName = "resources/chromedriver/chromedriver_mac";
				chromeDriverFileShortName = "chromedriver_mac";
			}

			// if we have the files locally (i.e. not in a jar), just use the existing binary
			try {
				in = new FileInputStream(chromeDriverFileName);
				chromeDriverPath = new File(".").getAbsolutePath();
				chromeDriverPath = chromeDriverPath.replaceAll("\\.\\s+$", "").replace("\\", "/") + "/" + chromeDriverFileName;
				TestManager.logger.logDebug("Using bundled chrome driver [" + chromeDriverPath + "]");
			} catch (Exception e) {
				in = null;
			}

			// if we don't have the file locally, try to extract it from the jar
			if (in == null) {
				try {
					in = WebDriverSetup.class.getClassLoader().getResourceAsStream(chromeDriverFileName);
					chromeDriverPath = System.getProperty("java.io.tmpdir") + "/" + chromeDriverFileShortName;
					FileOutputStream tempChromeDriver = new FileOutputStream(chromeDriverPath);
					while (in.available() > 0) {
						tempChromeDriver.write(in.read());
					}
					tempChromeDriver.close();
					in.close();
					TestManager.logger.logDebug("Using bundled chrome driver extracted to system temp directory [" + chromeDriverPath + "]");
				} catch (Exception e) {
					in = null;
				}
			}
		}

		TestManager.logger.logDebug("Setting system property webdriver.chrome.driver to chromedriver path " + chromeDriverPath);
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
	}
}
