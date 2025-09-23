/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.utils;

import java.util.List;

import com.ibm.lconn.hpnews.data.model.Person;
import com.ibm.lconn.homepage.dao.model.TabInst;
import com.ibm.lconn.homepage.dao.model.WidgetInst;
import com.ibm.lconn.homepage.model.TabInstance;
import com.ibm.lconn.homepage.model.User;
import com.ibm.lconn.homepage.model.UserUI;
import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.ServiceException;

// TODO extends is to keep Junits running until code is moved to News
public interface ModelConverter {
		
	public UserUI convertFromDaoUI(com.ibm.lconn.homepage.dao.model.UI ui) throws ServiceException;
	
	public Person convertToDaoPerson(User user) throws ServiceException;
	
	public TabInstance convertFromDaoTabInst(TabInst tabInst, String id) throws ServiceException;
	
	public List<TabInstance> convertFromDaoTabInstList(List<TabInst> tabInstList, String personId) throws ServiceException;
	
	public WidgetInstance convertFromDaoWidgetInst(WidgetInst widgetInst) throws ServiceException;
	
	public List<WidgetInstance> convertFromDaoWidgetInstList(List<WidgetInst> widgetInstList) throws ServiceException;
	
	public User convertFromPerson(Person person) throws ServiceException;
	
	public List<User> convertFromPersonList(List<Person> personList) throws ServiceException;

}
