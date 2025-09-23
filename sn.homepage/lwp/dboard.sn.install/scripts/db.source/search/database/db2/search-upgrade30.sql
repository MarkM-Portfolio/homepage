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

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start SEARCH FIXUP 30-32
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

---------------------------------------------------------------------------------
------------------------ START SEARCH -------------------------------------------
---------------------------------------------------------------------------------

------------------------------------------------
-- HOMEPAGE.SR_FILESCONTENT
------------------------------------------------

-- SPR# MPML7UVM9X
-- Missing index on SR_FILESCONTENT causes full table scan
CREATE INDEX HOMEPAGE.SR_FILESCONTENT_IS_CURRENT_IDX 
ON HOMEPAGE.SR_FILESCONTENT(IS_CURRENT);

DELETE FROM HOMEPAGE.SR_FILESCONTENT;

------------------------------------------------
-- HOMEPAGE.SR_FILECONTENTTASKDEF
------------------------------------------------

CREATE TABLE HOMEPAGE.SR_FILECONTENTTASKDEF (
	FILECONTENT_TASK_ID VARCHAR(36) NOT NULL,	
	TASK_ID VARCHAR(36) NOT NULL,
	FILE_CONTENT_TASK_SERVICES VARCHAR(256) NOT NULL,
	CONTENT_FAILURES_ONLY SMALLINT NOT NULL	
)
IN HOMEPAGETABSPACE;

ALTER TABLE HOMEPAGE.SR_FILECONTENTTASKDEF
	ADD CONSTRAINT "PK_FC_TASK_ID" PRIMARY KEY ("FILECONTENT_TASK_ID");

ALTER TABLE HOMEPAGE.SR_FILECONTENTTASKDEF
	ADD CONSTRAINT "UNIQUE_TASK_ID_FC" UNIQUE ("TASK_ID");
	
ALTER TABLE HOMEPAGE.SR_FILECONTENTTASKDEF
	ADD CONSTRAINT "FK_FC_TASK_ID" FOREIGN KEY ("TASK_ID") 
	REFERENCES  HOMEPAGE.SR_TASKDEF("TASK_ID") ON DELETE CASCADE;

------------------------------------------------
-- HOMEPAGE.SR_BACKUPTASKDEF
------------------------------------------------

CREATE TABLE HOMEPAGE.SR_BACKUPTASKDEF (
	BACKUP_TASK_ID VARCHAR(36) NOT NULL,	
	TASK_ID VARCHAR(36) NOT NULL,
	TYPE VARCHAR(36) NOT NULL,
	SCRIPT VARCHAR(256)
)
IN HOMEPAGETABSPACE;

ALTER TABLE HOMEPAGE.SR_BACKUPTASKDEF
	ADD CONSTRAINT "PK_BKUP_TASK_ID" PRIMARY KEY ("BACKUP_TASK_ID");	

ALTER TABLE HOMEPAGE.SR_BACKUPTASKDEF
	ADD CONSTRAINT "UNIQUE_TASK_ID_BKP" UNIQUE ("TASK_ID");
	
ALTER TABLE HOMEPAGE.SR_BACKUPTASKDEF
	ADD CONSTRAINT "FK_BKUP_TASK_ID" FOREIGN KEY ("TASK_ID") 
	REFERENCES  HOMEPAGE.SR_TASKDEF("TASK_ID") ON DELETE CASCADE;

------------------------------------------------
-- HOMEPAGE.SR_ALLTASKSDEF
------------------------------------------------

DROP VIEW HOMEPAGE.SR_ALLTASKSDEF;

CREATE VIEW HOMEPAGE.SR_ALLTASKSDEF AS
(
	SELECT 	T1.TASK_ID  		AS	PARENT_TASK_ID,
	T1.TASK_NAME 				AS	PARENT_TASK_NAME,
	T1.INTERVAL 				AS	PARENT_TASK_INTERVAL,
	T1.STARTBY	 				AS	PARENT_TASK_STARTBY,
	T1.TASK_TYPE 				AS	PARENT_TASK_TYPE,
	T1.ENABLED					AS  	PARENT_TASK_ENABLED,
	T2.INDEXING_TASK_SERVICES	AS	INDEXING_TASK_SERVICES,
	T2.INDEXING_TASK_OPTIMIZE	AS	INDEXING_TASK_OPTIMIZE,
	T2.INDEXING_TASK_ID			AS	INDEXING_TASK_ID,
	''							AS	OPTIMIZE_TASK_ID,
	'' 							AS 	FILECONTENT_TASK_ID,
	''							AS				FILE_CONTENT_TASK_SERVICES,
	0							AS 	CONTENT_FAILURES_ONLY,
	'' 							AS 	BACKUP_TASK_ID,
	''							AS	BACKUP_TASK_TYPE,
	''							AS	BACKUP_TASK_SCRIPT,
	T2.INDEXING_TASK_ID			AS	CHILDTASK_PK	
	FROM    HOMEPAGE.SR_TASKDEF T1,HOMEPAGE.SR_INDEXINGTASKDEF T2 
	WHERE T1.TASK_ID=T2.TASK_ID
) 
UNION 
(
	SELECT T3.TASK_ID		AS 	PARENT_TASK_ID,
	T3.TASK_NAME 			AS 	PARENT_TASK_NAME,
	T3.INTERVAL				AS 	PARENT_TASK_INTERVAL,
	T3.STARTBY 				AS	PARENT_TASK_STARTBY,
	T3.TASK_TYPE 			AS 	PARENT_TASK_TYPE,
 	T3.ENABLED 				AS  	PARENT_TASK_ENABLED,
	''						AS 	INDEXING_TASK_SERVICES,
	0						AS	INDEXING_TASK_OPTIMIZE,
	''						AS	INDEXING_TASK_ID,
	T4.OPTIMIZE_TASK_ID 	AS	OPTIMIZE_TASK_ID,
	'' 						AS 	FILECONTENT_TASK_ID,
	''						AS	FILE_CONTENT_TASK_SERVICES,
	0						AS	CONTENT_FAILURES_ONLY,
	'' 						AS 	BACKUP_TASK_ID,
	''						AS	BACKUP_TASK_TYPE,
	''						AS	BACKUP_TASK_SCRIPT,
	T4.OPTIMIZE_TASK_ID		AS	CHILDTASK_PK
	FROM   HOMEPAGE.SR_TASKDEF T3,HOMEPAGE.SR_OPTIMIZETASKDEF T4
	WHERE  T3.TASK_ID=T4.TASK_ID
)
UNION 
(
	SELECT T5.TASK_ID				AS	PARENT_TASK_ID,
	T5.TASK_NAME 					AS	PARENT_TASK_NAME,
	T5.INTERVAL						AS	PARENT_TASK_INTERVAL,
	T5.STARTBY 						AS	PARENT_TASK_STARTBY,
	T5.TASK_TYPE 					AS	PARENT_TASK_TYPE,
 	T5.ENABLED 						AS	PARENT_TASK_ENABLED,
	''								AS	INDEXING_TASK_SERVICES,
	0								AS	INDEXING_TASK_OPTIMIZE,
	''								AS	INDEXING_TASK_ID,
	''								AS	OPTIMIZE_TASK_ID,
	T6.FILECONTENT_TASK_ID 			AS	FILECONTENT_TASK_ID,
	T6.FILE_CONTENT_TASK_SERVICES	AS	FILE_CONTENT_TASK_SERVICES,
	T6.CONTENT_FAILURES_ONLY		AS	CONTENT_FAILURES_ONLY,
	'' 								AS 	BACKUP_TASK_ID,
	''								AS	BACKUP_TASK_TYPE,
	''								AS	BACKUP_TASK_SCRIPT,
	T6.FILECONTENT_TASK_ID			AS	CHILDTASK_PK
	FROM   HOMEPAGE.SR_TASKDEF T5,HOMEPAGE.SR_FILECONTENTTASKDEF T6
	WHERE  T5.TASK_ID=T6.TASK_ID
)
UNION 
(
	SELECT T7.TASK_ID		AS 	PARENT_TASK_ID,
	T7.TASK_NAME 			AS 	PARENT_TASK_NAME,
	T7.INTERVAL				AS 	PARENT_TASK_INTERVAL,
	T7.STARTBY 				AS	PARENT_TASK_STARTBY,
	T7.TASK_TYPE 			AS 	PARENT_TASK_TYPE,
 	T7.ENABLED 				AS	PARENT_TASK_ENABLED,
	''						AS 	INDEXING_TASK_SERVICES,
	0						AS	INDEXING_TASK_OPTIMIZE,
	''						AS	INDEXING_TASK_ID,
	''						AS	OPTIMIZE_TASK_ID,
	''		 				AS 	FILECONTENT_TASK_ID,
	''						AS	FILE_CONTENT_TASK_SERVICES,
	0						AS	CONTENT_FAILURES_ONLY,
	T8.BACKUP_TASK_ID		AS 	BACKUP_TASK_ID,
	T8.TYPE					AS	BACKUP_TASK_TYPE,
	T8.SCRIPT				AS	BACKUP_TASK_SCRIPT,
	T8.BACKUP_TASK_ID		AS	CHILDTASK_PK
	FROM   HOMEPAGE.SR_TASKDEF T7,HOMEPAGE.SR_BACKUPTASKDEF T8
	WHERE  T7.TASK_ID=T8.TASK_ID
);

------------------------------------------------
-- HOMEPAGE.SR_INDEX_MANAGEMENT
------------------------------------------------

CREATE TABLE HOMEPAGE.SR_INDEX_MANAGEMENT (
	NODE_ID VARCHAR(36) NOT NULL,
	LAST_CRAWLING_VERSION BIGINT NOT NULL,
	OUT_OF_DATE SMALLINT NOT NULL
)
IN HOMEPAGETABSPACE;

ALTER TABLE HOMEPAGE.SR_INDEX_MANAGEMENT
	ADD CONSTRAINT "PK_INDEX_MGMT_ID" PRIMARY KEY ("NODE_ID");
	
------------------------------------------------
-- HOMEPAGE.SR_RESUME_TOKENS
------------------------------------------------

CREATE TABLE HOMEPAGE.SR_RESUME_TOKENS (
	TOKEN_ID VARCHAR(36) NOT NULL,
	NODE_ID VARCHAR(36) NOT NULL,
	TOKEN VARCHAR(256) NOT NULL,
	SERVICE VARCHAR(36) NOT NULL
)
IN HOMEPAGETABSPACE;


ALTER TABLE HOMEPAGE.SR_RESUME_TOKENS
	ADD CONSTRAINT "PK_TOKEN_ID" PRIMARY KEY ("TOKEN_ID");


ALTER TABLE HOMEPAGE.SR_RESUME_TOKENS
	ADD CONSTRAINT "FK_RT_IDX_MGMT_ID" FOREIGN KEY ("NODE_ID")
	REFERENCES HOMEPAGE.SR_INDEX_MANAGEMENT("NODE_ID") ON DELETE CASCADE;

------------------------------------------------
-- HOMEPAGE.SR_INDEX_DOCS
------------------------------------------------

CREATE TABLE HOMEPAGE.SR_INDEX_DOCS(
	DOCUMENT_ID VARCHAR(36) NOT NULL,
	DOCUMENT BLOB NOT NULL,
	CRAWLING_VERSION BIGINT NOT NULL,
	ACTION SMALLINT NOT NULL,
	UPDATE_TIME  TIMESTAMP NOT NULL,
	RESUME_POINT VARCHAR(256),
	SERVICE VARCHAR(36) NOT NULL,
	FILESCONTENT_ID VARCHAR(36)
)
IN HOMEPAGETABSPACE;

ALTER TABLE HOMEPAGE.SR_INDEX_DOCS
	ADD CONSTRAINT "PK_INDEX_DOCS_ID" PRIMARY KEY ("DOCUMENT_ID");

CREATE INDEX HOMEPAGE.SR_INDEX_CRAWL_VERSION_IDX
	ON HOMEPAGE.SR_INDEX_DOCS (CRAWLING_VERSION);

------------------------------------------------
-- HOMEPAGE.SR_FACET_DOCS
------------------------------------------------

CREATE TABLE HOMEPAGE.SR_FACET_DOCS(
	FACET_ID VARCHAR(36) NOT NULL,
	DOCUMENT_ID VARCHAR(36) NOT NULL,
	FACET BLOB NOT NULL,
	CRAWLING_VERSION BIGINT NOT NULL
)
IN HOMEPAGETABSPACE;
	
ALTER TABLE HOMEPAGE.SR_FACET_DOCS
	ADD CONSTRAINT "PK_FACET_DOCS_ID" PRIMARY KEY ("FACET_ID");
	
-- This statement will be included in FIXUP32
--ALTER TABLE HOMEPAGE.SR_FACET_DOCS
--	ADD CONSTRAINT "FK_IDX_DOC_ID" FOREIGN KEY ("DOCUMENT_ID") 
--	REFERENCES  HOMEPAGE.SR_INDEX_DOCS("DOCUMENT_ID") ON DELETE CASCADE;

CREATE INDEX HOMEPAGE.SR_FACET_DOCS_PARENT_IDX
		ON HOMEPAGE.SR_FACET_DOCS (DOCUMENT_ID);


------------------------------------------
-- START GRANTS
------------------------------------------

{DB2_GRANT_START} HOMEPAGE.SR_ALLTASKSDEF {DB2_GRANT_STOP}

{DB2_GRANT_START} HOMEPAGE.SR_FILECONTENTTASKDEF {DB2_GRANT_STOP}

{DB2_GRANT_START} HOMEPAGE.SR_INDEX_DOCS {DB2_GRANT_STOP}

{DB2_GRANT_START} HOMEPAGE.SR_FACET_DOCS {DB2_GRANT_STOP}

{DB2_GRANT_START} HOMEPAGE.SR_RESUME_TOKENS {DB2_GRANT_STOP}

{DB2_GRANT_START} HOMEPAGE.SR_BACKUPTASKDEF {DB2_GRANT_STOP}

{DB2_GRANT_START} HOMEPAGE.SR_INDEX_MANAGEMENT {DB2_GRANT_STOP}

	
---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start SEARCH FIXUP 32
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.search-fixup32.sql}

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start SEARCH FIXUP 33
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
{include.search-fixup33.sql}


-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start SEARCH FIXUP 40
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
{include.search-fixup40.sql}

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start SEARCH FIXUP 42
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
{include.search-fixup42.sql}

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start SEARCH FIXUP 45
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
{include.search-fixup45.sql}

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start SEARCH FIXUP 49
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
{include.search-fixup49.sql}

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start SEARCH FIXUP 53
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
{include.search-fixup53.sql}

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start SEARCH FIXUP 55
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
{include.search-fixup55.sql}