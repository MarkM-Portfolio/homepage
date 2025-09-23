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

-- news-fixup707 Though no dupe row issue exists for SQLServer, it should not be included to keep the
-- status quo across db engines, as it was not performant enough for DB2, Oracle

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 707
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 707, RELEASEVER = '5.5.0.0 CR2' 
WHERE   DBSCHEMAVER = 706;

------------------------------------------------------------------------------------------------


GO


COMMIT