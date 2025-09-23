/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.utils.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.ibm.lconn.hpnews.data.model.LoginName;
import com.ibm.lconn.hpnews.data.model.Person;
import com.ibm.lconn.hpnews.data.model.PersonWithLoginNames;
import com.ibm.lconn.homepage.dao.model.TabInst;
import com.ibm.lconn.homepage.dao.model.WidgetInst;
import com.ibm.lconn.homepage.model.TabInstance;
import com.ibm.lconn.homepage.model.User;
import com.ibm.lconn.homepage.model.UserUI;
import com.ibm.lconn.homepage.model.UserWithLoginNames;
import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.utils.ModelConverter;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;

public class ModelConverterImpl implements ModelConverter {

	@Autowired
	private ResourceBundle catalogServiceUIResourceBundle;
	@Autowired
	private ResourceBundle serviceResourceBundle;
	
	private void copy(Object src, Object dest) {
		try {
			BeanUtils.copyProperties(dest, src);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public ResourceBundle getCatalogServiceUIResourceBundle() {
		return catalogServiceUIResourceBundle;
	}

	public void setCatalogServiceUIResourceBundle(
			ResourceBundle catalogServiceUIResourceBundle) {
		this.catalogServiceUIResourceBundle = catalogServiceUIResourceBundle;
	}

	public ResourceBundle getServiceResourceBundle() {
		return serviceResourceBundle;
	}

	public void setServiceResourceBundle(ResourceBundle serviceResourceBundle) {
		this.serviceResourceBundle = serviceResourceBundle;
	}

	public UserUI convertFromDaoUI(com.ibm.lconn.homepage.dao.model.UI ui) {
		if (ui == null)
			return null;

		UserUI userUI = new UserUI();
		copy(ui, userUI);
		return userUI;
	}

	public UserWithLoginNames convertFromPersonWithLoginNames(
			PersonWithLoginNames personWithLoginNames, Boolean boardEnabled) {
		UserWithLoginNames userWithLoginNames = new UserWithLoginNames();
		List<LoginName> daoLoginNames = personWithLoginNames.getLoginNames();
		if (daoLoginNames != null && !daoLoginNames.isEmpty()) {
			for (LoginName daoName : daoLoginNames) {
				com.ibm.lconn.hpnews.data.model.LoginName name = new com.ibm.lconn.hpnews.data.model.LoginName();
				copy(daoName, name);
				userWithLoginNames.addLoginName(name);
			}
		}

		copy(personWithLoginNames, userWithLoginNames);
		userWithLoginNames.setId(personWithLoginNames.getPersonId());
		userWithLoginNames.setExternalId(personWithLoginNames.getExId());
		userWithLoginNames
				.setDisplayname(personWithLoginNames.getDisplayName());
		userWithLoginNames.setMail(personWithLoginNames.getUserMail());
		userWithLoginNames.setBoardEnabled(boardEnabled);
		return userWithLoginNames;
	}

	public Person convertToDaoPerson(User user) {
		if (user == null)
			return null;

		Person person = new Person();
		person.setDisplayName(user.getDisplayname());
		person.setExId(user.getExternalId());
		person.setPersonId(user.getId());
		person.setUserMail(user.getMail());
		person.setOrganizationId(user.getOrgId());
		person.setIsExternal(user.getIsExternal());
		// copy(user, person);
		return person;
	}

	public TabInstance convertFromDaoTabInst(TabInst tabInst,
			String tabInstanceId) throws ServiceException {
		if (tabInst == null)
			return null;
		TabInstance tabInstance = new TabInstance();
		copy(tabInst, tabInstance);
		return tabInstance;
	}

	public List<TabInstance> convertFromDaoTabInstList(
			List<TabInst> tabInstList, String personId) throws ServiceException {
		List<TabInstance> tabInstanceList = new ArrayList<TabInstance>();

		if (tabInstList == null)
			return null;

		for (TabInst tabInst : tabInstList) {
			tabInstanceList.add(convertFromDaoTabInst(tabInst, tabInst
					.getTabInstId()));
		}
		return tabInstanceList;
	}

	public WidgetInstance convertFromDaoWidgetInst(WidgetInst widgetInst) {
		if (widgetInst == null)
			return null;

		WidgetInstance widgetInstance = new WidgetInstance();
		widgetInstance.setContainer(widgetInst.getContainer());
		widgetInstance.setFixed(widgetInst.getIsFixed());
		widgetInstance.setIsToggled(widgetInst.getIsToggled());
		widgetInstance.setOrderSequence(widgetInst.getOrderSequence());
		widgetInstance.setTabInstanceId(widgetInst.getTabInstanceId());
		widgetInstance.setWidgetId(widgetInst.getWidgetId());
		widgetInstance.setWidgetInstanceId(widgetInst.getWidgetInstId());
		widgetInstance.setWidgetSetting(widgetInst.getWidgetSetting());
		widgetInstance.setOrganizationId(widgetInst.getOrganizationId());

		return widgetInstance;
	}

	public List<WidgetInstance> convertFromDaoWidgetInstList(
			List<WidgetInst> widgetInstList) {
		if (widgetInstList == null)
			return null;

		List<WidgetInstance> widgetInstanceList = new ArrayList<WidgetInstance>();

		for (WidgetInst widgetInst : widgetInstList) {
			widgetInstanceList.add(convertFromDaoWidgetInst(widgetInst));
		}
		return widgetInstanceList;
	}

	public User convertFromPerson(Person person) {
		if (person == null)
			return null;

		User user = new User();
		user.setDisplayname(person.getDisplayName());
		user.setExternalId(person.getExId());
		user.setId(person.getPersonId());
		user.setMail(person.getUserMail());
		user.setOrgId(person.getOrganizationId());
		user.setIsExternal(person.getIsExternal());
		return user;
	}

	public List<User> convertFromPersonList(List<Person> personList) {
		if (personList == null)
			return null;

		List<User> userList = new ArrayList<User>();

		for (Person person : personList) {
			userList.add(convertFromPerson(person));
		}
		return userList;
	}
}
