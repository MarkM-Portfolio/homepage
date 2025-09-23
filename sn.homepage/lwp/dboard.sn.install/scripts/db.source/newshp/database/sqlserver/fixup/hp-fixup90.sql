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

ALTER TABLE HOMEPAGE.PERSON ADD ORGANIZATION_ID nvarchar(36);
ALTER TABLE HOMEPAGE.PERSON ADD COMMUNITY_INTERNAL_ONLY NUMERIC(5 ,0);
GO

-------------------------------------------------------------
-- START: OA updates scripts
-------------------------------------------------------------
INSERT INTO HOMEPAGE.OH_PROVIDER (ID, NAME, DESCRIPTION, REQUESTTOKENURL, AUTHORIZEURL, ACCESSTOKENURL, BASEURL, MANAGEURL, REGISTERURL, CREATEED) 
VALUES ( '00000000-0000-0000-0000-000000000000', 'connectionsProvider', 'IBM Connections OAuth Provider', 'provider/requestToken', 'provider/authorize', 'provider/accessToken', '', 'provider/manageAccess?serviceProvider=connectionsProvider', 'provider/register?serviceProvider=connectionsProvider', CURRENT_TIMESTAMP);

GO

CREATE INDEX OH_CONTEXT_USER_IDX 			ON HOMEPAGE.OH_CONTEXT (USERID);
CREATE INDEX OH_CONTEXT_CLIENT_USER_IDX 	ON HOMEPAGE.OH_CONTEXT (CLIENTID,USERID);
CREATE INDEX OH_CLIENT_USERNAME_IDX 		ON HOMEPAGE.OH_CLIENT (USERNAME);

GO


-- DROP UNIQUE INDEXES
DROP INDEX APP_PROVIDER_IDX ON HOMEPAGE.OH_APPLICATION;

DROP INDEX CLIENT_PROVIDER_IDX ON HOMEPAGE.OH_CLIENT;
DROP INDEX CLIENT_TOKEN_IDX ON HOMEPAGE.OH_CLIENT;	

DROP INDEX CONTEXT_CLIENT_IDX ON HOMEPAGE.OH_CONTEXT;
DROP INDEX CONTEXT_TOKEN_IDX ON HOMEPAGE.OH_CONTEXT;

DROP INDEX ACL_CONTEXT_IDX ON HOMEPAGE.OH_OAUTHACL;
DROP INDEX ACL_APP_IDX ON HOMEPAGE.OH_OAUTHACL;

GO

-- RECREATE
CREATE INDEX APP_PROVIDER_IDX 				ON HOMEPAGE.OH_APPLICATION (PROVIDERID);

CREATE INDEX CLIENT_PROVIDER_IDX 			ON HOMEPAGE.OH_CLIENT (PROVIDERID);
CREATE INDEX CLIENT_TOKEN_IDX 				ON HOMEPAGE.OH_CLIENT (TOKENID);	

CREATE INDEX CONTEXT_CLIENT_IDX 			ON HOMEPAGE.OH_CONTEXT (CLIENTID);
CREATE INDEX CONTEXT_TOKEN_IDX 				ON HOMEPAGE.OH_CONTEXT (TOKENID);

CREATE INDEX ACL_CONTEXT_IDX 				ON HOMEPAGE.OH_OAUTHACL (CONTEXTID);
CREATE INDEX ACL_APP_IDX 					ON HOMEPAGE.OH_OAUTHACL (AUTHORIZEDAPPID);

GO

-------------------------------------------------------------
-- END START: OA updates scripts
-------------------------------------------------------------