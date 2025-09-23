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


ALTER TABLE HOMEPAGE.SR_SANDTASKDEF
	ADD CONSTRAINT "UNIQUE_TASK_ID_ST" UNIQUE ("TASK_ID");
	
ALTER TABLE HOMEPAGE.SR_SANDTASKDEF
	ADD CONSTRAINT "FK_ST_TASK_ID" FOREIGN KEY ("TASK_ID") 
	REFERENCES  HOMEPAGE.SR_TASKDEF("TASK_ID") ON DELETE CASCADE;

----------------------------------------
--  SR_FILECONTENTTASKDEF
----------------------------------------

ALTER TABLE HOMEPAGE.SR_FILECONTENTTASKDEF
	ADD CONSTRAINT "UNIQUE_TASK_ID_FC" UNIQUE ("TASK_ID");
	
ALTER TABLE HOMEPAGE.SR_FILECONTENTTASKDEF
	ADD CONSTRAINT "FK_FC_TASK_ID" FOREIGN KEY ("TASK_ID") 
	REFERENCES  HOMEPAGE.SR_TASKDEF("TASK_ID") ON DELETE CASCADE;


----------------------------------------
--  SR_BACKUPTASKDEF
----------------------------------------


ALTER TABLE HOMEPAGE.SR_BACKUPTASKDEF
	ADD CONSTRAINT "UNIQUE_TASK_ID_BKP" UNIQUE ("TASK_ID");
	
ALTER TABLE HOMEPAGE.SR_BACKUPTASKDEF
	ADD CONSTRAINT "FK_BKUP_TASK_ID" FOREIGN KEY ("TASK_ID") 
	REFERENCES  HOMEPAGE.SR_TASKDEF("TASK_ID") ON DELETE CASCADE;

----------------------------------------
--  SR_INDEXTASKDEF
----------------------------------------

ALTER TABLE HOMEPAGE.SR_INDEXINGTASKDEF
	ADD CONSTRAINT UNIQUE_TASK_ID_IND UNIQUE ("TASK_ID");


ALTER TABLE HOMEPAGE.SR_INDEXINGTASKDEF
	ADD CONSTRAINT "FK_INDEX_TASK_ID" FOREIGN KEY ("TASK_ID") 
	REFERENCES HOMEPAGE.SR_TASKDEF("TASK_ID") ON DELETE CASCADE;

    

----------------------------------------
--  SR_OPTIMIZETASKDEF
----------------------------------------

ALTER TABLE HOMEPAGE.SR_OPTIMIZETASKDEF
	ADD CONSTRAINT UNIQUE_TASK_ID_OPT UNIQUE ("TASK_ID");



ALTER TABLE HOMEPAGE.SR_OPTIMIZETASKDEF
	ADD CONSTRAINT "FK_OPT_TASK_ID" FOREIGN KEY ("TASK_ID") 
	REFERENCES  HOMEPAGE.SR_TASKDEF("TASK_ID") ON DELETE CASCADE;


----------------------------------------
--  SR_TASKDEF
----------------------------------------

ALTER TABLE HOMEPAGE.SR_TASKDEF
    ADD CONSTRAINT UNIQUE_TASK_NAME UNIQUE ("TASK_NAME");

ALTER TABLE HOMEPAGE.SR_TASKDEF
    ADD CONSTRAINT CHECK_SR_TASKDEF
    CHECK ( (STARTBY IS NOT NULL AND INTERVAL IS NOT NULL) OR (STARTBY IS NULL AND INTERVAL IS NULL) );

----------------------------------------
--  SR_FILESCONTENT
----------------------------------------

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD CONSTRAINT UNIQUE_COMP_UUID UNIQUE ("COMPONENT_UUID","COMPONENT");


CREATE INDEX "HOMEPAGE"."SR_FILESCONTENT_IS_CURRENT_IDX" 
	ON HOMEPAGE.SR_FILESCONTENT(IS_CURRENT) TABLESPACE "HOMEPAGEINDEXTABSPACE";   


----------------------------------------
--  SR_RESUME_TOKENS
----------------------------------------


ALTER TABLE HOMEPAGE.SR_RESUME_TOKENS
	ADD CONSTRAINT "FK_RT_IDX_MGMT_ID" FOREIGN KEY ("NODE_ID")
	REFERENCES HOMEPAGE.SR_INDEX_MANAGEMENT("NODE_ID") ON DELETE CASCADE;


----------------------------------------
--  SR_INDEX_DOCS
----------------------------------------

CREATE INDEX "HOMEPAGE"."SR_INDEX_CRAWL_VERSION_IDX"
	ON HOMEPAGE.SR_INDEX_DOCS (CRAWLING_VERSION)  TABLESPACE "HOMEPAGEINDEXTABSPACE"; 

----------------------------------------
--  SR_FEEDBACK
----------------------------------------

ALTER TABLE HOMEPAGE.SR_FEEDBACK
	ADD CONSTRAINT "FK_SRFB_PERSON_ID" FOREIGN KEY ("PERSON_ID")
	REFERENCES HOMEPAGE.PERSON("PERSON_ID");
	
CREATE INDEX "HOMEPAGE"."SR_FEEDBACK_CLIENT_IDX"
		ON HOMEPAGE.SR_FEEDBACK (CLIENT_ID) TABLESPACE "HOMEPAGEINDEXTABSPACE";    

----------------------------------------
--  SR_FEEDBACK_CONTEXT
----------------------------------------

ALTER TABLE HOMEPAGE.SR_FEEDBACK_CONTEXT
	ADD CONSTRAINT "FK_FBK_CTXT_ID" FOREIGN KEY ("ID")
	REFERENCES HOMEPAGE.SR_FEEDBACK("ID") ON DELETE CASCADE;


----------------------------------------
--  SR_FEEDBACK_PARAMETERS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_FEEDBACK_PARAMETERS
	ADD CONSTRAINT "FK_FBK_PARAMS_ID" FOREIGN KEY ("ID")
	REFERENCES HOMEPAGE.SR_FEEDBACK("ID") ON DELETE CASCADE;

----------------------------------------
--  SR_STATS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_STATS
	ADD CONSTRAINT "UNIQUE_STAT_KEY" UNIQUE ("STAT_KEY");


----------------------------------------
--  SR_STRING_STATS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_STRING_STATS
	ADD CONSTRAINT "FK_STR_STAT_ID" FOREIGN KEY ("STAT_ID")
	REFERENCES HOMEPAGE.SR_STATS("STAT_ID") ON DELETE CASCADE;

----------------------------------------
--  SR_NUMBER_STATS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_NUMBER_STATS
	ADD CONSTRAINT "FK_NUM_STAT_ID" FOREIGN KEY ("STAT_ID")
	REFERENCES HOMEPAGE.SR_STATS("STAT_ID") ON DELETE CASCADE;

----------------------------------------
--  SR_TIMER_STATS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_TIMER_STATS
	ADD CONSTRAINT "FK_TMR_STAT_ID" FOREIGN KEY ("STAT_ID")
	REFERENCES HOMEPAGE.SR_STATS("STAT_ID") ON DELETE CASCADE;
	
----------------------------------------
--  SR_GLOBAL_SAND_PROPS
----------------------------------------

--NO FKS YET


