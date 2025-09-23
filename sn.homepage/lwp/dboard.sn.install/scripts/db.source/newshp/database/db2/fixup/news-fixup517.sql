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


ALTER TABLE HOMEPAGE.NR_AGGREGATED_READERS 
	ADD COLUMN ROLLUP_ACTION_TYPE VARCHAR(36)@

COMMIT@

ALTER TABLE HOMEPAGE.NR_STATUS_UPDATE_READERS
	ADD COLUMN ROLLUP_ACTION_TYPE VARCHAR(36)@

COMMIT@

ALTER TABLE HOMEPAGE.NR_EXTERNAL_READERS
	ADD COLUMN ROLLUP_ACTION_TYPE VARCHAR(36)@

COMMIT@

ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS 
	ADD COLUMN ROLLUP_ACTION_TYPE VARCHAR(36)@

COMMIT@

ALTER TABLE HOMEPAGE.NR_PROFILES_VIEW 
	ADD COLUMN ROLLUP_ACTION_TYPE VARCHAR(36)@

COMMIT@

ALTER TABLE HOMEPAGE.NR_NOTIFICATION_SENT_READERS
	ADD COLUMN ROLLUP_ACTION_TYPE VARCHAR(36)@

COMMIT@

ALTER TABLE HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS
	ADD COLUMN ROLLUP_ACTION_TYPE VARCHAR(36)@

COMMIT@

ALTER TABLE HOMEPAGE.NR_SAVED_READERS
	ADD COLUMN ROLLUP_ACTION_TYPE VARCHAR(36)@

COMMIT@

ALTER TABLE HOMEPAGE.NR_TOPICS_READERS
	ADD COLUMN ROLLUP_ACTION_TYPE VARCHAR(36)@

COMMIT@

ALTER TABLE HOMEPAGE.NR_MENTIONS_READERS
	ADD COLUMN ROLLUP_ACTION_TYPE VARCHAR(36)@

COMMIT@

ALTER TABLE HOMEPAGE.NR_COMMUNITIES_VIEW
	ADD COLUMN ROLLUP_ACTION_TYPE VARCHAR(36)@

COMMIT@

---------------------------------------
-- reorg
---------------------------------------

REORG TABLE HOMEPAGE.NR_AGGREGATED_READERS@
COMMIT@

REORG TABLE HOMEPAGE.NR_STATUS_UPDATE_READERS@
COMMIT@

REORG TABLE HOMEPAGE.NR_EXTERNAL_READERS@
COMMIT@

REORG TABLE HOMEPAGE.NR_ACTIONABLE_READERS@
COMMIT@

REORG TABLE HOMEPAGE.NR_PROFILES_VIEW@
COMMIT@

REORG TABLE HOMEPAGE.NR_NOTIFICATION_SENT_READERS@
COMMIT@

REORG TABLE HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS@
COMMIT@

REORG TABLE HOMEPAGE.NR_SAVED_READERS@
COMMIT@

REORG TABLE HOMEPAGE.NR_TOPICS_READERS@
COMMIT@

REORG TABLE HOMEPAGE.NR_MENTIONS_READERS@
COMMIT@

REORG TABLE HOMEPAGE.NR_COMMUNITIES_VIEW@
COMMIT@
