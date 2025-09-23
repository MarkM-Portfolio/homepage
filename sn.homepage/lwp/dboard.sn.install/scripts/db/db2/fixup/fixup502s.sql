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

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup502s - start' FROM SYSIBM.SYSDUMMY1@


{include.news-fixup502.sql}

-- SC env. already have run fixup500s.sql. 
-- So what was included late in fixup500s.sql never got run.
-- To have a consistent solution on SC we are going to remove for ever the include from fixup500s and move it to fixup502s.sql 
{include.search-fixup500.sql}




------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 502
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 502, RELEASEVER = '6.0.0.0' 
WHERE   DBSCHEMAVER = 501@

------------------------------------------------------------------------------------------------

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [END] fixup502s - end' FROM SYSIBM.SYSDUMMY1@


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