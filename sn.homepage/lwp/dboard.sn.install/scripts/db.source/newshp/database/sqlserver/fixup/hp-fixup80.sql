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
-- HOMEPAGE.NT_REPLYTO
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NT_REPLYTO (
	REPLYTO_ID nvarchar(36) NOT NULL,
	SOURCE nvarchar(36),
	EVENT_NAME nvarchar(256) NOT NULL,
	CONTAINER_ID nvarchar(256),	
	ITEM_ID nvarchar(36),
	ITEM_CORRELATION_ID nvarchar(36),	
	CREATION_DATE DATETIME NOT NULL,
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	CATEGORY_TYPE NUMERIC(5,0)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NT_REPLYTO
    ADD CONSTRAINT PK_REPLYTO PRIMARY KEY(REPLYTO_ID);

----------------------------------------------------------------------
-- HOMEPAGE.NT_REPLYTO_RECIPIENT
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT (
	REPLYTO_RECIPIENT_ID nvarchar(36) NOT NULL,
	REPLYTO_ID nvarchar(36) NOT NULL,
	PERSON_ID nvarchar(36) NOT NULL,	
	REPLYTO_ADDRESS_ID nvarchar(36) NOT NULL,
	LAST_UPDATE DATETIME
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT
    ADD CONSTRAINT REPLYTO_RECIP_ID PRIMARY KEY(REPLYTO_RECIPIENT_ID);

ALTER TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT
    ADD CONSTRAINT FK_REPLYTO_ID FOREIGN KEY (REPLYTO_ID)
	REFERENCES HOMEPAGE.NT_REPLYTO (REPLYTO_ID);

ALTER TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT
    ADD CONSTRAINT FK_REPLYTO_PER_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

CREATE UNIQUE INDEX REPLYTO_ADDRESS_IDX
    ON HOMEPAGE.NT_REPLYTO_RECIPIENT (REPLYTO_ADDRESS_ID);



GO

--------------------------------------------
-- ADDING SOURCE_TYPE - NT_NOTIFICATION
--------------------------------------------
ALTER TABLE HOMEPAGE.NT_NOTIFICATION
	ADD NOTIFICATION_SOURCE_TYPE NUMERIC(5,0);
GO	

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 1 WHERE NOTIFICATION_SOURCE = 'activities';
GO

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 2 WHERE NOTIFICATION_SOURCE = 'blogs';
GO

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 3 WHERE NOTIFICATION_SOURCE = 'communities';
GO

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 4 WHERE NOTIFICATION_SOURCE = 'wikis';
GO

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 5 WHERE NOTIFICATION_SOURCE = 'profiles';
GO

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 6 WHERE NOTIFICATION_SOURCE = 'homepage';
GO

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 7 WHERE NOTIFICATION_SOURCE = 'dogear';
GO

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 7 WHERE NOTIFICATION_SOURCE = 'bookmarks';
GO

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 8 WHERE NOTIFICATION_SOURCE = 'files';
GO

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 9 WHERE NOTIFICATION_SOURCE = 'forums';
GO	

--------------------------------------------
-- ADDING SOURCE_TYPE - NT_REPLYTO
--------------------------------------------
ALTER TABLE HOMEPAGE.NT_REPLYTO
	ADD SOURCE_TYPE NUMERIC(5,0);
GO	

UPDATE HOMEPAGE.NT_REPLYTO
	SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities';
GO

UPDATE HOMEPAGE.NT_REPLYTO
	SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs';
GO

UPDATE HOMEPAGE.NT_REPLYTO
	SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities';
GO

UPDATE HOMEPAGE.NT_REPLYTO
	SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis';
GO

UPDATE HOMEPAGE.NT_REPLYTO
	SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles';
GO

UPDATE HOMEPAGE.NT_REPLYTO
	SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage';
GO

UPDATE HOMEPAGE.NT_REPLYTO
	SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear';
GO

UPDATE HOMEPAGE.NT_REPLYTO
	SET SOURCE_TYPE = 8 WHERE SOURCE = 'files';
GO

UPDATE HOMEPAGE.NT_REPLYTO
	SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums';
GO   
    

 