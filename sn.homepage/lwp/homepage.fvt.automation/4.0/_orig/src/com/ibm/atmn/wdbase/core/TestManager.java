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

import org.testng.ITestContext;

import com.ibm.atmn.wdbase.core.config.RunConfiguration;
import com.ibm.atmn.wdbase.core.log.BaseLogger;
import com.ibm.atmn.wdbase.core.webdriver.WebDriverExecutor;

public class TestManager {

	private Executor driver;
	private RunConfiguration execConfig = RunConfiguration.getInstance();
	private TestConfiguration testConfig;
	public static BaseLogger logger;
	private static BaseLogger testLogger;

	public TestManager(ITestContext context) {

		logger = new BaseLogger();
		testLogger = new BaseLogger("Test Log");
		setTestConfig(TestConfiguration.getInstance());
		testConfig.loadConfigProperties(context);
		setDriver(new WebDriverExecutor(testConfig));

		logger.logDebug("Manager has been created for test: " + context.getName());
	}

	public TestConfiguration getTestConfig() {

		return this.testConfig;
	}

	private void setTestConfig(TestConfiguration testConfig) {

		this.testConfig = testConfig;
	}

	public Executor getExecutor() {

		return this.driver;
	}

	private void setDriver(Executor driver) {

		this.driver = driver;
	}

	public void masterTearDown() {

		driver.quit();
	}

	public RunConfiguration getExecConfig() {

		return execConfig;
	}

	public static void setTestLogger(BaseLogger testLogger) {

		TestManager.testLogger = testLogger;
	}

	public static BaseLogger getTestLogger() {

		return testLogger;
	}

}