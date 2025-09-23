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
--- Update COMMUNITY_INTERNAL_ONLY to have 1 as default value



DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.HOMEPAGE.PERSON') and name like '%COMM%')
SET @DROP_STMT = N'ALTER TABLE HOMEPAGE.PERSON DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO

ALTER TABLE HOMEPAGE.PERSON ADD DEFAULT 1 FOR COMMUNITY_INTERNAL_ONLY;

COMMIT;

BEGIN TRANSACTION
GO

--- Update existing value for COMMUNITY_INTERNAL_ONLY to be 1 where MEMBER_TYPE is 1
UPDATE HOMEPAGE.PERSON
SET    COMMUNITY_INTERNAL_ONLY = 1 WHERE MEMBER_TYPE = 1;
GO
COMMIT TRANSACTION;



BEGIN TRANSACTION
GO

