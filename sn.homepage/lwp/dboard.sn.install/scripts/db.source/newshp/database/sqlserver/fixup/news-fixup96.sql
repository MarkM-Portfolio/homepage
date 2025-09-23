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

---------------------------------------------------------------------
-- Add a column in the SOURCE_TYPE table storing the 
---------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE
	ADD IMAGE_URL_SSL nvarchar(2048);
GO


DELETE FROM HOMEPAGE.NR_SOURCE_TYPE;

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('activities_c9cax4cc4x80bx51af2ddef2c', 1, 'activities', 'activities', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconActivities16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconActivities16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('blogs_c9cax4cc4x80bx51af2ddef2c', 2, 'blogs', 'blogs', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconBlogs16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconActivities16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('communities_c9cax4cc4x80bx51af2d', 3, 'communities', 'communities', '${connections}/resources/web/com.ibm.lconn.core.styles/images//iconCommunities16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconActivities16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('wikis_c9cax4cc4x80bx51af2ddef2c', 4, 'wikis', 'wikis', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconWikis16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconActivities16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('profiles_c9cax4cc4x80bx51af2ddef2c', 5, 'profiles', 'profiles', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconProfiles16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconActivities16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('homepage_c9cax4cc4x80bx51af2ddef2c', 6, 'homepage', 'homepage', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconHome16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconActivities16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('dogear_c9cax4cc4x80bx51af2ddef2c', 7, 'dogear', 'dogear', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconActivities16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('files_c9cax4cc4x80bx51af2ddef2c', 8, 'files', 'files', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconFiles16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconActivities16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('forums_c9cax4cc4x80bx51af2ddef2c', 9, 'forums', 'forums', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconForums16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconActivities16.png');

GO

---------------------------------------------------------------------
-- Add the following columns  in stories and entries tables
---------------------------------------------------------------------
--* ITEM_AUTHOR_UUID nvarchar 36
--* ITEM_AUTHOR_DISPLAYNAME nvarchar 256
--* ITEM_CORRELATION_AUTHOR_UUID nvarchar 36
--* ITEM_CORRELATION_AUTHOR_NAME nvarchar 256

-- [ENTRIES]

--1) NR_ENTRIES_ACT
ALTER TABLE HOMEPAGE.NR_ENTRIES_ACT
	ADD ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	BRIEF_DESC nvarchar (4000);
	;
GO



--2) NR_ENTRIES_BLG
ALTER TABLE HOMEPAGE.NR_ENTRIES_BLG
	ADD ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	BRIEF_DESC nvarchar (4000);
GO


--3) NR_ENTRIES_COM
ALTER TABLE HOMEPAGE.NR_ENTRIES_COM
	ADD ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	BRIEF_DESC nvarchar (4000);
GO


--4) NR_ENTRIES_WIK
ALTER TABLE HOMEPAGE.NR_ENTRIES_WIK
	ADD ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	BRIEF_DESC nvarchar (4000);
GO


--5) NR_ENTRIES_PRF
ALTER TABLE HOMEPAGE.NR_ENTRIES_PRF
	ADD ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	BRIEF_DESC nvarchar (4000);
GO


--6) NR_ENTRIES_HP
ALTER TABLE HOMEPAGE.NR_ENTRIES_HP
	ADD ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	BRIEF_DESC nvarchar (4000);
GO


--7) NR_ENTRIES_DGR
ALTER TABLE HOMEPAGE.NR_ENTRIES_DGR
	ADD ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	BRIEF_DESC nvarchar (4000);
GO


--8) NR_ENTRIES_FILE
ALTER TABLE HOMEPAGE.NR_ENTRIES_FILE
	ADD ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	BRIEF_DESC nvarchar (4000);
GO


--9) NR_ENTRIES_FRM
ALTER TABLE HOMEPAGE.NR_ENTRIES_FRM
	ADD ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	BRIEF_DESC nvarchar (4000);
GO


--10) NR_ENTRIES_EXTERNAL
ALTER TABLE HOMEPAGE.NR_ENTRIES_EXTERNAL
	ADD ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	BRIEF_DESC nvarchar (4000);
GO


-- [STORIES]



----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------
--	META_TEMPLATE -> reduce to 3328
--	R_META_TEMPLATE -> reduce to  3328

--	PRIMARY_ACTION_URL -> reduce to 768 
--	SECONDARY_ACTION_URL -> reduce to 768 

--	ACTIVITY_META_DATA_1 -> reduce to 3584
--	ACTIVITY_META_DATA_2 -> reduce to 3584


------------------------------
-- i) COMMIT all the work
-- ii) enable xp_cmdshell
-----------------------------
COMMIT;


EXEC master.dbo.sp_configure 'show advanced options', 1
RECONFIGURE
EXEC master.dbo.sp_configure 'xp_cmdshell', 1
RECONFIGURE

---------------------------------
-- Move the data
---------------------------------


-----------------------------------------------------------------------
-- [start] ************ HOMEPAGE.NR_SRC_STORIES_ACT *********************
-----------------------------------------------------------------------
BEGIN TRANSACTION
GO

-- back up
CREATE TABLE HOMEPAGE.TMP_SRC (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(1536),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(4000),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(4000),
	ACTIVITY_META_DATA_2 nvarchar(4000),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(1024), 
	SECONDARY_ACTION_URL nvarchar(1024),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),	
) ON [PRIMARY]
GO

--set integrity for HOMEPAGE.TMP_SRC all immediate unchecked; 
--reorg table HOMEPAGE.TMP_SRC;

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	from HOMEPAGE.NR_SRC_STORIES_ACT
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.TMP_SRC' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.NR_SRC_STORIES_ACT;
GO

COMMIT;

BEGIN TRANSACTION
GO

-- restoring
----------------------------------------------------------------------
-- 1) HOMEPAGE.NR_SRC_STORIES_ACT
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_ACT (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(4000),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(3328) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(3328),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(3584),
	ACTIVITY_META_DATA_2 nvarchar(3584),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(768), 
	SECONDARY_ACTION_URL nvarchar(768),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),	
	CONSTRAINT   	CK_SRC1_TYPE
    				CHECK
    				(SOURCE_TYPE = 1)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_ACT
    ADD CONSTRAINT PK_ACT_STORY_ID PRIMARY KEY(STORY_ID);

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_ACT
  	ADD CONSTRAINT FK_ENTRY_ID_ACT FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.NR_ENTRIES_ACT (ENTRY_ID);    

CREATE INDEX NR_SRC_STORIES_ACT_DATE
	ON HOMEPAGE.NR_SRC_STORIES_ACT(CREATION_DATE DESC);

CREATE INDEX SRC_ACT_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_ACT (CONTAINER_ID);

CREATE INDEX SRC_ACT_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_ACT (ITEM_CORRELATION_ID);

CREATE INDEX NR_SRC_STORIES_ACT_EIDX
    ON HOMEPAGE.NR_SRC_STORIES_ACT (ENTRY_ID);
    
CREATE INDEX  NR_SRC_STORIES_ACT_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_ACT (EVENT_RECORD_UUID, ENTRY_ID);

GO    

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME	
	FROM 	HOMEPAGE.TMP_SRC
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_SRC_STORIES_ACT' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.TMP_SRC;
GO

COMMIT;
-----------------------------------------------------------------------
-- [end] ************ HOMEPAGE.NR_SRC_STORIES_ACT *********************
-----------------------------------------------------------------------

-----------------------------------------------------------------------
-- [start] ************ HOMEPAGE.NR_SRC_STORIES_BLG *********************
-----------------------------------------------------------------------
BEGIN TRANSACTION
GO

-- back up
CREATE TABLE HOMEPAGE.TMP_SRC (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(1536),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(4000),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(4000),
	ACTIVITY_META_DATA_2 nvarchar(4000),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(1024), 
	SECONDARY_ACTION_URL nvarchar(1024),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),	
) ON [PRIMARY]
GO

--set integrity for HOMEPAGE.TMP_SRC all immediate unchecked; 
--reorg table HOMEPAGE.TMP_SRC;

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	from HOMEPAGE.NR_SRC_STORIES_BLG
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.TMP_SRC' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.NR_SRC_STORIES_BLG;
GO

COMMIT;

BEGIN TRANSACTION
GO

-- restoring
----------------------------------------------------------------------
-- 1) HOMEPAGE.NR_SRC_STORIES_BLG
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_BLG (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(4000),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(3328) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(3328),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(3584),
	ACTIVITY_META_DATA_2 nvarchar(3584),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(768), 
	SECONDARY_ACTION_URL nvarchar(768),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	CONSTRAINT   	CK_SRC2_TYPE
    				CHECK
    				(SOURCE_TYPE = 2)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_BLG
    ADD CONSTRAINT PK_BLG_STORY_ID PRIMARY KEY (STORY_ID);

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_BLG
  	ADD CONSTRAINT FK_ENTRY_ID_BLG FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.NR_ENTRIES_BLG (ENTRY_ID);  
	
CREATE INDEX NR_SRC_STORIES_BLG_DATE
	ON HOMEPAGE.NR_SRC_STORIES_BLG(CREATION_DATE DESC);

CREATE INDEX SRC_BLG_STORIES_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_BLG (CONTAINER_ID);

CREATE INDEX SRC_BLG_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_BLG (ITEM_CORRELATION_ID);

CREATE INDEX NR_SRC_STORIES_BLG_EIDX
    ON HOMEPAGE.NR_SRC_STORIES_BLG (ENTRY_ID);
    
CREATE INDEX NR_SRC_STORIES_BLG_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_BLG (EVENT_RECORD_UUID, ENTRY_ID);  

GO    

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME	
	FROM 	HOMEPAGE.TMP_SRC
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_SRC_STORIES_BLG' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.TMP_SRC;
GO

COMMIT;
-----------------------------------------------------------------------
-- [end] ************ HOMEPAGE.NR_SRC_STORIES_BLG *********************
-----------------------------------------------------------------------

-----------------------------------------------------------------------
-- [start] ************ HOMEPAGE.NR_SRC_STORIES_COM *********************
-----------------------------------------------------------------------
BEGIN TRANSACTION
GO

-- back up
CREATE TABLE HOMEPAGE.TMP_SRC (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(1536),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(4000),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(4000),
	ACTIVITY_META_DATA_2 nvarchar(4000),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(1024), 
	SECONDARY_ACTION_URL nvarchar(1024),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),	
) ON [PRIMARY]
GO

--set integrity for HOMEPAGE.TMP_SRC all immediate unchecked; 
--reorg table HOMEPAGE.TMP_SRC;

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	from HOMEPAGE.NR_SRC_STORIES_COM
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.TMP_SRC' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.NR_SRC_STORIES_COM;
GO

COMMIT;

BEGIN TRANSACTION
GO

-- restoring
----------------------------------------------------------------------
-- 1) HOMEPAGE.NR_SRC_STORIES_COM
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_COM (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(4000),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(3328) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(3328),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(3584),
	ACTIVITY_META_DATA_2 nvarchar(3584),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(768), 
	SECONDARY_ACTION_URL nvarchar(768),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	CONSTRAINT   	CK_SRC3_TYPE
    				CHECK
    				(SOURCE_TYPE = 3)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_COM
    ADD CONSTRAINT PK_COM_STORY_ID PRIMARY KEY(STORY_ID);

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_COM
  	ADD CONSTRAINT FK_ENTRY_ID_COM FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.NR_ENTRIES_COM (ENTRY_ID);
	
CREATE INDEX NR_SRC_STORIES_COM_DATE
	ON HOMEPAGE.NR_SRC_STORIES_COM (CREATION_DATE DESC);

CREATE INDEX SRC_COM_STORIES_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_COM (CONTAINER_ID);

CREATE INDEX SRC_COM_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_COM (ITEM_CORRELATION_ID);

CREATE INDEX NR_SRC_STORIES_COM_EIDX
    ON HOMEPAGE.NR_SRC_STORIES_COM (ENTRY_ID);

CREATE INDEX NR_SRC_STORIES_COM_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_COM (EVENT_RECORD_UUID, ENTRY_ID);

GO    

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME	
	FROM 	HOMEPAGE.TMP_SRC
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_SRC_STORIES_COM' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.TMP_SRC;
GO

COMMIT;
-----------------------------------------------------------------------
-- [end] ************ HOMEPAGE.NR_SRC_STORIES_COM *********************
-----------------------------------------------------------------------

-----------------------------------------------------------------------
-- [start] ************ HOMEPAGE.NR_SRC_STORIES_WIK *********************
-----------------------------------------------------------------------
BEGIN TRANSACTION
GO

-- back up
CREATE TABLE HOMEPAGE.TMP_SRC (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(1536),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(4000),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(4000),
	ACTIVITY_META_DATA_2 nvarchar(4000),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(1024), 
	SECONDARY_ACTION_URL nvarchar(1024),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),	
) ON [PRIMARY]
GO

--set integrity for HOMEPAGE.TMP_SRC all immediate unchecked; 
--reorg table HOMEPAGE.TMP_SRC;

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	from HOMEPAGE.NR_SRC_STORIES_WIK
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.TMP_SRC' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.NR_SRC_STORIES_WIK;
GO

COMMIT;

BEGIN TRANSACTION
GO

-- restoring
----------------------------------------------------------------------
-- 1) HOMEPAGE.NR_SRC_STORIES_WIK
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_WIK (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(4000),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(3328) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(3328),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(3584),
	ACTIVITY_META_DATA_2 nvarchar(3584),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(768), 
	SECONDARY_ACTION_URL nvarchar(768),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	CONSTRAINT   	CK_SRC4_TYPE
    				CHECK
    				(SOURCE_TYPE = 4)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_WIK
    ADD CONSTRAINT PK_WIK_STORY_ID PRIMARY KEY(STORY_ID);

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_WIK
  	ADD CONSTRAINT FK_ENTRY_ID_WIK FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.NR_ENTRIES_WIK (ENTRY_ID);
	
CREATE INDEX NR_SRC_STORIES_WIK_DATE
	ON HOMEPAGE.NR_SRC_STORIES_WIK(CREATION_DATE DESC);

CREATE INDEX SRC_WIK_STORIES_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_WIK (CONTAINER_ID);

CREATE INDEX SRC_WIK_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_WIK (ITEM_CORRELATION_ID);

CREATE INDEX NR_SRC_STORIES_WIK_EIDX
    ON HOMEPAGE.NR_SRC_STORIES_WIK (ENTRY_ID);    

CREATE INDEX NR_SRC_STORIES_WIK_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_WIK (EVENT_RECORD_UUID, ENTRY_ID);
GO    

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	FROM 	HOMEPAGE.TMP_SRC
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_SRC_STORIES_WIK' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.TMP_SRC;
GO

COMMIT;
-----------------------------------------------------------------------
-- [end] ************ HOMEPAGE.NR_SRC_STORIES_WIK *********************
-----------------------------------------------------------------------

-----------------------------------------------------------------------
-- [start] ************ HOMEPAGE.NR_SRC_STORIES_PRF *********************
-----------------------------------------------------------------------
BEGIN TRANSACTION
GO

-- back up
CREATE TABLE HOMEPAGE.TMP_SRC (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(1536),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(4000),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(4000),
	ACTIVITY_META_DATA_2 nvarchar(4000),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(1024), 
	SECONDARY_ACTION_URL nvarchar(1024),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),	
) ON [PRIMARY]
GO

--set integrity for HOMEPAGE.TMP_SRC all immediate unchecked; 
--reorg table HOMEPAGE.TMP_SRC;

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	from HOMEPAGE.NR_SRC_STORIES_PRF
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.TMP_SRC' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.NR_SRC_STORIES_PRF;
GO

COMMIT;

BEGIN TRANSACTION
GO

-- restoring
----------------------------------------------------------------------
-- 1) HOMEPAGE.NR_SRC_STORIES_PRF
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_PRF (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(4000),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(3328) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(3328),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(3584),
	ACTIVITY_META_DATA_2 nvarchar(3584),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(768), 
	SECONDARY_ACTION_URL nvarchar(768),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	CONSTRAINT   	CK_SRC5_TYPE
    				CHECK
    				(SOURCE_TYPE = 5)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_PRF
    ADD CONSTRAINT PK_PRF_STORY_ID PRIMARY KEY(STORY_ID);

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_PRF
  	ADD CONSTRAINT FK_ENTRY_ID_PRF FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.NR_ENTRIES_PRF (ENTRY_ID);     

CREATE INDEX NR_SRC_STORIES_PRF_DATE
	ON HOMEPAGE.NR_SRC_STORIES_PRF(CREATION_DATE DESC);

CREATE INDEX SRC_PRF_STORIES_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_PRF (CONTAINER_ID);

CREATE INDEX SRC_PRF_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_PRF (ITEM_CORRELATION_ID);

CREATE INDEX NR_SRC_STORIES_PRF_EIDX
    ON HOMEPAGE.NR_SRC_STORIES_PRF (ENTRY_ID);
    
CREATE INDEX NR_SRC_STORIES_PRF_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_PRF (EVENT_RECORD_UUID, ENTRY_ID); 
GO    

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	FROM 	HOMEPAGE.TMP_SRC
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_SRC_STORIES_PRF' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.TMP_SRC;
GO

COMMIT;
-----------------------------------------------------------------------
-- [end] ************ HOMEPAGE.NR_SRC_STORIES_PRF *********************
-----------------------------------------------------------------------

-----------------------------------------------------------------------
-- [start] ************ HOMEPAGE.NR_SRC_STORIES_HP *********************
-----------------------------------------------------------------------
BEGIN TRANSACTION
GO

-- back up
CREATE TABLE HOMEPAGE.TMP_SRC (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(1536),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(4000),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(4000),
	ACTIVITY_META_DATA_2 nvarchar(4000),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(1024), 
	SECONDARY_ACTION_URL nvarchar(1024),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),	
) ON [PRIMARY]
GO

--set integrity for HOMEPAGE.TMP_SRC all immediate unchecked; 
--reorg table HOMEPAGE.TMP_SRC;

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	from HOMEPAGE.NR_SRC_STORIES_HP
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.TMP_SRC' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.NR_SRC_STORIES_HP;
GO

COMMIT;

BEGIN TRANSACTION
GO

-- restoring
----------------------------------------------------------------------
-- 1) HOMEPAGE.NR_SRC_STORIES_HP
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_HP (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(4000),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(3328) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(3328),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(3584),
	ACTIVITY_META_DATA_2 nvarchar(3584),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(768), 
	SECONDARY_ACTION_URL nvarchar(768),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	CONSTRAINT   	CK_SRC6_TYPE
    				CHECK
    				(SOURCE_TYPE = 6)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_HP
    ADD CONSTRAINT PK_HP_STORY_ID PRIMARY KEY (STORY_ID);

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_HP
  	ADD CONSTRAINT FK_ENTRY_ID_HP FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.NR_ENTRIES_HP (ENTRY_ID);
	
CREATE INDEX NR_SRC_STORIES_HP_DATE
	ON HOMEPAGE.NR_SRC_STORIES_HP(CREATION_DATE DESC);

CREATE INDEX SRC_HP_STORIES_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_HP (CONTAINER_ID);

CREATE INDEX SRC_HP_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_HP (ITEM_CORRELATION_ID);

CREATE INDEX NR_SRC_STORIES_HP_EIDX
    ON HOMEPAGE.NR_SRC_STORIES_HP (ENTRY_ID);
    
CREATE INDEX NR_SRC_STORIES_HP_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_HP (EVENT_RECORD_UUID, ENTRY_ID); 
    
GO    

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	FROM 	HOMEPAGE.TMP_SRC
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_SRC_STORIES_HP' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.TMP_SRC;
GO

COMMIT;
-----------------------------------------------------------------------
-- [end] ************ HOMEPAGE.NR_SRC_STORIES_HP *********************
-----------------------------------------------------------------------

-----------------------------------------------------------------------
-- [start] ************ HOMEPAGE.NR_SRC_STORIES_DGR *********************
-----------------------------------------------------------------------
BEGIN TRANSACTION
GO

-- back up
CREATE TABLE HOMEPAGE.TMP_SRC (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(1536),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(4000),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(4000),
	ACTIVITY_META_DATA_2 nvarchar(4000),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(1024), 
	SECONDARY_ACTION_URL nvarchar(1024),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),	
) ON [PRIMARY]
GO

--set integrity for HOMEPAGE.TMP_SRC all immediate unchecked; 
--reorg table HOMEPAGE.TMP_SRC;

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	from HOMEPAGE.NR_SRC_STORIES_DGR
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.TMP_SRC' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.NR_SRC_STORIES_DGR;
GO

COMMIT;

BEGIN TRANSACTION
GO

-- restoring
----------------------------------------------------------------------
-- 1) HOMEPAGE.NR_SRC_STORIES_DGR
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_DGR (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(4000),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(3328) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(3328),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(3584),
	ACTIVITY_META_DATA_2 nvarchar(3584),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(768), 
	SECONDARY_ACTION_URL nvarchar(768),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	CONSTRAINT   	CK_SRC7_TYPE
    				CHECK
    				(SOURCE_TYPE = 7)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_DGR
    ADD CONSTRAINT PK_DGR_STORY_ID PRIMARY KEY(STORY_ID);

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_DGR
  	ADD CONSTRAINT FK_ENTRY_ID_DGR FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.NR_ENTRIES_DGR (ENTRY_ID);     

CREATE INDEX NR_SRC_STORIES_DGR_DATE
	ON HOMEPAGE.NR_SRC_STORIES_DGR(CREATION_DATE DESC);

CREATE INDEX SRC_DGR_STORIES_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_DGR (CONTAINER_ID);

CREATE INDEX SRC_DGR_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_DGR (ITEM_CORRELATION_ID);

CREATE INDEX NR_SRC_STORIES_DGR_EIDX
    ON HOMEPAGE.NR_SRC_STORIES_DGR (ENTRY_ID);
    
CREATE INDEX NR_SRC_STORIES_DGR_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_DGR (EVENT_RECORD_UUID, ENTRY_ID); 
    
GO    

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	FROM 	HOMEPAGE.TMP_SRC
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_SRC_STORIES_DGR' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.TMP_SRC;
GO

COMMIT;
-----------------------------------------------------------------------
-- [end] ************ HOMEPAGE.NR_SRC_STORIES_DGR *********************
-----------------------------------------------------------------------

-----------------------------------------------------------------------
-- [start] ************ HOMEPAGE.NR_SRC_STORIES_FILE *********************
-----------------------------------------------------------------------
BEGIN TRANSACTION
GO

-- back up
CREATE TABLE HOMEPAGE.TMP_SRC (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(1536),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(4000),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(4000),
	ACTIVITY_META_DATA_2 nvarchar(4000),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(1024), 
	SECONDARY_ACTION_URL nvarchar(1024),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),	
) ON [PRIMARY]
GO

--set integrity for HOMEPAGE.TMP_SRC all immediate unchecked; 
--reorg table HOMEPAGE.TMP_SRC;

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	from HOMEPAGE.NR_SRC_STORIES_FILE
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.TMP_SRC' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.NR_SRC_STORIES_FILE;
GO

COMMIT;

BEGIN TRANSACTION
GO

-- restoring
----------------------------------------------------------------------
-- 1) HOMEPAGE.NR_SRC_STORIES_FILE
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_FILE (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(4000),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(3328) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(3328),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(3584),
	ACTIVITY_META_DATA_2 nvarchar(3584),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(768), 
	SECONDARY_ACTION_URL nvarchar(768),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	CONSTRAINT   	CK_SRC8_TYPE
    				CHECK
    				(SOURCE_TYPE = 8)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_FILE
    ADD CONSTRAINT PK_FILE_STORY_ID PRIMARY KEY(STORY_ID);

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_FILE
  	ADD CONSTRAINT FK_ENTRY_ID_FILE FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.NR_ENTRIES_FILE (ENTRY_ID);
	
CREATE INDEX NR_SRC_STORIES_FILE_DATE
	ON HOMEPAGE.NR_SRC_STORIES_FILE(CREATION_DATE DESC);

CREATE INDEX SRC_FILE_STORIES_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_FILE (CONTAINER_ID);

CREATE INDEX SRC_FILE_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_FILE (ITEM_CORRELATION_ID);

CREATE INDEX NR_SRC_STORIES_FILE_EIDX
    ON HOMEPAGE.NR_SRC_STORIES_FILE (ENTRY_ID);
    
CREATE INDEX NR_SRC_STORIES_FILE_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_FILE (EVENT_RECORD_UUID, ENTRY_ID);  
    
GO    

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	FROM 	HOMEPAGE.TMP_SRC
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_SRC_STORIES_FILE' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.TMP_SRC;
GO

COMMIT;
-----------------------------------------------------------------------
-- [end] ************ HOMEPAGE.NR_SRC_STORIES_FILE *********************
-----------------------------------------------------------------------

-----------------------------------------------------------------------
-- [start] ************ HOMEPAGE.NR_SRC_STORIES_FRM *********************
-----------------------------------------------------------------------
BEGIN TRANSACTION
GO

-- back up
CREATE TABLE HOMEPAGE.TMP_SRC (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(1536),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(4000),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(4000),
	ACTIVITY_META_DATA_2 nvarchar(4000),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(1024), 
	SECONDARY_ACTION_URL nvarchar(1024),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),	
) ON [PRIMARY]
GO

--set integrity for HOMEPAGE.TMP_SRC all immediate unchecked; 
--reorg table HOMEPAGE.TMP_SRC;

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	from HOMEPAGE.NR_SRC_STORIES_FRM
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.TMP_SRC' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.NR_SRC_STORIES_FRM;
GO

COMMIT;

BEGIN TRANSACTION
GO

-- restoring
----------------------------------------------------------------------
-- 1) HOMEPAGE.NR_SRC_STORIES_FRM
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_FRM  (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(4000),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(3328) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(3328),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(3584),
	ACTIVITY_META_DATA_2 nvarchar(3584),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(768), 
	SECONDARY_ACTION_URL nvarchar(768),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	CONSTRAINT   	CK_SRC9_TYPE
    				CHECK
    				(SOURCE_TYPE = 9)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_FRM 
    ADD CONSTRAINT PK_FRM_STORY_ID PRIMARY KEY(STORY_ID);

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_FRM
  	ADD CONSTRAINT FK_ENTRY_ID_FRM FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.NR_ENTRIES_FRM (ENTRY_ID); 
	
CREATE INDEX NR_SRC_STORIES_FRM_DATE
	ON HOMEPAGE.NR_SRC_STORIES_FRM (CREATION_DATE DESC);

CREATE INDEX SRC_FRM_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_FRM  (CONTAINER_ID);

CREATE INDEX SRC_FRM_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_FRM  (ITEM_CORRELATION_ID);

CREATE INDEX NR_SRC_STORIES_FRM_EIDX
    ON HOMEPAGE.NR_SRC_STORIES_FRM (ENTRY_ID);
    
CREATE INDEX NR_SRC_STORIES_FRM_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_FRM (EVENT_RECORD_UUID, ENTRY_ID);  
    
GO    

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	FROM 	HOMEPAGE.TMP_SRC
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_SRC_STORIES_FRM' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.TMP_SRC;
GO

COMMIT;
-----------------------------------------------------------------------
-- [end] ************ HOMEPAGE.NR_SRC_STORIES_FRM *********************
-----------------------------------------------------------------------

-----------------------------------------------------------------------
-- [start] ************ HOMEPAGE.NR_SRC_STORIES_EXTERNAL *********************
-----------------------------------------------------------------------
BEGIN TRANSACTION
GO

-- back up
CREATE TABLE HOMEPAGE.TMP_SRC (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(1536),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(4000),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(4000),
	ACTIVITY_META_DATA_2 nvarchar(4000),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(1024), 
	SECONDARY_ACTION_URL nvarchar(1024),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),	
) ON [PRIMARY]
GO

--set integrity for HOMEPAGE.TMP_SRC all immediate unchecked; 
--reorg table HOMEPAGE.TMP_SRC;

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	from HOMEPAGE.NR_SRC_STORIES_EXTERNAL
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.TMP_SRC' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.NR_SRC_STORIES_EXTERNAL;
GO

COMMIT;

BEGIN TRANSACTION
GO

-- restoring
----------------------------------------------------------------------
-- 1) HOMEPAGE.NR_SRC_STORIES_EXTERNAL
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_EXTERNAL (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),
	ITEM_TAGS nvarchar(1024),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(4000),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(3328) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(3328),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ACTIVITY_META_DATA_1 nvarchar(3584),
	ACTIVITY_META_DATA_2 nvarchar(3584),
	IS_META_DATA_TRUNCATED  NUMERIC(5,0),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(768), 
	SECONDARY_ACTION_URL nvarchar(768),
	ITEM_AUTHOR_UUID nvarchar (36),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (36),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	CONSTRAINT   	CK_SRC100_TYPE
    				CHECK
    				(SOURCE_TYPE >= 100)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_EXTERNAL
    ADD CONSTRAINT PK_EXT_STORY_ID PRIMARY KEY(STORY_ID);

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_EXTERNAL
  	ADD CONSTRAINT FK_ENTRY_ID_EXT FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.NR_ENTRIES_EXTERNAL (ENTRY_ID);    

CREATE INDEX SRC_STORIES_EXT_DATE
	ON HOMEPAGE.NR_SRC_STORIES_EXTERNAL (CREATION_DATE DESC);

CREATE INDEX SRC_EXT_CONTAINER_ID
    ON HOMEPAGE.NR_SRC_STORIES_EXTERNAL (CONTAINER_ID);

CREATE INDEX SRC_EXT_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_EXTERNAL (ITEM_CORRELATION_ID);
    
CREATE INDEX SRC_STORIES_EXT_EIDX
    ON HOMEPAGE.NR_SRC_STORIES_EXTERNAL (ENTRY_ID);
    
CREATE INDEX NR_SRC_STORIES_EXT_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_EXTERNAL (EVENT_RECORD_UUID, ENTRY_ID);  
    
GO    

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	select 	
			STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL,
			ITEM_ID, ITEM_CORRELATION_ID, ITEM_TAGS, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE,
			TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, HAS_ATTACHMENT,
			SOURCE_TYPE, ENTRY_ID, EVENT_TIME, VERB, ACTIVITY_META_DATA_1, ACTIVITY_META_DATA_2, IS_META_DATA_TRUNCATED,
			ITEM_SCOPE, ITEM_UPDATE_DATE, FIRST_RECIPIENT_ID, NUM_RECIPIENTS, PRIMARY_ACTION_URL, SECONDARY_ACTION_URL,
			' ' ITEM_AUTHOR_UUID, ' ' ITEM_AUTHOR_DISPLAYNAME, ' ' ITEM_CORRELATION_AUTHOR_UUID, ' ' ITEM_CORRELATION_AUTHOR_NAME
	FROM 	HOMEPAGE.TMP_SRC
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER;
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_SRC_STORIES_EXTERNAL' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;

BEGIN TRANSACTION
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

DROP TABLE HOMEPAGE.TMP_SRC;
GO

COMMIT;
-----------------------------------------------------------------------
-- [end] ************ HOMEPAGE.NR_SRC_STORIES_EXTERNAL *********************
-----------------------------------------------------------------------

----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------

BEGIN TRANSACTION
GO

--------------------------------------------------------------------
--------------------------------------------------------------------
-- [START] SAVED READERS TABLE
--------------------------------------------------------------------
--------------------------------------------------------------------
-- Adding four new categories for ui rendering of actianable stories
UPDATE HOMEPAGE.NR_CATEGORY_TYPE SET 
	CATEGORY_TYPE_ID = 'saved_readers',  
	CATEGORY_TYPE_NAME = '%saved_readers', 
	CATEGORY_TYPE_DESC = 'saved_readers'
	WHERE CATEGORY_TYPE = 13;
GO
	
CREATE TABLE HOMEPAGE.NR_SAVED_READERS (
	CATEGORY_READER_ID nvarchar(36)  DEFAULT ' ' NOT NULL,
	READER_ID nvarchar(36) NOT NULL,
	CATEGORY_TYPE NUMERIC(5,0) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(256),
	ITEM_ID nvarchar(36),
	ROLLUP_ENTRY_ID nvarchar(36),
	RESOURCE_TYPE NUMERIC(5,0) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0),
	USE_IN_ROLLUP NUMERIC(5,0),
	IS_NETWORK	NUMERIC(5,0),
	IS_FOLLOWER	NUMERIC(5,0),
	EVENT_TIME DATETIME,
	NOTE_TEXT nvarchar(4000),
	NOTE_UPDATE_DATE DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(36),
	OPERATION_ID nvarchar(512),
	ACTOR_UUID nvarchar(36),
	CONSTRAINT   	CK_CAT_SAVED_TYPE
    				CHECK
    				(CATEGORY_TYPE = 13)	
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SAVED_READERS 
    ADD CONSTRAINT PK_SAVED_READERS PRIMARY KEY (CATEGORY_READER_ID);
    
CREATE INDEX SAVED_READERS_STORIES_IDX
    ON HOMEPAGE.NR_SAVED_READERS (READER_ID, CREATION_DATE ASC);

CREATE INDEX SAVED_READERS_SIDX
    ON HOMEPAGE.NR_SAVED_READERS (STORY_ID);

CREATE INDEX SAVED_READERS_ITEM_ID
    ON HOMEPAGE.NR_SAVED_READERS (ITEM_ID);

CREATE UNIQUE INDEX SAVED_READERS_STORY
    ON HOMEPAGE.NR_SAVED_READERS (READER_ID, STORY_ID);

CREATE INDEX SAVED_READERS_IDX
    ON HOMEPAGE.NR_SAVED_READERS (READER_ID, CREATION_DATE DESC, SOURCE_TYPE);
    
CREATE INDEX SAVED_READERS_ROLL
	ON HOMEPAGE.NR_SAVED_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE DESC, USE_IN_ROLLUP);

GO

----------------------------------------------------------------------	
--	Move all the saved stories from actionable to SAVED_READERS
----------------------------------------------------------------------
GO

CREATE VIEW HOMEPAGE.TMP_VIEW AS (
	SELECT 	CATEGORY_READER_ID,
			READER_ID, CATEGORY_TYPE, SOURCE, CONTAINER_ID, ITEM_ID, ROLLUP_ENTRY_ID, RESOURCE_TYPE, CREATION_DATE, STORY_ID, SOURCE_TYPE,
			USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, NOTE_TEXT, NOTE_UPDATE_DATE, IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, OPERATION_ID
	FROM HOMEPAGE.NR_ACTIONABLE_READERS WHERE CATEGORY_TYPE = 13
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_VIEW TO HOMEPAGEUSER
GO

------------------------------
-- i) COMMIT all the work
-- ii) enable xp_cmdshell
-----------------------------
COMMIT;


EXEC master.dbo.sp_configure 'show advanced options', 1
RECONFIGURE
EXEC master.dbo.sp_configure 'xp_cmdshell', 1
RECONFIGURE

---------------------------------
-- Move the data
---------------------------------

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_VIEW' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_SAVED_READERS' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

DROP VIEW HOMEPAGE.TMP_VIEW;
GO

COMMIT;

-----------------------------------
-- Disable xp_cmdshell
----------------------------------

EXEC master.dbo.sp_configure 'show advanced options', 1
RECONFIGURE
EXEC master.dbo.sp_configure 'xp_cmdshell', 0
RECONFIGURE

----------------------------------
-----------------------------------

BEGIN TRANSACTION
GO

DELETE FROM HOMEPAGE.NR_ACTIONABLE_READERS WHERE CATEGORY_TYPE = 13;
GO

-- Alter category type constraint in the NR_ACTIONABLE_READERS
ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS DROP CONSTRAINT CK_CAT_ACTION_TYPE;
GO

ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS ADD CONSTRAINT CK_CAT_ACTION_TYPE 
	CHECK (CATEGORY_TYPE >= 14 AND CATEGORY_TYPE <= 16);
GO


--------------------------------------------------------------------
--------------------------------------------------------------------
-- [END] SAVED READERS TABLE
--------------------------------------------------------------------
--------------------------------------------------------------------

----------------------------------------------------------------
----------------------------------------------------------------
-- [START] Adding ACTOR_UUID in READER tables
----------------------------------------------------------------
----------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_PROFILES_READERS ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_COMMUNITIES_READERS ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_BLOGS_READERS ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_BOOKMARKS_READERS ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_FILES_READERS ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_FORUMS_READERS ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_WIKIS_READERS ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_TAGS_READERS ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_STATUS_UPDATE_READERS ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_EXTERNAL_READERS ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_DISCOVERY_VIEW ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_PROFILES_VIEW ADD ACTOR_UUID nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_NOTIFICATION_SENT_READERS ADD ACTOR_UUID nvarchar(36);
GO
----------------------------------------------------------------
----------------------------------------------------------------
-- [END] Adding ACTOR_UUID in READER tables
----------------------------------------------------------------
----------------------------------------------------------------