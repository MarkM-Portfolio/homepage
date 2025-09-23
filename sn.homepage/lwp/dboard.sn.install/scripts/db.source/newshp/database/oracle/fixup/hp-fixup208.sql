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
------------------------ START HP FIXUP 208 --------
----------------------------------------------------

-- 85942: Remove the tables NT_NOTIFICATION and NT_NOTIFICATION_RECIPIENT
DROP TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT;
COMMIT;

DROP TABLE HOMEPAGE.NT_NOTIFICATION;
COMMIT;

----------------------------------------------------
------------------------ START END FIXUP 208 -------
----------------------------------------------------