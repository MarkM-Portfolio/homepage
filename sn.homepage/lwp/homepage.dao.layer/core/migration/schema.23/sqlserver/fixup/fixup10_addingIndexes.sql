-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2008, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO 

----------------------------------------------------
-- 1) START ADDING INDEXES FOR HOMEPAGE TABLES
----------------------------------------------------
CREATE INDEX TAB_INST_UI_ID_IDX 
	ON HOMEPAGE.HP_TAB_INST(UI_ID)
GO

CREATE INDEX HP_WIDGET_INST_TAB_INST_ID_IDX
	ON HOMEPAGE.HP_WIDGET_INST(TAB_INST_ID)
GO
----------------------------------------------------
-- END ADDING INDEXES FOR HOMEPAGE TABLES
----------------------------------------------------

-------------------------------------------------------------------
-- 2) START ADDING INDEXES FOR NEWS REPOSITORY 
-------------------------------------------------------------------
-- NEW REPOSITORY INDEXES
CREATE INDEX NR_NEWS_RECORDS_READER_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS(READER_ID)
GO	

CREATE INDEX NR_NEWS_RECORDS_ACTOR_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS(ACTOR_UUID)
GO	

CREATE INDEX NR_NEWS_RECORDS_SOURCE_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS(SOURCE)
GO	

CREATE INDEX NR_NEWS_RECORDS_CONTAINER_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS(CONTAINER_ID)
GO
	
CREATE INDEX NR_NEWS_RECORDS_DATE_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS(CREATION_DATE DESC)
GO

CREATE INDEX NR_NEWS_RECORDS_EVENT_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS(EVENT_RECORD_UUID)	
GO
-------------------------------------------------------------------
-- END ADDING INDEXES FOR NEWS REPOSITORY 
-------------------------------------------------------------------

COMMIT;
