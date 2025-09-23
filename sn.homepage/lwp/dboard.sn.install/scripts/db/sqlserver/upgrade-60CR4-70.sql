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

USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO

{include.hp-postfixup710.9.sql}


UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 710, POSTSCHEMAVER = '710.9', RELEASEVER = '7.0.0.0'
WHERE   DBSCHEMAVER = 710;

--------------------------------------
-- COMMIT
--------------------------------------

GO

COMMIT;
GO