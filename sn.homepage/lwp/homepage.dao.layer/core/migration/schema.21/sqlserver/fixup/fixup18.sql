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

USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO

-------------------------------------------------------
-- 1) SEARCH: Make the field STARTBY and INTERVAL nullable
--------------------------------------------------------

CREATE TABLE HOMEPAGE.TMP (
	TMP_TASK_ID nvarchar(36) NOT NULL,
	TMP_STARTBY nvarchar(256),
	TMP_INTERVAL nvarchar(256)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.TMP
    ADD CONSTRAINT PK_TMP PRIMARY KEY(TMP_TASK_ID);

INSERT INTO HOMEPAGE.TMP
	SELECT SR_TASKDEF.TASK_ID, SR_TASKDEF.STARTBY, SR_TASKDEF.INTERVAL
	FROM HOMEPAGE.SR_TASKDEF SR_TASKDEF;

ALTER TABLE HOMEPAGE.SR_TASKDEF
DROP COLUMN STARTBY;
GO

ALTER TABLE HOMEPAGE.SR_TASKDEF
ADD  STARTBY nvarchar(256);
GO

ALTER TABLE HOMEPAGE.SR_TASKDEF
DROP COLUMN INTERVAL;

ALTER TABLE HOMEPAGE.SR_TASKDEF
ADD  INTERVAL nvarchar(256);
GO

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
GO


-------------------------------------------------------
-- 2) SEARCH: Adding the CHECK_SR_TASKDEF check
--------------------------------------------------------
ALTER TABLE HOMEPAGE.SR_TASKDEF
    ADD CONSTRAINT CHECK_SR_TASKDEF
    CHECK ( (STARTBY IS NOT NULL AND INTERVAL IS NOT NULL) OR (STARTBY IS NULL AND INTERVAL IS NULL) );
GO
-------------------------------------------------------
-- 3) JUST SQLServer fixing table HOMEPAGE.SR_FILESCONTENT
-- CONTENT 		must be a 	NVARCHAR(MAX) NOT NULL,
-- CLAIMED_TIME must be a	DATETIME NOT NULL
-- for this fix we don't need to keep the old data we can just and drop those columns
--------------------------------------------------------

-- REMOVE AND ADD THE OLD 
-- COLUMN CONTENT
ALTER TABLE HOMEPAGE.SR_FILESCONTENT
DROP COLUMN CONTENT;
GO

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
ADD  CONTENT nvarchar(MAX) NOT NULL;
GO

-- REMOVE AND ADD THE OLD 
-- CLAIMED_TIME
ALTER TABLE HOMEPAGE.SR_FILESCONTENT
DROP COLUMN CLAIMED_TIME;
GO

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
ADD  CLAIMED_TIME DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL;
GO

-------------------------------------------------------------------------------
-- Updating the schema version from 17 to 18
-------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 18
WHERE   DBSCHEMAVER = 17;
GO

COMMIT;