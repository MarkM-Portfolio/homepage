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

--------------------------------------
-- CREATE THE DATABASE
--------------------------------------
CREATE DATABASE HOMEPAGE ON PRIMARY
	( NAME = 'HOMEPAGE', FILENAME = '$(filepath)\HOMEPAGE.mdf', SIZE = 100MB, FILEGROWTH = 20MB )
	LOG ON
	( NAME = 'HOMEPAGE_log', FILENAME = '$(filepath)\HOMEPAGE_log.mdf', SIZE = 20MB, FILEGROWTH = 2MB ) COLLATE LATIN1_GENERAL_BIN
GO

USE HOMEPAGE;
GO
ALTER DATABASE HOMEPAGE SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
ALTER DATABASE HOMEPAGE SET READ_COMMITTED_SNAPSHOT ON;
ALTER DATABASE HOMEPAGE SET ALLOW_SNAPSHOT_ISOLATION ON;
ALTER DATABASE HOMEPAGE SET MULTI_USER;
GO

--------------------------------------
-- CONNECT TO HOMEPAGE
--------------------------------------
USE HOMEPAGE
GO

--------------------------------------
-- CREATE THE SCHEMA
-- The CREATE SCHEMA command must be the first statement in a query batch, that is, must right after GO command
--------------------------------------
CREATE SCHEMA HOMEPAGE
GO

--------------------------------------
-- CREATE HOMEPAGE USER
--------------------------------------
USE HOMEPAGE
GO
CREATE ROLE HOMEPAGE_ROLE
CREATE LOGIN HOMEPAGEUSER WITH PASSWORD = '$(password)', DEFAULT_DATABASE = HOMEPAGE

CREATE USER HOMEPAGEUSER FOR LOGIN HOMEPAGEUSER WITH DEFAULT_SCHEMA = HOMEPAGE
GO

--------------------------------------
-- CREATE THE TABLES
--------------------------------------
BEGIN TRANSACTION
GO

CREATE TABLE HOMEPAGE.HOMEPAGE_SCHEMA
(
	COMPKEY nvarchar(36) NOT NULL,
	DBSCHEMAVER int NOT NULL
)  ON [PRIMARY]
GO

CREATE TABLE HOMEPAGE.PERSON  (
	USER_ID nvarchar(256) NOT NULL,
	USER_MAIL nvarchar(256) NOT NULL,		
	LAST_VISIT datetime NOT NULL,
	WELCOME_MODE NUMERIC(5, 0) NOT NULL DEFAULT 1,
) ON [PRIMARY]
GO

CREATE TABLE HOMEPAGE.USER_WIDGET_PREF  (
	USER_WIDGET_PREF_ID nvarchar(36) NOT NULL,
	USER_ID nvarchar(256) NOT NULL,
	WIDGET_ID nvarchar(36) NOT NULL,
	PAGE_ID nvarchar(36),
	WIDGET_SETTING nvarchar(2048),
	MAX_MIN NUMERIC(5, 0) NOT NULL,
	ROW_NUM int,
	COL_NUM int
) ON [PRIMARY]
GO

CREATE TABLE HOMEPAGE.WIDGET (
		WIDGET_ID nvarchar(36) NOT NULL,
		WIDGET_TITLE nvarchar(256) NOT NULL,
		WIDGET_TEXT nvarchar(256),
		WIDGET_URL nvarchar(256) NOT NULL,
		WIDGET_ICON nvarchar(256),
		WIDGET_ENABLED NUMERIC(5, 0) NOT NULL DEFAULT 0,
		WIDGET_SYSTEM NUMERIC(5, 0) NOT NULL DEFAULT 0,
		WIDGET_HOMEPAGE_SPECIFIC NUMERIC(5, 0) NOT NULL DEFAULT 0,
) ON [PRIMARY]
GO

CREATE TABLE HOMEPAGE.PREREQ (
		PREREQ_ID nvarchar(36) NOT NULL,
		APP_ID nvarchar(36) NOT NULL,
		WIDGET_ID nvarchar(36) NOT NULL
) ON [PRIMARY]
GO

--------------------------------------
-- CREATE THE CONSTRAINTS
--------------------------------------
ALTER TABLE HOMEPAGE.PERSON
   ADD CONSTRAINT PK_USER_ID PRIMARY KEY (USER_ID)
GO

ALTER TABLE HOMEPAGE.USER_WIDGET_PREF
    ADD CONSTRAINT PK_USER_FP_ID PRIMARY KEY (USER_WIDGET_PREF_ID)
GO

ALTER TABLE HOMEPAGE.WIDGET 
	ADD CONSTRAINT PK_WIDGET PRIMARY KEY (WIDGET_ID)
GO

ALTER TABLE HOMEPAGE.PREREQ 
	ADD CONSTRAINT PK_PREREQ PRIMARY KEY (PREREQ_ID)
GO

ALTER TABLE HOMEPAGE.USER_WIDGET_PREF
    ADD CONSTRAINT FK_USER_ID FOREIGN KEY (USER_ID)
	REFERENCES HOMEPAGE.PERSON(USER_ID)
GO

ALTER TABLE HOMEPAGE.USER_WIDGET_PREF
    ADD CONSTRAINT FK_WIDGET_ID FOREIGN KEY (WIDGET_ID)
	REFERENCES HOMEPAGE.WIDGET (WIDGET_ID)
GO

ALTER TABLE HOMEPAGE.PREREQ 
	ADD CONSTRAINT FK_PREREQ_WIDGET FOREIGN KEY (WIDGET_ID)
	REFERENCES HOMEPAGE.WIDGET (WIDGET_ID)
	ON DELETE CASCADE
GO
--------------------------------------
-- CREATE THE INDEXES
--------------------------------------

CREATE INDEX USERID_WIDGET_UNIQUE
	ON HOMEPAGE.USER_WIDGET_PREF (USER_ID ASC, WIDGET_ID ASC);
GO
	
--------------------------------------
-- POPULATE THE TABLES
--------------------------------------
INSERT INTO HOMEPAGE.HOMEPAGE_SCHEMA ( COMPKEY, DBSCHEMAVER) VALUES ( 'HOMEPAGE', 3)
GO

--------------------------------------
-- GRANT TO HOMEPAGE USER
--------------------------------------
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.HOMEPAGE_SCHEMA TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.PERSON TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.USER_WIDGET_PREF TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.WIDGET TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.PREREQ TO HOMEPAGEUSER
GO

--------------------------------------
-- DISCONNECT
--------------------------------------
COMMIT
