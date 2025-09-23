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

-----------------------------------------------------------
-- HOMEPAGE.NR_AS_COUNTS
-----------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_AS_COUNTS (
	AS_COUNT_ID VARCHAR(36) CCSID 1208 NOT NULL,
	PERSON_ID VARCHAR(36) CCSID 1208 NOT NULL,	
	UI_VIEW SMALLINT,
	COUNT INTEGER,
	ORGANIZATION_ID VARCHAR (36) CCSID 1208 NOT NULL DEFAULT '00000000-0000-0000-0000-000000000000'
)	
;

ALTER TABLE HOMEPAGE.NR_AS_COUNTS 
  	ADD CONSTRAINT PK_AS_COUNT_ID PRIMARY KEY (AS_COUNT_ID);
  	
ALTER TABLE HOMEPAGE.NR_AS_COUNTS
    ADD CONSTRAINT FK_AS_COUNT_ORG_ID FOREIGN KEY (ORGANIZATION_ID) REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);
    
ALTER TABLE HOMEPAGE.NR_AS_COUNTS
    ADD CONSTRAINT FK_AS_COUNT_PER_ID FOREIGN KEY (PERSON_ID) REFERENCES HOMEPAGE.PERSON (PERSON_ID);    

CREATE UNIQUE INDEX HOMEPAGE.NR_COUNT_PER_VIEW_UNQ 
	ON HOMEPAGE.NR_AS_COUNTS (PERSON_ID, UI_VIEW);
	
CREATE INDEX HOMEPAGE.NR_AS_COUNT_PER_IDX
	ON HOMEPAGE.NR_AS_COUNTS (PERSON_ID);
	
CREATE INDEX HOMEPAGE.NR_AS_COUNT_ORG_IDX
	ON HOMEPAGE.NR_AS_COUNTS (ORGANIZATION_ID);	
	
COMMIT;

--runstats on table HOMEPAGE.NR_AS_COUNTS with distribution and detailed indexes all allow write access;

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_AS_COUNTS TO LCUSER;
 

  
  
  