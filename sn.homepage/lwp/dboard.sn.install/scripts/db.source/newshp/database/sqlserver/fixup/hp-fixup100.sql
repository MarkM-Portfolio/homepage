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
------------------------ START HP FIXUP 100 -------------------------------------
---------------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.OH2P_CACHE (
	LOOKUPKEY nvarchar(256) NOT NULL, 
	UNIQUEID nvarchar(128) NOT NULL, 
	COMPONENTID nvarchar(256) NOT NULL, 
	TYPE nvarchar(64) NOT NULL, 
	SUBTYPE nvarchar(64), 
	CREATEDAT NUMERIC(19,0), 
	LIFETIME NUMERIC(10,0), 
	EXPIRES NUMERIC(19,0), 
	TOKENSTRING nvarchar(2048) NOT NULL, 
	CLIENTID nvarchar(64) NOT NULL, 
	USERNAME nvarchar(64) NOT NULL, 
	SCOPE nvarchar(512) NOT NULL, 
	REDIRECTURI nvarchar(2048), 
	STATEID nvarchar(64) NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OH2P_CACHE 
	ADD CONSTRAINT PK_LOOKUPKEY PRIMARY KEY (LOOKUPKEY);

CREATE INDEX OH2P_CACHE_EXPIRES ON HOMEPAGE.OH2P_CACHE (EXPIRES ASC);

GO


CREATE TABLE HOMEPAGE.OH2P_CLIENTCFG (
	COMPONENTID nvarchar(256) NOT NULL, 
	CLIENTID nvarchar(256) NOT NULL, 
	CLIENTSECRET nvarchar(256), 
	DISPLAYNAME nvarchar(256) NOT NULL, 
	REDIRECTURI nvarchar(2048), 
	ENABLED NUMERIC(5,0)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OH2P_CLIENTCFG 
	ADD CONSTRAINT PK_COMPIDCLIENTID PRIMARY KEY (COMPONENTID,CLIENTID);

GO



ALTER TABLE HOMEPAGE.HP_UI ADD WELCOME_NOTE NUMERIC(5,0) DEFAULT 1;

GO


-- Update Dogear widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/dogear.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/dogear.xml'
WHERE WIDGET_ID='dogear46x0a77x4a43x82aaxb00187218631';

-- Update My Bookmarks widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/personal/mybookmarks.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/personal/mybookmarks.xml'
WHERE WIDGET_ID='dembk46x0a77x4a43x82aaxb00187218631';

-- Update Popular Bookmarks widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/popular/popularbookmarks.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/popular/popularbookmarks.xml'
WHERE WIDGET_ID='depbk46x0a77x4a43x82aaxb00187218631';

-- Update Recent Bookmarks widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/recent/recentbookmarks.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/recent/recentbookmarks.xml'
WHERE WIDGET_ID='derbk46x0a77x4a43x82aaxb00187218631';

-- Update Watchlist widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/watching/watchlist.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/watching/watchlist.xml'
WHERE WIDGET_ID='dewl46x0a77x4a43x82aaxb00187218631';

-- Update Activities Original widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/activities.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/activities.xml'
WHERE WIDGET_ID='activitixa187x491dxa4bfx2e1261d0b6ec';

-- Update MyActivities Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/personal/myactivities.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/personal/myactivities.xml'
WHERE WIDGET_ID='myactxa187x491dxa4bfx2e1261d0b6ec';

-- Update Public Activities Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/pub/publicactivities.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/pub/publicactivities.xml'
WHERE WIDGET_ID='pubactxa187x491dxa4bfx2e1261d0b6ec';

-- Update Sidebar Activities ToDo Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/activitiesTodoList.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/activitiesTodoList.xml'
WHERE WIDGET_ID='activities-sidebar7x4229x8';

-- Update Communities Original Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/communities.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/communities.xml'
WHERE WIDGET_ID='communitxe7c4x4e08xab54x80e7a4eb8933';

-- Update MyCommunities Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/personal/mycommunities.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/personal/mycommunities.xml'
WHERE WIDGET_ID='mycommunxe7c4x4e08xab54x80e7a4eb8933';

-- Update Public Communities Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/pub/publiccommunities.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/pub/publiccommunities.xml'
WHERE WIDGET_ID='pubcommuxe7c4x4e08xab54x80e7a4eb8933';

-- Update Blogs widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/blogs/blogs.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/blogs/blogs.xml'
WHERE WIDGET_ID='blogs448xcd34x4565x9469x9c34fcefe48c';

-- Update Profiles Original Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/profiles.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/profiles.xml'
WHERE WIDGET_ID='profilesxaac7x4229x87bbx9a1c3551c591';

-- Update MyProfile widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/personal/myprofile.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/personal/myprofile.xml'
WHERE WIDGET_ID='myprofisxaac7x4229x87bbx9a1c3551c591';

-- Update Colleague Profile widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/colleagues/colleagueprofile.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/colleagues/colleagueprofile.xml'
WHERE WIDGET_ID='colprofsxaac7x4229x87bbx9a1c3551c591';

-- Update MyWiki widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/mywiki.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/mywiki.xml'
WHERE WIDGET_ID='mywikiz1xaac7x4229x87BBx91ac3551c591';

-- Update Popular Wiki Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/popularwiki.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/popularwiki.xml'
WHERE WIDGET_ID='pop-wiki1xaac7x4229x87BBx91ac3551c5';

-- Update Latest Wiki Widget 
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/latestwiki.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/latestwiki.xml'
WHERE WIDGET_ID='latest-wiki5jz1xaac7x4229x87BBx91ac';

-- Update MyFiles widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/files.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/files.xml'
WHERE WIDGET_ID='myFilesPb86locI7vRV4yY1KKawZvE8Qul88';

-- Update Files shared with me Widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/sharedFiles.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/sharedFiles.xml'
WHERE WIDGET_ID='sharedFilesV4fv72LD5NAcGv2nbrex0ExEq';

-- Update Sand widget
UPDATE HOMEPAGE.WIDGET
SET WIDGET_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/sand/recommend.xml',
    WIDGET_SECURE_URL='${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/sand/recommend.xml'
WHERE WIDGET_ID='recommend7x4f6hd93kd9';

GO


---------------------------------------------------------------------------------
------------------------ END HP FIXUP 100 ---------------------------------------
---------------------------------------------------------------------------------
