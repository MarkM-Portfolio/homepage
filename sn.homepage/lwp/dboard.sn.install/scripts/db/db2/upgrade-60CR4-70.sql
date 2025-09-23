-- ***************************************************************** 
--                                                                   
-- HCL Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright HCL Corp. 2020, 2020                              
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                        
--                                                                   
-- ***************************************************************** 

connect to HOMEPAGE@

------------------------------------------------

{include.hp-postfixup710.9.sql}

UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 710, POSTSCHEMAVER = '710.9', RELEASEVER = '7.0.0.0'
WHERE   DBSCHEMAVER = 710@

--------------------------------------
-- COMMIT
--------------------------------------

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