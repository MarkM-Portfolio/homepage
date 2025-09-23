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

-----------------------------------------------------------------------------------------------------------
-- START SEARCH
-----------------------------------------------------------------------------------------------------------

INSERT INTO HOMEPAGE.SR_TASKDEF(TASK_ID,TASK_NAME,STARTBY,INTERVAL,TASK_TYPE,ENABLED)
VALUES('6f6ee109-6ad4-4926-afcb-d2a04a0e390d','15min-search-indexing-task','0 10/15 0,2-23 * * ?','0 1/15 0,2-23 * * ?','IndexingTask',1)@

INSERT INTO HOMEPAGE.SR_TASKDEF(TASK_ID,TASK_NAME,STARTBY,INTERVAL,TASK_TYPE,ENABLED)
VALUES('ea789e87-c262-484b-92f4-d60af4bef3d3','nightly-optimize-task','0 35 1 * * ?','0 30 1 * * ?' ,'OptimizeTask',1)@

INSERT INTO HOMEPAGE.SR_TASKDEF(TASK_ID,TASK_NAME,STARTBY,INTERVAL,TASK_TYPE,ENABLED)
VALUES('111111-1111-1111-1111-1111111FCRT','20min-file-retrieval-task','0 10/20 0,2-23 * * ?','0 1/20 0,2-23 * * ?','FileContentRetrievalTask',1)@

INSERT INTO HOMEPAGE.SR_INDEXINGTASKDEF(INDEXING_TASK_ID,TASK_ID,INDEXING_TASK_SERVICES,INDEXING_TASK_OPTIMIZE)
VALUES('315a416c-78e2-4cf4-bcb2-69eb8d3a2583','6f6ee109-6ad4-4926-afcb-d2a04a0e390d','all_configured',0)@

INSERT INTO HOMEPAGE.SR_OPTIMIZETASKDEF(OPTIMIZE_TASK_ID,TASK_ID)
VALUES('fd44131a-5075-4bcb-85a9-9e501bd010fa','ea789e87-c262-484b-92f4-d60af4bef3d3')@

INSERT INTO HOMEPAGE.SR_TASKDEF(TASK_ID,TASK_NAME,STARTBY,INTERVAL,TASK_TYPE,ENABLED)
VALUES('ea789e87-c262-484b-92f4-d60af4bef3d4','nightly-sand-task','0 5 1 * * ?','0 0 1 * * ?','SaNDTask',1)@

INSERT INTO HOMEPAGE.SR_SANDTASKDEF(SAND_TASK_ID,TASK_ID,SAND_TASK_SERVICES)
VALUES('fd44131a-5075-4bcb-85a9-9e501bd010fb','ea789e87-c262-484b-92f4-d60af4bef3d4','evidence-graph-manageremployees-tags-taggedby-communitymembership')@

INSERT INTO HOMEPAGE.SR_FILECONTENTTASKDEF(FILECONTENT_TASK_ID,TASK_ID,FILE_CONTENT_TASK_SERVICES,CONTENT_FAILURES_ONLY)
VALUES('111111-1111-1111-1111-1111111FCRT','111111-1111-1111-1111-1111111FCRT','all_configured',0)@

INSERT INTO HOMEPAGE.SR_TASKDEF(TASK_ID,TASK_NAME,STARTBY,INTERVAL,TASK_TYPE,ENABLED)
VALUES('60036bd4-e48d-4421-a6de-c0d819bbe408','20min-file-content-indexing-task','0 29,49,09 0,2-23 * * ?','0 11,31,51 0,2-23 * * ?','FileContentIndexingTask',1)@

INSERT INTO HOMEPAGE.SR_FILECONTENTINDEXINGTASK(FILE_CONTENT_INDEXING_TASK_ID,TASK_ID,FILE_CONT_INDX_TASK_SERVICES,FILE_CONT_INDX_TASK_DURATION)
VALUES('e52073b9-bbe2-4041-aa08-91fe76d17526','60036bd4-e48d-4421-a6de-c0d819bbe408','all_configured',300)@

-----------------------------------------------------------------------------------------------------------
-- END SEARCH
-----------------------------------------------------------------------------------------------------------
