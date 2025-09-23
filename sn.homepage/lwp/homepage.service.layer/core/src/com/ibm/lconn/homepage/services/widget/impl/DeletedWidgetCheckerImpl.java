/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2012, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
package com.ibm.lconn.homepage.services.widget.impl;

import java.util.Collections;
import java.util.List;

import com.ibm.lconn.core.services.cre.remote.widget.WidgetCatalogServiceRemote;
import com.ibm.lconn.core.services.cre.remote.widget.WidgetServiceException;
import com.ibm.lconn.homepage.dao.interfaces.IDeletedWidgetChecker;
import com.ibm.lconn.homepage.services.impl.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Michael Ahern (michael.ahern@ie.ibm.com)
 *
 */
public class DeletedWidgetCheckerImpl extends AbstractService implements IDeletedWidgetChecker {

	@Autowired
	private WidgetCatalogServiceRemote widgetCatalogServices;
	
	public DeletedWidgetCheckerImpl() {}
	
	@Override
	protected void internalAfterPropertiesSet() throws Exception {}

	/* (non-Javadoc)
	 * @see com.ibm.lconn.homepage.dao.interfaces.IDeletedWidgetChecker#getDeletedList(java.util.Collection)
	 */
	@Override
	public List<String> getDeletedList(List<String> widgetIds) throws WidgetServiceException {
		if (widgetIds.size() == 0) {
			return Collections.<String>emptyList();
		}
		return widgetCatalogServices.getDeletedWidgetsByIds(widgetIds);
	}

	/**
	 * @param widgetCatalogServices the widgetCatalogServices to set
	 */
	public void setWidgetCatalogServices(WidgetCatalogServiceRemote widgetCatalogServices) {
		this.widgetCatalogServices = widgetCatalogServices;
	}

}
