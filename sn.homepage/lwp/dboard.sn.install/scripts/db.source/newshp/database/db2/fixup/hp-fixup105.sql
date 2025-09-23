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

--71915: Dups records on HOMEPAGE.HP_TAB_INST. Customers affected (also w3). We can put a fix in NEXT and investigate further
--1 _panel.updatex4a43x82aaxb00187218631
--2 _panel.widgetx4a43x82aaxb00187218631
--3 _panel.customx4a43x82aaxb00187218631

-- 1
delete from HOMEPAGE.HP_WIDGET_INST where TAB_INST_ID in (
	SELECT HP_TAB_INST.TAB_INST_ID
	FROM (
		select  MAX(HP_TAB_INST.TAB_INST_ID) MAX_TAB_INST_ID, HP_TAB_INST.UI_ID
		from HOMEPAGE.HP_TAB_INST HP_TAB_INST
		where TAB_ID = '_panel.updatex4a43x82aaxb00187218631'
		GROUP by UI_ID
		HAVING COUNT(*) > 1
		) T,
		HOMEPAGE.HP_TAB_INST HP_TAB_INST
	WHERE HP_TAB_INST.UI_ID = T.UI_ID AND HP_TAB_INST.TAB_INST_ID < T.MAX_TAB_INST_ID
);
COMMIT;

-- 1
delete from HOMEPAGE.HP_TAB_INST where TAB_INST_ID in (
	SELECT HP_TAB_INST.TAB_INST_ID
	FROM (
		select  MAX(HP_TAB_INST.TAB_INST_ID) MAX_TAB_INST_ID, HP_TAB_INST.UI_ID
		from HOMEPAGE.HP_TAB_INST HP_TAB_INST
		where TAB_ID = '_panel.updatex4a43x82aaxb00187218631'
		GROUP by UI_ID
		HAVING COUNT(*) > 1
		) T,
		HOMEPAGE.HP_TAB_INST HP_TAB_INST
	WHERE HP_TAB_INST.UI_ID = T.UI_ID AND HP_TAB_INST.TAB_INST_ID < T.MAX_TAB_INST_ID
);
COMMIT;

-- 2
delete from HOMEPAGE.HP_WIDGET_INST where TAB_INST_ID in (
	SELECT HP_TAB_INST.TAB_INST_ID
	FROM (
		select  MAX(HP_TAB_INST.TAB_INST_ID) MAX_TAB_INST_ID, HP_TAB_INST.UI_ID
		from HOMEPAGE.HP_TAB_INST HP_TAB_INST
		where TAB_ID = '_panel.widgetx4a43x82aaxb00187218631'
		GROUP by UI_ID
		HAVING COUNT(*) > 1
		) T,
		HOMEPAGE.HP_TAB_INST HP_TAB_INST
	WHERE HP_TAB_INST.UI_ID = T.UI_ID AND HP_TAB_INST.TAB_INST_ID < T.MAX_TAB_INST_ID
);
COMMIT;

-- 2
delete from HOMEPAGE.HP_TAB_INST where TAB_INST_ID in (
	SELECT HP_TAB_INST.TAB_INST_ID
	FROM (
		select  MAX(HP_TAB_INST.TAB_INST_ID) MAX_TAB_INST_ID, HP_TAB_INST.UI_ID
		from HOMEPAGE.HP_TAB_INST HP_TAB_INST
		where TAB_ID = '_panel.widgetx4a43x82aaxb00187218631'
		GROUP by UI_ID
		HAVING COUNT(*) > 1
		) T,
		HOMEPAGE.HP_TAB_INST HP_TAB_INST
	WHERE HP_TAB_INST.UI_ID = T.UI_ID AND HP_TAB_INST.TAB_INST_ID < T.MAX_TAB_INST_ID
);
COMMIT;

-- 3
delete from HOMEPAGE.HP_WIDGET_INST where TAB_INST_ID in (
	SELECT HP_TAB_INST.TAB_INST_ID
	FROM (
		select  MAX(HP_TAB_INST.TAB_INST_ID) MAX_TAB_INST_ID, HP_TAB_INST.UI_ID
		from HOMEPAGE.HP_TAB_INST HP_TAB_INST
		where TAB_ID = '_panel.customx4a43x82aaxb00187218631'
		GROUP by UI_ID
		HAVING COUNT(*) > 1
		) T,
		HOMEPAGE.HP_TAB_INST HP_TAB_INST
	WHERE HP_TAB_INST.UI_ID = T.UI_ID AND HP_TAB_INST.TAB_INST_ID < T.MAX_TAB_INST_ID
);
COMMIT;

-- 3
delete from HOMEPAGE.HP_TAB_INST where TAB_INST_ID in (
	SELECT HP_TAB_INST.TAB_INST_ID
	FROM (
		select  MAX(HP_TAB_INST.TAB_INST_ID) MAX_TAB_INST_ID, HP_TAB_INST.UI_ID
		from HOMEPAGE.HP_TAB_INST HP_TAB_INST
		where TAB_ID = '_panel.customx4a43x82aaxb00187218631'
		GROUP by UI_ID
		HAVING COUNT(*) > 1
		) T,
		HOMEPAGE.HP_TAB_INST HP_TAB_INST
	WHERE HP_TAB_INST.UI_ID = T.UI_ID AND HP_TAB_INST.TAB_INST_ID < T.MAX_TAB_INST_ID
);
COMMIT;

CREATE UNIQUE INDEX HOMEPAGE.HP_TAB_INST_UNQ 
	ON HOMEPAGE.HP_TAB_INST (TAB_ID, UI_ID);	
COMMIT;	  
  