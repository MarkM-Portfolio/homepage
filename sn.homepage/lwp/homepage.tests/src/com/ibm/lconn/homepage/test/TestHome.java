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

package com.ibm.lconn.homepage.test;

import static com.ibm.lconn.homepage.test.data.util.DAOUnitTestConstants.LOGGING_CONFIG_FILE_NAME;

import java.util.Date;

import com.ibm.lconn.homepage.test.data.util.DAOUnitTestConstants;

/**
 * 
 * To exteranalize the PJ_HOME
 * 
 * @author lorenzo
 *
 */
public class TestHome {

	// This is the default pj_home used if it is not provided any sort of sys.prop
	
	// The PJ_HOME usually store the path of the config folder like: connections-config, db2Scripts, derby_scripts
	public static String PJ_HOME = null;
	
	// DB Vendor information
	public static enum DbVendor
	{
		DERBY("derby"),
		ORACLE("oracle"), 
		DB2("db2"),
		SQLSERVER("sqlserver"); 

		private final String name;

		private DbVendor(String name) {
			this.name = name;
		}
		
		public final String toString() {
			return this.name;
		}
		
		public static final DbVendor fromString(String name) {
	    	for(DbVendor vendor : DbVendor.values()) {
	    		if (name.equalsIgnoreCase(vendor.toString())) {
	    			return vendor;
	    		}
	    	}
	    	return DbVendor.DERBY;
		}
	}
	private static DbVendor DB_VENDOR = DbVendor.DERBY;		// Defaults to Derby

	
	public static final String getPJ_HOME() {
		
		if (PJ_HOME==null) {
		
			String pjHome = System.getProperty("test.project.home");
			
			if (pjHome!=null && pjHome.length()>0)
				PJ_HOME = pjHome;
			else
				PJ_HOME = "../homepage.tests";
		}
		
		
		return PJ_HOME;
	}
	
	public static final String getTestConfigFilesDir(){
		return getPJ_HOME()+"/connections-config";
	}
	
	/**
	 * 
	 * @return the current Db Vendor
	 */
	public static DbVendor getDbVendor() {
		String dbVendor = System.getProperty(DAOUnitTestConstants.DB_VENDOR_NAME);
		if (dbVendor != null) {
			DB_VENDOR = TestHome.DbVendor.fromString(dbVendor);
		}
		return DB_VENDOR;
	}
	
	/**
	 * We set this value in case is null. Otherwise we use what is passed from the -D option
	 * 
	 */
	public static void initLogging() {
		String loggingFile = System.getProperty(LOGGING_CONFIG_FILE_NAME);
		if (loggingFile == null) {
			System.setProperty(LOGGING_CONFIG_FILE_NAME, TestHome.getTestConfigFilesDir()+"/logging.properties");
		}
	}

	public static final void displayMemoryUsageInMB(String testName){
		System.gc();
		long MB=1024*1024;
		StringBuilder memoryUsageText=new StringBuilder();
		memoryUsageText.append(new Date().toString());
		memoryUsageText.append("\tMemoryUsageInMB");
		memoryUsageText.append("\tMax: ");
		memoryUsageText.append(Runtime.getRuntime().maxMemory()/MB);
		memoryUsageText.append("\tTotal: ");
		memoryUsageText.append(Runtime.getRuntime().totalMemory()/MB);
		memoryUsageText.append("\tFree: ");
		memoryUsageText.append(Runtime.getRuntime().freeMemory()/MB);
		memoryUsageText.append("\tUsed: ");
		memoryUsageText.append((Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory())/MB);
		memoryUsageText.append("\tTest: ");
		memoryUsageText.append(testName);
		System.out.println(memoryUsageText.toString());
	}	
}
