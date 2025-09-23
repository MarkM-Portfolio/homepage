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

----------------------------------------------------------------
-- [START] Changing definition for SOURCE_TYPE table
----------------------------------------------------------------
DROP TABLE HOMEPAGE.NR_SOURCE_TYPE;

CREATE TABLE HOMEPAGE.NR_SOURCE_TYPE (
	SOURCE_TYPE_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0) NOT NULL, -- numeric that is 1,2,3 etc.. 100, 101..
	EXTERNAL_ID nvarchar(256) NOT NULL,
	DISPLAY_NAME nvarchar(4000),
	IMAGE_URL nvarchar(2048),
	PUBLISHED DATETIME,
	UPDATED nvarchar(256),	
	IS_ENABLED NUMERIC(5,0),
	SUMMARY nvarchar (4000)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
  	ADD CONSTRAINT PK_SRC_TYPE_ID PRIMARY KEY(SOURCE_TYPE_ID);

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
	ADD CONSTRAINT SRC_TYPE_UNQ UNIQUE(SOURCE_TYPE);

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
	ADD CONSTRAINT SRC_EXT_ID_UNQ UNIQUE(EXTERNAL_ID);
	
-- Update also the init data 

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY)
VALUES ('activities_c9cax4cc4x80bx51af2ddef2c', 1, 'int_activities_id', 'activities', null, null, null, 1, null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY)
VALUES ('blogs_c9cax4cc4x80bx51af2ddef2c', 2, 'int_blogs_id', 'blogs', null, null, null, 1, null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY)
VALUES ('communities_c9cax4cc4x80bx51af2d', 3, 'int_communities_id', 'communities', null, null, null, 1, null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY)
VALUES ('wikis_c9cax4cc4x80bx51af2ddef2c', 4, 'int_wikis_id', 'wikis', null, null, null, 1, null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY)
VALUES ('profiles_c9cax4cc4x80bx51af2ddef2c', 5, 'int_profiles_id', 'profiles', null, null, null, 1, null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY)
VALUES ('homepage_c9cax4cc4x80bx51af2ddef2c', 6, 'int_homepage_id', 'homepage', null, null, null, 1, null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY)
VALUES ('dogear_c9cax4cc4x80bx51af2ddef2c', 7, 'int_dogear_id', 'dogear', null, null, null, 1, null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY)
VALUES ('files_c9cax4cc4x80bx51af2ddef2c', 8, 'int_files_id', 'files', null, null, null, 1, null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY)
VALUES ('forums_c9cax4cc4x80bx51af2ddef2c', 9, 'int_forums_id', 'forums', null, null, null, 1, null);
	
	
----------------------------------------------------------------
-- [END] Changing definition for SOURCE_TYPE table
----------------------------------------------------------------

------------------------------------------------------
-- [START] Adding indexes to the readers tables
------------------------------------------------------

--DISCOVERY VIEW READERS:
CREATE INDEX DISCOVERY_STORIES_SRC_IDX
    ON HOMEPAGE.NR_DISCOVERY_VIEW (READER_ID, CREATION_DATE DESC, SOURCE_TYPE);
    
CREATE INDEX DISCOVERY_STORIES_COM_IDX
    ON HOMEPAGE.NR_DISCOVERY_VIEW (READER_ID, CREATION_DATE DESC, IS_STORY_COMM);

--EXTERNAL READERS
CREATE INDEX EXT_STORIES_SRC_IDX
    ON HOMEPAGE.NR_EXTERNAL_READERS (READER_ID, CREATION_DATE DESC, SOURCE_TYPE);

--RESPONSES READERS
CREATE INDEX RESPONSES_STORIES_SRC_IDX
    ON HOMEPAGE.NR_RESPONSES_READERS (READER_ID, CREATION_DATE DESC, SOURCE_TYPE);

--ACTIONABLE READERS
CREATE INDEX ACTION_STORIES_SRC_IDX
    ON HOMEPAGE.NR_ACTIONABLE_READERS (READER_ID, CREATION_DATE DESC, SOURCE_TYPE);
    
------------------------------------------------------
-- [END] Adding indexes to the readers tables
-----------------------------------------------------
    
------------------------------------------
-- Dropping HOMEPAGE.BOARD_READERS
-------------------------------------------
DROP TABLE HOMEPAGE.BOARD_READERS;

----------------------------------------------------------------------------------------------
-- [START] This is a list of indexes to speed up seedlist performance and profiles API
----------------------------------------------------------------------------------------------

-----------------------
-- BOARD_ENTRIES
-----------------------
DROP INDEX NEWS_BRD_UPDATE ON HOMEPAGE.BOARD_ENTRIES;
DROP INDEX NEWS_BRD_ITEM ON HOMEPAGE.BOARD_ENTRIES;

GO

CREATE UNIQUE INDEX ITEM_ID_IDX
   ON HOMEPAGE.BOARD_ENTRIES (ITEM_ID ASC) INCLUDE (CONTAINER_ID);

GO

CREATE INDEX CREATION_ITEM_IDX 
	ON HOMEPAGE.BOARD_ENTRIES (CREATION_DATE DESC, ITEM_ID DESC);

GO
   
---------------------
-- BOARD_COMMENTS
---------------------
CREATE INDEX CREATION_DATE_IDX 
	ON HOMEPAGE.BOARD_COMMENTS (CREATION_DATE ASC);

GO

CREATE INDEX ITEM_CORR_CREATION_IDX 
	ON HOMEPAGE.BOARD_COMMENTS (ITEM_CORRELATION_ID, CREATION_DATE ASC);

GO

CREATE INDEX ITEM_ITEM_CORR_IDX
	ON HOMEPAGE.BOARD_COMMENTS (ITEM_ID ASC, ITEM_CORRELATION_ID ASC);
   
----------------------
-- NR_NETWORK
----------------------
CREATE INDEX COLL_PERSON_IDX 
	ON HOMEPAGE.NR_NETWORK (COLLEAGUE_ID, PERSON_ID);

GO

----------------------------------------------------------------------------------------------
-- [END] This is a list of indexes to speed up seedlist performance and profiles API
----------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------

-------------------------------------------------------
-- 1) HOMEPAGE.BOARD
-------------------------------------------------------
CREATE TABLE HOMEPAGE.BOARD  (
	BOARD_ID nvarchar(256) NOT NULL,
	BOARD_CONTAINER_ID nvarchar(256) NOT NULL,
	BOARD_TYPE nvarchar(64) NOT NULL,
	BOARD_OWNER_ASSOC_ID nvarchar(36) NOT NULL,
	BOARD_OWNER_ASSOC_TYPE nvarchar(64) NOT NULL,
	CREATED DATETIME NOT NULL,
	CREATED_BY nvarchar(36) NOT NULL,
	LASTUPDATE DATETIME NOT NULL,
	LASTUPDATE_BY nvarchar(36) NOT NULL,
	IS_ENABLED NUMERIC(5, 0) DEFAULT 1 NOT NULL,
	VISIBILITY nvarchar(36),
	EDITABILITY nvarchar(36)		
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.BOARD 
	ADD CONSTRAINT BOARD_PK PRIMARY KEY (BOARD_ID);

ALTER TABLE HOMEPAGE.BOARD 
	ADD CONSTRAINT FK_BRD_OWNER FOREIGN KEY (BOARD_OWNER_ASSOC_ID) 
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

ALTER TABLE HOMEPAGE.BOARD 
	ADD CONSTRAINT FK_BRD_CREATED FOREIGN KEY (CREATED_BY) 
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);	

ALTER TABLE HOMEPAGE.BOARD 
	ADD CONSTRAINT FK_BRD_LASTUPDATE FOREIGN KEY (LASTUPDATE_BY) 
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

CREATE UNIQUE INDEX BOARD_OWNER_ASSOC_UIDX 
	ON HOMEPAGE.BOARD (BOARD_OWNER_ASSOC_ID ASC, BOARD_TYPE ASC);

ALTER TABLE HOMEPAGE.BOARD
	ADD CONSTRAINT CONTAINER_ID_UNQ UNIQUE (BOARD_CONTAINER_ID);
      
CREATE UNIQUE INDEX BRD_CONTAINER_ID_UIDX 
	ON HOMEPAGE.BOARD (BOARD_CONTAINER_ID);	
	
GO

-------------------------------------------------------
-- 2) Adding fk to the existing BOARD_ENTRIES
-------------------------------------------------------

--CLEAR BOARD_* tables before applying CONTAINER_ID FK
DELETE FROM HOMEPAGE.BOARD_OBJECT_REFERENCE;
DELETE FROM HOMEPAGE.BOARD_CURRENT_STATUS;
DELETE FROM HOMEPAGE.BOARD_RECOMMENDATIONS;
DELETE FROM HOMEPAGE.BOARD_COMMENTS;
DELETE FROM HOMEPAGE.BOARD_ENTRIES;

GO

ALTER TABLE HOMEPAGE.BOARD_ENTRIES
	ADD CONSTRAINT FK_CONTAINER_ID FOREIGN KEY (CONTAINER_ID) 
	REFERENCES HOMEPAGE.BOARD (BOARD_CONTAINER_ID);

CREATE INDEX BRD_E_CONTAINER_ID_UIDX 
	ON HOMEPAGE.BOARD_ENTRIES (CONTAINER_ID);

GO
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------

------------------------
-- BUG TYPO FIXING
------------------------
-- RENAME FROM:  N_RECCOMANDATIONS to the correct: N_RECOMMENDATIONS 
EXEC sp_rename 'HOMEPAGE.NR_ENTRIES_ACT.N_RECCOMANDATIONS', 'N_RECOMMANDATIONS' , 'COLUMN';
GO
EXEC sp_rename 'HOMEPAGE.NR_ENTRIES_BLG.N_RECCOMANDATIONS', 'N_RECOMMANDATIONS' , 'COLUMN';
GO
EXEC sp_rename 'HOMEPAGE.NR_ENTRIES_COM.N_RECCOMANDATIONS', 'N_RECOMMANDATIONS' , 'COLUMN';
GO
EXEC sp_rename 'HOMEPAGE.NR_ENTRIES_DGR.N_RECCOMANDATIONS', 'N_RECOMMANDATIONS' , 'COLUMN';
GO
EXEC sp_rename 'HOMEPAGE.NR_ENTRIES_EXTERNAL.N_RECCOMANDATIONS', 'N_RECOMMANDATIONS' , 'COLUMN';
GO
EXEC sp_rename 'HOMEPAGE.NR_ENTRIES_FILE.N_RECCOMANDATIONS', 'N_RECOMMANDATIONS' , 'COLUMN';
GO
EXEC sp_rename 'HOMEPAGE.NR_ENTRIES_FRM.N_RECCOMANDATIONS', 'N_RECOMMANDATIONS' , 'COLUMN';
GO
EXEC sp_rename 'HOMEPAGE.NR_ENTRIES_HP.N_RECCOMANDATIONS', 'N_RECOMMANDATIONS' , 'COLUMN';
GO
EXEC sp_rename 'HOMEPAGE.NR_ENTRIES_PRF.N_RECCOMANDATIONS', 'N_RECOMMANDATIONS' , 'COLUMN';
GO
EXEC sp_rename 'HOMEPAGE.NR_ENTRIES_WIK.N_RECCOMANDATIONS', 'N_RECOMMANDATIONS' , 'COLUMN';
GO





------------------------------
-- i) COMMIT all the work
-- ii) enable xp_cmdshell to allow use of bcp
-----------------------------
COMMIT;


EXEC master.dbo.sp_configure 'show advanced options', 1
RECONFIGURE
EXEC master.dbo.sp_configure 'xp_cmdshell', 1
RECONFIGURE

---------------------------------
-- Move the data
---------------------------------


------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
--	[START] DISCOVERY MIGRATION
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------



-- 1 ACTIVITIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.DISCOVERY AS (
	SELECT 	NR_NEWS_DISCOVERY.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			'00000000-0000-0000-0000-000000000001'			READER_ID,
			17										CATEGORY_TYPE,
			NR_NEWS_DISCOVERY.SOURCE						SOURCE,
			NR_NEWS_DISCOVERY.CONTAINER_ID				CONTAINER_ID,
			NR_NEWS_DISCOVERY.ITEM_ID					ITEM_ID,
			NULL										ROLLUP_ENTRY_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_DISCOVERY.CREATION_DATE				CREATION_DATE,
			NR_NEWS_DISCOVERY.NEWS_STORY_ID				STORY_ID,
			NR_NEWS_DISCOVERY.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_DISCOVERY.CREATION_DATE				EVENT_TIME,
			NR_NEWS_DISCOVERY.IS_COMMUNITY_STORY			IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID					
	FROM 	HOMEPAGE.NR_NEWS_DISCOVERY NR_NEWS_DISCOVERY,  
			HOMEPAGE.NR_SRC_STORIES_ACT NR_SRC_STORIES_ACT
	WHERE 	NR_NEWS_DISCOVERY.NEWS_STORY_ID =  NR_SRC_STORIES_ACT.STORY_ID
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.DISCOVERY TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.DISCOVERY' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_DISCOVERY_VIEW' -- Schema name and table name

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

DROP VIEW HOMEPAGE.DISCOVERY;

COMMIT;

-- 2 BLOGS
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.DISCOVERY AS (
	SELECT 	NR_NEWS_DISCOVERY.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			'00000000-0000-0000-0000-000000000001'			READER_ID,
			17										CATEGORY_TYPE,
			NR_NEWS_DISCOVERY.SOURCE						SOURCE,
			NR_NEWS_DISCOVERY.CONTAINER_ID				CONTAINER_ID,
			NR_NEWS_DISCOVERY.ITEM_ID					ITEM_ID,
			NULL										ROLLUP_ENTRY_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_DISCOVERY.CREATION_DATE				CREATION_DATE,
			NR_NEWS_DISCOVERY.NEWS_STORY_ID				STORY_ID,
			NR_NEWS_DISCOVERY.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_DISCOVERY.CREATION_DATE				EVENT_TIME,
			NR_NEWS_DISCOVERY.IS_COMMUNITY_STORY			IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID					
	FROM 	HOMEPAGE.NR_NEWS_DISCOVERY NR_NEWS_DISCOVERY,  
			HOMEPAGE.NR_SRC_STORIES_BLG NR_SRC_STORIES_BLG
	WHERE 	NR_NEWS_DISCOVERY.NEWS_STORY_ID =  NR_SRC_STORIES_BLG.STORY_ID
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.DISCOVERY TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.DISCOVERY' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_DISCOVERY_VIEW' -- Schema name and table name

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

DROP VIEW HOMEPAGE.DISCOVERY;

COMMIT;

-- 3 COMMUNITIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.DISCOVERY AS (
	SELECT 	NR_NEWS_DISCOVERY.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			'00000000-0000-0000-0000-000000000001'			READER_ID,
			17										CATEGORY_TYPE,
			NR_NEWS_DISCOVERY.SOURCE						SOURCE,
			NR_NEWS_DISCOVERY.CONTAINER_ID				CONTAINER_ID,
			NR_NEWS_DISCOVERY.ITEM_ID					ITEM_ID,
			NULL										ROLLUP_ENTRY_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_DISCOVERY.CREATION_DATE				CREATION_DATE,
			NR_NEWS_DISCOVERY.NEWS_STORY_ID				STORY_ID,
			NR_NEWS_DISCOVERY.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_DISCOVERY.CREATION_DATE				EVENT_TIME,
			NR_NEWS_DISCOVERY.IS_COMMUNITY_STORY			IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID					
	FROM 	HOMEPAGE.NR_NEWS_DISCOVERY NR_NEWS_DISCOVERY,  
			HOMEPAGE.NR_SRC_STORIES_COM NR_SRC_STORIES_COM
	WHERE 	NR_NEWS_DISCOVERY.NEWS_STORY_ID =  NR_SRC_STORIES_COM.STORY_ID
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.DISCOVERY TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.DISCOVERY' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_DISCOVERY_VIEW' -- Schema name and table name

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

DROP VIEW HOMEPAGE.DISCOVERY;

COMMIT;

-- 4 WIKIS
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.DISCOVERY AS (
	SELECT 	NR_NEWS_DISCOVERY.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			'00000000-0000-0000-0000-000000000001'			READER_ID,
			17										CATEGORY_TYPE,
			NR_NEWS_DISCOVERY.SOURCE						SOURCE,
			NR_NEWS_DISCOVERY.CONTAINER_ID				CONTAINER_ID,
			NR_NEWS_DISCOVERY.ITEM_ID					ITEM_ID,
			NULL										ROLLUP_ENTRY_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_DISCOVERY.CREATION_DATE				CREATION_DATE,
			NR_NEWS_DISCOVERY.NEWS_STORY_ID				STORY_ID,
			NR_NEWS_DISCOVERY.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_DISCOVERY.CREATION_DATE				EVENT_TIME,
			NR_NEWS_DISCOVERY.IS_COMMUNITY_STORY			IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID					
	FROM 	HOMEPAGE.NR_NEWS_DISCOVERY NR_NEWS_DISCOVERY,  
			HOMEPAGE.NR_SRC_STORIES_WIK NR_SRC_STORIES_WIK
	WHERE 	NR_NEWS_DISCOVERY.NEWS_STORY_ID =  NR_SRC_STORIES_WIK.STORY_ID
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.DISCOVERY TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.DISCOVERY' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_DISCOVERY_VIEW' -- Schema name and table name

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

DROP VIEW HOMEPAGE.DISCOVERY;

COMMIT;

-- 5 PROFILES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.DISCOVERY AS (
	SELECT 	NR_NEWS_DISCOVERY.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			'00000000-0000-0000-0000-000000000001'			READER_ID,
			17										CATEGORY_TYPE,
			NR_NEWS_DISCOVERY.SOURCE						SOURCE,
			NR_NEWS_DISCOVERY.CONTAINER_ID				CONTAINER_ID,
			NR_NEWS_DISCOVERY.ITEM_ID					ITEM_ID,
			NULL										ROLLUP_ENTRY_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_DISCOVERY.CREATION_DATE				CREATION_DATE,
			NR_NEWS_DISCOVERY.NEWS_STORY_ID				STORY_ID,
			NR_NEWS_DISCOVERY.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_DISCOVERY.CREATION_DATE				EVENT_TIME,
			NR_NEWS_DISCOVERY.IS_COMMUNITY_STORY			IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID					
	FROM 	HOMEPAGE.NR_NEWS_DISCOVERY NR_NEWS_DISCOVERY,  
			HOMEPAGE.NR_SRC_STORIES_PRF NR_SRC_STORIES_PRF
	WHERE 	NR_NEWS_DISCOVERY.NEWS_STORY_ID =  NR_SRC_STORIES_PRF.STORY_ID
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.DISCOVERY TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.DISCOVERY' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_DISCOVERY_VIEW' -- Schema name and table name

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

DROP VIEW HOMEPAGE.DISCOVERY;

COMMIT;

-- 6 HOMEPAGE
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.DISCOVERY AS (
	SELECT 	NR_NEWS_DISCOVERY.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			'00000000-0000-0000-0000-000000000001'			READER_ID,
			17										CATEGORY_TYPE,
			NR_NEWS_DISCOVERY.SOURCE						SOURCE,
			NR_NEWS_DISCOVERY.CONTAINER_ID				CONTAINER_ID,
			NR_NEWS_DISCOVERY.ITEM_ID					ITEM_ID,
			NULL										ROLLUP_ENTRY_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_DISCOVERY.CREATION_DATE				CREATION_DATE,
			NR_NEWS_DISCOVERY.NEWS_STORY_ID				STORY_ID,
			NR_NEWS_DISCOVERY.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_DISCOVERY.CREATION_DATE				EVENT_TIME,
			NR_NEWS_DISCOVERY.IS_COMMUNITY_STORY			IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID					
	FROM 	HOMEPAGE.NR_NEWS_DISCOVERY NR_NEWS_DISCOVERY,  
			HOMEPAGE.NR_SRC_STORIES_HP NR_SRC_STORIES_HP
	WHERE 	NR_NEWS_DISCOVERY.NEWS_STORY_ID =  NR_SRC_STORIES_HP.STORY_ID
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.DISCOVERY TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.DISCOVERY' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_DISCOVERY_VIEW' -- Schema name and table name

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

DROP VIEW HOMEPAGE.DISCOVERY;

COMMIT;

-- 7 DOGEAR
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.DISCOVERY AS (
	SELECT 	NR_NEWS_DISCOVERY.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			'00000000-0000-0000-0000-000000000001'			READER_ID,
			17										CATEGORY_TYPE,
			NR_NEWS_DISCOVERY.SOURCE						SOURCE,
			NR_NEWS_DISCOVERY.CONTAINER_ID				CONTAINER_ID,
			NR_NEWS_DISCOVERY.ITEM_ID					ITEM_ID,
			NULL										ROLLUP_ENTRY_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_DISCOVERY.CREATION_DATE				CREATION_DATE,
			NR_NEWS_DISCOVERY.NEWS_STORY_ID				STORY_ID,
			NR_NEWS_DISCOVERY.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_DISCOVERY.CREATION_DATE				EVENT_TIME,
			NR_NEWS_DISCOVERY.IS_COMMUNITY_STORY			IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID					
	FROM 	HOMEPAGE.NR_NEWS_DISCOVERY NR_NEWS_DISCOVERY,  
			HOMEPAGE.NR_SRC_STORIES_DGR NR_SRC_STORIES_DGR
	WHERE 	NR_NEWS_DISCOVERY.NEWS_STORY_ID =  NR_SRC_STORIES_DGR.STORY_ID
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.DISCOVERY TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.DISCOVERY' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_DISCOVERY_VIEW' -- Schema name and table name

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

DROP VIEW HOMEPAGE.DISCOVERY;

COMMIT;

-- 8  FILES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.DISCOVERY AS (
	SELECT 	NR_NEWS_DISCOVERY.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			'00000000-0000-0000-0000-000000000001'			READER_ID,
			17										CATEGORY_TYPE,
			NR_NEWS_DISCOVERY.SOURCE						SOURCE,
			NR_NEWS_DISCOVERY.CONTAINER_ID				CONTAINER_ID,
			NR_NEWS_DISCOVERY.ITEM_ID					ITEM_ID,
			NULL										ROLLUP_ENTRY_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_DISCOVERY.CREATION_DATE				CREATION_DATE,
			NR_NEWS_DISCOVERY.NEWS_STORY_ID				STORY_ID,
			NR_NEWS_DISCOVERY.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_DISCOVERY.CREATION_DATE				EVENT_TIME,
			NR_NEWS_DISCOVERY.IS_COMMUNITY_STORY			IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID					
	FROM 	HOMEPAGE.NR_NEWS_DISCOVERY NR_NEWS_DISCOVERY,  
			HOMEPAGE.NR_SRC_STORIES_FILE NR_SRC_STORIES_FILE
	WHERE 	NR_NEWS_DISCOVERY.NEWS_STORY_ID =  NR_SRC_STORIES_FILE.STORY_ID
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.DISCOVERY TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.DISCOVERY' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_DISCOVERY_VIEW' -- Schema name and table name

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

DROP VIEW HOMEPAGE.DISCOVERY;

COMMIT;

-- 9  FORUMS
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.DISCOVERY AS (
	SELECT 	NR_NEWS_DISCOVERY.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			'00000000-0000-0000-0000-000000000001'			READER_ID,
			17										CATEGORY_TYPE,
			NR_NEWS_DISCOVERY.SOURCE						SOURCE,
			NR_NEWS_DISCOVERY.CONTAINER_ID				CONTAINER_ID,
			NR_NEWS_DISCOVERY.ITEM_ID					ITEM_ID,
			NULL										ROLLUP_ENTRY_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_DISCOVERY.CREATION_DATE				CREATION_DATE,
			NR_NEWS_DISCOVERY.NEWS_STORY_ID				STORY_ID,
			NR_NEWS_DISCOVERY.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_DISCOVERY.CREATION_DATE				EVENT_TIME,
			NR_NEWS_DISCOVERY.IS_COMMUNITY_STORY			IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID					
	FROM 	HOMEPAGE.NR_NEWS_DISCOVERY NR_NEWS_DISCOVERY,  
			HOMEPAGE.NR_SRC_STORIES_FRM NR_SRC_STORIES_FRM
	WHERE 	NR_NEWS_DISCOVERY.NEWS_STORY_ID =  NR_SRC_STORIES_FRM.STORY_ID
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.DISCOVERY TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.DISCOVERY' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_DISCOVERY_VIEW' -- Schema name and table name

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

DROP VIEW HOMEPAGE.DISCOVERY;

COMMIT;

------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
--	[START] SAVED MIGRATION
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------

-- A) INSERT ENTRIES
-- B) INSERT STORIES
-- C) INSERT ACTIONABLE

-- 1) ACT ENTRIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.TMP_ENTRIES AS (
	SELECT 
		ENTRY_ID, SOURCE, SOURCE_TYPE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_ID,     
		ITEM_NAME,  ITEM_URL, CREATION_DATE, UPDATE_DATE, TAGS, N_COMMENTS, N_RECOMMANDATIONS, N_ATTACHMENTS, BRIEF_DESC,  
		LAST_RECOMMENDER_ID, LAST_DATE_RECOMMENDER_ID, PREV_RECOMMENDER_ID, PREV_DATE_RECOMMENDER_ID,  LAST_COMMENT_ID, 
		LAST_DATE_COMMENT, LAST_DESC_COMMENT,  LAST_AUTHOR_COMMENT, PREV_COMMENT_ID,  PREV_DATE_COMMENT,  PREV_DESC_COMMENT,  
		PREV_AUTHOR_COMMENT, TARGET_SUBJECT_ID,  ITEM_ATOM_URL,  MESSAGE,   PREVIEW_IMAGE_URL,  JSON_META_DATA, EVENT_TIME, SCOPE
	FROM (
		SELECT 	NR_NEWS_SAVED.ITEM_ID ENTRY_ID,
				NR_NEWS_SAVED.SOURCE,
				NR_NEWS_SAVED.SOURCE_TYPE,
				NR_NEWS_SAVED.CONTAINER_ID,
				NR_NEWS_SAVED.CONTAINER_NAME,
				NR_NEWS_SAVED.CONTAINER_URL,
				NR_NEWS_SAVED.ITEM_ID,
				NR_NEWS_SAVED.ENTRY_NAME	ITEM_NAME,
				NR_NEWS_SAVED.ENTRY_URL ITEM_URL,			
				NR_NEWS_SAVED.CREATION_DATE,
				NR_NEWS_SAVED.CREATION_DATE UPDATE_DATE,
				NR_NEWS_SAVED.TAGS,
				NR_NEWS_SAVED.N_COMMENTS,
				NR_NEWS_SAVED.N_RECOMMANDATIONS,
				0 N_ATTACHMENTS, -- N_ATTACHMENTS
				NR_NEWS_SAVED.BRIEF_DESC,
				NULL	LAST_RECOMMENDER_ID,
				NULL	LAST_DATE_RECOMMENDER_ID,
				NULL  PREV_RECOMMENDER_ID,
				NULL PREV_DATE_RECOMMENDER_ID,
				NULL  LAST_COMMENT_ID,
				NULL LAST_DATE_COMMENT,
				NULL  LAST_DESC_COMMENT,
				NULL  LAST_AUTHOR_COMMENT,
				NULL  PREV_COMMENT_ID,
				NULL PREV_DATE_COMMENT,
				NULL  PREV_DESC_COMMENT,
				NULL  PREV_AUTHOR_COMMENT,
				NULL  TARGET_SUBJECT_ID,
				NR_NEWS_SAVED.ENTRY_ATOM_URL	ITEM_ATOM_URL,
				NULL  MESSAGE,
				NULL  PREVIEW_IMAGE_URL,
				NULL  JSON_META_DATA,
				NULL EVENT_TIME,
				0 SCOPE
		FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT 	ITEM_ID, NEWS_STORY_ID STORY_ID, MAX(CREATION_DATE) CREATION_DATE
				FROM 	HOMEPAGE.NR_NEWS_SAVED
				WHERE 	ITEM_ID IS NOT NULL AND SOURCE_TYPE = 1
				GROUP	BY ITEM_ID, NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.NEWS_STORY_ID = TEMP.STORY_ID AND NR_NEWS_SAVED.CREATION_DATE = TEMP.CREATION_DATE
		) TEMP_STORIES 
	WHERE TEMP_STORIES.ENTRY_ID NOT IN (	
									SELECT 	ENTRY_ID
									FROM 	HOMEPAGE.NR_ENTRIES_ACT
								)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_ENTRIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_ENTRIES' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ENTRIES_ACT' -- Schema name and table name

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

DROP VIEW HOMEPAGE.TMP_ENTRIES;

COMMIT;

-- 2) BLG ENTRIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.TMP_ENTRIES AS (
	SELECT 
		ENTRY_ID, SOURCE, SOURCE_TYPE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_ID,     
		ITEM_NAME,  ITEM_URL, CREATION_DATE, UPDATE_DATE, TAGS, N_COMMENTS, N_RECOMMANDATIONS, N_ATTACHMENTS, BRIEF_DESC,  
		LAST_RECOMMENDER_ID, LAST_DATE_RECOMMENDER_ID, PREV_RECOMMENDER_ID, PREV_DATE_RECOMMENDER_ID,  LAST_COMMENT_ID, 
		LAST_DATE_COMMENT, LAST_DESC_COMMENT,  LAST_AUTHOR_COMMENT, PREV_COMMENT_ID,  PREV_DATE_COMMENT,  PREV_DESC_COMMENT,  
		PREV_AUTHOR_COMMENT, TARGET_SUBJECT_ID,  ITEM_ATOM_URL,  MESSAGE,   PREVIEW_IMAGE_URL,  JSON_META_DATA, EVENT_TIME, SCOPE
	FROM (
		SELECT 	NR_NEWS_SAVED.ITEM_ID ENTRY_ID,
				NR_NEWS_SAVED.SOURCE,
				NR_NEWS_SAVED.SOURCE_TYPE,
				NR_NEWS_SAVED.CONTAINER_ID,
				NR_NEWS_SAVED.CONTAINER_NAME,
				NR_NEWS_SAVED.CONTAINER_URL,
				NR_NEWS_SAVED.ITEM_ID,
				NR_NEWS_SAVED.ENTRY_NAME	ITEM_NAME,
				NR_NEWS_SAVED.ENTRY_URL ITEM_URL,			
				NR_NEWS_SAVED.CREATION_DATE,
				NR_NEWS_SAVED.CREATION_DATE UPDATE_DATE,
				NR_NEWS_SAVED.TAGS,
				NR_NEWS_SAVED.N_COMMENTS,
				NR_NEWS_SAVED.N_RECOMMANDATIONS,
				0 N_ATTACHMENTS, -- N_ATTACHMENTS
				NR_NEWS_SAVED.BRIEF_DESC,
				NULL	LAST_RECOMMENDER_ID,
				NULL	LAST_DATE_RECOMMENDER_ID,
				NULL  PREV_RECOMMENDER_ID,
				NULL PREV_DATE_RECOMMENDER_ID,
				NULL  LAST_COMMENT_ID,
				NULL LAST_DATE_COMMENT,
				NULL  LAST_DESC_COMMENT,
				NULL  LAST_AUTHOR_COMMENT,
				NULL  PREV_COMMENT_ID,
				NULL PREV_DATE_COMMENT,
				NULL  PREV_DESC_COMMENT,
				NULL  PREV_AUTHOR_COMMENT,
				NULL  TARGET_SUBJECT_ID,
				NR_NEWS_SAVED.ENTRY_ATOM_URL	ITEM_ATOM_URL,
				NULL  MESSAGE,
				NULL  PREVIEW_IMAGE_URL,
				NULL  JSON_META_DATA,
				NULL EVENT_TIME,
				0 SCOPE
		FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT 	ITEM_ID, NEWS_STORY_ID STORY_ID, MAX(CREATION_DATE) CREATION_DATE
				FROM 	HOMEPAGE.NR_NEWS_SAVED
				WHERE 	ITEM_ID IS NOT NULL AND SOURCE_TYPE = 2
				GROUP	BY ITEM_ID, NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.NEWS_STORY_ID = TEMP.STORY_ID AND NR_NEWS_SAVED.CREATION_DATE = TEMP.CREATION_DATE
		) TEMP_STORIES 
	WHERE TEMP_STORIES.ENTRY_ID NOT IN (	
									SELECT 	ENTRY_ID
									FROM 	HOMEPAGE.NR_ENTRIES_BLG
								)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_ENTRIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_ENTRIES' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ENTRIES_BLG' -- Schema name and table name

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

DROP VIEW HOMEPAGE.TMP_ENTRIES;

COMMIT;

-- 3) COM ENTRIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.TMP_ENTRIES AS (
	SELECT 
		ENTRY_ID, SOURCE, SOURCE_TYPE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_ID,     
		ITEM_NAME,  ITEM_URL, CREATION_DATE, UPDATE_DATE, TAGS, N_COMMENTS, N_RECOMMANDATIONS, N_ATTACHMENTS, BRIEF_DESC,  
		LAST_RECOMMENDER_ID, LAST_DATE_RECOMMENDER_ID, PREV_RECOMMENDER_ID, PREV_DATE_RECOMMENDER_ID,  LAST_COMMENT_ID, 
		LAST_DATE_COMMENT, LAST_DESC_COMMENT,  LAST_AUTHOR_COMMENT, PREV_COMMENT_ID,  PREV_DATE_COMMENT,  PREV_DESC_COMMENT,  
		PREV_AUTHOR_COMMENT, TARGET_SUBJECT_ID,  ITEM_ATOM_URL,  MESSAGE,   PREVIEW_IMAGE_URL,  JSON_META_DATA, EVENT_TIME, SCOPE
	FROM (
		SELECT 	NR_NEWS_SAVED.ITEM_ID ENTRY_ID,
				NR_NEWS_SAVED.SOURCE,
				NR_NEWS_SAVED.SOURCE_TYPE,
				NR_NEWS_SAVED.CONTAINER_ID,
				NR_NEWS_SAVED.CONTAINER_NAME,
				NR_NEWS_SAVED.CONTAINER_URL,
				NR_NEWS_SAVED.ITEM_ID,
				NR_NEWS_SAVED.ENTRY_NAME	ITEM_NAME,
				NR_NEWS_SAVED.ENTRY_URL ITEM_URL,			
				NR_NEWS_SAVED.CREATION_DATE,
				NR_NEWS_SAVED.CREATION_DATE UPDATE_DATE,
				NR_NEWS_SAVED.TAGS,
				NR_NEWS_SAVED.N_COMMENTS,
				NR_NEWS_SAVED.N_RECOMMANDATIONS,
				0 N_ATTACHMENTS, -- N_ATTACHMENTS
				NR_NEWS_SAVED.BRIEF_DESC,
				NULL	LAST_RECOMMENDER_ID,
				NULL	LAST_DATE_RECOMMENDER_ID,
				NULL  PREV_RECOMMENDER_ID,
				NULL PREV_DATE_RECOMMENDER_ID,
				NULL  LAST_COMMENT_ID,
				NULL LAST_DATE_COMMENT,
				NULL  LAST_DESC_COMMENT,
				NULL  LAST_AUTHOR_COMMENT,
				NULL  PREV_COMMENT_ID,
				NULL PREV_DATE_COMMENT,
				NULL  PREV_DESC_COMMENT,
				NULL  PREV_AUTHOR_COMMENT,
				NULL  TARGET_SUBJECT_ID,
				NR_NEWS_SAVED.ENTRY_ATOM_URL	ITEM_ATOM_URL,
				NULL  MESSAGE,
				NULL  PREVIEW_IMAGE_URL,
				NULL  JSON_META_DATA,
				NULL EVENT_TIME,
				0 SCOPE
		FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT 	ITEM_ID, NEWS_STORY_ID STORY_ID, MAX(CREATION_DATE) CREATION_DATE
				FROM 	HOMEPAGE.NR_NEWS_SAVED
				WHERE 	ITEM_ID IS NOT NULL AND SOURCE_TYPE = 3
				GROUP	BY ITEM_ID, NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.NEWS_STORY_ID = TEMP.STORY_ID AND NR_NEWS_SAVED.CREATION_DATE = TEMP.CREATION_DATE
		) TEMP_STORIES  
	WHERE TEMP_STORIES.ENTRY_ID NOT IN (	
									SELECT 	ENTRY_ID
									FROM 	HOMEPAGE.NR_ENTRIES_COM
								)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_ENTRIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_ENTRIES' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ENTRIES_COM' -- Schema name and table name

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

DROP VIEW HOMEPAGE.TMP_ENTRIES;

COMMIT;

-- 4) WIK ENTRIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.TMP_ENTRIES AS (
	SELECT 
		ENTRY_ID, SOURCE, SOURCE_TYPE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_ID,     
		ITEM_NAME,  ITEM_URL, CREATION_DATE, UPDATE_DATE, TAGS, N_COMMENTS, N_RECOMMANDATIONS, N_ATTACHMENTS, BRIEF_DESC,  
		LAST_RECOMMENDER_ID, LAST_DATE_RECOMMENDER_ID, PREV_RECOMMENDER_ID, PREV_DATE_RECOMMENDER_ID,  LAST_COMMENT_ID, 
		LAST_DATE_COMMENT, LAST_DESC_COMMENT,  LAST_AUTHOR_COMMENT, PREV_COMMENT_ID,  PREV_DATE_COMMENT,  PREV_DESC_COMMENT,  
		PREV_AUTHOR_COMMENT, TARGET_SUBJECT_ID,  ITEM_ATOM_URL,  MESSAGE,   PREVIEW_IMAGE_URL,  JSON_META_DATA, EVENT_TIME, SCOPE
	FROM (
		SELECT 	NR_NEWS_SAVED.ITEM_ID ENTRY_ID,
				NR_NEWS_SAVED.SOURCE,
				NR_NEWS_SAVED.SOURCE_TYPE,
				NR_NEWS_SAVED.CONTAINER_ID,
				NR_NEWS_SAVED.CONTAINER_NAME,
				NR_NEWS_SAVED.CONTAINER_URL,
				NR_NEWS_SAVED.ITEM_ID,
				NR_NEWS_SAVED.ENTRY_NAME	ITEM_NAME,
				NR_NEWS_SAVED.ENTRY_URL ITEM_URL,			
				NR_NEWS_SAVED.CREATION_DATE,
				NR_NEWS_SAVED.CREATION_DATE UPDATE_DATE,
				NR_NEWS_SAVED.TAGS,
				NR_NEWS_SAVED.N_COMMENTS,
				NR_NEWS_SAVED.N_RECOMMANDATIONS,
				0 N_ATTACHMENTS, -- N_ATTACHMENTS
				NR_NEWS_SAVED.BRIEF_DESC,
				NULL	LAST_RECOMMENDER_ID,
				NULL	LAST_DATE_RECOMMENDER_ID,
				NULL  PREV_RECOMMENDER_ID,
				NULL PREV_DATE_RECOMMENDER_ID,
				NULL  LAST_COMMENT_ID,
				NULL LAST_DATE_COMMENT,
				NULL  LAST_DESC_COMMENT,
				NULL  LAST_AUTHOR_COMMENT,
				NULL  PREV_COMMENT_ID,
				NULL PREV_DATE_COMMENT,
				NULL  PREV_DESC_COMMENT,
				NULL  PREV_AUTHOR_COMMENT,
				NULL  TARGET_SUBJECT_ID,
				NR_NEWS_SAVED.ENTRY_ATOM_URL	ITEM_ATOM_URL,
				NULL  MESSAGE,
				NULL  PREVIEW_IMAGE_URL,
				NULL  JSON_META_DATA,
				NULL EVENT_TIME,
				0 SCOPE
		FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT 	ITEM_ID, NEWS_STORY_ID STORY_ID, MAX(CREATION_DATE) CREATION_DATE
				FROM 	HOMEPAGE.NR_NEWS_SAVED
				WHERE 	ITEM_ID IS NOT NULL AND SOURCE_TYPE = 4
				GROUP	BY ITEM_ID, NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.NEWS_STORY_ID = TEMP.STORY_ID AND NR_NEWS_SAVED.CREATION_DATE = TEMP.CREATION_DATE
		) TEMP_STORIES  
	WHERE TEMP_STORIES.ENTRY_ID NOT IN (	
									SELECT 	ENTRY_ID
									FROM 	HOMEPAGE.NR_ENTRIES_WIK
								)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_ENTRIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_ENTRIES' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ENTRIES_WIK' -- Schema name and table name

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

DROP VIEW HOMEPAGE.TMP_ENTRIES;

COMMIT;

-- 5) PRF ENTRIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.TMP_ENTRIES AS (
	SELECT 
		ENTRY_ID, SOURCE, SOURCE_TYPE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_ID,     
		ITEM_NAME,  ITEM_URL, CREATION_DATE, UPDATE_DATE, TAGS, N_COMMENTS, N_RECOMMANDATIONS, N_ATTACHMENTS, BRIEF_DESC,  
		LAST_RECOMMENDER_ID, LAST_DATE_RECOMMENDER_ID, PREV_RECOMMENDER_ID, PREV_DATE_RECOMMENDER_ID,  LAST_COMMENT_ID, 
		LAST_DATE_COMMENT, LAST_DESC_COMMENT,  LAST_AUTHOR_COMMENT, PREV_COMMENT_ID,  PREV_DATE_COMMENT,  PREV_DESC_COMMENT,  
		PREV_AUTHOR_COMMENT, TARGET_SUBJECT_ID,  ITEM_ATOM_URL,  MESSAGE,   PREVIEW_IMAGE_URL,  JSON_META_DATA, EVENT_TIME, SCOPE
	FROM (
		SELECT 	NR_NEWS_SAVED.ITEM_ID ENTRY_ID,
				NR_NEWS_SAVED.SOURCE,
				NR_NEWS_SAVED.SOURCE_TYPE,
				NR_NEWS_SAVED.CONTAINER_ID,
				NR_NEWS_SAVED.CONTAINER_NAME,
				NR_NEWS_SAVED.CONTAINER_URL,
				NR_NEWS_SAVED.ITEM_ID,
				NR_NEWS_SAVED.ENTRY_NAME	ITEM_NAME,
				NR_NEWS_SAVED.ENTRY_URL ITEM_URL,			
				NR_NEWS_SAVED.CREATION_DATE,
				NR_NEWS_SAVED.CREATION_DATE UPDATE_DATE,
				NR_NEWS_SAVED.TAGS,
				NR_NEWS_SAVED.N_COMMENTS,
				NR_NEWS_SAVED.N_RECOMMANDATIONS,
				0 N_ATTACHMENTS, -- N_ATTACHMENTS
				NR_NEWS_SAVED.BRIEF_DESC,
				NULL	LAST_RECOMMENDER_ID,
				NULL	LAST_DATE_RECOMMENDER_ID,
				NULL  PREV_RECOMMENDER_ID,
				NULL PREV_DATE_RECOMMENDER_ID,
				NULL  LAST_COMMENT_ID,
				NULL LAST_DATE_COMMENT,
				NULL  LAST_DESC_COMMENT,
				NULL  LAST_AUTHOR_COMMENT,
				NULL  PREV_COMMENT_ID,
				NULL PREV_DATE_COMMENT,
				NULL  PREV_DESC_COMMENT,
				NULL  PREV_AUTHOR_COMMENT,
				NULL  TARGET_SUBJECT_ID,
				NR_NEWS_SAVED.ENTRY_ATOM_URL	ITEM_ATOM_URL,
				NULL  MESSAGE,
				NULL  PREVIEW_IMAGE_URL,
				NULL  JSON_META_DATA,
				NULL EVENT_TIME,
				0 SCOPE
		FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT 	ITEM_ID, NEWS_STORY_ID STORY_ID, MAX(CREATION_DATE) CREATION_DATE
				FROM 	HOMEPAGE.NR_NEWS_SAVED
				WHERE 	ITEM_ID IS NOT NULL AND SOURCE_TYPE = 5
				GROUP	BY ITEM_ID, NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.NEWS_STORY_ID = TEMP.STORY_ID AND NR_NEWS_SAVED.CREATION_DATE = TEMP.CREATION_DATE
		) TEMP_STORIES  
	WHERE TEMP_STORIES.ENTRY_ID NOT IN (	
									SELECT 	ENTRY_ID
									FROM 	HOMEPAGE.NR_ENTRIES_PRF
								)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_ENTRIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_ENTRIES' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ENTRIES_PRF' -- Schema name and table name

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

DROP VIEW HOMEPAGE.TMP_ENTRIES;

COMMIT;

-- 6) HP ENTRIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.TMP_ENTRIES AS (
	SELECT 
		ENTRY_ID, SOURCE, SOURCE_TYPE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_ID,     
		ITEM_NAME,  ITEM_URL, CREATION_DATE, UPDATE_DATE, TAGS, N_COMMENTS, N_RECOMMANDATIONS, N_ATTACHMENTS, BRIEF_DESC,  
		LAST_RECOMMENDER_ID, LAST_DATE_RECOMMENDER_ID, PREV_RECOMMENDER_ID, PREV_DATE_RECOMMENDER_ID,  LAST_COMMENT_ID, 
		LAST_DATE_COMMENT, LAST_DESC_COMMENT,  LAST_AUTHOR_COMMENT, PREV_COMMENT_ID,  PREV_DATE_COMMENT,  PREV_DESC_COMMENT,  
		PREV_AUTHOR_COMMENT, TARGET_SUBJECT_ID,  ITEM_ATOM_URL,  MESSAGE,   PREVIEW_IMAGE_URL,  JSON_META_DATA, EVENT_TIME, SCOPE
	FROM (
		SELECT 	NR_NEWS_SAVED.ITEM_ID ENTRY_ID,
				NR_NEWS_SAVED.SOURCE,
				NR_NEWS_SAVED.SOURCE_TYPE,
				NR_NEWS_SAVED.CONTAINER_ID,
				NR_NEWS_SAVED.CONTAINER_NAME,
				NR_NEWS_SAVED.CONTAINER_URL,
				NR_NEWS_SAVED.ITEM_ID,
				NR_NEWS_SAVED.ENTRY_NAME	ITEM_NAME,
				NR_NEWS_SAVED.ENTRY_URL ITEM_URL,			
				NR_NEWS_SAVED.CREATION_DATE,
				NR_NEWS_SAVED.CREATION_DATE UPDATE_DATE,
				NR_NEWS_SAVED.TAGS,
				NR_NEWS_SAVED.N_COMMENTS,
				NR_NEWS_SAVED.N_RECOMMANDATIONS,
				0 N_ATTACHMENTS, -- N_ATTACHMENTS
				NR_NEWS_SAVED.BRIEF_DESC,
				NULL	LAST_RECOMMENDER_ID,
				NULL	LAST_DATE_RECOMMENDER_ID,
				NULL  PREV_RECOMMENDER_ID,
				NULL PREV_DATE_RECOMMENDER_ID,
				NULL  LAST_COMMENT_ID,
				NULL LAST_DATE_COMMENT,
				NULL  LAST_DESC_COMMENT,
				NULL  LAST_AUTHOR_COMMENT,
				NULL  PREV_COMMENT_ID,
				NULL PREV_DATE_COMMENT,
				NULL  PREV_DESC_COMMENT,
				NULL  PREV_AUTHOR_COMMENT,
				NULL  TARGET_SUBJECT_ID,
				NR_NEWS_SAVED.ENTRY_ATOM_URL	ITEM_ATOM_URL,
				NULL  MESSAGE,
				NULL  PREVIEW_IMAGE_URL,
				NULL  JSON_META_DATA,
				NULL EVENT_TIME,
				0 SCOPE
		FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT 	ITEM_ID, NEWS_STORY_ID STORY_ID, MAX(CREATION_DATE) CREATION_DATE
				FROM 	HOMEPAGE.NR_NEWS_SAVED
				WHERE 	ITEM_ID IS NOT NULL AND SOURCE_TYPE = 6
				GROUP	BY ITEM_ID, NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.NEWS_STORY_ID = TEMP.STORY_ID AND NR_NEWS_SAVED.CREATION_DATE = TEMP.CREATION_DATE
		) TEMP_STORIES  
	WHERE TEMP_STORIES.ENTRY_ID NOT IN (	
									SELECT 	ENTRY_ID
									FROM 	HOMEPAGE.NR_ENTRIES_HP
								)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_ENTRIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_ENTRIES' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ENTRIES_HP' -- Schema name and table name

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


DROP VIEW HOMEPAGE.TMP_ENTRIES;

COMMIT;

-- 7) DGR ENTRIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.TMP_ENTRIES AS (
	SELECT 
		ENTRY_ID, SOURCE, SOURCE_TYPE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_ID,     
		ITEM_NAME,  ITEM_URL, CREATION_DATE, UPDATE_DATE, TAGS, N_COMMENTS, N_RECOMMANDATIONS, N_ATTACHMENTS, BRIEF_DESC,  
		LAST_RECOMMENDER_ID, LAST_DATE_RECOMMENDER_ID, PREV_RECOMMENDER_ID, PREV_DATE_RECOMMENDER_ID,  LAST_COMMENT_ID, 
		LAST_DATE_COMMENT, LAST_DESC_COMMENT,  LAST_AUTHOR_COMMENT, PREV_COMMENT_ID,  PREV_DATE_COMMENT,  PREV_DESC_COMMENT,  
		PREV_AUTHOR_COMMENT, TARGET_SUBJECT_ID,  ITEM_ATOM_URL,  MESSAGE,   PREVIEW_IMAGE_URL,  JSON_META_DATA, EVENT_TIME, SCOPE
	FROM (
		SELECT 	NR_NEWS_SAVED.ITEM_ID ENTRY_ID,
				NR_NEWS_SAVED.SOURCE,
				NR_NEWS_SAVED.SOURCE_TYPE,
				NR_NEWS_SAVED.CONTAINER_ID,
				NR_NEWS_SAVED.CONTAINER_NAME,
				NR_NEWS_SAVED.CONTAINER_URL,
				NR_NEWS_SAVED.ITEM_ID,
				NR_NEWS_SAVED.ENTRY_NAME	ITEM_NAME,
				NR_NEWS_SAVED.ENTRY_URL ITEM_URL,			
				NR_NEWS_SAVED.CREATION_DATE,
				NR_NEWS_SAVED.CREATION_DATE UPDATE_DATE,
				NR_NEWS_SAVED.TAGS,
				NR_NEWS_SAVED.N_COMMENTS,
				NR_NEWS_SAVED.N_RECOMMANDATIONS,
				0 N_ATTACHMENTS, -- N_ATTACHMENTS
				NR_NEWS_SAVED.BRIEF_DESC,
				NULL	LAST_RECOMMENDER_ID,
				NULL	LAST_DATE_RECOMMENDER_ID,
				NULL  PREV_RECOMMENDER_ID,
				NULL PREV_DATE_RECOMMENDER_ID,
				NULL  LAST_COMMENT_ID,
				NULL LAST_DATE_COMMENT,
				NULL  LAST_DESC_COMMENT,
				NULL  LAST_AUTHOR_COMMENT,
				NULL  PREV_COMMENT_ID,
				NULL PREV_DATE_COMMENT,
				NULL  PREV_DESC_COMMENT,
				NULL  PREV_AUTHOR_COMMENT,
				NULL  TARGET_SUBJECT_ID,
				NR_NEWS_SAVED.ENTRY_ATOM_URL	ITEM_ATOM_URL,
				NULL  MESSAGE,
				NULL  PREVIEW_IMAGE_URL,
				NULL  JSON_META_DATA,
				NULL EVENT_TIME,
				0 SCOPE
		FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT 	ITEM_ID, NEWS_STORY_ID STORY_ID, MAX(CREATION_DATE) CREATION_DATE
				FROM 	HOMEPAGE.NR_NEWS_SAVED
				WHERE 	ITEM_ID IS NOT NULL AND SOURCE_TYPE = 7
				GROUP	BY ITEM_ID, NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.NEWS_STORY_ID = TEMP.STORY_ID AND NR_NEWS_SAVED.CREATION_DATE = TEMP.CREATION_DATE
		) TEMP_STORIES  
	WHERE TEMP_STORIES.ENTRY_ID NOT IN (	
									SELECT 	ENTRY_ID
									FROM 	HOMEPAGE.NR_ENTRIES_DGR
								)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_ENTRIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_ENTRIES' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ENTRIES_DGR' -- Schema name and table name

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

DROP VIEW HOMEPAGE.TMP_ENTRIES;

COMMIT;

-- 8) FILES ENTRIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.TMP_ENTRIES AS (
	SELECT 
		ENTRY_ID, SOURCE, SOURCE_TYPE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_ID,     
		ITEM_NAME,  ITEM_URL, CREATION_DATE, UPDATE_DATE, TAGS, N_COMMENTS, N_RECOMMANDATIONS, N_ATTACHMENTS, BRIEF_DESC,  
		LAST_RECOMMENDER_ID, LAST_DATE_RECOMMENDER_ID, PREV_RECOMMENDER_ID, PREV_DATE_RECOMMENDER_ID,  LAST_COMMENT_ID, 
		LAST_DATE_COMMENT, LAST_DESC_COMMENT,  LAST_AUTHOR_COMMENT, PREV_COMMENT_ID,  PREV_DATE_COMMENT,  PREV_DESC_COMMENT,  
		PREV_AUTHOR_COMMENT, TARGET_SUBJECT_ID,  ITEM_ATOM_URL,  MESSAGE,   PREVIEW_IMAGE_URL,  JSON_META_DATA, EVENT_TIME, SCOPE
	FROM (
		SELECT 	NR_NEWS_SAVED.ITEM_ID ENTRY_ID,
				NR_NEWS_SAVED.SOURCE,
				NR_NEWS_SAVED.SOURCE_TYPE,
				NR_NEWS_SAVED.CONTAINER_ID,
				NR_NEWS_SAVED.CONTAINER_NAME,
				NR_NEWS_SAVED.CONTAINER_URL,
				NR_NEWS_SAVED.ITEM_ID,
				NR_NEWS_SAVED.ENTRY_NAME	ITEM_NAME,
				NR_NEWS_SAVED.ENTRY_URL ITEM_URL,			
				NR_NEWS_SAVED.CREATION_DATE,
				NR_NEWS_SAVED.CREATION_DATE UPDATE_DATE,
				NR_NEWS_SAVED.TAGS,
				NR_NEWS_SAVED.N_COMMENTS,
				NR_NEWS_SAVED.N_RECOMMANDATIONS,
				0 N_ATTACHMENTS, -- N_ATTACHMENTS
				NR_NEWS_SAVED.BRIEF_DESC,
				NULL	LAST_RECOMMENDER_ID,
				NULL	LAST_DATE_RECOMMENDER_ID,
				NULL  PREV_RECOMMENDER_ID,
				NULL PREV_DATE_RECOMMENDER_ID,
				NULL  LAST_COMMENT_ID,
				NULL LAST_DATE_COMMENT,
				NULL  LAST_DESC_COMMENT,
				NULL  LAST_AUTHOR_COMMENT,
				NULL  PREV_COMMENT_ID,
				NULL PREV_DATE_COMMENT,
				NULL  PREV_DESC_COMMENT,
				NULL  PREV_AUTHOR_COMMENT,
				NULL  TARGET_SUBJECT_ID,
				NR_NEWS_SAVED.ENTRY_ATOM_URL	ITEM_ATOM_URL,
				NULL  MESSAGE,
				NULL  PREVIEW_IMAGE_URL,
				NULL  JSON_META_DATA,
				NULL EVENT_TIME,
				0 SCOPE
		FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT 	ITEM_ID, NEWS_STORY_ID STORY_ID, MAX(CREATION_DATE) CREATION_DATE
				FROM 	HOMEPAGE.NR_NEWS_SAVED
				WHERE 	ITEM_ID IS NOT NULL AND SOURCE_TYPE = 8
				GROUP	BY ITEM_ID, NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.NEWS_STORY_ID = TEMP.STORY_ID AND NR_NEWS_SAVED.CREATION_DATE = TEMP.CREATION_DATE
		) TEMP_STORIES  
	WHERE TEMP_STORIES.ENTRY_ID NOT IN (	
									SELECT 	ENTRY_ID
									FROM 	HOMEPAGE.NR_ENTRIES_FILE
								)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_ENTRIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_ENTRIES' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ENTRIES_FILE' -- Schema name and table name

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


DROP VIEW HOMEPAGE.TMP_ENTRIES;

COMMIT;

-- 9) FRM ENTRIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.TMP_ENTRIES AS (
	SELECT 
		ENTRY_ID, SOURCE, SOURCE_TYPE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_ID,     
		ITEM_NAME,  ITEM_URL, CREATION_DATE, UPDATE_DATE, TAGS, N_COMMENTS, N_RECOMMANDATIONS, N_ATTACHMENTS, BRIEF_DESC,  
		LAST_RECOMMENDER_ID, LAST_DATE_RECOMMENDER_ID, PREV_RECOMMENDER_ID, PREV_DATE_RECOMMENDER_ID,  LAST_COMMENT_ID, 
		LAST_DATE_COMMENT, LAST_DESC_COMMENT,  LAST_AUTHOR_COMMENT, PREV_COMMENT_ID,  PREV_DATE_COMMENT,  PREV_DESC_COMMENT,  
		PREV_AUTHOR_COMMENT, TARGET_SUBJECT_ID,  ITEM_ATOM_URL,  MESSAGE,   PREVIEW_IMAGE_URL,  JSON_META_DATA, EVENT_TIME, SCOPE
	FROM (
		SELECT 	NR_NEWS_SAVED.ITEM_ID ENTRY_ID,
				NR_NEWS_SAVED.SOURCE,
				NR_NEWS_SAVED.SOURCE_TYPE,
				NR_NEWS_SAVED.CONTAINER_ID,
				NR_NEWS_SAVED.CONTAINER_NAME,
				NR_NEWS_SAVED.CONTAINER_URL,
				NR_NEWS_SAVED.ITEM_ID,
				NR_NEWS_SAVED.ENTRY_NAME	ITEM_NAME,
				NR_NEWS_SAVED.ENTRY_URL ITEM_URL,			
				NR_NEWS_SAVED.CREATION_DATE,
				NR_NEWS_SAVED.CREATION_DATE UPDATE_DATE,
				NR_NEWS_SAVED.TAGS,
				NR_NEWS_SAVED.N_COMMENTS,
				NR_NEWS_SAVED.N_RECOMMANDATIONS,
				0 N_ATTACHMENTS, -- N_ATTACHMENTS
				NR_NEWS_SAVED.BRIEF_DESC,
				NULL	LAST_RECOMMENDER_ID,
				NULL	LAST_DATE_RECOMMENDER_ID,
				NULL  PREV_RECOMMENDER_ID,
				NULL PREV_DATE_RECOMMENDER_ID,
				NULL  LAST_COMMENT_ID,
				NULL LAST_DATE_COMMENT,
				NULL  LAST_DESC_COMMENT,
				NULL  LAST_AUTHOR_COMMENT,
				NULL  PREV_COMMENT_ID,
				NULL PREV_DATE_COMMENT,
				NULL  PREV_DESC_COMMENT,
				NULL  PREV_AUTHOR_COMMENT,
				NULL  TARGET_SUBJECT_ID,
				NR_NEWS_SAVED.ENTRY_ATOM_URL	ITEM_ATOM_URL,
				NULL  MESSAGE,
				NULL  PREVIEW_IMAGE_URL,
				NULL  JSON_META_DATA,
				NULL EVENT_TIME,
				0 SCOPE
		FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT 	ITEM_ID, NEWS_STORY_ID STORY_ID, MAX(CREATION_DATE) CREATION_DATE
				FROM 	HOMEPAGE.NR_NEWS_SAVED
				WHERE 	ITEM_ID IS NOT NULL AND SOURCE_TYPE = 9
				GROUP	BY ITEM_ID, NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.NEWS_STORY_ID = TEMP.STORY_ID AND NR_NEWS_SAVED.CREATION_DATE = TEMP.CREATION_DATE
		) TEMP_STORIES  
	WHERE TEMP_STORIES.ENTRY_ID NOT IN (	
									SELECT 	ENTRY_ID
									FROM 	HOMEPAGE.NR_ENTRIES_FRM
								)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.TMP_ENTRIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO


declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.TMP_ENTRIES' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ENTRIES_FRM' -- Schema name and table name

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

DROP VIEW HOMEPAGE.TMP_ENTRIES;

COMMIT;


----------------------------------------------------
----- STORIES
----------------------------------------------------

-- 1 ACT STORIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.STORIES AS (
	SELECT 		NEWS_STORY_ID		STORY_ID,
				EVENT_NAME			EVENT_NAME,
				SOURCE				SOURCE,
				CONTAINER_ID		CONTAINER_ID,
				CONTAINER_NAME		CONTAINER_NAME,
				CONTAINER_URL		CONTAINER_URL,
				ENTRY_ATOM_URL		ITEM_ATOM_URL,
				ITEM_CORRELATION_ID	ITEM_CORRELATION_ID,
				CREATION_DATE		CREATION_DATE,
				BRIEF_DESC			BRIEF_DESC,
				ACTOR_UUID			ACTOR_UUID,
				EVENT_RECORD_UUID	EVENT_RECORD_UUID,
				TAGS				TAGS,
				META_TEMPLATE		META_TEMPLATE,
				TEXT_META_TEMPLATE	TEXT_META_TEMPLATE,
				NULL					R_META_TEMPLATE,
				NULL					R_TEXT_META_TEMPLATE,
				IS_COMMUNITY_STORY	IS_COMMUNITY_STORY,
				NULL					ITEM_CORRELATION_NAME,
				SOURCE_TYPE			SOURCE_TYPE,
				0					HAS_ATTACHMENT,			
				ITEM_ID				ENTRY_ID,
				NULL					MESSAGE,
				CREATION_DATE		EVENT_TIME,
				NULL					VERB,
				NULL				ACTIVITY_META_DATA_1,
				NULL				ACTIVITY_META_DATA_2,
				0					IS_META_DATA_TRUNCATED
		FROM HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT NEWS_STORY_ID STORY_ID, MAX (CREATION_DATE) MAX_CREATION_DATE
				FROM HOMEPAGE.NR_NEWS_SAVED
				WHERE SOURCE_TYPE = 1
				GROUP BY NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.SOURCE_TYPE = 1 AND 	NR_NEWS_SAVED.CREATION_DATE = TEMP.MAX_CREATION_DATE AND NEWS_STORY_ID NOT IN 	(	SELECT STORY_ID
												 			FROM HOMEPAGE.NR_SRC_STORIES_ACT
												)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.STORIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.STORIES' -- The view name need also to be specified with the database name and the schema name
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

DROP VIEW HOMEPAGE.STORIES;

COMMIT;

-- 2 BLG STORIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.STORIES AS (
	SELECT 		NEWS_STORY_ID		STORY_ID,
				EVENT_NAME			EVENT_NAME,
				SOURCE				SOURCE,
				CONTAINER_ID		CONTAINER_ID,
				CONTAINER_NAME		CONTAINER_NAME,
				CONTAINER_URL		CONTAINER_URL,
				ENTRY_ATOM_URL		ITEM_ATOM_URL,
				ITEM_CORRELATION_ID	ITEM_CORRELATION_ID,
				CREATION_DATE		CREATION_DATE,
				BRIEF_DESC			BRIEF_DESC,
				ACTOR_UUID			ACTOR_UUID,
				EVENT_RECORD_UUID	EVENT_RECORD_UUID,
				TAGS				TAGS,
				META_TEMPLATE		META_TEMPLATE,
				TEXT_META_TEMPLATE	TEXT_META_TEMPLATE,
				NULL					R_META_TEMPLATE,
				NULL					R_TEXT_META_TEMPLATE,
				IS_COMMUNITY_STORY	IS_COMMUNITY_STORY,
				NULL					ITEM_CORRELATION_NAME,
				SOURCE_TYPE			SOURCE_TYPE,
				0					HAS_ATTACHMENT,			
				ITEM_ID				ENTRY_ID,
				NULL					MESSAGE,
				CREATION_DATE		EVENT_TIME,
				NULL					VERB,
				NULL				ACTIVITY_META_DATA_1,
				NULL				ACTIVITY_META_DATA_2,
				0					IS_META_DATA_TRUNCATED
		FROM HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT NEWS_STORY_ID STORY_ID, MAX (CREATION_DATE) MAX_CREATION_DATE
				FROM HOMEPAGE.NR_NEWS_SAVED
				WHERE SOURCE_TYPE = 2
				GROUP BY NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.SOURCE_TYPE = 2 AND 	NR_NEWS_SAVED.CREATION_DATE = TEMP.MAX_CREATION_DATE AND NEWS_STORY_ID NOT IN 	(	SELECT STORY_ID
												 			FROM HOMEPAGE.NR_SRC_STORIES_BLG
												)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.STORIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.STORIES' -- The view name need also to be specified with the database name and the schema name
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

DROP VIEW HOMEPAGE.STORIES;

COMMIT;

-- 3 COM STORIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.STORIES AS (
	SELECT 		NEWS_STORY_ID		STORY_ID,
				EVENT_NAME			EVENT_NAME,
				SOURCE				SOURCE,
				CONTAINER_ID		CONTAINER_ID,
				CONTAINER_NAME		CONTAINER_NAME,
				CONTAINER_URL		CONTAINER_URL,
				ENTRY_ATOM_URL		ITEM_ATOM_URL,
				ITEM_CORRELATION_ID	ITEM_CORRELATION_ID,
				CREATION_DATE		CREATION_DATE,
				BRIEF_DESC			BRIEF_DESC,
				ACTOR_UUID			ACTOR_UUID,
				EVENT_RECORD_UUID	EVENT_RECORD_UUID,
				TAGS				TAGS,
				META_TEMPLATE		META_TEMPLATE,
				TEXT_META_TEMPLATE	TEXT_META_TEMPLATE,
				NULL					R_META_TEMPLATE,
				NULL					R_TEXT_META_TEMPLATE,
				IS_COMMUNITY_STORY	IS_COMMUNITY_STORY,
				NULL					ITEM_CORRELATION_NAME,
				SOURCE_TYPE			SOURCE_TYPE,
				0					HAS_ATTACHMENT,			
				ITEM_ID				ENTRY_ID,
				NULL					MESSAGE,
				CREATION_DATE		EVENT_TIME,
				NULL					VERB,
				NULL				ACTIVITY_META_DATA_1,
				NULL				ACTIVITY_META_DATA_2,
				0					IS_META_DATA_TRUNCATED
		FROM HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT NEWS_STORY_ID STORY_ID, MAX (CREATION_DATE) MAX_CREATION_DATE
				FROM HOMEPAGE.NR_NEWS_SAVED
				WHERE SOURCE_TYPE = 3
				GROUP BY NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.SOURCE_TYPE = 3 AND 	NR_NEWS_SAVED.CREATION_DATE = TEMP.MAX_CREATION_DATE AND NEWS_STORY_ID NOT IN 	(	SELECT STORY_ID
												 			FROM HOMEPAGE.NR_SRC_STORIES_COM
												)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.STORIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.STORIES' -- The view name need also to be specified with the database name and the schema name
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

DROP VIEW HOMEPAGE.STORIES;

COMMIT;

-- 4 WIK STORIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.STORIES AS (
	SELECT 		NEWS_STORY_ID		STORY_ID,
				EVENT_NAME			EVENT_NAME,
				SOURCE				SOURCE,
				CONTAINER_ID		CONTAINER_ID,
				CONTAINER_NAME		CONTAINER_NAME,
				CONTAINER_URL		CONTAINER_URL,
				ENTRY_ATOM_URL		ITEM_ATOM_URL,
				ITEM_CORRELATION_ID	ITEM_CORRELATION_ID,
				CREATION_DATE		CREATION_DATE,
				BRIEF_DESC			BRIEF_DESC,
				ACTOR_UUID			ACTOR_UUID,
				EVENT_RECORD_UUID	EVENT_RECORD_UUID,
				TAGS				TAGS,
				META_TEMPLATE		META_TEMPLATE,
				TEXT_META_TEMPLATE	TEXT_META_TEMPLATE,
				NULL					R_META_TEMPLATE,
				NULL					R_TEXT_META_TEMPLATE,
				IS_COMMUNITY_STORY	IS_COMMUNITY_STORY,
				NULL					ITEM_CORRELATION_NAME,
				SOURCE_TYPE			SOURCE_TYPE,
				0					HAS_ATTACHMENT,			
				ITEM_ID				ENTRY_ID,
				NULL					MESSAGE,
				CREATION_DATE		EVENT_TIME,
				NULL					VERB,
				NULL				ACTIVITY_META_DATA_1,
				NULL				ACTIVITY_META_DATA_2,
				0					IS_META_DATA_TRUNCATED
		FROM HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT NEWS_STORY_ID STORY_ID, MAX (CREATION_DATE) MAX_CREATION_DATE
				FROM HOMEPAGE.NR_NEWS_SAVED
				WHERE SOURCE_TYPE = 4
				GROUP BY NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.SOURCE_TYPE = 4 AND 	NR_NEWS_SAVED.CREATION_DATE = TEMP.MAX_CREATION_DATE AND NEWS_STORY_ID NOT IN 	(	SELECT STORY_ID
												 			FROM HOMEPAGE.NR_SRC_STORIES_WIK
												)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.STORIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.STORIES' -- The view name need also to be specified with the database name and the schema name
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

DROP VIEW HOMEPAGE.STORIES;

COMMIT;

-- 5 PRF STORIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.STORIES AS (
	SELECT 		NEWS_STORY_ID		STORY_ID,
				EVENT_NAME			EVENT_NAME,
				SOURCE				SOURCE,
				CONTAINER_ID		CONTAINER_ID,
				CONTAINER_NAME		CONTAINER_NAME,
				CONTAINER_URL		CONTAINER_URL,
				ENTRY_ATOM_URL		ITEM_ATOM_URL,
				ITEM_CORRELATION_ID	ITEM_CORRELATION_ID,
				CREATION_DATE		CREATION_DATE,
				BRIEF_DESC			BRIEF_DESC,
				ACTOR_UUID			ACTOR_UUID,
				EVENT_RECORD_UUID	EVENT_RECORD_UUID,
				TAGS				TAGS,
				META_TEMPLATE		META_TEMPLATE,
				TEXT_META_TEMPLATE	TEXT_META_TEMPLATE,
				NULL					R_META_TEMPLATE,
				NULL					R_TEXT_META_TEMPLATE,
				IS_COMMUNITY_STORY	IS_COMMUNITY_STORY,
				NULL					ITEM_CORRELATION_NAME,
				SOURCE_TYPE			SOURCE_TYPE,
				0					HAS_ATTACHMENT,			
				ITEM_ID				ENTRY_ID,
				NULL					MESSAGE,
				CREATION_DATE		EVENT_TIME,
				NULL					VERB,
				NULL				ACTIVITY_META_DATA_1,
				NULL				ACTIVITY_META_DATA_2,
				0					IS_META_DATA_TRUNCATED
		FROM HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT NEWS_STORY_ID STORY_ID, MAX (CREATION_DATE) MAX_CREATION_DATE
				FROM HOMEPAGE.NR_NEWS_SAVED
				WHERE SOURCE_TYPE = 5
				GROUP BY NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.SOURCE_TYPE = 5 AND 	NR_NEWS_SAVED.CREATION_DATE = TEMP.MAX_CREATION_DATE AND NEWS_STORY_ID NOT IN 	(	SELECT STORY_ID
												 			FROM HOMEPAGE.NR_SRC_STORIES_PRF
												)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.STORIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.STORIES' -- The view name need also to be specified with the database name and the schema name
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

DROP VIEW HOMEPAGE.STORIES;

COMMIT;

-- 6 HP STORIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.STORIES AS (
	SELECT 		NEWS_STORY_ID		STORY_ID,
				EVENT_NAME			EVENT_NAME,
				SOURCE				SOURCE,
				CONTAINER_ID		CONTAINER_ID,
				CONTAINER_NAME		CONTAINER_NAME,
				CONTAINER_URL		CONTAINER_URL,
				ENTRY_ATOM_URL		ITEM_ATOM_URL,
				ITEM_CORRELATION_ID	ITEM_CORRELATION_ID,
				CREATION_DATE		CREATION_DATE,
				BRIEF_DESC			BRIEF_DESC,
				ACTOR_UUID			ACTOR_UUID,
				EVENT_RECORD_UUID	EVENT_RECORD_UUID,
				TAGS				TAGS,
				META_TEMPLATE		META_TEMPLATE,
				TEXT_META_TEMPLATE	TEXT_META_TEMPLATE,
				NULL					R_META_TEMPLATE,
				NULL					R_TEXT_META_TEMPLATE,
				IS_COMMUNITY_STORY	IS_COMMUNITY_STORY,
				NULL					ITEM_CORRELATION_NAME,
				SOURCE_TYPE			SOURCE_TYPE,
				0					HAS_ATTACHMENT,			
				ITEM_ID				ENTRY_ID,
				NULL					MESSAGE,
				CREATION_DATE		EVENT_TIME,
				NULL					VERB,
				NULL				ACTIVITY_META_DATA_1,
				NULL				ACTIVITY_META_DATA_2,
				0					IS_META_DATA_TRUNCATED
		FROM HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT NEWS_STORY_ID STORY_ID, MAX (CREATION_DATE) MAX_CREATION_DATE
				FROM HOMEPAGE.NR_NEWS_SAVED
				WHERE SOURCE_TYPE = 6
				GROUP BY NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.SOURCE_TYPE = 6 AND 	NR_NEWS_SAVED.CREATION_DATE = TEMP.MAX_CREATION_DATE AND NEWS_STORY_ID NOT IN 	(	SELECT STORY_ID
												 			FROM HOMEPAGE.NR_SRC_STORIES_HP
												)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.STORIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.STORIES' -- The view name need also to be specified with the database name and the schema name
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

DROP VIEW HOMEPAGE.STORIES;

COMMIT;

-- 7 DGR STORIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.STORIES AS (
	SELECT 		NEWS_STORY_ID		STORY_ID,
				EVENT_NAME			EVENT_NAME,
				SOURCE				SOURCE,
				CONTAINER_ID		CONTAINER_ID,
				CONTAINER_NAME		CONTAINER_NAME,
				CONTAINER_URL		CONTAINER_URL,
				ENTRY_ATOM_URL		ITEM_ATOM_URL,
				ITEM_CORRELATION_ID	ITEM_CORRELATION_ID,
				CREATION_DATE		CREATION_DATE,
				BRIEF_DESC			BRIEF_DESC,
				ACTOR_UUID			ACTOR_UUID,
				EVENT_RECORD_UUID	EVENT_RECORD_UUID,
				TAGS				TAGS,
				META_TEMPLATE		META_TEMPLATE,
				TEXT_META_TEMPLATE	TEXT_META_TEMPLATE,
				NULL					R_META_TEMPLATE,
				NULL					R_TEXT_META_TEMPLATE,
				IS_COMMUNITY_STORY	IS_COMMUNITY_STORY,
				NULL					ITEM_CORRELATION_NAME,
				SOURCE_TYPE			SOURCE_TYPE,
				0					HAS_ATTACHMENT,			
				ITEM_ID				ENTRY_ID,
				NULL					MESSAGE,
				CREATION_DATE		EVENT_TIME,
				NULL					VERB,
				NULL				ACTIVITY_META_DATA_1,
				NULL				ACTIVITY_META_DATA_2,
				0					IS_META_DATA_TRUNCATED
		FROM HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT NEWS_STORY_ID STORY_ID, MAX (CREATION_DATE) MAX_CREATION_DATE
				FROM HOMEPAGE.NR_NEWS_SAVED
				WHERE SOURCE_TYPE = 7
				GROUP BY NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.SOURCE_TYPE = 7 AND 	NR_NEWS_SAVED.CREATION_DATE = TEMP.MAX_CREATION_DATE AND NEWS_STORY_ID NOT IN 	(	SELECT STORY_ID
												 			FROM HOMEPAGE.NR_SRC_STORIES_DGR
												)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.STORIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.STORIES' -- The view name need also to be specified with the database name and the schema name
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

DROP VIEW HOMEPAGE.STORIES;

COMMIT;

-- 8 FILE STORIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.STORIES AS (
	SELECT 		NEWS_STORY_ID		STORY_ID,
				EVENT_NAME			EVENT_NAME,
				SOURCE				SOURCE,
				CONTAINER_ID		CONTAINER_ID,
				CONTAINER_NAME		CONTAINER_NAME,
				CONTAINER_URL		CONTAINER_URL,
				ENTRY_ATOM_URL		ITEM_ATOM_URL,
				ITEM_CORRELATION_ID	ITEM_CORRELATION_ID,
				CREATION_DATE		CREATION_DATE,
				BRIEF_DESC			BRIEF_DESC,
				ACTOR_UUID			ACTOR_UUID,
				EVENT_RECORD_UUID	EVENT_RECORD_UUID,
				TAGS				TAGS,
				META_TEMPLATE		META_TEMPLATE,
				TEXT_META_TEMPLATE	TEXT_META_TEMPLATE,
				NULL					R_META_TEMPLATE,
				NULL					R_TEXT_META_TEMPLATE,
				IS_COMMUNITY_STORY	IS_COMMUNITY_STORY,
				NULL					ITEM_CORRELATION_NAME,
				SOURCE_TYPE			SOURCE_TYPE,
				0					HAS_ATTACHMENT,			
				ITEM_ID				ENTRY_ID,
				NULL					MESSAGE,
				CREATION_DATE		EVENT_TIME,
				NULL					VERB,
				NULL				ACTIVITY_META_DATA_1,
				NULL				ACTIVITY_META_DATA_2,
				0					IS_META_DATA_TRUNCATED
		FROM HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT NEWS_STORY_ID STORY_ID, MAX (CREATION_DATE) MAX_CREATION_DATE
				FROM HOMEPAGE.NR_NEWS_SAVED
				WHERE SOURCE_TYPE = 8
				GROUP BY NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.SOURCE_TYPE = 8 AND 	NR_NEWS_SAVED.CREATION_DATE = TEMP.MAX_CREATION_DATE AND NEWS_STORY_ID NOT IN 	(	SELECT STORY_ID
												 			FROM HOMEPAGE.NR_SRC_STORIES_FILE
												)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.STORIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.STORIES' -- The view name need also to be specified with the database name and the schema name
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

DROP VIEW HOMEPAGE.STORIES;

COMMIT;

-- 9 FRM STORIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.STORIES AS (
	SELECT 		NEWS_STORY_ID		STORY_ID,
				EVENT_NAME			EVENT_NAME,
				SOURCE				SOURCE,
				CONTAINER_ID		CONTAINER_ID,
				CONTAINER_NAME		CONTAINER_NAME,
				CONTAINER_URL		CONTAINER_URL,
				ENTRY_ATOM_URL		ITEM_ATOM_URL,
				ITEM_CORRELATION_ID	ITEM_CORRELATION_ID,
				CREATION_DATE		CREATION_DATE,
				BRIEF_DESC			BRIEF_DESC,
				ACTOR_UUID			ACTOR_UUID,
				EVENT_RECORD_UUID	EVENT_RECORD_UUID,
				TAGS				TAGS,
				META_TEMPLATE		META_TEMPLATE,
				TEXT_META_TEMPLATE	TEXT_META_TEMPLATE,
				NULL					R_META_TEMPLATE,
				NULL					R_TEXT_META_TEMPLATE,
				IS_COMMUNITY_STORY	IS_COMMUNITY_STORY,
				NULL					ITEM_CORRELATION_NAME,
				SOURCE_TYPE			SOURCE_TYPE,
				0					HAS_ATTACHMENT,			
				ITEM_ID				ENTRY_ID,
				NULL					MESSAGE,
				CREATION_DATE		EVENT_TIME,
				NULL					VERB,
				NULL				ACTIVITY_META_DATA_1,
				NULL				ACTIVITY_META_DATA_2,
				0					IS_META_DATA_TRUNCATED
		FROM HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT NEWS_STORY_ID STORY_ID, MAX (CREATION_DATE) MAX_CREATION_DATE
				FROM HOMEPAGE.NR_NEWS_SAVED
				WHERE SOURCE_TYPE = 9
				GROUP BY NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.SOURCE_TYPE = 9 AND 	NR_NEWS_SAVED.CREATION_DATE = TEMP.MAX_CREATION_DATE AND NEWS_STORY_ID NOT IN 	(	SELECT STORY_ID
												 			FROM HOMEPAGE.NR_SRC_STORIES_FRM
												)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.STORIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.STORIES' -- The view name need also to be specified with the database name and the schema name
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

DROP VIEW HOMEPAGE.STORIES;

COMMIT;

--------------------------------------------------------------------------
-- READERS
--------------------------------------------------------------------------

-- 1 READERS
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.READERS AS (
	SELECT 	NR_NEWS_SAVED.READER_ID						READER_ID,
			13										CATEGORY_TYPE,
			NR_NEWS_SAVED.SOURCE						SOURCE,
			NR_NEWS_SAVED.CONTAINER_ID					CONTAINER_ID,
			NR_NEWS_SAVED.ITEM_ID						ITEM_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_SAVED.CREATION_DATE					CREATION_DATE,
			NR_NEWS_SAVED.NEWS_STORY_ID					STORY_ID,
			NR_NEWS_SAVED.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,				
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_SAVED.CREATION_DATE					EVENT_TIME,
			NULL										NOTE_TEXT,
			NULL 						NOTE_UPDATE_DATE,
			NULL										ROLLUP_ENTRY_ID,
			NR_NEWS_SAVED.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			NR_NEWS_SAVED.IS_COMMUNITY_STORY				IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID
	FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED
	WHERE 	SOURCE_TYPE = 1
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.READERS TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.READERS' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ACTIONABLE_READERS' -- Schema name and table name

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

DROP VIEW HOMEPAGE.READERS;

COMMIT;

-- 2 READERS
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.READERS AS (
	SELECT 	NR_NEWS_SAVED.READER_ID						READER_ID,
			13										CATEGORY_TYPE,
			NR_NEWS_SAVED.SOURCE						SOURCE,
			NR_NEWS_SAVED.CONTAINER_ID					CONTAINER_ID,
			NR_NEWS_SAVED.ITEM_ID						ITEM_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_SAVED.CREATION_DATE					CREATION_DATE,
			NR_NEWS_SAVED.NEWS_STORY_ID					STORY_ID,
			NR_NEWS_SAVED.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,				
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_SAVED.CREATION_DATE					EVENT_TIME,
			NULL										NOTE_TEXT,
			NULL 						NOTE_UPDATE_DATE,
			NULL										ROLLUP_ENTRY_ID,
			NR_NEWS_SAVED.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			NR_NEWS_SAVED.IS_COMMUNITY_STORY				IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID
	FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED
	WHERE 	SOURCE_TYPE = 2
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.READERS TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.READERS' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ACTIONABLE_READERS' -- Schema name and table name

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


DROP VIEW HOMEPAGE.READERS;

COMMIT;

-- 3 READERS
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.READERS AS (
	SELECT 	NR_NEWS_SAVED.READER_ID						READER_ID,
			13										CATEGORY_TYPE,
			NR_NEWS_SAVED.SOURCE						SOURCE,
			NR_NEWS_SAVED.CONTAINER_ID					CONTAINER_ID,
			NR_NEWS_SAVED.ITEM_ID						ITEM_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_SAVED.CREATION_DATE					CREATION_DATE,
			NR_NEWS_SAVED.NEWS_STORY_ID					STORY_ID,
			NR_NEWS_SAVED.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,				
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_SAVED.CREATION_DATE					EVENT_TIME,
			NULL										NOTE_TEXT,
			NULL 						NOTE_UPDATE_DATE,
			NULL										ROLLUP_ENTRY_ID,
			NR_NEWS_SAVED.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			NR_NEWS_SAVED.IS_COMMUNITY_STORY				IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID
	FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED
	WHERE 	SOURCE_TYPE = 3
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.READERS TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.READERS' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ACTIONABLE_READERS' -- Schema name and table name

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

DROP VIEW HOMEPAGE.READERS;

COMMIT;

-- 4 READERS
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.READERS AS (
	SELECT 	NR_NEWS_SAVED.READER_ID						READER_ID,
			13										CATEGORY_TYPE,
			NR_NEWS_SAVED.SOURCE						SOURCE,
			NR_NEWS_SAVED.CONTAINER_ID					CONTAINER_ID,
			NR_NEWS_SAVED.ITEM_ID						ITEM_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_SAVED.CREATION_DATE					CREATION_DATE,
			NR_NEWS_SAVED.NEWS_STORY_ID					STORY_ID,
			NR_NEWS_SAVED.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,				
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_SAVED.CREATION_DATE					EVENT_TIME,
			NULL										NOTE_TEXT,
			NULL 						NOTE_UPDATE_DATE,
			NULL										ROLLUP_ENTRY_ID,
			NR_NEWS_SAVED.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			NR_NEWS_SAVED.IS_COMMUNITY_STORY				IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID
	FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED
	WHERE 	SOURCE_TYPE = 4
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.READERS TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.READERS' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ACTIONABLE_READERS' -- Schema name and table name

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

DROP VIEW HOMEPAGE.READERS;

COMMIT;

-- 5 READERS
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.READERS AS (
	SELECT 	NR_NEWS_SAVED.READER_ID						READER_ID,
			13										CATEGORY_TYPE,
			NR_NEWS_SAVED.SOURCE						SOURCE,
			NR_NEWS_SAVED.CONTAINER_ID					CONTAINER_ID,
			NR_NEWS_SAVED.ITEM_ID						ITEM_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_SAVED.CREATION_DATE					CREATION_DATE,
			NR_NEWS_SAVED.NEWS_STORY_ID					STORY_ID,
			NR_NEWS_SAVED.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,				
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_SAVED.CREATION_DATE					EVENT_TIME,
			NULL										NOTE_TEXT,
			NULL 						NOTE_UPDATE_DATE,
			NULL										ROLLUP_ENTRY_ID,
			NR_NEWS_SAVED.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			NR_NEWS_SAVED.IS_COMMUNITY_STORY				IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID
	FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED
	WHERE 	SOURCE_TYPE = 5
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.READERS TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.READERS' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ACTIONABLE_READERS' -- Schema name and table name

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

DROP VIEW HOMEPAGE.READERS;

COMMIT;

-- 6 READERS
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.READERS AS (
	SELECT 	NR_NEWS_SAVED.READER_ID						READER_ID,
			13										CATEGORY_TYPE,
			NR_NEWS_SAVED.SOURCE						SOURCE,
			NR_NEWS_SAVED.CONTAINER_ID					CONTAINER_ID,
			NR_NEWS_SAVED.ITEM_ID						ITEM_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_SAVED.CREATION_DATE					CREATION_DATE,
			NR_NEWS_SAVED.NEWS_STORY_ID					STORY_ID,
			NR_NEWS_SAVED.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,				
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_SAVED.CREATION_DATE					EVENT_TIME,
			NULL										NOTE_TEXT,
			NULL 						NOTE_UPDATE_DATE,
			NULL										ROLLUP_ENTRY_ID,
			NR_NEWS_SAVED.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			NR_NEWS_SAVED.IS_COMMUNITY_STORY				IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID
	FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED
	WHERE 	SOURCE_TYPE = 6
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.READERS TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.READERS' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ACTIONABLE_READERS' -- Schema name and table name

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

DROP VIEW HOMEPAGE.READERS;

COMMIT;

-- 7 READERS
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.READERS AS (
	SELECT 	NR_NEWS_SAVED.READER_ID						READER_ID,
			13										CATEGORY_TYPE,
			NR_NEWS_SAVED.SOURCE						SOURCE,
			NR_NEWS_SAVED.CONTAINER_ID					CONTAINER_ID,
			NR_NEWS_SAVED.ITEM_ID						ITEM_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_SAVED.CREATION_DATE					CREATION_DATE,
			NR_NEWS_SAVED.NEWS_STORY_ID					STORY_ID,
			NR_NEWS_SAVED.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,				
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_SAVED.CREATION_DATE					EVENT_TIME,
			NULL										NOTE_TEXT,
			NULL 						NOTE_UPDATE_DATE,
			NULL										ROLLUP_ENTRY_ID,
			NR_NEWS_SAVED.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			NR_NEWS_SAVED.IS_COMMUNITY_STORY				IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID
	FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED
	WHERE 	SOURCE_TYPE = 7
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.READERS TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.READERS' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ACTIONABLE_READERS' -- Schema name and table name

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

DROP VIEW HOMEPAGE.READERS;

COMMIT;

-- 8 READERS
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.READERS AS (
	SELECT 	NR_NEWS_SAVED.READER_ID						READER_ID,
			13										CATEGORY_TYPE,
			NR_NEWS_SAVED.SOURCE						SOURCE,
			NR_NEWS_SAVED.CONTAINER_ID					CONTAINER_ID,
			NR_NEWS_SAVED.ITEM_ID						ITEM_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_SAVED.CREATION_DATE					CREATION_DATE,
			NR_NEWS_SAVED.NEWS_STORY_ID					STORY_ID,
			NR_NEWS_SAVED.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,				
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_SAVED.CREATION_DATE					EVENT_TIME,
			NULL										NOTE_TEXT,
			NULL 						NOTE_UPDATE_DATE,
			NULL										ROLLUP_ENTRY_ID,
			NR_NEWS_SAVED.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			NR_NEWS_SAVED.IS_COMMUNITY_STORY				IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID
	FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED
	WHERE 	SOURCE_TYPE = 8
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.READERS TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.READERS' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ACTIONABLE_READERS' -- Schema name and table name

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

DROP VIEW HOMEPAGE.READERS;

COMMIT;

-- 9 READERS
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.READERS AS (
	SELECT 	NR_NEWS_SAVED.READER_ID						READER_ID,
			13										CATEGORY_TYPE,
			NR_NEWS_SAVED.SOURCE						SOURCE,
			NR_NEWS_SAVED.CONTAINER_ID					CONTAINER_ID,
			NR_NEWS_SAVED.ITEM_ID						ITEM_ID,
			0										RESOURCE_TYPE,
			NR_NEWS_SAVED.CREATION_DATE					CREATION_DATE,
			NR_NEWS_SAVED.NEWS_STORY_ID					STORY_ID,
			NR_NEWS_SAVED.SOURCE_TYPE					SOURCE_TYPE,
			0										USE_IN_ROLLUP,				
			0										IS_NETWORK,
			0										IS_FOLLOWER,
			NR_NEWS_SAVED.CREATION_DATE					EVENT_TIME,
			NULL										NOTE_TEXT,
			NULL 						NOTE_UPDATE_DATE,
			NULL										ROLLUP_ENTRY_ID,
			NR_NEWS_SAVED.NEWS_RECORDS_ID 				CATEGORY_READER_ID,
			NR_NEWS_SAVED.IS_COMMUNITY_STORY				IS_STORY_COMM,
			0										IS_BROADCAST,
			NULL										ORGANIZATION_ID
	FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED
	WHERE 	SOURCE_TYPE = 9
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.READERS TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.READERS' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_ACTIONABLE_READERS' -- Schema name and table name

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


DROP VIEW HOMEPAGE.READERS;

COMMIT;


------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
--	[END] SAVED MIGRATION
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------

-------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------
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



