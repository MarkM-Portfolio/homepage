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



{include.hp-fixup471.sql}




------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 470
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 471, RELEASEVER = '5.0.0.0' 
WHERE   DBSCHEMAVER = 470; 

------------------------------------------------------------------------------------------------



COMMIT;


--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;