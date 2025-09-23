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

ALTER TABLE HOMEPAGE.NR_AS_COUNTS 
	ADD COLUMN CREATION_DATE TIMESTAMP
	ADD COLUMN UPDATE_DATE TIMESTAMP;

COMMIT;
	
--SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup604 - start NR_AS_COUNTS reorg' FROM SYSIBM.SYSDUMMY1;

--REORG TABLE HOMEPAGE.NR_AS_COUNTS;

--SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [END] fixup604 - end NR_AS_COUNTS_reorg' FROM SYSIBM.SYSDUMMY1;

	
UPDATE HOMEPAGE.NR_AS_COUNTS 
	SET CREATION_DATE = current_timestamp, 
		UPDATE_DATE = current_timestamp;
		
COMMIT;
		
ALTER TABLE HOMEPAGE.NR_AS_COUNTS
	ALTER COLUMN CREATION_DATE SET NOT NULL
	ALTER COLUMN UPDATE_DATE SET NOT NULL;
	
--SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup604 - start NR_AS_COUNTS reorg' FROM SYSIBM.SYSDUMMY1;

--REORG TABLE HOMEPAGE.NR_AS_COUNTS;

--SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [END] fixup604 - end NR_AS_COUNTS_reorg' FROM SYSIBM.SYSDUMMY1;	
		
COMMIT;