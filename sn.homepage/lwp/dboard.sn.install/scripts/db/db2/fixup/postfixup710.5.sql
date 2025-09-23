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

{include.hp-postfixup710.5.sql} -- WidgetCatalog updates
{include.news-postfixup710.5.sql} -- addition of NR_READ_STATUS index

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 710, POSTSCHEMAVER = '710.5', RELEASEVER = '6.0.0.0CR2' 
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