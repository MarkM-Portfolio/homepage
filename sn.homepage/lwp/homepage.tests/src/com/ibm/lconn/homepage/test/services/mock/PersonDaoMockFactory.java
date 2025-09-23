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

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;

import com.ibm.connections.directory.services.data.DSObject;
import com.ibm.lconn.hpnews.data.dao.interfaces.IPersonDao;
import com.ibm.lconn.hpnews.data.model.LoginName;
import com.ibm.lconn.hpnews.data.model.Person;
import com.ibm.lconn.hpnews.data.model.PersonWithLoginNames;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;
import com.ibm.lconn.homepage.model.User;

public class PersonDaoMockFactory {

	public static PersonWithLoginNames expectGetPersonWithLoginNamesByExtIdWithPerson(IPersonDao personDao, User user){
		PersonWithLoginNames personWithLoginNames = createPersonWithLoginNames(user);
		
		EasyMock.expect(personDao.getPersonWithLoginNamesByExtId(user.getExternalId())).andReturn(personWithLoginNames);
		return personWithLoginNames;
	}

	public static PersonWithLoginNames expectGetPersonWithLoginNamesByExtIdWithDsObject(IPersonDao personDao, DSObject dsObject){

		PersonWithLoginNames personWithLoginNames=new PersonWithLoginNames();
		personWithLoginNames.setDisplayName(dsObject.get_name());
		personWithLoginNames.setPersonId(dsObject.get_id());
		personWithLoginNames.setExId(dsObject.get_id());
		personWithLoginNames.setUserMail(dsObject.get_email());
		setupLoginNames(dsObject.get_email(), personWithLoginNames);

		EasyMock.expect(personDao.getPersonWithLoginNamesByExtId(dsObject.get_id() )).andReturn(personWithLoginNames);
		return personWithLoginNames;
	}
	
	public static PersonWithLoginNames expectGetPersonWithLoginNamesByExtIdWithoutPerson(IPersonDao personDao, String externalId){
		EasyMock.expect(personDao.getPersonWithLoginNamesByExtId(externalId)).andReturn(null);
		return null;
	}

	public static PersonWithLoginNames expectGetPersonWithLoginNamesByMailWithoutPerson(IPersonDao personDao, String email){
		EasyMock.expect(personDao.getPersonWithLoginNamesByMail(email)).andReturn(null);
		return null;
	}

	public static PersonWithLoginNames expectGetPersonWithLoginNamesByLoginName(IPersonDao personDao, User user){
		PersonWithLoginNames personWithLoginNames = createPersonWithLoginNames(user);
		
		EasyMock.expect(personDao.getPersonWithLoginNamesByLoginName(user.getMail())).andReturn(personWithLoginNames);
		return personWithLoginNames;
	}
	
	public static Person expectGetByEmailWithPerson(IPersonDao personDao, User user){
		Person person = createPersonFromUser(user);
		EasyMock.expect(personDao.getByEmail(user.getMail())).andReturn(person);
		return person;
	}

	public static Person expectGetByEmailWithoutPerson(IPersonDao personDao, User user){
		EasyMock.expect(personDao.getByEmail(user.getMail())).andReturn(null);
		return null;
	}

	public static PersonWithLoginNames expectInsertPerson(IPersonDao personDao, DSObject dsObject){
		PersonWithLoginNames personWithLoginNames=new PersonWithLoginNames();
		personWithLoginNames.setDisplayName(dsObject.get_name());
		personWithLoginNames.setPersonId(dsObject.get_id());
		personWithLoginNames.setExId(dsObject.get_id());
		personWithLoginNames.setUserMail(dsObject.get_email());
		setupLoginNames(dsObject.get_email(), personWithLoginNames);
		
		EasyMock.expect(personDao.insert((PersonWithLoginNames)EasyMock.anyObject())).andReturn(personWithLoginNames.getExId());
		return personWithLoginNames;
	}

	public static PersonWithLoginNames expectUpdatePerson(IPersonDao personDao, DSObject dsObject){
		PersonWithLoginNames personWithLoginNames=new PersonWithLoginNames();
		personWithLoginNames.setDisplayName(dsObject.get_name());
		personWithLoginNames.setPersonId(dsObject.get_id());
		personWithLoginNames.setExId(dsObject.get_id());
		personWithLoginNames.setUserMail(dsObject.get_email());
		setupLoginNames(dsObject.get_email(), personWithLoginNames);
		
		personDao.update((PersonWithLoginNames)EasyMock.anyObject());
		return personWithLoginNames;
	}

	public static void expectUpdateExternalID(IPersonDao personDao){		
		personDao.updateExternalID((Person)EasyMock.anyObject());
	}

	public static List<Person> expectSelectAll(IPersonDao personDao, User... users){		
		List<Person> homePageMemberList = new ArrayList<Person>();
		for(User user:users){
			homePageMemberList.add(createPersonFromUser(user));			
		}
		EasyMock.expect(personDao.selectAll()).andReturn(homePageMemberList);
		
		return homePageMemberList;
	}

	private static PersonWithLoginNames createPersonWithLoginNames(User user) {
		PersonWithLoginNames personWithLoginNames=new PersonWithLoginNames();
		personWithLoginNames.setDisplayName(user.getDisplayname());
		personWithLoginNames.setPersonId(user.getId());
		personWithLoginNames.setExId(user.getExternalId());
		personWithLoginNames.setUserMail(user.getMail());
		setupLoginNames(user.getMail(), personWithLoginNames);
		return personWithLoginNames;
	}

	private static void setupLoginNames(String loginNameString,
			PersonWithLoginNames personWithLoginNames) {
		List<LoginName>loginNames=new ArrayList<LoginName>();
		LoginName loginName=new LoginName();
		loginName.setLoginName(loginNameString);
		loginNames.add(loginName);
		personWithLoginNames.setLoginNames(loginNames);
	}

	private static Person createPersonFromUser(User user) {
		Person person = new Person();
		person.setDisplayName(user.getDisplayname());
		person.setPersonId(user.getId());
		person.setExId(user.getExternalId());
		person.setUserMail(user.getMail());
		return person;
	}
}
