-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2014, 2016                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 


-- 5724_S68 
CONNECT TO HOMEPAGE@


runstats on table HOMEPAGE.HOMEPAGE_SCHEMA with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.MT_ORGANIZATION with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.PERSON with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.PERSON_ROLE with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.HP_USER_PREFS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.LOGINNAME with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.HP_UI with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.HP_TAB with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.HP_TAB_INST with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.WIDGET with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.HP_WIDGET_TAB with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.HP_WIDGET_INST with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.PREREQ with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NT_REPLYTO with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NT_REPLYTO_RECIPIENT with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.METRIC_STAT with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.OAUTH1_TOKEN with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.OAUTH1_PROVIDER with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.OAUTH1_CLIENT with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.OAUTH1_CONTEXT with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.OAUTH2_PROVIDER with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.OAUTH2_CLIENT with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.OAUTH2_GADGET_BINDING with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.OAUTH2_TOKEN with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.OH2P_CACHE with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.OH2P_CLIENTCFG with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.MTCONFIG with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.MT_CFG_DEFINITIONS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.MT_CFG_SETTINGS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.MT_CFG_FILES with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_SOURCE_TYPE_DEFAULT with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_SOURCE_TYPE with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_TEMPLATE with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_SCHEDULER_TASK with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_SCHEDULER_TREG with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_SCHEDULER_LMGR with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_SCHEDULER_LMPR with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_NEWS_STATUS_NETWORK with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_NEWS_STATUS_COMMENT with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_NEWS_STATUS_CONTENT with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_NEWS_COMMENT_CONTENT with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_RESOURCE with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_FOLLOWS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_COMM_FOLLOW with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_STORIES_CONTENT with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_NETWORK with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.EMD_RESOURCE_PREF with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.EMD_TRANCHE with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.EMD_TRANCHE_INFO with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.EMD_EMAIL_PREFS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_COMM_PERSON_FOLLOW with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_ENTRIES with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_STORIES with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_ENTRIES_ARCHIVE with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_AGGREGATED_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_STATUS_UPDATE_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_EXTERNAL_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_ACTIONABLE_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.BOARD with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.BOARD_ENTRIES with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.BOARD_COMMENTS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.BOARD_OBJECT_REFERENCE with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.BOARD_RECOMMENDATIONS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.BOARD_CURRENT_STATUS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.BOARD_MENTIONS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.DELETED_STORIES_QUEUE with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_COMM_SETTINGS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_PROFILES_VIEW with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_NOTIFICATION_SENT_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_SAVED_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_TOPICS_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_MENTIONS_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_COMMUNITIES_VIEW with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_AS_SEEDLIST with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_AS_COLLECTION_CONFIG with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_AS_CRAWLER_STATUS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_AS_CONTENT_INDEX_STATS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.IMG_CACHE with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_AS_COUNTS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.OEMBED_SCHEDULER_TASK with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.OEMBED_SCHEDULER_TREG with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.OEMBED_SCHEDULER_LMGR with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.OEMBED_SCHEDULER_LMPR with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_CUSTOM_LIST with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_CUSTOM_LIST_ITEM with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_READ_STATUS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_ENTRIES_ROLLUP_ACTION with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_ENTRIES_ROLLUP_PERSON with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.L3T_ORGANIZATION with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.L3T_COMPANIES with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.L3T_USERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.L3T_PACKAGES with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.L3T_COMPANY_ENTITLEMENTS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.L3T_COMPANY_PKG_PREFS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.L3T_EXT_META2 with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.L3T_PACKAGE_DETAILS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.L3T_PACKAGE_LOCATION with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.L3T_USER_ENTITLEMENTS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.L3T_USER_PKG_PREFS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.L3T_EXT_POINTS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.L3T_EXT_BINDS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_TASKDEF with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_INDEXINGTASKDEF with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_OPTIMIZETASKDEF with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_FILECONTENTTASKDEF with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_BACKUPTASKDEF with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_SANDTASKDEF with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_FILESCONTENT with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_MIGTASKDEFINFO with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_INDEX_MANAGEMENT with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_RESUME_TOKENS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_INDEX_DOCS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_FEEDBACK with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_FEEDBACK_CONTEXT with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_FEEDBACK_PARAMETERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_STATS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_STRING_STATS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_NUMBER_STATS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_TIMER_STATS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_GLOBAL_SAND_PROPS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_POST_FILTERING_SERVICE with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_ECM_DOCUMENT_TYPE_PROPS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.LOTUSCONNECTIONSTASK with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.LOTUSCONNECTIONSTREG with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.LOTUSCONNECTIONSLMGR with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.LOTUSCONNECTIONSLMPR with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.SR_FILECONTENTINDEXINGTASK with distribution and detailed indexes all allow write access@
COMMIT@

RUNSTATS ON TABLE HOMEPAGE.APPREG_SERVICES with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_PATHS with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_OBJECTS with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_EVENTS with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_RESOURCES with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_THEMES with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_SERVICE_PATH with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_SERVICE_OBJECT with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_SERVICE_EVENT with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_SERVICE_RESOURCE with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_SERVICE_THEME with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_APPLICATIONS with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_SERVICE_APPLICATION with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_EXTENSIONS with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_APPLICATION_EXTENSION with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_EXTENSION_PATH with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_EXTENSION_OBJECT with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_EXTENSION_EVENT with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_EXTENSION_RESOURCE with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_EXTENSION_THEME with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.APPREG_SETTINGS with distribution and detailed indexes all allow write access@
COMMIT@

RUNSTATS ON TABLE HOMEPAGE.VM_EVENT_TRACKER with distribution and detailed indexes all allow write access@
COMMIT@

RUNSTATS ON TABLE HOMEPAGE.MIGN_EVENT_SYNC with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.MIGP_MIGRATIONPEOPLE with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.MIGP_POPULATESTATUS with distribution and detailed indexes all allow write access@
COMMIT@
RUNSTATS ON TABLE HOMEPAGE.MIGP_PROFILEKEYMAP with distribution and detailed indexes all allow write access@
COMMIT@

COMMIT@
FLUSH PACKAGE CACHE DYNAMIC@
connect reset@
terminate@
