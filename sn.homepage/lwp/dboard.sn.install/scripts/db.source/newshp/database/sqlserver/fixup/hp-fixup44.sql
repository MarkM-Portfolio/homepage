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

----------------------------------------------------------------------
-- HOMEPAGE.MT_METRICS 
----------------------------------------------------------------------

CREATE TABLE HOMEPAGE.MT_METRIC_STAT  (
      METRIC_STAT_ID nvarchar(36) NOT NULL,
      RECORDED_ON DATETIME NOT NULL,
      METRIC_TYPE NUMERIC(5,0) NOT NULL,
      METRIC_DESC nvarchar(36) NOT NULL,
      RES_BUNDLE_KEY nvarchar(144) NOT NULL,
      COUNT_LAST_24_H NUMERIC(19,0),
      COUNT_LAST_7_D NUMERIC(19,0),
      COUNT_LAST_1_M NUMERIC(19,0),
      TOP_STATS nvarchar(512),
      TOT_STAT NUMERIC(19,0),
      AVG_TOT_STAT NUMERIC(19,0)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.MT_METRIC_STAT 
    ADD CONSTRAINT PK_METRIC_STAT_ID PRIMARY KEY(METRIC_STAT_ID);

CREATE INDEX MT_METRICS_IDX
    ON HOMEPAGE.MT_METRIC_STAT (RECORDED_ON ASC, METRIC_TYPE);
    
{SQL_GRANT_START} HOMEPAGE.MT_METRIC_STAT {SQL_GRANT_STOP}


-------------------------------------------------
-- FIXING NT_NOTIFICATION TABLE
-------------------------------------------------

-- 1 NT_NOTIFICATION_RECIPIENT WHERE RECIPIENT_ID
DROP INDEX NT_NOT_RECIPIENT_INDEX ON HOMEPAGE.NT_NOTIFICATION_RECIPIENT;

DELETE FROM HOMEPAGE.NT_NOTIFICATION_RECIPIENT WHERE RECIPIENT_ID IS NULL;
GO

ALTER TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT
	ALTER COLUMN RECIPIENT_ID nvarchar(36) NOT NULL;
GO

CREATE INDEX NT_NOT_RECIPIENT_INDEX
	ON HOMEPAGE.NT_NOTIFICATION_RECIPIENT (RECIPIENT_ID);

-- 2 NT_NOTIFICATION WHERE FIRST_RECIPIENT_ID
DROP INDEX NT_NOTIFICATION_EXID_INDEX ON HOMEPAGE.NT_NOTIFICATION;

DROP INDEX NT_NOTIFICATION_IDX ON HOMEPAGE.NT_NOTIFICATION;

DROP INDEX NT_FIRST_RECIPIENT_PER ON HOMEPAGE.NT_NOTIFICATION;	

DELETE FROM HOMEPAGE.NT_NOTIFICATION WHERE FIRST_RECIPIENT_ID IS NULL;
GO

ALTER TABLE HOMEPAGE.NT_NOTIFICATION
	ALTER COLUMN FIRST_RECIPIENT_ID nvarchar(36) NOT NULL;
GO
	
-- 3 NT_NOTIFICATION WHERE SENDER_ID
DELETE FROM HOMEPAGE.NT_NOTIFICATION WHERE SENDER_ID IS NULL;
GO
	
ALTER TABLE HOMEPAGE.NT_NOTIFICATION
	ALTER COLUMN SENDER_ID nvarchar(36)  NOT NULL;
GO

CREATE INDEX NT_NOTIFICATION_EXID_INDEX
	ON HOMEPAGE.NT_NOTIFICATION (SENDER_ID);

CREATE INDEX NT_NOTIFICATION_IDX
    ON HOMEPAGE.NT_NOTIFICATION (DATETIME_STAMP DESC, FIRST_RECIPIENT_ID, SENDER_ID);

CREATE INDEX NT_FIRST_RECIPIENT_PER
    ON HOMEPAGE.NT_NOTIFICATION (FIRST_RECIPIENT_ID);	
GO

