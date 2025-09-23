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
-- #65088

DELETE FROM HOMEPAGE.NR_COMM_STORIES S WHERE NOT EXISTS ( SELECT S.STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = S.STORY_ID );
COMMIT;

DELETE FROM HOMEPAGE.NR_COMM_PERSON_STORIES S WHERE NOT EXISTS ( SELECT S.STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = S.STORY_ID );
COMMIT;

DELETE FROM HOMEPAGE.NR_RESPONSES_STORIES S WHERE NOT EXISTS ( SELECT S.STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = S.STORY_ID );
COMMIT;

DELETE FROM HOMEPAGE.NR_PROFILES_STORIES S WHERE NOT EXISTS ( SELECT S.STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = S.STORY_ID );
COMMIT;

DELETE FROM HOMEPAGE.NR_COMMUNITIES_STORIES S WHERE NOT EXISTS ( SELECT S.STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = S.STORY_ID );
COMMIT;

DELETE FROM HOMEPAGE.NR_ACTIVITIES_STORIES S WHERE NOT EXISTS ( SELECT S.STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = S.STORY_ID );
COMMIT;

DELETE FROM HOMEPAGE.NR_BLOGS_STORIES S WHERE NOT EXISTS ( SELECT S.STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = S.STORY_ID );
COMMIT;

DELETE FROM HOMEPAGE.NR_BOOKMARKS_STORIES S WHERE NOT EXISTS ( SELECT S.STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = S.STORY_ID );
COMMIT;

DELETE FROM HOMEPAGE.NR_FILES_STORIES S WHERE NOT EXISTS ( SELECT S.STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = S.STORY_ID );
COMMIT;

DELETE FROM HOMEPAGE.NR_FORUMS_STORIES S WHERE NOT EXISTS ( SELECT S.STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = S.STORY_ID );
COMMIT;

DELETE FROM HOMEPAGE.NR_WIKIS_STORIES S WHERE NOT EXISTS ( SELECT S.STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = S.STORY_ID );
COMMIT;

DELETE FROM HOMEPAGE.NR_TAGS_STORIES S WHERE NOT EXISTS ( SELECT S.STORY_ID FROM HOMEPAGE.NR_STORIES NS WHERE NS.STORY_ID = S.STORY_ID );
COMMIT;

-------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------

-- Moving on the migration TOP the drop of some indexes.
-- This should speed up the updates
-- 71040: Homepage and News migration is too slow while migrating very large dataset, oracle.

DROP INDEX HOMEPAGE.NR_COM_PER_STORIES_READER;
COMMIT;

DROP INDEX HOMEPAGE.NR_COM_PER_STORIES_STORY_ID;
COMMIT;

DROP INDEX HOMEPAGE.COMM_PERSON_STORIES_ITEM_ID;
COMMIT;

DROP INDEX HOMEPAGE.NR_COMM_PERSON_STORIES_DATE;
COMMIT;

----

DROP INDEX HOMEPAGE.RESPONSES_STORIES_IDX;
COMMIT;

DROP INDEX HOMEPAGE.RESPONSES_STORIES_CIDX;
COMMIT;

DROP INDEX HOMEPAGE.RESPONSES_STORIES_SIDX;
COMMIT;

DROP INDEX HOMEPAGE.RESPONSES_STORIES_ITEM_ID;
COMMIT;

DROP INDEX HOMEPAGE.NR_RESPONSES_STORIES_DATE;
COMMIT;

----

DROP INDEX HOMEPAGE.PROFILES_STORIES_IDX;
COMMIT;

DROP INDEX HOMEPAGE.PROFILES_STORIES_CIDX;
COMMIT;

DROP INDEX HOMEPAGE.PROFILES_STORIES_SIDX;
COMMIT;

DROP INDEX HOMEPAGE.PROFILES_STORIES_ITEM_ID;
COMMIT;

DROP INDEX HOMEPAGE.NR_NR_PROFILES_STORIES_DATE;
COMMIT; 

----

DROP INDEX HOMEPAGE.ACTIVITIES_STORIES_IDX; 
COMMIT;

DROP INDEX HOMEPAGE.ACTIVITIES_STORIES_CIDX;
COMMIT;

DROP INDEX HOMEPAGE.ACTIVITIES_STORIES_SIDX;
COMMIT;

DROP INDEX HOMEPAGE.ACTIVITIES_STORIES_ITEM_ID;
COMMIT;

DROP INDEX HOMEPAGE.NR_ACTIVITIES_STORIES_DATE;
COMMIT;

----

DROP INDEX HOMEPAGE.BLOGS_STORIES_IDX;
COMMIT;

DROP INDEX HOMEPAGE.BLOGS_STORIES_CIDX;
COMMIT;

DROP INDEX HOMEPAGE.BLOGS_STORIES_SIDX;
COMMIT;

DROP INDEX HOMEPAGE.BLOGS_STORIES_ITEM_ID;
COMMIT;

DROP INDEX HOMEPAGE.NR_BLOGS_STORIES_DATE;
COMMIT;

---

DROP INDEX HOMEPAGE.FILES_STORIES_IDX;
COMMIT;

DROP INDEX HOMEPAGE.FILES_STORIES_CIDX;
COMMIT;

DROP INDEX HOMEPAGE.FILES_STORIES_SIDX;
COMMIT;

DROP INDEX HOMEPAGE.FILES_STORIES_ITEM_ID;
COMMIT;

DROP INDEX HOMEPAGE.NR_FILES_STORIES_DATE;
COMMIT;

---

DROP INDEX HOMEPAGE.FORUMS_STORIES_IDX;
COMMIT;

DROP INDEX HOMEPAGE.FORUMS_STORIES_CIDX;
COMMIT;

DROP INDEX HOMEPAGE.FORUMS_STORIES_SIDX;
COMMIT;

DROP INDEX HOMEPAGE.FORUMS_STORIES_ITEM_ID;
COMMIT;

DROP INDEX HOMEPAGE.NR_FORUMS_STORIES_DATE;
COMMIT;

---

DROP INDEX HOMEPAGE.WIKIS_STORIES_IDX;
COMMIT;

DROP INDEX HOMEPAGE.WIKIS_STORIES_CIDX;
COMMIT;

DROP INDEX HOMEPAGE.WIKIS_STORIES_SIDX;
COMMIT;

DROP INDEX HOMEPAGE.WIKIS_STORIES_ITEM_ID;
COMMIT;

DROP INDEX HOMEPAGE.NR_WIKIS_STORIES_DATE;
COMMIT;

---

DROP INDEX HOMEPAGE.TAGS_STORIES_IDX;
COMMIT;

DROP INDEX HOMEPAGE.TAGS_STORIES_CIDX;
COMMIT;

DROP INDEX HOMEPAGE.TAGS_STORIES_SIDX;
COMMIT;

DROP INDEX HOMEPAGE.TAGS_STORIES_ITEM_ID;
COMMIT;

DROP INDEX HOMEPAGE.NR_TAGS_STORIES_DATE;
COMMIT;


---------------------------------------------------------------	
-- All tose tables are going to be stored into a dedicated table space
---------------------------------------------------------------
-- HOMEPAGE BOARD TABLETABLESPACE
CREATE TEMPORARY TABLESPACE "BOARDTMPTABSPACE"
	TEMPFILE 'BOARDTMPTABSPACE'
	SIZE 200M REUSE AUTOEXTEND ON;
	
CREATE SMALLFILE TABLESPACE "BOARDREGTABSPACE"
	DATAFILE 'BOARDREGTABSPACE'
	SIZE 200M REUSE AUTOEXTEND ON 
	NEXT 40M MAXSIZE UNLIMITED LOGGING 
	EXTENT MANAGEMENT LOCAL 
	SEGMENT SPACE MANAGEMENT AUTO;
	
CREATE SMALLFILE TABLESPACE "BOARDINDEXTABSPACE"
	DATAFILE 'BOARDINDEXTABSPACE'
	SIZE 200M REUSE AUTOEXTEND ON 
	NEXT 40M MAXSIZE UNLIMITED LOGGING 
	EXTENT MANAGEMENT LOCAL 
	SEGMENT SPACE MANAGEMENT AUTO;

CREATE 	TABLESPACE "BOARDLOBTABSPACE"
    DATAFILE 'BOARDLOBTABSPACE'
    SIZE 400M REUSE AUTOEXTEND ON
    NEXT 40M MAXSIZE UNLIMITED;
    
--------------------------------------------------------    
    
DROP TABLE HOMEPAGE.NR_ORGPERSON_FOLLOW;

COMMIT;    
        
-----------------------------------------------------------
--  HOMEPAGE.MTCONFIG
-----------------------------------------------------------
CREATE TABLE HOMEPAGE.MTCONFIG (
	UUID			VARCHAR2(36) NOT NULL,
	SCOPE 			VARCHAR2(36) NOT NULL,
	ID 				VARCHAR2(128) NOT NULL,
	CONFIG_VALUE 	VARCHAR2(1024),
	SERVICE 		VARCHAR2(256),
	NAME	 		VARCHAR2(64),
	DESCRIPTION		VARCHAR2(64),
	OVERRIDABLE 	NUMBER(5,0) DEFAULT 1 NOT NULL,
	ISPOLICY		NUMBER(5,0) DEFAULT 0 NOT NULL,
	ADMIN_VIS		NUMBER(5,0) DEFAULT 1 NOT NULL
) TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.MTCONFIG
	 ADD (CONSTRAINT "PK_ID" PRIMARY KEY ("UUID") USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");
	
CREATE UNIQUE INDEX HOMEPAGE.SCOPE_AND_ID
	ON HOMEPAGE.MTCONFIG (SCOPE, ID) TABLESPACE "HOMEPAGEINDEXTABSPACE";

CREATE INDEX HOMEPAGE.SETTINGS_BY_ID
    ON HOMEPAGE.MTCONFIG (ID) TABLESPACE "HOMEPAGEINDEXTABSPACE";
   
COMMIT;

ALTER TABLE HOMEPAGE.MTCONFIG ENABLE ROW MOVEMENT;

------------------------------------------------
-- NR_SOURCE_TYPE
------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SOURCE_TYPE (
	SOURCE_TYPE_ID VARCHAR2(36) NOT NULL,
	SOURCE_TYPE NUMBER(5 ,0) NOT NULL, -- numeric that is 1,2,3 etc.. 100, 101..
	SOURCE VARCHAR2(36) NOT NULL,
	DISPLAY_NAME VARCHAR2(4000),
	IMAGE_URL VARCHAR2(2048),
	PUBLISHED TIMESTAMP,
	UPDATED TIMESTAMP,	
	IS_ENABLED NUMBER(5 ,0),
	SUMMARY VARCHAR2 (4000),
	ORGANIZATION_ID VARCHAR2(36),
	URL VARCHAR2(2048),
	URL_SSL VARCHAR2(2048),
	IMAGE_URL_SSL VARCHAR2(2048)	
)
TABLESPACE "NEWSREGTABSPACE"; 

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
  	ADD (CONSTRAINT PK_SRC_TYPE_ID PRIMARY KEY(SOURCE_TYPE_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
	ADD CONSTRAINT SRC_TYPE_UNQ UNIQUE(SOURCE_TYPE);

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
	ADD CONSTRAINT SRC_UNQ UNIQUE(SOURCE);
	
ALTER TABLE "HOMEPAGE"."NR_SOURCE_TYPE" ENABLE ROW MOVEMENT;

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

COMMIT;

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

COMMIT;

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

COMMIT;

CREATE INDEX HOMEPAGE.HP_UI 
	ON HOMEPAGE.HP_UI (LAST_VISIT ASC) TABLESPACE "HPNTINDEXTABSPACE";

COMMIT;	



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
commit;

UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
commit;

UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
commit;

UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
commit;

UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
commit;

UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
commit;

UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
commit;

UPDATE HOMEPAGE.NR_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
commit;

--------------------------------

-- HOMEPAGE.NR_RESPONSES_STORIES
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
commit;

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
commit;

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
commit;

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
commit;

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
commit;

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
commit;

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
commit;

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
commit;


-- HOMEPAGE.NR_PROFILES_STORIES
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
commit;

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
commit;

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
commit;

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
commit;

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
commit;

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
commit;

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
commit;

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
commit;

-- HOMEPAGE.NR_ACTIVITIES_STORIES
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
commit;

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
commit;

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
commit;

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
commit;

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
commit;

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
commit;

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
commit;

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
commit;

-- HOMEPAGE.NR_BLOGS_STORIES
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
commit;

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
commit;

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
commit;

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
commit;

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
commit;

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
commit;

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
commit;

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
commit;

-- HOMEPAGE.NR_BOOKMARKS_STORIES
UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
commit;

UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
commit;

UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
commit;

UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
commit;

UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
commit;

UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
commit;

UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
commit;

UPDATE HOMEPAGE.NR_BOOKMARKS_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
commit;

-- HOMEPAGE.NR_FILES_STORIES
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
commit;

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
commit;

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
commit;

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
commit;

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
commit;

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
commit;

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
commit;

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
commit;

-- HOMEPAGE.NR_FORUMS_STORIES
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
commit;

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
commit;

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
commit;

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
commit;

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
commit;

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
commit;

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
commit;

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
commit;

-- HOMEPAGE.NR_WIKIS_STORIES
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
commit;

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
commit;

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
commit;

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
commit;

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
commit;

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
commit;

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
commit;

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
commit;

-- HOMEPAGE.NR_TAGS_STORIES
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
commit;

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
commit;

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
commit;

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
commit;

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
commit;

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
commit;

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
commit;

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
commit;

-----------------------------------

-- HOMEPAGE.NR_COMM_PERSON_STORIES
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
commit;

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
commit;

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
commit;

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
commit;

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
commit;

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
commit;

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
commit;

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
commit;

--HOMEPAGE.NR_NEWS_DISCOVERY
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
commit;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
commit;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
commit;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
commit;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
commit;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
commit;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
commit;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
commit;

--HOMEPAGE.NR_NEWS_SAVED
UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
commit;

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
commit;

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
commit;

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
commit;

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
commit;

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
commit;

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
commit;

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
commit;

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
COMMIT;

DELETE FROM HOMEPAGE.NR_FOLLOWS FOLLOWS WHERE EXISTS 
	(SELECT 1 FROM HOMEPAGE.TMP_FOLLOWS TMP_FOLLOWS WHERE FOLLOWS.PERSON_ID = TMP_FOLLOWS.PERSON_ID AND FOLLOWS.RESOURCE_ID = TMP_FOLLOWS.RESOURCE_ID );
COMMIT;

DROP VIEW HOMEPAGE.TMP_FOLLOWS;
COMMIT;
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-- [END] PMR 48211,L6Q,000: Fixing Entries in following reletionship after migration
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------

-------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------
-- PRE sanitize sql as we start from a 3.0.1 and we need to migrate it to 4.0

DROP INDEX HOMEPAGE.NR_ATT_STORY_ID;
DROP INDEX HOMEPAGE.NR_REC_STORY_ID;
DROP INDEX HOMEPAGE.NR_RECOMMENDER_STORY_ID;

ALTER TABLE HOMEPAGE.NR_ATTACHMENT DROP CONSTRAINT PK_ATTACHMENT;
ALTER TABLE HOMEPAGE.NR_RECOMMENDATION DROP CONSTRAINT PK_RECOMMENDATION;

ALTER TABLE HOMEPAGE.NR_ATTACHMENT 			RENAME TO NR_ATTACHMENT_301;
ALTER TABLE HOMEPAGE.NR_RECOMMENDATION 		RENAME TO NR_RECOMMENDATION_301;


commit;

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

COMMIT;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE = 'forums' WHERE EVENT_NAME LIKE '%forum%';

COMMIT;

UPDATE HOMEPAGE.NR_NEWS_SAVED SET SOURCE = 'forums' WHERE EVENT_NAME LIKE '%forum%';

COMMIT;

-- #5 - Icon for Following a person is the Homepage icon instead of Profiles icon
-- In LC 2.5.0.3, you will see a story for adding a user to your Homepage Watchlist. This story has a Homepage icon when migrated to LC 3.0.1
-- Ideally this story should have a "Profiles" icon in LC 3.0.1, as this is now a profiles story - profiles.person.followed
UPDATE HOMEPAGE.NR_STORIES  SET SOURCE = 'profiles' WHERE EVENT_NAME = 'profiles.person.followed';

COMMIT;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY  SET SOURCE = 'profiles' WHERE EVENT_NAME = 'profiles.person.followed';

COMMIT;


UPDATE HOMEPAGE.NR_NEWS_SAVED  SET SOURCE = 'profiles' WHERE EVENT_NAME = 'profiles.person.followed';

COMMIT;


--------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------
-- HOMEPAGE.PERSON
----------------------------------------------------------------------
-- add columns:
-- "PROF_TYPE" VARCHAR2(128)
-- "CREATION_DATE" TIMESTAMP
-- "THEME_ID" VARCHAR2(36)
-- "COMM_VISIBILITY" NUMBER(5,0)
-- "MEMBER_TYPE" NUMBER(5,0) NOT NULL WITH DEFAULT 0
-- "ORGANIZATION_ID" VARCHAR2(36)
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
ALTER TABLE HOMEPAGE.PERSON ADD (
	PROF_TYPE VARCHAR2(128),
	CREATION_DATE TIMESTAMP,
	THEME_ID VARCHAR2(36),
	COMM_VISIBILITY NUMBER(5 ,0),
	MEMBER_TYPE NUMBER(5 ,0) DEFAULT 0 NOT NULL,
	ORGANIZATION_ID VARCHAR2(36),
	COMMUNITY_INTERNAL_ONLY NUMBER(5 ,0)
);

COMMIT;

CREATE INDEX HOMEPAGE.DISPLAYNAME_LOWER_MEM
	ON HOMEPAGE.PERSON (DISPLAYNAME_LOWER, MEMBER_TYPE)  TABLESPACE "HOMEPAGEINDEXTABSPACE";
COMMIT;

-- Oracle doesn't support this INCLUDE
--CREATE UNIQUE INDEX HOMEPAGE.PERSON_IDX   
--	ON HOMEPAGE.PERSON (PERSON_ID) INCLUDE (USER_MAIL, DISPLAYNAME);

--COMMIT;

INSERT INTO HOMEPAGE.PERSON
		(PERSON_ID,DISPLAYNAME,EXID,STATE,MEMBER_TYPE)
VALUES  ('00000000-0000-0000-0000-000000000001','%anyone','00000000-0000-0000-0000-000000000001',0,2);

COMMIT;

-- --reorg table homepage.person;

COMMIT;

UPDATE 	HOMEPAGE.PERSON SET MEMBER_TYPE = 0, CREATION_DATE = LAST_UPDATE;
COMMIT;

UPDATE HOMEPAGE.PERSON SET MEMBER_TYPE = 2 WHERE PERSON_ID = '00000000-0000-0000-0000-000000000000';
COMMIT;


----------------------------------------------------------------------
-- HOMEPAGE.HP_UI
----------------------------------------------------------------------
-- add columns:
-- "LAST_ACTIONABLE_VISIT" TIMESTAMP
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
ALTER TABLE HOMEPAGE.HP_UI ADD (
	LAST_ACTIONABLE_VISIT TIMESTAMP,
	WELCOME_NOTE NUMBER(5, 0) DEFAULT 1
);
COMMIT;

DROP INDEX HOMEPAGE.HP_UI_PERSON_ID_INDEX;
COMMIT;

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

COMMIT;

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

COMMIT;

DELETE FROM HOMEPAGE.HP_UI WHERE UI_ID IN ( 
	SELECT MAX (UI_ID) MAX_UI_ID 
	FROM HOMEPAGE.HP_UI 
	GROUP BY PERSON_ID HAVING COUNT(*) > 1 
);

COMMIT;

CREATE UNIQUE INDEX HOMEPAGE.HP_UI_PERSONID
	ON HOMEPAGE.HP_UI (PERSON_ID) TABLESPACE "HPNTINDEXTABSPACE";
COMMIT;	

-- --reorg table homepage.hp_ui;
COMMIT;

UPDATE HOMEPAGE.HP_UI SET LAST_ACTIONABLE_VISIT = CURRENT_TIMESTAMP;
COMMIT;



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
CREATE INDEX HOMEPAGE.TAB_INST_TAB_ID 
	ON HOMEPAGE.HP_TAB_INST (TAB_ID) TABLESPACE "HPNTINDEXTABSPACE";
COMMIT;

----------------------------------------------------------------------
-- HOMEPAGE.WIDGET
----------------------------------------------------------------------
-- add columns:
--  "IS_GADGET" NUMBER(5,0) WITH DEFAULT 0
--
-- remove columns:
--
-- add indexes:
-- 
--
-- remove indexes:
--
----------------------------------------------------------------------
ALTER TABLE HOMEPAGE.WIDGET ADD (
	IS_GADGET NUMBER(5, 0) DEFAULT 0,
	WIDGET_POLICY_FLAGS NUMBER(5, 0) DEFAULT 1 NOT NULL,
	WIDGET_EXT_PROPERTIES VARCHAR2(2048)
);
COMMIT;

-- --reorg table HOMEPAGE.WIDGET;

UPDATE HOMEPAGE.WIDGET SET IS_GADGET = 0;

COMMIT;

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

commit;

UPDATE HOMEPAGE.WIDGET SET WIDGET_SYSTEM = 1 WHERE WIDGET_TITLE = '%widget.sand.recommend.name';
COMMIT;


----------------------------------------------------------------------
-- HOMEPAGE.NT_NOTIFICATION
----------------------------------------------------------------------
-- add columns:
--  "NOTIFICATION_SOURCE_TYPE" NUMBER(5,0)
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
	ADD NOTIFICATION_SOURCE_TYPE NUMBER(5,0);

COMMIT;

-- --reorg table homepage.nt_notification;

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 1 WHERE NOTIFICATION_SOURCE = 'activities';
COMMIT;

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 2 WHERE NOTIFICATION_SOURCE = 'blogs';
COMMIT;

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 3 WHERE NOTIFICATION_SOURCE = 'communities';
COMMIT;

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 4 WHERE NOTIFICATION_SOURCE = 'wikis';
COMMIT;

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 5 WHERE NOTIFICATION_SOURCE = 'profiles';
COMMIT;

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 6 WHERE NOTIFICATION_SOURCE = 'homepage';
COMMIT;

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 7 WHERE NOTIFICATION_SOURCE = 'dogear';
COMMIT;

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 7 WHERE NOTIFICATION_SOURCE = 'bookmarks';
COMMIT;

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 8 WHERE NOTIFICATION_SOURCE = 'files';
COMMIT;

UPDATE HOMEPAGE.NT_NOTIFICATION
	SET NOTIFICATION_SOURCE_TYPE = 9 WHERE NOTIFICATION_SOURCE = 'forums';
COMMIT;	

----------------------------------------------------------------------
-- HOMEPAGE.NR_SOURCE
----------------------------------------------------------------------
-- add columns:
--  "SOURCE_TYPE" NUMBER(5,0)
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
	ADD SOURCE_TYPE NUMBER(5,0);

COMMIT;

-- --reorg table HOMEPAGE.NR_SOURCE;

COMMIT;

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities';
COMMIT;

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs';
COMMIT;

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities';
COMMIT;

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis';
COMMIT;

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles';
COMMIT;

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage';
COMMIT;

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear';
COMMIT;

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 8 WHERE SOURCE = 'files';
COMMIT;

UPDATE HOMEPAGE.NR_SOURCE
	SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums';
COMMIT;	



----------------------------------------------------------------------
-- HOMEPAGE.NR_NEWS_SAVED
----------------------------------------------------------------------
-- add columns:
--  "SOURCE_TYPE" NUMBER(5,0)
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
	ADD SOURCE_TYPE NUMBER(5,0);

COMMIT;

-- --reorg table homepage.nr_news_saved; 

COMMIT;

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities';
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs';
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities';
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis';
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles';
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage';
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear';
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 8 WHERE SOURCE = 'files';
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_SAVED
	SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums';
COMMIT;	

----------------------------------------------------------------------
-- HOMEPAGE.NR_NEWS_DISCOVERY
----------------------------------------------------------------------
-- add columns:
--  "SOURCE_TYPE" NUMBER(5,0) 
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
	ADD SOURCE_TYPE NUMBER(5,0);

COMMIT;

-- --reorg table  HOMEPAGE.NR_NEWS_DISCOVERY;

COMMIT;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND NEWS_RECORDS_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND NEWS_RECORDS_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND NEWS_RECORDS_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND NEWS_RECORDS_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND NEWS_RECORDS_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND NEWS_RECORDS_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND NEWS_RECORDS_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND NEWS_RECORDS_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '0..f' AND NEWS_RECORDS_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '1..f' AND NEWS_RECORDS_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '2..f' AND NEWS_RECORDS_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '3..f' AND NEWS_RECORDS_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '4..f' AND NEWS_RECORDS_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '5..f' AND NEWS_RECORDS_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '6..f' AND NEWS_RECORDS_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '7..f' AND NEWS_RECORDS_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '8..f' AND NEWS_RECORDS_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= '9..f' AND NEWS_RECORDS_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= 'a..f' AND NEWS_RECORDS_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= 'b..f' AND NEWS_RECORDS_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= 'c..f' AND NEWS_RECORDS_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= 'd..f' AND NEWS_RECORDS_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= 'e..f' AND NEWS_RECORDS_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_NEWS_DISCOVERY SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND NEWS_RECORDS_ID >= 'f..f' ;
COMMIT;


--------------------------------------------
-- ADDING SOURCE_TYPE - NR_RESOURCE
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_RESOURCE
	ADD SOURCE_TYPE NUMBER(5,0);
COMMIT;	

-- --reorg table HOMEPAGE.NR_RESOURCE;
COMMIT;

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities';
COMMIT;

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs';
COMMIT;

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities';
COMMIT;

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis';
COMMIT;

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles';
COMMIT;

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage';
COMMIT;

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear';
COMMIT;

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 8 WHERE SOURCE = 'files';
COMMIT;

UPDATE HOMEPAGE.NR_RESOURCE
	SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums';
COMMIT;	
	
--------------------------------------------
-- ADDING SOURCE_TYPE - NR_COMM_STORIES
--------------------------------------------
ALTER TABLE HOMEPAGE.NR_COMM_STORIES
	ADD SOURCE_TYPE NUMBER(5,0);
COMMIT;	

UPDATE HOMEPAGE.NR_COMM_STORIES
	SET SOURCE_TYPE = 3;
COMMIT;

--------------------------------------------
-- ADDING SOURCE_TYPE - NR_COMM_PERSON_STORIES
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_COMM_PERSON_STORIES
	ADD SOURCE_TYPE NUMBER(5,0);
COMMIT;	

-- partioned update



UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND COMM_PER_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND COMM_PER_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND COMM_PER_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND COMM_PER_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND COMM_PER_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND COMM_PER_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND COMM_PER_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND COMM_PER_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '0..f' AND COMM_PER_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '1..f' AND COMM_PER_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '2..f' AND COMM_PER_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '3..f' AND COMM_PER_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '4..f' AND COMM_PER_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '5..f' AND COMM_PER_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '6..f' AND COMM_PER_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '7..f' AND COMM_PER_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '8..f' AND COMM_PER_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= '9..f' AND COMM_PER_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= 'a..f' AND COMM_PER_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= 'b..f' AND COMM_PER_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= 'c..f' AND COMM_PER_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= 'd..f' AND COMM_PER_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= 'e..f' AND COMM_PER_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_COMM_PERSON_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND COMM_PER_STORY_ID >= 'f..f' ;
COMMIT;




-- categories tables
--------------------------------------------
-- ADDING SOURCE_TYPE - NR_RESPONSES_STORIES
--------------------------------------------
-- partitioned update

ALTER TABLE HOMEPAGE.NR_RESPONSES_STORIES
	ADD SOURCE_TYPE NUMBER(5,0);
COMMIT;	



UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_RESPONSES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;



	
--------------------------------------------
-- ADDING SOURCE_TYPE - NR_PROFILES_STORIES
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_PROFILES_STORIES
	ADD SOURCE_TYPE NUMBER(5,0);
COMMIT;




UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_PROFILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;



	
--------------------------------------------
-- ADDING SOURCE_TYPE - NR_ACTIVITIES_STORIES
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_ACTIVITIES_STORIES
	ADD SOURCE_TYPE NUMBER(5,0);
COMMIT;




UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_ACTIVITIES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;



--------------------------------------------
-- ADDING SOURCE_TYPE - NR_BLOGS_STORIES
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_BLOGS_STORIES
	ADD SOURCE_TYPE NUMBER(5,0);
COMMIT;




UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_BLOGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;



	
--------------------------------------------
-- ADDING SOURCE_TYPE - NR_BOOKMARKS_STORIES (never used)
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_BOOKMARKS_STORIES
	ADD SOURCE_TYPE NUMBER(5,0);
COMMIT;	
	
--------------------------------------------
-- ADDING SOURCE_TYPE - NR_FILES_STORIES
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_FILES_STORIES
	ADD SOURCE_TYPE NUMBER(5,0);
COMMIT;	




UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FILES_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;




--------------------------------------------
-- ADDING SOURCE_TYPE - NR_FORUMS_STORIES
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_FORUMS_STORIES
	ADD SOURCE_TYPE NUMBER(5,0);
COMMIT;




UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_FORUMS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;




--------------------------------------------
-- ADDING SOURCE_TYPE - NR_WIKIS_STORIES
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_WIKIS_STORIES
	ADD SOURCE_TYPE NUMBER(5,0);
COMMIT;	




UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_WIKIS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;




--------------------------------------------
-- ADDING SOURCE_TYPE - NR_TAGS_STORIES
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_TAGS_STORIES
	ADD SOURCE_TYPE NUMBER(5,0);
COMMIT;




UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '0..f' AND FOLLOWED_STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '1..f' AND FOLLOWED_STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '2..f' AND FOLLOWED_STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '3..f' AND FOLLOWED_STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '4..f' AND FOLLOWED_STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '5..f' AND FOLLOWED_STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '6..f' AND FOLLOWED_STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '7..f' AND FOLLOWED_STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '8..f' AND FOLLOWED_STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= '9..f' AND FOLLOWED_STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'a..f' AND FOLLOWED_STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'b..f' AND FOLLOWED_STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'c..f' AND FOLLOWED_STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'd..f' AND FOLLOWED_STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'e..f' AND FOLLOWED_STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_TAGS_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND FOLLOWED_STORY_ID >= 'f..f' ;
COMMIT;

----------------------------------------------------------------------
-- HOMEPAGE.NR_NEWS_STATUS_NETWORK
----------------------------------------------------------------------
-- add columns:
--  "SOURCE_TYPE" NUMBER(5,0) 
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

COMMIT;

CREATE UNIQUE INDEX HOMEPAGE.NR_SN_ITEM_READ_UNQ
	ON HOMEPAGE.NR_NEWS_STATUS_NETWORK (ITEM_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
	
COMMIT;	

----------------------------------------------------------------------
-- HOMEPAGE.NR_COMM_STORIES
----------------------------------------------------------------------
-- add columns:
--  "SOURCE_TYPE" NUMBER(5,0) 
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
CREATE INDEX HOMEPAGE.NR_COMM_STORIES_CDATE
    ON HOMEPAGE.NR_COMM_STORIES (COMMUNITY_ID, CREATION_DATE ASC) TABLESPACE NEWSINDEXTABSPACE; 

COMMIT;

CREATE INDEX HOMEPAGE.NR_COM_STORY_ID			
	ON	HOMEPAGE.NR_COMM_STORIES (COMMUNITY_ID, STORY_ID) TABLESPACE NEWSINDEXTABSPACE;

COMMIT;

-- TODO: verify why db2 doesn't need this drop. Anyway this is just a dbComparator issue because NR_COMM_STORIES table won't be used in 4.0
DROP INDEX HOMEPAGE.NR_COMM_STORIES_COM_ID;
COMMIT;

----------------------------------------------------------------------
-- HOMEPAGE.NR_STORIES_CONTENT
----------------------------------------------------------------------
-- add columns:
--  "SOURCE_TYPE" NUMBER(5,0)
--  "ACTIVITY_META_DATA" CLOB
--  "ITEM_CONTENT" CLOB
--  "ITEM_CORRELATION_CONTENT"
--  "ITEM_CONTENT_FORMAT" NUMBER(5,0)  
--  "ITEM_CORRELATION_FORMAT" NUMBER(5,0)
--  "CONTENT_FORMAT" NUMBER(5,0)
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
	ADD SOURCE_TYPE NUMBER(5,0);
COMMIT;

-- --reorg table homepage.nr_stories_content;

COMMIT;

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
COMMIT;

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 2 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'blogs'
);
COMMIT;

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 3 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'communities'
);
COMMIT;

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 4 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'wikis'
);
COMMIT;

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 5 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'profiles'
);
COMMIT;

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 6 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'homepage'
);
COMMIT;

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 7 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'dogear'
);
COMMIT;

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 8 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'files'
);
COMMIT;

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET SOURCE_TYPE = 9 WHERE STORY_ID IN (
	SELECT 	NR_STORIES.STORY_ID
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES, HOMEPAGE.NR_STORIES_CONTENT NR_STORIES_CONTENT
	WHERE 	NR_STORIES.STORY_ID = NR_STORIES_CONTENT.STORY_ID AND SOURCE = 'forums'
);
COMMIT;

------------------------------------------------
-- NR_STORIES_CONTENT
------------------------------------------------
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT  
	ADD ACTIVITY_META_DATA 			CLOB 	LOB (ACTIVITY_META_DATA) STORE AS (TABLESPACE NEWSLOBTABSPACE STORAGE (INITIAL 1M) CHUNK 4000 NOCACHE NOLOGGING)
	ADD ITEM_CONTENT 				CLOB 	LOB (ITEM_CONTENT) STORE AS (TABLESPACE NEWSLOBTABSPACE STORAGE (INITIAL 1K) CHUNK 100 NOCACHE NOLOGGING)	
	ADD ITEM_CORRELATION_CONTENT 	CLOB 	LOB (ITEM_CORRELATION_CONTENT) STORE AS (TABLESPACE NEWSLOBTABSPACE STORAGE (INITIAL 1K) CHUNK 100 NOCACHE NOLOGGING)
	ADD ITEM_CONTENT_FORMAT  		NUMBER(5 ,0)
	ADD ITEM_CORRELATION_FORMAT  	NUMBER(5 ,0)
	ADD CONTENT_FORMAT 				NUMBER(5 ,0)
	ADD ACTIVITY_META_DATA_1 		RAW(2000)
	ADD ACTIVITY_META_DATA_2 		RAW(2000)
	ADD IS_META_DATA_TRUNCATED 		NUMBER(5,0)
	ADD ITEM_BRIEF_DESC 			VARCHAR2(4000)
	ADD ITEM_CORRELATION_BRIEF_DESC VARCHAR2(4000)
	ADD ITEM_TAGS 					VARCHAR2(1024)
	ADD ITEM_CORRELATION_TAGS 		VARCHAR2(1024);

COMMIT;

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
DELETE FROM HOMEPAGE.NR_NETWORK WHERE NETWORK_ID IN
(
	SELECT NETWORK_ID 
	FROM HOMEPAGE.NR_NETWORK NR_NETWORK
	INNER JOIN (
			SELECT PERSON_ID, COLLEAGUE_ID, MAX(NETWORK_ID) AS MAX_ID 	
			FROM HOMEPAGE.NR_NETWORK
			GROUP BY PERSON_ID , COLLEAGUE_ID 
			HAVING COUNT(*) > 1) T ON NR_NETWORK.PERSON_ID = T.PERSON_ID AND NR_NETWORK.COLLEAGUE_ID =  T.COLLEAGUE_ID
	WHERE NR_NETWORK.NETWORK_ID < T.MAX_ID	
);

CREATE INDEX HOMEPAGE.COLL_PERSON_IDX 
	ON HOMEPAGE.NR_NETWORK (COLLEAGUE_ID, PERSON_ID) TABLESPACE "BOARDINDEXTABSPACE";

COMMIT;

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

COMMIT;

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

COMMIT;

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

COMMIT;

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

COMMIT;

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

COMMIT;

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

COMMIT;

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

COMMIT;

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

COMMIT;

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

COMMIT;

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

COMMIT;

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

COMMIT;

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

COMMIT;

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

COMMIT;

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

COMMIT;

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

COMMIT;

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

COMMIT;

CREATE UNIQUE INDEX HOMEPAGE.EMD_RESOURCE_PREF_UNQ
	ON HOMEPAGE.EMD_RESOURCE_PREF (PERSON_ID, RESOURCE_TYPE) TABLESPACE "NEWSINDEXTABSPACE";
COMMIT;



----------------------------------------------------------------------
-- HOMEPAGE.EMD_TRANCHE
----------------------------------------------------------------------
-- add columns:
-- "IS_LOCKED_DAILY" NUMBER(5,0) NOT NULL WITH DEFAULT 0
-- "IS_LOCKED_WEEKLY" NUMBER(5,0) NOT NULL WITH DEFAULT 0
-- "LAST_STARTED_DAILY" TIMESTAMP 
-- "LAST_STARTED_WEEKLY" TIMESTAMP
-- "LAST_EXEC_TIME_DAILY_MIN" INTEGER
-- "LAST_EXEC_TIME_WEEKLY_MIN" INTEGER
-- "LAST_RUNNER_DAILY" VARCHAR2(256)
-- "LAST_RUNNER_WEEKLY" VARCHAR2(256)
--
--
-- remove columns:
--
-- add indexes:
--
-- remove indexes:
----------------------------------------------------------------------
ALTER TABLE HOMEPAGE.EMD_TRANCHE ADD (
	IS_LOCKED_DAILY NUMBER(5,0) DEFAULT 0 NOT NULL,
	IS_LOCKED_WEEKLY NUMBER(5,0) DEFAULT 0 NOT NULL,
	LAST_STARTED_DAILY TIMESTAMP,
	LAST_STARTED_WEEKLY TIMESTAMP,
	LAST_EXEC_TIME_DAILY_MIN  NUMBER(10 ,0),
	LAST_EXEC_TIME_WEEKLY_MIN  NUMBER(10 ,0),
	LAST_RUNNER_DAILY VARCHAR2(256),
	LAST_RUNNER_WEEKLY VARCHAR2(256)
);

-- --reorg table HOMEPAGE.EMD_TRANCHE;

COMMIT;

UPDATE HOMEPAGE.EMD_TRANCHE	SET 	
		IS_LOCKED = 0,
		IS_LOCKED_DAILY = 0,
		IS_LOCKED_WEEKLY = 0;

COMMIT;

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
ALTER TABLE HOMEPAGE.EMD_TRANCHE_INFO
ADD TMP_DOMAIN_AFFINITY CLOB;

COMMIT;

----reorg table HOMEPAGE.EMD_TRANCHE_INFO use NEWS4TMPTABSPACE;

UPDATE HOMEPAGE.EMD_TRANCHE_INFO SET TMP_DOMAIN_AFFINITY = DOMAIN_AFFINITY;

COMMIT;

ALTER TABLE HOMEPAGE.EMD_TRANCHE_INFO
DROP COLUMN DOMAIN_AFFINITY;

COMMIT;

----reorg table HOMEPAGE.EMD_TRANCHE_INFO use NEWS4TMPTABSPACE;

ALTER TABLE HOMEPAGE.EMD_TRANCHE_INFO
ADD DOMAIN_AFFINITY CLOB;

COMMIT;

----reorg table HOMEPAGE.EMD_TRANCHE_INFO use NEWS4TMPTABSPACE;

UPDATE HOMEPAGE.EMD_TRANCHE_INFO SET DOMAIN_AFFINITY = TMP_DOMAIN_AFFINITY;

COMMIT;

----reorg table HOMEPAGE.EMD_TRANCHE_INFO use NEWS4TMPTABSPACE;

ALTER TABLE HOMEPAGE.EMD_TRANCHE_INFO
DROP COLUMN TMP_DOMAIN_AFFINITY;

COMMIT;

----reorg table HOMEPAGE.EMD_TRANCHE_INFO use NEWS4TMPTABSPACE;

COMMIT;

----------------------------------------------------------------------
-- HOMEPAGE.EMD_EMAIL_PREFS
----------------------------------------------------------------------
-- add columns:
-- "REPLYTO_ENABLED" NUMBER(5,0) NOT NULL WITH DEFAULT 1
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
	ADD REPLYTO_ENABLED NUMBER(5,0) DEFAULT 1 NOT NULL;

COMMIT;

-- --reorg table HOMEPAGE.EMD_EMAIL_PREFS;

COMMIT;

CREATE INDEX HOMEPAGE.EMD_EMAIL_PREFS_TR
    ON HOMEPAGE.EMD_EMAIL_PREFS (TRANCHE_ID) TABLESPACE "NEWSINDEXTABSPACE";

COMMIT;


----------------------------------------------------------------------
-- HOMEPAGE.NR_COMM_PERSON_FOLLOW
----------------------------------------------------------------------
-- add columns:
-- "IS_READER_COMM" NUMBER(5,0) NOT NULL WITH DEFAULT 0
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
	ADD IS_READER_COMM NUMBER(5,0) DEFAULT 0 NOT NULL;
	
-- --reorg table HOMEPAGE.NR_COMM_PERSON_FOLLOW use NEWS4TMPTABSPACE;

COMMIT;

UPDATE HOMEPAGE.NR_COMM_PERSON_FOLLOW  SET IS_READER_COMM = 0;

COMMIT;

UPDATE HOMEPAGE.NR_COMM_PERSON_FOLLOW  SET IS_READER_COMM = 1 WHERE NOT EXISTS (
	SELECT 	1
 	FROM  	HOMEPAGE.PERSON PERSON
 	WHERE 	HOMEPAGE.NR_COMM_PERSON_FOLLOW.PERSON_COMMUNITY_ID = PERSON.PERSON_ID 
);

COMMIT;


----------------------------------------------------------------
-- HOMEPAGE.NR_ENTRIES_ARCHIVE
----------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE (
	ENTRY_ID VARCHAR2(36) NOT NULL,
	SOURCE VARCHAR2(36) NOT NULL,
	SOURCE_TYPE NUMBER(5,0),
	CONTAINER_ID VARCHAR2(256),
	CONTAINER_NAME VARCHAR2(256),
	CONTAINER_URL VARCHAR2(2048),
	ITEM_ID VARCHAR2(256),
	ITEM_NAME VARCHAR2(256),
	ITEM_URL VARCHAR2(2048),
	ITEM_CREATION_DATE TIMESTAMP,
	ITEM_UPDATE_DATE TIMESTAMP,
	ITEM_TAGS VARCHAR2(1024),
	N_COMMENTS NUMBER(5,0) DEFAULT NULL,
	N_RECOMMANDATIONS NUMBER(5,0) DEFAULT NULL,
	LAST_COMMENT_ID VARCHAR2(256),
	LAST_DATE_COMMENT TIMESTAMP,
	LAST_AUTHOR_COMMENT VARCHAR2(36),
	PREV_COMMENT_ID VARCHAR2(256),
	PREV_DATE_COMMENT TIMESTAMP,
	PREV_AUTHOR_COMMENT VARCHAR2(36),
	ITEM_ATOM_URL VARCHAR2(2048),
	PREVIEW_IMAGE_URL VARCHAR2(2048),
	JSON_META_DATA VARCHAR2(4000),
	ITEM_SCOPE NUMBER(5,0),
	IS_LAST_COMMENT_PUBLIC NUMBER(5,0), 
	IS_PREV_COMMENT_PUBLIC NUMBER(5,0),
	ITEM_CORRELATION_ID VARCHAR2(256),
	ITEM_CORRELATION_NAME VARCHAR2(256),
	LAST_DESC_COMMENT VARCHAR2(4000),
	PREV_DESC_COMMENT VARCHAR2(4000),
	LAST_UPDATE_DATE_COMMENT TIMESTAMP,
	PREV_UPDATE_DATE_COMMENT TIMESTAMP,
	ITEM_AUTHOR_UUID VARCHAR2 (256),
	ITEM_AUTHOR_DISPLAYNAME VARCHAR2 (256),
	ITEM_CORRELATION_AUTHOR_UUID VARCHAR2 (256),
	ITEM_CORRELATION_AUTHOR_NAME VARCHAR2 (256),
	BRIEF_DESC VARCHAR2 (4000),
	ITEM_CORRELATION_BRIEF_DESC VARCHAR2(4000),
	IS_LAST_COMMENT_VISIBLE NUMBER(5,0) DEFAULT 1,
	IS_PREV_COMMENT_VISIBLE NUMBER(5,0) DEFAULT 1,
	LAST_UPDATE_RECORD TIMESTAMP 
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE
    ADD (CONSTRAINT PK_ENTRY_AR_ID PRIMARY KEY(ENTRY_ID)  USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

CREATE INDEX HOMEPAGE.NR_ENTRIES_AR_CONT
    ON HOMEPAGE.NR_ENTRIES_ARCHIVE (CONTAINER_ID)  TABLESPACE "NEWSINDEXTABSPACE";

CREATE UNIQUE INDEX HOMEPAGE.NR_ENTRIES_AR_ITEM
    ON HOMEPAGE.NR_ENTRIES_ARCHIVE (ITEM_ID)  TABLESPACE "NEWSINDEXTABSPACE";

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE ENABLE ROW MOVEMENT;

----------------------------------------------------------------
-- HOMEPAGE.NR_ENTRIES
----------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_ENTRIES (
	ENTRY_ID VARCHAR2(36) NOT NULL,
	SOURCE VARCHAR2(36) NOT NULL,
	SOURCE_TYPE NUMBER(5,0),
	CONTAINER_ID VARCHAR2(256),
	CONTAINER_NAME VARCHAR2(256),
	CONTAINER_URL VARCHAR2(2048),
	ITEM_ID VARCHAR2(256),
	ITEM_NAME VARCHAR2(256),
	ITEM_URL VARCHAR2(2048),
	ITEM_CREATION_DATE TIMESTAMP,
	ITEM_UPDATE_DATE TIMESTAMP,
	ITEM_TAGS VARCHAR2(1024),
	N_COMMENTS NUMBER(5,0) DEFAULT NULL,
	N_RECOMMANDATIONS NUMBER(5,0) DEFAULT NULL,
	LAST_COMMENT_ID VARCHAR2(256),
	LAST_DATE_COMMENT TIMESTAMP,
	LAST_AUTHOR_COMMENT VARCHAR2(36),
	PREV_COMMENT_ID VARCHAR2(256),
	PREV_DATE_COMMENT TIMESTAMP,
	PREV_AUTHOR_COMMENT VARCHAR2(36),
	ITEM_ATOM_URL VARCHAR2(2048),
	PREVIEW_IMAGE_URL VARCHAR2(2048),
	JSON_META_DATA VARCHAR2(4000),
	ITEM_SCOPE NUMBER(5,0),
	IS_LAST_COMMENT_PUBLIC NUMBER(5,0), 
	IS_PREV_COMMENT_PUBLIC NUMBER(5,0),
	ITEM_CORRELATION_ID VARCHAR2(256),
	ITEM_CORRELATION_NAME VARCHAR2(256),
	LAST_DESC_COMMENT VARCHAR2(4000),
	PREV_DESC_COMMENT VARCHAR2(4000),
	LAST_UPDATE_DATE_COMMENT TIMESTAMP,
	PREV_UPDATE_DATE_COMMENT TIMESTAMP,
	ITEM_AUTHOR_UUID VARCHAR2 (256),
	ITEM_AUTHOR_DISPLAYNAME VARCHAR2 (256),
	ITEM_CORRELATION_AUTHOR_UUID VARCHAR2 (256),
	ITEM_CORRELATION_AUTHOR_NAME VARCHAR2 (256),
	BRIEF_DESC VARCHAR2 (4000),
	ITEM_CORRELATION_BRIEF_DESC VARCHAR2(4000),
	IS_LAST_COMMENT_VISIBLE NUMBER(5,0) DEFAULT 1,
	IS_PREV_COMMENT_VISIBLE NUMBER(5,0) DEFAULT 1,
	LAST_UPDATE_RECORD TIMESTAMP 
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_ENTRIES
    ADD (CONSTRAINT PK_ENTRY_ID PRIMARY KEY(ENTRY_ID)  USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

CREATE INDEX HOMEPAGE.NR_ENTRIES_CONT
    ON HOMEPAGE.NR_ENTRIES (CONTAINER_ID)  TABLESPACE "NEWSINDEXTABSPACE";

CREATE UNIQUE INDEX HOMEPAGE.NR_ENTRIES_ITEM
    ON HOMEPAGE.NR_ENTRIES (ITEM_ID)  TABLESPACE "NEWSINDEXTABSPACE";

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES ENABLE ROW MOVEMENT;


----------------------------------------------------------------------
-- HOMEPAGE.NR_STORIES
----------------------------------------------------------------------
-- add columns:
-- "SOURCE_TYPE" NUMBER(5,0)
-- "ITEM_ATOM_URL" VARCHAR2(2048)
-- "EVENT_TIME" TIMESTAMP , 
-- "VERB" VARCHAR2(128) ,
-- "ACTIVITY_META_DATA_1" VARCHAR2(3584) FOR BIT DATA , 
-- "ACTIVITY_META_DATA_2" VARCHAR2(3584) FOR BIT DATA , 
-- "IS_META_DATA_TRUNCATED" NUMBER(5,0) ,
--  ENTRY_ID VARCHAR2(36)
-- EVENT_SCOPE NUMBER(5,0)
-- ITEM_TAGS VARCHAR2(1024)
-- ITEM_SCOPE NUMBER(5,0)
-- ITEM_UPDATE_DATE TIMESTAMP
-- ITEM_ID VARCHAR2(36)
-- ITEM_NAME VARCHAR2(256)
-- ITEM_URL VARCHAR2(2048)
-- FIRST_RECIPIENT_ID VARCHAR2(36)
-- NUM_RECIPIENTS NUMBER(5,0) 
-- PRIMARY_ACTION_URL VARCHAR2(768) 
-- SECONDARY_ACTION_URL VARCHAR2(768);	
--
-- remove columns:
--
-- add indexes:
--
--
-- remove indexes:
----------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_STORIES
	ADD SOURCE_TYPE NUMBER(5,0);
COMMIT;

-- --reorg table HOMEPAGE.NR_STORIES;

COMMIT;


UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 1 WHERE SOURCE = 'activities'   AND STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 2 WHERE SOURCE = 'blogs'   AND STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 3 WHERE SOURCE = 'communities'   AND STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 4 WHERE SOURCE = 'wikis'   AND STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 5 WHERE SOURCE = 'profiles'   AND STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 6 WHERE SOURCE = 'homepage'   AND STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 7 WHERE SOURCE = 'dogear'   AND STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 8 WHERE SOURCE = 'files'   AND STORY_ID >= 'f..f' ;
COMMIT;

UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID < '0..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '0..f' AND STORY_ID < '1..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '1..f' AND STORY_ID < '2..f' ;
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '2..f' AND STORY_ID < '3..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '3..f' AND STORY_ID < '4..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '4..f' AND STORY_ID < '5..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '5..f' AND STORY_ID < '6..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '6..f' AND STORY_ID < '7..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '7..f' AND STORY_ID < '8..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '8..f' AND STORY_ID < '9..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= '9..f' AND STORY_ID < 'a..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= 'a..f' AND STORY_ID < 'b..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= 'b..f' AND STORY_ID < 'c..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= 'c..f' AND STORY_ID < 'd..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= 'd..f' AND STORY_ID < 'e..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= 'e..f' AND STORY_ID < 'f..f';
COMMIT;
UPDATE HOMEPAGE.NR_STORIES SET SOURCE_TYPE = 9 WHERE SOURCE = 'forums'   AND STORY_ID >= 'f..f' ;
COMMIT;

CREATE TABLE HOMEPAGE.NR_STORIES_40 (
	STORY_ID VARCHAR2(36) NOT NULL,
	EVENT_NAME VARCHAR2(256) NOT NULL,
	SOURCE VARCHAR2(36),
	CONTAINER_ID VARCHAR2(256),	
	CONTAINER_NAME VARCHAR2(256),
	CONTAINER_URL VARCHAR2(2048),
	CONTAINER_ATOM_URL VARCHAR2(2048),
	ITEM_NAME VARCHAR2(256),
	ITEM_URL VARCHAR2(2048),
	ITEM_ATOM_URL VARCHAR2(2048),
	ITEM_ID VARCHAR2(256),
	ITEM_CORRELATION_ID VARCHAR2(256),
	CREATION_DATE TIMESTAMP NOT NULL,
	BRIEF_DESC VARCHAR2(4000),
	ACTOR_UUID VARCHAR2(256),
	EVENT_RECORD_UUID VARCHAR2(36) NOT NULL,
	TAGS VARCHAR2(1024),
	META_TEMPLATE VARCHAR2(3328) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE VARCHAR2(1024),
	R_META_TEMPLATE VARCHAR2(3328),
	R_TEXT_META_TEMPLATE VARCHAR2(1024),
	IS_COMMUNITY_STORY NUMBER(5,0) DEFAULT 0,
	ITEM_CORRELATION_NAME VARCHAR2(256),
	HAS_ATTACHMENT NUMBER(5,0) DEFAULT 0,
	SOURCE_TYPE NUMBER(5,0),
	ENTRY_ID VARCHAR2(36),
	EVENT_TIME TIMESTAMP,
	VERB VARCHAR2 (128),
	ITEM_SCOPE NUMBER(5 ,0),
	ITEM_UPDATE_DATE TIMESTAMP,
	FIRST_RECIPIENT_ID VARCHAR2(36),
	NUM_RECIPIENTS NUMBER(5 ,0),
	PRIMARY_ACTION_URL VARCHAR2(768),
	SECONDARY_ACTION_URL VARCHAR2(768),
	ITEM_AUTHOR_UUID VARCHAR2 (256),
	ITEM_CORRELATION_AUTHOR_UUID VARCHAR2 (256),
	ITEM_CORRELATION_AUTHOR_NAME VARCHAR2 (256),
	EVENT_SCOPE NUMBER(5 ,0),
	RELATED_COMMUNITY_ID VARCHAR2(36),
	ITEM_CORRELATION_SCOPE NUMBER(5),
	ITEM_CORRELATION_UPDATE_DATE TIMESTAMP,
	ITEM_CORRELATION_URL VARCHAR2(2048)
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_STORIES_40 ENABLE ROW MOVEMENT;

CREATE VIEW HOMEPAGE.STORIES_VIEW AS (
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

COMMIT;

DECLARE  CURSOR s_cur IS  SELECT * 	FROM HOMEPAGE.STORIES_VIEW;



TYPE fetch_array IS TABLE OF s_cur%ROWTYPE;
s_array fetch_array;

BEGIN
  	OPEN s_cur;
  	LOOP
    	FETCH s_cur BULK COLLECT INTO s_array LIMIT 1000;
    	FORALL i IN 1..s_array.COUNT
    	INSERT INTO HOMEPAGE.NR_STORIES_40 	VALUES s_array(i);
		COMMIT;
    	
		EXIT WHEN s_cur%NOTFOUND;
  	END LOOP;
  	CLOSE s_cur;
  	COMMIT;
END;
/

commit;


DROP VIEW HOMEPAGE.STORIES_VIEW;

COMMIT;

DROP TABLE HOMEPAGE.NR_STORIES;

COMMIT;

ALTER TABLE HOMEPAGE.NR_STORIES_40 RENAME TO NR_STORIES;
COMMIT;

ALTER TABLE HOMEPAGE.NR_STORIES ENABLE ROW MOVEMENT;

-----------------------------------------
-- Adding NR_STORIES constraints
-----------------------------------------
ALTER TABLE HOMEPAGE.NR_STORIES
    ADD (CONSTRAINT "PK_STORY_ID" PRIMARY KEY("STORY_ID")  USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_STORIES
  	ADD CONSTRAINT FK_ENTRY_ID FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.NR_ENTRIES (ENTRY_ID);    

CREATE INDEX HOMEPAGE.NR_STORIES_DATE
	ON HOMEPAGE.NR_STORIES (CREATION_DATE DESC)  TABLESPACE "NEWSINDEXTABSPACE";

CREATE INDEX HOMEPAGE.STORY_CONTAINED_ID
    ON HOMEPAGE.NR_STORIES (CONTAINER_ID)  TABLESPACE "NEWSINDEXTABSPACE";

CREATE INDEX HOMEPAGE.STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_STORIES (ITEM_CORRELATION_ID)  TABLESPACE "NEWSINDEXTABSPACE";

CREATE INDEX HOMEPAGE.NR_STORIES_EIDX
    ON HOMEPAGE.NR_STORIES (ENTRY_ID)  TABLESPACE "NEWSINDEXTABSPACE";

CREATE INDEX HOMEPAGE.NR_STORIES_ER_UUID
    ON HOMEPAGE.NR_STORIES (EVENT_RECORD_UUID, ENTRY_ID)  TABLESPACE "NEWSINDEXTABSPACE";
    
CREATE INDEX HOMEPAGE.NR_STORY_CD_IDX 
	ON HOMEPAGE.NR_STORIES (CREATION_DATE ASC, STORY_ID) TABLESPACE "NEWSINDEXTABSPACE"; 

CREATE INDEX HOMEPAGE.STORIES_CONTAINER_URL_IDX ON 
	HOMEPAGE.NR_STORIES (CONTAINER_URL ASC) TABLESPACE "NEWSINDEXTABSPACE";

CREATE INDEX HOMEPAGE.STORIES_EVENT_ITEM_ACTOR_IDX ON
	HOMEPAGE.NR_STORIES (EVENT_NAME, ITEM_ID, ACTOR_UUID) TABLESPACE "NEWSINDEXTABSPACE";

COMMIT;      


---------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------
-- ADD READERS TABLES ( They where the old _STORIES tables)
----------------------------------------------------------------------
-- Removing an OLD unused table:
DROP TABLE HOMEPAGE.NR_ORGPERSON_STORIES;
COMMIT;

----------------------------------------------------------------------
----------------------------------------------------------------------
-- CREATE TABLE "HOMEPAGE"."NR_AGGREGATED_READERS"
----------------------------------------------------------------------
-- HOMEPAGE.NR_AGGREGATED_READERS 
----------------------------------------------------------------------
ALTER TABLE  HOMEPAGE.NR_COMM_PERSON_STORIES  DROP CONSTRAINT PK_COMPER_STORY_ID;

ALTER TABLE HOMEPAGE.NR_COMM_PERSON_STORIES DROP CONSTRAINT UNIQUE_COMM_PERSON;	
COMMIT;

-- Rename table
ALTER TABLE HOMEPAGE.NR_COMM_PERSON_STORIES RENAME TO NR_AGGREGATED_READERS;
COMMIT;

ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS RENAME COLUMN COMM_PER_STORY_ID TO CATEGORY_READER_ID;
COMMIT;
ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS RENAME COLUMN COMM_PER_READER_ID TO READER_ID;
COMMIT;

-- Adding two columns:
ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS
	ADD ROLLUP_ENTRY_ID VARCHAR2(256)
	ADD USE_IN_ROLLUP NUMBER(5 ,0)
	ADD IS_NETWORK	NUMBER(5 ,0)
	ADD IS_FOLLOWER	NUMBER(5 ,0)
	ADD EVENT_TIME 	TIMESTAMP
	ADD IS_STORY_COMM NUMBER(5 ,0)
	ADD IS_BROADCAST NUMBER(5 ,0)
	ADD ORGANIZATION_ID VARCHAR2(256)
	ADD ACTOR_UUID VARCHAR2(256)
	ADD IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL
	ADD ROLLUP_AUTHOR_ID VARCHAR (256);
COMMIT;

-- set USE_IN_ROLLUP to be zero
UPDATE HOMEPAGE.NR_AGGREGATED_READERS SET USE_IN_ROLLUP = 0;
COMMIT;

-- Set CATEGORY_READER_ID to be a DEFAULT ' ' NOT NULL 
ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS MODIFY (CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ');
COMMIT;

-- Increase the exiting ITEM_ID to be 256
ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS MODIFY (ITEM_ID VARCHAR2(256));
COMMIT;	

-- PK
--REORG TABLE HOMEPAGE.NR_AGGREGATED_READERS;
COMMIT;

ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS
    ADD (CONSTRAINT PK_AGG_READERS PRIMARY KEY(CATEGORY_READER_ID)  USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS 
	ADD CONSTRAINT FK_AGG_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
--  [start indexes] NR_AGGREGATED_READERS
CREATE  INDEX HOMEPAGE.AGGREGATED_READERS_STR_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.AGGREGATED_READERS_ITM_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.AGGREGATED_READERS_CD_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.AGGREGATED_READERS_SRC_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.AGGREGATED_READERS_AUT_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.AGGREGATED_READERS_RLL_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_AGGREGATED_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.AGGREGATED_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_AGGREGATED_READERS_RDR_IX ON HOMEPAGE.NR_AGGREGATED_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.AGGREGATED_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_AGGREGATED_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_AGGREGATED_READERS_RIR_IX
 	ON HOMEPAGE.NR_AGGREGATED_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_AGGREGATED_READERS
    
ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS ENABLE ROW MOVEMENT;


-- NR_RESPONSES_READERS old  "NR_RESPONSES_STORIES"
--------------------------------------------------------------------------
-- 1 HOMEPAGE.NR_RESPONSES_READERS
--------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_RESPONSES_STORIES DROP CONSTRAINT  PK_RESP_STORIES;

-- Rename table
ALTER TABLE HOMEPAGE.NR_RESPONSES_STORIES RENAME TO NR_RESPONSES_READERS;
COMMIT;

ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS RENAME COLUMN FOLLOWED_STORY_ID TO CATEGORY_READER_ID;
COMMIT;

-- Adding two columns:
ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS
	ADD ROLLUP_ENTRY_ID VARCHAR2(256)
	ADD USE_IN_ROLLUP NUMBER(5 ,0)
	ADD IS_NETWORK	NUMBER(5 ,0)
	ADD IS_FOLLOWER	NUMBER(5 ,0)
	ADD EVENT_TIME 	TIMESTAMP
	ADD IS_STORY_COMM NUMBER(5 ,0)
	ADD IS_BROADCAST NUMBER(5 ,0)
	ADD ORGANIZATION_ID VARCHAR2(256)
	ADD ACTOR_UUID VARCHAR2(256)
	ADD IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL
	ADD ROLLUP_AUTHOR_ID VARCHAR (256);
COMMIT;

-- set USE_IN_ROLLUP to be zero
UPDATE HOMEPAGE.NR_RESPONSES_READERS SET USE_IN_ROLLUP = 0;
COMMIT;

-- Set CATEGORY_READER_ID to be a DEFAULT ' ' NOT NULL 
ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS MODIFY (CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ');
COMMIT;

-- Increase the exiting ITEM_ID to be 256
ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS MODIFY (ITEM_ID VARCHAR2(256));
COMMIT;

-- PK
--REORG TABLE HOMEPAGE.NR_RESPONSES_READERS;
COMMIT;

ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS 
    ADD (CONSTRAINT PK_RESP_READERS PRIMARY KEY(CATEGORY_READER_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS 
	ADD CONSTRAINT FK_RES_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
--  [start indexes] NR_RESPONSES_READERS
CREATE  INDEX HOMEPAGE.RESPONSES_READERS_STR_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.RESPONSES_READERS_ITM_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.RESPONSES_READERS_CD_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.RESPONSES_READERS_SRC_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.RESPONSES_READERS_AUT_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.RESPONSES_READERS_RLL_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_RESPONSES_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.RESPONSES_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_RESPONSES_READERS_RDR_IX ON HOMEPAGE.NR_RESPONSES_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.RESPONSES_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_RESPONSES_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_RESPONSES_READERS_RIR_IX
 	ON HOMEPAGE.NR_RESPONSES_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_RESPONSES_READERS

ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS ENABLE ROW MOVEMENT;


-- NR_PROFILES_READERS old NR_PROFILES_STORIES
--------------------------------------------------------------------------
-- 2 HOMEPAGE.NR_PROFILES_READERS
--------------------------------------------------------------------------
ALTER TABLE  HOMEPAGE.NR_PROFILES_STORIES  DROP CONSTRAINT PK_PROF_STORIES;
COMMIT;

-- Rename table
ALTER TABLE HOMEPAGE.NR_PROFILES_STORIES RENAME TO NR_PROFILES_READERS;
COMMIT; 
ALTER TABLE HOMEPAGE.NR_PROFILES_READERS RENAME COLUMN FOLLOWED_STORY_ID TO CATEGORY_READER_ID;
COMMIT;

-- Adding two columns:
ALTER TABLE HOMEPAGE.NR_PROFILES_READERS
	ADD ROLLUP_ENTRY_ID VARCHAR2(256)
	ADD USE_IN_ROLLUP NUMBER(5 ,0)
	ADD IS_NETWORK	NUMBER(5 ,0)
	ADD IS_FOLLOWER	NUMBER(5 ,0)
	ADD EVENT_TIME 	TIMESTAMP
	ADD IS_STORY_COMM NUMBER(5 ,0)
	ADD IS_BROADCAST NUMBER(5 ,0)
	ADD ORGANIZATION_ID VARCHAR2(256)
	ADD ACTOR_UUID VARCHAR2(256)
	ADD IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL
	ADD ROLLUP_AUTHOR_ID VARCHAR (256);
COMMIT;

-- set USE_IN_ROLLUP to be zero
UPDATE HOMEPAGE.NR_PROFILES_READERS SET USE_IN_ROLLUP = 0;
COMMIT;

-- Set CATEGORY_READER_ID to be a DEFAULT ' ' NOT NULL 
ALTER TABLE HOMEPAGE.NR_PROFILES_READERS MODIFY (CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ');
COMMIT;

-- Increase the exiting ITEM_ID to be 256
ALTER TABLE HOMEPAGE.NR_PROFILES_READERS MODIFY (ITEM_ID VARCHAR2(256));
COMMIT;

-- PK
--REORG TABLE HOMEPAGE.NR_PROFILES_READERS;
COMMIT;

ALTER TABLE HOMEPAGE.NR_PROFILES_READERS 
    ADD (CONSTRAINT PK_PROF_READERS PRIMARY KEY(CATEGORY_READER_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");    

ALTER TABLE HOMEPAGE.NR_PROFILES_READERS 
	ADD CONSTRAINT FK_PRF_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
--  [start indexes] NR_PROFILES_READERS
CREATE  INDEX HOMEPAGE.PROFILES_READERS_STR_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.PROFILES_READERS_ITM_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.PROFILES_READERS_CD_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.PROFILES_READERS_SRC_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.PROFILES_READERS_AUT_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.PROFILES_READERS_RLL_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_PROFILES_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.PROFILES_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_PROFILES_READERS_RDR_IX ON HOMEPAGE.NR_PROFILES_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.PROFILES_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_PROFILES_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_PROFILES_READERS_RIR_IX
 	ON HOMEPAGE.NR_PROFILES_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_PROFILES_READERS

ALTER TABLE HOMEPAGE.NR_PROFILES_READERS ENABLE ROW MOVEMENT;

-- "TO NR_COMMUNITIES_READERS old NR_COMM_STORIES"  
--------------------------------------------------------------------------
-- 3 HOMEPAGE.NR_COMMUNITIES_READERS
--------------------------------------------------------------------------
DROP TABLE HOMEPAGE.NR_COMMUNITIES_STORIES;
COMMIT;

CREATE TABLE HOMEPAGE.NR_COMMUNITIES_READERS (
	CATEGORY_READER_ID VARCHAR2(36)   DEFAULT ' ' NOT NULL,
	READER_ID VARCHAR2(36) NOT NULL,
	CATEGORY_TYPE NUMBER(5,0) NOT NULL,
	SOURCE VARCHAR2(36) NOT NULL,
	CONTAINER_ID VARCHAR2(256),
	ITEM_ID VARCHAR2(256),
	ROLLUP_ENTRY_ID VARCHAR2(256),
	RESOURCE_TYPE NUMBER(5,0) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	STORY_ID VARCHAR2(36) NOT NULL,
	SOURCE_TYPE NUMBER(5,0),
	USE_IN_ROLLUP NUMBER(5,0),
	IS_NETWORK	NUMBER(5,0),
	IS_FOLLOWER	NUMBER(5,0),
	EVENT_TIME 	TIMESTAMP,
	IS_STORY_COMM NUMBER(5 ,0),
	IS_BROADCAST NUMBER(5,0),
	ORGANIZATION_ID VARCHAR2(256),
	ACTOR_UUID VARCHAR2(256),
	ROLLUP_AUTHOR_ID VARCHAR2 (256),
	IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL,	
        CONSTRAINT   	CK_CAT3_TYPE
    			CHECK
    			(CATEGORY_TYPE = 3)
)
TABLESPACE "NEWSREGTABSPACE";

--moving data
DECLARE  CURSOR s_cur IS  
SELECT 
	COMM_STORY_ID CATEGORY_READER_ID, COMMUNITY_ID READER_ID, 3 CATEGORY_TYPE, 'communities' SOURCE, CONTAINER_ID, ITEM_ID,
	' ' ROLLUP_ENTRY_ID, RESOURCE_TYPE, CREATION_DATE, STORY_ID, SOURCE_TYPE, 0 USE_IN_ROLLUP,
	0 IS_NETWORK, 0 IS_FOLLOWER, CREATION_DATE EVENT_TIME, 1 IS_STORY_COMM, 
	0 IS_BROADCAST, ' ' ORGANIZATION_ID, ' ' ACTOR_UUID, ' ' ROLLUP_AUTHOR_ID, 1 IS_VISIBLE	
FROM HOMEPAGE.NR_COMM_STORIES;

TYPE fetch_array IS TABLE OF s_cur%ROWTYPE;
s_array fetch_array;

BEGIN
  	OPEN s_cur;
  	LOOP
    	FETCH s_cur BULK COLLECT INTO s_array LIMIT 1000;
    	FORALL i IN 1..s_array.COUNT
    	INSERT INTO HOMEPAGE.NR_COMMUNITIES_READERS VALUES s_array(i);
		COMMIT;
    	
		EXIT WHEN s_cur%NOTFOUND;
  	END LOOP;
  	CLOSE s_cur;
  	COMMIT;
END;
/

commit;

--DROP TABLE HOMEPAGE.NR_COMM_STORIES;
--COMMIT;

-- PK
--REORG TABLE HOMEPAGE.NR_COMMUNITIES_READERS;
COMMIT;

ALTER TABLE HOMEPAGE.NR_COMMUNITIES_READERS 
    ADD (CONSTRAINT PK_COMM_READERS PRIMARY KEY(CATEGORY_READER_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_COMMUNITIES_READERS
	ADD CONSTRAINT FK_COM_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
--  [start indexes] NR_COMMUNITIES_READERS
CREATE  INDEX HOMEPAGE.COMM_READERS_STR_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.COMM_READERS_ITM_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.COMM_READERS_CD_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.COMM_READERS_SRC_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.COMM_READERS_AUT_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.COMM_READERS_RLL_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_COMMUNITIES_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.COMM_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_COMM_READERS_RDR_IX ON HOMEPAGE.NR_COMMUNITIES_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.COMM_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_COMM_READERS_RIR_IX
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_COMMUNITIES_READERS

ALTER TABLE HOMEPAGE.NR_COMMUNITIES_READERS ENABLE ROW MOVEMENT;

-- NR_ACTIVITIES_READERS old NR_ACTIVITIES_STORIES
--------------------------------------------------------------------------
-- 4 HOMEPAGE.NR_ACTIVITIES_READERS
--------------------------------------------------------------------------
ALTER TABLE  HOMEPAGE.NR_ACTIVITIES_STORIES  DROP CONSTRAINT PK_ACT_STORIES;
COMMIT;

-- Rename table
ALTER TABLE HOMEPAGE.NR_ACTIVITIES_STORIES RENAME TO NR_ACTIVITIES_READERS;
COMMIT;
ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS RENAME COLUMN FOLLOWED_STORY_ID TO CATEGORY_READER_ID;
COMMIT;

-- Adding two columns:
ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS
	ADD ROLLUP_ENTRY_ID VARCHAR2(256)
	ADD USE_IN_ROLLUP NUMBER(5 ,0)
	ADD IS_NETWORK	NUMBER(5 ,0)
	ADD IS_FOLLOWER	NUMBER(5 ,0)
	ADD EVENT_TIME 	TIMESTAMP
	ADD IS_STORY_COMM NUMBER(5 ,0)
	ADD IS_BROADCAST NUMBER(5 ,0)
	ADD ORGANIZATION_ID VARCHAR2(256)
	ADD ACTOR_UUID VARCHAR2(256)
	ADD IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL
	ADD ROLLUP_AUTHOR_ID VARCHAR (256);
COMMIT;

-- Increase the exiting ITEM_ID to be 256
ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS MODIFY (ITEM_ID VARCHAR2(256));
COMMIT;

-- Set CATEGORY_READER_ID to be a DEFAULT ' ' NOT NULL 
ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS MODIFY (CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ');
COMMIT;

-- set USE_IN_ROLLUP to be zero
UPDATE HOMEPAGE.NR_ACTIVITIES_READERS SET USE_IN_ROLLUP = 0;
COMMIT;

-- PK
--REORG TABLE HOMEPAGE.NR_ACTIVITIES_READERS;
COMMIT;

ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS 
    ADD (CONSTRAINT PK_ACT_READERS PRIMARY KEY(CATEGORY_READER_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS
	ADD CONSTRAINT FK_ACI_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
--  [start indexes] NR_ACTIVITIES_READERS
CREATE  INDEX HOMEPAGE.ACTIVITIES_READERS_STR_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.ACTIVITIES_READERS_ITM_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.ACTIVITIES_READERS_CD_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.ACTIVITIES_READERS_SRC_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.ACTIVITIES_READERS_AUT_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.ACTIVITIES_READERS_RLL_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_ACTIVITIES_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.ACTIVITIES_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_ACTIVITIES_READERS_RDR_IX ON HOMEPAGE.NR_ACTIVITIES_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.ACTIVITIES_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_ACTIVITIES_READERS_RIR_IX
 	ON HOMEPAGE.NR_ACTIVITIES_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_ACTIVITIES_READERS

ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS ENABLE ROW MOVEMENT;

-- NR_BLOGS_READERS old NR_BLOGS_STORIES
--------------------------------------------------------------------------
-- 5 HOMEPAGE.NR_BLOGS_READERS
--------------------------------------------------------------------------
ALTER TABLE  HOMEPAGE.NR_BLOGS_STORIES  DROP CONSTRAINT PK_BLOGS_STORIES;
COMMIT;

-- Rename table
ALTER TABLE HOMEPAGE.NR_BLOGS_STORIES RENAME TO NR_BLOGS_READERS;
COMMIT; 
ALTER TABLE HOMEPAGE.NR_BLOGS_READERS RENAME COLUMN FOLLOWED_STORY_ID TO CATEGORY_READER_ID;
COMMIT;

-- Adding two columns:
ALTER TABLE HOMEPAGE.NR_BLOGS_READERS
	ADD ROLLUP_ENTRY_ID VARCHAR2(256)
	ADD USE_IN_ROLLUP NUMBER(5 ,0)
	ADD IS_NETWORK	NUMBER(5 ,0)
	ADD IS_FOLLOWER	NUMBER(5 ,0)
	ADD EVENT_TIME 	TIMESTAMP
	ADD IS_STORY_COMM NUMBER(5 ,0)
	ADD IS_BROADCAST NUMBER(5 ,0)
	ADD ORGANIZATION_ID VARCHAR2(256)
	ADD ACTOR_UUID VARCHAR2(256)
	ADD IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL
	ADD ROLLUP_AUTHOR_ID VARCHAR (256);
COMMIT;

-- set USE_IN_ROLLUP to be zero
UPDATE HOMEPAGE.NR_BLOGS_READERS SET USE_IN_ROLLUP = 0;
COMMIT;

-- Set CATEGORY_READER_ID to be a DEFAULT ' ' NOT NULL 
ALTER TABLE HOMEPAGE.NR_BLOGS_READERS MODIFY (CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ');
COMMIT;

-- Increase the exiting ITEM_ID to be 256
ALTER TABLE HOMEPAGE.NR_BLOGS_READERS MODIFY (ITEM_ID VARCHAR2(256));
COMMIT;

-- PK
--REORG TABLE HOMEPAGE.NR_BLOGS_READERS;
COMMIT;

ALTER TABLE HOMEPAGE.NR_BLOGS_READERS 
    ADD (CONSTRAINT PK_BLOGS_READERS PRIMARY KEY(CATEGORY_READER_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_BLOGS_READERS
	ADD CONSTRAINT FK_BLG_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
--  [start indexes] NR_BLOGS_READERS
CREATE  INDEX HOMEPAGE.BLOGS_READERS_STR_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.BLOGS_READERS_ITM_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.BLOGS_READERS_CD_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.BLOGS_READERS_SRC_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.BLOGS_READERS_AUT_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.BLOGS_READERS_RLL_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_BLOGS_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.BLOGS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_BLOGS_READERS_RDR_IX ON HOMEPAGE.NR_BLOGS_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.BLOGS_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_BLOGS_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_BLOGS_READERS_RIR_IX
 	ON HOMEPAGE.NR_BLOGS_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_BLOGS_READERS
	
ALTER TABLE HOMEPAGE.NR_BLOGS_READERS ENABLE ROW MOVEMENT;

-- NR_BOOKMARKS_READERS old "HOMEPAGE"."NR_BOOKMARKS_STORIES"
--------------------------------------------------------------------------
-- 6 NR_BOOKMARKS_STORIES (EMPTY TABLE)
--------------------------------------------------------------------------
DROP TABLE HOMEPAGE.NR_BOOKMARKS_STORIES;
COMMIT;

CREATE TABLE HOMEPAGE.NR_BOOKMARKS_READERS (
	CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ' NOT NULL,
	READER_ID VARCHAR2(36) NOT NULL,
	CATEGORY_TYPE NUMBER(5,0) NOT NULL,
	SOURCE VARCHAR2(36) NOT NULL,
	CONTAINER_ID VARCHAR2(256),
	ITEM_ID VARCHAR2(256),
	ROLLUP_ENTRY_ID VARCHAR2(256),
	RESOURCE_TYPE NUMBER(5,0) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	STORY_ID VARCHAR2(36) NOT NULL,
	SOURCE_TYPE NUMBER(5,0),
	USE_IN_ROLLUP NUMBER(5,0),
	IS_NETWORK	NUMBER(5,0),
	IS_FOLLOWER	NUMBER(5,0),
	EVENT_TIME 	TIMESTAMP,	
	IS_STORY_COMM NUMBER(5 ,0),
	IS_BROADCAST NUMBER(5,0),
	ORGANIZATION_ID VARCHAR2(256),
	ACTOR_UUID VARCHAR2(256),
	ROLLUP_AUTHOR_ID VARCHAR2 (256),
	IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL,	
        CONSTRAINT   	CK_CAT6_TYPE
    			CHECK
    			(CATEGORY_TYPE = 6)
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_BOOKMARKS_READERS 
    ADD (CONSTRAINT PK_BOOKS_READERS PRIMARY KEY(CATEGORY_READER_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");
    
ALTER TABLE HOMEPAGE.NR_BOOKMARKS_READERS
	ADD CONSTRAINT FK_BKM_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);

--  [start indexes] NR_BOOKMARKS_READERS
CREATE  INDEX HOMEPAGE.BOOKMARKS_READERS_STR_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.BOOKMARKS_READERS_ITM_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.BOOKMARKS_READERS_CD_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.BOOKMARKS_READERS_SRC_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.BOOKMARKS_READERS_AUT_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.BOOKMARKS_READERS_RLL_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_BOOKMARKS_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.BOOKMARKS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_BOOKMARKS_READERS_RDR_IX ON HOMEPAGE.NR_BOOKMARKS_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.BOOKMARKS_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_BOOKMARKS_READERS_RIR_IX
 	ON HOMEPAGE.NR_BOOKMARKS_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_BOOKMARKS_READERS

ALTER TABLE HOMEPAGE.NR_BOOKMARKS_READERS ENABLE ROW MOVEMENT;

-- NR_FILES_READERS old NR_FILES_STORIES
--------------------------------------------------------------------------
-- 7 NR_FILES_STORIES
--------------------------------------------------------------------------
ALTER TABLE  HOMEPAGE.NR_FILES_STORIES  DROP CONSTRAINT PK_FILES_STORIES;
COMMIT;

-- Rename table
ALTER TABLE HOMEPAGE.NR_FILES_STORIES RENAME TO NR_FILES_READERS;
COMMIT; 
ALTER TABLE HOMEPAGE.NR_FILES_READERS RENAME COLUMN FOLLOWED_STORY_ID TO CATEGORY_READER_ID;
COMMIT;

-- Adding two columns:
ALTER TABLE HOMEPAGE.NR_FILES_READERS
	ADD ROLLUP_ENTRY_ID VARCHAR2(256)
	ADD USE_IN_ROLLUP NUMBER(5 ,0)
	ADD IS_NETWORK	NUMBER(5 ,0)
	ADD IS_FOLLOWER	NUMBER(5 ,0)
	ADD EVENT_TIME 	TIMESTAMP
	ADD IS_STORY_COMM NUMBER(5 ,0)
	ADD IS_BROADCAST NUMBER(5 ,0)
	ADD ORGANIZATION_ID VARCHAR2(256)
	ADD ACTOR_UUID VARCHAR2(256)
	ADD IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL
	ADD ROLLUP_AUTHOR_ID VARCHAR (256);
COMMIT;

-- set USE_IN_ROLLUP to be zero
UPDATE HOMEPAGE.NR_FILES_READERS SET USE_IN_ROLLUP = 0;
COMMIT;

-- Set CATEGORY_READER_ID to be a DEFAULT ' ' NOT NULL 
ALTER TABLE HOMEPAGE.NR_FILES_READERS MODIFY (CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ');
COMMIT;

-- Increase the exiting ITEM_ID to be 256
ALTER TABLE HOMEPAGE.NR_FILES_READERS MODIFY (ITEM_ID VARCHAR2(256));
COMMIT;

-- PK
--REORG TABLE HOMEPAGE.NR_FILES_READERS;
COMMIT;

ALTER TABLE HOMEPAGE.NR_FILES_READERS 
    ADD (CONSTRAINT PK_FILES_READERS PRIMARY KEY(CATEGORY_READER_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_FILES_READERS
	ADD CONSTRAINT FK_FIL_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
--  [start indexes] NR_FILES_READERS
CREATE  INDEX HOMEPAGE.FILES_READERS_STR_IX 
 	ON HOMEPAGE.NR_FILES_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.FILES_READERS_ITM_IX 
 	ON HOMEPAGE.NR_FILES_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.FILES_READERS_CD_IX 
 	ON HOMEPAGE.NR_FILES_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.FILES_READERS_SRC_IX 
 	ON HOMEPAGE.NR_FILES_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.FILES_READERS_AUT_IX 
 	ON HOMEPAGE.NR_FILES_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.FILES_READERS_RLL_IX 
 	ON HOMEPAGE.NR_FILES_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_FILES_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.FILES_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_FILES_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_FILES_READERS_RDR_IX ON HOMEPAGE.NR_FILES_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.FILES_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_FILES_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_FILES_READERS_RIR_IX
 	ON HOMEPAGE.NR_FILES_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_FILES_READERS

ALTER TABLE HOMEPAGE.NR_FILES_READERS ENABLE ROW MOVEMENT;

-- "NR_FORUMS_READERS old "NR_FORUMS_STORIES" 
--------------------------------------------------------------------------
-- 8 NR_FORUMS_STORIES
--------------------------------------------------------------------------
ALTER TABLE  HOMEPAGE.NR_FORUMS_STORIES  DROP CONSTRAINT PK_FORUMS_STORIES;
COMMIT;

-- Rename table
ALTER TABLE HOMEPAGE.NR_FORUMS_STORIES RENAME TO NR_FORUMS_READERS;
COMMIT;
ALTER TABLE HOMEPAGE.NR_FORUMS_READERS RENAME COLUMN FOLLOWED_STORY_ID TO CATEGORY_READER_ID;
COMMIT;

-- Adding two columns:
ALTER TABLE HOMEPAGE.NR_FORUMS_READERS
	ADD ROLLUP_ENTRY_ID VARCHAR2(256)
	ADD USE_IN_ROLLUP NUMBER(5 ,0)
	ADD IS_NETWORK	NUMBER(5 ,0)
	ADD IS_FOLLOWER	NUMBER(5 ,0)
	ADD EVENT_TIME 	TIMESTAMP
	ADD IS_STORY_COMM NUMBER(5 ,0)
	ADD IS_BROADCAST NUMBER(5 ,0)
	ADD ORGANIZATION_ID VARCHAR2(256)
	ADD ACTOR_UUID VARCHAR2(256)
	ADD IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL
	ADD ROLLUP_AUTHOR_ID VARCHAR (256);
COMMIT;

-- set USE_IN_ROLLUP to be zero
UPDATE HOMEPAGE.NR_FORUMS_READERS SET USE_IN_ROLLUP = 0;
COMMIT;

-- Set CATEGORY_READER_ID to be a DEFAULT ' ' NOT NULL 
ALTER TABLE HOMEPAGE.NR_FORUMS_READERS MODIFY (CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ');
COMMIT;

-- Increase the exiting ITEM_ID to be 256
ALTER TABLE HOMEPAGE.NR_FORUMS_READERS MODIFY (ITEM_ID VARCHAR2(256));
COMMIT;

-- PK
--REORG TABLE HOMEPAGE.NR_FORUMS_READERS;
COMMIT;

ALTER TABLE HOMEPAGE.NR_FORUMS_READERS 
    ADD (CONSTRAINT PK_FORUMS_READERS PRIMARY KEY(CATEGORY_READER_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_FORUMS_READERS
	ADD CONSTRAINT FK_FRM_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
--  [start indexes] NR_FORUMS_READERS
CREATE  INDEX HOMEPAGE.FORUMS_READERS_STR_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.FORUMS_READERS_ITM_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.FORUMS_READERS_CD_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.FORUMS_READERS_SRC_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.FORUMS_READERS_AUT_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.FORUMS_READERS_RLL_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_FORUMS_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.FORUMS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_FORUMS_READERS_RDR_IX ON HOMEPAGE.NR_FORUMS_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.FORUMS_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_FORUMS_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_FORUMS_READERS_RIR_IX
 	ON HOMEPAGE.NR_FORUMS_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_FORUMS_READERS

ALTER TABLE HOMEPAGE.NR_FORUMS_READERS ENABLE ROW MOVEMENT;


-- NR_WIKIS_READERS old NR_WIKIS_STORIES
--------------------------------------------------------------------------
-- 9 NR_WIKIS_STORIES
--------------------------------------------------------------------------
ALTER TABLE  HOMEPAGE.NR_WIKIS_STORIES  DROP CONSTRAINT PK_WIKIS_STORIES;
COMMIT; 

-- Rename table
ALTER TABLE HOMEPAGE.NR_WIKIS_STORIES RENAME TO NR_WIKIS_READERS;
COMMIT; 
ALTER TABLE HOMEPAGE.NR_WIKIS_READERS RENAME COLUMN FOLLOWED_STORY_ID TO CATEGORY_READER_ID;
COMMIT;

-- Adding two columns:
ALTER TABLE HOMEPAGE.NR_WIKIS_READERS
	ADD ROLLUP_ENTRY_ID VARCHAR2(256)
	ADD USE_IN_ROLLUP NUMBER(5 ,0)
	ADD IS_NETWORK	NUMBER(5 ,0)
	ADD IS_FOLLOWER	NUMBER(5 ,0)
	ADD EVENT_TIME 	TIMESTAMP
	ADD IS_STORY_COMM NUMBER(5 ,0)
	ADD IS_BROADCAST NUMBER(5 ,0)
	ADD ORGANIZATION_ID VARCHAR2(256)
	ADD ACTOR_UUID VARCHAR2(256)
	ADD IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL
	ADD ROLLUP_AUTHOR_ID VARCHAR (256);
COMMIT;

-- set USE_IN_ROLLUP to be zero
UPDATE HOMEPAGE.NR_WIKIS_READERS SET USE_IN_ROLLUP = 0;
COMMIT;

-- Set CATEGORY_READER_ID to be a DEFAULT ' ' NOT NULL 
ALTER TABLE HOMEPAGE.NR_WIKIS_READERS MODIFY (CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ');
COMMIT;

-- Increase the exiting ITEM_ID to be 256
ALTER TABLE HOMEPAGE.NR_WIKIS_READERS MODIFY (ITEM_ID VARCHAR2(256));
COMMIT;

-- PK
--REORG TABLE HOMEPAGE.NR_WIKIS_READERS;
COMMIT;

ALTER TABLE HOMEPAGE.NR_WIKIS_READERS
    ADD (CONSTRAINT PK_WIKIS_READERS PRIMARY KEY(CATEGORY_READER_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_WIKIS_READERS
	ADD CONSTRAINT FK_WIK_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
--  [start indexes] NR_WIKIS_READERS
CREATE  INDEX HOMEPAGE.WIKIS_READERS_STR_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.WIKIS_READERS_ITM_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.WIKIS_READERS_CD_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.WIKIS_READERS_SRC_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.WIKIS_READERS_AUT_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.WIKIS_READERS_RLL_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_WIKIS_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.WIKIS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_WIKIS_READERS_RDR_IX ON HOMEPAGE.NR_WIKIS_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.WIKIS_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_WIKIS_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_WIKIS_READERS_RIR_IX
 	ON HOMEPAGE.NR_WIKIS_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_WIKIS_READERS

ALTER TABLE HOMEPAGE.NR_WIKIS_READERS ENABLE ROW MOVEMENT;


-- NR_TAGS_READERS old NR_TAGS_STORIES
--------------------------------------------------------------------------
-- 10 HOMEPAGE.NR_TAGS_READERS
--------------------------------------------------------------------------
ALTER TABLE  HOMEPAGE.NR_TAGS_STORIES  DROP CONSTRAINT PK_TAGS_STORIES;
COMMIT;

-- Rename table
ALTER TABLE HOMEPAGE.NR_TAGS_STORIES RENAME TO NR_TAGS_READERS;
COMMIT; 
ALTER TABLE HOMEPAGE.NR_TAGS_READERS RENAME COLUMN FOLLOWED_STORY_ID TO CATEGORY_READER_ID;
COMMIT;

-- Adding two columns:
ALTER TABLE HOMEPAGE.NR_TAGS_READERS
	ADD ROLLUP_ENTRY_ID VARCHAR2(256)
	ADD USE_IN_ROLLUP NUMBER(5 ,0)
	ADD IS_NETWORK	NUMBER(5 ,0)
	ADD IS_FOLLOWER	NUMBER(5 ,0)
	ADD EVENT_TIME 	TIMESTAMP
	ADD IS_STORY_COMM NUMBER(5 ,0)
	ADD IS_BROADCAST NUMBER(5 ,0)
	ADD ORGANIZATION_ID VARCHAR2(256)
	ADD ACTOR_UUID VARCHAR2(256)
	ADD IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL
	ADD ROLLUP_AUTHOR_ID VARCHAR (256);
COMMIT;

-- set USE_IN_ROLLUP to be zero
UPDATE HOMEPAGE.NR_TAGS_READERS SET USE_IN_ROLLUP = 0;
COMMIT;

-- Set CATEGORY_READER_ID to be a DEFAULT ' ' NOT NULL 
ALTER TABLE HOMEPAGE.NR_TAGS_READERS MODIFY (CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ');
COMMIT;

-- Increase the exiting ITEM_ID to be 256
ALTER TABLE HOMEPAGE.NR_TAGS_READERS MODIFY (ITEM_ID VARCHAR2(256));
COMMIT;

-- PK
--REORG TABLE HOMEPAGE.NR_TAGS_READERS;
COMMIT;

ALTER TABLE HOMEPAGE.NR_TAGS_READERS 
    ADD (CONSTRAINT PK_TAGS_READERS PRIMARY KEY(CATEGORY_READER_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_TAGS_READERS
	ADD CONSTRAINT FK_TAG_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
--  [start indexes] NR_TAGS_READERS
CREATE  INDEX HOMEPAGE.TAGS_READERS_STR_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.TAGS_READERS_ITM_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.TAGS_READERS_CD_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.TAGS_READERS_SRC_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.TAGS_READERS_AUT_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.TAGS_READERS_RLL_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_TAGS_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.TAGS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_TAGS_READERS_RDR_IX ON HOMEPAGE.NR_TAGS_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.TAGS_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_TAGS_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_TAGS_READERS_RIR_IX
 	ON HOMEPAGE.NR_TAGS_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_TAGS_READERS
	
ALTER TABLE HOMEPAGE.NR_TAGS_READERS ENABLE ROW MOVEMENT;

--CREATE TABLE "HOMEPAGE"."NR_ACTIONABLE_READERS"
--------------------------------------------------------------------------
-- 11) HOMEPAGE.NR_STATUS_UPDATE_READERS
--------------------------------------------------------------------------	
CREATE TABLE HOMEPAGE.NR_STATUS_UPDATE_READERS (
	CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ' NOT NULL,
	READER_ID VARCHAR2(36) NOT NULL,
	CATEGORY_TYPE NUMBER(5,0) NOT NULL,
	SOURCE VARCHAR2(36) NOT NULL,
	CONTAINER_ID VARCHAR2(256),
	ITEM_ID VARCHAR2(256),
	ROLLUP_ENTRY_ID VARCHAR2(256),
	RESOURCE_TYPE NUMBER(5,0) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	STORY_ID VARCHAR2(36) NOT NULL,
	SOURCE_TYPE NUMBER(5,0),
	USE_IN_ROLLUP NUMBER(5,0),
	IS_NETWORK	NUMBER(5,0),
	IS_FOLLOWER	NUMBER(5,0),
	EVENT_TIME TIMESTAMP,
	IS_STORY_COMM NUMBER(5 ,0),
	IS_BROADCAST NUMBER(5,0),
	ORGANIZATION_ID VARCHAR2(256),
	ACTOR_UUID VARCHAR2(256),
	ROLLUP_AUTHOR_ID VARCHAR2 (256),
	IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL,	
   	CONSTRAINT  CK_CAT11_TYPE
    			CHECK
    			(CATEGORY_TYPE = 11)
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_STATUS_UPDATE_READERS 
    ADD (CONSTRAINT PK_SU_READERS PRIMARY KEY(CATEGORY_READER_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_STATUS_UPDATE_READERS
	ADD CONSTRAINT FK_STA_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
--  [start indexes] NR_STATUS_UPDATE_READERS
CREATE  INDEX HOMEPAGE.STATUS_READERS_STR_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.STATUS_READERS_ITM_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.STATUS_READERS_CD_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.STATUS_READERS_SRC_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.STATUS_READERS_AUT_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.STATUS_READERS_RLL_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_STATUS_UPDATE_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.STATUS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_STATUS_READERS_RDR_IX ON HOMEPAGE.NR_STATUS_UPDATE_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.STATUS_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_STATUS_READERS_RIR_IX
 	ON HOMEPAGE.NR_STATUS_UPDATE_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_STATUS_UPDATE_READERS

ALTER TABLE  HOMEPAGE.NR_STATUS_UPDATE_READERS ENABLE ROW MOVEMENT;

---------------------------------------------------------
-- 12) ADDING A NR_EXTERNAL_READERS READER TABLE
---------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_EXTERNAL_READERS (
	CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ' NOT NULL,
	READER_ID VARCHAR2(36) NOT NULL,
	CATEGORY_TYPE NUMBER(5,0) NOT NULL,
	SOURCE VARCHAR2(36) NOT NULL,
	CONTAINER_ID VARCHAR2(256),
	ITEM_ID VARCHAR2(256),
	ROLLUP_ENTRY_ID VARCHAR2(256),
	RESOURCE_TYPE NUMBER(5,0) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	STORY_ID VARCHAR2(36) NOT NULL,
	SOURCE_TYPE NUMBER(5,0),
	USE_IN_ROLLUP NUMBER(5,0),
	IS_NETWORK	NUMBER(5,0),
	IS_FOLLOWER	NUMBER(5,0),
	EVENT_TIME TIMESTAMP,
	IS_STORY_COMM NUMBER(5 ,0),
	IS_BROADCAST NUMBER(5,0),
	ORGANIZATION_ID VARCHAR2(256),
	ACTOR_UUID VARCHAR2(256),
	ROLLUP_AUTHOR_ID VARCHAR2 (256),
	IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL,	
   	CONSTRAINT  CK_CAT12_TYPE
    			CHECK
    			(CATEGORY_TYPE = 12)
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_EXTERNAL_READERS 
    ADD (CONSTRAINT PK_EXT_READERS PRIMARY KEY(CATEGORY_READER_ID)  USING INDEX TABLESPACE "NEWSINDEXTABSPACE");
   
ALTER TABLE HOMEPAGE.NR_EXTERNAL_READERS
	ADD CONSTRAINT FK_EXT_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
--  [start indexes] NR_EXTERNAL_READERS
CREATE  INDEX HOMEPAGE.EXTERNAL_READERS_STR_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.EXTERNAL_READERS_ITM_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.EXTERNAL_READERS_CD_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.EXTERNAL_READERS_SRC_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.EXTERNAL_READERS_AUT_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.EXTERNAL_READERS_RLL_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_EXTERNAL_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.EXTERNAL_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_EXTERNAL_READERS_RDR_IX ON HOMEPAGE.NR_EXTERNAL_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.EXTERNAL_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_EXTERNAL_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_EXTERNAL_READERS_RIR_IX
 	ON HOMEPAGE.NR_EXTERNAL_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_EXTERNAL_READERS

ALTER TABLE HOMEPAGE.NR_EXTERNAL_READERS  ENABLE ROW MOVEMENT;

---------------------------------------------------------
-- 13-16) ADDING NR_ACTIONABLE_READERS READER TABLE
---------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_ACTIONABLE_READERS (
	CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ' NOT NULL,
	READER_ID VARCHAR2(36) NOT NULL,
	CATEGORY_TYPE NUMBER(5,0) NOT NULL,
	SOURCE VARCHAR2(36) NOT NULL,
	CONTAINER_ID VARCHAR2(256),
	ITEM_ID VARCHAR2(256),
	ROLLUP_ENTRY_ID VARCHAR2(256),
	RESOURCE_TYPE NUMBER(5,0) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	STORY_ID VARCHAR2(36) NOT NULL,
	SOURCE_TYPE NUMBER(5,0),
	USE_IN_ROLLUP NUMBER(5,0),
	IS_NETWORK	NUMBER(5,0),
	IS_FOLLOWER	NUMBER(5,0),
	EVENT_TIME TIMESTAMP,
	NOTE_TEXT VARCHAR2(4000),
	NOTE_UPDATE_DATE TIMESTAMP,
	IS_STORY_COMM NUMBER(5 ,0),
	IS_BROADCAST NUMBER(5,0),
	ORGANIZATION_ID VARCHAR2(256),
	OPERATION_ID VARCHAR2(512),
	ACTOR_UUID VARCHAR2(256),
	ROLLUP_AUTHOR_ID VARCHAR2 (256),
	IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL,	
	CONSTRAINT   	CK_CAT_ACTION_TYPE
    				CHECK
    				(CATEGORY_TYPE >= 14 AND CATEGORY_TYPE <= 16)	
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS 
    ADD (CONSTRAINT PK_ACTION_READERS PRIMARY KEY (CATEGORY_READER_ID)  USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS
	ADD CONSTRAINT FK_ACT_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
--  [start indexes] NR_ACTIONABLE_READERS
CREATE  INDEX HOMEPAGE.ACTIONABLE_READERS_STR_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.ACTIONABLE_READERS_ITM_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.ACTIONABLE_READERS_CD_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.ACTIONABLE_READERS_SRC_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.ACTIONABLE_READERS_AUT_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.ACTIONABLE_READERS_RLL_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_ACTIONABLE_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.ACTIONABLE_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_ACTIONABLE_READERS_RDR_IX ON HOMEPAGE.NR_ACTIONABLE_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.ACTIONABLE_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_ACTIONABLE_READERS_RIR_IX
 	ON HOMEPAGE.NR_ACTIONABLE_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_ACTIONABLE_READERS
	
ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS ENABLE ROW MOVEMENT;

COMMIT;

DROP VIEW HOMEPAGE.NR_FOLLOWED_STORIES;

COMMIT;

--------------------------------------------------------------------------------------------------------------
-- CREATE THE VIEW FOR ALL THE STORIES
--------------------------------------------------------------------------------------------------------------
CREATE VIEW HOMEPAGE.NR_CATEGORIES_READERS AS (
    SELECT CATEGORY_READER_ID, ROLLUP_ENTRY_ID, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID FROM HOMEPAGE.NR_RESPONSES_READERS
        UNION ALL
    SELECT CATEGORY_READER_ID, ROLLUP_ENTRY_ID, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID FROM HOMEPAGE.NR_PROFILES_READERS
        UNION ALL
    SELECT CATEGORY_READER_ID, ROLLUP_ENTRY_ID, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID FROM HOMEPAGE.NR_COMMUNITIES_READERS
        UNION ALL
    SELECT CATEGORY_READER_ID, ROLLUP_ENTRY_ID, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID FROM HOMEPAGE.NR_ACTIVITIES_READERS
        UNION ALL
    SELECT CATEGORY_READER_ID, ROLLUP_ENTRY_ID, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID FROM HOMEPAGE.NR_BLOGS_READERS
        UNION ALL
    SELECT CATEGORY_READER_ID, ROLLUP_ENTRY_ID, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID FROM HOMEPAGE.NR_BOOKMARKS_READERS
        UNION ALL
    SELECT CATEGORY_READER_ID, ROLLUP_ENTRY_ID, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID FROM HOMEPAGE.NR_FILES_READERS
        UNION ALL
    SELECT CATEGORY_READER_ID, ROLLUP_ENTRY_ID, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID FROM HOMEPAGE.NR_FORUMS_READERS
        UNION ALL
    SELECT CATEGORY_READER_ID, ROLLUP_ENTRY_ID, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID FROM HOMEPAGE.NR_WIKIS_READERS
        UNION ALL
    SELECT CATEGORY_READER_ID, ROLLUP_ENTRY_ID, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID FROM HOMEPAGE.NR_TAGS_READERS
    	UNION ALL
    SELECT CATEGORY_READER_ID, ROLLUP_ENTRY_ID, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID FROM HOMEPAGE.NR_STATUS_UPDATE_READERS
    	UNION ALL
    SELECT CATEGORY_READER_ID, ROLLUP_ENTRY_ID, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID FROM HOMEPAGE.NR_EXTERNAL_READERS   	
);
COMMIT;

--------------------------------------------------------------------------
-- 17 HOMEPAGE.NR_DISCOVERY_VIEW
--------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_DISCOVERY_VIEW (
	CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ' NOT NULL,
	READER_ID VARCHAR2(36),
	CATEGORY_TYPE NUMBER(5,0) NOT NULL,
	SOURCE VARCHAR2(36) NOT NULL,
	CONTAINER_ID VARCHAR2(256),
	ITEM_ID VARCHAR2(256),
	ROLLUP_ENTRY_ID VARCHAR2(256),
	RESOURCE_TYPE NUMBER(5,0) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	STORY_ID VARCHAR2(36) NOT NULL,
	SOURCE_TYPE NUMBER(5,0),
	USE_IN_ROLLUP NUMBER(5,0),
	IS_NETWORK	NUMBER(5,0),
	IS_FOLLOWER	NUMBER(5,0),
	EVENT_TIME TIMESTAMP,
	IS_STORY_COMM NUMBER(5 ,0),
	IS_BROADCAST NUMBER(5,0),
	ORGANIZATION_ID VARCHAR2(256),
	ACTOR_UUID VARCHAR2(256),
	ROLLUP_AUTHOR_ID VARCHAR2 (256),
	IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL,	
	CONSTRAINT   CK_CAT17_TYPE
    			CHECK
    			(CATEGORY_TYPE = 17)
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_DISCOVERY_VIEW 
    ADD (CONSTRAINT PK_DISCOVERY_VIEW PRIMARY KEY(CATEGORY_READER_ID)   USING INDEX TABLESPACE "NEWSINDEXTABSPACE");
    
ALTER TABLE HOMEPAGE.NR_DISCOVERY_VIEW
	ADD CONSTRAINT FK_DIS_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_STR_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_ITM_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_CD_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_SRC_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_AUT_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_RLL_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_DISCOVERY_VIEW (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_DEL_SERV_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_DISCOVERY_VIEW_RDR_IX ON HOMEPAGE.NR_DISCOVERY_VIEW (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_ROLLUP_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_DISCOVERY_VIEW_RIR_IX
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_DISCOVERY_VIEW


ALTER TABLE HOMEPAGE.NR_DISCOVERY_VIEW ENABLE ROW MOVEMENT;


COMMIT;

-----------------------------------------------------
-- 18 NR_PROFILES_VIEW
-----------------------------------------------------
CREATE TABLE HOMEPAGE.NR_PROFILES_VIEW (
	CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ' NOT NULL,
	READER_ID VARCHAR2(36),
	CATEGORY_TYPE NUMBER(5,0) NOT NULL,
	SOURCE VARCHAR2(36) NOT NULL,
	CONTAINER_ID VARCHAR2(256),
	ITEM_ID VARCHAR2(256),
	ROLLUP_ENTRY_ID VARCHAR2(256),
	RESOURCE_TYPE NUMBER(5,0) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	STORY_ID VARCHAR2(36) NOT NULL,
	SOURCE_TYPE NUMBER(5,0),
	USE_IN_ROLLUP NUMBER(5,0),
	IS_NETWORK	NUMBER(5,0),
	IS_FOLLOWER	NUMBER(5,0),
	EVENT_TIME TIMESTAMP,
	IS_STORY_COMM NUMBER(5 ,0),
	IS_BROADCAST NUMBER(5 ,0),
	ORGANIZATION_ID VARCHAR2(256),
	ACTOR_UUID VARCHAR2(256),
	ROLLUP_AUTHOR_ID VARCHAR2 (256),
	IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL,	
	CONSTRAINT   	CK_CAT18_TYPE
    				CHECK
    				(CATEGORY_TYPE = 18)
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_PROFILES_VIEW 
    ADD (CONSTRAINT PK_PROFILES_VIEW PRIMARY KEY(CATEGORY_READER_ID)  USING INDEX TABLESPACE "NEWSINDEXTABSPACE");
    
ALTER TABLE HOMEPAGE.NR_PROFILES_VIEW
	ADD CONSTRAINT FK_PRO_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_PROFILES_VIEW
CREATE  INDEX HOMEPAGE.PROFILES_VIEW_STR_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.PROFILES_VIEW_ITM_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.PROFILES_VIEW_CD_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.PROFILES_VIEW_SRC_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.PROFILES_VIEW_AUT_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.PROFILES_VIEW_RLL_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_PROFILES_VIEW (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.PROFILES_VIEW_DEL_SERV_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_PROFILES_VIEW_RDR_IX ON HOMEPAGE.NR_PROFILES_VIEW (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.PROFILES_VIEW_ROLLUP_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_PROFILES_VIEW_RIR_IX
 	ON HOMEPAGE.NR_PROFILES_VIEW (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_PROFILES_VIEW

ALTER TABLE HOMEPAGE.NR_PROFILES_VIEW ENABLE ROW MOVEMENT;

COMMIT;

--------------------------------------------------------------------------
-- 19 HOMEPAGE.NR_NOTIFICATION_SENT_READERS
--------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_NOTIFICATION_SENT_READERS (
	CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ' NOT NULL,
	READER_ID VARCHAR2(36) NOT NULL,
	CATEGORY_TYPE NUMBER(5 ,0) NOT NULL,
	SOURCE VARCHAR2(36) NOT NULL,
	CONTAINER_ID VARCHAR2(256),
	ITEM_ID VARCHAR2(256),
	ROLLUP_ENTRY_ID VARCHAR2(256),
	RESOURCE_TYPE NUMBER(5 ,0) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	STORY_ID VARCHAR2(36) NOT NULL,
	SOURCE_TYPE NUMBER(5 ,0),
	USE_IN_ROLLUP NUMBER(5 ,0),
	IS_NETWORK	NUMBER(5 ,0),
	IS_FOLLOWER	NUMBER(5 ,0),
	EVENT_TIME 	TIMESTAMP,
	IS_STORY_COMM NUMBER(5 ,0),
	IS_BROADCAST NUMBER(5 ,0),
	ORGANIZATION_ID VARCHAR2(256),
	ACTOR_UUID VARCHAR2(256),
	ROLLUP_AUTHOR_ID VARCHAR2 (256),
	IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL,	
        CONSTRAINT   	CK_CAT19_TYPE
    			CHECK
    			(CATEGORY_TYPE = 19)
)	
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_NOTIFICATION_SENT_READERS 
    ADD (CONSTRAINT PK_SENT_NOT_READ PRIMARY KEY(CATEGORY_READER_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");
    
ALTER TABLE HOMEPAGE.NR_NOTIFICATION_SENT_READERS
	ADD CONSTRAINT FK_NOT_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);   

--  [start indexes] NR_NOTIFICATION_SENT_READERS
CREATE  INDEX HOMEPAGE.NOTIFICA_READERS_STR_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.NOTIFICA_READERS_ITM_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.NOTIFICA_READERS_CD_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.NOTIFICA_READERS_SRC_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.NOTIFICA_READERS_AUT_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.NOTIFICA_READERS_RLL_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_NOTIFICATION_SENT_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.NOTIFICA_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_NOTIFICA_READERS_RDR_IX ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.NOTIFICA_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (ROLLUP_ENTRY_ID, READER_ID)  TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_NOTIFICA_READERS_RIR_IX
 	ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_NOTIFICATION_SENT_READERS
    
ALTER TABLE HOMEPAGE.NR_NOTIFICATION_SENT_READERS ENABLE ROW MOVEMENT;	
    
COMMIT;

--------------------------------------------------------------------------
-- 20 HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS
--------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (
	CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ' NOT NULL,
	READER_ID VARCHAR2(36) NOT NULL,
	CATEGORY_TYPE NUMBER(5 ,0) NOT NULL,
	SOURCE VARCHAR2(36) NOT NULL,
	CONTAINER_ID VARCHAR2(256),
	ITEM_ID VARCHAR2(256),
	ROLLUP_ENTRY_ID VARCHAR2(256),
	RESOURCE_TYPE NUMBER(5 ,0) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	STORY_ID VARCHAR2(36) NOT NULL,
	SOURCE_TYPE NUMBER(5 ,0),
	USE_IN_ROLLUP NUMBER(5 ,0),
	IS_NETWORK	NUMBER(5 ,0),
	IS_FOLLOWER	NUMBER(5 ,0),
	EVENT_TIME 	TIMESTAMP,
	IS_STORY_COMM NUMBER(5 ,0),
	IS_BROADCAST NUMBER(5 ,0),
	ORGANIZATION_ID VARCHAR2(256),
	ACTOR_UUID VARCHAR2(256),
	ROLLUP_AUTHOR_ID VARCHAR2 (256),
	IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL,	
        CONSTRAINT   	CK_CAT20_TYPE
    			CHECK
    			(CATEGORY_TYPE = 20)
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS 
    ADD (CONSTRAINT PK_REC_NOT_READ PRIMARY KEY(CATEGORY_READER_ID)  USING INDEX TABLESPACE "NEWSINDEXTABSPACE");
    
ALTER TABLE HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS
	ADD CONSTRAINT FK_REC_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_NOTIFICATION_RECEIV_READERS
CREATE  INDEX HOMEPAGE.NOT_REC_READERS_STR_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.NOT_REC_READERS_ITM_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.NOT_REC_READERS_CD_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.NOT_REC_READERS_SRC_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.NOT_REC_READERS_AUT_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.NOT_REC_READERS_RLL_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_NOTIFICATI_RECEIV_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.NOT_REC_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_NOT_REC_READERS_RDR_IX ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.NOT_REC_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (ROLLUP_ENTRY_ID, READER_ID)  TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_NOT_REC_READERS_RIR_IX
 	ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_NOTIFICATION_RECEIV_READERS

ALTER TABLE HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS ENABLE ROW MOVEMENT;

--------------------------------------------------------------------------
-- 13 HOMEPAGE.NR_SAVED_READERS
--------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SAVED_READERS (
	CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ' NOT NULL,
	READER_ID VARCHAR2(36) NOT NULL,
	CATEGORY_TYPE NUMBER(5,0) NOT NULL,
	SOURCE VARCHAR2(36) NOT NULL,
	CONTAINER_ID VARCHAR2(256),
	ITEM_ID VARCHAR2(256),
	ROLLUP_ENTRY_ID VARCHAR2(256),
	RESOURCE_TYPE NUMBER(5,0) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	STORY_ID VARCHAR2(36) NOT NULL,
	SOURCE_TYPE NUMBER(5,0),
	USE_IN_ROLLUP NUMBER(5,0),
	IS_NETWORK	NUMBER(5,0),
	IS_FOLLOWER	NUMBER(5,0),
	EVENT_TIME TIMESTAMP,
	NOTE_TEXT VARCHAR2(4000),
	NOTE_UPDATE_DATE TIMESTAMP,
	IS_STORY_COMM NUMBER(5 ,0),
	IS_BROADCAST NUMBER(5,0),
	ORGANIZATION_ID VARCHAR2(256),
	OPERATION_ID VARCHAR2(512),
	ACTOR_UUID VARCHAR2(256),
	ROLLUP_AUTHOR_ID VARCHAR2 (256),
	IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL,	
	CONSTRAINT   	CK_CAT_SAVED_TYPE
    				CHECK
    				(CATEGORY_TYPE = 13)	
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_SAVED_READERS 
    ADD (CONSTRAINT PK_SAVED_READERS PRIMARY KEY (CATEGORY_READER_ID)  USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_SAVED_READERS
	ADD CONSTRAINT FK_SAV_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
--  [start indexes] NR_SAVED_READERS
CREATE  INDEX HOMEPAGE.SAVED_READERS_STR_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.SAVED_READERS_ITM_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.SAVED_READERS_CD_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.SAVED_READERS_SRC_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.SAVED_READERS_AUT_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.SAVED_READERS_RLL_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_SAVED_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.SAVED_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_SAVED_READERS_RDR_IX ON HOMEPAGE.NR_SAVED_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.SAVED_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_SAVED_READERS_RIR_IX
 	ON HOMEPAGE.NR_SAVED_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_SAVED_READERS

ALTER TABLE HOMEPAGE.NR_SAVED_READERS ENABLE ROW MOVEMENT;

COMMIT;

--------------------------------------------------------------------------
-- 21 HOMEPAGE.NR_TOPICS_READERS
--------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_TOPICS_READERS (
	CATEGORY_READER_ID VARCHAR2(36)  DEFAULT ' ' NOT NULL,
	READER_ID VARCHAR2(36) NOT NULL,
	CATEGORY_TYPE NUMBER(5 ,0) NOT NULL,
	SOURCE VARCHAR2(36) NOT NULL,
	CONTAINER_ID VARCHAR2(256),
	ITEM_ID VARCHAR2(256),
	ROLLUP_ENTRY_ID VARCHAR2(256),
	RESOURCE_TYPE NUMBER(5 ,0) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	STORY_ID VARCHAR2(36) NOT NULL,
	SOURCE_TYPE NUMBER(5 ,0),
	USE_IN_ROLLUP NUMBER(5 ,0),
	IS_NETWORK	NUMBER(5 ,0),
	IS_FOLLOWER	NUMBER(5 ,0),
	EVENT_TIME 	TIMESTAMP,
	IS_STORY_COMM NUMBER(5 ,0),
	IS_BROADCAST NUMBER(5 ,0),
	ORGANIZATION_ID VARCHAR2(256),
	ACTOR_UUID VARCHAR2(256),
	ROLLUP_AUTHOR_ID VARCHAR2(256),
	IS_VISIBLE SMALLINT DEFAULT 1 NOT NULL,
	CONSTRAINT   	CK_CAT_TOPICS_TYPE
    				CHECK
    				(CATEGORY_TYPE = 21)	
)	
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_TOPICS_READERS 
    ADD (CONSTRAINT PK_TOPICS_READERS PRIMARY KEY (CATEGORY_READER_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");
    
ALTER TABLE HOMEPAGE.NR_TOPICS_READERS
	ADD CONSTRAINT FK_TOP_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);    

--  [start indexes] NR_TOPICS_READERS
CREATE  INDEX HOMEPAGE.TOPICS_READERS_STR_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (STORY_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.TOPICS_READERS_ITM_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (ITEM_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.TOPICS_READERS_CD_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (STORY_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.TOPICS_READERS_SRC_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.TOPICS_READERS_AUT_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (READER_ID, CREATION_DATE DESC, ROLLUP_AUTHOR_ID, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.TOPICS_READERS_RLL_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (USE_IN_ROLLUP, READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  CLUSTER HOMEPAGE.NR_TOPICS_READERS (READER_ID VARCHAR2(36), STORY_ID VARCHAR2(36)) INDEX TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.TOPICS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX 
 	 HOMEPAGE.NR_TOPICS_READERS_RDR_IX ON HOMEPAGE.NR_TOPICS_READERS (READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT; 

CREATE  INDEX HOMEPAGE.TOPICS_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_TOPICS_READERS (ROLLUP_ENTRY_ID, READER_ID) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE INDEX HOMEPAGE.NR_TOPICS_READERS_RIR_IX
 	ON HOMEPAGE.NR_TOPICS_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

--  [end indexes] NR_TOPICS_READERS

ALTER TABLE "HOMEPAGE"."NR_TOPICS_READERS" ENABLE ROW MOVEMENT;


----------------------------------------------------------------------
----------------------------------------------------------------------
----------------------------------------------------------------------


--------------------------------------------------
-- Add table: "HOMEPAGE"."NR_ATTACHMENT"
--------------------------------------------------
----------------------------------------------------------------
-- ADDING NR_ATTACHMENT - CAN RELATE TO ANY STORIES TABLE
----------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_ATTACHMENT (
	ATTACHMENT_ID VARCHAR2(36) NOT NULL,
	ENTRY_ID VARCHAR2(36) NOT NULL, -- 47
	SOURCE_TYPE NUMBER(5,0) NOT NULL,
	ATTACHMENT_TYPE NUMBER(5,0),
	CREATION_DATE TIMESTAMP,
	NAME VARCHAR2(2048),
	DESCRIPTION VARCHAR2(4000),	
	TARGET_ID  VARCHAR2(256),
	TARGET_URL VARCHAR2(2048),
	META_DATA VARCHAR2(4000)
)
TABLESPACE "NEWSREGTABSPACE"; 

ALTER TABLE HOMEPAGE.NR_ATTACHMENT 
    ADD (CONSTRAINT PK_ATTACHMENT PRIMARY KEY(ATTACHMENT_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

CREATE INDEX HOMEPAGE.NR_ATT_ENTRY_ID
    ON HOMEPAGE.NR_ATTACHMENT (ENTRY_ID) TABLESPACE "NEWSINDEXTABSPACE";    

COMMIT;

ALTER TABLE HOMEPAGE.NR_ATTACHMENT ENABLE ROW MOVEMENT;   

--------------------------------------------------
-- Add table: "HOMEPAGE"."NR_RECOMMENDATION"
--------------------------------------------------
----------------------------------------------------------------
-- 5) NR_RECOMMENDATION - CAN RELATE TO ANY ENTRY TABLE
----------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_RECOMMENDATION  (
	RECOMMENDATION_ID VARCHAR2(36) NOT NULL, --primary key
	RECOMMENDER_ID VARCHAR2(36) NOT NULL, -- PERSON_ID of the recommender, FK to PERSON table
	ENTRY_ID VARCHAR2(36) NOT NULL, 
	SOURCE_TYPE NUMBER(5,0) NOT NULL, 
	CREATION_DATE TIMESTAMP
)
TABLESPACE "NEWSREGTABSPACE"; 

ALTER TABLE HOMEPAGE.NR_RECOMMENDATION 
    ADD (CONSTRAINT PK_RECOMMENDATION PRIMARY KEY(RECOMMENDATION_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_RECOMMENDATION
  	ADD CONSTRAINT FK_RECOMMENDER_ID FOREIGN KEY (RECOMMENDER_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);
    
CREATE INDEX HOMEPAGE.NR_REC_ENTRY_ID
    ON HOMEPAGE.NR_RECOMMENDATION (ENTRY_ID) TABLESPACE "NEWSINDEXTABSPACE";

CREATE INDEX HOMEPAGE.NR_RECCOMANDER_ID
    ON HOMEPAGE.NR_RECOMMENDATION (RECOMMENDER_ID) TABLESPACE "NEWSINDEXTABSPACE";
    
CREATE UNIQUE INDEX HOMEPAGE.NR_RECOMMENDER_ENTRY_ID
    ON HOMEPAGE.NR_RECOMMENDATION (RECOMMENDER_ID, ENTRY_ID) TABLESPACE "NEWSINDEXTABSPACE";

COMMIT;

ALTER TABLE HOMEPAGE.NR_RECOMMENDATION ENABLE ROW MOVEMENT;

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
	GSP_ID			VARCHAR2(36) NOT NULL,
	GSP_NAME		VARCHAR2(36) NOT NULL,
	GSP_VALUE		VARCHAR2(36) NOT NULL,
	GSP_TYPE        NUMBER(5,0) NOT NULL
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.SR_GLOBAL_SAND_PROPS
	 ADD (CONSTRAINT "PK_GSP_ID" PRIMARY KEY ("GSP_ID")
	 USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");

ALTER TABLE HOMEPAGE.SR_GLOBAL_SAND_PROPS
    ADD CONSTRAINT UNIQUE_GSP_NAME UNIQUE ("GSP_NAME");

ALTER TABLE HOMEPAGE.SR_GLOBAL_SAND_PROPS		
	ADD CONSTRAINT GSP_TYPE_CHECK
	CHECK (GSP_TYPE >=0 AND GSP_TYPE < 4);

ALTER TABLE "HOMEPAGE"."SR_GLOBAL_SAND_PROPS" ENABLE ROW MOVEMENT;



--reorg table HOMEPAGE.SR_GLOBAL_SAND_PROPS use TEMPSPACE1;
--reorg indexes all for table HOMEPAGE.SR_GLOBAL_SAND_PROPS;

--RUNSTATS ON TABLE "HOMEPAGE"."SR_RESUME_TOKENS";
--RUNSTATS ON TABLE "HOMEPAGE"."SR_RESUME_TOKENS" FOR INDEXES ALL;
	
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

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSTASK;
COMMIT;

ALTER TABLE HOMEPAGE.LOTUSCONNECTIONSTASK ADD CONSTRAINT UNIQUE_LCT_NAME UNIQUE ("NAME");

--END 40252: SPR#WDWU8AAA3P : search cluster nodes will insert dup scheduler task when scheduler table is empty


---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start SEARCH FIXUP 91
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
---------------------------------------------------------------------------------
------------------------ START SEARCH -------------------------------------------
---------------------------------------------------------------------------------

--START 36202: Modify the indexing and text extraction processes in accordance with designs in Search CDD
DROP TABLE HOMEPAGE.SR_FILESCONTENT;
COMMIT;

CREATE TABLE HOMEPAGE.SR_FILESCONTENT (
	FILESCONTENT_ID VARCHAR2(36) NOT NULL,
	URL VARCHAR2(256) NOT NULL,
	COMPONENT_UUID VARCHAR2(36) NOT NULL,
	COMPONENT VARCHAR2(36) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	LAST_MODIFIED_DATE TIMESTAMP NOT NULL,
	LAST_ACCESSED_DATE TIMESTAMP NOT NULL,
	IS_CURRENT NUMBER(5,0) NOT NULL,
	FILE_SIZE NUMBER(19 ,0) NOT NULL,
	CONTENT_SIZE NUMBER(19 ,0) NOT NULL,
	INPUT_MIME_TYPE VARCHAR2(256) NOT NULL,
	CLAIMED NUMBER(5,0) DEFAULT 0 NOT NULL,
	CLAIMED_TIME TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,	
	FS_LOCAL_PATH VARCHAR2(256) NOT NULL,
	PROCESSOR VARCHAR2(36),
 	PROCESSOR_STATE BLOB,
 	CONTENT_LOCATION VARCHAR2(256) NOT NULL,
	IS_READY NUMBER(5,0) DEFAULT 0 NOT NULL
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD (CONSTRAINT "PK_FILESCONTENT_ID" PRIMARY KEY ("FILESCONTENT_ID")
	USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");	

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD CONSTRAINT UNIQUE_COMP_UUID UNIQUE ("COMPONENT_UUID","COMPONENT");

CREATE INDEX "HOMEPAGE"."SR_FILESCONTENT_IS_CURRENT_IDX" 
	ON HOMEPAGE.SR_FILESCONTENT(IS_CURRENT) TABLESPACE "HOMEPAGEINDEXTABSPACE";    

ALTER TABLE "HOMEPAGE"."SR_FILESCONTENT" ENABLE ROW MOVEMENT;

--RUNSTATS ON TABLE "HOMEPAGE"."SR_FILESCONTENT";
--RUNSTATS ON TABLE "HOMEPAGE"."SR_FILESCONTENT" FOR INDEXES ALL;


--reorg table HOMEPAGE.SR_FILESCONTENT use TEMPSPACE1;
--reorg indexes all for table HOMEPAGE.SR_FILESCONTENT;

--RUNSTATS ON TABLE "HOMEPAGE"."SR_FILESCONTENT";
--RUNSTATS ON TABLE "HOMEPAGE"."SR_FILESCONTENT" FOR INDEXES ALL;
	
--END 36202: Modify the indexing and text extraction processes in accordance with designs in Search CDD  


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
DROP TABLE HOMEPAGE.SR_FACET_DOCS;
COMMIT;

DROP TABLE HOMEPAGE.SR_INDEX_DOCS;
COMMIT;

CREATE TABLE HOMEPAGE.SR_INDEX_DOCS(
	DOCUMENT_ID VARCHAR2(36) NOT NULL,
	DOCUMENT BLOB NOT NULL,
	CRAWLING_VERSION NUMBER(19, 0) NOT NULL,
	ACTION NUMBER(5, 0) NOT NULL,
	UPDATE_TIME  TIMESTAMP NOT NULL,
	RESUME_POINT VARCHAR2(256),
	SERVICE VARCHAR2(36) NOT NULL,
	FILES_REF_ID VARCHAR(36),
	ATOM_ID  VARCHAR2(256)
)
TABLESPACE "HOMEPAGEREGTABSPACE";


ALTER TABLE HOMEPAGE.SR_INDEX_DOCS
	ADD (CONSTRAINT "PK_INDEX_DOCS_ID" PRIMARY KEY ("DOCUMENT_ID")
	USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");	

CREATE INDEX "HOMEPAGE"."SR_INDEX_CRAWL_VERSION_IDX"
	ON HOMEPAGE.SR_INDEX_DOCS (CRAWLING_VERSION)  TABLESPACE "HOMEPAGEINDEXTABSPACE";

CREATE INDEX HOMEPAGE.SR_INDEX_DOCS_RPS_IDX
    ON HOMEPAGE.SR_INDEX_DOCS (RESUME_POINT,SERVICE) TABLESPACE "HOMEPAGEINDEXTABSPACE";	   
	
CREATE INDEX HOMEPAGE.SR_INDEX_DOCS_ACT_IDX
	ON HOMEPAGE.SR_INDEX_DOCS(ACTION) TABLESPACE "HOMEPAGEINDEXTABSPACE";
	
CREATE INDEX HOMEPAGE.SR_INDEX_DOCS_ACS_IDX
	ON HOMEPAGE.SR_INDEX_DOCS(SERVICE,ATOM_ID,CRAWLING_VERSION) TABLESPACE "HOMEPAGEINDEXTABSPACE";
	
ALTER TABLE HOMEPAGE.SR_INDEX_DOCS 
	ADD CONSTRAINT "ID_ACT_CHECK"
	CHECK (ACTION >=0 AND ACTION < 4);	
	
ALTER TABLE HOMEPAGE.SR_INDEX_DOCS 
	ADD CONSTRAINT "IGNORE_ACT_CHECK"
	CHECK (ACTION <> 3 OR RESUME_POINT IS NULL); 

--40269: PERF, homepage db,  this read sql needs index on sr_index_docs
CREATE INDEX HOMEPAGE.SR_INDEX_DOCS_LLT4_IDX ON HOMEPAGE.SR_INDEX_DOCS(UPDATE_TIME ASC,ACTION DESC,CRAWLING_VERSION,SERVICE) TABLESPACE "HOMEPAGEINDEXTABSPACE";

ALTER TABLE "HOMEPAGE"."SR_INDEX_DOCS" ENABLE ROW MOVEMENT;

--RUNSTATS ON TABLE "HOMEPAGE"."SR_INDEX_DOCS";
--RUNSTATS ON TABLE "HOMEPAGE"."SR_INDEX_DOCS" FOR INDEXES ALL;
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

CREATE INDEX HOMEPAGE.SR_INDEX_DOCS_FRID_IDX 
	ON  HOMEPAGE.SR_INDEX_DOCS(FILES_REF_ID ASC, SERVICE ASC, UPDATE_TIME DESC) TABLESPACE "HOMEPAGEINDEXTABSPACE";
COMMIT;


--RUNSTATS ON TABLE "HOMEPAGE"."SR_INDEX_DOCS";
--RUNSTATS ON TABLE "HOMEPAGE"."SR_INDEX_DOCS" FOR INDEXES ALL;
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

CREATE INDEX "HOMEPAGE"."SR_FEEDBACK_PERSON_IDX" 
		ON  HOMEPAGE.SR_FEEDBACK(PERSON_ID) TABLESPACE "HOMEPAGEINDEXTABSPACE";
COMMIT;


DELETE FROM HOMEPAGE.SR_INDEX_MANAGEMENT;
COMMIT;

ALTER TABLE HOMEPAGE.SR_INDEX_MANAGEMENT
ADD OUT_OF_SYNC NUMBER(5 ,0) DEFAULT 0 NOT NULL;
COMMIT;

--reorg table HOMEPAGE.SR_FILESCONTENT use TEMPSPACE1;
--reorg indexes all for table HOMEPAGE.SR_FILESCONTENT;

--reorg table HOMEPAGE.SR_INDEX_MANAGEMENT use TEMPSPACE1;
--reorg indexes all for table HOMEPAGE.SR_INDEX_MANAGEMENT;

--RUNSTATS ON TABLE "HOMEPAGE"."SR_FILESCONTENT";
--RUNSTATS ON TABLE "HOMEPAGE"."SR_FILESCONTENT" FOR INDEXES ALL;

--RUNSTATS ON TABLE "HOMEPAGE"."SR_INDEX_MANAGEMENT";
--RUNSTATS ON TABLE "HOMEPAGE"."SR_INDEX_MANAGEMENT" FOR INDEXES ALL;

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
	BOARD_ID VARCHAR2(36) NOT NULL,
	BOARD_CONTAINER_ID VARCHAR2(36) NOT NULL,
	BOARD_TYPE VARCHAR2(64) NOT NULL,
	BOARD_OWNER_ASSOC_ID VARCHAR2(36) NOT NULL,
	BOARD_OWNER_ASSOC_TYPE VARCHAR2(64) NOT NULL,
	CREATED TIMESTAMP NOT NULL,
	CREATED_BY VARCHAR2(36) NOT NULL,
	LASTUPDATE TIMESTAMP NOT NULL,
	LASTUPDATE_BY VARCHAR2(36) NOT NULL,
	IS_ENABLED NUMBER(5, 0) DEFAULT 1 NOT NULL,
	VISIBILITY VARCHAR2(36),
	EDITABILITY VARCHAR2(36),
	WIDGET_ID VARCHAR2(36)			
)
TABLESPACE "BOARDREGTABSPACE";

ALTER TABLE HOMEPAGE.BOARD 
	ADD (CONSTRAINT BOARD_PK PRIMARY KEY (BOARD_ID) USING INDEX TABLESPACE "BOARDINDEXTABSPACE");

ALTER TABLE HOMEPAGE.BOARD 
	ADD CONSTRAINT FK_BRD_OWNER FOREIGN KEY (BOARD_OWNER_ASSOC_ID) 
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

ALTER TABLE HOMEPAGE.BOARD 
	ADD CONSTRAINT FK_BRD_CREATED FOREIGN KEY (CREATED_BY) 
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);	

ALTER TABLE HOMEPAGE.BOARD 
	ADD CONSTRAINT FK_BRD_LASTUPDATE FOREIGN KEY (LASTUPDATE_BY) 
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

CREATE UNIQUE INDEX HOMEPAGE.BOARD_OWNER_ASSOC_UIDX 
	ON HOMEPAGE.BOARD (BOARD_OWNER_ASSOC_ID ASC, BOARD_TYPE ASC) TABLESPACE "BOARDINDEXTABSPACE";

ALTER TABLE HOMEPAGE.BOARD
	ADD CONSTRAINT CONTAINER_ID_UNQ UNIQUE (BOARD_CONTAINER_ID);	
	
--CREATE UNIQUE INDEX HOMEPAGE.BRD_CONTAINER_ID_UIDX 
--	ON HOMEPAGE.BOARD (BOARD_CONTAINER_ID)  TABLESPACE "BOARDINDEXTABSPACE";

COMMIT;

ALTER TABLE HOMEPAGE.BOARD ENABLE ROW MOVEMENT;
	
--------------------------------------
-- HOMEPAGE.BOARD_ENTRIES to store the relationship between a reader and a status update
--------------------------------------
CREATE TABLE HOMEPAGE.BOARD_ENTRIES  (
	ENTRY_ID VARCHAR2(47) NOT NULL, -- the format will include in the pk also the creation time
	ENTRY_TYPE NUMBER(5,0),
	CATEGORY_TYPE NUMBER(5,0),
	SOURCE VARCHAR2(36),
	SOURCE_TYPE NUMBER(5,0),
	ITEM_ID VARCHAR(36) NOT NULL,
	ITEM_URL VARCHAR(2048) NOT NULL,
	CONTAINER_ID VARCHAR2(256),
	CREATION_DATE TIMESTAMP NOT NULL, -- used also by the seedlist, when something is created the update is equal to is created
	UPDATE_DATE TIMESTAMP NOT NULL, -- this field is used and queried from the the seedlist for initial and incremental index
	ACTOR_UUID VARCHAR2(36),
	IS_COMMUNITY_STORY NUMBER(5,0) DEFAULT 0,
	TARGET_SUBJECT_ID VARCHAR2(256),
	TAGS VARCHAR2(1024),
	SL_IS_UPDATED NUMBER(5,0) DEFAULT 0 NOT NULL, -- this field is used by the seedlist
	SL_IS_DELETED NUMBER(5,0) DEFAULT 0 NOT NULL, -- this field is used by the seedlist
	SL_UPDATE_DATE TIMESTAMP NOT NULL, -- this field is used by the seedlist
	CONTENT BLOB NOT NULL,
	LAST_CONTRIBUTOR_ID VARCHAR2(36),
	CONTENT_LOCALE  VARCHAR2(5)
)
LOB (CONTENT) STORE AS (TABLESPACE BOARDLOBTABSPACE 
STORAGE (INITIAL 1M)
CHUNK 4000
NOCACHE NOLOGGING)
TABLESPACE "BOARDREGTABSPACE";

ALTER TABLE HOMEPAGE.BOARD_ENTRIES 
    ADD (CONSTRAINT PK_BRD_ENTRIES PRIMARY KEY(ENTRY_ID) USING INDEX TABLESPACE "BOARDINDEXTABSPACE");

ALTER TABLE HOMEPAGE.BOARD_ENTRIES
	ADD CONSTRAINT FK_CONTAINER_ID FOREIGN KEY (CONTAINER_ID) 
	REFERENCES HOMEPAGE.BOARD (BOARD_CONTAINER_ID);    

CREATE INDEX HOMEPAGE.NEWS_BRD_SL_UPDATE
    ON HOMEPAGE.BOARD_ENTRIES (SL_UPDATE_DATE ASC) TABLESPACE "BOARDINDEXTABSPACE";

CREATE UNIQUE INDEX HOMEPAGE.NEWS_BRD_ITEM
    ON HOMEPAGE.BOARD_ENTRIES (ITEM_ID) TABLESPACE "BOARDINDEXTABSPACE";

CREATE INDEX HOMEPAGE.CREATION_ITEM_IDX 
	ON HOMEPAGE.BOARD_ENTRIES (CREATION_DATE DESC, ITEM_ID DESC) TABLESPACE "BOARDINDEXTABSPACE";    

CREATE INDEX HOMEPAGE.BRD_E_CONTAINER_ID_UIDX 
	ON HOMEPAGE.BOARD_ENTRIES (CONTAINER_ID) TABLESPACE "BOARDINDEXTABSPACE";
	
CREATE INDEX HOMEPAGE.BRD_SL_UPDATED_DEL 
	ON HOMEPAGE.BOARD_ENTRIES (SL_UPDATE_DATE ASC, SL_IS_DELETED) TABLESPACE "BOARDINDEXTABSPACE";	
	
CREATE INDEX HOMEPAGE.BRD_ENTRIES_ITEM
	ON HOMEPAGE.BOARD_ENTRIES (ITEM_ID, SL_IS_DELETED) TABLESPACE "BOARDINDEXTABSPACE";		
	
COMMIT;

ALTER TABLE HOMEPAGE.BOARD_ENTRIES ENABLE ROW MOVEMENT;

--------------------------------------
-- HOMEPAGE.BOARD_COMMENTS to store the comments
--------------------------------------
CREATE TABLE HOMEPAGE.BOARD_COMMENTS  (
	COMMENT_ID VARCHAR2(47) NOT NULL, -- the format will include in the pk also the creation time
	ACTOR_UUID VARCHAR2(36),
	CREATION_DATE TIMESTAMP NOT NULL,
	ITEM_ID VARCHAR2(47),
	ENTRY_ID VARCHAR2(47),
	ITEM_URL VARCHAR2(2048),	
	CONTENT BLOB,
	UPDATE_DATE TIMESTAMP,
	LAST_CONTRIBUTOR_ID VARCHAR2(36),
	CONTENT_LOCALE VARCHAR2(5),
	PUBLISHED_ORDER NUMBER(5 ,0),
	TAGS VARCHAR2(1024)
)	
LOB (CONTENT) STORE AS (TABLESPACE BOARDLOBTABSPACE 
STORAGE (INITIAL 1M)
CHUNK 4000
NOCACHE NOLOGGING)
TABLESPACE "BOARDREGTABSPACE";

ALTER TABLE HOMEPAGE.BOARD_COMMENTS 
    ADD (CONSTRAINT PK_BRD_COMMENT_ID PRIMARY KEY(COMMENT_ID) USING INDEX TABLESPACE "BOARDINDEXTABSPACE");

ALTER TABLE HOMEPAGE.BOARD_COMMENTS
	ADD CONSTRAINT FK_BRD_COM_ENTRY FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.BOARD_ENTRIES (ENTRY_ID);

CREATE INDEX HOMEPAGE.NEWS_BRD_ITEM_ID
    ON HOMEPAGE.BOARD_COMMENTS  (ITEM_ID) TABLESPACE "BOARDINDEXTABSPACE";

CREATE INDEX HOMEPAGE.NEWS_BRD_ITEM_CORR_ID
    ON HOMEPAGE.BOARD_COMMENTS  (ENTRY_ID) TABLESPACE "BOARDINDEXTABSPACE";

CREATE INDEX HOMEPAGE.CREATION_DATE_IDX 
	ON HOMEPAGE.BOARD_COMMENTS (CREATION_DATE ASC) TABLESPACE "BOARDINDEXTABSPACE";
	
CREATE INDEX HOMEPAGE.ITEM_CORR_CREATION_IDX 
	ON HOMEPAGE.BOARD_COMMENTS (ENTRY_ID, CREATION_DATE ASC) TABLESPACE "BOARDINDEXTABSPACE";
	
CREATE INDEX HOMEPAGE.ITEM_ITEM_CORR_IDX
	ON HOMEPAGE.BOARD_COMMENTS (ITEM_ID ASC, ENTRY_ID ASC) TABLESPACE "BOARDINDEXTABSPACE";	

CREATE UNIQUE INDEX HOMEPAGE.BRD_COMM_ITEM_COR_PUB
    ON HOMEPAGE.BOARD_COMMENTS (ENTRY_ID, PUBLISHED_ORDER ASC) TABLESPACE "BOARDINDEXTABSPACE";
    
ALTER TABLE HOMEPAGE.BOARD_COMMENTS ENABLE ROW MOVEMENT;

----------------------------------------------------------------
-- ADDING BOARD_OBJECT_REFERENCE
----------------------------------------------------------------
CREATE TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE (
	OBJECT_ID VARCHAR2(47) NOT NULL,
	ENTRY_ID VARCHAR2(47) NOT NULL,
	DISPLAY_NAME VARCHAR2(2048),
	IMAGE_NAME   VARCHAR2(2048),
	NAME VARCHAR2(512), 
	URL VARCHAR2(1024), 
	CONTENT VARCHAR2(4000),
	IMAGE_URL VARCHAR2(1024),
	SOURCE  VARCHAR2(36),
	SOURCE_TYPE  NUMBER(5 ,0),
	CREATION_DATE TIMESTAMP,
	MIME_TYPE VARCHAR2(36),
	AUTHOR_ID VARCHAR2(36),
	AUTHOR_DISPLAY_NAME VARCHAR2(256),
	OBJECT_META_DATA VARCHAR2(4000),
	OBJECT_META_DATA_2 VARCHAR2(4000),
	FULL_OBJECT_META_DATA CLOB,
	OBJECT_EXTERNAL_ID VARCHAR2(256)
)
LOB (FULL_OBJECT_META_DATA) STORE AS (TABLESPACE NEWSLOBTABSPACE STORAGE (INITIAL 1M) CHUNK 4000 NOCACHE NOLOGGING)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE 
    ADD (CONSTRAINT PK_BRD_OBJ_ID PRIMARY KEY(OBJECT_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE
	ADD CONSTRAINT FK_BRD_OBJ_ENTRY FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.BOARD_ENTRIES (ENTRY_ID);

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE
	ADD CONSTRAINT FK_BRD_AUTHOR_ID FOREIGN KEY (AUTHOR_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

CREATE INDEX HOMEPAGE.BRD_ENTRY_IDX
    ON HOMEPAGE.BOARD_OBJECT_REFERENCE (ENTRY_ID) TABLESPACE NEWSINDEXTABSPACE;

CREATE INDEX HOMEPAGE.BRD_AUTHOR_IDX
    ON HOMEPAGE.BOARD_OBJECT_REFERENCE (AUTHOR_ID) TABLESPACE NEWSINDEXTABSPACE;
	
COMMIT;

ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE ENABLE ROW MOVEMENT;

--------------------------------------
-- HOMEPAGE.BOARD_RECOMMANDATIONS to store the relationship between a board entries and something which has been recommended
---------------------------------------
CREATE TABLE HOMEPAGE.BOARD_RECOMMENDATIONS  (
	RECOMMENDATION_ID VARCHAR2(47) NOT NULL, --primary key
	RECOMMENDER_ID VARCHAR2(36) NOT NULL, -- PERSON_ID of the recommender, FK to PERSON table
	ENTRY_ID VARCHAR2(47) NOT NULL,
	SOURCE_TYPE NUMBER(5,0) NOT NULL, 
	CREATION_DATE TIMESTAMP
)
TABLESPACE "BOARDREGTABSPACE";

ALTER TABLE HOMEPAGE.BOARD_RECOMMENDATIONS 
    ADD (CONSTRAINT PK_BRD_RECOMM_ID PRIMARY KEY(RECOMMENDATION_ID) USING INDEX TABLESPACE "BOARDINDEXTABSPACE");
    
ALTER TABLE HOMEPAGE.BOARD_RECOMMENDATIONS
	ADD CONSTRAINT FK_BRD_RECOMMENDER FOREIGN KEY (RECOMMENDER_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

ALTER TABLE HOMEPAGE.BOARD_RECOMMENDATIONS
	ADD CONSTRAINT FK_BRD_ENTRY_ID FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.BOARD_ENTRIES (ENTRY_ID);    

CREATE INDEX HOMEPAGE.BRD_REC_STORY_ID
    ON HOMEPAGE.BOARD_RECOMMENDATIONS (ENTRY_ID) TABLESPACE "BOARDINDEXTABSPACE";
    
CREATE UNIQUE INDEX HOMEPAGE.BRD_RECOM_ENTRY_ID
    ON HOMEPAGE.BOARD_RECOMMENDATIONS (RECOMMENDER_ID, ENTRY_ID) TABLESPACE "BOARDINDEXTABSPACE";
    
CREATE INDEX HOMEPAGE.BRD_RECOMMENDER_ID
    ON HOMEPAGE.BOARD_RECOMMENDATIONS (RECOMMENDER_ID) TABLESPACE "BOARDINDEXTABSPACE";    

COMMIT;

ALTER TABLE HOMEPAGE.BOARD_RECOMMENDATIONS ENABLE ROW MOVEMENT;

-------------------------------------------------------
-- HOMEPAGE.BOARD_CURRENT_STATUS 
-- table to store the history of current status updates
-------------------------------------------------------
CREATE TABLE HOMEPAGE.BOARD_CURRENT_STATUS  (
	CURRENT_STATUS_ID VARCHAR2(47) NOT NULL, -- the format will include in the pk also the creation time
	ACTOR_UUID VARCHAR2(36) NOT NULL,
	ENTRY_ID VARCHAR2(47) NOT NULL,
	CURRENT_STATUS_SET TIMESTAMP NOT NULL
)
TABLESPACE "NEWSREGTABSPACE"; 

ALTER TABLE HOMEPAGE.BOARD_CURRENT_STATUS 
    ADD (CONSTRAINT PK_CUR_ST_ID PRIMARY KEY (CURRENT_STATUS_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.BOARD_CURRENT_STATUS
	ADD CONSTRAINT FK_CUR_ST_ACTOR_ID FOREIGN KEY (ACTOR_UUID)
	REFERENCES HOMEPAGE.PERSON(PERSON_ID);

ALTER TABLE HOMEPAGE.BOARD_CURRENT_STATUS
	ADD CONSTRAINT FK_CUR_ST_ENTRY_ID FOREIGN KEY (ENTRY_ID)
	REFERENCES HOMEPAGE.BOARD_ENTRIES(ENTRY_ID);

CREATE INDEX HOMEPAGE.CURRENT_STATUS_INDEX 
	ON HOMEPAGE.BOARD_CURRENT_STATUS	(ACTOR_UUID ASC, CURRENT_STATUS_SET ASC, CURRENT_STATUS_ID ASC, ENTRY_ID ASC) TABLESPACE "NEWSINDEXTABSPACE";

CREATE UNIQUE INDEX HOMEPAGE.ACTOR_ENTRY 
	ON HOMEPAGE.BOARD_CURRENT_STATUS (ACTOR_UUID) TABLESPACE "NEWSINDEXTABSPACE";
	
CREATE INDEX HOMEPAGE.BRD_CURRENT_STATUS 
	ON HOMEPAGE.BOARD_CURRENT_STATUS (ENTRY_ID) TABLESPACE "BOARDINDEXTABSPACE";	

COMMIT;

ALTER TABLE "HOMEPAGE"."BOARD_CURRENT_STATUS" ENABLE ROW MOVEMENT;

---------------------------------------------------------------------------------
-- ADDING QUARANTINE TABLE
---------------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.DELETED_STORIES_QUEUE (
	QUEUE_ID VARCHAR2(36) NOT NULL,
	STORY_ID VARCHAR2(36) NOT NULL,
	SOURCE 	VARCHAR2(36) NOT NULL, -- this is externalized
	SOURCE_TYPE NUMBER(5 ,0) NOT NULL,
	STATUS NUMBER(5 ,0) DEFAULT 0 NOT NULL 	
)	
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.DELETED_STORIES_QUEUE 
  	ADD (CONSTRAINT PK_QUARANTINE PRIMARY KEY(QUEUE_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");
  	
CREATE INDEX HOMEPAGE.DELETED_STORY_ID 
  	ON HOMEPAGE.DELETED_STORIES_QUEUE (STORY_ID) TABLESPACE "NEWSINDEXTABSPACE";  

COMMIT; 

ALTER TABLE "HOMEPAGE"."DELETED_STORIES_QUEUE" ENABLE ROW MOVEMENT;

-----------------------------------------------
-- HOMEPAGE.NR_COMM_SETTINGS
-----------------------------------------------
CREATE TABLE HOMEPAGE.NR_COMM_SETTINGS (
	COMM_ID VARCHAR2(36) NOT NULL,
	MICROBLOGGING_WRITE_ACL NUMBER(5 ,0),
	FLAGS VARCHAR2(36)
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_COMM_SETTINGS
    ADD (CONSTRAINT PK_COMM_ID PRIMARY KEY(COMM_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NR_COMM_SETTINGS ENABLE ROW MOVEMENT;

--CREATE TABLE "HOMEPAGE"."NT_REPLYTO"
----------------------------------------------------------------------
-- HOMEPAGE.NT_REPLYTO
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NT_REPLYTO (
	REPLYTO_NOTIFICATION_ID VARCHAR2(36) NOT NULL,
	SOURCE VARCHAR2(36),
	EVENT_NAME VARCHAR2(256) NOT NULL,
	CONTAINER_ID VARCHAR2(256),	
	ITEM_ID VARCHAR2(36),
	ITEM_CORRELATION_ID VARCHAR2(36),	
	CREATION_DATE TIMESTAMP NOT NULL,
	ACTOR_UUID VARCHAR2(36),
	EVENT_RECORD_UUID VARCHAR2(36) NOT NULL,
	CATEGORY_TYPE NUMBER(5,0),
	SOURCE_TYPE NUMBER(5,0)
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.NT_REPLYTO
    ADD (CONSTRAINT PK_REPLYTO PRIMARY KEY(REPLYTO_NOTIFICATION_ID) USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NT_REPLYTO ENABLE ROW MOVEMENT;

COMMIT;

----------------------------------------------------------------------
-- HOMEPAGE.NT_REPLYTO_RECIPIENT
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT (
	REPLYTO_RECIPIENT_ID VARCHAR2(36) NOT NULL,
	REPLYTO_NOTIFICATION_ID VARCHAR2(36) NOT NULL,
	PERSON_ID VARCHAR2(36) NOT NULL,	
	REPLYTO_ID VARCHAR2(36) NOT NULL,
	LAST_UPDATE TIMESTAMP
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT
    ADD (CONSTRAINT REPLYTO_RECIP_ID PRIMARY KEY(REPLYTO_RECIPIENT_ID) USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");

ALTER TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT
    ADD CONSTRAINT FK_REPLYTO_NOT_ID FOREIGN KEY (REPLYTO_NOTIFICATION_ID)
	REFERENCES HOMEPAGE.NT_REPLYTO (REPLYTO_NOTIFICATION_ID);

ALTER TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT
    ADD CONSTRAINT FK_REPLYTO_PER_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

CREATE UNIQUE INDEX HOMEPAGE.REPLYTO_IDX
    ON HOMEPAGE.NT_REPLYTO_RECIPIENT (REPLYTO_ID) TABLESPACE "HPNTINDEXTABSPACE";

ALTER TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT ENABLE ROW MOVEMENT;

COMMIT;


------------------------------------------------------------------------------------------------
-- [START] UPDATING SET OF INDEXES FOR NR_NEWS_STATUS_NETWORK AND NR_NEWS_STATUS_COMMENT TABLES
------------------------------------------------------------------------------------------------

--------------------------------------------------------------
-- Make the existing index on ITEM_CORRELATION_ID to a clustered index
--------------------------------------------------------------
DROP INDEX HOMEPAGE.NR_NEWS_SC_ITEM_COR;
COMMIT;
 
CREATE CLUSTER 
	HOMEPAGE.CL_STATUS_COMMENT_IDX (ITEM_CORRELATION_ID VARCHAR2(36)) INDEX TABLESPACE "NEWSINDEXTABSPACE";
COMMIT;


-----------------------------------------------------------------------------
-- DROP AND RECREATE THE INDEXES USING UPDATE_DATE INSTEAD OF CREATION_DATE
-----------------------------------------------------------------------------
DROP INDEX HOMEPAGE.NR_NEWS_SN_READER_FOLL;
COMMIT;

CREATE INDEX HOMEPAGE.NR_NEWS_SN_READER_FOLL
    ON HOMEPAGE.NR_NEWS_STATUS_NETWORK (UPDATE_DATE DESC, READER_ID, IS_FOLLOW_NEWS) TABLESPACE "NEWSINDEXTABSPACE";
COMMIT;

DROP INDEX HOMEPAGE.NR_NEWS_SN_READER_NETW;
COMMIT;

CREATE INDEX HOMEPAGE.NR_NEWS_SN_READER_NETW
    ON HOMEPAGE.NR_NEWS_STATUS_NETWORK (UPDATE_DATE DESC, READER_ID, IS_NETWORK_NEWS) TABLESPACE "NEWSINDEXTABSPACE";
COMMIT;

DROP INDEX HOMEPAGE.NR_NEWS_STATUS_NETWORK_READER;
COMMIT;

CREATE INDEX HOMEPAGE.NR_NEWS_STATUS_NETWORK_READER
    ON HOMEPAGE.NR_NEWS_STATUS_NETWORK (UPDATE_DATE DESC, READER_ID) TABLESPACE "NEWSINDEXTABSPACE";
COMMIT;

DROP INDEX HOMEPAGE.NR_STATUS_NETWORK_DATE;
COMMIT;

CREATE INDEX HOMEPAGE.NR_STATUS_NETWORK_DATE
    ON HOMEPAGE.NR_NEWS_STATUS_NETWORK (UPDATE_DATE ASC) TABLESPACE "NEWSINDEXTABSPACE";
COMMIT;

------------------------------------------------------------------------------------------------
-- [END] UPDATING SET OF INDEXES FOR NR_NEWS_STATUS_NETWORK AND NR_NEWS_STATUS_COMMENT TABLES
------------------------------------------------------------------------------------------------


-----------------------------------------------------------------------
-- [START] SEEDLIST
-----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_AS_SEEDLIST (
	SEEDLIST_ID VARCHAR2(36) NOT NULL, -- pk which is not linked to any value
	IS_VISIBLE NUMBER(5 ,0) DEFAULT 1 NOT NULL, -- when an item id is moderated
	IS_DELETED NUMBER(5 ,0) DEFAULT 0 NOT NULL, -- when a story is moderated
	DATA VARCHAR2(4000),
	FULL_DATA CLOB, -- in case data is not enough big
	FOLLOWERS VARCHAR2(4000),
	FULL_FOLLOWERS CLOB, -- in case followers is not enough big
	STORY_ID VARCHAR2(36) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	UPDATE_DATE TIMESTAMP NOT NULL,
	ITEM_ID VARCHAR2(36)
)
LOB (FULL_DATA) STORE AS (TABLESPACE NEWSLOBTABSPACE STORAGE (INITIAL 1M) CHUNK 4000 NOCACHE NOLOGGING),
LOB (FULL_FOLLOWERS) STORE AS (TABLESPACE NEWSLOBTABSPACE STORAGE (INITIAL 1M) CHUNK 4000 NOCACHE NOLOGGING),
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_AS_SEEDLIST
	ADD (CONSTRAINT PK PRIMARY KEY (SEEDLIST_ID)  USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

CREATE UNIQUE INDEX HOMEPAGE.NR_SL_STR_UNIQUE
	ON HOMEPAGE.NR_AS_SEEDLIST(STORY_ID);	

CREATE INDEX HOMEPAGE.NR_SL_ITEM_ID_IX
	ON HOMEPAGE.NR_AS_SEEDLIST (ITEM_ID);

CREATE INDEX HOMEPAGE.NR_SL_UD_DELTED_IX
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC, IS_DELETED)  TABLESPACE  NEWSINDEXTABSPACE; 

CREATE INDEX HOMEPAGE.NR_SL_UD_VISIBLE_IX
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC, IS_VISIBLE)  TABLESPACE  NEWSINDEXTABSPACE; 
	
CREATE INDEX HOMEPAGE.NR_SL_UD_DELETED_VIS
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC, IS_DELETED, IS_VISIBLE)  TABLESPACE  NEWSINDEXTABSPACE;
	
CREATE INDEX HOMEPAGE.NR_SL_UD_DELETED
        ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC)  TABLESPACE  "NEWSINDEXTABSPACE";	
	
COMMIT;	

ALTER TABLE "HOMEPAGE"."NR_AS_SEEDLIST" ENABLE ROW MOVEMENT;


-----------------------------------------------------------------------
-- [END] SEEDLIST
-----------------------------------------------------------------------

------------------------------------------------
-- HOMEPAGE.NR_AS_COLLECTION_CONFIG
------------------------------------------------
CREATE TABLE HOMEPAGE.NR_AS_COLLECTION_CONFIG (
	COLLECTION_ID VARCHAR2 (50) NOT NULL,
	XML_DATA CLOB NOT NULL
)
LOB (XML_DATA) STORE AS (TABLESPACE NEWSLOBTABSPACE STORAGE (INITIAL 1M) CHUNK 4000 NOCACHE NOLOGGING),
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_AS_COLLECTION_CONFIG
	ADD (CONSTRAINT NR_AS_CONFIG_PK PRIMARY KEY (COLLECTION_ID)  USING INDEX TABLESPACE "NEWSINDEXTABSPACE");


ALTER TABLE "HOMEPAGE"."NR_AS_COLLECTION_CONFIG" ENABLE ROW MOVEMENT;

COMMIT;

------------------------------------------------
-- HOMEPAGE.NR_AS_CRAWLER_STATUS
------------------------------------------------	
CREATE TABLE HOMEPAGE.NR_AS_CRAWLER_STATUS (
	COLLECTION_ID VARCHAR2 (50) NOT NULL,
	XML_DATA CLOB NOT NULL
)
LOB (XML_DATA) STORE AS (TABLESPACE NEWSLOBTABSPACE STORAGE (INITIAL 1M) CHUNK 4000 NOCACHE NOLOGGING),
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_AS_CRAWLER_STATUS
	ADD (CONSTRAINT NR_AS_STATUS_PK PRIMARY KEY (COLLECTION_ID)  USING INDEX TABLESPACE "NEWSINDEXTABSPACE");

ALTER TABLE "HOMEPAGE"."NR_AS_CRAWLER_STATUS" ENABLE ROW MOVEMENT;

COMMIT;

------------------------------------------------------------------------------
-- ADD NR_AS_CONTENT_INDEX_STATS TABLE
------------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_AS_CONTENT_INDEX_STATS (
	STAT_ID VARCHAR2(36) NOT NULL,
	STAT_NAME VARCHAR2(2048),
	STAT_VALUE BLOB,
	STAT_UPDATE_DATE TIMESTAMP
)
LOB (STAT_VALUE) STORE AS (TABLESPACE NEWSLOBTABSPACE STORAGE (INITIAL 1M) CHUNK 4000 NOCACHE NOLOGGING) 
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.NR_AS_CONTENT_INDEX_STATS
  	ADD (CONSTRAINT NR_CONTENTSTATS_PK PRIMARY KEY(STAT_ID) USING INDEX TABLESPACE "NEWSINDEXTABSPACE") ;

ALTER TABLE "HOMEPAGE"."NR_AS_CONTENT_INDEX_STATS" ENABLE ROW MOVEMENT;

COMMIT;


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
	ID VARCHAR2(256) NOT NULL PRIMARY KEY USING INDEX  TABLESPACE "NEWSINDEXTABSPACE",
	TOKEN VARCHAR2(1024) NOT NULL,
	SECRET VARCHAR2(1024) NOT NULL,
	EXPIRATION TIMESTAMP,
	CREATED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP NOT NULL
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OAUTH1_TOKEN ENABLE ROW MOVEMENT;

COMMIT;


-------------------------------------------
-- HOMEPAGE.OAUTH1_PROVIDER
-------------------------------------------	 
CREATE TABLE HOMEPAGE.OAUTH1_PROVIDER (
	ID VARCHAR2(256) NOT NULL PRIMARY KEY USING INDEX  TABLESPACE "NEWSINDEXTABSPACE",
	NAME VARCHAR2(254) NOT NULL,
	DESCRIPTION VARCHAR2(1024),
	REQUESTTOKENURL VARCHAR2(512) NOT NULL,
	AUTHORIZEURL VARCHAR2(512) NOT NULL,
	ACCESSTOKENURL VARCHAR2(512) NOT NULL,
	BASEURL VARCHAR2(512),
	MANAGEURL VARCHAR2(512),
	REGISTERURL VARCHAR2(512),
	SIGNMETHOD VARCHAR2(32),
	VERSION VARCHAR2(8),
	CREATED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP NOT NULL,
	CONSTRAINT PRVD_NAME_UK UNIQUE (NAME) USING INDEX  TABLESPACE "NEWSINDEXTABSPACE"
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OAUTH1_PROVIDER ENABLE ROW MOVEMENT; 

COMMIT;

-------------------------------------------
-- HOMEPAGE.OAUTH1_CLIENT
-------------------------------------------
CREATE TABLE HOMEPAGE.OAUTH1_CLIENT (
	ID VARCHAR2(256) NOT NULL PRIMARY KEY USING INDEX  TABLESPACE "NEWSINDEXTABSPACE",
	PERSON_ID VARCHAR2(36) NOT NULL,
	NAME VARCHAR2(254) NOT NULL,
	TOKENID VARCHAR2(256) NOT NULL,
	PROVIDERID VARCHAR2(256) NOT NULL,
	DESCRIPTION VARCHAR2(1024),
	CALLBACKURL VARCHAR2(512),
	CREATED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP NOT NULL,
	CONSTRAINT CLNT_NAME_UK UNIQUE (NAME) USING INDEX  TABLESPACE "NEWSINDEXTABSPACE"
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OAUTH1_CLIENT 
	ADD CONSTRAINT CLNT_FK FOREIGN KEY (PROVIDERID) 
	REFERENCES HOMEPAGE.OAUTH1_PROVIDER(ID);

ALTER TABLE HOMEPAGE.OAUTH1_CLIENT 
	ADD CONSTRAINT CLNT_TOKEN_FK FOREIGN KEY (TOKENID) 
	REFERENCES HOMEPAGE.OAUTH1_TOKEN(ID);
	
ALTER TABLE HOMEPAGE.OAUTH1_CLIENT
	ADD CONSTRAINT FK_OA1CL_PERSON_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON(PERSON_ID);	
	
ALTER TABLE HOMEPAGE.OAUTH1_CLIENT ENABLE ROW MOVEMENT; 

COMMIT;

-------------------------------------------
-- HOMEPAGE.OAUTH1_CONTEXT
-------------------------------------------
CREATE TABLE HOMEPAGE.OAUTH1_CONTEXT (
	ID VARCHAR2(256) NOT NULL PRIMARY KEY USING INDEX  TABLESPACE "NEWSINDEXTABSPACE",
	CLIENTID VARCHAR2(256) NOT NULL,
	PERSON_ID VARCHAR2(36),
	TOKENID VARCHAR2(256) NOT NULL,
	CREATED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP NOT NULL,
	NONCE VARCHAR2(256),
	EXPIRATION TIMESTAMP,
	AUTHORIZED NUMBER(5,0) DEFAULT 0 NOT NULL
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OAUTH1_CONTEXT
	ADD CONSTRAINT CONTEXT_CLNT_FK FOREIGN KEY (CLIENTID) 
	REFERENCES HOMEPAGE.OAUTH1_CLIENT(ID);

ALTER TABLE HOMEPAGE.OAUTH1_CONTEXT
	ADD CONSTRAINT AUTH_TOKEN_FK FOREIGN KEY (TOKENID) 
	REFERENCES HOMEPAGE.OAUTH1_TOKEN(ID) ON DELETE CASCADE INITIALLY DEFERRED DEFERRABLE;
	
ALTER TABLE HOMEPAGE.OAUTH1_CONTEXT
	ADD CONSTRAINT FK_OA1C_PERSON_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON(PERSON_ID);		

ALTER TABLE HOMEPAGE.OAUTH1_CONTEXT ENABLE ROW MOVEMENT;

COMMIT;
	
-------------------------------------------
-- HOMEPAGE.OAUTH2_PROVIDER
-------------------------------------------
CREATE TABLE HOMEPAGE.OAUTH2_PROVIDER (
  NAME VARCHAR2(254) NOT NULL PRIMARY KEY USING INDEX  TABLESPACE "NEWSINDEXTABSPACE",
  CLIENT_AUTH VARCHAR2(254),
  AUTH_HEADER NUMBER(5,0),
  URL_PARAM NUMBER(5,0),
  AUTH_URL VARCHAR2(1024),
  TOKEN_URL VARCHAR2(1024)
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OAUTH2_PROVIDER ENABLE ROW MOVEMENT;  

COMMIT;


-------------------------------------------
-- HOMEPAGE.OAUTH2_CLIENT
-------------------------------------------
CREATE TABLE HOMEPAGE.OAUTH2_CLIENT (
  NAME VARCHAR2(254) NOT NULL PRIMARY KEY USING INDEX  TABLESPACE "NEWSINDEXTABSPACE",
  PROVIDER_NAME VARCHAR2(254) NOT NULL,
  REDIRECT_URI VARCHAR2(1024),
  CTYPE VARCHAR2(254) NOT NULL,
  GRANT_TYPE VARCHAR2(254) NOT NULL,
  CLIENT_ID VARCHAR2(1024) NOT NULL,
  CLIENT_SECRET VARCHAR2(1024) NOT NULL
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OAUTH2_CLIENT
  ADD CONSTRAINT FK_CLIENT_PRO_NAME FOREIGN KEY(PROVIDER_NAME)
  REFERENCES HOMEPAGE.OAUTH2_PROVIDER(NAME);
  
ALTER TABLE HOMEPAGE.OAUTH2_CLIENT ENABLE ROW MOVEMENT;   

COMMIT;
    
-------------------------------------------
-- HOMEPAGE.OAUTH2_GADGET_BINDING
-------------------------------------------
CREATE TABLE HOMEPAGE.OAUTH2_GADGET_BINDING (
  ID VARCHAR2(36) NOT NULL PRIMARY KEY USING INDEX  TABLESPACE "NEWSINDEXTABSPACE",
  WIDGET_ID VARCHAR2(36) NOT NULL,
  URI_SHA1 CHAR(40) NOT NULL, 
  SERVICE_NAME VARCHAR2(254)  NOT NULL,
  URI VARCHAR2(1024) NOT NULL,
  CLIENT_NAME VARCHAR2(254) NOT NULL,
  OVERRIDES NUMBER(5,0) NOT NULL,
  CONSTRAINT URI_GADGET_UK UNIQUE (URI_SHA1, SERVICE_NAME) USING INDEX  TABLESPACE "NEWSINDEXTABSPACE",
  CONSTRAINT WIDGET_GADGET_UK UNIQUE (WIDGET_ID, SERVICE_NAME) USING INDEX  TABLESPACE "NEWSINDEXTABSPACE"
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OAUTH2_GADGET_BINDING
  ADD CONSTRAINT FK_BINDING_CLIENT FOREIGN KEY(CLIENT_NAME)
  REFERENCES HOMEPAGE.OAUTH2_CLIENT(NAME);

ALTER TABLE HOMEPAGE.OAUTH2_GADGET_BINDING
  ADD CONSTRAINT FK_BINDING_WIDGET FOREIGN KEY(WIDGET_ID)
  REFERENCES HOMEPAGE.WIDGET(WIDGET_ID);
  
CREATE INDEX OA2T_GBINDING_WID
	ON HOMEPAGE.OAUTH2_GADGET_BINDING (WIDGET_ID) TABLESPACE "HOMEPAGEINDEXTABSPACE";

ALTER TABLE HOMEPAGE.OAUTH2_GADGET_BINDING ENABLE ROW MOVEMENT;

COMMIT;


-------------------------------------------
-- HOMEPAGE.OAUTH2_TOKEN
-------------------------------------------
CREATE TABLE HOMEPAGE.OAUTH2_TOKEN (
  ID VARCHAR2(254) NOT NULL PRIMARY KEY USING INDEX  TABLESPACE "NEWSINDEXTABSPACE",
  URI VARCHAR2(1024) NOT NULL,
  SERVICE_NAME VARCHAR2(254) NOT NULL,
  PERSON_ID VARCHAR2(36) NOT NULL,
  SCOPE VARCHAR2(1024) NOT NULL,
  TTYPE NUMBER(5,0) NOT NULL,
  SECRET VARCHAR2(1024),
  EXPIRES TIMESTAMP,
  ISSUED TIMESTAMP NOT NULL,
  TOKEN_TYPE VARCHAR2(254),
  MAC_SECRET VARCHAR2(1024),
  MAC_ALG VARCHAR2(254),
  MAC_EXT VARCHAR2(254),
  PROPS VARCHAR2(1024)
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OAUTH2_TOKEN
	ADD CONSTRAINT FK_OA2T_PERSON_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON(PERSON_ID);	

CREATE INDEX HOMEPAGE.OA2T_PERSON_SERVICE
	ON HOMEPAGE.OAUTH2_TOKEN (PERSON_ID, SERVICE_NAME) TABLESPACE "NEWSINDEXTABSPACE";
	
ALTER TABLE HOMEPAGE.OAUTH2_TOKEN ENABLE ROW MOVEMENT;

COMMIT;

CREATE TABLE HOMEPAGE.OH2P_CACHE (
	LOOKUPKEY VARCHAR2(256) NOT NULL, 
	UNIQUEID VARCHAR2(128) NOT NULL, 
	COMPONENTID VARCHAR2(256) NOT NULL, 
	TYPE VARCHAR2(64) NOT NULL, 
	SUBTYPE VARCHAR2(64), 
	CREATEDAT NUMBER(19), 
	LIFETIME NUMBER(10), 
	EXPIRES NUMBER(19), 
	TOKENSTRING VARCHAR2(2048) NOT NULL, 
	CLIENTID VARCHAR2(64) NOT NULL, 
	USERNAME VARCHAR2(64) NOT NULL, 
	SCOPE VARCHAR2(512) NOT NULL, 
	REDIRECTURI VARCHAR2(2048), 
	STATEID VARCHAR2(64) NOT NULL
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OH2P_CACHE 
	ADD CONSTRAINT PK_LOOKUPKEY PRIMARY KEY (LOOKUPKEY) USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE";

CREATE INDEX OH2P_CACHE_EXPIRES ON HOMEPAGE.OH2P_CACHE (EXPIRES ASC) TABLESPACE "HOMEPAGEINDEXTABSPACE";

ALTER TABLE HOMEPAGE.OH2P_CACHE ENABLE ROW MOVEMENT;

COMMIT;


CREATE TABLE HOMEPAGE.OH2P_CLIENTCFG (
	COMPONENTID VARCHAR2(256) NOT NULL, 
	CLIENTID VARCHAR2(256) NOT NULL, 
	CLIENTSECRET VARCHAR2(256), 
	DISPLAYNAME VARCHAR2(256) NOT NULL, 
	REDIRECTURI VARCHAR2(2048), 
	ENABLED NUMBER(5)
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OH2P_CLIENTCFG 
	ADD CONSTRAINT PK_COMPIDCLIENTID PRIMARY KEY (COMPONENTID,CLIENTID) USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE";

ALTER TABLE HOMEPAGE.OH2P_CLIENTCFG ENABLE ROW MOVEMENT;

COMMIT;

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

COMMIT;

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

commit;

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

COMMIT;

-----------------------------------------------
--  Recommendations widget is editable  [Work Item 52621]
-----------------------------------------------
UPDATE HOMEPAGE.WIDGET SET WIDGET_SYSTEM = 1 WHERE WIDGET_TITLE = '%widget.sand.recommend.name';
COMMIT;

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

COMMIT;

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

CREATE VIEW HOMEPAGE.STATUS_UPDATE_READERS  AS (
	SELECT 	NR_NEWS_STATUS_NETWORK.NEWS_STATUS_NETWORK_ID	CATEGORY_READER_ID,
			NR_NEWS_STATUS_NETWORK.READER_ID				READER_ID,			
			11 												CATEGORY_TYPE,
			'profiles' 										SOURCE,
			NR_NEWS_STATUS_NETWORK.ACTOR_UUID 				CONTAINER_ID,
			NR_NEWS_STATUS_NETWORK.ITEM_ID					ITEM_ID,
			' ' 											ROLLUP_ENTRY_ID,
			10 												RESOURCE_TYPE,
			NR_NEWS_STATUS_NETWORK.CREATION_DATE			CREATION_DATE,
			NR_STORIES.STORY_ID								STORY_ID,
			5 												SOURCE_TYPE,
			0 												USE_IN_ROLLUP,
			NR_NEWS_STATUS_NETWORK.IS_NETWORK_NEWS 			IS_NETWORK,
			NR_NEWS_STATUS_NETWORK.IS_FOLLOW_NEWS 			IS_FOLLOWER,
			NR_NEWS_STATUS_NETWORK.CREATION_DATE 			EVENT_TIME,
			0 												IS_STORY_COMM,
			1 												IS_BROADCAST,
			' ' 											ORGANIZATION_ID,
			NR_NEWS_STATUS_NETWORK.ACTOR_UUID				ACTOR_UUID,
			NR_STORIES.ACTOR_UUID 							ROLLUP_AUTHOR_ID,
			1 												IS_VISIBLE				
	FROM 	HOMEPAGE.NR_STORIES NR_STORIES,
			HOMEPAGE.NR_NEWS_STATUS_NETWORK NR_NEWS_STATUS_NETWORK
	WHERE 	NR_STORIES.ITEM_ID = NR_NEWS_STATUS_NETWORK.ITEM_ID
);

COMMIT;

DECLARE  CURSOR s_cur IS SELECT * FROM HOMEPAGE.STATUS_UPDATE_READERS;

TYPE fetch_array IS TABLE OF s_cur%ROWTYPE;
s_array fetch_array;
BEGIN
  	OPEN s_cur;
  	LOOP
    	FETCH s_cur BULK COLLECT INTO s_array LIMIT 1000;
    	FORALL i IN 1..s_array.COUNT
    	INSERT INTO HOMEPAGE.NR_STATUS_UPDATE_READERS VALUES s_array(i);
		COMMIT;
    	
		EXIT WHEN s_cur%NOTFOUND;
  	END LOOP;
  	CLOSE s_cur;
  	COMMIT;
END;
/

DROP VIEW HOMEPAGE.STATUS_UPDATE_READERS;

COMMIT;

--------------------------------------------------------------------------------------------------------------------
-- In 4.0 we have that if the author is equal to the reader then the flag IS_NETWORK and IS_FOLLOW need to be 0
-- https://swgjazz.ibm.com:8001/jazz/resource/itemName/com.ibm.team.workitem.WorkItem/63104
--------------------------------------------------------------------------------------------------------------------


--  [start] NR_STATUS_UPDATE_READERS
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID < '0..f';
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '0..f' AND CATEGORY_READER_ID < '1..f' ;
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '1..f' AND CATEGORY_READER_ID < '2..f' ;
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '2..f' AND CATEGORY_READER_ID < '3..f';
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '3..f' AND CATEGORY_READER_ID < '4..f';
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '4..f' AND CATEGORY_READER_ID < '5..f';
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '5..f' AND CATEGORY_READER_ID < '6..f';
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '6..f' AND CATEGORY_READER_ID < '7..f';
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '7..f' AND CATEGORY_READER_ID < '8..f';
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '8..f' AND CATEGORY_READER_ID < '9..f';
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= '9..f' AND CATEGORY_READER_ID < 'a..f';
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= 'a..f' AND CATEGORY_READER_ID < 'b..f';
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= 'b..f' AND CATEGORY_READER_ID < 'c..f';
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= 'c..f' AND CATEGORY_READER_ID < 'd..f';
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= 'd..f' AND CATEGORY_READER_ID < 'e..f';
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= 'e..f' AND CATEGORY_READER_ID < 'f..f';
COMMIT;

COMMIT;
UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS  
 	 SET IS_NETWORK = 0, IS_FOLLOWER = 0 WHERE ACTOR_UUID = READER_ID AND  CATEGORY_READER_ID >= 'f..f';
COMMIT;

COMMIT;
--  [end] NR_STATUS_UPDATE_READERS


COMMIT;


--------------------------------------------------------------------------------------------------------------------
CREATE VIEW HOMEPAGE.AGGREGATED_READERS  AS (
	SELECT 		
			CATEGORY_READER_ID,
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
			IS_VISIBLE,
			ROLLUP_AUTHOR_ID		
	FROM 	HOMEPAGE.NR_STATUS_UPDATE_READERS
	WHERE 	READER_ID = ACTOR_UUID
);

COMMIT;

DECLARE  CURSOR s_cur IS SELECT * FROM HOMEPAGE.AGGREGATED_READERS;
	
TYPE fetch_array IS TABLE OF s_cur%ROWTYPE;
s_array fetch_array;
BEGIN
  	OPEN s_cur;
  	LOOP
    	FETCH s_cur BULK COLLECT INTO s_array LIMIT 1000;
    	FORALL i IN 1..s_array.COUNT
    	INSERT INTO HOMEPAGE.NR_AGGREGATED_READERS VALUES s_array(i);
		COMMIT;
    	
		EXIT WHEN s_cur%NOTFOUND;
  	END LOOP;
  	CLOSE s_cur;
  	COMMIT;
END;
/

DROP VIEW HOMEPAGE.AGGREGATED_READERS;

COMMIT;
----------------------------------------------------------------------------------
-- [DATA_MIGRATION] [END] data migration for NR_STATUS_UPDATE_READERS
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------
----------------------------------------------------------------------------------


------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
--	[START] SAVED MIGRATION
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------

-- MIGRATION OF SAVED READERS
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
				NULL				ITEM_UPDATE_DATE, --
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


DECLARE  CURSOR s_cur IS 
SELECT  * FROM HOMEPAGE.STORIES;
	
TYPE fetch_array IS TABLE OF s_cur%ROWTYPE;
s_array fetch_array;
BEGIN
  	OPEN s_cur;
  	LOOP
    	FETCH s_cur BULK COLLECT INTO s_array LIMIT 1000;
    	FORALL i IN 1..s_array.COUNT
    	INSERT INTO HOMEPAGE.NR_STORIES VALUES s_array(i);
    	COMMIT;
    	EXIT WHEN s_cur%NOTFOUND;
  	END LOOP;
  	CLOSE s_cur;
  	COMMIT;
END;
/

DROP VIEW HOMEPAGE.STORIES;

COMMIT;

-- 1 READERS
CREATE VIEW HOMEPAGE.READERS AS (
	SELECT 	
			NR_NEWS_SAVED.NEWS_RECORDS_ID 	CATEGORY_READER_ID,
			NR_NEWS_SAVED.READER_ID			READER_ID,
			13								CATEGORY_TYPE,
			NR_NEWS_SAVED.SOURCE			SOURCE,
			NR_NEWS_SAVED.CONTAINER_ID		CONTAINER_ID,
			NR_NEWS_SAVED.ITEM_ID			ITEM_ID,
			' '								ROLLUP_ENTRY_ID,
			0								RESOURCE_TYPE,
			NR_NEWS_SAVED.CREATION_DATE		CREATION_DATE,
			NR_NEWS_SAVED.NEWS_STORY_ID		STORY_ID,
			NR_NEWS_SAVED.SOURCE_TYPE		SOURCE_TYPE,
			0								USE_IN_ROLLUP,				
			0								IS_NETWORK,
			0								IS_FOLLOWER,
			NR_NEWS_SAVED.CREATION_DATE		EVENT_TIME,
			' '								NOTE_TEXT,
			NULL							NOTE_UPDATE_DATE,
			NR_NEWS_SAVED.IS_COMMUNITY_STORY IS_STORY_COMM,
			0								IS_BROADCAST,
			' '								ORGANIZATION_ID,
			' '								OPERATION_ID,
			' '								ACTOR_UUID,
			' '								ROLLUP_AUTHOR_ID,
			1								IS_VISIBLE
	FROM 	HOMEPAGE.NR_NEWS_SAVED NR_NEWS_SAVED
);

DECLARE  CURSOR s_cur IS 
SELECT * FROM HOMEPAGE.READERS;	

TYPE fetch_array IS TABLE OF s_cur%ROWTYPE;
s_array fetch_array;
BEGIN
  	OPEN s_cur;
  	LOOP
    	FETCH s_cur BULK COLLECT INTO s_array LIMIT 1000;
    	FORALL i IN 1..s_array.COUNT
    	INSERT INTO HOMEPAGE.NR_SAVED_READERS VALUES s_array(i);
		COMMIT;
    	
		EXIT WHEN s_cur%NOTFOUND;
  	END LOOP;
  	CLOSE s_cur;
  	COMMIT;
END;
/

COMMIT;

DROP VIEW HOMEPAGE.READERS;

COMMIT;

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
		HOMEPAGE.NR_STORIES STORIES
	WHERE 	NR_NEWS_DISCOVERY.NEWS_STORY_ID =  STORIES.STORY_ID
);

COMMIT;


DECLARE  CURSOR s_cur IS 
SELECT * FROM HOMEPAGE.DISCOVERY;	

TYPE fetch_array IS TABLE OF s_cur%ROWTYPE;
s_array fetch_array;
BEGIN
  	OPEN s_cur;
  	LOOP
    	FETCH s_cur BULK COLLECT INTO s_array LIMIT 1000;
    	FORALL i IN 1..s_array.COUNT
    	INSERT INTO HOMEPAGE.NR_DISCOVERY_VIEW 	VALUES s_array(i);
		COMMIT;
    	
		EXIT WHEN s_cur%NOTFOUND;
  	END LOOP;
  	CLOSE s_cur;
  	COMMIT;
END;
/

DROP VIEW HOMEPAGE.DISCOVERY;

COMMIT;


--set integrity for HOMEPAGE.NR_DISCOVERY_VIEW all immediate unchecked; 
commit;


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

COMMIT;


--------------------------------------------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 100
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 101 , RELEASEVER = '4.0.0.0'
WHERE   DBSCHEMAVER = 59;
