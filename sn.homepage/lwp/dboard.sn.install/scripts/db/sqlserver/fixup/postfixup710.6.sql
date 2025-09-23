-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2016, 2017                      
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 
                                              
USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO

------------------------------------------------------------------------------------------------

{include.hp-postfixup710.6.sql} 	-- SQL Server changes DATETIME to DATETIME2(3) only
{include.news-postfixup710.6.sql} 	-- SQL Server changes DATETIME to DATETIME2(3) only

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 710, POSTSCHEMAVER = '710.6', RELEASEVER = '6.0.0.0CR2' 
WHERE   DBSCHEMAVER = 710;

------------------------------------------------------------------------------------------------

GO


COMMIT