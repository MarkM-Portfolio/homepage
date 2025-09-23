-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2016                                    
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

{include.hp-fixup706.sql}
{include.news-fixup706.sql}

------------------------------------------------

UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 706, RELEASEVER = '5.5.0.0 CR1' 
WHERE   DBSCHEMAVER = 705; 

GO


COMMIT;
