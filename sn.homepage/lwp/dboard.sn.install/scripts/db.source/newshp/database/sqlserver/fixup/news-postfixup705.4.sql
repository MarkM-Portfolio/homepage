-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2016                                   
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}
-- Appregistry Table
------------ APPREG_SETTINGS ------------
CREATE TABLE HOMEPAGE.APPREG_SETTINGS(
	SETTING_KEY nvarchar(100) NOT NULL,
	SETTING_VALUE nvarchar(100) NOT NULL
)ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.APPREG_SETTINGS ADD CONSTRAINT PK_APPREG_SETTINGS PRIMARY KEY (SETTING_KEY);
GO

-- Alter Appreg Service and Extension Tables

ALTER TABLE HOMEPAGE.APPREG_SERVICES ALTER COLUMN ENABLE SMALLINT NULL
GO

ALTER TABLE HOMEPAGE.APPREG_EXTENSIONS ALTER COLUMN TYPE nvarchar(100) NULL
GO

ALTER TABLE HOMEPAGE.APPREG_EXTENSIONS ALTER COLUMN PAYLOAD nvarchar(max) NULL
GO
