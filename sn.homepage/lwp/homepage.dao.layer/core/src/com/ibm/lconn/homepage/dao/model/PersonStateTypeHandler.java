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

package com.ibm.lconn.homepage.dao.model;

import static java.util.logging.Level.FINER;

import java.sql.SQLException;
import java.util.logging.Logger;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;
import com.ibm.lconn.hpnews.data.model.Person;

/**
 * Perform conversion to smallint of Person.State enum for ibatis
 * 
 * @author vincent
 * 
 */
public class PersonStateTypeHandler implements TypeHandlerCallback {

	private static String CLASS_NAME = PersonStateTypeHandler.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	public Object getResult(ResultGetter getter) throws SQLException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "getResult", getter);

		int dbValue = getter.getInt();

		Person.State stateInstance = Person.State.getInstance(dbValue);

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "getResult", stateInstance);

		return stateInstance;
	}

	public void setParameter(ParameterSetter setter, Object param)
			throws SQLException {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "setParameter", new Object[] { setter,
					param });

		Person.State state = (Person.State) param;

		setter.setInt(state.getDbValue());

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "setParameter");

	}

	public Object valueOf(String s) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "valueOf", s);

		// just return the string as not needed (we don't store the string
		// representation)

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "valueOf", s);

		return s;
	}

}
