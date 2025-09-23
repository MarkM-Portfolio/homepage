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
-- INCLUDE UPGRADE30 FOR HP
------------------------------------------------

{include.hp-upgrade30b.sql}

------------------------------------------------
-- INCLUDE UPGRADE30 FOR NEWS 
------------------------------------------------

{include.news-upgrade30b.sql}


------------------------------------------------
-- INCLUDE UPGRADE30 FOR SEARCH
------------------------------------------------

{include.search-upgrade30.sql}


------------------------------------------------
-- CLEAR SCHEDULERS
------------------------------------------------

{include.news-clearScheduler.sql}

{include.search-clearScheduler.sql}


------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 56
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 56 , RELEASEVER = '3.0.0.0'
WHERE   DBSCHEMAVER = 21; 


--------------------------------------
-- COMMIT
--------------------------------------
COMMIT;
--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;