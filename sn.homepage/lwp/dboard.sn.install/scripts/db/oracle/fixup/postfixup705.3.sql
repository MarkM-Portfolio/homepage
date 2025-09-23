-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2016                              
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- 5724_S68

{include.news-postfixup705.3.sql}
{include.search-postfixup705.3.sql}

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 705, POSTSCHEMAVER = '705.3', RELEASEVER = '5.5.0.0' 
WHERE   DBSCHEMAVER = 705;


------------------------------------------------------------------------------------------------




COMMIT;


--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;