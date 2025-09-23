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

import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;

import com.ibm.lconn.homepage.dao.interfaces.IDeletedWidgetChecker;

/**
 * 
 * @author Michael Ahern (michael.ahern@ie.ibm.com)
 */
public class MockDeletedWidgetChecker {
	
	private static IDeletedWidgetChecker mockChecker;
	
	public static void reset() {
		mockChecker = EasyMock.createMock(IDeletedWidgetChecker.class);
	}
	
	static {
		reset();
	}
	
	public static IDeletedWidgetChecker getMockChecker() {
		return new IDeletedWidgetChecker() {
			@Override
			public List<String> getDeletedList(List<String> widgetIds)
					throws Exception {
				//System.out.println("+++++ CALL: " + widgetIds + " / CLASS: " + widgetIds.getClass());
				
				List<String> res = mockChecker.getDeletedList(widgetIds);
				
				// since will never return 'null'; a null return means that the mock object is not being exercised
				// to avoid test errors, return an empty set instead
				if (res == null) {
					res = Collections.emptyList();
					reset(); // need reset to avoid exceptions
				}
					
				return res;
			}			
		};
	}
	
	/**
	 * Reset the mock object and return
	 * @return
	 */
	public static IDeletedWidgetChecker getResetRealMockObject() {
		reset();
		return mockChecker;
	}
	
}
