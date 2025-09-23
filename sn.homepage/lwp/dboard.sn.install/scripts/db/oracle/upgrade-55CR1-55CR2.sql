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


{include.news-fixup707.sql}

------------------------------------------------

UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 707, RELEASEVER = '5.5.0.0 CR2' 
WHERE   DBSCHEMAVER = 706; 

--------------------------------------
-- COMMIT
--------------------------------------

COMMIT;


--------------------------------------
-- DISCONNECT
--------------------------------------

DISCONNECT ALL;

QUIT;
