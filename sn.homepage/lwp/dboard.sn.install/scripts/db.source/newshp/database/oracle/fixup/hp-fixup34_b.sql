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

-- 1) Update HOMEPAGE.PERSON  
ALTER TABLE HOMEPAGE.PERSON
ADD STATE NUMBER(5,0) DEFAULT 0 NOT NULL;

-- b) update the NT_NOTIFICATION table
ALTER TABLE HOMEPAGE.NT_NOTIFICATION
ADD FIRST_RECIPIENT_EXID VARCHAR2(36);

ALTER TABLE HOMEPAGE.NT_NOTIFICATION
ADD NUM_RECIPIENTS NUMBER(5,0);

ALTER TABLE HOMEPAGE.NT_NOTIFICATION
ADD IS_DELETED NUMBER(5,0);

ALTER TABLE HOMEPAGE.NT_NOTIFICATION
ADD PRIMARY_ACTION_URL VARCHAR2(2048);

ALTER TABLE HOMEPAGE.NT_NOTIFICATION
ADD SECONDARY_ACTION_URL VARCHAR2(2048);

--c) update NT_RECIPIENTS table
ALTER TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT
ADD IS_DELETED NUMBER(5,0);