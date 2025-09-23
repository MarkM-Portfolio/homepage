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

package com.ibm.lconn.homepage.services.utils;

import static java.util.logging.Level.FINER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;


public class WidgetUtils {
	
	private static String CLASS_NAME = WidgetUtils.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	public static List<Widget> sortWidgetsByColNum(List<Widget> lists, String columnNo) {
		
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "sortWidgetsByColNum", new Object[] { lists, columnNo });

		List<Widget> sameColWidgets = new ArrayList<Widget>();

		for (int i = 0; i < lists.size(); i++) {
			Widget widget = (Widget) lists.get(i);
			if (widget.getContainerId().equals(columnNo)) {
				sameColWidgets.add(widget);
			}
		}

		Collections.sort(sameColWidgets, new Comparator<Widget>() {

			public int compare(Widget o1, Widget o2) {
				return o1.getOrderSeq() - o2.getOrderSeq();
			}

		});

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "sortWidgetsByColNum", sameColWidgets);

		return sameColWidgets;
	}
}
