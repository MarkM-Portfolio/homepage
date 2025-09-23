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
reorg table HOMEPAGE.HP_UI use TEMPSPACE1;
reorg table HOMEPAGE.HP_TAB use TEMPSPACE1;
reorg table HOMEPAGE.HP_TAB_INST use TEMPSPACE1;
reorg table HOMEPAGE.HP_WIDGET_INST use TEMPSPACE1;
reorg table HOMEPAGE.HP_WIDGET_TAB use TEMPSPACE1;
reorg table HOMEPAGE.PREREQ use TEMPSPACE1;

reorg table HOMEPAGE.NT_NOTIFICATION use HPNTTMPTABSPACE;
reorg table HOMEPAGE.NT_NOTIFICATION_RECIPIENT use HPNTTMPTABSPACE;

reorg table HOMEPAGE.NR_SOURCE use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_SUBSCRIPTION use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_NEWS_RECORDS use NEWSTMPTABSPACE;
reorg table HOMEPAGE.NR_TEMPLATE use NEWSTMPTABSPACE;


reorg indexes all for table HOMEPAGE.HOMEPAGE_SCHEMA;
reorg indexes all for table HOMEPAGE.PERSON;
reorg indexes all for table HOMEPAGE.LOGINNAME;
reorg indexes all for table HOMEPAGE.HP_UI;
reorg indexes all for table HOMEPAGE.HP_TAB;
reorg indexes all for table HOMEPAGE.HP_TAB_INST;
reorg indexes all for table HOMEPAGE.HP_WIDGET_INST;
reorg indexes all for table HOMEPAGE.HP_WIDGET_TAB;
reorg indexes all for table HOMEPAGE.PREREQ;
reorg indexes all for table HOMEPAGE.NT_NOTIFICATION;
reorg indexes all for table HOMEPAGE.NT_NOTIFICATION_RECIPIENT;

reorg indexes all for table HOMEPAGE.NR_SOURCE;
reorg indexes all for table HOMEPAGE.NR_SUBSCRIPTION;
reorg indexes all for table HOMEPAGE.NR_NEWS_RECORDS;
reorg indexes all for table HOMEPAGE.NR_TEMPLATE;


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
