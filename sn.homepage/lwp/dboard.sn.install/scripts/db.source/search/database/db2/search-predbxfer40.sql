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

-- {COPYRIGHT}

-- SEARCH TABLES


----------------------------------------
--  SR_SANDTASKDEF
----------------------------------------

ALTER TABLE HOMEPAGE.SR_SANDTASKDEF DROP UNIQUE "UNIQUE_TASK_ID_ST";

ALTER TABLE HOMEPAGE.SR_SANDTASKDEF DROP FOREIGN KEY "FK_ST_TASK_ID";

----------------------------------------
--  SR_FILECONTENTTASKDEF
----------------------------------------

ALTER TABLE HOMEPAGE.SR_FILECONTENTTASKDEF DROP UNIQUE  "UNIQUE_TASK_ID_FC";

ALTER TABLE HOMEPAGE.SR_FILECONTENTTASKDEF DROP FOREIGN KEY "FK_FC_TASK_ID";

----------------------------------------
--  SR_BACKUPTASKDEF
----------------------------------------

ALTER TABLE HOMEPAGE.SR_BACKUPTASKDEF DROP UNIQUE "UNIQUE_TASK_ID_BKP";

ALTER TABLE HOMEPAGE.SR_BACKUPTASKDEF DROP FOREIGN KEY "FK_BKUP_TASK_ID";

----------------------------------------
--  SR_INDEXTASKDEF
----------------------------------------

ALTER TABLE HOMEPAGE.SR_INDEXINGTASKDEF DROP FOREIGN KEY "FK_INDEX_TASK_ID";

ALTER TABLE HOMEPAGE.SR_INDEXINGTASKDEF DROP UNIQUE "UNIQUE_TASK_ID_IND";

----------------------------------------
--  SR_OPTIMIZETASKDEF
----------------------------------------

ALTER TABLE HOMEPAGE.SR_OPTIMIZETASKDEF DROP UNIQUE "UNIQUE_TASK_ID_OPT";

ALTER TABLE HOMEPAGE.SR_OPTIMIZETASKDEF DROP FOREIGN KEY "FK_OPT_TASK_ID";

----------------------------------------
--  SR_TASKDEF
----------------------------------------

ALTER TABLE HOMEPAGE.SR_TASKDEF DROP UNIQUE "UNIQUE_TASK_NAME";

ALTER TABLE HOMEPAGE.SR_TASKDEF DROP CHECK "CHECK_SR_TASKDEF";

----------------------------------------
--  SR_FILESCONTENT
----------------------------------------

ALTER TABLE HOMEPAGE.SR_FILESCONTENT DROP UNIQUE "UNIQUE_COMP_UUID";

DROP INDEX  HOMEPAGE.SR_FILESCONTENT_IS_CURRENT_IDX;


----------------------------------------
--  SR_RESUME_TOKENS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_RESUME_TOKENS DROP FOREIGN KEY "FK_RT_IDX_MGMT_ID";


----------------------------------------
--  SR_INDEX_DOCS
----------------------------------------

DROP INDEX  HOMEPAGE.SR_INDEX_CRAWL_VERSION_IDX;

----------------------------------------
--  SR_FEEDBACK
----------------------------------------

ALTER TABLE HOMEPAGE.SR_FEEDBACK DROP FOREIGN KEY "FK_SRFB_PERSON_ID";

DROP INDEX HOMEPAGE.SR_FEEDBACK_CLIENT_IDX;

----------------------------------------
--  SR_FEEDBACK_CONTEXT
----------------------------------------

ALTER TABLE HOMEPAGE.SR_FEEDBACK_CONTEXT DROP FOREIGN KEY "FK_FBK_CTXT_ID";

----------------------------------------
--  SR_FEEDBACK_PARAMETERS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_FEEDBACK_PARAMETERS DROP FOREIGN KEY "FK_FBK_PARAMS_ID";

----------------------------------------
--  SR_STATS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_STATS DROP  UNIQUE "UNIQUE_STAT_KEY";

----------------------------------------
--  SR_STRING_STATS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_STRING_STATS DROP FOREIGN KEY "FK_STR_STAT_ID";

----------------------------------------
--  SR_NUMBER_STATS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_NUMBER_STATS DROP FOREIGN KEY "FK_NUM_STAT_ID";

----------------------------------------
--  SR_TIMER_STATS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_TIMER_STATS DROP FOREIGN KEY "FK_TMR_STAT_ID";

----------------------------------------
--  SR_GLOBAL_SAND_PROPS
----------------------------------------

--NO FKS YET



-- SEARCH deletion
{include.search-deleteAllRows.sql}
