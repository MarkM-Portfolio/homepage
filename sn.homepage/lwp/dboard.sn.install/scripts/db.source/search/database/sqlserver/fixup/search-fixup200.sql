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
-- SR_ECM_DOCUMENT_TYPE_LABELS
----------------------------------------


--START 74517: Create script that creates DB table that hold document type labels

CREATE TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS(
        LABEL_ID NVARCHAR(36) NOT NULL,
        LABEL_NAME NVARCHAR(256) NOT NULL,
        DOCUMENT_TYPES  NVARCHAR(MAX) NOT NULL,
        UPDATE_TIME  DATETIME NOT NULL
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS
    ADD CONSTRAINT PK_LABEL_ID PRIMARY KEY (LABEL_ID)
GO

ALTER TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS
    ADD CONSTRAINT UNIQUE_LABEL_NAME UNIQUE (LABEL_NAME)
GO

--END 74517: Create script that creates DB table that hold document type labels

--START Task 78083 Prepare DB table for storing post filtering service.

----------------------------------------
-- SR_POST_FILTERING_SERVICE
----------------------------------------

CREATE TABLE HOMEPAGE.SR_POST_FILTERING_SERVICE(
    PFS_ID NVARCHAR(36) NOT NULL,
    SERVICE_NAME NVARCHAR(36) NOT NULL,
    URL NVARCHAR(2048) NOT NULL
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.SR_POST_FILTERING_SERVICE
   ADD CONSTRAINT PFS_ID PRIMARY KEY (PFS_ID)
GO


ALTER TABLE HOMEPAGE.SR_POST_FILTERING_SERVICE
    ADD CONSTRAINT UNIQUE_SERVICE_NAME UNIQUE (SERVICE_NAME);
GO

--END Task 78083 Prepare DB table for storing post filtering service.

--START Defect 77766: Search slow query
CREATE INDEX SR_INDEX_DOCS_SU_IDX 
	ON  HOMEPAGE.SR_INDEX_DOCS(SERVICE,UPDATE_TIME)
GO 
--END Defect 77766: Search slow query

--START SmartCloud Defect 96370
CREATE INDEX SR_INDEX_DOCS_TAW_IDX 
	ON  HOMEPAGE.SR_INDEX_DOCS(UPDATE_TIME ASC,DOCUMENT_ID ASC,CRAWLING_VERSION ASC)
GO 
--END SmartCloud Defect 96370

---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
