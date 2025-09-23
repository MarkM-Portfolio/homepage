-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2012, 2015                                    
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

--START 74517: Create script that creates DB table that hold document type labels

----------------------------------------
--  SR_ECM_DOCUMENT_TYPE_LABELS
----------------------------------------


CREATE TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS(
	LABEL_ID VARCHAR2(36) NOT NULL,
	LABEL_NAME VARCHAR2(256) NOT NULL,	
	DOCUMENT_TYPES CLOB NOT NULL,
	UPDATE_TIME  TIMESTAMP NOT NULL
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS
         ADD (CONSTRAINT "PK_LABEL_ID" PRIMARY KEY ("LABEL_ID")
         USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");

ALTER TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS
    ADD CONSTRAINT UNIQUE_LABEL_NAME UNIQUE ("LABEL_NAME");


--END 74517: Create script that creates DB table that hold document type labels

--START Task 78083 Prepare DB table for storing post filtering service.
----------------------------------------
--  SR_POST_FILTERING_SERVICE
----------------------------------------

CREATE TABLE HOMEPAGE.SR_POST_FILTERING_SERVICE(
    PFS_ID VARCHAR2(36) NOT NULL,
    SERVICE_NAME VARCHAR2(36) NOT NULL,
    URL VARCHAR2(2048) NOT NULL
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.SR_POST_FILTERING_SERVICE
         ADD (CONSTRAINT "PK_PFS_ID" PRIMARY KEY ("PFS_ID")
         USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");


ALTER TABLE HOMEPAGE.SR_POST_FILTERING_SERVICE
    ADD CONSTRAINT UNIQUE_SERVICE_NAME UNIQUE ("SERVICE_NAME");

--END Task 78083 Prepare DB table for storing post filtering service.
 

--START Defect 77766: Search slow query 
CREATE INDEX HOMEPAGE.SR_INDEX_DOCS_SU_IDX ON  HOMEPAGE.SR_INDEX_DOCS(SERVICE,UPDATE_TIME) TABLESPACE "HOMEPAGEINDEXTABSPACE";
--END Defect 77766: Search slow query 

--START SmartCloud Defect 96370
CREATE INDEX HOMEPAGE.SR_INDEX_DOCS_TAW_IDX ON  HOMEPAGE.SR_INDEX_DOCS(UPDATE_TIME ASC,DOCUMENT_ID ASC,CRAWLING_VERSION ASC) TABLESPACE "HOMEPAGEINDEXTABSPACE";
--END SmartCloud Defect 96370

ALTER TABLE "HOMEPAGE"."SR_INDEX_DOCS" ENABLE ROW MOVEMENT;




---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
