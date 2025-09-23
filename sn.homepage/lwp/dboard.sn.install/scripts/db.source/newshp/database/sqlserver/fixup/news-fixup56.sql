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

CREATE INDEX NR_SC_UDATE
    ON HOMEPAGE.NR_NEWS_STATUS_CONTENT (UPDATE_DATE ASC);  
   
GO

CREATE INDEX NR_NEWS_DISCOVERY_ITEM_C
    ON HOMEPAGE.NR_NEWS_DISCOVERY (ITEM_CORRELATION_ID);
   
GO

-- SPR #RSTR8A5Q7R
-- Slow query on Homepage nr_news_status_network table: Oracle
CREATE INDEX NR_NEWS_SN_READER_IDX
	ON HOMEPAGE.NR_NEWS_STATUS_NETWORK (READER_ID);

GO

--SPR #MAKN8A8DXJ SVT: 
--Homepage News Feed (Filter Responses and People) is very slow
CREATE INDEX NR_EVENT_READER 
	ON HOMEPAGE.NR_NEWS_SAVED (READER_ID,EVENT_RECORD_UUID);
GO	
