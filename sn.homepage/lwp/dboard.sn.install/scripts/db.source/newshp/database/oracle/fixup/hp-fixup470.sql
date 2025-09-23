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

CREATE INDEX HOMEPAGE.PERSON_EMD_CHECK 
	ON HOMEPAGE.PERSON (MEMBER_TYPE, CREATION_DATE ASC, STATE, USER_MAIL_LOWER, PERSON_ID, ORGANIZATION_ID) TABLESPACE "HPNTINDEXTABSPACE";

COMMIT;	
	
DROP INDEX HOMEPAGE.PERSON_USER_MAIL;	

COMMIT;

DROP INDEX HOMEPAGE.PERSON_EXID;
COMMIT;

CREATE UNIQUE INDEX HOMEPAGE.PERSON_EXID_ORG
	ON HOMEPAGE.PERSON (EXID, ORGANIZATION_ID) TABLESPACE "HPNTINDEXTABSPACE";
COMMIT;	