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

--START 36202: Modify the indexing and text extraction processes in accordance with designs in Search CDD

DELETE FROM HOMEPAGE.SR_FILESCONTENT
GO

DELETE FROM HOMEPAGE.SR_FACET_DOCS
GO

DELETE FROM HOMEPAGE.SR_INDEX_DOCS
GO

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
ADD FS_LOCAL_PATH NVARCHAR(256) NOT NULL
GO

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
ADD PROCESSOR NVARCHAR(36)
GO

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
ADD PROCESSOR_STATE VARBINARY(MAX)
GO

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
ADD CONTENT_LOCATION NVARCHAR(256) NOT NULL
GO

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
ADD IS_READY NUMERIC(5,0) NOT NULL
GO
	
--END 36202: Modify the indexing and text extraction processes in accordance with designs in Search CDD  


---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------