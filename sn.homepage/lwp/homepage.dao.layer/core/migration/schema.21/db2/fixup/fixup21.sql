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

connect to HOMEPAGE;

-- #HZLI7U639N Bad SQL performance on home page table NR_NEWS_RECORDS
CREATE UNIQUE INDEX HOMEPAGE.NR_NEWS_RECORDS_COMM_UUID_IDX
    ON HOMEPAGE.NR_NEWS_RECORDS (CREATION_DATE DESC, NEWS_RECORDS_ID, SOURCE, RELATED_COMM_UUID ) INCLUDE (READER_ID, IS_CONTAINER, IS_PUBLIC);

-- #HKPL7U9QUV Need index on NR_SUBSCRIPTION
CREATE INDEX HOMEPAGE.NR_SUBSCRIPTION_SOURCE_IDX  
    ON HOMEPAGE.NR_SUBSCRIPTION(SOURCE_ID);

-- #HKPL7U9QUV Need index on NR_SOURCE
CREATE INDEX HOMEPAGE.NR_SOURCE_CONTAINER_NAME_IDX
    ON HOMEPAGE.NR_SOURCE(SOURCE ASC, CONTAINER_NAME ASC, ENTRY_ID ASC, IS_ACL ASC);

-------------------------------------------------------------------------------
-- Updating the schema version from 20 to 21
-------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 21
WHERE   DBSCHEMAVER = 20;

--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate;