/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.web.utils;

import java.util.List;
import java.util.Map;

import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.core.services.cre.remote.widget.model.WidgetCategory;
import com.ibm.lconn.homepage.web.WebException;

public interface IUrlNormalizer {

	public void normalizeWidget(Widget widget) throws WebException;

	public void translateUrls(Widget widget, boolean isSecure)
			throws WebException;

	public void translateUrls(List<Widget> widgets, boolean isSecure)
			throws WebException;

	public void translateUrls(Map<String, WidgetCategory> widgets,
			boolean isSecure) throws WebException;
}
