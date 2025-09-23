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

		
CREATE INDEX "HOMEPAGE"."SR_FEEDBACK_PERSON_IDX" 
		ON  HOMEPAGE.SR_FEEDBACK(PERSON_ID) TABLESPACE "HOMEPAGEINDEXTABSPACE";
COMMIT;		

ALTER TABLE HOMEPAGE.SR_FILESCONTENT DROP COLUMN CONTENT;
COMMIT;

DELETE FROM HOMEPAGE.SR_INDEX_MANAGEMENT;
COMMIT;

ALTER TABLE HOMEPAGE.SR_INDEX_MANAGEMENT
ADD OUT_OF_SYNC NUMBER(5, 0) DEFAULT 0 NOT NULL
MODIFY OUT_OF_DATE DEFAULT 0;

COMMIT;

---------------------------------------------------------------------------------
---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
