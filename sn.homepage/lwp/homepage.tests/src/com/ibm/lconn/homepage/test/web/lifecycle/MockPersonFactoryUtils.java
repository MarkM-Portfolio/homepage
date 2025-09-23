/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2022                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.web.lifecycle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.ibm.lconn.hpnews.data.model.LoginName;
import com.ibm.lconn.hpnews.data.model.Person;
import com.ibm.lconn.hpnews.data.model.PersonWithLoginNames;
import com.ibm.lconn.lifecycle.data.IPerson;
import com.ibm.lconn.lifecycle.data.impl.PersonImpl;

/**
 * 
 * @author vincent
 * 
 */
public class MockPersonFactoryUtils {

	public static final String EXTERNAL_ID = "ext_1234";
	public static final String INTERNAL_ID = "int_1234";
	public static final String EMAIL = "vincent.burckhardt@ie.ibm.com";
	public static final String DISPLAY_NAME = "Vincent Burckhardt";

	// state as represented in news/hp
	public static final Person.State NEWS_STATE = Person.State.ACTIVE;

	public static final IPerson.State SPI_STATE = IPerson.State.ACTIVE;

	/**
	 * Helper class to create mock (HP/News) Person bean
	 * 
	 * @param extId
	 * @param internalId
	 * @param email
	 * @param loginNames
	 * @return
	 */
	public static PersonWithLoginNames createNewsPerson(String extId,
			String internalId, String email, Person.State state,
			String displayName, LoginName... loginNames) {
		PersonWithLoginNames person = new PersonWithLoginNames();

		person.setExId(extId);
		person.setPersonId(internalId);
		person.setUserMail(email);
		person.setLoginNames(Arrays.asList(loginNames));
		person.setDisplayName(displayName);
		person.setState(state);

		return person;
	}

	public static PersonWithLoginNames createNewsPerson(String extId,
			String internalId, String email, Person.State state,
			String displayName, Collection<LoginName> loginNames) {

		return createNewsPerson(extId, internalId, email, state, displayName,
				loginNames.toArray(new LoginName[0]));
	}

	/**
	 * Helper class to create mock (SPI) Person bean
	 * 
	 * @param extId
	 * @param internalId
	 * @param email
	 * @param state
	 * @param displayName
	 * @param loginNames
	 * @return
	 */
	public static IPerson createSpiPerson(String extId, String internalId,
			String email, IPerson.State state, String displayName,
			String... loginNames) {
		PersonImpl person = new PersonImpl();

		person.setExtId(extId);
		person.setInternalId(internalId);
		person.setEmail(email);
		person.setLogins((Arrays.asList(loginNames)));
		person.setDisplayName(displayName);
		person.setState(state);

		return person;
	}

	public static List<LoginName> createLoginNames(String internalId,
			String... loginNames) {

		List<LoginName> loginNamesBean = new ArrayList<LoginName>();

		if (loginNames != null) {
			for (String loginName : loginNames) {
				LoginName loginNameBean = new LoginName();
				loginNameBean.setId(internalId);
				loginNameBean.setLoginName(loginName);

				loginNamesBean.add(loginNameBean);
			}
		}

		return loginNamesBean;
	}
}
