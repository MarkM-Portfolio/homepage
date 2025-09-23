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

package com.ibm.lconn.homepage.test.sandbox;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.db2.Db2DataTypeFactory;
import org.dbunit.ext.mssql.MsSqlDataTypeFactory;
import org.dbunit.ext.oracle.Oracle10DataTypeFactory;
import org.dbunit.operation.DatabaseOperation;

import com.ibm.lconn.homepage.test.TestHome;


public class DBUnitRefreshDB implements IRefreshDB {
	
	public static String DB2 = "Db2";
	public static String ORACLE = "Oracle";
	public static String SQLSERVER = "SQLServer";
	
	private final static String TABLES_TO_IGNORE_ARRAY[] = {"HOMEPAGE","SR_GLOBAL_SAND_PROPS","SR_TIMER_STATS","SR_NUMBER_STATS",
															"NR_AS_CONTENT_INDEX_STATS","SR_STRING_STATS","SR_STATS","SR_FEEDBACK_PARAMETERS",
															"SR_FEEDBACK_CONTEXT","SR_FEEDBACK","SR_INDEX_DOCS","SR_RESUME_TOKENS",
															"SR_INDEX_MANAGEMENT","SR_MIGTASKDEFINFO","SR_FILESCONTENT","SR_SANDTASKDEF",
															"SR_BACKUPTASKDEF","SR_FILECONTENTTASKDEF","SR_OPTIMIZETASKDEF","SR_INDEXINGTASKDEF","SR_TASKDEF",
															"NR_AS_CRAWLER_STATUS", "NR_AS_COLLECTION_CONFIG", "OH2P_CACHE","OH2P_CLIENTCFG", "NR_NEWS_RECORDS", 
															"NR_ATTACHMENT_301", "NR_RECOMMENDATION_301", "NR_SUBSCRIPTION", "NR_SCHEDULER_TREG", "NR_SCHEDULER_LMGR", 
															"NR_SCHEDULER_LMPR",
															"HOMEPAGE_SCHEMA",
															"NT_REPLYTO", "NT_REPLYTO_RECIPIENT",
															"OAUTH1_TOKEN","OAUTH1_PROVIDER","OAUTH1_CLIENT","OAUTH1_CONTEXT","OAUTH2_PROVIDER","OAUTH2_CLIENT","OAUTH2_GADGET_BINDING","OAUTH2_TOKEN",
															"MTCONFIG","NR_SOURCE_TYPE","NR_CATEGORY_TYPE",
															"NR_NEWS_STATUS_NETWORK","NR_NEWS_STATUS_COMMENT","NR_NEWS_STATUS_CONTENT","NR_NEWS_COMMENT_CONTENT",
															"NR_RESOURCE_TYPE","NR_RESOURCE","NR_FOLLOWS","NR_COMM_FOLLOW",
															"NR_NETWORK",
															"EMD_FREQUENCY_TYPE","EMD_RESOURCE_PREF","EMD_TRANCHE_INFO","EMD_EMAIL_PREFS",
															"EMD_TRANCHE",
															"NR_STORIES_CONTENT",
															"NR_COMM_PERSON_FOLLOW","NR_ENTRIES","NR_ENTRIES","NR_STORIES","NR_ENTRIES_ARCHIVE",
															"NR_AGGREGATED_READERS","NR_RESPONSES_READERS","NR_PROFILES_READERS","NR_COMMUNITIES_READERS","NR_ACTIVITIES_READERS",
															"NR_BLOGS_READERS","NR_BOOKMARKS_READERS","NR_FILES_READERS","NR_FORUMS_READERS","NR_WIKIS_READERS","NR_SAVED_READERS",
															"NR_DISCOVERY_VIEW","NR_PROFILES_VIEW","NR_MENTIONS_READERS","NR_TAGS_READERS","NR_TOPICS_READERS","NR_STATUS_UPDATE_READERS",
															"NR_EXTERNAL_READERS","NR_ACTIONABLE_READERS",
															"BOARD","BOARD_ENTRIES","BOARD_CURRENT_STATUS","BOARD_COMMENTS","BOARD_RECOMMENDATIONS","BOARD_OBJECT_REFERENCE","BOARD_MENTIONS",
															"DELETED_STORIES_QUEUE","NR_COMM_SETTINGS",
															"NR_NOTIFICATION_SENT_READERS","NR_NOTIFICATION_RECEIV_READERS",
															"NR_AS_SEEDLIST","SR_ECM_DOCUMENT_TYPE_LABELS","SR_POST_FILTERING_SERVICE","SR_ECM_DOCUMENT_TYPE_PROPS",
															"MT_CFG_DEFINITIONS","MT_CFG_SETTINGS","MT_CFG_FILES"}; 
	private final static List<String> TABLES_TO_IGNORE_LIST = Arrays.asList(TABLES_TO_IGNORE_ARRAY); 

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
			System.out.println("DBUNIT: Refresh db is running: it is deleting all the records and adding back what you have specified in your sql file list");
			Date start = new Date();
			StringBuffer sb = new StringBuffer();			
			
			SandboxUtility.deleteManuallyAllTables(ds);
			
			IDatabaseConnection databaseConn = new DatabaseDataSourceConnection(ds,"HOMEPAGE"); // setting schema HOMEPAGE
			configDatatypeFactory(databaseConn);
			
			FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
			builder.setColumnSensing(true);
			
			// Verify if there is something to populate
			if (tables != null) {
				// When the getTableNames contains HOMEPAGE then it means we want to re-insert all the data in the full db
				if (!tables[0].equals("")) {
					if (tables[0].equals("HOMEPAGE")) {
						List<String> fullListOfHomepageTables = SandboxUtility.getTableNames();
						
						
						for (String table : fullListOfHomepageTables) { 
							if (!TABLES_TO_IGNORE_LIST.contains(table)) {
								String fileXml = table+".xml";
								System.out.println("\nRunning this xml script: "+fileXml);						
								IDataSet dataSet = builder.build(SandboxUtility.getFile(fileXml));			
								DatabaseOperation.INSERT.execute(databaseConn, dataSet);
								sb.append(table+", ");
							}
						}
					}
					else {
						for (String table : tables) {
							String fileXml = table+".xml";
							System.out.println("\nRunning this xml script: "+fileXml);
							IDataSet dataSet = new FlatXmlDataSet(SandboxUtility.getFile(fileXml));
							DatabaseOperation.INSERT.execute(databaseConn, dataSet);
							sb.append(table+", ");
						}
					}
				}
				databaseConn.close();
			}
			
			Date end = new Date();
			System.out.println("DBUNIT: Refresh db completed in: "+(end.getTime() - start.getTime() )+"ms. This is the list of tables loaded: "+sb);

		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(-1); // If the database is not populated stop all the test
		}
	}
	
	/**
	 * To configure the right datatype factory
	 * 
	 * @param databaseConn
	 */
	private void configDatatypeFactory(IDatabaseConnection databaseConn) {
		DatabaseConfig config = databaseConn.getConfig();
		TestHome.DbVendor dbvendor = TestHome.getDbVendor();
		
		if (dbvendor.equals(TestHome.DbVendor.ORACLE))
				config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new Oracle10DataTypeFactory()); // to workaround CLOB oracle limitation
		if (dbvendor.equals(TestHome.DbVendor.DB2))
				config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new Db2DataTypeFactory());
		if (dbvendor.equals(TestHome.DbVendor.SQLSERVER))
				config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MsSqlDataTypeFactory());
	}
}
