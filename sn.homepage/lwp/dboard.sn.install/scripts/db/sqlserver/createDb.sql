-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2016, 2017                              
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- 5724_S68                                              
-- CREATE THE DATABASE
--------------------------------------
CREATE DATABASE HOMEPAGE ON PRIMARY
	( NAME = 'HOMEPAGE', FILENAME = '$(filepath)\HOMEPAGE.mdf', SIZE = 100MB, FILEGROWTH = 20MB )
	LOG ON
	( NAME = 'HOMEPAGE_log', FILENAME = '$(filepath)\HOMEPAGE_log.ldf', SIZE = 20MB, FILEGROWTH = 2MB ) COLLATE LATIN1_GENERAL_BIN
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

CREATE TABLE HOMEPAGE.HOMEPAGE_SCHEMA (
	COMPKEY nvarchar(36) NOT NULL,
	DBSCHEMAVER NUMERIC(5,0) NOT NULL,
	PRESCHEMAVER nvarchar(10) DEFAULT '0' NOT NULL,
	POSTSCHEMAVER nvarchar(10) DEFAULT '0' NOT NULL,
	RELEASEVER nvarchar(32) NOT NULL DEFAULT ''
)  ON [PRIMARY]
GO

--------------------------------------
-- STARTING HOMEPAGE TABLES DEFINITIONS
--------------------------------------
{include.hp-createDb.sql}
--------------------------------------
-- END HOMEPAGE TABLES DEFINITIONS
--------------------------------------

--------------------------------------
-- STARTING NEWS TABLES DEFINITIONS
--------------------------------------
{include.news-createDb.sql}
--------------------------------------
-- END NEWS TABLES DEFINITIONS
--------------------------------------

--------------------------------------
-- STARTING SEARCH TABLES DEFINITIONS
--------------------------------------
{include.search-createDb.sql}
--------------------------------------
-- END SEARCH TABLES DEFINITIONS
--------------------------------------

--------------------------------------
-- INSERT THE SCHEMA VERSION
--------------------------------------
INSERT INTO HOMEPAGE.HOMEPAGE_SCHEMA (COMPKEY, DBSCHEMAVER, POSTSCHEMAVER, RELEASEVER)
VALUES ('HOMEPAGE', 710, '710.9', '7.0.0.0');
GO


--------------------------------------
-- DISCONNECT
--------------------------------------
COMMIT
