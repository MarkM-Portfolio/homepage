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
--  SR_ECM_DOCUMENT_TYPE_PROPS
----------------------------------------


--START 77488 Create DB tables and scripts for ecm properties
   
CREATE TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_PROPS(
    PROPERTIES_ID VARCHAR(36) NOT NULL,
    DOCUMENT_TYPE_ID VARCHAR(256) NOT NULL,
    LOCALE VARCHAR(10) NOT NULL,
    PROPERTIES CLOB NOT NULL
)
IN HOMEPAGETABSPACE;

ALTER TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_PROPS
         ADD CONSTRAINT "PK_PROPERTIES_ID" PRIMARY KEY ("PROPERTIES_ID");


RUNSTATS ON TABLE "HOMEPAGE"."SR_ECM_DOCUMENT_TYPE_PROPS";
RUNSTATS ON TABLE "HOMEPAGE"."SR_ECM_DOCUMENT_TYPE_PROPS" FOR INDEXES ALL;

--END Task 77488 Create DB tables and scripts for ecm properties

--START Defect 80084: Rename IS_CURRENT Field to STATUS.

DROP INDEX HOMEPAGE.SR_FILESCONTENT_IS_CURRENT_IDX;

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	RENAME COLUMN IS_CURRENT TO STATUS;
	
CREATE INDEX  HOMEPAGE.SR_FILESCONTENT_STATUS_IDX 
ON HOMEPAGE.SR_FILESCONTENT (STATUS);	
	
REORG TABLE HOMEPAGE.SR_FILESCONTENT ALLOW NO ACCESS;
COMMIT;
RUNSTATS ON TABLE HOMEPAGE.SR_FILESCONTENT  AND INDEXES ALL;
COMMIT;

--END Defect 80084: Rename IS_CURRENT Field to STATUS.


	
---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
