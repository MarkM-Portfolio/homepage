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
connect to HOMEPAGE;

-- LL requirement
CREATE VIEW HOMEPAGE.SNCORE_PERSON (SNC_INTERNAL_ID, SNC_IDKEY, SNC_EMAIL_LOWER, SNC_DISPLAY_NAME) 
    AS SELECT PERSON_ID, EXID, lower(USER_MAIL) USER_MAIL, DISPLAYNAME FROM HOMEPAGE.PERSON;

-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 2.5.1.0
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 23 , RELEASEVER = '2.5.1.0'
WHERE   DBSCHEMAVER = 22;

--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate;