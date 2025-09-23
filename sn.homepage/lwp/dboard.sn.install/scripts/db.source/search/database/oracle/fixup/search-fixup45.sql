-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2010, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

---------------------------------------------------------------------------------
------------------------ START SEARCH -------------------------------------------
---------------------------------------------------------------------------------


----------------------------------------
--  SR_INDEX_MANAGEMENT
----------------------------------------

DELETE FROM HOMEPAGE.SR_INDEX_MANAGEMENT;

ALTER TABLE HOMEPAGE.SR_INDEX_MANAGEMENT
ADD INDEXER NUMBER(5,0) DEFAULT 1 NOT NULL
MODIFY NODE_ID VARCHAR2(256);

----------------------------------------
--  HOMEPAGE.SR_RESUME_TOKENS
----------------------------------------

DELETE FROM HOMEPAGE.SR_RESUME_TOKENS;

ALTER TABLE HOMEPAGE.SR_RESUME_TOKENS
MODIFY NODE_ID VARCHAR2(256);

----------------------------------------
--  SR_LOTUSCONNECTIONS*
----------------------------------------

{include.search-clearScheduler.sql}

---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------