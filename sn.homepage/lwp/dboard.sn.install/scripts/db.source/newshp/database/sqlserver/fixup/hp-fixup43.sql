-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2007, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

UPDATE HOMEPAGE.PERSON SET IS_ACTIVE = 1;

ALTER TABLE HOMEPAGE.PERSON
	ALTER COLUMN IS_ACTIVE NUMERIC(5,0) NOT NULL;

UPDATE HOMEPAGE.HP_TAB SET ENABLED = 1;

-- HOME PAGE
{SQL_GRANT_START} HOMEPAGE.HOMEPAGE_SCHEMA {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.PERSON {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.SNCORE_PERSON {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.LOGINNAME {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.PREREQ {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.WIDGET  {SQL_GRANT_STOP}
GO


{SQL_GRANT_START} HOMEPAGE.HP_UI  {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.HP_TAB  {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.HP_TAB_INST  {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.HP_WIDGET_INST  {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.HP_WIDGET_TAB  {SQL_GRANT_STOP}
GO

{SQL_GRANT_START} HOMEPAGE.NT_NOTIFICATION {SQL_GRANT_STOP}
GO
{SQL_GRANT_START} HOMEPAGE.NT_NOTIFICATION_RECIPIENT {SQL_GRANT_STOP}
GO