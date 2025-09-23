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
-- INCLUDE PREDBXFER30 FOR SEARCH
------------------------------------------------

{include.search-predbxfer30.sql}

------------------------------------------------
-- INCLUDE PREDBXFER30 FOR NEWS
------------------------------------------------

{include.news-predbxfer30.sql}

------------------------------------------------
-- INCLUDE PREDBXFER30 FOR HP
------------------------------------------------

{include.hp-predbxfer30.sql}

COMMIT;