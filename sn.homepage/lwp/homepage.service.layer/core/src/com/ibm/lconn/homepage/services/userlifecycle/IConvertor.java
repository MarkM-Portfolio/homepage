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

package com.ibm.lconn.homepage.services.userlifecycle;

/**
 * Generic interface implemented by class offering some "converting" capability
 * ie. converting HP Person Bean to UserLifeCycle SPI Person Bean.
 * 
 * 
 * From News - Need to put that person common stuff (DAO) in sn.infra after 3.0
 * 
 * @author vincent
 * 
 */
public interface IConvertor<T, U> {

	public U convert(T objectToConvert);

	public T reverseConvert(U objectToConvert);

}
