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

-- HOMEPAGE.NR_AS_COUNTS
-----------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_AS_COUNTS (
	AS_COUNT_ID nvarchar(36) NOT NULL,
	PERSON_ID nvarchar(36) NOT NULL,	
	UI_VIEW NUMERIC(5 ,0),
	COUNT NUMERIC(10),
	ORGANIZATION_ID nvarchar(36) DEFAULT '00000000-0000-0000-0000-000000000000' NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_AS_COUNTS 
  	ADD CONSTRAINT PK_AS_COUNT_ID PRIMARY KEY (AS_COUNT_ID);
  	
ALTER TABLE HOMEPAGE.NR_AS_COUNTS
    ADD CONSTRAINT FK_AS_COUNT_ORG_ID FOREIGN KEY (ORGANIZATION_ID) REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);
    
ALTER TABLE HOMEPAGE.NR_AS_COUNTS
    ADD CONSTRAINT FK_AS_COUNT_PER_ID FOREIGN KEY (PERSON_ID) REFERENCES HOMEPAGE.PERSON (PERSON_ID);    

CREATE UNIQUE INDEX NR_COUNT_PER_VIEW_UNQ 
	ON HOMEPAGE.NR_AS_COUNTS (PERSON_ID, UI_VIEW);
	
CREATE INDEX NR_AS_COUNT_PER_IDX
	ON HOMEPAGE.NR_AS_COUNTS (PERSON_ID);
	
CREATE INDEX NR_AS_COUNT_ORG_IDX
	ON HOMEPAGE.NR_AS_COUNTS (ORGANIZATION_ID);
	
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_AS_COUNTS TO HOMEPAGEUSER;
GO
  
  
  