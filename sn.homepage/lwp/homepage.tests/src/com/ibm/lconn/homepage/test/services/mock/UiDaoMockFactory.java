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

package com.ibm.lconn.homepage.test.services.mock;

import org.easymock.EasyMock;

import com.ibm.lconn.homepage.dao.interfaces.IUIDao;
import com.ibm.lconn.homepage.dao.model.UI;
import com.ibm.lconn.homepage.services.ServiceException;

public class UiDaoMockFactory {

	public static UI expectInsert(IUIDao uiDao) throws ServiceException{
		UI ui=new UI();
		EasyMock.expect(uiDao.insert((UI)EasyMock.anyObject())).andReturn(ui.getUiId());
		return ui;
	}
}
