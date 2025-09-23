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

ALTER TABLE HOMEPAGE.PERSON
	ADD PROF_TYPE VARCHAR2(128);

-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-- START: OAUTH TABLES
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: OAUTH Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

------------------------------------------------
-- TOKEN
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_TOKEN (
	ID VARCHAR2(36) NOT NULL,
	TOKEN VARCHAR2(1024) NOT NULL,
	SECRET VARCHAR2(1024)NOT NULL,
	EXPIRATION TIMESTAMP,
	CREATED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OH_TOKEN 
    ADD (CONSTRAINT PK_OH_TOKEN_ID PRIMARY KEY(ID)  USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");
    
ALTER TABLE HOMEPAGE.OH_TOKEN ENABLE ROW MOVEMENT;    

------------------------------------------------
-- PROVIDER
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_PROVIDER (
	ID VARCHAR2(36) NOT NULL,
	NAME VARCHAR2(256) NOT NULL,
	DESCRIPTION VARCHAR2(1024),
	REQUESTTOKENURL VARCHAR2(512) NOT NULL,
	AUTHORIZEURL VARCHAR2(512) NOT NULL,
	ACCESSTOKENURL VARCHAR2(512) NOT NULL,
	BASEURL VARCHAR2(512),
	MANAGEURL VARCHAR2(512),	
	REGISTERURL VARCHAR2(512),	
	SIGNMETHOD VARCHAR2(32),
	VERSION VARCHAR2(8),
	CREATEED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OH_PROVIDER
    ADD (CONSTRAINT PK_PROVIDER_ID PRIMARY KEY(ID)  USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");

CREATE UNIQUE INDEX HOMEPAGE.PRVD_NAME_UNIQUE 
	ON HOMEPAGE.OH_PROVIDER (NAME);

ALTER TABLE HOMEPAGE.OH_PROVIDER ENABLE ROW MOVEMENT;	
	
------------------------------------------------
-- CLIENT
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_CLIENT (
	ID VARCHAR2(36) NOT NULL,
	NAME VARCHAR2(256) NOT NULL,
	TOKENID VARCHAR2(36) NOT NULL,
	PROVIDERID VARCHAR2(36) NOT NULL,
	DESCRIPTION VARCHAR2(1024),
	CALLBACKURL VARCHAR2(512),
	USERNAME VARCHAR2(256) NOT NULL,
	CREATEED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OH_CLIENT
    ADD (CONSTRAINT PK_CLIENT_ID PRIMARY KEY(ID)  USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");

CREATE UNIQUE INDEX HOMEPAGE.CLNT_NAME_UNIQUE
	ON HOMEPAGE.OH_CLIENT (NAME);

ALTER TABLE HOMEPAGE.OH_CLIENT
	ADD CONSTRAINT FK_CLIENT_PRO_ID FOREIGN KEY (PROVIDERID)
	REFERENCES HOMEPAGE.OH_PROVIDER(ID);

ALTER TABLE HOMEPAGE.OH_CLIENT
	ADD CONSTRAINT FK_CLIENT_TOKEN_ID FOREIGN KEY (TOKENID)
	REFERENCES HOMEPAGE.OH_TOKEN(ID);

CREATE UNIQUE INDEX HOMEPAGE.CLIENT_PROVIDER_IDX
    ON HOMEPAGE.OH_CLIENT (PROVIDERID);

CREATE UNIQUE INDEX HOMEPAGE.CLIENT_TOKEN_IDX
    ON HOMEPAGE.OH_CLIENT (TOKENID);	

ALTER TABLE HOMEPAGE.OH_CLIENT ENABLE ROW MOVEMENT;
    
------------------------------------------------
-- CONTEXT
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_CONTEXT (
	ID VARCHAR2(36) NOT NULL,
	CLIENTID VARCHAR2(36) NOT NULL,
	USERID VARCHAR2(256),
	CALLBACKURL VARCHAR2(1024),
	REQUESTAUTHURL VARCHAR2(1024),
	TOKENID VARCHAR2(36) NOT NULL,
	CREATED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP,
	SESSIONHANDLE VARCHAR2(256),
	REFERER VARCHAR2(512),
	EXPIRATION TIMESTAMP,
	AUTHORIZED NUMBER(5,0),
	COMPLETED NUMBER(5,0)
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OH_CONTEXT
    ADD (CONSTRAINT PK_CONTEXT_ID PRIMARY KEY(ID)  USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");

ALTER TABLE HOMEPAGE.OH_CONTEXT
	ADD CONSTRAINT FK_CNTXT_CLIENT_ID FOREIGN KEY (CLIENTID)
	REFERENCES HOMEPAGE.OH_CLIENT(ID);

ALTER TABLE HOMEPAGE.OH_CONTEXT
	ADD CONSTRAINT FK_CNTXT_TOKEN_ID FOREIGN KEY (TOKENID)
	REFERENCES HOMEPAGE.OH_TOKEN(ID);

CREATE UNIQUE INDEX HOMEPAGE.CONTEXT_CLIENT_IDX
    ON HOMEPAGE.OH_CONTEXT (CLIENTID);

CREATE UNIQUE INDEX HOMEPAGE.CONTEXT_TOKEN_IDX
    ON HOMEPAGE.OH_CONTEXT (TOKENID);

ALTER TABLE HOMEPAGE.OH_CONTEXT ENABLE ROW MOVEMENT;

------------------------------------------------
-- APPLICATION
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_APPLICATION (
	ID VARCHAR2(36) NOT NULL,
	NAME VARCHAR2(256) NOT NULL,
	DESCRIPTION VARCHAR2(1024),
	BASEURL VARCHAR2(512) NOT NULL,
	SECUREBASEURL VARCHAR2(512),
	PROVIDERID VARCHAR2(36) NOT NULL,
	CREATEED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OH_APPLICATION
    ADD (CONSTRAINT PK_APP_ID PRIMARY KEY(ID)  USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");

CREATE UNIQUE INDEX HOMEPAGE.APP_NAME_UNIQUE
	ON HOMEPAGE.OH_APPLICATION (NAME);

ALTER TABLE HOMEPAGE.OH_APPLICATION
	ADD CONSTRAINT FK_APP_PROVIDER_ID FOREIGN KEY (PROVIDERID)
	REFERENCES HOMEPAGE.OH_PROVIDER(ID);

CREATE UNIQUE INDEX HOMEPAGE.APP_PROVIDER_IDX
    ON HOMEPAGE.OH_APPLICATION (PROVIDERID);

ALTER TABLE HOMEPAGE.OH_APPLICATION ENABLE ROW MOVEMENT;

------------------------------------------------
-- OAUTHACL
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_OAUTHACL (
	ID VARCHAR2(36) NOT NULL,
	CONTEXTID VARCHAR2(36) NOT NULL,
	AUTHORIZETYPE NUMBER(5,0),
	AUTHORIZEDAPPID VARCHAR2(36),
	CREATED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OH_OAUTHACL
    ADD (CONSTRAINT PK_OAUTHACL_ID PRIMARY KEY(ID)  USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");

ALTER TABLE HOMEPAGE.OH_OAUTHACL
	ADD CONSTRAINT FK_ACL_CONTEXT_ID FOREIGN KEY (CONTEXTID)
	REFERENCES HOMEPAGE.OH_CONTEXT(ID);

ALTER TABLE HOMEPAGE.OH_OAUTHACL
	ADD CONSTRAINT FK_ACL_APP_ID FOREIGN KEY (AUTHORIZEDAPPID)
	REFERENCES HOMEPAGE.OH_APPLICATION(ID);

CREATE UNIQUE INDEX HOMEPAGE.ACL_CONTEXT_IDX
    ON HOMEPAGE.OH_OAUTHACL (CONTEXTID);

CREATE UNIQUE INDEX HOMEPAGE.ACL_APP_IDX
    ON HOMEPAGE.OH_OAUTHACL (AUTHORIZEDAPPID);

ALTER TABLE HOMEPAGE.OH_OAUTHACL ENABLE ROW MOVEMENT;     
    
   
	
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END OAUTH Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-- END: OAUTH TABLES
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------	