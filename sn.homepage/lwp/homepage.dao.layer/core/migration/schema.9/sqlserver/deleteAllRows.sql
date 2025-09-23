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

USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO

DELETE FROM HOMEPAGE.HOMEPAGE_SCHEMA;

DELETE FROM HOMEPAGE.HP_WIDGET_INST;

DELETE FROM HOMEPAGE.HP_TAB_INST;

DELETE FROM HOMEPAGE.HP_UI;

DELETE FROM HOMEPAGE.HP_WIDGET_TAB;

DELETE FROM HOMEPAGE.HP_TAB;

DELETE FROM HOMEPAGE.WIDGET;

DELETE FROM HOMEPAGE.PREREQ;

DELETE FROM HOMEPAGE.LOGINNAME;

DELETE FROM HOMEPAGE.NT_NOTIFICATION_RECIPIENT;

DELETE FROM HOMEPAGE.NT_NOTIFICATION;

DELETE FROM HOMEPAGE.NR_TEMPLATE;

DELETE FROM HOMEPAGE.NR_NEWS_RECORDS;

DELETE FROM HOMEPAGE.NR_SUBSCRIPTION;

DELETE FROM HOMEPAGE.NR_SOURCE;

DELETE FROM HOMEPAGE.PERSON;

COMMIT;
