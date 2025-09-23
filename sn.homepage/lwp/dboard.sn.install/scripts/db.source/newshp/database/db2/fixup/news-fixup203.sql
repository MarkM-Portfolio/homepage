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

---------------------------------------------------------------------------------
------------------------ START NEWS FIXUP 203  -----------------------------------
---------------------------------------------------------------------------------

------------------------------------------------
-- HOMEPAGE.BOARD_MENTIONS
------------------------------------------------
CREATE TABLE HOMEPAGE.BOARD_MENTIONS  (
	BOARD_MENTIONS_ID VARCHAR(36) NOT NULL, --primary key
	ITEM_ID VARCHAR(256) NOT NULL, 
	PERSON_ID VARCHAR(36) NOT NULL, -- PERSON_ID of the recommender, FK to PERSON table
	LABEL VARCHAR(4000),
	WAS_ITEM_VISIBLE SMALLINT DEFAULT 0 NOT NULL
)
IN BOARD16TABSPACE;

ALTER TABLE HOMEPAGE.BOARD_MENTIONS 
    ADD CONSTRAINT PK_BRD_MEN_ID PRIMARY KEY(BOARD_MENTIONS_ID);

ALTER TABLE HOMEPAGE.BOARD_MENTIONS
	ADD CONSTRAINT FK_BRD_MEN_PER_ID FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);

CREATE INDEX HOMEPAGE.BRD_MEN_PER_IDX
	ON HOMEPAGE.BOARD_MENTIONS (PERSON_ID);
	
CREATE INDEX HOMEPAGE.BRD_MEN_ITEM_IDX
	ON HOMEPAGE.BOARD_MENTIONS (ITEM_ID);	
	
COMMIT;	
	
---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 203 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  
