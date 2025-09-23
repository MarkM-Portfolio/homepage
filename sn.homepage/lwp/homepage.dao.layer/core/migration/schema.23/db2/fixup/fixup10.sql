-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2008, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

connect to HOMEPAGE;  

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
-- START THE DEFINITION FOR THE SEARCH DATABASE
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
DROP TABLE HOMEPAGE.LOTUSCONNECTIONSTASK;
DROP TABLE HOMEPAGE.LOTUSCONNECTIONSTREG;
DROP TABLE HOMEPAGE.LOTUSCONNECTIONSLMGR;
DROP TABLE HOMEPAGE.LOTUSCONNECTIONSLMPR;

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSTASK ("TASKID" BIGINT NOT NULL ,
              "VERSION" VARCHAR(5) NOT NULL ,
              "ROW_VERSION" INTEGER NOT NULL ,
              "TASKTYPE" INTEGER NOT NULL ,
              "TASKSUSPENDED" SMALLINT NOT NULL ,
              "CANCELLED" SMALLINT NOT NULL ,
              "NEXTFIRETIME" BIGINT NOT NULL ,
              "STARTBYINTERVAL" VARCHAR(254) ,
              "STARTBYTIME" BIGINT ,
              "VALIDFROMTIME" BIGINT ,
              "VALIDTOTIME" BIGINT ,
              "REPEATINTERVAL" VARCHAR(254) ,
              "MAXREPEATS" INTEGER NOT NULL ,
              "REPEATSLEFT" INTEGER NOT NULL ,
              "TASKINFO" BLOB(102400) LOGGED NOT COMPACT ,
              "NAME" VARCHAR(254) NOT NULL ,
              "AUTOPURGE" INTEGER NOT NULL ,
              "FAILUREACTION" INTEGER ,
              "MAXATTEMPTS" INTEGER ,
              "QOS" INTEGER ,
              "PARTITIONID" INTEGER ,
              "OWNERTOKEN" VARCHAR(200) NOT NULL ,
              "CREATETIME" BIGINT NOT NULL )  IN "HOMEPAGETABSPACE";

ALTER TABLE HOMEPAGE.LOTUSCONNECTIONSTASK ADD PRIMARY KEY ("TASKID");

CREATE INDEX HOMEPAGE.LOTUSCONNECTIONSTASK_IDX1 ON HOMEPAGE.LOTUSCONNECTIONSTASK ("TASKID",
              "OWNERTOKEN") ;

CREATE INDEX HOMEPAGE.LOTUSCONNECTIONSTASK_IDX2 ON HOMEPAGE.LOTUSCONNECTIONSTASK ("NEXTFIRETIME" ASC,
               "REPEATSLEFT",
               "PARTITIONID") CLUSTER;

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSTREG ("REGKEY" VARCHAR(254) NOT NULL ,
              "REGVALUE" VARCHAR(254) ) IN "HOMEPAGETABSPACE";

ALTER TABLE HOMEPAGE.LOTUSCONNECTIONSTREG ADD PRIMARY KEY ("REGKEY");

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSLMGR (LEASENAME VARCHAR(254) NOT NULL,
               LEASEOWNER VARCHAR(254) NOT NULL,
               LEASE_EXPIRE_TIME  BIGINT,
              DISABLED VARCHAR(5))IN "HOMEPAGETABSPACE";

ALTER TABLE HOMEPAGE.LOTUSCONNECTIONSLMGR ADD PRIMARY KEY ("LEASENAME");

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSLMPR (LEASENAME VARCHAR(254) NOT NULL,
              NAME VARCHAR(254) NOT NULL,
              VALUE VARCHAR(254) NOT NULL)IN "HOMEPAGETABSPACE";

CREATE INDEX HOMEPAGE.LOTUSCONNECTIONSLMPR_IDX1 ON HOMEPAGE.LOTUSCONNECTIONSLMPR (LEASENAME,
               NAME);

--------------------------------------
-- grants for scheduler tables
--------------------------------------

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSTASK TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSTREG  TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSLMGR  TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSLMPR TO USER LCUSER;

COMMIT;
	
--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
-- END THE DEFINITION FOR THE SEARCH DATABASE
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------
--- START FIXING THE WIDGET_INST INFO FOR EACH USERS
----------------------------------------------------------------------------------------

-- Remove the old closed widget
DELETE FROM  HOMEPAGE.HP_WIDGET_INST WHERE CONTAINER = '' AND ORDER_SEQUENCE = 0;

-- The new orders sequence count start from 0 and not from 1. Decrease the old value stored in ROW_NUM (now ORDER_SEQUENCE) of one
UPDATE HOMEPAGE.HP_WIDGET_INST SET ORDER_SEQUENCE = (ORDER_SEQUENCE-1);

-- By default IS_TOGGLED is setted to 0
UPDATE HOMEPAGE.HP_WIDGET_INST SET IS_TOGGLED = 0;

----------------------------------------------------------------------------------------
--- END FIXING THE WIDGET_INST INFO FOR EACH USERS
----------------------------------------------------------------------------------------

-- Removing and adding the new templates
DELETE FROM HOMEPAGE.NR_TEMPLATE;

------------ 
--- START INSERT TEMPLATES
------------
INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actor-Bei35oPldKwZTaR7aAiPFw4L08CyRW','actor', 'actorExtID', 'person', 1); 

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actorProfile-CAsyXgPhQd7N3wSMw7C0IUe','actorProfiles', 'actorExtID', 'profilePhoto', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('target-Ah3vEOPQZDEpgpwcQ2JNaHFfMnMcG','subject', 'targetSubjectExtIDs', 'person', 3);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actEnt-anXIr0xP82OSZnuoYbAZa2M5AsRFr', 'activityEntry', 'activity.entry.name;activity.entry.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actEntComm-WEJHX1TBvSCW0PS8ayfbPlZ1k','activityEntryWithComment', 'activity.entry.name;activity.entry.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actCont-WHHEhu6HTkRWtCQPylcUdSENF4mU', 'activityContainerName', 'activity.name;activity.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actCont-Mg2xRFBLmRqg3Zj7Etrdttiyc6OH','activityContainerNameACL', 'activity.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actToDo-psOHtvbY4lOJv8jOXJH5YgLoGYP7','toDoEntry', 'activity.todo.name;activity.todo.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blog-Itf9INx14G6bbCWRYNRpLhSawJXF8Qs','blogContainerName', 'blog.name;blog.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogNew-qsgRrfp88AKWys4wcm4gIuZm3T2p','newBlogContainer', 'blog.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogEntry-ubpjmKeG6Vi8XpQXYKVx3mtCqm','blogEntry', 'blog.entry.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogComment-92JYbZEOrk0CBLEJHYwkrqPA','blogEntryWithComment', 'blog.entry.name;blog.comment.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-Qf0I5rSEaImjaCcds3HYi4SeCGYCDCN','newCommunityContainer', 'community.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-new-IduwKhHDNCLdmJFi0G7lwrE86IJ','communityContainerName', 'community.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-topic6V808ijHf9TRgUSUqVVZoSOGg5','topicEntryName', 'community.topic.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-book-VSWMFF7uZVaE11XRe4Q1ElJIc6','communityBookmarkEntryName', 'community.bookmark.name;community.bookmark.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-feed-Ja6XFBT7kyfEJv07Unitc0MsJT','feedEntryName', 'community.feed.name;community.feed.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('dogear-book-DXAWZl1FIGMuMr4Ea4Lejbum','dogearBookmarkEntry', 'dogear.bookmark.title;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('tags-a7YmLTHTY2FdHKPsHU0wbAZCkgYrkk1','tag', 'tags', 'plain', 3);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('profileEntry-Vrh9M5YNmxNogF2oERZWyRE','profileLinkEntry', 'profiles.link.title;profiles.link.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('profile-status-HNX0o3AF32cFuPWhtYMjl','profileStatusEntry', 'status', 'plain', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-9qgQLuYLDdh66pf82um3c6q1lQ0gZl5','wikiContainerName','wiki.name;wiki.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-page-P75oD6oAdgoWCRNt4YYh7hZfuM','wikiEntryPage', 'wiki.page.name;wiki.page.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-comment-Ye2xdX8pbYM996pc7je30LI','wikiEntryPageCommented', 'wiki.page.name;wiki.page.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('file-bbqZpR6oxvE2sYAXxasKS0EtS1mDg4h','fileEntry', 'file.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('collection-7jEWoKkWx8ucNTo6Z7AndhRFh','collectionContainer', 'files.collection.name;htmlURL', 'link', 1);
------------
--- END INSERT TEMPLATES
------------

-- Updating the schema version from 9 to 10
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 10
WHERE   DBSCHEMAVER = 9;



reorg table HOMEPAGE.HP_WIDGET_INST use TEMPSPACE1;

------------------------------------------------
-- GRANT HOMEPAGE
------------------------------------------------
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.HP_UI TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.HP_TAB TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.HP_TAB_INST TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.HP_WIDGET_INST TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.HP_WIDGET_TAB TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NT_NOTIFICATION TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NT_NOTIFICATION_RECIPIENT TO USER LCUSER;

--------------------------------------
-- GRANT NEWS REPOSITORY
--------------------------------------
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_SOURCE TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_SUBSCRIPTION TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_NEWS_RECORDS TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_TEMPLATE TO USER LCUSER;

COMMIT;



--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate; 
