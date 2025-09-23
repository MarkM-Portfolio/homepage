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

package com.ibm.lconn.homepage.sql.utilities.script;

import java.sql.Connection;

public interface IScriptGenerator {

	public static String DB2 = "DB2";
	public static String ORACLE = "ORACLE";
	public static String SQLSERVER = "SQLSERVER";
	
	public StringBuffer generateScript(Connection conn) throws Exception;
	
	public String getDbType();
	
	public String getFilename();
	
}
