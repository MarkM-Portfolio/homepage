-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2011, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

---------------------------------------------------------------------------------
------------------------ START SEARCH -------------------------------------------
---------------------------------------------------------------------------------

--START 38374: DAO Layer Changes for Dynamic Global Properties for SAND 

----------------------------------------
--  SR_GLOBAL_SAND_PROPS
----------------------------------------

CREATE TABLE HOMEPAGE.SR_GLOBAL_SAND_PROPS(
	GSP_ID			VARCHAR2(36) NOT NULL,
	GSP_NAME		VARCHAR2(36) NOT NULL,
	GSP_VALUE		VARCHAR2(36) NOT NULL,
	GSP_TYPE        NUMBER(5,0) NOT NULL
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.SR_GLOBAL_SAND_PROPS
	 ADD (CONSTRAINT "PK_GSP_ID" PRIMARY KEY ("GSP_ID")
	 USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");

ALTER TABLE HOMEPAGE.SR_GLOBAL_SAND_PROPS
    ADD CONSTRAINT UNIQUE_GSP_NAME UNIQUE ("GSP_NAME");

ALTER TABLE HOMEPAGE.SR_GLOBAL_SAND_PROPS		
	ADD CONSTRAINT GSP_TYPE_CHECK
	CHECK (GSP_TYPE >=0 AND GSP_TYPE < 4);

ALTER TABLE "HOMEPAGE"."SR_GLOBAL_SAND_PROPS" ENABLE ROW MOVEMENT;

--END 38374: DAO Layer Changes for Dynamic Global Properties for SAND 

---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------