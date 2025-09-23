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

----------------------------------------------------
------------------------ START HP FIXUP 462 --------
----------------------------------------------------

DROP INDEX UNQ_ORG_EXID ON HOMEPAGE.MT_ORGANIZATION;
GO

-- 99469: [fixup462] Resize ORGANIZATION_EXID column in MT_ORGANIZATION table to 36 char rather than 256 chars
ALTER TABLE HOMEPAGE.MT_ORGANIZATION ALTER COLUMN ORGANIZATION_EXID nvarchar(36);

CREATE UNIQUE INDEX UNQ_ORG_EXID
	ON HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_EXID);
GO

DROP INDEX UNQ_ORG_NAME ON HOMEPAGE.MT_CFG_SETTINGS;
GO

CREATE UNIQUE INDEX UNQ_ORG_NAME
	ON HOMEPAGE.MT_CFG_SETTINGS (NAME, ORGANIZATION_ID, ROLE);
GO

----------------------------------------------------
------------------------ END   HP FIXUP 462 -------
----------------------------------------------------