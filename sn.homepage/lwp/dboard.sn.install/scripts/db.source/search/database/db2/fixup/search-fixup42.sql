-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2010, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

---------------------------------------------------------------------------------
------------------------ START SEARCH -------------------------------------------
---------------------------------------------------------------------------------


----------------------------------------
--  SR_TASKDEF
----------------------------------------
reorg table HOMEPAGE.SR_TASKDEF use TEMPSPACE1;
runstats on table HOMEPAGE.SR_TASKDEF with distribution and detailed indexes all allow write access;
 
INSERT INTO HOMEPAGE.SR_TASKDEF(TASK_ID,TASK_NAME,STARTBY,INTERVAL,TASK_TYPE,ENABLED)
VALUES('111111-1111-1111-1111-1111111FCRT','20min-file-retrieval-task','0 10/20 0-23 * * ?','0 1/20 0-23 * * ?','FileContentRetrievalTask',1);

----------------------------------------
--  SR_FILECONTENTTASKDEF
----------------------------------------
reorg table HOMEPAGE.SR_FILECONTENTTASKDEF use TEMPSPACE1;
runstats on table HOMEPAGE.SR_FILECONTENTTASKDEF with distribution and detailed indexes all allow write access;

INSERT INTO HOMEPAGE.SR_FILECONTENTTASKDEF(FILECONTENT_TASK_ID,TASK_ID,FILE_CONTENT_TASK_SERVICES,CONTENT_FAILURES_ONLY)
VALUES('111111-1111-1111-1111-1111111FCRT','111111-1111-1111-1111-1111111FCRT','all_configured',0);

---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------