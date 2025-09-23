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

package com.ibm.lconn.homepage.test.web;

import static org.easymock.EasyMock.createMock;

import org.junit.Before;

import com.ibm.lconn.homepage.services.IConfigurationService;
import com.ibm.lconn.homepage.services.IHomepageActionRequiredService;
import com.ibm.lconn.homepage.services.IUserServices;
import com.ibm.lconn.homepage.services.widget.IWidgetServices;
import com.ibm.lconn.homepage.web.utils.IUrlNormalizer;

public class AbstractWidgetControllerTest extends AbstractLoggedInUserTest {

	protected IUserServices mockUserServices;
	protected IWidgetServices mockWidgetServices;
	protected IConfigurationService mockConfigurationService;
	protected IUrlNormalizer mockUrlNormalizer;
	protected IHomepageActionRequiredService mockHomepageActionRequiredService;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		setMockUserServices(createMock(IUserServices.class));
		setMockWidgetServices(createMock(IWidgetServices.class));
		setMockConfigurationService(createMock(IConfigurationService.class));
		setMockUrlNormalizer(createMock(IUrlNormalizer.class));
		setMockHomepageActionRequiredService(createMock(IHomepageActionRequiredService.class));
	}

	public IConfigurationService getMockConfigurationService() {
		return mockConfigurationService;
	}

	public void setMockConfigurationService(
			IConfigurationService mockConfigurationService) {
		this.mockConfigurationService = mockConfigurationService;
	}

	public IUserServices getMockUserServices() {
		return mockUserServices;
	}

	public void setMockUserServices(IUserServices userServices) {
		this.mockUserServices = userServices;
	}

	public IWidgetServices getMockWidgetServices() {
		return mockWidgetServices;
	}

	public void setMockWidgetServices(IWidgetServices widgetServices) {
		this.mockWidgetServices = widgetServices;
	}

	public IUrlNormalizer getMockUrlNormalizer() {
		return mockUrlNormalizer;
	}

	public void setMockUrlNormalizer(IUrlNormalizer mockUrlNormalizer) {
		this.mockUrlNormalizer = mockUrlNormalizer;
	}

	public IHomepageActionRequiredService getMockHomepageActionRequiredService() {
		return mockHomepageActionRequiredService;
	}

	public void setMockHomepageActionRequiredService(
			IHomepageActionRequiredService mockHomepageActionRequiredService) {
		this.mockHomepageActionRequiredService = mockHomepageActionRequiredService;
	}

}
