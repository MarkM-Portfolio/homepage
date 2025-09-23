-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2014, 2015                             
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

-----------------------------------------------------------------------------------
-- [Work Item 137609] There is no schema for 4 homepage DB indexes [o]
-----------------------------------------------------------------------------------

DROP INDEX NR_FOLLOWS_PER_RES_UIDX;
DROP INDEX NR_RES_LU_UIDX; 
DROP INDEX NR_RES_CONT_NAME_ORG_ID_IDX; 
DROP INDEX OH2P_CACHE_EXPIRES;

COMMIT;

CREATE UNIQUE INDEX HOMEPAGE.NR_FOLLOWS_PER_RES_UIDX
	ON HOMEPAGE.NR_FOLLOWS (PERSON_ID, RESOURCE_ID);  
	
CREATE UNIQUE INDEX HOMEPAGE.NR_RES_LU_UIDX
	ON HOMEPAGE.NR_RESOURCE (RESOURCE_ID, RESOURCE_TYPE, LAST_UPDATE DESC, CONTAINER_NAME DESC);
	
CREATE INDEX HOMEPAGE.NR_RES_CONT_NAME_ORG_ID_IDX
	ON HOMEPAGE.NR_RESOURCE (CONTAINER_NAME, ORGANIZATION_ID);		

CREATE INDEX HOMEPAGE.OH2P_CACHE_EXPIRES ON HOMEPAGE.OH2P_CACHE (EXPIRES ASC);
COMMIT;

-----------------------------------------------------------------------------------
-- 136967: [fixup513] Switch to use CLOB INLINE for NR_STORIES_CONTENT
-----------------------------------------------------------------------------------

-----------------------------------------------------------------------------------
-- ii) save data
/*
--SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup513 - Start export data HOMEPAGE.NR_STORIES_CONTENT' FROM SYSIBM.SYSDUMMY1;

EXPORT TO data.tmp.ixf OF IXF METHOD N (
        	STORY_CONTENT_ID, ACTIVITY_META_DATA, ITEM_CONTENT, ITEM_CORRELATION_CONTENT, CONTENT
)
 SELECT
		STORY_CONTENT_ID, ACTIVITY_META_DATA, ITEM_CONTENT, ITEM_CORRELATION_CONTENT, CONTENT
FROM HOMEPAGE.NR_STORIES_CONTENT;

COMMIT;

--SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [END] fixup513 - End export data HOMEPAGE.NR_STORIES_CONTENT' FROM SYSIBM.SYSDUMMY1;

--SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup513 - Start alter table and reorg HOMEPAGE.NR_STORIES_CONTENT' FROM SYSIBM.SYSDUMMY1;

-- iii) drop columns
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT 
	DROP COLUMN ACTIVITY_META_DATA
	DROP COLUMN ITEM_CONTENT
	DROP COLUMN ITEM_CORRELATION_CONTENT
	DROP COLUMN CONTENT;

COMMIT;

-- iv) create new columns
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT
      ADD COLUMN ACTIVITY_META_DATA CLOB INLINE LENGTH 200
      ADD COLUMN ITEM_CONTENT CLOB INLINE LENGTH 4700
      ADD COLUMN ITEM_CORRELATION_CONTENT CLOB INLINE LENGTH 4700
      ADD COLUMN CONTENT CLOB INLINE LENGTH 4700;
COMMIT;

--REORG TABLE HOMEPAGE.NR_STORIES_CONTENT;

--COMMIT;

--SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [END] fixup513 - End alter table and reorg HOMEPAGE.NR_STORIES_CONTENT' FROM SYSIBM.SYSDUMMY1;

--SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup513 - Start import datat into HOMEPAGE.NR_STORIES_CONTENT' FROM SYSIBM.SYSDUMMY1;

IMPORT FROM data.tmp.ixf OF IXF METHOD N (
	STORY_CONTENT_ID, ACTIVITY_META_DATA, ITEM_CONTENT, ITEM_CORRELATION_CONTENT, CONTENT
) COMMITCOUNT 1000
INSERT_UPDATE INTO HOMEPAGE.NR_STORIES_CONTENT
(
 	STORY_CONTENT_ID, ACTIVITY_META_DATA, ITEM_CONTENT, ITEM_CORRELATION_CONTENT, CONTENT
);

COMMIT;

--SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup513 - End import data to HOMEPAGE.NR_STORIES_CONTENT' FROM SYSIBM.SYSDUMMY1;

----------------------------------------------------------------------------------------
-- 137979: [fixup513] Remove un-used READERs tables (pre\post, appGrants, reorg etc.. )
----------------------------------------------------------------------------------------

--SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup513 - Start in removing un-used READERS tables ' FROM SYSIBM.SYSDUMMY1;
*/
DROP VIEW HOMEPAGE.NR_CATEGORIES_READERS;
COMMIT;

DROP TABLE HOMEPAGE.NR_RESPONSES_READERS;
COMMIT;

DROP TABLE HOMEPAGE.NR_PROFILES_READERS;
COMMIT;

DROP TABLE HOMEPAGE.NR_COMMUNITIES_READERS;
COMMIT;

DROP TABLE HOMEPAGE.NR_ACTIVITIES_READERS;
COMMIT;

DROP TABLE HOMEPAGE.NR_BLOGS_READERS;
COMMIT;

DROP TABLE HOMEPAGE.NR_BOOKMARKS_READERS;
COMMIT;

DROP TABLE HOMEPAGE.NR_FILES_READERS;
COMMIT;

DROP TABLE HOMEPAGE.NR_FORUMS_READERS;
COMMIT;

DROP TABLE HOMEPAGE.NR_WIKIS_READERS;
COMMIT;

DROP TABLE HOMEPAGE.NR_TAGS_READERS;
COMMIT;

DROP TABLE HOMEPAGE.NR_DISCOVERY_VIEW;
COMMIT;

--SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup513 - End of removing un-used READERS tables ' FROM SYSIBM.SYSDUMMY1;
