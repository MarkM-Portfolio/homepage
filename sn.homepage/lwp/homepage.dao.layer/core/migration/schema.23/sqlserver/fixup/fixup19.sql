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

-----------------------------------------------------------------
-- Fix a wrong tempalate set
-----------------------------------------------------------------

DELETE FROM HOMEPAGE.NR_TEMPLATE;

------------ 
--- START INSERT TEMPLATES
------------

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actor-Bei35oPldKwZTaR7aAiPFw4L08CyRW','actor', 'actorExtID', 'profilePhoto', 1); 

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actorProfile-CAsyXgPhQd7N3wSMw7C0IUe','actorProfiles', 'actorExtID', 'profilePhoto', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('target-Ah3vEOPQZDEpgpwcQ2JNaHFfMnMcG','subject', 'targetSubjectExtIDs', 'profilePhoto', 3);

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
VALUES	('comm-Qf0I5rSEaImjaCcds3HYi4SeCGYCDCN','newCommunityContainer', 'community.name;community.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-new-IduwKhHDNCLdmJFi0G7lwrE86IJ','communityContainerName', 'community.name;community.url', 'link', 1);

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

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-9qgQLuYLDdh66pf82um3c6q1lQdfgt5','newWikiContainer','wiki.name;wiki.url', 'link', 1);


------------
--- END INSERT TEMPLATES
------------

-----------------------------------------------------------------
-- Increase the size for BRIEF_DESC to 512
-----------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
ALTER COLUMN BRIEF_DESC  nvarchar(512);
GO

--------------------------------------------------------
-- Fixing issue with the wrong dbschema number (just oracle and sqlserver)
--------------------------------------------------------

ALTER TABLE HOMEPAGE.HOMEPAGE_SCHEMA
ALTER COLUMN  DBSCHEMAVER    NUMERIC(5,0) NOT NULL;
GO

--------------------------------------------------------
-- Fixing issue with the wrong WIDGET_HOMEPAGE_SPECIFIC number (just sqlserver)
--------------------------------------------------------
ALTER TABLE HOMEPAGE.WIDGET
ALTER COLUMN  WIDGET_HOMEPAGE_SPECIFIC  NUMERIC(5,0) NOT NULL ;
GO


-------------------------------------------------------------------------------
-- Updating the schema version from 17 to 18
-------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 19
WHERE   DBSCHEMAVER = 18;
GO

COMMIT;
