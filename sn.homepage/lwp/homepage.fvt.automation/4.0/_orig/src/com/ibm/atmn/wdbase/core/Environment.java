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

import java.io.*;
import java.security.InvalidParameterException;
import java.util.*;

import com.ibm.atmn.wdbase.core.log.BaseLogger;

/**
 * Helpers for determining and handling an environment. Local environment can be determined automatically. Remote
 * environments are determined from test configuration.
 * 
 */
public class Environment {

	private static enum OSType {
		WINDOWS, LINUX, MAC
	}

	private static enum ArchType {
		BIT64, BIT32
	}

	private OSType os;
	private ArchType arch;

	private boolean isLocal = false;

	private Properties props;

	// file names of property files within the jar that we want to read in
	private String propFile = "test_config/conf.properties";

	/**
	 * Constructs Environment based on local System
	 */
	public Environment() {

		this(true, "");
	}

	/**
	 * Use to construct a remote environment. Default arch is BIT32. 'Windows' or 'Unix' or 'Linux' or 'Mac' keyword is
	 * required.
	 * 
	 * @param os
	 *            String with OS info from configuration file. OS name,version,arch. e.g. Windows7x64
	 * 
	 */
	public Environment(String configOS) {

		this(false, configOS);
	}

	/**
	 * Use to construct local environment from configuration string instead of attempting to determine form System
	 * properties.
	 * 
	 * @param isLocal
	 * @param configOS
	 */
	public Environment(boolean isLocal, String configOS) {

		this.isLocal = isLocal;
		props = new Properties();
		loadProps();

		if (this.isLocal && configOS.length() == 0) {
			addSystemInfoProperties();
			configOS = props.getProperty("os.name") + props.getProperty("os.version") + props.getProperty("os.arch");
		}

		identifyOS(configOS);

	}

	/**
	 * Tries to determine os properties and set public boolean helpers.
	 * 
	 * @param os
	 */
	private void identifyOS(String configOS) {

		if (configOS.toLowerCase().contains("win")) {
			this.os = OSType.WINDOWS;
		}
		else if (configOS.toLowerCase().contains("nix") || configOS.toLowerCase().contains("nux")) {
			this.os = OSType.LINUX;
		}
		else if (configOS.toLowerCase().contains("mac")) {
			this.os = OSType.MAC;
		}
		else {
			throw new InvalidParameterException("The OS type could not be determined from the name provided.");
		}

		if (configOS.toLowerCase().contains("64")) {
			this.arch = ArchType.BIT64;
		}
		else { //Default
			this.arch = ArchType.BIT32;
		}
	}

	public boolean isWindows() {

		return this.os == OSType.WINDOWS ? true : false;
	}

	public boolean isLinux() {

		return this.os == OSType.LINUX ? true : false;
	}

	public boolean isMac() {

		return this.os == OSType.MAC ? true : false;
	}

	public boolean is64Bit() {

		return this.arch == ArchType.BIT64 ? true : false;
	}

	public boolean is32Bit() {

		return this.arch == ArchType.BIT32 ? true : false;
	}

	/**
	 * Adds system info to the properties set
	 */
	public void addSystemInfoProperties() {

		props.setProperty("os.name", System.getProperty("os.name"));
		props.setProperty("os.version", System.getProperty("os.version"));

		//TODO: os.arch tells you if the JVM is 32 or 64 bit, not if the OS is. This info should probably be provided by the user.
		props.setProperty("os.arch", System.getProperty("os.arch"));
	}

	/**
	 * Checks whether a given property key exists
	 * 
	 * @param propName
	 *            Name of the property
	 */
	public boolean checkPropertyExists(String propName) {

		if (props.containsKey(propName)) {
			return true;
		}

		return false;
	}

	/**
	 * Returns a value for the specified property name Returns an empty string if the property does not exist in the
	 * hash
	 * 
	 * @param propName
	 *            Name of the property
	 */
	public String getProperty(String propName) {

		if (props.containsKey(propName)) {
			return props.getProperty(propName);
		}

		return "";
	}

	/**
	 * Creates a new entry in the Properties hash with the specified name and value
	 * 
	 * @param propName
	 *            Name of the property
	 * @param propValue
	 *            Value of the property
	 */
	public void setProperty(String propName, String propValue) {

		props.setProperty(propName, propValue);
	}

	/**
	 * Loads any of the properties file we need to load into a single properties hash
	 */
	public void loadProps() {

		try {
			loadBundledPropertyFile(propFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("IOException occurred while attempting to load property file " + propFile);
		}
	}

	/**
	 * Loads a property file that is bundled into the jar
	 * 
	 * @param file
	 *            Name of the file in the jar
	 */
	private void loadBundledPropertyFile(String file) throws IOException {

		// handle two scenarios (1) file exists locally, (2) file is in bundle
		InputStream in;

		try {
			in = new FileInputStream(file);
		} catch (Exception e) {
			in = null;
		}

		// if we don't have the file locally, try to extract it from the jar
		if (in == null) {
			try {
				in = getClass().getClassLoader().getResourceAsStream(file);
			} catch (Exception e) {
				in = null;
			}
		}

		if (in == null) {
			throw new IOException("Could not find properties file " + file);
		}

		props.load(in);
	}

	/**
	 * Prints out all of the environment properties
	 */
	public void printProps(BaseLogger logger) {

		Enumeration<Object> propsList = props.keys();
		ArrayList<String> sortedPropsList = new ArrayList<String>();

		// sort the properties
		while (propsList.hasMoreElements()) {
			sortedPropsList.add((String) propsList.nextElement());
		}
		Collections.sort(sortedPropsList);

		if (props.size() > 0) {
			logger.logInfo("*************** ENVIRONMENT ***************");
		}

		for (String key : sortedPropsList) {
			logger.logInfo(String.format("%1$30s", key) + " = " + getProperty(key));
		}

		if (props.size() > 0) {
			logger.logInfo("*******************************************");
		}
	}

	/**
	 * Returns the properties object
	 */
	public Properties getProps() {

		return props;
	}

	/**
	 * Constructs an absolute path for this environment including correct separator and root.
	 * 
	 * @param folders
	 *            The directories in order, ending with target directory, starting from root (e.g. 'c:\' or '/')
	 * @return The absolute path without a trailing separator
	 */
	public String constructAbsolutePathToDirectoryFromRoot(String... folders) {

		String path = "";
		String separator = getSeparator();
		int pos = 0;

		if (folders[0].toLowerCase().startsWith("c:") || folders[0].toLowerCase().startsWith("/")) {
			path = folders[0];
			pos = 1;
			if (path.endsWith(separator)) {
				path = path.substring(0, path.lastIndexOf(separator));
			}
		}
		else {
			if (isWindows()) {
				path = "C:";
			}
		}

		while (pos < folders.length) {
			String folder = folders[pos];
			path = path + separator + folder;
			pos++;
		}
		return path;
	}

	/**
	 * 
	 * @param dir
	 *            An absolute path to directory (see constructAbsolutePathToDirectoryFromRoot(String... dirs))
	 * @param fileName
	 *            The name of the file to add to the path
	 * @return Absolute path to the file
	 */
	public String getAbsoluteFilePath(String dir, String fileName) {

		String separator = getSeparator();
		if (dir.endsWith(separator)) {
			return dir + fileName;
		}
		else {
			return dir + separator + fileName;
		}
	}

	/**
	 * Use instead of File.separator to construct paths for remote machines with possibly different OS.
	 * 
	 * @return Forward-slash or back-slash depending on OS
	 */
	private String getSeparator() {

		if (isWindows()) {
			return "\\";
		}
		else {
			return "/";
		}
	}

	public boolean isLocal() {

		return this.isLocal;
	}
}
