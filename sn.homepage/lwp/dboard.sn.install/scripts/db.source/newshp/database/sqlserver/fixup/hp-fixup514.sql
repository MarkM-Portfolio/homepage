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

-- 139069: Refactoring of two unique indexes

DROP INDEX LOGINNAME_UNIQUE ON HOMEPAGE.LOGINNAME;

CREATE UNIQUE INDEX LOGINNAME_UNIQUE
	ON HOMEPAGE.LOGINNAME (LOGINNAME, ORGANIZATION_ID);
	
-- 139994: Clean HW data
DELETE FROM HOMEPAGE.MT_CFG_FILES WHERE SETTING_ID IN (
	SELECT SETTING_ID FROM HOMEPAGE.MT_CFG_SETTINGS WHERE (NAME = 'url.blacklist' OR NAME = 'url.whitelist') AND ROLE <> '___default_role___'
);

GO

DELETE FROM HOMEPAGE.MT_CFG_SETTINGS WHERE SETTING_ID IN (
	SELECT SETTING_ID FROM HOMEPAGE.MT_CFG_SETTINGS WHERE (NAME = 'url.blacklist' OR NAME = 'url.whitelist') AND ROLE <> '___default_role___'
);

GO




