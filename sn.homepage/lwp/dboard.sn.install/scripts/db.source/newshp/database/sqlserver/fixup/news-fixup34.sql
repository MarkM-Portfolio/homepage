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
-- 1) UPDATE CATEGORY
----------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_FOLLOW DROP CONSTRAINT FK_CATEGORY_TYPE;

UPDATE HOMEPAGE.NR_CATEGORY_TYPE SET CATEGORY_TYPE_NAME = '%tags', CATEGORY_TYPE_DESC='tags', CATEGORY_TYPE=10 WHERE CATEGORY_TYPE = 2;

UPDATE HOMEPAGE.NR_CATEGORY_TYPE SET CATEGORY_TYPE_NAME = '%profiles', CATEGORY_TYPE=2 WHERE CATEGORY_TYPE = 1;

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('responses_0fdf1xc9cax4cc4x8b0bx51af2', 1, '%responses', 'responses');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('communities_0f1xc9cax4cc4x8b0bx51af2', 3, '%communities', 'communities');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('activities_0ff1xc9cax4cc4x8b0bx51af2', 4, '%activities', 'activities');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('blogs_0ffdsfds1xc9cax4cc4x8b0bx51af2', 5, '%blogs', 'blogs');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('bookmarks_0fdf1xc9cax4cc4x8b0bx51af2', 6, '%bookmarks', 'bookmarks');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('files_0fdsfdsf1xc9cax4cc4x8b0bx51af2', 7, '%files', 'files');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('forums_0fdsfdf1xc9cax4cc4x8b0bx51af2', 8, '%forums', 'forums');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('wikis_0fdsfdsf1xc9cax4cc4x8b0bx51af2', 9, '%wikis', 'wikis');

ALTER TABLE HOMEPAGE.NR_FOLLOW
	ADD CONSTRAINT FK_CATEGORY_TYPE FOREIGN KEY (CATEGORY_TYPE)
	REFERENCES HOMEPAGE.NR_CATEGORY_TYPE(CATEGORY_TYPE);

----------------------------------------------------------------------
-- 2) UPDATE TEMPLATES
----------------------------------------------------------------------
DELETE FROM HOMEPAGE.NR_TEMPLATE;

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actor-Bei35oPldKwZTaR7aAiPFw4L08CyRW','actor', 'actorInternalId', 'profilePhoto', 1); 

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actorProfile-CAsyXgPhQd7N3wSMw7C0IUe','actorProfiles', 'actorInternalId', 'profilePhoto', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('target-Ah3vEOPQZDEpgpwcQ2JNaHFfMnMcG','subject', 'targetSubjectsInternalIds', 'profilePhoto', 3);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actEnt-anXIr0xP82OSZnuoYbAZa2M5AsRFr', 'activityEntry', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actEntComm-WEJHX1TBvSCW0PS8ayfbPlZ1k','activityEntryWithComment', 'containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actCont-WHHEhu6HTkRWtCQPylcUdSENF4mU', 'activityContainerName', 'containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actCont-Mg2xRFBLmRqg3Zj7Etrdttiyc6OH','activityContainerNameACL', 'containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actToDo-psOHtvbY4lOJv8jOXJH5YgLoGYP7','toDoEntry', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blog-Itf9INx14G6bbCWRYNRpLhSawJXF8Qs','blogContainerName', 'containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogNew-qsgRrfp88AKWys4wcm4gIuZm3T2p','newBlogContainer', 'containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogEntry-ubpjmKeG6Vi8XpQXYKVx3mtCqm','blogEntry', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogComment-92JYbZEOrk0CBLEJHYwkrqPA','blogEntryWithComment', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-Qf0I5rSEaImjaCcds3HYi4SeCGYCDCN','newCommunityContainer', 'containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-new-IduwKhHDNCLdmJFi0G7lwrE86IJ','communityContainerName', 'containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-topic6V808ijHf9TRgUSUqVVZoSOGg5','topicEntryName', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-book-VSWMFF7uZVaE11XRe4Q1ElJIc6','communityBookmarkEntryName', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-feed-Ja6XFBT7kyfEJv07Unitc0MsJT','feedEntryName', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('dogear-book-DXAWZl1FIGMuMr4Ea4Lejbum','dogearBookmarkEntry', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('tags-a7YmLTHTY2FdHKPsHU0wbAZCkgYrkk1','tag', 'contentTags', 'plain', 3);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('profileEntry-Vrh9M5YNmxNogF2oERZWyRE','profileLinkEntry', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('profile-status-HNX0o3AF32cFuPWhtYMjl','profileStatusEntry', 'status', 'plain', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-9qgQLuYLDdh66pf82um3c6q1lQ0gZl5','wikiContainerName','containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-page-P75oD6oAdgoWCRNt4YYh7hZfuM','wikiEntryPage', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-comment-Ye2xdX8pbYM996pc7je30LI','wikiEntryPageCommented', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('file-bbqZpR6oxvE2sYAXxasKS0EtS1mDg4h','fileEntry', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('collection-7jEWoKkWx8ucNTo6Z7AndhRFh','collectionContainer', 'containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-9qgQLuYLDdh66pf82um3c6q1lQdfgt5','newWikiContainer','containerName;containerHtmlPath', 'link', 1);

------------------------------------------------
-- NR_RESOURCE_TYPE
------------------------------------------------
CREATE TABLE HOMEPAGE.NR_RESOURCE_TYPE (
	RESOURCE_TYPE_ID nvarchar(36) NOT NULL,
	RESOURCE_TYPE_NAME nvarchar(36) NOT NULL, -- this is externalized
	RESOURCE_TYPE NUMERIC(5,0) NOT NULL,
	RESOURCE_TYPE_DESC nvarchar(256) NOT NULL
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_RESOURCE_TYPE 
  	ADD CONSTRAINT PK_RES_TYPE_ID PRIMARY KEY(RESOURCE_TYPE_ID);

ALTER TABLE HOMEPAGE.NR_RESOURCE_TYPE 
	ADD CONSTRAINT RES_TYPE_UNIQUE UNIQUE (RESOURCE_TYPE);

{SQL_GRANT_START} HOMEPAGE.NR_RESOURCE_TYPE  {SQL_GRANT_STOP}	

----------------------------------------------------------------------
-- HOMEPAGE.NR_RESOURCE
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_RESOURCE (
	RESOURCE_ID nvarchar(36) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(36) NOT NULL,
	CONTAINER_NAME nvarchar(36),
	CONTAINER_URL nvarchar(2048),
	CATEGORY_TYPE NUMERIC(5,0),
	RESOURCE_TYPE NUMERIC(5,0)
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_RESOURCE 
    ADD CONSTRAINT PK_RESOURCE_ID PRIMARY KEY(RESOURCE_ID);

{SQL_GRANT_START} HOMEPAGE.NR_RESOURCE  {SQL_GRANT_STOP}  

----------------------------------------------------------------------
-- HOMEPAGE.NR_FOLLOWS 
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_FOLLOWS (
	FOLLOW_ID nvarchar(36) NOT NULL,
	PERSON_ID nvarchar(36) NOT NULL,
	RESOURCE_ID nvarchar(36) NOT NULL
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_FOLLOWS 
    ADD CONSTRAINT PK_FOLLOWS_ID PRIMARY KEY(FOLLOW_ID);

ALTER TABLE HOMEPAGE.NR_FOLLOWS
    ADD CONSTRAINT FK_FS_PERSON_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

ALTER TABLE HOMEPAGE.NR_FOLLOWS
    ADD CONSTRAINT FK_FS_RESOURCE_ID FOREIGN KEY (RESOURCE_ID)
	REFERENCES HOMEPAGE.NR_RESOURCE (RESOURCE_ID);

{SQL_GRANT_START} HOMEPAGE.NR_FOLLOWS  {SQL_GRANT_STOP}	

----------------------------------------------------------------------
-- HOMEPAGE.NR_COMM_FOLLOW 
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_COMM_FOLLOW (
	COMM_FOLLOW_ID nvarchar(36) NOT NULL,
	PERSON_ID nvarchar(36) NOT NULL,
	COMMUNITY_ID nvarchar(36) NOT NULL
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_COMM_FOLLOW
    ADD CONSTRAINT PK_COMM_FOLLOW_ID PRIMARY KEY(COMM_FOLLOW_ID);

ALTER TABLE HOMEPAGE.NR_COMM_FOLLOW
    ADD CONSTRAINT FK_COMM_PERSON_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

{SQL_GRANT_START} HOMEPAGE.NR_COMM_FOLLOW  {SQL_GRANT_STOP}	  

----------------------------------------------------------------------
-- HOMEPAGE.NR_ORGPERSON_FOLLOW
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_ORGPERSON_FOLLOW (
	ORGPERSON_FOLLOW_ID nvarchar(36) NOT NULL,
	PERSON_ID nvarchar(36) NOT NULL,
	ORGANIZATION_ID nvarchar(36) NOT NULL
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_ORGPERSON_FOLLOW
    ADD CONSTRAINT PK_ORGP_FOLLOW_ID PRIMARY KEY(ORGPERSON_FOLLOW_ID);

ALTER TABLE HOMEPAGE.NR_ORGPERSON_FOLLOW
    ADD CONSTRAINT FK_ORGP_PERSON_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

{SQL_GRANT_START} HOMEPAGE.NR_ORGPERSON_FOLLOW  {SQL_GRANT_STOP}

----------------------------------------------------------------------
-- HOMEPAGE.NR_STORIES
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_STORIES (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(36),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(36), -- NEW
	ITEM_CORRELATION_ID nvarchar(36), -- NEW	
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(512),
	ACTOR_UUID nvarchar(36),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	RELATED_COMM_UUID nvarchar(36),
	RELATED_COMM_NAME nvarchar(256),
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(4000) DEFAULT '' NOT NULL,
	R_TEXT_META_TEMPLATE nvarchar(1024),
	N_COMMENTS NUMERIC(5,0) NOT NULL DEFAULT 0, -- NEW
	N_RECOMMANDATIONS NUMERIC(5,0) NOT NULL DEFAULT 0 -- NEW
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_STORIES
    ADD CONSTRAINT PK_STORY_ID PRIMARY KEY(STORY_ID);

{SQL_GRANT_START} HOMEPAGE.NR_STORIES  {SQL_GRANT_STOP}

----------------------------------------------------------------------
-- HOMEPAGE.NR_FOLLOWED_STORIES
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_FOLLOWED_STORIES (
	FOLLOWED_STORY_ID nvarchar(36) NOT NULL,
	READER_ID nvarchar(36) NOT NULL,
	CATEGORY_TYPE NUMERIC(5,0) NOT NULL,
	SOURCE nvarchar(36) NOT NULL, 
	ITEM_ID nvarchar(36) NOT NULL, -- I don't think we need SOURCE_ID here, this should be in the SOURCE table?
	RESOURCE_TYPE_ID nvarchar(36) NOT NULL, -- ???? what is this ??
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_FOLLOWED_STORIES
    ADD CONSTRAINT PK_F_STORY_ID PRIMARY KEY(FOLLOWED_STORY_ID);

ALTER TABLE HOMEPAGE.NR_FOLLOWED_STORIES
    ADD CONSTRAINT FK_F_STORY_ID FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);

{SQL_GRANT_START} HOMEPAGE.NR_FOLLOWED_STORIES  {SQL_GRANT_STOP}    

----------------------------------------------------------------------
-- HOMEPAGE.NR_COMM_STORIES
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_COMM_STORIES (
	COMM_STORY_ID nvarchar(36) NOT NULL,
	COMMUNITY_ID nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(36),
	RESOURCE_TYPE_ID nvarchar(36) NOT NULL, -- ???? what is this ??
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_COMM_STORIES
    ADD CONSTRAINT PK_F_CSTORY_ID PRIMARY KEY(COMM_STORY_ID);

ALTER TABLE HOMEPAGE.NR_COMM_STORIES
    ADD CONSTRAINT FK_COMM_STORY_ID FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);

{SQL_GRANT_START} HOMEPAGE.NR_COMM_STORIES  {SQL_GRANT_STOP}

----------------------------------------------------------------------
-- HOMEPAGE.NR_ORGPERSON_STORIES
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_ORGPERSON_STORIES (
	ORGPERSON_STORY_ID nvarchar(36) NOT NULL,
	ORGANIZATION_ID nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(36),
	ITEM_ID nvarchar(36) NOT NULL,
	RESOURCE_TYPE_ID nvarchar(36) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_ORGPERSON_STORIES
    ADD CONSTRAINT PK_ORGP_STORY_ID PRIMARY KEY(ORGPERSON_STORY_ID);

ALTER TABLE HOMEPAGE.NR_ORGPERSON_STORIES
    ADD CONSTRAINT FK_ORGP_STORY_ID FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);

{SQL_GRANT_START} HOMEPAGE.NR_ORGPERSON_STORIES  {SQL_GRANT_STOP}

----------------------------------------------------------------------------------
-- 5) Creating the story table where to store the actual content for a story
----------------------------------------------------------------------------------

------------------------------------------------
-- NR_STORIES_CONTENT
------------------------------------------------
CREATE TABLE HOMEPAGE.NR_STORIES_CONTENT (
	STORY_CONTENT_ID nvarchar(36) NOT NULL,
	CONTENT varbinary (MAX) NOT NULL,
	CREATION_DATE DATETIME NOT NULL
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT
  	ADD CONSTRAINT PK_STORY_CONT_ID PRIMARY KEY(STORY_CONTENT_ID);

{SQL_GRANT_START} HOMEPAGE.NR_STORIES_CONTENT  {SQL_GRANT_STOP}

