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
import junit.framework.TestCase;

import org.junit.Test;

import com.ibm.lconn.hpnews.data.model.LoginName;
import com.ibm.lconn.hpnews.data.model.PersonWithLoginNames;
import com.ibm.lconn.homepage.services.userlifecycle.impl.PersonBeanConvertor;
import com.ibm.lconn.lifecycle.data.IPerson;

/**
 * 
 * @author vincent
 * 
 */
public class PersonBeanConvertorTest extends TestCase {

	private com.ibm.lconn.homepage.services.userlifecycle.impl.PersonBeanConvertor classUnderTest;

	@Override
	protected void setUp() throws Exception {
		super.setUp();

		classUnderTest = new PersonBeanConvertor();
	}

	@Test
	public void testConvert() {
		PersonWithLoginNames person = createNewsPerson(EXTERNAL_ID,
				INTERNAL_ID, EMAIL, NEWS_STATE, DISPLAY_NAME, createLoginNames(
						INTERNAL_ID, "login1", "login2"));

		IPerson actualSpiPerson = classUnderTest.convert(person);

		assertEquals(EXTERNAL_ID, actualSpiPerson.getExtId());
		assertEquals(INTERNAL_ID, actualSpiPerson.getInternalId());
		assertEquals(DISPLAY_NAME, actualSpiPerson.getDisplayName());
		assertEquals(EMAIL, actualSpiPerson.getEmail());
		assertEquals(SPI_STATE, actualSpiPerson.getState());

		assertEquals(2, actualSpiPerson.getLogins().size());
		assertTrue((actualSpiPerson.getLogins().contains("login1")));
		assertTrue((actualSpiPerson.getLogins().contains("login2")));
	}
	
	public void testReverseConvert() {
		IPerson person = createSpiPerson(EXTERNAL_ID, INTERNAL_ID, EMAIL,
				SPI_STATE, DISPLAY_NAME, "login1", "login2");

		PersonWithLoginNames actualNewsPerson = (PersonWithLoginNames) classUnderTest
				.reverseConvert(person);

		assertEquals(EXTERNAL_ID, actualNewsPerson.getExId());
		assertEquals(INTERNAL_ID, actualNewsPerson.getPersonId());
		assertEquals(DISPLAY_NAME, actualNewsPerson.getDisplayName());
		assertEquals(EMAIL, actualNewsPerson.getUserMail());
		assertEquals(NEWS_STATE, actualNewsPerson.getState());

		assertEquals(2, actualNewsPerson.getLoginNames().size());
		String intId1 = ((LoginName) actualNewsPerson.getLoginNames().get(0)).getPrimaryKey();
		assertEquals(INTERNAL_ID, intId1);
	}

}
