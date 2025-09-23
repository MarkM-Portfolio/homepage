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

-------------------------------------------------------------------
-- Adding CREATION_DATE in the PERSON table
-------------------------------------------------------------------
ALTER TABLE HOMEPAGE.PERSON ADD CREATION_DATE TIMESTAMP;
UPDATE 		HOMEPAGE.PERSON SET CREATION_DATE = LAST_UPDATE;
COMMIT;

-----------------------------------------------
-- UPDATE PERSON
-----------------------------------------------
ALTER TABLE HOMEPAGE.PERSON
	ADD THEME_ID VARCHAR(36);

ALTER TABLE HOMEPAGE.PERSON
	ADD COMM_VISIBILITY SMALLINT;	

ALTER TABLE HOMEPAGE.PERSON
	ADD MEMBER_TYPE SMALLINT DEFAULT 0;

UPDATE HOMEPAGE.PERSON SET MEMBER_TYPE = 0;

ALTER TABLE HOMEPAGE.PERSON 
	ALTER COLUMN MEMBER_TYPE SET NOT NULL;

COMMIT;	
	
reorg table HOMEPAGE.PERSON;	

COMMIT;

UPDATE HOMEPAGE.PERSON SET MEMBER_TYPE = 2 WHERE PERSON_ID = '00000000-0000-0000-0000-000000000000';

COMMIT;