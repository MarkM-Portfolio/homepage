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
	ADD SERVER_ACCESS CLOB
	ADD ADDITIONAL_FEATURES CLOB;
COMMIT;

REORG TABLE HOMEPAGE.WIDGET ALLOW NO ACCESS;
COMMIT;


----------------------------------------------------
------------------------ START END FIXUP 204 -------
----------------------------------------------------