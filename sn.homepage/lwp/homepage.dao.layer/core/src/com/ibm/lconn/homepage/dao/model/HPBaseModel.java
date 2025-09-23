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

package com.ibm.lconn.homepage.dao.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.ibm.lconn.hpnews.data.model.IModel;
import com.ibm.lconn.hpnews.data.model.NRBaseModel;

/**
 * Base class. All the model object that extends NRBasaModel (News Repository) can work over the right db schema .
 * 
 * This value it is used here to substitute the $schema$ paramete in the SQLMap config 	
 * 
 * IE: 
 * 
 * <select id="select-SOURCE" parameterClass="Source" resultMap="SourceResultMap" >
 *		<![CDATA[
 *			SELECT * from $schema$.NR_SOURCE
 *		]]>
 *	</select>
 *
 * @author Lorenzo Notarfonzo
 * 
 */
public abstract class HPBaseModel extends NRBaseModel implements IModel {
	
}
