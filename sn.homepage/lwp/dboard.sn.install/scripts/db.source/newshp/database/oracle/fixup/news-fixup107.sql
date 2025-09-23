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
------------------------ START NEWS FIXUP 107 -----------------------------------
---------------------------------------------------------------------------------

---------------------------------------
-- JUST ORACLE: INDEX NAME FIX
---------------------------------------

-------------------
-- Dropping an old cluster index
DROP CLUSTER  HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS;
-------------------

ALTER INDEX HOMEPAGE.NR_AGGREGATED_READERS_RIR_IX RENAME TO AGGREGATED_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_RESPONSES_READERS_RIR_IX RENAME TO RESPONSES_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_PROFILES_READERS_RIR_IX RENAME TO  PROFILES_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_COMM_READERS_RIR_IX RENAME TO COMM_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_ACTIVITIES_READERS_RIR_IX RENAME TO ACTIVITIES_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_BLOGS_READERS_RIR_IX RENAME TO BLOGS_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_BOOKMARKS_READERS_RIR_IX RENAME TO BOOKMARKS_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_FILES_READERS_RIR_IX RENAME TO FILES_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_FORUMS_READERS_RIR_IX RENAME TO  FORUMS_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_WIKIS_READERS_RIR_IX RENAME TO WIKIS_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_TAGS_READERS_RIR_IX RENAME TO TAGS_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_STATUS_READERS_RIR_IX RENAME TO STATUS_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_EXTERNAL_READERS_RIR_IX RENAME TO EXTERNAL_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_ACTIONABLE_READERS_RIR_IX RENAME TO ACTIONABLE_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_DISCOVERY_VIEW_RIR_IX RENAME TO DISCOVERY_VIEW_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_PROFILES_VIEW_RIR_IX RENAME TO PROFILES_VIEW_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_NOTIFICA_READERS_RIR_IX RENAME TO NOTIFICA_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_NOT_REC_READERS_RIR_IX RENAME TO NOT_REC_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_SAVED_READERS_RIR_IX RENAME TO SAVED_READERS_RIR_IX;
COMMIT;

ALTER INDEX HOMEPAGE.NR_TOPICS_READERS_RIR_IX RENAME TO TOPICS_READERS_RIR_IX;
COMMIT;
---------------------------------------



--73896: [Performance|Server] News DB disk I/O 100% busy when posting stories







--  [start indexes] NR_AGGREGATED_READERS
DROP  INDEX HOMEPAGE.NR_AGGREGATED_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_AGGREGATED_READERS





--  [start indexes] NR_RESPONSES_READERS
DROP  INDEX HOMEPAGE.NR_RESPONSES_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_RESPONSES_READERS





--  [start indexes] NR_PROFILES_READERS
DROP  INDEX HOMEPAGE.NR_PROFILES_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_PROFILES_READERS





--  [start indexes] NR_COMMUNITIES_READERS
DROP  INDEX HOMEPAGE.NR_COMM_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_COMMUNITIES_READERS





--  [start indexes] NR_ACTIVITIES_READERS
DROP  INDEX HOMEPAGE.NR_ACTIVITIES_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_ACTIVITIES_READERS





--  [start indexes] NR_BLOGS_READERS
DROP  INDEX HOMEPAGE.NR_BLOGS_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_BLOGS_READERS





--  [start indexes] NR_BOOKMARKS_READERS
DROP  INDEX HOMEPAGE.NR_BOOKMARKS_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_BOOKMARKS_READERS





--  [start indexes] NR_FILES_READERS
DROP  INDEX HOMEPAGE.NR_FILES_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_FILES_READERS





--  [start indexes] NR_FORUMS_READERS
DROP  INDEX HOMEPAGE.NR_FORUMS_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_FORUMS_READERS





--  [start indexes] NR_WIKIS_READERS
DROP  INDEX HOMEPAGE.NR_WIKIS_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_WIKIS_READERS





--  [start indexes] NR_TAGS_READERS
DROP  INDEX HOMEPAGE.NR_TAGS_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_TAGS_READERS





--  [start indexes] NR_STATUS_UPDATE_READERS
DROP  INDEX HOMEPAGE.NR_STATUS_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_STATUS_UPDATE_READERS





--  [start indexes] NR_EXTERNAL_READERS
DROP  INDEX HOMEPAGE.NR_EXTERNAL_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_EXTERNAL_READERS





--  [start indexes] NR_ACTIONABLE_READERS
DROP  INDEX HOMEPAGE.NR_ACTIONABLE_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_ACTIONABLE_READERS





--  [start indexes] NR_DISCOVERY_VIEW
DROP  INDEX HOMEPAGE.NR_DISCOVERY_VIEW_RDR_IX;
COMMIT;

--  [end indexes] NR_DISCOVERY_VIEW





--  [start indexes] NR_PROFILES_VIEW
DROP  INDEX HOMEPAGE.NR_PROFILES_VIEW_RDR_IX;
COMMIT;

--  [end indexes] NR_PROFILES_VIEW





--  [start indexes] NR_NOTIFICATION_SENT_READERS
DROP  INDEX HOMEPAGE.NR_NOTIFICA_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_NOTIFICATION_SENT_READERS





--  [start indexes] NR_NOTIFICATION_RECEIV_READERS
DROP  INDEX HOMEPAGE.NR_NOT_REC_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_NOTIFICATION_RECEIV_READERS





--  [start indexes] NR_SAVED_READERS
DROP  INDEX HOMEPAGE.NR_SAVED_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_SAVED_READERS





--  [start indexes] NR_TOPICS_READERS
DROP  INDEX HOMEPAGE.NR_TOPICS_READERS_RDR_IX;
COMMIT;

--  [end indexes] NR_TOPICS_READERS

------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------
-- 74177: [Performance|Server|DB2] Inefficient SQL/Index for NR_DISCOVERY_VIEW
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------


DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_SRC_IX;
COMMIT;

DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_RLL_IX; 
COMMIT;

DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_RDR_STR;  
COMMIT;

DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_ROLLUP_IX;  
COMMIT;

DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_RIR_IX;
COMMIT;


CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_SRC_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE)  TABLESPACE NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_RLL_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (IS_VISIBLE, USE_IN_ROLLUP)  TABLESPACE NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_ROLLUP_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (ROLLUP_ENTRY_ID)  TABLESPACE NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_RIR_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC)  TABLESPACE NEWSINDEXTABSPACE;  
COMMIT;

------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------


---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 107 -------------------------------------
---------------------------------------------------------------------------------
 

  
  
  
