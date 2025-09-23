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
------------------------ START NEWS FIXUP 109 -----------------------------------
---------------------------------------------------------------------------------

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_ACT_CD_VIS_IDX  
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (ACTOR_UUID, CREATION_DATE DESC, IS_VISIBLE) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

DROP INDEX HOMEPAGE.STORIES_CONTAINER_URL_IDX;
COMMIT;

-------------------------------------------------------------------------
--75925: Duplicate entries in mobile/homepage/StatusUpdates
-------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_NETWORK MODIFY (BRIEF_DESC VARCHAR2(4000));
COMMIT;

CREATE INDEX HOMEPAGE.NR_STATUS_UPDATE_IX
	ON HOMEPAGE.NR_NEWS_STATUS_NETWORK (READER_ID, UPDATE_DATE ASC, CREATION_DATE ASC, ITEM_ID, ACTOR_UUID) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;


--76074: Fixup109.sql - add indexes based on performance and dev activities
DROP INDEX HOMEPAGE.NR_SL_UD_DELTED_IX;
COMMIT;

DROP INDEX HOMEPAGE.NR_SL_UD_VISIBLE_IX;
COMMIT;

DROP INDEX HOMEPAGE.NR_AS_SEEDLIST_IDX;
COMMIT;

CREATE INDEX HOMEPAGE.NR_SL_UD_STR
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE ASC, STORY_ID) TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

-- Index for deletion service
DROP INDEX HOMEPAGE.COMM_READERS_DEL_SERV_IX;
COMMIT;

DROP INDEX HOMEPAGE.DISCOVERY_VIEW_DEL_SERV_IX;
COMMIT;

DROP INDEX HOMEPAGE.PROFILES_VIEW_DEL_SERV_IX;
COMMIT;

CREATE  INDEX HOMEPAGE.COMM_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (CREATION_DATE DESC, IS_BROADCAST) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_DEL_SERV_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (CREATION_DATE DESC, IS_BROADCAST) TABLESPACE  NEWSINDEXTABSPACE;  
COMMIT;

CREATE  INDEX HOMEPAGE.PROFILES_VIEW_DEL_SERV_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (CREATION_DATE DESC, IS_BROADCAST) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;

-------------------------------------------------------------------------
    
---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 109 -------------------------------------
---------------------------------------------------------------------------------
 

  
  
  
