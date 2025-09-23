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

-----------------------------------------
-- 1) HOMEPAGE.EMD_EMAIL_PREFS
-----------------------------------------
ALTER TABLE HOMEPAGE.EMD_EMAIL_PREFS
    DROP CONSTRAINT UNIQUE_PREFS;

CREATE UNIQUE INDEX HOMEPAGE.EMD_EMAIL_PREFS_PER
    ON HOMEPAGE.EMD_EMAIL_PREFS (PERSON_ID);

------------------------------------------------
-- 2) NR_NEWS_SAVED
------------------------------------------------
CREATE INDEX HOMEPAGE.SAVED_ITEM_ID
    ON HOMEPAGE.NR_NEWS_SAVED (ITEM_ID);

CREATE INDEX HOMEPAGE.SAVED_STORY_ID
    ON HOMEPAGE.NR_NEWS_SAVED (NEWS_STORY_ID);

------------------------------------------------
-- 3) NR_NEWS_DISCOVERY
------------------------------------------------
CREATE INDEX HOMEPAGE.DISCOVERY_ITEM_ID
    ON HOMEPAGE.NR_NEWS_DISCOVERY (ITEM_ID);

CREATE INDEX HOMEPAGE.DISCOVERY_STORY_ID
    ON HOMEPAGE.NR_NEWS_DISCOVERY (NEWS_STORY_ID);

CREATE INDEX HOMEPAGE.DISCOVERY_CONTAINER_ID
    ON HOMEPAGE.NR_NEWS_DISCOVERY (CONTAINER_ID);

------------------------------------------------
-- 4) NR_NEWS_STATUS_COMMENT
------------------------------------------------
CREATE INDEX HOMEPAGE.STATUS_COMMENT_ITEM_ID
    ON HOMEPAGE.NR_NEWS_STATUS_COMMENT (ITEM_ID);

----------------------------------------------------------------------
-- 5) HOMEPAGE.NR_STORIES
----------------------------------------------------------------------
CREATE INDEX HOMEPAGE.STORIES_CONTAINED_ID
    ON HOMEPAGE.NR_STORIES (CONTAINER_ID);

CREATE INDEX HOMEPAGE.STORIES_ITEM_ID
    ON HOMEPAGE.NR_STORIES (ITEM_ID);

CREATE INDEX HOMEPAGE.STORIES_ITEM_CORR_ID
    ON HOMEPAGE.NR_STORIES (ITEM_CORRELATION_ID);

----------------------------------------------------------------------
-- 6) HOMEPAGE.NR_COMM_STORIES
----------------------------------------------------------------------
CREATE INDEX HOMEPAGE.COMM_STORIES_ITEM_ID
    ON HOMEPAGE.NR_COMM_STORIES (ITEM_ID);

------------------------------------------------
-- 7) NR_STORIES_CONTENT
------------------------------------------------
CREATE INDEX HOMEPAGE.STORIES_CONTENT_STORY
    ON HOMEPAGE.NR_STORIES_CONTENT (STORY_ID);

----------------------------------------------------------------------
-- 8) HOMEPAGE.NR_COMM_PERSON_STORIES 
----------------------------------------------------------------------
CREATE INDEX HOMEPAGE.COMM_PERSON_STORIES_ITEM_ID
    ON HOMEPAGE.NR_COMM_PERSON_STORIES (ITEM_ID);

--------------------------------------------------------------------------
-- 1 NR_RESPONSES_STORIES
--------------------------------------------------------------------------
CREATE INDEX HOMEPAGE.RESPONSES_STORIES_ITEM_ID
    ON HOMEPAGE.NR_RESPONSES_STORIES (ITEM_ID);

--------------------------------------------------------------------------
-- 2 NR_PROFILES_STORIES
--------------------------------------------------------------------------
CREATE INDEX HOMEPAGE.PROFILES_STORIES_ITEM_ID
    ON HOMEPAGE.NR_PROFILES_STORIES (ITEM_ID);

--------------------------------------------------------------------------
-- 3 NR_COMMUNITIES_STORIES
--------------------------------------------------------------------------
CREATE INDEX HOMEPAGE.COMMUNITIES_STORIES_ITEM_ID
    ON HOMEPAGE.NR_COMMUNITIES_STORIES (ITEM_ID);

--------------------------------------------------------------------------
-- 4 NR_ACTIVITIES_STORIES
--------------------------------------------------------------------------
CREATE INDEX HOMEPAGE.ACTIVITIES_STORIES_ITEM_ID
    ON HOMEPAGE.NR_ACTIVITIES_STORIES (ITEM_ID);

--------------------------------------------------------------------------
-- 5 NR_BLOGS_STORIES
--------------------------------------------------------------------------
CREATE INDEX HOMEPAGE.BLOGS_STORIES_ITEM_ID
    ON HOMEPAGE.NR_BLOGS_STORIES (ITEM_ID);

--------------------------------------------------------------------------
-- 6 NR_BOOKMARKS_STORIES
--------------------------------------------------------------------------
CREATE INDEX HOMEPAGE.BOOKMARKS_STORIES_ITEM_ID
    ON HOMEPAGE.NR_BOOKMARKS_STORIES (ITEM_ID);

--------------------------------------------------------------------------
-- 7 NR_FILES_STORIES
--------------------------------------------------------------------------
CREATE INDEX HOMEPAGE.FILES_STORIES_ITEM_ID
    ON HOMEPAGE.NR_FILES_STORIES (ITEM_ID);

--------------------------------------------------------------------------
-- 8 NR_FORUMS_STORIES
--------------------------------------------------------------------------
CREATE INDEX HOMEPAGE.FORUMS_STORIES_ITEM_ID
    ON HOMEPAGE.NR_FORUMS_STORIES (ITEM_ID);

--------------------------------------------------------------------------
-- 9 NR_WIKIS_STORIES
--------------------------------------------------------------------------
CREATE INDEX HOMEPAGE.WIKIS_STORIES_ITEM_ID
    ON HOMEPAGE.NR_WIKIS_STORIES (ITEM_ID);

--------------------------------------------------------------------------
-- 10 NR_TAGS_STORIES
--------------------------------------------------------------------------
CREATE INDEX HOMEPAGE.TAGS_STORIES_ITEM_ID
    ON HOMEPAGE.NR_TAGS_STORIES (ITEM_ID);


--------------------------------------------------------------------------
-- RUNSTATS
--------------------------------------------------------------------------
    
runstats on table HOMEPAGE.NR_NEWS_SAVED with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_NEWS_DISCOVERY with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NR_NEWS_STATUS_COMMENT with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NR_STORIES_CONTENT with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_COMM_STORIES with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_STORIES with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_COMM_PERSON_STORIES with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.EMD_EMAIL_PREFS with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NR_RESPONSES_STORIES with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_PROFILES_STORIES with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_COMMUNITIES_STORIES with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_ACTIVITIES_STORIES with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_BLOGS_STORIES with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_BOOKMARKS_STORIES with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_FILES_STORIES with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_FORUMS_STORIES with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_WIKIS_STORIES with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_TAGS_STORIES with distribution and detailed indexes all allow write access;    