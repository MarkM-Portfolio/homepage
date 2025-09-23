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
CONNECT TO HOMEPAGE;

------------------------------------------------
-- INCLUDE PREDBXFER40 FOR SEARCH
------------------------------------------------

{include.search-predbxfer40.sql}

------------------------------------------------
-- INCLUDE PREDBXFER35 FOR NEWS
------------------------------------------------

{include.news-predbxfer40.sql}

------------------------------------------------
-- INCLUDE PREDBXFER35 FOR HP
------------------------------------------------

{include.hp-predbxfer40.sql}


COMMIT;
CONNECT RESET;