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
connect to HOMEPAGE;

DROP VIEW HOMEPAGE.SR_ALLTASKSDEF;

-------------------------------------------------------
-- 1) SEARCH: Make the field STARTBY and INTERVAL nullable
--------------------------------------------------------

CREATE TABLE HOMEPAGE.TMP (
	TMP_TASK_ID VARCHAR(36) NOT NULL,
	TMP_STARTBY VARCHAR(256),
	TMP_INTERVAL VARCHAR(256)
)
IN HOMEPAGETABSPACE;


ALTER TABLE HOMEPAGE.TMP
    ADD CONSTRAINT "PK_TMP" PRIMARY KEY("TMP_TASK_ID");

INSERT INTO HOMEPAGE.TMP
	SELECT SR_TASKDEF.TASK_ID, SR_TASKDEF.STARTBY, SR_TASKDEF.INTERVAL
	FROM HOMEPAGE.SR_TASKDEF SR_TASKDEF;

ALTER TABLE HOMEPAGE.SR_TASKDEF
DROP COLUMN STARTBY;

ALTER TABLE HOMEPAGE.SR_TASKDEF
ADD COLUMN STARTBY VARCHAR(256);

ALTER TABLE HOMEPAGE.SR_TASKDEF
DROP COLUMN INTERVAL;

ALTER TABLE HOMEPAGE.SR_TASKDEF
ADD COLUMN INTERVAL VARCHAR(256);

-- runstats and reorg
RUNSTATS ON TABLE HOMEPAGE.SR_TASKDEF;
RUNSTATS ON TABLE HOMEPAGE.TMP;
RUNSTATS ON TABLE HOMEPAGE.SR_TASKDEF	FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.TMP			FOR INDEXES ALL;

reorg table HOMEPAGE.SR_TASKDEF 		use TEMPSPACE1;
reorg table HOMEPAGE.TMP 				use TEMPSPACE1;
reorg indexes all for table HOMEPAGE.SR_TASKDEF;
reorg indexes all for table HOMEPAGE.TMP;

-- moving back the data
UPDATE HOMEPAGE.SR_TASKDEF 
SET STARTBY = 	(  	SELECT TMP.TMP_STARTBY FROM HOMEPAGE.TMP TMP
                    WHERE TMP.TMP_TASK_ID = HOMEPAGE.SR_TASKDEF.TASK_ID
           		);

UPDATE HOMEPAGE.SR_TASKDEF 
SET INTERVAL = 	(	SELECT TMP.TMP_INTERVAL FROM HOMEPAGE.TMP TMP
                    WHERE TMP.TMP_TASK_ID = HOMEPAGE.SR_TASKDEF.TASK_ID
           		);

-- dropping the temp table
DROP TABLE HOMEPAGE.TMP;


-------------------------------------------------------
-- 2) SEARCH: Adding the CHECK_SR_TASKDEF check
--------------------------------------------------------
ALTER TABLE HOMEPAGE.SR_TASKDEF
    ADD CONSTRAINT "CHECK_SR_TASKDEF"
    CHECK ( (STARTBY IS NOT NULL AND INTERVAL IS NOT NULL) OR (STARTBY IS NULL AND INTERVAL IS NULL) );


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

-------------------------------------------------------------------------------
-- Updating the schema version from 17 to 18
-------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 18
WHERE   DBSCHEMAVER = 17;

--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate;