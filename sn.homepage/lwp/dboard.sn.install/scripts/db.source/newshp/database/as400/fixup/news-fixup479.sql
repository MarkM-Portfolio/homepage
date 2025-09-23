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

-- 103979: Store creation date for item and correlated item in Activity Stream tables   
ALTER TABLE HOMEPAGE.NR_STORIES 
	ADD ITEM_CREATION_DATE TIMESTAMP
	ADD ITEM_CORRELATION_CREATION_DATE TIMESTAMP;

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES 
	ADD ITEM_CORRELATION_CREATION_DATE TIMESTAMP;

COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE 
	ADD ITEM_CORRELATION_CREATION_DATE TIMESTAMP;

COMMIT;

--REORG TABLE HOMEPAGE.NR_ENTRIES;
--COMMIT;

--REORG TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE;
--COMMIT;

--REORG TABLE HOMEPAGE.NR_STORIES;
--COMMIT;

