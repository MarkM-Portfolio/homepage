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
ALTER TABLE HOMEPAGE.HOMEPAGE_SCHEMA ADD PRESCHEMAVER nvarchar(10) DEFAULT '0' NOT NULL;
ALTER TABLE HOMEPAGE.HOMEPAGE_SCHEMA ADD POSTSCHEMAVER nvarchar(10) DEFAULT '0' NOT NULL;
GO

-----------------------------------------------------------------------
-- CREATE NEW INDEX FOR NR_AGGREGATED_READERS
-----------------------------------------------------------------------
CREATE INDEX MPM_AGG_READERS_1B 
	ON HOMEPAGE.NR_AGGREGATED_READERS (READER_ID, CATEGORY_TYPE, IS_VISIBLE, USE_IN_ROLLUP, ORGANIZATION_ID, CREATION_DATE, SOURCE_TYPE);
GO