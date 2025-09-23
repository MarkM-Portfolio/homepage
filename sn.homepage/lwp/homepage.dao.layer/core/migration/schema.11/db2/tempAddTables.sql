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

-- Scriptfile to create schema for DB2
-- 1. Replace all occurrances of NR_SCHEDULER_ to the Table Prefix you will use in the
--    configured Scheduler resource.
-- 2. Replace all occurrances of @SCHED_TABLESPACE@ with a valid tablespace that was
--    created by the createTablesapceDb2.ddl script.
-- 3. Process this script in the DB2 command line processor
-- Example:
--             db2 connect to SCHEDDB
--             db2 -tf createSchemaDb2.ddl
-- The schema assumes existing tablespaces that should have been
-- created before using createTablespaceDb2.ddl script.

CONNECT TO HOMEPAGE;

CREATE TABLE HOMEPAGE.NR_SCHEDULER_TASK (TASKID BIGINT NOT NULL ,
              VERSION VARCHAR(5) NOT NULL ,
              ROW_VERSION INTEGER NOT NULL ,
              TASKTYPE INTEGER NOT NULL ,
              TASKSUSPENDED SMALLINT NOT NULL ,
              CANCELLED SMALLINT NOT NULL ,
              NEXTFIRETIME BIGINT NOT NULL ,
              STARTBYINTERVAL VARCHAR(254) ,
              STARTBYTIME BIGINT ,
              VALIDFROMTIME BIGINT ,
              VALIDTOTIME BIGINT ,
              REPEATINTERVAL VARCHAR(254) ,
              MAXREPEATS INTEGER NOT NULL ,
              REPEATSLEFT INTEGER NOT NULL ,
              TASKINFO BLOB(102400) LOGGED NOT COMPACT ,
              NAME VARCHAR(254) NOT NULL ,
              AUTOPURGE INTEGER NOT NULL ,
              FAILUREACTION INTEGER ,
              MAXATTEMPTS INTEGER ,
              QOS INTEGER ,
              PARTITIONID INTEGER ,
              OWNERTOKEN VARCHAR(200) NOT NULL ,
              CREATETIME BIGINT NOT NULL )  IN NEWSTABSPACE;

ALTER TABLE HOMEPAGE.NR_SCHEDULER_TASK ADD PRIMARY KEY (TASKID);

CREATE INDEX HOMEPAGE.NR_SCHEDULER_TASK_IDX1 ON HOMEPAGE.NR_SCHEDULER_TASK (TASKID,
              OWNERTOKEN) ;

CREATE INDEX HOMEPAGE.NR_SCHEDULER_TASK_IDX2 ON HOMEPAGE.NR_SCHEDULER_TASK (NEXTFIRETIME ASC,
               REPEATSLEFT,
               PARTITIONID) CLUSTER;

CREATE TABLE HOMEPAGE.NR_SCHEDULER_TREG (REGKEY VARCHAR(254) NOT NULL ,
              REGVALUE VARCHAR(254) ) IN NEWSTABSPACE;

ALTER TABLE HOMEPAGE.NR_SCHEDULER_TREG ADD PRIMARY KEY (REGKEY);

CREATE TABLE HOMEPAGE.NR_SCHEDULER_LMGR (LEASENAME VARCHAR(254) NOT NULL,
               LEASEOWNER VARCHAR(254) NOT NULL,
               LEASE_EXPIRE_TIME  BIGINT,
              DISABLED VARCHAR(5))IN NEWSTABSPACE;

ALTER TABLE HOMEPAGE.NR_SCHEDULER_LMGR ADD PRIMARY KEY (LEASENAME);

CREATE TABLE HOMEPAGE.NR_SCHEDULER_LMPR (LEASENAME VARCHAR(254) NOT NULL,
              NAME VARCHAR(254) NOT NULL,
              VALUE VARCHAR(254) NOT NULL)IN NEWSTABSPACE;

CREATE INDEX HOMEPAGE.NR_SCHEDULER_LMPR_IDX1 ON HOMEPAGE.NR_SCHEDULER_LMPR (LEASENAME,
               NAME);

COMMIT;

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_SCHEDULER_TASK TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_SCHEDULER_TREG TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_SCHEDULER_LMGR TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_SCHEDULER_LMPR TO USER LCUSER;

--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
-- END THE DEFINITION FOR THE SEARCH DATABASE
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
-- STARTING OF THE DEFINITION FOR EVENT RECORD TABLES
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------
-- NEWS REPOSITORY - CREATE EVENT RECORDS TABLES
----------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_EVENT_RECORDS  (
	EVENT_RECORD_ID VARCHAR(36) NOT NULL,
	EVENT_RECORD_CONTENT BLOB,
	IS_ACL SMALLINT NOT NULL
)
IN NEWSTABSPACE;

----------------------------------------------------------------------------
-- NEWS REPOSITORY - EVENT RECORDS TABLE CONSTRAINTS
----------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_EVENT_RECORDS
    ADD CONSTRAINT "PK_EVENT_RECORD_ID" PRIMARY KEY("EVENT_RECORD_ID");    

-----------------------------------------------------------------------------
-- NEWS REPOSITORY -  RUN STATS
-----------------------------------------------------------------------------
RUNSTATS ON TABLE "HOMEPAGE"."NR_EVENT_RECORDS" FOR INDEXES ALL;

-----------------------------------------------------------------------------
-- NEWS REPOSITORY -  APP GRANTS STATS
-----------------------------------------------------------------------------
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_EVENT_RECORDS TO USER LCUSER;

COMMIT;

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
-- END OF DEFINITION FOR EVENT RECORDS TABLES
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
-- BEGINNNG OF DEFINITION OF INDEXES FOR NEWS RECORDS TABLE
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------

CREATE INDEX HOMEPAGE.NR_NEWS_RECORDS_READER_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS(READER_ID);

CREATE INDEX HOMEPAGE.NR_NEWS_RECORDS_ACTOR_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS(ACTOR_UUID);

CREATE INDEX HOMEPAGE.NR_NEWS_RECORDS_SOURCE_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS(SOURCE);

CREATE INDEX HOMEPAGE.NR_NEWS_RECORDS_CONTAINER_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS(CONTAINER_ID);
	
CREATE INDEX HOMEPAGE.NR_NEWS_RECORDS_DATE_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS(CREATION_DATE DESC);	

CREATE INDEX HOMEPAGE.NR_NEWS_RECORDS_EVENT_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS(EVENT_RECORD_UUID);	

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
-- END OF DEFINITION OF INDEXES FOR NEWS RECORDS TABLE
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate;
