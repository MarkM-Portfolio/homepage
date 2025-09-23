-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2001, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- 5724_S68                                              
USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO

-- LL requirement
CREATE VIEW HOMEPAGE.SNCORE_PERSON (SNC_INTERNAL_ID, SNC_IDKEY, SNC_EMAIL_LOWER, SNC_DISPLAY_NAME) 
    AS SELECT PERSON_ID, EXID, lower(USER_MAIL) USER_MAIL, DISPLAYNAME FROM HOMEPAGE.PERSON;
GO

-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 2.5.1.0
-- NOTE: In the prev. createDb.sql ( which is into 2.5 gold the last version was wrong setted to 20 from the createDb.sql and 21 from upgrade25.sql and fixup )
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 23 , RELEASEVER = '2.5.1.0'
WHERE   DBSCHEMAVER = 22;
GO

COMMIT;