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


CREATE INDEX PERSON_EMD_CHECK 
	ON HOMEPAGE.PERSON (MEMBER_TYPE, CREATION_DATE ASC, STATE, USER_MAIL_LOWER, PERSON_ID, ORGANIZATION_ID);

GO

DROP INDEX PERSON_USER_MAIL ON HOMEPAGE.PERSON;

GO

DROP INDEX PERSON_EXID ON HOMEPAGE.PERSON;
GO

CREATE UNIQUE INDEX PERSON_EXID_ORG
	ON HOMEPAGE.PERSON (EXID, ORGANIZATION_ID);	
GO	