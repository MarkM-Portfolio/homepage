-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2014, 2015                             
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

-- 139069: Refactoring of two unique indexes
DROP INDEX HOMEPAGE.SEQ_NUMBER_UNIQUE;

CREATE UNIQUE INDEX HOMEPAGE.SEQ_NUMBER_UNIQUE
    ON HOMEPAGE.EMD_TRANCHE (SEQ_NUMBER, ORGANIZATION_ID) TABLESPACE "NEWSINDEXTABSPACE";
    
COMMIT;    

-- 103979: Store creation date for item and correlated item in Activity Stream tables   
ALTER TABLE HOMEPAGE.NR_STORIES 
	ADD ITEM_CREATION_DATE TIMESTAMP
	ADD ITEM_CORRELATION_CREATION_DATE TIMESTAMP;

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES 
	ADD ITEM_CORRELATION_CREATION_DATE TIMESTAMP;

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE 
	ADD ITEM_CORRELATION_CREATION_DATE TIMESTAMP;

COMMIT;

-- 139426: DAO: Table storing whether a given story is read by a given user

---------------------------------------------------------------
-- HOMEPAGE.NR_READ_STATUS
---------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_READ_STATUS (
	READ_STATUS_ID VARCHAR2(36) NOT NULL,
	PERSON_ID VARCHAR2(36) NOT NULL,
	STORY_ID VARCHAR2(36) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	ORGANIZATION_ID VARCHAR2(36) NOT NULL
)
TABLESPACE "NEWSREGTABSPACE"; 

ALTER TABLE HOMEPAGE.NR_READ_STATUS
  	ADD (CONSTRAINT PK_READ_STATUS_ID PRIMARY KEY(READ_STATUS_ID)  USING INDEX TABLESPACE "NEWSINDEXTABSPACE");
  	
ALTER TABLE HOMEPAGE.NR_READ_STATUS
  	ADD CONSTRAINT FK_READ_STATUS_PER FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);  
	
ALTER TABLE HOMEPAGE.NR_READ_STATUS
  	ADD CONSTRAINT FK_READ_STATUS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
ALTER TABLE HOMEPAGE.NR_READ_STATUS
  	ADD CONSTRAINT FK_READ_STATUS_ORG FOREIGN KEY (ORGANIZATION_ID)
	REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);
	
CREATE UNIQUE INDEX HOMEPAGE.READ_STATUS_PER_STR_UNQ_IDX
	ON HOMEPAGE.NR_READ_STATUS (PERSON_ID, STORY_ID)  TABLESPACE "NEWSINDEXTABSPACE";	
	
CREATE INDEX HOMEPAGE.READ_STATUS_ORG_IDX
	ON HOMEPAGE.NR_READ_STATUS (ORGANIZATION_ID)  TABLESPACE "NEWSINDEXTABSPACE";	
	
ALTER TABLE HOMEPAGE.NR_READ_STATUS ENABLE ROW MOVEMENT;

COMMIT;	
