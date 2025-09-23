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
	ON HOMEPAGE.NR_FOLLOWS (PERSON_ID, RESOURCE_ID)  TABLESPACE  NEWSINDEXTABSPACE;
	
CREATE UNIQUE INDEX HOMEPAGE.NR_RES_LU_UIDX
	ON HOMEPAGE.NR_RESOURCE (RESOURCE_ID, RESOURCE_TYPE, LAST_UPDATE DESC, CONTAINER_NAME DESC) TABLESPACE  NEWSINDEXTABSPACE;
	
CREATE INDEX HOMEPAGE.NR_RES_CONT_NAME_ORG_ID_IDX
	ON HOMEPAGE.NR_RESOURCE (CONTAINER_NAME, ORGANIZATION_ID) TABLESPACE "NEWSINDEXTABSPACE";		

CREATE INDEX HOMEPAGE.OH2P_CACHE_EXPIRES ON HOMEPAGE.OH2P_CACHE (EXPIRES ASC) TABLESPACE "HOMEPAGEINDEXTABSPACE";

COMMIT;


-----------------------------------------------------------------------------------
-- 136967: [fixup513] Switch to use CLOB INLINE for NR_STORIES_CONTENT
-----------------------------------------------------------------------------------

-----------------------------------------------------------------------------------
-- LOB values are stored inline when any of the following conditions apply:
-- When the size of the LOB stored in the given row is small, approximately 4000 bytes or less, and you either explicitly specify ENABLE STORAGE IN ROW
-- When the LOB value is NULL

ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT RENAME COLUMN ACTIVITY_META_DATA TO ACTIVITY_META_DATA_TMP;
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT RENAME COLUMN ITEM_CONTENT TO ITEM_CONTENT_TMP;
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT RENAME COLUMN ITEM_CORRELATION_CONTENT TO ITEM_CORRELATION_CONTENT_TMP;
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT RENAME COLUMN CONTENT TO CONTENT_TMP;
COMMIT;

-- iv) create new columns
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT
	ADD     ACTIVITY_META_DATA CLOB      		LOB (ACTIVITY_META_DATA) STORE AS (TABLESPACE NEWSLOBTABSPACE ENABLE STORAGE IN ROW  CACHE);
              	
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT
	ADD	ITEM_CONTENT CLOB      		LOB (ITEM_CONTENT) STORE AS (TABLESPACE NEWSLOBTABSPACE ENABLE STORAGE IN ROW  CACHE);

ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT
      	ADD	ITEM_CORRELATION_CONTENT CLOB      	LOB (ITEM_CORRELATION_CONTENT) STORE AS (TABLESPACE NEWSLOBTABSPACE ENABLE STORAGE IN ROW CACHE);

ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT
      	ADD	CONTENT CLOB      			LOB (CONTENT) STORE AS (TABLESPACE NEWSLOBTABSPACE ENABLE STORAGE IN ROW CACHE);
		
COMMIT;


UPDATE HOMEPAGE.NR_STORIES_CONTENT SET 
					ACTIVITY_META_DATA = ACTIVITY_META_DATA_TMP,
				 	ITEM_CONTENT = ITEM_CONTENT_TMP,
				 	ITEM_CORRELATION_CONTENT = ITEM_CORRELATION_CONTENT_TMP,
				 	CONTENT = CONTENT_TMP;

COMMIT;

ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT DROP (ACTIVITY_META_DATA_TMP, ITEM_CONTENT_TMP, ITEM_CORRELATION_CONTENT_TMP, CONTENT_TMP);
COMMIT;

----------------------------------------------------------------------------------------
-- 137979: [fixup513] Remove un-used READERs tables (pre\post, appGrants, reorg etc.. )
----------------------------------------------------------------------------------------

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

