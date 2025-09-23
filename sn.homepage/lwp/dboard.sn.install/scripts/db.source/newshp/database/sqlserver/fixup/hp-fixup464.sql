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

------------------------------------------------------------------------------------------
-- 109134: PERSON - HP_UI - perf. improvement - increase PCTFREE to 40 - Implement a reverse index for:   HOMEPAGE.HP_UI, HOMEPAGE.HP_UI_PERSONID_LANG
------------------------------------------------------------------------------------------  
DROP INDEX HP_UI ON HOMEPAGE.HP_UI;
GO
       
CREATE INDEX HP_UI_R
	ON HOMEPAGE.HP_UI (LAST_VISIT);
GO	
 



