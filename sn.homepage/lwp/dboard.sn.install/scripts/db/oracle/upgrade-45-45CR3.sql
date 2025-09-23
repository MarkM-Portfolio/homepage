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
-- HOMEPAGE.NR_AS_COUNTS
-----------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_AS_COUNTS (
	AS_COUNT_ID VARCHAR2(36) NOT NULL,
	PERSON_ID VARCHAR2(36) NOT NULL,	
	UI_VIEW NUMBER(5 ,0),
	COUNT NUMBER(10),
	ORGANIZATION_ID VARCHAR2(36) DEFAULT '00000000-0000-0000-0000-000000000000' NOT NULL
)	
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_AS_COUNTS 
  	ADD CONSTRAINT PK_AS_COUNT_ID PRIMARY KEY (AS_COUNT_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE";
  	
ALTER TABLE HOMEPAGE.NR_AS_COUNTS
    ADD CONSTRAINT FK_AS_COUNT_ORG_ID FOREIGN KEY (ORGANIZATION_ID) REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);
    
ALTER TABLE HOMEPAGE.NR_AS_COUNTS
    ADD CONSTRAINT FK_AS_COUNT_PER_ID FOREIGN KEY (PERSON_ID) REFERENCES HOMEPAGE.PERSON (PERSON_ID);    

CREATE UNIQUE INDEX HOMEPAGE.NR_COUNT_PER_VIEW_UNQ 
	ON HOMEPAGE.NR_AS_COUNTS (PERSON_ID, UI_VIEW) TABLESPACE "NEWSINDEXTABSPACE";
	
CREATE INDEX HOMEPAGE.NR_AS_COUNT_PER_IDX
	ON HOMEPAGE.NR_AS_COUNTS (PERSON_ID) TABLESPACE "NEWSINDEXTABSPACE";
	
CREATE INDEX HOMEPAGE.NR_AS_COUNT_ORG_IDX
	ON HOMEPAGE.NR_AS_COUNTS (ORGANIZATION_ID) TABLESPACE "NEWSINDEXTABSPACE";
	
COMMIT;

ALTER TABLE HOMEPAGE.NR_AS_COUNTS ENABLE ROW MOVEMENT;

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_AS_COUNTS TO HOMEPAGEUSER_ROLE;

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 211
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 211 , RELEASEVER = '4.5.0.0'
WHERE   DBSCHEMAVER = 210;

--------------------------------------
-- COMMIT
--------------------------------------

COMMIT;
--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;
