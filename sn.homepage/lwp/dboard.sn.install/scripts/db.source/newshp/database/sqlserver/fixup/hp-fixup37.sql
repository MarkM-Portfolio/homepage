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

----------------------------------------------------------------------
-- 1) Insert the panel tab page widget
----------------------------------------------------------------------
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_panel.getstartx4a43x82aaxb001872186' , '%panel.getstart' , 1 , 0, 1);

----------------------------------------------------------------------
-- 2) LAST_NOTIFY_VISIT TIMESTAMP
----------------------------------------------------------------------
ALTER TABLE HOMEPAGE.HP_UI
	ADD LAST_NOTIFY_VISIT DATETIME;
GO	

UPDATE HOMEPAGE.HP_UI SET LAST_NOTIFY_VISIT = CURRENT_TIMESTAMP;