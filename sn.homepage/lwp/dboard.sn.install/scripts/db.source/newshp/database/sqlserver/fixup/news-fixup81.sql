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
--	ADD COMMUNITY_NAME VARCHAR(256);

--------------------------------------------------------------
-- 2) ADDING NEW COLUMN (HAS_ATTACHMENT) TO THE STORIES TABLES
--------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_ACT
	ADD HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0;
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_BLG
	ADD HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0;
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_COM
	ADD HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0;
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_WIK
	ADD HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0;
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_PRF
	ADD HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0;
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_HP
	ADD HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0;
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_DGR
	ADD HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0;
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_FILE
	ADD HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0;
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_FRM
	ADD HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0;
GO


----------------------------------------------------------------
-- 3) ADDING TABLE FOR PROFILES STORIES COMMENTS 
----------------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_NEWS_PRF_COMMENT (
	NEWS_COMMENT_ID nvarchar(36) NOT NULL,
	ACTOR_UUID nvarchar(36) NOT NULL,
	CREATION_DATE DATETIME,
	BRIEF_DESC nvarchar(500),
	STORY_ID  nvarchar(36),
	SOURCE_TYPE NUMERIC (5,0) NOT NULL,
	ITEM_ID  nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_URL nvarchar(2048),
	CONTENT  varbinary (MAX)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_NEWS_PRF_COMMENT
  	ADD CONSTRAINT PK_NEWS_PRF_COMMENT_ID PRIMARY KEY(NEWS_COMMENT_ID);

ALTER TABLE HOMEPAGE.NR_NEWS_PRF_COMMENT
  	ADD CONSTRAINT FK_PRF_STORY_ID FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_SRC_STORIES_PRF (STORY_ID);

CREATE INDEX PRF_COMMENT_STORY_ID
    ON HOMEPAGE.NR_NEWS_PRF_COMMENT (STORY_ID);

CREATE INDEX NR_NEWS_PRF_COMMENT_DATE
    ON HOMEPAGE.NR_NEWS_PRF_COMMENT (CREATION_DATE ASC);     
GO
	
----------------------------------------------------------------
-- 4) ADDING NR_ATTACHMENT - CAN RELATE TO ANY STORIES TABLE
----------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_ATTACHMENT (
	ATTACHMENT_ID nvarchar(36) NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC (5,0) NOT NULL,
	ATTACHMENT_TYPE NUMERIC (5,0),
	CREATION_DATE DATETIME,
	FILE_NAME nvarchar(2048),
	FILE_DESC nvarchar(4000),	
	FILE_ID  nvarchar(128),
	REPO_ID nvarchar(128)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_ATTACHMENT 
    ADD CONSTRAINT PK_ATTACHMENT PRIMARY KEY(ATTACHMENT_ID);

CREATE INDEX NR_ATT_STORY_ID
    ON HOMEPAGE.NR_ATTACHMENT (STORY_ID);
GO

----------------------------------------------------------------
-- 5) ADDING NR_RECOMMENDATION - CAN RELATE TO ANY STORY TABLE
----------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_RECOMMENDATION  (
	RECOMMENDATION_ID nvarchar(36) NOT NULL, --primary key
	RECOMMENDER_ID nvarchar(36) NOT NULL, -- PERSON_ID of the recommender, FK to PERSON table
	STORY_ID nvarchar(36) NOT NULL, 
	SOURCE_TYPE NUMERIC (5,0) NOT NULL, 
	CREATION_DATE DATETIME
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_RECOMMENDATION 
    ADD CONSTRAINT PK_RECOMMENDATION PRIMARY KEY(RECOMMENDATION_ID);

CREATE INDEX NR_REC_STORY_ID
    ON HOMEPAGE.NR_RECOMMENDATION (STORY_ID);
    
CREATE UNIQUE INDEX NR_RECOMMENDER_STORY_ID
    ON HOMEPAGE.NR_RECOMMENDATION (RECOMMENDER_ID, STORY_ID);
GO



