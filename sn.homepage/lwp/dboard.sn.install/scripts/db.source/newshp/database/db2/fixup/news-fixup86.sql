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

-- The field to store the latest contributor
-- The latest contributor is equal to the latest comment creator or if no comments are provided
-- is equal to the status update creator (actor_uuid)
ALTER TABLE HOMEPAGE.BOARD_ENTRIES
	ADD LAST_CONTRIBUTOR_ID VARCHAR(36);

-- The field to store the latest updated date for a comment
-- If a comment is not updated it is equal to the creation date
ALTER TABLE HOMEPAGE.BOARD_COMMENTS
	ADD UPDATE_DATE TIMESTAMP;

COMMIT;	

reorg table HOMEPAGE.BOARD_ENTRIES;

-------------------------------------------------------
-- HOMEPAGE.BOARD_CURRENT_STATUS 
-- table to store the history of current status updates
-------------------------------------------------------
CREATE TABLE HOMEPAGE.BOARD_CURRENT_STATUS  (
	CURRENT_STATUS_ID VARCHAR(47) NOT NULL, -- the format will include in the pk also the creation time
	ACTOR_UUID VARCHAR(36) NOT NULL,
	ENTRY_ID VARCHAR(47) NOT NULL,
	CURRENT_STATUS_SET TIMESTAMP NOT NULL
)
IN BOARD16TABSPACE;

ALTER TABLE HOMEPAGE.BOARD_CURRENT_STATUS 
    ADD CONSTRAINT PK_CUR_ST_ID PRIMARY KEY (CURRENT_STATUS_ID);

ALTER TABLE HOMEPAGE.BOARD_CURRENT_STATUS
	ADD CONSTRAINT FK_CUR_ST_ACTOR_ID FOREIGN KEY (ACTOR_UUID)
	REFERENCES HOMEPAGE.PERSON(PERSON_ID);

ALTER TABLE HOMEPAGE.BOARD_CURRENT_STATUS
	ADD CONSTRAINT FK_CUR_ST_ENTRY_ID FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.BOARD_ENTRIES(ENTRY_ID);

CREATE INDEX HOMEPAGE.CURRENT_STATUS_INDEX 
	ON HOMEPAGE.BOARD_CURRENT_STATUS	(ACTOR_UUID ASC, CURRENT_STATUS_SET ASC, CURRENT_STATUS_ID ASC, ENTRY_ID ASC);

CREATE UNIQUE INDEX HOMEPAGE.ACTOR_ENTRY 
	ON HOMEPAGE.BOARD_CURRENT_STATUS (ACTOR_UUID);



COMMIT;