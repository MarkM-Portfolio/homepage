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

----------------------------------------------------
-- 1) START ADDING INDEXES FOR HOMEPAGE TABLES
----------------------------------------------------
CREATE INDEX "HOMEPAGE"."TAB_INST_UI_ID_IDX" 
	ON "HOMEPAGE"."HP_TAB_INST"("UI_ID") TABLESPACE "HPNTINDEXTABSPACE";	


CREATE INDEX "HOMEPAGE"."HP_WIDGET_INST_TAB_INST_ID_IDX"
	ON "HOMEPAGE"."HP_WIDGET_INST"("TAB_INST_ID") TABLESPACE "HPNTINDEXTABSPACE";

----------------------------------------------------
-- END ADDING INDEXES FOR HOMEPAGE TABLES
----------------------------------------------------

-------------------------------------------------------------------
-- 2) START ADDING INDEXES FOR NEWS REPOSITORY 
-------------------------------------------------------------------
-- NEW REPOSITORY INDEXES
CREATE INDEX "HOMEPAGE"."NR_NEWS_RECORDS_READER_IDX"
	ON HOMEPAGE.NR_NEWS_RECORDS("READER_ID") TABLESPACE "NEWSINDEXTABSPACE";

CREATE INDEX "HOMEPAGE"."NR_NEWS_RECORDS_ACTOR_IDX"
	ON HOMEPAGE.NR_NEWS_RECORDS("ACTOR_UUID") TABLESPACE "NEWSINDEXTABSPACE";

CREATE INDEX "HOMEPAGE"."NR_NEWS_RECORDS_SOURCE_IDX"
	ON HOMEPAGE.NR_NEWS_RECORDS("SOURCE") TABLESPACE "NEWSINDEXTABSPACE";

CREATE INDEX "HOMEPAGE"."NR_NEWS_RECORDS_CONTAINER_IDX"
	ON HOMEPAGE.NR_NEWS_RECORDS("CONTAINER_ID") TABLESPACE "NEWSINDEXTABSPACE";
	
CREATE INDEX "HOMEPAGE"."NR_NEWS_RECORDS_DATE_IDX"
	ON HOMEPAGE.NR_NEWS_RECORDS("CREATION_DATE" DESC) TABLESPACE "NEWSINDEXTABSPACE";

CREATE INDEX HOMEPAGE.NR_NEWS_RECORDS_EVENT_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS("EVENT_RECORD_UUID") TABLESPACE "NEWSINDEXTABSPACE";	
-------------------------------------------------------------------
-- END ADDING INDEXES FOR NEWS REPOSITORY 
-------------------------------------------------------------------


COMMIT;

--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;
