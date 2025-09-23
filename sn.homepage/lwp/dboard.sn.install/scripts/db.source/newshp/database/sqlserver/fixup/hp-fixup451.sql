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

----------------------------------------------------
------------------------ START HP FIXUP 451 --------
----------------------------------------------------

-- [Work Item 88850] New: Add constraint to MT_ORGANIZATION table for ORGANIZATION_EXID to be unique [o]
CREATE UNIQUE INDEX UNQ_ORG_EXID
	ON HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_EXID);

-- 87406: Rename MT_METRIC_STAT to METRIC_STAT
ALTER TABLE HOMEPAGE.MT_METRIC_STAT DROP CONSTRAINT PK_METRIC_STAT_ID;
ALTER TABLE HOMEPAGE.MT_METRIC_STAT DROP CONSTRAINT FK_METRIC_STAT_ORG_ID;
DROP INDEX MT_METRICS_IDX ON HOMEPAGE.MT_METRIC_STAT;

GO

EXEC sp_rename 'HOMEPAGE.MT_METRIC_STAT' 	,		'METRIC_STAT';
GO

ALTER TABLE HOMEPAGE.METRIC_STAT 
    ADD CONSTRAINT PK_METRIC_STAT_ID PRIMARY KEY(METRIC_STAT_ID);

CREATE INDEX METRIC_IDX
    ON HOMEPAGE.METRIC_STAT (RECORDED_ON ASC, METRIC_TYPE);
    
ALTER TABLE HOMEPAGE.METRIC_STAT 
	ADD CONSTRAINT FK_METRIC_ORG_ID FOREIGN KEY (ORGANIZATION_ID) 
	REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);    

GO



-----------------------------------------------------
--  HOMEPAGE.MT_CFG_DEFINITIONS
-----------------------------------------------------
CREATE TABLE HOMEPAGE.MT_CFG_DEFINITIONS (
	DEFINITION_ID	nvarchar(36) NOT NULL,
	NAME			nvarchar(128) NOT NULL,
	TITLE			nvarchar(128),
	DESCRIPTION		nvarchar(256),
	TYPE			NVARCHAR(256),
	EDITOR			nvarchar(128),
	CAN_MODIFY		NUMERIC(5,0) DEFAULT 0 NOT NULL,
	DISPLAY			NUMERIC(5,0) DEFAULT 0 NOT NULL,
	REQUIRES		nvarchar(128),
	VALIDATOR		nvarchar(256),
	CATEGORY 		nvarchar(128)
)  ON [PRIMARY]
GO
    
ALTER TABLE HOMEPAGE.MT_CFG_DEFINITIONS
	ADD CONSTRAINT PK_MT_CFG_DEFINITION PRIMARY KEY (DEFINITION_ID);

CREATE UNIQUE INDEX UNQ_NAME
	ON HOMEPAGE.MT_CFG_DEFINITIONS (NAME);
	

	
-----------------------------------------------------
--  HOMEPAGE.MT_CFG_SETTINGS
-----------------------------------------------------
CREATE TABLE HOMEPAGE.MT_CFG_SETTINGS (
	SETTING_ID		nvarchar(36) NOT NULL,
	ORGANIZATION_ID	nvarchar(36) NOT NULL,
	NAME			nvarchar(128) NOT NULL,
	VALUE			NVARCHAR(256),
	CAN_MODIFY		NUMERIC(5,0) DEFAULT 0 NOT NULL
)  ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.MT_CFG_SETTINGS
	ADD CONSTRAINT PK_MT_CFG_SETTINGS PRIMARY KEY (SETTING_ID);
	
ALTER TABLE HOMEPAGE.MT_CFG_SETTINGS 
	ADD CONSTRAINT FK_CFG_SET_ORG_ID FOREIGN KEY (ORGANIZATION_ID) 
	REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID); 	
	
CREATE UNIQUE INDEX UNQ_ORG_NAME
	ON HOMEPAGE.MT_CFG_SETTINGS (NAME, ORGANIZATION_ID);
	
	

-----------------------------------------------------
--  HOMEPAGE.MT_CFG_FILES
-- 	TYPE: 0: BINARY FILE, 1: TXT FILE, 2: CONFIGURATION FILE
-----------------------------------------------------
CREATE TABLE HOMEPAGE.MT_CFG_FILES (
	FILE_ID			nvarchar(36) NOT NULL,
	SETTING_ID		nvarchar(36) NOT NULL,
	TYPE			NUMERIC(5,0) NOT NULL, 
	FILENAME		nvarchar(256),		
	CACHE			NUMERIC(5,0) DEFAULT 0 NOT NULL,
	C_CONTENT		NVARCHAR(MAX),
	B_CONTENT		VARBINARY(MAX),
	ORGANIZATION_ID	NVARCHAR(36) NOT NULL
)  ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.MT_CFG_FILES
	ADD CONSTRAINT PK_MT_CFG_FILE PRIMARY KEY (FILE_ID);

ALTER TABLE HOMEPAGE.MT_CFG_FILES 
	ADD CONSTRAINT FK_SETTING_ID FOREIGN KEY (SETTING_ID) REFERENCES HOMEPAGE.MT_CFG_SETTINGS (SETTING_ID);

CREATE UNIQUE INDEX UNQ_SETTING
	ON HOMEPAGE.MT_CFG_FILES (SETTING_ID);	
	
ALTER TABLE HOMEPAGE.MT_CFG_FILES 
	ADD CONSTRAINT FK_CFG_FILE_ORG_ID FOREIGN KEY (ORGANIZATION_ID) 
	REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);	

	
-- 89575: Add new entry to MT_ORGANIZATION table in initData for on premise organization
UPDATE HOMEPAGE.MT_ORGANIZATION SET ORGANIZATION_EXID = '00000000-0000-0000-0000-000000000000' WHERE ORGANIZATION_ID ='00000000-0000-0000-0000-000000000000';

-----------------------------------------------------------------------------------------------------------------------------------------------------------
-- 91299: Database schema updates to support per-user performances and DAO layer
-----------------------------------------------------------------------------------------------------------------------------------------------------------

-----------------------------------------
-- HOMEPAGE.USER_PREFS
-----------------------------------------
CREATE TABLE HOMEPAGE.HP_USER_PREFS (
	USER_PREFS_ID nvarchar(36) NOT NULL,
	PERSON_ID nvarchar(36) NOT NULL,
	PREF_NAME nvarchar(400),
	PREF_VALUE nvarchar(1536),
	LAST_UPDATE DATETIME,
	CREATE_DATE DATETIME,
	ORGANIZATION_ID nvarchar(36) DEFAULT '00000000-0000-0000-0000-000000000000'
)  ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.HP_USER_PREFS
    ADD CONSTRAINT PK_USER_PREFS_ID PRIMARY KEY(USER_PREFS_ID);

ALTER TABLE HOMEPAGE.HP_USER_PREFS 
	ADD CONSTRAINT FK_USER_PREFS_PERS_ID FOREIGN KEY (PERSON_ID) REFERENCES HOMEPAGE.PERSON (PERSON_ID);
	
ALTER TABLE HOMEPAGE.HP_USER_PREFS 
	ADD CONSTRAINT FK_USER_PREFS_ORG_ID FOREIGN KEY (ORGANIZATION_ID) REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);

CREATE INDEX USER_PREF_NAME_IDX
	ON HOMEPAGE.HP_USER_PREFS (PREF_NAME);

CREATE INDEX USER_PREF_PER_IDX
	ON  HOMEPAGE.HP_USER_PREFS (PERSON_ID);

CREATE UNIQUE INDEX USER_PREF_PER_NAME_UNQ
	ON  HOMEPAGE.HP_USER_PREFS (PERSON_ID, PREF_NAME);
	
GO	

----------------------------------------------------
------------------------ END   HP FIXUP 451 -------
----------------------------------------------------