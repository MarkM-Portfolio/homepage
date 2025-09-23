/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2008, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */


package com.ibm.lconn.homepage.test.dao.ibatis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ibm.lconn.homepage.dao.model.MetricStat;
import com.ibm.lconn.homepage.dao.model.TabInst;
import com.ibm.lconn.homepage.dao.model.UI;
import com.ibm.lconn.homepage.dao.model.WidgetInst;
import com.ibm.lconn.hpnews.data.model.IModel;


/**
 * To provide mock obj to insert and update during the CRUD test case
 * @author Lorenzo Notarfonzo
 *
 */
public class MockBuilder extends com.ibm.lconn.hpnews.test.data.dao.impl.ibatis.MockBuilder{
	
	
	public static TabInst getUIPageToInsert() {
		TabInst uiPage = new TabInst();
		uiPage.setTabInstId("99999999-9999-9999-9999-999999999999");
		//uiPage.setTabInstId("22222222-2222-2222-2222-222222222222");
		uiPage.setUiId("33333333-3333-3333-3333-333333333333");
		uiPage.setNumColumns(2);
		uiPage.setTabId("%panel.customx4a43x82aaxb00187218631");
		uiPage.setLastModified(new Date());
		return uiPage;
	}

	public static TabInst getUIPageToUpdate(TabInst baseObjectToUpdate) {
		TabInst uiPage = (TabInst) baseObjectToUpdate;
		uiPage.setNumColumns(3);
		return uiPage;
	}
		
	public static Map<String, Long> getWidgetsUsageAsMap() {
		Map<String, Long> usage = new HashMap<String, Long>();

		usage.put("%Widget Test 1_2", 2L);
		usage.put("%Widget Test 3", 2L);
		usage.put("%Widget Test 1", 0L);
		usage.put("%Widget Test 4", 0L);
		usage.put("%Widget Test 5", 0L);
		usage.put("%Widget Test 6", 1L);
		usage.put("%Widget Test 7", 0L);
		usage.put("%Widget Test 8", 0L);
		usage.put("%Widget Test 9", 0L);
		usage.put("%Widget Test 0", 0L);

		return usage;
	}
	
	public static WidgetInst getWidgetInstToInsert(){
		WidgetInst widgetInst = new WidgetInst();
		widgetInst.setContainer("container");
		widgetInst.setIsFixed(true);
		widgetInst.setLastModified(new Date());
		widgetInst.setLastUpdated(new Date());
		widgetInst.setOrderSequence(5);
		widgetInst.setTabInstanceId("55555555-5555-5555-5555-555555555555");
		widgetInst.setIsToggled(true);
		widgetInst.setWidgetId("11111111-1111-1111-1111-111111111111");
		return widgetInst;
	}
	
	public static WidgetInst getWidgetInstToUpdate(WidgetInst widgetInst){
		WidgetInst widgetInstUpdated = widgetInst;
		widgetInstUpdated.setContainer("containerUpdated");
		widgetInstUpdated.setIsFixed(false);
		widgetInstUpdated.setLastModified(new Date());
		widgetInstUpdated.setLastUpdated(new Date());
		widgetInstUpdated.setOrderSequence(1);
		widgetInstUpdated.setTabInstanceId("55555555-5555-5555-5555-555555555555");
		widgetInstUpdated.setIsToggled(false);
		widgetInstUpdated.setWidgetId("11111111-1111-1111-1111-111111111111");
		return widgetInstUpdated;
	}
	public static UI getUIToInsert(){
		UI ui = new UI();
		ui.setPersonLang("en_EN");
		ui.setPersonId("66666666-6666-6666-6666-666666666666");
		ui.setUiId("66666666-6666-6666-6666-666666666667");
		ui.setLastVisit(new Date());
		ui.setWelcomeMode(true);
		ui.setShowDisabledWidgets(true);
		return ui;
	}
	
	public static UI getUIToUpdate(UI ui){
		ui.setPersonLang("en_US");
		return ui;
	}


	
	public static MetricStat getMetricStatToInsert(){
		MetricStat metric= new MetricStat();
		metric.setAvgTotStat(0l);
		metric.setCountLast1m(10l);
		metric.setCountLast24h(5l);
		metric.setCountLast7d(3l);
		metric.setMetricDesc("stories");
		metric.setMetricType(0);
		metric.setRecordedOn(new Date());

		metric.setResBundleKey("homepage.metric.totals.stories.saved");
		metric.setTopStats("");
		metric.setTotStat(25l);
		return metric;
	
	}	

	public static MetricStat getMetricStatToUpdate(IModel metric){
		MetricStat stat = (MetricStat) metric;
		stat.setCountLast1m(5l);
		Date d = new Date();
		stat.setRecordedOn(d);
		stat.setTopStats("blogs:5;activities:4;wikis:3;files:2;news:1;");
		return stat;
	}

	public static MetricStat getMetricStat(){
		//this type of object will never normall exist, with all the fields set
		MetricStat metric= new MetricStat();
		metric.setAvgTotStat(0l);
		metric.setCountLast1m(10l);
		metric.setCountLast24h(5l);
		metric.setCountLast7d(3l);
		metric.setMetricDesc("stories");
		metric.setMetricType(0);
		metric.setRecordedOn(new Date());
	
		metric.setResBundleKey("homepage.metric.totals.stories.saved");
		metric.setTopStats("blogs:5;activities:4;wikis:3;files:2;news:1;");
		metric.setTotStat(25l);
		return metric;
	
	}	


	
}
