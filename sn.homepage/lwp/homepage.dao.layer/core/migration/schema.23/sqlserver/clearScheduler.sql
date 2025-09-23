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

USE HOMEPAGE;
GO

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSTASK;
GO
DELETE FROM HOMEPAGE.LOTUSCONNECTIONSTREG;
GO
DELETE FROM HOMEPAGE.LOTUSCONNECTIONSLMGR;
GO
DELETE FROM HOMEPAGE.LOTUSCONNECTIONSLMPR;
GO

DELETE FROM HOMEPAGE.NR_SCHEDULER_TASK;
GO
DELETE FROM HOMEPAGE.NR_SCHEDULER_TREG;
GO
DELETE FROM HOMEPAGE.NR_SCHEDULER_LMGR;
GO
DELETE FROM HOMEPAGE.NR_SCHEDULER_LMPR;
GO


