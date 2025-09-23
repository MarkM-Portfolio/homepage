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


{include.news-fixup601.sql}

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 601
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 601, RELEASEVER = '6.0.0.0' 
WHERE   DBSCHEMAVER = 600;


------------------------------------------------------------------------------------------------




COMMIT;


--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;