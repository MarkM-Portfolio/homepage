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

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start HP FIXUP 30
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 30
------------------------------------------------------------------------------------------------

-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 3.0.0
-- UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 30 , RELEASEVER = '3.0.0'
-- WHERE   DBSCHEMAVER = 23;
-- GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start HP FIXUP 31
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

---------------------------------------------------------------------------------
------------------------ START HOMEPAGE ---------------------------------------------
---------------------------------------------------------------------------------

------------------------------------------------
-- PERSON
------------------------------------------------


ALTER TABLE HOMEPAGE.PERSON
	ADD IS_ACTIVE NUMERIC(5,0) DEFAULT 1;
GO

ALTER TABLE HOMEPAGE.PERSON
	ADD USER_MAIL_LOWER nvarchar(256);
GO

UPDATE HOMEPAGE.PERSON SET USER_MAIL_LOWER =  lower(USER_MAIL);
GO

ALTER TABLE HOMEPAGE.PERSON
	ADD DISPLAYNAME_LOWER nvarchar(256);	
GO

UPDATE HOMEPAGE.PERSON SET DISPLAYNAME_LOWER =  lower(DISPLAYNAME);
GO

-- in 2.5 it didn't exist
--DROP VIEW HOMEPAGE.SNCORE_PERSON;
--GO

CREATE INDEX PERSON_USER_MAIL_LWR
    ON HOMEPAGE.PERSON(USER_MAIL_LOWER ASC);
GO

CREATE INDEX PERSON_DISPLAYNAME_LWR
    ON HOMEPAGE.PERSON(DISPLAYNAME_LOWER ASC);
GO

------------------------------------------------
-- SNCORE_PERSON
------------------------------------------------

CREATE VIEW HOMEPAGE.SNCORE_PERSON (SNC_INTERNAL_ID, SNC_IDKEY, SNC_EMAIL_LOWER, SNC_DISPLAY_NAME) 
    AS SELECT PERSON_ID, EXID, USER_MAIL_LOWER, DISPLAYNAME FROM HOMEPAGE.PERSON;		
GO

------------------------------------------------
-- HP_TAB
------------------------------------------------

ALTER TABLE HOMEPAGE.HP_TAB
	ADD ENABLED NUMERIC(5,0) DEFAULT 1;
GO


---------------------------------------------------------------------------------
---------------- END UPDATE HOMEPAGE DATABASE ----------------    
--------------------------------------------------------------


------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 31
------------------------------------------------------------------------------------------------
-- UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 31 , RELEASEVER = '3.0.0'
-- WHERE   DBSCHEMAVER = 30;
-- GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start HP FIXUP 34
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
{include.hp-fixup34_b.sql}
GO


-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start HP FIXUP 35
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
{include.hp-fixup35.sql}
GO


-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start HP FIXUP 36
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
{include.hp-fixup36.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start HP FIXUP 37
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
{include.hp-fixup37.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start HP FIXUP 38
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
{include.hp-fixup38.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start HP FIXUP 41
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
{include.hp-fixup41.sql}
GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start HP FIXUP 43
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
{include.hp-fixup43.sql}
GO
