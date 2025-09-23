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

-- 99469: [fixup462] Resize ORGANIZATION_EXID column in MT_ORGANIZATION table to 36 char rather than 256 chars
ALTER TABLE HOMEPAGE.MT_ORGANIZATION ALTER COLUMN ORGANIZATION_EXID SET DATA TYPE VARCHAR(36)@
REORG TABLE HOMEPAGE.MT_ORGANIZATION@
COMMIT@

DROP INDEX HOMEPAGE.UNQ_ORG_NAME@
COMMIT@

CREATE UNIQUE INDEX HOMEPAGE.UNQ_ORG_NAME
	ON HOMEPAGE.MT_CFG_SETTINGS (NAME, ORGANIZATION_ID, ROLE)@
COMMIT@

----------------------------------------------------
------------------------ END   HP FIXUP 462 -------
----------------------------------------------------