/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2008, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.data.util;

/**

 * 
 * @author Lorenzo Notarfonzo
 * 
 */
import static com.ibm.lconn.homepage.test.data.util.DAOUnitTestConstants.DERBY_DB_DIR_LOCATION_SYSTEM_PROPERTY_NAME;
import static com.ibm.lconn.homepage.test.data.util.DAOUnitTestConstants.DERBY_SYSTEM_HOME_SYSTEM_PROPERTY_NAME;
import static com.ibm.lconn.homepage.test.data.util.DAOUnitTestConstants.JAVA_IO_TMPDIR_SYSTEM_PROPERTY_NAME;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.derby.tools.ij;

import com.ibm.lconn.homepage.test.TestHome;

/**
 * To init the derby db.
 * 
 * @author Lorenzo Notarfonzo
 *
 */
public class DerbyUtility {
	
	private static String CLASS_NAME = DerbyUtility.class.getName();
	private static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CLASS_NAME);
	
	public static final String PJ_HOME = TestHome.getPJ_HOME();
	private static final String CREATE_DERBY_DB = PJ_HOME+"/derby_scripts/createDb.sql";
	
	private static String DATABASE_NAME = "HOMEPAGE";
	
	// We create the Derby db just one time in memory
	private static boolean IS_DERBY_DB_CREATED = false;

//	private static final String INIT_DATA = PJ_HOME+"/derby_scripts/init.sql";
//	private static final String TEMP_ADD_TABLES= PJ_HOME+"/derby_scripts/tempAddTables.sql";
//	private static String DATABASE_HOME = null;

	/**
	 * 
	 */
	public static void initDerbyDb() {
		if (logger.isLoggable(java.util.logging.Level.FINER))
			logger.entering(CLASS_NAME, "initDerbyDb");
		
		DerbyUtility.setDerbySystemHome();
//		DATABASE_HOME = DBUtility.getDerbySystemHome();
		System.out.println("DATABASE CREATES -> IS_DERBY_DB_CREATED "+IS_DERBY_DB_CREATED);
		
		if (IS_DERBY_DB_CREATED == false) {
			createDerbyDb();
			IS_DERBY_DB_CREATED = true;
		}
		
		if (logger.isLoggable(java.util.logging.Level.FINER)) {
			logger.exiting(CLASS_NAME, "initDerbyDb");
		}
	}

	/**
	 * 
	 */
	private static void createDerbyDb() {
		
		String url = "jdbc:derby:memory:" + DATABASE_NAME + ";create=true;user=homepage";
		String driver =  "org.apache.derby.jdbc.EmbeddedDriver";
		Connection conn = null;
		try {

			Class.forName(driver);
            conn = DriverManager.getConnection(url);              

			// Create tables based on script
			System.out.println("Creating db...");
			
			FileInputStream createScript = new FileInputStream(CREATE_DERBY_DB);
			ij.runScript(conn, createScript, "UTF-8", System.out,"UTF-8");
			
			System.out.println("Db created");

			conn.commit();
			conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (conn!=null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 
	 */
	public static void setDerbySystemHome() {
		String testDerbyDbFileLocation = System.getProperty(DERBY_DB_DIR_LOCATION_SYSTEM_PROPERTY_NAME);
		String derbySysHome = System.getProperty(DERBY_SYSTEM_HOME_SYSTEM_PROPERTY_NAME);

		if(testDerbyDbFileLocation == null && derbySysHome == null) {		
			String javaIOTmpDir = System.getProperty(JAVA_IO_TMPDIR_SYSTEM_PROPERTY_NAME);			 
			if(javaIOTmpDir!=null) {
				File f1 = new File(javaIOTmpDir);				
				String newsDerbyDir = javaIOTmpDir + "NewsDerbyDir/";
				try {
					newsDerbyDir = f1.getCanonicalPath() + File.separator + "NewsDerbyDir/";
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				File f2 = new File(newsDerbyDir);
				if(f1.exists() && f1.canWrite()) {
					boolean newsDerbyDirCreated = f2.mkdir();
					if(f2.exists() || newsDerbyDirCreated) {
						 System.setProperty(DERBY_SYSTEM_HOME_SYSTEM_PROPERTY_NAME,newsDerbyDir);					
					}
				}	
			}
		} else if(testDerbyDbFileLocation!=null) {
			System.setProperty(DERBY_SYSTEM_HOME_SYSTEM_PROPERTY_NAME,testDerbyDbFileLocation);
		}
	}
	
	/** UNUSED CODE **/
/*	
	public static void destryDerbyDb() {
		shutDownDatabase();
	}

	private static boolean checkIfDatabaseExists() {
		boolean dbExists = false;
		List<File> pathsToDatabase = searchDatabasePath();
		for (File pathToDatabase: pathsToDatabase) {
			if (pathToDatabase.exists()) {
				dbExists = true;
			}
		}
		
		return dbExists;
	}
	
	private static void checkAndDropDatabase() {
		//System.out.println("checkAndDropDatabase");
		List<File> pathDatabaseToDeletes = searchDatabasePath();
		for (File pathDatabaseToDelete: pathDatabaseToDeletes) {
			System.out.println("checkAndDropDatabase - pathDatabaseToDelete: "+pathDatabaseToDelete.getAbsolutePath());
			System.out.println("checkAndDropDatabase - pathDatabaseToDelete exists: "+pathDatabaseToDelete.exists());			
			if (pathDatabaseToDelete.exists()) {
				System.out.println("Shutdown db....");
				shutDownDatabase();				
				System.out.println("checkAndDropDatabase - delete database at: "+pathDatabaseToDelete);
				deleteDirectory(pathDatabaseToDelete);
			}
		}
	}
	
	private static List<File> searchDatabasePath() {
		//System.out.println("searchDatabasePath");
		File start = new File(DATABASE_HOME);
		ArrayList<File> results = new ArrayList<File>();
		searchDatabasePath(start,results);
		return results;
	}
	
	private static void searchDatabasePath(File start, List<File> results) {
		//System.out.println("start"+start);
		if (start.isDirectory() && start.getName().equals(DATABASE_NAME))
			results.add(start);
		if (start.isDirectory()) {
			File[] files = start.listFiles();
			if (files.length>0) {
				for (File file: files) {
					if (file.isDirectory()) {
						searchDatabasePath(file,results);
					}
				}
			}
		}			
	}
	
	private static boolean deleteDirectory(File path) {
		//System.out.println("deleteDirectory");
		if (path.exists()) {
			File[] files = path.listFiles();
			for(File file: files) {
				if(file.isDirectory())
					deleteDirectory(file);
				else
					file.delete();
			}
	    }
		System.out.println("Delete dir: "+path.getAbsolutePath());
	    return (path.delete());
	}
	
	private static void shutDownDatabase() {
		try {
			// Before to drop any database we need to be sure that the database is shoutdown
			// ERROR 08006 is not an error: it confirms a successful shutdown. exit terminates the IJ application.
			DriverManager.getConnection("jdbc:derby:"+DATABASE_NAME+";shutdown=true");
			//DriverManager.getConnection("jdbc:derby:;shutdown=true");
		} catch (Exception e) {
			//e2.printStackTrace();
		}
	}

	public static  String getDerbySystemHome() {
		return System.getProperty(DERBY_SYSTEM_HOME_SYSTEM_PROPERTY_NAME);
	}
*/
}
