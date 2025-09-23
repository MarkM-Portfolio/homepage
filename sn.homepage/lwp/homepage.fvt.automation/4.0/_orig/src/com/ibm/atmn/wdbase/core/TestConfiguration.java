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

package com.ibm.atmn.wdbase.core;

import java.io.File;
import java.security.InvalidParameterException;
import java.util.EnumMap;
import java.util.Map;

import org.testng.ITestContext;

/**
 * For gathering and holding test and suite configuration. Should be refreshed at least at start of every test to ensure
 * config from testng test structure perspective.
 */
public class TestConfiguration {

	//The directory for saving this test's screenshots to.
	private File screenshotsDir;

	private Environment browserEnvironment;

	public static enum BrowserType {
		IE, FIREFOX, CHROME, SAFARI, OPERA;

		private String version;

		BrowserType() {

			this.version = null;
		}

		BrowserType(String version) {

			this.version = version;
		}

		public void setVersion(String version) {

			this.version = version;
		}

		public String getVersion() {

			return this.version;
		}
	}

	private BrowserType browser;

	private enum ParameterNames {
		SERVER_HOST, SERVER_PORT(""), BROWSER_START_COMMAND, BROWSER_START_COMMAND_DELIMITER("_"), BROWSER_URL, SERVER_IS_GRID_HUB, USER_DATA_FILE_NAME("users.csv"), SPECIAL_USERS_DELIMITER(
				";"), ADMIN_USERS(""), GLOBAL_MOD_USERS(""), USERS_LOGIN_PREFERENCE("uid"), TEST_LOG_LEVEL("info");

		private final String defaultValue;

		ParameterNames() {

			this.defaultValue = null;
		}

		ParameterNames(String defaultValue) {

			this.defaultValue = defaultValue;
		}

		public String getDefaultValue() {

			return this.defaultValue;
		}
	}

	private Map<ParameterNames, String> watsConfigParameters = new EnumMap<ParameterNames, String>(ParameterNames.class);

	private TestConfiguration() {

	}

	private static class ThreadTestConfiguration extends ThreadLocal<Object> {

		@Override
		public Object initialValue() {

			return new TestConfiguration();
		}
	}

	private static ThreadTestConfiguration instance = new ThreadTestConfiguration();

	//TODO: This does not necessarily need to be thread local to be thread-safe. This must be tested with testng behaviour.
	/**
	 * Returns a thread local singleton instance.
	 * 
	 * @return TestConfiguration
	 */
	public static TestConfiguration getInstance() {

		return (TestConfiguration) instance.get();
	}

	//TODO: This can be added to constructor once thread-safe implementation is improved to ensure it is alwasy run.
	public void loadConfigProperties(ITestContext context) {

		//Cycles through expected Parameters and adds values from testng.xml (or default) to parameter map.	
		for (ParameterNames paramName : ParameterNames.values()) {
			String param = null;
			param = context.getCurrentXmlTest().getParameter(paramName.toString().toLowerCase());
			if (param != null) {
				this.watsConfigParameters.put(paramName, param);
			}
			else if (paramName.getDefaultValue() != null) {
				this.watsConfigParameters.put(paramName, paramName.getDefaultValue());
			}
			else {
				throw new InvalidParameterException("Required parameter not found in testng .xml config file: " + paramName.toString().toLowerCase());
			}
		}

		//TODO: Validate parameters appropriately
		String browserStartCommandDelimiter = watsConfigParameters.get(ParameterNames.BROWSER_START_COMMAND_DELIMITER);
		String[] browserStartCommand = watsConfigParameters.get(ParameterNames.BROWSER_START_COMMAND).split(browserStartCommandDelimiter);
		String browserName = browserStartCommand[0];
		String browserVersion = browserStartCommand[1];
		String browserEnvironmentOS = browserStartCommand[2];

		//Create browser environment.
		if (useLocalBrowser()) {
			setBrowserEnvironment(new Environment(true, browserEnvironmentOS));
		}
		else {
			setBrowserEnvironment(new Environment(browserEnvironmentOS));
		}

		//TODO: browser start command is a relic from selenium rc and grid1. This must be tool independent? Processing should maybe be moved to [Tool}Setup class.
		if (browserName.equalsIgnoreCase("Firefox") || browserName.equalsIgnoreCase("FF")) {
			this.browser = BrowserType.FIREFOX;
		}
		else if (browserName.equalsIgnoreCase("InternetExplorer") || browserName.equalsIgnoreCase("IE")) {
			this.browser = BrowserType.IE;
		}
		else if (browserName.equalsIgnoreCase("GoogleChrome") || browserName.equalsIgnoreCase("GC")) {
			this.browser = BrowserType.CHROME;
		}
		else if (browserName.equalsIgnoreCase("Opera")) {
			this.browser = BrowserType.OPERA;
		}
		else if (browserName.equalsIgnoreCase("Safari")) {
			this.browser = BrowserType.SAFARI;
		}
		else {
			throw new InvalidParameterException("Browser could not be identified from: " + browserName);
		}
		browser.setVersion(browserVersion);

		//File objects for IO to local machine
		this.setScreenshotsDir(new File(context.getOutputDirectory() + File.separator + "screenshots"));

		printProps();
	}

	/**
	 * Prints out all of the environment properties
	 */
	public void printProps() {

		LoggingHandler logger = TestManager.logger;
		logger.logInfo("*************** TEST CONFIGURATION ***************");

		// TODO: sort the properties
		for (Map.Entry<ParameterNames, String> param : watsConfigParameters.entrySet()) {
			logger.logInfo(String.format("%1$30s", param.getKey().toString()) + " = " + param.getValue());
		}
		logger.logInfo("*******************************************");
	}

	public Environment getBrowserEnvironment() {

		return this.browserEnvironment;
	}

	public void setBrowserEnvironment(Environment env) {

		this.browserEnvironment = env;
	}
	
	public String getBrowserVersion(){
		String[] browserStartCommand = watsConfigParameters.get(ParameterNames.BROWSER_START_COMMAND).split(watsConfigParameters.get(ParameterNames.BROWSER_START_COMMAND_DELIMITER));
		return browserStartCommand[1];
	}

	public String getServerHost(){
		return watsConfigParameters.get(ParameterNames.SERVER_HOST);
	}
	public String getUsersLoginPreference() {

		return this.watsConfigParameters.get(ParameterNames.USERS_LOGIN_PREFERENCE);
	}

	public boolean browserIs(BrowserType browser) {

		return this.browser == browser ? true : false;
	}

	public BrowserType getBrowser() {

		return this.browser;
	}

	public boolean serverIsGridHub() { //Get if server is grid hub or not. Affects browser start command.

		return Boolean.parseBoolean(this.watsConfigParameters.get(ParameterNames.SERVER_IS_GRID_HUB));
	}

	public String getUserDataFileName() {

		return this.watsConfigParameters.get(ParameterNames.USER_DATA_FILE_NAME);
	}

	public String[] getAdminUsers() {

		return this.watsConfigParameters.get(ParameterNames.ADMIN_USERS).split(this.watsConfigParameters.get(ParameterNames.SPECIAL_USERS_DELIMITER));
	}

	public String[] getGlobalModUsers() {

		return this.watsConfigParameters.get(ParameterNames.GLOBAL_MOD_USERS).split(this.watsConfigParameters.get(ParameterNames.SPECIAL_USERS_DELIMITER));
	}

	public String getBrowserURL() {

		return this.watsConfigParameters.get(ParameterNames.BROWSER_URL);
	}

	private boolean useLocalBrowser() {

		String target = watsConfigParameters.get(ParameterNames.SERVER_HOST);
		if ((target.equalsIgnoreCase("localhost") || target.equalsIgnoreCase("127.0.0.1")) && !serverIsGridHub()) {
			return true;
		}
		else return false;
	}

	public void setScreenshotsDir(File screenshotsDir) {

		this.screenshotsDir = screenshotsDir;
	}

	public File getScreenshotsDir() {

		return screenshotsDir;
	}
}
