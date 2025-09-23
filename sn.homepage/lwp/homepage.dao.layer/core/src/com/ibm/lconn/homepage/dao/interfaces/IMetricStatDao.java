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

package com.ibm.lconn.homepage.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.ibm.lconn.hpnews.data.model.IModel;
import com.ibm.lconn.hpnews.data.dao.interfaces.ICrud;
/**
 * 
 * @author lorenzo
 *
 */
public interface IMetricStatDao extends ICrud {

	public List<IModel> selectByDate(Date now);
}
