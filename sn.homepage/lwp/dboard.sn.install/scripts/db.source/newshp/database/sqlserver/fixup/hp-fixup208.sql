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

----------------------------------------------------
------------------------ START HP FIXUP 208 --------
----------------------------------------------------

-- 85942: Remove the tables NT_NOTIFICATION and NT_NOTIFICATION_RECIPIENT
DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.NT_NOTIFICATION'))
SET @DROP_STMT = N'DROP TABLE HOMEPAGE.NT_NOTIFICATION DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO

DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.NT_NOTIFICATION_RECIPIENT'))
SET @DROP_STMT = N'DROP TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO

DROP TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT
GO

DROP TABLE HOMEPAGE.NT_NOTIFICATION;
GO

----------------------------------------------------
------------------------ END FIXUP 208 -------
----------------------------------------------------