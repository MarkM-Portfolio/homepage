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


DROP INDEX AGGREGATED_READERS_STR_RDR ON HOMEPAGE.NR_AGGREGATED_READERS;

CREATE UNIQUE  INDEX AGGREGATED_READERS_STR_RDR 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (STORY_ID, READER_ID, CONTAINER_ID, CATEGORY_TYPE);
 	
 	
-----------------------------------------------------------------------------------------	
-- 142909: Delivery fixup515.sql - increase of ENTRY_CORRELATION_ID from 36 bytes to 256 bytes
-----------------------------------------------------------------------------------------	
ALTER TABLE HOMEPAGE.NR_STORIES
	ALTER COLUMN ENTRY_CORRELATION_ID nvarchar(256);

ALTER TABLE HOMEPAGE.NT_REPLYTO
	ALTER COLUMN ITEM_CORRELATION_ID nvarchar(256);
	
ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_COMMENT
	ALTER COLUMN ITEM_CORRELATION_ID nvarchar(256);	