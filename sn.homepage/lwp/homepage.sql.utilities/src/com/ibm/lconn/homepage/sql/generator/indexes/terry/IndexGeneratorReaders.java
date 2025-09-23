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

package com.ibm.lconn.homepage.sql.generator.indexes.terry;

public class IndexGeneratorReaders {
	
	
	
	
	private static final String[] tables = {	"NR_AGGREGATED_READERS", 
												"NR_RESPONSES_READERS", 
												"NR_PROFILES_READERS", 
												"NR_COMMUNITIES_READERS", 
												"NR_ACTIVITIES_READERS", 
												"NR_BLOGS_READERS", 
												"NR_BOOKMARKS_READERS", 
												"NR_FILES_READERS", 
												"NR_FORUMS_READERS", 
												"NR_WIKIS_READERS", 
												"NR_TAGS_READERS",
												"NR_STATUS_UPDATE_READERS",
												"NR_EXTERNAL_READERS",												
												"NR_ACTIONABLE_READERS",
												"NR_DISCOVERY_VIEW",
												"NR_PROFILES_VIEW",
												"NR_NOTIFICATION_SENT_READERS",
												"NR_NOTIFICATION_RECEIV_READERS",
												"NR_SAVED_READERS",
												"NR_TOPICS_READERS",
												"NR_MENTIONS_READERS",
	
	
	};
	
	private static final String[] indexes_names = { 
		//"STR_IX", // 1
		//"ITM_IX", // 2
		//"CD_IX", // 3
		"ORG_CD_IX", // 4
		//"RLL_IX", //5		
		//"RDR_STR", //6
		//"DEL_SERV_IX", //7
		//"ROLLUP_IX", // 8
		//"RIR_IX", // 9
		//"RLL_BRD_VIS", // 10
		//"SR_IX"// 11 JUST SAVED_READERS
	};
	
	private static final String[] indexes = {
		//"(STORY_ID)", //1								 		
		//"(ITEM_ID)", //2
		//"(STORY_ID, CREATION_DATE DESC)", // 3
		"(ORGANIZATION_ID,  CREATION_DATE DESC)", //4
		//"(READER_ID, IS_VISIBLE, USE_IN_ROLLUP)", // 5	
		//"(READER_ID, STORY_ID)", // 6
		//"(CREATION_DATE DESC)", // 7
		//"(ROLLUP_ENTRY_ID, READER_ID)", //8
		//"(READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC)", // 9
		//"(ROLLUP_AUTHOR_ID, IS_BROADCAST, USE_IN_ROLLUP, IS_VISIBLE)", // 10
		//"(STORY_ID, READER_ID)" // 11 JUST SAVED_READERS
	};
	
	private static final String[] unique = {
		"", //1								 		
		"", //2
		"", // 3
		"", //4
		"", //5
		"", // 6
		"", // 7
		"", // 8
		"", //9 cluster
		"", // 10
		""
	};
	
	private static String db2Indexes_drop =  "DROP #unique# INDEX HOMEPAGE.#index_name#;";
	private static String db2Indexes =  "CREATE #unique# INDEX HOMEPAGE.#index_name# \n "+
    									"\tON HOMEPAGE.#table_name# #index_def#; ";
	
	private static String oraIndexes_drop =  "DROP #unique# INDEX HOMEPAGE.#index_name#;";
	private static String oraIndexes =  "CREATE #unique# INDEX HOMEPAGE.#index_name# \n "+
	"\tON HOMEPAGE.#table_name# #index_def# TABLESPACE  NEWSINDEXTABSPACE; ";
	
	private static String sqlIndexes_drop =  "DROP #unique# INDEX #index_name# \n "+
	"\tON HOMEPAGE.#table_name#;";
	private static String sqlIndexes =  "CREATE #unique# INDEX #index_name# \n "+
	"\tON HOMEPAGE.#table_name# #index_def#; ";

	
	
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Just one value can be true
		boolean create = false;
		
		boolean isDb2 = true;
		boolean isOracle = false;
		boolean isSQLServer = false;

		for (int i=0; i<tables.length; i++) {
			System.out.println("");
			System.out.println("--  [start indexes] "+tables[i]);
			for (int k=0; k<indexes.length; k++) {
				
				// CHANGE HERE TO SET IF YOU LIKE ORA OR DB2 OR SQLSERVER indexes
				
				String sql = "";
				
				// db2
				if (isDb2 && create ) {
					sql = db2Indexes;
				}
				if (isDb2 && !create ) {
					sql = db2Indexes_drop;
				}
				
				// ora
				if (isOracle && create) {
					sql = oraIndexes;
				}
				if (isOracle && !create) {
					sql = oraIndexes_drop;
				}
				
				// sqlserver
				if (isSQLServer && create) {
					sql = sqlIndexes;
				}
				if (isSQLServer && !create) {
					sql = sqlIndexes_drop
					;
				}
				
				
				String table = tables[i];
				table = table.substring(3);
				
				if (table.equals("NOTIFICATION_SENT_READERS")) {
					table = "NOTIFICA_READERS";
				}
				
				if (table.equals("STATUS_UPDATE_READERS")) {
					table = "STATUS_READERS";
				}
				
				if (table.equals("COMMUNITIES_READERS")) {
					table = "COMM_READERS";
				}
				
				if (table.equals("NOTIFICATION_RECEIV_READERS")) {
					table = "NOT_REC_READERS";
				}
				
				String indexName = table+"_"+indexes_names[k];
				
//				if (tables[i].contains("TAGS") && indexName.contains("UNQ")) {
//					indexName = indexName.replaceAll("UNQ", "");
//				}
				
				sql = sql.replace("#table_name#", tables[i]);
				sql = sql.replace("#index_name#", indexName);
				

				sql = sql.replace("#unique#", unique[k]);
				
				
				sql = sql.replace("#table_name#", tables[i]);
				sql = sql.replace("#index_def#", indexes[k]);
				
				// if the table is DISCOVERY_TABLE we remove the READER_ID from the index definition
				if (table.equals("DISCOVERY_VIEW")) {
					sql = sql.replace("READER_ID, ", "");
					sql = sql.replace(", READER_ID", "");
				}
				
				
				if (tables[i].contains("TAGS")) {
					sql = sql.replace("UNQ", "");
					sql = sql.replace("UNIQUE", "");
				}
				
				if (tables[i].contains("NR_COMMUNITIES_READERS") ||  tables[i].contains("NR_DISCOVERY_VIEW")   || tables[i].contains("NR_PROFILES_VIEW")    ) {
					// change (CREATION_DATE DESC)
					if (sql.contains("(CREATION_DATE DESC)")) {
						sql = sql.replace("(CREATION_DATE DESC)", "(CREATION_DATE DESC, IS_BROADCAST)");;
					}
				}
				
				if (isSQLServer) {
					sql += "\nGO\n";
				}
				else
					sql += "\nCOMMIT;\n";
				
				// Special case for cluster indexes
				if (sql.contains("CLUSTER")) {
					if (isOracle) {
						sql = sql.replace("INDEX HOMEPAGE.", "CLUSTER");
						sql = sql.replace(indexName, "");
						sql = sql.replace("ON", "");
						sql = sql.replace("READER_ID", "READER_ID VARCHAR2(36)");
						sql = sql.replace("CLUSTER TABLESPACE", "INDEX TABLESPACE");
						
					}						
				}
				
				//sql += "\nGO\n";
				
				
				
				if (sql.contains("DISCOVERY_VIEW_RDR_STR") ) { // don't print as it is not needed 					
					sql = "";
				}
				
				if (sql.contains("SR_IX") && !sql.contains("SAVED_READERS") ) {
					sql = "";
				}
				
				System.out.println(sql);
				
				
				
				
				//System.out.println("\n");
				//System.out.println("COMMIT;");
			}
			System.out.println("--  [end indexes] "+tables[i]);
		}

	}

}