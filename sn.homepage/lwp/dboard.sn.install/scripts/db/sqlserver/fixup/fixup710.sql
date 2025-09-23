-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2015, 2016                      
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

-- Does nothing as purpose of fixup710 is just to bump the version for Connections 6.0

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION, POSTSCHEMA VERSION AND RELEASE VERSION
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 710, POSTSCHEMAVER = '710.0', RELEASEVER = '6.0.0.0' 
WHERE   DBSCHEMAVER = 707;

------------------------------------------------------------------------------------------------

GO

COMMIT
