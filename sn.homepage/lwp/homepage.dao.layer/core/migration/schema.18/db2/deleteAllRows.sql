-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2009, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- REMOVING RECORDS -----------------
CONNECT TO HOMEPAGE;

DELETE FROM HOMEPAGE.HOMEPAGE_SCHEMA;
DELETE FROM HOMEPAGE.PERSON;
DELETE FROM HOMEPAGE.LOGINNAME;
DELETE FROM HOMEPAGE.PREREQ;
DELETE FROM HOMEPAGE.WIDGET;

DELETE FROM HOMEPAGE.HP_UI;
DELETE FROM HOMEPAGE.HP_TAB;
DELETE FROM HOMEPAGE.HP_TAB_INST;
DELETE FROM HOMEPAGE.HP_WIDGET_INST;
DELETE FROM HOMEPAGE.HP_WIDGET_TAB;

DELETE FROM HOMEPAGE.NT_NOTIFICATION;
DELETE FROM HOMEPAGE.NT_NOTIFICATION_RECIPIENT;

DELETE FROM HOMEPAGE.NR_SOURCE;
DELETE FROM HOMEPAGE.NR_SUBSCRIPTION;
DELETE FROM HOMEPAGE.NR_NEWS_RECORDS;
DELETE FROM HOMEPAGE.NR_TEMPLATE;
DELETE FROM HOMEPAGE.NR_EVENT_RECORDS;

DELETE FROM HOMEPAGE.EMD_JOBS;
DELETE FROM HOMEPAGE.EMD_JOBS_STATS;
DELETE FROM HOMEPAGE.EMD_RECIPIENTS;

DELETE FROM HOMEPAGE.SR_INDEXINGTASKDEF;
DELETE FROM HOMEPAGE.SR_OPTIMIZETASKDEF;
DELETE FROM HOMEPAGE.SR_TASKDEF;
DELETE FROM HOMEPAGE.SR_FILESCONTENT;
DELETE FROM HOMEPAGE.SR_MIGTASKDEFINFO;



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
