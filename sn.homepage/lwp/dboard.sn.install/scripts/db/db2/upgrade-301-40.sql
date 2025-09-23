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
-- [START] INCLUDE BETA2 upgrade script
------------------------------------------------

{include.upgrade-301-40b2.sql}

------------------------------------------------
-- [END] INCLUDE BETA2 upgrade script
------------------------------------------------

-- 76996: numDb not updated by migration scripts
UPDATE DBM CFG USING NUMDB 12;
COMMIT;

-------------------------------------------------------------------------------------------------
-- ******************************************************************************************* --
-- ******************************************************************************************* --
-- ******************************************************************************************* --
-- ******************************************************************************************* --
-- ******************************************************************************************* --
-- ******************************************************************************************* --
-- ******************************************************************************************* --
-- ******************************************************************************************* --
-- ******************************************************************************************* --
-- ******************************************************************************************* --
-------------------------------------------------------------------------------------------------

-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- Starting with fixup script after 4.0 b2 - schema 101
-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++

------------------------------------------------
-- INCLUDE FIXUP 102 FOR HP
------------------------------------------------

{include.hp-fixup102.sql}


------------------------------------------------
-- INCLUDE FIXUP 102 FOR NEWS
------------------------------------------------

{include.news-fixup102.sql}

------------------------------------------------
-- INCLUDE FIXUP 103 FOR HP
------------------------------------------------

{include.hp-fixup103.sql}

------------------------------------------------
-- INCLUDE FIXUP 103 FOR NEWS
------------------------------------------------

{include.news-fixup103.sql}

------------------------------------------------
-- INCLUDE FIXUP 104 FOR HP
------------------------------------------------

{include.hp-fixup104.sql}

------------------------------------------------
-- INCLUDE FIXUP 105 FOR HP
------------------------------------------------

{include.hp-fixup105.sql}

------------------------------------------------
-- INCLUDE FIXUP 106 FOR HP
------------------------------------------------

{include.hp-fixup106.sql}

------------------------------------------------
-- INCLUDE FIXUP 109 FOR HP
------------------------------------------------

{include.hp-fixup109.sql}

------------------------------------------------
-- INCLUDE FIXUP 104 FOR NEWS
------------------------------------------------

{include.news-fixup104.sql}

------------------------------------------------
-- INCLUDE FIXUP 105 FOR NEWS
------------------------------------------------

{include.news-fixup105.sql}

------------------------------------------------
-- INCLUDE FIXUP 107 FOR NEWS
------------------------------------------------

{include.news-fixup107.sql}

------------------------------------------------
-- INCLUDE FIXUP 108 FOR NEWS
------------------------------------------------

{include.news-fixup108.sql}

------------------------------------------------
-- INCLUDE FIXUP 109 FOR NEWS
------------------------------------------------

{include.news-fixup109.sql}

------------------------------------------------
-- INCLUDE FIXUP 110 FOR NEWS
------------------------------------------------

{include.news-fixup110.sql}

------------------------------------------------
-- INCLUDE UPGRADE 40 b2 to 40  FOR SEARCH
------------------------------------------------

{include.search-upgrade-40b2-40.sql}

------------------------------------------------
-- INCLUDE FIXUP 103 FOR SEARCH
------------------------------------------------

{include.search-fixup103.sql}



----------------------------------------------------------------------------------------------------
-- TODO: MERGE THIS SCRIPT IN THE MAIN SCRIPT - FOR NOW LEAVE IT AT THE END
-- During java migration we perform some hevy tasks in moving records, inserting and updating them
-- For this reasone we need to remove them
-- After java migration we will insert them again with this script: post-java-migration-301-40b2-<dbms>.sql
----------------------------------------------------------------------------------------------------


-- Preparing NR_STORIES
ALTER TABLE HOMEPAGE.NR_STORIES DROP FOREIGN KEY FK_ENTRY_ID ;
COMMIT;

DROP INDEX HOMEPAGE.NR_STORIES_EIDX;
COMMIT;

DROP INDEX HOMEPAGE.NR_STORIES_ER_UUID;
COMMIT;

DROP INDEX HOMEPAGE.NR_STORIES_DATE;
COMMIT;

DROP INDEX HOMEPAGE.STORY_CONTAINED_ID;
COMMIT;

DROP INDEX HOMEPAGE.NR_STORY_CD_IDX;
COMMIT;

DROP INDEX HOMEPAGE.STORIES_EVENT_ITEM_ACTOR_IDX;
COMMIT;

CREATE INDEX HOMEPAGE.MIG_STORIES_ITEM_CD
	ON HOMEPAGE.NR_STORIES (ITEM_ID, CREATION_DATE DESC);
COMMIT;

--CREATE INDEX HOMEPAGE.MIG_STORIES_ITEM_ID ON
--	HOMEPAGE.NR_STORIES (ITEM_ID);
--COMMIT;

-- Preparing NR_ENTRIES and NR_ENTRIES_ARCHIVE
DROP INDEX HOMEPAGE.NR_ENTRIES_CONT;
COMMIT;

DROP INDEX HOMEPAGE.NR_ENTRIES_AR_CONT;
COMMIT;

-- Preparing NR_NOTIFICATION to speed up notification reads:
--CREATE INDEX HOMEPAGE.MIG_NOT_EXID_PER_ID 
--	ON HOMEPAGE.PERSON  (EXID, PERSON_ID);
--COMMIT;

CREATE INDEX HOMEPAGE.MIG_NOT_READS 
	ON HOMEPAGE.NT_NOTIFICATION (IS_DELETED,  NOTIFICATION_ID);
COMMIT;

-- drop fk for table: NR_AGGREGATED_READERS
ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS DROP CONSTRAINT FK_AGG_READERS_STR;
COMMIT;


--  [start indexes] NR_AGGREGATED_READERS
--DROP  INDEX HOMEPAGE.AGGREGATED_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.AGGREGATED_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.AGGREGATED_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.AGGREGATED_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.AGGREGATED_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.AGGREGATED_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.AGGREGATED_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.AGGREGATED_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.AGGREGATED_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.AGGREGATED_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.AGGREGATED_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_AGGREGATED_READERS



-- drop fk for table: NR_RESPONSES_READERS
ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS DROP CONSTRAINT FK_RES_READERS_STR;
COMMIT;


--  [start indexes] NR_RESPONSES_READERS
--DROP  INDEX HOMEPAGE.RESPONSES_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.RESPONSES_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.RESPONSES_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.RESPONSES_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.RESPONSES_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.RESPONSES_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.RESPONSES_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.RESPONSES_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.RESPONSES_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.RESPONSES_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.RESPONSES_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_RESPONSES_READERS



-- drop fk for table: NR_PROFILES_READERS
ALTER TABLE HOMEPAGE.NR_PROFILES_READERS DROP CONSTRAINT FK_PRF_READERS_STR;
COMMIT;


--  [start indexes] NR_PROFILES_READERS
--DROP  INDEX HOMEPAGE.PROFILES_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.PROFILES_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.PROFILES_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_PROFILES_READERS



-- drop fk for table: NR_COMMUNITIES_READERS
ALTER TABLE HOMEPAGE.NR_COMMUNITIES_READERS DROP CONSTRAINT FK_COM_READERS_STR;
COMMIT;


--  [start indexes] NR_COMMUNITIES_READERS
--DROP  INDEX HOMEPAGE.COMM_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.COMM_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.COMM_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.COMM_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.COMM_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.COMM_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.COMM_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.COMM_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.COMM_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.COMM_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.COMM_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_COMMUNITIES_READERS



-- drop fk for table: NR_ACTIVITIES_READERS
ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS DROP CONSTRAINT FK_ACI_READERS_STR;
COMMIT;


--  [start indexes] NR_ACTIVITIES_READERS
--DROP  INDEX HOMEPAGE.ACTIVITIES_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.ACTIVITIES_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.ACTIVITIES_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.ACTIVITIES_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.ACTIVITIES_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.ACTIVITIES_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.ACTIVITIES_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.ACTIVITIES_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.ACTIVITIES_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.ACTIVITIES_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.ACTIVITIES_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_ACTIVITIES_READERS



-- drop fk for table: NR_BLOGS_READERS
ALTER TABLE HOMEPAGE.NR_BLOGS_READERS DROP CONSTRAINT FK_BLG_READERS_STR;
COMMIT;


--  [start indexes] NR_BLOGS_READERS
--DROP  INDEX HOMEPAGE.BLOGS_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.BLOGS_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.BLOGS_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.BLOGS_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.BLOGS_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.BLOGS_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.BLOGS_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.BLOGS_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.BLOGS_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.BLOGS_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.BLOGS_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_BLOGS_READERS



-- drop fk for table: NR_BOOKMARKS_READERS
ALTER TABLE HOMEPAGE.NR_BOOKMARKS_READERS DROP CONSTRAINT FK_BKM_READERS_STR;
COMMIT;


--  [start indexes] NR_BOOKMARKS_READERS
--DROP  INDEX HOMEPAGE.BOOKMARKS_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.BOOKMARKS_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.BOOKMARKS_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.BOOKMARKS_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.BOOKMARKS_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.BOOKMARKS_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.BOOKMARKS_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.BOOKMARKS_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.BOOKMARKS_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.BOOKMARKS_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.BOOKMARKS_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_BOOKMARKS_READERS



-- drop fk for table: NR_FILES_READERS
ALTER TABLE HOMEPAGE.NR_FILES_READERS DROP CONSTRAINT FK_FIL_READERS_STR;
COMMIT;


--  [start indexes] NR_FILES_READERS
--DROP  INDEX HOMEPAGE.FILES_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.FILES_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.FILES_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.FILES_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.FILES_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.FILES_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.FILES_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.FILES_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.FILES_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.FILES_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.FILES_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_FILES_READERS



-- drop fk for table: NR_FORUMS_READERS
ALTER TABLE HOMEPAGE.NR_FORUMS_READERS DROP CONSTRAINT FK_FRM_READERS_STR;
COMMIT;


--  [start indexes] NR_FORUMS_READERS
--DROP  INDEX HOMEPAGE.FORUMS_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.FORUMS_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.FORUMS_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.FORUMS_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.FORUMS_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.FORUMS_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.FORUMS_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.FORUMS_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.FORUMS_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.FORUMS_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.FORUMS_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_FORUMS_READERS



-- drop fk for table: NR_WIKIS_READERS
ALTER TABLE HOMEPAGE.NR_WIKIS_READERS DROP CONSTRAINT FK_WIK_READERS_STR;
COMMIT;


--  [start indexes] NR_WIKIS_READERS
--DROP  INDEX HOMEPAGE.WIKIS_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.WIKIS_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.WIKIS_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.WIKIS_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.WIKIS_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.WIKIS_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.WIKIS_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.WIKIS_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.WIKIS_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.WIKIS_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.WIKIS_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_WIKIS_READERS



-- drop fk for table: NR_TAGS_READERS
ALTER TABLE HOMEPAGE.NR_TAGS_READERS DROP CONSTRAINT FK_TAG_READERS_STR;
COMMIT;


--  [start indexes] NR_TAGS_READERS
--DROP  INDEX HOMEPAGE.TAGS_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.TAGS_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.TAGS_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.TAGS_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.TAGS_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.TAGS_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.TAGS_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.TAGS_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.TAGS_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.TAGS_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.TAGS_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_TAGS_READERS



-- drop fk for table: NR_STATUS_UPDATE_READERS
ALTER TABLE HOMEPAGE.NR_STATUS_UPDATE_READERS DROP CONSTRAINT FK_STA_READERS_STR;
COMMIT;


--  [start indexes] NR_STATUS_UPDATE_READERS
--DROP  INDEX HOMEPAGE.STATUS_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.STATUS_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.STATUS_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.STATUS_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.STATUS_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.STATUS_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.STATUS_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.STATUS_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.STATUS_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.STATUS_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.STATUS_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_STATUS_UPDATE_READERS



-- drop fk for table: NR_EXTERNAL_READERS
ALTER TABLE HOMEPAGE.NR_EXTERNAL_READERS DROP CONSTRAINT FK_EXT_READERS_STR;
COMMIT;


--  [start indexes] NR_EXTERNAL_READERS
--DROP  INDEX HOMEPAGE.EXTERNAL_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.EXTERNAL_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.EXTERNAL_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.EXTERNAL_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.EXTERNAL_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.EXTERNAL_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.EXTERNAL_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.EXTERNAL_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.EXTERNAL_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.EXTERNAL_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.EXTERNAL_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_EXTERNAL_READERS



-- drop fk for table: NR_ACTIONABLE_READERS
ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS DROP CONSTRAINT FK_ACT_READERS_STR;
COMMIT;


--  [start indexes] NR_ACTIONABLE_READERS
--DROP  INDEX HOMEPAGE.ACTIONABLE_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.ACTIONABLE_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.ACTIONABLE_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.ACTIONABLE_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.ACTIONABLE_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.ACTIONABLE_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.ACTIONABLE_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.ACTIONABLE_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.ACTIONABLE_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.ACTIONABLE_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.ACTIONABLE_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_ACTIONABLE_READERS



-- drop fk for table: NR_DISCOVERY_VIEW
ALTER TABLE HOMEPAGE.NR_DISCOVERY_VIEW DROP CONSTRAINT FK_DIS_READERS_STR;
COMMIT;


--  [start indexes] NR_DISCOVERY_VIEW
--DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_RLL_IX;
COMMIT;

-- this is removed in 107
--DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_RDR_STR;
--COMMIT;

DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_RIR_IX;
COMMIT;

-- 109
DROP INDEX HOMEPAGE.DISCOVERY_VIEW_ACT_CD_VIS_IDX;
COMMIT;

--  [end indexes] NR_DISCOVERY_VIEW



-- drop fk for table: NR_PROFILES_VIEW
ALTER TABLE HOMEPAGE.NR_PROFILES_VIEW DROP CONSTRAINT FK_PRO_READERS_STR;
COMMIT;


--  [start indexes] NR_PROFILES_VIEW
--DROP  INDEX HOMEPAGE.PROFILES_VIEW_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_VIEW_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_VIEW_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_VIEW_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.PROFILES_VIEW_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_VIEW_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_VIEW_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_VIEW_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.PROFILES_VIEW_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_VIEW_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.PROFILES_VIEW_RIR_IX;
COMMIT;

--  [end indexes] NR_PROFILES_VIEW



-- drop fk for table: NR_NOTIFICATION_SENT_READERS
ALTER TABLE HOMEPAGE.NR_NOTIFICATION_SENT_READERS DROP CONSTRAINT FK_NOT_READERS_STR;
COMMIT;


--  [start indexes] NR_NOTIFICATION_SENT_READERS
--DROP  INDEX HOMEPAGE.NOTIFICA_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.NOTIFICA_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.NOTIFICA_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.NOTIFICA_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.NOTIFICA_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.NOTIFICA_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.NOTIFICA_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.NOTIFICA_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.NOTIFICA_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.NOTIFICA_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.NOTIFICA_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_NOTIFICATION_SENT_READERS



-- drop fk for table: NR_NOTIFICATION_RECEIV_READERS
ALTER TABLE HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS DROP CONSTRAINT FK_REC_READERS_STR;
COMMIT;


--  [start indexes] NR_NOTIFICATION_RECEIV_READERS
--DROP  INDEX HOMEPAGE.NOT_REC_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.NOT_REC_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.NOT_REC_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.NOT_REC_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.NOT_REC_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.NOT_REC_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.NOT_REC_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.NOT_REC_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.NOT_REC_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.NOT_REC_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.NOT_REC_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_NOTIFICATION_RECEIV_READERS



-- drop fk for table: NR_SAVED_READERS
ALTER TABLE HOMEPAGE.NR_SAVED_READERS DROP CONSTRAINT FK_SAV_READERS_STR;
COMMIT;


--  [start indexes] NR_SAVED_READERS
--DROP  INDEX HOMEPAGE.SAVED_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.SAVED_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.SAVED_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_SAVED_READERS



-- drop fk for table: NR_TOPICS_READERS
ALTER TABLE HOMEPAGE.NR_TOPICS_READERS DROP CONSTRAINT FK_TOP_READERS_STR;
COMMIT;


--  [start indexes] NR_TOPICS_READERS
--DROP  INDEX HOMEPAGE.TOPICS_READERS_STR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.TOPICS_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.TOPICS_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.TOPICS_READERS_SRC_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.TOPICS_READERS_AUT_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.TOPICS_READERS_RLL_IX;
COMMIT;

DROP  INDEX HOMEPAGE.TOPICS_READERS_RDR_STR;
COMMIT;

DROP  INDEX HOMEPAGE.TOPICS_READERS_DEL_SERV_IX;
COMMIT;

--DROP  INDEX HOMEPAGE.TOPICS_READERS_CL_RDR_IX;
--COMMIT;

DROP  INDEX HOMEPAGE.TOPICS_READERS_ROLLUP_IX;
COMMIT;

DROP  INDEX HOMEPAGE.TOPICS_READERS_RIR_IX;
COMMIT;

--  [end indexes] NR_TOPICS_READERS

--------------------------------------------------- ADDING A SPECIFIC INDEX USED BY MIGRATION ROLLUP ----------------------

--  [start indexes] NR_AGGREGATED_READERS
CREATE INDEX HOMEPAGE.AGGREGATED_READERS_MIG_IX ON HOMEPAGE.NR_AGGREGATED_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_AGGREGATED_READERS

--  [start indexes] NR_RESPONSES_READERS
CREATE INDEX HOMEPAGE.RESPONSES_READERS_MIG_IX ON HOMEPAGE.NR_RESPONSES_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_RESPONSES_READERS

--  [start indexes] NR_PROFILES_READERS
CREATE INDEX HOMEPAGE.PROFILES_READERS_MIG_IX ON HOMEPAGE.NR_PROFILES_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_PROFILES_READERS

--  [start indexes] NR_COMMUNITIES_READERS
CREATE INDEX HOMEPAGE.COMM_READERS_MIG_IX ON HOMEPAGE.NR_COMMUNITIES_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_COMMUNITIES_READERS

--  [start indexes] NR_ACTIVITIES_READERS
CREATE INDEX HOMEPAGE.ACTIVITIES_READERS_MIG_IX ON HOMEPAGE.NR_ACTIVITIES_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_ACTIVITIES_READERS

--  [start indexes] NR_BLOGS_READERS
CREATE INDEX HOMEPAGE.BLOGS_READERS_MIG_IX ON HOMEPAGE.NR_BLOGS_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_BLOGS_READERS

--  [start indexes] NR_BOOKMARKS_READERS
CREATE INDEX HOMEPAGE.BOOKMARKS_READERS_MIG_IX ON HOMEPAGE.NR_BOOKMARKS_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_BOOKMARKS_READERS

--  [start indexes] NR_FILES_READERS
CREATE INDEX HOMEPAGE.FILES_READERS_MIG_IX ON HOMEPAGE.NR_FILES_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_FILES_READERS

--  [start indexes] NR_FORUMS_READERS
CREATE INDEX HOMEPAGE.FORUMS_READERS_MIG_IX ON HOMEPAGE.NR_FORUMS_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_FORUMS_READERS

--  [start indexes] NR_WIKIS_READERS
CREATE INDEX HOMEPAGE.WIKIS_READERS_MIG_IX ON HOMEPAGE.NR_WIKIS_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_WIKIS_READERS

--  [start indexes] NR_TAGS_READERS
CREATE INDEX HOMEPAGE.TAGS_READERS_MIG_IX ON HOMEPAGE.NR_TAGS_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_TAGS_READERS

--  [start indexes] NR_STATUS_UPDATE_READERS
CREATE INDEX HOMEPAGE.STATUS_READERS_MIG_IX ON HOMEPAGE.NR_STATUS_UPDATE_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_STATUS_UPDATE_READERS

--  [start indexes] NR_EXTERNAL_READERS
CREATE INDEX HOMEPAGE.EXTERNAL_READERS_MIG_IX ON HOMEPAGE.NR_EXTERNAL_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_EXTERNAL_READERS

--  [start indexes] NR_ACTIONABLE_READERS
CREATE INDEX HOMEPAGE.ACTIONABLE_READERS_MIG_IX ON HOMEPAGE.NR_ACTIONABLE_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_ACTIONABLE_READERS


--  [start indexes] NR_DISCOVERY_VIEW
CREATE INDEX HOMEPAGE.DISCOVERY_VIEW_MIG_IX ON HOMEPAGE.NR_DISCOVERY_VIEW (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_DISCOVERY_VIEW

--  [start indexes] NR_PROFILES_VIEW
CREATE INDEX HOMEPAGE.PROFILES_VIEW_MIG_IX ON HOMEPAGE.NR_PROFILES_VIEW (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_PROFILES_VIEW

--  [start indexes] NR_NOTIFICATION_SENT_READERS
CREATE INDEX HOMEPAGE.NOTIFICA_READERS_MIG_IX ON HOMEPAGE.NR_NOTIFICATION_SENT_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_NOTIFICATION_SENT_READERS

--  [start indexes] NR_NOTIFICATION_RECEIV_READERS
CREATE INDEX HOMEPAGE.NOT_REC_READERS_MIG_IX ON HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_NOTIFICATION_RECEIV_READERS

--  [start indexes] NR_SAVED_READERS
CREATE INDEX HOMEPAGE.SAVED_READERS_MIG_IX ON HOMEPAGE.NR_SAVED_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_SAVED_READERS

--  [start indexes] NR_TOPICS_READERS
CREATE INDEX HOMEPAGE.TOPICS_READERS_MIG_IX ON HOMEPAGE.NR_TOPICS_READERS (READER_ID, ROLLUP_ENTRY_ID, CREATION_DATE ASC);
COMMIT;

--  [end indexes] NR_TOPICS_READERS

-----------------------------------------------------------------------------------------------------
-- [START] 70821: [fixup104] After rally we see we can improve of 90% perf. on querying readers table.
-----------------------------------------------------------------------------------------------------

DROP   INDEX HOMEPAGE.AGGREGATED_READERS_RLL_BRD_VIS;
COMMIT;
 
DROP   INDEX HOMEPAGE.RESPONSES_READERS_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.PROFILES_READERS_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.COMM_READERS_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.ACTIVITIES_READERS_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.BLOGS_READERS_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.BOOKMARKS_READERS_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.FILES_READERS_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.FORUMS_READERS_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.WIKIS_READERS_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.TAGS_READERS_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.STATUS_READERS_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.EXTERNAL_READERS_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.SAVED_READERS_RLL_BRD_VIS;
COMMIT;
 
DROP   INDEX HOMEPAGE.ACTIONABLE_READERS_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.DISCOVERY_VIEW_RLL_BRD_VIS;
COMMIT;
 
DROP   INDEX HOMEPAGE.PROFILES_VIEW_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.NOTIFICA_READERS_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.NOT_REC_READERS_RLL_BRD_VIS; 
COMMIT;

DROP   INDEX HOMEPAGE.TOPICS_READERS_RLL_BRD_VIS;
COMMIT;

-----------------------------------------------------------------------------------------------------
-- [END] 70821: [fixup104] After rally we see we can improve of 90% perf. on querying readers table.
-----------------------------------------------------------------------------------------------------

--71040: Homepage and News migration is too slow while migrating very large dataset, oracle.
CREATE UNIQUE INDEX HOMEPAGE.MIG_RLL_STORY_IDX  
   ON HOMEPAGE.NR_STORIES (STORY_ID, ITEM_ID, ITEM_CORRELATION_ID);

----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
--69425: Speedup Migration for stories migration. Add the following indexes in upgrade scripts. Remove them in post migration script.
CREATE UNIQUE INDEX HOMEPAGE.MIG_STR_CORR 
	ON HOMEPAGE.NR_STORIES (STORY_ID, ITEM_CORRELATION_ID);

COMMIT;	

DROP INDEX HOMEPAGE.NR_STORIES_REL_COMM;
COMMIT;


-- Drop index created in fixup108
DROP INDEX HOMEPAGE.STORIES_ITEM_ENTRY_CORR_ID;
COMMIT;

----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------

runstats on table HOMEPAGE.PERSON with distribution and detailed indexes all ;
runstats on table HOMEPAGE.LOGINNAME with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NT_NOTIFICATION with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NT_NOTIFICATION_RECIPIENT with distribution and detailed indexes all ;

runstats on table HOMEPAGE.NR_AGGREGATED_READERS with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_RESPONSES_READERS with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_PROFILES_READERS with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_COMMUNITIES_READERS with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_ACTIVITIES_READERS with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_BLOGS_READERS with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_BOOKMARKS_READERS with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_FILES_READERS with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_FORUMS_READERS with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_WIKIS_READERS with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_TAGS_READERS with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_STATUS_UPDATE_READERS with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_ACTIONABLE_READERS  with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_SAVED_READERS  with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_DISCOVERY_VIEW with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_PROFILES_VIEW with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_NOTIFICATION_SENT_READERS with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_TOPICS_READERS  with distribution and detailed indexes all ;

runstats on table HOMEPAGE.NR_ENTRIES  with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_ENTRIES_ARCHIVE  with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_STORIES_CONTENT with distribution and detailed indexes all ;
runstats on table HOMEPAGE.NR_STORIES with distribution and detailed indexes all ;



------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 110
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 110 , RELEASEVER = '4.0.0.0'
WHERE   DBSCHEMAVER = 101;

--------------------------------------
-- COMMIT
--------------------------------------

COMMIT;
--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate;
