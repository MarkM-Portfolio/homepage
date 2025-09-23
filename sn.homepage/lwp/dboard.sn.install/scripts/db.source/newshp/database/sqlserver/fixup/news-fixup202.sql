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
------------------------ START NEWS FIXUP 202 -----------------------------------
---------------------------------------------------------------------------------

-- 78618: [DB] Add columns in NR_ENTRIES/NR_ENTRIES_ARCHIVE tables to store total number of comments
ALTER TABLE HOMEPAGE.NR_ENTRIES ADD
	PREV_COMMENT_NUM_REC NUMERIC(10,0) DEFAULT NULL,
	LAST_COMMENT_NUM_REC NUMERIC(10,0) DEFAULT NULL;
GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE ADD
	PREV_COMMENT_NUM_REC NUMERIC(10,0) DEFAULT NULL,
	LAST_COMMENT_NUM_REC NUMERIC(10,0) DEFAULT NULL;
GO

-- 78857: Remove FK_BRD_ENTRY_ID constraint on BOARD_RECOMMENDATIONS table
ALTER TABLE HOMEPAGE.BOARD_RECOMMENDATIONS 	DROP CONSTRAINT FK_BRD_ENTRY_ID;
GO
---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 202 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  
