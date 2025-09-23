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

--START 85055: ecm files schema change:  add to "SR_FILECONTENT" table a new column named "VERSION". 

----------------------------------------
--  SR_FILESCONTENT
----------------------------------------


ALTER TABLE HOMEPAGE.SR_FILESCONTENT
ADD VERSION NVARCHAR(36);	
GO

CREATE INDEX SR_FILESCONTENT_VERSION_IDX 
	ON HOMEPAGE.SR_FILESCONTENT(VERSION);
GO

--END 85055: ecm files schema change:  add to "SR_FILECONTENT" table a new column named "VERSION". 


---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
