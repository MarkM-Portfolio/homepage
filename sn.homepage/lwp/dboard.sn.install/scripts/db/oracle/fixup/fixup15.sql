-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2001, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- 5724_S68                                              
-- 1) Adding column IS_CONTAINER to table NR_NEWS_RECORDS
-----------------------------------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
	ADD IS_CONTAINER NUMBER(5,0) DEFAULT 0 NOT NULL;

-----------------------------------------------------------------------------------------------------------
-- 2) Adding column IS_CNAME_RTL and IS_ENAME_RTL to table NR_SOURCE
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_SOURCE
	ADD IS_CNAME_RTL NUMBER(5,0) DEFAULT 0 NOT NULL;

ALTER TABLE HOMEPAGE.NR_SOURCE
	ADD IS_ENAME_RTL NUMBER(5,0) DEFAULT 0 NOT NULL;

-----------------------------------------------------------------------------------------------------------
-- 3) Adding column NR_SUBSCRIPTION to table NR_SUBSCRIPTION
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_SUBSCRIPTION
	ADD IS_ACTIVE NUMBER(5,0) DEFAULT 1 NOT NULL;

-----------------------------------------------------------------------------------------------------------
-- 4) Adding column STATUS to table EMD_JOBS_STATS
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.EMD_JOBS_STATS
	ADD STATUS VARCHAR2(36) DEFAULT 'COMPLETED' NOT NULL;

-----------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------
--  START SEARCH
-----------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------
-- 4) Adding column ENABLED to table SR_TASKDEF
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.SR_TASKDEF
	ADD ENABLED NUMBER(5,0) DEFAULT 1 NOT NULL;
	
-----------------------------------------------------------------------------------------------------------
-- 5) Adding column CLAIMED and CLAIMED_TIME to table SR_FILESCONTENT
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD CLAIMED NUMBER(5,0) DEFAULT 0 NOT NULL;
	
ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD CLAIMED_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL;

-----------------------------------------------------------------------------------------------------------
-- 6) Adding table SR_MIGTASKDEFINFO
-----------------------------------------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.SR_MIGTASKDEFINFO (
	MIGTASKDEFINFO_ID VARCHAR2(36) NOT NULL,
	NUM_TASKS_MIG NUMBER(10,0) DEFAULT 1 NOT NULL  
)
TABLESPACE "HOMEPAGEREGTABSPACE";

-----------------------------------------------------------------------------------------------------------
-- 6) Update the search SR_ALLTASKSDEF adding T1.ENABLED
-----------------------------------------------------------------------------------------------------------
DROP VIEW HOMEPAGE.SR_ALLTASKSDEF;

CREATE VIEW HOMEPAGE.SR_ALLTASKSDEF AS
(
	SELECT 	T1.TASK_ID  		AS	PARENT_TASK_ID,
	T1.TASK_NAME 				AS	PARENT_TASK_NAME,
	T1.INTERVAL 				AS	PARENT_TASK_INTERVAL,
	T1.STARTBY	 				AS	PARENT_TASK_STARTBY,
	T1.TASK_TYPE 				AS	PARENT_TASK_TYPE, 
    T1.ENABLED 					AS  PARENT_TASK_ENABLED,
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
 	T3.ENABLED 			AS  PARENT_TASK_ENABLED,
	''							AS 	INDEXING_TASK_SERVICES,
	0							AS	INDEXING_TASK_OPTIMIZE,
	''							AS	INDEXING_TASK_ID,
	T4.OPTIMIZE_TASK_ID 		AS	OPTIMIZE_TASK_ID,
	T4.OPTIMIZE_TASK_ID			AS	CHILDTASK_PK
	FROM   HOMEPAGE.SR_TASKDEF T3,HOMEPAGE.SR_OPTIMIZETASKDEF T4
	WHERE  T3.TASK_ID=T4.TASK_ID
);

-----------------------------------------------------------------------------------------------------------
-- 7) Adding the new constraint for the PK
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.SR_MIGTASKDEFINFO
	ADD (CONSTRAINT "PK_MIGTASKDEF_ID" PRIMARY KEY("MIGTASKDEFINFO_ID")
	USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");

-----------------------------------------------------------------------------------------------------------
-- 8) Grant for the new table SR_MIGTASKDEFINFO
-----------------------------------------------------------------------------------------------------------
ALTER TABLE "HOMEPAGE"."SR_MIGTASKDEFINFO" ENABLE ROW MOVEMENT;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.SR_ALLTASKSDEF TO HOMEPAGEUSER_ROLE;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.SR_MIGTASKDEFINFO TO HOMEPAGEUSER_ROLE;

-----------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------
--  END SEARCH
-----------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------
-- x) Upgrade fixup number to 15
-----------------------------------------------------------------------------------------------------------

-- Updating the schema version from 14 to 15
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 15
WHERE   DBSCHEMAVER = 14;

COMMIT;

--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;