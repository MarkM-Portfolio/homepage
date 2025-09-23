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

UPDATE HOMEPAGE.PERSON SET IS_ACTIVE = 1;
 
ALTER TABLE HOMEPAGE.PERSON
	MODIFY IS_ACTIVE NOT NULL;

-- CLEANUP POSSIBLE NULL VALUES
-- DELETE FROM HOMEPAGE.NT_NOTIFICATION NT_NOTIFICATION WHERE NT_NOTIFICATION.SENDER_ID IS NULL;
-- DELETE FROM HOMEPAGE.NT_NOTIFICATION NT_NOTIFICATION WHERE NT_NOTIFICATION.FIRST_RECIPIENT_ID IS NULL;
-- DELETE FROM HOMEPAGE.NT_NOTIFICATION_RECIPIENT NT_NOTIFICATION_RECIPIENT WHERE NT_NOTIFICATION_RECIPIENT.RECIPIENT_ID IS NULL; 

--UPDATE HOMEPAGE.NT_NOTIFICATION_RECIPIENT SET RECIPIENT_ID = ' ' WHERE RECIPIENT_ID IS NULL;

-- ALTER TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT
--    MODIFY RECIPIENT_ID
--    VARCHAR2(36) NOT NULL;

UPDATE HOMEPAGE.HP_TAB SET ENABLED = 1;

-- HOME PAGE
{ORA_GRANT_START} HOMEPAGE.HOMEPAGE_SCHEMA {ORA_GRANT_STOP}
{ORA_GRANT_START} HOMEPAGE.PERSON {ORA_GRANT_STOP}
{ORA_GRANT_START} HOMEPAGE.SNCORE_PERSON {ORA_GRANT_STOP}
{ORA_GRANT_START} HOMEPAGE.LOGINNAME {ORA_GRANT_STOP}
{ORA_GRANT_START} HOMEPAGE.PREREQ {ORA_GRANT_STOP}
{ORA_GRANT_START} HOMEPAGE.WIDGET  {ORA_GRANT_STOP}


{ORA_GRANT_START} HOMEPAGE.HP_UI  {ORA_GRANT_STOP}
{ORA_GRANT_START} HOMEPAGE.HP_TAB  {ORA_GRANT_STOP}
{ORA_GRANT_START} HOMEPAGE.HP_TAB_INST  {ORA_GRANT_STOP}
{ORA_GRANT_START} HOMEPAGE.HP_WIDGET_INST  {ORA_GRANT_STOP}
{ORA_GRANT_START} HOMEPAGE.HP_WIDGET_TAB  {ORA_GRANT_STOP}   