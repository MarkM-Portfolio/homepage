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
------------------------ START NEWS FIXUP 205  -----------------------------------
---------------------------------------------------------------------------------


-- NR_NEWS_STATUS_CONTENT
EXEC sp_rename 'HOMEPAGE.NR_NEWS_STATUS_CONTENT.CONTENT','OLD_CONTENT'; 
GO

ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_CONTENT ALTER COLUMN OLD_CONTENT varbinary(max) NULL;
GO

ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_CONTENT ADD CONTENT nvarchar(max);
GO



-- NR_NEWS_COMMENT_CONTENT
EXEC sp_rename 'HOMEPAGE.NR_NEWS_COMMENT_CONTENT.CONTENT','OLD_CONTENT'; 
GO

ALTER TABLE HOMEPAGE.NR_NEWS_COMMENT_CONTENT ALTER COLUMN OLD_CONTENT varbinary(max) NULL;
GO

ALTER TABLE HOMEPAGE.NR_NEWS_COMMENT_CONTENT ADD CONTENT nvarchar(max);
GO


-- NR_STORIES_CONTENT
EXEC sp_rename 'HOMEPAGE.NR_STORIES_CONTENT.CONTENT','OLD_CONTENT'; 
GO

ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT ALTER COLUMN OLD_CONTENT varbinary(max) NULL;
GO

ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT ADD CONTENT nvarchar(max);
GO


-- BOARD_ENTRIES
EXEC sp_rename 'HOMEPAGE.BOARD_ENTRIES.CONTENT','OLD_CONTENT'; 
GO

ALTER TABLE HOMEPAGE.BOARD_ENTRIES ALTER COLUMN OLD_CONTENT varbinary(max) NULL;
GO

ALTER TABLE HOMEPAGE.BOARD_ENTRIES  ADD CONTENT nvarchar(max);
GO



-- BOARD_COMMENTS
EXEC sp_rename 'HOMEPAGE.BOARD_COMMENTS.CONTENT','OLD_CONTENT'; 
GO

ALTER TABLE HOMEPAGE.BOARD_COMMENTS ALTER COLUMN OLD_CONTENT varbinary(max) NULL;
GO

ALTER TABLE HOMEPAGE.BOARD_COMMENTS  ADD CONTENT nvarchar(max);
GO




---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 205 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  
