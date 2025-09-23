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
import java.io.IOException;
import java.util.ArrayList;

import org.testng.TestNG;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ibm.atmn.wdbase.core.config.RunConfiguration;
import com.ibm.atmn.wdbase.utils.FileIOHandler;

/**
 * To be set as main class of executable jar to allow for custom arguments.
 * 
 */
public class Main {

	private static final String TEMPLATE_FILE_ARG = "-IBMtemplate";
	private static final String BROWSER_START_COMMAND_DELIMITER = ";";

	/**
	 * To be used instead of TestNG main method for executable jar only. Testng Ant task runner and plug-in do not use
	 * this.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		ArrayList<String> suites = null;
		ArrayList<String> testngArgs = new ArrayList<String>();

		//loop through all args and process custom arguments
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (arg.equalsIgnoreCase(TEMPLATE_FILE_ARG)) {
				suites = createSuiteFiles(args[i + 1]);
				i++;
			}
			else { //not a custom argument: belongs to testng
				testngArgs.add(arg);
			}
		}

		//set testng -suitethreadpoolsize argument automatically to run all suites in parallel
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

		for (String finalarg : modifiedArgs) {
			//TODO: Change to use logger.debug
			System.out.println("Arg: " + finalarg);
		}
		TestNG.main(modifiedArgs);
		System.exit(0);
	}

	/**
	 * Creates new testng suite xml files from a template.xml
	 * 
	 * @param templateName
	 * @return ArrayList<String> of testng suite.xml file names
	 */
	private static ArrayList<String> createSuiteFiles(String templateName) {

		ArrayList<String> suiteFilenames = new ArrayList<String>();
		String[] startCommands = null;

		//read xml
		File readFile = new File(RunConfiguration.getInstance().getTestConfigFolder(), templateName);
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
			startCommands = parameterValue.split(BROWSER_START_COMMAND_DELIMITER);
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
				RunConfiguration rConfig = RunConfiguration.getInstance();
				String fileName = browserStartCommand + ".xml";
				File targetFile = new File(rConfig.getLogOutputFolder(), fileName);
				boolean fileCreated = false;
				if (!targetFile.exists()) {
					try {
						fileCreated = targetFile.createNewFile();
					} catch (IOException e2) {
						System.out.println(targetFile);
						e2.printStackTrace();
					}
				}
				if (fileCreated) {
					suiteFilenames.add(rConfig.getLogOutputFolder().getName() + File.separator + fileName);
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
