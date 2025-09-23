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
-- FIXING DB INCONSISTENCES
--------------------------------------------------------------

-- 1) NR_STORIES
ALTER TABLE HOMEPAGE.NR_STORIES
    ALTER COLUMN R_META_TEMPLATE DROP NOT NULL;
    
reorg table HOMEPAGE.NR_STORIES use NEWS32TMPTABSPACE;

-- 2) EMD_EMAIL_PREFS
ALTER TABLE HOMEPAGE.EMD_EMAIL_PREFS
    ALTER COLUMN EMAIL_ADDRESS DROP NOT NULL;

reorg table HOMEPAGE.EMD_EMAIL_PREFS use NEWS4TMPTABSPACE;

-- 3) NR_STORIES_CONTENT
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT
    ALTER COLUMN CONTENT DROP NOT NULL;

ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT
    ADD COLUMN STORY_ID VARCHAR(36);
    
reorg table HOMEPAGE.NR_STORIES_CONTENT use NEWS4TMPTABSPACE;

-- 4) NR_NEWS_DISCOVERY
ALTER TABLE HOMEPAGE.NR_NEWS_DISCOVERY
    ADD COLUMN IS_COMMUNITY_STORY SMALLINT DEFAULT 0;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET IS_COMMUNITY_STORY = 0;

------------------------------------------------------------------
-- FIXING TEMPLATE ISSUES
------------------------------------------------------------------
UPDATE HOMEPAGE.NR_TEMPLATE SET DATA_SOURCE_STRING = 'itemName;itemHtmlPath' 
WHERE TEMPLATE_ID = 'actEntComm-WEJHX1TBvSCW0PS8ayfbPlZ1k';

INSERT INTO HOMEPAGE.NR_TEMPLATE values ('blogCorrName-2G3abCWRYNRpLhSawJXF6Qd', 'blogCorrelationName', 'link', 'correlationName;correlationHtmlPath', 1);

-----------------------------------------------------------------
-- ADDING TABLES TO SUPPORT THE NEWS FFED
-----------------------------------------------------------------

------------------------------
-- NR_COMM_PERSON_FOLLOW
------------------------------
CREATE TABLE HOMEPAGE.NR_COMM_PERSON_FOLLOW (
	COMM_PERSON_FOLLOW_ID VARCHAR(36) NOT NULL,
	PERSON_ID VARCHAR(36) NOT NULL,
	PERSON_COMMUNITY_ID VARCHAR(36) NOT NULL
)
IN NEWS4TABSPACE;

ALTER TABLE HOMEPAGE.NR_COMM_PERSON_FOLLOW
    ADD CONSTRAINT "PK_COMM_PER_ID" PRIMARY KEY("COMM_PERSON_FOLLOW_ID");

ALTER TABLE HOMEPAGE.NR_COMM_PERSON_FOLLOW
    ADD CONSTRAINT "FK_COMM_PER_PER_ID" FOREIGN KEY ("PERSON_ID")
	REFERENCES HOMEPAGE.PERSON ("PERSON_ID");

CREATE INDEX HOMEPAGE.NR_COMM_PER_FOLLOW_PER_ID
    ON HOMEPAGE.NR_COMM_PERSON_FOLLOW (PERSON_ID);

CREATE INDEX HOMEPAGE.NR_COMM_FOLLOW_COM_PER_ID
    ON HOMEPAGE.NR_COMM_PERSON_FOLLOW (PERSON_COMMUNITY_ID);

{DB2_GRANT_START} HOMEPAGE.NR_COMM_PERSON_FOLLOW {DB2_GRANT_STOP}


----------------------------------------------------------------------
-- HOMEPAGE.NR_COMM_PERSON_STORIES 
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_COMM_PERSON_STORIES (
	COMM_PER_STORY_ID VARCHAR(36) NOT NULL,
	COMM_PER_READER_ID VARCHAR(36) NOT NULL,
	CONTAINER_ID VARCHAR(256),
	ITEM_ID VARCHAR(36),
	RESOURCE_TYPE SMALLINT NOT NULL,
	CATEGORY_TYPE SMALLINT NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	SOURCE VARCHAR(36) NOT NULL,
	STORY_ID VARCHAR(36) NOT NULL
)
IN NEWS4TABSPACE;

ALTER TABLE HOMEPAGE.NR_COMM_PERSON_STORIES
    ADD CONSTRAINT "PK_COMPER_STORY_ID" PRIMARY KEY("COMM_PER_STORY_ID");

ALTER TABLE HOMEPAGE.NR_COMM_PERSON_STORIES
    ADD CONSTRAINT "FK_FCP_STORY_ID" FOREIGN KEY ("STORY_ID")
	REFERENCES HOMEPAGE.NR_STORIES ("STORY_ID");

CREATE INDEX HOMEPAGE.NR_COM_PER_STORIES_READER
    ON HOMEPAGE.NR_COMM_PERSON_STORIES (CREATION_DATE ASC, COMM_PER_READER_ID);

CREATE INDEX HOMEPAGE.NR_COM_PER_STORIES_STORY_ID
    ON HOMEPAGE.NR_COMM_PERSON_STORIES (STORY_ID);  

{DB2_GRANT_START} HOMEPAGE.NR_COMM_PERSON_STORIES {DB2_GRANT_STOP}

 
      