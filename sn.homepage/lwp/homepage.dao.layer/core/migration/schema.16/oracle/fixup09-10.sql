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


------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
-- START THE DEFINITION FOR THE SEARCH DATABASE
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
DROP TABLE "HOMEPAGE"."LOTUSCONNECTIONSTASK";
DROP TABLE "HOMEPAGE"."LOTUSCONNECTIONSTREG";
DROP TABLE "HOMEPAGE"."LOTUSCONNECTIONSLMGR";
DROP TABLE "HOMEPAGE"."LOTUSCONNECTIONSLMPR";

CREATE TABLE "HOMEPAGE"."LOTUSCONNECTIONSTASK"("TASKID" NUMBER(19) NOT NULL,
               "VERSION" VARCHAR2(5) NOT NULL,
               "ROW_VERSION" NUMBER(10) NOT NULL,
               "TASKTYPE" NUMBER(10) NOT NULL,
               "TASKSUSPENDED" NUMBER(1) NOT NULL,
               "CANCELLED" NUMBER(1) NOT NULL,
               "NEXTFIRETIME" NUMBER(19) NOT NULL,
               "STARTBYINTERVAL" VARCHAR2(254),
               "STARTBYTIME" NUMBER(19),
               "VALIDFROMTIME" NUMBER(19),
               "VALIDTOTIME" NUMBER(19),
               "REPEATINTERVAL" VARCHAR2(254),
               "MAXREPEATS" NUMBER(10) NOT NULL,
               "REPEATSLEFT" NUMBER(10) NOT NULL,
               "TASKINFO" BLOB,
               "NAME" VARCHAR2(254),
               "AUTOPURGE" NUMBER(10) NOT NULL,
               "FAILUREACTION" NUMBER(10),
               "MAXATTEMPTS" NUMBER(10),
               "QOS" NUMBER(10),
               "PARTITIONID" NUMBER(10),
               "OWNERTOKEN" VARCHAR2(200) NOT NULL,
               "CREATETIME" NUMBER(19) NOT NULL,
               PRIMARY KEY ("TASKID") ) TABLESPACE "HOMEPAGEREGTABSPACE";

CREATE INDEX "HOMEPAGE"."LOTUSCONNECTIONSTASK_IDX1" ON "HOMEPAGE"."LOTUSCONNECTIONSTASK" ("TASKID",
              "OWNERTOKEN") ;

CREATE INDEX "HOMEPAGE"."LOTUSCONNECTIONSTASK_IDX2" ON "HOMEPAGE"."LOTUSCONNECTIONSTASK" ("NEXTFIRETIME" ASC,
               "REPEATSLEFT",
               "PARTITIONID") ;

CREATE TABLE "HOMEPAGE"."LOTUSCONNECTIONSTREG" ("REGKEY" VARCHAR2(254) NOT NULL ,
               "REGVALUE" VARCHAR2(254) ,
               PRIMARY KEY ( "REGKEY" )) TABLESPACE "HOMEPAGEREGTABSPACE";

CREATE TABLE "HOMEPAGE"."LOTUSCONNECTIONSLMGR" ("LEASENAME" VARCHAR2(254) NOT NULL,
               "LEASEOWNER" VARCHAR2(254),
               "LEASE_EXPIRE_TIME" NUMBER(19),
               "DISABLED" VARCHAR2(254),
               PRIMARY KEY ( "LEASENAME" )) TABLESPACE "HOMEPAGEREGTABSPACE";

CREATE TABLE "HOMEPAGE"."LOTUSCONNECTIONSLMPR" ("LEASENAME" VARCHAR2(254) NOT NULL,
               "NAME" VARCHAR2(254) NOT NULL,
               "VALUE" VARCHAR2(254) NOT NULL ) TABLESPACE "HOMEPAGEREGTABSPACE";

CREATE INDEX "HOMEPAGE"."LOTUSCONNECTIONSLMPR_IDX1" ON "HOMEPAGE"."LOTUSCONNECTIONSLMPR" ("LEASENAME",
               "NAME") ;


ALTER TABLE "HOMEPAGE"."LOTUSCONNECTIONSTASK" ENABLE ROW MOVEMENT;
ALTER TABLE "HOMEPAGE"."LOTUSCONNECTIONSTREG" ENABLE ROW MOVEMENT;
ALTER TABLE "HOMEPAGE"."LOTUSCONNECTIONSLMGR" ENABLE ROW MOVEMENT;
ALTER TABLE "HOMEPAGE"."LOTUSCONNECTIONSLMPR" ENABLE ROW MOVEMENT;

COMMIT;

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
-- END THE DEFINITION FOR THE SEARCH DATABASE
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------------------
--- START FIXING THE WIDGET_INST INFO FOR EACH USERS
----------------------------------------------------------------------------------------

-- Remove the old closed widget
DELETE FROM  HOMEPAGE.HP_WIDGET_INST WHERE CONTAINER IS NULL AND ORDER_SEQUENCE < 1;

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



COMMIT;

--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;