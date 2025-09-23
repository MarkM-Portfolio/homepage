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

-- 104298: [fixup463.sql] [SQLServer] Remove CONTAINER_URL and HAS_ATTACHEMENT from createDb.sql

DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.HOMEPAGE.BOARD_ENTRIES') and name like '%HAS%')
SET @DROP_STMT = N'ALTER TABLE HOMEPAGE.BOARD_ENTRIES DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO

ALTER TABLE HOMEPAGE.BOARD_ENTRIES DROP COLUMN CONTAINER_URL;
GO

ALTER TABLE HOMEPAGE.BOARD_ENTRIES DROP COLUMN HAS_ATTACHMENT;
GO