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
ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_CONTENT  RENAME COLUMN CONTENT TO OLD_CONTENT;
COMMIT;
ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_CONTENT  ALTER COLUMN OLD_CONTENT DROP NOT NULL;
COMMIT;

ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_CONTENT ADD CONTENT CLOB;
COMMIT;

REORG TABLE HOMEPAGE.NR_NEWS_STATUS_CONTENT ALLOW NO ACCESS;
COMMIT;

-- NR_NEWS_COMMENT_CONTENT
ALTER TABLE HOMEPAGE.NR_NEWS_COMMENT_CONTENT  RENAME COLUMN CONTENT TO OLD_CONTENT;
COMMIT;
ALTER TABLE HOMEPAGE.NR_NEWS_COMMENT_CONTENT  ALTER COLUMN OLD_CONTENT DROP NOT NULL;
COMMIT;

ALTER TABLE HOMEPAGE.NR_NEWS_COMMENT_CONTENT ADD CONTENT CLOB;
COMMIT;

REORG TABLE HOMEPAGE.NR_NEWS_COMMENT_CONTENT ALLOW NO ACCESS;
COMMIT;

-- NR_STORIES_CONTENT
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT  RENAME COLUMN CONTENT TO OLD_CONTENT;
COMMIT;
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT  ALTER COLUMN OLD_CONTENT DROP NOT NULL;
COMMIT;

ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT ADD CONTENT CLOB;
COMMIT;

REORG TABLE HOMEPAGE.NR_STORIES_CONTENT ALLOW NO ACCESS;
COMMIT;

-- BOARD_ENTRIES
ALTER TABLE HOMEPAGE.BOARD_ENTRIES  RENAME COLUMN CONTENT TO OLD_CONTENT;
COMMIT;
ALTER TABLE HOMEPAGE.BOARD_ENTRIES  ALTER COLUMN OLD_CONTENT DROP NOT NULL;
COMMIT;

ALTER TABLE HOMEPAGE.BOARD_ENTRIES  ADD CONTENT CLOB;
COMMIT;

REORG TABLE HOMEPAGE.BOARD_ENTRIES ALLOW NO ACCESS;
COMMIT;


-- BOARD_COMMENTS
ALTER TABLE HOMEPAGE.BOARD_COMMENTS  RENAME COLUMN CONTENT TO OLD_CONTENT;
COMMIT;
ALTER TABLE HOMEPAGE.BOARD_COMMENTS  ALTER COLUMN OLD_CONTENT DROP NOT NULL;
COMMIT;

ALTER TABLE HOMEPAGE.BOARD_COMMENTS  ADD CONTENT CLOB;
COMMIT;

REORG TABLE HOMEPAGE.BOARD_COMMENTS ALLOW NO ACCESS;
COMMIT;



---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 205 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  
