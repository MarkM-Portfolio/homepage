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
--  SR_ECM_DOCUMENT_TYPE_PROPS
----------------------------------------


CREATE TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_PROPS(
	PROPERTIES_ID VARCHAR2(36) NOT NULL,
	DOCUMENT_TYPE_ID VARCHAR2(256) NOT NULL,
	LOCALE VARCHAR2(10) NOT NULL,	
	PROPERTIES CLOB NOT NULL
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_PROPS
         ADD (CONSTRAINT "PK_PROPERTIES_ID" PRIMARY KEY ("PROPERTIES_ID")
         USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");

--END 74517: Create script that creates DB table that hold document type labels

--START Defect 80084: Rename IS_CURRENT Field to STATUS.

DROP INDEX  HOMEPAGE.SR_FILESCONTENT_IS_CURRENT_IDX;

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	RENAME COLUMN IS_CURRENT TO STATUS;

CREATE INDEX "HOMEPAGE"."SR_FILESCONTENT_STATUS_IDX" 
	ON HOMEPAGE.SR_FILESCONTENT(STATUS) TABLESPACE "HOMEPAGEINDEXTABSPACE"; 
 
--END Defect 80084: Rename IS_CURRENT Field to STATUS.
         
         

---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
