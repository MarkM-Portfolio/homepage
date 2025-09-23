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

-- {COPYRIGHT}

-- -----------------------------------------------------------------
-- Defect 173268:
-- -----------------------------------------------------------------

-- NR_ENTRIES_ITEMORG was already create as unique in fixup704 on SQLServer
-- so we are not required to do anything (as per DB2 and Oracle which we do)

DROP INDEX NR_ENTRIES_AR_ITEM ON HOMEPAGE.NR_ENTRIES_ARCHIVE;
GO

CREATE UNIQUE INDEX NR_ENTRIES_AR_ITEMORG
    ON HOMEPAGE.NR_ENTRIES_ARCHIVE (ITEM_ID, ORGANIZATION_ID);
GO

-- -----------------------------------------------------------------
-- Defect 177030:
-- -----------------------------------------------------------------

-- drop old indexes
DROP INDEX NR_COMM_PERSON_F ON HOMEPAGE.NR_COMM_PERSON_FOLLOW;
GO

-- recreate new indexes
CREATE INDEX NR_COMM_PERSON_F 
	ON HOMEPAGE.NR_COMM_PERSON_FOLLOW (PERSON_ID, IS_READER_COMM, PERSON_COMMUNITY_ID, ORGANIZATION_ID);
GO
