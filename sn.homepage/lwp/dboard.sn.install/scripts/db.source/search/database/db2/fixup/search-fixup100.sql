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

CREATE INDEX HOMEPAGE.SR_FEEDBACK_PERSON_ID_IDX ON HOMEPAGE.SR_FEEDBACK (PERSON_ID);
COMMIT;

ALTER TABLE HOMEPAGE.SR_FILESCONTENT 
DROP COLUMN CONTENT;
COMMIT;

DELETE FROM HOMEPAGE.SR_INDEX_MANAGEMENT;
COMMIT;

ALTER TABLE HOMEPAGE.SR_INDEX_MANAGEMENT
ADD COLUMN OUT_OF_SYNC SMALLINT NOT NULL DEFAULT 0;
COMMIT;

reorg table HOMEPAGE.SR_FILESCONTENT use TEMPSPACE1;
reorg indexes all for table HOMEPAGE.SR_FILESCONTENT;

reorg table HOMEPAGE.SR_INDEX_MANAGEMENT use TEMPSPACE1;
reorg indexes all for table HOMEPAGE.SR_INDEX_MANAGEMENT;

RUNSTATS ON TABLE "HOMEPAGE"."SR_FILESCONTENT";
RUNSTATS ON TABLE "HOMEPAGE"."SR_FILESCONTENT" FOR INDEXES ALL;

RUNSTATS ON TABLE "HOMEPAGE"."SR_INDEX_MANAGEMENT";
RUNSTATS ON TABLE "HOMEPAGE"."SR_INDEX_MANAGEMENT" FOR INDEXES ALL;

---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
