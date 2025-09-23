-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2009, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- scriptfile to drop schema for DB2
-- 1. Replace all occurrances of NR_SCHEDULER_ to the Table Prefix you will use in the
--    configured Scheduler resource.
-- 2. Process this script in the DB2 command line processor
-- Example:
--             db2 connect to SCHEDDB
--             db2 -tf dropSchemaDb2.ddl

CONNECT TO HOMEPAGE;

DROP INDEX HOMEPAGE.NR_SCHEDULER_TASK_IDX1;

DROP INDEX HOMEPAGE.NR_SCHEDULER_TASK_IDX2;

DROP TABLE HOMEPAGE.NR_SCHEDULER_TASK;

DROP TABLE HOMEPAGE.NR_SCHEDULER_TREG;

DROP TABLE HOMEPAGE.NR_SCHEDULER_LMGR;

DROP INDEX HOMEPAGE.NR_SCHEDULER_LMPR_IDX1;

DROP TABLE HOMEPAGE.NR_SCHEDULER_LMPR;

DROP TABLE HOMEPAGE.NR_EVENT_RECORDS;

COMMIT;

--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
-- END THE DEFINITION FOR THE SEARCH DATABASE
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------


--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate;


