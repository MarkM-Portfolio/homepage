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
-- REMOVING RECORDS -----------------

-----------------------------------------------------------------------------------------------------------
-- START HOMEPAGE 
-----------------------------------------------------------------------------------------------------------


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


-- REMOVING RECORDS -----------------

commit;



DELETE FROM HOMEPAGE.HOMEPAGE_SCHEMA;
commit;

DELETE FROM HOMEPAGE.LOGINNAME;
commit;

DELETE FROM HOMEPAGE.HP_WIDGET_INST;
commit;

DELETE FROM HOMEPAGE.HP_TAB_INST;
commit;

DELETE FROM HOMEPAGE.HP_WIDGET_TAB; 
commit;

DELETE FROM HOMEPAGE.HP_UI;
commit;

DELETE FROM HOMEPAGE.HP_TAB;
commit;

DELETE FROM HOMEPAGE.PREREQ;
commit;

DELETE FROM HOMEPAGE.WIDGET;
commit;


DELETE FROM HOMEPAGE.PERSON;
commit;

DELETE FROM HOMEPAGE.MT_METRIC_STAT;
commit;

DELETE FROM HOMEPAGE.NT_REPLYTO_RECIPIENT;
commit;

DELETE FROM HOMEPAGE.NT_REPLYTO;
commit;

DELETE FROM HOMEPAGE.MTCONFIG;
commit;



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
commit;

DELETE FROM HOMEPAGE.NR_ENTRIES;
commit;

DELETE FROM HOMEPAGE.NR_ENTRIES_ARCHIVE;
commit;

DELETE FROM HOMEPAGE.NR_COMM_SETTINGS;
commit;

DELETE FROM HOMEPAGE.DELETED_STORIES_QUEUE;
commit;

-- News delete board tables
DELETE FROM HOMEPAGE.BOARD_CURRENT_STATUS;
commit;

DELETE FROM HOMEPAGE.BOARD_RECOMMENDATIONS;
commit;

DELETE FROM HOMEPAGE.BOARD_OBJECT_REFERENCE;
commit;

DELETE FROM HOMEPAGE.BOARD_COMMENTS;
commit;

DELETE FROM HOMEPAGE.BOARD_ENTRIES;
commit;

DELETE FROM HOMEPAGE.BOARD;

commit;

-- New delete categories stories
DELETE FROM HOMEPAGE.NR_RESPONSES_READERS;
commit;

DELETE FROM HOMEPAGE.NR_PROFILES_READERS;
commit;

DELETE FROM HOMEPAGE.NR_COMMUNITIES_READERS;
commit;

DELETE FROM HOMEPAGE.NR_ACTIVITIES_READERS;
commit;

DELETE FROM HOMEPAGE.NR_BLOGS_READERS;
commit;

DELETE FROM HOMEPAGE.NR_BOOKMARKS_READERS;
commit;

DELETE FROM HOMEPAGE.NR_FILES_READERS;
commit;

DELETE FROM HOMEPAGE.NR_FORUMS_READERS;
commit;

DELETE FROM HOMEPAGE.NR_WIKIS_READERS;
commit;

DELETE FROM HOMEPAGE.NR_TAGS_READERS;
commit;

DELETE FROM HOMEPAGE.NR_STATUS_UPDATE_READERS;
commit;

DELETE FROM HOMEPAGE.NR_DISCOVERY_VIEW;
commit;

DELETE FROM HOMEPAGE.NR_PROFILES_VIEW;
commit;

DELETE FROM HOMEPAGE.NR_NOTIFICATION_SENT_READERS;
commit;

DELETE FROM HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS;
commit;

DELETE FROM HOMEPAGE.NR_TOPICS_READERS;
commit;

DELETE FROM HOMEPAGE.NR_AS_SEEDLIST;
commit;

DELETE FROM HOMEPAGE.NR_AS_COLLECTION_CONFIG; 
commit;

DELETE FROM HOMEPAGE.NR_AS_CRAWLER_STATUS; 
commit;

-- NEWS deletion
DELETE FROM HOMEPAGE.NR_SOURCE;
commit;

DELETE FROM HOMEPAGE.NR_TEMPLATE;
commit;

DELETE FROM HOMEPAGE.NR_CATEGORY_TYPE;
commit;

DELETE FROM HOMEPAGE.NR_NEWS_COMMENT_CONTENT;
commit;

DELETE FROM HOMEPAGE.NR_NEWS_STATUS_CONTENT;
commit;

DELETE FROM HOMEPAGE.NR_NEWS_STATUS_COMMENT;
commit;

DELETE FROM HOMEPAGE.NR_NEWS_STATUS_NETWORK;
commit;

DELETE FROM HOMEPAGE.NR_AGGREGATED_READERS;
commit;

DELETE FROM HOMEPAGE.NR_COMM_PERSON_FOLLOW;
commit;

DELETE FROM HOMEPAGE.NR_NETWORK;
commit;

DELETE FROM HOMEPAGE.NR_STORIES_CONTENT;
commit;

DELETE FROM HOMEPAGE.NR_COMM_FOLLOW;
commit;

DELETE FROM HOMEPAGE.NR_FOLLOWS;	
commit;

DELETE FROM HOMEPAGE.NR_RESOURCE;
commit;

DELETE FROM HOMEPAGE.NR_RESOURCE_TYPE;
commit;

-- EMail Digest deletion
DELETE FROM HOMEPAGE.EMD_EMAIL_PREFS;
commit;

DELETE FROM HOMEPAGE.EMD_TRANCHE_INFO;
commit;

DELETE FROM HOMEPAGE.EMD_TRANCHE;
commit;

DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF;
commit;

DELETE FROM HOMEPAGE.EMD_FREQUENCY_TYPE;
commit;

DELETE FROM HOMEPAGE.NR_SOURCE_TYPE;
commit;

-- Delete actionable stories
DELETE FROM HOMEPAGE.NR_ACTIONABLE_READERS;
commit;

DELETE FROM HOMEPAGE.NR_SAVED_READERS;
commit;

DELETE FROM HOMEPAGE.NR_DISCOVERY_VIEW;
commit;

DELETE FROM HOMEPAGE.NR_AS_CONTENT_INDEX_STATS;
commit;

DELETE FROM HOMEPAGE.OH2P_CLIENTCFG;
commit;

DELETE FROM HOMEPAGE.OH2P_CACHE;
commit;

DELETE FROM HOMEPAGE.OAUTH2_TOKEN;
commit;

DELETE FROM HOMEPAGE.OAUTH2_GADGET_BINDING;
commit;

DELETE FROM HOMEPAGE.OAUTH2_CLIENT;
commit;

DELETE FROM HOMEPAGE.OAUTH2_PROVIDER;
commit;

DELETE FROM HOMEPAGE.OAUTH1_CONTEXT;
commit;

DELETE FROM HOMEPAGE.OAUTH1_CLIENT;
commit;

DELETE FROM HOMEPAGE.OAUTH1_PROVIDER;
commit;

DELETE FROM HOMEPAGE.OAUTH1_TOKEN;
commit;




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

DELETE FROM HOMEPAGE.SR_FILECONTENTTASKDEF;

DELETE FROM HOMEPAGE.SR_SANDTASKDEF;

DELETE FROM HOMEPAGE.SR_BACKUPTASKDEF;

DELETE FROM HOMEPAGE.SR_INDEXINGTASKDEF;

DELETE FROM HOMEPAGE.SR_OPTIMIZETASKDEF;

DELETE FROM HOMEPAGE.SR_TASKDEF;

DELETE FROM HOMEPAGE.SR_FILESCONTENT;

DELETE FROM HOMEPAGE.SR_MIGTASKDEFINFO;

DELETE FROM HOMEPAGE.SR_INDEX_DOCS;

DELETE FROM HOMEPAGE.SR_RESUME_TOKENS;

DELETE FROM HOMEPAGE.SR_INDEX_MANAGEMENT;

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSLMGR;

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSLMPR;

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSTASK;

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSTREG;

DELETE FROM HOMEPAGE.SR_FEEDBACK;

DELETE FROM HOMEPAGE.SR_FEEDBACK_CONTEXT;

DELETE FROM HOMEPAGE.SR_FEEDBACK_PARAMETERS;

DELETE FROM HOMEPAGE.SR_STATS;

DELETE FROM HOMEPAGE.SR_STRING_STATS;

DELETE FROM HOMEPAGE.SR_NUMBER_STATS;

DELETE FROM HOMEPAGE.SR_TIMER_STATS;

DELETE FROM HOMEPAGE.SR_GLOBAL_SAND_PROPS; 

DELETE FROM HOMEPAGE.SR_ECM_DOCUMENT_TYPE_PROPS;

DELETE FROM HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS;

DELETE FROM HOMEPAGE.SR_POST_FILTERING_SERVICE;

--
DELETE FROM HOMEPAGE.MT_ORGANIZATION; 



-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END Search Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


-----------------------------------------------------------------------------------------------------------
-- END SEARCH
-----------------------------------------------------------------------------------------------------------


COMMIT;

--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;
