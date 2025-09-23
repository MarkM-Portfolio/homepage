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
------------------------ START NEWS FIXUP 105 -----------------------------------
---------------------------------------------------------------------------------


---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 105 -------------------------------------
---------------------------------------------------------------------------------
 

  
  
  


----------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------

EXEC sp_rename 'HOMEPAGE.NR_STORIES_CONTENT.ACTIVITY_META_DATA_1', 'A1', 'column';
GO

ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT
	ADD ACTIVITY_META_DATA_1 VARBINARY (3584);
GO

UPDATE HOMEPAGE.NR_STORIES_CONTENT SET ACTIVITY_META_DATA_1 = CONVERT(VARBINARY (3584), A1);
GO

EXEC sp_rename 'HOMEPAGE.NR_STORIES_CONTENT.ACTIVITY_META_DATA_2', 'A2', 'column';
GO

ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT
	ADD ACTIVITY_META_DATA_2 VARBINARY (3584);
GO
	
UPDATE HOMEPAGE.NR_STORIES_CONTENT SET ACTIVITY_META_DATA_2 = CONVERT(VARBINARY (3584), A2);
GO

ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT DROP COLUMN A1;
GO
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT DROP COLUMN A2;
GO

---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 105 -------------------------------------
---------------------------------------------------------------------------------
 

  
  
  
