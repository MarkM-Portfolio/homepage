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
------------------------ START HP FIXUP 101 -------------------------------------
---------------------------------------------------------------------------------

--------------------------------------
-- CREATE THE TABLES FOR MT
--------------------------------------    

CREATE TABLE HOMEPAGE.MTCONFIG (
	UUID			VARCHAR(36) NOT NULL,
	SCOPE 			VARCHAR(32) NOT NULL,
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
   
COMMIT;


--------------------------------------
-- OAUTH
--------------------------------------    

ALTER TABLE HOMEPAGE.WIDGET 
	ADD COLUMN WIDGET_POLICY_FLAGS INTEGER DEFAULT 1 NOT NULL
	ADD COLUMN WIDGET_EXT_PROPERTIES VARCHAR(2048);

COMMIT;

reorg table HOMEPAGE.WIDGET;

COMMIT;


ALTER TABLE HOMEPAGE.OAUTH1_CLIENT DROP FOREIGN KEY CLNT_FK;

ALTER TABLE HOMEPAGE.OAUTH1_CLIENT 
	ADD CONSTRAINT CLNT_FK FOREIGN KEY (PROVIDERID) 
	REFERENCES HOMEPAGE.OAUTH1_PROVIDER(ID);

COMMIT;


ALTER TABLE HOMEPAGE.OAUTH1_CLIENT DROP FOREIGN KEY CLNT_TOKEN_FK; 

ALTER TABLE HOMEPAGE.OAUTH1_CLIENT 
	ADD CONSTRAINT CLNT_TOKEN_FK FOREIGN KEY (TOKENID) 
	REFERENCES HOMEPAGE.OAUTH1_TOKEN(ID);

COMMIT;


ALTER TABLE HOMEPAGE.OAUTH1_CONTEXT DROP FOREIGN KEY CONTEXT_CLNT_FK; 

ALTER TABLE HOMEPAGE.OAUTH1_CONTEXT
	ADD CONSTRAINT CONTEXT_CLNT_FK FOREIGN KEY (CLIENTID) 
	REFERENCES HOMEPAGE.OAUTH1_CLIENT(ID);

COMMIT;


ALTER TABLE HOMEPAGE.OAUTH2_CLIENT DROP FOREIGN KEY FK_CLIENT_PRO_NAME;

ALTER TABLE HOMEPAGE.OAUTH2_CLIENT
	ADD CONSTRAINT FK_CLIENT_PRO_NAME FOREIGN KEY(PROVIDER_NAME)
	REFERENCES HOMEPAGE.OAUTH2_PROVIDER(NAME);

COMMIT;



DROP TABLE HOMEPAGE.OAUTH2_GADGET_BINDING;

CREATE TABLE HOMEPAGE.OAUTH2_GADGET_BINDING (
  ID VARCHAR(36) NOT NULL PRIMARY KEY,
  WIDGET_ID VARCHAR(36) NOT NULL,
  URI_SHA1 CHAR(40) NOT NULL, 
  SERVICE_NAME VARCHAR(254)  NOT NULL,
  URI VARCHAR(1024) NOT NULL,
  CLIENT_NAME VARCHAR(254) NOT NULL,
  OVERRIDES SMALLINT NOT NULL,
  CONSTRAINT URI_GADGET_UK UNIQUE (URI_SHA1, SERVICE_NAME),
  CONSTRAINT WIDGET_GADGET_UK UNIQUE (WIDGET_ID, SERVICE_NAME)
)
IN HPNT16TABSPACE;

ALTER TABLE HOMEPAGE.OAUTH2_GADGET_BINDING
  ADD CONSTRAINT FK_BINDING_CLIENT FOREIGN KEY(CLIENT_NAME)
  REFERENCES HOMEPAGE.OAUTH2_CLIENT(NAME);

ALTER TABLE HOMEPAGE.OAUTH2_GADGET_BINDING
  ADD CONSTRAINT FK_BINDING_WIDGET FOREIGN KEY(WIDGET_ID)
  REFERENCES HOMEPAGE.WIDGET(WIDGET_ID);
  
CREATE INDEX HOMEPAGE.OA2T_GBINDING_WID
	ON HOMEPAGE.OAUTH2_GADGET_BINDING (WIDGET_ID);


COMMIT;


--
-- Insert special non-UI panels needed for gadgets
--

-- hidden pane for gadgets
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_noui.gadgetpanx11e1b0c40800200c9a66' , '%panel.gadgets' , 1 , 0, 1);

-- hidden pane for EE gadgets
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_noui.embeddedxx11e1b0c40800200c9a66' , '%panel.embedxp' , 1 , 0, 1);

-- hidden pane for sharebox gadgets
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_noui.share_boxx11e1b0c40800200c9a66' , '%panel.sharebox' , 1 , 0, 1);


COMMIT;


---------------------------------------------------------------------------------
------------------------ END HP FIXUP 101 ---------------------------------------
---------------------------------------------------------------------------------
  