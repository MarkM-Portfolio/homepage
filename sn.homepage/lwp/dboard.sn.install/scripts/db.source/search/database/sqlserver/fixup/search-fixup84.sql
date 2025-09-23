-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2011, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

---------------------------------------------------------------------------------
------------------------ START SEARCH -------------------------------------------
---------------------------------------------------------------------------------

--START 34215: Implement and unit test changes to DAO layer for per-document seedlist retrieval

ALTER TABLE HOMEPAGE.SR_INDEX_DOCS 
	ADD ATOM_ID NVARCHAR(256) NULL
GO

CREATE INDEX SR_INDEX_DOCS_ACT_IDX
	ON HOMEPAGE.SR_INDEX_DOCS(ACTION)
GO

CREATE INDEX SR_INDEX_DOCS_ACS_IDX
	ON HOMEPAGE.SR_INDEX_DOCS(SERVICE,ATOM_ID,CRAWLING_VERSION)
GO

ALTER TABLE HOMEPAGE.SR_INDEX_DOCS 
	ADD CONSTRAINT ID_ACT_CHECK
	CHECK (ACTION >= 0 AND ACTION < 4) 
GO

ALTER TABLE HOMEPAGE.SR_INDEX_DOCS 
	ADD CONSTRAINT IGNORE_ACT_CHECK
	CHECK (ACTION <> 3 OR RESUME_POINT IS NULL) 
GO

--END  34215: Implement and unit test changes to DAO layer for per-document seedlist retrieval

---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------