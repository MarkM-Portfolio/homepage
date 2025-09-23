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
ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_CONTENT  MODIFY (OLD_CONTENT NULL); 
COMMIT;

ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_CONTENT ADD CONTENT CLOB 
LOB (CONTENT) STORE AS (TABLESPACE NEWSLOBTABSPACE STORAGE (INITIAL 1M) CHUNK 4000 NOCACHE NOLOGGING);
COMMIT;


-- NR_NEWS_COMMENT_CONTENT
ALTER TABLE HOMEPAGE.NR_NEWS_COMMENT_CONTENT  RENAME COLUMN CONTENT TO OLD_CONTENT;
COMMIT;
ALTER TABLE HOMEPAGE.NR_NEWS_COMMENT_CONTENT  MODIFY (OLD_CONTENT NULL);
COMMIT;

ALTER TABLE HOMEPAGE.NR_NEWS_COMMENT_CONTENT ADD CONTENT CLOB
LOB (CONTENT) STORE AS (TABLESPACE NEWSLOBTABSPACE STORAGE (INITIAL 1M) CHUNK 4000 NOCACHE NOLOGGING);

COMMIT;


-- NR_STORIES_CONTENT
ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT  RENAME COLUMN CONTENT TO OLD_CONTENT;
COMMIT;
--ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT  MODIFY (OLD_CONTENT NULL); it is already nullable
--COMMIT;

ALTER TABLE HOMEPAGE.NR_STORIES_CONTENT ADD CONTENT CLOB
LOB (CONTENT) STORE AS (TABLESPACE NEWSLOBTABSPACE STORAGE (INITIAL 1M) CHUNK 4000 NOCACHE NOLOGGING);
COMMIT;


-- BOARD_ENTRIES
ALTER TABLE HOMEPAGE.BOARD_ENTRIES  RENAME COLUMN CONTENT TO OLD_CONTENT;
COMMIT;
ALTER TABLE HOMEPAGE.BOARD_ENTRIES  MODIFY (OLD_CONTENT NULL);
COMMIT;

ALTER TABLE HOMEPAGE.BOARD_ENTRIES  ADD CONTENT CLOB
LOB (CONTENT) STORE AS (TABLESPACE BOARDLOBTABSPACE STORAGE (INITIAL 1M) CHUNK 4000 NOCACHE NOLOGGING);
COMMIT;



-- BOARD_COMMENTS
ALTER TABLE HOMEPAGE.BOARD_COMMENTS  RENAME COLUMN CONTENT TO OLD_CONTENT;
COMMIT;
--ALTER TABLE HOMEPAGE.BOARD_COMMENTS  MODIFY (OLD_CONTENT NULL); it is already nullable
--COMMIT;

ALTER TABLE HOMEPAGE.BOARD_COMMENTS  ADD CONTENT CLOB
LOB (CONTENT) STORE AS (TABLESPACE BOARDLOBTABSPACE STORAGE (INITIAL 1M) CHUNK 4000 NOCACHE NOLOGGING);
COMMIT;




---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 205 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  
