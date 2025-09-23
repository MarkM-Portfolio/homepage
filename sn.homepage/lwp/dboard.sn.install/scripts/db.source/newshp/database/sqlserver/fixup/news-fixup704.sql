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

-- {COPYRIGHT}

-- -----------------------------------------------------------------
-- Task 166639:
-- -----------------------------------------------------------------

DROP INDEX NR_ENTRIES_ITEM ON HOMEPAGE.NR_ENTRIES;
GO

CREATE UNIQUE INDEX NR_ENTRIES_ITEMORG 
 	ON HOMEPAGE.NR_ENTRIES(ITEM_ID, ORGANIZATION_ID); 
GO

-- -----------------------------------------------------------------
-- Defect 164873:
-- -----------------------------------------------------------------

-- not required under SQLServer

-- -----------------------------------------------------------------
-- Task 163650:
-- -----------------------------------------------------------------

ALTER TABLE HOMEPAGE.L3T_EXT_BINDS ADD ENABLED NUMERIC(5,0) NOT NULL DEFAULT 0;
GO

-- -----------------------------------------------------------------
-- Defect 153043:
-- -----------------------------------------------------------------

CREATE UNIQUE INDEX NR_CUSTOM_LIST_ITEM_CRTIME_IDX 
 	ON HOMEPAGE.NR_CUSTOM_LIST_ITEM(CREATION_TIME); 
GO

-- -----------------------------------------------------------------
-- Defect 165365:
-- -----------------------------------------------------------------

CREATE INDEX MWC_AGG_READERS_1B
    ON HOMEPAGE.NR_AGGREGATED_READERS (ROLLUP_ENTRY_ID, CATEGORY_TYPE, READER_ID, USE_IN_ROLLUP, ORGANIZATION_ID, CREATION_DATE);
GO

-- -----------------------------------------------------------------
-- Defect 167926:
-- -----------------------------------------------------------------

CREATE INDEX L3T_PACKAGE_DETAILS_LABSID_IDX 
	ON HOMEPAGE.L3T_PACKAGE_DETAILS (LABS_ID);
GO

CREATE INDEX L3T_PACKAGE_LOCATN_LABSID_IDX 
	ON HOMEPAGE.L3T_PACKAGE_LOCATION (LABS_ID);
GO
