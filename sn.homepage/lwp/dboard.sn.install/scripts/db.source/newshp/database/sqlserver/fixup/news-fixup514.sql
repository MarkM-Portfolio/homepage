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

DROP INDEX SEQ_NUMBER_UNIQUE ON HOMEPAGE.EMD_TRANCHE;

CREATE UNIQUE INDEX SEQ_NUMBER_UNIQUE
    ON HOMEPAGE.EMD_TRANCHE (SEQ_NUMBER, ORGANIZATION_ID);
    
-- 103979: Store creation date for item and correlated item in Activity Stream tables   
ALTER TABLE HOMEPAGE.NR_STORIES 
	ADD 	ITEM_CREATION_DATE DATETIME,
			ITEM_CORRELATION_CREATION_DATE DATETIME;
GO

ALTER TABLE HOMEPAGE.NR_ENTRIES 
	ADD 	ITEM_CORRELATION_CREATION_DATE DATETIME;
GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE 
	ADD 	ITEM_CORRELATION_CREATION_DATE DATETIME;
GO

-- 139426: DAO: Table storing whether a given story is read by a given user

---------------------------------------------------------------
-- HOMEPAGE.NR_READ_STATUS
---------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_READ_STATUS (
	READ_STATUS_ID nvarchar(36) NOT NULL,
	PERSON_ID nvarchar(36) NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	ORGANIZATION_ID nvarchar(36) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_READ_STATUS
  	ADD CONSTRAINT PK_READ_STATUS_ID PRIMARY KEY(READ_STATUS_ID);
  	
ALTER TABLE HOMEPAGE.NR_READ_STATUS
  	ADD CONSTRAINT FK_READ_STATUS_PER FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);  
	
ALTER TABLE HOMEPAGE.NR_READ_STATUS
  	ADD CONSTRAINT FK_READ_STATUS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
ALTER TABLE HOMEPAGE.NR_READ_STATUS
  	ADD CONSTRAINT FK_READ_STATUS_ORG FOREIGN KEY (ORGANIZATION_ID)
	REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);
	
CREATE UNIQUE INDEX READ_STATUS_PER_STR_UNQ_IDX
	ON HOMEPAGE.NR_READ_STATUS (PERSON_ID, STORY_ID);  
	
CREATE INDEX READ_STATUS_ORG_IDX
	ON HOMEPAGE.NR_READ_STATUS (ORGANIZATION_ID);
	

GO