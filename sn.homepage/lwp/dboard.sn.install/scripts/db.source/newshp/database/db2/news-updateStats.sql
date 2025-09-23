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

--NEWS



runstats on table HOMEPAGE.NR_SOURCE with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SUBSCRIPTION with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_NEWS_RECORDS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_TEMPLATE with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NR_SCHEDULER_LMGR with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SCHEDULER_LMPR with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SCHEDULER_TASK with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SCHEDULER_TREG with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NR_CATEGORY_TYPE with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_NEWS_SAVED with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_NEWS_DISCOVERY with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NR_NEWS_COMMENT_CONTENT with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_NEWS_STATUS_CONTENT with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_NEWS_STATUS_COMMENT with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_NEWS_STATUS_NETWORK with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NR_NETWORK with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_STORIES_CONTENT with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NR_COMM_FOLLOW with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_FOLLOWS with distribution and detailed indexes all allow write access;	
runstats on table HOMEPAGE.NR_RESOURCE with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_RESOURCE_TYPE with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_COMM_PERSON_FOLLOW with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_AGGREGATED_READERS with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.EMD_FREQUENCY_TYPE with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.EMD_RESOURCE_PREF with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.EMD_TRANCHE with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.EMD_TRANCHE_INFO with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.EMD_EMAIL_PREFS with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NR_RESPONSES_READERS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_PROFILES_READERS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_COMMUNITIES_READERS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_ACTIVITIES_READERS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_BLOGS_READERS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_BOOKMARKS_READERS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_FILES_READERS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_FORUMS_READERS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_WIKIS_READERS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_TAGS_READERS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_STATUS_UPDATE_READERS with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NR_RECOMMENDATION with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_ATTACHMENT with distribution and detailed indexes all allow write access;    

runstats on table HOMEPAGE.BOARD_ENTRIES with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.BOARD_COMMENTS with distribution and detailed indexes all allow write access;   
runstats on table HOMEPAGE.BOARD_OBJECT_REFERENCE with distribution and detailed indexes all allow write access;   
runstats on table HOMEPAGE.BOARD_RECOMMENDATIONS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.BOARD_CURRENT_STATUS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.BOARD with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NR_ACTIONABLE_READERS  with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SAVED_READERS  with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NR_COMM_SETTINGS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_DISCOVERY_VIEW with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_PROFILES_VIEW with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_NOTIFICATION_SENT_READERS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NR_ENTRIES  with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_ENTRIES_ARCHIVE  with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_TOPICS_READERS  with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.OAUTH1_TOKEN  with distribution and detailed indexes all allow write access;
commit;
runstats on table HOMEPAGE.OAUTH1_PROVIDER  with distribution and detailed indexes all allow write access;
commit;
runstats on table HOMEPAGE.OAUTH1_CLIENT  with distribution and detailed indexes all allow write access;
commit;
runstats on table HOMEPAGE.OAUTH1_CONTEXT  with distribution and detailed indexes all allow write access;
commit;
runstats on table HOMEPAGE.OAUTH2_PROVIDER  with distribution and detailed indexes all allow write access;
commit;
runstats on table HOMEPAGE.OAUTH2_CLIENT  with distribution and detailed indexes all allow write access;
commit;
runstats on table HOMEPAGE.OAUTH2_GADGET_BINDING  with distribution and detailed indexes all allow write access;
commit;
runstats on table HOMEPAGE.OAUTH2_TOKEN  with distribution and detailed indexes all allow write access;
commit;

runstats on table HOMEPAGE.NR_AS_COLLECTION_CONFIG  with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_AS_CRAWLER_STATUS  with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_AS_CONTENT_INDEX_STATS  with distribution and detailed indexes all allow write access;

