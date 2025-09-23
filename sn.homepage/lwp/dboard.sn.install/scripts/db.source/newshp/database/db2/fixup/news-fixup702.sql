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

-----------------------------------------------------------------------
-- Add columns to HOMEPAGE.HOMEPAGE_SCHEMA
-----------------------------------------------------------------------
ALTER TABLE HOMEPAGE.HOMEPAGE_SCHEMA ADD COLUMN PRESCHEMAVER VARCHAR(10) DEFAULT '0' NOT NULL@
ALTER TABLE HOMEPAGE.HOMEPAGE_SCHEMA ADD COLUMN POSTSCHEMAVER VARCHAR(10) DEFAULT '0' NOT NULL@

-----------------------------------------------------------------------
-- CREATE NEW INDEX FOR NR_AGGREGATED_READERS
-----------------------------------------------------------------------
CREATE INDEX HOMEPAGE.MPM_AGG_READERS_1B 
	ON HOMEPAGE.NR_AGGREGATED_READERS (READER_ID, CATEGORY_TYPE, IS_VISIBLE, USE_IN_ROLLUP, ORGANIZATION_ID, CREATION_DATE, SOURCE_TYPE) 
	ALLOW REVERSE SCANS PAGE SPLIT SYMMETRIC COLLECT SAMPLED DETAILED STATISTICS COMPRESS YES INCLUDE NULL KEYS@
COMMIT@
