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

import com.ibm.atmn.base.FileIOHandler;

import org.testng.ITestContext;

/**
 * For gathering and holding suite configuration. Should be refreshed in method setUp for config from test structure
 * perspective.
 * 
 * @author Ruairí Pidgeon
 * 
 */
public class SuiteConfigManager {

	/*
	 * Files identify purposed directories on browser machine. Folder names should be common to all suites but paths
	 * will vary depending on browserEnvironmentOS.
	 */
	public File browserEnvironmentBaseDir;
	public File uploadFilesDir;

	//These Files specifies suite-name dependent directories on local machine for saving screenshots to.
	public File screenshotsDir;
	public File suiteReportOutputDir;

	//Derived from browser start command
	public String browserVersion = null; //Consult grid config for versions in use. e.g. 3.6, 9, 10 etc.
	public String browserEnvironmentOS = null; //'browserEnvironment' is the platform of the browser host machine. e.g. Windows7, WindowsVista, WindowsXP, MacOSX, LinuxMint etc.
	public boolean browserIsIE = false;
	public boolean browserIsFirefox = false;
	public boolean browserIsGoogleChrome = false;

	//Derived from browserEnvironment
	public boolean browserEnvironmentIsUnix = false; //includes linux, may not consider all Unix based OS
	public boolean browserEnvironmentIsWindows = false;
	public boolean browserEnvironmentIsMac = false;

	//TODO: Does implementation of Selenium controller and suite config prevent test/method parallel?
	//These are used to instantiate DefaultSelenium. Everything except browser start command should be common to the run (in run config), but that is not essential/required, so they are kept together here since DefaultSelenium objects belong to a thread.
	public String browserURL = null;
	public String serverHost = null;
	public String serverPort = null;
	public String browserStartCommand = null;

	//Whether serverHost above is a grid hub or a stand-alone selenium server will affect browser start command.
	public boolean serverIsGridHub = true;

	public static final String BROWSER_ROOT_FOLDER_PROPERTY_NAME = "browser_root_folder";
	public static final String UPLOAD_FILES_FOLDER_PROPERTY_NAME = "upload_files_folder";

	private SuiteConfigManager() {

	}

	private static class ThreadSuiteConfigManager extends ThreadLocal<Object> {

		@Override
		public Object initialValue() {

			return new SuiteConfigManager();
		}
	}

	private static ThreadSuiteConfigManager instance = new ThreadSuiteConfigManager();

	/**
	 * Returns a thread local singleton instance.
	 * 
	 * @return SuiteConfigManager
	 */
	public static SuiteConfigManager getInstance() {

		return (SuiteConfigManager) instance.get();
	}

	public void loadConfigProperties(ITestContext context) {

		this.serverHost = context.getCurrentXmlTest().getParameter("server_host");
		this.serverPort = context.getCurrentXmlTest().getParameter("server_port");
		String[] browserStartCommand = context.getCurrentXmlTest().getParameter("browser_start_command").replaceAll(" ", "").split("_");
		this.browserURL = context.getCurrentXmlTest().getParameter("browser_url");

		//TODO: Validate parameters

		//Get browser environment from parameters
		//String browserName = context.getCurrentXmlTest().getParameter("browser_name").replaceAll(" ", "").replaceAll("_", "");
		//String browserVersion = context.getCurrentXmlTest().getParameter("browser_version").replaceAll(" ", "").replaceAll("_", "");
		//String browserEnvironmentOS = context.getCurrentXmlTest().getParameter("browser_environment_os").replaceAll(" ", "").replaceAll("_", "");
		String browserName = browserStartCommand[0];
		String browserVersion = browserStartCommand[1];
		String browserEnvironmentOS = browserStartCommand[2];

		//Get if server is grid hub
		this.serverIsGridHub = context.getCurrentXmlTest().getParameter("server_is_grid_hub").equalsIgnoreCase("true") ? true : false;

		//Set browser platform here.
		if (browserEnvironmentOS.toLowerCase().contains("win")) {
			this.browserEnvironmentIsWindows = true;
		}
		else if (browserEnvironmentOS.toLowerCase().contains("nix") || browserEnvironmentOS.toLowerCase().contains("nux")) {
			this.browserEnvironmentIsUnix = true;
		}
		else if (browserEnvironmentOS.toLowerCase().contains("mac")) {
			this.browserEnvironmentIsMac = true;
		}
		else {
			//TODO: do something
		}

		if (browserName.equalsIgnoreCase("Firefox")) {
			this.browserIsFirefox = true;
			if (!this.serverIsGridHub) {
				this.browserStartCommand = "*firefox";
			}
		}
		else if (browserName.equalsIgnoreCase("InternetExplorer") || browserName.equalsIgnoreCase("IE")) {
			this.browserIsIE = true;
			if (!this.serverIsGridHub) {
				this.browserStartCommand = "*iexplore";
			}
		}
		else if (browserName.equalsIgnoreCase("GoogleChrome")) {
			this.browserIsGoogleChrome = true;
			if (!this.serverIsGridHub) {
				this.browserStartCommand = "*googlechrome";
			}
		}
		else {
			//TODO: do something
		}

		//Set browserStartCommand for grid. This will overwrite browser start command set above.
		if (this.serverIsGridHub) {
			this.browserStartCommand = browserName + "_" + browserVersion + "_" + browserEnvironmentOS;
		}

		RunConfigManager rConfig = RunConfigManager.getInstance();
		FileIOHandler fileHandler = new FileIOHandler();
		this.browserEnvironmentBaseDir = fileHandler.buildFolderPath(this.browserEnvironmentIsUnix, this.browserEnvironmentIsWindows, this.browserEnvironmentIsMac,
				rConfig.fileManifest.getProperty(BROWSER_ROOT_FOLDER_PROPERTY_NAME));
		this.uploadFilesDir = fileHandler.buildFolderPath(this.browserEnvironmentIsUnix, this.browserEnvironmentIsWindows, this.browserEnvironmentIsMac,
				this.browserEnvironmentBaseDir.getName(), rConfig.fileManifest.getProperty(UPLOAD_FILES_FOLDER_PROPERTY_NAME));
		this.suiteReportOutputDir = new File(context.getOutputDirectory());
		this.screenshotsDir = new File(this.suiteReportOutputDir.getAbsolutePath() + File.separator + "screenshots");
	}
}
