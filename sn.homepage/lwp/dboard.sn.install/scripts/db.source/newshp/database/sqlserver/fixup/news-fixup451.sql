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

---------------------------------------------------------------------------------
------------------------ START NEWS FIXUP 451 -----------------------------------
---------------------------------------------------------------------------------

-- 89208: Remove DEFAULT constraints from BOARD tables: BOARD,  BOARD_COMMENTS, BOARD_CURRENT_STATUS 
-- BOARD_ENTRIES, BOARD_MENTIONS, BOARD_OBJECT_REFERENCE, BOARD_RECOMMENDATIONS 

DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.BOARD') and name like '%ORG%')
SET @DROP_STMT = N'ALTER TABLE HOMEPAGE.BOARD DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO

DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.BOARD_COMMENTS') and name like '%ORG%')
SET @DROP_STMT = N'ALTER TABLE HOMEPAGE.BOARD_COMMENTS DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO

DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.BOARD_CURRENT_STATUS') and name like '%ORG%')
SET @DROP_STMT = N'ALTER TABLE HOMEPAGE.BOARD_CURRENT_STATUS DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO

DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.BOARD_ENTRIES') and name like '%ORG%')
SET @DROP_STMT = N'ALTER TABLE HOMEPAGE.BOARD_ENTRIES DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO

DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.BOARD_MENTIONS') and name like '%ORG%')
SET @DROP_STMT = N'ALTER TABLE HOMEPAGE.BOARD_MENTIONS DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO

DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.BOARD_OBJECT_REFERENCE') and name like '%ORG%')
SET @DROP_STMT = N'ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO

DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.BOARD_RECOMMENDATIONS') and name like '%ORG%')
SET @DROP_STMT = N'ALTER TABLE HOMEPAGE.BOARD_RECOMMENDATIONS DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO

-- 89815: Query vs DISCOVERY and PROFILE_VIEW - need to review the access plan and study indexes to use ORGANIZATION_ID
-- NEW DISCOVERY_VIEW index for SC to support public reads
CREATE INDEX DISCOVERY_VIEW_ORG_IDX 
   	ON HOMEPAGE.NR_DISCOVERY_VIEW (ORGANIZATION_ID, USE_IN_ROLLUP, IS_VISIBLE, CREATION_DATE, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, STORY_ID);
GO

-- NEW PROFILES_VIEW index for SC to support public reads
CREATE INDEX PROFILES_VIEW_ORG_IDX 
   	ON HOMEPAGE.NR_PROFILES_VIEW (ORGANIZATION_ID, USE_IN_ROLLUP, IS_VISIBLE, CREATION_DATE, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, STORY_ID);
GO


---------------------------------------------------------------------------------
------------------------ END   NEWS FIXUP 451 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  
