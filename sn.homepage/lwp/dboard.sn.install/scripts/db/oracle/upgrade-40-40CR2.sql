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
-- INCLUDE FIXUP 130 FOR NEWS
------------------------------------------------


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
---------------------------------------------------------------------------------
------------------------ START NEWS FIXUP 130 -----------------------------------
---------------------------------------------------------------------------------

-- 78618: [DB] Add columns in NR_ENTRIES/NR_ENTRIES_ARCHIVE tables to store total number of comments
ALTER TABLE HOMEPAGE.NR_ENTRIES ADD (
	PREV_COMMENT_NUM_REC NUMBER(10 ,0) DEFAULT NULL,
	LAST_COMMENT_NUM_REC NUMBER(10 ,0) DEFAULT NULL
);
COMMIT;


ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE ADD (
	PREV_COMMENT_NUM_REC NUMBER(10 ,0) DEFAULT NULL,
	LAST_COMMENT_NUM_REC NUMBER(10 ,0) DEFAULT NULL
);
COMMIT;


-- 78857: Remove FK_BRD_ENTRY_ID constraint on BOARD_RECOMMENDATIONS table
ALTER TABLE HOMEPAGE.BOARD_RECOMMENDATIONS 	DROP CONSTRAINT FK_BRD_ENTRY_ID;
COMMIT;
---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 130 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



------------------------------------------------
-- INCLUDE FIXUP 131 FOR NEWS
------------------------------------------------


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
---------------------------------------------------------------------------------
------------------------ START NEWS FIXUP 131 -----------------------------------
---------------------------------------------------------------------------------

------------------------------------------------
-- HOMEPAGE.BOARD_MENTIONS
------------------------------------------------
CREATE TABLE HOMEPAGE.BOARD_MENTIONS  (
	BOARD_MENTIONS_ID VARCHAR2(36) NOT NULL, --primary key
	ITEM_ID VARCHAR2(256) NOT NULL, 
	PERSON_ID VARCHAR2(36) NOT NULL, -- PERSON_ID of the recommender, FK to PERSON table
	LABEL VARCHAR2(4000),
	WAS_ITEM_VISIBLE NUMBER(5,0) DEFAULT 0 NOT NULL
)
TABLESPACE "BOARDREGTABSPACE";

ALTER TABLE HOMEPAGE.BOARD_MENTIONS 
    ADD (CONSTRAINT PK_BRD_MEN_ID PRIMARY KEY(BOARD_MENTIONS_ID) USING INDEX TABLESPACE "BOARDINDEXTABSPACE");

ALTER TABLE HOMEPAGE.BOARD_MENTIONS
	ADD CONSTRAINT FK_BRD_MEN_PER_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

CREATE INDEX HOMEPAGE.BRD_MEN_PER_IDX
	ON HOMEPAGE.BOARD_MENTIONS (PERSON_ID) TABLESPACE "BOARDINDEXTABSPACE";
	
CREATE INDEX HOMEPAGE.BRD_MEN_ITEM_IDX
	ON HOMEPAGE.BOARD_MENTIONS (ITEM_ID) TABLESPACE "BOARDINDEXTABSPACE";	
	
ALTER TABLE HOMEPAGE.BOARD_MENTIONS ENABLE ROW MOVEMENT;

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.BOARD_MENTIONS TO HOMEPAGEUSER_ROLE;

COMMIT;

INSERT INTO HOMEPAGE.NR_TEMPLATE (TEMPLATE_ID, NAME, FORMAT, DATA_SOURCE_STRING, NO_VALUES) 
VALUES ('mentionee1', 'mentionee1', 'profilePhoto', 'mentionee1', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE (TEMPLATE_ID, NAME, FORMAT, DATA_SOURCE_STRING, NO_VALUES) 
VALUES ('mentionee2', 'mentionee2', 'profilePhoto', 'mentionee2', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE (TEMPLATE_ID, NAME, FORMAT, DATA_SOURCE_STRING, NO_VALUES) 
VALUES ('mentionee3', 'mentionee3', 'profilePhoto', 'mentionee3', 1);

COMMIT;

UPDATE HOMEPAGE.PERSON SET MEMBER_TYPE = 3 WHERE PERSON_ID = '00000000-0000-0000-0000-000000000000';
COMMIT;

---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 131 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


------------------------------------------------
-- INCLUDE FIXUP 131 FOR SEARCH
------------------------------------------------


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
---------------------------------------------------------------------------------
------------------------ START SEARCH 131  ------ 
---------------------------------------------------------------------------------

--Defect 77766: Search slow query
CREATE INDEX HOMEPAGE.SR_INDEX_DOCS_SU_IDX 
	ON HOMEPAGE.SR_INDEX_DOCS (SERVICE, UPDATE_TIME) TABLESPACE "HOMEPAGEINDEXTABSPACE";

--SmartCloud Defect 96370
CREATE INDEX HOMEPAGE.SR_INDEX_DOCS_TAW_IDX 
	ON HOMEPAGE.SR_INDEX_DOCS	(UPDATE_TIME ASC, DOCUMENT_ID ASC, CRAWLING_VERSION ASC) TABLESPACE "HOMEPAGEINDEXTABSPACE";


---------------------------------------------------------------------------------
------------------------ END SEARCH 131 ---------------------------------------------
---------------------------------------------------------------------------------
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%






------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 131
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 132 , RELEASEVER = '4.0.0'
WHERE   DBSCHEMAVER = 110;

--------------------------------------
-- COMMIT
--------------------------------------

COMMIT;
--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;
