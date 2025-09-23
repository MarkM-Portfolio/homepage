/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2015                                          */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.sql.generator.indexes.fixup515;

public class MergeTables {

	
	public static void main(String[] args) {
		
		StringBuffer sb = new StringBuffer();
		
		String[] tables= {
				"HOMEPAGE.NR_RESPONSES_READERS",
				"HOMEPAGE.NR_PROFILES_READERS",
				"HOMEPAGE.NR_COMMUNITIES_READERS",
				"HOMEPAGE.NR_ACTIVITIES_READERS",
				"HOMEPAGE.NR_BLOGS_READERS",
				"HOMEPAGE.NR_BOOKMARKS_READERS",
				"HOMEPAGE.NR_FILES_READERS",
				"HOMEPAGE.NR_FORUMS_READERS",
				"HOMEPAGE.NR_WIKIS_READERS",
				"HOMEPAGE.NR_TAGS_READERS",
				"HOMEPAGE.NR_STATUS_UPDATE_READERS",
				"HOMEPAGE.NR_DISCOVERY_VIEW"			
		};
		
		
		for (String table: tables) {
		
			sb.append("-- Merging "+table+" \n");
			sb.append("		EXPORT TO data.tmp.ixf OF IXF METHOD N ( \n"); 
			sb.append("			CATEGORY_READER_ID, READER_ID, CATEGORY_TYPE, SOURCE, CONTAINER_ID, ITEM_ID, \n"); 
			sb.append("			ROLLUP_ENTRY_ID, RESOURCE_TYPE, CREATION_DATE, STORY_ID, SOURCE_TYPE, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, \n"); 
			sb.append("			IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID, COMMUNITY_ID \n"); 
			sb.append("		) \n"); 
			sb.append("		SELECT \n"); 
			sb.append("			CATEGORY_READER_ID, READER_ID, CATEGORY_TYPE, SOURCE, CONTAINER_ID, ITEM_ID, \n"); 
			sb.append("			ROLLUP_ENTRY_ID, RESOURCE_TYPE, CREATION_DATE, STORY_ID, SOURCE_TYPE, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, \n"); 
			sb.append("			IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID, COMMUNITY_ID \n"); 
			sb.append("		FROM HOMEPAGE."+table+"@ \n"); 
			sb.append("		COMMIT@ \n"); 
			sb.append(" \n"); 
			sb.append("		IMPORT FROM data.tmp.ixf OF IXF METHOD N ( \n"); 
			sb.append("			CATEGORY_READER_ID, READER_ID, CATEGORY_TYPE, SOURCE, CONTAINER_ID, ITEM_ID, \n"); 
			sb.append("			ROLLUP_ENTRY_ID, RESOURCE_TYPE, CREATION_DATE, STORY_ID, SOURCE_TYPE, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, \n"); 
			sb.append("			IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID, COMMUNITY_ID \n"); 
			sb.append("		) COMMITCOUNT 2000 \n"); 
			sb.append("		INSERT_UPDATE INTO HOMEPAGE.NR_AGGREGATED_READERS ( \n"); 
			sb.append("			CATEGORY_READER_ID, READER_ID, CATEGORY_TYPE, SOURCE, CONTAINER_ID, ITEM_ID, \n"); 
			sb.append("			ROLLUP_ENTRY_ID, RESOURCE_TYPE, CREATION_DATE, STORY_ID, SOURCE_TYPE, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, \n"); 
			sb.append("			IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID, COMMUNITY_ID \n"); 
			sb.append("		)@ \n"); 
			sb.append("		COMMIT@ \n");
			sb.append("		\n");
			sb.append("DROP TABLE HOMEPAGE."+table+"@ \n");
			sb.append("COMMIT@ \n\n");
			
			System.out.println(sb);
		
		}
	}
}
