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


----------------------------------------
--  SR_ECM_DOCUMENT_TYPE_LABELS 
----------------------------------------


--START Task 74517: Create script that creates DB table that hold document type labels

CREATE TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS(
    LABEL_ID VARCHAR(36) NOT NULL,
    LABEL_NAME VARCHAR(256) NOT NULL,
    DOCUMENT_TYPES CLOB NOT NULL,
    UPDATE_TIME  TIMESTAMP NOT NULL
)
IN HOMEPAGETABSPACE;

ALTER TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS
         ADD CONSTRAINT "PK_LABEL_ID" PRIMARY KEY ("LABEL_ID");

ALTER TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS
        ADD CONSTRAINT UNIQUE_LABEL_NAME UNIQUE ("LABEL_NAME");


RUNSTATS ON TABLE "HOMEPAGE"."SR_ECM_DOCUMENT_TYPE_LABELS";
RUNSTATS ON TABLE "HOMEPAGE"."SR_ECM_DOCUMENT_TYPE_LABELS" FOR INDEXES ALL;

--END Task 74517: Create script that creates DB table that hold document type labels

----------------------------------------
--  SR_POST_FILTERING_SERVICE
----------------------------------------
--START Task 78083 Prepare DB table for storing post filtering service.
CREATE TABLE HOMEPAGE.SR_POST_FILTERING_SERVICE(
    PFS_ID VARCHAR(36) NOT NULL,
    SERVICE_NAME VARCHAR(36) NOT NULL,
    URL VARCHAR(2048) NOT NULL
)
IN HOMEPAGETABSPACE;

ALTER TABLE HOMEPAGE.SR_POST_FILTERING_SERVICE
         ADD CONSTRAINT "PK_PFS_ID" PRIMARY KEY ("PFS_ID");

ALTER TABLE HOMEPAGE.SR_POST_FILTERING_SERVICE
        ADD CONSTRAINT UNIQUE_SERVICE_NAME UNIQUE ("SERVICE_NAME");

RUNSTATS ON TABLE "HOMEPAGE"."SR_POST_FILTERING_SERVICE";
RUNSTATS ON TABLE "HOMEPAGE"."SR_POST_FILTERING_SERVICE" FOR INDEXES ALL;

--END Task 78083 Prepare DB table for storing post filtering service.


--START Defect 77766: Search slow query
CREATE INDEX HOMEPAGE.SR_INDEX_DOCS_SUID_IDX ON  HOMEPAGE.SR_INDEX_DOCS (SERVICE,UPDATE_TIME);
-- END Defect 77766: Search slow query 

--START SmartCloud Defect 96370
CREATE INDEX HOMEPAGE.SR_INDEX_DOCS_TAW_IDX ON  HOMEPAGE.SR_INDEX_DOCS (UPDATE_TIME ASC,DOCUMENT_ID ASC,CRAWLING_VERSION ASC);
--END SmartCloud Defect 96370

RUNSTATS ON TABLE "HOMEPAGE"."SR_INDEX_DOCS";
RUNSTATS ON TABLE "HOMEPAGE"."SR_INDEX_DOCS" FOR INDEXES ALL;
---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
