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

-- 1) NR_STORIES
ALTER TABLE HOMEPAGE.NR_STORIES
    ALTER COLUMN R_META_TEMPLATE nvarchar(4000);    
GO

-- 2) EMD_EMAIL_PREFS
ALTER TABLE HOMEPAGE.EMD_EMAIL_PREFS
    ALTER COLUMN EMAIL_ADDRESS nvarchar(256);
GO

-- 3) NR_STORIES_CONTENT
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT
    ALTER COLUMN CONTENT varbinary(MAX);
GO
    
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT
    ADD STORY_ID nvarchar(36);    
GO

-- 4) NR_NEWS_DISCOVERY
ALTER TABLE HOMEPAGE.NR_NEWS_DISCOVERY
    ADD IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0;
GO    

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
	COMM_PERSON_FOLLOW_ID nvarchar(36) NOT NULL,
	PERSON_ID nvarchar(36) NOT NULL,
	PERSON_COMMUNITY_ID nvarchar(36) NOT NULL
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_COMM_PERSON_FOLLOW
    ADD CONSTRAINT PK_COMM_PER_ID PRIMARY KEY(COMM_PERSON_FOLLOW_ID);

ALTER TABLE HOMEPAGE.NR_COMM_PERSON_FOLLOW
    ADD CONSTRAINT FK_COMM_PER_PER_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

CREATE INDEX NR_COMM_PER_FOLLOW_PER_ID
    ON HOMEPAGE.NR_COMM_PERSON_FOLLOW (PERSON_ID);

CREATE INDEX NR_COMM_FOLLOW_COM_PER_ID
    ON HOMEPAGE.NR_COMM_PERSON_FOLLOW (PERSON_COMMUNITY_ID);

{SQL_GRANT_START} HOMEPAGE.NR_COMM_PERSON_FOLLOW {SQL_GRANT_STOP}


----------------------------------------------------------------------
-- HOMEPAGE.NR_COMM_PERSON_STORIES 
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_COMM_PERSON_STORIES (
	COMM_PER_STORY_ID nvarchar(36) NOT NULL,
	COMM_PER_READER_ID nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(256),
	ITEM_ID nvarchar(36),
	RESOURCE_TYPE NUMERIC(5,0) NOT NULL,
	CATEGORY_TYPE NUMERIC(5,0) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	STORY_ID nvarchar(36) NOT NULL
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_COMM_PERSON_STORIES
    ADD CONSTRAINT PK_COMPER_STORY_ID PRIMARY KEY(COMM_PER_STORY_ID);

ALTER TABLE HOMEPAGE.NR_COMM_PERSON_STORIES
    ADD CONSTRAINT FK_FCP_STORY_ID FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);

CREATE INDEX NR_COM_PER_STORIES_READER
    ON HOMEPAGE.NR_COMM_PERSON_STORIES (CREATION_DATE ASC, COMM_PER_READER_ID);

CREATE INDEX NR_COM_PER_STORIES_STORY_ID
    ON HOMEPAGE.NR_COMM_PERSON_STORIES (STORY_ID);  

{SQL_GRANT_START} HOMEPAGE.NR_COMM_PERSON_STORIES {SQL_GRANT_STOP}