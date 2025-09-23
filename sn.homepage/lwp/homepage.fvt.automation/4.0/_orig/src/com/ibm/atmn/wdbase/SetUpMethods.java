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

package com.ibm.atmn.wdbase;

import java.security.InvalidParameterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.ibm.atmn.wdbase.core.Executor;
import com.ibm.atmn.wdbase.core.TestConfiguration;
import com.ibm.atmn.wdbase.core.TestManager;
import com.ibm.atmn.wdbase.core.data.UserAllocation;

public class SetUpMethods {

	protected Executor driver;
	protected TestManager exec;
	protected TestConfiguration testConfig;
	protected UserAllocation userAllocator;

	public static String genDateBasedRandVal() {

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

	public enum CustomParameterNames {
		START_URL, TEST_IS_WEB_DRIVER("false"), ROOT_FOLDER_NAME("SeleniumServer"), UPLOAD_FILES_FOLDER_NAME("TestFiles");

		private final String defaultValue;

		CustomParameterNames() {

			this.defaultValue = null;
		}

		CustomParameterNames(String defaultValue) {

			this.defaultValue = defaultValue;
		}

		public String getDefaultValue() {

			return this.defaultValue;
		}
	}

	/*
	 * Files identify purposed directories on browser machine. Folder names should be common to all suites but paths
	 * will depend on browserEnvironmentOS.
	 */
	private String browserEnvironmentBaseDir;
	private String uploadFilesDir;

	protected Map<CustomParameterNames, String> customParameters = new EnumMap<CustomParameterNames, String>(CustomParameterNames.class);

	public SetUpMethods() {

	}

	@BeforeTest
	public void beforeTest(ITestContext context) {


	}

	@BeforeMethod
	public void beforeMethod(ITestContext context) {
		this.exec = new TestManager(context);
		this.testConfig = exec.getTestConfig();
		this.driver = exec.getExecutor();
		
		this.userAllocator = UserAllocation.getInstance();
		
		gatherCustomParameters(context);

		//Absolute paths to folders on browser machine
		setBrowserEnvironmentBaseDir(testConfig.getBrowserEnvironment().constructAbsolutePathToDirectoryFromRoot(customParameters.get(CustomParameterNames.ROOT_FOLDER_NAME)));
		setUploadFilesDir(testConfig.getBrowserEnvironment().constructAbsolutePathToDirectoryFromRoot(getBrowserEnvironmentBaseDir(),
				customParameters.get(CustomParameterNames.UPLOAD_FILES_FOLDER_NAME)));
	}

	@AfterMethod
	public void afterMethod() {

		driver.quit();
	}

	@AfterClass
	public void classTearDown() {

		UserAllocation allocator = UserAllocation.getInstance();
		allocator.clearAllUsers();
	}

	private void gatherCustomParameters(ITestContext context) {

		String param = null;
		for (CustomParameterNames paramName : CustomParameterNames.values()) {
			param = context.getCurrentXmlTest().getParameter(paramName.toString().toLowerCase());
			if (param != null) {
				this.customParameters.put(paramName, param);
			}
			else if (paramName.getDefaultValue() != null) {
				this.customParameters.put(paramName, paramName.getDefaultValue());
			}
			else {
				throw new InvalidParameterException("Required parameter not found in testng .xml config file: " + paramName.toString().toLowerCase());
			}
		}
	}

	protected void setUploadFilesDir(String uploadFilesDir) {

		this.uploadFilesDir = uploadFilesDir;
	}

	protected String getUploadFilesDir() {

		return uploadFilesDir;
	}

	protected String getBrowserEnvironmentBaseDir() {

		return browserEnvironmentBaseDir;
	}

	protected void setBrowserEnvironmentBaseDir(String browserEnvironmentBaseDir) {

		this.browserEnvironmentBaseDir = browserEnvironmentBaseDir;
	}

}
