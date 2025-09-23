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

 
----------------------------------------------------------------------
-- 4) ADDING A UNIQUE CONSTRAINT on EMD_EMAIL_PREFES - PERSON ID
---------------------------------------------------------------------
ALTER TABLE HOMEPAGE.EMD_EMAIL_PREFS 
	ADD CONSTRAINT UNIQUE_PREFS UNIQUE ("PERSON_ID");

----------------------------------------------------------------------
-- 5) REMOVE NR_NEWS_COMMENT table
----------------------------------------------------------------------
DROP TABLE HOMEPAGE.NR_NEWS_COMMENT;

----------------------------------------------------------------------
-- 6) SET HP_UI.WELCOME MODE  to 1 for all the users 
----------------------------------------------------------------------
UPDATE HOMEPAGE.HP_UI SET WELCOME_MODE = 1;

----------------------------------------------------------------------
-- 7) DROP NOT NULL on NR_STORIES for column R_META_TEMPLATE 
----------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_FOLLOWED_STORIES DROP CONSTRAINT "FK_F_STORY_ID";
ALTER TABLE HOMEPAGE.NR_COMM_STORIES DROP CONSTRAINT "FK_COMM_STORY_ID";
ALTER TABLE HOMEPAGE.NR_ORGPERSON_STORIES DROP CONSTRAINT "FK_ORGP_STORY_ID";

DROP TABLE HOMEPAGE.NR_STORIES;

CREATE TABLE HOMEPAGE.NR_STORIES (
	STORY_ID VARCHAR2(36) NOT NULL,
	EVENT_NAME VARCHAR2(256) NOT NULL,
	SOURCE VARCHAR2(36),
	CONTAINER_ID VARCHAR2(36),	
	CONTAINER_NAME VARCHAR2(256),
	CONTAINER_URL VARCHAR2(2048),
	ITEM_NAME VARCHAR2(256),
	ITEM_URL VARCHAR2(2048),
	ITEM_ATOM_URL VARCHAR2(2048),
	ITEM_ID VARCHAR2(36), -- NEW
	ITEM_CORRELATION_ID VARCHAR2(36), -- NEW	
	CREATION_DATE TIMESTAMP NOT NULL,
	BRIEF_DESC VARCHAR2(512),
	ACTOR_UUID VARCHAR2(36),
	EVENT_RECORD_UUID VARCHAR2(36) NOT NULL,
	TAGS VARCHAR2(1024),
	META_TEMPLATE VARCHAR2(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE VARCHAR2(1024),
	R_META_TEMPLATE VARCHAR2(4000),
	R_TEXT_META_TEMPLATE VARCHAR2(1024),
	N_COMMENTS NUMBER(5,0) DEFAULT 0 NOT NULL , -- NEW
	N_RECOMMANDATIONS NUMBER(5,0) DEFAULT 0 NOT NULL -- NEW
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_STORIES
    ADD (CONSTRAINT "PK_STORY_ID" PRIMARY KEY("STORY_ID") USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

{ORA_GRANT_START} HOMEPAGE.NR_STORIES  {ORA_GRANT_STOP}  
ALTER TABLE HOMEPAGE.NR_STORIES ENABLE ROW MOVEMENT;

ALTER TABLE HOMEPAGE.NR_FOLLOWED_STORIES
    ADD CONSTRAINT "FK_F_STORY_ID" FOREIGN KEY ("STORY_ID")
	REFERENCES HOMEPAGE.NR_STORIES ("STORY_ID");

ALTER TABLE HOMEPAGE.NR_COMM_STORIES
    ADD CONSTRAINT "FK_COMM_STORY_ID" FOREIGN KEY ("STORY_ID")
	REFERENCES HOMEPAGE.NR_STORIES ("STORY_ID");

ALTER TABLE HOMEPAGE.NR_ORGPERSON_STORIES
    ADD CONSTRAINT "FK_ORGP_STORY_ID" FOREIGN KEY ("STORY_ID")
	REFERENCES HOMEPAGE.NR_STORIES ("STORY_ID");

----------------------------------------------------------------------
-- 8) REMOVE RELATED COMMUNITY COLUMNS FROM DISCOVERY TABLES 
----------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_NEWS_DISCOVERY
DROP COLUMN RELATED_COMM_UUID;

ALTER TABLE HOMEPAGE.NR_NEWS_DISCOVERY
DROP COLUMN RELATED_COMM_NAME;

----------------------------------------------------------------------
-- 9) REMOVE RELATED COMMUNITY COLUMNS FROM NR_NEWS_SAVED TABLES 
----------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_NEWS_SAVED
DROP COLUMN RELATED_COMM_UUID;

ALTER TABLE HOMEPAGE.NR_NEWS_SAVED
DROP COLUMN RELATED_COMM_NAME;
