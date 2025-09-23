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
	ADD PROF_TYPE VARCHAR(128);

reorg table HOMEPAGE.PERSON;

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
	ID VARCHAR(36) NOT NULL,
	TOKEN VARCHAR(1024) NOT NULL,
	SECRET VARCHAR(1024)NOT NULL,
	EXPIRATION TIMESTAMP,
	CREATED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP
)
IN HPNT16TABSPACE;

ALTER TABLE HOMEPAGE.OH_TOKEN 
    ADD CONSTRAINT PK_OH_TOKEN_ID PRIMARY KEY(ID);
    
    

------------------------------------------------
-- PROVIDER
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_PROVIDER (
	ID VARCHAR(36) NOT NULL,
	NAME VARCHAR(256) NOT NULL,
	DESCRIPTION VARCHAR(1024),
	REQUESTTOKENURL VARCHAR(512) NOT NULL,
	AUTHORIZEURL VARCHAR(512) NOT NULL,
	ACCESSTOKENURL VARCHAR(512) NOT NULL,
	BASEURL VARCHAR(512),
	MANAGEURL VARCHAR(512),	
	REGISTERURL VARCHAR(512),	
	SIGNMETHOD VARCHAR(32),
	VERSION VARCHAR(8),
	CREATEED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP
)
IN HPNT16TABSPACE;

ALTER TABLE HOMEPAGE.OH_PROVIDER
    ADD CONSTRAINT PK_PROVIDER_ID PRIMARY KEY(ID);

CREATE UNIQUE INDEX HOMEPAGE.PRVD_NAME_UNIQUE 
	ON HOMEPAGE.OH_PROVIDER (NAME);
	
------------------------------------------------
-- CLIENT
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_CLIENT (
	ID VARCHAR(36) NOT NULL,
	NAME VARCHAR(256) NOT NULL,
	TOKENID VARCHAR(36) NOT NULL,
	PROVIDERID VARCHAR(36) NOT NULL,
	DESCRIPTION VARCHAR(1024),
	CALLBACKURL VARCHAR(512),
	USERNAME VARCHAR(256) NOT NULL,
	CREATEED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP
)
IN HPNT16TABSPACE;

ALTER TABLE HOMEPAGE.OH_CLIENT
    ADD CONSTRAINT PK_CLIENT_ID PRIMARY KEY(ID);

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

------------------------------------------------
-- CONTEXT
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_CONTEXT (
	ID VARCHAR(36) NOT NULL,
	CLIENTID VARCHAR(36) NOT NULL,
	USERID VARCHAR(256),
	CALLBACKURL VARCHAR(1024),
	REQUESTAUTHURL VARCHAR(1024),
	TOKENID VARCHAR(36) NOT NULL,
	CREATED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP,
	SESSIONHANDLE VARCHAR(256),
	REFERER VARCHAR(512),
	EXPIRATION TIMESTAMP,
	AUTHORIZED SMALLINT,
	COMPLETED SMALLINT
)
IN HPNT16TABSPACE;

ALTER TABLE HOMEPAGE.OH_CONTEXT
    ADD CONSTRAINT PK_CONTEXT_ID PRIMARY KEY(ID);

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
	
------------------------------------------------
-- APPLICATION
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_APPLICATION (
	ID VARCHAR(36) NOT NULL,
	NAME VARCHAR(256) NOT NULL,
	DESCRIPTION VARCHAR(1024),
	BASEURL VARCHAR(512) NOT NULL,
	SECUREBASEURL VARCHAR(512),
	PROVIDERID VARCHAR(36) NOT NULL,
	CREATEED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP
)
IN HPNT16TABSPACE;

ALTER TABLE HOMEPAGE.OH_APPLICATION
    ADD CONSTRAINT PK_APP_ID PRIMARY KEY(ID);

CREATE UNIQUE INDEX HOMEPAGE.APP_NAME_UNIQUE
	ON HOMEPAGE.OH_APPLICATION (NAME);

ALTER TABLE HOMEPAGE.OH_APPLICATION
	ADD CONSTRAINT FK_APP_PROVIDER_ID FOREIGN KEY (PROVIDERID)
	REFERENCES HOMEPAGE.OH_PROVIDER(ID);

CREATE UNIQUE INDEX HOMEPAGE.APP_PROVIDER_IDX
    ON HOMEPAGE.OH_APPLICATION (PROVIDERID);
	
------------------------------------------------
-- OAUTHACL
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_OAUTHACL (
	ID VARCHAR(36) NOT NULL,
	CONTEXTID VARCHAR(36) NOT NULL,
	AUTHORIZETYPE SMALLINT,
	AUTHORIZEDAPPID VARCHAR(36),
	CREATED TIMESTAMP NOT NULL,
	MODIFIED TIMESTAMP
)
IN HPNT16TABSPACE;

ALTER TABLE HOMEPAGE.OH_OAUTHACL
    ADD CONSTRAINT PK_OAUTHACL_ID PRIMARY KEY(ID);

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
