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

{include.hp-postfixup710.4.sql}
{include.hp-postfixup710.5.sql}
{include.news-postfixup710.5.sql} -- addition of NR_READ_STATUS index
{include.hp-postfixup710.6.sql}
{include.news-postfixup710.6.sql}

GO

UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 710, POSTSCHEMAVER = '710.6', RELEASEVER = '6.0.0.0CR2'
WHERE   DBSCHEMAVER = 710;

--------------------------------------
-- COMMIT
--------------------------------------

GO

COMMIT;
GO