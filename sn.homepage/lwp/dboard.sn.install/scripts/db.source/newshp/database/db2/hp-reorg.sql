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


-- to create a new baseline

reorg table HOMEPAGE.HOMEPAGE_SCHEMA use TEMPSPACE1;
reorg table HOMEPAGE.PERSON use TEMPSPACE1;
reorg table HOMEPAGE.LOGINNAME use TEMPSPACE1;
reorg table HOMEPAGE.PREREQ use TEMPSPACE1;
reorg table HOMEPAGE.WIDGET use HPNT16TMPTABSPACE;

reorg table HOMEPAGE.HP_UI use TEMPSPACE1;
reorg table HOMEPAGE.HP_TAB use TEMPSPACE1;
reorg table HOMEPAGE.HP_TAB_INST use TEMPSPACE1;
reorg table HOMEPAGE.HP_WIDGET_INST use TEMPSPACE1;
reorg table HOMEPAGE.HP_WIDGET_TAB use TEMPSPACE1;

reorg table HOMEPAGE.NT_NOTIFICATION use HPNT16TMPTABSPACE;
reorg table HOMEPAGE.NT_NOTIFICATION_RECIPIENT use HPNT16TMPTABSPACE;

reorg table HOMEPAGE.METRIC_STAT use TEMPSPACE1;

reorg table HOMEPAGE.MTCONFIG use TEMPSPACE1;


-------------------------------------------------------

reorg indexes all for table HOMEPAGE.NT_NOTIFICATION;
reorg indexes all for table HOMEPAGE.NT_NOTIFICATION_RECIPIENT;

reorg indexes all for table HOMEPAGE.PERSON;
reorg indexes all for table HOMEPAGE.LOGINNAME;
reorg indexes all for table HOMEPAGE.PREREQ;
reorg indexes all for table HOMEPAGE.WIDGET;

reorg indexes all for table HOMEPAGE.HP_UI;
reorg indexes all for table HOMEPAGE.HP_TAB;
reorg indexes all for table HOMEPAGE.HP_TAB_INST;
reorg indexes all for table HOMEPAGE.HP_WIDGET_INST;
reorg indexes all for table HOMEPAGE.HP_WIDGET_TAB;

reorg indexes all for table HOMEPAGE.METRIC_STAT;

-- NEW SECTION 3.5
reorg table HOMEPAGE.NT_REPLYTO use HPNT16TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NT_REPLYTO;

reorg table HOMEPAGE.NT_REPLYTO_RECIPIENT use HPNT16TMPTABSPACE;
reorg indexes all for table HOMEPAGE.NT_REPLYTO_RECIPIENT;

reorg indexes all for table HOMEPAGE.MTCONFIG;

