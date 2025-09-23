-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2009, 2015                                    
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

CREATE TABLE HOMEPAGE.HP_UI  (
	UI_ID nvarchar(36) NOT NULL,
	PERSON_ID nvarchar(36) NOT NULL,
	PERSON_LANG nvarchar(5) NOT NULL,
	LAST_VISIT DATETIME NOT NULL,
	WELCOME_MODE NUMERIC(5,0) NOT NULL DEFAULT 1,
	SHOW_DISABLED_WIDGETS NUMERIC(5,0) NOT NULL DEFAULT 0 
) ON [PRIMARY]
GO
 
CREATE TABLE HOMEPAGE.HP_TAB  (
	TAB_ID nvarchar(36) NOT NULL,
	DEFAULT_NAME nvarchar(36) NOT NULL,
	DEFAULT_N_COLUMNS NUMERIC(5, 0) NOT NULL DEFAULT 0,
	IS_NAME_CHANGEABLE NUMERIC(5,0) NOT NULL DEFAULT 0	
) ON [PRIMARY]
GO

CREATE TABLE HOMEPAGE.HP_TAB_INST  (
	TAB_INST_ID nvarchar(36) NOT NULL,
	TAB_ID nvarchar(36) NOT NULL,
	UI_ID nvarchar(36) NOT NULL,
	TAB_NAME nvarchar(36),
	N_COLUMNS NUMERIC(5, 0) NOT NULL DEFAULT 0,
	LAST_MODIFIED DATETIME NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE HOMEPAGE.HP_WIDGET_INST  (
	WIDGET_INST_ID nvarchar(36) NOT NULL,
	WIDGET_ID nvarchar(36) NOT NULL,
	TAB_INST_ID nvarchar(36) NOT NULL,
	WIDGET_SETTING nvarchar(2048),
	CONTAINER nvarchar(36),
	ORDER_SEQUENCE NUMERIC(5, 0),
	IS_FIXED NUMERIC(5,0),
	IS_TOGGLED NUMERIC(5,0),
	LAST_MODIFIED DATETIME NOT NULL,
	LAST_UPDATED DATETIME
) ON [PRIMARY]
GO

CREATE TABLE HOMEPAGE.HP_WIDGET_TAB (
	WIDGET_TAB_ID nvarchar(36) NOT NULL,
	WIDGET_ID nvarchar(36) NOT NULL,
	TAB_ID nvarchar(36) NOT NULL,
	TYPE nvarchar(36) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE HOMEPAGE.NT_NOTIFICATION  (
	  NOTIFICATION_ID nvarchar(36) NOT NULL,
	  NOTIFICATION_SOURCE nvarchar(36) NOT NULL,
	  NOTIFICATION_TYPE nvarchar(256) NOT NULL,
	  DATETIME_STAMP DATETIME NOT NULL,
	  SENDER_EXID nvarchar(36) NOT NULL,
	  SUBJECT nvarchar(256),
	  MESSAGE nvarchar(2048),
	  CONTAINER_NAME nvarchar(256),
	  CONTAINER_URL nvarchar(2048),
	  ITEM_NAME nvarchar(256),
	  ITEM_URL nvarchar(2048)
) ON [PRIMARY]
GO

CREATE TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT (
		  ID nvarchar(36) NOT NULL,
		  NOTIFICATION_ID nvarchar(36) NOT NULL,
		  RECIPIENT_EXID nvarchar(36) NOT NULL		  
) ON [PRIMARY]
GO

-- START Populate the HP_TAB table
-- Insert the panel tab page widget
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE)
VALUES 		('%panel.widgetx4a43x82aaxb00187218631' , '%panel.widget' , 2 , 0);

-- Insert the panel tab page update
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE)
VALUES 		('%panel.updatex4a43x82aaxb00187218631' , '%panel.update' , 1 , 0);

-- Insert the panel tab page widget
INSERT INTO HOMEPAGE.HP_TAB
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE)
VALUES 		('%panel.customx4a43x82aaxb00187218631' , '%panel.custom' , 2 , 1); 


-- START Populate the HP_HP_UI and table HP_TAB_INST
-- move all the info stored into HOMEPAGE.PERSON into HOMEPAGE.UI 
-- moving: LAST_VISIT, WELCOME_MODE, SHOW_DISABLED_WIDGETS from PERSON to UI
-- copy USER_ID in PERSON_ID
INSERT INTO HOMEPAGE.HP_UI  (UI_ID, PERSON_ID, PERSON_LANG, LAST_VISIT, WELCOME_MODE, SHOW_DISABLED_WIDGETS) 
	SELECT 'UIxxxxxx' + '' + SUBSTRING(P.PERSON_ID, 9, LEN(P.PERSON_ID)), P.PERSON_ID, 'en-EN', P.LAST_VISIT, P.WELCOME_MODE, P.SHOW_DISABLED_WIDGETS
	FROM HOMEPAGE.PERSON P;
-- END  Populate the HP_HP_UI


-- START Populate HP_TAB_INST
-- For each UI record create the related tab_inst records. So for each UI we will have 3 records
-- insert values inside the HP_TAB_INST with the default name %widget_page and %panel.update.
-- I don't insert %panel.custom because the user cannot have any custom panel
INSERT INTO HOMEPAGE.HP_TAB_INST  (TAB_INST_ID, TAB_ID, UI_ID, TAB_NAME, N_COLUMNS, LAST_MODIFIED) 
	SELECT '%panel.widget_' + '' + SUBSTRING(UI.UI_ID, 15, LEN(UI.UI_ID)), '%panel.widgetx4a43x82aaxb00187218631',UI.UI_ID, '%panel.widget',2, (CURRENT_TIMESTAMP)
	FROM HOMEPAGE.HP_UI UI;

INSERT INTO HOMEPAGE.HP_TAB_INST  (TAB_INST_ID, TAB_ID, UI_ID, TAB_NAME, N_COLUMNS, LAST_MODIFIED) 
	SELECT '%panel.update_' + '' + SUBSTRING(UI.UI_ID, 15, LEN(UI.UI_ID)), '%panel.updatex4a43x82aaxb00187218631',UI.UI_ID, '%panel.update',2, (CURRENT_TIMESTAMP)                
	FROM HOMEPAGE.HP_UI UI;
-- END Populate HP_TAB_INST
	
-- START Populate HP_WIDGET_INST
-- insert values inside the HP_WIDGET_INST with the default name %widget_page
-- add a temp column: TEMP_CONTAINER

ALTER TABLE HOMEPAGE.HP_WIDGET_INST
	ADD TEMP_CONTAINER NUMERIC(5,0);
GO

INSERT INTO HOMEPAGE.HP_WIDGET_INST  (WIDGET_INST_ID, WIDGET_ID, TAB_INST_ID, WIDGET_SETTING, CONTAINER, ORDER_SEQUENCE, IS_FIXED, IS_TOGGLED, LAST_MODIFIED, LAST_UPDATED, TEMP_CONTAINER) 
SELECT TEMP.USER_WIDGET_PREF_ID, TEMP.WIDGET_ID, TAB_INST_ID,  TEMP.WIDGET_SETTING, '', TEMP.ROW_NUM, 0, MAX_MIN, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, TEMP.COL_NUM
FROM (
        SELECT  USER_WIDGET_PREF.USER_WIDGET_PREF_ID, USER_WIDGET_PREF.WIDGET_ID, HP_TAB_INST.TAB_INST_ID ,USER_WIDGET_PREF.WIDGET_SETTING, USER_WIDGET_PREF.COL_NUM, USER_WIDGET_PREF.ROW_NUM, 0 as TMP_COL1, USER_WIDGET_PREF.MAX_MIN, CURRENT_TIMESTAMP as TMP_COL2, CURRENT_TIMESTAMP  as TMP_COL3
        FROM    HOMEPAGE.PERSON PERSON, HOMEPAGE.HP_TAB_INST HP_TAB_INST, HOMEPAGE.HP_UI HP_UI, HOMEPAGE.USER_WIDGET_PREF USER_WIDGET_PREF
        WHERE   PERSON.PERSON_ID = HP_UI.PERSON_ID AND HP_UI.UI_ID = HP_TAB_INST.UI_ID AND PERSON.PERSON_ID = USER_WIDGET_PREF.USER_ID AND
                HP_TAB_INST.TAB_NAME = '%panel.widget'         
) AS TEMP;

UPDATE HOMEPAGE.HP_WIDGET_INST SET 
	CONTAINER = '1'
WHERE HOMEPAGE.HP_WIDGET_INST.TEMP_CONTAINER = 1
GO

UPDATE HOMEPAGE.HP_WIDGET_INST SET 
	CONTAINER = '2'
WHERE HOMEPAGE.HP_WIDGET_INST.TEMP_CONTAINER = 2
GO

-- remove a temp column: TEMP_CONTAINER
ALTER TABLE HOMEPAGE.HP_WIDGET_INST DROP COLUMN TEMP_CONTAINER
GO
-- END Populate HP_WIDGET_INST

-- START Alter the Person table
ALTER TABLE HOMEPAGE.PERSON DROP COLUMN LAST_VISIT
GO

-- REMOVING WELCOME_MODE COLUMN. BEFORE WE NEED TO REMOVE THE CONSTRAINT
DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.PERSON') and parent_column_id=6)
SET @DROP_STMT = N'ALTER TABLE HOMEPAGE.PERSON DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO

ALTER TABLE HOMEPAGE.PERSON DROP COLUMN WELCOME_MODE
GO

-- REMOVING SHOW_DISABLED_WIDGETS COLUMN. BEFORE WE NEED TO REMOVE THE CONSTRAINT
DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.PERSON') and parent_column_id=7)
SET @DROP_STMT = N'ALTER TABLE HOMEPAGE.PERSON DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO

ALTER TABLE HOMEPAGE.PERSON DROP COLUMN SHOW_DISABLED_WIDGETS
GO
-- END Alter the Person table

-- drop the old table HOMEPAGE.USER_WIDGET_PREF
DROP TABLE HOMEPAGE.USER_WIDGET_PREF
GO

-- START to add the new columns into WIDGET tables and update it values
-- adding the new columns WIDGET_PREVIEW_IMAGE and WIDGET_CATEGORY to the WIDGET table
ALTER TABLE HOMEPAGE.WIDGET
ADD WIDGET_PREVIEW_IMAGE nvarchar(256)
GO

ALTER TABLE HOMEPAGE.WIDGET
ADD WIDGET_CATEGORY nvarchar(36) NOT NULL DEFAULT ''
GO

ALTER TABLE HOMEPAGE.WIDGET
ADD WIDGET_IS_DEFAULT_OPENED NUMERIC(5,0) DEFAULT 0 NOT NULL
GO

ALTER TABLE HOMEPAGE.WIDGET
ADD WIDGET_MULTIPLE_INSTANCES NUMERIC(5,0) DEFAULT 0 NOT NULL
GO

ALTER TABLE HOMEPAGE.WIDGET
ADD WIDGET_MARKED_CACHABLE  NUMERIC(5,0) DEFAULT 0 NOT NULL
GO

-- UPDATE THE OLD WIDGETS
UPDATE HOMEPAGE.WIDGET SET 
	WIDGET_CATEGORY = 'DOGEAR', 
	WIDGET_PREVIEW_IMAGE='${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg',
	WIDGET_MULTIPLE_INSTANCES=1,
	WIDGET_IS_DEFAULT_OPENED=1,
	WIDGET_MARKED_CACHABLE=0
WHERE WIDGET_TITLE = '%widget.dogear.name'AND WIDGET_ID='dogear46x0a77x4a43x82aaxb00187218631';

UPDATE HOMEPAGE.WIDGET SET 
	WIDGET_CATEGORY = 'ACTIVITIES', 
	WIDGET_PREVIEW_IMAGE='${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg',
	WIDGET_MULTIPLE_INSTANCES=1,
	WIDGET_IS_DEFAULT_OPENED=1,
	WIDGET_MARKED_CACHABLE=0
WHERE WIDGET_TITLE = '%widget.activities.name' AND WIDGET_ID='activitixa187x491dxa4bfx2e1261d0b6ec';

UPDATE HOMEPAGE.WIDGET SET 
	WIDGET_CATEGORY = 'COMMUNITIES', 
	WIDGET_PREVIEW_IMAGE = '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg',
	WIDGET_MULTIPLE_INSTANCES=1,
	WIDGET_IS_DEFAULT_OPENED=1,
	WIDGET_MARKED_CACHABLE=0
WHERE WIDGET_TITLE = '%widget.communities.name' AND WIDGET_ID='communitxe7c4x4e08xab54x80e7a4eb8933';

UPDATE HOMEPAGE.WIDGET SET 
	WIDGET_CATEGORY = 'BLOGS', 
	WIDGET_PREVIEW_IMAGE = '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg',
	WIDGET_MULTIPLE_INSTANCES=1,
	WIDGET_IS_DEFAULT_OPENED=1,
	WIDGET_MARKED_CACHABLE=0
WHERE WIDGET_TITLE = '%widget.blogs.name' AND WIDGET_ID='blogs448xcd34x4565x9469x9c34fcefe48c';

UPDATE HOMEPAGE.WIDGET SET 
	WIDGET_CATEGORY = 'PROFILES', 
	WIDGET_PREVIEW_IMAGE='${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg',
	WIDGET_MULTIPLE_INSTANCES=1,
	WIDGET_IS_DEFAULT_OPENED=1,
	WIDGET_MARKED_CACHABLE=0
WHERE WIDGET_TITLE = '%widget.profiles.name' AND WIDGET_ID='profilesxaac7x4229x87bbx9a1c3551c591';

UPDATE HOMEPAGE.WIDGET SET 
	WIDGET_CATEGORY = 'OTHER',
	WIDGET_PREVIEW_IMAGE = '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg',
	WIDGET_MULTIPLE_INSTANCES=0,
	WIDGET_IS_DEFAULT_OPENED=0,
	WIDGET_MARKED_CACHABLE=0
WHERE   WIDGET_TITLE != '%widget.dogear.name' AND
        WIDGET_TITLE != '%widget.activities.name' AND
        WIDGET_TITLE != '%widget.communities.name' AND
        WIDGET_TITLE != '%widget.blogs.name' AND
        WIDGET_TITLE != '%widget.profiles.name';
        
-- END to add the new columns into WIDGET tables and update it values

------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------


--------------------------------------
-- REMOVE THE OLD CONSTRAINTS THAT NOW ARE BEEN RENAMED
--------------------------------------

ALTER TABLE HOMEPAGE.LOGINNAME 
	DROP CONSTRAINT FK_PERSON_ID;
GO

--------------------------------------
-- CREATE THE CONSTRAINTS
--------------------------------------

ALTER TABLE HOMEPAGE.HP_UI
    ADD CONSTRAINT PK_HP_UI PRIMARY KEY (UI_ID)  
GO    

ALTER TABLE HOMEPAGE.HP_TAB
    ADD CONSTRAINT PK_HP_TAB PRIMARY KEY (TAB_ID)
GO
    
ALTER TABLE HOMEPAGE.HP_TAB_INST
    ADD CONSTRAINT PK_HP_TAB_INST PRIMARY KEY (TAB_INST_ID)    
GO    
	
ALTER TABLE HOMEPAGE.HP_WIDGET_TAB 
	ADD CONSTRAINT PK_WIDGET_TAB PRIMARY KEY (WIDGET_TAB_ID)
GO

ALTER TABLE HOMEPAGE.HP_WIDGET_INST
	ADD CONSTRAINT PK_HP_WIDGET_INST PRIMARY KEY (WIDGET_INST_ID)
GO
	
ALTER TABLE HOMEPAGE.NT_NOTIFICATION
	ADD CONSTRAINT PK_NOTIFICATION PRIMARY KEY (NOTIFICATION_ID)
	
ALTER TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT
	ADD CONSTRAINT PK_NOTIF_RECIP PRIMARY KEY (ID)
GO

ALTER TABLE HOMEPAGE.LOGINNAME
	ADD CONSTRAINT FK_LN_PERSON_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID)
GO
	
ALTER TABLE HOMEPAGE.HP_UI
    ADD CONSTRAINT FK_UI_PERSON_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID)	
GO	
	
ALTER TABLE HOMEPAGE.HP_TAB_INST
    ADD CONSTRAINT FK_UI_ID FOREIGN KEY (UI_ID)
	REFERENCES HOMEPAGE.HP_UI(UI_ID)
GO

ALTER TABLE HOMEPAGE.HP_TAB_INST
    ADD CONSTRAINT FK_TAB_INST_TAB_ID FOREIGN KEY (TAB_ID)
	REFERENCES HOMEPAGE.HP_TAB(TAB_ID)
GO

ALTER TABLE HOMEPAGE.HP_WIDGET_INST
    ADD CONSTRAINT FK_WIDGET_ID FOREIGN KEY (WIDGET_ID)
	REFERENCES HOMEPAGE.WIDGET (WIDGET_ID)
GO

ALTER TABLE HOMEPAGE.HP_WIDGET_INST
    ADD CONSTRAINT FK_TAB_INST_ID FOREIGN KEY (TAB_INST_ID)
	REFERENCES HOMEPAGE.HP_TAB_INST (TAB_INST_ID)
GO

ALTER TABLE HOMEPAGE.HP_WIDGET_TAB	
    ADD CONSTRAINT FK_WID_TAB_WID_ID FOREIGN KEY (WIDGET_ID)
	REFERENCES HOMEPAGE.WIDGET (WIDGET_ID)
GO

ALTER TABLE HOMEPAGE.HP_WIDGET_TAB	
    ADD CONSTRAINT FK_WID_TAB_TAB_ID FOREIGN KEY (TAB_ID)
	REFERENCES HOMEPAGE.HP_TAB (TAB_ID)
GO	
	
ALTER TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT 
	ADD CONSTRAINT FK_RECIP_NOTIF FOREIGN KEY (NOTIFICATION_ID)
	REFERENCES HOMEPAGE.NT_NOTIFICATION (NOTIFICATION_ID)
	ON DELETE CASCADE
GO

--------------------------------------
-- CREATE THE INDEXES
--------------------------------------
CREATE INDEX WIDGET_INST
	ON HOMEPAGE.HP_WIDGET_INST (TAB_INST_ID ASC, WIDGET_ID ASC)
GO
	
CREATE INDEX HP_UI_PERSON_ID_INDEX
	ON HOMEPAGE.HP_UI (PERSON_ID)
GO

CREATE INDEX NT_NOTIFICATION_EXID_INDEX
	ON HOMEPAGE.NT_NOTIFICATION(SENDER_EXID)
GO
	
CREATE INDEX NT_NOTIF_RECT_EXID_INDEX
	ON HOMEPAGE.NT_NOTIFICATION_RECIPIENT(RECIPIENT_EXID)
GO	
	
CREATE INDEX NT_NOTIF_RECT_NID_INDEX
	ON HOMEPAGE.NT_NOTIFICATION_RECIPIENT(NOTIFICATION_ID)
GO	
	
------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------	

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
-- STARTING OF THE DEFINITION FOR THE NEWS REPOSITORY DATABASE
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------

----------------------------------------------------------------------------
-- NEWS REPOSITORY - CREATE THE TABLES
----------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_SOURCE (
	SOURCE_ID nvarchar(36) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(36) NOT NULL,
	CONTAINER_NAME nvarchar(256) NOT NULL,
	CONTAINER_URL nvarchar(2048),
	ENTRY_ID  nvarchar(36),
	ENTRY_NAME nvarchar(256),
	ENTRY_URL nvarchar(2048),
	ENTRY_ATOM_URL nvarchar(2048),
	IS_ACL NUMERIC(5,0) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE HOMEPAGE.NR_SUBSCRIPTION  (
	SUBSCRIPTION_ID nvarchar(36) NOT NULL,
	PERSON_ID nvarchar(36) NOT NULL,
	SOURCE_ID nvarchar(36) NOT NULL,
	IS_EXPLICIT NUMERIC(5,0) NOT NULL
) ON [PRIMARY]
GO

CREATE TABLE HOMEPAGE.NR_NEWS_RECORDS (
	NEWS_RECORDS_ID nvarchar(36) NOT NULL,
	EVENT_NAME nvarchar(256) NOT NULL,
    READER_ID nvarchar(36),
    SOURCE nvarchar(36),
    CONTAINER_ID nvarchar(36),
    CONTAINER_NAME nvarchar(256),
    CONTAINER_URL nvarchar(2048),
    ENTRY_ID nvarchar(36),
    ENTRY_NAME nvarchar(256),
    ENTRY_URL nvarchar(2048),
    ENTRY_ATOM_URL nvarchar(2048),
    CREATION_DATE DATETIME NOT NULL,
    IS_INBOX NUMERIC(5,0) NOT NULL,
    IS_SAVED NUMERIC(5,0) NOT NULL,
    IS_TOP_STORY NUMERIC(5,0) NOT NULL,
    IS_PUBLIC NUMERIC(5,0) NOT NULL,
    IS_MAILED NUMERIC(5,0) NOT NULL,
    TIME_STAMP DATETIME NOT NULL,
    BRIEF_DESC nvarchar(256),
    IS_BRIEF_DESC_RTL NUMERIC(5,0) NOT NULL,
    ACTOR_UUID nvarchar(36),
    EVENT_RECORD_UUID nvarchar(36) NOT NULL,
    RELATED_COMM_UUID nvarchar(36),
    RELATED_COMM_NAME nvarchar(256),
    TAGS nvarchar(max),
    META_TEMPLATE nvarchar(max) NOT NULL
) ON [PRIMARY]
GO


CREATE TABLE HOMEPAGE.NR_TEMPLATE (
	TEMPLATE_ID nvarchar(36) NOT NULL,
 	NAME nvarchar(256) NOT NULL,
 	FORMAT nvarchar(256) NOT NULL,
 	DATA_SOURCE_STRING nvarchar(256) NOT NULL,
 	NO_VALUES NUMERIC(5,0) NOT NULL
) ON [PRIMARY]
GO

----------------------------------------------------------------------------
-- NEWS REPOSITORY -  CREATE THE CONSTRAINTS
----------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_SOURCE
    ADD CONSTRAINT PK_SOURCE_ID PRIMARY KEY(SOURCE_ID)    
GO

ALTER TABLE HOMEPAGE.NR_SUBSCRIPTION
    ADD CONSTRAINT PK_SUBSCRIPTION_ID PRIMARY KEY(SUBSCRIPTION_ID)
GO

ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
    ADD CONSTRAINT PK_NR_NEWS_RECORDS PRIMARY KEY(NEWS_RECORDS_ID)
GO

ALTER TABLE HOMEPAGE.NR_TEMPLATE 
    ADD CONSTRAINT PK_TEMPLATE_ID PRIMARY KEY(TEMPLATE_ID)
GO
    
ALTER TABLE HOMEPAGE.NR_SUBSCRIPTION
    ADD CONSTRAINT FK_PERSON_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);
GO

ALTER TABLE HOMEPAGE.NR_SUBSCRIPTION
    ADD CONSTRAINT FK_SOURCE_ID FOREIGN KEY (SOURCE_ID)
	REFERENCES HOMEPAGE.NR_SOURCE (SOURCE_ID)	
GO

----------------------------------------------------------------------------
-- NEWS REPOSITORY -  CREATE THE INDEXES
----------------------------------------------------------------------------
CREATE UNIQUE INDEX NR_SUBSCRIPTION_IX_UNIQUE
	ON HOMEPAGE.NR_SUBSCRIPTION(PERSON_ID ASC, SOURCE_ID ASC)
GO
	

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
-- END OF THE DEFINITION FOR THE NEWS REPOSITORY DATABASE
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------


------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------

---------------------------------
--- START INSERTING THE NEW WIDGET
---------------------------------
-- Beginning Dogear widgets 

-- Dogear MyBookmarks widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('dembk46x0a77x4a43x82aaxb00187218631', '%widget.dogear.personal.name', '%widget.dogear.personal.desc', 'web/widgets/dogear/personal/mybookmarks.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/dogear_icon.gif', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR', 0,  1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7c1a34f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'dembk46x0a77x4a43x82aaxb00187218631');

-- Dogear PopularBookmarks widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('depbk46x0a77x4a43x82aaxb00187218631', '%widget.dogear.popular.name', '%widget.dogear.popular.desc', 'web/widgets/dogear/popular/popularbookmarks.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/dogear_icon.gif', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR', 0,  1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7w1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'depbk46x0a77x4a43x82aaxb00187218631');

-- Dogear RecentBookmarks widget - opened by default
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('derbk46x0a77x4a43x82aaxb00187218631',  '%widget.dogear.recent.name', '%widget.dogear.recent.desc', 'web/widgets/dogear/recent/recentbookmarks.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/dogear_icon.gif', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR', 0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7t1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'derbk46x0a77x4a43x82aaxb00187218631');

-- Dogear WatchList widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('dewl46x0a77x4a43x82aaxb00187218631', '%widget.dogear.watchlist.name', '%widget.dogear.watchlist.desc', 'web/widgets/dogear/watching/watchlist.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/dogear_icon.gif', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR',  0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7m1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'dewl46x0a77x4a43x82aaxb00187218631');

-- End Dogear widgets

-- Beginning Activities widgets

-- Activities MyActivities widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('myactxa187x491dxa4bfx2e1261d0b6ec', '%widget.activities.personal.name', '%widget.activities.personal.desc', 'web/widgets/activities/personal/myactivities.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/activities_icon.gif', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'ACTIVITIES', 0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0b9ccx9ffex4874xbf4ax10e389b5b4e3', 'activities', 'myactxa187x491dxa4bfx2e1261d0b6ec');

-- Activities PublicActivities widget - opened by default
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('pubactxa187x491dxa4bfx2e1261d0b6ec',  '%widget.activities.public.name', '%widget.activities.public.desc', 'web/widgets/activities/pub/publicactivities.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/activities_icon.gif', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'ACTIVITIES', 0,  1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0v9ccx9ffex4523xbf4ax10e389b5b4e3', 'activities', 'pubactxa187x491dxa4bfx2e1261d0b6ec');

-- Sidebar Activities TODO widget - in update page
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('activities-sidebar7x4229x8',  '%widget.activities.sidebar.todo.name', '%widget.activities.sidebar.todo.desc', 'web/widgets/activitiesSideBar/activities.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/activities_icon.gif', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'ACTIVITIES', 0,  1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0z9ccx9ffex4523xbf4ax10e389b5b4e3', 'activities', 'activities-sidebar7x4229x8');

-- End Activities widgets



-- Beginning Communities widgets

-- Communities MyCommunities widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('mycommunxe7c4x4e08xab54x80e7a4eb8933',  '%widget.communities.personal.name', '%widget.communities.personal.desc', 'web/widgets/communities/personal/mycommunities.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/communities_icon.gif', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'COMMUNITIES', 0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('73de6624x9317x4195x90c0x696c1b6da4ff', 'communities', 'mycommunxe7c4x4e08xab54x80e7a4eb8933');

-- Communities PublicCommunities widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID,  WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('pubcommuxe7c4x4e08xab54x80e7a4eb8933',  '%widget.communities.public.name', '%widget.communities.public.desc', 'web/widgets/communities/pub/publiccommunities.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/communities_icon.gif', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'COMMUNITIES', 0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('73de8824x9677y4195x90c0x696c1b6da4ff', 'communities', 'pubcommuxe7c4x4e08xab54x80e7a4eb8933');

-- End Communities widgets

-- Beginning Profiles widgets

-- Profiles MyProfile widget - opened by default
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID,  WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('myprofisxaac7x4229x87bbx9a1c3551c591', '%widget.profiles.personal.name', '%widget.profiles.personal.desc', 'web/widgets/profiles/personal/myprofile.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/profiles_icon.gif', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'PROFILES', 0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7451x4ad0x807ex2aa4373360bd', 'profiles', 'myprofisxaac7x4229x87bbx9a1c3551c591');

-- Profiles ColleagueProfile widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('colprofsxaac7x4229x87bbx9a1c3551c591', '%widget.profiles.colleagues.name', '%widget.profiles.colleagues.desc', 'web/widgets/profiles/colleagues/colleagueprofile.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/profiles_icon.gif', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'PROFILES',  0,  1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7891x4aw0x807ex2aa4373360bd', 'profiles', 'colprofsxaac7x4229x87bbx9a1c3551c591');

-- End Profiles widgets



-- Beginning Wiki Widget

-- My Wiky widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED,  WIDGET_MULTIPLE_INSTANCES)
VALUES      ('mywikiz1xaac7x4229x87BBx91ac3551c591', '%widget.mywiki.name', '%widget.mywiki.desc', 'web/widgets/wiki/mywiki.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/wiki_icon.gif', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'WIKI',  0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7621x4awk5020ex2aa4373360bd', 'wikis', 'mywikiz1xaac7x4229x87BBx91ac3551c591');

-- Popular Wiki
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED,  WIDGET_MULTIPLE_INSTANCES)
VALUES      ('pop-wiki1xaac7x4229x87BBx91ac3551c5', '%widget.popwiki.name', '%widget.popwiki.desc', 'web/widgets/wiki/popularwiki.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/wiki_icon.gif', 1, 1, 1,  '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'WIKI',  0,  1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b8997620ojaw08d20ex2o4837336r8j', 'wikis', 'pop-wiki1xaac7x4229x87BBx91ac3551c5');

-- Latest wiki
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('latest-wiki5jz1xaac7x4229x87BBx91ac', '%widget.latestwiki.name', '%widget.latestwiki.desc', 'web/widgets/wiki/latestwiki.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/wiki_icon.gif', 1, 1, 1,  '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'WIKI',  0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('45thpb899762998dw08ee96x20of33o0Ur8j', 'wikis', 'latest-wiki5jz1xaac7x4229x87BBx91ac');

-- My Files widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('1g2ch9Bei35oPldKwZTaR7aAiPFw4L08CyRW', '%widget.files.name', '%widget.files.desc', 'web/widgets/files/files.xml','${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/files_icon.gif', 1, 1, 1,'${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'FILES',  0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('faDuCpJT3Mw4CC8S4bsd7HUvB53B0qrmFiFl','files','1g2ch9Bei35oPldKwZTaR7aAiPFw4L08CyRW');

-- Files shared with me widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('HxnvWqXFfQvWEJHX1TBvSCW0PS8ayfbPlZ1k', '%widget.sharedfiles.name', '%widget.sharedfiles.desc', 'web/widgets/files/sharedFiles.xml','${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/files_icon.gif', 1, 1, 1,'${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'FILES',  0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('nk7PWRAEWHHEhu6HTkRWtCQPylcUdSENF4mU','files','HxnvWqXFfQvWEJHX1TBvSCW0PS8ayfbPlZ1k');



---------------------------------
--- END INSERTING THE NEW WIDGETS
---------------------------------

----------------
--- INSERT THE DATA INTO THE WIDGET_TAB TABLE
----------------
-- Dogear Original Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_dogear7x4229x87BBx91ac3551c5', 'dogear46x0a77x4a43x82aaxb00187218631', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Dogear MyBookmarks widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_dembkx7x4229x87BBx91ac3551c5', 'dembk46x0a77x4a43x82aaxb00187218631', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Dogear PopularBookmarks widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_depbkx7x4229x87BBx91ac3551c5', 'depbk46x0a77x4a43x82aaxb00187218631', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Dogear RecentBookmarks widget - opened by default
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_derbkx7x4229x87BBx91ac3551c5', 'derbk46x0a77x4a43x82aaxb00187218631', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Dogear WatchList widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_dewlx7x4229x87BBx91ac3551c5', 'dewl46x0a77x4a43x82aaxb00187218631', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Activities Original Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_activitixa187x491dxa4bfx2e1', 'activitixa187x491dxa4bfx2e1261d0b6ec', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Activities MyActivities widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_myactxa187x491dxa4bfx2e1261', 'myactxa187x491dxa4bfx2e1261d0b6ec', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Activities PublicActivities widget - opened by default
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_pubactxa187x491dxa4bfx2e126', 'pubactxa187x491dxa4bfx2e1261d0b6ec', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Communities Original Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_communitxe7c4x4e08xab54x80e', 'communitxe7c4x4e08xab54x80e7a4eb8933', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Communities MyCommunities widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_mycommunxe7c4x4e08xab54x80e', 'mycommunxe7c4x4e08xab54x80e7a4eb8933', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Communities PublicCommunities widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_pubcommuxe7c4x4e08xab54x80e', 'pubcommuxe7c4x4e08xab54x80e7a4eb8933', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Blogs widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_blogs448xcd34x4565x9469x9c3', 'blogs448xcd34x4565x9469x9c34fcefe48c', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Profiles Original Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_profilesxaac7x4229x87bbx9a1', 'profilesxaac7x4229x87bbx9a1c3551c591', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Profiles MyProfile widget - opened by default
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_myprofisxaac7x4229x87bbx9a1', 'myprofisxaac7x4229x87bbx9a1c3551c591', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Profiles ColleagueProfile widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_colprofsxaac7x4229x87bbx9al', 'colprofsxaac7x4229x87bbx9a1c3551c591', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- My Wiky widget                
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_mywikiz1xaac7x4229x87BBx91a', 'mywikiz1xaac7x4229x87BBx91ac3551c591', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Popular Wiki
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_pop-wiki1xaac7x4229x87BBx91', 'pop-wiki1xaac7x4229x87BBx91ac3551c5', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Latest wiki
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_latest-wiki5jz1xaac7x4229x8', 'latest-wiki5jz1xaac7x4229x87BBx91ac', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Files Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_HXMb14Y53n2XAuLeHgrZI3f1CEGhN', '1g2ch9Bei35oPldKwZTaR7aAiPFw4L08CyRW', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Files sharded with me widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_FfQvWEJHX1TBvSCW0PS8ayfbPlZ1k', 'HxnvWqXFfQvWEJHX1TBvSCW0PS8ayfbPlZ1k', '%panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Sidebar activities widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('UPDATES_activities-sidebar', 'activities-sidebar7x4229x8', '%panel.updatex4a43x82aaxb00187218631', 'primary');

----------------
--- END THE DATA INTO THE WIDGET_TAB TABLE
----------------


------------
--- INSERT TEMPLATES
------------


INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('1g2ch9Bei35oPldKwZTaR7aAiPFw4L08CyRW','actor', 'actorExtID', 'person', 1); 

--INSERT INTO HOMEPAGE.NR_TEMPLATE
--		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
--VALUES	('faDuCpJT3Mw4CC8S4bsd7HUvB53B0qrmFiFl','actorOld', 'profiles.name.old', 'person', 1);

--INSERT INTO HOMEPAGE.NR_TEMPLATE
--		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
--VALUES	('ivMv5EZHXMb14Y53n2XAuLeHgrZI3f1CEGhN','actorNew', 'profiles.name.new;htmlURL', 'person', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('W09u2vvMBihLrCAsyXgPhQd7N3wSMw7C0IUe','actorProfiles', 'actorExtID', 'profilePhoto', 1);


INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('FoEDBgOAh3vEOPQZDEpgpwcQ2JNaHFfMnMcG','subject', 'targetSubjectExtIDs', 'person', 3);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('7mNHNYhanXIr0xP82OSZnuoYbAZa2M5AsRFr', 'activityEntry', 'activity.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('HxnvWqXFfQvWEJHX1TBvSCW0PS8ayfbPlZ1k','activityEntryWithComment', 'activity.entry.name;activity.entry.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('nk7PWRAEWHHEhu6HTkRWtCQPylcUdSENF4mU', 'activityContainerName', 'activity.name;activity.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('5gL9LpwoMg2xRFBLmRqg3Zj7Etrdttiyc6OH','activityContainerNameACL', 'activity.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('oHpJQGyRpsOHtvbY4lOJv8jOXJH5YgLoGYP7','toDoEntry', 'activity.todo.name;activity.todo.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('JIIsfItf9INx14G6bbCWRYNRpLhSawJXF8Qs','blogContainerName', 'blog.name;blog.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('lna9QyGfqsgRrfp88AKWys4wcm4gIuZm3T2p','newBlogContainer', 'blog.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('OLpFAu4Mn8ubpjmKeG6Vi8XpQXYKVx3mtCqm','blogEntry', 'blog.entry.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('gX1jXkFGTXle92JYbZEOrk0CBLEJHYwkrqPA','blogEntryWithComment', 'blog.entry.name;blog.entry.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('MERRLQf0I5rSEaImjaCcds3HYi4SeCGYCDCN','newCommunityContainer', 'community.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('8y2V7O9DfIduwKhHDNCLdmJFi0G7lwrE86IJ','communityContainerName', 'community.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('Q5yoSVoQke6V808ijHf9TRgUSUqVVZoSOGg5','topicEntryName', 'community.topic.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('bZXsMB2ixZVSWMFF7uZVaE11XRe4Q1ElJIc6','communityBookmarkEntryName', 'community.bookmark.name;community.bookmark.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('Jbo7uadvnDJa6XFBT7kyfEJv07Unitc0MsJT','feedEntryName', 'community.feed.name;community.feed.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('91LQHMi8v7y7DXAWZl1FIGMuMr4Ea4Lejbum','dogearBookmarkEntry', 'dogear.bookmark.title;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('WsECua7YmLTHTY2FdHKPsHU0wbAZCkgYrkk1','tag', 'tags', 'plain', 3);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('QEIWD5TwFo761Vrh9M5YNmxNogF2oERZWyRE','profileLinkEntry', 'profiles.link.title;profiles.link.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('Rr5UuUPa6mkcePgHNX0o3AF32cFuPWhtYMjl','profileStatusEntry', 'profiles.status', 'plain', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('idKF19qgQLuYLDdh66pf82um3c6q1lQ0gZl5','wikiContainerName','wiki.name;wiki.name.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('sCtP7EHuOdP75oD6oAdgoWCRNt4YYh7hZfuM','wikiEntryPage', 'wiki.page.name;wiki.page.url', 'link', 1);


-- ALTER THE HOMEPAGE_SCHEMA TABLE INSERTING THE NEW COLUMN RELEASEVER SET TO 2.5
DELETE FROM HOMEPAGE.HOMEPAGE_SCHEMA;

ALTER TABLE HOMEPAGE.HOMEPAGE_SCHEMA
ADD RELEASEVER nvarchar(32) NOT NULL DEFAULT ''
GO

INSERT INTO HOMEPAGE.HOMEPAGE_SCHEMA
	(COMPKEY, DBSCHEMAVER, RELEASEVER)
VALUES ('HOMEPAGE', 7 ,'2.5.0.0');


--------------------------------------
-- GRANT TO HOMEPAGE USER
--------------------------------------
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.HP_UI TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.HP_TAB TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.HP_TAB_INST TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.HP_WIDGET_INST TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.HP_WIDGET_TAB TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NT_NOTIFICATION TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NT_NOTIFICATION_RECIPIENT TO HOMEPAGEUSER
GO
-- NEWS REPOSITORY
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_SOURCE TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_SUBSCRIPTION TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_NEWS_RECORDS TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_TEMPLATE TO HOMEPAGEUSER
GO

--------------------------------------
-- DISCONNECT
--------------------------------------

COMMIT
