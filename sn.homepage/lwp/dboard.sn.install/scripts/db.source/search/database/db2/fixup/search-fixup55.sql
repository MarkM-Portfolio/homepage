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

------ START FIX FORPMAN89HG7Z ------ 

UPDATE HOMEPAGE.SR_TASKDEF SET STARTBY='0 10/15 0,2-23 * * ?',INTERVAL='0 1/15 0,2-23 * * ?' WHERE TASK_NAME='15min-search-indexing-task';

UPDATE HOMEPAGE.SR_TASKDEF SET STARTBY='0 10/20 0,2-23 * * ?',INTERVAL='0 1/20 0,2-23 * * ?' WHERE TASK_NAME='20min-file-retrieval-task';

UPDATE HOMEPAGE.SR_TASKDEF SET STARTBY='0 5 1 * * ?',INTERVAL='0 0 1 * * ?' WHERE TASK_NAME='nightly-sand-task';

UPDATE HOMEPAGE.SR_TASKDEF SET STARTBY='0 35 1 * * ?',INTERVAL='0 30 1 * * ?' WHERE TASK_NAME='nightly-optimize-task';

------ END FIX FOR PMAN89HG7Z ------ 

---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
