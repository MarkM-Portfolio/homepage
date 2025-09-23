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

package com.ibm.lconn.homepage.sql.utilities.fixes;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.sql.utilities.UtilitySQL;
import com.ibm.lconn.homepage.sql.utilities.beans.Index;
import com.ibm.lconn.homepage.sql.utilities.beans.Table;
import com.ibm.lconn.homepage.sql.utilities.orgid.AbstractSetNotNullOrgId;
import com.ibm.lconn.homepage.sql.utilities.orgid.ISetNotNullOrgId;
import com.ibm.lconn.homepage.sql.utilities.script.IScriptGenerator;


public class FixWrongOrgIdPerfomanceTeamDB2 extends AbstractSetNotNullOrgId implements ISetNotNullOrgId {

	private static String CLASS_NAME = FixWrongOrgIdPerfomanceTeam.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	private static int step = 0;
	
	public static String FILE_NAME = "resetOrgId.sql";
	
	@Override
	public String getFilename() {
		return FILE_NAME;
	}
	
	@Override
	public String getDbType() {
		return IScriptGenerator.DB2;
	}
	
	public StringBuffer generateScript(Connection conn) throws Exception {
		StringBuffer sb = new StringBuffer();
				
		UtilitySQL.startScript(sb,getDbType());
		
		sb.append("\n-- INIT THE DATABASE\n");
		initDB(conn, sb);
		
		sb.append("\n-- DROPPING FK\n");
		dropOrgFK(conn, sb);
		//System.out.println(sb);
		
		sb.append("\n-- DROPPING INDEXES\n");
		dropExistingOrgIndexes(conn, sb);
		//System.out.println(sb);
		
		sb.append("\n-- FIX ORGANIZATION_ID\n");
		setNotNullOrgId(conn, sb);
		//System.out.println(sb);
		
		sb.append("\n-- RESTORING FK\n");
		createOrgFK(conn, sb);
		//System.out.println(sb);
		
		sb.append("\n-- RESTORING INDEXES\n");
		restoringExistingOrgIndexes(conn, sb);
		//System.out.println(sb);
		
		sb.append("\n-- CLEAN MT table\n");
		cleanMT(conn, sb);
		
		UtilitySQL.endScript(sb,getDbType());
		
		return sb;
	}

	
	public List<String> initDB(Connection conn, StringBuffer sb) throws Exception  {
		List<String> inits = new ArrayList<String>();
			
		
		sb.append("\n DROP INDEX HOMEPAGE.UNQ_ORG_EXID;\n COMMIT;\n");
		
		sb.append("\n INSERT INTO HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID, ORGANIZATION_EXID) "); 
		sb.append("\n ( "); 
		sb.append("\n	SELECT ORGANIZATION_EXID, ORGANIZATION_EXID "); 
		sb.append("\n	FROM HOMEPAGE.MT_ORG_S18_VERSION  MT_ORG_S18_VERSION "); 
		sb.append("\n	WHERE MT_ORG_S18_VERSION.ORGANIZATION_EXID NOT IN  (SELECT ORGANIZATION_ID FROM HOMEPAGE.MT_ORGANIZATION) "); 
		sb.append("\n ); ");
		sb.append("\n COMMIT;\n ");
		
		return inits;		
	}
	
	public List<String> cleanMT(Connection conn, StringBuffer sb) throws Exception  {
		List<String> inits = new ArrayList<String>();
			
		
		sb.append("\n DELETE FROM HOMEPAGE.MT_ORGANIZATION WHERE ORGANIZATION_ID <> ORGANIZATION_EXID;\n COMMIT;\n");
		

		sb.append("\n CREATE UNIQUE INDEX HOMEPAGE.UNQ_ORG_EXID "); 
		sb.append("\n	ON HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_EXID); ");
		sb.append("\n COMMIT;\n ");
		
		return inits;		
	}
	
	public List<String> dropOrgFK(Connection conn, StringBuffer sb) throws Exception  {
		HashMap<String, String> fks = UtilitySQL.getOrgIdFK(conn);
		List<String> drops = new ArrayList<String>();
		

		for (Entry<String, String> entry : fks.entrySet()) {
			String table = entry.getKey();
			String fk = entry.getValue();
			String statment = "ALTER TABLE HOMEPAGE."+table+" DROP CONSTRAINT "+fk+";\nCOMMIT;\n";
			
			sb.append(statment);
			//addTimeDebug(table,sb);
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
			String statment = "ALTER TABLE HOMEPAGE."+table+" ADD CONSTRAINT "+fk+ " FOREIGN KEY (ORGANIZATION_ID) REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);\nCOMMIT;\n";
			
			sb.append(statment);
			//addTimeDebug(table,sb);
		    creates.add(statment);
		    
		}
		
		return creates;
	}
	
	public List<String> dropExistingOrgIndexes(Connection conn, StringBuffer sb) throws Exception  {
		ArrayList<Index> indexes = UtilitySQL.getOrgIdIndexes(conn);
		List<String> drops = new ArrayList<String>();
		

		for (Index index: indexes) {

			String statment = "DROP INDEX HOMEPAGE."+index.getIndexName()+";\nCOMMIT;\n";
			
			sb.append(statment);
			//addTimeDebug(index.getTabName(),sb);
		    drops.add(statment);
		    
		}
		return drops;		
	}
	
	public ArrayList<String> restoringExistingOrgIndexes(Connection conn, StringBuffer sb) throws Exception  {
		ArrayList<Index> indexes = UtilitySQL.getOrgIdIndexes(conn);
		ArrayList<String> creates = new ArrayList<String>();
		

		for (Index index: indexes) {
			
			String statment = "CREATE INDEX HOMEPAGE."+index.getIndexName()+" ON HOMEPAGE."+index.getTabName()+" (";
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
			statment += ");\nCOMMIT;\n";

			sb.append(statment);
			//addTimeDebug(index.getTabName(),sb);
		    creates.add(statment);
		    
		}

		return creates;
	}

	
	
	@Override
	public List<String> setNotNullOrgId(Connection conn, StringBuffer sb)
			throws Exception {
	
		ArrayList<Table> tables = UtilitySQL.getTables(conn,true);
		List<String> updates = new ArrayList<String>();
		
		for (Table t: tables) {
			if (checkIfTableContainsOrganizationIdColumn(t)) {
				String tmpIndexName = t.getTableName()+"_TMP_ORG_IDX";
				
				StringBuffer sbTmp = new StringBuffer();
				
				// Create an index
				
				if (! (t.getTableName().equals("MT_ORGANIZATION") || t.getTableName().equals("MT_ORG_S18_VERSION"))) {
					sbTmp.append("\n CREATE INDEX HOMEPAGE."+tmpIndexName+"  ON HOMEPAGE."+t.getTableName()+" (ORGANIZATION_ID);\n COMMIT;\n");
					sbTmp.append("\n RUNSTATS ON TABLE HOMEPAGE."+t.getTableName()+" FOR SAMPLED DETAILED INDEX HOMEPAGE."+tmpIndexName+";\n COMMIT;\n");
				
				
			
					sbTmp.append("\n UPDATE HOMEPAGE."+t.getTableName()+" T1 "); 
					sbTmp.append("\n SET T1.ORGANIZATION_ID = "); 
					sbTmp.append("\n	( "); 
					sbTmp.append("\n	SELECT 	T2.ORGANIZATION_EXID "); 
					sbTmp.append("\n	FROM 	HOMEPAGE.MT_ORG_S18_VERSION AS T2 "); 
					sbTmp.append("\n	WHERE 	T1.ORGANIZATION_ID = T2.ORGANIZATION_ID "); 
					sbTmp.append("\n	) "); 
					sbTmp.append("\n WHERE EXISTS ( "); 
					sbTmp.append("\n	SELECT 1 "); 
					sbTmp.append("\n	FROM HOMEPAGE.MT_ORG_S18_VERSION AS T2 "); 
					sbTmp.append("\n	WHERE T1.ORGANIZATION_ID = T2.ORGANIZATION_ID "); 
					sbTmp.append("\n ); ");
					
					sbTmp.append("\n	COMMIT; "); 
					sbTmp.append("\n "); 
					
					
				
					sbTmp.append("\n DROP INDEX HOMEPAGE."+tmpIndexName+";\n COMMIT;\n");
					addTimeDebug(t.getTableName(),sb);
					
					String sql = sbTmp.toString();
					sql += "\nCOMMIT;\n";
					
					sb.append(sql);
					updates.add(sql);
				}
				
			}
		}
		
		//System.out.println(updates);
		return updates;
	}


	private static void addTimeDebug(String table, StringBuffer sb) {
		sb.append(UtilitySQL.selectCurrentTimeStampStatmentDB2(table,step++)+";\n\n");
	}



	

}
