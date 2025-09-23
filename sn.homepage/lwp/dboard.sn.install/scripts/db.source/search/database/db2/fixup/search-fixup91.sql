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

--START 36202: Modify the indexing and text extraction processes in accordance with designs in Search CDD

DELETE FROM HOMEPAGE.SR_FILESCONTENT;

DELETE FROM HOMEPAGE.SR_FACET_DOCS;

DELETE FROM HOMEPAGE.SR_INDEX_DOCS;

DROP TABLE HOMEPAGE.SR_FILESCONTENT;

CREATE TABLE HOMEPAGE.SR_FILESCONTENT (
	FILESCONTENT_ID VARCHAR(36) NOT NULL,
	URL VARCHAR(256) NOT NULL,
	COMPONENT_UUID VARCHAR(36) NOT NULL,
	COMPONENT VARCHAR(36) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	LAST_MODIFIED_DATE TIMESTAMP NOT NULL,
	LAST_ACCESSED_DATE TIMESTAMP NOT NULL,
	IS_CURRENT SMALLINT NOT NULL,
	CONTENT CLOB(200 K) NOT NULL,
	FILE_SIZE BIGINT NOT NULL,
	CONTENT_SIZE BIGINT NOT NULL,
	INPUT_MIME_TYPE VARCHAR(256) NOT NULL,
	CLAIMED SMALLINT NOT NULL DEFAULT 0,
	CLAIMED_TIME TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	FS_LOCAL_PATH VARCHAR(256) NOT NULL,
	PROCESSOR VARCHAR(36),
	PROCESSOR_STATE BLOB,
	CONTENT_LOCATION VARCHAR(256) NOT NULL,
	IS_READY SMALLINT NOT NULL DEFAULT 0
)
IN HOMEPAGETABSPACE;

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD CONSTRAINT "PK_FILESCONTENT_ID" PRIMARY KEY ("FILESCONTENT_ID");

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD CONSTRAINT UNIQUE_COMP_UUID UNIQUE ("COMPONENT_UUID","COMPONENT");

CREATE INDEX HOMEPAGE.SR_FILESCONTENT_IS_CURRENT_IDX 
	ON HOMEPAGE.SR_FILESCONTENT (IS_CURRENT);

RUNSTATS ON TABLE "HOMEPAGE"."SR_FILESCONTENT";
RUNSTATS ON TABLE "HOMEPAGE"."SR_FILESCONTENT" FOR INDEXES ALL;


reorg table HOMEPAGE.SR_FILESCONTENT use TEMPSPACE1;
reorg indexes all for table HOMEPAGE.SR_FILESCONTENT;

RUNSTATS ON TABLE "HOMEPAGE"."SR_FILESCONTENT";
RUNSTATS ON TABLE "HOMEPAGE"."SR_FILESCONTENT" FOR INDEXES ALL;
	
--END 36202: Modify the indexing and text extraction processes in accordance with designs in Search CDD  


---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------