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

--START 74517: Create script that creates DB table that hold document type labels

----------------------------------------
-- SR_ECM_DOCUMENT_TYPE_LABELS
----------------------------------------


CREATE TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_PROPS(
        PROPERTIES_ID NVARCHAR(36) NOT NULL,
        DOCUMENT_TYPE_ID  NVARCHAR(256) NOT NULL,
        LOCALE NVARCHAR(10) NOT NULL,
        PROPERTIES NVARCHAR(MAX) NOT NULL
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_PROPS
    ADD CONSTRAINT PK_PROPERTIES_ID PRIMARY KEY (PROPERTIES_ID);
GO


--END 74517: Create script that creates DB table that hold document type labels

--START Defect 80084: Rename IS_CURRENT Field to STATUS.

DROP INDEX SR_FILESCONTENT_IS_CURRENT_IDX ON HOMEPAGE.SR_FILESCONTENT;

EXEC sp_rename 'HOMEPAGE.SR_FILESCONTENT.IS_CURRENT','STATUS'; 
GO

CREATE INDEX SR_FILESCONTENT_STATUS_IDX 
	ON HOMEPAGE.SR_FILESCONTENT(STATUS);

--END Defect 80084: Rename IS_CURRENT Field to STATUS.


---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
