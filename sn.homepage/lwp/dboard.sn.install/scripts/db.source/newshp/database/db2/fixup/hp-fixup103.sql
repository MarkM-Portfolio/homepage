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
------------------------ START HP FIXUP 103 -------------------------------------
---------------------------------------------------------------------------------

UPDATE HOMEPAGE.WIDGET 
 	SET WIDGET_TITLE = '%widget.connshare.microblog.name', 
 	 WIDGET_TEXT = '%widget.connshare.microblog.desc', 
 	 WIDGET_URL = '${COMMON_CONTEXT_ROOT}/web/lconn.news.microblogging.sharebox/globalMicrobloggingForm.xml', 
 	 WIDGET_ICON = NULL, 
 	 WIDGET_ENABLED = 1 , 
 	 WIDGET_SYSTEM = 1 , 
 	 WIDGET_HOMEPAGE_SPECIFIC = 0, 
 	 WIDGET_PREVIEW_IMAGE  = NULL, 
 	 WIDGET_CATEGORY  = 'CONNECTIONSSHARE', 
 	 WIDGET_IS_DEFAULT_OPENED  = 0, 
 	 WIDGET_MARKED_CACHABLE  = 0,
	 WIDGET_MULTIPLE_INSTANCES  = 0,
	 WIDGET_SECURE_URL  = '${COMMON_CONTEXT_ROOT}/web/lconn.news.microblogging.sharebox/globalMicrobloggingForm.xml',
	 WIDGET_SECURE_ICON  = NULL ,
	 IS_GADGET  = 1,
	 WIDGET_POLICY_FLAGS = 55, 
	 PROXY_POLICY  = 'intranet_access',
	 SHARE_ORDER = 0
WHERE WIDGET_ID = '826e0a39-d231-49bd-a1fa-b1e6e787aaa1';

COMMIT;


UPDATE HOMEPAGE.WIDGET 
 	SET WIDGET_TITLE = '%widget.communities.event.name', 
 	 WIDGET_TEXT = '%widget.communities.event.desc', 
 	 WIDGET_URL = '${COMMON_CONTEXT_ROOT}/web/lconn.calendar/CalendarGadget.xml', 
 	 WIDGET_ICON = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', 
 	 WIDGET_ENABLED = 1 , 
 	 WIDGET_SYSTEM = 1 , 
 	 WIDGET_HOMEPAGE_SPECIFIC = 1, 
 	 WIDGET_PREVIEW_IMAGE  = '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 
 	 WIDGET_CATEGORY  = 'COMMUNITIES', 
 	 WIDGET_IS_DEFAULT_OPENED  = 1, 
 	 WIDGET_MARKED_CACHABLE  = 0,
	 WIDGET_MULTIPLE_INSTANCES  = 0,
	 WIDGET_SECURE_URL  = '${COMMON_CONTEXT_ROOT}/web/lconn.calendar/CalendarGadget.xml',
	 WIDGET_SECURE_ICON  = '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png' ,
	 IS_GADGET  = 1,
	 WIDGET_POLICY_FLAGS = 39, 
	 PROXY_POLICY  = 'intranet_access',
	 SHARE_ORDER = -1
WHERE WIDGET_ID = 'commuevtxe7c4x4e08xab54x80e7a4eb8933';

COMMIT;

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('61532c5f-664f-40c0-bd09-abf337b43312','commuevtxe7c4x4e08xab54x80e7a4eb8933','_noui.gadgetpanx11e1b0c40800200c9a66','primary');

COMMIT;










CREATE INDEX HOMEPAGE.HP_WIDGET_IDX 
	ON HOMEPAGE.HP_WIDGET_INST (WIDGET_ID);
COMMIT;	
	
--68052: Slow search queries. Creating an index in person table after rally	
CREATE INDEX HOMEPAGE.PERSON_SND_SR 
	ON HOMEPAGE.PERSON (MEMBER_TYPE, SAND_OPT, SAND_LAST_UPDATE ASC);
COMMIT;
	
DROP TABLE HOMEPAGE.MTCONFIG;

CREATE TABLE HOMEPAGE.MTCONFIG (
	UUID			VARCHAR(36) NOT NULL,
	SCOPE 			VARCHAR(36) NOT NULL,
	ID 				VARCHAR(128) NOT NULL,
	CONFIG_VALUE 	VARCHAR(1024),
	SERVICE 		VARCHAR(256),
	NAME	 		VARCHAR(64),
	DESCRIPTION		VARCHAR(64),
	OVERRIDABLE 	SMALLINT NOT NULL DEFAULT 1,
	ISPOLICY		SMALLINT NOT NULL DEFAULT 0,
	ADMIN_VIS		SMALLINT NOT NULL DEFAULT 1
) IN HOMEPAGETABSPACE;

ALTER TABLE HOMEPAGE.MTCONFIG
	 ADD CONSTRAINT "PK_ID" PRIMARY KEY ("UUID");
	 
ALTER TABLE HOMEPAGE.MTCONFIG
	ADD CONSTRAINT "UNIQUE_ID" UNIQUE ("SCOPE","ID");

CREATE INDEX HOMEPAGE.SETTINGS_BY_ID
    ON HOMEPAGE.MTCONFIG(ID);

-- search indexes    
CREATE INDEX HOMEPAGE.PERSON_SAND_OPT_IDX
    ON HOMEPAGE.PERSON(SAND_OPT, SAND_LAST_UPDATE ASC);
COMMIT;    

CREATE INDEX HOMEPAGE.PERSON_STATE_IDX
  ON HOMEPAGE.PERSON(STATE, LAST_UPDATE ASC);  
COMMIT;  
    
    
---------------------------------------------------------------------------------
------------------------ END HP FIXUP 103 ---------------------------------------
---------------------------------------------------------------------------------
  
