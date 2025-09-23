/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
package com.ibm.lconn.homepage.test.services.mock;

import org.easymock.EasyMock;

import com.ibm.lconn.core.services.cre.remote.widget.WidgetCatalogServiceRemote;
import com.ibm.lconn.core.services.cre.remote.widget.WidgetLayoutServiceRemote;
import com.ibm.lconn.core.services.cre.remote.widget.WidgetServiceException;
import com.ibm.lconn.core.services.cre.remote.widget.model.Tab;
import com.ibm.lconn.core.services.cre.remote.widget.model.TabType;

/**
 * Provides mock versions of widget services for JUnits
 * @author Michael Ahern (michael.ahern@ie.ibm.com)
 */
public class MockWidgetServicesRemote {
	private static final WidgetCatalogServiceRemote widgetCatalogService = EasyMock.createMock(WidgetCatalogServiceRemote.class); 
	private static final WidgetLayoutServiceRemote widgetLayoutService = EasyMock.createMock(WidgetLayoutServiceRemote.class);

	/**
	 * @return the widgetCatalogService
	 */
	public static final WidgetCatalogServiceRemote getWidgetCatalogService() {
		return widgetCatalogService;
	}

	/**
	 * @return the widgetLayoutService
	 */
	public static final WidgetLayoutServiceRemote getWidgetLayoutService() {
		return widgetLayoutService;
	}

	public static void expectTabCustom(int multiple) throws WidgetServiceException {
		Tab rTab = new Tab();
		rTab.setTabId("%panel.customx4a43x82aaxb00187218631");
		rTab.setNumColumns(2);
		EasyMock.expect(widgetLayoutService.getTabByName(TabType.CUSTOM.getDBValue())).andReturn(rTab).times(multiple);
	}

	public static void expectTabUpdate(int multiple) throws WidgetServiceException {
		Tab rTab = new Tab();
		rTab.setTabId("%panel.updatex4a43x82aaxb00187218631");
		rTab.setNumColumns(2);
		EasyMock.expect(widgetLayoutService.getTabByName(TabType.UPDATE.getDBValue())).andReturn(rTab).times(multiple);
	}
	
	public static void expectTabWidget(int multiple) throws WidgetServiceException {
		Tab rTab = new Tab();
		rTab.setTabId("%panel.widgetx4a43x82aaxb00187218631");
		rTab.setNumColumns(2);
		EasyMock.expect(widgetLayoutService.getTabByName(TabType.WIDGET.getDBValue())).andReturn(rTab).times(multiple);
	}
	
	/**
	 * Reset the mock objects
	 */
	public static void reset() {
		EasyMock.reset(widgetCatalogService, widgetLayoutService);
	}
}
