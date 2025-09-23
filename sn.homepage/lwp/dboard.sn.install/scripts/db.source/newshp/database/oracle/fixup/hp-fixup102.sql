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
------------------------ START HP FIXUP 102 -------------------------------------
---------------------------------------------------------------------------------

--------------------------------------
-- OAuth2 Consumer Proxy security requirement for allowed domains
--------------------------------------    

ALTER TABLE HOMEPAGE.OAUTH2_CLIENT
	ADD ALLOWED_DOMAINS VARCHAR2(1024);

COMMIT;


ALTER TABLE HOMEPAGE.OAUTH2_GADGET_BINDING ADD (
	SECURE_URI_SHA1 CHAR(40),
	SECURE_URI VARCHAR2(1024)
);

COMMIT;

ALTER TABLE HOMEPAGE.OAUTH2_GADGET_BINDING
  DROP CONSTRAINT URI_GADGET_UK;

COMMIT;


--------------------------------------
-- Additional schema changes for widgets
--------------------------------------  

ALTER TABLE HOMEPAGE.WIDGET ADD ( 
	PROXY_POLICY VARCHAR2(64) DEFAULT 'intranet_access' NOT NULL,
	SHARE_ORDER NUMBER(9,0) DEFAULT -1 NOT NULL
);

COMMIT;


-- Update names
-- Insert the panel tab page widget
--
DELETE FROM HOMEPAGE.HP_TAB WHERE TAB_ID = '_noui.share_boxx11e1b0c40800200c9a66';

INSERT INTO HOMEPAGE.HP_TAB 
                  (TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES            ('_noui.share_diax11e1b0c40800200c9a66' , '%panel.sharedialog' , 1 , 0, 1);

-- CONN MAIL
--
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('405a4f26-fa08-4cef-a995-7d90fbe2634f','%widget.connmail.name','%widget.connmail.desc','${COMMON_CONTEXT_ROOT}/socmail-client/gadgets/inbox.xml',NULL,1,1,0,NULL,'CONNECTIONSMAIL',0,0,0,'${COMMON_CONTEXT_ROOT}/socmail-client/gadgets/inbox.xml',NULL,1, 43, 'intranet_access', -1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('0d0a920d-3772-48bf-84e3-069676aea87b', 'connectionsmail', '405a4f26-fa08-4cef-a995-7d90fbe2634f');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('d8361d6b-0021-41de-b562-55ca27a3e954','405a4f26-fa08-4cef-a995-7d90fbe2634f','_noui.gadgetpanx11e1b0c40800200c9a66','primary');


--
-- SHARE Microblogging
--
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('826e0a39-d231-49bd-a1fa-b1e6e787aaa1','%widget.connshare.microblog.name','%widget.connshare.microblog.desc','${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.news.microblogging.sharebox.form/globalMicrobloggingForm.xml',NULL,1,1,0,NULL,'CONNECTIONSSHARE',0,0,0,'${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.news.microblogging.sharebox.form/globalMicrobloggingForm.xml',NULL,1,43,'intranet_access', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('0c28301d-40c6-49b9-ab2e-918f58076c0f', 'news', '826e0a39-d231-49bd-a1fa-b1e6e787aaa1');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('825edb79-8b86-45c3-afbb-4891355755b2','826e0a39-d231-49bd-a1fa-b1e6e787aaa1','_noui.gadgetpanx11e1b0c40800200c9a66','primary');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('5394716f-273c-4a0e-9845-5069275ab029','826e0a39-d231-49bd-a1fa-b1e6e787aaa1','_noui.share_diax11e1b0c40800200c9a66','primary');

--
-- SHARE Files
--
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('8426c915-6836-4a4b-a3f5-2db0ced9909f','%widget.connshare.files.name','%widget.connshare.files.desc','${COMMON_CONTEXT_ROOT}/web/com.ibm.social.sharebox/UploadFile.xml',NULL,1,1,0,NULL,'CONNECTIONSSHARE',0,0,0,'${COMMON_CONTEXT_ROOT}/web/com.ibm.social.sharebox/UploadFile.xml',NULL,1,39,'intranet_access', 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('37c6cd92-6ef5-43b9-8022-89a051e37e9a','files','8426c915-6836-4a4b-a3f5-2db0ced9909f');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('ecd31763-d711-4a57-9e14-eb6b3d71daee','8426c915-6836-4a4b-a3f5-2db0ced9909f', '_noui.gadgetpanx11e1b0c40800200c9a66','primary');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('c1f5196b-4f94-49ec-bc38-f7d8dacc0bb4','8426c915-6836-4a4b-a3f5-2db0ced9909f','_noui.share_diax11e1b0c40800200c9a66','primary');

--
-- Connections EE
--
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('aad20aa1-c0fa-48ef-bd05-8abe630c0012','%widget.connee.name','%widget.connee.desc','${COMMON_CONTEXT_ROOT}/web/com.ibm.social.ee/ConnectionsEE.xml',NULL,1,1,0,NULL,'CONNECTIONSEE',0,0,0,'${COMMON_CONTEXT_ROOT}/web/com.ibm.social.ee/ConnectionsEE.xml',NULL,1,7,'intranet_access', -1);

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('985f431a-3c00-4301-a93b-d375181d9814', 'aad20aa1-c0fa-48ef-bd05-8abe630c0012', '_noui.gadgetpanx11e1b0c40800200c9a66','primary');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('8cd18252-ec33-4f8f-97df-b62f3bb49e28','aad20aa1-c0fa-48ef-bd05-8abe630c0012','_noui.embeddedxx11e1b0c40800200c9a66','primary');


COMMIT;



---------------------------------------------------------------------------------
-- ADD ADDITIONAL INDEXES BASED ON W3 RALLY AND MIGRATION TESTS
---------------------------------------------------------------------------------


-- PERSON TABLE

CREATE INDEX HOMEPAGE.PERSON_EXID_PER_ID_IX
	ON HOMEPAGE.PERSON  (EXID, PERSON_ID) TABLESPACE "HPNTINDEXTABSPACE";

CREATE INDEX HOMEPAGE.PERSON_STATE_MEM_LU_IX
	ON HOMEPAGE.PERSON  (STATE, MEMBER_TYPE,LAST_UPDATE) TABLESPACE "HPNTINDEXTABSPACE";

CREATE INDEX HOMEPAGE.PERSON_MEM_STATE_IX
	ON HOMEPAGE.PERSON  (MEMBER_TYPE,STATE) TABLESPACE "HPNTINDEXTABSPACE";

CREATE INDEX HOMEPAGE.PERSON_MEM_SAND_IX
	ON HOMEPAGE.PERSON  (MEMBER_TYPE,SAND_OPT) TABLESPACE "HPNTINDEXTABSPACE";
   
COMMIT;


 
--HP_UI: include PERSON_LANG

CREATE UNIQUE INDEX HOMEPAGE.HP_UI_PERSONID_LANG
   ON HOMEPAGE.HP_UI (PERSON_ID,PERSON_LANG) TABLESPACE "HPNTINDEXTABSPACE";
 
COMMIT;


---------------------------------------------------------------------------------
------------------------ END HP FIXUP 102 ---------------------------------------
---------------------------------------------------------------------------------
