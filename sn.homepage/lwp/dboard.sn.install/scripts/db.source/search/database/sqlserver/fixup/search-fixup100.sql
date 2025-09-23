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


CREATE INDEX SR_FEEDBACK_PERSON_ID_IDX 
	ON HOMEPAGE.SR_FEEDBACK(PERSON_ID)
GO

ALTER TABLE HOMEPAGE.SR_FILESCONTENT DROP COLUMN CONTENT
GO

DELETE FROM HOMEPAGE.SR_INDEX_MANAGEMENT
GO

ALTER TABLE HOMEPAGE.SR_INDEX_MANAGEMENT 
ADD OUT_OF_SYNC NUMERIC(5,0) DEFAULT 0 NOT NULL
GO

---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
