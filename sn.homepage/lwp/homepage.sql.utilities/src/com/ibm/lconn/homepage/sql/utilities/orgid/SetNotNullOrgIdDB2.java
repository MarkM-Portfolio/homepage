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
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.sql.utilities.UtilitySQL;
import com.ibm.lconn.homepage.sql.utilities.beans.Column;
import com.ibm.lconn.homepage.sql.utilities.beans.Index;
import com.ibm.lconn.homepage.sql.utilities.beans.Table;
import com.ibm.lconn.homepage.sql.utilities.script.IScriptGenerator;


public class SetNotNullOrgIdDB2 extends AbstractSetNotNullOrgId implements ISetNotNullOrgId {

	private static String CLASS_NAME = SetNotNullOrgIdDB2.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	private static int step = 0;
	
	@Override
	public String getDbType() {
		return IScriptGenerator.DB2;
	}
	
//	CREATE VIEW HOMEPAGE.STORIES_CONTENT_VIEW AS (
//			SELECT
//			        STORY_CONTENT_ID, STORY_ID, CONTENT, CREATION_DATE, SOURCE_TYPE
//			FROM HOMEPAGE.NR_STORIES_CONTENT
//			);
//
//			COMMIT;
//
//			EXPORT TO data.tmp.ixf OF IXF METHOD N
//			        (
//			        STORY_CONTENT_ID, STORY_ID, CONTENT, CREATION_DATE, SOURCE_TYPE
//			        )
//
//			SELECT * FROM HOMEPAGE.STORIES_CONTENT_VIEW WITH UR;
//			COMMIT;
//
//			IMPORT FROM data.tmp.ixf OF IXF METHOD N
//			        (
//			        STORY_CONTENT_ID, STORY_ID, CONTENT, CREATION_DATE, SOURCE_TYPE
//			        )
//			ALLOW NO ACCESS COMMITCOUNT 2000
//			INSERT INTO HOMEPAGE.NR_STORIES_CONTENT_40
//			        (
//			        STORY_CONTENT_ID, STORY_ID, CONTENT, CREATION_DATE, SOURCE_TYPE
//			        );
//
//			COMMIT;

	
	public List<String> dropOrgFK(Connection conn, StringBuffer sb) throws Exception  {
		HashMap<String, String> fks = UtilitySQL.getOrgIdFK(conn);
		List<String> drops = new ArrayList<String>();
		

		for (Entry<String, String> entry : fks.entrySet()) {
			String table = entry.getKey();
			String fk = entry.getValue();
			String statment = "ALTER TABLE HOMEPAGE."+table+" DROP CONSTRAINT "+fk+"@\nCOMMIT@\n";
			
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
			String statment = "ALTER TABLE HOMEPAGE."+table+" ADD CONSTRAINT "+fk+ " FOREIGN KEY (ORGANIZATION_ID) REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID)@\nCOMMIT@\n";
			
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

			String statment = "DROP INDEX HOMEPAGE."+index.getIndexName()+"@\nCOMMIT@\n";
			
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
			statment += ")@\nCOMMIT@\n";

			sb.append(statment);
			//addTimeDebug(index.getTabName(),sb);
		    creates.add(statment);
		    
		}

		return creates;
	}

	
//	CREATE VIEW HOMEPAGE.STORIES_CONTENT_VIEW AS (
//	SELECT
//	        STORY_CONTENT_ID, STORY_ID, CONTENT, CREATION_DATE, SOURCE_TYPE
//	FROM HOMEPAGE.NR_STORIES_CONTENT
//	);
//
//	COMMIT;
//
//	EXPORT TO data.tmp.ixf OF IXF METHOD N
//	        (
//	        STORY_CONTENT_ID, STORY_ID, CONTENT, CREATION_DATE, SOURCE_TYPE
//	        )
//
//	SELECT * FROM HOMEPAGE.STORIES_CONTENT_VIEW WITH UR;
//	COMMIT;
//
//	IMPORT FROM data.tmp.ixf OF IXF METHOD N
//	        (
//	        STORY_CONTENT_ID, STORY_ID, CONTENT, CREATION_DATE, SOURCE_TYPE
//	        )
//	ALLOW NO ACCESS COMMITCOUNT 2000
//	INSERT INTO HOMEPAGE.NR_STORIES_CONTENT_40
//	        (
//	        STORY_CONTENT_ID, STORY_ID, CONTENT, CREATION_DATE, SOURCE_TYPE
//	        );
//
//	COMMIT;
	
	@Override
	public List<String> setNotNullOrgId(Connection conn, StringBuffer sb)
			throws Exception {
	
		ArrayList<Table> tables = UtilitySQL.getTables(conn, true);
		List<String> updates = new ArrayList<String>();
		
		for (Table t: tables) {
			if (checkIfTableContainsOrganizationIdColumn(t)) {
				String tmpIndexName = t.getTableName()+"_TMP_ORG_IDX";
				
				StringBuffer sbTmp = new StringBuffer();
				
				// Create an index
				// Create an index
				if (!t.getTableName().equals("MT_ORGANIZATION")) {
					sbTmp.append("\n CREATE INDEX HOMEPAGE."+tmpIndexName+"  ON HOMEPAGE."+t.getTableName()+" (ORGANIZATION_ID)@\n COMMIT@\n");
					sbTmp.append("\n RUNSTATS ON TABLE HOMEPAGE."+t.getTableName()+" FOR SAMPLED DETAILED INDEX HOMEPAGE."+tmpIndexName+"@\n COMMIT@\n");
				}
				
				sbTmp.append("\n	EXPORT TO data.tmp.ixf OF IXF METHOD N "); 
				sbTmp.append("\n	        ( "); 
				
				if (t.getTableName().equals("MT_ORGANIZATION"))
					sbTmp.append("\n	        "+t.getPk()+", ORGANIZATION_EXID");
				else
					sbTmp.append("\n	        "+t.getPk()+", ORGANIZATION_ID"); 
				
				sbTmp.append("\n	        ) "); 
				
				if (t.getTableName().equals("MT_ORGANIZATION")) {
					sbTmp.append("\n	SELECT "+t.getPk()+", '"+DEF_ORG_ID+"' ORGANIZATION_EXID FROM HOMEPAGE."+t.getTableName()+" WHERE ORGANIZATION_ID IS NULL WITH UR@ ");
				}
				else {
					sbTmp.append("\n	SELECT "+t.getPk()+", '"+DEF_ORG_ID+"' ORGANIZATION_ID FROM HOMEPAGE."+t.getTableName()+" WHERE ORGANIZATION_ID IS NULL WITH UR@ ");
				}
				
				sbTmp.append("\n "); 
				sbTmp.append("\n	COMMIT@ "); 
				sbTmp.append("\n "); 
				//addTimeDebug(t.getTableName(),sb);
				
				//Drop index
				if (!t.getTableName().equals("MT_ORGANIZATION")) {
					sbTmp.append("\n DROP INDEX HOMEPAGE."+tmpIndexName+"@\n COMMIT@\n");
				}
					
				sbTmp.append("\n	IMPORT FROM data.tmp.ixf OF IXF METHOD N "); 
				sbTmp.append("\n	        ( "); 
				
				if (t.getTableName().equals("MT_ORGANIZATION")) {
					sbTmp.append("\n	        "+t.getPk()+", ORGANIZATION_EXID");
				}
				else {
					sbTmp.append("\n	        "+t.getPk()+", ORGANIZATION_ID"); 
				}
					
				sbTmp.append("\n	        ) "); 
				sbTmp.append("\n	ALLOW NO ACCESS COMMITCOUNT 2000 "); 
				sbTmp.append("\n	INSERT_UPDATE INTO HOMEPAGE."+t.getTableName()+" "); 
				sbTmp.append("\n	        ( "); 
				
				if (t.getTableName().equals("MT_ORGANIZATION")) {
					sbTmp.append("\n	        "+t.getPk()+", ORGANIZATION_EXID");
				}
				else {
					sbTmp.append("\n	        "+t.getPk()+", ORGANIZATION_ID"); 
				}
				
				
				sbTmp.append("\n	        )@ ");
				sbTmp.append("\n	COMMIT@ ");
				sbTmp.append("\n "); 
				//addTimeDebug(t.getTableName(),sb);
				
				
				String sql = sbTmp.toString();
				sql += "\nCOMMIT@\n";
				
				sb.append(sql);
				updates.add(sql);
				
			}
		}
		
		//System.out.println(updates);
		return updates;
	}


	private static void addTimeDebug(String table, StringBuffer sb) {
		sb.append(UtilitySQL.selectCurrentTimeStampStatmentDB2(table,step++)+";\n\n");
	}

	

}
