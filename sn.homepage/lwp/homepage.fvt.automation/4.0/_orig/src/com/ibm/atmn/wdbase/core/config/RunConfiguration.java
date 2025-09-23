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

package com.ibm.atmn.wdbase.core.config;

import java.io.File;

import com.ibm.atmn.wdbase.core.Environment;
import com.ibm.atmn.wdbase.core.TestManager;
import com.ibm.atmn.wdbase.utils.FileIOHandler;

public class RunConfiguration {

	/*
	 * Files identify purposed directories and files on local machine. These files/folders must be common to all suites.
	 */
	private File testConfigFolder;
	private File logOutputFolder;
	private static final String LOG_FOLDER_NAME = "baselogs";
	private static final String TEST_CONFIG_FOLDER_NAME = "test_config";
	private Environment localEnvironment;

	private RunConfiguration() {

		verifyFilesAndDirectories();
		this.localEnvironment = new Environment();
	}

	private static class WATSConfigurationHolder {

		private static final RunConfiguration instance = new RunConfiguration();
	}

	/**
	 * Returns singleton instance
	 * 
	 * @return PATSConfiguration
	 */
	public static RunConfiguration getInstance() {

		return WATSConfigurationHolder.instance;
	}

	//TODO: This must be moved and changed so that it will cleanup files as necessary and verify existence of all required files.
	private void verifyFilesAndDirectories() {

		testConfigFolder = new File(TEST_CONFIG_FOLDER_NAME);
		if (!testConfigFolder.exists()) {
			TestManager.logger.logDebug("Config Folder can not be found");
		}
		logOutputFolder = FileIOHandler.createFolderFromPath(LOG_FOLDER_NAME, true);
	}

	public Environment getLocalEnvironment() {

		return this.localEnvironment;
	}

	public File getTestConfigFolder() {

		return this.testConfigFolder;
	}

	public File getLogOutputFolder() {

		return this.logOutputFolder;
	}
}
