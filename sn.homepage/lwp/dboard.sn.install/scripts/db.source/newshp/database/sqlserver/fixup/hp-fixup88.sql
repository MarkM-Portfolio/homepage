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
GO

DROP TABLE HOMEPAGE.NT_REPLYTO; 
GO

----------------------------------------------------------------------
-- HOMEPAGE.NT_REPLYTO
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NT_REPLYTO (
	REPLYTO_NOTIFICATION_ID nvarchar(36) NOT NULL,
	SOURCE nvarchar(36),
	EVENT_NAME nvarchar(256) NOT NULL,
	CONTAINER_ID nvarchar(256),	
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),	
	CREATION_DATE DATETIME NOT NULL,
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	CATEGORY_TYPE NUMERIC(5,0),
	SOURCE_TYPE NUMERIC(5,0)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NT_REPLYTO
    ADD CONSTRAINT PK_REPLYTO PRIMARY KEY(REPLYTO_NOTIFICATION_ID);
GO
----------------------------------------------------------------------
-- HOMEPAGE.NT_REPLYTO_RECIPIENT
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT (
	REPLYTO_RECIPIENT_ID nvarchar(36) NOT NULL,
	REPLYTO_NOTIFICATION_ID nvarchar(36) NOT NULL,
	PERSON_ID nvarchar(36) NOT NULL,	
	REPLYTO_ID nvarchar(36) NOT NULL,
	LAST_UPDATE DATETIME
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT
    ADD CONSTRAINT REPLYTO_RECIP_ID PRIMARY KEY(REPLYTO_RECIPIENT_ID);

ALTER TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT
    ADD CONSTRAINT FK_REPLYTO_NOT_ID FOREIGN KEY (REPLYTO_NOTIFICATION_ID)
	REFERENCES HOMEPAGE.NT_REPLYTO (REPLYTO_NOTIFICATION_ID);

ALTER TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT
    ADD CONSTRAINT FK_REPLYTO_PER_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

CREATE UNIQUE INDEX REPLYTO_IDX
    ON HOMEPAGE.NT_REPLYTO_RECIPIENT (REPLYTO_ID);

GO	


GO
