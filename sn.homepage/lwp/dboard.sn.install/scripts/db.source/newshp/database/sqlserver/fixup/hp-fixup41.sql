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

-- {COPYRIGHT}

-----------------------------------------------------------------------------------
-- PERSON
-----------------------------------------------------------------------------------
-- select * 
-- from HOMEPAGE.PERSON 
-- where LAST_UPDATE > ?
CREATE INDEX PERSON_LAST_UPDATE
    ON HOMEPAGE.PERSON (LAST_UPDATE DESC);

-----------------------------------------------------------------------------------
-- NT_NOTIFICATION
-----------------------------------------------------------------------------------
-- select N.NOTIFICATION_ID, N.NOTIFICATION_SOURCE AS SOURCE, N.NOTIFICATION_TYPE AS TYPE, N.DATETIME_STAMP, P1.PERSON_ID, N.SENDER_ID,
--        N.SUBJECT, N.MESSAGE, N.CONTAINER_NAME, N.CONTAINER_URL, N.ITEM_NAME, N.ITEM_URL, P2.PERSON_ID AS FIRST_RECIPIENT_PERSON_ID,
--         P2.EXID AS FIRST_RECIPIENT_EXID, P2.DISPLAYNAME AS FIRST_RECIPIENT_DISPLAY_NAME, N.NUM_RECIPIENTS
-- from HOMEPAGE.NT_NOTIFICATION N, HOMEPAGE.PERSON P1, HOMEPAGE.PERSON P2 
-- where P1.PERSON_ID = ? AND P2.PERSON_ID = N.FIRST_RECIPIENT_ID AND N.SENDER_ID = P1.PERSON_ID AND N.IS_DELETED = 0 
-- order by N.DATETIME_STAMP DESC
CREATE INDEX NT_NOTIFICATION_IDX
    ON HOMEPAGE.NT_NOTIFICATION (DATETIME_STAMP DESC, FIRST_RECIPIENT_ID, SENDER_ID);

CREATE INDEX NT_FIRST_RECIPIENT_PER
    ON HOMEPAGE.NT_NOTIFICATION (FIRST_RECIPIENT_ID);
