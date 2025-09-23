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
-- INCLUDE UPGRADE30 FOR HP
------------------------------------------------

{include.hp-upgrade30a.sql}

------------------------------------------------
-- INCLUDE UPGRADE30 FOR NEWS 
------------------------------------------------

{include.news-upgrade30a.sql}

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