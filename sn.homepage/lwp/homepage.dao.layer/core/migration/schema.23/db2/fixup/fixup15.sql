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
-- 1) Adding column IS_CONTAINER to table NR_NEWS_RECORDS
-----------------------------------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
	ADD COLUMN IS_CONTAINER SMALLINT NOT NULL DEFAULT 0;

-----------------------------------------------------------------------------------------------------------
-- 2) Adding column IS_CNAME_RTL and IS_ENAME_RTL to table NR_SOURCE
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_SOURCE
	ADD COLUMN IS_CNAME_RTL SMALLINT NOT NULL DEFAULT 0;

ALTER TABLE HOMEPAGE.NR_SOURCE
	ADD COLUMN IS_ENAME_RTL SMALLINT NOT NULL DEFAULT 0;

-----------------------------------------------------------------------------------------------------------
-- 3) Adding column NR_SUBSCRIPTION to table NR_SUBSCRIPTION
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_SUBSCRIPTION
	ADD COLUMN IS_ACTIVE SMALLINT NOT NULL DEFAULT 1;

-----------------------------------------------------------------------------------------------------------
-- 4) Adding column STATUS to table EMD_JOBS_STATS
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.EMD_JOBS_STATS
	ADD COLUMN STATUS VARCHAR(36) NOT NULL DEFAULT 'COMPLETED';

-----------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------
--  START SEARCH
-----------------------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------
-- 4) Adding column ENABLED to table SR_TASKDEF
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.SR_TASKDEF
	ADD COLUMN ENABLED SMALLINT NOT NULL DEFAULT 1;
	
-----------------------------------------------------------------------------------------------------------
-- 5) Adding column CLAIMED and CLAIMED_TIME to table SR_FILESCONTENT
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD COLUMN CLAIMED SMALLINT NOT NULL DEFAULT 0;
	
ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD COLUMN CLAIMED_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;

-----------------------------------------------------------------------------------------------------------
-- 6) Adding table SR_MIGTASKDEFINFO
-----------------------------------------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.SR_MIGTASKDEFINFO (
	MIGTASKDEFINFO_ID VARCHAR(36) NOT NULL,
	NUM_TASKS_MIG INTEGER NOT NULL DEFAULT 1  
)
IN HOMEPAGETABSPACE;

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
	SELECT T3.TASK_ID	AS 	PARENT_TASK_ID,
	T3.TASK_NAME 		AS 	PARENT_TASK_NAME,
	T3.INTERVAL			AS 	PARENT_TASK_INTERVAL,
	T3.STARTBY 			AS	PARENT_TASK_STARTBY,
	T3.TASK_TYPE 		AS 	PARENT_TASK_TYPE,
 	T3.ENABLED 			AS  PARENT_TASK_ENABLED,
	''					AS 	INDEXING_TASK_SERVICES,
	0					AS	INDEXING_TASK_OPTIMIZE,
	''					AS	INDEXING_TASK_ID,
	T4.OPTIMIZE_TASK_ID AS	OPTIMIZE_TASK_ID,
	T4.OPTIMIZE_TASK_ID	AS	CHILDTASK_PK
	FROM   HOMEPAGE.SR_TASKDEF T3,HOMEPAGE.SR_OPTIMIZETASKDEF T4
	WHERE  T3.TASK_ID=T4.TASK_ID
);

-----------------------------------------------------------------------------------------------------------
-- 7) Adding the new constraint for the PK
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.SR_MIGTASKDEFINFO
	ADD CONSTRAINT "PK_MIGTASKDEF_ID" PRIMARY KEY("MIGTASKDEFINFO_ID");

-----------------------------------------------------------------------------------------------------------
-- 8) Grant for the new table SR_MIGTASKDEFINFO
-----------------------------------------------------------------------------------------------------------
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.SR_ALLTASKSDEF TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.SR_MIGTASKDEFINFO TO USER LCUSER;

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
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate;
