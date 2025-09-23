-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2015, 2016                             
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

-- Create Appreg Table
------------ APPREG_SETTINGS ------------
CREATE TABLE HOMEPAGE.APPREG_SETTINGS(
	SETTING_KEY VARCHAR(100) NOT NULL,
	SETTING_VALUE VARCHAR(100) NOT NULL
)
IN NEWS4TABSPACE@

ALTER TABLE HOMEPAGE.APPREG_SETTINGS ADD CONSTRAINT PK_APPREG_SETTINGS PRIMARY KEY (SETTING_KEY)@

RUNSTATS ON TABLE HOMEPAGE.APPREG_SETTINGS@


-- Alter Appreg columns
ALTER TABLE HOMEPAGE.APPREG_SERVICES ALTER COLUMN ENABLE DROP NOT NULL@
COMMIT@

ALTER TABLE HOMEPAGE.APPREG_EXTENSIONS ALTER COLUMN TYPE DROP NOT NULL@
COMMIT@

ALTER TABLE HOMEPAGE.APPREG_EXTENSIONS ALTER COLUMN PAYLOAD DROP NOT NULL@
COMMIT@

reorg table HOMEPAGE.APPREG_SERVICES@
reorg table HOMEPAGE.APPREG_EXTENSIONS@

COMMIT@
