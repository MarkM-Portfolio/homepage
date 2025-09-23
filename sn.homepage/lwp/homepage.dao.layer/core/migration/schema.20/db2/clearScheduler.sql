-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2001, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

CONNECT TO HOMEPAGE;

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSTASK;
COMMIT;
DELETE FROM HOMEPAGE.LOTUSCONNECTIONSTREG;
COMMIT;
DELETE FROM HOMEPAGE.LOTUSCONNECTIONSLMGR;
COMMIT;
DELETE FROM HOMEPAGE.LOTUSCONNECTIONSLMPR;
COMMIT;

DELETE FROM HOMEPAGE.NR_SCHEDULER_TASK;
COMMIT;
DELETE FROM HOMEPAGE.NR_SCHEDULER_TREG;
COMMIT;
DELETE FROM HOMEPAGE.NR_SCHEDULER_LMGR;
COMMIT;
DELETE FROM HOMEPAGE.NR_SCHEDULER_LMPR;
COMMIT;
 


CONNECT RESET;


