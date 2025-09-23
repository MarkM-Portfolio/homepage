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

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start	FIXUP 211
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


------------------------------------------------
-- INCLUDE FIX UP 211 FOR HP
------------------------------------------------

{include.news-fixup211.sql}


------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 211
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 211, RELEASEVER = '4.5.0.0' 
WHERE   DBSCHEMAVER = 210; 

------------------------------------------------------------------------------------------------

COMMIT;
