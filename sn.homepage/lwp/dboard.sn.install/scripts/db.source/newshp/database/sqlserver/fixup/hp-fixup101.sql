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

CREATE TABLE HOMEPAGE.MTCONFIG (
	UUID			nvarchar(36) NOT NULL,
	SCOPE 			nvarchar(32) NOT NULL,
	ID 				nvarchar(128) NOT NULL,
	CONFIG_VALUE 	nvarchar(1024),
	SERVICE 		nvarchar(256),
	NAME	 		nvarchar(64),
	DESCRIPTION		nvarchar(64),
	OVERRIDABLE 	NUMERIC(5,0) DEFAULT 1 NOT NULL,
	ISPOLICY		NUMERIC(5,0) DEFAULT 0 NOT NULL,
	ADMIN_VIS		NUMERIC(5,0) DEFAULT 1 NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.MTCONFIG
	 ADD CONSTRAINT PK_ID PRIMARY KEY (UUID);
	 
ALTER TABLE HOMEPAGE.MTCONFIG
	ADD CONSTRAINT UNIQUE_ID UNIQUE (SCOPE,ID);

CREATE INDEX SETTINGS_BY_ID
    ON HOMEPAGE.MTCONFIG (ID);


--------------------------------------
-- OAUTH
--------------------------------------    

ALTER TABLE HOMEPAGE.WIDGET ADD 
	WIDGET_POLICY_FLAGS NUMERIC(5,0) DEFAULT 1 NOT NULL,
	WIDGET_EXT_PROPERTIES nvarchar(2048);

GO


ALTER TABLE HOMEPAGE.OAUTH1_CLIENT DROP CLNT_FK;

ALTER TABLE HOMEPAGE.OAUTH1_CLIENT 
	ADD CONSTRAINT CLNT_FK FOREIGN KEY (PROVIDERID) 
	REFERENCES HOMEPAGE.OAUTH1_PROVIDER(ID);

GO


ALTER TABLE HOMEPAGE.OAUTH1_CLIENT DROP CLNT_TOKEN_FK; 

ALTER TABLE HOMEPAGE.OAUTH1_CLIENT 
	ADD CONSTRAINT CLNT_TOKEN_FK FOREIGN KEY (TOKENID) 
	REFERENCES HOMEPAGE.OAUTH1_TOKEN(ID);

GO


ALTER TABLE HOMEPAGE.OAUTH1_CONTEXT DROP CONTEXT_CLNT_FK; 

ALTER TABLE HOMEPAGE.OAUTH1_CONTEXT
	ADD CONSTRAINT CONTEXT_CLNT_FK FOREIGN KEY (CLIENTID) 
	REFERENCES HOMEPAGE.OAUTH1_CLIENT(ID);

GO


ALTER TABLE HOMEPAGE.OAUTH2_CLIENT DROP FK_CLIENT_PRO_NAME;

ALTER TABLE HOMEPAGE.OAUTH2_CLIENT
	ADD CONSTRAINT FK_CLIENT_PRO_NAME FOREIGN KEY(PROVIDER_NAME)
	REFERENCES HOMEPAGE.OAUTH2_PROVIDER(NAME);

GO



DROP TABLE HOMEPAGE.OAUTH2_GADGET_BINDING;

CREATE TABLE HOMEPAGE.OAUTH2_GADGET_BINDING (
  ID nvarchar(36) NOT NULL PRIMARY KEY,
  WIDGET_ID nvarchar(36) NOT NULL,
  URI_SHA1 CHAR(40) NOT NULL, 
  SERVICE_NAME nvarchar(254)  NOT NULL,
  URI nvarchar(1024) NOT NULL,
  CLIENT_NAME nvarchar(254) NOT NULL,
  OVERRIDES NUMERIC(5,0) NOT NULL,
  CONSTRAINT URI_GADGET_UK UNIQUE (URI_SHA1, SERVICE_NAME),
  CONSTRAINT WIDGET_GADGET_UK UNIQUE (WIDGET_ID, SERVICE_NAME)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OAUTH2_GADGET_BINDING
  ADD CONSTRAINT FK_BINDING_CLIENT FOREIGN KEY(CLIENT_NAME)
  REFERENCES HOMEPAGE.OAUTH2_CLIENT(NAME);

ALTER TABLE HOMEPAGE.OAUTH2_GADGET_BINDING
  ADD CONSTRAINT FK_BINDING_WIDGET FOREIGN KEY(WIDGET_ID)
  REFERENCES HOMEPAGE.WIDGET(WIDGET_ID);
  
CREATE INDEX OA2T_GBINDING_WID
	ON HOMEPAGE.OAUTH2_GADGET_BINDING (WIDGET_ID);

GO


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

GO


---------------------------------------------------------------------------------
------------------------ END HP FIXUP 101 ---------------------------------------
---------------------------------------------------------------------------------
