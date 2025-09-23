-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

USE HOMEPAGE
GO

BEGIN TRANSACTION
GO


{include.news-fixup476.sql}
{include.news-fixup477.sql}

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_CUSTOM_LIST TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_CUSTOM_LIST_ITEM TO HOMEPAGEUSER
GO

------------------------------------------------

UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 477, RELEASEVER = '5.0.0.0 CR1' 
WHERE   DBSCHEMAVER = 475; 

GO


COMMIT;