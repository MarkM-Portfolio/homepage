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
CONNECT TO HOMEPAGE;

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start	FIXUP 463
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

--------------------------------------
{include.hp-fixup463.sql}
--------------------------------------

-- 103573: Reset Id for Mettings Gadget in Widget table
UPDATE HOMEPAGE.HP_WIDGET_TAB 
	SET WIDGET_TAB_ID = '1143431a-6700-4201-a93b-d425151d1234' 
	WHERE WIDGET_ID = '1520aa1-c2fa-48ef-be05-8dee630c0054' AND TAB_ID = '_panel.updatex4a43x82aaxb00187218631';
	
COMMIT;


------------------------------------------------------------------------
--
-------------------------------------------------------------------------

CREATE INDEX HOMEPAGE.TMP_IDX1 ON HOMEPAGE.NR_ENTRIES(LAST_AUTHOR_COMMENT);
COMMIT;

CREATE INDEX HOMEPAGE.TMP_IDX2 ON HOMEPAGE.NR_ENTRIES(PREV_AUTHOR_COMMENT);
COMMIT;

runstats on table HOMEPAGE.NR_ENTRIES with distribution and detailed indexes all allow write access;
COMMIT;

CREATE TABLE HOMEPAGE.TEMP (
	TEMP_ENTRY_ID VARCHAR(36) NOT NULL
);
COMMIT;

CREATE INDEX HOMEPAGE.TEMP_IDX ON HOMEPAGE.TEMP(TEMP_ENTRY_ID);
COMMIT;

INSERT INTO HOMEPAGE.TEMP (
	SELECT ENTRY_ID 
	FROM (
		SELECT LAST_AUTHOR_COMMENT AUTHOR FROM HOMEPAGE.NR_ENTRIES
		EXCEPT
		SELECT PERSON_ID AUTHOR FROM HOMEPAGE.PERSON
	) TEMP, HOMEPAGE.NR_ENTRIES NR_ENTRIES	
	WHERE TEMP.AUTHOR = NR_ENTRIES.LAST_AUTHOR_COMMENT
);
COMMIT;

UPDATE HOMEPAGE.NR_ENTRIES NR_ENTRIES 
SET 
	LAST_COMMENT_ID = NULL, 
	LAST_DATE_COMMENT = NULL, 
	LAST_AUTHOR_COMMENT = NULL, 
	LAST_DESC_COMMENT = NULL, 
	LAST_UPDATE_DATE_COMMENT = NULL, 
	LAST_UPDATE_RECORD = NULL,
	LAST_COMMENT_NUM_REC = NULL,
	IS_LAST_COMMENT_PUBLIC = 0,
	IS_LAST_COMMENT_VISIBLE =0
WHERE EXISTS (SELECT 1 FROM HOMEPAGE.TEMP AS TEMP WHERE NR_ENTRIES.ENTRY_ID = TEMP.TEMP_ENTRY_ID);
COMMIT;

TRUNCATE TABLE HOMEPAGE.TEMP IMMEDIATE;
COMMIT;

INSERT INTO HOMEPAGE.TEMP (
	SELECT ENTRY_ID 
	FROM (
		SELECT PREV_AUTHOR_COMMENT AUTHOR FROM HOMEPAGE.NR_ENTRIES
		EXCEPT
		SELECT PERSON_ID AUTHOR FROM HOMEPAGE.PERSON
	) TEMP, HOMEPAGE.NR_ENTRIES NR_ENTRIES	
	WHERE TEMP.AUTHOR = NR_ENTRIES.PREV_AUTHOR_COMMENT
);
COMMIT;

UPDATE HOMEPAGE.NR_ENTRIES NR_ENTRIES 
SET 
	PREV_COMMENT_ID = NULL, 
	PREV_DATE_COMMENT = NULL, 
	PREV_AUTHOR_COMMENT = NULL, 
	PREV_DESC_COMMENT = NULL, 
	PREV_UPDATE_DATE_COMMENT = NULL, 
	PREV_COMMENT_NUM_REC = NULL,
	IS_PREV_COMMENT_PUBLIC = 0,
	IS_PREV_COMMENT_VISIBLE =0
WHERE EXISTS (SELECT 1 FROM HOMEPAGE.TEMP AS TEMP WHERE NR_ENTRIES.ENTRY_ID = TEMP.TEMP_ENTRY_ID);
COMMIT;

DROP TABLE HOMEPAGE.TEMP;
COMMIT;

DROP INDEX HOMEPAGE.TMP_IDX1;
COMMIT;

DROP INDEX HOMEPAGE.TMP_IDX2;
COMMIT;

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 463
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 463, RELEASEVER = '4.6.0.0' 
WHERE   DBSCHEMAVER = 462; 

------------------------------------------------------------------------------------------------

COMMIT;

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					end	FIXUP 463
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;


--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate;