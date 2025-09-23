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

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 30
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

---------------------------------------------------------------------------------
------------------------ START NEWS ---------------------------------------------
---------------------------------------------------------------------------------

------------------------------------------------
-- NR_NEWS_RECORDS
------------------------------------------------

ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
	ADD ITEM_ID nvarchar(36);
GO


ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
	ADD ITEM_CORRELATION_ID nvarchar(36);
GO

---------------------------------------------------------------------------------
------------------------ END NEWS -----------------------------------------------
---------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 30
------------------------------------------------------------------------------------------------

-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 3.0.0
-- UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 30 , RELEASEVER = '3.0.0'
-- WHERE   DBSCHEMAVER = 23;
-- GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start	FIXUP 31
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


---------------------------------------------------------------------------------
------------------------ START NEWS ---------------------------------------------
---------------------------------------------------------------------------------


---------------------------------------------------------------
-- TO MANAGE PARTECIPATION: IMPLICIT SUBSCRIPTION
---------------------------------------------------------------

------------------------------------------------
-- NR_GROUP_TYPE
------------------------------------------------

CREATE TABLE HOMEPAGE.NR_GROUP_TYPE (
	GROUP_TYPE_ID nvarchar(36) NOT NULL,
	GROUP_TYPE NUMERIC(5,0) NOT NULL,
	GROUP_TYPE_DESC nvarchar(256) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_GROUP_TYPE 
    ADD CONSTRAINT PK_GROUP_TYPE_ID PRIMARY KEY(GROUP_TYPE_ID);
GO

ALTER TABLE HOMEPAGE.NR_GROUP_TYPE 
	ADD CONSTRAINT GROUP_TYPE_UNIQUE UNIQUE(GROUP_TYPE);
GO
------------------------------------------------
-- NR_GROUP
------------------------------------------------

CREATE TABLE HOMEPAGE.NR_GROUP (
	GROUP_ID nvarchar(36) NOT NULL,
	GROUP_NAME nvarchar(256) NOT NULL,
	GROUP_TYPE NUMERIC(5,0) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_GROUP 
    ADD CONSTRAINT PK_GROUP_ID PRIMARY KEY(GROUP_ID);
GO	

ALTER TABLE HOMEPAGE.NR_GROUP
	ADD CONSTRAINT FK_GROUP_TYPE FOREIGN KEY (GROUP_TYPE)
	REFERENCES HOMEPAGE.NR_GROUP_TYPE(GROUP_TYPE);
GO
------------------------------------------------
-- NR_PERSON_SOURCE
------------------------------------------------

CREATE TABLE HOMEPAGE.NR_PERSON_SOURCE (
	PARTICIPATION_ID nvarchar(36) NOT NULL,
	READER_ID nvarchar(36) NOT NULL,
	SOURCE_ID nvarchar(36) NOT NULL,
	GROUP_TYPE NUMERIC(5,0) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_PERSON_SOURCE 
    ADD CONSTRAINT PK_PART_PER_ID PRIMARY KEY(PARTICIPATION_ID);
GO	

ALTER TABLE HOMEPAGE.NR_PERSON_SOURCE
	ADD CONSTRAINT FK_READER_PER_ID FOREIGN KEY (READER_ID)
	REFERENCES HOMEPAGE.PERSON(PERSON_ID);
GO
	
ALTER TABLE HOMEPAGE.NR_PERSON_SOURCE
	ADD CONSTRAINT FK_SOURCE_PER_ID FOREIGN KEY (SOURCE_ID)
	REFERENCES HOMEPAGE.NR_SOURCE(SOURCE_ID);
GO

ALTER TABLE HOMEPAGE.NR_PERSON_SOURCE
	ADD CONSTRAINT FK_GROUP_TYPE_PER FOREIGN KEY (GROUP_TYPE)
	REFERENCES HOMEPAGE.NR_GROUP_TYPE(GROUP_TYPE);
GO

------------------------------------------------
-- NR_GROUP_SOURCE
------------------------------------------------

CREATE TABLE HOMEPAGE.NR_GROUP_SOURCE (
	PARTICIPATION_ID nvarchar(36) NOT NULL,
	READER_ID nvarchar(36) NOT NULL,
	SOURCE_ID nvarchar(36) NOT NULL,
	GROUP_TYPE NUMERIC(5,0) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_GROUP_SOURCE 
    ADD CONSTRAINT PK_PART_GRP_ID PRIMARY KEY(PARTICIPATION_ID);
GO
	
ALTER TABLE HOMEPAGE.NR_GROUP_SOURCE
	ADD CONSTRAINT FK_READER_GRP_ID FOREIGN KEY (READER_ID)
	REFERENCES HOMEPAGE.PERSON(PERSON_ID);
GO
	
ALTER TABLE HOMEPAGE.NR_GROUP_SOURCE
	ADD CONSTRAINT FK_SOURCE_GRP_ID FOREIGN KEY (SOURCE_ID)
	REFERENCES HOMEPAGE.NR_SOURCE(SOURCE_ID);
GO
	
ALTER TABLE HOMEPAGE.NR_GROUP_SOURCE
	ADD CONSTRAINT FK_GROUP_TYPE_GRP FOREIGN KEY (GROUP_TYPE)
	REFERENCES HOMEPAGE.NR_GROUP_TYPE(GROUP_TYPE);
GO	

--------------------------------------------------------
-- TO MANAGE FOLLOW: WATCHLIST
-- HOMEPAGE.NR_CATEGORY_TYPE 
--------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_CATEGORY_TYPE (
	CATEGORY_TYPE_ID nvarchar(36) NOT NULL,
	CATEGORY_TYPE_NAME nvarchar(36) NOT NULL, -- this is externalized
	CATEGORY_TYPE NUMERIC(5,0) NOT NULL,
	CATEGORY_TYPE_DESC nvarchar(256) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_CATEGORY_TYPE 
    ADD CONSTRAINT PK_CAT_TYPE_ID	PRIMARY KEY(CATEGORY_TYPE_ID);
GO	

ALTER TABLE HOMEPAGE.NR_CATEGORY_TYPE 
	ADD CONSTRAINT CAT_TYPE_UNIQUE UNIQUE(CATEGORY_TYPE);

--------------------------------------------------------
-- TO MANAGE FOLLOW: WATCHLIST
-- HOMEPAGE.NR_CATEGORY
--------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_CATEGORY (
	CATEGORY_ID nvarchar(36) NOT NULL,
	PERSON_ID nvarchar(36) NOT NULL,
	CATEGORY_NAME nvarchar(36) NOT NULL, -- this is externalized
	CATEGORY_TYPE NUMERIC(5,0) NOT NULL DEFAULT 0
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_CATEGORY
    ADD CONSTRAINT PK_CATEGORY_ID PRIMARY KEY(CATEGORY_ID);
GO	

ALTER TABLE HOMEPAGE.NR_CATEGORY
	ADD CONSTRAINT FK_C_PERSON_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON(PERSON_ID);
GO
	
ALTER TABLE HOMEPAGE.NR_CATEGORY
	ADD CONSTRAINT FK_CATEGORY_TYPE FOREIGN KEY (CATEGORY_TYPE)
	REFERENCES HOMEPAGE.NR_CATEGORY_TYPE(CATEGORY_TYPE);
GO
--------------------------------------------------------
-- TO MANAGE FOLLOW: WATCHLIST
-- HOMEPAGE.NR_SOURCE_WATCHED
--------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_SOURCE_WATCHED (
	SOURCE_ID nvarchar(36) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(36) NOT NULL,
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ENTRY_ID nvarchar(36),
	ENTRY_NAME nvarchar(256),
	ENTRY_URL nvarchar(2048),
	ENTRY_ATOM_URL nvarchar(2048),
	IS_ACL NUMERIC(5,0) NOT NULL,
	IS_PRIVATE NUMERIC(5,0),
	LAST_UPDATE DATETIME,
	IS_CNAME_RTL NUMERIC(5,0) NOT NULL DEFAULT 0,
	IS_ENAME_RTL NUMERIC(5,0) NOT NULL DEFAULT 0
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SOURCE_WATCHED 
    ADD CONSTRAINT PK_SRC_WATCHED_ID PRIMARY KEY(SOURCE_ID);
GO	
--------------------------------------------------------
-- TO MANAGE FOLLOW: WATCHLIST
-- NR_FOLLOW
--------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_FOLLOW (
	FOLLOW_ID nvarchar(36) NOT NULL,
	PERSON_ID nvarchar(36) NOT NULL,
	SOURCE_ID nvarchar(36) NOT NULL,
	CATEGORY_ID nvarchar(36) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_FOLLOW 
    ADD CONSTRAINT PK_FOLLOW_ID PRIMARY KEY(FOLLOW_ID);
GO

ALTER TABLE HOMEPAGE.NR_FOLLOW
	ADD CONSTRAINT FK_F_PERSON_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON(PERSON_ID);
GO	

ALTER TABLE HOMEPAGE.NR_FOLLOW
	ADD CONSTRAINT FK_F_SOURCE_ID FOREIGN KEY (SOURCE_ID)
	REFERENCES HOMEPAGE.NR_SOURCE_WATCHED(SOURCE_ID);
GO	

ALTER TABLE HOMEPAGE.NR_FOLLOW
	ADD CONSTRAINT FK_CATEGORY_ID FOREIGN KEY (CATEGORY_ID)
	REFERENCES HOMEPAGE.NR_CATEGORY(CATEGORY_ID);
GO	

--------------------------------------------------------
-- TO MANAGE FOLLOW: WATCHLIST
-- HOMEPAGE.NR_FOLLOW_GROUP
--------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_FOLLOW_GROUP (
	FOLLOW_ID nvarchar(36) NOT NULL,
	PERSON_ID nvarchar(36) NOT NULL,
	SOURCE_ID nvarchar(36) NOT NULL,
	CATEGORY_ID nvarchar(36) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_FOLLOW_GROUP 
    ADD CONSTRAINT PK_FOLLOW_GRP_ID PRIMARY KEY(FOLLOW_ID);
GO	

ALTER TABLE HOMEPAGE.NR_FOLLOW_GROUP
	ADD CONSTRAINT FK_PERSON_GRP_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON(PERSON_ID);
GO
	

ALTER TABLE HOMEPAGE.NR_FOLLOW_GROUP
	ADD CONSTRAINT FK_FGSOURCE_GRP_ID FOREIGN KEY (SOURCE_ID)
	REFERENCES HOMEPAGE.NR_SOURCE_WATCHED(SOURCE_ID);

GO	

ALTER TABLE HOMEPAGE.NR_FOLLOW_GROUP
	ADD CONSTRAINT FK_CATEGORY_GRP_ID FOREIGN KEY (CATEGORY_ID)
	REFERENCES HOMEPAGE.NR_CATEGORY(CATEGORY_ID);
GO	

---------------------------------------------------------
-- TO MANAGE THE NEWS
-- HOMEPAGE.NR_NEWS_TOP_UPDATES
---------------------------------------------------------

-- SCRIPT to simulate a new design for the news table --
CREATE TABLE HOMEPAGE.NR_NEWS_TOP_UPDATES (
	NEWS_RECORDS_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	READER_ID nvarchar(36),
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(36),
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	-- ENTRY_ID nvarchar(36), -- REMOVED
	ENTRY_NAME nvarchar(256),
	ENTRY_URL nvarchar(2048),
	ENTRY_ATOM_URL nvarchar(2048),
	CREATION_DATE DATETIME NOT NULL,
	-- IS_INBOX NUMERIC(5,0) NOT NULL, never used -- REMOVED
	-- IS_SAVED NUMERIC(5,0) NOT NULL,
	-- IS_TOP_STORY NUMERIC(5,0) NOT NULL, we remove it as it is already a top story table -- REMOVED
	-- IS_PUBLIC NUMERIC(5,0) NOT NULL, top updates are never public -- REMOVED
	-- IS_MAILED NUMERIC(5,0) NOT NULL, never used -- REMOVED
	-- TIME_STAMP TIMESTAMP NOT NULL, never used -- REMOVED
	BRIEF_DESC nvarchar(512),
	IS_BRIEF_DESC_RTL NUMERIC(5,0) NOT NULL,
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	RELATED_COMM_UUID nvarchar(36),
	RELATED_COMM_NAME nvarchar(256),
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	IS_CONTAINER NUMERIC(5,0) NOT NULL DEFAULT 0,
	ITEM_ID nvarchar(36), -- NEW
	ITEM_CORRELATION_ID nvarchar(36), -- NEW
	N_COMMENTS NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	N_RECOMMANDATIONS NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	GROUP_TYPE NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	NEWS_STORY_ID nvarchar(36) NOT NULL -- NEW
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_NEWS_TOP_UPDATES 
    ADD CONSTRAINT PK_TOP_UPDATES_ID PRIMARY KEY(NEWS_RECORDS_ID);

---------------------------------------------------------
-- TO MANAGE THE NEWS
-- HOMEPAGE..NR_NEWS_SAVED
---------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_NEWS_SAVED (
	NEWS_RECORDS_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	READER_ID nvarchar(36),
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(36),
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	-- ENTRY_ID nvarchar(36), -- REMOVED
	ENTRY_NAME nvarchar(256),
	ENTRY_URL nvarchar(2048),
	ENTRY_ATOM_URL nvarchar(2048),
	CREATION_DATE DATETIME NOT NULL,
	-- IS_INBOX NUMERIC(5,0) NOT NULL, never used -- REMOVED
	-- IS_SAVED NUMERIC(5,0) NOT NULL,
	-- IS_TOP_STORY NUMERIC(5,0) NOT NULL, we remove it as it is already a top story table -- REMOVED
	-- IS_PUBLIC NUMERIC(5,0) NOT NULL, top updates are never public -- REMOVED
	-- IS_MAILED NUMERIC(5,0) NOT NULL, never used -- REMOVED
	-- TIME_STAMP TIMESTAMP NOT NULL, never used -- REMOVED
	BRIEF_DESC nvarchar(512),
	IS_BRIEF_DESC_RTL NUMERIC(5,0) NOT NULL,
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	RELATED_COMM_UUID nvarchar(36),
	RELATED_COMM_NAME nvarchar(256),
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	IS_CONTAINER NUMERIC(5,0) NOT NULL DEFAULT 0,
	ITEM_ID nvarchar(36), -- NEW
	ITEM_CORRELATION_ID nvarchar(36), -- NEW
	N_COMMENTS NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	N_RECOMMANDATIONS NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	GROUP_TYPE NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	NEWS_STORY_ID nvarchar(36) NOT NULL -- NEW
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_NEWS_SAVED 
    ADD CONSTRAINT PK_SAVED_ID PRIMARY KEY(NEWS_RECORDS_ID);
GO	
---------------------------------------------------------
-- TO MANAGE THE NEWS
-- HOMEPAGE.NR_NEWS_DISCOVERY
---------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_NEWS_DISCOVERY (
	NEWS_RECORDS_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	-- READER_ID nvarchar(36), re remove it because it is a discovery table
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(36),
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	-- ENTRY_ID nvarchar(36), -- REMOVED
	ENTRY_NAME nvarchar(256),
	ENTRY_URL nvarchar(2048),
	ENTRY_ATOM_URL nvarchar(2048),
	CREATION_DATE DATETIME NOT NULL,
	-- IS_INBOX NUMERIC(5,0) NOT NULL,
	-- IS_SAVED NUMERIC(5,0) NOT NULL, we cannot save a discovery
	-- IS_TOP_STORY NUMERIC(5,0) NOT NULL, re remove it because it is a discovery table
	-- IS_PUBLIC NUMERIC(5,0) NOT NULL, this is always public
	-- IS_MAILED NUMERIC(5,0) NOT NULL, never used
	-- TIME_STAMP TIMESTAMP NOT NULL, never used
	BRIEF_DESC nvarchar(512),
	IS_BRIEF_DESC_RTL NUMERIC(5,0) NOT NULL,
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	RELATED_COMM_UUID nvarchar(36),
	RELATED_COMM_NAME nvarchar(256),
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	-- IS_CONTAINER NUMERIC(5,0) NOT NULL DEFAULT 0,
	ITEM_ID nvarchar(36), -- NEW
	ITEM_CORRELATION_ID nvarchar(36), -- NEW
	N_COMMENTS NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	N_RECOMMANDATIONS NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	GROUP_TYPE NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	NEWS_STORY_ID nvarchar(36) NOT NULL -- NEW
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_NEWS_DISCOVERY 
    ADD CONSTRAINT PK_DISCOVERY_ID PRIMARY KEY(NEWS_RECORDS_ID);
GO	
---------------------------------------------------------
-- TO MANAGE THE NEWS
--  HOMEPAGE.NR_NEWS_WATCHLIST
---------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_NEWS_WATCHLIST (
	NEWS_RECORDS_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE_ID nvarchar(36), -- reader_id in this context is replaced by source_id
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(36),
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	-- ENTRY_ID nvarchar(36), -- REMOVED
	ENTRY_NAME nvarchar(256),
	ENTRY_URL nvarchar(2048),
	ENTRY_ATOM_URL nvarchar(2048),
	CREATION_DATE DATETIME NOT NULL,
	-- IS_INBOX NUMERIC(5,0) NOT NULL, never used -- REMOVED
	-- IS_SAVED NUMERIC(5,0) NOT NULL,
	-- IS_TOP_STORY NUMERIC(5,0) NOT NULL, we remove it as it is already a top story table -- REMOVED
	-- IS_PUBLIC NUMERIC(5,0) NOT NULL, top updates are never public -- REMOVED
	-- IS_MAILED NUMERIC(5,0) NOT NULL, never used -- REMOVED
	-- TIME_STAMP TIMESTAMP NOT NULL, never used -- REMOVED
	BRIEF_DESC nvarchar(512),
	IS_BRIEF_DESC_RTL NUMERIC(5,0) NOT NULL,
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	RELATED_COMM_UUID nvarchar(36),
	RELATED_COMM_NAME nvarchar(256),
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	IS_CONTAINER NUMERIC(5,0) NOT NULL DEFAULT 0,
	ITEM_ID nvarchar(36), -- NEW
	ITEM_CORRELATION_ID nvarchar(36), -- NEW
	N_COMMENTS NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	N_RECOMMANDATIONS NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	GROUP_TYPE NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	NEWS_STORY_ID nvarchar(36) NOT NULL -- NEW
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_NEWS_WATCHLIST 
    ADD CONSTRAINT PK_WATCHLIST_ID PRIMARY KEY(NEWS_RECORDS_ID);

---------------------------------------------------------
-- TO MANAGE THE NEWS
--  HOMEPAGE.NR_NEWS_STATUS_NETWORK
---------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_NEWS_STATUS_NETWORK (
	NEWS_RECORDS_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	READER_ID nvarchar(36),
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(36),
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	-- ENTRY_ID nvarchar(36), -- REMOVED
	ENTRY_NAME nvarchar(256),
	ENTRY_URL nvarchar(2048),
	ENTRY_ATOM_URL nvarchar(2048),
	CREATION_DATE DATETIME NOT NULL,
	-- IS_INBOX NUMERIC(5,0) NOT NULL, never used -- REMOVED
	-- IS_SAVED NUMERIC(5,0) NOT NULL,
	-- IS_TOP_STORY NUMERIC(5,0) NOT NULL, we remove it as it is already a top story table -- REMOVED
	-- IS_PUBLIC NUMERIC(5,0) NOT NULL, top updates are never public -- REMOVED
	-- IS_MAILED NUMERIC(5,0) NOT NULL, never used -- REMOVED
	-- TIME_STAMP TIMESTAMP NOT NULL, never used -- REMOVED
	BRIEF_DESC nvarchar(512),
	IS_BRIEF_DESC_RTL NUMERIC(5,0) NOT NULL,
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	RELATED_COMM_UUID nvarchar(36),
	RELATED_COMM_NAME nvarchar(256),
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	IS_CONTAINER NUMERIC(5,0) NOT NULL DEFAULT 0,
	ITEM_ID nvarchar(36), -- NEW
	ITEM_CORRELATION_ID nvarchar(36), -- NEW
	N_COMMENTS NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	N_RECOMMANDATIONS NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	GROUP_TYPE NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	NEWS_STORY_ID nvarchar(36) NOT NULL -- NEW
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_NETWORK 
    ADD CONSTRAINT PK_STATUS_ID PRIMARY KEY(NEWS_RECORDS_ID);
GO	

----------------------------------------------------------------------------
-- TO MANAGE NEW STORY and COMMENTS
----------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_NEWS_STORY (
	NEWS_STORY_ID nvarchar(36) NOT NULL,
	CONTENT nvarchar(max) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_NEWS_STORY 
    ADD CONSTRAINT PK_NEWS_STORY_ID PRIMARY KEY(NEWS_STORY_ID);
GO	
----------------------------------------------------------------------------
-- TO MANAGE NEW STORY and COMMENTS
-- HOMEPAGE.NR_NEWS_COMMENT
----------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_NEWS_COMMENT (
	COMMENT_ID nvarchar(36) NOT NULL,
	NEWS_STORY_ID nvarchar(36) NOT NULL,
	COMMENTS nvarchar(4000) DEFAULT '' NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_NEWS_COMMENT 
    ADD CONSTRAINT PK_COMMENT_ID PRIMARY KEY(COMMENT_ID);
GO	

ALTER TABLE HOMEPAGE.NR_NEWS_COMMENT
	ADD CONSTRAINT FK_NEWS_STORY_ID FOREIGN KEY (NEWS_STORY_ID)
	REFERENCES HOMEPAGE.NR_NEWS_STORY(NEWS_STORY_ID);
GO	
-- GIVING GRANTS TO THE NEW TABLES --


{SQL_GRANT_START} HOMEPAGE.NR_GROUP_TYPE {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NR_GROUP {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NR_PERSON_SOURCE {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NR_GROUP_SOURCE {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NR_CATEGORY_TYPE {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NR_CATEGORY {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NR_SOURCE_WATCHED {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NR_FOLLOW {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NR_FOLLOW_GROUP {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NR_NEWS_TOP_UPDATES {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NR_NEWS_SAVED {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NR_NEWS_DISCOVERY {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NR_NEWS_WATCHLIST {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NR_NEWS_STATUS_NETWORK {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NR_NEWS_STORY {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NR_NEWS_COMMENT {SQL_GRANT_STOP}
GO

--------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------

------------------------------------- MIGRATION DATA ---------------------------------


--------------------------------------------------------------------------------------
-- Moving data from the old NR_NEWS_RECORDS to the new table NR_NEWS_TOP_UPDATES
--------------------------------------------------------------------------------------
INSERT INTO HOMEPAGE.NR_NEWS_TOP_UPDATES ( 
            NEWS_RECORDS_ID,
            EVENT_NAME,
            READER_ID,
            SOURCE,
            CONTAINER_ID,
            CONTAINER_NAME,
            CONTAINER_URL,
            -- ENTRY_ID nvarchar(36), -- REMOVED
            ENTRY_NAME,
            ENTRY_URL,
            ENTRY_ATOM_URL,
            CREATION_DATE,
            -- IS_INBOX NUMERIC(5,0) NOT NULL, never used -- REMOVED
            -- IS_SAVED, removed as we use a special table to store saved records
            -- IS_TOP_STORY NUMERIC(5,0) NOT NULL, we remove it as it is already a top story table -- REMOVED
            -- IS_PUBLIC NUMERIC(5,0) NOT NULL, top updates are never public -- REMOVED
            -- IS_MAILED NUMERIC(5,0) NOT NULL, never used -- REMOVED
            -- TIME_STAMP TIMESTAMP NOT NULL, never used -- REMOVED
            BRIEF_DESC,
            IS_BRIEF_DESC_RTL,
            ACTOR_UUID,
            EVENT_RECORD_UUID,
            RELATED_COMM_UUID,
            RELATED_COMM_NAME,
            TAGS,
            META_TEMPLATE,
            TEXT_META_TEMPLATE,
            IS_CONTAINER,
            ITEM_ID, -- NEW
            ITEM_CORRELATION_ID, -- NEW
            N_COMMENTS, --N_COMMENTS, -- NEW
            N_RECOMMANDATIONS, --N_RECOMMANDATIONS, -- NEW
            GROUP_TYPE, --GROUP_TYPE, -- NEW
            NEWS_STORY_ID-- NEWS_STORY_ID -- NEW
)
    SELECT 
            NEWS_RECORDS_ID,
            EVENT_NAME,
            READER_ID,
            SOURCE,
            CONTAINER_ID,
            CONTAINER_NAME,
            CONTAINER_URL,
            -- ENTRY_ID, REMOVED
            ENTRY_NAME,
            ENTRY_URL,
            ENTRY_ATOM_URL,
            CREATION_DATE,
            -- IS_INBOX, REMOVED
            -- IS_SAVED,
            -- IS_TOP_STORY, we are inserting top stories
            -- IS_PUBLIC,
            -- IS_MAILED,
            -- TIME_STAMP,
            BRIEF_DESC,
            IS_BRIEF_DESC_RTL,
            ACTOR_UUID,
            EVENT_RECORD_UUID,
            RELATED_COMM_UUID,
            RELATED_COMM_NAME,
            TAGS,
            META_TEMPLATE,
            TEXT_META_TEMPLATE,
            IS_CONTAINER,
            ITEM_ID,
            ITEM_CORRELATION_ID,
            0,
            0,
            0,
            NEWS_RECORDS_ID
    FROM    HOMEPAGE.NR_NEWS_RECORDS
    WHERE   HOMEPAGE.NR_NEWS_RECORDS.IS_TOP_STORY = 1 AND READER_ID IS NOT NULL;
GO	
	
--------------------------------------------------------------------------------------
-- Moving data from the old NR_NEWS_RECORDS to the new table NR_NEWS_SAVED
--------------------------------------------------------------------------------------
INSERT INTO HOMEPAGE.NR_NEWS_SAVED ( 
            NEWS_RECORDS_ID,
            EVENT_NAME,
            READER_ID,
            SOURCE,
            CONTAINER_ID,
            CONTAINER_NAME,
            CONTAINER_URL,
            -- ENTRY_ID nvarchar(36), -- REMOVED
            ENTRY_NAME,
            ENTRY_URL,
            ENTRY_ATOM_URL,
            CREATION_DATE,
            -- IS_INBOX NUMERIC(5,0) NOT NULL, never used -- REMOVED
            -- IS_SAVED, removed as we use a special table to store saved records
            -- IS_TOP_STORY NUMERIC(5,0) NOT NULL, we remove it as it is already a top story table -- REMOVED
            -- IS_PUBLIC NUMERIC(5,0) NOT NULL, top updates are never public -- REMOVED
            -- IS_MAILED NUMERIC(5,0) NOT NULL, never used -- REMOVED
            -- TIME_STAMP TIMESTAMP NOT NULL, never used -- REMOVED
            BRIEF_DESC,
            IS_BRIEF_DESC_RTL,
            ACTOR_UUID,
            EVENT_RECORD_UUID,
            RELATED_COMM_UUID,
            RELATED_COMM_NAME,
            TAGS,
            META_TEMPLATE,
            TEXT_META_TEMPLATE,
            IS_CONTAINER,
            ITEM_ID, -- NEW
            ITEM_CORRELATION_ID, -- NEW
            N_COMMENTS, --N_COMMENTS, -- NEW
            N_RECOMMANDATIONS, --N_RECOMMANDATIONS, -- NEW
            GROUP_TYPE, --GROUP_TYPE, -- NEW
            NEWS_STORY_ID-- NEWS_STORY_ID -- NEW
)
    SELECT 
            NEWS_RECORDS_ID,
            EVENT_NAME,
            READER_ID,
            SOURCE,
            CONTAINER_ID,
            CONTAINER_NAME,
            CONTAINER_URL,
            -- ENTRY_ID, REMOVED
            ENTRY_NAME,
            ENTRY_URL,
            ENTRY_ATOM_URL,
            CREATION_DATE,
            -- IS_INBOX, REMOVED
            -- IS_SAVED,
            -- IS_TOP_STORY, we are inserting top stories
            -- IS_PUBLIC,
            -- IS_MAILED,
            -- TIME_STAMP,
            BRIEF_DESC,
            IS_BRIEF_DESC_RTL,
            ACTOR_UUID,
            EVENT_RECORD_UUID,
            RELATED_COMM_UUID,
            RELATED_COMM_NAME,
            TAGS,
            META_TEMPLATE,
            TEXT_META_TEMPLATE,
            IS_CONTAINER,
            ITEM_ID,
            ITEM_CORRELATION_ID,
            0,
            0,
            0,
            NEWS_RECORDS_ID
    FROM    HOMEPAGE.NR_NEWS_RECORDS
    WHERE   HOMEPAGE.NR_NEWS_RECORDS.IS_SAVED = 1;
GO	

--------------------------------------------------------------------------------------
-- Moving data from the old NR_NEWS_RECORDS to the new table NR_NEWS_TOP_UPDATES
--------------------------------------------------------------------------------------
INSERT INTO HOMEPAGE.NR_NEWS_DISCOVERY ( 
            NEWS_RECORDS_ID,
            EVENT_NAME,
            -- READER_ID,
            SOURCE,
            CONTAINER_ID,
            CONTAINER_NAME,
            CONTAINER_URL,
            -- ENTRY_ID nvarchar(36), -- REMOVED
            ENTRY_NAME,
            ENTRY_URL,
            ENTRY_ATOM_URL,
            CREATION_DATE,
            -- IS_INBOX NUMERIC(5,0) NOT NULL, never used -- REMOVED
            -- IS_SAVED,
            -- IS_TOP_STORY NUMERIC(5,0) NOT NULL, we remove it as it is already a top story table -- REMOVED
            -- IS_PUBLIC NUMERIC(5,0) NOT NULL, top updates are never public -- REMOVED
            -- IS_MAILED NUMERIC(5,0) NOT NULL, never used -- REMOVED
            -- TIME_STAMP TIMESTAMP NOT NULL, never used -- REMOVED
            BRIEF_DESC,
            IS_BRIEF_DESC_RTL,
            ACTOR_UUID,
            EVENT_RECORD_UUID,
            RELATED_COMM_UUID,
            RELATED_COMM_NAME,
            TAGS,
            META_TEMPLATE,
            TEXT_META_TEMPLATE,
            -- IS_CONTAINER,
            ITEM_ID, -- NEW
            ITEM_CORRELATION_ID, -- NEW
            N_COMMENTS, --N_COMMENTS, -- NEW
            N_RECOMMANDATIONS, --N_RECOMMANDATIONS, -- NEW
            GROUP_TYPE, --GROUP_TYPE, -- NEW
            NEWS_STORY_ID-- NEWS_STORY_ID -- NEW
)
    SELECT 
            NEWS_RECORDS_ID,
            EVENT_NAME,
            -- READER_ID,
            SOURCE,
            CONTAINER_ID,
            CONTAINER_NAME,
            CONTAINER_URL,
            -- ENTRY_ID, REMOVED
            ENTRY_NAME,
            ENTRY_URL,
            ENTRY_ATOM_URL,
            CREATION_DATE,
            -- IS_INBOX, REMOVED
            -- IS_SAVED,
            -- IS_TOP_STORY, we are inserting top stories
            -- IS_PUBLIC,
            -- IS_MAILED,
            -- TIME_STAMP,
            BRIEF_DESC,
            IS_BRIEF_DESC_RTL,
            ACTOR_UUID,
            EVENT_RECORD_UUID,
            RELATED_COMM_UUID,
            RELATED_COMM_NAME,
            TAGS,
            META_TEMPLATE,
            TEXT_META_TEMPLATE,
            -- IS_CONTAINER,
            ITEM_ID,
            ITEM_CORRELATION_ID,
            0,
            0,
            0,
            NEWS_RECORDS_ID
    FROM    HOMEPAGE.NR_NEWS_RECORDS
    WHERE   HOMEPAGE.NR_NEWS_RECORDS.IS_PUBLIC = 1 AND READER_ID IS NULL AND IS_CONTAINER=0;
GO

-----------------------------------------------------------------------------------------------------------------
-- WATCHLIST
-----------------------------------------------------------------------------------------------------------------

-- INSERTING CATEGORY_TYPE (profiles and tag)
INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('profiles_c9cax4cc4x8b0bx51af2ddef2cd', 1, '%profile', 'profiles');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('tags_0f1xc9cax4cc4x8b0bx51af2ddef2cd', 2, '%tag', 'tag');

-- CREATE FOR EACH USERS A DEFAULT CATEGORY FOR EACH CATEGORY TYPE. 
-- THIS TABLE WILL HAVE HAS RESULTS N_USERS X CATEGORY_TYPES RECORDS
INSERT INTO HOMEPAGE.NR_CATEGORY 
    (
        CATEGORY_ID,
        PERSON_ID,
        CATEGORY_NAME,
        CATEGORY_TYPE
    )
    SELECT
        '-' + SUBSTRING((NR_CATEGORY_TYPE.CATEGORY_TYPE_NAME + PERSON.PERSON_ID),2,LEN(PERSON.PERSON_ID)-2) CATEGORY_ID,
        PERSON.PERSON_ID, 
        NR_CATEGORY_TYPE.CATEGORY_TYPE_NAME, 
        NR_CATEGORY_TYPE.CATEGORY_TYPE
    FROM HOMEPAGE.PERSON PERSON, HOMEPAGE.NR_CATEGORY_TYPE NR_CATEGORY_TYPE;
GO

-- SELECT THE PROFILES SOURCE
INSERT INTO HOMEPAGE.NR_SOURCE_WATCHED
    (
        SOURCE_ID,
        SOURCE,
        CONTAINER_ID,
        CONTAINER_NAME,
        CONTAINER_URL,
        ENTRY_ID,
        ENTRY_NAME,
        ENTRY_URL,
        ENTRY_ATOM_URL,
        IS_ACL,
        IS_PRIVATE,
        LAST_UPDATE,
        IS_CNAME_RTL,
        IS_ENAME_RTL
    )
SELECT 
        SOURCE_ID,
        SOURCE,
        CONTAINER_ID,
        CONTAINER_NAME,
        CONTAINER_URL,
        ENTRY_ID,
        ENTRY_NAME,
        ENTRY_URL,
        ENTRY_ATOM_URL,
        IS_ACL,
        IS_PRIVATE,
        LAST_UPDATE,
        IS_CNAME_RTL,
        IS_ENAME_RTL
FROM HOMEPAGE.NR_SOURCE NR_SOURCE
WHERE NR_SOURCE.SOURCE = 'profiles' AND NR_SOURCE.CONTAINER_NAME IS NULL;
GO

-- SELECT THE TAG SOURCE
INSERT INTO HOMEPAGE.NR_SOURCE_WATCHED
    (
        SOURCE_ID,
        SOURCE,
        CONTAINER_ID,
        CONTAINER_NAME,
        CONTAINER_URL,
        ENTRY_ID,
        ENTRY_NAME,
        ENTRY_URL,
        ENTRY_ATOM_URL,
        IS_ACL,
        IS_PRIVATE,
        LAST_UPDATE,
        IS_CNAME_RTL,
        IS_ENAME_RTL
    )
SELECT 
        SOURCE_ID,
        SOURCE,
        CONTAINER_ID,
        CONTAINER_NAME,
        CONTAINER_URL,
        ENTRY_ID,
        ENTRY_NAME,
        ENTRY_URL,
        ENTRY_ATOM_URL,
        IS_ACL,
        IS_PRIVATE,
        LAST_UPDATE,
        IS_CNAME_RTL,
        IS_ENAME_RTL
FROM HOMEPAGE.NR_SOURCE NR_SOURCE
WHERE NR_SOURCE.SOURCE = 'tag' AND NR_SOURCE.CONTAINER_NAME IS NOT NULL;
GO

-- CREATE THE RELETIONSHIP FOR PROFILES SOURCE INTO THE FOLLOW TABLE
INSERT INTO HOMEPAGE.NR_FOLLOW (
    FOLLOW_ID,
    PERSON_ID,
    SOURCE_ID,
    CATEGORY_ID    
)
SELECT  SUBSTRING(NR_SUBSCRIPTION.PERSON_ID,1,10) + 
        SUBSTRING(NR_SOURCE.SOURCE_ID,1,10) +  
        SUBSTRING((SUBSTRING((NR_CATEGORY_TYPE.CATEGORY_TYPE_NAME + NR_SUBSCRIPTION.PERSON_ID),2,36)),1,14),
        NR_SUBSCRIPTION.PERSON_ID,
        NR_SOURCE.SOURCE_ID,
         '-' + SUBSTRING((NR_CATEGORY_TYPE.CATEGORY_TYPE_NAME + NR_SUBSCRIPTION.PERSON_ID),2,LEN(NR_SUBSCRIPTION.PERSON_ID)-2) CATEGORY_ID
FROM    HOMEPAGE.NR_SUBSCRIPTION NR_SUBSCRIPTION, 
        HOMEPAGE.NR_SOURCE NR_SOURCE, 
        HOMEPAGE.NR_CATEGORY_TYPE NR_CATEGORY_TYPE        
WHERE   NR_SUBSCRIPTION.SOURCE_ID = NR_SOURCE.SOURCE_ID  AND 
        NR_SUBSCRIPTION.IS_EXPLICIT = 1 AND
        NR_SUBSCRIPTION.IS_ACTIVE = 1 AND
        NR_CATEGORY_TYPE.CATEGORY_TYPE = 1 AND -- 1 is profile
        NR_SOURCE.SOURCE = 'profiles' AND NR_SOURCE.CONTAINER_NAME IS NULL;
GO		

-- CREATE THE RELETIONSHIP FOR TAGS SOURCE INTO THE FOLLOW TABLE
INSERT INTO HOMEPAGE.NR_FOLLOW (
    FOLLOW_ID,
    PERSON_ID,
    SOURCE_ID,
    CATEGORY_ID    
)
SELECT  SUBSTRING(NR_SUBSCRIPTION.PERSON_ID,1,10) + 
        SUBSTRING(NR_SOURCE.SOURCE_ID,1,10) +  
        SUBSTRING((SUBSTRING((NR_CATEGORY_TYPE.CATEGORY_TYPE_NAME + NR_SUBSCRIPTION.PERSON_ID),2,36)),1,14),
        NR_SUBSCRIPTION.PERSON_ID,
        NR_SOURCE.SOURCE_ID,
         '-' + SUBSTRING((NR_CATEGORY_TYPE.CATEGORY_TYPE_NAME + NR_SUBSCRIPTION.PERSON_ID),2,LEN(NR_SUBSCRIPTION.PERSON_ID)-2) CATEGORY_ID
FROM    HOMEPAGE.NR_SUBSCRIPTION NR_SUBSCRIPTION, 
        HOMEPAGE.NR_SOURCE NR_SOURCE, 
        HOMEPAGE.NR_CATEGORY_TYPE NR_CATEGORY_TYPE        
WHERE   NR_SUBSCRIPTION.SOURCE_ID = NR_SOURCE.SOURCE_ID  AND 
        NR_SUBSCRIPTION.IS_EXPLICIT = 1 AND
        NR_SUBSCRIPTION.IS_ACTIVE = 1 AND
        NR_CATEGORY_TYPE.CATEGORY_TYPE = 2 AND -- 1 is profile
        NR_SOURCE.SOURCE = 'tag' AND NR_SOURCE.CONTAINER_NAME IS NOT NULL;
GO

-- POPULATE THE NEW TABLE NR_NEWS_WATCHLIST WHERE WE LINK A STORY TO A SOURCE AND NOT ANYMORE TO A SOURCE_ID
-- INSERTING PROFILES STORIES
-- 1 profile status update
INSERT INTO HOMEPAGE.NR_NEWS_WATCHLIST 
    (
        NEWS_RECORDS_ID,
        EVENT_NAME,
        SOURCE_ID,
        SOURCE,
        CONTAINER_ID,
        CONTAINER_NAME,
        CONTAINER_URL,
        ENTRY_NAME,
        ENTRY_URL,
        ENTRY_ATOM_URL,
        CREATION_DATE,
        BRIEF_DESC,
        IS_BRIEF_DESC_RTL,
        ACTOR_UUID,
        EVENT_RECORD_UUID,
        RELATED_COMM_UUID,
        RELATED_COMM_NAME,
        TAGS,
        META_TEMPLATE,
        TEXT_META_TEMPLATE,
        IS_CONTAINER,
        ITEM_ID,
        ITEM_CORRELATION_ID,
        N_COMMENTS,
        N_RECOMMANDATIONS,
        GROUP_TYPE,
        NEWS_STORY_ID
    )
SELECT 
        NR_NEWS_RECORDS.NEWS_RECORDS_ID,
        NR_NEWS_RECORDS.EVENT_NAME,
        NR_SOURCE_WATCHED.SOURCE_ID,
        NR_NEWS_RECORDS.SOURCE,
        NR_NEWS_RECORDS.CONTAINER_ID,
        NR_NEWS_RECORDS.CONTAINER_NAME,
        NR_NEWS_RECORDS.CONTAINER_URL,
        --NR_NEWS_RECORDS.ENTRY_ID,
        NR_NEWS_RECORDS.ENTRY_NAME,
        NR_NEWS_RECORDS.ENTRY_URL,
        NR_NEWS_RECORDS.ENTRY_ATOM_URL,
        NR_NEWS_RECORDS.CREATION_DATE,
        --NR_NEWS_RECORDS.IS_INBOX,
        --NR_NEWS_RECORDS.IS_SAVED,
        --NR_NEWS_RECORDS.IS_TOP_STORY,
        --NR_NEWS_RECORDS.IS_PUBLIC,
        --NR_NEWS_RECORDS.IS_MAILED,
        --NR_NEWS_RECORDS.TIME_STAMP,
        NR_NEWS_RECORDS.BRIEF_DESC,
        NR_NEWS_RECORDS.IS_BRIEF_DESC_RTL,
        NR_NEWS_RECORDS.ACTOR_UUID,
        NR_NEWS_RECORDS.EVENT_RECORD_UUID,
        NR_NEWS_RECORDS.RELATED_COMM_UUID,
        NR_NEWS_RECORDS.RELATED_COMM_NAME,
        NR_NEWS_RECORDS.TAGS,
        NR_NEWS_RECORDS.META_TEMPLATE,
        NR_NEWS_RECORDS.TEXT_META_TEMPLATE,
        NR_NEWS_RECORDS.IS_CONTAINER,
        NR_NEWS_RECORDS.ITEM_ID,
        NR_NEWS_RECORDS.ITEM_CORRELATION_ID,
        0,
        0,
        0,
        NR_NEWS_RECORDS.NEWS_RECORDS_ID
FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS, HOMEPAGE.NR_SOURCE_WATCHED NR_SOURCE_WATCHED
WHERE   NR_NEWS_RECORDS.READER_ID IS NULL AND NR_NEWS_RECORDS.ACTOR_UUID IS NULL AND
        (NR_NEWS_RECORDS.SOURCE='profiles' AND NR_NEWS_RECORDS.IS_CONTAINER = 1 AND NR_NEWS_RECORDS.CONTAINER_ID IS NOT NULL) AND
        NR_NEWS_RECORDS.CONTAINER_ID = NR_SOURCE_WATCHED.CONTAINER_ID;
GO		

-- 2 where actor uuid is specified        
INSERT INTO HOMEPAGE.NR_NEWS_WATCHLIST 
    (
        NEWS_RECORDS_ID,
        EVENT_NAME,
        SOURCE_ID,
        SOURCE,
        CONTAINER_ID,
        CONTAINER_NAME,
        CONTAINER_URL,
        ENTRY_NAME,
        ENTRY_URL,
        ENTRY_ATOM_URL,
        CREATION_DATE,
        BRIEF_DESC,
        IS_BRIEF_DESC_RTL,
        ACTOR_UUID,
        EVENT_RECORD_UUID,
        RELATED_COMM_UUID,
        RELATED_COMM_NAME,
        TAGS,
        META_TEMPLATE,
        TEXT_META_TEMPLATE,
        IS_CONTAINER,
        ITEM_ID,
        ITEM_CORRELATION_ID,
        N_COMMENTS,
        N_RECOMMANDATIONS,
        GROUP_TYPE,
        NEWS_STORY_ID
    )
SELECT 
        NR_NEWS_RECORDS.NEWS_RECORDS_ID,
        NR_NEWS_RECORDS.EVENT_NAME,
        NR_SOURCE_WATCHED.SOURCE_ID,
        NR_NEWS_RECORDS.SOURCE,
        NR_NEWS_RECORDS.CONTAINER_ID,
        NR_NEWS_RECORDS.CONTAINER_NAME,
        NR_NEWS_RECORDS.CONTAINER_URL,
        --NR_NEWS_RECORDS.ENTRY_ID,
        NR_NEWS_RECORDS.ENTRY_NAME,
        NR_NEWS_RECORDS.ENTRY_URL,
        NR_NEWS_RECORDS.ENTRY_ATOM_URL,
        NR_NEWS_RECORDS.CREATION_DATE,
        --NR_NEWS_RECORDS.IS_INBOX,
        --NR_NEWS_RECORDS.IS_SAVED,
        --NR_NEWS_RECORDS.IS_TOP_STORY,
        --NR_NEWS_RECORDS.IS_PUBLIC,
        --NR_NEWS_RECORDS.IS_MAILED,
        --NR_NEWS_RECORDS.TIME_STAMP,
        NR_NEWS_RECORDS.BRIEF_DESC,
        NR_NEWS_RECORDS.IS_BRIEF_DESC_RTL,
        NR_NEWS_RECORDS.ACTOR_UUID,
        NR_NEWS_RECORDS.EVENT_RECORD_UUID,
        NR_NEWS_RECORDS.RELATED_COMM_UUID,
        NR_NEWS_RECORDS.RELATED_COMM_NAME,
        NR_NEWS_RECORDS.TAGS,
        NR_NEWS_RECORDS.META_TEMPLATE,
        NR_NEWS_RECORDS.TEXT_META_TEMPLATE,
        NR_NEWS_RECORDS.IS_CONTAINER,
        NR_NEWS_RECORDS.ITEM_ID,
        NR_NEWS_RECORDS.ITEM_CORRELATION_ID,
        0,
        0,
        0,
        NR_NEWS_RECORDS.NEWS_RECORDS_ID
FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS, HOMEPAGE.NR_SOURCE_WATCHED NR_SOURCE_WATCHED
WHERE   READER_ID IS NULL AND
        (NR_NEWS_RECORDS.IS_PUBLIC = 1 AND ACTOR_UUID IS NOT NULL) AND
        NR_NEWS_RECORDS.ACTOR_UUID = NR_SOURCE_WATCHED.CONTAINER_ID;
GO		

-- INSERTING TAGS STORIES
INSERT INTO HOMEPAGE.NR_NEWS_WATCHLIST 
    (
        NEWS_RECORDS_ID,
        EVENT_NAME,
        SOURCE_ID,
        SOURCE,
        CONTAINER_ID,
        CONTAINER_NAME,
        CONTAINER_URL,
        ENTRY_NAME,
        ENTRY_URL,
        ENTRY_ATOM_URL,
        CREATION_DATE,
        BRIEF_DESC,
        IS_BRIEF_DESC_RTL,
        ACTOR_UUID,
        EVENT_RECORD_UUID,
        RELATED_COMM_UUID,
        RELATED_COMM_NAME,
        TAGS,
        META_TEMPLATE,
        TEXT_META_TEMPLATE,
        IS_CONTAINER,
        ITEM_ID,
        ITEM_CORRELATION_ID,
        N_COMMENTS,
        N_RECOMMANDATIONS,
        GROUP_TYPE,
        NEWS_STORY_ID
    )
SELECT 
        NR_NEWS_RECORDS.NEWS_RECORDS_ID,
        NR_NEWS_RECORDS.EVENT_NAME,
        NR_SOURCE_WATCHED.SOURCE_ID,
        NR_NEWS_RECORDS.SOURCE,
        NR_NEWS_RECORDS.CONTAINER_ID,
        NR_NEWS_RECORDS.CONTAINER_NAME,
        NR_NEWS_RECORDS.CONTAINER_URL,
        --NR_NEWS_RECORDS.ENTRY_ID,
        NR_NEWS_RECORDS.ENTRY_NAME,
        NR_NEWS_RECORDS.ENTRY_URL,
        NR_NEWS_RECORDS.ENTRY_ATOM_URL,
        NR_NEWS_RECORDS.CREATION_DATE,
        --NR_NEWS_RECORDS.IS_INBOX,
        --NR_NEWS_RECORDS.IS_SAVED,
        --NR_NEWS_RECORDS.IS_TOP_STORY,
        --NR_NEWS_RECORDS.IS_PUBLIC,
        --NR_NEWS_RECORDS.IS_MAILED,
        --NR_NEWS_RECORDS.TIME_STAMP,
        NR_NEWS_RECORDS.BRIEF_DESC,
        NR_NEWS_RECORDS.IS_BRIEF_DESC_RTL,
        NR_NEWS_RECORDS.ACTOR_UUID,
        NR_NEWS_RECORDS.EVENT_RECORD_UUID,
        NR_NEWS_RECORDS.RELATED_COMM_UUID,
        NR_NEWS_RECORDS.RELATED_COMM_NAME,
        NR_NEWS_RECORDS.TAGS,
        NR_NEWS_RECORDS.META_TEMPLATE,
        NR_NEWS_RECORDS.TEXT_META_TEMPLATE,
        NR_NEWS_RECORDS.IS_CONTAINER,
        NR_NEWS_RECORDS.ITEM_ID,
        NR_NEWS_RECORDS.ITEM_CORRELATION_ID,
        0,
        0,
        0,
        NR_NEWS_RECORDS.NEWS_RECORDS_ID
FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS, HOMEPAGE.NR_SOURCE_WATCHED NR_SOURCE_WATCHED
WHERE   NR_NEWS_RECORDS.READER_ID IS NULL AND
        NR_NEWS_RECORDS.IS_CONTAINER = 1 AND
        NR_NEWS_RECORDS.SOURCE LIKE 'tag%' AND
        NR_NEWS_RECORDS.CONTAINER_ID = NR_SOURCE_WATCHED.CONTAINER_ID;		
GO		

UPDATE 	HOMEPAGE.NR_TEMPLATE SET DATA_SOURCE_STRING='collection.name;htmlURL'
WHERE 	TEMPLATE_ID='collection-7jEWoKkWx8ucNTo6Z7AndhRFh';

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 31
------------------------------------------------------------------------------------------------
-- UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 31 , RELEASEVER = '3.0.0'
-- WHERE   DBSCHEMAVER = 30;
-- GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 32
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup32.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 33
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup33.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 34
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup34_b.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 35
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
GO

{include.news-fixup35.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 36
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup36.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 37
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup37.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 38
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup38.sql}
GO


-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 39
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup39.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 40
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup40.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 41
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup41.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 43
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup43.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 44
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup44.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 45
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup45.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 46
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup46.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 47
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup47.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 48
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup48.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 49
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup49.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 50
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup50.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 51
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup51.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 52
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup52.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 53
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup53.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 54
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup54.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start NEWS FIXUP 56
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup56.sql}
GO