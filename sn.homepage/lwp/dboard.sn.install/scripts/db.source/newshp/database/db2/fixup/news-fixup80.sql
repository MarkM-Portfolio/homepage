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

-- HOMEPAGE.NR_COMMUNITIES_STORIES
UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'activities' WHERE SOURCE = 'tag.activities';
commit;

UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'blogs' WHERE SOURCE = 'tag.blogs';
commit;

UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'communities' WHERE SOURCE = 'tag.communities';
commit;

UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'files' WHERE SOURCE = 'tag.files';
commit;

UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'homepage' WHERE SOURCE = 'tag.homepage';
commit;

UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'profiles' WHERE SOURCE = 'tag.profiles';
commit;

UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'wikis' WHERE SOURCE = 'tag.wikis';
commit;

UPDATE HOMEPAGE.NR_COMMUNITIES_STORIES SET SOURCE = 'dogear' WHERE SOURCE = 'tag.dogear';
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

RENAME TABLE HOMEPAGE.NR_ATTACHMENT 		TO NR_ATTACHMENT_301;
RENAME TABLE HOMEPAGE.NR_RECOMMENDATION 	TO NR_RECOMMENDATION_301;



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

-- #4 - Status Updates \ People I'm Following - should also include status by people in My Network
-- If you are networked with a user in 2.5.0.3, their latest status should be present in the "People I'm Following" view in 3.0.1 when migrated.
-- The default behaviour is that any network colleague making a status update automatically appears in your People I'm Following view.
-- This is not a ship stop if fix not included, but we should be including all relevant data in the migration process.
UPDATE HOMEPAGE.NR_NEWS_STATUS_NETWORK SET IS_FOLLOW_NEWS = 1 WHERE IS_NETWORK_NEWS = 1;

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





--------------------------------------------------------------
-- CREATING A PARTITIONED TABLES
--------------------------------------------------------------

------------------------------------------------
-- 1) NR_SOURCE_TYPE
------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SOURCE_TYPE (
	SOURCE_TYPE_ID VARCHAR(36) NOT NULL,
	SOURCE_TYPE_NAME VARCHAR(36) NOT NULL, -- this is externalized
	SOURCE_TYPE SMALLINT NOT NULL,
	SOURCE_TYPE_DESC VARCHAR(256) NOT NULL
)
IN NEWS4TABSPACE;

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
  	ADD CONSTRAINT "PK_SRC_TYPE_ID" PRIMARY KEY("SOURCE_TYPE_ID");

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE 
	ADD CONSTRAINT SRC_TYPE_UNIQUE UNIQUE(SOURCE_TYPE);

runstats on table HOMEPAGE.NR_SOURCE_TYPE with distribution and detailed indexes all allow write access;


-------------------------------------------------
-- 2) ADDING SOURCE TYPE COLUMNS TO EXISTING TABLES
-------------------------------------------------

--------------------------------------------
-- ADDING SOURCE_TYPE - NR_SOURCE
--------------------------------------------
ALTER TABLE HOMEPAGE.NR_SOURCE
	ADD COLUMN SOURCE_TYPE SMALLINT;
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

--------------------------------------------
-- ADDING SOURCE_TYPE - NR_NEWS_SAVED
--------------------------------------------
ALTER TABLE HOMEPAGE.NR_NEWS_SAVED
	ADD COLUMN SOURCE_TYPE SMALLINT;
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

--------------------------------------------
-- ADDING SOURCE_TYPE - NR_NEWS_DISCOVERY
--------------------------------------------
-- because NR_NEWS_DISCOVERY is a very big table we need to perform a partioned update based on pk.
-- partitioned
ALTER TABLE HOMEPAGE.NR_NEWS_DISCOVERY
	ADD COLUMN SOURCE_TYPE SMALLINT;
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
----------------------------------n----------	
ALTER TABLE HOMEPAGE.NR_RESOURCE
	ADD COLUMN SOURCE_TYPE SMALLINT;
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
	ADD COLUMN SOURCE_TYPE SMALLINT;
COMMIT;	

UPDATE HOMEPAGE.NR_COMM_STORIES
	SET SOURCE_TYPE = 3;
COMMIT;

--------------------------------------------
-- ADDING SOURCE_TYPE - NR_COMM_PERSON_STORIES
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_COMM_PERSON_STORIES
	ADD COLUMN SOURCE_TYPE SMALLINT;
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
	ADD COLUMN SOURCE_TYPE SMALLINT;
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
	ADD COLUMN SOURCE_TYPE SMALLINT;
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
-- ADDING SOURCE_TYPE - NR_COMMUNITIES_STORIES (never used)
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_COMMUNITIES_STORIES
	ADD COLUMN SOURCE_TYPE SMALLINT;
COMMIT;	
	
--------------------------------------------
-- ADDING SOURCE_TYPE - NR_ACTIVITIES_STORIES
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_ACTIVITIES_STORIES
	ADD COLUMN SOURCE_TYPE SMALLINT;
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
	ADD COLUMN SOURCE_TYPE SMALLINT;
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
	ADD COLUMN SOURCE_TYPE SMALLINT;
COMMIT;	
	
--------------------------------------------
-- ADDING SOURCE_TYPE - NR_FILES_STORIES
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_FILES_STORIES
	ADD COLUMN SOURCE_TYPE SMALLINT;
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
	ADD COLUMN SOURCE_TYPE SMALLINT;
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
	ADD COLUMN SOURCE_TYPE SMALLINT;
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
	ADD COLUMN SOURCE_TYPE SMALLINT;
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




-------------------------------------------------
-- 3) ADDING PARTITIONED TABLES
-------------------------------------------------

----------------------------------------------------------------------
-- 1) HOMEPAGE.NR_SRC_STORIES_ACT
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_ACT (
	STORY_ID VARCHAR(36) NOT NULL,
	EVENT_NAME VARCHAR(256) NOT NULL,
	SOURCE VARCHAR(36),
	CONTAINER_ID VARCHAR(256),	
	CONTAINER_NAME VARCHAR(256),
	CONTAINER_URL VARCHAR(2048),
	ITEM_NAME VARCHAR(256),
	ITEM_URL VARCHAR(2048),
	ITEM_ATOM_URL VARCHAR(2048),
	ITEM_ID VARCHAR(36), -- NEW
	ITEM_CORRELATION_ID VARCHAR(36), -- NEW	
	CREATION_DATE TIMESTAMP NOT NULL,
	BRIEF_DESC VARCHAR(512),
	ACTOR_UUID VARCHAR(36),
	EVENT_RECORD_UUID VARCHAR(36) NOT NULL,
	TAGS VARCHAR(1024),
	META_TEMPLATE VARCHAR(4096) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE VARCHAR(1024),
	R_META_TEMPLATE VARCHAR(4096),
	R_TEXT_META_TEMPLATE VARCHAR(1024),
	N_COMMENTS SMALLINT NOT NULL DEFAULT 0, -- NEW
	N_RECOMMANDATIONS SMALLINT NOT NULL DEFAULT 0, -- NEW
	IS_COMMUNITY_STORY SMALLINT DEFAULT 0,
	ITEM_CORRELATION_NAME VARCHAR(256),
	SOURCE_TYPE SMALLINT,
	CONSTRAINT   	CK_SRC1_TYPE
    				CHECK
    				(SOURCE_TYPE = 1)
)
IN NEWS32TABSPACE;

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_ACT
    ADD CONSTRAINT "PK_ACT_STORY_ID" PRIMARY KEY("STORY_ID");

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_ACT_DATE
	ON HOMEPAGE.NR_SRC_STORIES_ACT(CREATION_DATE DESC);

CREATE INDEX HOMEPAGE.SRC_ACT_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_ACT (CONTAINER_ID);

CREATE INDEX HOMEPAGE.SRC_ACT_STORIES_ITEM_ID
    ON HOMEPAGE.NR_SRC_STORIES_ACT (ITEM_ID);

CREATE INDEX HOMEPAGE.SRC_ACT_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_ACT (ITEM_CORRELATION_ID);

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_ACT_SIDX
    ON HOMEPAGE.NR_SRC_STORIES_ACT (SOURCE_TYPE);

----------------------------------------------------------------------
-- 2) HOMEPAGE.NR_SRC_STORIES_BLG
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_BLG (
	STORY_ID VARCHAR(36) NOT NULL,
	EVENT_NAME VARCHAR(256) NOT NULL,
	SOURCE VARCHAR(36),
	CONTAINER_ID VARCHAR(256),	
	CONTAINER_NAME VARCHAR(256),
	CONTAINER_URL VARCHAR(2048),
	ITEM_NAME VARCHAR(256),
	ITEM_URL VARCHAR(2048),
	ITEM_ATOM_URL VARCHAR(2048),
	ITEM_ID VARCHAR(36), -- NEW
	ITEM_CORRELATION_ID VARCHAR(36), -- NEW	
	CREATION_DATE TIMESTAMP NOT NULL,
	BRIEF_DESC VARCHAR(512),
	ACTOR_UUID VARCHAR(36),
	EVENT_RECORD_UUID VARCHAR(36) NOT NULL,
	TAGS VARCHAR(1024),
	META_TEMPLATE VARCHAR(4096) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE VARCHAR(1024),
	R_META_TEMPLATE VARCHAR(4096),
	R_TEXT_META_TEMPLATE VARCHAR(1024),
	N_COMMENTS SMALLINT NOT NULL DEFAULT 0, -- NEW
	N_RECOMMANDATIONS SMALLINT NOT NULL DEFAULT 0, -- NEW
	IS_COMMUNITY_STORY SMALLINT DEFAULT 0,
	ITEM_CORRELATION_NAME VARCHAR(256),
	SOURCE_TYPE SMALLINT,
	CONSTRAINT   	CK_SRC2_TYPE
    				CHECK
    				(SOURCE_TYPE = 2)
)
IN NEWS32TABSPACE;

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_BLG
    ADD CONSTRAINT "PK_BLG_STORY_ID" PRIMARY KEY("STORY_ID");

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_BLG_DATE
	ON HOMEPAGE.NR_SRC_STORIES_BLG(CREATION_DATE DESC);

CREATE INDEX HOMEPAGE.SRC_BLG_STORIES_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_BLG (CONTAINER_ID);

CREATE INDEX HOMEPAGE.SRC_BLG_STORIES_ITEM_ID
    ON HOMEPAGE.NR_SRC_STORIES_BLG (ITEM_ID);

CREATE INDEX HOMEPAGE.SRC_BLG_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_BLG (ITEM_CORRELATION_ID);

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_BLG_SIDX
    ON HOMEPAGE.NR_SRC_STORIES_BLG (SOURCE_TYPE);

----------------------------------------------------------------------
-- 3) HOMEPAGE.NR_SRC_STORIES_COM
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_COM (
	STORY_ID VARCHAR(36) NOT NULL,
	EVENT_NAME VARCHAR(256) NOT NULL,
	SOURCE VARCHAR(36),
	CONTAINER_ID VARCHAR(256),	
	CONTAINER_NAME VARCHAR(256),
	CONTAINER_URL VARCHAR(2048),
	ITEM_NAME VARCHAR(256),
	ITEM_URL VARCHAR(2048),
	ITEM_ATOM_URL VARCHAR(2048),
	ITEM_ID VARCHAR(36), -- NEW
	ITEM_CORRELATION_ID VARCHAR(36), -- NEW	
	CREATION_DATE TIMESTAMP NOT NULL,
	BRIEF_DESC VARCHAR(512),
	ACTOR_UUID VARCHAR(36),
	EVENT_RECORD_UUID VARCHAR(36) NOT NULL,
	TAGS VARCHAR(1024),
	META_TEMPLATE VARCHAR(4096) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE VARCHAR(1024),
	R_META_TEMPLATE VARCHAR(4096),
	R_TEXT_META_TEMPLATE VARCHAR(1024),
	N_COMMENTS SMALLINT NOT NULL DEFAULT 0, -- NEW
	N_RECOMMANDATIONS SMALLINT NOT NULL DEFAULT 0, -- NEW
	IS_COMMUNITY_STORY SMALLINT DEFAULT 0,
	ITEM_CORRELATION_NAME VARCHAR(256),
	SOURCE_TYPE SMALLINT,
	CONSTRAINT   	CK_SRC3_TYPE
    				CHECK
    				(SOURCE_TYPE = 3)
)
IN NEWS32TABSPACE;

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_COM
    ADD CONSTRAINT "PK_COM_STORY_ID" PRIMARY KEY("STORY_ID");

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_COM_DATE
	ON HOMEPAGE.NR_SRC_STORIES_COM (CREATION_DATE DESC);

CREATE INDEX HOMEPAGE.SRC_COM_STORIES_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_COM (CONTAINER_ID);

CREATE INDEX HOMEPAGE.SRC_COM_STORIES_ITEM_ID
    ON HOMEPAGE.NR_SRC_STORIES_COM (ITEM_ID);

CREATE INDEX HOMEPAGE.SRC_COM_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_COM (ITEM_CORRELATION_ID);

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_COM_SIDX
    ON HOMEPAGE.NR_SRC_STORIES_COM (SOURCE_TYPE);
    
----------------------------------------------------------------------
-- 4) HOMEPAGE.NR_SRC_STORIES_WIK
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_WIK (
	STORY_ID VARCHAR(36) NOT NULL,
	EVENT_NAME VARCHAR(256) NOT NULL,
	SOURCE VARCHAR(36),
	CONTAINER_ID VARCHAR(256),	
	CONTAINER_NAME VARCHAR(256),
	CONTAINER_URL VARCHAR(2048),
	ITEM_NAME VARCHAR(256),
	ITEM_URL VARCHAR(2048),
	ITEM_ATOM_URL VARCHAR(2048),
	ITEM_ID VARCHAR(36), -- NEW
	ITEM_CORRELATION_ID VARCHAR(36), -- NEW	
	CREATION_DATE TIMESTAMP NOT NULL,
	BRIEF_DESC VARCHAR(512),
	ACTOR_UUID VARCHAR(36),
	EVENT_RECORD_UUID VARCHAR(36) NOT NULL,
	TAGS VARCHAR(1024),
	META_TEMPLATE VARCHAR(4096) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE VARCHAR(1024),
	R_META_TEMPLATE VARCHAR(4096),
	R_TEXT_META_TEMPLATE VARCHAR(1024),
	N_COMMENTS SMALLINT NOT NULL DEFAULT 0, -- NEW
	N_RECOMMANDATIONS SMALLINT NOT NULL DEFAULT 0, -- NEW
	IS_COMMUNITY_STORY SMALLINT DEFAULT 0,
	ITEM_CORRELATION_NAME VARCHAR(256),
	SOURCE_TYPE SMALLINT,
	CONSTRAINT   	CK_SRC4_TYPE
    				CHECK
    				(SOURCE_TYPE = 4)
)
IN NEWS32TABSPACE;

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_WIK
    ADD CONSTRAINT "PK_WIK_STORY_ID" PRIMARY KEY("STORY_ID");

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_WIK_DATE
	ON HOMEPAGE.NR_SRC_STORIES_WIK(CREATION_DATE DESC);

CREATE INDEX HOMEPAGE.SRC_WIK_STORIES_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_WIK (CONTAINER_ID);

CREATE INDEX HOMEPAGE.SRC_WIK_STORIES_ITEM_ID
    ON HOMEPAGE.NR_SRC_STORIES_WIK (ITEM_ID);

CREATE INDEX HOMEPAGE.SRC_WIK_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_WIK (ITEM_CORRELATION_ID);

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_WIK_SIDX
    ON HOMEPAGE.NR_SRC_STORIES_WIK (SOURCE_TYPE);
    
----------------------------------------------------------------------
-- 5) HOMEPAGE.NR_SRC_STORIES_PRF
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_PRF (
	STORY_ID VARCHAR(36) NOT NULL,
	EVENT_NAME VARCHAR(256) NOT NULL,
	SOURCE VARCHAR(36),
	CONTAINER_ID VARCHAR(256),	
	CONTAINER_NAME VARCHAR(256),
	CONTAINER_URL VARCHAR(2048),
	ITEM_NAME VARCHAR(256),
	ITEM_URL VARCHAR(2048),
	ITEM_ATOM_URL VARCHAR(2048),
	ITEM_ID VARCHAR(36), -- NEW
	ITEM_CORRELATION_ID VARCHAR(36), -- NEW	
	CREATION_DATE TIMESTAMP NOT NULL,
	BRIEF_DESC VARCHAR(512),
	ACTOR_UUID VARCHAR(36),
	EVENT_RECORD_UUID VARCHAR(36) NOT NULL,
	TAGS VARCHAR(1024),
	META_TEMPLATE VARCHAR(4096) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE VARCHAR(1024),
	R_META_TEMPLATE VARCHAR(4096),
	R_TEXT_META_TEMPLATE VARCHAR(1024),
	N_COMMENTS SMALLINT NOT NULL DEFAULT 0, -- NEW
	N_RECOMMANDATIONS SMALLINT NOT NULL DEFAULT 0, -- NEW
	IS_COMMUNITY_STORY SMALLINT DEFAULT 0,
	ITEM_CORRELATION_NAME VARCHAR(256),
	SOURCE_TYPE SMALLINT,
	CONSTRAINT   	CK_SRC5_TYPE
    				CHECK
    				(SOURCE_TYPE = 5)
)
IN NEWS32TABSPACE;

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_PRF
    ADD CONSTRAINT "PK_PRF_STORY_ID" PRIMARY KEY("STORY_ID");

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_PRF_DATE
	ON HOMEPAGE.NR_SRC_STORIES_PRF(CREATION_DATE DESC);

CREATE INDEX HOMEPAGE.SRC_PRF_STORIES_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_PRF (CONTAINER_ID);

CREATE INDEX HOMEPAGE.SRC_PRF_STORIES_ITEM_ID
    ON HOMEPAGE.NR_SRC_STORIES_PRF (ITEM_ID);

CREATE INDEX HOMEPAGE.SRC_PRF_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_PRF (ITEM_CORRELATION_ID); 

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_PRF_SIDX
    ON HOMEPAGE.NR_SRC_STORIES_PRF (SOURCE_TYPE);    

----------------------------------------------------------------------
-- 6) HOMEPAGE.NR_SRC_STORIES_HP
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_HP (
	STORY_ID VARCHAR(36) NOT NULL,
	EVENT_NAME VARCHAR(256) NOT NULL,
	SOURCE VARCHAR(36),
	CONTAINER_ID VARCHAR(256),	
	CONTAINER_NAME VARCHAR(256),
	CONTAINER_URL VARCHAR(2048),
	ITEM_NAME VARCHAR(256),
	ITEM_URL VARCHAR(2048),
	ITEM_ATOM_URL VARCHAR(2048),
	ITEM_ID VARCHAR(36), -- NEW
	ITEM_CORRELATION_ID VARCHAR(36), -- NEW	
	CREATION_DATE TIMESTAMP NOT NULL,
	BRIEF_DESC VARCHAR(512),
	ACTOR_UUID VARCHAR(36),
	EVENT_RECORD_UUID VARCHAR(36) NOT NULL,
	TAGS VARCHAR(1024),
	META_TEMPLATE VARCHAR(4096) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE VARCHAR(1024),
	R_META_TEMPLATE VARCHAR(4096),
	R_TEXT_META_TEMPLATE VARCHAR(1024),
	N_COMMENTS SMALLINT NOT NULL DEFAULT 0, -- NEW
	N_RECOMMANDATIONS SMALLINT NOT NULL DEFAULT 0, -- NEW
	IS_COMMUNITY_STORY SMALLINT DEFAULT 0,
	ITEM_CORRELATION_NAME VARCHAR(256),
	SOURCE_TYPE SMALLINT,
	CONSTRAINT   	CK_SRC6_TYPE
    				CHECK
    				(SOURCE_TYPE = 6)
)
IN NEWS32TABSPACE;

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_HP
    ADD CONSTRAINT "PK_HP_STORY_ID" PRIMARY KEY("STORY_ID");

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_HP_DATE
	ON HOMEPAGE.NR_SRC_STORIES_HP(CREATION_DATE DESC);

CREATE INDEX HOMEPAGE.SRC_HP_STORIES_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_HP (CONTAINER_ID);

CREATE INDEX HOMEPAGE.SRC_HP_STORIES_ITEM_ID
    ON HOMEPAGE.NR_SRC_STORIES_HP (ITEM_ID);

CREATE INDEX HOMEPAGE.SRC_HP_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_HP (ITEM_CORRELATION_ID);

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_HP_SIDX
    ON HOMEPAGE.NR_SRC_STORIES_HP (SOURCE_TYPE);    

----------------------------------------------------------------------
-- 7) HOMEPAGE.NR_SRC_STORIES_DGR
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_DGR (
	STORY_ID VARCHAR(36) NOT NULL,
	EVENT_NAME VARCHAR(256) NOT NULL,
	SOURCE VARCHAR(36),
	CONTAINER_ID VARCHAR(256),	
	CONTAINER_NAME VARCHAR(256),
	CONTAINER_URL VARCHAR(2048),
	ITEM_NAME VARCHAR(256),
	ITEM_URL VARCHAR(2048),
	ITEM_ATOM_URL VARCHAR(2048),
	ITEM_ID VARCHAR(36), -- NEW
	ITEM_CORRELATION_ID VARCHAR(36), -- NEW	
	CREATION_DATE TIMESTAMP NOT NULL,
	BRIEF_DESC VARCHAR(512),
	ACTOR_UUID VARCHAR(36),
	EVENT_RECORD_UUID VARCHAR(36) NOT NULL,
	TAGS VARCHAR(1024),
	META_TEMPLATE VARCHAR(4096) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE VARCHAR(1024),
	R_META_TEMPLATE VARCHAR(4096),
	R_TEXT_META_TEMPLATE VARCHAR(1024),
	N_COMMENTS SMALLINT NOT NULL DEFAULT 0, -- NEW
	N_RECOMMANDATIONS SMALLINT NOT NULL DEFAULT 0, -- NEW
	IS_COMMUNITY_STORY SMALLINT DEFAULT 0,
	ITEM_CORRELATION_NAME VARCHAR(256),
	SOURCE_TYPE SMALLINT,
	CONSTRAINT   	CK_SRC7_TYPE
    				CHECK
    				(SOURCE_TYPE = 7)
)
IN NEWS32TABSPACE;

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_DGR
    ADD CONSTRAINT "PK_DGR_STORY_ID" PRIMARY KEY("STORY_ID");

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_DGR_DATE
	ON HOMEPAGE.NR_SRC_STORIES_DGR(CREATION_DATE DESC);

CREATE INDEX HOMEPAGE.SRC_DGR_STORIES_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_DGR (CONTAINER_ID);

CREATE INDEX HOMEPAGE.SRC_DGR_STORIES_ITEM_ID
    ON HOMEPAGE.NR_SRC_STORIES_DGR (ITEM_ID);

CREATE INDEX HOMEPAGE.SRC_DGR_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_DGR (ITEM_CORRELATION_ID);

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_DGR_SIDX
    ON HOMEPAGE.NR_SRC_STORIES_DGR (SOURCE_TYPE);    

----------------------------------------------------------------------
-- 8) HOMEPAGE.NR_SRC_STORIES_FILE
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_FILE (
	STORY_ID VARCHAR(36) NOT NULL,
	EVENT_NAME VARCHAR(256) NOT NULL,
	SOURCE VARCHAR(36),
	CONTAINER_ID VARCHAR(256),	
	CONTAINER_NAME VARCHAR(256),
	CONTAINER_URL VARCHAR(2048),
	ITEM_NAME VARCHAR(256),
	ITEM_URL VARCHAR(2048),
	ITEM_ATOM_URL VARCHAR(2048),
	ITEM_ID VARCHAR(36), -- NEW
	ITEM_CORRELATION_ID VARCHAR(36), -- NEW	
	CREATION_DATE TIMESTAMP NOT NULL,
	BRIEF_DESC VARCHAR(512),
	ACTOR_UUID VARCHAR(36),
	EVENT_RECORD_UUID VARCHAR(36) NOT NULL,
	TAGS VARCHAR(1024),
	META_TEMPLATE VARCHAR(4096) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE VARCHAR(1024),
	R_META_TEMPLATE VARCHAR(4096),
	R_TEXT_META_TEMPLATE VARCHAR(1024),
	N_COMMENTS SMALLINT NOT NULL DEFAULT 0, -- NEW
	N_RECOMMANDATIONS SMALLINT NOT NULL DEFAULT 0, -- NEW
	IS_COMMUNITY_STORY SMALLINT DEFAULT 0,
	ITEM_CORRELATION_NAME VARCHAR(256),
	SOURCE_TYPE SMALLINT,
	CONSTRAINT   	CK_SRC8_TYPE
    				CHECK
    				(SOURCE_TYPE = 8)
)
IN NEWS32TABSPACE;

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_FILE
    ADD CONSTRAINT "PK_FILE_STORY_ID" PRIMARY KEY("STORY_ID");

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_FILE_DATE
	ON HOMEPAGE.NR_SRC_STORIES_FILE(CREATION_DATE DESC);

CREATE INDEX HOMEPAGE.SRC_FILE_STORIES_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_FILE (CONTAINER_ID);

CREATE INDEX HOMEPAGE.SRC_FILE_STORIES_ITEM_ID
    ON HOMEPAGE.NR_SRC_STORIES_FILE (ITEM_ID);

CREATE INDEX HOMEPAGE.SRC_FILE_STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_FILE (ITEM_CORRELATION_ID);

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_FILE_SIDX
    ON HOMEPAGE.NR_SRC_STORIES_FILE (SOURCE_TYPE);    

----------------------------------------------------------------------
-- 9) HOMEPAGE.NR_SRC_STORIES_FRM 
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SRC_STORIES_FRM  (
	STORY_ID VARCHAR(36) NOT NULL,
	EVENT_NAME VARCHAR(256) NOT NULL,
	SOURCE VARCHAR(36),
	CONTAINER_ID VARCHAR(256),	
	CONTAINER_NAME VARCHAR(256),
	CONTAINER_URL VARCHAR(2048),
	ITEM_NAME VARCHAR(256),
	ITEM_URL VARCHAR(2048),
	ITEM_ATOM_URL VARCHAR(2048),
	ITEM_ID VARCHAR(36), -- NEW
	ITEM_CORRELATION_ID VARCHAR(36), -- NEW	
	CREATION_DATE TIMESTAMP NOT NULL,
	BRIEF_DESC VARCHAR(512),
	ACTOR_UUID VARCHAR(36),
	EVENT_RECORD_UUID VARCHAR(36) NOT NULL,
	TAGS VARCHAR(1024),
	META_TEMPLATE VARCHAR(4096) DEFAULT '' NOT NULL,
	TEXT_META_TEMPLATE VARCHAR(1024),
	R_META_TEMPLATE VARCHAR(4096),
	R_TEXT_META_TEMPLATE VARCHAR(1024),
	N_COMMENTS SMALLINT NOT NULL DEFAULT 0, -- NEW
	N_RECOMMANDATIONS SMALLINT NOT NULL DEFAULT 0, -- NEW
	IS_COMMUNITY_STORY SMALLINT DEFAULT 0,
	ITEM_CORRELATION_NAME VARCHAR(256),
	SOURCE_TYPE SMALLINT,
	CONSTRAINT   	CK_SRC9_TYPE
    				CHECK
    				(SOURCE_TYPE = 9)
)
IN NEWS32TABSPACE;

ALTER TABLE HOMEPAGE.NR_SRC_STORIES_FRM 
    ADD CONSTRAINT "PK_FRM_STORY_ID" PRIMARY KEY("STORY_ID");

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_FRM_DATE
	ON HOMEPAGE.NR_SRC_STORIES_FRM (CREATION_DATE DESC);

CREATE INDEX HOMEPAGE.SRC_FRM_CONTAINED_ID
    ON HOMEPAGE.NR_SRC_STORIES_FRM  (CONTAINER_ID);

CREATE INDEX HOMEPAGE.SRC_FRM_ITEM_ID
    ON HOMEPAGE.NR_SRC_STORIES_FRM  (ITEM_ID);

CREATE INDEX HOMEPAGE.SRC_FRM_ITEM_CORR_ID
    ON HOMEPAGE.NR_SRC_STORIES_FRM  (ITEM_CORRELATION_ID);

CREATE INDEX HOMEPAGE.NR_SRC_STORIES_FRM_SIDX
    ON HOMEPAGE.NR_SRC_STORIES_FRM (SOURCE_TYPE);

---------------------------------------------------------------------------------
-- 4) RUNNING DATA MIGRATION
---------------------------------------------------------------------------------

------------
--- START INSERT NR_SOURCE_TYPE
------------

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE_TYPE_NAME, SOURCE_TYPE_DESC)
VALUES ('activities_c9cax4cc4x80bx51af2ddef2c', 1, '%activities', 'activities');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE_TYPE_NAME, SOURCE_TYPE_DESC)
VALUES ('blogs________0f1xc9caxcc4x8b0bx51af2', 2, '%blogs', 'blogs');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE_TYPE_NAME, SOURCE_TYPE_DESC)
VALUES ('communities____f1xc9caxcc48b0bx51af2', 3, '%communities', 'communities');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE_TYPE_NAME, SOURCE_TYPE_DESC)
VALUES ('wikis________dfdxc9cax4cc4xb0bx51af2', 4, '%wikis', 'wikis');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE_TYPE_NAME, SOURCE_TYPE_DESC)
VALUES ('profiles____________fdfdc98b0bx51af2', 5, '%profiles', 'profiles');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE_TYPE_NAME, SOURCE_TYPE_DESC)
VALUES ('homepage_0f1xc9cax4cc4x8cdb0bx51f2dd', 6, '%homepage', 'homepage');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE_TYPE_NAME, SOURCE_TYPE_DESC)
VALUES ('dogear_0f1xc9cax4cc4x8cdb0bx51f2d', 7, '%dogear', 'dogear');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE_TYPE_NAME, SOURCE_TYPE_DESC)
VALUES ('files____________fdfdc9cax8b0bx51af2', 8, '%files', 'files');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE_TYPE_NAME, SOURCE_TYPE_DESC)
VALUES ('forums____________fdfdc9cax80bx51af2', 9, '%forums', 'forums');
------------
--- END INSERT NR_SOURCE_TYPE
------------

COMMIT;

--------------------------------------------------------------------------------
-- START MIGRATING OLD DATA FROM THE NR_STORIES TABLE TO THE PARTITIONED TABLES
--------------------------------------------------------------------------------

-- PARTITIONING MAIN TABLE
ALTER TABLE HOMEPAGE.NR_STORIES
	ADD COLUMN SOURCE_TYPE SMALLINT;

-- partitioned update	



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


   

--------------------------------------------
-- ADDING SOURCE_TYPE - NR_STORIES_CONTENT (THIS IS BASED ON A JOIN WITH NR_STORIES
--------------------------------------------	
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT
	ADD COLUMN SOURCE_TYPE SMALLINT;
COMMIT;	

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

-- COPYING BACK THE DATA

-- [START]  Working on HOMEPAGE.NR_SRC_STORIES_ACT
reorg table HOMEPAGE.NR_SRC_STORIES_ACT;
commit; 

EXPORT TO data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
)
 SELECT 
  	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
  FROM HOMEPAGE.NR_STORIES WHERE SOURCE_TYPE = 1; 



IMPORT FROM data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
) COMMITCOUNT 1000 
INSERT INTO HOMEPAGE.NR_SRC_STORIES_ACT ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
); 
commit; 

reorg table HOMEPAGE.NR_SRC_STORIES_ACT;
commit; 

-- [END]  Working on HOMEPAGE.NR_SRC_STORIES_ACT




-- [START]  Working on HOMEPAGE.NR_SRC_STORIES_BLG
reorg table HOMEPAGE.NR_SRC_STORIES_BLG;
commit; 

EXPORT TO data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
)
 SELECT 
  	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
  FROM HOMEPAGE.NR_STORIES WHERE SOURCE_TYPE = 2; 



IMPORT FROM data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
) COMMITCOUNT 1000 
INSERT INTO HOMEPAGE.NR_SRC_STORIES_BLG ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
); 
commit; 

reorg table HOMEPAGE.NR_SRC_STORIES_BLG;
commit; 

-- [END]  Working on HOMEPAGE.NR_SRC_STORIES_BLG


-- [START]  Working on HOMEPAGE.NR_SRC_STORIES_COM
reorg table HOMEPAGE.NR_SRC_STORIES_COM;
commit; 

EXPORT TO data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
)
 SELECT 
  	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
  FROM HOMEPAGE.NR_STORIES WHERE SOURCE_TYPE = 3; 



IMPORT FROM data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
) COMMITCOUNT 1000 
INSERT INTO HOMEPAGE.NR_SRC_STORIES_COM ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
); 
commit; 

reorg table HOMEPAGE.NR_SRC_STORIES_COM;
commit; 

-- [END]  Working on HOMEPAGE.NR_SRC_STORIES_COM


-- [START]  Working on HOMEPAGE.NR_SRC_STORIES_WIK
reorg table HOMEPAGE.NR_SRC_STORIES_WIK;
commit; 

EXPORT TO data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
)
 SELECT 
  	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
  FROM HOMEPAGE.NR_STORIES WHERE SOURCE_TYPE = 4; 



IMPORT FROM data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
) COMMITCOUNT 1000 
INSERT INTO HOMEPAGE.NR_SRC_STORIES_WIK ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
); 
commit; 

reorg table HOMEPAGE.NR_SRC_STORIES_WIK;
commit; 

-- [END]  Working on HOMEPAGE.NR_SRC_STORIES_WIK


-- [START]  Working on HOMEPAGE.NR_SRC_STORIES_PRF
reorg table HOMEPAGE.NR_SRC_STORIES_PRF;
commit; 

EXPORT TO data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
)
 SELECT 
  	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
  FROM HOMEPAGE.NR_STORIES WHERE SOURCE_TYPE = 5; 



IMPORT FROM data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
) COMMITCOUNT 1000 
INSERT INTO HOMEPAGE.NR_SRC_STORIES_PRF ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
); 
commit; 

reorg table HOMEPAGE.NR_SRC_STORIES_PRF;
commit; 

-- [END]  Working on HOMEPAGE.NR_SRC_STORIES_PRF


-- [START]  Working on HOMEPAGE.NR_SRC_STORIES_HP
reorg table HOMEPAGE.NR_SRC_STORIES_HP;
commit; 

EXPORT TO data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
)
 SELECT 
  	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
  FROM HOMEPAGE.NR_STORIES WHERE SOURCE_TYPE = 6; 



IMPORT FROM data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
) COMMITCOUNT 1000 
INSERT INTO HOMEPAGE.NR_SRC_STORIES_HP ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
); 
commit; 

reorg table HOMEPAGE.NR_SRC_STORIES_HP;
commit; 

-- [END]  Working on HOMEPAGE.NR_SRC_STORIES_HP


-- [START]  Working on HOMEPAGE.NR_SRC_STORIES_DGR
reorg table HOMEPAGE.NR_SRC_STORIES_DGR;
commit; 

EXPORT TO data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
)
 SELECT 
  	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
  FROM HOMEPAGE.NR_STORIES WHERE SOURCE_TYPE = 7; 



IMPORT FROM data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
) COMMITCOUNT 1000 
INSERT INTO HOMEPAGE.NR_SRC_STORIES_DGR ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
); 
commit; 

reorg table HOMEPAGE.NR_SRC_STORIES_DGR;
commit; 

-- [END]  Working on HOMEPAGE.NR_SRC_STORIES_DGR


-- [START]  Working on HOMEPAGE.NR_SRC_STORIES_FILE
reorg table HOMEPAGE.NR_SRC_STORIES_FILE;
commit; 

EXPORT TO data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
)
 SELECT 
  	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
  FROM HOMEPAGE.NR_STORIES WHERE SOURCE_TYPE = 8; 



IMPORT FROM data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
) COMMITCOUNT 1000 
INSERT INTO HOMEPAGE.NR_SRC_STORIES_FILE ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
); 
commit; 

reorg table HOMEPAGE.NR_SRC_STORIES_FILE;
commit; 

-- [END]  Working on HOMEPAGE.NR_SRC_STORIES_FILE


-- [START]  Working on HOMEPAGE.NR_SRC_STORIES_FRM
reorg table HOMEPAGE.NR_SRC_STORIES_FRM;
commit; 

EXPORT TO data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
)
 SELECT 
  	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
  FROM HOMEPAGE.NR_STORIES WHERE SOURCE_TYPE = 9; 



IMPORT FROM data.tmp.ixf OF IXF METHOD N ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE
) COMMITCOUNT 1000 
INSERT INTO HOMEPAGE.NR_SRC_STORIES_FRM ( 
 	 STORY_ID, EVENT_NAME, SOURCE, CONTAINER_ID, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, 
 	 ITEM_ID, ITEM_CORRELATION_ID, CREATION_DATE, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, TAGS, META_TEMPLATE, 
 	 TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, IS_COMMUNITY_STORY, 
  	 ITEM_CORRELATION_NAME, SOURCE_TYPE 
); 
commit; 

reorg table HOMEPAGE.NR_SRC_STORIES_FRM;
commit; 

-- [END]  Working on HOMEPAGE.NR_SRC_STORIES_FRM



---------------------------------------------------------------------------------
-- DROPPING OLD TABLE TO CREATE A NEW VIEW (those constraints have already been deleted)
---------------------------------------------------------------------------------
--ALTER TABLE HOMEPAGE.NR_COMM_STORIES DROP CONSTRAINT "FK_COMM_STORY_ID";
--ALTER TABLE HOMEPAGE.NR_ORGPERSON_STORIES DROP CONSTRAINT "FK_ORGP_STORY_ID";
--ALTER TABLE HOMEPAGE.NR_COMM_PERSON_STORIES DROP CONSTRAINT "FK_FCP_STORY_ID";

--ALTER TABLE HOMEPAGE.NR_RESPONSES_STORIES DROP CONSTRAINT "FK_RESP_STORY_ID";
--ALTER TABLE HOMEPAGE.NR_PROFILES_STORIES DROP CONSTRAINT "FK_PROF_STORY_ID";
--ALTER TABLE HOMEPAGE.NR_COMMUNITIES_STORIES DROP CONSTRAINT "FK_COM_STORY_ID";
--ALTER TABLE HOMEPAGE.NR_ACTIVITIES_STORIES DROP CONSTRAINT "FK_ACT_STORY_ID";
--ALTER TABLE HOMEPAGE.NR_BLOGS_STORIES DROP CONSTRAINT "FK_BLOGS_STORY_ID";
--ALTER TABLE HOMEPAGE.NR_BOOKMARKS_STORIES DROP CONSTRAINT "FK_BOOKS_STORY_ID";
--ALTER TABLE HOMEPAGE.NR_FILES_STORIES DROP CONSTRAINT "FK_FILES_STORY_ID";
--ALTER TABLE HOMEPAGE.NR_FORUMS_STORIES DROP CONSTRAINT "FK_FORUMS_STORY_ID";
--ALTER TABLE HOMEPAGE.NR_WIKIS_STORIES DROP CONSTRAINT "FK_WIKIS_STORY_ID";
--ALTER TABLE HOMEPAGE.NR_TAGS_STORIES DROP CONSTRAINT "FK_TAGS_STORY_ID";
COMMIT;

DROP TABLE HOMEPAGE.NR_STORIES;
COMMIT;

--------------------------------------------------------------------------------------------------------------
-- CREATE THE VIEW FOR ALL THE SOURCE STORIES
--------------------------------------------------------------------------------------------------------------
CREATE VIEW HOMEPAGE.NR_STORIES AS (
    SELECT * FROM HOMEPAGE.NR_SRC_STORIES_ACT
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_SRC_STORIES_BLG
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_SRC_STORIES_COM
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_SRC_STORIES_WIK
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_SRC_STORIES_PRF
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_SRC_STORIES_HP
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_SRC_STORIES_DGR
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_SRC_STORIES_FILE
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_SRC_STORIES_FRM
);

--ALTER VIEW HOMEPAGE.NR_STORIES ENABLE QUERY OPTIMIZATION;









-------------------------------------------------
-- 5) REORG AND UPDATE STATS
-------------------------------------------------
--reorg table HOMEPAGE.NR_SRC_STORIES_ACT use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_ACT;

--reorg table HOMEPAGE.NR_SRC_STORIES_BLG use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_BLG;

--reorg table HOMEPAGE.NR_SRC_STORIES_COM use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_COM;

--reorg table HOMEPAGE.NR_SRC_STORIES_WIK use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_WIK;

--reorg table HOMEPAGE.NR_SRC_STORIES_PRF use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_PRF;

--reorg table HOMEPAGE.NR_SRC_STORIES_HP use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_HP;

--reorg table HOMEPAGE.NR_SRC_STORIES_DGR use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_DGR;

--reorg table HOMEPAGE.NR_SRC_STORIES_FILE use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_FILE;

--reorg table HOMEPAGE.NR_SRC_STORIES_FRM use NEWS32TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_SRC_STORIES_FRM;

runstats on table HOMEPAGE.NR_SRC_STORIES_ACT with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SRC_STORIES_BLG with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SRC_STORIES_COM with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SRC_STORIES_WIK with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SRC_STORIES_PRF with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SRC_STORIES_HP with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SRC_STORIES_DGR with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SRC_STORIES_FILE with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SRC_STORIES_FRM with distribution and detailed indexes all allow write access;

	