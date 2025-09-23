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
import java.util.ArrayList;
import java.util.Properties;

import org.testng.TestNG;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;

/**
 * For gathering and holding configuration at global 'run' level.
 * 
 * @author Ruairí Pidgeon
 * 
 */
public class RunConfigManager {

	/*
	 * Files identify purposed directories and files on local machine. These files/folders must be common to all suites.
	 */
	public File testConfigFolder;
	public File logFolder;

	//'local' is the environment where the tests are running
	public boolean localIsUnix = false;
	public boolean localIsWindows = false;
	public boolean localIsMac = false;

	//paths to files
	private static final String FILES_PROPERTIES = "/com/ibm/atmn/base/config/files.properties";
	public Properties fileManifest;

	private static final String TEST_CONFIG_FOLDER_PROPERTY_NAME = "test_config_folder";
	private static final String LOG_FOLDER_PROPERTY_NAME = "log_folder";
	private static final String USERS_DATA_FILE_ARG = "-IBMusersData";
	private static final String TEMPLATE_FILE_ARG = "-IBMtemplate";

	public static final String USER_DATA_PROPERTY_NAME = "user_data_file";
	public static final String USER_CONF_PROPERTY_NAME = "user_conf_file";
	public static final String ADMIN_USERS_PROPERTY_NAME = "admin_users";
	public static final String MODERATOR_USERS_PROPERTY_NAME = "global_mods";
	public static final String SPECIAL_USERS_DELIMITER = ";";
	public static final String BROWSER_START_COMMANDS_DELIMITER = ";";

	private RunConfigManager() {

		//Set local platform here.
		identifyAndSetLocalOS();
		initFileManifest();
		createRunDir();

		//localBaseDir = new FileIOHandler().buildFolderPath(localIsUnix, localIsWindows, localIsMac, context.getCurrentXmlTest().getParameter("local_root_folder"));
		//reportOutputDir = new File(context.getOutputDirectory());
	}

	private static class RunConfigManagerHolder {

		private static final RunConfigManager oneRunConfigManager = new RunConfigManager();
	}

	/**
	 * Returns singleton instance of RunConfigManager
	 * 
	 * @return RunConfigManager
	 */
	public static RunConfigManager getInstance() {

		return RunConfigManagerHolder.oneRunConfigManager;
	}

	/**
	 * To be used instead of TestNG main method for executable jar only. Ant task and plugin do not use this. Used to
	 * create File instances for config files outside jar. Other launch systems will use internal config package files.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		ArrayList<String> suites = null;
		ArrayList<String> testngArgs = new ArrayList<String>();
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (arg.equalsIgnoreCase(USERS_DATA_FILE_ARG)) {
				RunConfigManager.getInstance().fileManifest.put(USER_DATA_PROPERTY_NAME, args[i + 1]);
				i++;
			}
			else if (arg.equalsIgnoreCase(TEMPLATE_FILE_ARG)) {
				suites = RunConfigManager.getInstance().createSuiteFiles(args[i + 1]);
				i++;
			}
			else {
				testngArgs.add(arg);
			}
		}
		if (suites != null && suites.size() > 0) {
			if (testngArgs.indexOf("-suitethreadpoolsize") > -1) {
				testngArgs.set(testngArgs.indexOf("-suitethreadpoolsize") + 1, String.valueOf(suites.size()));
			}
			else {
				testngArgs.add("-suitethreadpoolsize");
				testngArgs.add(String.valueOf(suites.size()));
			}
			for (String suite : suites) {
				testngArgs.add(suite);
			}
		}
		String[] modifiedArgs = new String[testngArgs.size()];
		testngArgs.toArray(modifiedArgs);

		//TODO: Remove print
		for (String finalarg : modifiedArgs) {
			System.out.println("Arg: " + finalarg);
		}
		TestNG.main(modifiedArgs);
	}

	private void identifyAndSetLocalOS() {

		String localOS = System.getProperty("os.name");
		if (localOS.toLowerCase().contains("win")) {
			this.localIsWindows = true;
		}
		else if (localOS.toLowerCase().contains("nix") || localOS.toLowerCase().contains("nux")) {
			this.localIsUnix = true;
		}
		else if (localOS.toLowerCase().contains("mac")) {
			this.localIsMac = true;
		}
		else {
			//TODO: something
		}
	}

	private void initFileManifest() {

		this.fileManifest = FileIOHandler.getProperties(FILES_PROPERTIES);
	}

	private void createRunDir() {

		testConfigFolder = FileIOHandler.createFolderFromPath(fileManifest.getProperty(TEST_CONFIG_FOLDER_PROPERTY_NAME));
		logFolder = FileIOHandler.createFolderFromPath(fileManifest.getProperty(LOG_FOLDER_PROPERTY_NAME), true);
	}

	private ArrayList<String> createSuiteFiles(String templateName) {

		ArrayList<String> suiteFilenames = new ArrayList<String>();
		String[] startCommands = null;

		//read xml
		File readFile = new File(RunConfigManager.getInstance().testConfigFolder, templateName);
		if (readFile.exists() != true || readFile.canRead() != true) {
			//TODO:something
		}
		Document template = FileIOHandler.getXMLConfig(readFile);

		//get browser start command parameter
		NodeList parameters = template.getElementsByTagName("parameter");
		Node browserStartCommandParameter = findNodeByAttributeValue(parameters, "name", "browser_start_command");

		//get value attribute
		String parameterValue = getAttributeValue(browserStartCommandParameter, "value");

		//Get start commands
		if (parameterValue != null) {
			startCommands = parameterValue.split(BROWSER_START_COMMANDS_DELIMITER);
		}
		else {
			//TODO: something
		}

		//modify template with commands, 1 per browser start command, write to file
		if (startCommands != null && startCommands.length > 0) {
			for (String browserStartCommand : startCommands) {
				setAttributeValue(browserStartCommandParameter, "value", browserStartCommand);
				Node suite = template.getElementsByTagName("suite").item(0);
				setAttributeValue(suite, "name", browserStartCommand);
				RunConfigManager rConfig = RunConfigManager.getInstance();
				String fileName = browserStartCommand + ".xml";
				File targetFile = new File(rConfig.logFolder + File.separator + fileName);
				boolean fileCreated = false;
				if (!targetFile.exists()) {
					try {
						fileCreated = targetFile.createNewFile();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
				if (fileCreated) {
					suiteFilenames.add(rConfig.logFolder.getName() + File.separator + fileName);
					FileIOHandler.writeXmlFile(template, targetFile);
				}
			}
		}
		//return file names
		return suiteFilenames;

	}

	private static Node findNodeByAttributeValue(NodeList elements, String attributeName, String attributeValue) {

		for (int i = 0; i < elements.getLength(); i++) {
			Node element = elements.item(i);
			if (element.hasAttributes()) {
				String attValue = getAttributeValue(element, attributeName);
				if (attValue != null && attValue.equalsIgnoreCase(attributeValue)) {
					return element;
				}
			}

		}
		return null;
	}

	private static String getAttributeValue(Node element, String attributeName) {

		return getAttribute(element, attributeName).getNodeValue();
	}

	private static Node getAttribute(Node element, String attributeName) {

		NamedNodeMap attributes = element.getAttributes();
		for (int k = 0; k < attributes.getLength(); k++) {
			Node current = attributes.item(k);
			if (current.getNodeName().equalsIgnoreCase(attributeName)) {
				return current;
			}
		}
		return null;
	}

	private static void setAttributeValue(Node element, String attributeName, String newValue) {

		getAttribute(element, attributeName).setNodeValue(newValue);
	}
}
