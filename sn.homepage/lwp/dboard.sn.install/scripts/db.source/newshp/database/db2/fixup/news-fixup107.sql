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


--73896: [Performance|Server] News DB disk I/O 100% busy when posting stories



--  [start indexes] NR_AGGREGATED_READERS
DROP  INDEX HOMEPAGE.AGGREGATED_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_AGGREGATED_READERS





--  [start indexes] NR_RESPONSES_READERS
DROP  INDEX HOMEPAGE.RESPONSES_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_RESPONSES_READERS





--  [start indexes] NR_PROFILES_READERS
DROP  INDEX HOMEPAGE.PROFILES_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_PROFILES_READERS





--  [start indexes] NR_COMMUNITIES_READERS
DROP  INDEX HOMEPAGE.COMM_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_COMMUNITIES_READERS





--  [start indexes] NR_ACTIVITIES_READERS
DROP  INDEX HOMEPAGE.ACTIVITIES_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_ACTIVITIES_READERS





--  [start indexes] NR_BLOGS_READERS
DROP  INDEX HOMEPAGE.BLOGS_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_BLOGS_READERS





--  [start indexes] NR_BOOKMARKS_READERS
DROP  INDEX HOMEPAGE.BOOKMARKS_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_BOOKMARKS_READERS





--  [start indexes] NR_FILES_READERS
DROP  INDEX HOMEPAGE.FILES_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_FILES_READERS





--  [start indexes] NR_FORUMS_READERS
DROP  INDEX HOMEPAGE.FORUMS_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_FORUMS_READERS





--  [start indexes] NR_WIKIS_READERS
DROP  INDEX HOMEPAGE.WIKIS_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_WIKIS_READERS





--  [start indexes] NR_TAGS_READERS
DROP  INDEX HOMEPAGE.TAGS_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_TAGS_READERS





--  [start indexes] NR_STATUS_UPDATE_READERS
DROP  INDEX HOMEPAGE.STATUS_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_STATUS_UPDATE_READERS





--  [start indexes] NR_EXTERNAL_READERS
DROP  INDEX HOMEPAGE.EXTERNAL_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_EXTERNAL_READERS





--  [start indexes] NR_ACTIONABLE_READERS
DROP  INDEX HOMEPAGE.ACTIONABLE_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_ACTIONABLE_READERS





--  [start indexes] NR_DISCOVERY_VIEW
DROP  INDEX HOMEPAGE.DISCOVERY_VIEW_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_DISCOVERY_VIEW





--  [start indexes] NR_PROFILES_VIEW
DROP  INDEX HOMEPAGE.PROFILES_VIEW_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_PROFILES_VIEW





--  [start indexes] NR_NOTIFICATION_SENT_READERS
DROP  INDEX HOMEPAGE.NOTIFICA_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_NOTIFICATION_SENT_READERS





--  [start indexes] NR_NOTIFICATION_RECEIV_READERS
DROP  INDEX HOMEPAGE.NOT_REC_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_NOTIFICATION_RECEIV_READERS





--  [start indexes] NR_SAVED_READERS
DROP  INDEX HOMEPAGE.SAVED_READERS_CL_RDR_IX;
COMMIT;

--  [end indexes] NR_SAVED_READERS





--  [start indexes] NR_TOPICS_READERS
DROP  INDEX HOMEPAGE.TOPICS_READERS_CL_RDR_IX;
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
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
COMMIT;

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_RLL_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (IS_VISIBLE, USE_IN_ROLLUP); 
COMMIT;

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_ROLLUP_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (ROLLUP_ENTRY_ID); 
COMMIT;

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_RIR_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC); 
COMMIT;

------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------





---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 107 -------------------------------------
---------------------------------------------------------------------------------
 

  
  
  
