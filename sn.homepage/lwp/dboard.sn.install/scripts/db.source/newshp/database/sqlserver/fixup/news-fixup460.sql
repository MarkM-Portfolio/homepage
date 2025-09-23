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

---------------------------------------------------------------------------------
------------------------ START NEWS FIXUP 460 -----------------------------------
---------------------------------------------------------------------------------

-----------------------------------------------------------
-- [START] 98254: fixup460 remove MAX_UPDATE_FOR_READER	
-----------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_WIKIS_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_TOPICS_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_TAGS_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_STATUS_UPDATE_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_SAVED_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_PROFILES_VIEW DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_PROFILES_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_NOTIFICATION_SENT_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_MENTIONS_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_FORUMS_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_FILES_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_EXTERNAL_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_DISCOVERY_VIEW DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_COMMUNITIES_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_BOOKMARKS_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_BLOGS_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
GO

-----------------------------------------------------------
-- [END] 98254: fixup460 remove MAX_UPDATE_FOR_READER	
-----------------------------------------------------------

-----------------------------------------------------------
-- [START] 98252:  [fixup460] create the new table NR_AS_COUNTS
-----------------------------------------------------------


-----------------------------------------------------------
-- HOMEPAGE.NR_AS_COUNTS
-----------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_AS_COUNTS (
	AS_COUNT_ID nvarchar(36) NOT NULL,
	PERSON_ID nvarchar(36) NOT NULL,	
	UI_VIEW NUMERIC(5 ,0),
	COUNT NUMERIC(10),
	ORGANIZATION_ID nvarchar(36) DEFAULT '00000000-0000-0000-0000-000000000000' NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_AS_COUNTS 
  	ADD CONSTRAINT PK_AS_COUNT_ID PRIMARY KEY (AS_COUNT_ID);
  	
ALTER TABLE HOMEPAGE.NR_AS_COUNTS
    ADD CONSTRAINT FK_AS_COUNT_ORG_ID FOREIGN KEY (ORGANIZATION_ID) REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);
    
ALTER TABLE HOMEPAGE.NR_AS_COUNTS
    ADD CONSTRAINT FK_AS_COUNT_PER_ID FOREIGN KEY (PERSON_ID) REFERENCES HOMEPAGE.PERSON (PERSON_ID);    

CREATE UNIQUE INDEX NR_COUNT_PER_VIEW_UNQ 
	ON HOMEPAGE.NR_AS_COUNTS (PERSON_ID, UI_VIEW);
	
CREATE INDEX NR_AS_COUNT_PER_IDX
	ON HOMEPAGE.NR_AS_COUNTS (PERSON_ID);
	
CREATE INDEX NR_AS_COUNT_ORG_IDX
	ON HOMEPAGE.NR_AS_COUNTS (ORGANIZATION_ID);
	
GO





-----------------------------------------------------------
-- [END] 98252:  [fixup460] create the new table NR_AS_COUNTS
-----------------------------------------------------------

--98630: [fixup460] IMG_CACHE  table Create a new field to store HASH values for the URL. That field need to be indexed. 
DELETE FROM HOMEPAGE.IMG_CACHE;
GO

ALTER TABLE HOMEPAGE.IMG_CACHE ADD ORIGINAL_URL_MD5 char(32) NOT NULL;
GO

CREATE INDEX IMG_CACHE_URL_IDX
	ON HOMEPAGE.IMG_CACHE (ORIGINAL_URL_MD5, ORGANIZATION_ID);
GO	



---------------------------------------------------------------------------------
------------------------ END   NEWS FIXUP 460 -------------------------------------
---------------------------------------------------------------------------------
