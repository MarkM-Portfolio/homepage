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

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup501s - start' FROM SYSIBM.SYSDUMMY1@

------------------------------------------
-- 130750: [fixup501.sql] add delete statements for cleanup work
------------------------------------------


-- SC shouldn't need that piece of work


------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 501
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 501, RELEASEVER = '6.0.0.0' 
WHERE   DBSCHEMAVER = 500@

------------------------------------------------------------------------------------------------


SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [END] fixup501s - end' FROM SYSIBM.SYSDUMMY1@

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