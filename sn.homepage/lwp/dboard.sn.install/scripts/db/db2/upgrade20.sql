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
-- Script to upgrade Home Page database
-- from Beta 1 to Connections 2.0
----------------------------------------------------

CONNECT TO HOMEPAGE;

ALTER TABLE HOMEPAGE.USER_WIDGET_PREF
   ADD SHOW_DISABLED_WIDGETS SMALLINT DEFAULT 0 NOT NULL;
   
UPDATE HOMEPAGE.HOMEPAGE_SCHEMA
   SET   DBSCHEMAVER = 5
   WHERE COMPKEY = 'HOMEPAGE';

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

