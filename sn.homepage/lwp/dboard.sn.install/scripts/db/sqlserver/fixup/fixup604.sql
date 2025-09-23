-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2001, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- 5724_S68                                              
USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO


{include.news-fixup604.sql}



------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 604
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 604, RELEASEVER = '6.0.0.0' 
WHERE   DBSCHEMAVER = 603;

------------------------------------------------------------------------------------------------


GO


COMMIT