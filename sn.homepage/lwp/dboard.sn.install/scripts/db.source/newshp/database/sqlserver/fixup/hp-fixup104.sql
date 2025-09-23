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

---------------------------------------------------------------------------------
------------------------ START HP FIXUP 104 -------------------------------------
---------------------------------------------------------------------------------

--69726: [HP/News104 Schema change] Fix registration URL for SocialMail gadget
UPDATE HOMEPAGE.WIDGET
	SET  	WIDGET_URL = '{connectionsmail}/gadgets/inbox.xml', WIDGET_SECURE_URL = '{connectionsmail}/gadgets/inbox.xml'
	WHERE 	WIDGET_ID = '405a4f26-fa08-4cef-a995-7d90fbe2634f';    
	
---------------------------------------------------------------------------------
------------------------ END HP FIXUP 104 ---------------------------------------
---------------------------------------------------------------------------------
  
