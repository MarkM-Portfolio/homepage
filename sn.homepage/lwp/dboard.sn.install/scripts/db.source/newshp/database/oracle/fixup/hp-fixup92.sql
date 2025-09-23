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
-- HOMEPAGE.OH_TOKEN: DROP TOKEN COLUMN AND RECREATE + Add Index
--ALTER TABLE HOMEPAGE.OH_TOKEN DROP COLUMN TOKEN;
--ALTER TABLE HOMEPAGE.OH_TOKEN ADD TOKEN VARCHAR2(450);


--commit;

--CREATE UNIQUE INDEX HOMEPAGE.TOKEN_IDX 
--	ON HOMEPAGE.OH_TOKEN (TOKEN) TABLESPACE "HPNTINDEXTABSPACE";

INSERT INTO HOMEPAGE.PERSON
		(PERSON_ID,DISPLAYNAME,EXID,STATE,MEMBER_TYPE)
VALUES  ('00000000-0000-0000-0000-000000000001','%anyone','00000000-0000-0000-0000-000000000001',0,2);

