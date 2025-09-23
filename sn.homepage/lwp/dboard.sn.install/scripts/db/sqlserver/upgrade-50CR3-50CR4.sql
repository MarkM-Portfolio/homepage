-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2015, 2016                              
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

-------------------------------------------------------------
-- No DB changes for 5.0 CR4 so no need to update DBSCHEMAVER
-------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 479, RELEASEVER = '5.0.0.0 CR4' 
WHERE   DBSCHEMAVER = 479; 

GO


COMMIT;
