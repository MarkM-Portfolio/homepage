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

ALTER TABLE HOMEPAGE.PREREQ DROP CONSTRAINT FK_PREREQ_WIDGET;
GO

ALTER TABLE HOMEPAGE.USER_WIDGET_PREF DROP CONSTRAINT FK_WIDGET_ID;
GO

ALTER TABLE HOMEPAGE.USER_WIDGET_PREF DROP CONSTRAINT FK_USER_ID;
GO

DROP INDEX USERID_WIDGET_UNIQUE ON HOMEPAGE.USER_WIDGET_PREF;
GO

--------------------------------------
-- DISCONNECT
--------------------------------------
COMMIT