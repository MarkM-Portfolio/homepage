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

import static java.util.logging.Level.FINER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.sql.utilities.beans.Column;
import com.ibm.lconn.homepage.sql.utilities.beans.ForeignKey;
import com.ibm.lconn.homepage.sql.utilities.beans.Index;
import com.ibm.lconn.homepage.sql.utilities.beans.Table;
import com.ibm.lconn.homepage.sql.utilities.script.IScriptGenerator;

public class UtilitySQL {

	private static Properties properties = null;
	private static String DBNAME = null;
	private static String SCHEMA = null;
	
	private static String CLASS_NAME = UtilitySQL.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	public static Connection getConnection(Properties propJDBC) throws Exception {
		
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getConnection", propJDBC);
		
		properties = propJDBC;
		DBNAME = properties.getProperty("DBNAME");
		SCHEMA = properties.getProperty("SCHEMA");
		
		String host = "";
		String uName = "";
		String uPass = "";
		String driver = "";
		
		Connection conn = null;
		
		
		try {

            //get sql credentials from properties
             driver = propJDBC.getProperty("driver");
             host = propJDBC.getProperty("host");
             uName = propJDBC.getProperty("user");
             uPass = propJDBC.getProperty("password");

		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
		//load drivers
		try {
		    Class.forName(driver);
		} catch(ClassNotFoundException e) {
		    System.err.println("Error loading driver: " + e);
		}
			
		//connect to database and execute query
		try {
			conn = DriverManager.getConnection(host, uName, uPass);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getConnection", conn);
		
		//set the results in the script properties file 
		return conn;
	}
	
	
	public static List<String> getAllNameTablesViewInOrder(Connection conn) throws Exception  {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getAllNameTablesViewInOrder", conn);
		
		List<String> tables = new ArrayList<String>();
		
		String query = "select NAME from sysibm.systables where CREATOR = '"+DBNAME+"' AND (TYPE = 'T' OR TYPE = 'V') ORDER BY CTIME ASC ";
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			tables.add(DBNAME+"." + rs.getString(1));
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getAllNameTablesViewInOrder", tables);
		
		return tables;
	}
	
	public static List<String> getAllNameTablesInOrder(Connection conn) throws Exception {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getAllNameTablesInOrder", conn);
		
		List<String> tables = new ArrayList<String>();
		
		String query = "select NAME from sysibm.systables where CREATOR = '"+DBNAME+"' AND TYPE = 'T' ORDER BY CTIME ASC ";
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			tables.add(DBNAME+"." + rs.getString(1));
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getAllNameTablesInOrder", tables);
		
		return tables;
	}
	
	public static List<String> getAllNameTablesInReverse(Connection conn) throws Exception {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getAllNameTablesInReverse", conn);
		
		List<String> tables = new ArrayList<String>();
		
		String query = "select NAME from sysibm.systables where CREATOR = '"+DBNAME+"' AND TYPE = 'T' ORDER BY CTIME DESC ";
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			tables.add(DBNAME+"." + rs.getString(1));
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getAllNameTablesInReverse", tables);
		
		return tables;
	}
	
	public static HashMap<String,String> getOrgIdFK(Connection conn) throws Exception {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getOrgIdFK", conn);
		
		HashMap<String,String> fks = new HashMap<String,String>();
		
		String query = "SELECT TABNAME, CONSTNAME  FROM SYSCAT.REFERENCES WHERE REFKEYNAME = 'PK_ORGANIZATION_ID'";
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			fks.put(rs.getString(1),rs.getString(2));
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getOrgIdFK", fks);
		
		return fks;
	}
	
	/**
	 * Key of map: indexName
	 * Value of map: tableName
	 * 
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String,String> getIndexesToBeCompressed(Connection conn) throws Exception {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getIndexesToBeCompressed", conn);
		
		
		
		String query = "SELECT INDNAME, TABNAME FROM syscat.INDEXES WHERE INDSCHEMA = '"+SCHEMA+"' order by create_time asc";
		HashMap<String,String> indexes = new HashMap<String,String>();
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			indexes.put(rs.getString(1),rs.getString(2));
		}		
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getIndexesToBeCompressed", indexes);
		
		return indexes;
	}
	
	public static List<String> getTablesToBeCompressed(Connection conn) throws Exception {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getTablesToBeCompressed", conn);
		
		String query = "SELECT TABNAME from syscat.TABLES WHERE TABSCHEMA = '"+SCHEMA+"' AND TYPE = 'T'";
		List<String> tables = new ArrayList<String>();
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			tables.add(rs.getString(1));
		}		
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getTablesToBeCompressed", tables);
		
		return tables;
	}
	
	
	public static ArrayList<ForeignKey> getFK(Connection conn) throws Exception {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getFK", conn);
		
		ArrayList<ForeignKey> foreignKeys = new ArrayList<ForeignKey>();
		
		String query = "SELECT TABNAME, CONSTNAME FROM SYSCAT.REFERENCES WHERE tabschema = '"+DBNAME+"'";
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			ForeignKey foreignKey = new ForeignKey();
			foreignKey.setTabName(rs.getString(1));
			foreignKey.setConstName(rs.getString(2));			
			
			foreignKeys.add(foreignKey);
			
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getFK", foreignKeys);
		
		return foreignKeys;
		
		
	}
	/*public static HashMap<String,String> getFK(Connection conn) throws Exception {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getFK", conn);
		
		HashMap<String,String> fks = new HashMap<String,String>();
		
		String query = "SELECT TABNAME, CONSTNAME  FROM SYSCAT.REFERENCES WHERE tabschema = 'HOMEPAGE'";
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			fks.put(rs.getString(1),rs.getString(2));
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getFK", fks);
		
		return fks;
	}*/
	
	public static ArrayList<ForeignKey> getFKColNames(Connection conn) throws Exception {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getFKColNames", conn);
		
		ArrayList<ForeignKey> foreignKeys = new ArrayList<ForeignKey>();
		
		String query = "SELECT TABNAME, CONSTNAME, FK_COLNAMES, REFTABNAME, PK_COLNAMES, DELETERULE FROM SYSCAT.REFERENCES WHERE tabschema = '"+DBNAME+"'";
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			ForeignKey foreignKey = new ForeignKey();
			foreignKey.setTabName(rs.getString(1));
			foreignKey.setConstName(rs.getString(2));
			foreignKey.setRefTabName(rs.getString(4));
			foreignKey.setFKColName(rs.getString(3));
			foreignKey.setPrimKeyName(rs.getString(5));
			foreignKey.setDeleteRule(rs.getString(6));
			
			foreignKeys.add(foreignKey);
			
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getFKColNames", foreignKeys);
		
		return foreignKeys;
		
		
	}
	
	public static ArrayList<Index> getOrgIdIndexes(Connection conn) throws Exception {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getOrgIdIndexes", conn);
		
		ArrayList<Index> indexes = new ArrayList<Index>();
		
		String query = "select INDNAME, TABNAME, COLNAMES from syscat.indexes where TABSCHEMA = '"+DBNAME+"' and COLNAMES like '%ORGANIZATION%'";
		PreparedStatement pstmt = conn.prepareStatement(query);
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Index index = new Index();
			index.setIndexName(rs.getString(1));
			index.setTabName(rs.getString(2));
			String cl = rs.getString(3);
			String[] cols = cl.substring(1).split("\\+|\\-");
			ArrayList<String> colms = new ArrayList<String>();
			for (String c:cols) {				
				colms.add(c);
			}			
			index.setColumns(colms);
			
			if (!(index.getIndexName().equals("PK_ORGANIZATION_ID"))) {
				indexes.add(index);
			}
		}
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getOrgIdIndexes", indexes);
		
		return indexes;
	}
	



	
	
	public static void startScript(StringBuffer sb, String dbType) throws Exception  {
		
		sb.append("\n-- ***************************************************************** "); 
		sb.append("\n-- "); 
		sb.append("\n-- Licensed Materials - Property of IBM "); 
		sb.append("\n-- "); 
		sb.append("\n-- 5724_S68 "); 
		sb.append("\n-- "); 
		sb.append("\n-- Copyright IBM Corp. 2014 All Rights Reserved. "); 
		sb.append("\n-- "); 
		sb.append("\n-- US Government Users Restricted Rights - Use, duplication or "); 
		sb.append("\n-- disclosure restricted by GSA ADP Schedule Contract with "); 
		sb.append("\n-- IBM Corp. "); 
		sb.append("\n-- "); 
		sb.append("\n-- ***************************************************************** "); 
		
		
		sb.append("\n");
		
		if (dbType.equals(IScriptGenerator.DB2)) {
			String startScript = "\nCONNECT TO "+DBNAME+"@\n\n";
			sb.append(startScript);
		}
		if (dbType.equals(IScriptGenerator.ORACLE)) {
			String startScript = "\n";
			sb.append(startScript);
		}
		if (dbType.equals(IScriptGenerator.SQLSERVER)) {
			String startScript = "\nUSE "+DBNAME+";\nGO\nBEGIN TRANSACTION\nGO\n";
			sb.append(startScript);
		}
		
	}
	
	public static void endScript(StringBuffer sb, String dbType) throws Exception {
		if (dbType.equals(IScriptGenerator.DB2)) {
			String endScript = "\nCOMMIT@\nFLUSH PACKAGE CACHE DYNAMIC@\nconnect reset@\nterminate@\n";
			sb.append(endScript);
		}
		if (dbType.equals(IScriptGenerator.ORACLE)) {
			String endScript = "\nCOMMIT;\nDISCONNECT ALL;\nQUIT;\n";
			sb.append(endScript);
		}
		if (dbType.equals(IScriptGenerator.SQLSERVER)) {
			String endScript = "\nCOMMIT;\n";//\nGO\nCOMMIT\n";
			sb.append(endScript);
		}		
	}
	
	public static ArrayList<Table> getTables(Connection conn, boolean inOrder) throws Exception {
		ArrayList<Table> tables = new ArrayList<Table>(); 
		List<String> ts;
		if (inOrder == false){
			ts = getAllNameTablesInReverse(conn);
		}
		else {
			ts = getAllNameTablesInOrder(conn);
		}
		String sql = "select  C.COLUMN_NAME from sysibm.COLUMNS C, sysibm.SYSTABLES T where  T.CREATOR = '"+DBNAME+"' AND T.TYPE = 'T' AND C.TABLE_NAME = T.NAME AND C.TABLE_NAME = ? ORDER BY C.ORDINAL_POSITION";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		for (String t: ts) {
			Table table = new Table();
			table.setFullTableName(t);
			String[] tmp = table.getFullTableName().split("\\.");
			table.setTableName(tmp[1]);
			pstmt.setString(1, table.getTableName());
			ResultSet rs = pstmt.executeQuery();
			ArrayList<Column> cols = new ArrayList<Column>();
			int i=1;
			while (rs.next()) {
				Column col = new Column();
				col.setColumnName(rs.getString(1));
				col.setTableName(table.getTableName());
				col.setOrdinalPosition(i);
				if (i==1) {
					table.setPk(col.getColumnName());
				}
				cols.add(col);
				i++;
			}
			rs.close();
			table.setColumns(cols);
			tables.add(table);
		}
		pstmt.close();
		
		return tables;		
	}
	
	public static String selectCurrentTimeStampStatmentDB2(String tableName, int step) {
		return "SELECT '"+tableName+"---' || '"+step+"' AS STEP, CURRENT_TIMESTAMP NOW FROM "+DBNAME+"."+DBNAME+"_SCHEMA FETCH FIRST 1 ROWS ONLY";
	}
	
	public static String getDatabaseName() {
		return DBNAME;
	}
	
	public static String getSchema() {
		return SCHEMA;
	}
	
}
