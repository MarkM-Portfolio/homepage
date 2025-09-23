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

-- 5724_S68                                              

CONNECT TO HOMEPAGE@

{include.hp-postfixup705.1.sql}
{include.news-postfixup705.1.sql}

-- PLEASE NOTE: SPECIAL CASE DUE TO NEW ZERO DOWNTIME PROCESS. 
-- 5.5CR1 WAS ALREADY RELEASED TO CUSTOMERS AS DBSCHEMAVER=706. WE CANNOT UPDATE THE DBSCHEMAVER UNTIL WE CAN HANDLE
-- CHANGES TO DBSCHEMAVER POST APP START SO THIS HAS TO BE REPRESENTED AS 705.1 ON CLOUD.

------------------------------------------------------------------------------------------------
-- UPDATE VERSION INFORMATION
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 705, POSTSCHEMAVER = '705.1', RELEASEVER = '5.5.0.0 CR1' 
WHERE   DBSCHEMAVER = 705@


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