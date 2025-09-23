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

ALTER TABLE 	HOMEPAGE.PERSON			ADD  ORGANIZATION_ID VARCHAR(36);
ALTER TABLE 	HOMEPAGE.PERSON			ADD  COMMUNITY_INTERNAL_ONLY SMALLINT;
COMMIT;


-------------------------------------------------------------
-- START: OA updates scripts
-------------------------------------------------------------
INSERT INTO HOMEPAGE.OH_PROVIDER (ID, NAME, DESCRIPTION, REQUESTTOKENURL, AUTHORIZEURL, ACCESSTOKENURL, BASEURL, MANAGEURL, REGISTERURL, CREATEED) 
VALUES ( '00000000-0000-0000-0000-000000000000', 'connectionsProvider', 'IBM Connections OAuth Provider', 'provider/requestToken', 'provider/authorize', 'provider/accessToken', '', 'provider/manageAccess?serviceProvider=connectionsProvider', 'provider/register?serviceProvider=connectionsProvider', CURRENT TIMESTAMP);

COMMIT;

CREATE INDEX HOMEPAGE.OH_CONTEXT_USER_IDX 			ON HOMEPAGE.OH_CONTEXT (USERID);
CREATE INDEX HOMEPAGE.OH_CONTEXT_CLIENT_USER_IDX 	ON HOMEPAGE.OH_CONTEXT (CLIENTID,USERID);
CREATE INDEX HOMEPAGE.OH_CLIENT_USERNAME_IDX 		ON HOMEPAGE.OH_CLIENT (USERNAME);

COMMIT;


-- DROP UNIQUE INDEXES
DROP INDEX HOMEPAGE.APP_PROVIDER_IDX;

DROP INDEX HOMEPAGE.CLIENT_PROVIDER_IDX;
DROP INDEX HOMEPAGE.CLIENT_TOKEN_IDX;	

DROP INDEX HOMEPAGE.CONTEXT_CLIENT_IDX;
DROP INDEX HOMEPAGE.CONTEXT_TOKEN_IDX;

DROP INDEX HOMEPAGE.ACL_CONTEXT_IDX;
DROP INDEX HOMEPAGE.ACL_APP_IDX;

COMMIT;

-- RECREATE
CREATE INDEX HOMEPAGE.APP_PROVIDER_IDX 				ON HOMEPAGE.OH_APPLICATION (PROVIDERID);

CREATE INDEX HOMEPAGE.CLIENT_PROVIDER_IDX 			ON HOMEPAGE.OH_CLIENT (PROVIDERID);
CREATE INDEX HOMEPAGE.CLIENT_TOKEN_IDX 				ON HOMEPAGE.OH_CLIENT (TOKENID);	

CREATE INDEX HOMEPAGE.CONTEXT_CLIENT_IDX 			ON HOMEPAGE.OH_CONTEXT (CLIENTID);
CREATE INDEX HOMEPAGE.CONTEXT_TOKEN_IDX 			ON HOMEPAGE.OH_CONTEXT (TOKENID);

CREATE INDEX HOMEPAGE.ACL_CONTEXT_IDX 				ON HOMEPAGE.OH_OAUTHACL (CONTEXTID);
CREATE INDEX HOMEPAGE.ACL_APP_IDX 					ON HOMEPAGE.OH_OAUTHACL (AUTHORIZEDAPPID);

COMMIT;

-------------------------------------------------------------
-- END START: OA updates scripts
-------------------------------------------------------------
