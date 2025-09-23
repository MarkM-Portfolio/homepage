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
ALTER TABLE HOMEPAGE.PERSON ADD COLUMN IS_EXTERNAL SMALLINT DEFAULT 0 NOT NULL@
COMMIT@

REORG TABLE HOMEPAGE.PERSON@
COMMIT@

EXPORT TO data.tmp.ixf OF IXF METHOD N (
        PERSON_ID, IS_EXTERNAL
)
SELECT PERSON_ID, 0 FROM HOMEPAGE.PERSON WITH UR@
COMMIT@

IMPORT FROM data.tmp.ixf OF IXF METHOD N 
	        ( 
	        PERSON_ID, IS_EXTERNAL 
	        ) 
ALLOW NO ACCESS COMMITCOUNT 2000 
INSERT_UPDATE INTO HOMEPAGE.PERSON 
	        ( 
	        PERSON_ID, IS_EXTERNAL 
	        )@ 
COMMIT@ 

-- Adding PERSON_ROLE
--* Add a table PERSON_ROLE with:
--** PERSON_ID - foreign key to PERSON table
--** ROLE - VARCHAR 256
--** + one column for the primary key

------------------------------------------------
-- PERSON
------------------------------------------------
CREATE TABLE HOMEPAGE.PERSON_ROLE  (
	PERSON_ROLE_ID VARCHAR(36) NOT NULL,
	ROLE VARCHAR(256) NOT NULL,
	PERSON_ID VARCHAR(36) NOT NULL,
	ORGANIZATION_ID VARCHAR(36) DEFAULT '00000000-0000-0000-0000-000000000000'
)
IN HOMEPAGETABSPACE@

ALTER TABLE HOMEPAGE.PERSON_ROLE 
    ADD CONSTRAINT "PK_PERSON_ROLE_ID" PRIMARY KEY("PERSON_ROLE_ID")@
    
ALTER TABLE HOMEPAGE.PERSON_ROLE ADD CONSTRAINT FK_PERSON_ROLE_ORG_ID FOREIGN KEY (ORGANIZATION_ID) REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID)@

ALTER TABLE HOMEPAGE.PERSON_ROLE ADD CONSTRAINT FK_PERSON_ROLE_PER_ID FOREIGN KEY (PERSON_ID) REFERENCES HOMEPAGE.PERSON (PERSON_ID)@

CREATE INDEX HOMEPAGE.PERSON_ROLE_PER_IDX
    ON HOMEPAGE.PERSON_ROLE (PERSON_ID)@
    
CREATE INDEX HOMEPAGE.PERSON_ROLE_PER_R_IDX
    ON HOMEPAGE.PERSON_ROLE (PERSON_ID, PERSON_ROLE_ID)@
    
CREATE UNIQUE INDEX HOMEPAGE.PERSON_ROLE_ROLE_IDX
    ON HOMEPAGE.PERSON_ROLE (PERSON_ID, ROLE)@