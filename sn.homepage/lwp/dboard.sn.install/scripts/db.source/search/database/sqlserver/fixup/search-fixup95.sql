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

DROP TABLE HOMEPAGE.SR_FACET_DOCS
GO

--46592: Rename FILESCONTENT_ID in HOMEPAGE.SR_INDEX_DOCS to FILE_COMPONENT_UUID

DROP TABLE HOMEPAGE.SR_INDEX_DOCS
GO

CREATE TABLE HOMEPAGE.SR_INDEX_DOCS(
	DOCUMENT_ID NVARCHAR(36) NOT NULL,
	DOCUMENT VARBINARY(MAX) NOT NULL,
	CRAWLING_VERSION NUMERIC(19,0) NOT NULL,
	ACTION NUMERIC(5,0) NOT NULL,
	UPDATE_TIME  DATETIME NOT NULL,
	RESUME_POINT NVARCHAR(256),
	SERVICE NVARCHAR(36) NOT NULL,
	FILES_REF_ID NVARCHAR(36),
	ATOM_ID  NVARCHAR(256)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.SR_INDEX_DOCS
	ADD CONSTRAINT PK_INDEX_DOCS_ID PRIMARY KEY (DOCUMENT_ID)
GO	

CREATE INDEX SR_INDEX_DOCS_CRAWL_VERSION_IDX
	ON HOMEPAGE.SR_INDEX_DOCS (CRAWLING_VERSION)
GO

CREATE INDEX SR_INDEX_DOCS_RPS_IDX
    ON HOMEPAGE.SR_INDEX_DOCS (RESUME_POINT,SERVICE)
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

--START 40269: PERF, homepage db,  this read sql needs index on sr_index_docs
CREATE INDEX SR_INDEX_DOCS_LLT4_IDX ON HOMEPAGE.SR_INDEX_DOCS(UPDATE_TIME ASC, ACTION DESC, CRAWLING_VERSION, SERVICE)
GO


---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------


