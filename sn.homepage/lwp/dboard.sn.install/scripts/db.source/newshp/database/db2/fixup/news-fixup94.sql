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

COMMIT;

----------------------------------------------------------------
-- ADDING BOARD_OBJECT_REFERENCE
----------------------------------------------------------------
CREATE TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE (
	OBJECT_ID VARCHAR(47) NOT NULL,
	ENTRY_ID VARCHAR(47) NOT NULL,
	DISPLAY_NAME VARCHAR(2048),
	IMAGE_NAME   VARCHAR(2048),
	NAME VARCHAR(512), 
	URL VARCHAR(1024), 
	CONTENT VARCHAR(4000),
	IMAGE_URL VARCHAR(1024),
	SOURCE  VARCHAR(36),
	SOURCE_TYPE  SMALLINT,
	CREATION_DATE TIMESTAMP,
	MIME_TYPE VARCHAR(36),
	AUTHOR_ID VARCHAR(36),
	AUTHOR_DISPLAY_NAME VARCHAR(256),
	JSON_META_DATA VARCHAR(4000)
)
IN BOARD16TABSPACE;

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE 
    ADD CONSTRAINT PK_BRD_OBJ_ID PRIMARY KEY(OBJECT_ID);

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE
	ADD CONSTRAINT FK_BRD_OBJ_ENTRY FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.BOARD_ENTRIES (ENTRY_ID);

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE
	ADD CONSTRAINT FK_BRD_AUTHOR_ID FOREIGN KEY (AUTHOR_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

CREATE INDEX HOMEPAGE.BRD_ENTRY_IDX
    ON HOMEPAGE.BOARD_OBJECT_REFERENCE (ENTRY_ID);

CREATE INDEX HOMEPAGE.BRD_AUTHOR_IDX
    ON HOMEPAGE.BOARD_OBJECT_REFERENCE (AUTHOR_ID);     
	
COMMIT;

-----------------------------------------------------
-- [START] reorg TABLES JUST DB2
-----------------------------------------------------
reorg table HOMEPAGE.BOARD_ENTRIES;
commit;
reorg indexes all for table  HOMEPAGE.BOARD_ENTRIES;
commit;

reorg table HOMEPAGE.BOARD_COMMENTS;
commit;
reorg indexes all for table  HOMEPAGE.BOARD_COMMENTS;
commit;

reorg table HOMEPAGE.BOARD_OBJECT_REFERENCE;
commit;

reorg indexes all for table  HOMEPAGE.BOARD_OBJECT_REFERENCE;
commit;

reorg table HOMEPAGE.BOARD_RECOMMENDATIONS;
commit;

reorg indexes all for table  HOMEPAGE.BOARD_RECOMMENDATIONS;
commit;

reorg table HOMEPAGE.BOARD_CURRENT_STATUS;
commit;

reorg indexes all for table  HOMEPAGE.BOARD_CURRENT_STATUS;
commit;

reorg table HOMEPAGE.BOARD;
commit;

reorg indexes all for table  HOMEPAGE.BOARD;
commit;

-----------------------------------------------------
-- [END] reorg TABLES JUST DB2
-----------------------------------------------------

------------------------------------------------------------
--
------------------------------------------------------------
DROP TABLE HOMEPAGE.NR_SOURCE_TYPE;

------------------------------------------------
-- NR_SOURCE_TYPE
------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SOURCE_TYPE (
	SOURCE_TYPE_ID VARCHAR(36) NOT NULL,
	SOURCE_TYPE SMALLINT NOT NULL, -- numeric that is 1,2,3 etc.. 100, 101..
	EXTERNAL_ID VARCHAR(256) NOT NULL,
	DISPLAY_NAME VARCHAR(4000),
	IMAGE_URL VARCHAR(2048),
	PUBLISHED TIMESTAMP,
	UPDATED TIMESTAMP,	
	IS_ENABLED SMALLINT,
	SUMMARY VARCHAR (4000),
	ORGANIZATION_ID VARCHAR(36),
	URL VARCHAR(2048),
	URL_SSL VARCHAR(2048)
)	
IN NEWS32TABSPACE;

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
  	ADD CONSTRAINT PK_SRC_TYPE_ID PRIMARY KEY(SOURCE_TYPE_ID);

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
	ADD CONSTRAINT SRC_TYPE_UNQ UNIQUE(SOURCE_TYPE);

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
	ADD CONSTRAINT SRC_EXT_ID_UNQ UNIQUE(EXTERNAL_ID);
	
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
CREATE INDEX HOMEPAGE.NR_SRC_STORIES_ACT_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_ACT (EVENT_RECORD_UUID, ENTRY_ID);  

commit;
    
CREATE INDEX HOMEPAGE.NR_SRC_STORIES_BLG_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_BLG (EVENT_RECORD_UUID, ENTRY_ID);

commit;
    
CREATE INDEX HOMEPAGE.NR_SRC_STORIES_COM_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_COM (EVENT_RECORD_UUID, ENTRY_ID);

commit;

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_WIK_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_WIK (EVENT_RECORD_UUID, ENTRY_ID);

commit;


CREATE INDEX HOMEPAGE.NR_SRC_STORIES_PRF_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_PRF (EVENT_RECORD_UUID, ENTRY_ID);

commit;
    
CREATE INDEX HOMEPAGE.NR_SRC_STORIES_HP_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_HP (EVENT_RECORD_UUID, ENTRY_ID);

commit;
    
CREATE INDEX HOMEPAGE.NR_SRC_STORIES_DGR_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_DGR (EVENT_RECORD_UUID, ENTRY_ID);

commit;
    
CREATE INDEX HOMEPAGE.NR_SRC_STORIES_FILE_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_FILE (EVENT_RECORD_UUID, ENTRY_ID);

commit;
    
CREATE INDEX HOMEPAGE.NR_SRC_STORIES_FRM_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_FRM (EVENT_RECORD_UUID, ENTRY_ID);

commit;
    
CREATE INDEX HOMEPAGE.NR_SRC_STORIES_EXT_ER_UUID
    ON HOMEPAGE.NR_SRC_STORIES_EXTERNAL (EVENT_RECORD_UUID, ENTRY_ID);    

commit;

------------------------------------------------------
--[END]: perf defect 40892 
------------------------------------------------------

---------------------------------------------------------------
-- Add the flag SCOPE to story table
---------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_ACT ADD SCOPE SMALLINT;
   
commit;
    
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_BLG ADD SCOPE SMALLINT;

commit;
    
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_COM ADD SCOPE SMALLINT;

commit;

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_WIK ADD SCOPE SMALLINT;

commit;

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_PRF  ADD SCOPE SMALLINT;

commit;
    
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_HP  ADD SCOPE SMALLINT;

commit;
    
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_DGR  ADD SCOPE SMALLINT;

commit;
    
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_FILE  ADD SCOPE SMALLINT;

commit;
    
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_FRM  ADD SCOPE SMALLINT;

commit;
    
ALTER TABLE HOMEPAGE.NR_SRC_STORIES_EXTERNAL  ADD SCOPE SMALLINT; 

commit;

-----------------------------------------------------------------------------
-------------------------- [START] db2 reorg --------------------------------
-----------------------------------------------------------------------------

reorg table HOMEPAGE.NR_SRC_STORIES_ACT use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_ACT;

reorg table HOMEPAGE.NR_SRC_STORIES_BLG use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_BLG;

reorg table HOMEPAGE.NR_SRC_STORIES_COM use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_COM;

reorg table HOMEPAGE.NR_SRC_STORIES_WIK use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_WIK;

reorg table HOMEPAGE.NR_SRC_STORIES_PRF use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_PRF;

reorg table HOMEPAGE.NR_SRC_STORIES_HP use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_HP;

reorg table HOMEPAGE.NR_SRC_STORIES_DGR use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_DGR;

reorg table HOMEPAGE.NR_SRC_STORIES_FILE use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_FILE;

reorg table HOMEPAGE.NR_SRC_STORIES_FRM use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_FRM;

reorg table HOMEPAGE.NR_SRC_STORIES_EXTERNAL  use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_EXTERNAL;

-----------------------------------------------------------------------------
-------------------------- [END] db2 reorg ----------------------------------
-----------------------------------------------------------------------------

-----------------------------------------------------
-- Renaming existing index
-----------------------------------------------------
DROP INDEX HOMEPAGE.HP_UI_UNQ;

CREATE UNIQUE INDEX HOMEPAGE.HP_UI_PERSONID
	ON HOMEPAGE.HP_UI (PERSON_ID);
	
COMMIT;	

--------------------------------------------------------
-- Adding an index on EMD_EMAIL_PREFS TRANCHE_ID
--------------------------------------------------------
CREATE INDEX HOMEPAGE.EMD_EMAIL_PREFS_TR
    ON HOMEPAGE.EMD_EMAIL_PREFS (TRANCHE_ID);
    
COMMIT;

----------------------------------------------------------
-- drop not null constraint
----------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_ENTRIES_ACT ALTER COLUMN ITEM_ID DROP NOT NULL;

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES_BLG ALTER COLUMN ITEM_ID DROP NOT NULL;

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES_COM ALTER COLUMN ITEM_ID DROP NOT NULL;

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES_WIK ALTER COLUMN ITEM_ID DROP NOT NULL;

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES_PRF ALTER COLUMN ITEM_ID DROP NOT NULL;

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES_HP ALTER COLUMN ITEM_ID DROP NOT NULL;

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES_DGR ALTER COLUMN ITEM_ID DROP NOT NULL;

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES_FILE ALTER COLUMN ITEM_ID DROP NOT NULL;

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES_FRM ALTER COLUMN ITEM_ID DROP NOT NULL;

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES_EXTERNAL ALTER COLUMN ITEM_ID DROP NOT NULL;

COMMIT;

reorg table HOMEPAGE.NR_ENTRIES_ACT;
reorg table HOMEPAGE.NR_ENTRIES_BLG;
reorg table HOMEPAGE.NR_ENTRIES_COM;
reorg table HOMEPAGE.NR_ENTRIES_WIK;
reorg table HOMEPAGE.NR_ENTRIES_PRF;
reorg table HOMEPAGE.NR_ENTRIES_HP;
reorg table HOMEPAGE.NR_ENTRIES_DGR;
reorg table HOMEPAGE.NR_ENTRIES_FILE;
reorg table HOMEPAGE.NR_ENTRIES_FRM;
reorg table HOMEPAGE.NR_ENTRIES_EXTERNAL;

commit;

--------------------------------------------
-- Add IS_GADGET 
--------------------------------------------
ALTER TABLE HOMEPAGE.WIDGET ADD IS_GADGET SMALLINT DEFAULT 0;

COMMIT;

reorg table HOMEPAGE.WIDGET;

UPDATE HOMEPAGE.WIDGET SET IS_GADGET = 0;

----------------------------------------------------
-- Adding OPERATION_ID to NR_ACTIONABLE_READERS
----------------------------------------------------
ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS ADD OPERATION_ID VARCHAR(512);
   
commit;

reorg table HOMEPAGE.NR_ACTIONABLE_READERS;

commit;

    