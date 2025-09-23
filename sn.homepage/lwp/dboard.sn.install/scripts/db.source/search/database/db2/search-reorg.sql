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

-- {COPYRIGHT}

-- to create a new baseline

-- SEARCH
reorg table HOMEPAGE.SR_INDEXINGTASKDEF use TEMPSPACE1;
reorg table HOMEPAGE.SR_OPTIMIZETASKDEF use TEMPSPACE1;
reorg table HOMEPAGE.SR_TASKDEF use TEMPSPACE1;
reorg table HOMEPAGE.SR_FILESCONTENT use TEMPSPACE1;
reorg table HOMEPAGE.SR_MIGTASKDEFINFO use TEMPSPACE1;
reorg table HOMEPAGE.SR_FILECONTENTTASKDEF use TEMPSPACE1;
reorg table HOMEPAGE.SR_INDEX_DOCS use TEMPSPACE1;
reorg table HOMEPAGE.SR_RESUME_TOKENS use TEMPSPACE1;
reorg table HOMEPAGE.SR_BACKUPTASKDEF use TEMPSPACE1;
reorg table HOMEPAGE.SR_INDEX_MANAGEMENT use TEMPSPACE1;
reorg table HOMEPAGE.SR_SANDTASKDEF use TEMPSPACE1;
reorg table HOMEPAGE.SR_FEEDBACK use TEMPSPACE1;
reorg table HOMEPAGE.SR_FEEDBACK_CONTEXT use TEMPSPACE1;
reorg table HOMEPAGE.SR_FEEDBACK_PARAMETERS use TEMPSPACE1;
reorg table HOMEPAGE.SR_STATS use TEMPSPACE1;
reorg table HOMEPAGE.SR_STRING_STATS use TEMPSPACE1;
reorg table HOMEPAGE.SR_NUMBER_STATS use TEMPSPACE1;
reorg table HOMEPAGE.SR_TIMER_STATS use TEMPSPACE1;
reorg table HOMEPAGE.SR_GLOBAL_SAND_PROPS use TEMPSPACE1;
reorg table HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS use TEMPSPACE1;
reorg table HOMEPAGE.SR_ECM_DOCUMENT_TYPE_PROPS  use TEMPSPACE1;
reorg table HOMEPAGE.SR_POST_FILTERING_SERVICE use TEMPSPACE1;

reorg table HOMEPAGE.LOTUSCONNECTIONSLMGR use TEMPSPACE1;
reorg table HOMEPAGE.LOTUSCONNECTIONSLMPR use TEMPSPACE1;
reorg table HOMEPAGE.LOTUSCONNECTIONSTASK use TEMPSPACE1;
reorg table HOMEPAGE.LOTUSCONNECTIONSTREG use TEMPSPACE1;

-------------------------------------------------------


-- SEARCH
reorg indexes all for table HOMEPAGE.SR_INDEXINGTASKDEF;
reorg indexes all for table HOMEPAGE.SR_OPTIMIZETASKDEF;
reorg indexes all for table HOMEPAGE.SR_TASKDEF;
reorg indexes all for table HOMEPAGE.SR_FILESCONTENT;
reorg indexes all for table HOMEPAGE.SR_MIGTASKDEFINFO;
reorg indexes all for table HOMEPAGE.SR_FILECONTENTTASKDEF;
reorg indexes all for table HOMEPAGE.SR_INDEX_DOCS;
reorg indexes all for table HOMEPAGE.SR_RESUME_TOKENS;
reorg indexes all for table HOMEPAGE.SR_BACKUPTASKDEF;
reorg indexes all for table HOMEPAGE.SR_INDEX_MANAGEMENT;
reorg indexes all for table HOMEPAGE.SR_SANDTASKDEF;
reorg indexes all for table HOMEPAGE.SR_FEEDBACK;
reorg indexes all for table HOMEPAGE.SR_FEEDBACK_CONTEXT;
reorg indexes all for table HOMEPAGE.SR_FEEDBACK_PARAMETERS;
reorg indexes all for table HOMEPAGE.SR_STATS;
reorg indexes all for table HOMEPAGE.SR_STRING_STATS;
reorg indexes all for table HOMEPAGE.SR_NUMBER_STATS;
reorg indexes all for table HOMEPAGE.SR_TIMER_STATS;
reorg indexes all for table HOMEPAGE.SR_GLOBAL_SAND_PROPS;
reorg indexes all for table HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS;
reorg indexes all for table HOMEPAGE.SR_ECM_DOCUMENT_TYPE_PROPS;
reorg indexes all for table HOMEPAGE.SR_POST_FILTERING_SERVICE;

reorg indexes all for table HOMEPAGE.LOTUSCONNECTIONSLMGR;
reorg indexes all for table HOMEPAGE.LOTUSCONNECTIONSLMPR;
reorg indexes all for table HOMEPAGE.LOTUSCONNECTIONSTASK;
reorg indexes all for table HOMEPAGE.LOTUSCONNECTIONSTREG;
