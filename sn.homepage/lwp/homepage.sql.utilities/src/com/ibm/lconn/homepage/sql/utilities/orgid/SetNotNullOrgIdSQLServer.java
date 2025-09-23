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
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.sql.utilities.UtilitySQL;
import com.ibm.lconn.homepage.sql.utilities.beans.Index;
import com.ibm.lconn.homepage.sql.utilities.beans.Table;
import com.ibm.lconn.homepage.sql.utilities.script.IScriptGenerator;


public class SetNotNullOrgIdSQLServer extends AbstractSetNotNullOrgId implements ISetNotNullOrgId {

	private static String CLASS_NAME = SetNotNullOrgIdSQLServer.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	private static int step = 0;
	
	@Override
	public String getDbType() {
		return IScriptGenerator.SQLSERVER;
	}
	
	public List<String> dropOrgFK(Connection conn, StringBuffer sb) throws Exception  {
		HashMap<String, String> fks = UtilitySQL.getOrgIdFK(conn);
		List<String> drops = new ArrayList<String>();
		
		
		for (Entry<String, String> entry : fks.entrySet()) {
			String table = entry.getKey();
			String fk = entry.getValue();
			String statment = "ALTER TABLE HOMEPAGE."+table+" DROP CONSTRAINT "+fk+";\nGO\n";
			
			sb.append(statment);
		    drops.add(statment);
		    
		}
		return drops;		
	}
	
	public List<String> createOrgFK(Connection conn, StringBuffer sb) throws Exception  {
		HashMap<String, String> fks = UtilitySQL.getOrgIdFK(conn);
		List<String> creates = new ArrayList<String>();
		
	
		for (Entry<String, String> entry : fks.entrySet()) {
			String table = entry.getKey();
			String fk = entry.getValue();
			String statment = "ALTER TABLE HOMEPAGE."+table+" ADD CONSTRAINT "+fk+ " FOREIGN KEY (ORGANIZATION_ID) REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);\nGO\n";
			
			sb.append(statment);
		    creates.add(statment);
		    
		}
		
		return creates;
	}
	
	public List<String> dropExistingOrgIndexes(Connection conn, StringBuffer sb) throws Exception  {
		ArrayList<Index> indexes = UtilitySQL.getOrgIdIndexes(conn);
		List<String> drops = new ArrayList<String>();
		

		for (Index index: indexes) {

			String statment = "DROP INDEX "+index.getIndexName()+" ON HOMEPAGE." + index.getTabName() + ";\nGO\n";
			
			sb.append(statment);
		    drops.add(statment);
		    
		}
		return drops;		
	}
	
	public ArrayList<String> restoringExistingOrgIndexes(Connection conn, StringBuffer sb) throws Exception  {
		ArrayList<Index> indexes = UtilitySQL.getOrgIdIndexes(conn);
		ArrayList<String> creates = new ArrayList<String>();
		
	
		for (Index index: indexes) {
			
			String statment = "CREATE INDEX "+index.getIndexName()+" ON HOMEPAGE."+index.getTabName()+" (";
			ArrayList<String> cols  = index.getColumns();
			for (String c: index.getColumns()) {
				if (c.equals("CREATION_DATE")) {
					statment +=c+" DESC, ";
				}
				else {
					statment +=c+", ";
				}
			}
			statment = statment.substring(0, statment.length()-2);
			statment += ");\nGO\n";

			sb.append(statment);
		    creates.add(statment);
		    
		}

		return creates; 
	}
	
	/*@Override
	public List<String> setNotNullOrgId(Connection conn, StringBuffer sb)
			throws Exception {
	
		ArrayList<Table> tables = UtilitySQL.getTables(conn);
		List<String> updates = new ArrayList<String>();
		
		for (Table t: tables) {
			if (checkIfTableContainsOrganizationIdColumn(t)) {
				String sql = "UPDATE HOMEPAGE."+t.getFullTableName()+" SET ORGANIZATION_ID = '"+DEF_ORG_ID+"' WHERE ORGANIZATION_ID IS NULL;";
				sql += "\nCOMMIT;\n";
				updates.add(sql);
			}
		}
		
		return updates;
	}*/
	
	
	@Override
	public List<String> setNotNullOrgId(Connection conn, StringBuffer sb)
			throws Exception {
	
		ArrayList<Table> tables = UtilitySQL.getTables(conn, true);
		List<String> updates = new ArrayList<String>();
		
		StringBuffer sbTmp = new StringBuffer();
		
		sbTmp.append("\nCOMMIT;");
		sbTmp.append("\nGO");
		sbTmp.append("\n");
		//sbTmp.append("\nUSE HOMEPAGE;");
		//sbTmp.append("\nGO");		
		//sbTmp.append("\nBEGIN TRANSACTION");
		//sbTmp.append("\nGO");
		//sbTmp.append("\n");
		//sbTmp.append("\nif object_id('tempdb..#USEROPTIONS') > 0");
		//sbTmp.append("\n	drop table #USEROPTIONS");
		//sbTmp.append("\nCREATE TABLE #USEROPTIONS(SetOption varchar(100), Value varchar(100))");
		//sbTmp.append("\nDECLARE  @IsolationLevel varchar(100)");
		//sbTmp.append("\nINSERT    #USEROPTIONS");
		//sbTmp.append("\nEXEC('DBCC USEROPTIONS WITH NO_INFOMSGS')");
		//sbTmp.append("\n");
		//sbTmp.append("\nGO");
		//sbTmp.append("\nCOMMIT TRANSACTION;");
		//sbTmp.append("\nGO");
		//sbTmp.append("\n");
		//sbTmp.append("\nSET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;");
		//sbTmp.append("\n");
		
		for (Table t: tables) {
			if (checkIfTableContainsOrganizationIdColumn(t)) {
				sbTmp.append("\nBEGIN TRANSACTION");
				
				String tmpIndexName = t.getTableName()+"_TMP_ORG_IDX";
				
				if (!t.getTableName().equals("MT_ORGANIZATION")) {
					sbTmp.append("\n CREATE INDEX "+tmpIndexName+"  ON HOMEPAGE."+t.getTableName()+" (ORGANIZATION_ID);\n GO\n");
				}
				
				
				sbTmp.append("\nGO");
				sbTmp.append("\n");
				//sbTmp.append("\nWHILE (1 = 1)");
				//sbTmp.append("\nBEGIN");
				//sbTmp.append("\n					BEGIN TRANSACTION");
				sbTmp.append("\n					UPDATE HOMEPAGE."+t.getTableName());
				sbTmp.append("\n					SET    ORGANIZATION_ID = '00000000-0000-0000-0000-000000000000'");
				sbTmp.append("\n					WHERE  ORGANIZATION_ID IS NULL;");
				//sbTmp.append("\n					IF @@ROWCOUNT = 0 -- terminating condition;");
				//sbTmp.append("\n						BEGIN");
				//sbTmp.append("\n							COMMIT TRANSACTION");
				//sbTmp.append("\n							BREAK");
				//sbTmp.append("\n						END");
				//sbTmp.append("\n				    COMMIT TRANSACTION");
				//sbTmp.append("\n					WAITFOR DELAY '00:00:01';");
				//sbTmp.append("\nEND ");
				sbTmp.append("\nGO");
				
				
				if (!t.getTableName().equals("MT_ORGANIZATION")) {
					sbTmp.append("\n DROP INDEX "+tmpIndexName+" ON HOMEPAGE."+t.getTableName()+";\n GO\n");
				}
			
				sbTmp.append("\nCOMMIT;");
				sbTmp.append("\nGO");
				sbTmp.append("\n");
				
			}
		}
		
		//sbTmp.append("\nDECLARE  @IsolationLevel varchar(100)");
		//sbTmp.append("\nSELECT    @IsolationLevel = Value");
		//sbTmp.append("\nFROM      #USEROPTIONS");
		//sbTmp.append("\nWHERE     SetOption = 'isolation level'");
		//sbTmp.append("\n");
		//sbTmp.append("\nIF (@IsolationLevel = 'read uncommitted' OR @IsolationLevel = 'read uncommitted snapshot') BEGIN set transaction isolation level read uncommitted; END");
		//sbTmp.append("\nELSE IF (@IsolationLevel = 'read committed' OR @IsolationLevel = 'read committed snapshot') BEGIN set transaction isolation level read committed; END");
		//sbTmp.append("\nELSE IF @IsolationLevel = 'repeatable read' BEGIN set transaction isolation level repeatable read; END");
		//sbTmp.append("\nELSE IF @IsolationLevel = 'serializable' BEGIN set transaction isolation level serializable; END");
		//sbTmp.append("\nELSE IF @IsolationLevel = 'snapshot' BEGIN set transaction isolation level snapshot; END");
		
		sbTmp.append("\nBEGIN TRANSACTION");
		sbTmp.append("\nGO\n");
		sbTmp.append("\nGO\n");
		
		String sql = sbTmp.toString();
		sql += "\nGO\n";
	
		sb.append(sql);
		updates.add(sql);
		//System.out.println(updates);
		return updates;
	}



}
