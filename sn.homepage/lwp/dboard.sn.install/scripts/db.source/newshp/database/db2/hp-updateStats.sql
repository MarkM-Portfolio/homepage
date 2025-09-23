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

--HOMEPAGE

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

runstats on table HOMEPAGE.METRIC_STAT with distribution and detailed indexes all allow write access;

-- NEW SECTION 3.5
runstats on table HOMEPAGE.NT_REPLYTO with distribution and detailed indexes all allow write access;
runstats on table HOMEPAGE.NT_REPLYTO_RECIPIENT with distribution and detailed indexes all allow write access;

runstats on table HOMEPAGE.MTCONFIG with distribution and detailed indexes all allow write access;
