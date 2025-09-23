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

-- 1) Update PERSON table SAND_OPT NUMERIC(5,0) DEFAULT 1	NOT NULL
ALTER TABLE HOMEPAGE.PERSON
    ADD SAND_OPT NUMERIC(5,0) DEFAULT 1;
GO

UPDATE HOMEPAGE.PERSON SET SAND_OPT = 1;
GO

-- 2) Drop IS_ACTIVE from PERSON table
--ALTER TABLE HOMEPAGE.PERSON
--	ALTER COLUMN IS_ACTIVE NUMERIC(5,0) NULL;
--GO

-- REMOVING WELCOME_MODE COLUMN. BEFORE WE NEED TO REMOVE THE CONSTRAINT
DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.PERSON') and parent_column_id=5)
SET @DROP_STMT = N'ALTER TABLE HOMEPAGE.PERSON DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO
	
ALTER TABLE HOMEPAGE.PERSON
    DROP COLUMN IS_ACTIVE;
GO