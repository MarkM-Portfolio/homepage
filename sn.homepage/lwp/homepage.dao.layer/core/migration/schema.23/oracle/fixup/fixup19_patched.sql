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
MODIFY (BRIEF_DESC  VARCHAR2(512));

--------------------------------------------------------
-- Fixing issue with the wrong dbschema number (just oracle and sqlserver)
--------------------------------------------------------

DROP TABLE "HOMEPAGE"."HOMEPAGE_SCHEMA";

CREATE TABLE "HOMEPAGE"."HOMEPAGE_SCHEMA" ( 
	COMPKEY VARCHAR2(36) NOT NULL,
	DBSCHEMAVER NUMBER(5, 0) NOT NULL,
	RELEASEVER VARCHAR2(32) DEFAULT ' ' NOT NULL
) 
TABLESPACE "HOMEPAGEREGTABSPACE";

INSERT INTO "HOMEPAGE"."HOMEPAGE_SCHEMA"
	( "COMPKEY", "DBSCHEMAVER", "RELEASEVER") 
VALUES 
	( 'HOMEPAGE', 18, '2.5.0.0');

---------------------------------------------------------------------------------
-- DbComparator fixing the widget table structure
---------------------------------------------------------------------------------
-- Create a temp table with the right  structure "HOMEPAGE"."TMP_WIDGET"

CREATE TABLE "HOMEPAGE"."TMP_WIDGET" (
	WIDGET_ID VARCHAR2(36) NOT NULL,
	WIDGET_TITLE VARCHAR2(256) NOT NULL,
	WIDGET_TEXT VARCHAR2(256),
	WIDGET_URL VARCHAR2(256) NOT NULL,
	WIDGET_ICON VARCHAR2(256),
	WIDGET_ENABLED NUMBER(5,0) DEFAULT 0 NOT NULL,
	WIDGET_SYSTEM NUMBER(5,0) DEFAULT 0 NOT NULL,
	WIDGET_HOMEPAGE_SPECIFIC NUMBER(5,0) DEFAULT 0 NOT NULL,
	WIDGET_PREVIEW_IMAGE VARCHAR2(256),
	WIDGET_CATEGORY VARCHAR2(36) DEFAULT ' ' NOT NULL,
	WIDGET_IS_DEFAULT_OPENED NUMBER(5,0) DEFAULT 0 NOT NULL,
	WIDGET_MULTIPLE_INSTANCES NUMBER(5,0) DEFAULT 0 NOT NULL,
	WIDGET_MARKED_CACHABLE NUMBER(5,0) DEFAULT 0 NOT NULL,
	WIDGET_SECURE_URL VARCHAR2(256) DEFAULT '',
	WIDGET_SECURE_ICON VARCHAR2(256) DEFAULT ''	
)
TABLESPACE "HOMEPAGEREGTABSPACE";

-- Copy the content from the widget table "HOMEPAGE"."WIDGET" to the "HOMEPAGE"."TMP_WIDGET"
INSERT INTO "HOMEPAGE"."TMP_WIDGET"
(	SELECT 	WIDGET.WIDGET_ID, 
		WIDGET.WIDGET_TITLE, 
		WIDGET.WIDGET_TEXT,
		WIDGET.WIDGET_URL,
		WIDGET.WIDGET_ICON,
		WIDGET.WIDGET_ENABLED,
		WIDGET.WIDGET_SYSTEM,
		WIDGET.WIDGET_HOMEPAGE_SPECIFIC,
		WIDGET.WIDGET_PREVIEW_IMAGE,
		WIDGET.WIDGET_CATEGORY,
		WIDGET.WIDGET_IS_DEFAULT_OPENED,
		WIDGET.WIDGET_MULTIPLE_INSTANCES,
		WIDGET.WIDGET_MARKED_CACHABLE,
		WIDGET.WIDGET_SECURE_URL,
		WIDGET.WIDGET_SECURE_ICON
	FROM "HOMEPAGE"."WIDGET" WIDGET
);

-- Remove the fk
ALTER TABLE "HOMEPAGE"."HP_WIDGET_INST" DROP CONSTRAINT "FK_WIDGET_ID";
ALTER TABLE "HOMEPAGE"."HP_WIDGET_TAB" DROP CONSTRAINT "FK_WID_TAB_WID_ID";
ALTER TABLE "HOMEPAGE"."PREREQ" DROP CONSTRAINT "FK_PREREQ_WIDGET";

-- Drop the original widget table
DROP TABLE "HOMEPAGE"."WIDGET";

-- Recreate the original widget table with the rigt ddl
CREATE TABLE "HOMEPAGE"."WIDGET" (
	WIDGET_ID VARCHAR2(36) NOT NULL,
	WIDGET_TITLE VARCHAR2(256) NOT NULL,
	WIDGET_TEXT VARCHAR2(256),
	WIDGET_URL VARCHAR2(256) NOT NULL,
	WIDGET_ICON VARCHAR2(256),
	WIDGET_ENABLED NUMBER(5,0) DEFAULT 0 NOT NULL,
	WIDGET_SYSTEM NUMBER(5,0) DEFAULT 0 NOT NULL,
	WIDGET_HOMEPAGE_SPECIFIC NUMBER(5,0) DEFAULT 0 NOT NULL,
	WIDGET_PREVIEW_IMAGE VARCHAR2(256),
	WIDGET_CATEGORY VARCHAR2(36) DEFAULT ' ' NOT NULL,
	WIDGET_IS_DEFAULT_OPENED NUMBER(5,0) DEFAULT 0 NOT NULL,
	WIDGET_MULTIPLE_INSTANCES NUMBER(5,0) DEFAULT 0 NOT NULL,
	WIDGET_MARKED_CACHABLE NUMBER(5,0) DEFAULT 0 NOT NULL,
	WIDGET_SECURE_URL VARCHAR2(256) DEFAULT '',
	WIDGET_SECURE_ICON VARCHAR2(256) DEFAULT ''	
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE "HOMEPAGE"."WIDGET"
	ADD (CONSTRAINT "PK_WIDGET" PRIMARY KEY ("WIDGET_ID")
    USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");

-- Moving back the backuped data in this table
INSERT INTO "HOMEPAGE"."WIDGET"
(	SELECT 	TMP_WIDGET.WIDGET_ID, 
		TMP_WIDGET.WIDGET_TITLE, 
		TMP_WIDGET.WIDGET_TEXT,
                TMP_WIDGET.WIDGET_URL,
                TMP_WIDGET.WIDGET_ICON,
                TMP_WIDGET.WIDGET_ENABLED,
                TMP_WIDGET.WIDGET_SYSTEM,
                TMP_WIDGET.WIDGET_HOMEPAGE_SPECIFIC,
                TMP_WIDGET.WIDGET_PREVIEW_IMAGE,
                TMP_WIDGET.WIDGET_CATEGORY,
                TMP_WIDGET.WIDGET_IS_DEFAULT_OPENED,
                TMP_WIDGET.WIDGET_MULTIPLE_INSTANCES,
                TMP_WIDGET.WIDGET_MARKED_CACHABLE,
                TMP_WIDGET.WIDGET_SECURE_URL,
                TMP_WIDGET.WIDGET_SECURE_ICON
	FROM "HOMEPAGE"."TMP_WIDGET" TMP_WIDGET
);

-- Putting back old constraints
ALTER TABLE "HOMEPAGE"."HP_WIDGET_TAB"	
    ADD CONSTRAINT "FK_WID_TAB_WID_ID" FOREIGN KEY ("WIDGET_ID")
	REFERENCES "HOMEPAGE"."WIDGET" ("WIDGET_ID");

ALTER TABLE "HOMEPAGE"."HP_WIDGET_INST"
    ADD CONSTRAINT "FK_WIDGET_ID" FOREIGN KEY ("WIDGET_ID")
	REFERENCES "HOMEPAGE"."WIDGET" ("WIDGET_ID");
	
ALTER TABLE "HOMEPAGE"."PREREQ"
	ADD CONSTRAINT "FK_PREREQ_WIDGET" FOREIGN KEY ("WIDGET_ID")
	REFERENCES "HOMEPAGE"."WIDGET" ("WIDGET_ID")
	ON DELETE CASCADE;	

-- Drop the tmp widget table
DROP TABLE "HOMEPAGE"."TMP_WIDGET";

-------------------------------------------------------------------------------
-- Updating the schema version from 18 to 19
-------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 19
WHERE   DBSCHEMAVER = 18;


COMMIT;
--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;
