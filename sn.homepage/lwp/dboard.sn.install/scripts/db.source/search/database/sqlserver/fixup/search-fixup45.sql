-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2010, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

---------------------------------------------------------------------------------
------------------------ START SEARCH -------------------------------------------
---------------------------------------------------------------------------------


----------------------------------------
--  SR_INDEX_MANAGEMENT
----------------------------------------

DELETE FROM HOMEPAGE.SR_RESUME_TOKENS
GO

DELETE FROM HOMEPAGE.SR_INDEX_MANAGEMENT
GO

ALTER TABLE HOMEPAGE.SR_RESUME_TOKENS DROP CONSTRAINT FK_RT_IDX_MGMT_ID
GO

DROP TABLE HOMEPAGE.SR_INDEX_MANAGEMENT
GO

CREATE TABLE HOMEPAGE.SR_INDEX_MANAGEMENT (
	NODE_ID NVARCHAR(256) NOT NULL,
	LAST_CRAWLING_VERSION NUMERIC(19,0) NOT NULL,
	OUT_OF_DATE NUMERIC(5,0) NOT NULL,
	INDEXER  NUMERIC(5,0)  DEFAULT 1 NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.SR_INDEX_MANAGEMENT
	ADD CONSTRAINT PK_INDEX_MGMT_ID PRIMARY KEY (NODE_ID)
GO	

{SQL_GRANT_START} HOMEPAGE.SR_INDEX_MANAGEMENT {SQL_GRANT_STOP}
GO

----------------------------------------
--  HOMEPAGE.SR_RESUME_TOKENS
----------------------------------------

ALTER TABLE HOMEPAGE.SR_RESUME_TOKENS
ALTER COLUMN NODE_ID NVARCHAR(256) NOT NULL;
GO

ALTER TABLE HOMEPAGE.SR_RESUME_TOKENS
	ADD CONSTRAINT FK_RT_IDX_MGMT_ID FOREIGN KEY (NODE_ID)
	REFERENCES HOMEPAGE.SR_INDEX_MANAGEMENT(NODE_ID) ON DELETE CASCADE
GO	

----------------------------------------
--  SR_LOTUSCONNECTIONS*
----------------------------------------

{include.search-clearScheduler.sql}


---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------