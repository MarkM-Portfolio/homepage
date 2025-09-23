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

-----------------------------------------------------------------------------------------------------------
-- 1) Adding search table SR_FILESCONTENT
-----------------------------------------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.SR_FILESCONTENT (
	FILESCONTENT_ID nvarchar(36) NOT NULL,
	URL nvarchar(256) NOT NULL,
	COMPONENT_UUID nvarchar(36) NOT NULL,
	COMPONENT nvarchar(36) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	LAST_MODIFIED_DATE DATETIME NOT NULL,
	LAST_ACCESSED_DATE DATETIME NOT NULL,
	IS_CURRENT NUMERIC(5,0) NOT NULL,
	CONTENT varbinary (MAX) NOT NULL,
	FILE_SIZE NUMERIC(19,0) NOT NULL,
	CONTENT_SIZE NUMERIC(19,0) NOT NULL,
	INPUT_MIME_TYPE nvarchar(36) NOT NULL
)  ON [PRIMARY]
GO

-----------------------------------------------------------------------------------------------------------
-- 2) Adding search constraints for table SR_FILESCONTENT
-----------------------------------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD CONSTRAINT PK_FILESCONTENT_ID PRIMARY KEY (FILESCONTENT_ID)
GO

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD CONSTRAINT UNIQUE_COMP_UUID UNIQUE (COMPONENT_UUID,COMPONENT)
GO

-----------------------------------------------------------------------------------------------------------
-- 3) Grant the user for the new table SR_FILESCONTENT
-----------------------------------------------------------------------------------------------------------
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.SR_FILESCONTENT TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.SR_ALLTASKSDEF TO HOMEPAGEUSER
GO

-----------------------------------------------------------------------------------------------------------
-- 4) Upgrade fixup number to 14
-----------------------------------------------------------------------------------------------------------

-- Updating the schema version from 13 to 14
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 14
WHERE   DBSCHEMAVER = 13
GO


COMMIT;