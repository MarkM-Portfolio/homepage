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

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;


public interface ICreateScripts {
	
	  /**
     * Returns the directory name specified by the user in the properties file
     * @param propJDBC - properties file in which user specifies jdbc properties 
     * @return String containing directory name 
     */
	
	public String getDirectoryName(Properties propJDBC);
	
	  /**
     * Returns the file name for the script being generated
     * @param propJDBC - properties file in which user specifies jdbc properties 
     * @return String containing file name 
     */
	
	public String getFileName(Properties propJDBC);
	
	 /**
     * Splits up Object returned from JDBC query, generates lines for sql script, 
     * and calls method to add these to the file
     * @param propJDBC - properties file in which user specifies jdbc properties 
     * @throws IOException 
     */
    
    public void buildScript(Object returnedFromQuery, File file) throws IOException;
    
    /**
     * Calls methods to get files and directorys, createFile and buildScript
     * @param propJDBC - properties file in which user specifies jdbc properties 
     * @param ReturnedResult - the Object returned from sql query specified by user in properties file
     * @throws SQLException 
     * @throws IOException
     */
    
    public void create(Properties propJDBC, Object returnedResult) throws SQLException, IOException;
    	

}
