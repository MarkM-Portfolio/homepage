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
ADD
   (
      CREATION_DATE TIMESTAMP,
      UPDATE_DATE TIMESTAMP
   );

	
UPDATE HOMEPAGE.NR_AS_COUNTS 
	SET CREATION_DATE = LOCALTIMESTAMP, 
		UPDATE_DATE = LOCALTIMESTAMP;
		
ALTER TABLE HOMEPAGE.NR_AS_COUNTS
	MODIFY (CREATION_DATE NOT NULL);
	
ALTER TABLE HOMEPAGE.NR_AS_COUNTS
	MODIFY (UPDATE_DATE NOT NULL);
	
COMMIT;