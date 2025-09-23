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

-- 72807: In edge condition where there are multiple results from 'find-OAUTH2_TOKEN' system breaks
DELETE FROM HOMEPAGE.OAUTH2_TOKEN;
COMMIT;

ALTER TABLE HOMEPAGE.OAUTH2_TOKEN 
	ADD TOKEN_SHA1 CHAR(40);
COMMIT;

CREATE UNIQUE INDEX HOMEPAGE.OA2_TOKEN_SHA1_UNQ 
	ON HOMEPAGE.OAUTH2_TOKEN (TOKEN_SHA1) TABLESPACE "NEWSINDEXTABSPACE";
COMMIT;	 
