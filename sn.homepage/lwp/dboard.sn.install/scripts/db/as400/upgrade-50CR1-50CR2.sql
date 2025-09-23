-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2007, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

SET CURRENT SCHEMA HOMEPAGE;

----------------------------------------------------------------------------------------------------------
-- 140717: SVT CR2 IC5.0_CR_Install_20141122-1831:00000106 MessageHandle .. Invalid correlation ID length
----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_STORIES
        ALTER COLUMN ENTRY_CORRELATION_ID SET DATA TYPE VARCHAR(256) CCSID 1208;

ALTER TABLE HOMEPAGE.NT_REPLYTO
        ALTER COLUMN ITEM_CORRELATION_ID SET DATA TYPE VARCHAR(256) CCSID 1208;

ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_COMMENT
        ALTER COLUMN ITEM_CORRELATION_ID SET DATA TYPE VARCHAR(256) CCSID 1208;

COMMIT;

------------------------------------------------

UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 478, RELEASEVER = '5.0.0.0 CR2' 
WHERE   DBSCHEMAVER = 477;

--------------------------------------
-- COMMIT
--------------------------------------

COMMIT;
--------------------------------------
-- FLUSH
--------------------------------------
--FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
--connect reset;
--terminate;