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


public class SetNotNullOrgIdOracle extends AbstractSetNotNullOrgId implements ISetNotNullOrgId {

	private static String CLASS_NAME = SetNotNullOrgIdOracle.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	@Override
	public String getDbType() {
		return IScriptGenerator.ORACLE;
	}
	
	public List<String> dropOrgFK(Connection conn, StringBuffer sb) throws Exception  {
		HashMap<String, String> fks = UtilitySQL.getOrgIdFK(conn);
		List<String> drops = new ArrayList<String>();
		

		for (Entry<String, String> entry : fks.entrySet()) {
			String table = entry.getKey();
			String fk = entry.getValue();
			String statment = "ALTER TABLE HOMEPAGE."+table+" DROP CONSTRAINT "+fk+";\nCOMMIT;\n";
			
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
			String statment = "ALTER TABLE HOMEPAGE."+table+" ADD CONSTRAINT "+fk+ " FOREIGN KEY (ORGANIZATION_ID) REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);\nCOMMIT;\n";
			
			sb.append(statment);
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
			statment += ") TABLESPACE  NEWSINDEXTABSPACE;\nCOMMIT;\n";

			sb.append(statment);
		    creates.add(statment);
		    
		}

		return creates;
	}

	
//	CREATE VIEW HOMEPAGE.DISCOVERY AS (
//			SELECT 	NR_NEWS_DISCOVERY.NEWS_RECORDS_ID 			CATEGORY_READER_ID,
//				'00000000-0000-0000-0000-000000000001'			READER_ID,
//				17												CATEGORY_TYPE,
//				NR_NEWS_DISCOVERY.SOURCE						SOURCE,
//				NR_NEWS_DISCOVERY.CONTAINER_ID					CONTAINER_ID,
//				NR_NEWS_DISCOVERY.ITEM_ID						ITEM_ID,
//				' '												ROLLUP_ENTRY_ID,
//				0												RESOURCE_TYPE,
//				NR_NEWS_DISCOVERY.CREATION_DATE					CREATION_DATE,
//				NR_NEWS_DISCOVERY.NEWS_STORY_ID					STORY_ID,
//				NR_NEWS_DISCOVERY.SOURCE_TYPE					SOURCE_TYPE,
//				0												USE_IN_ROLLUP,
//				0												IS_NETWORK,
//				0												IS_FOLLOWER,
//				NR_NEWS_DISCOVERY.CREATION_DATE					EVENT_TIME,
//				NR_NEWS_DISCOVERY.IS_COMMUNITY_STORY			IS_STORY_COMM,
//				0												IS_BROADCAST,
//				' '												ORGANIZTION_ID,
//				' '												ACTOR_UUID,
//				' '												ROLLUP_AUTHOR_ID,
//				1												IS_VISIBLE				
//			FROM 	HOMEPAGE.NR_NEWS_DISCOVERY NR_NEWS_DISCOVERY, 
//				HOMEPAGE.NR_STORIES STORIES
//			WHERE 	NR_NEWS_DISCOVERY.NEWS_STORY_ID =  STORIES.STORY_ID
//		);
//
//		COMMIT;
//
//
//		DECLARE  CURSOR s_cur IS 
//		SELECT * FROM HOMEPAGE.DISCOVERY;	
//
//		TYPE fetch_array IS TABLE OF s_cur%ROWTYPE;
//		s_array fetch_array;
//		BEGIN
//		  	OPEN s_cur;
//		  	LOOP
//		    	FETCH s_cur BULK COLLECT INTO s_array LIMIT 1000;
//		    	FORALL i IN 1..s_array.COUNT
//		    	INSERT INTO HOMEPAGE.NR_DISCOVERY_VIEW 	VALUES s_array(i);
//				COMMIT;
//		    	
//				EXIT WHEN s_cur%NOTFOUND;
//		  	END LOOP;
//		  	CLOSE s_cur;
//		  	COMMIT;
//		END;
//		/
//
//		DROP VIEW HOMEPAGE.DISCOVERY;
//
//		COMMIT;
	@Override
	public List<String> setNotNullOrgId(Connection conn, StringBuffer sb)
			throws Exception {
	
		ArrayList<Table> tables = UtilitySQL.getTables(conn, true);
		List<String> updates = new ArrayList<String>();
		
		for (Table t: tables) {
			if (checkIfTableContainsOrganizationIdColumn(t)) {
				String tmpIndexName =  t.getTableName().substring(0,4)+"_TMP_ORG_IDX";
				
				StringBuffer sbTmp = new StringBuffer();
				
				// Create an index
				if (!t.getTableName().equals("MT_ORGANIZATION")) {
					sbTmp.append("\n CREATE INDEX HOMEPAGE."+tmpIndexName+"  ON HOMEPAGE."+t.getTableName()+" (ORGANIZATION_ID) TABLESPACE NEWSINDEXTABSPACE;\n COMMIT;\n");
				}
				
				sbTmp.append("\n DECLARE CURSOR cur IS SELECT "+t.getPk()+" PK FROM HOMEPAGE."+t.getTableName()+" WHERE ORGANIZATION_ID IS NULL FOR UPDATE; "); 
				sbTmp.append("\n "); 
				sbTmp.append("\n "); 
				sbTmp.append("\n                BEGIN "); 
				sbTmp.append("\n                        FOR rec IN cur LOOP "); 
				sbTmp.append("\n                        	UPDATE HOMEPAGE."+t.getTableName()+" SET ORGANIZATION_ID = '00000000-0000-0000-0000-000000000000' WHERE CURRENT OF cur; "); 
				sbTmp.append("\n                        END LOOP; ");
				sbTmp.append("\n                        COMMIT; "); 
				sbTmp.append("\n                END; "); 
				sbTmp.append("\n / ");
				
				if (!t.getTableName().equals("MT_ORGANIZATION")) {
					sbTmp.append("\n DROP INDEX HOMEPAGE."+tmpIndexName+";\n COMMIT;\n");
				}
				
				String sql = sbTmp.toString();
				sql += "\nCOMMIT;\n";
				
				
				
				sb.append(sql);
				updates.add(sql);
				
			}
		}
		
		//System.out.println(updates);
		return updates;
	}



	

}
