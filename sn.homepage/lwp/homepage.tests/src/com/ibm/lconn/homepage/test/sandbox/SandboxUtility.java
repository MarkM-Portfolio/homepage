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

package com.ibm.lconn.homepage.test.sandbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.ibm.lconn.homepage.test.TestHome;

public class SandboxUtility {

	
	private static final String CREATE_DERBY_DB = TestHome.getPJ_HOME()+"/derby_scripts/createDb.sql";
	
	private static Map<String, String> files;
	
	private static List<String> tableNames; //contains a list of tablenames, excludes views as they cant be deleted properly - the list is in the same order of createDb.sql
	private static List<String> dbTablesInReverseOrder;// contains a list of tablenames in reverse order for deletion - the list is in the inverse order

	private static Map<String, Boolean> refreshClassNeed;
	
	/**
	 * Returns true/false if the class is refreshing on every method call.
	 * Will also insert the value need into the refreshClassNeedmap if (need != null)
	 * 
	 * @param CLASSNAME
	 * @param need
	 * @return
	 */
	public static boolean getRefreshClassNeed(final String CLASSNAME, Boolean need) {
		if (refreshClassNeed == null)
			refreshClassNeed = new HashMap<String, Boolean>();
		
		Boolean status = refreshClassNeed.get(CLASSNAME);
		
		if (status == null) {
			if (need != null) {
				refreshClassNeed.put(CLASSNAME, need);
			}
			return true; // the first time return always true
		}
		else
			return status;		
	}
	
	/**
	 * 
	 * From a file like PERSON.xml it give you the right File object
	 * 
	 * @param filename
	 * @return
	 */
	public static File getFile(String filename) {
		files =  getFiles();
		return new File(files.get(filename));
	}
	
	/**
	 * Load all the files in DB_tables folder
	 * 
	 * @return
	 */
	public static Map<String, String> getFiles() {
		if (files == null) {
			String HP_REPOSITORY_FOLDER = TestHome.getPJ_HOME()+"/derby_scripts/DB_tables/";
			  
			Map<String, String> filenameMap = new HashMap<String, String>();
			files = recurseDBFilesDir(filenameMap, new File(HP_REPOSITORY_FOLDER));
		}
		return files;
	}	
	
	public static void deleteManuallyAllTables(DataSource ds) {
		//System.out.println("deleteManuallyAllTables ..");
		readInCreateBDFile(); // to init the table
		
		try {
			
			Connection conn = ds.getConnection();	
			String schema = "HOMEPAGE";
			
			if (conn!=null && !conn.isClosed()) {
				System.out.println("Testing is running vs: "+conn.getMetaData().getURL());
				Statement stmt = null;
				stmt = conn.createStatement();
				for(String tableName : dbTablesInReverseOrder) {
					try{
						String deleteSQL =  (new StringBuffer().append("DELETE FROM ").append(schema).append("."+tableName)).toString();
						 
						//System.out.println("deleteManuallyAllTables:  Executing this SQL query: "+deleteSQL);
						
						stmt.addBatch(deleteSQL);
					}
					catch(Exception e){//if a table does not exist in derby we get an exception
						System.out.println(e.getMessage());
						e.printStackTrace();
						if (stmt!=null) {
							stmt.close();
						}
					}
				} // Closing for
				int [] numDeleted = stmt.executeBatch();
				
				//System.out.println("deleteManuallyAllTables : Deleted those number of records: "+getNumUpdates(numDeleted));
				
				if (!conn.getAutoCommit())
						conn.commit();
				stmt.close();
				conn.close(); // Release the connection to the DS
			}
			else {
				throw new Exception("The jdbc connection is not valid. It looks null or closed: "+conn);
			}
		}
		catch (Exception e) {
			e.printStackTrace();

		}
	}
	
	
	// Pattern to read the sql file
	private static String createStringPattern = "^CREATE TABLE HOMEPAGE\\.(\\w*).*";
	private static Pattern createStatement = Pattern.compile(createStringPattern);
	
	private static String createViewStringPattern = "^CREATE VIEW HOMEPAGE\\.(\\w*).*";
	private static Pattern createViewStatement = Pattern.compile(createViewStringPattern);
	/**
	 * From the sql file it reads all the tables name.
	 * This method is very important if you want to refresh the db
	 * 
	 * @return
	 */
	public static List<String> readInCreateBDFile() {
		
		List<String> dbTables= new ArrayList<String>();
		
		if (tableNames == null)
				tableNames = new ArrayList<String>();
		
		BufferedReader in;
		try{
			in = new BufferedReader(new FileReader(CREATE_DERBY_DB));
			StringBuffer buff = new StringBuffer(1024);
			String line = null;
			while((line = in.readLine()) != null){
				Matcher m = createStatement.matcher(line);
				if(m.matches()){
					String s = m.group(1);
					if (s.equals("NR_ENTRIES")) {
						dbTables.add("NR_ENTRIES_ACT");
						dbTables.add("NR_ENTRIES_BLG");
						dbTables.add("NR_ENTRIES_COM");
						dbTables.add("NR_ENTRIES_WIK");
						dbTables.add("NR_ENTRIES_PRF");
						dbTables.add("NR_ENTRIES_HP");
						dbTables.add("NR_ENTRIES_DGR");
						dbTables.add("NR_ENTRIES_FILE");
						dbTables.add("NR_ENTRIES_FRM");
						dbTables.add("NR_ENTRIES_EXTERNAL");
					}
					if (s.equals("NR_STORIES")) {
						dbTables.add("NR_SRC_STORIES_ACT");
						dbTables.add("NR_SRC_STORIES_BLG");
						dbTables.add("NR_SRC_STORIES_COM");
						dbTables.add("NR_SRC_STORIES_WIK");
						dbTables.add("NR_SRC_STORIES_PRF");
						dbTables.add("NR_SRC_STORIES_HP");
						dbTables.add("NR_SRC_STORIES_DGR");
						dbTables.add("NR_SRC_STORIES_FILE");
						dbTables.add("NR_SRC_STORIES_FRM");
						dbTables.add("NR_SRC_STORIES_EXTERNAL");
						
						dbTables.add("NR_STORIES");
					}
					else {
						dbTables.add(s);
					}
					
					if(!tableNames.contains(s)) {
						tableNames.add(s);												
					}
				}
				Matcher mView = createViewStatement.matcher(line);
				if(mView.matches()){
					dbTables.add(mView.group(1));
				}
				
			}
			//Adding manually tables names		
			//System.out.println(dbTables);
			
			
			in.close();
		}
		catch(Exception e){
			System.out.print(e.getMessage());
		}
		// We need to remove some tables because they are not used in the unit test and they generated exception likes:
		// Caused by: ERROR 42X05: Table/View 'HOMEPAGE.LOTUSCONNECTIONSTASK' does not exist.
		tableNames.remove("LOTUSCONNECTIONSTASK");
		tableNames.remove("LOTUSCONNECTIONSTREG");
		tableNames.remove("LOTUSCONNECTIONSLMGR");
		tableNames.remove("LOTUSCONNECTIONSLMPR");
		tableNames.remove("NR_SCHEDULER_TASK");
		
		if(dbTablesInReverseOrder == null)
			dbTablesInReverseOrder = new ArrayList<String>(tableNames);
		Collections.copy(dbTablesInReverseOrder, tableNames);
		Collections.reverse(dbTablesInReverseOrder); //reverse the list to delete from bottom up
		
		return dbTables;
	}
	
	public static List<String> getTableNames() {
		if (tableNames==null)
			readInCreateBDFile();
		
		return tableNames;
	}
	
	private static Map<String, String> recurseDBFilesDir(Map<String, String> filenames, File currentFile){
		if(currentFile.isDirectory()){
			File[] dirfileList = currentFile.listFiles();
			for(File dirFile : dirfileList){
				filenames = recurseDBFilesDir(filenames, dirFile);
			}
		}
		else if(currentFile.isFile()){
			filenames.put(currentFile.getName(),currentFile.getPath());
		}
		return filenames;
	}
	
	private SandboxUtility(){};
	
	
	private static int getNumUpdates(int [] numUpdates) {
		int res=0;
		
		if (numUpdates !=null) {
		
			for (int i=0; i < numUpdates.length; i++) { 
			    if (numUpdates[i] == java.sql.Statement.SUCCESS_NO_INFO) 
			    	res += 1;
			    else
			    	res += numUpdates[i];
			  }
			
		}
		return res;
	}

	public static Resource getResourceScript(String scriptFileName) {
		String file = scriptFileName;
		FileSystemResource resource = new FileSystemResource(getFile(file));
		return resource;
	}
	
	
}
