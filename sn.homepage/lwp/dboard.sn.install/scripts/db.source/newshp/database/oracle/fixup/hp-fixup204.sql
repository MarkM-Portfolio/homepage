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
------------------------ START HP FIXUP 204 --------
----------------------------------------------------
-- 80778: Schema changes to enable per-gadget-per-server rules

ALTER TABLE HOMEPAGE.WIDGET 
	ADD SERVER_ACCESS 			CLOB 	LOB (SERVER_ACCESS) STORE AS (TABLESPACE NEWSLOBTABSPACE STORAGE (INITIAL 1M) CHUNK 4000 NOCACHE NOLOGGING)
	ADD ADDITIONAL_FEATURES 	CLOB 	LOB (ADDITIONAL_FEATURES) STORE AS (TABLESPACE NEWSLOBTABSPACE STORAGE (INITIAL 1K) CHUNK 100 NOCACHE NOLOGGING);


COMMIT;


----------------------------------------------------
------------------------ START END FIXUP 204 -------
----------------------------------------------------