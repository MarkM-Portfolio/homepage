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

package com.ibm.lconn.homepage.services.userlifecycle.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ibm.lconn.hpnews.data.model.LoginName;
import com.ibm.lconn.hpnews.data.model.Person;
import com.ibm.lconn.hpnews.data.model.PersonWithLoginNames;
import com.ibm.lconn.homepage.services.userlifecycle.IConvertor;
import com.ibm.lconn.lifecycle.data.IPerson;
import com.ibm.lconn.lifecycle.data.impl.PersonImpl;

/**
 * Conversion capability from a News Model Person Bean to User Life Cycle Person
 * Bean (and reverse)
 * 
 * From News - Need to put that person common stuff (DAO) in sn.infra after 3.0
 * 
 * @author vincent
 * 
 */
public class PersonBeanConvertor implements IConvertor<Person, IPerson> {

	/**
	 * Obtain a User Life Cycle Person Bean from a News Model Person Bean
	 */
	public IPerson convert(Person newsPerson) {

		PersonImpl result = null;

		if (newsPerson != null) {
			result = new PersonImpl();

			result.setDisplayName(newsPerson.getDisplayName());
			result.setEmail(newsPerson.getUserMail());
			result.setExtId(newsPerson.getExId());
			result.setInternalId(newsPerson.getPersonId());

			switch (newsPerson.getState()) {
			case ACTIVE:
				result.setState(IPerson.State.ACTIVE);
				break;
			case INACTIVE:
				result.setState(IPerson.State.INACTIVE);
				break;
			}

			result.setLogins(null);
		}

		return result;
	}

	public IPerson convert(PersonWithLoginNames newsPerson) {
		PersonImpl result = null;

		if (newsPerson != null) {

			result = (PersonImpl) convert((Person) newsPerson);

			List<LoginName> loginNamesBeans = newsPerson.getLoginNames();

			if (loginNamesBeans != null) {
				Set<String> loginStrings = new HashSet<String>();

				for (LoginName loginName : loginNamesBeans) {
					loginStrings.add(loginName.getLoginName());
				}

				result.setLogins(loginStrings);
			}
		}

		return result;
	}

	/**
	 * Obtain a News Model Person Bean from a User Life Cycle Person Bean
	 */
	public Person reverseConvert(IPerson spiPerson) {

		PersonWithLoginNames result = null;

		if (spiPerson != null) {

			result = new PersonWithLoginNames();

			result.setDisplayName(spiPerson.getDisplayName());
			result.setExId(spiPerson.getExtId());
			result.setPersonId((String) spiPerson.getInternalId());
			result.setUserMail(spiPerson.getEmail());

			switch (spiPerson.getState()) {
			case ACTIVE:
				result.setState(Person.State.ACTIVE);
				break;
			case INACTIVE:
				result.setState(Person.State.INACTIVE);
				break;
			}

			Collection<String> logins = spiPerson.getLogins();

			List<LoginName> loginNamesBeans = null;

			if (logins != null) {
				loginNamesBeans = new ArrayList<LoginName>();

				for (String login : logins) {
					LoginName loginName = new LoginName();
					loginName.setId((String) spiPerson.getInternalId());
					loginName.setLoginName(login);

					loginNamesBeans.add(loginName);
				}
			}

			result.setLoginNames(loginNamesBeans);
		}

		return result;
	}
}
