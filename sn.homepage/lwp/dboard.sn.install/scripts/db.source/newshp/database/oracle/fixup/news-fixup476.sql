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

CREATE INDEX HOMEPAGE.NR_SL_VIS_DELETED_US_STR 
	ON HOMEPAGE.NR_AS_SEEDLIST (IS_VISIBLE, IS_DELETED, UPDATE_DATE DESC, STORY_ID) TABLESPACE NEWSINDEXTABSPACE;
COMMIT;

ALTER TABLE HOMEPAGE.NR_AS_SEEDLIST ADD COMMUNITY_ID VARCHAR2(36);
COMMIT;

CREATE INDEX HOMEPAGE.NR_AS_SEEDLIST_COMM_IDX
	ON HOMEPAGE.NR_AS_SEEDLIST (COMMUNITY_ID) TABLESPACE NEWSINDEXTABSPACE;
COMMIT;

