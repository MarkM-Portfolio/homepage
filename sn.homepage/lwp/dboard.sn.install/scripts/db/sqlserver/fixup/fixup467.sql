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
USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start	FIXUP 467
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

-- upgrade-45CR3-45CR4.sql

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

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES ADD
		JSON_META_DATA_TEMP NVARCHAR(MAX)
GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE ADD
		JSON_META_DATA_TEMP NVARCHAR(MAX)
GO


BEGIN TRANSACTION
GO

UPDATE HOMEPAGE.NR_ENTRIES 
SET    JSON_META_DATA_TEMP = JSON_META_DATA
WHERE  JSON_META_DATA IS NOT NULL;
GO
COMMIT;

BEGIN TRANSACTION

UPDATE HOMEPAGE.NR_ENTRIES_ARCHIVE
SET    JSON_META_DATA_TEMP = JSON_META_DATA
WHERE	JSON_META_DATA IS NOT NULL; 
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

COMMIT;

BEGIN TRANSACTION
GO

ALTER TABLE HOMEPAGE.NR_ENTRIES ADD
		PREV_COMMENT_OBJECT_META_DATA NVARCHAR(MAX),
		LAST_COMMENT_OBJECT_META_DATA NVARCHAR(MAX),
		LAST_COMMENT_TAGS nvarchar (1024),
		PREV_COMMENT_TAGS nvarchar (1024)
GO
ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE ADD
		PREV_COMMENT_OBJECT_META_DATA NVARCHAR(MAX),
		LAST_COMMENT_OBJECT_META_DATA NVARCHAR(MAX),
		LAST_COMMENT_TAGS nvarchar (1024),
		PREV_COMMENT_TAGS nvarchar (1024)
GO

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 467
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 467, RELEASEVER = '4.6.0.0' 
WHERE   DBSCHEMAVER = 466; 

------------------------------------------------------------------------------------------------

GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					end	FIXUP 467
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

COMMIT