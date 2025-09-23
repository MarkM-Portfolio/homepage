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

--------------------------------------------------------------
-- 1) ADDING NEW COLUMN TO THE NR_COMM_FOLLOW TABLE (FROM FIXUP 58)
--------------------------------------------------------------
-- it is already in 3.0.1
--ALTER TABLE HOMEPAGE.NR_COMM_FOLLOW
--	ADD COLUMN COMMUNITY_NAME VARCHAR(256);

COMMIT;

--------------------------------------------------------------
-- 2) ADDING NEW COLUMN (HAS_ATTACHMENT) TO THE STORIES TABLES
--------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_ACT
	ADD COLUMN HAS_ATTACHMENT SMALLINT DEFAULT 0;
	
reorg table HOMEPAGE.NR_SRC_STORIES_ACT use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_ACT;
COMMIT;

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_BLG
	ADD COLUMN HAS_ATTACHMENT SMALLINT DEFAULT 0;

reorg table HOMEPAGE.NR_SRC_STORIES_BLG use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_BLG;
COMMIT;

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_COM
	ADD COLUMN HAS_ATTACHMENT SMALLINT DEFAULT 0;

reorg table HOMEPAGE.NR_SRC_STORIES_COM use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_COM;
COMMIT;
	
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_WIK
	ADD COLUMN HAS_ATTACHMENT SMALLINT DEFAULT 0;

reorg table HOMEPAGE.NR_SRC_STORIES_WIK use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_WIK;
COMMIT;
	
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_PRF
	ADD COLUMN HAS_ATTACHMENT SMALLINT DEFAULT 0;

reorg table HOMEPAGE.NR_SRC_STORIES_PRF use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_PRF;
COMMIT;
	
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_HP
	ADD COLUMN HAS_ATTACHMENT SMALLINT DEFAULT 0;

reorg table HOMEPAGE.NR_SRC_STORIES_HP use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_HP;
COMMIT;
	
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_DGR
	ADD COLUMN HAS_ATTACHMENT SMALLINT DEFAULT 0;

reorg table HOMEPAGE.NR_SRC_STORIES_DGR use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_DGR;
COMMIT;
	
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_FILE
	ADD COLUMN HAS_ATTACHMENT SMALLINT DEFAULT 0;

reorg table HOMEPAGE.NR_SRC_STORIES_FILE use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_FILE;
COMMIT;
	
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_FRM
	ADD COLUMN HAS_ATTACHMENT SMALLINT DEFAULT 0;

reorg table HOMEPAGE.NR_SRC_STORIES_FRM use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_FRM;
COMMIT;

----------------------------------------------------------------
-- 3) ADDING TABLE FOR PROFILES STORIES COMMENTS 
----------------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_NEWS_PRF_COMMENT (
	NEWS_COMMENT_ID VARCHAR(36) NOT NULL,
	ACTOR_UUID VARCHAR(36) NOT NULL,
	CREATION_DATE TIMESTAMP,
	BRIEF_DESC VARCHAR(500),
	STORY_ID  VARCHAR(36),
	SOURCE_TYPE SMALLINT NOT NULL,
	ITEM_ID  VARCHAR(36),
	ITEM_CORRELATION_ID VARCHAR(36),
	ITEM_URL VARCHAR(2048),
	CONTENT BLOB(1M)
)
IN NEWSTABSPACE;

ALTER TABLE HOMEPAGE.NR_NEWS_PRF_COMMENT
  	ADD CONSTRAINT "PK_NEWS_COMMENT_ID" PRIMARY KEY("NEWS_COMMENT_ID");

ALTER TABLE HOMEPAGE.NR_NEWS_PRF_COMMENT
  	ADD CONSTRAINT "FK_STORY_ID" FOREIGN KEY ("STORY_ID")
	REFERENCES HOMEPAGE.NR_SRC_STORIES_PRF ("STORY_ID");
  	
CREATE INDEX HOMEPAGE.PRF_COMMENT_STORY_ID
    ON HOMEPAGE.NR_NEWS_PRF_COMMENT (STORY_ID);

CREATE INDEX HOMEPAGE.NR_NEWS_PRF_COMMENT_DATE
    ON HOMEPAGE.NR_NEWS_PRF_COMMENT (CREATION_DATE ASC);     

COMMIT;
	
----------------------------------------------------------------
-- 4) ADDING NR_ATTACHMENT - CAN RELATE TO ANY STORIES TABLE
----------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_ATTACHMENT (
	ATTACHMENT_ID VARCHAR(36) NOT NULL,
	STORY_ID VARCHAR(36) NOT NULL,
	SOURCE_TYPE SMALLINT NOT NULL,
	ATTACHMENT_TYPE SMALLINT,
	CREATION_DATE TIMESTAMP,
	FILE_NAME VARCHAR(2048),
	FILE_DESC VARCHAR(4000),	
	FILE_ID  VARCHAR(128),
	REPO_ID VARCHAR(128)
)
IN NEWSTABSPACE;

ALTER TABLE HOMEPAGE.NR_ATTACHMENT 
    ADD CONSTRAINT PK_ATTACHMENT PRIMARY KEY(ATTACHMENT_ID);

CREATE INDEX HOMEPAGE.NR_ATT_STORY_ID
    ON HOMEPAGE.NR_ATTACHMENT (STORY_ID);    

COMMIT;

----------------------------------------------------------------
-- 5) ADDING NR_RECOMMENDATION - CAN RELATE TO ANY STORY TABLE
----------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_RECOMMENDATION  (
	RECOMMENDATION_ID VARCHAR(36) NOT NULL, --primary key
	RECOMMENDER_ID VARCHAR(36) NOT NULL, -- PERSON_ID of the recommender, FK to PERSON table
	STORY_ID VARCHAR(36) NOT NULL, 
	SOURCE_TYPE SMALLINT NOT NULL, 
	CREATION_DATE TIMESTAMP
)
IN NEWS4TABSPACE;

ALTER TABLE HOMEPAGE.NR_RECOMMENDATION 
    ADD CONSTRAINT PK_RECOMMENDATION PRIMARY KEY(RECOMMENDATION_ID);

CREATE INDEX HOMEPAGE.NR_REC_STORY_ID
    ON HOMEPAGE.NR_RECOMMENDATION (STORY_ID);
    
CREATE UNIQUE INDEX HOMEPAGE.NR_RECOMMENDER_STORY_ID
    ON HOMEPAGE.NR_RECOMMENDATION (RECOMMENDER_ID, STORY_ID);

COMMIT;
    
  

