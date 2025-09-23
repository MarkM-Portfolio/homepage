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
ADD (IS_DIGEST_ENABLED NUMBER(5,0) DEFAULT 0 NOT NULL,
     DEFAULT_DIGEST_FREQUENCY NUMBER(5,0)  DEFAULT 0 NOT NULL,
     IS_DIGEST_LOCKED NUMBER(5,0) DEFAULT 0 NOT NULL);
COMMIT;

ALTER TABLE HOMEPAGE.NR_ENTRIES
MODIFY (N_COMMENTS NUMBER(10,0),
		N_RECOMMANDATIONS NUMBER(10,0));
COMMIT;
	
---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 201 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  
