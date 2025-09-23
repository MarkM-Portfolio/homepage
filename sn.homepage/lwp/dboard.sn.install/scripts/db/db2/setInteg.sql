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



-- SET INTEGRITY:
set integrity for  HOMEPAGE.HOMEPAGE_SCHEMA immediate checked@
COMMIT@
set integrity for  HOMEPAGE.MT_ORGANIZATION immediate checked@
COMMIT@
set integrity for  HOMEPAGE.PERSON immediate checked@
COMMIT@
set integrity for  HOMEPAGE.PERSON_ROLE immediate checked@
COMMIT@
set integrity for  HOMEPAGE.HP_USER_PREFS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.LOGINNAME immediate checked@
COMMIT@
set integrity for  HOMEPAGE.HP_UI immediate checked@
COMMIT@
set integrity for  HOMEPAGE.HP_TAB immediate checked@
COMMIT@
set integrity for  HOMEPAGE.HP_TAB_INST immediate checked@
COMMIT@
set integrity for  HOMEPAGE.WIDGET immediate checked@
COMMIT@
set integrity for  HOMEPAGE.HP_WIDGET_TAB immediate checked@
COMMIT@
set integrity for  HOMEPAGE.HP_WIDGET_INST immediate checked@
COMMIT@
set integrity for  HOMEPAGE.PREREQ immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NT_REPLYTO immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NT_REPLYTO_RECIPIENT immediate checked@
COMMIT@
set integrity for  HOMEPAGE.METRIC_STAT immediate checked@
COMMIT@
set integrity for  HOMEPAGE.OAUTH1_TOKEN immediate checked@
COMMIT@
set integrity for  HOMEPAGE.OAUTH1_PROVIDER immediate checked@
COMMIT@
set integrity for  HOMEPAGE.OAUTH1_CLIENT immediate checked@
COMMIT@
set integrity for  HOMEPAGE.OAUTH1_CONTEXT immediate checked@
COMMIT@
set integrity for  HOMEPAGE.OAUTH2_PROVIDER immediate checked@
COMMIT@
set integrity for  HOMEPAGE.OAUTH2_CLIENT immediate checked@
COMMIT@
set integrity for  HOMEPAGE.OAUTH2_GADGET_BINDING immediate checked@
COMMIT@
set integrity for  HOMEPAGE.OAUTH2_TOKEN immediate checked@
COMMIT@
set integrity for  HOMEPAGE.OH2P_CACHE immediate checked@
COMMIT@
set integrity for  HOMEPAGE.OH2P_CLIENTCFG immediate checked@
COMMIT@
set integrity for  HOMEPAGE.MTCONFIG immediate checked@
COMMIT@
set integrity for  HOMEPAGE.MT_CFG_DEFINITIONS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.MT_CFG_SETTINGS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.MT_CFG_FILES immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_SOURCE_TYPE_DEFAULT immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_SOURCE_TYPE immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_TEMPLATE immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_SCHEDULER_TASK immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_SCHEDULER_TREG immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_SCHEDULER_LMGR immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_SCHEDULER_LMPR immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_NEWS_STATUS_NETWORK immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_NEWS_STATUS_COMMENT immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_NEWS_STATUS_CONTENT immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_NEWS_COMMENT_CONTENT immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_RESOURCE immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_FOLLOWS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_COMM_FOLLOW immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_STORIES_CONTENT immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_NETWORK immediate checked@
COMMIT@
set integrity for  HOMEPAGE.EMD_RESOURCE_PREF immediate checked@
COMMIT@
set integrity for  HOMEPAGE.EMD_TRANCHE immediate checked@
COMMIT@
set integrity for  HOMEPAGE.EMD_TRANCHE_INFO immediate checked@
COMMIT@
set integrity for  HOMEPAGE.EMD_EMAIL_PREFS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_COMM_PERSON_FOLLOW immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_ENTRIES immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_STORIES immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_ENTRIES_ARCHIVE immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_AGGREGATED_READERS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_STATUS_UPDATE_READERS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_EXTERNAL_READERS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_ACTIONABLE_READERS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.BOARD immediate checked@
COMMIT@
set integrity for  HOMEPAGE.BOARD_ENTRIES immediate checked@
COMMIT@
set integrity for  HOMEPAGE.BOARD_COMMENTS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.BOARD_OBJECT_REFERENCE immediate checked@
COMMIT@
set integrity for  HOMEPAGE.BOARD_RECOMMENDATIONS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.BOARD_CURRENT_STATUS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.BOARD_MENTIONS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.DELETED_STORIES_QUEUE immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_COMM_SETTINGS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_PROFILES_VIEW immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_NOTIFICATION_SENT_READERS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_SAVED_READERS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_TOPICS_READERS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_MENTIONS_READERS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_COMMUNITIES_VIEW immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_AS_SEEDLIST immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_AS_COLLECTION_CONFIG immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_AS_CRAWLER_STATUS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_AS_CONTENT_INDEX_STATS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.IMG_CACHE immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_AS_COUNTS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.OEMBED_SCHEDULER_TASK immediate checked@
COMMIT@
set integrity for  HOMEPAGE.OEMBED_SCHEDULER_TREG immediate checked@
COMMIT@
set integrity for  HOMEPAGE.OEMBED_SCHEDULER_LMGR immediate checked@
COMMIT@
set integrity for  HOMEPAGE.OEMBED_SCHEDULER_LMPR immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_CUSTOM_LIST immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_CUSTOM_LIST_ITEM immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_READ_STATUS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_ENTRIES_ROLLUP_ACTION immediate checked@
COMMIT@
set integrity for  HOMEPAGE.NR_ENTRIES_ROLLUP_PERSON immediate checked@
COMMIT@
set integrity for  HOMEPAGE.L3T_ORGANIZATION immediate checked@
COMMIT@
set integrity for  HOMEPAGE.L3T_COMPANIES immediate checked@
COMMIT@
set integrity for  HOMEPAGE.L3T_USERS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.L3T_PACKAGES immediate checked@
COMMIT@
set integrity for  HOMEPAGE.L3T_COMPANY_ENTITLEMENTS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.L3T_COMPANY_PKG_PREFS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.L3T_EXT_META2 immediate checked@
COMMIT@
set integrity for  HOMEPAGE.L3T_PACKAGE_DETAILS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.L3T_PACKAGE_LOCATION immediate checked@
COMMIT@
set integrity for  HOMEPAGE.L3T_USER_ENTITLEMENTS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.L3T_USER_PKG_PREFS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.L3T_EXT_POINTS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.L3T_EXT_BINDS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_TASKDEF immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_INDEXINGTASKDEF immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_OPTIMIZETASKDEF immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_FILECONTENTTASKDEF immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_BACKUPTASKDEF immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_SANDTASKDEF immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_FILESCONTENT immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_MIGTASKDEFINFO immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_INDEX_MANAGEMENT immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_RESUME_TOKENS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_INDEX_DOCS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_FEEDBACK immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_FEEDBACK_CONTEXT immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_FEEDBACK_PARAMETERS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_STATS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_STRING_STATS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_NUMBER_STATS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_TIMER_STATS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_GLOBAL_SAND_PROPS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_POST_FILTERING_SERVICE immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_ECM_DOCUMENT_TYPE_PROPS immediate checked@
COMMIT@
set integrity for  HOMEPAGE.LOTUSCONNECTIONSTASK immediate checked@
COMMIT@
set integrity for  HOMEPAGE.LOTUSCONNECTIONSTREG immediate checked@
COMMIT@
set integrity for  HOMEPAGE.LOTUSCONNECTIONSLMGR immediate checked@
COMMIT@
set integrity for  HOMEPAGE.LOTUSCONNECTIONSLMPR immediate checked@
COMMIT@
set integrity for  HOMEPAGE.SR_FILECONTENTINDEXINGTASK immediate checked@
COMMIT@

set integrity for HOMEPAGE.APPREG_SERVICES immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_PATHS immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_OBJECTS immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_EVENTS immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_RESOURCES immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_THEMES immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_SERVICE_PATH immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_SERVICE_OBJECT immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_SERVICE_EVENT immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_SERVICE_RESOURCE immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_SERVICE_THEME immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_EXTENSION_PATH immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_EXTENSION_OBJECT immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_EXTENSION_EVENT immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_EXTENSION_RESOURCE immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_EXTENSION_THEME immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_APPLICATIONS immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_SERVICE_APPLICATION immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_EXTENSIONS immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_APPLICATION_EXTENSION immediate checked@
COMMIT@
set integrity for HOMEPAGE.APPREG_SETTINGS immediate checked@
COMMIT@

set integrity for HOMEPAGE.VM_EVENT_TRACKER immediate checked@
COMMIT@

set integrity for HOMEPAGE.MIGN_EVENT_SYNC immediate checked@
COMMIT@
set integrity for HOMEPAGE.MIGP_MIGRATIONPEOPLE immediate checked@
COMMIT@
set integrity for HOMEPAGE.MIGP_POPULATESTATUS immediate checked@
COMMIT@
set integrity for HOMEPAGE.MIGP_PROFILEKEYMAP immediate checked@
COMMIT@

COMMIT@
FLUSH PACKAGE CACHE DYNAMIC@
connect reset@
terminate@
