-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2008, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

connect to HOMEPAGE;


runstats on table HOMEPAGE.HOMEPAGE_SCHEMA with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.PERSON with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.LOGINNAME with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.PREREQ with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.WIDGET with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.HP_UI with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.HP_TAB with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.HP_TAB_INST with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.HP_WIDGET_INST with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.HP_WIDGET_TAB with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NT_NOTIFICATION with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NT_NOTIFICATION_RECIPIENT with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NR_SOURCE with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SUBSCRIPTION with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_NEWS_RECORDS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_TEMPLATE with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_EVENT_RECORDS with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.EMD_JOBS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.EMD_JOBS_STATS with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.EMD_RECIPIENTS with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.NR_SCHEDULER_LMGR with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SCHEDULER_LMPR with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SCHEDULER_TASK with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NR_SCHEDULER_TREG with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.SR_INDEXINGTASKDEF with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.SR_OPTIMIZETASKDEF with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.SR_TASKDEF with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.SR_FILESCONTENT with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.SR_MIGTASKDEFINFO with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.LOTUSCONNECTIONSLMGR with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.LOTUSCONNECTIONSLMPR with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.LOTUSCONNECTIONSTASK with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.LOTUSCONNECTIONSTREG with distribution and detailed indexes all allow write access;

COMMIT;
	
--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate; 