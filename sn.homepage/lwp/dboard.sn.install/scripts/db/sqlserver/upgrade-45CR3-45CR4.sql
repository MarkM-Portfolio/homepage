-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2001, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- 5724_S68                                              
USE HOMEPAGE
GO

BEGIN TRANSACTION
GO


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

-- 109943: Rename ENTRY_ID column in BOARD_OBJECT_REFERENCE
DROP INDEX BRD_ENTRY_IDX ON HOMEPAGE.BOARD_OBJECT_REFERENCE;

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE DROP CONSTRAINT FK_BRD_OBJ_ENTRY;
GO

EXEC sp_rename 'HOMEPAGE.BOARD_OBJECT_REFERENCE.ENTRY_ID' , 'ITEM_ID', 'COLUMN';
GO

CREATE INDEX BRD_ENTRY_IDX
    ON HOMEPAGE.BOARD_OBJECT_REFERENCE (ITEM_ID);
GO



ALTER TABLE HOMEPAGE.NR_ENTRIES ADD
		JSON_META_DATA_TEMP NVARCHAR(MAX),
		PREV_COMMENT_OBJECT_META_DATA NVARCHAR(MAX),
		LAST_COMMENT_OBJECT_META_DATA NVARCHAR(MAX),
		LAST_COMMENT_TAGS nvarchar (1024),
		PREV_COMMENT_TAGS nvarchar (1024)
GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE ADD
		JSON_META_DATA_TEMP NVARCHAR(MAX),
		PREV_COMMENT_OBJECT_META_DATA NVARCHAR(MAX),
		LAST_COMMENT_OBJECT_META_DATA NVARCHAR(MAX),
		LAST_COMMENT_TAGS nvarchar (1024),
		PREV_COMMENT_TAGS nvarchar (1024)
GO

COMMIT;

BEGIN TRANSACTION
GO

UPDATE	 HOMEPAGE.NR_ENTRIES
SET    	JSON_META_DATA_TEMP = JSON_META_DATA WHERE JSON_META_DATA IS NOT NULL;
GO

COMMIT;


BEGIN TRANSACTION
GO

UPDATE HOMEPAGE.NR_ENTRIES_ARCHIVE
SET    JSON_META_DATA_TEMP = JSON_META_DATA WHERE JSON_META_DATA IS NOT NULL;

GO
COMMIT;

BEGIN TRANSACTION
GO

ALTER TABLE HOMEPAGE.NR_ENTRIES
		DROP COLUMN JSON_META_DATA;
GO

EXEC sp_rename 'HOMEPAGE.NR_ENTRIES.JSON_META_DATA_TEMP' , 'JSON_META_DATA', 'COLUMN';
GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE
		DROP COLUMN JSON_META_DATA;
GO

EXEC sp_rename 'HOMEPAGE.NR_ENTRIES_ARCHIVE.JSON_META_DATA_TEMP' , 'JSON_META_DATA', 'COLUMN';
GO
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- 111032: Backport to 4.5CR4: [Must for August] [Performance|DB2] ActivityStream search indexing is very slow (90881)
-- Changes based on news-fixup457.sql
DROP INDEX NR_SL_UD_STR_DELETED_VIS ON HOMEPAGE.NR_AS_SEEDLIST;
GO

CREATE INDEX NR_SL_UD_DELETED_VIS
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC, IS_DELETED, IS_VISIBLE);
GO

CREATE INDEX NR_SL_UD_DELETED
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC);
GO

CREATE INDEX NR_SL_CD_DELETED
	ON HOMEPAGE.NR_AS_SEEDLIST (CREATION_DATE DESC);	
GO

CREATE INDEX NR_SL_UD_STR
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE ASC, STORY_ID);
GO

-- Just sql server
CREATE INDEX NR_SL_UD_STR_DELETED_VIS_DESC
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC, STORY_ID, IS_DELETED, IS_VISIBLE);    
GO

CREATE INDEX NR_SL_UD_STR_DELETED_VIS_ASC
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE ASC, STORY_ID, IS_DELETED, IS_VISIBLE);	
GO	
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 213
------------------------------------------------------------------------------------------------

UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 213 , RELEASEVER = '4.5.0.0 CR4'
WHERE   DBSCHEMAVER = 211;

--------------------------------------
-- COMMIT
--------------------------------------

COMMIT;