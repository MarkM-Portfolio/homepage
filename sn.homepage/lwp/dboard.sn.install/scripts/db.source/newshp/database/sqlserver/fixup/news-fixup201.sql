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

---------------------------------------------------------------------------------
------------------------ START NEWS FIXUP 201 -----------------------------------
---------------------------------------------------------------------------------

-- 78715 - New columns on NR_SOURCE_TYPE table.
ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE
ADD IS_DIGEST_ENABLED NUMERIC(5,0)  NOT NULL DEFAULT 0,
    DEFAULT_DIGEST_FREQUENCY NUMERIC(5,0)  NOT NULL DEFAULT 0,
    IS_DIGEST_LOCKED NUMERIC(5,0) NOT NULL DEFAULT 0;
GO

ALTER TABLE HOMEPAGE.NR_ENTRIES
  ALTER COLUMN N_COMMENTS NUMERIC(10,0);
GO

ALTER TABLE HOMEPAGE.NR_ENTRIES
  ALTER COLUMN N_RECOMMANDATIONS NUMERIC(10,0);
GO


---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 201 -------------------------------------
---------------------------------------------------------------------------------
 

  
  
  
