-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2008, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

----------------------------------------------------
----------------------------------------------------
-- Script to upgrade Home Page database
-- from schema version 4 to 5
----------------------------------------------------

CONNECT TO HOMEPAGE;

UPDATE HOMEPAGE.HOMEPAGE_SCHEMA
   SET   DBSCHEMAVER = 5
   WHERE COMPKEY = 'HOMEPAGE';

CONNECT RESET;
DISCONNECT ALL;	

