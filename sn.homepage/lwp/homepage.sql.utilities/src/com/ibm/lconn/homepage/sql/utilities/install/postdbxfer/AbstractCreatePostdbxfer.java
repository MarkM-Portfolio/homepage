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

package com.ibm.lconn.homepage.sql.utilities.install.postdbxfer;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.sql.utilities.UtilitySQL;
import com.ibm.lconn.homepage.sql.utilities.beans.Column;
import com.ibm.lconn.homepage.sql.utilities.beans.Table;

public abstract class AbstractCreatePostdbxfer  implements ICreatePostdbxfer {

	private static String CLASS_NAME = AbstractCreatePostdbxfer.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	public static String FILE_NAME = "postdbxfer60.sql";
	public static String DEF_ORG_ID = "00000000-0000-0000-0000-000000000000";
	
	public String getFilename() {
		return FILE_NAME;
	}
	
	public StringBuffer generateScript(Connection conn) throws Exception {
		StringBuffer sb = new StringBuffer();
				
		UtilitySQL.startScript(sb,getDbType());
		
		sb.append("\n-- CREATING FKs\n");
		createFK(conn, sb);
		//System.out.println(sb);
		
		UtilitySQL.endScript(sb,getDbType());
		
		return sb;
	}
	
	public static boolean checkIfTableContainsOrganizationIdColumn(Table t) {
		ArrayList<Column> cols = t.getColumns();
		for (Column c: cols) {
			if (c.getColumnName().equals("ORGANIZATION_ID"))
				return true;
		}
		return false;
	}
		
	public abstract List<String> createFK(Connection conn, StringBuffer sb) throws Exception;
	
}
