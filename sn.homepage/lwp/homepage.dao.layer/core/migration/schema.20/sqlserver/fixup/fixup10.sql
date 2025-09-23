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

USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO 

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
-- START THE DEFINITION FOR THE SEARCH DATABASE
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
DROP TABLE HOMEPAGE.LOTUSCONNECTIONSLMPR;
DROP TABLE HOMEPAGE.LOTUSCONNECTIONSLMGR;
DROP TABLE HOMEPAGE.LOTUSCONNECTIONSTREG;
DROP TABLE HOMEPAGE.LOTUSCONNECTIONSTASK;

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSTASK ( [TASKID] BIGINT NOT NULL ,
               [VERSION] NVARCHAR(5) NOT NULL ,
               [ROW_VERSION] INT NOT NULL ,
               [TASKTYPE] INT NOT NULL ,
               [TASKSUSPENDED] TINYINT NOT NULL ,
               [CANCELLED] TINYINT NOT NULL ,
               [NEXTFIRETIME] BIGINT NOT NULL ,
               [STARTBYINTERVAL] NVARCHAR(254) NULL ,
               [STARTBYTIME] BIGINT NULL ,
               [VALIDFROMTIME] BIGINT NULL ,
               [VALIDTOTIME] BIGINT NULL ,
               [REPEATINTERVAL] NVARCHAR(254) NULL ,
               [MAXREPEATS] INT NOT NULL ,
               [REPEATSLEFT] INT NOT NULL ,
               [TASKINFO] IMAGE NULL ,
               [NAME] NVARCHAR(254) NOT NULL ,
               [AUTOPURGE] INT NOT NULL ,
               [FAILUREACTION] INT NULL ,
               [MAXATTEMPTS] INT NULL ,
               [QOS] INT NULL ,
               [PARTITIONID] INT NULL ,
               [OWNERTOKEN] NVARCHAR(200) NOT NULL ,
               [CREATETIME] BIGINT NOT NULL ) 
GO

ALTER TABLE HOMEPAGE.LOTUSCONNECTIONSTASK WITH NOCHECK ADD CONSTRAINT LOTUSCONNECTIONSTASK_PK PRIMARY KEY  NONCLUSTERED ( [TASKID] ) 
GO

CREATE INDEX LOTUSCONNECTIONSTASK_IDX1 ON HOMEPAGE.LOTUSCONNECTIONSTASK ( [TASKID],
               [OWNERTOKEN] ) 
GO

CREATE CLUSTERED INDEX LOTUSCONNECTIONSTASK_IDX2 ON HOMEPAGE.LOTUSCONNECTIONSTASK ( [NEXTFIRETIME] ASC,
               [REPEATSLEFT],
               [PARTITIONID] )
GO

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSTREG ( [REGKEY] NVARCHAR(254) NOT NULL ,
               [REGVALUE] NVARCHAR(254) NULL ,
               PRIMARY KEY ( [REGKEY] ) )
GO

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSLMGR ( [LEASENAME] NVARCHAR(254) NOT NULL,
               [LEASEOWNER] NVARCHAR(254) NOT NULL,
               [LEASE_EXPIRE_TIME] BIGINT,
               [DISABLED] NVARCHAR(5) )
GO

ALTER TABLE HOMEPAGE.LOTUSCONNECTIONSLMGR WITH NOCHECK ADD CONSTRAINT LOTUSCONNECTIONSLMGR_PK PRIMARY KEY  NONCLUSTERED ( [LEASENAME] ) 
GO

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSLMPR ( [LEASENAME] NVARCHAR(224) NOT NULL,
               [NAME] NVARCHAR(224) NOT NULL,
               [VALUE] NVARCHAR(254) NOT NULL )
GO

CREATE INDEX LOTUSCONNECTIONSLMPR_IDX1 ON HOMEPAGE.LOTUSCONNECTIONSLMPR ( [LEASENAME],
               [NAME] ) 
GO

-- SEARCH
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSLMPR TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSLMGR TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSTREG TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSTASK TO HOMEPAGEUSER
GO
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




COMMIT;