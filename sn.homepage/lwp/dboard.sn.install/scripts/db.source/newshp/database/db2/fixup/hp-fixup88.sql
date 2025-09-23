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

----------------------------------------------------------------------
-- RENAME REPLYTO COLUMNS - DROP AND CREATE THE TABLES
----------------------------------------------------------------------
    	
DROP TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT;

DROP TABLE HOMEPAGE.NT_REPLYTO; 

----------------------------------------------------------------------
-- HOMEPAGE.NT_REPLYTO
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NT_REPLYTO (
	REPLYTO_NOTIFICATION_ID VARCHAR(36) NOT NULL,
	SOURCE VARCHAR(36),
	EVENT_NAME VARCHAR(256) NOT NULL,
	CONTAINER_ID VARCHAR(256),	
	ITEM_ID VARCHAR(36),
	ITEM_CORRELATION_ID VARCHAR(36),	
	CREATION_DATE TIMESTAMP NOT NULL,
	ACTOR_UUID VARCHAR(36),
	EVENT_RECORD_UUID VARCHAR(36) NOT NULL,
	CATEGORY_TYPE SMALLINT,
	SOURCE_TYPE SMALLINT
)
IN HPNT16TABSPACE;

ALTER TABLE HOMEPAGE.NT_REPLYTO
    	ADD CONSTRAINT PK_REPLYTO PRIMARY KEY(REPLYTO_NOTIFICATION_ID);
    	
----------------------------------------------------------------------
-- HOMEPAGE.NT_REPLYTO_RECIPIENT
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT (
	REPLYTO_RECIPIENT_ID VARCHAR(36) NOT NULL,
	REPLYTO_NOTIFICATION_ID VARCHAR(36) NOT NULL,
	PERSON_ID VARCHAR(36) NOT NULL,	
	REPLYTO_ID VARCHAR(36) NOT NULL,
	LAST_UPDATE TIMESTAMP
)
IN HPNT16TABSPACE;

ALTER TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT
    ADD CONSTRAINT REPLYTO_RECIP_ID PRIMARY KEY(REPLYTO_RECIPIENT_ID);

ALTER TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT
    ADD CONSTRAINT FK_REPLYTO_NOT_ID FOREIGN KEY (REPLYTO_NOTIFICATION_ID)
	REFERENCES HOMEPAGE.NT_REPLYTO (REPLYTO_NOTIFICATION_ID);

ALTER TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT
    ADD CONSTRAINT FK_REPLYTO_PER_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

CREATE UNIQUE INDEX HOMEPAGE.REPLYTO_IDX
    ON HOMEPAGE.NT_REPLYTO_RECIPIENT (REPLYTO_ID);



COMMIT;