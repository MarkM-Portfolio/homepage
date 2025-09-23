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
--The following updates are needed in order to track visitor / external user, as well as the list of roles associated with a given user:
--* Add a column IS_EXTERNAL to the PERSON table. This will store a boolean (SMALLINT)


-- Adding IS_EXTERNAL
ALTER TABLE HOMEPAGE.PERSON ADD IS_EXTERNAL NUMERIC(5 ,0) DEFAULT 0 NOT NULL;
GO

COMMIT;

BEGIN TRANSACTION
GO

UPDATE HOMEPAGE.PERSON
SET    IS_EXTERNAL = '0' WHERE IS_EXTERNAL IS NULL;
GO

COMMIT;

GO

BEGIN TRANSACTION
GO

------------------------------------------------
-- PERSON
------------------------------------------------
CREATE TABLE HOMEPAGE.PERSON_ROLE  (
	PERSON_ROLE_ID NVARCHAR(36) NOT NULL PRIMARY KEY,
	ROLE NVARCHAR(256) NOT NULL,
	PERSON_ID NVARCHAR(36) NOT NULL,
	ORGANIZATION_ID NVARCHAR(36) DEFAULT '00000000-0000-0000-0000-000000000000'
) ON [PRIMARY]
GO
    
ALTER TABLE HOMEPAGE.PERSON_ROLE ADD CONSTRAINT FK_PERSON_ROLE_ORG_ID FOREIGN KEY (ORGANIZATION_ID) REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID)
GO

ALTER TABLE HOMEPAGE.PERSON_ROLE ADD CONSTRAINT FK_PERSON_ROLE_PER_ID FOREIGN KEY (PERSON_ID) REFERENCES HOMEPAGE.PERSON (PERSON_ID)
GO

CREATE INDEX PERSON_ROLE_PER_IDX
    ON HOMEPAGE.PERSON_ROLE (PERSON_ID);
    
CREATE INDEX PERSON_ROLE_PER_R_IDX
    ON HOMEPAGE.PERSON_ROLE (PERSON_ID, PERSON_ROLE_ID);
    
CREATE UNIQUE INDEX PERSON_ROLE_ROLE_IDX
    ON HOMEPAGE.PERSON_ROLE (PERSON_ID, ROLE);
    
GO

 



