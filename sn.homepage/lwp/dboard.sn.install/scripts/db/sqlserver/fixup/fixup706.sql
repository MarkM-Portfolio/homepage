-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2016                            
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

-- hp-fixup706 was already brought to cloud/W3 via hp-postfixup705.1
-- news-fixup706 was already brought to cloud/W3 via news-postfixup705.1

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 706
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 706, RELEASEVER = '5.5.0.0 CR1' 
WHERE   DBSCHEMAVER = 705;

------------------------------------------------------------------------------------------------


GO


COMMIT