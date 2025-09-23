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
------------------------ START SEARCH 103 									------ 
---------------------------------------------------------------------------------

--[START search]
DROP INDEX SR_FEEDBACK_CLIENT_IDX ON HOMEPAGE.SR_FEEDBACK;
GO

--
ALTER TABLE HOMEPAGE.SR_FEEDBACK 
	ALTER COLUMN CLIENT_ID  NVARCHAR(256) NOT NULL;
GO
	
ALTER TABLE HOMEPAGE.SR_FEEDBACK 
	ALTER COLUMN ACTION  NVARCHAR(256) NOT NULL;
GO
	
ALTER TABLE HOMEPAGE.SR_FEEDBACK 
	ALTER COLUMN ITEM_ID  NVARCHAR(256) NOT NULL;
GO	

ALTER TABLE HOMEPAGE.SR_FEEDBACK_CONTEXT  
	ALTER COLUMN TYPE  NVARCHAR(256) NOT NULL;
GO
	
ALTER TABLE HOMEPAGE.SR_FEEDBACK_CONTEXT  
	ALTER COLUMN TYPE_VALUE  NVARCHAR(256) NOT NULL;
GO
	
ALTER TABLE HOMEPAGE.SR_FEEDBACK_CONTEXT  
	ALTER COLUMN WEIGHT  NVARCHAR(256) NOT NULL;
GO

ALTER TABLE HOMEPAGE.SR_FEEDBACK_PARAMETERS  
	ALTER COLUMN PARAM  NVARCHAR(256) NOT NULL;
GO
	
ALTER TABLE HOMEPAGE.SR_FEEDBACK_PARAMETERS  
	ALTER COLUMN PARAM_VALUE  NVARCHAR(256) NOT NULL;
GO


CREATE INDEX SR_FEEDBACK_CLIENT_IDX
		ON HOMEPAGE.SR_FEEDBACK (CLIENT_ID);
GO	
--[END search]

---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------