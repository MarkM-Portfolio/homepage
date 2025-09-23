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

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID = '00000000-0000-0000-0000-000000000000' WHERE PERSON_ID = '00000000-0000-0000-0000-000000000001'@
COMMIT@

UPDATE HOMEPAGE.PERSON  SET 
        EXID = 'INVALID_' || SUBSTR(PERSON_ID,1, 27),
        USER_MAIL = 'INVALID_' || USER_MAIL,
        USER_MAIL_LOWER = 'INVALID_' || USER_MAIL_LOWER,
        LAST_UPDATE = TIMESTAMP ('2000-01-01 00:00:00')
WHERE ORGANIZATION_ID IS NULL AND MEMBER_TYPE = 0@
COMMIT@

UPDATE HOMEPAGE.PERSON  SET 
        EXID = 'INVALID_' || SUBSTR(PERSON_ID,1, 27),
        USER_MAIL = 'INVALID_' || USER_MAIL,
        USER_MAIL_LOWER = 'INVALID_' || USER_MAIL_LOWER,
        LAST_UPDATE = TIMESTAMP ('2000-01-01 00:00:00')
WHERE ORGANIZATION_ID IS NULL AND MEMBER_TYPE = 1@
COMMIT@

UPDATE HOMEPAGE.PERSON  SET 
        EXID = 'INVALID_' || SUBSTR(PERSON_ID,1, 27),
        USER_MAIL = 'INVALID_' || USER_MAIL,
        USER_MAIL_LOWER = 'INVALID_' || USER_MAIL_LOWER,
        LAST_UPDATE = TIMESTAMP ('2000-01-01 00:00:00')
WHERE ORGANIZATION_ID IS NULL AND MEMBER_TYPE > 1@
COMMIT@

TRUNCATE HOMEPAGE.LOGINNAME IMMEDIATE@
COMMIT@



