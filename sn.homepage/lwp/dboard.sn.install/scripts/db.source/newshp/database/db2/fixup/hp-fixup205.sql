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
------------------------ START HP FIXUP 205 --------
----------------------------------------------------
-- 82712: update SQL files to change microblogging gadget from INLINE -> iframe
UPDATE HOMEPAGE.WIDGET SET WIDGET_POLICY_FLAGS = 39 WHERE WIDGET_TITLE = '%widget.connshare.files.name';
COMMIT;

----------------------------------------------------
------------------------ START END FIXUP 205 -------
----------------------------------------------------