-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2015                                   
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- 5724_S68                                              

CONNECT TO HOMEPAGE@

-- -----------------------------------------------------------------
-- Task 166639:
-- -----------------------------------------------------------------

DROP INDEX HOMEPAGE.NR_ENTRIES_ITEM@
COMMIT@

CREATE INDEX HOMEPAGE.NR_ENTRIES_ITEMORG
 	ON HOMEPAGE.NR_ENTRIES(ITEM_ID, ORGANIZATION_ID)@ 
COMMIT@

-- -----------------------------------------------------------------
-- Defect 164873: is not meant for Cloud hence we copy content
-- of news-fixup704 here instead of including it as it contains 
-- script for defect 164873
-- -----------------------------------------------------------------

-- -----------------------------------------------------------------
-- Task 163650:
-- -----------------------------------------------------------------

ALTER TABLE HOMEPAGE.L3T_EXT_BINDS ADD COLUMN ENABLED SMALLINT DEFAULT 0 NOT NULL@

-- -----------------------------------------------------------------
-- Defect 153043:
-- -----------------------------------------------------------------

CREATE INDEX HOMEPAGE.NR_CUSTOM_LIST_ITEM_CRTIME_IDX 
 	ON HOMEPAGE.NR_CUSTOM_LIST_ITEM(CREATION_TIME)@ 

COMMIT@

-- -----------------------------------------------------------------
-- Defect 165365:
-- -----------------------------------------------------------------

-- not required under DB2

-- -----------------------------------------------------------------
-- Defect 167926:
-- -----------------------------------------------------------------

CREATE INDEX HOMEPAGE.L3T_PACKAGE_DETAILS_LABSID_IDX 
	ON HOMEPAGE.L3T_PACKAGE_DETAILS (LABS_ID)@
CREATE INDEX HOMEPAGE.L3T_PACKAGE_LOCATN_LABSID_IDX 
	ON HOMEPAGE.L3T_PACKAGE_LOCATION (LABS_ID)@

COMMIT@

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 704
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 704, RELEASEVER = '5.5.0.0' 
WHERE   DBSCHEMAVER = 703@


------------------------------------------------------------------------------------------------




COMMIT@

--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC@

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset@
terminate@