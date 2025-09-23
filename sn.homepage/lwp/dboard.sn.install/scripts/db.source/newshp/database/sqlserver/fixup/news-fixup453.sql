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

---------------------------------------------------------------------------------
------------------------ START NEWS FIXUP 453 -----------------------------------
---------------------------------------------------------------------------------

------------------------------------------------
-- NR_SOURCE_TYPE_DEFAULT
------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SOURCE_TYPE_DEFAULT (
	SOURCE_TYPE_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5,0) NOT NULL, -- numeric that is 1,2,3 etc.. 100, 101..
	SOURCE nvarchar(36) NOT NULL,
	DISPLAY_NAME nvarchar(4000),
	IMAGE_URL nvarchar(2048),
	PUBLISHED DATETIME,
	UPDATED DATETIME,	
	IS_ENABLED NUMERIC(5,0),
	SUMMARY nvarchar (4000),
	URL nvarchar(2048),
	URL_SSL nvarchar(2048),
	IMAGE_URL_SSL nvarchar(2048),
	IS_DIGEST_ENABLED NUMERIC(5,0)  NOT NULL DEFAULT 0,
    DEFAULT_DIGEST_FREQUENCY NUMERIC(5,0)  NOT NULL DEFAULT 0,
    IS_DIGEST_LOCKED NUMERIC(5,0) NOT NULL DEFAULT 0
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE_DEFAULT 
  	ADD CONSTRAINT PK_SRC_TYPE_DEF_ID PRIMARY KEY(SOURCE_TYPE_ID);
  	
CREATE UNIQUE INDEX NR_SRC_TYPE_DEF_UIDX 
	ON HOMEPAGE.NR_SOURCE_TYPE_DEFAULT (SOURCE_TYPE);
	
CREATE UNIQUE INDEX NR_SRC_DEF_UIDX
	ON HOMEPAGE.NR_SOURCE_TYPE_DEFAULT (SOURCE);

GO	


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

-- Dropping UNIQUE constraints
ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE DROP CONSTRAINT SRC_TYPE_UNQ;

ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE DROP CONSTRAINT SRC_UNQ;

CREATE UNIQUE INDEX NR_SRC_TYPE_ORG_UIDX 
	ON HOMEPAGE.NR_SOURCE_TYPE (SOURCE_TYPE, ORGANIZATION_ID);
GO	

------------------------------------------------

--95519: Pre-register the ActivityStream gadget in initData, fixup, migration for Homepage DB. (95015)
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('7514c563-1a38-4f91-b0c2-881288dd7dcc','%widget.activitystream.name','%widget.activitystream.desc','${COMMON_CONTEXT_ROOT}/web/com.ibm.social.as/gadget/ActivityStream.xml',NULL,1,1,0,NULL,'CONNECTIONSAS',0,0,0,'${COMMON_CONTEXT_ROOT}/web/com.ibm.social.as/gadget/ActivityStream.xml',NULL,1,7,'intranet_access', -1);
GO

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('e2070ae0-1ca4-4de6-a550-9d7b3ea5fa84', '7514c563-1a38-4f91-b0c2-881288dd7dcc', '_noui.gadgetpanx11e1b0c40800200c9a66','primary');
GO
	
---------------------------------------------------------------------------------
------------------------ END   NEWS FIXUP 453 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  
