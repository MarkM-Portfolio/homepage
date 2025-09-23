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

package com.ibm.lconn.homepage.test.services.userlifecycle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.ibm.lconn.hpnews.data.model.LoginName;
import com.ibm.lconn.hpnews.data.model.Person;
import com.ibm.lconn.hpnews.data.model.PersonWithLoginNames;
import com.ibm.lconn.homepage.services.userlifecycle.impl.PersonBeanConvertor;
import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;
import com.ibm.lconn.lifecycle.data.IPerson;
import com.ibm.lconn.lifecycle.data.impl.PersonImpl;

/**
 * 
 * @author John Reddin
 * 
 */
public class PersonBeanConvertorTest extends SpringContextAwareTestCase {
	
	private static String CLASSNAME = PersonBeanConvertorTest.class.getName();
	
	@Before
	public void init() throws Exception {
		personBeanConvertor = (PersonBeanConvertor)getBean("personBeanConvertor");
		Assert.assertNotNull(personBeanConvertor);
		refreshDB(CLASSNAME, false );		
	}

	@Override
	public String[] getDBTableNames() {
		String table1 = "PERSON";
		String table2 = "LOGINNAME";
		String[] filenames = {"MT_ORGANIZATION",table1, table2};
		return filenames;
	}

	private static final String LOGIN_NAME2 = "loginname2";
	private static final String LOGIN_NAME_ID2 = "LoginNameID2";
	private static final String LOGIN_NAME1 = "loginname1";
	private static final String LOGIN_NAME_ID1 = "LoginNameID1";
	private static final String PERSON_ID = "personId";
	private static final String PERSON_EXT_ID = "personExtId";
	private static final String PERSON_USER_MAIL = "personUserMail";
	private static final String PERSON_DISPLAY_NAME = "personDisplayName";
	private PersonBeanConvertor personBeanConvertor;

	public void setModelConverter(PersonBeanConvertor personBeanConvertor) {
		this.personBeanConvertor = personBeanConvertor;
	}


	@Test
	public void testConvertHPModelPersonToLifeCyclePerson() {

		Person person = createPerson();

		IPerson lifeCyclePerson = personBeanConvertor.convert(person);
		assertEquals(lifeCyclePerson.getDisplayName(), PERSON_DISPLAY_NAME);
		assertEquals(lifeCyclePerson.getEmail(), PERSON_USER_MAIL);
		assertEquals(lifeCyclePerson.getExtId(), PERSON_EXT_ID);
		assertEquals(lifeCyclePerson.getInternalId(), PERSON_ID);
		assertEquals(lifeCyclePerson.getState(), IPerson.State.ACTIVE);

		person.setState(Person.State.INACTIVE);
		lifeCyclePerson = personBeanConvertor.convert(person);
		assertEquals(lifeCyclePerson.getState(), IPerson.State.INACTIVE);
	}

	@Test
	public void testConvertPersonWithLoginNamesToLifeCyclePerson() {

		PersonWithLoginNames personWithLoginNames = createPersonWithLoginNames();

		IPerson iPerson = personBeanConvertor.convert(personWithLoginNames);
		assertEquals(iPerson.getDisplayName(), PERSON_DISPLAY_NAME);
		assertEquals(iPerson.getEmail(), PERSON_USER_MAIL);
		assertEquals(iPerson.getExtId(), PERSON_EXT_ID);
		assertEquals(iPerson.getInternalId(), PERSON_ID);
		assertEquals(iPerson.getState(), IPerson.State.ACTIVE);
		assertEquals(iPerson.getLogins().size(), 2);

		Iterator<String> it = iPerson.getLogins().iterator();
		List<String> loginNames = new ArrayList<String>();
		loginNames.add(it.next());
		loginNames.add(it.next());
		
		assertTrue(loginNames.contains(LOGIN_NAME1));
		assertTrue(loginNames.contains(LOGIN_NAME2));
	}

	@Test
	public void testReverseConvert() {
		PersonWithLoginNames personWithLoginNames = createPersonWithLoginNames();
		IPerson iPerson = personBeanConvertor.convert(personWithLoginNames);

		PersonWithLoginNames reversedPerson = (PersonWithLoginNames) personBeanConvertor
				.reverseConvert(iPerson);
		assertEquals(reversedPerson.getDisplayName(), PERSON_DISPLAY_NAME);
		assertEquals(reversedPerson.getUserMail(), PERSON_USER_MAIL);
		assertEquals(reversedPerson.getExId(), PERSON_EXT_ID);
		assertEquals(reversedPerson.getPersonId(), PERSON_ID);
		assertEquals(reversedPerson.getState(), Person.State.ACTIVE);
		assertEquals(reversedPerson.getLoginNames().size(), 2);

		Iterator<LoginName> it = reversedPerson.getLoginNames().iterator();
		List<String> loginNames = new ArrayList<String>();
		loginNames.add(it.next().getLoginName());
		loginNames.add(it.next().getLoginName());
		
		assertTrue(loginNames.contains(LOGIN_NAME1));
		assertTrue(loginNames.contains(LOGIN_NAME2));
	
		((PersonImpl) iPerson).setState(IPerson.State.INACTIVE);
		reversedPerson = (PersonWithLoginNames) personBeanConvertor
				.reverseConvert(iPerson);
		assertEquals(reversedPerson.getState(), Person.State.INACTIVE);
	}

	private Person createPerson() {
		Person person = new Person();
		person.setDisplayName(PERSON_DISPLAY_NAME);
		person.setUserMail(PERSON_USER_MAIL);
		person.setExId(PERSON_EXT_ID);
		person.setPersonId(PERSON_ID);
		person.setState(Person.State.ACTIVE);
		return person;
	}

	private PersonWithLoginNames createPersonWithLoginNames() {
		PersonWithLoginNames personWithLoginNames = new PersonWithLoginNames();
		personWithLoginNames.setDisplayName(PERSON_DISPLAY_NAME);
		personWithLoginNames.setUserMail(PERSON_USER_MAIL);
		personWithLoginNames.setExId(PERSON_EXT_ID);
		personWithLoginNames.setPersonId(PERSON_ID);
		personWithLoginNames.setState(Person.State.ACTIVE);

		List<LoginName> loginNames = createLoginNames();
		personWithLoginNames.setLoginNames(loginNames);
		return personWithLoginNames;
	}

	private List<LoginName> createLoginNames() {
		List<LoginName> loginNames = new ArrayList<LoginName>();
		LoginName loginName1 = new LoginName();
		loginName1.setId(LOGIN_NAME_ID1);
		loginName1.setLoginName(LOGIN_NAME1);
		loginNames.add(loginName1);

		LoginName loginName2 = new LoginName();
		loginName2.setId(LOGIN_NAME_ID2);
		loginName2.setLoginName(LOGIN_NAME2);
		loginNames.add(loginName2);
		return loginNames;
	}



}
