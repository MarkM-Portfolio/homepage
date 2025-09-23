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


DROP INDEX HOMEPAGE.AGGREGATED_READERS_STR_RDR;

CREATE UNIQUE INDEX HOMEPAGE.AGGREGATED_READERS_STR_RDR 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (STORY_ID, READER_ID, CONTAINER_ID, CATEGORY_TYPE) TABLESPACE  NEWSINDEXTABSPACE;
 	
-----------------------------------------------------------------------------------------	
-- 142909: Delivery fixup515.sql - increase of ENTRY_CORRELATION_ID from 36 bytes to 256 bytes
-----------------------------------------------------------------------------------------	
ALTER TABLE HOMEPAGE.NR_STORIES
	MODIFY ENTRY_CORRELATION_ID VARCHAR2(256);
	
ALTER TABLE HOMEPAGE.NT_REPLYTO
	MODIFY ITEM_CORRELATION_ID VARCHAR2(256);
	
ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_COMMENT
	MODIFY ITEM_CORRELATION_ID VARCHAR2(256);	
		
COMMIT;
