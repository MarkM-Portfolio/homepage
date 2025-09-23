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
------------------------ START NEWS FIXUP 201 -----------------------------------
---------------------------------------------------------------------------------

-- 78715 - New columns on NR_SOURCE_TYPE table.
ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE
	ADD COLUMN IS_DIGEST_ENABLED SMALLINT NOT NULL DEFAULT 0
	ADD COLUMN DEFAULT_DIGEST_FREQUENCY SMALLINT NOT NULL DEFAULT 0
	ADD COLUMN IS_DIGEST_LOCKED SMALLINT NOT NULL DEFAULT 0;
COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES
	ALTER COLUMN N_COMMENTS SET DATA TYPE INTEGER
	ALTER COLUMN N_RECOMMANDATIONS SET DATA TYPE INTEGER;
COMMIT;

REORG TABLE HOMEPAGE.NR_SOURCE_TYPE;
REORG TABLE HOMEPAGE.NR_ENTRIES;

COMMIT;

---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 201 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  
