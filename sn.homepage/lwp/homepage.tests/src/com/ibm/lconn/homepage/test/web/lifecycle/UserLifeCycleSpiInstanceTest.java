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

import static com.ibm.lconn.homepage.test.web.lifecycle.MockPersonFactoryUtils.DISPLAY_NAME;
import static com.ibm.lconn.homepage.test.web.lifecycle.MockPersonFactoryUtils.EMAIL;
import static com.ibm.lconn.homepage.test.web.lifecycle.MockPersonFactoryUtils.EXTERNAL_ID;
import static com.ibm.lconn.homepage.test.web.lifecycle.MockPersonFactoryUtils.INTERNAL_ID;
import static com.ibm.lconn.homepage.test.web.lifecycle.MockPersonFactoryUtils.NEWS_STATE;
import static com.ibm.lconn.homepage.test.web.lifecycle.MockPersonFactoryUtils.SPI_STATE;
import static com.ibm.lconn.homepage.test.web.lifecycle.MockPersonFactoryUtils.createLoginNames;
import static com.ibm.lconn.homepage.test.web.lifecycle.MockPersonFactoryUtils.createNewsPerson;
import static com.ibm.lconn.homepage.test.web.lifecycle.MockPersonFactoryUtils.createSpiPerson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import junit.framework.TestCase;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.easymock.EasyMock;
import org.easymock.LogicalOperator;
import org.junit.Test;

import com.ibm.lconn.hpnews.data.dao.interfaces.ILoginNameDao;
import com.ibm.lconn.hpnews.data.dao.interfaces.IPersonDao;
import com.ibm.lconn.hpnews.data.model.LoginName;
import com.ibm.lconn.hpnews.data.model.PersonWithLoginNames;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;
import com.ibm.lconn.homepage.services.userlifecycle.impl.PersonBeanConvertor;
import com.ibm.lconn.homepage.services.userlifecycle.impl.UserLifeCycleHandlerSpiImpl;
import com.ibm.lconn.lifecycle.callback.IUserCallback;
import com.ibm.lconn.lifecycle.data.IPerson;

/**
 * 
 * @author vincent
 * 
 */
public class UserLifeCycleSpiInstanceTest extends TestCase {

	private UserLifeCycleHandlerSpiImpl classUnderTest;

	private IPersonDao mockPersonDao;
	private ILoginNameDao mockLoginNameDao;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		classUnderTest = new UserLifeCycleHandlerSpiImpl();

		mockPersonDao = EasyMock.createMock(IPersonDao.class);
		mockLoginNameDao = EasyMock.createMock(ILoginNameDao.class);

		// testing against the real convertor, not mock object
		PersonBeanConvertor convertor = new PersonBeanConvertor();

		classUnderTest.setPersonDao(mockPersonDao);
		classUnderTest.setLoginDao(mockLoginNameDao);
		classUnderTest.setPersonBeanConvertor(convertor);
	}

	@Test
	public void testGetPersonByEmail() {
		// set expectations
		PersonWithLoginNames expectedPerson = createNewsPerson(EXTERNAL_ID,
				INTERNAL_ID, EMAIL, NEWS_STATE, DISPLAY_NAME);

		EasyMock.expect(mockPersonDao.getPersonWithLoginNamesByMail(EMAIL ))
				.andReturn(expectedPerson);

		EasyMock.replay(mockPersonDao);

		// test starts here
		IPerson actualPerson = classUnderTest.getPersonByEmail(EMAIL);

		assertEquals(DISPLAY_NAME, actualPerson.getDisplayName());
		assertEquals(EXTERNAL_ID, actualPerson.getExtId());
		assertEquals(INTERNAL_ID, actualPerson.getInternalId());
		assertEquals(IPerson.State.ACTIVE, actualPerson.getState());
		assertEquals(EMAIL, actualPerson.getEmail());

		EasyMock.verify(mockPersonDao);
	}

	@Test
	public void testGetPersonByInternalId() {
		// set expectations
		PersonWithLoginNames expectedPerson = createNewsPerson(EXTERNAL_ID,
				INTERNAL_ID, EMAIL, NEWS_STATE, DISPLAY_NAME);

		EasyMock.expect(mockPersonDao.getPersonExternalId(INTERNAL_ID))
				.andReturn(EXTERNAL_ID);

		EasyMock.expect(
				mockPersonDao.getPersonWithLoginNamesByExtId( (EXTERNAL_ID)))
				.andReturn(expectedPerson);

		EasyMock.replay(mockPersonDao);

		// test starts here
		IPerson actualPerson = classUnderTest
				.getPersonByInternalId(INTERNAL_ID);

		assertEquals(DISPLAY_NAME, actualPerson.getDisplayName());
		assertEquals(EXTERNAL_ID, actualPerson.getExtId());
		assertEquals(INTERNAL_ID, actualPerson.getInternalId());
		assertEquals(IPerson.State.ACTIVE, actualPerson.getState());
		assertEquals(EMAIL, actualPerson.getEmail());

		EasyMock.verify(mockPersonDao);
	}

	@Test
	public void testGetPersonByExternalId() {
		// set expectations
		PersonWithLoginNames expectedPerson = createNewsPerson(EXTERNAL_ID,
				INTERNAL_ID, EMAIL, NEWS_STATE, DISPLAY_NAME);

		EasyMock.expect(
				mockPersonDao.getPersonWithLoginNamesByExtId(EXTERNAL_ID))
				.andReturn(expectedPerson);

		EasyMock.replay(mockPersonDao);

		// test starts here
		IPerson actualPerson = classUnderTest
				.getPersonByExternalId(EXTERNAL_ID);

		assertEquals(DISPLAY_NAME, actualPerson.getDisplayName());
		assertEquals(EXTERNAL_ID, actualPerson.getExtId());
		assertEquals(INTERNAL_ID, actualPerson.getInternalId());
		assertEquals(IPerson.State.ACTIVE, actualPerson.getState());
		assertEquals(EMAIL, actualPerson.getEmail());

		EasyMock.verify(mockPersonDao);
	}

	@Test
	public void testGetPersonByLogin() {
		// set expectations

		Collection<LoginName> logins = createLoginNames(INTERNAL_ID, "login1");

		PersonWithLoginNames expectedPerson = createNewsPerson(EXTERNAL_ID,
				INTERNAL_ID, EMAIL, NEWS_STATE, DISPLAY_NAME, logins);

		EasyMock.expect(
				mockPersonDao.getPersonWithLoginNamesByLoginName("login1"))
				.andReturn(expectedPerson);

		EasyMock.replay(mockPersonDao);

		// test starts here
		IPerson actualPerson = classUnderTest.getPersonByLogin("login1");

		assertEquals(DISPLAY_NAME, actualPerson.getDisplayName());
		assertEquals(EXTERNAL_ID, actualPerson.getExtId());
		assertEquals(INTERNAL_ID, actualPerson.getInternalId());
		assertEquals(IPerson.State.ACTIVE, actualPerson.getState());
		assertEquals(EMAIL, actualPerson.getEmail());

		EasyMock.verify(mockPersonDao);
	}


//	@Test
//	public void testUpdatePerson() {
//
//		IPerson expectedSpiPerson = createSpiPerson(EXTERNAL_ID, INTERNAL_ID,
//				EMAIL, SPI_STATE, DISPLAY_NAME, "login1");
//
//		PersonWithLoginNames expectedNewsPerson = createNewsPerson(EXTERNAL_ID,
//				INTERNAL_ID, EMAIL, NEWS_STATE, DISPLAY_NAME, createLoginNames(
//						INTERNAL_ID, "login1"));
//
//		mockPersonDao.update(EasyMock.cmp(expectedNewsPerson,
//				new newsPersonCmp(), LogicalOperator.EQUAL));
//
//		LoginName expectedLoginName = new LoginName();
//		expectedLoginName.setId(INTERNAL_ID);
//		expectedLoginName.setLoginName("login1");
//
//		EasyMock.expect(
//				mockLoginNameDao.insert(EasyMock.cmp(expectedLoginName,
//						new Comparator<LoginName>() {
//
//							public int compare(LoginName o1, LoginName o2) {
//								return EqualsBuilder.reflectionEquals(o1, o2) ? 0
//										: 1;
//							}
//
//						}, LogicalOperator.EQUAL))).andReturn(null);
//
//		EasyMock.expect(mockLoginNameDao.findLoginNameByPersonId(INTERNAL_ID))
//				.andReturn(null);
//		
//		EasyMock.replay(mockPersonDao);
//		EasyMock.replay(mockLoginNameDao);
//
//		// test starts here
//		classUnderTest.updatePerson(expectedSpiPerson);
//
//		EasyMock.verify(mockPersonDao);
//		EasyMock.verify(mockLoginNameDao);
//	}

	/**
	 * Only valid for strict comparisons (equals)
	 * 
	 * @author vincent
	 * 
	 */
	private class newsPersonCmp implements Comparator<PersonWithLoginNames> {

		public int compare(PersonWithLoginNames o1, PersonWithLoginNames o2) {

			int result;

			boolean beansEquals = EqualsBuilder.reflectionEquals(o1, o2,
					new String[] { "loginNames" });

			// just test size for now... not good but that's sufficient for unit
			// testing given other priorities
			boolean loginsEquals = o1.getLoginNames().size() == o2
					.getLoginNames().size();

			if (beansEquals && loginsEquals) {
				result = 0;
			} else {
				result = 1;
			}

			return result;
		}
	}

	/**
	 * Only valid for strict comparisons (equals)
	 * 
	 * @author vincent
	 * 
	 */
	private class spiPersonCmp implements Comparator<IPerson> {

		public int compare(IPerson o1, IPerson o2) {

			int result;

			boolean beansEquals = EqualsBuilder.reflectionEquals(o1, o2,
					new String[] { "logins" });

			// just test size for now... not good but that's sufficient for unit
			// testing given other priorities

			boolean loginsEquals = true;

			if ((o1.getLogins() != null) && (o2.getLogins() != null)) {
				loginsEquals = o1.getLogins().size() == o2.getLogins().size();
			} else {
				loginsEquals = ((o1.getLogins() != null) || (o2.getLogins() != null));
			}

			if (beansEquals && loginsEquals) {
				result = 0;
			} else {
				result = 1;
			}

			return result;
		}
	}
}
