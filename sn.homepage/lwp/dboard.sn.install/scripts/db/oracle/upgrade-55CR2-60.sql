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


-- 705.1 not needed as it is already in fixup706
{include.news-postfixup705.2.sql} -- App Registry updates
{include.news-postfixup705.3.sql} -- VM_EVENT_TRACKER and READERS_ROLLUP index improvements
{include.news-postfixup705.4.sql} -- App Registry updates
-- 705.5 not needed as it contains only REORG commands which are already in reorg.sql

{include.search-postfixup705.3.sql} -- Search CRAWL index update

{include.news-postfixup710.1.sql} -- App Registry updates
{include.hp-postfixup710.2.sql} -- Create an index (HOMEPAGE.OH2P_CACHE_DUP) in HOMEPAGE.OH2P_CACHE 

------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 710, POSTSCHEMAVER = '710.2', RELEASEVER = '6.0.0.0' 
WHERE   DBSCHEMAVER = 707; 

--------------------------------------
-- COMMIT
--------------------------------------

COMMIT;

--------------------------------------
-- DISCONNECT
--------------------------------------

DISCONNECT ALL;

QUIT;
