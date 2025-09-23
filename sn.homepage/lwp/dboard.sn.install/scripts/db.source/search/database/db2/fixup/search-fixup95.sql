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

DROP TABLE HOMEPAGE.SR_FACET_DOCS;

DROP TABLE HOMEPAGE.SR_INDEX_DOCS;

CREATE TABLE HOMEPAGE.SR_INDEX_DOCS(
	DOCUMENT_ID VARCHAR(36) NOT NULL,
	DOCUMENT BLOB NOT NULL,
	CRAWLING_VERSION BIGINT NOT NULL,
	ACTION SMALLINT NOT NULL,
	UPDATE_TIME  TIMESTAMP NOT NULL,
	RESUME_POINT VARCHAR(256),
	SERVICE VARCHAR(36) NOT NULL,
	FILES_REF_ID VARCHAR(36),
	ATOM_ID  VARCHAR(256)
)
IN HOMEPAGETABSPACE;


ALTER TABLE HOMEPAGE.SR_INDEX_DOCS
	ADD CONSTRAINT "PK_INDEX_DOCS_ID" PRIMARY KEY ("DOCUMENT_ID");

	
CREATE INDEX HOMEPAGE.SR_INDEX_CRAWL_VERSION_IDX
	ON HOMEPAGE.SR_INDEX_DOCS (CRAWLING_VERSION);

CREATE INDEX HOMEPAGE.SR_INDEX_DOCS_RPS_IDX  
	ON HOMEPAGE.SR_INDEX_DOCS (RESUME_POINT,SERVICE);
	
CREATE INDEX HOMEPAGE.SR_INDEX_DOCS_ACT_IDX
	ON HOMEPAGE.SR_INDEX_DOCS(ACTION);
	
CREATE INDEX HOMEPAGE.SR_INDEX_DOCS_ACS_IDX
	ON HOMEPAGE.SR_INDEX_DOCS(SERVICE,ATOM_ID,CRAWLING_VERSION);	
	
ALTER TABLE HOMEPAGE.SR_INDEX_DOCS 
	ADD CONSTRAINT "ID_ACT_CHECK"
	CHECK (ACTION >=0 AND ACTION < 4);		
	
ALTER TABLE HOMEPAGE.SR_INDEX_DOCS 
	ADD CONSTRAINT "IGNORE_ACT_CHECK"
	CHECK (ACTION <> 3 OR RESUME_POINT IS NULL); 
	
--40269: PERF, homepage db,  this read sql needs index on sr_index_docs
CREATE INDEX HOMEPAGE.SR_INDEX_DOCS_LLT4_IDX ON HOMEPAGE.SR_INDEX_DOCS (UPDATE_TIME ASC,ACTION DESC ,CRAWLING_VERSION,SERVICE);

RUNSTATS ON TABLE "HOMEPAGE"."SR_INDEX_DOCS";
RUNSTATS ON TABLE "HOMEPAGE"."SR_INDEX_DOCS" FOR INDEXES ALL;
---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------


