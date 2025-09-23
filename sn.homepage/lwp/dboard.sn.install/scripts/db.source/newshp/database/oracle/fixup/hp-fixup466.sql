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
ALTER TABLE HOMEPAGE.PERSON ADD IS_EXTERNAL NUMBER(5,0) DEFAULT 0 NOT NULL;
COMMIT;
 
DECLARE CURSOR cur IS SELECT PERSON_ID FROM HOMEPAGE.PERSON FOR UPDATE;


                BEGIN
                        FOR rec IN cur LOOP
                                UPDATE HOMEPAGE.PERSON SET IS_EXTERNAL = '0' WHERE CURRENT OF cur;
                        END LOOP;
						COMMIT;
                END;
 /
COMMIT;
                
          


-- Adding PERSON_ROLE
--* Add a table PERSON_ROLE with:
--** PERSON_ID - foreign key to PERSON table
--** ROLE - VARCHAR 256
--** + one column for the primary key

------------------------------------------------
-- PERSON
------------------------------------------------

CREATE TABLE HOMEPAGE.PERSON_ROLE  (
	PERSON_ROLE_ID VARCHAR2(36) NOT NULL,
	ROLE VARCHAR2(256) NOT NULL,
	PERSON_ID VARCHAR2(36) NOT NULL,
	ORGANIZATION_ID VARCHAR2(36) DEFAULT '00000000-0000-0000-0000-000000000000'
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.PERSON_ROLE 
    ADD CONSTRAINT PK_PERSON_ROLE_ID PRIMARY KEY(PERSON_ROLE_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE";
    
ALTER TABLE HOMEPAGE.PERSON_ROLE ADD CONSTRAINT FK_PERSON_ROLE_ORG_ID FOREIGN KEY (ORGANIZATION_ID) REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);

ALTER TABLE HOMEPAGE.PERSON_ROLE ADD CONSTRAINT FK_PERSON_ROLE_PER_ID FOREIGN KEY (PERSON_ID) REFERENCES HOMEPAGE.PERSON (PERSON_ID);

CREATE INDEX HOMEPAGE.PERSON_ROLE_PER_IDX
    ON HOMEPAGE.PERSON_ROLE (PERSON_ID) TABLESPACE "NEWSINDEXTABSPACE";
    
CREATE INDEX HOMEPAGE.PERSON_ROLE_PER_R_IDX
    ON HOMEPAGE.PERSON_ROLE (PERSON_ID, PERSON_ROLE_ID) TABLESPACE "NEWSINDEXTABSPACE";
    
CREATE UNIQUE INDEX HOMEPAGE.PERSON_ROLE_ROLE_IDX
    ON HOMEPAGE.PERSON_ROLE (PERSON_ID, ROLE) TABLESPACE "NEWSINDEXTABSPACE";

