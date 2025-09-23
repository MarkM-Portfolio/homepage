-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2015                                  
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

-- 141809: Rollup is by object + action


ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS ADD  ROLLUP_ACTION_TYPE nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_STATUS_UPDATE_READERS ADD  ROLLUP_ACTION_TYPE nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_EXTERNAL_READERS ADD  ROLLUP_ACTION_TYPE nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS ADD  ROLLUP_ACTION_TYPE nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_PROFILES_VIEW ADD  ROLLUP_ACTION_TYPE nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_NOTIFICATION_SENT_READERS ADD  ROLLUP_ACTION_TYPE nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS ADD  ROLLUP_ACTION_TYPE nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_SAVED_READERS ADD  ROLLUP_ACTION_TYPE nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_TOPICS_READERS ADD  ROLLUP_ACTION_TYPE nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_MENTIONS_READERS ADD  ROLLUP_ACTION_TYPE nvarchar(36);
GO

ALTER TABLE HOMEPAGE.NR_COMMUNITIES_VIEW ADD  ROLLUP_ACTION_TYPE nvarchar(36);
GO


