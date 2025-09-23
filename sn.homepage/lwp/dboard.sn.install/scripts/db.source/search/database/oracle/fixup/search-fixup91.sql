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

--START 36202: Modify the indexing and text extraction processes in accordance with designs in Search CDD

DELETE FROM HOMEPAGE.SR_FILESCONTENT;

DELETE FROM HOMEPAGE.SR_FACET_DOCS;

DELETE FROM HOMEPAGE.SR_INDEX_DOCS;

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD FS_LOCAL_PATH VARCHAR2(256) NOT NULL;

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD PROCESSOR VARCHAR2(36);
	
ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD PROCESSOR_STATE BLOB;
	
ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD CONTENT_LOCATION VARCHAR2(256) NOT NULL;

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD IS_READY NUMBER(5,0) DEFAULT 0 NOT NULL ;


	
--END 36202: Modify the indexing and text extraction processes in accordance with designs in Search CDD  
---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------