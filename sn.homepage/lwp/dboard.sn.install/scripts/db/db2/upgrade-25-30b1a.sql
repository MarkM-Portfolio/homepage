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

-- Need to create a new tabspace

------------------------------------------------
-- INCLUDE UPGRADE30 B1 FOR HP
------------------------------------------------
{include.hp-upgrade-25-30b1a.sql}

------------------------------------------------
-- INCLUDE UPGRADE30 B1 FOR NEWS
------------------------------------------------
{include.news-upgrade-25-30b1a.sql}

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
disconnect current;
stop dbm force;
start dbm;