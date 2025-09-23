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

-- 5724_S68                                              

CONNECT TO HOMEPAGE@

{include.news-postfixup710.1.sql}

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 710, POSTSCHEMAVER = '710.1', RELEASEVER = '5.5.0.0' 
WHERE   DBSCHEMAVER = 710@


------------------------------------------------------------------------------------------------

COMMIT@

--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC@

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset@
terminate@