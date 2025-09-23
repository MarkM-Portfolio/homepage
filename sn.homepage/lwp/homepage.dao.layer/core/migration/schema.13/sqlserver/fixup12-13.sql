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


USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO 

-----------------------------------------------------------------------------------------------------------
--  1 Adding the LOCALE and DISPLAYNAME attribute to the EMD_RECIPIENTS
-----------------------------------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.EMD_RECIPIENTS
	ADD LOCALE nvarchar(5)
GO

ALTER TABLE HOMEPAGE.EMD_RECIPIENTS
	ADD DISPLAYNAME nvarchar(256)
GO

-----------------------------------------------------------------------------------------------------------
--  2 Adding the TEXT_META_TEMPLATE an empty space
-----------------------------------------------------------------------------------------------------------
UPDATE HOMEPAGE.NR_NEWS_RECORDS SET TEXT_META_TEMPLATE = ' '
GO

-----------------------------------------------------------------------------------------------------------
--  3 Adding the default value to 0 for IS_PRIVATE in the table HOMEPAGE.NR_SOURCE
-----------------------------------------------------------------------------------------------------------
UPDATE HOMEPAGE.NR_SOURCE SET IS_PRIVATE = 0
GO

-----------------------------------------------------------------------------------------------------------
--  4 Upgrade fixup number to 13
-----------------------------------------------------------------------------------------------------------

-- Updating the schema version from 12 to 13
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 13
WHERE   DBSCHEMAVER = 12
GO





COMMIT;