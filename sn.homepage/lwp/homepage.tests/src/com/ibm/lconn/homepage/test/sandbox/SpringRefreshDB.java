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

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.init.ScriptUtils;

public class SpringRefreshDB implements IRefreshDB {

	/**
	 * 
	 * This method will always refresh the DB at each method execution.
	 * It will delete all the database content and it will insert just what getDBTableNames() give back.
	 * 
	 * If getDbTableNames will give back null a full database refresh will be performed: TODO.
	 * 
	 * @throws Exception
	 */
	public void refreshDB(DataSource ds, String[] tables)  {
		try {
			System.out.println("SPRING: Refresh db is running: it is deleting all the records and adding back what you have specified in your sql file list");
			Date start = new Date();
			StringBuffer sb = new StringBuffer();
			
			SandboxUtility.deleteManuallyAllTables(ds);
			
			// Verify if there is something to populate
			if (tables != null) {
				// When the getTableNames contains HOMEPAGE then it means we want to re-insert all the data in the full db
				if (tables[0].equals("HOMEPAGE")) {
					List<String> fullListOfHomepageTables = SandboxUtility.getTableNames();
					for (String table : fullListOfHomepageTables) {
						String sqlScript = table+".sql";
						System.out.println("\nRunning this xml script: "+sqlScript);
						ScriptUtils.executeSqlScript(ds.getConnection(), SandboxUtility.getResourceScript(sqlScript));
						sb.append(table+", ");
					}
				}
				
				for (String table : tables) {
					String sqlScript = table+".sql";
					System.out.println("\nRunning this sql script: "+sqlScript);
                                        ScriptUtils.executeSqlScript(ds.getConnection(), SandboxUtility.getResourceScript(sqlScript));
					sb.append(table+", ");
				}
			}
			
			Date end = new Date();
			System.out.println("SPRING: Refresh db completed in: "+(end.getTime() - start.getTime() )+"ms. This is the list of tables loaded: "+sb);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(-1); // If the database is not populated stop all the test
		}
	}
}
