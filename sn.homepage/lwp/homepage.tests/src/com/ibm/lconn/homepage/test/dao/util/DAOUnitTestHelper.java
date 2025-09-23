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
package com.ibm.lconn.homepage.test.dao.util;


import java.io.File;
import java.io.IOException;

import static com.ibm.lconn.homepage.test.data.util.DAOUnitTestConstants.*;

public class DAOUnitTestHelper {
	
	public static void setDerbySystemHome() {
		String testDerbyDbFileLocation  =  System.getProperty(DERBY_DB_DIR_LOCATION_SYSTEM_PROPERTY_NAME);
		String derbySysHome  			=  System.getProperty(DERBY_SYSTEM_HOME_SYSTEM_PROPERTY_NAME);
		//
		if(testDerbyDbFileLocation ==null && derbySysHome==null){		
			String javaIOTmpDir  			=  System.getProperty(JAVA_IO_TMPDIR_SYSTEM_PROPERTY_NAME);			 
			if(javaIOTmpDir!=null){
				File f1 = new File(javaIOTmpDir);				
				String homepageDerbyDir = javaIOTmpDir + "HomepageDerbyDir_30/";
				try {
					homepageDerbyDir = f1.getCanonicalPath() + File.separator + "HomepageDerbyDir_30/";
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(-1);
				}
				File f2 = new File(homepageDerbyDir);
				if(f1.exists() && f1.canWrite()) {
					boolean homepageDerbyDirCreated = f2.mkdir();
					if(f2.exists() || homepageDerbyDirCreated){
						 System.setProperty(DERBY_SYSTEM_HOME_SYSTEM_PROPERTY_NAME,homepageDerbyDir);					
					}
				}	
			}
		} else if(testDerbyDbFileLocation!=null) {
			//
			System.setProperty(DERBY_SYSTEM_HOME_SYSTEM_PROPERTY_NAME,testDerbyDbFileLocation);
		}
	}	

	public static  String getDerbySystemHome() {
		return System.getProperty(DERBY_SYSTEM_HOME_SYSTEM_PROPERTY_NAME);
	}
	
}
