/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2013, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.sql.utilities;

import static java.util.logging.Level.FINER;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;


public class MainScriptGenerator {
	
	static String CLASS_NAME = MainScriptGenerator.class.getName();
	static Logger logger = Logger.getLogger(CLASS_NAME);
	
	public static Object returnedResult;
	
	public static void main (String args[]) throws SQLException, IOException{
		
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "main", new Object[]{args});
		
		// get query from properties file, send to jdbc and create sql files
		Properties propJDBC = new Properties();
		String query = getQuery(propJDBC);
		//appGrants(propJDBC);
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "main");
	}
		
	public static String getQuery(Properties propJDBC) {
		
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getQuery", new Object[]{propJDBC});
		
		try {
			propJDBC.load(new FileInputStream("jdbc.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// get statement from properties file
		String statement = propJDBC.getProperty("statement");
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getQuery", statement);
		
		return statement;
	}
	
	
	public static void addToFile(File file, String[] statements) throws IOException{
		
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "addToFile", new Object[]{file, statements});
		
		// add lines of sql statements to specified file
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		for (int i=0; i<statements.length-1; i++){
			bw.write(statements[i]);
		}	
		bw.close();		
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "addToFile");
	}
	
	/*public static void appGrants(Properties propJDBC) throws SQLException, IOException{
		
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "appGrants", new Object[]{propJDBC});
		
		// generate appGrants files for each database type
		CreateScriptsAppGrantsDB2 appGrantsDB2 = new CreateScriptsAppGrantsDB2();
		appGrantsDB2.create(propJDBC, returnedResult);
		CreateScriptsAppGrantsSQL appGrantsSQL = new CreateScriptsAppGrantsSQL();
		appGrantsSQL.create(propJDBC, returnedResult);
		CreateScriptsAppGrantsOracle appGrantsOracle = new CreateScriptsAppGrantsOracle();
		appGrantsOracle.create(propJDBC, returnedResult);
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "appGrants");
	}*/
	
}
