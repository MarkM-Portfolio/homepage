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

------------------------------------------------
-- TOKEN
------------------------------------------------
-- Comment out as we don't are still not sure about the token dimension
-- HOMEPAGE.OH_TOKEN: DROP TOKEN COLUMN AND RECREATE + Add Index
--ALTER TABLE HOMEPAGE.OH_TOKEN DROP COLUMN TOKEN;
--ALTER TABLE HOMEPAGE.OH_TOKEN ADD TOKEN VARCHAR(450);


--commit;

--reorg table HOMEPAGE.OH_TOKEN use HPNT16TMPTABSPACE;

--commit;

--CREATE UNIQUE INDEX HOMEPAGE.TOKEN_IDX 
--	ON HOMEPAGE.OH_TOKEN (TOKEN);
	

-----------------------
-- PERSON
-----------------------
CREATE UNIQUE INDEX HOMEPAGE.PERSON_IDX   
	ON HOMEPAGE.PERSON (PERSON_ID) INCLUDE (USER_MAIL, DISPLAYNAME);

COMMIT;	

INSERT INTO HOMEPAGE.PERSON
		(PERSON_ID,DISPLAYNAME,EXID,STATE,MEMBER_TYPE)
VALUES  ('00000000-0000-0000-0000-000000000001','%anyone','00000000-0000-0000-0000-000000000001',0,2);


