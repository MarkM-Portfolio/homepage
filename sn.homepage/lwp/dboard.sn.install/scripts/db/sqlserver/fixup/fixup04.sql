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
-- from schema version 3 to 4
----------------------------------------------------

USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO

ALTER TABLE HOMEPAGE.USER_WIDGET_PREF
   ADD SHOW_DISABLED_WIDGETS NUMERIC(5, 0) NOT NULL DEFAULT 0;
GO

UPDATE HOMEPAGE.HOMEPAGE_SCHEMA
   SET   DBSCHEMAVER = 4
   WHERE COMPKEY = 'HOMEPAGE';
GO

COMMIT;
