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

package com.ibm.lconn.homepage.services.userlifecycle.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.lconn.core.web.util.admin.UlcSyncMBeanRegistrar;
import com.ibm.lconn.hpnews.data.dao.interfaces.ILoginNameDao;
import com.ibm.lconn.hpnews.data.dao.interfaces.IPersonDao;
import com.ibm.lconn.hpnews.data.model.LoginName;
import com.ibm.lconn.hpnews.data.model.Person;
import com.ibm.lconn.hpnews.data.model.PersonWithLoginNames;
import com.ibm.lconn.hpnews.data.model.Person.State;
import com.ibm.lconn.lifecycle.callback.IUserCallback;
import com.ibm.lconn.lifecycle.data.IPerson;
import com.ibm.lconn.lifecycle.spi.IUserLifeCycleSpi;
import com.ibm.websphere.validation.base.bindings.applicationbnd.applicationbndvalidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Implementation of user life cycle handler for News.
 * 
 * @author vincent
 * 
 */
public class UserLifeCycleHandlerSpiImpl implements IUserLifeCycleSpi,
		InitializingBean {

	private static String CLASS_NAME = UserLifeCycleHandlerSpiImpl.class
			.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

    @Autowired
	@Qualifier("HPNEWS-DATABASE:personDao")
	private IPersonDao personDao;
    @Autowired
	@Qualifier("HPNEWS-DATABASE:loginNameDao")
	private ILoginNameDao loginDao;
    @Autowired
	@Qualifier("LifeCycle:personConvertor")
	private PersonBeanConvertor personBeanConvertor;
	private int numPersonsToProcess = 100; // default = 100, spring override for unit tests

	@Transactional
	public IPerson getPersonByEmail(String email) {
		if (logger.isLoggable(Level.FINER))
			logger.entering(CLASS_NAME, "getPersonByEmail", email);

		// get from db
		PersonWithLoginNames newsPerson = personDao
				.getPersonWithLoginNamesByMail(email);

		// convert to spi person
		IPerson person = personBeanConvertor.convert(newsPerson);

		if (logger.isLoggable(Level.FINER))
			logger.exiting(CLASS_NAME, "getPersonByEmail", person);

		return person;
	}

	@Transactional
	public IPerson getPersonByExternalId(String extId) {
		if (logger.isLoggable(Level.FINER))
			logger.entering(CLASS_NAME, "getPersonByExternalId", extId);

		// get from db
		PersonWithLoginNames newsPerson = personDao
				.getPersonWithLoginNamesByExtId(extId);

		// convert to spi person
		IPerson person = personBeanConvertor.convert(newsPerson);

		if (logger.isLoggable(Level.FINER))
			logger.exiting(CLASS_NAME, "getPersonByExternalId", person);

		return person;
	}

	@Transactional
	public IPerson getPersonByInternalId(String internalId) {
		if (logger.isLoggable(Level.FINER))
			logger.entering(CLASS_NAME, "getPersonByInternalId", internalId);

		// get from db
		String extId = personDao.getPersonExternalId(internalId);
		PersonWithLoginNames newsPerson = personDao
				.getPersonWithLoginNamesByExtId(extId);

		// convert to spi person
		IPerson person = personBeanConvertor.convert(newsPerson);

		if (logger.isLoggable(Level.FINER))
			logger.exiting(CLASS_NAME, "getPersonByInternalId", person);

		return person;
	}

	@Transactional
	public IPerson getPersonByLogin(String login) {
		if (logger.isLoggable(Level.FINER))
			logger.entering(CLASS_NAME, "getPersonByLogin", login);

		// get from db
		PersonWithLoginNames newsPerson = personDao
				.getPersonWithLoginNamesByLoginName(login);

		// convert to spi person
		IPerson person = personBeanConvertor.convert(newsPerson);

		if (logger.isLoggable(Level.FINER))
			logger.exiting(CLASS_NAME, "getPersonByLogin", person);

		return person;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void updatePerson(IPerson person) {
		if (logger.isLoggable(Level.FINER))
			logger.entering(CLASS_NAME, "updatePerson", person);

		PersonWithLoginNames newsUpdatePerson = (PersonWithLoginNames) personBeanConvertor
				.reverseConvert(person);

		// get the dbPerson prior to performing the update as we need the
		// current state to invoke listeners
		Person dbPerson = (Person) personDao.getByPK((String) person
				.getInternalId());
		State dbState = dbPerson.getState();

		// TODO: assuming that updatePerson is only called when update necessary
		// might need to check the values in DB first here to see whether an
		// update is really needed (performance)

		// update person in DB
		personDao.update(newsUpdatePerson);

		// remove all existing logins for person and add new ones - safer and
		// performing better than using update as the list of logins might
		// change
		List<LoginName> dbLoginNames = loginDao
				.findLoginNameByPersonId(newsUpdatePerson.getPersonId());

		if (dbLoginNames != null) {
			for (LoginName loginName : dbLoginNames) {
				loginDao.removeLoginName(loginName);
			}
		}

		// now insert new list of login names
		List<LoginName> updatedLoginName = newsUpdatePerson.getLoginNames();

		if (updatedLoginName != null) {
			for (LoginName loginName : updatedLoginName) {
				loginDao.insert(loginName);
			}
		}

		if (logger.isLoggable(Level.FINER))
			logger.exiting(CLASS_NAME, "updatePerson");
	}

	// set propagation to never to prevent long running transactions - callers
	// should not start a transaction before calling this method
	@SuppressWarnings("unchecked")
	@Transactional(propagation = Propagation.NEVER)
	public void updateAllUsers(IUserCallback callback) {
		if (logger.isLoggable(Level.FINER))
			logger.entering(CLASS_NAME, "updateAllUsers", loginDao);

		// Used for syncAllMembersByExtID admin task which is no longer supplied
		// by the Homepage wsadmin features. The function is required to satisfy
		// the interface used.

		if (logger.isLoggable(Level.FINER))
			logger.entering(CLASS_NAME, "updateAllUsers", loginDao);
	}

	/**
	 * Register this object to the SPI registry. This is done at startup when
	 * spring is initiliazed.
	 */
	public void afterPropertiesSet() throws Exception {
		if (logger.isLoggable(Level.FINER))
			logger.entering(CLASS_NAME, "afterPropertiesSet");

		UlcSyncMBeanRegistrar.registerUlcSyncMBean(this, "Homepage");

		if (logger.isLoggable(Level.FINER))
			logger.exiting(CLASS_NAME, "afterPropertiesSet");
	}

	public IPersonDao getPersonDao() {
		if (logger.isLoggable(Level.FINER))
			logger.entering(CLASS_NAME, "getPersonDao");

		if (logger.isLoggable(Level.FINER))
			logger.exiting(CLASS_NAME, "getPersonDao", personDao);

		return personDao;
	}

	public void setPersonDao(IPersonDao personDao) {
		if (logger.isLoggable(Level.FINER))
			logger.entering(CLASS_NAME, "setPersonDao", personDao);

		this.personDao = personDao;

		if (logger.isLoggable(Level.FINER))
			logger.exiting(CLASS_NAME, "setPersonDao");
	}

	public ILoginNameDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(ILoginNameDao loginDao) {
		if (logger.isLoggable(Level.FINER))
			logger.entering(CLASS_NAME, "setLoginDao", loginDao);

		this.loginDao = loginDao;

		if (logger.isLoggable(Level.FINER))
			logger.exiting(CLASS_NAME, "setLoginDao");
	}

	public PersonBeanConvertor getPersonBeanConvertor() {
		return personBeanConvertor;
	}

	public void setPersonBeanConvertor(PersonBeanConvertor personBeanConvertor) {
		this.personBeanConvertor = personBeanConvertor;
	}

	public void setNumPersonsToProcess(int numPersonsToProcess) {
		this.numPersonsToProcess = numPersonsToProcess;
	}

}
