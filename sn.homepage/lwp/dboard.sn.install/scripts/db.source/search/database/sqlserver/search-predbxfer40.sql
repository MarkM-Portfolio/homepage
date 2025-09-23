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

ALTER TABLE HOMEPAGE.SR_SANDTASKDEF DROP CONSTRAINT UNIQUE_TASK_ID_ST;

ALTER TABLE HOMEPAGE.SR_SANDTASKDEF DROP CONSTRAINT FK_ST_TASK_ID;

----------------------------------------
--  SR_FILECONTENTTASKDEF
----------------------------------------

ALTER TABLE HOMEPAGE.SR_FILECONTENTTASKDEF DROP CONSTRAINT UNIQUE_TASK_ID_FC;

ALTER TABLE HOMEPAGE.SR_FILECONTENTTASKDEF DROP CONSTRAINT FK_FC_TASK_ID;

----------------------------------------
--  SR_BACKUPTASKDEF
----------------------------------------

ALTER TABLE HOMEPAGE.SR_BACKUPTASKDEF DROP CONSTRAINT UNIQUE_TASK_ID_BKP;

ALTER TABLE HOMEPAGE.SR_BACKUPTASKDEF DROP CONSTRAINT FK_BKUP_TASK_ID;

----------------------------------------
--  SR_INDEXTASKDEF
----------------------------------------

ALTER TABLE HOMEPAGE.SR_INDEXINGTASKDEF DROP CONSTRAINT FK_INDEX_TASK_ID;

ALTER TABLE HOMEPAGE.SR_INDEXINGTASKDEF DROP CONSTRAINT UNIQUE_TASK_ID_IND;

----------------------------------------
--  SR_OPTIMIZETASKDEF
----------------------------------------

ALTER TABLE HOMEPAGE.SR_OPTIMIZETASKDEF DROP CONSTRAINT UNIQUE_TASK_ID_OPT;

ALTER TABLE HOMEPAGE.SR_OPTIMIZETASKDEF DROP CONSTRAINT FK_OPT_TASK_ID;

----------------------------------------
--  SR_TASKDEF
----------------------------------------

ALTER TABLE HOMEPAGE.SR_TASKDEF DROP CONSTRAINT UNIQUE_TASK_NAME;

ALTER TABLE HOMEPAGE.SR_TASKDEF DROP CONSTRAINT CHECK_SR_TASKDEF;


----------------------------------------
--  SR_FILESCONTENT
----------------------------------------

ALTER TABLE HOMEPAGE.SR_FILESCONTENT DROP CONSTRAINT UNIQUE_COMP_UUID;

DROP INDEX SR_FILESCONTENT_IS_CURRENT_IDX ON HOMEPAGE.SR_FILESCONTENT;

----------------------------------------
--  SR_RESUME_TOKENS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_RESUME_TOKENS DROP CONSTRAINT FK_RT_IDX_MGMT_ID;


----------------------------------------
--  SR_INDEX_DOCS
----------------------------------------

DROP INDEX  SR_INDEX_DOCS_CRAWL_VERSION_IDX ON HOMEPAGE.SR_INDEX_DOCS;

----------------------------------------
--  SR_FEEDBACK
----------------------------------------

ALTER TABLE HOMEPAGE.SR_FEEDBACK DROP CONSTRAINT FK_SRFB_PERSON_ID;

DROP INDEX SR_FEEDBACK_CLIENT_IDX ON HOMEPAGE.SR_FEEDBACK;

----------------------------------------
--  SR_FEEDBACK_CONTEXT
----------------------------------------

ALTER TABLE HOMEPAGE.SR_FEEDBACK_CONTEXT DROP CONSTRAINT FK_FBK_CTXT_ID;

----------------------------------------
--  SR_FEEDBACK_PARAMETERS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_FEEDBACK_PARAMETERS DROP CONSTRAINT FK_FBK_PARAMS_ID;

----------------------------------------
--  SR_STATS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_STATS DROP  CONSTRAINT UNIQUE_STAT_KEY;

----------------------------------------
--  SR_STRING_STATS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_STRING_STATS DROP CONSTRAINT FK_STR_STAT_ID;

----------------------------------------
--  SR_NUMBER_STATS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_NUMBER_STATS DROP CONSTRAINT FK_NUM_STAT_ID;

----------------------------------------
--  SR_TIMER_STATS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_TIMER_STATS DROP CONSTRAINT FK_TMR_STAT_ID;

----------------------------------------
--  SR_GLOBAL_SAND_PROPS
----------------------------------------

--NO FKS YET


-- SEARCH deletion
{include.search-deleteAllRows.sql}
