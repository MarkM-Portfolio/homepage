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

-----------------------------------------------------------------------------------------------------------
--  1 Adding the LOCALE and DISPLAYNAME attribute to the EMD_RECIPIENTS
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.EMD_RECIPIENTS
	ADD COLUMN LOCALE VARCHAR(5);

ALTER TABLE HOMEPAGE.EMD_RECIPIENTS
	ADD COLUMN DISPLAYNAME VARCHAR(256);

-----------------------------------------------------------------------------------------------------------
--  2 Adding the TEXT_META_TEMPLATE an empty space
-----------------------------------------------------------------------------------------------------------
UPDATE HOMEPAGE.NR_NEWS_RECORDS SET TEXT_META_TEMPLATE = ' ';

-----------------------------------------------------------------------------------------------------------
--  3 Adding the default value to 0 for IS_PRIVATE in the table HOMEPAGE.NR_SOURCE
-----------------------------------------------------------------------------------------------------------
UPDATE HOMEPAGE.NR_SOURCE SET IS_PRIVATE = 0;

-----------------------------------------------------------------------------------------------------------
--  4 Upgrade fixup number to 13
-----------------------------------------------------------------------------------------------------------

-- Updating the schema version from 12 to 13
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 13
WHERE   DBSCHEMAVER = 12;

COMMIT;

--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate; 
