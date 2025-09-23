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

package com.ibm.lconn.homepage.test.services.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.ibm.lconn.hpnews.data.model.Person;
import com.ibm.lconn.homepage.model.User;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.utils.ModelConverter;
import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;

/**
 * 
 * @author John Reddin
 * 
 */
public class ModelConverterImplTest extends SpringContextAwareTestCase {

	private static final String USER_MAIL = "userMail";
	private static final String USER_ID = "userId";
	private static final String USER_EXTERNAL_ID = "userExternalId";
	private static final String USER_DISPLAY_NAME = "userDisplayName";
	private static final int NUM_USERS = 3;
	private ModelConverter modelConverter;

	@Before
	public void init() throws Exception {
		modelConverter = (ModelConverter) getBean("modelConverter");
		Assert.assertNotNull(modelConverter);
		
	}

	@Override
	protected String[] getDBTableNames() {
		return null;
	}
	
	public void setModelConverter(ModelConverter modelConverter) {
		this.modelConverter = modelConverter;
	}



	@Test
	public void testConvertToDaoPerson() {
		User user = createUser();
		try {
			Person person = modelConverter.convertToDaoPerson(user);
			assertEquals(person.getDisplayName(), USER_DISPLAY_NAME);
			assertEquals(person.getExId(), USER_EXTERNAL_ID);
			assertEquals(person.getPersonId(), USER_ID);
			assertEquals(person.getUserMail(), USER_MAIL);

		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testConvertFromPerson() {
		Person person = createPerson();
		try {
			User user = modelConverter.convertFromPerson(person);
			assertEquals(user.getDisplayname(), USER_DISPLAY_NAME);
			assertEquals(user.getExternalId(), USER_EXTERNAL_ID);
			assertEquals(user.getId(), USER_ID);
			assertEquals(user.getMail(), USER_MAIL);

		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testConvertFromPersonList() {
		List<Person> personList = new ArrayList<Person>();
		for (int i = 0; i < NUM_USERS; i++) {
			personList.add(createPerson());
		}

		try {
			List<User> userList = modelConverter
					.convertFromPersonList(personList);
			
			Iterator<User> it = userList.iterator();
			while(it.hasNext()) {
				User currUser = it.next();
				assertEquals(currUser.getDisplayname(), USER_DISPLAY_NAME);
				assertEquals(currUser.getExternalId(), USER_EXTERNAL_ID);
				assertEquals(currUser.getId(), USER_ID);
				assertEquals(currUser.getMail(), USER_MAIL);
			}

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Person createPerson() {
		Person person = new Person();
		person.setDisplayName(USER_DISPLAY_NAME);
		person.setExId(USER_EXTERNAL_ID);
		person.setPersonId(USER_ID);
		person.setUserMail(USER_MAIL);
		return person;
	}

	private User createUser() {
		User user = new User();
		user.setDisplayname(USER_DISPLAY_NAME);
		user.setExternalId(USER_EXTERNAL_ID);
		user.setId(USER_ID);
		user.setMail(USER_MAIL);
		return user;
	}



}
