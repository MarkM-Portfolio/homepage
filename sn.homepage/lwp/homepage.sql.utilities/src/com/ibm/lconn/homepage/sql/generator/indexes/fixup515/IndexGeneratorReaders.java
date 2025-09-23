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

package com.ibm.lconn.homepage.sql.generator.indexes.fixup515;

public class IndexGeneratorReaders {
	
	
	/*
	Tables will be moved under  NR_AGGREGATED_READERS
	HOMEPAGE.NR_AGGREGATED_READERS 24
		HOMEPAGE.NR_RESPONSES_READERS 1
		HOMEPAGE.NR_PROFILES_READERS 2
		HOMEPAGE.NR_COMMUNITIES_READERS 3
		HOMEPAGE.NR_ACTIVITIES_READERS 4
		HOMEPAGE.NR_BLOGS_READERS 5
		HOMEPAGE.NR_BOOKMARKS_READERS 6
		HOMEPAGE.NR_FILES_READERS 7
		HOMEPAGE.NR_FORUMS_READERS 8
		HOMEPAGE.NR_WIKIS_READERS 9
		HOMEPAGE.NR_TAGS_READERS 10
		HOMEPAGE.NR_STATUS_UPDATE_READERS 11
		HOMEPAGE.NR_DISCOVERY_VIEW 17

	HOMEPAGE.NR_EXTERNAL_READERS 12

	HOMEPAGE.NR_ACTIONABLE_READERS 16 14 16

	HOMEPAGE.NR_SAVED_READERS 13
		
	HOMEPAGE.NR_NOTIFICATION_SENT_READERS 19 

	HOMEPAGE.NR_TOPICS_READERS 21

	HOMEPAGE.NR_MENTIONS_READERS 22

	HOMEPAGE.NR_COMMUNITIES_VIEW 23


	To delete:	HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS 20 ( never used )
	*/
	
	
	private static final String[] tables = {	"NR_AGGREGATED_READERS", 
												//"NR_RESPONSES_READERS", 
												//"NR_PROFILES_READERS", 
												//"NR_COMMUNITIES_READERS", 
												//"NR_ACTIVITIES_READERS", 
												//"NR_BLOGS_READERS", 
												//"NR_BOOKMARKS_READERS", 
												//"NR_FILES_READERS", 
												//"NR_FORUMS_READERS", 
												//"NR_WIKIS_READERS", 
												//"NR_TAGS_READERS",
												"NR_STATUS_UPDATE_READERS",
												"NR_EXTERNAL_READERS",												
												"NR_ACTIONABLE_READERS",
												//"NR_DISCOVERY_VIEW",
												"NR_PROFILES_VIEW",
												"NR_NOTIFICATION_SENT_READERS",
												//"NR_NOTIFICATION_RECEIV_READERS",
												"NR_SAVED_READERS",
												"NR_TOPICS_READERS",
												"NR_MENTIONS_READERS",
												"NR_COMMUNITIES_VIEW"
	
	
	};
	
	// TOPICS_READERS_SRC_IX *
	// TOPICS_READERS_RLL_IIX
	// TOPICS_READERS_ROLLUP_IIX
	// TOPICS_READERS_RIR_IX *
	// TOPICS_READERS_RLL_BRD_VVIS

	
	//RLL_IIX
	//ROLLUP_IIX
	//RLL_BRD_VVIS
	
	private static final String[] indexes_names = { 
		//"STR_IX", // 1
		//"ITM_IX", // 2
		//"CD_IX", // 3
		"RLL_IIX", // 4	
		"STR_RDR", // 5
		//"DEL_SERV_IX", // 6
		"ROLLUP_IIIX", //  7
		//"RLL_BRD_VVIS", // 8
		//"ORG_IIDX", //9 Just for discovery table
		//"COM", // DISCOVERY_VIEW_COM Just for discovery table
		//"ACT_CD_VIS_IDX", //  DISCOVERY_VIEW_ACT_CD_VIS_IDX
		//"COMM_IDX"// all tables to support soft delete
	};
	
	// 4 - 5 - 7 need to be refactored
	private static final String[] indexes = {
		//"(STORY_ID)", //1								 		
		//"(ITEM_ID)", //2
		//"(STORY_ID, CREATION_DATE DESC)", // 3
		"(READER_ID, CATEGORY_TYPE, IS_VISIBLE, ORGANIZATION_ID, CREATION_DATE DESC)", 	// 4	RLL_IIX 	(Refactoring adding category type)
		"(STORY_ID, READER_ID, CATEGORY_TYPE)", 										// 5	STR_RDR 	(Refactoring adding category type - note on NR_AGG_READER there is also CONTAINER_ID)
		//"(CREATION_DATE DESC)", // 6
		"(ROLLUP_ENTRY_ID, READER_ID, CATEGORY_TYPE, IS_VISIBLE, CREATION_DATE DESC)", 	// 7 	ROLLUP_IIX 	(Refactoring adding category type)
		//"(ROLLUP_AUTHOR_ID, IS_BROADCAST,S IS_VISIBLE)", // 8 RLL_BRD_VVIS
		//"(ORGANIZATION_ID, IS_VISIBLE, IS_BROADCAST, ROLLUP_ENTRY_ID, CREATION_DATE DESC)", //9 ORG_IIDX
		// Just for discovery table
		//"(IS_STORY_COMM, ORGANIZATION_ID, IS_VISIBLE, USE_IN_ROLLUP, CREATION_DATE DESC, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, STORY_ID)", // 9
		//"(ACTOR_UUID, IS_VISIBLE, CREATION_DATE DESC)", //10
		//"(COMMUNITY_ID)"
	};
	
	private static final String[] unique = {
		//"", //1								 		
		//"", //2
		//"", // 3
		"", //4
		"UNIQUE", //5
		//"", // 6
		"", // 7
		//"", // 8
		//"", // 9
		//"", // 10
		//"", // 11
		//"", // 12
		//""
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
		boolean create = true;
		
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
					sql = sqlIndexes_drop;
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
				
//				// if the table is DISCOVERY_TABLE we remove the READER_ID from the index definition
//				if (table.equals("DISCOVERY_VIEW")) {
//					sql = sql.replace("READER_ID, ", "");
//					sql = sql.replace(", READER_ID", "");
//					
//					
//				}
				
				
				if (tables[i].contains("TAGS")) {
					sql = sql.replace("UNQ", "");
					sql = sql.replace("UNIQUE", "");
				}
				
				if (tables[i].contains("NR_COMMUNITIES_READERS") || tables[i].contains("NR_PROFILES_VIEW")    ) {
					// change (CREATION_DATE DESC)
					if (sql.contains("(CREATION_DATE DESC)")) {
						sql = sql.replace("(CREATION_DATE DESC)", "(CREATION_DATE DESC, IS_BROADCAST)");
					}
				}
				
				if (sql.contains("IS_STORY_COMM") || sql.contains("ACT_CD_VIS_IDX") ) {
					if (!(table.equals("DISCOVERY_VIEW"))) {
						sql = "";
						continue;
					}
				}
				
							
				if (tables[i].contains("NR_DISCOVERY_VIEW") && indexName.contains("DISCOVERY_VIEW_RLL_IIX")  ) {
					// change (CREATION_DATE DESC) RLL_IIX
					if (sql.contains("(IS_VISIBLE, ORGANIZATION_ID, CREATION_DATE DESC)")) {
						sql = sql.replace("(IS_VISIBLE, ORGANIZATION_ID, CREATION_DATE DESC)","(USE_IN_ROLLUP, ORGANIZATION_ID, IS_VISIBLE, CREATION_DATE DESC,  IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, STORY_ID)");
						
					}
				}
				
				// COM index is just for DISCOVERY_VIEW
				if (!tables[i].contains("NR_DISCOVERY_VIEW") && indexName.contains("_COM")  ) {
						sql = "";
					
				}
				
				// Oracle limitation with long identifier. We need to cut the last char:
				if (	indexName.contains("AGGREGATED_READERS_RLL_BRD_VVIS") || 
						indexName.contains("ACTIONABLE_READERS_RLL_BRD_VVIS") || 
						indexName.contains("ACTIVITIES_READERS_RLL_BRD_VVIS")
					) {
					sql = sql.replace("VVIS", "VVI");
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
				
				if (tables[i].contains("NR_AGGREGATED_READERS") && indexName.contains("STR_RDR")  ) {
					sql =  sql.replace("(STORY_ID, READER_ID, CATEGORY_TYPE)","(STORY_ID, READER_ID, CONTAINER_ID, CATEGORY_TYPE)");
				}
				
				if (sql.contains("DISCOVERY_VIEW_RDR_STR") || sql.contains("DISCOVERY_VIEW_R_O_V_IDX") ) { // don't print as it is not needed 					
					sql = "";
				}
				
				if (sql.contains("SR_IX") && !sql.contains("SAVED_READERS") ) {
					sql = "";
				}
				
				if (isDb2) {
					sql = sql.replaceAll(";", "@");
				}
				
				if (indexes_names[k].contains("ORG_IIDX")) {
					if (tables[i].contains("NR_DISCOVERY_VIEW") || tables[i].contains("NR_PROFILES_VIEW" )) 
						sql = "--NEW ORG_ID "+tables[i]+" \n"+sql;
					else
						sql = "";
				} 
				
				// DISCOVERY VIEW DOESN'T HAVE STR_RDR as it is replicated of STR_IX
				if (indexes_names[k].contains("STR_RDR") && tables[i].contains("NR_DISCOVERY_VIEW")) {
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