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
                                           
USE HOMEPAGE
GO

BEGIN TRANSACTION
GO


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

    
-- 103979: Store creation date for item and correlated item in Activity Stream tables   
ALTER TABLE HOMEPAGE.NR_STORIES 
	ADD 	ITEM_CREATION_DATE DATETIME,
			ITEM_CORRELATION_CREATION_DATE DATETIME;
GO

ALTER TABLE HOMEPAGE.NR_ENTRIES 
	ADD 	ITEM_CORRELATION_CREATION_DATE DATETIME;
GO

ALTER TABLE HOMEPAGE.NR_ENTRIES_ARCHIVE 
	ADD 	ITEM_CORRELATION_CREATION_DATE DATETIME;
GO


-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END NewsHp Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%


------------------------------------------------

UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 479, RELEASEVER = '5.0.0.0 CR3' 
WHERE   DBSCHEMAVER = 478; 

GO


COMMIT;
