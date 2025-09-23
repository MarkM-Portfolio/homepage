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

--------------------------------------------------------------------------------------------------------------------
--- 127961: DB schema and DAO addition to support user impersonation and date override
--------------------------------------------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.BOARD_ENTRIES 
	ADD	CREATION_DATE_DISPLAY TIMESTAMP
	ADD	UPDATE_DATE_DISPLAY TIMESTAMP
	ADD	ACTOR_UUID_DISPLAY VARCHAR2(36)
	ADD	LAST_CONTRIBUTOR_ID_DISPLAY VARCHAR2(36);
	
COMMIT;	

ALTER TABLE HOMEPAGE.BOARD_COMMENTS 
	ADD AUTHOR_UUID_DISPLAY VARCHAR2(36)
	ADD LAST_CONTRIBUTOR_ID_DISPLAY VARCHAR2(36)
	ADD CREATION_DATE_DISPLAY TIMESTAMP
	ADD UPDATE_DATE_DISPLAY TIMESTAMP;

COMMIT;

ALTER TABLE HOMEPAGE.BOARD_RECOMMENDATIONS
	ADD RECOMMENDER_ID_DISPLAY VARCHAR2(36)
	ADD CREATION_DATE_DISPLAY TIMESTAMP;

COMMIT;

-- Note FK it is just for 	RECOMMENDER_ID_DISPLAY
ALTER TABLE HOMEPAGE.BOARD_RECOMMENDATIONS
	ADD CONSTRAINT FK_BRD_RECOMMENDER_D FOREIGN KEY (RECOMMENDER_ID_DISPLAY) REFERENCES HOMEPAGE.PERSON (PERSON_ID);

COMMIT;

CREATE INDEX HOMEPAGE.BRD_RECOMMENDER_ID_D
    ON HOMEPAGE.BOARD_RECOMMENDATIONS (RECOMMENDER_ID_DISPLAY)  TABLESPACE "BOARDINDEXTABSPACE";

COMMIT;