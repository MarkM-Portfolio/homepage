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
	SETTING_KEY VARCHAR2(100) NOT NULL,
	SETTING_VALUE VARCHAR2(100) NOT NULL
)TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.APPREG_SETTINGS ADD CONSTRAINT PK_APPREG_SETTINGS PRIMARY KEY (SETTING_KEY);

ALTER TABLE HOMEPAGE.APPREG_SETTINGS ENABLE ROW MOVEMENT;

-- Alter Appreg Service and Extension 
ALTER TABLE HOMEPAGE.APPREG_SERVICES MODIFY (ENABLE NULL);
COMMIT;
ALTER TABLE HOMEPAGE.APPREG_EXTENSIONS MODIFY (TYPE NULL);
COMMIT;
ALTER TABLE HOMEPAGE.APPREG_EXTENSIONS MODIFY (PAYLOAD NULL);
COMMIT;
