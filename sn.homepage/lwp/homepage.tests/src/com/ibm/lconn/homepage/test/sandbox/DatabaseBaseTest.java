/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.sandbox;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.BeforeClass;

import com.ibm.lconn.homepage.test.TestHome;
import com.ibm.lconn.homepage.test.data.util.DerbyUtility;

/**
 * NB: Before attempting to write new unit tests, please ensure that you are familiar with the
 * process outlined here:
 * https://w3-connections.ibm.com/wikis/home?lang=en#/wiki/Lotus%20Connections%202.5/page/Unit%20tests
 * This is especially important if adding new Spring managed contexts or adding/editing test Database data.
 * @author ieu71139
 *
 */


public abstract class DatabaseBaseTest {
	
	private static String disclaimer = 
			"/****************************************************************************%n" +
			" *  WARNING: Read: Running these unit tests without reading the unit test%n" +
			" *  documentation may seriously damage your health, or at a minimum,%n" +
			" *  wreck your head :o),please see here first for further information if%n" +
			" *  experiencing \"issues\":%n" +
			" *  %s %n"+
			" ****************************************************************************/%n";
 
	
	private static String unitTestDocUrl = "https://w3-connections.ibm.com/wikis/home?lang=en#/wiki/Lotus%20Connections%202.5/page/Unit%20tests";
	
	private static IRefreshDB refreshDB;
	
	
	@BeforeClass
	public static void initDB() {			
		// IMPORTANT: if you want to see logs queries also for DB2, ORACLE and SQLSERVER, then this line MUST be the first line in the method.
		TestHome.initLogging();
		
		System.out.format(disclaimer, unitTestDocUrl);

		refreshDB = new DBUnitRefreshDB();
		
		System.setProperty("test.config.files", TestHome.getTestConfigFilesDir());
		
		if (TestHome.getDbVendor().equals(TestHome.DbVendor.DERBY)) {
			DerbyUtility.initDerbyDb();
		}
	}	
	
	@Before
	public abstract void init() throws Exception;
	
	protected abstract DataSource getDataSource();
	
	/**
	 * You need to override this method to set the ordered set of tables that you 
	 * want to have populated.
	 * 
	 * You must specify the table name without the schema name: i.e PERSON table.
	 * 
	 * If you don't want any table populated then the method must return null
	 * 
	 * If you want populate the full database then the method must return an array with one string equals to HOMEPAGE.
	 * 
	 * Those are valid implementation of the method:
	 * 
	 * 	
	 * @Override
		public String[] getDBTableNames() {
			return new String[]{"HOMEPAGE"};
		}
		
		@Override
		public String[] getDBTableNames() {
			return null;
		}
		
		public String[] getDBTableNames() {
			return new String[]{"PERSON, HP_UI"};
		}

	 * 
	 * @return
	 */
	protected abstract String[] getDBTableNames();
	
	/**
	 * 
	 * This method performs a db refresh based on the boolean value of isRefreshNeededEachMethod.
	 * 
	 * If isRefreshNeededEachMethod is true then before each method the database is refreshed. This is not good in terms of perf.
	 * If isRefreshNeededEachMethod is false then the database is refreshed just the first time a test method run.
	 * 
	 * The status is saved in SandboxUtility object in a static map.
	 * 
	 * @param CLASSNAME
	 * @param isRefreshNeededEachMethod
	 * @throws Exception
	 */
	protected void refreshDB(String CLASSNAME, boolean isRefreshNeededEachMethod) throws Exception {
		if (SandboxUtility.getRefreshClassNeed(CLASSNAME, Boolean.valueOf(isRefreshNeededEachMethod))) {
			refreshDB.refreshDB(getDataSource(), getDBTableNames());
		}
	}
	
	/**
	 * This method performs a db refresh if the CLASSNAME is not already refreshing on every method call.
	 * This allows individual methods to refresh the database BUT only if the whole class is not already
	 * doing this (saves on performance)
	 * 
	 * @param CLASSNAME
	 * @throws Exception
	 */
	protected void refreshDB(String CLASSNAME) throws Exception {
		if (!SandboxUtility.getRefreshClassNeed(CLASSNAME, null)) {
			refreshDB.refreshDB(getDataSource(), getDBTableNames());
		}		
	}
}
