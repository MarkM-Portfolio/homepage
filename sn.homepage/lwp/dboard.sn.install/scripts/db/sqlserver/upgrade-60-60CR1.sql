-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2017, 2017                              
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

------------------------------------------------

{include.hp-postfixup710.3.sql}

------------------------------------------------

UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 710, POSTSCHEMAVER = '710.3', RELEASEVER = '6.0.0.0CR1' 
WHERE   DBSCHEMAVER = 710; 
------------------------------------------------

GO

------------------------------------------------

COMMIT;
GO