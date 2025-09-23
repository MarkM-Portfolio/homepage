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

package com.ibm.lconn.homepage.dao.interfaces;

import java.util.List;

import com.ibm.lconn.homepage.dao.model.TabInst;
import com.ibm.lconn.hpnews.data.dao.interfaces.ICrud;


/**
 * 
 * @author Lorenzo Notarfonzo
 *
 */
public interface ITabInstDao extends ICrud {

	public TabInst getPersonUIPageByDefaultName(String personId, String defaultName);
	
	public List<TabInst> getPersonUIPages(String personId);

}