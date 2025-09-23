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
import com.ibm.lconn.hpnews.service.waltz.IWaltzSyncService;

public class WaltzSyncServiceMockFactory {
	
	public static boolean expectUpdateUserExternalIDByLoginName(IWaltzSyncService waltzSyncService,Person person) throws Exception{
		EasyMock.expect(waltzSyncService.updatePersonExternalIDByLoginName(person.getUserMail(), person)).andReturn(true);
		return true;
	}
	
	public static boolean expectUpdateUserExternalIDByEmail(IWaltzSyncService waltzSyncService,Person person) throws Exception{
		EasyMock.expect(waltzSyncService.updatePersonExternalIDByEmail(person.getUserMail(), person)).andReturn(true);
		return true;
	}
	
	public static boolean expectPopulateUserLoginNames(IWaltzSyncService waltzSyncService,Person person) throws Exception{
		EasyMock.expect(waltzSyncService.populatePersonLoginNames(person)).andReturn(true);
		return true;
	}
}
