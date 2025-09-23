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
-- INCLUDE PREDBXFER30 FOR SEARCH
------------------------------------------------

{include.search-postdbxfer30.sql}

------------------------------------------------
-- INCLUDE PREDBXFER30 FOR NEWS
------------------------------------------------

{include.news-postdbxfer30.sql}

------------------------------------------------
-- INCLUDE PREDBXFER30 FOR HP
------------------------------------------------

{include.hp-postdbxfer30.sql}


COMMIT;
CONNECT RESET;