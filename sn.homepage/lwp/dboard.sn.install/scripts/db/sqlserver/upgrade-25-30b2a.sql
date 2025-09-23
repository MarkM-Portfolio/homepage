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
USE HOMEPAGE
GO

BEGIN TRANSACTION
GO

------------------------------------------------
-- INCLUDE UPGRADE30 FOR NEWS HP
------------------------------------------------

{include.hp-upgrade-25-30b2a.sql}

------------------------------------------------
-- INCLUDE UPGRADE30 FOR NEWS HP
------------------------------------------------

{include.news-upgrade-25-30b2a.sql}


--------------------------------------
-- COMMIT
--------------------------------------

COMMIT;