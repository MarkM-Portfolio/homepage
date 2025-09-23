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
connect to HOMEPAGE;

------------------------------------------------
-- INCLUDE UPGRADE30 B1 FOR HP
------------------------------------------------

{include.hp-upgrade-25-30b1b.sql}

------------------------------------------------
-- INCLUDE UPGRADE30 B1 FOR NEWS 
------------------------------------------------

{include.news-upgrade-25-30b1b.sql}


------------------------------------------------
-- INCLUDE UPGRADE30 B1 FOR SEARCH
------------------------------------------------

{include.search-upgrade30.sql}


------------------------------------------------
-- CLEAR SCHEDULERS
------------------------------------------------

{include.news-clearScheduler.sql}

{include.search-clearScheduler.sql}


------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 43
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 43 , RELEASEVER = '3.0.0.0'
WHERE   DBSCHEMAVER = 21;

--------------------------------------
-- COMMIT
--------------------------------------

COMMIT;
--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate;