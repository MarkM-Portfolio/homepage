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
      CREATION_DATE DATETIME,
      UPDATE_DATE DATETIME;
GO   

	
UPDATE HOMEPAGE.NR_AS_COUNTS 
	SET CREATION_DATE = current_timestamp, 
		UPDATE_DATE = current_timestamp;
GO
		
ALTER TABLE HOMEPAGE.NR_AS_COUNTS
	ALTER COLUMN CREATION_DATE DATETIME NOT NULL;
	
GO

ALTER TABLE HOMEPAGE.NR_AS_COUNTS
	ALTER COLUMN UPDATE_DATE DATETIME NOT NULL;

GO