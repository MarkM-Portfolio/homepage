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

import org.easymock.EasyMock;

import com.ibm.lconn.hpnews.data.model.Person;
import com.ibm.lconn.homepage.model.UserWithLoginNames;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.utils.ModelConverter;

public class ConverterMockFactory {

	public static UserWithLoginNames expectConvertFromPerson(ModelConverter modelConverter, Person person) throws ServiceException{
		UserWithLoginNames userWithLoginNames=new UserWithLoginNames();
		userWithLoginNames.setDisplayname(person.getDisplayName());
		userWithLoginNames.setId(person.getPersonId());
		userWithLoginNames.setExternalId(person.getExId());
		userWithLoginNames.setMail(person.getUserMail());
		userWithLoginNames.setBoardEnabled(true);
		EasyMock.expect(modelConverter.convertFromPerson(person)).andReturn(userWithLoginNames);
		return userWithLoginNames;
	}
}
