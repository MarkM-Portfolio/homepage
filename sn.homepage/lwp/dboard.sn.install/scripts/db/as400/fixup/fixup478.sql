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

--CONNECT TO HOMEPAGE;
SET CURRENT SCHEMA HOMEPAGE;


{include.news-fixup478.sql}




------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 478
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 478, RELEASEVER = '5.0.0.0 CR2' 
WHERE   DBSCHEMAVER = 477;

------------------------------------------------------------------------------------------------




COMMIT;

--------------------------------------
-- FLUSH
--------------------------------------
--FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
--connect reset;
--terminate;