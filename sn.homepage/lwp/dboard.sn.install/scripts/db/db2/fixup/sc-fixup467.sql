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
CONNECT TO HOMEPAGE@

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start	FIXUP 467
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

------------------------------------------------
-- INCLUDE FIX UP 467
------------------------------------------------

-- upgrade-45CR3-45CR4

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- 109943: Rename ENTRY_ID column in BOARD_OBJECT_REFERENCE
DROP INDEX HOMEPAGE.BRD_ENTRY_IDX@
COMMIT@

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE DROP CONSTRAINT FK_BRD_OBJ_ENTRY@
COMMIT@

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE RENAME COLUMN ENTRY_ID TO ITEM_ID@
COMMIT@

CREATE INDEX HOMEPAGE.BRD_ENTRY_IDX
    ON HOMEPAGE.BOARD_OBJECT_REFERENCE (ITEM_ID)@
COMMIT@

REORG TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE@
COMMIT@

---------------------------------------------------------------------
-- Alter Columns HOMEPAGE.NR_ENTRIES HOMEPAGE.NR_ENTRIES_ARCHIVE
---------------------------------------------------------------------

EXPORT TO data.tmp.ixf OF IXF METHOD N (
        ENTRY_ID, JSON_META_DATA
)
 SELECT
       ENTRY_ID, JSON_META_DATA
 FROM HOMEPAGE.NR_ENTRIES @

ALTER TABLE HOMEPAGE.NR_ENTRIES DROP COLUMN JSON_META_DATA@
COMMIT@

ALTER TABLE HOMEPAGE.NR_ENTRIES
        ADD JSON_META_DATA CLOB INLINE LENGTH 800@
COMMIT@

REORG TABLE HOMEPAGE.NR_ENTRIES@
COMMIT@

IMPORT FROM data.tmp.ixf OF IXF METHOD N (
        ENTRY_ID, JSON_META_DATA
) COMMITCOUNT 1000
	INSERT_UPDATE INTO HOMEPAGE.NR_ENTRIES
	        ( 
	        ENTRY_ID, JSON_META_DATA
	        )@ 
COMMIT@

EXPORT TO data.tmp.ixf OF IXF METHOD N (
        ENTRY_ID, JSON_META_DATA
)
 SELECT
       ENTRY_ID, JSON_META_DATA
 FROM HOMEPAGE.NR_ENTRIES_ARCHIVE @

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE DROP COLUMN JSON_META_DATA@
COMMIT@

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE
        ADD JSON_META_DATA CLOB INLINE LENGTH 800@
COMMIT@

REORG TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE@
COMMIT@

IMPORT FROM data.tmp.ixf OF IXF METHOD N (
        ENTRY_ID, JSON_META_DATA
) COMMITCOUNT 1000
	INSERT_UPDATE INTO HOMEPAGE.NR_ENTRIES_ARCHIVE
	        ( 
	        ENTRY_ID, JSON_META_DATA
	        )@ 
COMMIT@


---------------------------------------------------------------------
-- Adding Columns HOMEPAGE.NR_ENTRIES HOMEPAGE.NR_ENTRIES_ARCHIVE
---------------------------------------------------------------------
-- Adding Columns
ALTER TABLE HOMEPAGE.NR_ENTRIES 
		ADD PREV_COMMENT_OBJECT_META_DATA CLOB INLINE LENGTH 800
		ADD LAST_COMMENT_OBJECT_META_DATA CLOB INLINE LENGTH 800
		ADD	LAST_COMMENT_TAGS VARCHAR(1024)
		ADD PREV_COMMENT_TAGS VARCHAR(1024)@
COMMIT@
ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE
		ADD PREV_COMMENT_OBJECT_META_DATA CLOB INLINE LENGTH 800
		ADD LAST_COMMENT_OBJECT_META_DATA CLOB INLINE LENGTH 800
		ADD	LAST_COMMENT_TAGS VARCHAR(1024)
		ADD PREV_COMMENT_TAGS VARCHAR(1024)@
COMMIT@

REORG TABLE HOMEPAGE.NR_ENTRIES@
COMMIT@
REORG TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE@
COMMIT@





------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 467
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 467, RELEASEVER = '5.0.0.0' 
WHERE   DBSCHEMAVER = 466@

------------------------------------------------------------------------------------------------

COMMIT;

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					end	FIXUP 467
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC@


--------------------------------------
-- TERMINATE
--------------------------------------
connect reset@
terminate@