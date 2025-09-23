-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2013, 2015                                    
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

-- On-premise defect 99580
CREATE INDEX SR_INDEX_DOCS_SUDCRA_IDX
	ON HOMEPAGE.SR_INDEX_DOCS (SERVICE ASC, UPDATE_TIME ASC, DOCUMENT_ID ASC, CRAWLING_VERSION ASC, RESUME_POINT ASC, ACTION ASC);
GO
	
---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
