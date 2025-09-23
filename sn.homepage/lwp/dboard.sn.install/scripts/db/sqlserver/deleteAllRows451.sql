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
USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO

-----------------------------------------------------------------------------------------------------------
-- START HOMEPAGE 
-----------------------------------------------------------------------------------------------------------


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


-- REMOVING RECORDS -----------------

GO



DELETE FROM HOMEPAGE.HOMEPAGE_SCHEMA;
GO

DELETE FROM HOMEPAGE.LOGINNAME;
GO

DELETE FROM HOMEPAGE.HP_WIDGET_INST;
GO

DELETE FROM HOMEPAGE.HP_TAB_INST;
GO

DELETE FROM HOMEPAGE.HP_WIDGET_TAB; 
GO

DELETE FROM HOMEPAGE.HP_UI;
GO

DELETE FROM HOMEPAGE.HP_TAB;
GO

DELETE FROM HOMEPAGE.PREREQ;
GO

DELETE FROM HOMEPAGE.WIDGET;
GO


DELETE FROM HOMEPAGE.PERSON;
GO

DELETE FROM HOMEPAGE.HP_USER_PREFS;
GO

DELETE FROM HOMEPAGE.METRIC_STAT;
GO

DELETE FROM HOMEPAGE.NT_REPLYTO_RECIPIENT;
GO

DELETE FROM HOMEPAGE.NT_REPLYTO;
GO

DELETE FROM HOMEPAGE.MTCONFIG;
GO



-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


-----------------------------------------------------------------------------------------------------------
-- END HOMEPAGE
-----------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------
-- START NEWS
-----------------------------------------------------------------------------------------------------------


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

DELETE FROM HOMEPAGE.NR_STORIES;
GO

DELETE FROM HOMEPAGE.NR_ENTRIES;
GO

DELETE FROM HOMEPAGE.NR_ENTRIES_ARCHIVE;
GO

DELETE FROM HOMEPAGE.NR_COMM_SETTINGS;
GO

DELETE FROM HOMEPAGE.DELETED_STORIES_QUEUE;
GO

-- News delete board tables
DELETE FROM HOMEPAGE.BOARD_CURRENT_STATUS;
GO

DELETE FROM HOMEPAGE.BOARD_RECOMMENDATIONS;
GO

DELETE FROM HOMEPAGE.BOARD_OBJECT_REFERENCE;
GO

DELETE FROM HOMEPAGE.BOARD_COMMENTS;
GO

DELETE FROM HOMEPAGE.BOARD_ENTRIES;
GO

DELETE FROM HOMEPAGE.BOARD;

GO

-- New delete categories stories
DELETE FROM HOMEPAGE.NR_RESPONSES_READERS;
GO

DELETE FROM HOMEPAGE.NR_PROFILES_READERS;
GO

DELETE FROM HOMEPAGE.NR_COMMUNITIES_READERS;
GO

DELETE FROM HOMEPAGE.NR_ACTIVITIES_READERS;
GO

DELETE FROM HOMEPAGE.NR_BLOGS_READERS;
GO

DELETE FROM HOMEPAGE.NR_BOOKMARKS_READERS;
GO

DELETE FROM HOMEPAGE.NR_FILES_READERS;
GO

DELETE FROM HOMEPAGE.NR_FORUMS_READERS;
GO

DELETE FROM HOMEPAGE.NR_WIKIS_READERS;
GO

DELETE FROM HOMEPAGE.NR_TAGS_READERS;
GO

DELETE FROM HOMEPAGE.NR_STATUS_UPDATE_READERS;
GO

DELETE FROM HOMEPAGE.NR_DISCOVERY_VIEW;
GO

DELETE FROM HOMEPAGE.NR_PROFILES_VIEW;
GO

DELETE FROM HOMEPAGE.NR_NOTIFICATION_SENT_READERS;
GO

DELETE FROM HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS;
GO

DELETE FROM HOMEPAGE.NR_TOPICS_READERS;
GO

DELETE FROM HOMEPAGE.NR_AS_SEEDLIST;
GO

DELETE FROM HOMEPAGE.NR_AS_COLLECTION_CONFIG; 
GO

DELETE FROM HOMEPAGE.NR_AS_CRAWLER_STATUS; 
GO

-- NEWS deletion
DELETE FROM HOMEPAGE.NR_SOURCE;
GO

DELETE FROM HOMEPAGE.NR_TEMPLATE;
GO

DELETE FROM HOMEPAGE.NR_CATEGORY_TYPE;
GO

DELETE FROM HOMEPAGE.NR_NEWS_COMMENT_CONTENT;
GO

DELETE FROM HOMEPAGE.NR_NEWS_STATUS_CONTENT;
GO

DELETE FROM HOMEPAGE.NR_NEWS_STATUS_COMMENT;
GO

DELETE FROM HOMEPAGE.NR_NEWS_STATUS_NETWORK;
GO

DELETE FROM HOMEPAGE.NR_AGGREGATED_READERS;
GO

DELETE FROM HOMEPAGE.NR_COMM_PERSON_FOLLOW;
GO

DELETE FROM HOMEPAGE.NR_NETWORK;
GO

DELETE FROM HOMEPAGE.NR_STORIES_CONTENT;
GO

DELETE FROM HOMEPAGE.NR_COMM_FOLLOW;
GO

DELETE FROM HOMEPAGE.NR_FOLLOWS;	
GO

DELETE FROM HOMEPAGE.NR_RESOURCE;
GO

DELETE FROM HOMEPAGE.NR_RESOURCE_TYPE;
GO

-- EMail Digest deletion
DELETE FROM HOMEPAGE.EMD_EMAIL_PREFS;
GO

DELETE FROM HOMEPAGE.EMD_TRANCHE_INFO;
GO

DELETE FROM HOMEPAGE.EMD_TRANCHE;
GO

DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF;
GO

DELETE FROM HOMEPAGE.EMD_FREQUENCY_TYPE;
GO


DELETE FROM HOMEPAGE.NR_SOURCE_TYPE;
GO

-- Delete actionable stories
DELETE FROM HOMEPAGE.NR_ACTIONABLE_READERS;
GO

DELETE FROM HOMEPAGE.NR_SAVED_READERS;
GO

DELETE FROM HOMEPAGE.NR_DISCOVERY_VIEW;
GO

DELETE FROM HOMEPAGE.NR_AS_CONTENT_INDEX_STATS;
GO

DELETE FROM HOMEPAGE.OH2P_CLIENTCFG;
GO

DELETE FROM HOMEPAGE.OH2P_CACHE;
GO

DELETE FROM HOMEPAGE.OAUTH2_TOKEN;
GO

DELETE FROM HOMEPAGE.OAUTH2_GADGET_BINDING;
GO

DELETE FROM HOMEPAGE.OAUTH2_CLIENT;
GO

DELETE FROM HOMEPAGE.OAUTH2_PROVIDER;
GO

DELETE FROM HOMEPAGE.OAUTH1_CONTEXT;
GO

DELETE FROM HOMEPAGE.OAUTH1_CLIENT;
GO

DELETE FROM HOMEPAGE.OAUTH1_PROVIDER;
GO

DELETE FROM HOMEPAGE.OAUTH1_TOKEN;
GO




-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


-----------------------------------------------------------------------------------------------------------
-- END NEWS
-----------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------
-- START SEARCH
-----------------------------------------------------------------------------------------------------------


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


-- REMOVING RECORDS -----------------

DELETE FROM HOMEPAGE.SR_FILECONTENTTASKDEF
GO

DELETE FROM HOMEPAGE.SR_SANDTASKDEF
GO

DELETE FROM HOMEPAGE.SR_BACKUPTASKDEF
GO

DELETE FROM HOMEPAGE.SR_INDEXINGTASKDEF
GO

DELETE FROM HOMEPAGE.SR_OPTIMIZETASKDEF
GO

DELETE FROM HOMEPAGE.SR_TASKDEF
GO

DELETE FROM HOMEPAGE.SR_FILESCONTENT
GO

DELETE FROM HOMEPAGE.SR_MIGTASKDEFINFO
GO

DELETE FROM HOMEPAGE.SR_INDEX_DOCS
GO

DELETE FROM HOMEPAGE.SR_RESUME_TOKENS
GO

DELETE FROM HOMEPAGE.SR_INDEX_MANAGEMENT
GO

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSLMGR
GO

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSLMPR
GO

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSTASK
GO

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSTREG
GO

DELETE FROM HOMEPAGE.SR_FEEDBACK
GO

DELETE FROM HOMEPAGE.SR_FEEDBACK_CONTEXT
GO

DELETE FROM HOMEPAGE.SR_FEEDBACK_PARAMETERS
GO

DELETE FROM HOMEPAGE.SR_STATS
GO

DELETE FROM HOMEPAGE.SR_STRING_STATS
GO

DELETE FROM HOMEPAGE.SR_NUMBER_STATS
GO

DELETE FROM HOMEPAGE.SR_TIMER_STATS
GO

DELETE FROM HOMEPAGE.SR_GLOBAL_SAND_PROPS
GO

DELETE FROM HOMEPAGE.SR_ECM_DOCUMENT_TYPE_PROPS
GO

DELETE FROM HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS
GO

DELETE FROM HOMEPAGE.SR_POST_FILTERING_SERVICE
GO

-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


--
DELETE FROM HOMEPAGE.MT_ORGANIZATION; 
GO
DELETE FROM HOMEPAGE.MT_CFG_FILES;
GO
DELETE FROM HOMEPAGE.MT_CFG_SETTINGS;
GO
DELETE FROM HOMEPAGE.MT_CFG_DEFINITIONS;
GO


-----------------------------------------------------------------------------------------------------------
-- END SEARCH
-----------------------------------------------------------------------------------------------------------


COMMIT;
