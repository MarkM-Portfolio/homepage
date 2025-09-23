-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2009, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- PUTTING BACK THE INTEG CONSTRAINTS

ALTER TABLE HOMEPAGE.LOGINNAME
	ADD CONSTRAINT "FK_PERSON_ID" FOREIGN KEY ("PERSON_ID")
	REFERENCES HOMEPAGE.PERSON("PERSON_ID");
	
ALTER TABLE HOMEPAGE.USER_WIDGET_PREF
    ADD CONSTRAINT "FK_USER_ID" FOREIGN KEY ("USER_ID")
	REFERENCES HOMEPAGE.PERSON("PERSON_ID");

ALTER TABLE HOMEPAGE.USER_WIDGET_PREF
    ADD CONSTRAINT "FK_WIDGET_ID" FOREIGN KEY ("WIDGET_ID")
	REFERENCES HOMEPAGE.WIDGET ("WIDGET_ID");

ALTER TABLE HOMEPAGE.PREREQ 
	ADD CONSTRAINT "FK_PREREQ_WIDGET" FOREIGN KEY ("WIDGET_ID")
	REFERENCES HOMEPAGE.WIDGET ("WIDGET_ID")
	ON DELETE CASCADE;

CREATE UNIQUE INDEX HOMEPAGE.USERID_WIDGET_UNIQUE
	ON HOMEPAGE.USER_WIDGET_PREF(USER_ID ASC, WIDGET_ID ASC);	

COMMIT;

QUIT;
