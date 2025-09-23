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

----------------------------------------------
-- Adding: JSON_META_DATA
----------------------------------------------

DROP TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE;

GO

----------------------------------------------------------------
-- ADDING BOARD_OBJECT_REFERENCE
----------------------------------------------------------------
CREATE TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE (
	OBJECT_ID nvarchar(47) NOT NULL,
	ENTRY_ID nvarchar(47) NOT NULL,
	DISPLAY_NAME nvarchar(2048),
	IMAGE_NAME   nvarchar(2048),
	NAME nvarchar(512), 
	URL nvarchar(1024), 
	CONTENT nvarchar(4000),
	IMAGE_URL nvarchar(1024),
	SOURCE  nvarchar(36),
	SOURCE_TYPE  NUMERIC(5 ,0),
	CREATION_DATE DATETIME,
	MIME_TYPE nvarchar(36),
	AUTHOR_ID nvarchar(36),
	AUTHOR_DISPLAY_NAME nvarchar(256),
	JSON_META_DATA nvarchar(4000)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE 
    ADD CONSTRAINT PK_BRD_OBJ_ID PRIMARY KEY(OBJECT_ID);

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE
	ADD CONSTRAINT FK_BRD_OBJ_ENTRY FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.BOARD_ENTRIES (ENTRY_ID);

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE
	ADD CONSTRAINT FK_BRD_AUTHOR_ID FOREIGN KEY (AUTHOR_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

CREATE INDEX  BRD_ENTRY_IDX
    ON HOMEPAGE.BOARD_OBJECT_REFERENCE (ENTRY_ID);

CREATE INDEX  BRD_AUTHOR_IDX
    ON HOMEPAGE.BOARD_OBJECT_REFERENCE (AUTHOR_ID);    
GO

------------------------------------------------------------
--
------------------------------------------------------------
DROP TABLE HOMEPAGE.NR_SOURCE_TYPE;

------------------------------------------------
-- NR_SOURCE_TYPE
------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SOURCE_TYPE (
	SOURCE_TYPE_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0) NOT NULL, -- numeric that is 1,2,3 etc.. 100, 101..
	EXTERNAL_ID nvarchar(256) NOT NULL,
	DISPLAY_NAME nvarchar(4000),
	IMAGE_URL nvarchar(2048),
	PUBLISHED DATETIME,
	UPDATED DATETIME,	
	IS_ENABLED NUMERIC(5,0),
	SUMMARY nvarchar (4000),
	ORGANIZATION_ID nvarchar(36),
	URL nvarchar(2048),
	URL_SSL nvarchar(2048)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
  	ADD CONSTRAINT PK_SRC_TYPE_ID PRIMARY KEY(SOURCE_TYPE_ID);

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
	ADD CONSTRAINT SRC_TYPE_UNQ UNIQUE(SOURCE_TYPE);

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
	ADD CONSTRAINT SRC_EXT_ID_UNQ UNIQUE(EXTERNAL_ID);

GO	


------------
--- START INSERT NR_SOURCE_TYPE
------------

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, URL)
VALUES ('activities_c9cax4cc4x80bx51af2ddef2c', 1, 'int_activities_id', 'activities', null, null, null, 1, null, 'default', null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, URL)
VALUES ('blogs_c9cax4cc4x80bx51af2ddef2c', 2, 'int_blogs_id', 'blogs', null, null, null, 1, null, 'default', null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, URL)
VALUES ('communities_c9cax4cc4x80bx51af2d', 3, 'int_communities_id', 'communities', null, null, null, 1, null, 'default', null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, URL)
VALUES ('wikis_c9cax4cc4x80bx51af2ddef2c', 4, 'int_wikis_id', 'wikis', null, null, null, 1, null, 'default', null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, URL)
VALUES ('profiles_c9cax4cc4x80bx51af2ddef2c', 5, 'int_profiles_id', 'profiles', null, null, null, 1, null, 'default', null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, URL)
VALUES ('homepage_c9cax4cc4x80bx51af2ddef2c', 6, 'int_homepage_id', 'homepage', null, null, null, 1, null, 'default', null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, URL)
VALUES ('dogear_c9cax4cc4x80bx51af2ddef2c', 7, 'int_dogear_id', 'dogear', null, null, null, 1, null, 'default', null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, URL)
VALUES ('files_c9cax4cc4x80bx51af2ddef2c', 8, 'int_files_id', 'files', null, null, null, 1, null, 'default', null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, URL)
VALUES ('forums_c9cax4cc4x80bx51af2ddef2c', 9, 'int_forums_id', 'forums', null, null, null, 1, null, 'default', null);

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, EXTERNAL_ID, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, URL)
VALUES ('default_c9cax4cc4x80bx51af2ddef2c', 100, 'default', 'default', null, null, null, 1, null, 'default', null);

------------
--- END INSERT NR_SOURCE_TYPE
------------

------------------------------------------------------
--[START]: perf defect 40892 
------------------------------------------------------
CREATE INDEX  NR_SRC_STORIES_ACT_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_ACT (EVENT_RECORD_UUID, ENTRY_ID); 

GO
    
CREATE INDEX NR_SRC_STORIES_BLG_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_BLG (EVENT_RECORD_UUID, ENTRY_ID);

GO
    
CREATE INDEX NR_SRC_STORIES_COM_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_COM (EVENT_RECORD_UUID, ENTRY_ID);

GO

CREATE INDEX NR_SRC_STORIES_WIK_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_WIK (EVENT_RECORD_UUID, ENTRY_ID);

GO


CREATE INDEX NR_SRC_STORIES_PRF_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_PRF (EVENT_RECORD_UUID, ENTRY_ID);

GO
    
CREATE INDEX NR_SRC_STORIES_HP_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_HP (EVENT_RECORD_UUID, ENTRY_ID);

GO
    
CREATE INDEX NR_SRC_STORIES_DGR_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_DGR (EVENT_RECORD_UUID, ENTRY_ID);

GO
    
CREATE INDEX NR_SRC_STORIES_FILE_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_FILE (EVENT_RECORD_UUID, ENTRY_ID);

GO
    
CREATE INDEX NR_SRC_STORIES_FRM_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_FRM (EVENT_RECORD_UUID, ENTRY_ID);

GO
    
CREATE INDEX NR_SRC_STORIES_EXT_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_EXTERNAL (EVENT_RECORD_UUID, ENTRY_ID);

GO

------------------------------------------------------
--[END]: perf defect 40892 
------------------------------------------------------

---------------------------------------------------------------
-- Add the flag SCOPE to story table
---------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_ACT ADD SCOPE NUMERIC(5,0);
   
GO
    
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_BLG ADD SCOPE NUMERIC(5,0);

GO
    
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_COM ADD SCOPE NUMERIC(5,0);

GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_WIK ADD SCOPE NUMERIC(5,0);

GO

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_PRF  ADD SCOPE NUMERIC(5,0);

GO
    
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_HP  ADD SCOPE NUMERIC(5,0);

GO
    
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_DGR  ADD SCOPE NUMERIC(5,0);

GO
    
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_FILE  ADD SCOPE NUMERIC(5,0);

GO
    
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_FRM  ADD SCOPE NUMERIC(5,0);

GO
    
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_EXTERNAL  ADD SCOPE NUMERIC(5,0); 

GO

-----------------------------------------------------
-- Renaming existing index
-----------------------------------------------------
DROP INDEX HP_UI_UNQ ON HOMEPAGE.HP_UI;

GO

CREATE UNIQUE INDEX HP_UI_PERSONID
	ON HOMEPAGE.HP_UI (PERSON_ID);
	
GO	

--------------------------------------------------------
-- Adding an index on EMD_EMAIL_PREFS TRANCHE_ID
--------------------------------------------------------
CREATE INDEX EMD_EMAIL_PREFS_TR
    ON HOMEPAGE.EMD_EMAIL_PREFS (TRANCHE_ID);
    
GO

----------------------------------------------------------
-- drop not null constraint
----------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_ENTRIES_ACT ALTER COLUMN ITEM_ID nvarchar(36);

GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_BLG ALTER COLUMN ITEM_ID nvarchar(36);

GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_COM ALTER COLUMN ITEM_ID nvarchar(36);

GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_WIK ALTER COLUMN ITEM_ID nvarchar(36);

GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_PRF ALTER COLUMN ITEM_ID nvarchar(36);

GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_HP ALTER COLUMN ITEM_ID nvarchar(36);

GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_DGR ALTER COLUMN ITEM_ID nvarchar(36);

GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_FILE ALTER COLUMN ITEM_ID nvarchar(36);

GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_FRM ALTER COLUMN ITEM_ID nvarchar(36);

GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_EXTERNAL ALTER COLUMN ITEM_ID nvarchar(36);


GO

--------------------------------------------
-- Add IS_GADGET 
--------------------------------------------
ALTER TABLE HOMEPAGE.WIDGET ADD IS_GADGET NUMERIC(5,0) DEFAULT 0;

GO


UPDATE HOMEPAGE.WIDGET SET IS_GADGET = 0;

----------------------------------------------------
-- Adding OPERATION_ID to NR_ACTIONABLE_READERS
----------------------------------------------------
ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS ADD OPERATION_ID nvarchar(512);
   
GO