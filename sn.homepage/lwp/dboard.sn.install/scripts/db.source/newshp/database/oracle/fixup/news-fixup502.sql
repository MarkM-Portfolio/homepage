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

----------------------------------------------------------------------------------
-- 147873: SC: Network Followers view NOT displaying followers. IC20 delivery
----------------------------------------------------------------------------------

DELETE FROM HOMEPAGE.NR_FOLLOWS WHERE RESOURCE_ID  IN (
	SELECT RESOURCE_ID
	FROM HOMEPAGE.PERSON P,
		HOMEPAGE.NR_RESOURCE R
	WHERE R.CONTAINER_ID = P.PERSON_ID AND P.EXID LIKE 'INVALID%'
);

COMMIT;

DELETE FROM HOMEPAGE.NR_RESOURCE WHERE CONTAINER_ID  IN (
	SELECT PERSON_ID
	FROM HOMEPAGE.PERSON P
	WHERE P.EXID LIKE 'INVALID%'
);

COMMIT;