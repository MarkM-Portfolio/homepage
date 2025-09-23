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
-- START: NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- 109943: Rename ENTRY_ID column in BOARD_OBJECT_REFERENCE
DROP INDEX HOMEPAGE.BRD_ENTRY_IDX;
COMMIT;

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE DROP CONSTRAINT FK_BRD_OBJ_ENTRY;
COMMIT;

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE RENAME COLUMN ENTRY_ID TO ITEM_ID;
COMMIT;

CREATE INDEX HOMEPAGE.BRD_ENTRY_IDX
    ON HOMEPAGE.BOARD_OBJECT_REFERENCE (ITEM_ID) TABLESPACE NEWSINDEXTABSPACE;
COMMIT;


---------------------------------------------------------------------
-- Adding Columns HOMEPAGE.NR_ENTRIES HOMEPAGE.NR_ENTRIES_ARCHIVE
---------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_ENTRIES
       	ADD	JSON_META_DATA_TEMP CLOB 	LOB (JSON_META_DATA_TEMP) STORE AS (TABLESPACE NEWSLOBTABSPACE ENABLE STORAGE IN ROW CHUNK 480 CACHE );
COMMIT;


 DECLARE CURSOR cur IS SELECT ENTRY_ID, JSON_META_DATA FROM HOMEPAGE.NR_ENTRIES;
              BEGIN

                        FOR rec IN cur LOOP
                                UPDATE HOMEPAGE.NR_ENTRIES SET JSON_META_DATA_TEMP = rec.JSON_META_DATA WHERE ENTRY_ID = rec.ENTRY_ID;
                        END LOOP;
                        COMMIT;
                END;
 /
COMMIT;


ALTER TABLE HOMEPAGE.NR_ENTRIES
		DROP COLUMN JSON_META_DATA;
COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES
		RENAME COLUMN JSON_META_DATA_TEMP TO JSON_META_DATA;
COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES
	ADD	PREV_COMMENT_OBJECT_META_DATA CLOB 	LOB (PREV_COMMENT_OBJECT_META_DATA) STORE AS (TABLESPACE NEWSLOBTABSPACE ENABLE STORAGE IN ROW CHUNK 480 CACHE );
	
ALTER TABLE HOMEPAGE.NR_ENTRIES
	ADD	LAST_COMMENT_OBJECT_META_DATA CLOB 	LOB (LAST_COMMENT_OBJECT_META_DATA) STORE AS (TABLESPACE NEWSLOBTABSPACE ENABLE STORAGE IN ROW CHUNK 480 CACHE );	
	
ALTER TABLE HOMEPAGE.NR_ENTRIES
	ADD	LAST_COMMENT_TAGS VARCHAR2(1024);
	
ALTER TABLE HOMEPAGE.NR_ENTRIES
	ADD	PREV_COMMENT_TAGS VARCHAR2(1024);
	

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE
       	ADD	JSON_META_DATA_TEMP CLOB 	LOB (JSON_META_DATA_TEMP) STORE AS (TABLESPACE NEWSLOBTABSPACE ENABLE STORAGE IN ROW CHUNK 480 CACHE );
COMMIT;


 DECLARE CURSOR cur IS SELECT ENTRY_ID, JSON_META_DATA FROM HOMEPAGE.NR_ENTRIES_ARCHIVE; 
              BEGIN 
	                	
                        FOR rec IN cur LOOP 
                        	UPDATE HOMEPAGE.NR_ENTRIES_ARCHIVE SET JSON_META_DATA_TEMP = rec.JSON_META_DATA WHERE ENTRY_ID = rec.ENTRY_ID; 
                        END LOOP; 
                        COMMIT; 
                END; 
 / 
COMMIT;


ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE
		DROP COLUMN JSON_META_DATA;
COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE
		RENAME COLUMN JSON_META_DATA_TEMP TO JSON_META_DATA;
COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE
	ADD	PREV_COMMENT_OBJECT_META_DATA CLOB 	LOB (PREV_COMMENT_OBJECT_META_DATA) STORE AS (TABLESPACE NEWSLOBTABSPACE ENABLE STORAGE IN ROW CHUNK 480 CACHE );
	
ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE
	ADD	LAST_COMMENT_OBJECT_META_DATA CLOB 	LOB (LAST_COMMENT_OBJECT_META_DATA) STORE AS (TABLESPACE NEWSLOBTABSPACE ENABLE STORAGE IN ROW CHUNK 480 CACHE );		
	
ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE
	ADD	LAST_COMMENT_TAGS VARCHAR2(1024);
	
ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE
	ADD	PREV_COMMENT_TAGS VARCHAR2(1024);
	
COMMIT;

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- 111032: Backport to 4.5CR4: [Must for August] [Performance|DB2] ActivityStream search indexing is very slow (90881)
-- Changes based on news-fixup457.sql
DROP INDEX HOMEPAGE.NR_SL_UD_STR_DELETED_VIS;
COMMIT;

CREATE INDEX HOMEPAGE.NR_SL_UD_DELETED_VIS
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC, IS_DELETED, IS_VISIBLE) TABLESPACE "NEWSINDEXTABSPACE";
COMMIT;
	
CREATE INDEX HOMEPAGE.NR_SL_UD_DELETED
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC) TABLESPACE "NEWSINDEXTABSPACE";
COMMIT;	
	
CREATE INDEX HOMEPAGE.NR_SL_CD_DELETED
	ON HOMEPAGE.NR_AS_SEEDLIST (CREATION_DATE DESC) TABLESPACE "NEWSINDEXTABSPACE";	
COMMIT;	
	
CREATE INDEX HOMEPAGE.NR_SL_UD_STR
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE ASC, STORY_ID) TABLESPACE "NEWSINDEXTABSPACE";
COMMIT;

-- moving two existing indexes to the righ tabspace (JUST ORACLE)
DROP INDEX HOMEPAGE.NR_SL_STR_UNIQUE;
COMMIT;

DROP INDEX HOMEPAGE.NR_SL_ITEM_ID_IX;
COMMIT;

CREATE UNIQUE INDEX HOMEPAGE.NR_SL_STR_UNIQUE
	ON HOMEPAGE.NR_AS_SEEDLIST(STORY_ID) TABLESPACE "NEWSINDEXTABSPACE";	
COMMIT;

CREATE INDEX HOMEPAGE.NR_SL_ITEM_ID_IX
	ON HOMEPAGE.NR_AS_SEEDLIST (ITEM_ID) TABLESPACE "NEWSINDEXTABSPACE";	
COMMIT;
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
--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;
