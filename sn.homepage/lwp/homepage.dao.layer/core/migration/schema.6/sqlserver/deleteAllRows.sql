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

USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO

-- HOMEPAGE_SCHEMA
DELETE FROM HOMEPAGE.HOMEPAGE_SCHEMA;
GO

-- LOGINNAME
DELETE FROM HOMEPAGE.LOGINNAME;
GO

-- USER_WIDGET_PREF
DELETE FROM HOMEPAGE.USER_WIDGET_PREF;
GO

-- WIDGET
DELETE FROM HOMEPAGE.WIDGET;
GO

-- PREREQ
DELETE FROM HOMEPAGE.PREREQ;
GO

-- PERSON
DELETE FROM HOMEPAGE.PERSON;
GO

COMMIT;
