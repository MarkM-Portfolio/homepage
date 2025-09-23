-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2001, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- 5724_S68                                              
USE HOMEPAGE
GO

BEGIN TRANSACTION
GO

-------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------
-- #65088

DELETE FROM HOMEPAGE.NR_COMM_STORIES WHERE NOT EXISTS ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = STORY_ID );
GO

DELETE FROM HOMEPAGE.NR_COMM_PERSON_STORIES WHERE NOT EXISTS ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = STORY_ID );
GO

DELETE FROM HOMEPAGE.NR_RESPONSES_STORIES WHERE NOT EXISTS ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = STORY_ID );
GO

DELETE FROM HOMEPAGE.NR_PROFILES_STORIES WHERE NOT EXISTS ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = STORY_ID );
GO

DELETE FROM HOMEPAGE.NR_COMMUNITIES_STORIES WHERE NOT EXISTS ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = STORY_ID );
GO

DELETE FROM HOMEPAGE.NR_ACTIVITIES_STORIES WHERE NOT EXISTS ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = STORY_ID );
GO

DELETE FROM HOMEPAGE.NR_BLOGS_STORIES WHERE NOT EXISTS ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = STORY_ID );
GO

DELETE FROM HOMEPAGE.NR_BOOKMARKS_STORIES WHERE NOT EXISTS ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = STORY_ID );
GO

DELETE FROM HOMEPAGE.NR_FILES_STORIES WHERE NOT EXISTS ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = STORY_ID );
GO

DELETE FROM HOMEPAGE.NR_FORUMS_STORIES WHERE NOT EXISTS ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = STORY_ID );
GO

DELETE FROM HOMEPAGE.NR_WIKIS_STORIES WHERE NOT EXISTS ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = STORY_ID );
GO

DELETE FROM HOMEPAGE.NR_TAGS_STORIES WHERE NOT EXISTS ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = STORY_ID );
GO


DROP TABLE HOMEPAGE.NR_ORGPERSON_FOLLOW;
GO


-- Moving on the migration TOP the drop of some indexes.
-- This should speed up the updates
-- 71040: Homepage and News migration is too slow while migrating very large dataset, oracle.

DROP INDEX NR_COM_PER_STORIES_READER ON HOMEPAGE.NR_COMM_PERSON_STORIES;

DROP INDEX NR_COM_PER_STORIES_STORY_ID ON HOMEPAGE.NR_COMM_PERSON_STORIES;

DROP INDEX COMM_PERSON_STORIES_ITEM_ID ON HOMEPAGE.NR_COMM_PERSON_STORIES;

DROP INDEX NR_COMM_PERSON_STORIES_DATE ON HOMEPAGE.NR_COMM_PERSON_STORIES;

GO

----

DROP INDEX RESPONSES_STORIES_IDX ON HOMEPAGE.NR_RESPONSES_STORIES;

DROP INDEX RESPONSES_STORIES_CIDX ON HOMEPAGE.NR_RESPONSES_STORIES;

DROP INDEX RESPONSES_STORIES_SIDX ON HOMEPAGE.NR_RESPONSES_STORIES;

DROP INDEX RESPONSES_STORIES_ITEM_ID ON HOMEPAGE.NR_RESPONSES_STORIES;

DROP INDEX NR_RESPONSES_STORIES_DATE ON HOMEPAGE.NR_RESPONSES_STORIES;

GO

----

DROP INDEX PROFILES_STORIES_IDX ON HOMEPAGE.NR_PROFILES_STORIES;

DROP INDEX PROFILES_STORIES_CIDX ON HOMEPAGE.NR_PROFILES_STORIES;

DROP INDEX PROFILES_STORIES_SIDX ON HOMEPAGE.NR_PROFILES_STORIES;

DROP INDEX PROFILES_STORIES_ITEM_ID ON HOMEPAGE.NR_PROFILES_STORIES;

DROP INDEX NR_PROFILES_STORIES_DATE ON HOMEPAGE.NR_PROFILES_STORIES;


GO

----

DROP INDEX ACTIVITIES_STORIES_IDX ON HOMEPAGE.NR_ACTIVITIES_STORIES;

DROP INDEX ACTIVITIES_STORIES_CIDX ON HOMEPAGE.NR_ACTIVITIES_STORIES;

DROP INDEX ACTIVITIES_STORIES_SIDX ON HOMEPAGE.NR_ACTIVITIES_STORIES;

DROP INDEX ACTIVITIES_STORIES_ITEM_ID ON HOMEPAGE.NR_ACTIVITIES_STORIES;

DROP INDEX NR_ACTIVITIES_STORIES_DATE ON HOMEPAGE.NR_ACTIVITIES_STORIES;

GO

----

DROP INDEX BLOGS_STORIES_IDX ON HOMEPAGE.NR_BLOGS_STORIES;

DROP INDEX BLOGS_STORIES_CIDX ON HOMEPAGE.NR_BLOGS_STORIES;

DROP INDEX BLOGS_STORIES_SIDX ON HOMEPAGE.NR_BLOGS_STORIES;

DROP INDEX BLOGS_STORIES_ITEM_ID ON HOMEPAGE.NR_BLOGS_STORIES;

DROP INDEX NR_BLOGS_STORIES_DATE ON HOMEPAGE.NR_BLOGS_STORIES;

GO

---
DROP INDEX FILES_STORIES_IDX ON HOMEPAGE.NR_FILES_STORIES;

DROP INDEX FILES_STORIES_CIDX ON HOMEPAGE.NR_FILES_STORIES;

DROP INDEX FILES_STORIES_SIDX ON HOMEPAGE.NR_FILES_STORIES;

DROP INDEX FILES_STORIES_ITEM_ID ON HOMEPAGE.NR_FILES_STORIES;

DROP INDEX NR_FILES_STORIES_DATE ON HOMEPAGE.NR_FILES_STORIES;

GO
---

DROP INDEX FORUMS_STORIES_IDX ON HOMEPAGE.NR_FORUMS_STORIES;

DROP INDEX FORUMS_STORIES_CIDX ON HOMEPAGE.NR_FORUMS_STORIES;

DROP INDEX FORUMS_STORIES_SIDX ON HOMEPAGE.NR_FORUMS_STORIES;

DROP INDEX FORUMS_STORIES_ITEM_ID ON HOMEPAGE.NR_FORUMS_STORIES;

DROP INDEX NR_FORUMS_STORIES_DATE ON HOMEPAGE.NR_FORUMS_STORIES;

GO
---

DROP INDEX WIKIS_STORIES_IDX ON HOMEPAGE.NR_WIKIS_STORIES;

DROP INDEX WIKIS_STORIES_CIDX ON HOMEPAGE.NR_WIKIS_STORIES;

DROP INDEX WIKIS_STORIES_SIDX ON HOMEPAGE.NR_WIKIS_STORIES;

DROP INDEX WIKIS_STORIES_ITEM_ID ON HOMEPAGE.NR_WIKIS_STORIES;

DROP INDEX NR_WIKIS_STORIES_DATE ON HOMEPAGE.NR_WIKIS_STORIES;

GO
---

DROP INDEX TAGS_STORIES_IDX ON HOMEPAGE.NR_TAGS_STORIES;

DROP INDEX TAGS_STORIES_CIDX ON HOMEPAGE.NR_TAGS_STORIES;

DROP INDEX TAGS_STORIES_SIDX ON HOMEPAGE.NR_TAGS_STORIES;

DROP INDEX TAGS_STORIES_ITEM_ID ON HOMEPAGE.NR_TAGS_STORIES;

DROP INDEX NR_TAGS_STORIES_DATE ON HOMEPAGE.NR_TAGS_STORIES;

GO

-----------------------------------------------------------
--  HOMEPAGE.MTCONFIG
-----------------------------------------------------------

CREATE TABLE HOMEPAGE.MTCONFIG (
	UUID			nvarchar(36) NOT NULL,
	SCOPE 			nvarchar(36) NOT NULL,
	ID 				nvarchar(128) NOT NULL,
	CONFIG_VALUE 	nvarchar(1024),
	SERVICE 		nvarchar(256),
	NAME	 		nvarchar(64),
	DESCRIPTION		nvarchar(64),
	OVERRIDABLE 	NUMERIC(5,0) DEFAULT 1 NOT NULL,
	ISPOLICY		NUMERIC(5,0) DEFAULT 0 NOT NULL,
	ADMIN_VIS		NUMERIC(5,0) DEFAULT 1 NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.MTCONFIG
	 ADD CONSTRAINT PK_ID PRIMARY KEY (UUID);
	 
ALTER TABLE HOMEPAGE.MTCONFIG
	ADD CONSTRAINT UNIQUE_ID UNIQUE (SCOPE,ID);

CREATE INDEX SETTINGS_BY_ID
    ON HOMEPAGE.MTCONFIG (ID);


------------------------------------------------
-- NR_SOURCE_TYPE
------------------------------------------------

CREATE TABLE HOMEPAGE.NR_SOURCE_TYPE (
	SOURCE_TYPE_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0) NOT NULL, -- numeric that is 1,2,3 etc.. 100, 101..
	SOURCE nvarchar(36) NOT NULL,
	DISPLAY_NAME nvarchar(4000),
	IMAGE_URL nvarchar(2048),
	PUBLISHED DATETIME,
	UPDATED DATETIME,	
	IS_ENABLED NUMERIC(5,0),
	SUMMARY nvarchar (4000),
	ORGANIZATION_ID nvarchar(36),
	URL nvarchar(2048),
	URL_SSL nvarchar(2048),
	IMAGE_URL_SSL nvarchar(2048)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
  	ADD CONSTRAINT PK_SRC_TYPE_ID PRIMARY KEY(SOURCE_TYPE_ID);

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
	ADD CONSTRAINT SRC_TYPE_UNQ UNIQUE(SOURCE_TYPE);

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
	ADD CONSTRAINT SRC_UNQ UNIQUE(SOURCE);

GO	


---------------------------------------------------------------------------------
-- SPR #JSTH8EZJXT - Removing dups records from HP_UI table and others issues like EMD delviery locks
---------------------------------------------------------------------------------

DELETE FROM HOMEPAGE.HP_WIDGET_INST WHERE WIDGET_INST_ID IN 
(
	SELECT HP_WIDGET_INST.WIDGET_INST_ID
	FROM HOMEPAGE.HP_WIDGET_INST HP_WIDGET_INST,
		(
			SELECT  	HP_TAB_INST.TAB_INST_ID
			FROM 	HOMEPAGE.HP_TAB_INST HP_TAB_INST,
					(
						SELECT UI_ID 
						FROM HOMEPAGE.HP_UI HP_UI 
						INNER JOIN (
								SELECT PERSON_ID,MAX(LAST_VISIT) AS MAX_LAST_VISIT 	FROM HOMEPAGE.HP_UI 
								GROUP BY PERSON_ID 
								HAVING COUNT(*) > 1) T ON HP_UI.PERSON_ID = T.PERSON_ID
						WHERE HP_UI.LAST_VISIT < T.MAX_LAST_VISIT	) TMP_UI
			WHERE	HP_TAB_INST.UI_ID = TMP_UI.UI_ID
		) TMP_TAB_INST
	WHERE HP_WIDGET_INST.TAB_INST_ID = TMP_TAB_INST.TAB_INST_ID
);

GO

DELETE FROM HOMEPAGE.HP_TAB_INST WHERE TAB_INST_ID IN 
(
	SELECT  HP_TAB_INST.TAB_INST_ID
	FROM HOMEPAGE.HP_TAB_INST HP_TAB_INST,
		(
			SELECT UI_ID 
			FROM HOMEPAGE.HP_UI HP_UI 
			INNER JOIN (
					SELECT PERSON_ID,MAX(LAST_VISIT) AS MAX_LAST_VISIT 	FROM HOMEPAGE.HP_UI 
					GROUP BY PERSON_ID 
					HAVING COUNT(*) > 1) T ON HP_UI.PERSON_ID = T.PERSON_ID
			WHERE HP_UI.LAST_VISIT < T.MAX_LAST_VISIT	) TMP_UI
	WHERE	HP_TAB_INST.UI_ID = TMP_UI.UI_ID
);

GO

DELETE FROM HOMEPAGE.HP_UI WHERE UI_ID IN
(
	SELECT UI_ID 
	FROM HOMEPAGE.HP_UI HP_UI 
	INNER JOIN (
			SELECT PERSON_ID,MAX(LAST_VISIT) AS MAX_LAST_VISIT 	FROM HOMEPAGE.HP_UI 
			GROUP BY PERSON_ID 
			HAVING COUNT(*) > 1) T ON HP_UI.PERSON_ID = T.PERSON_ID
	WHERE HP_UI.LAST_VISIT < T.MAX_LAST_VISIT	
);

GO



--------------------------------------------------------------------------------------------
-- [start] 49953 -  At migration time we lose some informations regarding few stories.
--------------------------------------------------------------------------------------------
-- Before to start migration we need to sanitize the source name of the following source
-- tag.activities
-- tag.blogs
-- tag.communities
-- tag.files
-- tag.homepage
-- tag.profiles
-- tag.wikis
--------------------------------------------------------------------------------------------
UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
GO

--------------------------------

-- HOMEPAGE.NR_RESPONSES_STORIES
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
GO

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
GO

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
GO

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
GO

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
GO

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
GO

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
GO

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
GO


-- HOMEPAGE.NR_PROFILES_STORIES
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
GO

-- HOMEPAGE.NR_COMMUNITIES_STORIES
UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
GO

UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
GO

UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
GO

UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
GO

UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
GO

UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
GO

UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
GO

UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
GO

-- HOMEPAGE.NR_ACTIVITIES_STORIES
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
GO

-- HOMEPAGE.NR_BLOGS_STORIES
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
GO

-- HOMEPAGE.NR_BOOKMARKS_STORIES
UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
GO

UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
GO

UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
GO

UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
GO

UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
GO

UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
GO

UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
GO

UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
GO

-- HOMEPAGE.NR_FILES_STORIES
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
GO

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
GO

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
GO

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
GO

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
GO

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
GO

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
GO

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
GO

-- HOMEPAGE.NR_FORUMS_STORIES
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
GO

-- HOMEPAGE.NR_WIKIS_STORIES
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
GO

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
GO

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
GO

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
GO

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
GO

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
GO

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
GO

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
GO

-- HOMEPAGE.NR_TAGS_STORIES
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
GO

-----------------------------------

-- HOMEPAGE.NR_COMM_PERSON_STORIES
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
GO

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
GO

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
GO

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
GO

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
GO

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
GO

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
GO

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
GO

--HOMEPAGE.NR_NEWS_DISCOVERY
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
GO

--HOMEPAGE.NR_NEWS_SAVED
UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
GO

--------------------------------------------------------------------------------------------
-- [end] 49953 -  At migration time we lose some informations regarding few stories.
--------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-- [START] PMR 48211,L6Q,000: Fixing Entries in following reletionship after migration
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
CREATE VIEW HOMEPAGE.TMP_FOLLOWS AS ( 
   SELECT  NR_SUBSCRIPTION.PERSON_ID PERSON_ID, TEMP.CONTAINER_ID FOLLOWED_CONTAINER, RESOURCE_ID
    FROM    HOMEPAGE.NR_SUBSCRIPTION NR_SUBSCRIPTION, HOMEPAGE.NR_RESOURCE NR_RESOURCE,
            ( 
            SELECT NR_SOURCE.CONTAINER_ID, NR_SOURCE.SOURCE_ID 
            FROM HOMEPAGE.NR_SOURCE NR_SOURCE, HOMEPAGE.PERSON PERSON 
            WHERE SOURCE = 'profiles' AND PERSON.PERSON_ID = NR_SOURCE.CONTAINER_ID 
            ) TEMP 
    WHERE   IS_EXPLICIT = 0 AND IS_ACTIVE = 1 AND NR_SUBSCRIPTION.SOURCE_ID = TEMP.SOURCE_ID AND TEMP.CONTAINER_ID = NR_RESOURCE.CONTAINER_ID
);
GO

DELETE FROM HOMEPAGE.NR_FOLLOWS WHERE EXISTS 
	(SELECT 1 FROM HOMEPAGE.TMP_FOLLOWS TMP_FOLLOWS WHERE NR_FOLLOWS.PERSON_ID = TMP_FOLLOWS.PERSON_ID AND NR_FOLLOWS.RESOURCE_ID = TMP_FOLLOWS.RESOURCE_ID );
GO
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-- [END] PMR 48211,L6Q,000: Fixing Entries in following reletionship after migration
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------

-------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------
-- PRE sanitize sql as we start from a 3.0.1 and we need to migrate it to 4.0

DROP INDEX NR_ATT_STORY_ID ON HOMEPAGE.NR_ATTACHMENT;
DROP INDEX NR_REC_STORY_ID ON HOMEPAGE.NR_RECOMMENDATION;
DROP INDEX NR_RECOMMENDER_STORY_ID ON HOMEPAGE.NR_RECOMMENDATION;

ALTER TABLE HOMEPAGE.NR_ATTACHMENT DROP CONSTRAINT PK_ATTACHMENT;
ALTER TABLE HOMEPAGE.NR_RECOMMENDATION DROP CONSTRAINT PK_RECOMMENDATION;


EXEC sp_rename 'HOMEPAGE.NR_ATTACHMENT' 	,		'NR_ATTACHMENT_301';
EXEC sp_rename 'HOMEPAGE.NR_RECOMMENDATION'	, 	'NR_RECOMMENDATION_301';

GO

-------------------------------------------------------
-- RENAMING NATIONWIDE specific table
-------------------------------------------------------

----------------------------------------------------------------
-- SPR #DMCE8ECKYM
----------------------------------------------------------------
-- #1 - Community Forum icon in news stories displays the Community icon
-- When you migrate 2.5.0x content that includes a community with community forum content, 
-- the story icon used in the 3.0.1 system appears to be misleading (Community icon currently  instead of Forum icon). 
-- See screenshot below as an example. This is not a ship stop if fix not included.
UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'forums' WHERE EVENT_NAME LIKE '%forum%';

GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'forums' WHERE EVENT_NAME LIKE '%forum%';

GO

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'forums' WHERE EVENT_NAME LIKE '%forum%';

GO

-- #5 - Icon for Following a person is the Homepage icon instead of Profiles icon
-- In LC 2.5.0.3, you will see a story for adding a user to your Homepage Watchlist. This story has a Homepage icon when migrated to LC 3.0.1
-- Ideally this story should have a "Profiles" icon in LC 3.0.1, as this is now a profiles story - profiles.person.followed
UPDATE HOMEPAGE.NR_STORIES  SET SOURCE = 'profiles' WHERE EVENT_NAME = 'profiles.person.followed';

GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY  SET SOURCE = 'profiles' WHERE EVENT_NAME = 'profiles.person.followed';

GO


UPDATE HOMEPAGE.NR_NEWS_SAVED  SET SOURCE = 'profiles' WHERE EVENT_NAME = 'profiles.person.followed';

GO



----------------------------------------------------------------------
-- HOMEPAGE.PERSON
----------------------------------------------------------------------
-- add columns:
-- "PROF_TYPE" VARCHAR(128)
-- "CREATION_DATE" TIMESTAMP
-- "THEME_ID" VARCHAR(36)
-- "COMM_VISIBILITY" SMALLINT
-- "MEMBER_TYPE" SMALLINT NOT NULL WITH DEFAULT 0
-- "ORGANIZATION_ID" VARCHAR(36)
-- "COMMUNITY_INTERNAL_ONLY"
--
-- remove columns:
--
-- add indexes:
-- CREATE INDEX "HOMEPAGE"."DISPLAYNAME_LOWER_MEM"
-- CREATE UNIQUE INDEX "HOMEPAGE"."PERSON_IDX"
--
-- remove indexes:
--
----------------------------------------------------------------------

ALTER TABLE HOMEPAGE.PERSON ADD 
	PROF_TYPE nvarchar(128),
	CREATION_DATE DATETIME,
	THEME_ID nvarchar(36),
	COMM_VISIBILITY NUMERIC(5 ,0),	
	MEMBER_TYPE NUMERIC(5 ,0) NOT NULL DEFAULT 0,
	ORGANIZATION_ID nvarchar(36),
	COMMUNITY_INTERNAL_ONLY NUMERIC(5 ,0);

GO

CREATE INDEX DISPLAYNAME_LOWER_MEM
	ON HOMEPAGE.PERSON (DISPLAYNAME_LOWER, MEMBER_TYPE);

CREATE UNIQUE INDEX PERSON_IDX   
	ON HOMEPAGE.PERSON (PERSON_ID) INCLUDE (USER_MAIL, DISPLAYNAME);
GO

INSERT INTO HOMEPAGE.PERSON
		(PERSON_ID,DISPLAYNAME,EXID,STATE,MEMBER_TYPE)
VALUES  ('00000000-0000-0000-0000-000000000001','%anyone','00000000-0000-0000-0000-000000000001',0,2);

GO

UPDATE 	HOMEPAGE.PERSON SET MEMBER_TYPE = 0, CREATION_DATE = LAST_UPDATE;
GO

UPDATE HOMEPAGE.PERSON SET MEMBER_TYPE = 2 WHERE PERSON_ID = '00000000-0000-0000-0000-000000000000';
GO



----------------------------------------------------------------------
-- HOMEPAGE.HP_UI
----------------------------------------------------------------------
-- add columns:
-- "LAST_ACTIONABLE_VISIT" TIMESTAMP
-- "WELCOME_NOTE" SMALLINT DEFAULT 1
--
-- remove columns:
-- 
-- add indexes:
-- CREATE INDEX "HOMEPAGE"."HP_UI"
-- CREATE UNIQUE INDEX "HOMEPAGE"."HP_UI_PERSONID"
-- 
-- remove indexes:
-- HP_UI_PERSON_ID_INDEX
----------------------------------------------------------------------

ALTER TABLE HOMEPAGE.HP_UI ADD 
	LAST_ACTIONABLE_VISIT DATETIME,
	WELCOME_NOTE NUMERIC(5,0) DEFAULT 1;
GO

DROP INDEX HP_UI_PERSON_ID_INDEX ON HOMEPAGE.HP_UI;

-- 65156
-- We perform an additional delete for edge case when two records have the same value for the LAST_VISIT
-- 76000: SQL0532N  A parent row cannot be deleted because the relationship "HOMEPAGE.HP_TAB_INST.FK_UI_ID" restricts the deletion.  SQLSTATE=23504
-- Fixing Edge Case where there are some records with the same LAST_VISIT values, In oder we need to follow the FK. So we delete:
-- HP_WIDGET_INST, HP_TAB_INST, HP_UI
DELETE FROM HOMEPAGE.HP_WIDGET_INST WHERE WIDGET_INST_ID IN 
(
	SELECT HP_WIDGET_INST.WIDGET_INST_ID
	FROM HOMEPAGE.HP_WIDGET_INST HP_WIDGET_INST,
		(
			SELECT  HP_TAB_INST.TAB_INST_ID
			FROM HOMEPAGE.HP_TAB_INST HP_TAB_INST,
		 	(
				SELECT MAX (UI_ID) MAX_UI_ID 
				FROM HOMEPAGE.HP_UI 
				GROUP BY PERSON_ID HAVING COUNT(*) > 1
			) TMP_UI
			WHERE	HP_TAB_INST.UI_ID = TMP_UI.MAX_UI_ID			
		) TMP_TAB_INST
	WHERE HP_WIDGET_INST.TAB_INST_ID = TMP_TAB_INST.TAB_INST_ID
);

GO

DELETE FROM HOMEPAGE.HP_TAB_INST WHERE TAB_INST_ID IN 
(
	SELECT  HP_TAB_INST.TAB_INST_ID
	FROM HOMEPAGE.HP_TAB_INST HP_TAB_INST,
 	(
		SELECT MAX (UI_ID) MAX_UI_ID 
		FROM HOMEPAGE.HP_UI 
		GROUP BY PERSON_ID HAVING COUNT(*) > 1
	) TMP_UI
	WHERE	HP_TAB_INST.UI_ID = TMP_UI.MAX_UI_ID
);

GO

DELETE FROM HOMEPAGE.HP_UI WHERE UI_ID IN ( 
	SELECT MAX (UI_ID) MAX_UI_ID 
	FROM HOMEPAGE.HP_UI 
	GROUP BY PERSON_ID HAVING COUNT(*) > 1 
);

GO

CREATE UNIQUE INDEX HP_UI_PERSONID
	ON HOMEPAGE.HP_UI (PERSON_ID);
	
GO	

CREATE INDEX HP_UI 
	ON HOMEPAGE.HP_UI (LAST_VISIT ASC);	

GO

UPDATE HOMEPAGE.HP_UI SET LAST_ACTIONABLE_VISIT = CURRENT_TIMESTAMP;
GO



----------------------------------------------------------------------
-- HOMEPAGE.HP_TAB_INST
----------------------------------------------------------------------
-- add columns:
--
--
-- remove columns:
--
-- add indexes:
-- CREATE INDEX "HOMEPAGE"."TAB_INST_TAB_ID"
--
-- remove indexes:
--
----------------------------------------------------------------------

CREATE INDEX TAB_INST_TAB_ID 
	ON HOMEPAGE.HP_TAB_INST (TAB_ID);
GO	



----------------------------------------------------------------------
-- HOMEPAGE.WIDGET
----------------------------------------------------------------------
-- add columns:
--  "IS_GADGET" SMALLINT WITH DEFAULT 0
--
-- remove columns:
--
-- add indexes:
-- 
--
-- remove indexes:
--
----------------------------------------------------------------------

ALTER TABLE HOMEPAGE.WIDGET ADD
	IS_GADGET NUMERIC(5,0) DEFAULT 0,
	WIDGET_POLICY_FLAGS NUMERIC(5,0) DEFAULT 1 NOT NULL,
	WIDGET_EXT_PROPERTIES nvarchar(2048);
GO

UPDATE HOMEPAGE.WIDGET SET IS_GADGET = 0;

GO

/* Bookmarks widgets */
UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png'
WHERE WIDGET_ID='dogear46x0a77x4a43x82aaxb00187218631';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png'
WHERE WIDGET_ID='dembk46x0a77x4a43x82aaxb00187218631';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png'
WHERE WIDGET_ID='depbk46x0a77x4a43x82aaxb00187218631';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png'
WHERE WIDGET_ID='derbk46x0a77x4a43x82aaxb00187218631';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png'
WHERE WIDGET_ID='dewl46x0a77x4a43x82aaxb00187218631';

/* Activities widgets */
UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png'
WHERE WIDGET_ID='activitixa187x491dxa4bfx2e1261d0b6ec';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png'
WHERE WIDGET_ID='myactxa187x491dxa4bfx2e1261d0b6ec';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png'
WHERE WIDGET_ID='pubactxa187x491dxa4bfx2e1261d0b6ec';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png'
WHERE WIDGET_ID='activities-sidebar7x4229x8';

/* Communities widgets. */
UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png'
WHERE WIDGET_ID='communitxe7c4x4e08xab54x80e7a4eb8933';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png'
WHERE WIDGET_ID='mycommunxe7c4x4e08xab54x80e7a4eb8933';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png'
WHERE WIDGET_ID='pubcommuxe7c4x4e08xab54x80e7a4eb8933';

/* Blogs widget. */
UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBlogs16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBlogs16.png'
WHERE WIDGET_ID='blogs448xcd34x4565x9469x9c34fcefe48c';

/* Profiles widget. */
UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png'
WHERE WIDGET_ID='profilesxaac7x4229x87bbx9a1c3551c591';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png'
WHERE WIDGET_ID='myprofisxaac7x4229x87bbx9a1c3551c591';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png'
WHERE WIDGET_ID='colprofsxaac7x4229x87bbx9a1c3551c591';

/* Wiki widget. */
UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png'
WHERE WIDGET_ID='mywikiz1xaac7x4229x87BBx91ac3551c591';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png'
WHERE WIDGET_ID='pop-wiki1xaac7x4229x87BBx91ac3551c5';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png'
WHERE WIDGET_ID='latest-wiki5jz1xaac7x4229x87BBx91ac';

/* Files widget. */
UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png'
WHERE WIDGET_ID='myFilesPb86locI7vRV4yY1KKawZvE8Qul88';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png'
WHERE WIDGET_ID='sharedFilesV4fv72LD5NAcGv2nbrex0ExEq';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png'
WHERE WIDGET_ID='sharedFilesV4fv72LD5NAcGv2nbrex0ExEq';

GO

UPDATE HOMEPAGE.WIDGET SET WIDGET_SYSTEM = 1 WHERE WIDGET_TITLE = '%widget.sand.recommend.name';

GO



----------------------------------------------------------------------
-- HOMEPAGE.NT_NOTIFICATION
----------------------------------------------------------------------
-- add columns:
--  "NOTIFICATION_SOURCE_TYPE" SMALLINT
--
-- remove columns:
--
-- add indexes:
-- 
--
-- remove indexes:
--
----------------------------------------------------------------------

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


----------------------------------------------------------------------
-- HOMEPAGE.NR_SOURCE
----------------------------------------------------------------------
-- add columns:
--  "SOURCE_TYPE" SMALLINT
--
-- remove columns:
--
-- add indexes:
-- 
--
-- remove indexes:
--
----------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_SOURCE
	ADD SOURCE_TYPE NUMERIC(5,0);
GO

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities';
GO

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs';
GO

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities';
GO

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis';
GO

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles';
GO

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage';
GO

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear';
GO

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 8 WHERE SOURCE = 'files';
GO

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums';
GO	

----------------------------------------------------------------------
-- HOMEPAGE.NR_NEWS_SAVED
----------------------------------------------------------------------
-- add columns:
--  "SOURCE_TYPE" SMALLINT
--
-- remove columns:
--
-- add indexes:
-- 
--
-- remove indexes:
--
----------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_NEWS_SAVED
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities';
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs';
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities';
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis';
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles';
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage';
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear';
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 8 WHERE SOURCE = 'files';
GO

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums';
GO	


----------------------------------------------------------------------
-- HOMEPAGE.NR_NEWS_DISCOVERY
----------------------------------------------------------------------
-- add columns:
--  "SOURCE_TYPE" SMALLINT 
--
-- remove columns:
--
-- add indexes:
-- 
--
-- remove indexes:
--
----------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_NEWS_DISCOVERY
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO	

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= 'f..f' ;
GO



--------------------------------------------
-- ADDING SOURCE_TYPE - NR_RESOURCE
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_RESOURCE
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO	

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities';
GO

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs';
GO

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities';
GO

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis';
GO

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles';
GO

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage';
GO

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear';
GO

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 8 WHERE SOURCE = 'files';
GO

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums';
GO	
	


--------------------------------------------
-- ADDING SOURCE_TYPE - NR_COMM_STORIES
--------------------------------------------

ALTER TABLE HOMEPAGE.NR_COMM_STORIES
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO	

UPDATE HOMEPAGE.NR_COMM_STORIES
	SET SOURCE_TYPE = 3;
GO



--------------------------------------------
-- ADDING SOURCE_TYPE - NR_COMM_PERSON_STORIES
--------------------------------------------	

ALTER TABLE HOMEPAGE.NR_COMM_PERSON_STORIES
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO	

-- partioned update



UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= 'f..f' ;
GO


-- categories tables


--------------------------------------------
-- ADDING SOURCE_TYPE - NR_RESPONSES_STORIES
--------------------------------------------

ALTER TABLE HOMEPAGE.NR_RESPONSES_STORIES
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO	

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO



--------------------------------------------
-- ADDING SOURCE_TYPE - NR_PROFILES_STORIES
--------------------------------------------	

ALTER TABLE HOMEPAGE.NR_PROFILES_STORIES
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO



--------------------------------------------
-- ADDING SOURCE_TYPE - NR_COMMUNITIES_STORIES (never used)
--------------------------------------------	

ALTER TABLE HOMEPAGE.NR_COMMUNITIES_STORIES
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO



--------------------------------------------
-- ADDING SOURCE_TYPE - NR_ACTIVITIES_STORIES
--------------------------------------------	

ALTER TABLE HOMEPAGE.NR_ACTIVITIES_STORIES
	ADD SOURCE_TYPE NUMERIC(5,0);
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO



--------------------------------------------
-- ADDING SOURCE_TYPE - NR_BLOGS_STORIES
--------------------------------------------	

ALTER TABLE HOMEPAGE.NR_BLOGS_STORIES
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO



--------------------------------------------
-- ADDING SOURCE_TYPE - NR_BOOKMARKS_STORIES (never used)
--------------------------------------------	

ALTER TABLE HOMEPAGE.NR_BOOKMARKS_STORIES
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO	



--------------------------------------------
-- ADDING SOURCE_TYPE - NR_FILES_STORIES
--------------------------------------------	

ALTER TABLE HOMEPAGE.NR_FILES_STORIES
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO	

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO



--------------------------------------------
-- ADDING SOURCE_TYPE - NR_FORUMS_STORIES
--------------------------------------------	

ALTER TABLE HOMEPAGE.NR_FORUMS_STORIES
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO



--------------------------------------------
-- ADDING SOURCE_TYPE - NR_WIKIS_STORIES
--------------------------------------------	

ALTER TABLE HOMEPAGE.NR_WIKIS_STORIES
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO	

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO



--------------------------------------------
-- ADDING SOURCE_TYPE - NR_TAGS_STORIES
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_TAGS_STORIES
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
GO



----------------------------------------------------------------------
-- HOMEPAGE.NR_NEWS_STATUS_NETWORK
----------------------------------------------------------------------
-- add columns:
--
-- remove columns:
--
-- add indexes:
-- CREATE UNIQUE INDEX "HOMEPAGE"."NR_SN_ITEM_READ_UNQ"
--
-- remove indexes:
--
----------------------------------------------------------------------

DELETE FROM HOMEPAGE.NR_NEWS_STATUS_NETWORK WHERE NEWS_STATUS_NETWORK_ID IN (
	SELECT NR_NEWS_STATUS_NETWORK.NEWS_STATUS_NETWORK_ID
		FROM (
			SELECT MAX(NEWS_STATUS_NETWORK_ID) NEWS_STATUS_NETWORK_ID, ITEM_ID, READER_ID
			FROM HOMEPAGE.NR_NEWS_STATUS_NETWORK
			GROUP BY 	ITEM_ID, READER_ID
			) T,
			 HOMEPAGE.NR_NEWS_STATUS_NETWORK NR_NEWS_STATUS_NETWORK
		WHERE 	NR_NEWS_STATUS_NETWORK.NEWS_STATUS_NETWORK_ID < T.NEWS_STATUS_NETWORK_ID AND 
				NR_NEWS_STATUS_NETWORK.ITEM_ID = T.ITEM_ID AND
				NR_NEWS_STATUS_NETWORK.READER_ID = T.READER_ID
);

GO

CREATE UNIQUE INDEX NR_SN_ITEM_READ_UNQ
	ON HOMEPAGE.NR_NEWS_STATUS_NETWORK (ITEM_ID, READER_ID); 
	
GO



----------------------------------------------------------------------
-- HOMEPAGE.NR_COMM_STORIES
----------------------------------------------------------------------
-- add columns:
--  "SOURCE_TYPE" SMALLINT 
--
-- remove columns:
--
-- add indexes:
-- CREATE INDEX "HOMEPAGE"."NR_COMM_STORIES_CDATE"
-- CREATE INDEX "HOMEPAGE"."NR_COM_STORY_ID"
--
-- remove indexes:
-- "HOMEPAGE"."NR_COMM_STORIES_COM_ID"
----------------------------------------------------------------------
CREATE INDEX NR_COMM_STORIES_CDATE
    ON HOMEPAGE.NR_COMM_STORIES (COMMUNITY_ID, CREATION_DATE ASC);
GO

CREATE INDEX NR_COM_STORY_ID			
	ON HOMEPAGE.NR_COMM_STORIES (COMMUNITY_ID, STORY_ID);    
GO

----------------------------------------------------------------------
-- HOMEPAGE.NR_STORIES_CONTENT
----------------------------------------------------------------------
-- add columns:
--  "SOURCE_TYPE" SMALLINT
--  "ACTIVITY_META_DATA" CLOB
--  "ITEM_CONTENT" CLOB
--  "ITEM_CORRELATION_CONTENT"
--  "ITEM_CONTENT_FORMAT" SMALLINT  
--  "ITEM_CORRELATION_FORMAT" SMALLINT
--  "CONTENT_FORMAT" SMALLINT
--
-- remove columns:
--
-- add indexes:
--
--
-- remove indexes:
--
----------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO	

-- TODO: optimize those updates (see with a large data set if this query can be changed into:
--UPDATE HOMEPAGE.NR_STORIES_CONTENT SC SET SOURCE_TYPE = 1 WHERE EXISTS (	
--		SELECT 	1
--		FROM 	HOMEPAGE.NR_STORIES NR_STORIES
--		WHERE  	SC.STORY_ID = NR_STORIES.STORY_ID  AND
--				NR_STORIES.SOURCE = 'activities'
--);
-- an a small dataset I see that it is worst to use exist

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 1 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'activities'
);
GO

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 2 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'blogs'
);
GO

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 3 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'communities'
);
GO

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 4 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'wikis'
);
GO

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 5 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'profiles'
);
GO

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 6 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'homepage'
);
GO

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 7 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'dogear'
);
GO

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 8 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'files'
);
GO

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 9 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'forums'
);
GO

ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT ADD 
	ITEM_CONTENT VARCHAR(MAX),	
	ITEM_CORRELATION_CONTENT VARCHAR(MAX),
	ITEM_CONTENT_FORMAT  NUMERIC(5 ,0),
	ITEM_CORRELATION_FORMAT  NUMERIC(5 ,0),
	CONTENT_FORMAT NUMERIC(5 ,0),
	ACTIVITY_META_DATA VARCHAR(MAX),
	ACTIVITY_META_DATA_1 nvarchar(3584),
	ACTIVITY_META_DATA_2 nvarchar(3584),
	IS_META_DATA_TRUNCATED NUMERIC(5,0),
	ITEM_BRIEF_DESC nvarchar(4000),
	ITEM_CORRELATION_BRIEF_DESC nvarchar(4000),
	ITEM_TAGS nvarchar(1024),
	ITEM_CORRELATION_TAGS nvarchar(1024);

GO



----------------------------------------------------------------------
-- HOMEPAGE.NR_NETWORK
----------------------------------------------------------------------
-- add columns:
--
-- remove columns:
--
-- add indexes:
-- CREATE UNIQUE INDEX "HOMEPAGE"."COLL_PERSON_IDX"
--
-- remove indexes:
----------------------------------------------------------------------

DELETE FROM HOMEPAGE.NR_NETWORK WHERE NETWORK_ID IN (
	select NR_NETWORK.NETWORK_ID
	from (
		SELECT MAX(NETWORK_ID) NETWORK_ID, PERSON_ID, COLLEAGUE_ID 
		FROM HOMEPAGE.NR_NETWORK
		GROUP BY 	PERSON_ID, COLLEAGUE_ID
		) T,
		 HOMEPAGE.NR_NETWORK NR_NETWORK
	where 	NR_NETWORK.NETWORK_ID < T.NETWORK_ID AND 
			NR_NETWORK.PERSON_ID = T.PERSON_ID AND
			NR_NETWORK.COLLEAGUE_ID = T.COLLEAGUE_ID		
);

GO


CREATE UNIQUE INDEX COLL_PERSON_IDX 
	ON HOMEPAGE.NR_NETWORK (COLLEAGUE_ID, PERSON_ID);
GO



----------------------------------------------------------------------
-- HOMEPAGE.EMD_RESOURCE_PREF
----------------------------------------------------------------------
-- add columns:
--
-- remove columns:
--
-- add indexes:
-- CREATE UNIQUE INDEX "HOMEPAGE"."EMD_RESOURCE_PREF_UNQ"
--
-- remove indexes:
----------------------------------------------------------------------

-- EMD FIX - ADDING UNIQUE CONSTRAINT
-- TO REMOVE ALL DUPLICATED RECORDS - WE NEED TO EXECUTE THIS QUERY N_TIME IN A PARTITIONED WAY TO DON'T GET FULL THE LOG.
-- THIS IS THE NOT PARTITIONED QUERIES

-- delete using 0
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		WHERE RESOURCE_PREF_ID < '0..f'
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE AND
			EMD_RESOURCE_PREF.RESOURCE_PREF_ID < '0..f'			
);

GO

-- delete using 1
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		WHERE RESOURCE_PREF_ID < '1..f'
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE AND
			EMD_RESOURCE_PREF.RESOURCE_PREF_ID < '1..f'			
);

GO

-- delete using 2
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		WHERE RESOURCE_PREF_ID < '2..f'
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE AND
			EMD_RESOURCE_PREF.RESOURCE_PREF_ID < '2..f'			
);

GO

-- delete using 3
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		WHERE RESOURCE_PREF_ID < '3..f'
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE AND
			EMD_RESOURCE_PREF.RESOURCE_PREF_ID < '3..f'			
);

GO

-- delete using 4
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		WHERE RESOURCE_PREF_ID < '4..f'
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE AND
			EMD_RESOURCE_PREF.RESOURCE_PREF_ID < '4..f'			
);

GO

-- delete using 5
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		WHERE RESOURCE_PREF_ID < '5..f'
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE AND
			EMD_RESOURCE_PREF.RESOURCE_PREF_ID < '5..f'			
);

GO

-- delete using 6
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		WHERE RESOURCE_PREF_ID < '6..f'
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE AND
			EMD_RESOURCE_PREF.RESOURCE_PREF_ID < '6..f'			
);

GO

-- delete using 7
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		WHERE RESOURCE_PREF_ID < '7..f'
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE AND
			EMD_RESOURCE_PREF.RESOURCE_PREF_ID < '7..f'			
);

GO

-- delete using 8
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		WHERE RESOURCE_PREF_ID < '8..f'
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE AND
			EMD_RESOURCE_PREF.RESOURCE_PREF_ID < '8..f'			
);

GO

-- delete using 9
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		WHERE RESOURCE_PREF_ID < '9..f'
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE AND
			EMD_RESOURCE_PREF.RESOURCE_PREF_ID < '9..f'			
);

GO

-- delete using a
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		WHERE RESOURCE_PREF_ID < 'a..f'
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE AND
			EMD_RESOURCE_PREF.RESOURCE_PREF_ID < 'a..f'			
);

GO

-- delete using b
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		WHERE RESOURCE_PREF_ID < 'b..f'
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE AND
			EMD_RESOURCE_PREF.RESOURCE_PREF_ID < 'b..f'			
);

GO

-- delete using c
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		WHERE RESOURCE_PREF_ID < 'c..f'
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE AND
			EMD_RESOURCE_PREF.RESOURCE_PREF_ID < 'c..f'			
);

GO

-- delete using d
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		WHERE RESOURCE_PREF_ID < 'd..f'
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE AND
			EMD_RESOURCE_PREF.RESOURCE_PREF_ID < 'd..f'			
);

GO

-- delete using e
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		WHERE RESOURCE_PREF_ID < 'e..f'
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE AND
			EMD_RESOURCE_PREF.RESOURCE_PREF_ID < 'e..f'			
);

GO

-- The last delete all
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF WHERE RESOURCE_PREF_ID IN (
	select EMD_RESOURCE_PREF.RESOURCE_PREF_ID
	from (
		SELECT MAX(RESOURCE_PREF_ID) RESOURCE_PREF_ID, PERSON_ID, RESOURCE_TYPE 
		FROM HOMEPAGE.EMD_RESOURCE_PREF
		GROUP BY 	PERSON_ID, RESOURCE_TYPE
		) T,
		 HOMEPAGE.EMD_RESOURCE_PREF EMD_RESOURCE_PREF
	where 	EMD_RESOURCE_PREF.RESOURCE_PREF_ID < T.RESOURCE_PREF_ID AND 
			EMD_RESOURCE_PREF.PERSON_ID = T.PERSON_ID AND
			EMD_RESOURCE_PREF.RESOURCE_TYPE = T.RESOURCE_TYPE		
);
GO

-- Add unique constraint:
CREATE UNIQUE INDEX EMD_RESOURCE_PREF_UNQ
	ON HOMEPAGE.EMD_RESOURCE_PREF (PERSON_ID, RESOURCE_TYPE);

GO



----------------------------------------------------------------------
-- HOMEPAGE.EMD_TRANCHE
----------------------------------------------------------------------
-- add columns:
-- "IS_LOCKED_DAILY" SMALLINT NOT NULL WITH DEFAULT 0
-- "IS_LOCKED_WEEKLY" SMALLINT NOT NULL WITH DEFAULT 0
-- "LAST_STARTED_DAILY" TIMESTAMP 
-- "LAST_STARTED_WEEKLY" TIMESTAMP
-- "LAST_EXEC_TIME_DAILY_MIN" INTEGER
-- "LAST_EXEC_TIME_WEEKLY_MIN" INTEGER
-- "LAST_RUNNER_DAILY" VARCHAR(256)
-- "LAST_RUNNER_WEEKLY" VARCHAR(256)
--
--
-- remove columns:
--
-- add indexes:
--
-- remove indexes:
----------------------------------------------------------------------

ALTER TABLE HOMEPAGE.EMD_TRANCHE ADD 
	IS_LOCKED_DAILY NUMERIC(5,0) NOT NULL DEFAULT 0,
	IS_LOCKED_WEEKLY NUMERIC(5,0) NOT NULL DEFAULT 0,
	LAST_STARTED_DAILY DATETIME,
	LAST_STARTED_WEEKLY DATETIME,
	LAST_EXEC_TIME_DAILY_MIN  NUMERIC(10 ,0),
	LAST_EXEC_TIME_WEEKLY_MIN  NUMERIC(10 ,0),
	LAST_RUNNER_DAILY nvarchar(256),
	LAST_RUNNER_WEEKLY nvarchar(256);

GO

UPDATE HOMEPAGE.EMD_TRANCHE	SET 	
		IS_LOCKED = 0,
		IS_LOCKED_DAILY = 0,
		IS_LOCKED_WEEKLY = 0;
GO



----------------------------------------------------------------------
-- HOMEPAGE.EMD_TRANCHE_INFO
----------------------------------------------------------------------
-- add columns:
-- "DOMAIN_AFFINITY" CLOB(1048576)
--
--
-- remove columns:
--
-- add indexes:
--
-- remove indexes:
----------------------------------------------------------------------

--------------------------------------------------------------------------------------
-- START: EMD FIX: changing the DOMAIN_AFFINITY VARCHAR TO BE CLOB 
--------------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.EMD_TRANCHE_INFO
	ADD TMP_DOMAIN_AFFINITY VARCHAR(MAX);
GO

UPDATE HOMEPAGE.EMD_TRANCHE_INFO SET TMP_DOMAIN_AFFINITY = DOMAIN_AFFINITY;
GO

ALTER TABLE HOMEPAGE.EMD_TRANCHE_INFO
	DROP COLUMN DOMAIN_AFFINITY;
GO

ALTER TABLE HOMEPAGE.EMD_TRANCHE_INFO
	ADD DOMAIN_AFFINITY VARCHAR(MAX);
GO

UPDATE HOMEPAGE.EMD_TRANCHE_INFO SET DOMAIN_AFFINITY = TMP_DOMAIN_AFFINITY;
GO

ALTER TABLE HOMEPAGE.EMD_TRANCHE_INFO
	DROP COLUMN TMP_DOMAIN_AFFINITY;
GO



----------------------------------------------------------------------
-- HOMEPAGE.EMD_EMAIL_PREFS
----------------------------------------------------------------------
-- add columns:
-- "REPLYTO_ENABLED" SMALLINT NOT NULL WITH DEFAULT 1
--
--
-- remove columns:
--
-- add indexes:
-- CREATE INDEX "HOMEPAGE"."EMD_EMAIL_PREFS_TR"
--
-- remove indexes:
----------------------------------------------------------------------

ALTER TABLE HOMEPAGE.EMD_EMAIL_PREFS 
	ADD REPLYTO_ENABLED NUMERIC(5,0) NOT NULL DEFAULT 1;

GO

CREATE INDEX EMD_EMAIL_PREFS_TR
    ON HOMEPAGE.EMD_EMAIL_PREFS (TRANCHE_ID);
    
GO



----------------------------------------------------------------------
-- HOMEPAGE.NR_COMM_PERSON_FOLLOW
----------------------------------------------------------------------
-- add columns:
-- "IS_READER_COMM" SMALLINT NOT NULL WITH DEFAULT 0
--
--
-- remove columns:
--
-- add indexes:
--
--
-- remove indexes:
----------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_COMM_PERSON_FOLLOW
	ADD IS_READER_COMM NUMERIC(5,0) DEFAULT 0 NOT NULL;
GO	


UPDATE HOMEPAGE.NR_COMM_PERSON_FOLLOW  SET IS_READER_COMM = 1 WHERE NOT EXISTS (
	SELECT 	1
 	FROM  	HOMEPAGE.PERSON PERSON
 	WHERE 	HOMEPAGE.NR_COMM_PERSON_FOLLOW.PERSON_COMMUNITY_ID = PERSON.PERSON_ID 
);
		
GO	



----------------------------------------------------------------
-- HOMEPAGE.NR_ENTRIES_ARCHIVE
----------------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE (
	ENTRY_ID nvarchar(36) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0),
	CONTAINER_ID nvarchar(256),
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_ID nvarchar(256),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_CREATION_DATE DATETIME NOT NULL,
	ITEM_UPDATE_DATE DATETIME NOT NULL,
	ITEM_TAGS nvarchar(1024),
	N_COMMENTS NUMERIC(5,0) DEFAULT NULL,
	N_RECOMMANDATIONS NUMERIC(5,0) DEFAULT NULL,
	LAST_COMMENT_ID nvarchar(256),
	LAST_DATE_COMMENT DATETIME,
	LAST_AUTHOR_COMMENT nvarchar(36),
	PREV_COMMENT_ID nvarchar(256),
	PREV_DATE_COMMENT DATETIME,
	PREV_AUTHOR_COMMENT nvarchar(36),
	ITEM_ATOM_URL nvarchar(2048),
	PREVIEW_IMAGE_URL nvarchar(2048),
	JSON_META_DATA nvarchar(4000),
	ITEM_SCOPE NUMERIC(5,0),
	IS_LAST_COMMENT_PUBLIC NUMERIC(5,0),
	IS_PREV_COMMENT_PUBLIC NUMERIC(5,0),
	ITEM_CORRELATION_ID nvarchar(256),
	ITEM_CORRELATION_NAME nvarchar(256),
	LAST_DESC_COMMENT nvarchar(4000),
	PREV_DESC_COMMENT nvarchar(4000),
	LAST_UPDATE_DATE_COMMENT DATETIME, 
	PREV_UPDATE_DATE_COMMENT DATETIME,
	ITEM_AUTHOR_UUID nvarchar (256),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (256),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	BRIEF_DESC nvarchar (4000),
	ITEM_CORRELATION_BRIEF_DESC nvarchar (4000),
	IS_LAST_COMMENT_VISIBLE NUMERIC(5,0) DEFAULT 1,
	IS_PREV_COMMENT_VISIBLE NUMERIC(5,0) DEFAULT 1,
	LAST_UPDATE_RECORD DATETIME
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE
    ADD CONSTRAINT PK_ENTRY_AR_ID PRIMARY KEY(ENTRY_ID);

CREATE INDEX NR_ENTRIES_AR_CONT
    ON HOMEPAGE.NR_ENTRIES_ARCHIVE (CONTAINER_ID);

CREATE UNIQUE INDEX NR_ENTRIES_AR_ITEM
    ON HOMEPAGE.NR_ENTRIES_ARCHIVE (ITEM_ID);

GO



----------------------------------------------------------------
-- HOMEPAGE.NR_ENTRIES
----------------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_ENTRIES (
	ENTRY_ID nvarchar(36) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0),
	CONTAINER_ID nvarchar(256),
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	ITEM_ID nvarchar(256),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_CREATION_DATE DATETIME NOT NULL,
	ITEM_UPDATE_DATE DATETIME NOT NULL,
	ITEM_TAGS nvarchar(1024),
	N_COMMENTS NUMERIC(5,0) DEFAULT NULL,
	N_RECOMMANDATIONS NUMERIC(5,0) DEFAULT NULL,
	LAST_COMMENT_ID nvarchar(256),
	LAST_DATE_COMMENT DATETIME,
	LAST_AUTHOR_COMMENT nvarchar(36),
	PREV_COMMENT_ID nvarchar(256),
	PREV_DATE_COMMENT DATETIME,
	PREV_AUTHOR_COMMENT nvarchar(36),
	ITEM_ATOM_URL nvarchar(2048),
	PREVIEW_IMAGE_URL nvarchar(2048),
	JSON_META_DATA nvarchar(4000),
	ITEM_SCOPE NUMERIC(5,0),
	IS_LAST_COMMENT_PUBLIC NUMERIC(5,0),
	IS_PREV_COMMENT_PUBLIC NUMERIC(5,0),
	ITEM_CORRELATION_ID nvarchar(256),
	ITEM_CORRELATION_NAME nvarchar(256),
	LAST_DESC_COMMENT nvarchar(4000),
	PREV_DESC_COMMENT nvarchar(4000),
	LAST_UPDATE_DATE_COMMENT DATETIME, 
	PREV_UPDATE_DATE_COMMENT DATETIME,
	ITEM_AUTHOR_UUID nvarchar (256),
	ITEM_AUTHOR_DISPLAYNAME nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (256),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	BRIEF_DESC nvarchar (4000),
	ITEM_CORRELATION_BRIEF_DESC nvarchar(4000),
	IS_LAST_COMMENT_VISIBLE NUMERIC(5,0) DEFAULT 1,
	IS_PREV_COMMENT_VISIBLE NUMERIC(5,0) DEFAULT 1,
	LAST_UPDATE_RECORD DATETIME
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_ENTRIES
    ADD CONSTRAINT PK_ENTRY_ID PRIMARY KEY(ENTRY_ID);

CREATE INDEX NR_ENTRIES_CONT
    ON HOMEPAGE.NR_ENTRIES (CONTAINER_ID);

CREATE UNIQUE INDEX NR_ENTRIES_ITEM
    ON HOMEPAGE.NR_ENTRIES (ITEM_ID);

GO



----------------------------------------------------------------------
-- HOMEPAGE.NR_STORIES
----------------------------------------------------------------------
-- add columns:
-- "SOURCE_TYPE" SMALLINT
-- "ITEM_ATOM_URL" VARCHAR(2048)
-- "EVENT_TIME" TIMESTAMP , 
-- "VERB" VARCHAR(128) ,
-- "ACTIVITY_META_DATA_1" VARCHAR(3584) FOR BIT DATA , 
-- "ACTIVITY_META_DATA_2" VARCHAR(3584) FOR BIT DATA , 
-- "IS_META_DATA_TRUNCATED" SMALLINT ,
--  ENTRY_ID VARCHAR(36)
-- EVENT_SCOPE SMALLINT
-- ITEM_TAGS VARCHAR(1024)
-- ITEM_SCOPE SMALLINT
-- ITEM_UPDATE_DATE TIMESTAMP
-- ITEM_ID VARCHAR(36)
-- ITEM_NAME VARCHAR(256)
-- ITEM_URL VARCHAR(2048)
-- FIRST_RECIPIENT_ID VARCHAR(36)
-- NUM_RECIPIENTS SMALLINT 
-- PRIMARY_ACTION_URL VARCHAR(768) 
-- SECONDARY_ACTION_URL VARCHAR(768);	
--
-- remove columns:
--
-- add indexes:
--
--
-- remove indexes:
----------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_STORIES
	ADD  SOURCE_TYPE NUMERIC(5,0);
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= 'f..f' ;
GO

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID < '0..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
GO
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= 'f..f' ;
GO


CREATE TABLE HOMEPAGE.NR_STORIES_40 (
	STORY_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
	SOURCE nvarchar(36),
	CONTAINER_ID nvarchar(256),	
	CONTAINER_NAME nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	CONTAINER_ATOM_URL nvarchar(2048),
	ITEM_NAME nvarchar(256),
	ITEM_URL nvarchar(2048),
	ITEM_ATOM_URL nvarchar(2048),
	ITEM_ID nvarchar(256),
	ITEM_CORRELATION_ID nvarchar(256),
	CREATION_DATE DATETIME NOT NULL,
	BRIEF_DESC nvarchar(4000),
	ACTOR_UUID nvarchar(256),
	EVENT_RECORD_UUID nvarchar(36) NOT NULL,
	TAGS nvarchar(1024),
	META_TEMPLATE nvarchar(3328) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE nvarchar(1024),
	R_META_TEMPLATE nvarchar(3328),
	R_TEXT_META_TEMPLATE nvarchar(1024),
	IS_COMMUNITY_STORY NUMERIC(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME nvarchar(256),
	HAS_ATTACHMENT NUMERIC(5,0) DEFAULT 0,
	SOURCE_TYPE NUMERIC(5,0),
	ENTRY_ID nvarchar(36),
	EVENT_TIME DATETIME,
	VERB nvarchar (128),
	ITEM_SCOPE NUMERIC(5,0),
	ITEM_UPDATE_DATE DATETIME,
	FIRST_RECIPIENT_ID nvarchar(36),
	NUM_RECIPIENTS NUMERIC(5,0), 
	PRIMARY_ACTION_URL nvarchar(768), 
	SECONDARY_ACTION_URL nvarchar(768),
	ITEM_AUTHOR_UUID nvarchar (256),
	ITEM_CORRELATION_AUTHOR_UUID nvarchar (256),
	ITEM_CORRELATION_AUTHOR_NAME nvarchar (256),
	EVENT_SCOPE NUMERIC(5 ,0),
	RELATED_COMMUNITY_ID nvarchar(36),
	ITEM_CORRELATION_SCOPE NUMERIC(5,0),
	ITEM_CORRELATION_UPDATE_DATE DATETIME,
	ITEM_CORRELATION_URL nvarchar(2048)
) ON [PRIMARY]
GO

COMMIT;

-----------------------------------
-- Enable xp_cmdshell
----------------------------------

EXEC master.dbo.sp_configure 'show advanced options', 1
RECONFIGURE
EXEC master.dbo.sp_configure 'xp_cmdshell', 1
RECONFIGURE

---------------------------------
-- Move STORIES data
---------------------------------

BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.STORIES AS (
SELECT  
	STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, 
	null CONTAINER_ATOM_URL,
	ITEM_NAME,
	ITEM_URL, ITEM_ATOM_URL, ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC,
	ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, TEXT_META_TEMPLATE, R_META_TEMPLATE,
	R_TEXT_META_TEMPLATE, IS_COMMUNITY_STORY, ITEM_CORRELATION_NAME, 
	0 HAS_ATTACHMENT,
	SOURCE_TYPE,
	null ENTRY_ID, null EVENT_TIME, null VERB, null ITEM_SCOPE, null ITEM_UPDATE_DATE, null FIRST_RECIPIENT_ID, null NUM_RECIPIENTS,
	null PRIMARY_ACTION_URL, null SECONDARY_ACTION_URL, null ITEM_AUTHOR_UUID, null ITEM_CORRELATION_AUTHOR_UUID, null ITEM_CORRELATION_AUTHOR_NAME,
	null EVENT_SCOPE, null RELATED_COMMUNITY_ID, null ITEM_CORRELATION_SCOPE, null ITEM_CORRELATION_UPDATE_DATE, null ITEM_CORRELATION_URL
FROM HOMEPAGE.NR_STORIES
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.STORIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.STORIES' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_STORIES_40' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

DROP VIEW HOMEPAGE.STORIES;

COMMIT;

BEGIN TRANSACTION
GO

DROP TABLE HOMEPAGE.NR_STORIES;
GO

EXEC sp_rename 'HOMEPAGE.NR_STORIES_40','NR_STORIES';
GO

-----------------------------------------
-- Adding NR_STORIES constraints
-----------------------------------------
ALTER TABLE HOMEPAGE.NR_STORIES
    ADD CONSTRAINT PK_STORY_ID PRIMARY KEY(STORY_ID);

ALTER TABLE HOMEPAGE.NR_STORIES
  	ADD CONSTRAINT FK_ENTRY_ID FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.NR_ENTRIES (ENTRY_ID);    

CREATE INDEX NR_STORIES_DATE
	ON HOMEPAGE.NR_STORIES (CREATION_DATE DESC);

CREATE INDEX STORY_CONTAINED_ID
    ON HOMEPAGE.NR_STORIES (CONTAINER_ID);

CREATE INDEX STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_STORIES (ITEM_CORRELATION_ID);

CREATE INDEX NR_STORIES_EIDX
    ON HOMEPAGE.NR_STORIES (ENTRY_ID);

CREATE INDEX NR_STORIES_ER_UUID
    ON HOMEPAGE.NR_STORIES (EVENT_RECORD_UUID, ENTRY_ID);
    
CREATE INDEX NR_STORY_CD_IDX 
	ON HOMEPAGE.NR_STORIES (CREATION_DATE ASC, STORY_ID);    

CREATE INDEX STORIES_CONTAINER_URL_IDX ON 
	HOMEPAGE.NR_STORIES (CONTAINER_URL ASC);

CREATE INDEX STORIES_EVENT_ITEM_ACTOR_IDX ON
	HOMEPAGE.NR_STORIES (EVENT_NAME, ITEM_ID, ACTOR_UUID);

GO

----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
-- ADD READERS TABLES ( They where the old _STORIES tables)
----------------------------------------------------------------------

-- Removing an OLD unused table:
DROP TABLE HOMEPAGE.NR_ORGPERSON_STORIES;
GO



----------------------------------------------------------------------
----------------------------------------------------------------------
-- CREATE TABLE "HOMEPAGE"."NR_AGGREGATED_READERS"
----------------------------------------------------------------------
-- HOMEPAGE.NR_AGGREGATED_READERS 
----------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_COMM_PERSON_STORIES
	DROP CONSTRAINT PK_COMPER_STORY_ID;

ALTER TABLE HOMEPAGE.NR_COMM_PERSON_STORIES
	DROP CONSTRAINT UNIQUE_COMM_PERSON;

GO

EXEC sp_rename 'HOMEPAGE.NR_COMM_PERSON_STORIES','NR_AGGREGATED_READERS';
GO

EXEC sp_rename 'HOMEPAGE.NR_AGGREGATED_READERS.COMM_PER_STORY_ID','CATEGORY_READER_ID', 'COLUMN';
GO

EXEC sp_rename 'HOMEPAGE.NR_AGGREGATED_READERS.COMM_PER_READER_ID','READER_ID', 'COLUMN';
GO
	
ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS ADD
	ROLLUP_ENTRY_ID nvarchar(256),
	USE_IN_ROLLUP NUMERIC(5 ,0),
	IS_NETWORK	NUMERIC(5 ,0),
	IS_FOLLOWER	NUMERIC(5 ,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL;
GO

UPDATE HOMEPAGE.NR_AGGREGATED_READERS SET USE_IN_ROLLUP = 0;
GO

ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS
	ALTER COLUMN ITEM_ID nvarchar(256);
GO

CREATE INDEX AGGREGATED_READERS_RDR_STR 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS
    ADD CONSTRAINT PK_AGG_READERS PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS 
	ADD CONSTRAINT FK_AGG_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_AGGREGATED_READERS
CREATE  INDEX AGGREGATED_READERS_STR_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (STORY_ID); 
GO

CREATE  INDEX AGGREGATED_READERS_ITM_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (ITEM_ID); 
GO

CREATE  INDEX AGGREGATED_READERS_CD_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX AGGREGATED_READERS_SRC_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX AGGREGATED_READERS_AUT_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX AGGREGATED_READERS_RLL_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_AGGREGATED_READERS_IDX
	ON HOMEPAGE.NR_AGGREGATED_READERS (READER_ID);
GO

CREATE  INDEX AGGREGATED_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX AGGREGATED_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX AGGREGATED_READERS_RIR_IX
 	ON HOMEPAGE.NR_AGGREGATED_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO



-- NR_RESPONSES_READERS old  "NR_RESPONSES_STORIES"
--------------------------------------------------------------------------
-- 1 HOMEPAGE.NR_RESPONSES_READERS
--------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_RESPONSES_STORIES
	DROP CONSTRAINT PK_RESP_STORIES;
   
GO

EXEC sp_rename 'HOMEPAGE.NR_RESPONSES_STORIES','NR_RESPONSES_READERS';
GO

EXEC sp_rename 'HOMEPAGE.NR_RESPONSES_READERS.FOLLOWED_STORY_ID','CATEGORY_READER_ID', 'COLUMN';
GO

ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS ADD
	ROLLUP_ENTRY_ID nvarchar(256),
	USE_IN_ROLLUP NUMERIC(5 ,0),
	IS_NETWORK	NUMERIC(5 ,0),
	IS_FOLLOWER	NUMERIC(5 ,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL;
GO

UPDATE HOMEPAGE.NR_RESPONSES_READERS SET USE_IN_ROLLUP = 0;
GO

ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS
	ALTER COLUMN ITEM_ID nvarchar(256);
GO

CREATE  INDEX RESPONSES_READERS_RDR_STR 
 	ON HOMEPAGE.NR_RESPONSES_READERS (READER_ID, STORY_ID); 
GO	
	
ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS 
    ADD CONSTRAINT PK_RESP_READERS PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS 
	ADD CONSTRAINT FK_RES_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);

--  [start indexes] NR_RESPONSES_READERS
CREATE  INDEX RESPONSES_READERS_STR_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (STORY_ID); 
GO

CREATE  INDEX RESPONSES_READERS_ITM_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (ITEM_ID); 
GO

CREATE  INDEX RESPONSES_READERS_CD_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX RESPONSES_READERS_SRC_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX RESPONSES_READERS_AUT_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX RESPONSES_READERS_RLL_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_RESPONSES_READERS_IDX
	ON HOMEPAGE.NR_RESPONSES_READERS (READER_ID);
GO

CREATE  INDEX RESPONSES_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX RESPONSES_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX RESPONSES_READERS_RIR_IX
 	ON HOMEPAGE.NR_RESPONSES_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_RESPONSES_READERS



-- NR_PROFILES_READERS old NR_PROFILES_STORIES
--------------------------------------------------------------------------
-- 2 HOMEPAGE.NR_PROFILES_READERS
--------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_PROFILES_STORIES
	DROP CONSTRAINT PK_PROF_STORIES;
   
EXEC sp_rename 'HOMEPAGE.NR_PROFILES_STORIES','NR_PROFILES_READERS';
GO

EXEC sp_rename 'HOMEPAGE.NR_PROFILES_READERS.FOLLOWED_STORY_ID','CATEGORY_READER_ID', 'COLUMN';
GO

ALTER TABLE HOMEPAGE.NR_PROFILES_READERS ADD
	ROLLUP_ENTRY_ID nvarchar(256),
	USE_IN_ROLLUP NUMERIC(5 ,0),
	IS_NETWORK	NUMERIC(5 ,0),
	IS_FOLLOWER	NUMERIC(5 ,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL;
GO

UPDATE HOMEPAGE.NR_PROFILES_READERS SET USE_IN_ROLLUP = 0;
GO

ALTER TABLE HOMEPAGE.NR_PROFILES_READERS
	ALTER COLUMN ITEM_ID nvarchar(256);
GO

CREATE  INDEX PROFILES_READERS_RDR_STR 
 	ON HOMEPAGE.NR_PROFILES_READERS (READER_ID, STORY_ID); 
GO	

ALTER TABLE HOMEPAGE.NR_PROFILES_READERS 
    ADD CONSTRAINT PK_PROF_READERS PRIMARY KEY(CATEGORY_READER_ID);
GO    
    
ALTER TABLE HOMEPAGE.NR_PROFILES_READERS 
	ADD CONSTRAINT FK_PRF_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    
GO
	
--  [start indexes] NR_PROFILES_READERS
CREATE  INDEX PROFILES_READERS_STR_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (STORY_ID); 
GO

CREATE  INDEX PROFILES_READERS_ITM_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (ITEM_ID); 
GO

CREATE  INDEX PROFILES_READERS_CD_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX PROFILES_READERS_SRC_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX PROFILES_READERS_AUT_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX PROFILES_READERS_RLL_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_PROFILES_READERS_IDX
	ON HOMEPAGE.NR_PROFILES_READERS (READER_ID);
GO

CREATE  INDEX PROFILES_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX PROFILES_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX PROFILES_READERS_RIR_IX
 	ON HOMEPAGE.NR_PROFILES_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_PROFILES_READERS



-- "TO NR_COMMUNITIES_READERS old NR_COMMUNITIES_STORIES"  
--------------------------------------------------------------------------
-- 3 HOMEPAGE.NR_COMMUNITIES_READERS
--------------------------------------------------------------------------
DROP TABLE HOMEPAGE.NR_COMMUNITIES_STORIES;
GO

CREATE TABLE HOMEPAGE.NR_COMMUNITIES_READERS (
	CATEGORY_READER_ID nvarchar(36)  DEFAULT ' ' NOT NULL,
	READER_ID nvarchar(36) NOT NULL,
	CATEGORY_TYPE NUMERIC(5,0) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(256),
	ITEM_ID nvarchar(256),
	ROLLUP_ENTRY_ID nvarchar(256),
	RESOURCE_TYPE NUMERIC(5,0) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0),
	USE_IN_ROLLUP NUMERIC(5,0),
	IS_NETWORK	NUMERIC(5,0),
	IS_FOLLOWER	NUMERIC(5,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL,	
	CONSTRAINT   	CK_CAT3_TYPE
					CHECK
					(CATEGORY_TYPE = 3)
)
ON [PRIMARY]
GO

COMMIT;

---------------------------------
-- Move FROM NR_COMM_STORIES TO COMMUNITIES_READERS
---------------------------------

BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.COMM_READERS AS (
SELECT 
	COMM_STORY_ID CATEGORY_READER_ID, COMMUNITY_ID READER_ID, 3 CATEGORY_TYPE, 'communities' SOURCE, CONTAINER_ID, ITEM_ID,
	' ' ROLLUP_ENTRY_ID, RESOURCE_TYPE, CREATION_DATE, STORY_ID, SOURCE_TYPE, 0 USE_IN_ROLLUP,
	0 IS_NETWORK, 0 IS_FOLLOWER, CREATION_DATE EVENT_TIME, 1 IS_STORY_COMM, 
	0 IS_BROADCAST, ' ' ORGANIZATION_ID, ' ' ACTOR_UUID, ' ' ROLLUP_AUTHOR_ID, 1 IS_VISIBLE		
FROM HOMEPAGE.NR_COMM_STORIES
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.COMM_READERS TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.COMM_READERS' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_COMMUNITIES_READERS' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

DROP VIEW HOMEPAGE.COMM_READERS;

COMMIT;

BEGIN TRANSACTION
GO

CREATE  INDEX COMM_READERS_RDR_STR 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_COMMUNITIES_READERS 
    ADD CONSTRAINT PK_COMM_READERS PRIMARY KEY(CATEGORY_READER_ID);
GO    

ALTER TABLE HOMEPAGE.NR_COMMUNITIES_READERS
	ADD CONSTRAINT FK_COM_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
GO	
	
--  [start indexes] NR_COMMUNITIES_READERS
CREATE  INDEX COMM_READERS_STR_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (STORY_ID); 
GO

CREATE  INDEX COMM_READERS_ITM_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (ITEM_ID); 
GO

CREATE  INDEX COMM_READERS_CD_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX COMM_READERS_SRC_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX COMM_READERS_AUT_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX COMM_READERS_RLL_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_COMM_READERS_IDX
	ON HOMEPAGE.NR_COMMUNITIES_READERS (READER_ID);
GO

CREATE  INDEX COMM_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX COMM_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX COMM_READERS_RIR_IX
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_COMMUNITIES_READERS



-- NR_ACTIVITIES_READERS old NR_ACTIVITIES_STORIES
--------------------------------------------------------------------------
-- 4 HOMEPAGE.NR_ACTIVITIES_READERS
--------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_ACTIVITIES_STORIES
	DROP CONSTRAINT PK_ACT_STORIES;

EXEC sp_rename 'HOMEPAGE.NR_ACTIVITIES_STORIES','NR_ACTIVITIES_READERS';
GO

EXEC sp_rename 'HOMEPAGE.NR_ACTIVITIES_READERS.FOLLOWED_STORY_ID','CATEGORY_READER_ID', 'COLUMN';
GO

ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS ADD
	ROLLUP_ENTRY_ID nvarchar(256),
	USE_IN_ROLLUP NUMERIC(5 ,0),
	IS_NETWORK	NUMERIC(5 ,0),
	IS_FOLLOWER	NUMERIC(5 ,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL;
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_READERS SET USE_IN_ROLLUP = 0;
GO

ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS
	ALTER COLUMN ITEM_ID nvarchar(256);
GO

CREATE  INDEX ACTIVITIES_READERS_RDR_STR 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS 
    ADD CONSTRAINT PK_ACT_READERS PRIMARY KEY(CATEGORY_READER_ID);

ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS
	ADD CONSTRAINT FK_ACI_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);	
	
--  [start indexes] NR_ACTIVITIES_READERS
CREATE  INDEX ACTIVITIES_READERS_STR_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (STORY_ID); 
GO

CREATE  INDEX ACTIVITIES_READERS_ITM_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (ITEM_ID); 
GO

CREATE  INDEX ACTIVITIES_READERS_CD_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX ACTIVITIES_READERS_SRC_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX ACTIVITIES_READERS_AUT_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX ACTIVITIES_READERS_RLL_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_ACTIVITIES_READERS_IDX
	ON HOMEPAGE.NR_ACTIVITIES_READERS (READER_ID);
GO

CREATE  INDEX ACTIVITIES_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX ACTIVITIES_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX ACTIVITIES_READERS_RIR_IX
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_ACTIVITIES_READERS



-- NR_BLOGS_READERS old NR_BLOGS_STORIES
--------------------------------------------------------------------------
-- 5 HOMEPAGE.NR_BLOGS_READERS
--------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_BLOGS_STORIES
	DROP CONSTRAINT PK_BLOGS_STORIES;
   

EXEC sp_rename 'HOMEPAGE.NR_BLOGS_STORIES','NR_BLOGS_READERS';
GO

EXEC sp_rename 'HOMEPAGE.NR_BLOGS_READERS.FOLLOWED_STORY_ID','CATEGORY_READER_ID', 'COLUMN';
GO

ALTER TABLE HOMEPAGE.NR_BLOGS_READERS ADD
	ROLLUP_ENTRY_ID nvarchar(256),
	USE_IN_ROLLUP NUMERIC(5 ,0),
	IS_NETWORK	NUMERIC(5 ,0),
	IS_FOLLOWER	NUMERIC(5 ,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL;
GO

UPDATE HOMEPAGE.NR_BLOGS_READERS SET USE_IN_ROLLUP = 0;
GO

ALTER TABLE HOMEPAGE.NR_BLOGS_READERS
	ALTER COLUMN ITEM_ID nvarchar(256);
GO

CREATE  INDEX BLOGS_READERS_RDR_STR 
 	ON HOMEPAGE.NR_BLOGS_READERS (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_BLOGS_READERS 
    ADD CONSTRAINT PK_BLOGS_READERS PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_BLOGS_READERS
	ADD CONSTRAINT FK_BLG_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_BLOGS_READERS
CREATE  INDEX BLOGS_READERS_STR_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (STORY_ID); 
GO

CREATE  INDEX BLOGS_READERS_ITM_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (ITEM_ID); 
GO

CREATE  INDEX BLOGS_READERS_CD_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX BLOGS_READERS_SRC_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX BLOGS_READERS_AUT_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX BLOGS_READERS_RLL_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_BLOGS_READERS_IDX
	ON HOMEPAGE.NR_BLOGS_READERS (READER_ID);
GO

CREATE  INDEX BLOGS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX BLOGS_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX BLOGS_READERS_RIR_IX
 	ON HOMEPAGE.NR_BLOGS_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_BLOGS_READERS



-- NR_BOOKMARKS_READERS old "HOMEPAGE"."NR_BOOKMARKS_STORIES"
--------------------------------------------------------------------------
-- 6 NR_BOOKMARKS_STORIES (EMPTY TABLE)
--------------------------------------------------------------------------

DROP TABLE HOMEPAGE.NR_BOOKMARKS_STORIES;
GO

CREATE TABLE HOMEPAGE.NR_BOOKMARKS_READERS (
	CATEGORY_READER_ID nvarchar(36)  DEFAULT ' ' NOT NULL,
	READER_ID nvarchar(36) NOT NULL,
	CATEGORY_TYPE NUMERIC(5,0) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(256),
	ITEM_ID nvarchar(256),
	ROLLUP_ENTRY_ID nvarchar(256),
	RESOURCE_TYPE NUMERIC(5,0) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0),
	USE_IN_ROLLUP NUMERIC(5,0),
	IS_NETWORK	NUMERIC(5,0),
	IS_FOLLOWER	NUMERIC(5,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL,	
	CONSTRAINT   	CK_CAT6_TYPE
					CHECK
					(CATEGORY_TYPE = 6)
)
ON [PRIMARY]
GO

CREATE  INDEX BOOKMARKS_READERS_RDR_STR 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_BOOKMARKS_READERS 
    ADD CONSTRAINT PK_BOOKS_READERS PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_BOOKMARKS_READERS
	ADD CONSTRAINT FK_BKM_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_BOOKMARKS_READERS
CREATE  INDEX BOOKMARKS_READERS_STR_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (STORY_ID); 
GO

CREATE  INDEX BOOKMARKS_READERS_ITM_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (ITEM_ID); 
GO

CREATE  INDEX BOOKMARKS_READERS_CD_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX BOOKMARKS_READERS_SRC_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX BOOKMARKS_READERS_AUT_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX BOOKMARKS_READERS_RLL_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_BOOKMARKS_READERS_IDX
	ON HOMEPAGE.NR_BOOKMARKS_READERS (READER_ID);
GO

CREATE  INDEX BOOKMARKS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX BOOKMARKS_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX BOOKMARKS_READERS_RIR_IX
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_BOOKMARKS_READERS



-- NR_FILES_READERS old NR_FILES_STORIES
--------------------------------------------------------------------------
-- 7 NR_FILES_STORIES
--------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_FILES_STORIES
	DROP CONSTRAINT PK_FILES_STORIES;
   

EXEC sp_rename 'HOMEPAGE.NR_FILES_STORIES','NR_FILES_READERS';
GO

EXEC sp_rename 'HOMEPAGE.NR_FILES_READERS.FOLLOWED_STORY_ID','CATEGORY_READER_ID', 'COLUMN';
GO

ALTER TABLE HOMEPAGE.NR_FILES_READERS ADD
	ROLLUP_ENTRY_ID nvarchar(256),
	USE_IN_ROLLUP NUMERIC(5 ,0),
	IS_NETWORK	NUMERIC(5 ,0),
	IS_FOLLOWER	NUMERIC(5 ,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL;
GO

UPDATE HOMEPAGE.NR_FILES_READERS SET USE_IN_ROLLUP = 0;
GO

ALTER TABLE HOMEPAGE.NR_FILES_READERS
	ALTER COLUMN ITEM_ID nvarchar(256);
GO

CREATE  INDEX FILES_READERS_RDR_STR 
 	ON HOMEPAGE.NR_FILES_READERS (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_FILES_READERS 
    ADD CONSTRAINT PK_FILES_READERS PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_FILES_READERS
	ADD CONSTRAINT FK_FIL_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_FILES_READERS
CREATE  INDEX FILES_READERS_STR_IX 
 	ON HOMEPAGE.NR_FILES_READERS (STORY_ID); 
GO

CREATE  INDEX FILES_READERS_ITM_IX 
 	ON HOMEPAGE.NR_FILES_READERS (ITEM_ID); 
GO

CREATE  INDEX FILES_READERS_CD_IX 
 	ON HOMEPAGE.NR_FILES_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX FILES_READERS_SRC_IX 
 	ON HOMEPAGE.NR_FILES_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX FILES_READERS_AUT_IX 
 	ON HOMEPAGE.NR_FILES_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX FILES_READERS_RLL_IX 
 	ON HOMEPAGE.NR_FILES_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_FILES_READERS_IDX
	ON HOMEPAGE.NR_FILES_READERS (READER_ID);
GO

CREATE  INDEX FILES_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_FILES_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX FILES_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_FILES_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX FILES_READERS_RIR_IX
 	ON HOMEPAGE.NR_FILES_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_FILES_READERS



-- "NR_FORUMS_READERS old "NR_FORUMS_STORIES" 
--------------------------------------------------------------------------
-- 8 NR_FORUMS_STORIES
--------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_FORUMS_STORIES
	DROP CONSTRAINT PK_FORUMS_STORIES;
   

EXEC sp_rename 'HOMEPAGE.NR_FORUMS_STORIES','NR_FORUMS_READERS';
GO

EXEC sp_rename 'HOMEPAGE.NR_FORUMS_READERS.FOLLOWED_STORY_ID','CATEGORY_READER_ID', 'COLUMN';
GO

ALTER TABLE HOMEPAGE.NR_FORUMS_READERS ADD
	ROLLUP_ENTRY_ID nvarchar(256),
	USE_IN_ROLLUP NUMERIC(5 ,0),
	IS_NETWORK	NUMERIC(5 ,0),
	IS_FOLLOWER	NUMERIC(5 ,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL;
GO

UPDATE HOMEPAGE.NR_FORUMS_READERS SET USE_IN_ROLLUP = 0;
GO

ALTER TABLE HOMEPAGE.NR_FORUMS_READERS
	ALTER COLUMN ITEM_ID nvarchar(256);
GO

CREATE  INDEX FORUMS_READERS_RDR_STR 
 	ON HOMEPAGE.NR_FORUMS_READERS (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_FORUMS_READERS 
    ADD CONSTRAINT PK_FORUMS_READERS PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_FORUMS_READERS
	ADD CONSTRAINT FK_FRM_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_FORUMS_READERS
CREATE  INDEX FORUMS_READERS_STR_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (STORY_ID); 
GO

CREATE  INDEX FORUMS_READERS_ITM_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (ITEM_ID); 
GO

CREATE  INDEX FORUMS_READERS_CD_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX FORUMS_READERS_SRC_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX FORUMS_READERS_AUT_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX FORUMS_READERS_RLL_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_FORUMS_READERS_IDX
	ON HOMEPAGE.NR_FORUMS_READERS (READER_ID);
GO

CREATE  INDEX FORUMS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX FORUMS_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX FORUMS_READERS_RIR_IX
 	ON HOMEPAGE.NR_FORUMS_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_FORUMS_READERS



-- NR_WIKIS_READERS old NR_WIKIS_STORIES
--------------------------------------------------------------------------
-- 9 NR_WIKIS_STORIES
--------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_WIKIS_STORIES
	DROP CONSTRAINT PK_WIKIS_STORIES;
   

EXEC sp_rename 'HOMEPAGE.NR_WIKIS_STORIES','NR_WIKIS_READERS';
GO

EXEC sp_rename 'HOMEPAGE.NR_WIKIS_READERS.FOLLOWED_STORY_ID','CATEGORY_READER_ID', 'COLUMN';
GO

ALTER TABLE HOMEPAGE.NR_WIKIS_READERS ADD
	ROLLUP_ENTRY_ID nvarchar(256),
	USE_IN_ROLLUP NUMERIC(5 ,0),
	IS_NETWORK	NUMERIC(5 ,0),
	IS_FOLLOWER	NUMERIC(5 ,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL;
GO

UPDATE HOMEPAGE.NR_WIKIS_READERS SET USE_IN_ROLLUP = 0;
GO

ALTER TABLE HOMEPAGE.NR_WIKIS_READERS
	ALTER COLUMN ITEM_ID nvarchar(256);
GO

CREATE  INDEX WIKIS_READERS_RDR_STR 
 	ON HOMEPAGE.NR_WIKIS_READERS (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_WIKIS_READERS 
    ADD CONSTRAINT PK_WIKIS_READERS PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_WIKIS_READERS
	ADD CONSTRAINT FK_WIK_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_WIKIS_READERS
CREATE  INDEX WIKIS_READERS_STR_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (STORY_ID); 
GO

CREATE  INDEX WIKIS_READERS_ITM_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (ITEM_ID); 
GO

CREATE  INDEX WIKIS_READERS_CD_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX WIKIS_READERS_SRC_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX WIKIS_READERS_AUT_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX WIKIS_READERS_RLL_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_WIKIS_READERS_IDX
	ON HOMEPAGE.NR_WIKIS_READERS (READER_ID);
GO

CREATE  INDEX WIKIS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX WIKIS_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX WIKIS_READERS_RIR_IX
 	ON HOMEPAGE.NR_WIKIS_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_WIKIS_READERS



-- NR_TAGS_READERS old NR_TAGS_STORIES
--------------------------------------------------------------------------
-- 10 HOMEPAGE.NR_TAGS_READERS
--------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_TAGS_STORIES
	DROP CONSTRAINT PK_TAGS_STORIES;
   

EXEC sp_rename 'HOMEPAGE.NR_TAGS_STORIES','NR_TAGS_READERS';
GO

EXEC sp_rename 'HOMEPAGE.NR_TAGS_READERS.FOLLOWED_STORY_ID','CATEGORY_READER_ID', 'COLUMN';
GO

ALTER TABLE HOMEPAGE.NR_TAGS_READERS ADD
	ROLLUP_ENTRY_ID nvarchar(256),
	USE_IN_ROLLUP NUMERIC(5 ,0),
	IS_NETWORK	NUMERIC(5 ,0),
	IS_FOLLOWER	NUMERIC(5 ,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL;
GO

UPDATE HOMEPAGE.NR_TAGS_READERS SET USE_IN_ROLLUP = 0;
GO

ALTER TABLE HOMEPAGE.NR_TAGS_READERS
	ALTER COLUMN ITEM_ID nvarchar(256);
GO

CREATE  INDEX TAGS_READERS_RDR_STR 
 	ON HOMEPAGE.NR_TAGS_READERS (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_TAGS_READERS 
    ADD CONSTRAINT PK_TAGS_READERS PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_TAGS_READERS
	ADD CONSTRAINT FK_TAG_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_TAGS_READERS
CREATE  INDEX TAGS_READERS_STR_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (STORY_ID); 
GO

CREATE  INDEX TAGS_READERS_ITM_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (ITEM_ID); 
GO

CREATE  INDEX TAGS_READERS_CD_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX TAGS_READERS_SRC_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX TAGS_READERS_AUT_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX TAGS_READERS_RLL_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 

CREATE  INDEX CL_TAGS_READERS_IDX
	ON HOMEPAGE.NR_TAGS_READERS (READER_ID);
GO

CREATE  INDEX TAGS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX TAGS_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX TAGS_READERS_RIR_IX
 	ON HOMEPAGE.NR_TAGS_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_TAGS_READERS



--------------------------------------------------------------------------
-- 11) HOMEPAGE.NR_STATUS_UPDATE_READERS
--------------------------------------------------------------------------	
CREATE TABLE HOMEPAGE.NR_STATUS_UPDATE_READERS (
	CATEGORY_READER_ID nvarchar(36)  DEFAULT ' ' NOT NULL,
	READER_ID nvarchar(36) NOT NULL,
	CATEGORY_TYPE NUMERIC(5,0) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(256),
	ITEM_ID nvarchar(256),
	ROLLUP_ENTRY_ID nvarchar(256),
	RESOURCE_TYPE NUMERIC(5,0) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0),
	USE_IN_ROLLUP NUMERIC(5,0),
	IS_NETWORK	NUMERIC(5,0),
	IS_FOLLOWER	NUMERIC(5,0),
	EVENT_TIME DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL,	
   	CONSTRAINT  CK_CAT11_TYPE
    			CHECK
    			(CATEGORY_TYPE = 11)
) ON [PRIMARY]
GO

CREATE  INDEX STATUS_READERS_RDR_STR 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_STATUS_UPDATE_READERS 
    ADD CONSTRAINT PK_SU_READERS PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_STATUS_UPDATE_READERS
	ADD CONSTRAINT FK_STA_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_STATUS_UPDATE_READERS
CREATE  INDEX STATUS_READERS_STR_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (STORY_ID); 
GO

CREATE  INDEX STATUS_READERS_ITM_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (ITEM_ID); 
GO

CREATE  INDEX STATUS_READERS_CD_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX STATUS_READERS_SRC_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX STATUS_READERS_AUT_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX STATUS_READERS_RLL_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_STATUS_READERS_IDX
	ON HOMEPAGE.NR_STATUS_UPDATE_READERS  (READER_ID);
GO

CREATE  INDEX STATUS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX STATUS_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX STATUS_READERS_RIR_IX
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_STATUS_UPDATE_READERS



---------------------------------------------------------
-- 12) ADDING A NR_EXTERNAL_READERS READER TABLE
---------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_EXTERNAL_READERS (
	CATEGORY_READER_ID nvarchar(36)  DEFAULT ' ' NOT NULL,
	READER_ID nvarchar(36) NOT NULL,
	CATEGORY_TYPE NUMERIC(5,0) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(256),
	ITEM_ID nvarchar(256),
	ROLLUP_ENTRY_ID nvarchar(256),
	RESOURCE_TYPE NUMERIC(5,0) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0),
	USE_IN_ROLLUP NUMERIC(5,0),
	IS_NETWORK	NUMERIC(5,0),
	IS_FOLLOWER	NUMERIC(5,0),
	EVENT_TIME DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL,	
   	CONSTRAINT  CK_CAT12_TYPE
    			CHECK
    			(CATEGORY_TYPE = 12)
) ON [PRIMARY]
GO

CREATE  INDEX EXTERNAL_READERS_RDR_STR 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_EXTERNAL_READERS 
    ADD CONSTRAINT PK_EXT_READERS PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_EXTERNAL_READERS
	ADD CONSTRAINT FK_EXT_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    
    
--  [start indexes] NR_EXTERNAL_READERS
CREATE  INDEX EXTERNAL_READERS_STR_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (STORY_ID); 
GO

CREATE  INDEX EXTERNAL_READERS_ITM_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (ITEM_ID); 
GO

CREATE  INDEX EXTERNAL_READERS_CD_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX EXTERNAL_READERS_SRC_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX EXTERNAL_READERS_AUT_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX EXTERNAL_READERS_RLL_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_EXTERNAL_READERS_IDX
	ON HOMEPAGE.NR_EXTERNAL_READERS  (READER_ID);
GO

CREATE  INDEX EXTERNAL_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX EXTERNAL_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX EXTERNAL_READERS_RIR_IX
 	ON HOMEPAGE.NR_EXTERNAL_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_EXTERNAL_READERS
    
---------------------------------------------------------
-- ADDING NR_ACTIONABLE_READERS READER TABLE
---------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_ACTIONABLE_READERS (
	CATEGORY_READER_ID nvarchar(36)  DEFAULT ' ' NOT NULL,
	READER_ID nvarchar(36) NOT NULL,
	CATEGORY_TYPE NUMERIC(5,0) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(256),
	ITEM_ID nvarchar(256),
	ROLLUP_ENTRY_ID nvarchar(256),
	RESOURCE_TYPE NUMERIC(5,0) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0),
	USE_IN_ROLLUP NUMERIC(5,0),
	IS_NETWORK	NUMERIC(5,0),
	IS_FOLLOWER	NUMERIC(5,0),
	EVENT_TIME DATETIME,
	NOTE_TEXT nvarchar(4000),
	NOTE_UPDATE_DATE DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	OPERATION_ID nvarchar(512),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL,	
	CONSTRAINT   	CK_CAT_ACTION_TYPE
    				CHECK
    				(CATEGORY_TYPE >= 14 AND CATEGORY_TYPE <= 16)	
) ON [PRIMARY]
GO

CREATE  INDEX ACTIONABLE_READERS_RDR_STR 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS 
    ADD CONSTRAINT PK_ACTION_READERS PRIMARY KEY (CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS
	ADD CONSTRAINT FK_ACT_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    
    
--  [start indexes] NR_ACTIONABLE_READERS
CREATE  INDEX ACTIONABLE_READERS_STR_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (STORY_ID); 
GO

CREATE  INDEX ACTIONABLE_READERS_ITM_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (ITEM_ID); 
GO

CREATE  INDEX ACTIONABLE_READERS_CD_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX ACTIONABLE_READERS_SRC_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX ACTIONABLE_READERS_AUT_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX ACTIONABLE_READERS_RLL_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_ACTIONABLE_READERS_IDX
	ON HOMEPAGE.NR_ACTIONABLE_READERS  (READER_ID);
GO

CREATE  INDEX ACTIONABLE_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX ACTIONABLE_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX ACTIONABLE_READERS_RIR_IX
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_ACTIONABLE_READERS

--------------------------------------------------------------------------------------------------------------
-- CREATE THE VIEW FOR ALL THE CATEGORIES READERS TABLE
--------------------------------------------------------------------------------------------------------------
CREATE VIEW HOMEPAGE.NR_CATEGORIES_READERS AS (
    SELECT * FROM HOMEPAGE.NR_RESPONSES_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_PROFILES_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_COMMUNITIES_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_ACTIVITIES_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_BLOGS_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_BOOKMARKS_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_FILES_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_FORUMS_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_WIKIS_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_TAGS_READERS
    	UNION ALL
    SELECT * FROM HOMEPAGE.NR_STATUS_UPDATE_READERS
    	UNION ALL
    SELECT * FROM HOMEPAGE.NR_EXTERNAL_READERS 
);
GO


--------------------------------------------------------------------------
-- 17 HOMEPAGE.NR_DISCOVERY_VIEW
--------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_DISCOVERY_VIEW (
	CATEGORY_READER_ID nvarchar(36)  DEFAULT ' ' NOT NULL,
	READER_ID nvarchar(36),
	CATEGORY_TYPE NUMERIC(5,0) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(256),
	ITEM_ID nvarchar(256),
	ROLLUP_ENTRY_ID nvarchar(256),
	RESOURCE_TYPE NUMERIC(5,0) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0),
	USE_IN_ROLLUP NUMERIC(5,0),
	IS_NETWORK	NUMERIC(5,0),
	IS_FOLLOWER	NUMERIC(5,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL,	
	CONSTRAINT   	CK_CAT17_TYPE
					CHECK
					(CATEGORY_TYPE = 17)
)
ON [PRIMARY]
GO

CREATE  INDEX DISCOVERY_VIEW_RDR_STR 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_DISCOVERY_VIEW 
    ADD CONSTRAINT PK_DISCOVERY_VIEW PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_DISCOVERY_VIEW
	ADD CONSTRAINT FK_DIS_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_DISCOVERY_VIEW
CREATE  INDEX DISCOVERY_VIEW_STR_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (STORY_ID); 
GO

CREATE  INDEX DISCOVERY_VIEW_ITM_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (ITEM_ID); 
GO

CREATE  INDEX DISCOVERY_VIEW_CD_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX DISCOVERY_VIEW_SRC_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX DISCOVERY_VIEW_AUT_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX DISCOVERY_VIEW_RLL_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_DISCOVERY_READERS_IDX
	ON HOMEPAGE.NR_DISCOVERY_VIEW  (READER_ID);
GO

CREATE  INDEX DISCOVERY_VIEW_DEL_SERV_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (CREATION_DATE DESC); 
GO

CREATE  INDEX DISCOVERY_VIEW_ROLLUP_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX DISCOVERY_VIEW_RIR_IX
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_DISCOVERY_VIEW

-----------------------------------------------------
-- 18 NR_PROFILES_VIEW
-----------------------------------------------------
CREATE TABLE HOMEPAGE.NR_PROFILES_VIEW (
	CATEGORY_READER_ID nvarchar(36)  DEFAULT ' ' NOT NULL,
	READER_ID nvarchar(36),
	CATEGORY_TYPE NUMERIC(5,0) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(256),
	ITEM_ID nvarchar(256),
	ROLLUP_ENTRY_ID nvarchar(256),
	RESOURCE_TYPE NUMERIC(5,0) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0),
	USE_IN_ROLLUP NUMERIC(5,0),
	IS_NETWORK	NUMERIC(5,0),
	IS_FOLLOWER	NUMERIC(5,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5 ,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL,	
	CONSTRAINT   	CK_CAT18_TYPE
    				CHECK
    				(CATEGORY_TYPE = 18)
)
ON [PRIMARY]
GO

CREATE  INDEX PROFILES_VIEW_RDR_STR 
 	ON HOMEPAGE.NR_PROFILES_VIEW (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_PROFILES_VIEW 
    ADD CONSTRAINT PK_PROFILES_VIEW PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_PROFILES_VIEW
	ADD CONSTRAINT FK_PRO_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_PROFILES_VIEW
CREATE  INDEX PROFILES_VIEW_STR_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (STORY_ID); 
GO

CREATE  INDEX PROFILES_VIEW_ITM_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (ITEM_ID); 
GO

CREATE  INDEX PROFILES_VIEW_CD_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX PROFILES_VIEW_SRC_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX PROFILES_VIEW_AUT_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX PROFILES_VIEW_RLL_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_PROFILESV_READERS_IDX
	ON HOMEPAGE.NR_PROFILES_VIEW  (READER_ID);
GO

CREATE  INDEX PROFILES_VIEW_DEL_SERV_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (CREATION_DATE DESC); 
GO

CREATE  INDEX PROFILES_VIEW_ROLLUP_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX PROFILES_VIEW_RIR_IX
 	ON HOMEPAGE.NR_PROFILES_VIEW (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_PROFILES_VIEW

--------------------------------------------------------------------------
-- 19 HOMEPAGE.NR_NOTIFICATION_SENT_READERS
--------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_NOTIFICATION_SENT_READERS (
	CATEGORY_READER_ID nvarchar(36)  DEFAULT ' ' NOT NULL,
	READER_ID nvarchar(36) NOT NULL,
	CATEGORY_TYPE NUMERIC(5,0) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(256),
	ITEM_ID nvarchar(256),
	ROLLUP_ENTRY_ID nvarchar(256),
	RESOURCE_TYPE NUMERIC(5,0) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0),
	USE_IN_ROLLUP NUMERIC(5,0),
	IS_NETWORK	NUMERIC(5,0),
	IS_FOLLOWER	NUMERIC(5,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL,	
        CONSTRAINT   	CK_CAT19_TYPE
    			CHECK
    			(CATEGORY_TYPE = 19)
)
ON [PRIMARY]
GO

CREATE  INDEX NOTIFICA_READERS_RDR_STR 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_NOTIFICATION_SENT_READERS 
    ADD CONSTRAINT PK_SENT_NOT_READ PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_NOTIFICATION_SENT_READERS
	ADD CONSTRAINT FK_NOT_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_NOTIFICATION_SENT_READERS
CREATE  INDEX NOTIFICA_READERS_STR_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (STORY_ID); 
GO

CREATE  INDEX NOTIFICA_READERS_ITM_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (ITEM_ID); 
GO

CREATE  INDEX NOTIFICA_READERS_CD_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX NOTIFICA_READERS_SRC_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX NOTIFICA_READERS_AUT_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX NOTIFICA_READERS_RLL_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_NOTIFICA_READERS_IDX
	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS  (READER_ID);
GO

CREATE  INDEX NOTIFICA_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX NOTIFICA_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX NOTIFICA_READERS_RIR_IX
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_NOTIFICATION_SENT_READERS

--------------------------------------------------------------------------
-- 20 HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS
--------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (
	CATEGORY_READER_ID nvarchar(36)  DEFAULT ' ' NOT NULL,
	READER_ID nvarchar(36) NOT NULL,
	CATEGORY_TYPE NUMERIC(5,0) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(256),
	ITEM_ID nvarchar(256),
	ROLLUP_ENTRY_ID nvarchar(256),
	RESOURCE_TYPE NUMERIC(5,0) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0),
	USE_IN_ROLLUP NUMERIC(5,0),
	IS_NETWORK	NUMERIC(5,0),
	IS_FOLLOWER	NUMERIC(5,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL,	
        CONSTRAINT   	CK_CAT20_TYPE
    			CHECK
    			(CATEGORY_TYPE = 20)
) ON [PRIMARY]
GO

CREATE  INDEX NOT_REC_READERS_RDR_STR 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (READER_ID, STORY_ID); 
GO

ALTER TABLE HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS 
    ADD CONSTRAINT PK_REC_NOT_READ PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS
	ADD CONSTRAINT FK_REC_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_NOTIFICATION_RECEIV_READERS
CREATE  INDEX NOT_REC_READERS_STR_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (STORY_ID); 
GO

CREATE  INDEX NOT_REC_READERS_ITM_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (ITEM_ID); 
GO

CREATE  INDEX NOT_REC_READERS_CD_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX NOT_REC_READERS_SRC_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX NOT_REC_READERS_AUT_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX NOT_REC_READERS_RLL_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_REC_READERS_IDX
	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (READER_ID);
GO

CREATE  INDEX NOT_REC_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX NOT_REC_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX NOT_REC_READERS_RIR_IX
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_NOTIFICATION_RECEIV_READERS

--------------------------------------------------------------------------
-- 13 HOMEPAGE.NR_SAVED_READERS
--------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SAVED_READERS (
	CATEGORY_READER_ID nvarchar(36)  DEFAULT ' ' NOT NULL,
	READER_ID nvarchar(36) NOT NULL,
	CATEGORY_TYPE NUMERIC(5,0) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(256),
	ITEM_ID nvarchar(256),
	ROLLUP_ENTRY_ID nvarchar(256),
	RESOURCE_TYPE NUMERIC(5,0) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0),
	USE_IN_ROLLUP NUMERIC(5,0),
	IS_NETWORK	NUMERIC(5,0),
	IS_FOLLOWER	NUMERIC(5,0),
	EVENT_TIME DATETIME,
	NOTE_TEXT nvarchar(4000),
	NOTE_UPDATE_DATE DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	OPERATION_ID nvarchar(512),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL,
	CONSTRAINT   	CK_CAT_SAVED_TYPE
    				CHECK
    				(CATEGORY_TYPE = 13)	
) ON [PRIMARY]
GO

CREATE  INDEX SAVED_READERS_RDR_STR 
 	ON HOMEPAGE.NR_SAVED_READERS (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_SAVED_READERS 
    ADD CONSTRAINT PK_SAVED_READERS PRIMARY KEY (CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_SAVED_READERS
	ADD CONSTRAINT FK_SAV_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
--  [start indexes] NR_SAVED_READERS
CREATE  INDEX SAVED_READERS_STR_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (STORY_ID); 
GO

CREATE  INDEX SAVED_READERS_ITM_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (ITEM_ID); 
GO

CREATE  INDEX SAVED_READERS_CD_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX SAVED_READERS_SRC_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX SAVED_READERS_AUT_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX SAVED_READERS_RLL_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_SAVED_READERS_IDX
	ON HOMEPAGE.NR_SAVED_READERS  (READER_ID);
GO

CREATE  INDEX SAVED_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX SAVED_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX SAVED_READERS_RIR_IX
 	ON HOMEPAGE.NR_SAVED_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_SAVED_READERS

--------------------------------------------------------------------------
-- 21 HOMEPAGE.NR_TOPICS_READERS
--------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_TOPICS_READERS (
	CATEGORY_READER_ID nvarchar(36)  DEFAULT ' ' NOT NULL,
	READER_ID nvarchar(36) NOT NULL,
	CATEGORY_TYPE NUMERIC(5 ,0) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(256),
	ITEM_ID nvarchar(256),
	ROLLUP_ENTRY_ID nvarchar(256),
	RESOURCE_TYPE NUMERIC(5 ,0) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5 ,0),
	USE_IN_ROLLUP NUMERIC(5 ,0),
	IS_NETWORK	NUMERIC(5 ,0),
	IS_FOLLOWER	NUMERIC(5 ,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar(256),
	IS_VISIBLE NUMERIC(5,0) DEFAULT 1 NOT NULL,
	CONSTRAINT   	CK_CAT_TOPICS_TYPE
    				CHECK
    				(CATEGORY_TYPE = 21)	
) ON [PRIMARY]
GO

CREATE  INDEX TOPICS_READERS_RDR_STR 
 	ON HOMEPAGE.NR_TOPICS_READERS (READER_ID, STORY_ID); 
GO

ALTER TABLE HOMEPAGE.NR_TOPICS_READERS 
    ADD CONSTRAINT PK_TOPICS_READERS PRIMARY KEY (CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_TOPICS_READERS
	ADD CONSTRAINT FK_TOP_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_TOPICS_READERS
CREATE  INDEX TOPICS_READERS_STR_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (STORY_ID); 
GO

CREATE  INDEX TOPICS_READERS_ITM_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (ITEM_ID); 
GO

CREATE  INDEX TOPICS_READERS_CD_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX TOPICS_READERS_SRC_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX TOPICS_READERS_AUT_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX TOPICS_READERS_RLL_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX CL_TOPICS_READERS_IDX
	ON HOMEPAGE.NR_TOPICS_READERS (READER_ID);
GO

CREATE  INDEX TOPICS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX TOPICS_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE INDEX TOPICS_READERS_RIR_IX
 	ON HOMEPAGE.NR_TOPICS_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
GO

--  [end indexes] NR_TOPICS_READERS



----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------



----------------------------------------------------------------
-- ADDING NR_ATTACHMENT - CAN RELATE TO ANY STORIES TABLE
----------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_ATTACHMENT (
	ATTACHMENT_ID nvarchar(36) NOT NULL,
	ENTRY_ID nvarchar(36) NOT NULL, -- 47
	SOURCE_TYPE NUMERIC(5,0) NOT NULL,
	ATTACHMENT_TYPE NUMERIC(5,0),
	CREATION_DATE DATETIME,
	NAME nvarchar(2048),
	DESCRIPTION nvarchar(4000),	
	TARGET_ID  nvarchar(256),
	TARGET_URL nvarchar(2048),
	META_DATA nvarchar(4000)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_ATTACHMENT 
    ADD CONSTRAINT PK_ATTACHMENT PRIMARY KEY(ATTACHMENT_ID);

CREATE INDEX NR_ATT_ENTRY_ID
    ON HOMEPAGE.NR_ATTACHMENT (ENTRY_ID);  
GO

----------------------------------------------------------------
-- ADDING NR_RECOMMENDATION - CAN RELATE TO ANY STORY TABLE
----------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_RECOMMENDATION  (
	RECOMMENDATION_ID nvarchar(36) NOT NULL, --primary key
	RECOMMENDER_ID nvarchar(36) NOT NULL, -- PERSON_ID of the recommender, FK to PERSON table
	ENTRY_ID nvarchar(36) NOT NULL, 
	SOURCE_TYPE NUMERIC(5,0) NOT NULL, 
	CREATION_DATE DATETIME
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_RECOMMENDATION 
    ADD CONSTRAINT PK_RECOMMENDATION PRIMARY KEY(RECOMMENDATION_ID);

ALTER TABLE HOMEPAGE.NR_RECOMMENDATION
  	ADD CONSTRAINT FK_RECOMMENDER_ID FOREIGN KEY (RECOMMENDER_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);
    
CREATE INDEX NR_REC_ENTRY_ID
    ON HOMEPAGE.NR_RECOMMENDATION (ENTRY_ID);

CREATE INDEX NR_RECCOMANDER_ID
    ON HOMEPAGE.NR_RECOMMENDATION (RECOMMENDER_ID);
    
CREATE UNIQUE INDEX NR_RECOMMENDER_ENTRY_ID
    ON HOMEPAGE.NR_RECOMMENDATION (RECOMMENDER_ID, ENTRY_ID);
GO


--------------------------------------------------------------------------
--------------------------------------------------------------------------
--------------------------------------------------------------------------
-- TODO: SEARCH migration included. This is still not optimized and it is a c&p
-- from the upgrade script for search. Colm needs to review and optimze it

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start SEARCH FIXUP 87
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
---------------------------------------------------------------------------------
------------------------ START SEARCH -------------------------------------------
---------------------------------------------------------------------------------

--START 38374: DAO Layer Changes for Dynamic Global Properties for SAND 

----------------------------------------
--  SR_GLOBAL_SAND_PROPS
----------------------------------------

CREATE TABLE HOMEPAGE.SR_GLOBAL_SAND_PROPS(
	GSP_ID			NVARCHAR(36) NOT NULL,
	GSP_NAME		NVARCHAR(36) NOT NULL,
	GSP_VALUE		NVARCHAR(36) NOT NULL,
	GSP_TYPE        NUMERIC(5,0) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.SR_GLOBAL_SAND_PROPS
	ADD CONSTRAINT PK_GSP_ID PRIMARY KEY (GSP_ID)
GO

ALTER TABLE HOMEPAGE.SR_GLOBAL_SAND_PROPS
	ADD CONSTRAINT UNIQUE_GSP_NAME UNIQUE (GSP_NAME)
GO
	
ALTER TABLE HOMEPAGE.SR_GLOBAL_SAND_PROPS		
	ADD CONSTRAINT GSP_TYPE_CHECK
	CHECK (GSP_TYPE >=0 AND GSP_TYPE < 4);
GO


--END 38374: DAO Layer Changes for Dynamic Global Properties for SAND 

---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start SEARCH FIXUP 89
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
---------------------------------------------------------------------------------
------------------------ START SEARCH -------------------------------------------
---------------------------------------------------------------------------------

--START 40252: SPR#WDWU8AAA3P : search cluster nodes will insert dup scheduler task when scheduler table is empty

----------------------------------------
--  HOMEPAGE.LOTUSCONNECTIONSTASK
----------------------------------------

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSTASK
GO    


ALTER TABLE HOMEPAGE.LOTUSCONNECTIONSTASK
    ADD CONSTRAINT UNIQUE_LCT_NAME UNIQUE (NAME)
GO    


--END 40252: SPR#WDWU8AAA3P : search cluster nodes will insert dup scheduler task when scheduler table is empty

---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start SEARCH FIXUP 95
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
---------------------------------------------------------------------------------
------------------------ START SEARCH -------------------------------------------
---------------------------------------------------------------------------------

DROP TABLE HOMEPAGE.SR_FACET_DOCS
GO

--46592: Rename FILESCONTENT_ID in HOMEPAGE.SR_INDEX_DOCS to FILE_COMPONENT_UUID

DROP TABLE HOMEPAGE.SR_INDEX_DOCS
GO

CREATE TABLE HOMEPAGE.SR_INDEX_DOCS(
	DOCUMENT_ID NVARCHAR(36) NOT NULL,
	DOCUMENT VARBINARY(MAX) NOT NULL,
	CRAWLING_VERSION NUMERIC(19,0) NOT NULL,
	ACTION NUMERIC(5,0) NOT NULL,
	UPDATE_TIME  DATETIME NOT NULL,
	RESUME_POINT NVARCHAR(256),
	SERVICE NVARCHAR(36) NOT NULL,
	FILES_REF_ID NVARCHAR(36),
	ATOM_ID  NVARCHAR(256)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.SR_INDEX_DOCS
	ADD CONSTRAINT PK_INDEX_DOCS_ID PRIMARY KEY (DOCUMENT_ID)
GO	

CREATE INDEX SR_INDEX_DOCS_CRAWL_VERSION_IDX
	ON HOMEPAGE.SR_INDEX_DOCS (CRAWLING_VERSION)
GO

CREATE INDEX SR_INDEX_DOCS_RPS_IDX
    ON HOMEPAGE.SR_INDEX_DOCS (RESUME_POINT,SERVICE)
GO

CREATE INDEX SR_INDEX_DOCS_ACT_IDX
	ON HOMEPAGE.SR_INDEX_DOCS(ACTION)
GO

CREATE INDEX SR_INDEX_DOCS_ACS_IDX
	ON HOMEPAGE.SR_INDEX_DOCS(SERVICE,ATOM_ID,CRAWLING_VERSION)
GO

ALTER TABLE HOMEPAGE.SR_INDEX_DOCS 
	ADD CONSTRAINT ID_ACT_CHECK
	CHECK (ACTION >= 0 AND ACTION < 4)
GO

ALTER TABLE HOMEPAGE.SR_INDEX_DOCS 
	ADD CONSTRAINT IGNORE_ACT_CHECK
	CHECK (ACTION <> 3 OR RESUME_POINT IS NULL) 
GO

--START 40269: PERF, homepage db,  this read sql needs index on sr_index_docs
CREATE INDEX SR_INDEX_DOCS_LLT4_IDX ON HOMEPAGE.SR_INDEX_DOCS(UPDATE_TIME ASC, ACTION DESC, CRAWLING_VERSION, SERVICE)
GO


---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------



-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%



-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start SEARCH FIXUP 96
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
---------------------------------------------------------------------------------
------------------------ START SEARCH -------------------------------------------
---------------------------------------------------------------------------------

--51089: [4.0] New Index On SR_INDEX_DOCS

CREATE INDEX SR_INDEX_DOCS_FRID_IDX 
	ON HOMEPAGE.SR_INDEX_DOCS(FILES_REF_ID ASC, SERVICE ASC, UPDATE_TIME DESC)
GO

---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start SEARCH FIXUP 100
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
---------------------------------------------------------------------------------
------------------------ START SEARCH -------------------------------------------
---------------------------------------------------------------------------------


CREATE INDEX SR_FEEDBACK_PERSON_ID_IDX 
	ON HOMEPAGE.SR_FEEDBACK(PERSON_ID)
GO

DELETE FROM HOMEPAGE.SR_INDEX_MANAGEMENT
GO

ALTER TABLE HOMEPAGE.SR_INDEX_MANAGEMENT 
ADD OUT_OF_SYNC NUMERIC(5,0) DEFAULT 0 NOT NULL
GO

--START 36202: Modify the indexing and text extraction processes in accordance with designs in Search CDD
DROP TABLE HOMEPAGE.SR_FILESCONTENT
GO

CREATE TABLE HOMEPAGE.SR_FILESCONTENT (
	FILESCONTENT_ID NVARCHAR(36) NOT NULL,
	URL NVARCHAR(256) NOT NULL,
	COMPONENT_UUID NVARCHAR(36) NOT NULL,
	COMPONENT NVARCHAR(36) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	LAST_MODIFIED_DATE DATETIME NOT NULL,
	LAST_ACCESSED_DATE DATETIME NOT NULL,
	IS_CURRENT NUMERIC(5,0) NOT NULL,
	FILE_SIZE NUMERIC(19,0) NOT NULL,
	CONTENT_SIZE NUMERIC(19,0) NOT NULL,
	INPUT_MIME_TYPE NVARCHAR(256) NOT NULL,
	CLAIMED NUMERIC(5,0) DEFAULT 0 NOT NULL,
	CLAIMED_TIME DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
 	FS_LOCAL_PATH NVARCHAR(256) NOT NULL,
	PROCESSOR NVARCHAR(36),
	PROCESSOR_STATE VARBINARY(MAX),
	CONTENT_LOCATION NVARCHAR(256) NOT NULL,
	IS_READY NUMERIC(5,0) NOT NULL	
)  ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD CONSTRAINT PK_FILESCONTENT_ID PRIMARY KEY (FILESCONTENT_ID)
GO

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD CONSTRAINT UNIQUE_COMP_UUID UNIQUE (COMPONENT_UUID,COMPONENT)
GO

CREATE INDEX SR_FILESCONTENT_IS_CURRENT_IDX 
	ON HOMEPAGE.SR_FILESCONTENT(IS_CURRENT);
GO

---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

--------------------------------------------------------------------------
--------------------------------------------------------------------------
--------------------------------------------------------------------------


--------------------------------------------------------------------------
-- ADDING NEW TABLES 
--------------------------------------------------------------------------

-------------------------------------------------------
-- 1) HOMEPAGE.BOARD
-------------------------------------------------------
CREATE TABLE HOMEPAGE.BOARD  (
	BOARD_ID nvarchar(256) NOT NULL,
	BOARD_CONTAINER_ID nvarchar(256) NOT NULL,
	BOARD_TYPE nvarchar(64) NOT NULL,
	BOARD_OWNER_ASSOC_ID nvarchar(36) NOT NULL,
	BOARD_OWNER_ASSOC_TYPE nvarchar(64) NOT NULL,
	CREATED DATETIME NOT NULL,
	CREATED_BY nvarchar(36) NOT NULL,
	LASTUPDATE DATETIME NOT NULL,
	LASTUPDATE_BY nvarchar(36) NOT NULL,
	IS_ENABLED NUMERIC(5, 0) DEFAULT 1 NOT NULL,
	VISIBILITY nvarchar(36),
	EDITABILITY nvarchar(36),
	WIDGET_ID nvarchar(36)		
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.BOARD 
	ADD CONSTRAINT BOARD_PK PRIMARY KEY (BOARD_ID);

ALTER TABLE HOMEPAGE.BOARD 
	ADD CONSTRAINT FK_BRD_OWNER FOREIGN KEY (BOARD_OWNER_ASSOC_ID) 
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

ALTER TABLE HOMEPAGE.BOARD 
	ADD CONSTRAINT FK_BRD_CREATED FOREIGN KEY (CREATED_BY) 
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);	

ALTER TABLE HOMEPAGE.BOARD 
	ADD CONSTRAINT FK_BRD_LASTUPDATE FOREIGN KEY (LASTUPDATE_BY) 
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

CREATE UNIQUE INDEX BOARD_OWNER_ASSOC_UIDX 
	ON HOMEPAGE.BOARD (BOARD_OWNER_ASSOC_ID ASC, BOARD_TYPE ASC);

ALTER TABLE HOMEPAGE.BOARD
	ADD CONSTRAINT CONTAINER_ID_UNQ UNIQUE (BOARD_CONTAINER_ID);
      
CREATE UNIQUE INDEX BRD_CONTAINER_ID_UIDX 
	ON HOMEPAGE.BOARD (BOARD_CONTAINER_ID);	
	
GO

--------------------------------------
-- HOMEPAGE.BOARD_ENTRIES to store the relationship between a reader and a status update
--------------------------------------
CREATE TABLE HOMEPAGE.BOARD_ENTRIES  (
	ENTRY_ID nvarchar(47) NOT NULL, -- the format will include in the pk also the creation time
	ENTRY_TYPE NUMERIC (5,0),
	CATEGORY_TYPE NUMERIC (5,0),
	SOURCE nvarchar(36),
	SOURCE_TYPE NUMERIC (5,0),
	ITEM_ID nvarchar(36),
	ITEM_URL nvarchar(2048),
	CONTAINER_ID nvarchar(256),
	CONTAINER_URL nvarchar(2048),
	CREATION_DATE DATETIME NOT NULL, -- used also by the seedlist, when something is created the update is equal to is created
	UPDATE_DATE DATETIME NOT NULL, -- this field is used and queried from the the seedlist for initial and incremental index
	ACTOR_UUID nvarchar(36),
	IS_COMMUNITY_STORY NUMERIC (5,0) DEFAULT 0,
	HAS_ATTACHMENT NUMERIC (5,0) DEFAULT 0,
	TARGET_SUBJECT_ID nvarchar(256),
	TAGS nvarchar(1024),
	SL_IS_UPDATED NUMERIC (5,0) DEFAULT 0 NOT NULL, -- this field is used by the seedlist
	SL_IS_DELETED NUMERIC (5,0) DEFAULT 0 NOT NULL, -- this field is used by the seedlist
	SL_UPDATE_DATE DATETIME NOT NULL, -- this field is used by the seedlist
	CONTENT varbinary (MAX),
	LAST_CONTRIBUTOR_ID nvarchar(36),
	CONTENT_LOCALE  nvarchar(5)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.BOARD_ENTRIES 
    ADD CONSTRAINT PK_BRD_ENTRIES PRIMARY KEY(ENTRY_ID);

ALTER TABLE HOMEPAGE.BOARD_ENTRIES
	ADD CONSTRAINT FK_CONTAINER_ID FOREIGN KEY (CONTAINER_ID) 
	REFERENCES HOMEPAGE.BOARD (BOARD_CONTAINER_ID);	    

CREATE INDEX NEWS_BRD_SL_UPDATE
    ON HOMEPAGE.BOARD_ENTRIES (SL_UPDATE_DATE ASC);

CREATE UNIQUE INDEX ITEM_ID_IDX
   ON HOMEPAGE.BOARD_ENTRIES (ITEM_ID ASC) INCLUDE (CONTAINER_ID);

CREATE INDEX CREATION_ITEM_IDX 
	ON HOMEPAGE.BOARD_ENTRIES (CREATION_DATE DESC, ITEM_ID DESC);
	
CREATE INDEX BRD_E_CONTAINER_ID_UIDX 
	ON HOMEPAGE.BOARD_ENTRIES (CONTAINER_ID);

CREATE INDEX BRD_SL_UPDATED_DEL 
	ON HOMEPAGE.BOARD_ENTRIES (SL_UPDATE_DATE ASC, SL_IS_DELETED);
	
CREATE INDEX BRD_ENTRIES_ITEM
	ON HOMEPAGE.BOARD_ENTRIES (ITEM_ID, SL_IS_DELETED);	
    
GO

--------------------------------------
-- HOMEPAGE.BOARD_COMMENTS
--------------------------------------
CREATE TABLE HOMEPAGE.BOARD_COMMENTS  (
	COMMENT_ID nvarchar(47) NOT NULL, -- the format will include in the pk also the creation time
	ACTOR_UUID nvarchar(36),
	CREATION_DATE DATETIME NOT NULL,
	ITEM_ID nvarchar(47),
	ENTRY_ID nvarchar(47),
	ITEM_URL nvarchar(2048),	
	CONTENT varbinary (MAX),
	UPDATE_DATE DATETIME,
	LAST_CONTRIBUTOR_ID nvarchar(36),
	CONTENT_LOCALE nvarchar(5),
	PUBLISHED_ORDER NUMERIC(5,0),
	TAGS nvarchar(1024)
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.BOARD_COMMENTS 
    ADD CONSTRAINT PK_BRD_COMMENT_ID PRIMARY KEY(COMMENT_ID);

ALTER TABLE HOMEPAGE.BOARD_COMMENTS
	ADD CONSTRAINT FK_BRD_COM_ENTRY FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.BOARD_ENTRIES (ENTRY_ID);

CREATE INDEX NEWS_BRD_ITEM_ID
    ON HOMEPAGE.BOARD_COMMENTS  (ITEM_ID);

CREATE INDEX NEWS_BRD_ITEM_CORR_ID
    ON HOMEPAGE.BOARD_COMMENTS  (ENTRY_ID);

CREATE INDEX CREATION_DATE_IDX 
	ON HOMEPAGE.BOARD_COMMENTS (CREATION_DATE ASC);
	
CREATE INDEX ITEM_CORR_CREATION_IDX 
	ON HOMEPAGE.BOARD_COMMENTS (ENTRY_ID, CREATION_DATE ASC);
	
CREATE INDEX ITEM_ITEM_CORR_IDX
	ON HOMEPAGE.BOARD_COMMENTS (ITEM_ID ASC, ENTRY_ID ASC);	

CREATE UNIQUE INDEX BRD_COMM_ITEM_COR_PUB
    ON HOMEPAGE.BOARD_COMMENTS (ENTRY_ID, PUBLISHED_ORDER ASC); 
	
GO

----------------------------------------------------------------
-- ADDING BOARD_OBJECT_REFERENCE
----------------------------------------------------------------
CREATE TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE (
	OBJECT_ID nvarchar(47) NOT NULL,
	ENTRY_ID nvarchar(47) NOT NULL,
	DISPLAY_NAME nvarchar(2048),
	IMAGE_NAME   nvarchar(2048),
	NAME nvarchar(512), 
	URL nvarchar(1024), 
	CONTENT nvarchar(4000),
	IMAGE_URL nvarchar(1024),
	SOURCE  nvarchar(36),
	SOURCE_TYPE  NUMERIC(5 ,0),
	CREATION_DATE DATETIME,
	MIME_TYPE nvarchar(36),
	AUTHOR_ID nvarchar(36),
	AUTHOR_DISPLAY_NAME nvarchar(256),
	OBJECT_META_DATA nvarchar(4000),
	OBJECT_META_DATA_2 nvarchar(4000),
	FULL_OBJECT_META_DATA varchar(MAX),
	OBJECT_EXTERNAL_ID nvarchar(256)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE 
    ADD CONSTRAINT PK_BRD_OBJ_ID PRIMARY KEY(OBJECT_ID);

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE
	ADD CONSTRAINT FK_BRD_OBJ_ENTRY FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.BOARD_ENTRIES (ENTRY_ID);

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE
	ADD CONSTRAINT FK_BRD_AUTHOR_ID FOREIGN KEY (AUTHOR_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

CREATE INDEX BRD_ENTRY_IDX
    ON HOMEPAGE.BOARD_OBJECT_REFERENCE (ENTRY_ID);

CREATE INDEX BRD_AUTHOR_IDX
    ON HOMEPAGE.BOARD_OBJECT_REFERENCE (AUTHOR_ID);    
GO

--------------------------------------
-- HOMEPAGE.BOARD_RECOMMANDATIONS to store the relationship between a board entries and something which has been recommended
---------------------------------------
CREATE TABLE HOMEPAGE.BOARD_RECOMMENDATIONS  (
	RECOMMENDATION_ID nvarchar(47) NOT NULL, --primary key
	RECOMMENDER_ID nvarchar(36) NOT NULL, -- PERSON_ID of the recommender, FK to PERSON table
	ENTRY_ID nvarchar(47) NOT NULL,
	SOURCE_TYPE NUMERIC (5,0) NOT NULL, 
	CREATION_DATE DATETIME
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.BOARD_RECOMMENDATIONS 
    ADD CONSTRAINT PK_BRD_RECOMM_ID PRIMARY KEY(RECOMMENDATION_ID);
    
ALTER TABLE HOMEPAGE.BOARD_RECOMMENDATIONS
	ADD CONSTRAINT FK_BRD_RECOMMENDER FOREIGN KEY (RECOMMENDER_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

ALTER TABLE HOMEPAGE.BOARD_RECOMMENDATIONS
	ADD CONSTRAINT FK_BRD_ENTRY_ID FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.BOARD_ENTRIES (ENTRY_ID);    

CREATE INDEX BRD_REC_STORY_ID
    ON HOMEPAGE.BOARD_RECOMMENDATIONS (ENTRY_ID);
    
CREATE UNIQUE INDEX BRD_RECOM_ENTRY_ID
    ON HOMEPAGE.BOARD_RECOMMENDATIONS (RECOMMENDER_ID, ENTRY_ID);
    
CREATE INDEX BRD_RECOMMENDER_ID
    ON HOMEPAGE.BOARD_RECOMMENDATIONS (RECOMMENDER_ID);
    
GO

-------------------------------------------------------
-- HOMEPAGE.BOARD_CURRENT_STATUS 
-- table to store the history of current status updates
-------------------------------------------------------
CREATE TABLE HOMEPAGE.BOARD_CURRENT_STATUS  (
	CURRENT_STATUS_ID nvarchar(47) NOT NULL, -- the format will include in the pk also the creation time
	ACTOR_UUID nvarchar(36) NOT NULL,
	ENTRY_ID nvarchar(47) NOT NULL,
	CURRENT_STATUS_SET DATETIME NOT NULL
) ON [PRIMARY]
GO


ALTER TABLE HOMEPAGE.BOARD_CURRENT_STATUS 
    ADD CONSTRAINT PK_CUR_ST_ID PRIMARY KEY (CURRENT_STATUS_ID);

ALTER TABLE HOMEPAGE.BOARD_CURRENT_STATUS
	ADD CONSTRAINT FK_CUR_ST_ACTOR_ID FOREIGN KEY (ACTOR_UUID)
	REFERENCES HOMEPAGE.PERSON(PERSON_ID);

ALTER TABLE HOMEPAGE.BOARD_CURRENT_STATUS
	ADD CONSTRAINT FK_CUR_ST_ENTRY_ID FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.BOARD_ENTRIES(ENTRY_ID);

CREATE INDEX CURRENT_STATUS_INDEX 
	ON HOMEPAGE.BOARD_CURRENT_STATUS	(ACTOR_UUID ASC, CURRENT_STATUS_SET ASC, CURRENT_STATUS_ID ASC, ENTRY_ID ASC);

CREATE UNIQUE INDEX ACTOR_ENTRY 
	ON HOMEPAGE.BOARD_CURRENT_STATUS (ACTOR_UUID);
	
CREATE INDEX BRD_CURRENT_STATUS 
	ON HOMEPAGE.BOARD_CURRENT_STATUS (ENTRY_ID);
	
GO
	
---------------------------------------------------------------------------------
-- ADDING QUARANTINE TABLE
---------------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.DELETED_STORIES_QUEUE (
	QUEUE_ID nvarchar(36) NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	SOURCE 	nvarchar(36) NOT NULL, -- this is externalized
	SOURCE_TYPE NUMERIC(5,0) NOT NULL,
	STATUS NUMERIC(5,0) DEFAULT 0 NOT NULL	
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.DELETED_STORIES_QUEUE 
  	ADD CONSTRAINT PK_QUARANTINE PRIMARY KEY(QUEUE_ID);

CREATE INDEX DELETED_STORY_ID 
  	ON HOMEPAGE.DELETED_STORIES_QUEUE (STORY_ID);

GO 

-----------------------------------------------
-- HOMEPAGE.NR_COMM_SETTINGS
-----------------------------------------------

CREATE TABLE HOMEPAGE.NR_COMM_SETTINGS (
	COMM_ID nvarchar(36) NOT NULL,
	MICROBLOGGING_WRITE_ACL NUMERIC(5 ,0),
	FLAGS nvarchar(36)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_COMM_SETTINGS
    ADD CONSTRAINT PK_COMM_ID PRIMARY KEY(COMM_ID);
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



------------------------------------------------------------------------------------------------
-- [START] UPDATING SET OF INDEXES FOR NR_NEWS_STATUS_NETWORK AND NR_NEWS_STATUS_COMMENT TABLES
------------------------------------------------------------------------------------------------
DROP INDEX NR_NEWS_SC_ITEM_COR ON HOMEPAGE.NR_NEWS_STATUS_COMMENT;

GO


ALTER TABLE HOMEPAGE.NR_NEWS_COMMENT_CONTENT
  	DROP CONSTRAINT FK_C_COMMENT_ID;
GO

ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_COMMENT 
	DROP CONSTRAINT PK_NEWS_COMMENT_ID;

GO

CREATE CLUSTERED INDEX CL_STATUS_COMMENT_IDX ON HOMEPAGE.NR_NEWS_STATUS_COMMENT (ITEM_CORRELATION_ID);
 
GO

ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_COMMENT
  	ADD CONSTRAINT PK_NEWS_COMMENT_ID PRIMARY KEY(NEWS_STATUS_COMMENT_ID);
GO

ALTER TABLE HOMEPAGE.NR_NEWS_COMMENT_CONTENT
  	ADD CONSTRAINT FK_C_COMMENT_ID FOREIGN KEY (NEWS_STATUS_COMMENT_ID)
	REFERENCES HOMEPAGE.NR_NEWS_STATUS_COMMENT(NEWS_STATUS_COMMENT_ID);

GO

-----------------------------------------------------------------------------
-- DROP AND RECREATE THE INDEXES USING UPDATE_DATE INSTEAD OF CREATION_DATE
-----------------------------------------------------------------------------
DROP INDEX NR_NEWS_SN_READER_FOLL ON HOMEPAGE.NR_NEWS_STATUS_NETWORK;
GO

CREATE INDEX NR_NEWS_SN_READER_FOLL
    ON HOMEPAGE.NR_NEWS_STATUS_NETWORK (UPDATE_DATE DESC, READER_ID, IS_FOLLOW_NEWS);
GO

DROP INDEX NR_NEWS_SN_READER_NETW ON HOMEPAGE.NR_NEWS_STATUS_NETWORK;
GO

CREATE INDEX NR_NEWS_SN_READER_NETW
    ON HOMEPAGE.NR_NEWS_STATUS_NETWORK (UPDATE_DATE DESC, READER_ID, IS_NETWORK_NEWS);
GO

DROP INDEX NR_NEWS_STATUS_NETWORK_READER ON HOMEPAGE.NR_NEWS_STATUS_NETWORK;
GO

CREATE INDEX NR_NEWS_STATUS_NETWORK_READER
    ON HOMEPAGE.NR_NEWS_STATUS_NETWORK (UPDATE_DATE DESC, READER_ID);
GO

DROP INDEX NR_STATUS_NETWORK_DATE ON HOMEPAGE.NR_NEWS_STATUS_NETWORK;
GO

CREATE INDEX NR_STATUS_NETWORK_DATE
    ON HOMEPAGE.NR_NEWS_STATUS_NETWORK (UPDATE_DATE ASC);
GO

------------------------------------------------------------------------------------------------
-- [END] UPDATING SET OF INDEXES FOR NR_NEWS_STATUS_NETWORK AND NR_NEWS_STATUS_COMMENT TABLES
------------------------------------------------------------------------------------------------



-----------------------------------------------------------------------
-- [START] SEEDLIST
-----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_AS_SEEDLIST (
	SEEDLIST_ID nvarchar(36) NOT NULL, -- pk which is not linked to any value
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL, -- when an item id is moderated
	IS_DELETED NUMERIC(5 ,0) DEFAULT 0 NOT NULL, -- when a story is moderated
	DATA nvarchar(4000),
	FULL_DATA varbinary (MAX), -- in case data is not enough big
	FOLLOWERS nvarchar(4000),
	FULL_FOLLOWERS varbinary (MAX), -- in case followers is not enough big
	STORY_ID nvarchar(36) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	UPDATE_DATE DATETIME NOT NULL,
	ITEM_ID nvarchar(36)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_AS_SEEDLIST
	ADD CONSTRAINT NR_SEEDLIST_PK PRIMARY KEY (SEEDLIST_ID);

CREATE UNIQUE INDEX NR_SL_STR_UNIQUE
	ON HOMEPAGE.NR_AS_SEEDLIST(STORY_ID);	

CREATE INDEX NR_SL_ITEM_ID_IX
	ON HOMEPAGE.NR_AS_SEEDLIST (ITEM_ID);

CREATE INDEX NR_SL_UD_DELTED_IX
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC, IS_DELETED); 

CREATE INDEX NR_SL_UD_VISIBLE_IX
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC, IS_VISIBLE); 
	
CREATE INDEX NR_SL_UD_DELETED_VIS
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC, IS_DELETED, IS_VISIBLE);
	
CREATE INDEX NR_SL_UD_DELETED
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC); 	
	
GO

-----------------------------------------------------------------------
-- [END] SEEDLIST
-----------------------------------------------------------------------

------------------------------------------------
-- HOMEPAGE.NR_AS_COLLECTION_CONFIG
------------------------------------------------
CREATE TABLE HOMEPAGE.NR_AS_COLLECTION_CONFIG (
	COLLECTION_ID nvarchar (50) NOT NULL,
	XML_DATA varchar (MAX) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_AS_COLLECTION_CONFIG
	ADD CONSTRAINT NR_AS_CONFIG_PK PRIMARY KEY (COLLECTION_ID);
	
GO

------------------------------------------------
-- HOMEPAGE.NR_AS_CRAWLER_STATUS
------------------------------------------------	
CREATE TABLE HOMEPAGE.NR_AS_CRAWLER_STATUS (
	COLLECTION_ID nvarchar (50) NOT NULL,
	XML_DATA varchar (MAX) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_AS_CRAWLER_STATUS
	ADD CONSTRAINT NR_AS_STATUS_PK PRIMARY KEY (COLLECTION_ID);
	
GO

------------------------------------------------------------------------------
-- ADD AS_CONTENT_INDEX_STATS TABLE
------------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.NR_AS_CONTENT_INDEX_STATS (
	STAT_ID nvarchar(36) NOT NULL,
	STAT_NAME nvarchar(2048),
	STAT_VALUE varbinary(MAX),
	STAT_UPDATE_DATE DATETIME
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_AS_CONTENT_INDEX_STATS
  	ADD CONSTRAINT NR_CONTENTSTATS_PK PRIMARY KEY(STAT_ID);

GO



----------------------------------------------------------------
-- OAUTH
----------------------------------------------------------------

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: OAuth Consumer Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
--
-- Replace							Example
--  HOMEPAGE					HOMEPAGE
--	 		HOMEPAGE.
-- 	IN USERSPACE8K INDEX IN USERSPACE8K
--  LCUSER
--  STMT == End statement
--
-- Types - You will need to substitue these tokens
--  VARCHAR
--  TIMESTAMP

-------------------------------------------
-- HOMEPAGE.OAUTH1_TOKEN
-------------------------------------------	 
CREATE TABLE HOMEPAGE.OAUTH1_TOKEN (
	ID nvarchar(256) NOT NULL PRIMARY KEY,
	TOKEN nvarchar(1024) NOT NULL,
	SECRET nvarchar(1024) NOT NULL,
	EXPIRATION DATETIME,
	CREATED DATETIME NOT NULL,
	MODIFIED DATETIME NOT NULL
) ON [PRIMARY]
GO

-------------------------------------------
-- HOMEPAGE.OAUTH1_PROVIDER
-------------------------------------------	 
CREATE TABLE HOMEPAGE.OAUTH1_PROVIDER (
	ID nvarchar(256) NOT NULL PRIMARY KEY,
	NAME nvarchar(254) NOT NULL,
	DESCRIPTION nvarchar(1024),
	REQUESTTOKENURL nvarchar(512) NOT NULL,
	AUTHORIZEURL nvarchar(512) NOT NULL,
	ACCESSTOKENURL nvarchar(512) NOT NULL,
	BASEURL nvarchar(512),
	MANAGEURL nvarchar(512),
	REGISTERURL nvarchar(512),
	SIGNMETHOD nvarchar(32),
	VERSION nvarchar(8),
	CREATED DATETIME NOT NULL,
	MODIFIED DATETIME NOT NULL,
	CONSTRAINT PRVD_NAME_UK UNIQUE (NAME)
) ON [PRIMARY]
GO
	
-------------------------------------------
-- HOMEPAGE.OAUTH1_CLIENT
-------------------------------------------
CREATE TABLE HOMEPAGE.OAUTH1_CLIENT (
	ID nvarchar(256) NOT NULL PRIMARY KEY,
	PERSON_ID nvarchar(36) NOT NULL,
	NAME nvarchar(254) NOT NULL,
	TOKENID nvarchar(256) NOT NULL,
	PROVIDERID nvarchar(256) NOT NULL,
	DESCRIPTION nvarchar(1024),
	CALLBACKURL nvarchar(512),	
	CREATED DATETIME NOT NULL,
	MODIFIED DATETIME NOT NULL,
	CONSTRAINT CLNT_NAME_UK UNIQUE (NAME)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OAUTH1_CLIENT 
	ADD CONSTRAINT CLNT_FK FOREIGN KEY (PROVIDERID) 
	REFERENCES HOMEPAGE.OAUTH1_PROVIDER(ID);

ALTER TABLE HOMEPAGE.OAUTH1_CLIENT 
	ADD CONSTRAINT CLNT_TOKEN_FK FOREIGN KEY (TOKENID) 
	REFERENCES HOMEPAGE.OAUTH1_TOKEN(ID);
	
ALTER TABLE HOMEPAGE.OAUTH1_CLIENT
	ADD CONSTRAINT FK_OA1CL_PERSON_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON(PERSON_ID);		
	
GO

-------------------------------------------
-- HOMEPAGE.OAUTH1_CONTEXT
-------------------------------------------
CREATE TABLE HOMEPAGE.OAUTH1_CONTEXT (
	ID nvarchar(256) NOT NULL PRIMARY KEY,
	CLIENTID nvarchar(256) NOT NULL,
	PERSON_ID nvarchar(36),
	TOKENID nvarchar(256) NOT NULL,
	CREATED DATETIME NOT NULL,
	MODIFIED DATETIME NOT NULL,
	NONCE nvarchar(256),
	EXPIRATION DATETIME,
	AUTHORIZED NUMERIC(5,0) DEFAULT 0 NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OAUTH1_CONTEXT
	ADD CONSTRAINT CONTEXT_CLNT_FK FOREIGN KEY (CLIENTID) 
	REFERENCES HOMEPAGE.OAUTH1_CLIENT(ID);

ALTER TABLE HOMEPAGE.OAUTH1_CONTEXT
	ADD CONSTRAINT AUTH_TOKEN_FK FOREIGN KEY (TOKENID) 
	REFERENCES HOMEPAGE.OAUTH1_TOKEN(ID);
	
ALTER TABLE HOMEPAGE.OAUTH1_CONTEXT
	ADD CONSTRAINT FK_OA1C_PERSON_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON(PERSON_ID);		

GO
	
-------------------------------------------
-- HOMEPAGE.OAUTH2_PROVIDER
-------------------------------------------
CREATE TABLE HOMEPAGE.OAUTH2_PROVIDER (
  NAME NVARCHAR(254) NOT NULL PRIMARY KEY,
  CLIENT_AUTH NVARCHAR(254),
  AUTH_HEADER SMALLINT,
  URL_PARAM SMALLINT,
  AUTH_URL NVARCHAR(1024),
  TOKEN_URL NVARCHAR(1024)
) ON [PRIMARY]
GO

-------------------------------------------
-- HOMEPAGE.OAUTH2_CLIENT
-------------------------------------------
CREATE TABLE HOMEPAGE.OAUTH2_CLIENT (
  NAME NVARCHAR(254) NOT NULL PRIMARY KEY,
  PROVIDER_NAME NVARCHAR(254) NOT NULL,
  REDIRECT_URI NVARCHAR(1024),
  CTYPE NVARCHAR(254) NOT NULL,
  GRANT_TYPE NVARCHAR(254) NOT NULL,
  CLIENT_ID NVARCHAR(1024) NOT NULL,
  CLIENT_SECRET NVARCHAR(1024) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OAUTH2_CLIENT
  ADD CONSTRAINT FK_CLIENT_PRO_NAME FOREIGN KEY(PROVIDER_NAME)
  REFERENCES HOMEPAGE.OAUTH2_PROVIDER(NAME);
GO
    
-------------------------------------------
-- HOMEPAGE.OAUTH2_GADGET_BINDING
-------------------------------------------
CREATE TABLE HOMEPAGE.OAUTH2_GADGET_BINDING (
  ID nvarchar(36) NOT NULL PRIMARY KEY,
  WIDGET_ID nvarchar(36) NOT NULL,
  URI_SHA1 CHAR(40) NOT NULL, 
  SERVICE_NAME nvarchar(254)  NOT NULL,
  URI nvarchar(1024) NOT NULL,
  CLIENT_NAME nvarchar(254) NOT NULL,
  OVERRIDES NUMERIC(5,0) NOT NULL,
  CONSTRAINT URI_GADGET_UK UNIQUE (URI_SHA1, SERVICE_NAME),
  CONSTRAINT WIDGET_GADGET_UK UNIQUE (WIDGET_ID, SERVICE_NAME)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OAUTH2_GADGET_BINDING
  ADD CONSTRAINT FK_BINDING_CLIENT FOREIGN KEY(CLIENT_NAME)
  REFERENCES HOMEPAGE.OAUTH2_CLIENT(NAME);

ALTER TABLE HOMEPAGE.OAUTH2_GADGET_BINDING
  ADD CONSTRAINT FK_BINDING_WIDGET FOREIGN KEY(WIDGET_ID)
  REFERENCES HOMEPAGE.WIDGET(WIDGET_ID);
  
CREATE INDEX OA2T_GBINDING_WID
	ON HOMEPAGE.OAUTH2_GADGET_BINDING (WIDGET_ID);

GO


-------------------------------------------
-- HOMEPAGE.OAUTH2_TOKEN
-------------------------------------------
CREATE TABLE HOMEPAGE.OAUTH2_TOKEN (
  ID nvarchar(254) NOT NULL PRIMARY KEY,
  URI nvarchar(1024) NOT NULL,
  SERVICE_NAME nvarchar(254) NOT NULL,
  PERSON_ID nvarchar(36) NOT NULL,
  SCOPE nvarchar(1024) NOT NULL,
  TTYPE NUMERIC(5,0) NOT NULL,
  SECRET nvarchar(1024),
  EXPIRES DATETIME,
  ISSUED DATETIME NOT NULL,
  TOKEN_TYPE nvarchar(254),
  MAC_SECRET nvarchar(1024),
  MAC_ALG nvarchar(254),
  MAC_EXT nvarchar(254),
  PROPS nvarchar(1024)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OAUTH2_TOKEN
	ADD CONSTRAINT FK_OA2T_PERSON_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON(PERSON_ID);	

CREATE INDEX OA2T_PERSON_SERVICE
	ON HOMEPAGE.OAUTH2_TOKEN (PERSON_ID, SERVICE_NAME);
	
GO


CREATE TABLE HOMEPAGE.OH2P_CACHE (
	LOOKUPKEY nvarchar(256) NOT NULL, 
	UNIQUEID nvarchar(128) NOT NULL, 
	COMPONENTID nvarchar(256) NOT NULL, 
	TYPE nvarchar(64) NOT NULL, 
	SUBTYPE nvarchar(64), 
	CREATEDAT NUMERIC(19,0), 
	LIFETIME NUMERIC(10,0), 
	EXPIRES NUMERIC(19,0), 
	TOKENSTRING nvarchar(2048) NOT NULL, 
	CLIENTID nvarchar(64) NOT NULL, 
	USERNAME nvarchar(64) NOT NULL, 
	SCOPE nvarchar(512) NOT NULL, 
	REDIRECTURI nvarchar(2048), 
	STATEID nvarchar(64) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OH2P_CACHE 
	ADD CONSTRAINT PK_LOOKUPKEY PRIMARY KEY (LOOKUPKEY);

CREATE INDEX OH2P_CACHE_EXPIRES ON HOMEPAGE.OH2P_CACHE (EXPIRES ASC);

GO


CREATE TABLE HOMEPAGE.OH2P_CLIENTCFG (
	COMPONENTID nvarchar(256) NOT NULL, 
	CLIENTID nvarchar(256) NOT NULL, 
	CLIENTSECRET nvarchar(256), 
	DISPLAYNAME nvarchar(256) NOT NULL, 
	REDIRECTURI nvarchar(2048), 
	ENABLED NUMERIC(5,0)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OH2P_CLIENTCFG 
	ADD CONSTRAINT PK_COMPIDCLIENTID PRIMARY KEY (COMPONENTID,CLIENTID);

GO



-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END OAuth Consumer Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 



------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
-- **************************************  DATA MIGRATION *************************************
-- ********************************************************************************************
-- ********************************************************************************************
------------------------------------------------------------------------------------------------

------------
--- START INSERT NR_SOURCE_TYPE
------------

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('activities_c9cax4cc4x80bx51af2ddef2c', 1, 'activities', 'activities', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconActivities16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconActivities16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('blogs_c9cax4cc4x80bx51af2ddef2c', 2, 'blogs', 'blogs', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconBlogs16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconBlogs16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('communities_c9cax4cc4x80bx51af2d', 3, 'communities', 'communities', '${connections}/resources/web/com.ibm.lconn.core.styles/images//iconCommunities16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images//iconCommunities16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('wikis_c9cax4cc4x80bx51af2ddef2c', 4, 'wikis', 'wikis', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconWikis16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconWikis16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('profiles_c9cax4cc4x80bx51af2ddef2c', 5, 'profiles', 'profiles', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconProfiles16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconProfiles16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('homepage_c9cax4cc4x80bx51af2ddef2c', 6, 'homepage', 'homepage', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconHome16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconHome16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('dogear_c9cax4cc4x80bx51af2ddef2c', 7, 'dogear', 'dogear', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('files_c9cax4cc4x80bx51af2ddef2c', 8, 'files', 'files', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconFiles16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconFiles16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, ORGANIZATION_ID, IMAGE_URL_SSL)
VALUES ('forums_c9cax4cc4x80bx51af2ddef2c', 9, 'forums', 'forums', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconForums16.png', null, null, 1, null, 'default', '${connections}/resources/web/com.ibm.lconn.core.styles/images/iconForums16.png');

------------
--- END INSERT NR_SOURCE_TYPE
------------


-----------------------------------------------------------
-- [START] Adding templates into HOMEPAGE.NR_TEMPLATE
-----------------------------------------------------------
INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('general-containerffasfh3452yst42465','container','containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('general-item-slkmg9015bxBNKbkHkdf65','item','itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('general-correllation-asdfbe898Bjr95','correlationitem','correlationName;correlationHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('Actor-3913FB2B6B5D4323BFD4DAAC189B8','Actor', 'actorInternalId', 'profilePhoto', 1); 

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('Object-CEDEBA0964F2442EB3E5155493C0','Object', 'asObjectType;asObjectInternalId;asObjectName;asObjectHtmlPath', 'activityObject', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('Target-5EF5EDF61F9F47F692ED1CDFDF86','Target', 'asTargetType;asTargetInternalId;asTargetName;asTargetHtmlPath', 'activityObject', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('repostIcon-oPldKwZTaR7aAiPFw4L08CyRW','repostIcon', 'asRepostIcon', 'repostIcon', 1); 

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('itemAuthor-wPldKwZTaR7aAiPFw4L08CyRW','Author', 'asItemAuthor','profilePhoto', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('itemauthor-wPldKwZTaR7aAiPFw4L08CyRW','author', 'asItemAuthor','profilePhoto',  1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('itemCorrAuthorkla9034jl89vasgf3k5Wfd','correlationItemAuthor', 'correlationItemAuthor','profilePhoto',  1);

GO

-----------------------------------------------------------
-- [END] Adding templates into HOMEPAGE.NR_TEMPLATE
----------------------------------------------------------


-- DATA MIGRATION

/* Bookmarks widgets */
UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png'
WHERE WIDGET_ID='dogear46x0a77x4a43x82aaxb00187218631';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png'
WHERE WIDGET_ID='dembk46x0a77x4a43x82aaxb00187218631';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png'
WHERE WIDGET_ID='depbk46x0a77x4a43x82aaxb00187218631';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png'
WHERE WIDGET_ID='derbk46x0a77x4a43x82aaxb00187218631';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png'
WHERE WIDGET_ID='dewl46x0a77x4a43x82aaxb00187218631';

/* Activities widgets */
UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png'
WHERE WIDGET_ID='activitixa187x491dxa4bfx2e1261d0b6ec';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png'
WHERE WIDGET_ID='myactxa187x491dxa4bfx2e1261d0b6ec';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png'
WHERE WIDGET_ID='pubactxa187x491dxa4bfx2e1261d0b6ec';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png'
WHERE WIDGET_ID='activities-sidebar7x4229x8';

/* Communities widgets. */
UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png'
WHERE WIDGET_ID='communitxe7c4x4e08xab54x80e7a4eb8933';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png'
WHERE WIDGET_ID='mycommunxe7c4x4e08xab54x80e7a4eb8933';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png'
WHERE WIDGET_ID='pubcommuxe7c4x4e08xab54x80e7a4eb8933';

/* Blogs widget. */
UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBlogs16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBlogs16.png'
WHERE WIDGET_ID='blogs448xcd34x4565x9469x9c34fcefe48c';

/* Profiles widget. */
UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png'
WHERE WIDGET_ID='profilesxaac7x4229x87bbx9a1c3551c591';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png'
WHERE WIDGET_ID='myprofisxaac7x4229x87bbx9a1c3551c591';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png'
WHERE WIDGET_ID='colprofsxaac7x4229x87bbx9a1c3551c591';

/* Wiki widget. */
UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png'
WHERE WIDGET_ID='mywikiz1xaac7x4229x87BBx91ac3551c591';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png'
WHERE WIDGET_ID='pop-wiki1xaac7x4229x87BBx91ac3551c5';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png'
WHERE WIDGET_ID='latest-wiki5jz1xaac7x4229x87BBx91ac';

/* Files widget. */
UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png'
WHERE WIDGET_ID='myFilesPb86locI7vRV4yY1KKawZvE8Qul88';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png'
WHERE WIDGET_ID='sharedFilesV4fv72LD5NAcGv2nbrex0ExEq';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png',
    WIDGET_SECURE_ICON='${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png'
WHERE WIDGET_ID='sharedFilesV4fv72LD5NAcGv2nbrex0ExEq';

GO

-- Update Dogear widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/dogear.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/dogear.xml'
WHERE WIDGET_ID='dogear46x0a77x4a43x82aaxb00187218631';

UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/dogear.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/dogear.xml'
WHERE WIDGET_ID='dogear46-0a77-4a43-82aa-b00187218631';

-- Update My Bookmarks widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/personal/mybookmarks.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/personal/mybookmarks.xml'
WHERE WIDGET_ID='dembk46x0a77x4a43x82aaxb00187218631';

-- Update Popular Bookmarks widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/popular/popularbookmarks.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/popular/popularbookmarks.xml'
WHERE WIDGET_ID='depbk46x0a77x4a43x82aaxb00187218631';

-- Update Recent Bookmarks widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/recent/recentbookmarks.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/recent/recentbookmarks.xml'
WHERE WIDGET_ID='derbk46x0a77x4a43x82aaxb00187218631';

-- Update Watchlist widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/watching/watchlist.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/watching/watchlist.xml'
WHERE WIDGET_ID='dewl46x0a77x4a43x82aaxb00187218631';

-- Update Activities Original widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/activities.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/activities.xml'
WHERE WIDGET_ID='activitixa187x491dxa4bfx2e1261d0b6ec';

-- Update MyActivities Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/personal/myactivities.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/personal/myactivities.xml'
WHERE WIDGET_ID='myactxa187x491dxa4bfx2e1261d0b6ec';

-- Update Public Activities Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/pub/publicactivities.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/pub/publicactivities.xml'
WHERE WIDGET_ID='pubactxa187x491dxa4bfx2e1261d0b6ec';

-- Update Sidebar Activities ToDo Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/activitiesTodoList.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/activitiesTodoList.xml'
WHERE WIDGET_ID='activities-sidebar7x4229x8';

-- Update Communities Original Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/communities.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/communities.xml'
WHERE WIDGET_ID='communitxe7c4x4e08xab54x80e7a4eb8933';

-- Update MyCommunities Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/personal/mycommunities.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/personal/mycommunities.xml'
WHERE WIDGET_ID='mycommunxe7c4x4e08xab54x80e7a4eb8933';

-- Update Public Communities Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/pub/publiccommunities.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/pub/publiccommunities.xml'
WHERE WIDGET_ID='pubcommuxe7c4x4e08xab54x80e7a4eb8933';

-- Update Blogs widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/blogs/blogs.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/blogs/blogs.xml'
WHERE WIDGET_ID='blogs448xcd34x4565x9469x9c34fcefe48c';

-- Update Profiles Original Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/profiles.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/profiles.xml'
WHERE WIDGET_ID='profilesxaac7x4229x87bbx9a1c3551c591';

-- Update MyProfile widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/personal/myprofile.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/personal/myprofile.xml'
WHERE WIDGET_ID='myprofisxaac7x4229x87bbx9a1c3551c591';

-- Update Colleague Profile widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/colleagues/colleagueprofile.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/colleagues/colleagueprofile.xml'
WHERE WIDGET_ID='colprofsxaac7x4229x87bbx9a1c3551c591';

-- Update MyWiki widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/mywiki.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/mywiki.xml'
WHERE WIDGET_ID='mywikiz1xaac7x4229x87BBx91ac3551c591';

-- Update Popular Wiki Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/popularwiki.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/popularwiki.xml'
WHERE WIDGET_ID='pop-wiki1xaac7x4229x87BBx91ac3551c5';

-- Update Latest Wiki Widget 
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/latestwiki.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/latestwiki.xml'
WHERE WIDGET_ID='latest-wiki5jz1xaac7x4229x87BBx91ac';

-- Update MyFiles widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/files.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/files.xml'
WHERE WIDGET_ID='myFilesPb86locI7vRV4yY1KKawZvE8Qul88';

-- Update Files shared with me Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/sharedFiles.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/sharedFiles.xml'
WHERE WIDGET_ID='sharedFilesV4fv72LD5NAcGv2nbrex0ExEq';

-- Update Sand widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/sand/recommend.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/sand/recommend.xml'
WHERE WIDGET_ID='recommend7x4f6hd93kd9';

GO

-----------------------------------------------
--  Recommendations widget is editable  [Work Item 52621]
-----------------------------------------------
UPDATE HOMEPAGE.WIDGET SET WIDGET_SYSTEM = 1 WHERE WIDGET_TITLE = '%widget.sand.recommend.name';
GO

---------------------------------------------------------------------------------
--Community Event Widget
---------------------------------------------------------------------------------

INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('commuevtxe7c4x4e08xab54x80e7a4eb8933', '%widget.communities.event.name', '%widget.communities.event.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.calendar/CalendarGadget.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'COMMUNITIES', 1,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.calendar/CalendarGadget.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png',1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('73de6654x9317x4195x90c0x696c1b6da4ff', 'communities', 'commuevtxe7c4x4e08xab54x80e7a4eb8933');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('UPDATES_communityevent-sidebar', 'commuevtxe7c4x4e08xab54x80e7a4eb8933', '_panel.updatex4a43x82aaxb00187218631', 'primary');

GO

------------------------------
------------------------------
INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('status_update_x8b0bx51af2ddef2cd', 11, '%status_updates', 'status_updates');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('ext_9cax4cc4x8b0bx51af2ddef2cd', 12, '%external', 'external');

-- Adding four new categories for ui rendering of actianable stories
INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('saved_readers', 13, '%saved_readers', 'saved_readers');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('actionable_directed_notification_bx', 14, '%actionable_directed_notification', 'actionable_directed_notification');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('actionable_todo_______cax4cc4x8b0bx', 15, '%actionable_todo', 'actionable_todo');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('actionable_action_required__4x8b0bx', 16, '%actionable_action_required', 'actionable_action_required');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('discovery-view', 17, '%discovery', 'discovery');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('profiles-view', 18, '%profiles-view', 'profiles-view');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('notification-sent', 19, '%notification-sent', 'notification-sent');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('notification-received', 20, '%notification-received', 'notification-received');

INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('topics', 21, '%topics', 'topics');

-- Adding insert for new: NR_RESOURCE_TYPE
INSERT INTO HOMEPAGE.NR_RESOURCE_TYPE (RESOURCE_TYPE_ID, RESOURCE_TYPE, RESOURCE_TYPE_NAME, RESOURCE_TYPE_DESC)
VALUES ('topics____0f1xc9cax4cc4x8cdb0bx51f2d', 15, '%topics', 'topics');

GO

COMMIT;
----------------------------------------------------------------------------------------------
-- Migrating the old NEWS_STATUS_NETWORK to STATUS_UPDATE_READERS and AGGREGATED_READERS
----------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
-- [DATA_MIGRATION] [START] data migration for NR_STATUS_UPDATE_READERS
----------------------------------------------------------------------------------
-- [DATA_MIGRATION] [START] data migration for NR_STATUS_UPDATE_READERS
----------------------------------------------------------------------------------
--1) Move the status network info to the NR_STATUS_UPDATE_READERS

BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.STATUS_UPDATE_READERS AS (
	SELECT 	NR_NEWS_STATUS_NETWORK.NEWS_STATUS_NETWORK_ID CATEGORY_READER_ID,
			NR_NEWS_STATUS_NETWORK.READER_ID,
			11 CATEGORY_TYPE,
			'profiles' SOURCE,
			NR_NEWS_STATUS_NETWORK.ACTOR_UUID CONTAINER_ID,
			NR_NEWS_STATUS_NETWORK.ITEM_ID,
			' ' ROLLUP_ENTRY_ID,
			10 RESOURCE_TYPE,
			NR_NEWS_STATUS_NETWORK.CREATION_DATE,
			NR_STORIES.STORY_ID,
			5 SOURCE_TYPE,
			0 USE_IN_ROLLUP,
			NR_NEWS_STATUS_NETWORK.IS_NETWORK_NEWS IS_NETWORK,
			NR_NEWS_STATUS_NETWORK.IS_FOLLOW_NEWS IS_FOLLOWER,
			NR_NEWS_STATUS_NETWORK.CREATION_DATE EVENT_TIME,
			0 IS_STORY_COMM,
			1 IS_BROADCAST,
			' ' ORGANIZATION_ID,
			NR_NEWS_STATUS_NETWORK.ACTOR_UUID,
			NR_STORIES.ACTOR_UUID ROLLUP_AUTHOR_ID,
			1 IS_VISIBLE					
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES,
			HOMEPAGE.NR_NEWS_STATUS_NETWORK NR_NEWS_STATUS_NETWORK
	WHERE 	NR_STORIES.ITEM_ID = NR_NEWS_STATUS_NETWORK.ITEM_ID
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.STATUS_UPDATE_READERS TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.STATUS_UPDATE_READERS' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_STATUS_UPDATE_READERS' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

DROP VIEW HOMEPAGE.STATUS_UPDATE_READERS;

COMMIT;

BEGIN TRANSACTION
GO

--------------------------------------------------------------------------------------------------------------------
-- In 4.0 we have that if the author is equal to the reader then the flag IS_NETWORK and IS_FOLLOW need to be 0
-- https://swgjazz.ibm.com:8001/jazz/resource/itemName/com.ibm.team.workitem.WorkItem/63104
--------------------------------------------------------------------------------------------------------------------


--  [start] NR_STATUS_UPDATE_READERS
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID < '0..f';
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '0..f' AND CATEGORY_READER_ID < '1..f' ;
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '1..f' AND CATEGORY_READER_ID < '2..f' ;
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '2..f' AND CATEGORY_READER_ID < '3..f';
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '3..f' AND CATEGORY_READER_ID < '4..f';
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '4..f' AND CATEGORY_READER_ID < '5..f';
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '5..f' AND CATEGORY_READER_ID < '6..f';
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '6..f' AND CATEGORY_READER_ID < '7..f';
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '7..f' AND CATEGORY_READER_ID < '8..f';
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '8..f' AND CATEGORY_READER_ID < '9..f';
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '9..f' AND CATEGORY_READER_ID < 'a..f';
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= 'a..f' AND CATEGORY_READER_ID < 'b..f';
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= 'b..f' AND CATEGORY_READER_ID < 'c..f';
GO

GO
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= 'c..f' AND CATEGORY_READER_ID < 'd..f';
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= 'd..f' AND CATEGORY_READER_ID < 'e..f';
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= 'e..f' AND CATEGORY_READER_ID < 'f..f';
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= 'f..f';
GO

--  [end] NR_STATUS_UPDATE_READERS

COMMIT;

BEGIN TRANSACTION
GO

--1) Move the NR_STATUS_UPDATE_READERS info to the AGGREGATED_READERS

CREATE VIEW HOMEPAGE.VIEW_AGGREGATED_READERS AS (
	SELECT 	CATEGORY_READER_ID,
			READER_ID,
			CONTAINER_ID,
			ITEM_ID,
			RESOURCE_TYPE,
			CATEGORY_TYPE,
			CREATION_DATE,
			SOURCE,
			STORY_ID,
			SOURCE_TYPE,
			ROLLUP_ENTRY_ID,
			USE_IN_ROLLUP,
			IS_NETWORK,
			IS_FOLLOWER,
			EVENT_TIME,
			IS_STORY_COMM,
			IS_BROADCAST,
			ORGANIZATION_ID,
			ACTOR_UUID,
			ROLLUP_AUTHOR_ID,		
			IS_VISIBLE
	FROM 	HOMEPAGE.NR_STATUS_UPDATE_READERS
	WHERE 	READER_ID = ACTOR_UUID
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.VIEW_AGGREGATED_READERS TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.VIEW_AGGREGATED_READERS' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_AGGREGATED_READERS' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

DROP VIEW HOMEPAGE.VIEW_AGGREGATED_READERS;
GO

COMMIT;

----------------------------------------------------------------------------------
-- [DATA_MIGRATION] [END] data migration for NR_STATUS_UPDATE_READERS
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------


---------------------------------
-- Move the data
---------------------------------

------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
--	[START] SAVED MIGRATION
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------

-- B) INSERT STORIES
-- C) INSERT READERS

----------------------------------------------------
----- STORIES
----------------------------------------------------

BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.STORIES AS (
		SELECT 	NEWS_STORY_ID		STORY_ID,
				EVENT_NAME			EVENT_NAME,
				SOURCE				SOURCE,
				CONTAINER_ID		CONTAINER_ID,
				CONTAINER_NAME		CONTAINER_NAME,
				CONTAINER_URL		CONTAINER_URL,
				''					CONTAINER_ATOM_URL,
				ENTRY_NAME			ITEM_NAME, --
				ENTRY_URL 			ITEM_URL, --
				ENTRY_ATOM_URL		ITEM_ATOM_URL,
				ITEM_ID				ITEM_ID, --
				ITEM_CORRELATION_ID	ITEM_CORRELATION_ID,
				CREATION_DATE		CREATION_DATE,
				BRIEF_DESC			BRIEF_DESC,
				ACTOR_UUID			ACTOR_UUID,
				EVENT_RECORD_UUID	EVENT_RECORD_UUID,
				TAGS				TAGS,
				META_TEMPLATE		META_TEMPLATE,
				TEXT_META_TEMPLATE	TEXT_META_TEMPLATE,
				' '					R_META_TEMPLATE,
				' '					R_TEXT_META_TEMPLATE,
				IS_COMMUNITY_STORY	IS_COMMUNITY_STORY,
				' ' 				ITEM_CORRELATION_NAME,
				0					HAS_ATTACHMENT,	
				SOURCE_TYPE			SOURCE_TYPE,						
				NULL				ENTRY_ID, -- I set this value to be NULL as ENTRIES will be migrated later with java migration
				CREATION_DATE		EVENT_TIME,
				' '					VERB,
				0					ITEM_SCOPE,
				CREATION_DATE		ITEM_UPDATE_DATE, --
				' '					FIRST_RECIPIENT_ID,
				0					NUM_RECIPIENTS,
				' '					PRIMARY_ACTION_URL,
				' '					SECONDARY_ACTION_URL,
				' '					ITEM_AUTHOR_UUID,
				' '					ITEM_CORRELATION_AUTHOR_UUID,
				' '					ITEM_CORRELATION_AUHTOR_NAME,
				0					EVENT_SCOPE,
				' '					RELATED_COMMUNITY_ID,
				0					ITEM_CORRELATION_SCOPE,
				NULL				ITEM_CORRELATION_UPDATE_DATE,
				' '					ITEM_CORRELATION_URL
		FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED,
			(
				SELECT NEWS_STORY_ID STORY_ID, MAX (NEWS_RECORDS_ID) MAX_NEWS_RECORDS_ID
				FROM HOMEPAGE.NR_NEWS_SAVED
				GROUP BY NEWS_STORY_ID
			) TEMP
		WHERE 	NR_NEWS_SAVED.NEWS_RECORDS_ID = TEMP.MAX_NEWS_RECORDS_ID AND TEMP.STORY_ID NOT IN (SELECT STORY_ID FROM HOMEPAGE.NR_STORIES)
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.STORIES TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.STORIES' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_STORIES' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

DROP VIEW HOMEPAGE.STORIES;

COMMIT;


--------------------------------------------------------------------------
-- READERS
--------------------------------------------------------------------------

BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.READERS AS (
	SELECT 	NR_NEWS_SAVED.NEWS_RECORDS_ID	CATEGORY_READER_ID,
			NR_NEWS_SAVED.READER_ID			READER_ID,
			13								CATEGORY_TYPE,
			NR_NEWS_SAVED.SOURCE			SOURCE,
			NR_NEWS_SAVED.CONTAINER_ID		CONTAINER_ID,
			NR_NEWS_SAVED.ITEM_ID			ITEM_ID,
			NULL							ROLLUP_ENTRY_ID,
			0								RESOURCE_TYPE,
			NR_NEWS_SAVED.CREATION_DATE		CREATION_DATE,
			NR_NEWS_SAVED.NEWS_STORY_ID		STORY_ID,
			NR_NEWS_SAVED.SOURCE_TYPE		SOURCE_TYPE,
			0								USE_IN_ROLLUP,				
			0								IS_NETWORK,
			0								IS_FOLLOWER,
			NR_NEWS_SAVED.CREATION_DATE		EVENT_TIME,
			NULL							NOTE_TEXT,
			NULL							NOTE_UPDATE_DATE,
			NR_NEWS_SAVED.IS_COMMUNITY_STORY IS_STORY_COMM,
			0								IS_BROADCAST,
			NULL							ORGANIZATION_ID,
			NULL							OPERATION_ID,
			NULL							ACTOR_UUID,
			NULL							ROLLUP_AUTHOR_ID,
			1								IS_VISIBLE
	FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.READERS TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.READERS' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_SAVED_READERS' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

DROP VIEW HOMEPAGE.READERS;

COMMIT


------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
--	[END] SAVED MIGRATION
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
--	[START] DISCOVERY MIGRATION
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------



-- 1 ACTIVITIES
BEGIN TRANSACTION
GO

CREATE VIEW HOMEPAGE.DISCOVERY AS (
	SELECT 	NR_NEWS_DISCOVERY.NEWS_RECORDS_ID 			CATEGORY_READER_ID,
		'00000000-0000-0000-0000-000000000001'			READER_ID,
		17												CATEGORY_TYPE,
		NR_NEWS_DISCOVERY.SOURCE						SOURCE,
		NR_NEWS_DISCOVERY.CONTAINER_ID					CONTAINER_ID,
		NR_NEWS_DISCOVERY.ITEM_ID						ITEM_ID,
		' '												ROLLUP_ENTRY_ID,
		0												RESOURCE_TYPE,
		NR_NEWS_DISCOVERY.CREATION_DATE					CREATION_DATE,
		NR_NEWS_DISCOVERY.NEWS_STORY_ID					STORY_ID,
		NR_NEWS_DISCOVERY.SOURCE_TYPE					SOURCE_TYPE,
		0												USE_IN_ROLLUP,
		0												IS_NETWORK,
		0												IS_FOLLOWER,
		NR_NEWS_DISCOVERY.CREATION_DATE					EVENT_TIME,
		NR_NEWS_DISCOVERY.IS_COMMUNITY_STORY			IS_STORY_COMM,
		0												IS_BROADCAST,
		' '												ORGANIZTION_ID,
		' '												ACTOR_UUID,
		' '												ROLLUP_AUTHOR_ID,
		1												IS_VISIBLE				
	FROM 	HOMEPAGE.NR_NEWS_DISCOVERY NR_NEWS_DISCOVERY,  
			HOMEPAGE.NR_STORIES NR_STORIES
	WHERE 	NR_NEWS_DISCOVERY.NEWS_STORY_ID =  NR_STORIES.STORY_ID
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.DISCOVERY TO HOMEPAGEUSER
GO

COMMIT;

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.DISCOVERY' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_DISCOVERY_VIEW' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command
-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command
--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

DROP VIEW HOMEPAGE.DISCOVERY;

COMMIT;


-------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------
-----------------------------------
-- Disable xp_cmdshell
----------------------------------

EXEC master.dbo.sp_configure 'show advanced options', 1
RECONFIGURE
EXEC master.dbo.sp_configure 'xp_cmdshell', 0
RECONFIGURE

----------------------------------
-----------------------------------


BEGIN TRANSACTION
GO


--
-- Insert special non-UI panels needed for gadgets
--

-- hidden pane for gadgets
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_noui.gadgetpanx11e1b0c40800200c9a66' , '%panel.gadgets' , 1 , 0, 1);

-- hidden pane for EE gadgets
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_noui.embeddedxx11e1b0c40800200c9a66' , '%panel.embedxp' , 1 , 0, 1);

-- hidden pane for sharebox gadgets
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_noui.share_boxx11e1b0c40800200c9a66' , '%panel.sharebox' , 1 , 0, 1);

GO


--------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------


------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 100
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 101 , RELEASEVER = '4.0.0.0'
WHERE   DBSCHEMAVER = 59;

GO

--------------------------------------
--------------------------------------

COMMIT

