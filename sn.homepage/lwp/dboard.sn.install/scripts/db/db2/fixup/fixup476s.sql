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
CONNECT TO HOMEPAGE@

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup476s - start' FROM SYSIBM.SYSDUMMY1@

{include.news-fixup476.sql}




------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 476
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 476, RELEASEVER = '6.0.0.0' 
WHERE   DBSCHEMAVER = 475@

------------------------------------------------------------------------------------------------


SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [END] fixup476s - end' FROM SYSIBM.SYSDUMMY1@

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