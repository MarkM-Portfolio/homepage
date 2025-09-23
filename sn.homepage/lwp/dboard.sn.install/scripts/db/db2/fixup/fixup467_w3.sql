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



-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start	FIXUP 467
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


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



------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 467
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 467, RELEASEVER = '4.6.0.0' 
WHERE   DBSCHEMAVER = 466@

------------------------------------------------------------------------------------------------


-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					end	FIXUP 466
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

COMMIT@