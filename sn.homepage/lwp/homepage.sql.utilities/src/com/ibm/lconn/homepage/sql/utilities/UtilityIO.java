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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.sql.utilities.beans.Index;
import com.ibm.lconn.homepage.sql.utilities.script.IScriptGenerator;

public class UtilityIO {

	private static String CLASS_NAME = UtilityIO.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	public static Properties loadProperties() throws Exception {
		Properties propJDBC = new Properties();
		
		
		try {
			propJDBC.load(new FileInputStream("jdbc.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	

		return propJDBC;
	}

	public static File createFile(String fileDirectoryName, String fileName) {
		
		if (MainScriptGenerator.logger.isLoggable(FINER))
			MainScriptGenerator.logger.entering(MainScriptGenerator.CLASS_NAME, "createFile", new Object[]{fileDirectoryName, fileName});
		
		// create directories specified in properties file and file names
		File dir = new File(fileDirectoryName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(fileDirectoryName+"/"+fileName);
		try{
			if (!file.exists()) {
				file.createNewFile();
				return file;
			}
		}catch (IOException e){
			e.printStackTrace();
		}
		
		if (MainScriptGenerator.logger.isLoggable(FINER))
			MainScriptGenerator.logger.exiting(MainScriptGenerator.CLASS_NAME, "createFile", file);
		
		return file;
	}
	

	

	
	public static void generate(Properties props, Connection conn, IScriptGenerator generator) throws Exception {
		StringBuffer sb = generator.generateScript(conn);
		String path = (String) props.get("fileStructure");
		path += "/"+generator.getDbType().toLowerCase();
		File file = UtilityIO.createFile(path, generator.getFilename());
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			out.write(sb.toString());
			out.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Created: "+file);
	}
}
