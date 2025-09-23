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

-----------------------------------------------------------------------------------------------------------
-- 1) SEARCH: Update INPUT_MIME_TYPE to be VARCHAR(256) NOT NULL
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.SR_FILESCONTENT 
ALTER COLUMN INPUT_MIME_TYPE nvarchar(256)
GO

-----------------------------------------------------------------------------------------------------------
-- 2) SEARCH: Adding these values:
-----------------------------------------------------------------------------------------------------------
INSERT INTO HOMEPAGE.SR_TASKDEF(TASK_ID,TASK_NAME,STARTBY,INTERVAL,TASK_TYPE,ENABLED)
VALUES('6f6ee109-6ad4-4926-afcb-d2a04a0e390d','15min-search-indexing-task','0 10/15 0-23 * * ?','0 1/15 0-23 * * ?','IndexingTask',1);

INSERT INTO HOMEPAGE.SR_TASKDEF(TASK_ID,TASK_NAME,STARTBY,INTERVAL,TASK_TYPE,ENABLED)
VALUES('ea789e87-c262-484b-92f4-d60af4bef3d3','nightly-optimize-task','0 15 1 * * ?','0 0 1 * * ?','OptimizeTask',1);

INSERT INTO HOMEPAGE.SR_INDEXINGTASKDEF(INDEXING_TASK_ID,TASK_ID,INDEXING_TASK_SERVICES,INDEXING_TASK_OPTIMIZE)
VALUES('315a416c-78e2-4cf4-bcb2-69eb8d3a2583','6f6ee109-6ad4-4926-afcb-d2a04a0e390d','activities-blogs-communities-dogear-files-profiles-wikis',0);

INSERT INTO HOMEPAGE.SR_OPTIMIZETASKDEF(OPTIMIZE_TASK_ID,TASK_ID)
VALUES('fd44131a-5075-4bcb-85a9-9e501bd010fa','ea789e87-c262-484b-92f4-d60af4bef3d3');

-----------------------------------------------------------------------------------------------------------
-- 3) HOMEPAGE: ADD two new colums WIDGET_SECURE_URL, WIDGET_SECURE_ICON  to the widget table
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.WIDGET
ADD WIDGET_SECURE_URL nvarchar(256) DEFAULT ''
GO

ALTER TABLE HOMEPAGE.WIDGET
ADD WIDGET_SECURE_ICON nvarchar(256) DEFAULT ''
GO

-- Update: Dogear Original Widget 							-	dogear46x0a77x4a43x82aaxb00187218631
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBookmarks16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/dogear/dogear.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBookmarks16.png',
							WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'dogear46x0a77x4a43x82aaxb00187218631';

-- Update: Dogear MyBookmarks widget 						-	dembk46x0a77x4a43x82aaxb00187218631
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBookmarks16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/dogear/personal/mybookmarks.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBookmarks16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'dembk46x0a77x4a43x82aaxb00187218631';

-- Update: Dogear PopularBookmarks widget 					-	depbk46x0a77x4a43x82aaxb00187218631
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBookmarks16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/dogear/popular/popularbookmarks.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBookmarks16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'depbk46x0a77x4a43x82aaxb00187218631';

-- Update: Dogear RecentBookmarks widget - opened by default - 	derbk46x0a77x4a43x82aaxb00187218631
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBookmarks16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/dogear/recent/recentbookmarks.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBookmarks16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'derbk46x0a77x4a43x82aaxb00187218631';

-- Update: Dogear WatchList widget 							-	dewl46x0a77x4a43x82aaxb00187218631
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBookmarks16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/dogear/watching/watchlist.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBookmarks16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'dewl46x0a77x4a43x82aaxb00187218631';

-- Update: Activities Original Widget 						-	activitixa187x491dxa4bfx2e1261d0b6ec
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconActivities16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/activities/activities.xml',
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconActivities16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'activitixa187x491dxa4bfx2e1261d0b6ec';

-- Update: Activities MyActivities widget 					-	myactxa187x491dxa4bfx2e1261d0b6ec
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconActivities16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/activities/personal/myactivities.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconActivities16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'myactxa187x491dxa4bfx2e1261d0b6ec';

-- Update: Activities PublicActivities 	- opened by default -	pubactxa187x491dxa4bfx2e1261d0b6ec
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconActivities16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/activities/pub/publicactivities.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconActivities16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'pubactxa187x491dxa4bfx2e1261d0b6ec';

-- Update: Sidebar Activities TODO widget - 				-	in update page activities-sidebar7x4229x8
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconActivities16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/activitiesSideBar/activities.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconActivities16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'activities-sidebar7x4229x8';

-- Update: Communities Original Widget 						-	communitxe7c4x4e08xab54x80e7a4eb8933
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconCommunities16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/communities/communities.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconCommunities16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'communitxe7c4x4e08xab54x80e7a4eb8933';
 
-- Update: Communities MyCommunities widget 				-	mycommunxe7c4x4e08xab54x80e7a4eb8933
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconCommunities16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/communities/personal/mycommunities.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconCommunities16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'mycommunxe7c4x4e08xab54x80e7a4eb8933';

-- Update: Communities PublicCommunities widget 			-	pubcommuxe7c4x4e08xab54x80e7a4eb8933
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconCommunities16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/communities/pub/publiccommunities.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconCommunities16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'pubcommuxe7c4x4e08xab54x80e7a4eb8933';

-- Update: Blogs widget 									-	blogs448xcd34x4565x9469x9c34fcefe48c
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBlogs16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/blogs/blogs.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBlogs16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'blogs448xcd34x4565x9469x9c34fcefe48c';

-- Update: Profiles Original Widget 						-	profilesxaac7x4229x87bbx9a1c3551c591
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconProfiles16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/profiles/profiles.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconProfiles16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'profilesxaac7x4229x87bbx9a1c3551c591';

-- Update: Profiles MyProfile widget - opened by default 	-	myprofisxaac7x4229x87bbx9a1c3551c591
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconProfiles16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/profiles/personal/myprofile.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconProfiles16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'myprofisxaac7x4229x87bbx9a1c3551c591';

-- Update: Profiles ColleagueProfile widget 				-	colprofsxaac7x4229x87bbx9a1c3551c591
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconProfiles16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/profiles/colleagues/colleagueprofile.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconProfiles16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'colprofsxaac7x4229x87bbx9a1c3551c591';

-- Update: My Wiky widget 									-	mywikiz1xaac7x4229x87BBx91ac3551c591
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconWikis16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/wiki/mywiki.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconWikis16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'mywikiz1xaac7x4229x87BBx91ac3551c591';

-- Update: Popular Wiki 									-	pop-wiki1xaac7x4229x87BBx91ac3551c5
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconWikis16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/wiki/popularwiki.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconWikis16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'pop-wiki1xaac7x4229x87BBx91ac3551c5';

-- Update: Latest wiki latest								-	latest-wiki5jz1xaac7x4229x87BBx91ac
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconWikis16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/wiki/latestwiki.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconWikis16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'latest-wiki5jz1xaac7x4229x87BBx91ac';

-- Update: My Files widget 									-	myFilesPb86locI7vRV4yY1KKawZvE8Qul88
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconFiles16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/files/files.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconFiles16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'myFilesPb86locI7vRV4yY1KKawZvE8Qul88';

-- Update: Files shared with me widget 						- 	sharedFilesV4fv72LD5NAcGv2nbrex0ExEq
UPDATE HOMEPAGE.WIDGET SET  WIDGET_ICON         = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconFiles16.png',
                            WIDGET_SECURE_URL   = 'web/widgets/files/sharedFiles.xml', 
                            WIDGET_SECURE_ICON  = '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconFiles16.png',
                            WIDGET_MULTIPLE_INSTANCES = 0
WHERE WIDGET_ID = 'sharedFilesV4fv72LD5NAcGv2nbrex0ExEq';


-----------------------------------------------------------------------------------------------------------
-- 4) NEWS: Clean up NR_SOURCE and NR_SUBSCRIPTION
-----------------------------------------------------------------------------------------------------------
DELETE FROM HOMEPAGE.NR_SUBSCRIPTION;

DELETE FROM HOMEPAGE.NR_SOURCE;

-----------------------------------------------------------------------------------------------------------
-- 5) EMD: Delete the EMD_JOBS contents
-----------------------------------------------------------------------------------------------------------
DELETE FROM HOMEPAGE.EMD_JOBS;

-----------------------------------------------------------------------------------------------------------
-- x) Upgrade fixup number to 16
-----------------------------------------------------------------------------------------------------------

-- Updating the schema version from 15 to 16
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 16
WHERE   DBSCHEMAVER = 15;
GO

COMMIT;