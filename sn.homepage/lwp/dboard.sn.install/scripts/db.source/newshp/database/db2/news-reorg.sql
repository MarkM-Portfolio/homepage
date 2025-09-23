-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2007, 2016                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

--NEWS

-- to create a new baseline


reorg table HOMEPAGE.NT_NOTIFICATION use HPNT16TMPTABSPACE;
reorg table HOMEPAGE.NT_NOTIFICATION_RECIPIENT use HPNT16TMPTABSPACE;

reorg table HOMEPAGE.NR_SOURCE use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_SUBSCRIPTION use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_NEWS_RECORDS use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_TEMPLATE use NEWSTMPTABSPACE;

reorg table HOMEPAGE.NR_SCHEDULER_LMGR use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_SCHEDULER_LMPR use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_SCHEDULER_TASK use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_SCHEDULER_TREG use NEWSTMPTABSPACE;

reorg table HOMEPAGE.NR_NEWS_SAVED use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_NEWS_DISCOVERY use NEWSTMPTABSPACE;

reorg table HOMEPAGE.NR_NEWS_COMMENT_CONTENT use NEWS4TMPTABSPACE;
reorg table HOMEPAGE.NR_NEWS_STATUS_CONTENT use NEWS4TMPTABSPACE;
reorg table HOMEPAGE.NR_NEWS_STATUS_COMMENT use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_NEWS_STATUS_NETWORK use NEWSTMPTABSPACE;


reorg table HOMEPAGE.NR_NETWORK use NEWS4TMPTABSPACE;
reorg table HOMEPAGE.NR_STORIES_CONTENT use NEWS32TMPTABSPACE;

reorg table HOMEPAGE.NR_COMM_FOLLOW use NEWS4TMPTABSPACE;
reorg table HOMEPAGE.NR_FOLLOWS use NEWS4TMPTABSPACE;	
reorg table HOMEPAGE.NR_RESOURCE use NEWS4TMPTABSPACE;
reorg table HOMEPAGE.NR_RESOURCE_TYPE use NEWS4TMPTABSPACE;

reorg table HOMEPAGE.NR_COMM_PERSON_FOLLOW use NEWS4TMPTABSPACE;
reorg table HOMEPAGE.NR_AGGREGATED_READERS use NEWS4TMPTABSPACE;

reorg table HOMEPAGE.EMD_FREQUENCY_TYPE use NEWS4TMPTABSPACE;
reorg table HOMEPAGE.EMD_RESOURCE_PREF use NEWS4TMPTABSPACE;
reorg table HOMEPAGE.EMD_TRANCHE use NEWS4TMPTABSPACE;
reorg table HOMEPAGE.EMD_TRANCHE_INFO use NEWS4TMPTABSPACE;
reorg table HOMEPAGE.EMD_EMAIL_PREFS use NEWS4TMPTABSPACE;

reorg table HOMEPAGE.NR_COMM_SETTINGS use NEWS4TMPTABSPACE;

-------------------------------------------------------



reorg indexes all for table HOMEPAGE.NR_SOURCE;
reorg indexes all for table HOMEPAGE.NR_SUBSCRIPTION;
reorg indexes all for table HOMEPAGE.NR_NEWS_RECORDS;
reorg indexes all for table HOMEPAGE.NR_TEMPLATE;

reorg indexes all for table HOMEPAGE.NR_SCHEDULER_LMGR;
reorg indexes all for table HOMEPAGE.NR_SCHEDULER_LMPR;
reorg indexes all for table HOMEPAGE.NR_SCHEDULER_TASK;
reorg indexes all for table HOMEPAGE.NR_SCHEDULER_TREG;

reorg indexes all for table HOMEPAGE.NR_NEWS_SAVED;
reorg indexes all for table HOMEPAGE.NR_NEWS_DISCOVERY;

reorg indexes all for table HOMEPAGE.NR_NEWS_COMMENT_CONTENT;
reorg indexes all for table HOMEPAGE.NR_NEWS_STATUS_CONTENT;
reorg indexes all for table HOMEPAGE.NR_NEWS_STATUS_COMMENT;
reorg indexes all for table HOMEPAGE.NR_NEWS_STATUS_NETWORK;

reorg indexes all for table HOMEPAGE.NR_NETWORK;
reorg indexes all for table HOMEPAGE.NR_STORIES_CONTENT;



reorg indexes all for table HOMEPAGE.NR_FOLLOWS;	
reorg indexes all for table HOMEPAGE.NR_RESOURCE;
reorg indexes all for table HOMEPAGE.NR_RESOURCE_TYPE;

reorg indexes all for table HOMEPAGE.NR_COMM_PERSON_FOLLOW;
reorg indexes all for table HOMEPAGE.NR_AGGREGATED_READERS;

reorg indexes all for table HOMEPAGE.EMD_FREQUENCY_TYPE;
reorg indexes all for table HOMEPAGE.EMD_RESOURCE_PREF;
reorg indexes all for table HOMEPAGE.EMD_TRANCHE;
reorg indexes all for table HOMEPAGE.EMD_TRANCHE_INFO;
reorg indexes all for table HOMEPAGE.EMD_EMAIL_PREFS;

reorg indexes all for table HOMEPAGE.NR_COMM_SETTINGS;

reorg table HOMEPAGE.NR_RESPONSES_READERS use NEWS4TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_RESPONSES_READERS;
reorg table HOMEPAGE.NR_PROFILES_READERS use NEWS4TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_PROFILES_READERS;
reorg table HOMEPAGE.NR_COMMUNITIES_READERS use NEWS4TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_COMMUNITIES_READERS;
reorg table HOMEPAGE.NR_ACTIVITIES_READERS use NEWS4TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_ACTIVITIES_READERS;
reorg table HOMEPAGE.NR_BLOGS_READERS use NEWS4TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_BLOGS_READERS;
reorg table HOMEPAGE.NR_BOOKMARKS_READERS use NEWS4TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_BOOKMARKS_READERS;
reorg table HOMEPAGE.NR_FILES_READERS use NEWS4TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_FILES_READERS;
reorg table HOMEPAGE.NR_FORUMS_READERS use NEWS4TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_FORUMS_READERS;
reorg table HOMEPAGE.NR_WIKIS_READERS use NEWS4TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_WIKIS_READERS;
reorg table HOMEPAGE.NR_TAGS_READERS use NEWS4TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_TAGS_READERS;

reorg table  HOMEPAGE.NR_STATUS_UPDATE_READERS use NEWS4TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_STATUS_UPDATE_READERS;

--------------------------------------------------------------
-- NEW TABLES FOR 3.5
--------------------------------------------------------------
reorg table HOMEPAGE.NR_ATTACHMENT;
reorg indexes all for table HOMEPAGE.NR_ATTACHMENT;

-- BOARD
reorg table HOMEPAGE.BOARD_ENTRIES;
reorg indexes all for table  HOMEPAGE.BOARD_ENTRIES;

reorg table HOMEPAGE.BOARD_COMMENTS;   
reorg indexes all for table  HOMEPAGE.BOARD_COMMENTS;

reorg table HOMEPAGE.BOARD_OBJECT_REFERENCE;
reorg indexes all for table  HOMEPAGE.BOARD_OBJECT_REFERENCE;

reorg table HOMEPAGE.BOARD_RECOMMENDATIONS;
reorg indexes all for table  HOMEPAGE.BOARD_RECOMMENDATIONS;

reorg table HOMEPAGE.BOARD_CURRENT_STATUS;
reorg indexes all for table  HOMEPAGE.BOARD_CURRENT_STATUS;

reorg table HOMEPAGE.BOARD;
reorg indexes all for table  HOMEPAGE.BOARD;

-------------------------
reorg table HOMEPAGE.NR_ACTIONABLE_READERS;
reorg indexes all for table HOMEPAGE.NR_ACTIONABLE_READERS;
reorg table HOMEPAGE.NR_SAVED_READERS;
reorg indexes all for table HOMEPAGE.NR_SAVED_READERS;


reorg table HOMEPAGE.NR_DISCOVERY_VIEW;
reorg table HOMEPAGE.NR_PROFILES_VIEW;
reorg table HOMEPAGE.NR_NOTIFICATION_SENT_READERS;
reorg table HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS;
reorg table HOMEPAGE.NR_TOPICS_READERS;

reorg indexes all for table HOMEPAGE.NR_DISCOVERY_VIEW;
reorg indexes all for table HOMEPAGE.NR_PROFILES_VIEW;
reorg indexes all for table HOMEPAGE.NR_NOTIFICATION_SENT_READERS;
reorg indexes all for table HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS;
reorg indexes all for table HOMEPAGE.NR_TOPICS_READERS;

reorg table HOMEPAGE.NR_ENTRIES;
reorg table HOMEPAGE.NR_ENTRIES_ARCHIVE;
reorg table HOMEPAGE.NR_STORIES;

reorg indexes all for table HOMEPAGE.NR_ENTRIES;
reorg indexes all for table HOMEPAGE.NR_ENTRIES_ARCHIVE;
reorg indexes all for table HOMEPAGE.NR_STORIES;





reorg table HOMEPAGE.OAUTH1_TOKEN;
commit;
reorg table HOMEPAGE.OAUTH1_PROVIDER;
commit;
reorg table HOMEPAGE.OAUTH1_CLIENT;
commit;
reorg table HOMEPAGE.OAUTH1_CONTEXT;
commit;
reorg table HOMEPAGE.OAUTH2_PROVIDER;
commit;
reorg table HOMEPAGE.OAUTH2_CLIENT;
commit;
reorg table HOMEPAGE.OAUTH2_GADGET_BINDING;
commit;
reorg table HOMEPAGE.OAUTH2_TOKEN;
commit;

reorg indexes all for table HOMEPAGE.OAUTH1_TOKEN;
commit;
reorg indexes all for table HOMEPAGE.OAUTH1_PROVIDER;
commit;
reorg indexes all for table HOMEPAGE.OAUTH1_CLIENT;
commit;
reorg indexes all for table HOMEPAGE.OAUTH1_CONTEXT;
commit;
reorg indexes all for table HOMEPAGE.OAUTH2_PROVIDER;
commit;
reorg indexes all for table HOMEPAGE.OAUTH2_CLIENT;
commit;
reorg indexes all for table HOMEPAGE.OAUTH2_GADGET_BINDING;
commit;
reorg indexes all for table HOMEPAGE.OAUTH2_TOKEN;
commit;


reorg table HOMEPAGE.NR_AS_SEEDLIST;
reorg table HOMEPAGE.NR_AS_COLLECTION_CONFIG;
reorg table HOMEPAGE.NR_AS_CRAWLER_STATUS;
commit;

reorg indexes all for table HOMEPAGE.NR_AS_SEEDLIST;
reorg indexes all for table HOMEPAGE.NR_AS_COLLECTION_CONFIG;
reorg indexes all for table HOMEPAGE.NR_AS_CRAWLER_STATUS;
reorg indexes all for table HOMEPAGE.NR_AS_CONTENT_INDEX_STATS;
commit;


reorg table HOMEPAGE.APPREG_SERVICES;
COMMIT;
reorg table HOMEPAGE.APPREG_PATHS;
COMMIT;
reorg table HOMEPAGE.APPREG_OBJECTS;
COMMIT;
reorg table HOMEPAGE.APPREG_EVENTS;
COMMIT;
reorg table HOMEPAGE.APPREG_RESOURCES;
COMMIT;
reorg table HOMEPAGE.APPREG_THEMES;
COMMIT;
reorg table HOMEPAGE.APPREG_SERVICE_PATH;
COMMIT;
reorg table HOMEPAGE.APPREG_SERVICE_OBJECT;
COMMIT;
reorg table HOMEPAGE.APPREG_SERVICE_EVENT;
COMMIT;
reorg table HOMEPAGE.APPREG_SERVICE_RESOURCE;
COMMIT;
reorg table HOMEPAGE.APPREG_SERVICE_THEME;
COMMIT;
reorg table HOMEPAGE.APPREG_EXTENSION_PATH;
COMMIT;
reorg table HOMEPAGE.APPREG_EXTENSION_OBJECT;
COMMIT;
reorg table HOMEPAGE.APPREG_EXTENSION_EVENT;
COMMIT;
reorg table HOMEPAGE.APPREG_EXTENSION_RESOURCE;
COMMIT;
reorg table HOMEPAGE.APPREG_EXTENSION_THEME;
COMMIT;
reorg table HOMEPAGE.APPREG_APPLICATIONS;
COMMIT;
reorg table HOMEPAGE.APPREG_SERVICE_APPLICATION;
COMMIT;
reorg table HOMEPAGE.APPREG_EXTENSIONS;
COMMIT;
reorg table HOMEPAGE.APPREG_APPLICATION_EXTENSION;
COMMIT;
reorg table HOMEPAGE.APPREG_SETTINGS;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_SERVICES;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_PATHS;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_OBJECTS;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_EVENTS;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_RESOURCES;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_THEMES;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_SERVICE_PATH;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_SERVICE_OBJECT;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_SERVICE_EVENT;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_SERVICE_RESOURCE;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_SERVICE_THEME;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_EXTENSION_PATH;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_EXTENSION_OBJECT;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_EXTENSION_EVENT;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_EXTENSION_RESOURCE;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_EXTENSION_THEME;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_APPLICATIONS;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_SERVICE_APPLICATION;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_EXTENSIONS;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_APPLICATION_EXTENSION;
COMMIT;
reorg indexes all for table HOMEPAGE.APPREG_SETTINGS;
COMMIT;

reorg table HOMEPAGE.VM_EVENT_TRACKER;
COMMIT;
reorg indexes all for table HOMEPAGE.VM_EVENT_TRACKER;
COMMIT;
