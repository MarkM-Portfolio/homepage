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

reorg table HOMEPAGE.HOMEPAGE_SCHEMA use TEMPSPACE1;
reorg table HOMEPAGE.PERSON use TEMPSPACE1;
reorg table HOMEPAGE.LOGINNAME use TEMPSPACE1;
reorg table HOMEPAGE.PREREQ use TEMPSPACE1;
reorg table HOMEPAGE.WIDGET use TEMPSPACE1;

reorg table HOMEPAGE.HP_UI use TEMPSPACE1;
reorg table HOMEPAGE.HP_TAB use TEMPSPACE1;
reorg table HOMEPAGE.HP_TAB_INST use TEMPSPACE1;
reorg table HOMEPAGE.HP_WIDGET_INST use TEMPSPACE1;
reorg table HOMEPAGE.HP_WIDGET_TAB use TEMPSPACE1;

reorg table HOMEPAGE.NT_NOTIFICATION use HPNTTMPTABSPACE;
reorg table HOMEPAGE.NT_NOTIFICATION_RECIPIENT use HPNTTMPTABSPACE;

reorg table HOMEPAGE.NR_SOURCE use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_SUBSCRIPTION use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_NEWS_RECORDS use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_TEMPLATE use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_EVENT_RECORDS use NEWSTMPTABSPACE;

reorg table HOMEPAGE.EMD_JOBS use NEWSTMPTABSPACE;
reorg table HOMEPAGE.EMD_JOBS_STATS use NEWSTMPTABSPACE;
reorg table HOMEPAGE.EMD_RECIPIENTS use NEWSTMPTABSPACE;

reorg table HOMEPAGE.NR_SCHEDULER_LMGR use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_SCHEDULER_LMPR use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_SCHEDULER_TASK use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_SCHEDULER_TREG use NEWSTMPTABSPACE;

reorg table HOMEPAGE.SR_INDEXINGTASKDEF use TEMPSPACE1;
reorg table HOMEPAGE.SR_OPTIMIZETASKDEF use TEMPSPACE1;
reorg table HOMEPAGE.SR_TASKDEF use TEMPSPACE1;
reorg table HOMEPAGE.SR_FILESCONTENT use TEMPSPACE1;

reorg table HOMEPAGE.LOTUSCONNECTIONSLMGR use TEMPSPACE1;
reorg table HOMEPAGE.LOTUSCONNECTIONSLMPR use TEMPSPACE1;
reorg table HOMEPAGE.LOTUSCONNECTIONSTASK use TEMPSPACE1;
reorg table HOMEPAGE.LOTUSCONNECTIONSTREG use TEMPSPACE1;

-------------------------------------------------------

reorg indexes all for table HOMEPAGE.HOMEPAGE_SCHEMA;
reorg indexes all for table HOMEPAGE.PERSON;
reorg indexes all for table HOMEPAGE.LOGINNAME;
reorg indexes all for table HOMEPAGE.PREREQ;
reorg indexes all for table HOMEPAGE.WIDGET;

reorg indexes all for table HOMEPAGE.HP_UI;
reorg indexes all for table HOMEPAGE.HP_TAB;
reorg indexes all for table HOMEPAGE.HP_TAB_INST;
reorg indexes all for table HOMEPAGE.HP_WIDGET_INST;
reorg indexes all for table HOMEPAGE.HP_WIDGET_TAB;

reorg indexes all for table HOMEPAGE.NT_NOTIFICATION;
reorg indexes all for table HOMEPAGE.NT_NOTIFICATION_RECIPIENT;

reorg indexes all for table HOMEPAGE.NR_SOURCE;
reorg indexes all for table HOMEPAGE.NR_SUBSCRIPTION;
reorg indexes all for table HOMEPAGE.NR_NEWS_RECORDS;
reorg indexes all for table HOMEPAGE.NR_TEMPLATE;
reorg indexes all for table HOMEPAGE.NR_EVENT_RECORDS;

reorg indexes all for table HOMEPAGE.EMD_JOBS;
reorg indexes all for table HOMEPAGE.EMD_JOBS_STATS;
reorg indexes all for table HOMEPAGE.EMD_RECIPIENTS;

reorg indexes all for table HOMEPAGE.NR_SCHEDULER_LMGR;
reorg indexes all for table HOMEPAGE.NR_SCHEDULER_LMPR;
reorg indexes all for table HOMEPAGE.NR_SCHEDULER_TASK;
reorg indexes all for table HOMEPAGE.NR_SCHEDULER_TREG;

reorg indexes all for table HOMEPAGE.SR_INDEXINGTASKDEF;
reorg indexes all for table HOMEPAGE.SR_OPTIMIZETASKDEF;
reorg indexes all for table HOMEPAGE.SR_TASKDEF;
reorg indexes all for table HOMEPAGE.SR_FILESCONTENT;

reorg indexes all for table HOMEPAGE.LOTUSCONNECTIONSLMGR;
reorg indexes all for table HOMEPAGE.LOTUSCONNECTIONSLMPR;
reorg indexes all for table HOMEPAGE.LOTUSCONNECTIONSTASK;
reorg indexes all for table HOMEPAGE.LOTUSCONNECTIONSTREG;


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
