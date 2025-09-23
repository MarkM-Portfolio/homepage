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
-- INCLUDE FIXUP 451 FOR HP
------------------------------------------------

{include.hp-fixup451.sql}
{include.news-fixup451.sql}

{include.news-fixup453.sql}

{include.news-fixup455.sql}

{include.news-fixup456.sql}

-- news-fixup457.sql is not needed as it is part of CR4

{include.hp-fixup460.sql}



---------------------------------------------------------------------------------
------------------------ START NEWS FIXUP 460 -----------------------------------
---------------------------------------------------------------------------------

-----------------------------------------------------------
-- [START] 98254: fixup460 remove MAX_UPDATE_FOR_READER	
-----------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_WIKIS_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_TOPICS_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_TAGS_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_STATUS_UPDATE_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_SAVED_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_PROFILES_VIEW DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_PROFILES_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_NOTIFICATION_SENT_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_MENTIONS_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_FORUMS_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_FILES_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_EXTERNAL_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_DISCOVERY_VIEW DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_COMMUNITIES_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_BOOKMARKS_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_BLOGS_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS DROP COLUMN MAX_UPDATE_FOR_READER;
COMMIT;

-----------------------------------------------------------
-- [END] 98254: fixup460 remove MAX_UPDATE_FOR_READER	
-----------------------------------------------------------

--98630: [fixup460] IMG_CACHE  table Create a new field to store HASH values for the URL. That field need to be indexed. 
DELETE FROM HOMEPAGE.IMG_CACHE;
COMMIT;

ALTER TABLE HOMEPAGE.IMG_CACHE ADD ORIGINAL_URL_MD5 char(32) NOT NULL;
COMMIT;

CREATE INDEX HOMEPAGE.IMG_CACHE_URL_IDX
	ON HOMEPAGE.IMG_CACHE (ORIGINAL_URL_MD5, ORGANIZATION_ID) TABLESPACE "NEWSINDEXTABSPACE";
COMMIT;

------------------------------------------------
-- INCLUDING A SPECIFIC MIGRATION PATH FOR ON-PREMISIS
------------------------------------------------
-- this is part of fixup460
{include.onpremises-45-50-updateNullOrgId.sql}

{include.hp-fixup462.sql}

{include.news-fixup462.sql}

-- added manualy from fixup462
DROP INDEX HOMEPAGE.PERSON_EXID;
COMMIT;

CREATE UNIQUE INDEX HOMEPAGE.PERSON_EXID
    ON HOMEPAGE.PERSON (EXID) TABLESPACE "HPNTINDEXTABSPACE";
COMMIT;


{include.search-fixup462.sql}


-- removing hp-fixup463.sql as it is not needed
--{include.hp-fixup463.sql}

{include.hp-fixup464.sql}
{include.news-fixup464.sql}
{include.search-fixup464.sql}

{include.news-fixup465.sql}

{include.hp-fixup466.sql}

-- NO FIXUP 467: that script is used for merge purpose and it is just internal

{include.hp-fixup468.sql}

{include.news-fixup468.sql}

{include.search-fixup468.sql}

{include.news-fixup469.sql}

{include.hp-fixup470.sql}

{include.news-fixup470.sql}

-- hp-fixup471.sql is replaced from hp-fixup475


{include.news-fixup472.sql}

{include.news-fixup473.sql}

{include.news-fixup474.sql}

{include.hp-fixup475.sql}

{include.news-fixup475.sql}



------------------------------------------------

UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 475 , RELEASEVER = '5.0.0.0'
WHERE   DBSCHEMAVER = 213;

--------------------------------------
-- COMMIT
--------------------------------------

COMMIT;


--------------------------------------
-- DISCONNECT
--------------------------------------

DISCONNECT ALL;

QUIT;