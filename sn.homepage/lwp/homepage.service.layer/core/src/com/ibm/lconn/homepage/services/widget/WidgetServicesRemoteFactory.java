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
package com.ibm.lconn.homepage.services.widget;

import com.ibm.lconn.core.services.cre.remote.widget.WidgetCatalogServiceRemote;
import com.ibm.lconn.core.services.cre.remote.widget.WidgetLayoutServiceRemote;
import com.ibm.lconn.core.services.cre.remote.widget.WidgetServiceRemoteFactory;
import com.ibm.lconn.core.tkrproxysvc.service.LCRemoteServiceFactory;

/**
 * @author Michael Ahern (michael.ahern@ie.ibm.com)
 *
 */
public class WidgetServicesRemoteFactory {

	static {
		LCRemoteServiceFactory.setComponentName("homepage");
	}
	
	/**
	 * Get access to catalog services singleton
	 * @return
	 */
	public static final WidgetCatalogServiceRemote getWidgetCatalogService() {
		return WidgetServiceRemoteFactory.getWidgetCatalogService();
	}
	
	/**
	 * Get access to the layout services singleton
	 * @return
	 */
	public static final WidgetLayoutServiceRemote getWidgetLayoutService() {
		return WidgetServiceRemoteFactory.getWidgetLayoutService();
	}
	
}
