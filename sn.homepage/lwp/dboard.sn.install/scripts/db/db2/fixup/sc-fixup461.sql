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
CONNECT TO HOMEPAGE;

--122349: [IC 101939] [OCS 1122349] [Workaround in place on daily] DailyB - Homepage application failing with SQL operation exception (122349) ] [Workaround in place on daily] DailyB - Homepage application failing with SQL operation exception 
TRUNCATE HOMEPAGE.LOGINNAME IMMEDIATE;
COMMIT;



------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 461
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 461, RELEASEVER = '4.6.0.0' 
WHERE   DBSCHEMAVER = 460; 

------------------------------------------------------------------------------------------------

COMMIT;

--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;


--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate;