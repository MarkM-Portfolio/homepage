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

-- 113881: [fixup468] Add an explicit set to default org. for PERSON records. hp-init and fixup script. This is required also for SC
-- 133156: [BHT6A][S26] Very high CPU usage on acnode2
UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID = '00000000-0000-0000-0000-000000000000' WHERE PERSON_ID = '00000000-0000-0000-0000-000000000001';
COMMIT;

UPDATE HOMEPAGE.PERSON  SET 
        EXID = 'INVALID_' || SUBSTR(PERSON_ID,1, 27),
        USER_MAIL = 'INVALID_' || USER_MAIL,
        USER_MAIL_LOWER = 'INVALID_' || USER_MAIL_LOWER,
        LAST_UPDATE = TO_TIMESTAMP ('2000-Jan-01 00:00:00', 'RRRR-Mon-DD HH24:MI:SS.FF')
WHERE ORGANIZATION_ID IS NULL AND MEMBER_TYPE = 0;
COMMIT;

UPDATE HOMEPAGE.PERSON  SET 
        EXID = 'INVALID_' || SUBSTR(PERSON_ID,1, 27),
        USER_MAIL = 'INVALID_' || USER_MAIL,
        USER_MAIL_LOWER = 'INVALID_' || USER_MAIL_LOWER,
        LAST_UPDATE = TO_TIMESTAMP ('2000-Jan-01 00:00:00', 'RRRR-Mon-DD HH24:MI:SS.FF')
WHERE ORGANIZATION_ID IS NULL AND MEMBER_TYPE = 1;
COMMIT;

UPDATE HOMEPAGE.PERSON  SET 
        EXID = 'INVALID_' || SUBSTR(PERSON_ID,1, 27),
        USER_MAIL = 'INVALID_' || USER_MAIL,
        USER_MAIL_LOWER = 'INVALID_' || USER_MAIL_LOWER,
        LAST_UPDATE = TO_TIMESTAMP ('2000-Jan-01 00:00:00', 'RRRR-Mon-DD HH24:MI:SS.FF')
WHERE ORGANIZATION_ID IS NULL AND MEMBER_TYPE > 1;
COMMIT;

TRUNCATE TABLE HOMEPAGE.LOGINNAME;
COMMIT;

