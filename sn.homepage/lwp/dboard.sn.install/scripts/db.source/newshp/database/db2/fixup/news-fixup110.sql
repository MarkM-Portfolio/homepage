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
------------------------ START NEWS FIXUP 110 -----------------------------------
---------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.OH2P_CACHE ALTER COLUMN USERNAME SET DATA TYPE VARCHAR(256);
COMMIT;

CREATE INDEX HOMEPAGE.OH2P_CACHE_CNT 
	ON HOMEPAGE.OH2P_CACHE (USERNAME, CLIENTID, COMPONENTID);
COMMIT;

-- 76830: Missing index on CREATION_DATE column in HOMEPAGE.NR_AS_SEEDLIST
CREATE INDEX HOMEPAGE.NR_SL_CD_DELETED
	ON HOMEPAGE.NR_AS_SEEDLIST (CREATION_DATE DESC);
COMMIT;


---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 110 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  
