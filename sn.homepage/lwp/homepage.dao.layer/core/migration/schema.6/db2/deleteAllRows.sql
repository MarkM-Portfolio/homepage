-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2009, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- REMOVING RECORDS -----------------
CONNECT TO HOMEPAGE;

-- HOMEPAGE_SCHEMA
DELETE FROM HOMEPAGE.HOMEPAGE_SCHEMA;

-- LOGINNAME
DELETE FROM HOMEPAGE.LOGINNAME;

-- USER_WIDGET_PREF
DELETE FROM HOMEPAGE.USER_WIDGET_PREF;

-- WIDGET
DELETE FROM HOMEPAGE.WIDGET;

-- PREREQ
DELETE FROM HOMEPAGE.PREREQ;

-- PERSON
DELETE FROM HOMEPAGE.PERSON;

CONNECT RESET;
DISCONNECT ALL;
