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

{include.hp-postfixup710.3.sql} -- Widget Catalog updates

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 710, POSTSCHEMAVER = '710.3', RELEASEVER = '6.0.0.0CR1' 
WHERE   DBSCHEMAVER = 710;

------------------------------------------------------------------------------------------------

COMMIT;

--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;