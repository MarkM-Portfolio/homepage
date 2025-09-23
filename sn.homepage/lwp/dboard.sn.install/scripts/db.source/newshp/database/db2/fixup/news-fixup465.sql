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

ALTER BUFFERPOOL BOARD16KBP SIZE 32000@
COMMIT@

ALTER BUFFERPOOL BRD32KBP SIZE 32000@
COMMIT@


-- 110166: fixup465 - rename BOARD_OBJECT_REFERENCE.META_TYPE to BOARD_OBJECT_REFERENCE.TYPE
ALTER TABLE HOMEPAGE.BOARD_OBJECT_REFERENCE RENAME COLUMN MIME_TYPE TO TYPE@
COMMIT@
