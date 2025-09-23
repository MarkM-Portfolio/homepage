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

ALTER TABLE HOMEPAGE.PERSON ADD CREATION_DATE DATETIME;
GO

UPDATE 		HOMEPAGE.PERSON SET CREATION_DATE = LAST_UPDATE;
GO

-----------------------------------------------
-- UPDATE PERSON
-----------------------------------------------
ALTER TABLE HOMEPAGE.PERSON
	ADD THEME_ID nvarchar(36);

ALTER TABLE HOMEPAGE.PERSON
	ADD COMM_VISIBILITY NUMERIC(5 ,0);	

ALTER TABLE HOMEPAGE.PERSON
	ADD MEMBER_TYPE NUMERIC(5 ,0) DEFAULT 0;
GO
	
UPDATE HOMEPAGE.PERSON SET MEMBER_TYPE = 0;

GO

ALTER TABLE HOMEPAGE.PERSON 
	ALTER COLUMN MEMBER_TYPE NUMERIC(5 ,0) NOT NULL;
 
GO

UPDATE HOMEPAGE.PERSON SET MEMBER_TYPE = 2 WHERE PERSON_ID = '00000000-0000-0000-0000-000000000000';
GO