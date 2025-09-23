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
--- Update COMMUNITY_INTERNAL_ONLY to have 1 as default value

ALTER TABLE HOMEPAGE.PERSON ADD DEFAULT ('1') FOR COMMUNITY_INTERNAL_ONLY;
COMMIT;

 --- Update existing value for COMMUNITY_INTERNAL_ONLY to be 1 where MEMBER_TYPE is 1

BEGIN TRANSACTION
GO


UPDATE HOMEPAGE.PERSON
SET    COMMUNITY_INTERNAL_ONLY = '1'
WHERE 	MEMBER_TYPE = 1 AND (COMMUNITY_INTERNAL_ONLY != '1' OR COMMUNITY_INTERNAL_ONLY IS NULL);


COMMIT;
GO

BEGIN TRANSACTION
GO