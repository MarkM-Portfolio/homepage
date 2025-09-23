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

package com.ibm.lconn.homepage.sql.utilities.orgid;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.sql.utilities.UtilitySQL;
import com.ibm.lconn.homepage.sql.utilities.beans.Column;
import com.ibm.lconn.homepage.sql.utilities.beans.Table;

public abstract class AbstractSetNotNullOrgId  implements ISetNotNullOrgId {

	private static String CLASS_NAME = AbstractSetNotNullOrgId.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	public static String FILE_NAME = "setNotNullOrgId.sql";
	public static String DEF_ORG_ID = "00000000-0000-0000-0000-000000000000";
	
	public String getFilename() {
		return FILE_NAME;
	}
	
	public StringBuffer generateScript(Connection conn) throws Exception {
		StringBuffer sb = new StringBuffer();
				
		//UtilitySQL.startScript(sb,getDbType());
		
		sb.append("\n-- DROPPING FK\n");
		dropOrgFK(conn, sb);
		//System.out.println(sb);
		
		sb.append("\n-- DROPPING INDEXES\n");
		dropExistingOrgIndexes(conn, sb);
		//System.out.println(sb);
		
		sb.append("\n-- UPDATE NOT NULL ORGANIZATION_ID\n");
		setNotNullOrgId(conn, sb);
		//System.out.println(sb);
		
		sb.append("\n-- RESTORING FK\n");
		createOrgFK(conn, sb);
		//System.out.println(sb);
		
		sb.append("\n-- RESTORING INDEXES\n");
		restoringExistingOrgIndexes(conn, sb);
		//System.out.println(sb);
		
		//UtilitySQL.endScript(sb,getDbType());
		
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
	
	public abstract List<String> dropOrgFK(Connection conn, StringBuffer sb) throws Exception;
	
	public abstract List<String> createOrgFK(Connection conn, StringBuffer sb) throws Exception;
	
	public abstract List<String> dropExistingOrgIndexes(Connection conn, StringBuffer sb) throws Exception;
	
	public abstract ArrayList<String> restoringExistingOrgIndexes(Connection conn, StringBuffer sb) throws Exception;
	
	public abstract List<String> setNotNullOrgId(Connection conn, StringBuffer sb) throws Exception;
	
	
}
