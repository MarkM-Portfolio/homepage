-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2015                                   
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

-- 146330

-------------------------------------------------------
-- Drop existing index
-------------------------------------------------------
DROP INDEX HOMEPAGE.STATUS_READERS_DEL_SERV_IX;

CREATE  INDEX HOMEPAGE.STATUS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (CREATION_DATE DESC, IS_STORY_COMM) TABLESPACE NEWSINDEXTABSPACE;

DROP INDEX HOMEPAGE.AGGREGATED_READERS_DEL_SERV_IX;

CREATE  INDEX HOMEPAGE.AGGREGATED_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (CREATION_DATE DESC, IS_BROADCAST) TABLESPACE NEWSINDEXTABSPACE;
 	
