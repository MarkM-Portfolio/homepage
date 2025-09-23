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
------------------------ START NEWS FIXUP 204 -----------------------------------
---------------------------------------------------------------------------------

----------------------------------------------------------------------
-- 22 HOMEPAGE.NR_MENTIONS_READERS 
----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.NR_MENTIONS_READERS (
	CATEGORY_READER_ID nvarchar(36)  DEFAULT ' ' NOT NULL,
	READER_ID nvarchar(39) NOT NULL,
	CATEGORY_TYPE NUMERIC(5 ,0) NOT NULL,
	SOURCE nvarchar(36) NOT NULL,
	CONTAINER_ID nvarchar(256),
	ITEM_ID nvarchar(256),
	ROLLUP_ENTRY_ID nvarchar(256),
	RESOURCE_TYPE NUMERIC(5 ,0) NOT NULL,
	CREATION_DATE DATETIME NOT NULL,
	STORY_ID nvarchar(36) NOT NULL,
	SOURCE_TYPE NUMERIC(5 ,0),
	USE_IN_ROLLUP NUMERIC(5 ,0),
	IS_NETWORK	NUMERIC(5 ,0),
	IS_FOLLOWER	NUMERIC(5 ,0),
	EVENT_TIME 	DATETIME,
	IS_STORY_COMM NUMERIC(5 ,0),
	IS_BROADCAST NUMERIC(5,0),
	ORGANIZATION_ID nvarchar(256),
	ACTOR_UUID nvarchar(256),
	ROLLUP_AUTHOR_ID nvarchar (256),
	IS_VISIBLE NUMERIC(5 ,0) DEFAULT 1 NOT NULL,
	MAX_UPDATE_FOR_READER DATETIME,
		CONSTRAINT   	CK_CAT22_TYPE
    			CHECK
    			(CATEGORY_TYPE = 22)
) ON [PRIMARY]
GO

CREATE INDEX AGGREGATED_READERS_RDR_STR 
 	ON HOMEPAGE.NR_MENTIONS_READERS (READER_ID, STORY_ID); 
GO
	
ALTER TABLE HOMEPAGE.NR_MENTIONS_READERS
    ADD CONSTRAINT PK_MEN_READERS PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_MENTIONS_READERS 
	ADD CONSTRAINT FK_MEN_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID); 
	
--  [start indexes] NR_MENTIONS_READERS
CREATE  INDEX MENTIONS_READERS_STR_IX 
 	ON HOMEPAGE.NR_MENTIONS_READERS (STORY_ID); 
GO

CREATE  INDEX MENTIONS_READERS_ITM_IX 
 	ON HOMEPAGE.NR_MENTIONS_READERS (ITEM_ID); 
GO

CREATE  INDEX MENTIONS_READERS_CD_IX 
 	ON HOMEPAGE.NR_MENTIONS_READERS (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX MENTIONS_READERS_SRC_IX 
 	ON HOMEPAGE.NR_MENTIONS_READERS (READER_ID, CREATION_DATE DESC, STORY_ID, IS_BROADCAST, ROLLUP_ENTRY_ID, SOURCE_TYPE, IS_VISIBLE); 
GO

CREATE  INDEX MENTIONS_READERS_RLL_IX 
 	ON HOMEPAGE.NR_MENTIONS_READERS (READER_ID, IS_VISIBLE, USE_IN_ROLLUP); 
GO

CREATE  INDEX MENTIONS_READERS_RDR_STR 
 	ON HOMEPAGE.NR_MENTIONS_READERS (READER_ID, STORY_ID); 
GO

CREATE  INDEX MENTIONS_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_MENTIONS_READERS (CREATION_DATE DESC); 
GO

CREATE  INDEX MENTIONS_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_MENTIONS_READERS (ROLLUP_ENTRY_ID, READER_ID); 
GO

CREATE  INDEX MENTIONS_READERS_RIR_IX 
 	ON HOMEPAGE.NR_MENTIONS_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC); 
GO

CREATE  INDEX MENTIONS_READERS_RLL_BRD_VIS 
 	ON HOMEPAGE.NR_MENTIONS_READERS (ROLLUP_AUTHOR_ID, IS_BROADCAST, USE_IN_ROLLUP, IS_VISIBLE); 
GO


--  [end indexes] NR_MENTIONS_READERS
	
INSERT INTO HOMEPAGE.NR_CATEGORY_TYPE (CATEGORY_TYPE_ID, CATEGORY_TYPE, CATEGORY_TYPE_NAME, CATEGORY_TYPE_DESC)
VALUES ('mentions', 22, '%mentions', 'mentions');

GO

--------------------------------------------------------
-- DROPPING UNUSED OLD TABLES
--------------------------------------------------------
DROP TABLE HOMEPAGE.NR_SUBSCRIPTION;
GO

DROP TABLE HOMEPAGE.NR_NEWS_DISCOVERY;
GO

DROP TABLE HOMEPAGE.NR_NEWS_SAVED;
GO

DROP TABLE HOMEPAGE.NR_ATTACHMENT;
GO

DROP TABLE HOMEPAGE.NR_ATTACHMENT_301;
GO

DROP TABLE HOMEPAGE.NR_RECOMMENDATION_301;
GO

DROP TABLE HOMEPAGE.NR_RECOMMENDATION;
GO

DROP TABLE HOMEPAGE.NR_NEWS_RECORDS;
GO

------------------ 81333: Rename ENTRY_ID to ITEM_ID in BOARD_RECOMMENDATIONS --------
DROP INDEX BRD_REC_STORY_ID ON HOMEPAGE.BOARD_RECOMMENDATIONS;
GO  
    
DROP INDEX BRD_RECOM_ENTRY_ID ON HOMEPAGE.BOARD_RECOMMENDATIONS;
GO

EXEC sp_rename 'HOMEPAGE.BOARD_RECOMMENDATIONS.ENTRY_ID','ITEM_ID'; 
GO

CREATE INDEX BRD_REC_ITEM_ID
    ON HOMEPAGE.BOARD_RECOMMENDATIONS (ITEM_ID);
GO    
    
CREATE UNIQUE INDEX BRD_REC_RECOM_ITEM_ID
    ON HOMEPAGE.BOARD_RECOMMENDATIONS (RECOMMENDER_ID, ITEM_ID);
GO    
---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 204 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  
