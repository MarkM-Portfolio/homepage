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

-- NEWS

------------ 
--- START INSERT TEMPLATES
------------

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actor-Bei35oPldKwZTaR7aAiPFw4L08CyRW','actor', 'actorInternalId', 'profilePhoto', 1); 

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actorProfile-CAsyXgPhQd7N3wSMw7C0IUe','actorProfiles', 'actorInternalId', 'profilePhoto', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('target-Ah3vEOPQZDEpgpwcQ2JNaHFfMnMcG','subject', 'targetSubjectsInternalIds', 'profilePhoto', 3);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actEnt-anXIr0xP82OSZnuoYbAZa2M5AsRFr', 'activityEntry', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actEntComm-WEJHX1TBvSCW0PS8ayfbPlZ1k','activityEntryWithComment', 'rollupName;rollupHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actCont-WHHEhu6HTkRWtCQPylcUdSENF4mU', 'activityContainerName', 'containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actCont-Mg2xRFBLmRqg3Zj7Etrdttiyc6OH','activityContainerNameACL', 'containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actToDo-psOHtvbY4lOJv8jOXJH5YgLoGYP7','toDoEntry', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blog-Itf9INx14G6bbCWRYNRpLhSawJXF8Qs','blogContainerName', 'containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogNew-qsgRrfp88AKWys4wcm4gIuZm3T2p','newBlogContainer', 'containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogEntry-ubpjmKeG6Vi8XpQXYKVx3mtCqm','blogEntry', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogComment-92JYbZEOrk0CBLEJHYwkrqPA','blogEntryWithComment', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-Qf0I5rSEaImjaCcds3HYi4SeCGYCDCN','newCommunityContainer', 'containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-new-IduwKhHDNCLdmJFi0G7lwrE86IJ','communityContainerName', 'containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-topic6V808ijHf9TRgUSUqVVZoSOGg5','topicEntryName', 'rollupName;rollupHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-book-VSWMFF7uZVaE11XRe4Q1ElJIc6','communityBookmarkEntryName', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-feed-Ja6XFBT7kyfEJv07Unitc0MsJT','feedEntryName', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('dogear-book-DXAWZl1FIGMuMr4Ea4Lejbum','dogearBookmarkEntry', 'itemName;itemDirectHttpPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('tags-a7YmLTHTY2FdHKPsHU0wbAZCkgYrkk1','tag', 'contentTags', 'plain', 3);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('profileEntry-Vrh9M5YNmxNogF2oERZWyRE','profileLinkEntry', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('profile-status-HNX0o3AF32cFuPWhtYMjl','profileStatusEntry', 'status', 'plain', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-9qgQLuYLDdh66pf82um3c6q1lQ0gZl5','wikiContainerName','containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-page-P75oD6oAdgoWCRNt4YYh7hZfuM','wikiEntryPage', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-comment-Ye2xdX8pbYM996pc7je30LI','wikiEntryPageCommented', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('file-bbqZpR6oxvE2sYAXxasKS0EtS1mDg4h','fileEntry', 'itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('collection-7jEWoKkWx8ucNTo6Z7AndhRFh','collectionContainer', 'containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-9qgQLuYLDdh66pf82um3c6q1lQdfgt5','newWikiContainer','containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('membership-9qgdh66pf82um3c6q1lQdfgt5','newMembers', 'memberAddedInternalIds', 'profilePhoto', 3);

INSERT INTO HOMEPAGE.NR_TEMPLATE 
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES ('blogCorrName-2G3abCWRYNRpLhSawJXF6Qd', 'blogCorrelationName', 'correlationName;correlationHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('general-containerffasfh3452yst42465','container','containerName;containerHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('general-item-slkmg9015bxBNKbkHkdf65','item','itemName;itemHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('general-correllation-asdfbe898Bjr95','correlationitem','correlationName;correlationHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('Actor-3913FB2B6B5D4323BFD4DAAC189B8','Actor', 'actorInternalId', 'profilePhoto', 1); 

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('Object-CEDEBA0964F2442EB3E5155493C0','Object', 'asObjectType;asObjectInternalId;asObjectName;asObjectHtmlPath', 'activityObject', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('Target-5EF5EDF61F9F47F692ED1CDFDF86','Target', 'asTargetType;asTargetInternalId;asTargetName;asTargetHtmlPath', 'activityObject', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('repostIcon-oPldKwZTaR7aAiPFw4L08CyRW','repostIcon', 'asRepostIcon', 'repostIcon', 1); 

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('itemAuthor-wPldKwZTaR7aAiPFw4L08CyRW','Author', 'asItemAuthor','profilePhoto', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('itemauthor-wPldKwZTaR7aAiPFw4L08CyRW','author', 'asItemAuthor','profilePhoto',  1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('itemCorrAuthorkla9034jl89vasgf3k5Wfd','correlationItemAuthor', 'correlationItemAuthor','profilePhoto',  1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('profileContainervmladkL9DFYU5ADFla9a','profileContainer', 'profileContainer', 'profilePhoto', 1); 


INSERT INTO HOMEPAGE.NR_TEMPLATE 
		(TEMPLATE_ID, NAME, FORMAT, DATA_SOURCE_STRING, NO_VALUES) 
VALUES ('mentionee1', 'mentionee1', 'profilePhoto', 'mentionee1', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE 
		(TEMPLATE_ID, NAME, FORMAT, DATA_SOURCE_STRING, NO_VALUES) 
VALUES ('mentionee2', 'mentionee2', 'profilePhoto', 'mentionee2', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE 
		(TEMPLATE_ID, NAME, FORMAT, DATA_SOURCE_STRING, NO_VALUES) 
VALUES ('mentionee3', 'mentionee3', 'profilePhoto', 'mentionee3', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogDuplicateEntry','blogDuplicateEntry', 'duplicateTo.name;duplicateTo.htmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
                (TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES  ('general-rollupitem-asdfbe898Bjr95','rollupitem','rollupName;rollupHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogEntryComment','blogEntryComment', 'itemName;itemHtmlPath;rollupName;rollupHtmlPath', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('lastActor','lastActor', 'lastActor', 'profilePhoto', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('previousActor','previousActor', 'previousActor', 'profilePhoto', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actionCount','actionCount', 'actionCount', 'plain', 1);

------------
--- END INSERT TEMPLATES
------------

-------------------------------------------------------------------------
-- START: INIT EMD_TRANCHE
-------------------------------------------------------------------------
-- EMD_TRANCHE 1
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_1_5oPldKwZTaR7aAiPFw4L08CyRW', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_1_KwZTaR7aAiPFw4L08CyRW', 'tranche_1_5oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 2
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_2_5oPldKwZTaR7aAiPFw4L08CyRW', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_2_KwZTaR7aAiPFw4L08CyRW','tranche_2_5oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 3
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_3_5oPldKwZTaR7aAiPFw4L08CyRW', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_3_KwZTaR7aAiPFw4L08CyRW','tranche_3_5oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 4
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_4_5oPldKwZTaR7aAiPFw4L08CyRW', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_4_KwZTaR7aAiPFw4L08CyRW','tranche_4_5oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 5
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_5_5oPldKwZTaR7aAiPFw4L08CyRW', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_5_KwZTaR7aAiPFw4L08CyRW','tranche_5_5oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 6
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_6_5oPldKwZTaR7aAiPFw4L08CyRW', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_6_KwZTaR7aAiPFw4L08CyRW','tranche_6_5oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 7
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_7_5oPldKwZTaR7aAiPFw4L08CyRW', 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_7_KwZTaR7aAiPFw4L08CyRW','tranche_7_5oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 8
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_8_5oPldKwZTaR7aAiPFw4L08CyRW', 8, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_8_KwZTaR7aAiPFw4L08CyRW','tranche_8_5oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 9
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_9_5oPldKwZTaR7aAiPFw4L08CyRW', 9, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_9_KwZTaR7aAiPFw4L08CyRW','tranche_9_5oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 10
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_10_oPldKwZTaR7aAiPFw4L08CyRW', 10, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_10_wZTaR7aAiPFw4L08CyRW','tranche_10_oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 11
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_11_oPldKwZTaR7aAiPFw4L08CyRW', 11, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_11_wZTaR7aAiPFw4L08CyRW','tranche_11_oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 12
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_12_oPldKwZTaR7aAiPFw4L08CyRW', 12, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_12_wZTaR7aAiPFw4L08CyRW','tranche_12_oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 13
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_13_oPldKwZTaR7aAiPFw4L08CyRW', 13, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_13_wZTaR7aAiPFw4L08CyRW','tranche_13_oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 14
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_14_oPldKwZTaR7aAiPFw4L08CyRW', 14, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_14_wZTaR7aAiPFw4L08CyRW','tranche_14_oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 15
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_15_oPldKwZTaR7aAiPFw4L08CyRW', 15, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_15_wZTaR7aAiPFw4L08CyRW','tranche_15_oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 16
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_16_oPldKwZTaR7aAiPFw4L08CyRW', 16, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_16_wZTaR7aAiPFw4L08CyRW','tranche_16_oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 17
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_17_oPldKwZTaR7aAiPFw4L08CyRW', 17, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_17_wZTaR7aAiPFw4L08CyRW','tranche_17_oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 18
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_18_oPldKwZTaR7aAiPFw4L08CyRW', 18, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_18_wZTaR7aAiPFw4L08CyRW','tranche_18_oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 19
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_19_oPldKwZTaR7aAiPFw4L08CyRW', 19, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_19_wZTaR7aAiPFw4L08CyRW','tranche_19_oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);

-- EMD_TRANCHE 20
INSERT INTO HOMEPAGE.EMD_TRANCHE
		(TRANCHE_ID, SEQ_NUMBER, LAST_PROCESSED_DAILY, LAST_PROCESSED_WEEKLY, IS_LOCKED, IS_LOCKED_DAILY, IS_LOCKED_WEEKLY)
VALUES          ('tranche_20_oPldKwZTaR7aAiPFw4L08CyRW', 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,0,0,0);

INSERT INTO HOMEPAGE.EMD_TRANCHE_INFO
                (TRANCHE_INFO_ID, TRANCHE_ID, COUNT_PROCESSED_DAILY, COUNT_PROCESSED_WEEKLY, AVG_EXEC_TIME_DAILY_MIN, AVG_EXEC_TIME_WEEKLY_MIN, DOMAIN_AFFINITY, N_USERS)
VALUES          ('tranche_info_20_wZTaR7aAiPFw4L08CyRW','tranche_20_oPldKwZTaR7aAiPFw4L08CyRW', 0, 0, 0, 0,' ',0);
-------------------------------------------------------------------------
-- END: INIT EMD_TRANCHE
-------------------------------------------------------------------------

------------
--- START INSERT NR_SOURCE_TYPE_DEFAULT
------------

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE_DEFAULT (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('activities_c9cax4cc4x80bx51af2ddef2c', 1, 'activities', 'activities', '{webresources}/web/com.ibm.lconn.core.styles/images/iconActivities16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconActivities16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE_DEFAULT (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('blogs_c9cax4cc4x80bx51af2ddef2c', 2, 'blogs', 'blogs', '{webresources}/web/com.ibm.lconn.core.styles/images/iconBlogs16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconBlogs16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE_DEFAULT (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('communities_c9cax4cc4x80bx51af2d', 3, 'communities', 'communities', '{webresources}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE_DEFAULT (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('wikis_c9cax4cc4x80bx51af2ddef2c', 4, 'wikis', 'wikis', '{webresources}/web/com.ibm.lconn.core.styles/images/iconWikis16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconWikis16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE_DEFAULT (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('profiles_c9cax4cc4x80bx51af2ddef2c', 5, 'profiles', 'profiles', '{webresources}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE_DEFAULT (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('homepage_c9cax4cc4x80bx51af2ddef2c', 6, 'homepage', 'homepage', '{webresources}/web/com.ibm.lconn.core.styles/images/iconHome16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconHome16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE_DEFAULT (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('dogear_c9cax4cc4x80bx51af2ddef2c', 7, 'dogear', 'dogear', '{webresources}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE_DEFAULT (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('files_c9cax4cc4x80bx51af2ddef2c', 8, 'files', 'files', '{webresources}/web/com.ibm.lconn.core.styles/images/iconFiles16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconFiles16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE_DEFAULT (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('forums_c9cax4cc4x80bx51af2ddef2c', 9, 'forums', 'forums', '{webresources}/web/com.ibm.lconn.core.styles/images/iconForums16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconForums16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE_DEFAULT 	
	(SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, URL, URL_SSL, IMAGE_URL_SSL)
VALUES 							
	('ecm_files', 	10000, 	'ecm_files', 'Libraries', '{webresources}/web/com.ibm.oneui3.styles/imageLibrary/Icons/ComponentsGray/LibraryManagedGray16.png', null, null, 1, null, '{ecm_files}', '{ecm_files}', '{webresources}/web/com.ibm.oneui3.styles/imageLibrary/Icons/ComponentsGray/LibraryManagedGray16.png'); 

------------
--- END INSERT NR_SOURCE_TYPE_DEFAULT
------------

------------
--- START INSERT NR_SOURCE_TYPE
------------

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('activities_c9cax4cc4x80bx51af2ddef2c', 1, 'activities', 'activities', '{webresources}/web/com.ibm.lconn.core.styles/images/iconActivities16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconActivities16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('blogs_c9cax4cc4x80bx51af2ddef2c', 2, 'blogs', 'blogs', '{webresources}/web/com.ibm.lconn.core.styles/images/iconBlogs16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconBlogs16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('communities_c9cax4cc4x80bx51af2d', 3, 'communities', 'communities', '{webresources}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('wikis_c9cax4cc4x80bx51af2ddef2c', 4, 'wikis', 'wikis', '{webresources}/web/com.ibm.lconn.core.styles/images/iconWikis16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconWikis16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('profiles_c9cax4cc4x80bx51af2ddef2c', 5, 'profiles', 'profiles', '{webresources}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('homepage_c9cax4cc4x80bx51af2ddef2c', 6, 'homepage', 'homepage', '{webresources}/web/com.ibm.lconn.core.styles/images/iconHome16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconHome16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('dogear_c9cax4cc4x80bx51af2ddef2c', 7, 'dogear', 'dogear', '{webresources}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('files_c9cax4cc4x80bx51af2ddef2c', 8, 'files', 'files', '{webresources}/web/com.ibm.lconn.core.styles/images/iconFiles16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconFiles16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, IMAGE_URL_SSL)
VALUES ('forums_c9cax4cc4x80bx51af2ddef2c', 9, 'forums', 'forums', '{webresources}/web/com.ibm.lconn.core.styles/images/iconForums16.png', null, null, 1, null, '{webresources}/web/com.ibm.lconn.core.styles/images/iconForums16.png');

INSERT INTO HOMEPAGE.NR_SOURCE_TYPE 	
	(SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, URL, URL_SSL, IMAGE_URL_SSL)
VALUES 							
	('ecm_files', 	10000, 	'ecm_files', 'Libraries', '{webresources}/web/com.ibm.oneui3.styles/imageLibrary/Icons/ComponentsGray/LibraryManagedGray16.png', null, null, 1, null, '{ecm_files}', '{ecm_files}', '{webresources}/web/com.ibm.oneui3.styles/imageLibrary/Icons/ComponentsGray/LibraryManagedGray16.png'); 

------------
--- END INSERT NR_SOURCE_TYPE
------------	



