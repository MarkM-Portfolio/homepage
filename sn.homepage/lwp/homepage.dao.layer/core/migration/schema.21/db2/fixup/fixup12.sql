-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2008, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

connect to HOMEPAGE;

-----------------------------------------------------------------------------------------------------------
-- 1 Remove and add the new templates
-----------------------------------------------------------------------------------------------------------
-- Removing and adding the new templates
DELETE FROM HOMEPAGE.NR_TEMPLATE;

------------ 
--- START INSERT TEMPLATES
------------

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actor-Bei35oPldKwZTaR7aAiPFw4L08CyRW','actor', 'actorExtID', 'person', 1); 

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actorProfile-CAsyXgPhQd7N3wSMw7C0IUe','actorProfiles', 'actorExtID', 'profilePhoto', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('target-Ah3vEOPQZDEpgpwcQ2JNaHFfMnMcG','subject', 'targetSubjectExtIDs', 'profilePhoto', 3);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actEnt-anXIr0xP82OSZnuoYbAZa2M5AsRFr', 'activityEntry', 'activity.entry.name;activity.entry.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actEntComm-WEJHX1TBvSCW0PS8ayfbPlZ1k','activityEntryWithComment', 'activity.entry.name;activity.entry.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actCont-WHHEhu6HTkRWtCQPylcUdSENF4mU', 'activityContainerName', 'activity.name;activity.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actCont-Mg2xRFBLmRqg3Zj7Etrdttiyc6OH','activityContainerNameACL', 'activity.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actToDo-psOHtvbY4lOJv8jOXJH5YgLoGYP7','toDoEntry', 'activity.todo.name;activity.todo.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blog-Itf9INx14G6bbCWRYNRpLhSawJXF8Qs','blogContainerName', 'blog.name;blog.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogNew-qsgRrfp88AKWys4wcm4gIuZm3T2p','newBlogContainer', 'blog.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogEntry-ubpjmKeG6Vi8XpQXYKVx3mtCqm','blogEntry', 'blog.entry.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogComment-92JYbZEOrk0CBLEJHYwkrqPA','blogEntryWithComment', 'blog.entry.name;blog.comment.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-Qf0I5rSEaImjaCcds3HYi4SeCGYCDCN','newCommunityContainer', 'community.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-new-IduwKhHDNCLdmJFi0G7lwrE86IJ','communityContainerName', 'community.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-topic6V808ijHf9TRgUSUqVVZoSOGg5','topicEntryName', 'community.topic.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-book-VSWMFF7uZVaE11XRe4Q1ElJIc6','communityBookmarkEntryName', 'community.bookmark.name;community.bookmark.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-feed-Ja6XFBT7kyfEJv07Unitc0MsJT','feedEntryName', 'community.feed.name;community.feed.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('dogear-book-DXAWZl1FIGMuMr4Ea4Lejbum','dogearBookmarkEntry', 'dogear.bookmark.title;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('tags-a7YmLTHTY2FdHKPsHU0wbAZCkgYrkk1','tag', 'tags', 'plain', 3);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('profileEntry-Vrh9M5YNmxNogF2oERZWyRE','profileLinkEntry', 'profiles.link.title;profiles.link.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('profile-status-HNX0o3AF32cFuPWhtYMjl','profileStatusEntry', 'status', 'plain', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-9qgQLuYLDdh66pf82um3c6q1lQ0gZl5','wikiContainerName','wiki.name;wiki.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-page-P75oD6oAdgoWCRNt4YYh7hZfuM','wikiEntryPage', 'wiki.page.name;wiki.page.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-comment-Ye2xdX8pbYM996pc7je30LI','wikiEntryPageCommented', 'wiki.page.name;wiki.page.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('file-bbqZpR6oxvE2sYAXxasKS0EtS1mDg4h','fileEntry', 'file.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('collection-7jEWoKkWx8ucNTo6Z7AndhRFh','collectionContainer', 'files.collection.name;htmlURL', 'link', 1);

------------
--- END INSERT TEMPLATES
------------


-----------------------------------------------------------------------------------------------------------
-- 2 News Repository ( add three new columns)
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
	ADD COLUMN TEXT_META_TEMPLATE CLOB(16000);

ALTER TABLE HOMEPAGE.NR_SOURCE
	ADD COLUMN IS_PRIVATE SMALLINT;

ALTER TABLE HOMEPAGE.NR_SOURCE
	ADD COLUMN LAST_UPDATE TIMESTAMP;


-----------------------------------------------------------------------------------------------------------
-- 3 Fixing SPR: SPR #RHLI7QXJHA  News Repository: several new indexes are needed on Table NR_NEWS_RECORDS 
-----------------------------------------------------------------------------------------------------------
CREATE INDEX HOMEPAGE.NR_READ_SOUR_ACTR_IS_PUB_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS (READER_ID, SOURCE, ACTOR_UUID, IS_PUBLIC);

CREATE INDEX HOMEPAGE.NR_READER_ER_UUID_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS (READER_ID, EVENT_RECORD_UUID);
	
CREATE INDEX HOMEPAGE.NR_READER_IS_TOP_STORY_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS (READER_ID, IS_TOP_STORY);

CREATE INDEX HOMEPAGE.NR_READER_IS_SAVED_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS (READER_ID, IS_SAVED);




-----------------------------------------------------------------------------------------------------------
-- 4 Email Digest 
--		a) add a job frequency scale value, 
--		b) rename the exec_time into exec_time_seconds, 
--   	c) remove a not null constraints
-----------------------------------------------------------------------------------------------------------

-- add scale value 
ALTER TABLE HOMEPAGE.EMD_JOBS
	ADD COLUMN JOB_FREQUENCY_SCALE VARCHAR(36) NOT NULL DEFAULT '';

-- change to nullable NEXT_EXEC_TIME
ALTER TABLE HOMEPAGE.EMD_JOBS
	DROP COLUMN NEXT_EXEC_TIME;
	
ALTER TABLE HOMEPAGE.EMD_JOBS
	ADD COLUMN NEXT_EXEC_TIME TIMESTAMP;

-- rename AVG_EXEC_JOB to AVG_EXEC_TIME_SECONDS
ALTER TABLE HOMEPAGE.EMD_JOBS
	DROP COLUMN AVG_EXEC_JOB;

ALTER TABLE HOMEPAGE.EMD_JOBS
	ADD COLUMN AVG_EXEC_TIME_SECONDS INTEGER;

-- rename EXEC_TIME to AVG_EXEC_JOB_SECONDS
ALTER TABLE HOMEPAGE.EMD_JOBS_STATS
	DROP COLUMN EXEC_TIME;

ALTER TABLE HOMEPAGE.EMD_JOBS_STATS
	ADD COLUMN EXEC_TIME_SECONDS INTEGER;
	 
		
-----------------------------------------------------------------------------------------------------------
-- 5 Email Digest - init the table data for email digest
-----------------------------------------------------------------------------------------------------------
REORG TABLE HOMEPAGE.EMD_JOBS;
REORG TABLE HOMEPAGE.EMD_JOBS_STATS;

INSERT INTO HOMEPAGE.EMD_JOBS 
        (JOB_ID, JOB_FREQUENCY, JOB_FREQUENCY_SCALE, N_PERSONS_TO_UPDATE, AVG_EXEC_TIME_SECONDS)
VALUES  ('1_job_freq_l_day_fddsfdsx4a43x82aaxb', 1, 'D', 0, 0);

INSERT INTO HOMEPAGE.EMD_JOBS 
        (JOB_ID, JOB_FREQUENCY, JOB_FREQUENCY_SCALE, N_PERSONS_TO_UPDATE, AVG_EXEC_TIME_SECONDS)
VALUES  ('2_job_freq_7_day_fddsfdsx4a43x82aaxb', 7, 'D', 0, 0);



-----------------------------------------------------------------------------------------------------------
-- 6 Adding the new set of tables for search 
-----------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------
-- SEARCH -  CREATE THE TABLES
----------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.SR_TASKDEF (
	TASK_ID VARCHAR(36) NOT NULL,
	TASK_NAME VARCHAR(256) NOT NULL,
	STARTBY VARCHAR(256) NOT NULL,
	INTERVAL VARCHAR(256) NOT NULL,
	TASK_TYPE VARCHAR(36) NOT NULL
)
IN HOMEPAGETABSPACE;

CREATE TABLE HOMEPAGE.SR_INDEXINGTASKDEF (
	INDEXING_TASK_ID VARCHAR(36) NOT NULL,
	TASK_ID VARCHAR(36) NOT NULL,
	INDEXING_TASK_SERVICES VARCHAR(256) NOT NULL,
	INDEXING_TASK_OPTIMIZE SMALLINT NOT NULL	
)
IN HOMEPAGETABSPACE;

CREATE TABLE HOMEPAGE.SR_OPTIMIZETASKDEF (
	OPTIMIZE_TASK_ID VARCHAR(36) NOT NULL,	
	TASK_ID VARCHAR(36) NOT NULL	
)
IN HOMEPAGETABSPACE;

CREATE VIEW HOMEPAGE.SR_ALLTASKSDEF AS
(
	SELECT 	T1.TASK_ID  		AS	PARENT_TASK_ID,
	T1.TASK_NAME 				AS	PARENT_TASK_NAME,
	T1.INTERVAL 				AS	PARENT_TASK_INTERVAL,
	T1.STARTBY	 				AS	PARENT_TASK_STARTBY,
	T1.TASK_TYPE 				AS	PARENT_TASK_TYPE, 
	T2.INDEXING_TASK_SERVICES	AS	INDEXING_TASK_SERVICES,
	T2.INDEXING_TASK_OPTIMIZE	AS	INDEXING_TASK_OPTIMIZE,
	T2.INDEXING_TASK_ID			AS	INDEXING_TASK_ID,
	''							AS	OPTIMIZE_TASK_ID,
	T2.INDEXING_TASK_ID			AS	CHILDTASK_PK
	FROM    HOMEPAGE.SR_TASKDEF T1,HOMEPAGE.SR_INDEXINGTASKDEF T2 
	WHERE T1.TASK_ID=T2.TASK_ID
) 
UNION 
(
	SELECT T3.TASK_ID			AS 	PARENT_TASK_ID,
	T3.TASK_NAME 				AS 	PARENT_TASK_NAME,
	T3.INTERVAL					AS 	PARENT_TASK_INTERVAL,
	T3.STARTBY 					AS	PARENT_TASK_STARTBY,
	T3.TASK_TYPE 				AS 	PARENT_TASK_TYPE, 
	''							AS 	INDEXING_TASK_SERVICES,
	0							AS	INDEXING_TASK_OPTIMIZE,
	''							AS	INDEXING_TASK_ID,
	T4.OPTIMIZE_TASK_ID 		AS	OPTIMIZE_TASK_ID,
	T4.OPTIMIZE_TASK_ID			AS	CHILDTASK_PK
	FROM   HOMEPAGE.SR_TASKDEF T3,HOMEPAGE.SR_OPTIMIZETASKDEF T4
	WHERE  T3.TASK_ID=T4.TASK_ID
);

----------------------------------------------------------------------------
-- SEARCH -  CREATE THE CONSTRAINTS
----------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.SR_TASKDEF
	 ADD CONSTRAINT "PK_TASK_ID" PRIMARY KEY ("TASK_ID");

ALTER TABLE HOMEPAGE.SR_INDEXINGTASKDEF
	ADD CONSTRAINT "PK_INDEX_TASK_ID" PRIMARY KEY ("INDEXING_TASK_ID");

ALTER TABLE HOMEPAGE.SR_OPTIMIZETASKDEF
	ADD CONSTRAINT "PK_OPT_TASK_ID" PRIMARY KEY ("OPTIMIZE_TASK_ID");

ALTER TABLE HOMEPAGE.SR_INDEXINGTASKDEF
	ADD CONSTRAINT "FK_INDEX_TASK_ID" FOREIGN KEY ("TASK_ID") 
	REFERENCES HOMEPAGE.SR_TASKDEF("TASK_ID") ON DELETE CASCADE;

ALTER TABLE HOMEPAGE.SR_OPTIMIZETASKDEF
	ADD CONSTRAINT "FK_OPT_TASK_ID" FOREIGN KEY ("TASK_ID") 
	REFERENCES  HOMEPAGE.SR_TASKDEF("TASK_ID") ON DELETE CASCADE;

ALTER TABLE HOMEPAGE.SR_TASKDEF
    ADD CONSTRAINT UNIQUE_TASK_NAME UNIQUE ("TASK_NAME");
    
ALTER TABLE HOMEPAGE.SR_INDEXINGTASKDEF
	ADD CONSTRAINT UNIQUE_TASK_ID_IND UNIQUE ("TASK_ID");

ALTER TABLE HOMEPAGE.SR_OPTIMIZETASKDEF
	ADD CONSTRAINT UNIQUE_TASK_ID_OPT UNIQUE ("TASK_ID");

-------------------------------------------------------------------
-- START GRANT LCUSER FOR THE SEARCH TABLES
-------------------------------------------------------------------
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.SR_TASKDEF TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.SR_OPTIMIZETASKDEF TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.SR_INDEXINGTASKDEF TO USER LCUSER;


-- Fixing a minor dbComparator issue
ALTER TABLE HOMEPAGE.EMD_JOBS
ALTER COLUMN JOB_FREQUENCY_SCALE DROP DEFAULT;

-----------------------------------------------------------------------------------------------------------
-- 7 Upgrade fixup number to 12
-----------------------------------------------------------------------------------------------------------

-- Updating the schema version from 11 to 12
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 12
WHERE   DBSCHEMAVER = 11;

COMMIT;

--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate; 

