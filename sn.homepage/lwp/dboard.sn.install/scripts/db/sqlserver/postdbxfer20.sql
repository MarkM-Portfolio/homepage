-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2001, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- 5724_S68                                              
USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO

ALTER TABLE HOMEPAGE.USER_WIDGET_PREF
    ADD CONSTRAINT FK_USER_ID FOREIGN KEY (USER_ID)
	REFERENCES HOMEPAGE.PERSON (USER_ID)
GO

ALTER TABLE HOMEPAGE.USER_WIDGET_PREF
    ADD CONSTRAINT FK_WIDGET_ID FOREIGN KEY (WIDGET_ID)
	REFERENCES HOMEPAGE.WIDGET (WIDGET_ID)
GO

ALTER TABLE HOMEPAGE.PREREQ 
	ADD CONSTRAINT FK_PREREQ_WIDGET FOREIGN KEY (WIDGET_ID)
	REFERENCES HOMEPAGE.WIDGET (WIDGET_ID)
	ON DELETE CASCADE
GO

CREATE UNIQUE INDEX USERID_WIDGET_UNIQUE
	ON HOMEPAGE.USER_WIDGET_PREF (USER_ID ASC, WIDGET_ID ASC)
GO

COMMIT;