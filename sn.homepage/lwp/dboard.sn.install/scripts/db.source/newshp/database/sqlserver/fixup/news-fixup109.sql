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

CREATE  INDEX DISCOVERY_VIEW_ACT_CD_VIS_IDX  
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (ACTOR_UUID, CREATION_DATE DESC, IS_VISIBLE); 
GO
    

DROP INDEX STORIES_CONTAINER_URL_IDX ON HOMEPAGE.NR_STORIES;
GO
---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 109 -------------------------------------
---------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_NETWORK ALTER COLUMN BRIEF_DESC nvarchar(4000);
GO

CREATE INDEX NR_STATUS_UPDATE_IX
	ON HOMEPAGE.NR_NEWS_STATUS_NETWORK (READER_ID, UPDATE_DATE ASC, CREATION_DATE ASC, ITEM_ID, ACTOR_UUID);
GO


--76074: Fixup109.sql - add indexes based on performance and dev activities
DROP INDEX NR_SL_UD_DELTED_IX ON HOMEPAGE.NR_AS_SEEDLIST;
GO

DROP INDEX NR_SL_UD_VISIBLE_IX ON HOMEPAGE.NR_AS_SEEDLIST;
GO

DROP INDEX NR_AS_SEEDLIST_IDX ON HOMEPAGE.NR_AS_SEEDLIST;
GO

CREATE INDEX NR_SL_UD_STR
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE ASC, STORY_ID);
GO

-- Index for deletion service
DROP INDEX COMM_READERS_DEL_SERV_IX ON HOMEPAGE.NR_COMMUNITIES_READERS;
GO

DROP INDEX DISCOVERY_VIEW_DEL_SERV_IX ON HOMEPAGE.NR_DISCOVERY_VIEW;
GO

DROP INDEX PROFILES_VIEW_DEL_SERV_IX ON HOMEPAGE.NR_PROFILES_VIEW;
GO

CREATE  INDEX COMM_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_READERS (CREATION_DATE DESC, IS_BROADCAST); 
GO

CREATE  INDEX DISCOVERY_VIEW_DEL_SERV_IX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (CREATION_DATE DESC, IS_BROADCAST); 
GO

CREATE  INDEX PROFILES_VIEW_DEL_SERV_IX 
 	ON HOMEPAGE.NR_PROFILES_VIEW (CREATION_DATE DESC, IS_BROADCAST); 
GO

---------------------------------------------------------------------------------