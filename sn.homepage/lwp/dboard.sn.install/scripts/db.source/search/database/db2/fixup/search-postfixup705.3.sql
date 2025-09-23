-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2016                                   
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

-- -----------------------------------------------------------------
-- OCS Task 192084
-- -----------------------------------------------------------------

DROP INDEX HOMEPAGE.SR_INDEX_CRAWL_VERSION_IDX@

CREATE INDEX HOMEPAGE.SR_INDEX_CRAWL_SRV_ACT_IDX 
	ON HOMEPAGE.SR_INDEX_DOCS (CRAWLING_VERSION, SERVICE, ACTION) 
	ALLOW REVERSE SCANS PAGE SPLIT SYMMETRIC COLLECT UNSAMPLED DETAILED STATISTICS COMPRESS YES@

-- -----------------------------------------------------------------
-- IC Task 173849
-- -----------------------------------------------------------------

DROP TABLE HOMEPAGE.SR_RECOMMEND_CACHE_USERPROFILE@
