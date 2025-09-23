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
-- 148440: PC1 : Following a contact doesn't work
----------------------------------------------------------------------------------

update HOMEPAGE.NR_RESOURCE r set (r.RESOURCE_ID, r.ORGANIZATION_ID) = 
  (select
  r.RESOURCE_ID, p.organization_id from homepage.PERSON p where r.CONTAINER_ID = p.PERSON_ID
  and r.organization_id = '00000000-0000-0000-0000-000000000000'
  and r.SOURCE = 'profiles')
  where exists (select * from homepage.PERSON p where r.CONTAINER_ID = p.PERSON_ID
  and r.organization_id = '00000000-0000-0000-0000-000000000000'
  and r.SOURCE = 'profiles')@
	
COMMIT@
