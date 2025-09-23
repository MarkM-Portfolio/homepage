/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.selenium.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Util for homepage DOH classes
 * @author Robert Campion
 */
public class DOHTestUtil {
	/**
	 * Create a file if that file doesn't exist.
	 */
	public static synchronized void createFile(String propertiesFile){
		File file = new File(propertiesFile);
		
		if(!file.exists()){
			// Create it
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Adds a property to a properties file if that property 
	 * doesn't already exist.
	 * @param key
	 * @param value
	 */
	public static synchronized void addProperty(String propertiesFile, String key, String value){
		Properties dohResultsProps = new Properties();
		
		try {
			dohResultsProps.load(new FileInputStream(propertiesFile));

			// Only set the value if it is not already set
			if(dohResultsProps.getProperty(key) == null){
				System.out.println(key + " is null, setting it to " + value);
				dohResultsProps.setProperty(key, value);
				dohResultsProps.store(new FileOutputStream(propertiesFile), "");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
