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

------------------------------------------------------------------------------------------------------------------------------
-- 142999: [Production, E1, ACDEV1, ACDEV ] Where multiple followed tags are attached to an object only 1 tag returns story using Following tags filters
------------------------------------------------------------------------------------------------------------------------------


DROP INDEX HOMEPAGE.AGGREGATED_READERS_STR_RDR@

CREATE UNIQUE INDEX HOMEPAGE.AGGREGATED_READERS_STR_RDR 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (STORY_ID, READER_ID, CONTAINER_ID, CATEGORY_TYPE)@

------------------------------------------------------------------------------------------------------------------------------
-- 141134: [fixup515] [Performance|DB2] Finding a suitable inline length for LOBS in NR_STORIES_CONTENT - Value found is 1000B
-------------------------------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------
-- ii) save data

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup515 - Start export data HOMEPAGE.NR_STORIES_CONTENT' FROM SYSIBM.SYSDUMMY1@

EXPORT TO data.tmp.ixf OF IXF METHOD N (
        	STORY_CONTENT_ID, ACTIVITY_META_DATA, ITEM_CONTENT, ITEM_CORRELATION_CONTENT, CONTENT
)
 SELECT
		STORY_CONTENT_ID, ACTIVITY_META_DATA, ITEM_CONTENT, ITEM_CORRELATION_CONTENT, CONTENT
FROM HOMEPAGE.NR_STORIES_CONTENT@

COMMIT@

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [END] fixup515 - End export data HOMEPAGE.NR_STORIES_CONTENT' FROM SYSIBM.SYSDUMMY1@

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup515 - Start alter table and reorg HOMEPAGE.NR_STORIES_CONTENT' FROM SYSIBM.SYSDUMMY1@

-- iii) drop columns
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT 
	DROP COLUMN ITEM_CONTENT
	DROP COLUMN ITEM_CORRELATION_CONTENT
	DROP COLUMN CONTENT@

COMMIT@

-- iv) create new columns
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT
      ADD COLUMN ITEM_CONTENT CLOB INLINE LENGTH 512
      ADD COLUMN ITEM_CORRELATION_CONTENT CLOB INLINE LENGTH 512
      ADD COLUMN CONTENT CLOB INLINE LENGTH 512@
COMMIT@

REORG TABLE HOMEPAGE.NR_STORIES_CONTENT@

COMMIT@

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [END] fixup515 - End alter table and reorg HOMEPAGE.NR_STORIES_CONTENT' FROM SYSIBM.SYSDUMMY1@

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup515 - Start import datat into HOMEPAGE.NR_STORIES_CONTENT' FROM SYSIBM.SYSDUMMY1@

IMPORT FROM data.tmp.ixf OF IXF METHOD N (
	STORY_CONTENT_ID, ITEM_CONTENT, ITEM_CORRELATION_CONTENT, CONTENT
) COMMITCOUNT 1000
INSERT_UPDATE INTO HOMEPAGE.NR_STORIES_CONTENT
(
 	STORY_CONTENT_ID, ITEM_CONTENT, ITEM_CORRELATION_CONTENT, CONTENT
)@

COMMIT@

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup515 - End import data to HOMEPAGE.NR_STORIES_CONTENT' FROM SYSIBM.SYSDUMMY1@

-----------------------------------------------------------------------------------------	
-- 142909: Delivery fixup515.sql - increase of ENTRY_CORRELATION_ID from 36 bytes to 256 bytes
-----------------------------------------------------------------------------------------	
ALTER TABLE HOMEPAGE.NR_STORIES
	ALTER COLUMN ENTRY_CORRELATION_ID SET DATA TYPE VARCHAR(256)@

ALTER TABLE HOMEPAGE.NT_REPLYTO
	ALTER COLUMN ITEM_CORRELATION_ID SET DATA TYPE VARCHAR(256)@
	
ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_COMMENT
	ALTER COLUMN ITEM_CORRELATION_ID SET DATA TYPE VARCHAR(256)@	
	
COMMIT@	



