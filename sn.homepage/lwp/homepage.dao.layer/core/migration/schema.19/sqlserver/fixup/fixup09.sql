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

----------------------------------------------------------------------------------------
--- START CHANGING SOME WIDGET PK, AND ENABLE SOME WIDGETS TO BE OPENED BY DEFUALT
----------------------------------------------------------------------------------------

-- START Change the pk of My Files and Shared widget
-- removing fk 
ALTER TABLE HOMEPAGE.HP_WIDGET_INST DROP CONSTRAINT FK_WIDGET_ID;
GO
ALTER TABLE HOMEPAGE.PREREQ DROP CONSTRAINT FK_PREREQ_WIDGET;
GO
ALTER TABLE HOMEPAGE.HP_WIDGET_TAB DROP CONSTRAINT FK_WID_TAB_WID_ID;
GO

-- My Files widget change: 1g2ch9Bei35oPldKwZTaR7aAiPFw4L08CyRW or aQn0vVnPb86locI7vRV4yY1KKawZvE8Qul88 to: myFilesPb86locI7vRV4yY1KKawZvE8Qul88
UPDATE HOMEPAGE.WIDGET 
SET WIDGET_ID = 'myFilesPb86locI7vRV4yY1KKawZvE8Qul88' 
WHERE WIDGET_ID = '1g2ch9Bei35oPldKwZTaR7aAiPFw4L08CyRW';

UPDATE HOMEPAGE.WIDGET 
SET WIDGET_ID = 'myFilesPb86locI7vRV4yY1KKawZvE8Qul88' 
WHERE WIDGET_ID = 'aQn0vVnPb86locI7vRV4yY1KKawZvE8Qul88';

UPDATE HOMEPAGE.PREREQ 
SET WIDGET_ID = 'myFilesPb86locI7vRV4yY1KKawZvE8Qul88' 
WHERE WIDGET_ID = '1g2ch9Bei35oPldKwZTaR7aAiPFw4L08CyRW';

UPDATE HOMEPAGE.PREREQ 
SET WIDGET_ID = 'myFilesPb86locI7vRV4yY1KKawZvE8Qul88' 
WHERE WIDGET_ID = 'aQn0vVnPb86locI7vRV4yY1KKawZvE8Qul88';

UPDATE HOMEPAGE.HP_WIDGET_TAB 
SET WIDGET_ID = 'myFilesPb86locI7vRV4yY1KKawZvE8Qul88' 
WHERE WIDGET_ID = '1g2ch9Bei35oPldKwZTaR7aAiPFw4L08CyRW';

UPDATE HOMEPAGE.HP_WIDGET_TAB 
SET WIDGET_ID = 'myFilesPb86locI7vRV4yY1KKawZvE8Qul88' 
WHERE WIDGET_ID = 'aQn0vVnPb86locI7vRV4yY1KKawZvE8Qul88';

-- Shared files widget change: HxnvWqXFfQvWEJHX1TBvSCW0PS8ayfbPlZ1k or hnhebUi03cBV4fv72LD5NAcGv2nbrex0ExEq to: sharedFilesV4fv72LD5NAcGv2nbrex0ExEq
UPDATE HOMEPAGE.WIDGET 
SET WIDGET_ID = 'sharedFilesV4fv72LD5NAcGv2nbrex0ExEq' 
WHERE WIDGET_ID = 'HxnvWqXFfQvWEJHX1TBvSCW0PS8ayfbPlZ1k';

UPDATE HOMEPAGE.WIDGET 
SET WIDGET_ID = 'sharedFilesV4fv72LD5NAcGv2nbrex0ExEq' 
WHERE WIDGET_ID = 'hnhebUi03cBV4fv72LD5NAcGv2nbrex0ExEq';

UPDATE HOMEPAGE.PREREQ 
SET WIDGET_ID = 'sharedFilesV4fv72LD5NAcGv2nbrex0ExEq' 
WHERE WIDGET_ID = 'HxnvWqXFfQvWEJHX1TBvSCW0PS8ayfbPlZ1k';

UPDATE HOMEPAGE.PREREQ 
SET WIDGET_ID = 'sharedFilesV4fv72LD5NAcGv2nbrex0ExEq' 
WHERE WIDGET_ID = 'hnhebUi03cBV4fv72LD5NAcGv2nbrex0ExEq';

UPDATE HOMEPAGE.HP_WIDGET_TAB 
SET WIDGET_ID = 'sharedFilesV4fv72LD5NAcGv2nbrex0ExEq' 
WHERE WIDGET_ID = 'HxnvWqXFfQvWEJHX1TBvSCW0PS8ayfbPlZ1k';

UPDATE HOMEPAGE.HP_WIDGET_TAB 
SET WIDGET_ID = 'sharedFilesV4fv72LD5NAcGv2nbrex0ExEq' 
WHERE WIDGET_ID = 'hnhebUi03cBV4fv72LD5NAcGv2nbrex0ExEq';

-- putting back fk
ALTER TABLE HOMEPAGE.HP_WIDGET_INST
    ADD CONSTRAINT FK_WIDGET_ID FOREIGN KEY (WIDGET_ID)
	REFERENCES HOMEPAGE.WIDGET (WIDGET_ID)
GO

ALTER TABLE HOMEPAGE.PREREQ 
	ADD CONSTRAINT FK_PREREQ_WIDGET FOREIGN KEY (WIDGET_ID)
	REFERENCES HOMEPAGE.WIDGET (WIDGET_ID)
	ON DELETE CASCADE
GO

ALTER TABLE HOMEPAGE.HP_WIDGET_TAB	
    ADD CONSTRAINT FK_WID_TAB_WID_ID FOREIGN KEY (WIDGET_ID)
	REFERENCES HOMEPAGE.WIDGET (WIDGET_ID)
GO
	
-- END Change the pk of My Files and Shared widget		
	

-- Sidebar Activities TODO widget it must be open by default.
UPDATE HOMEPAGE.WIDGET 
SET WIDGET_IS_DEFAULT_OPENED = 1 
WHERE WIDGET_ID = 'activities-sidebar7x4229x8';

UPDATE HOMEPAGE.WIDGET 
SET WIDGET_IS_DEFAULT_OPENED = 1 
WHERE WIDGET_ID = 'latest-wiki5jz1xaac7x4229x87BBx91ac';

-- Latest wiki widget must be open by default.
UPDATE HOMEPAGE.WIDGET 
SET WIDGET_IS_DEFAULT_OPENED = 1 
WHERE WIDGET_ID = 'latest-wiki5jz1xaac7x4229x87BBx91ac';

-- My Files widget must be open by default.
UPDATE HOMEPAGE.WIDGET 
SET WIDGET_IS_DEFAULT_OPENED = 1 
WHERE WIDGET_ID = 'myFilesPb86locI7vRV4yY1KKawZvE8Qul88';

----------------------------------------------------------------------------------------
--- END CHANGING SOME WIDGET PK, AND ENABLE SOME WIDGETS TO BE OPENED BY DEFUALT
----------------------------------------------------------------------------------------

------------
--- START CHANGE SOME WRONG PREREQ_ID
------------
UPDATE HOMEPAGE.PREREQ 
SET PREREQ_ID = 'JlNdbgUL4kAwMEFAEAwlYXQ3YBOWnBry4x6Y' 
WHERE PREREQ_ID = 'nk7PWRAEWHHEhu6HTkRWtCQPylcUdSENF4mU';

UPDATE HOMEPAGE.PREREQ 
SET PREREQ_ID = 'U9PdeKPWtviSGv2QP57ZRady4RHYBPyXCL9a' 
WHERE PREREQ_ID = 'faDuCpJT3Mw4CC8S4bsd7HUvB53B0qrmFiFl';

------------
--- END CHANGE SOME WRONG PREREQ_ID
------------
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
VALUES	('profile-status-HNX0o3AF32cFuPWhtYMjl','profileStatusEntry', 'profiles.status', 'plain', 1);

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


------------
--- START ADDING INDEXES FOR NEW REP TABLES 
------------

CREATE UNIQUE INDEX NR_SOURCE_IX_UNIQUE
	ON HOMEPAGE.NR_SOURCE(SOURCE ASC, CONTAINER_ID ASC, ENTRY_ID ASC, IS_ACL ASC)
GO

------------
--- END ADDING INDEXES FOR NEW REP TABLES 
------------


-- Updating the schema version from 8 to 9
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 9
WHERE   DBSCHEMAVER = 8;




COMMIT;

