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
USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO

---------------------------------------------------------------------------------
------------------------ START SEARCH -------------------------------------------
---------------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.SR_FILECONTENTTASKDEF (
	FILECONTENT_TASK_ID nvarchar(36) NOT NULL,	
	TASK_ID nvarchar(36) NOT NULL,
	FILE_CONTENT_TASK_SERVICES nvarchar(256) NOT NULL,
	CONTENT_FAILURES_ONLY NUMERIC(5,0) NOT NULL	
) ON [PRIMARY]
GO

DROP VIEW HOMEPAGE.SR_ALLTASKSDEF
GO

CREATE VIEW HOMEPAGE.SR_ALLTASKSDEF 	AS
(
	SELECT 	T1.TASK_ID  			AS	PARENT_TASK_ID,
	T1.TASK_NAME 					AS	PARENT_TASK_NAME,
	T1.INTERVAL 					AS	PARENT_TASK_INTERVAL,
	T1.STARTBY	 					AS	PARENT_TASK_STARTBY,
	T1.TASK_TYPE 					AS	PARENT_TASK_TYPE,
    T1.ENABLED 						AS  PARENT_TASK_ENABLED,
	T2.INDEXING_TASK_SERVICES		AS	INDEXING_TASK_SERVICES,
	T2.INDEXING_TASK_OPTIMIZE		AS	INDEXING_TASK_OPTIMIZE,
	T2.INDEXING_TASK_ID				AS	INDEXING_TASK_ID,
	''								AS	OPTIMIZE_TASK_ID,
	'' 								AS 	FILECONTENT_TASK_ID,
	''								AS	FILE_CONTENT_TASK_SERVICES,
	0								AS 	CONTENT_FAILURES_ONLY,
	T2.INDEXING_TASK_ID				AS	CHILDTASK_PK
	FROM    HOMEPAGE.SR_TASKDEF T1,
			HOMEPAGE.SR_INDEXINGTASKDEF T2 
	WHERE T1.TASK_ID=T2.TASK_ID
) 
UNION 
(
	SELECT T3.TASK_ID				AS 	PARENT_TASK_ID,
	T3.TASK_NAME 					AS 	PARENT_TASK_NAME,
	T3.INTERVAL						AS 	PARENT_TASK_INTERVAL,
	T3.STARTBY 						AS	PARENT_TASK_STARTBY,
	T3.TASK_TYPE 					AS 	PARENT_TASK_TYPE,
 	T3.ENABLED 						AS  PARENT_TASK_ENABLED,
	''								AS 	INDEXING_TASK_SERVICES,
	0								AS	INDEXING_TASK_OPTIMIZE,
	''								AS	INDEXING_TASK_ID,
	T4.OPTIMIZE_TASK_ID 			AS	OPTIMIZE_TASK_ID,
	'' 								AS 	FILECONTENT_TASK_ID,
	''								AS	FILE_CONTENT_TASK_SERVICES,
	0								AS 	CONTENT_FAILURES_ONLY,
	T4.OPTIMIZE_TASK_ID				AS	CHILDTASK_PK
	FROM   	HOMEPAGE.SR_TASKDEF T3,
			HOMEPAGE.SR_OPTIMIZETASKDEF T4
	WHERE  T3.TASK_ID=T4.TASK_ID
)
UNION 
(
	SELECT T5.TASK_ID				AS 	PARENT_TASK_ID,
	T5.TASK_NAME 					AS 	PARENT_TASK_NAME,
	T5.INTERVAL						AS 	PARENT_TASK_INTERVAL,
	T5.STARTBY 						AS	PARENT_TASK_STARTBY,
	T5.TASK_TYPE 					AS 	PARENT_TASK_TYPE,
 	T5.ENABLED 						AS  PARENT_TASK_ENABLED,
	''								AS 	INDEXING_TASK_SERVICES,
	0								AS	INDEXING_TASK_OPTIMIZE,
	''								AS	INDEXING_TASK_ID,
	''								AS	OPTIMIZE_TASK_ID,
	T6.FILECONTENT_TASK_ID 			AS 	FILECONTENT_TASK_ID,
	T6.FILE_CONTENT_TASK_SERVICES	AS	FILE_CONTENT_TASK_SERVICES,
	T6.CONTENT_FAILURES_ONLY		AS 	CONTENT_FAILURES_ONLY,
	T6.FILECONTENT_TASK_ID			AS	CHILDTASK_PK
	FROM   	HOMEPAGE.SR_TASKDEF T5,
			HOMEPAGE.SR_FILECONTENTTASKDEF T6
	WHERE  T5.TASK_ID=T6.TASK_ID
)
GO

ALTER TABLE HOMEPAGE.SR_FILECONTENTTASKDEF
	ADD CONSTRAINT PK_FC_TASK_ID PRIMARY KEY (FILECONTENT_TASK_ID)
GO

ALTER TABLE HOMEPAGE.SR_FILECONTENTTASKDEF
	ADD CONSTRAINT UNIQUE_TASK_ID_FC UNIQUE (TASK_ID)
GO
	
ALTER TABLE HOMEPAGE.SR_FILECONTENTTASKDEF
	ADD CONSTRAINT FK_FC_TASK_ID FOREIGN KEY (TASK_ID) 
	REFERENCES  HOMEPAGE.SR_TASKDEF(TASK_ID) ON DELETE CASCADE
GO
	
---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------

---------------------------------------------------------------------------------
------------------------ START NEWS ---------------------------------------------
---------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
	ADD ITEM_ID nvarchar(36);

ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
	ADD ITEM_CORRELATION_ID nvarchar(36);

---------------------------------------------------------------------------------
------------------------ END NEWS -----------------------------------------------
---------------------------------------------------------------------------------

-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 30
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 30 , RELEASEVER = '3.0.0'
WHERE   DBSCHEMAVER = 20;

---------------------------------------------------------------------------------
---------------------------------------------------------------------------------
---------------------------------------------------------------------------------

--------------------------------------
-- COMMIT
--------------------------------------

COMMIT;