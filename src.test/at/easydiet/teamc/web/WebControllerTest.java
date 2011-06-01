/**
 * This File is part of EasyDiet
 * Created on: 27.05.2011
 * Created by: Michael
 * File: WebControllerTest.java
 */
package at.easydiet.teamc.web;

import junit.framework.TestCase;

import org.junit.Test;

import at.easydiet.teamc.controller.LoginController;
import at.easydiet.teamc.controller.usecase.CreateNutritionProtocolController;
import at.easydiet.teamc.exception.LoginFailedException;
import at.easydiet.teamc.model.data.PatientData;

/**
 * 
 * @author Michael
 */
public class WebControllerTest extends TestCase {

	// class variables
	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(WebControllerTest.class);

	// instance variables
	private WebController _webCtrl;
	private CreateNutritionProtocolController _protocolCtrl;

	{
		_webCtrl = new WebController();
		_protocolCtrl = new CreateNutritionProtocolController();
	}

	/**
	 * Test method for
	 * {@link at.easydiet.teamc.web.WebController#loginPatient()}.
	 * @throws LoginFailedException
	 */
	@Test
	public void testLoginPatient() {
		String result = "Thomas Geiger";

		// correct login
		PatientData activePatient;
		try {
			activePatient = LoginController.getInstance().loginPatient(
					"tgeiger", "tgeiger");
			assertEquals(
					activePatient.getForename() + " "
							+ activePatient.getLastname(), result);
		} catch (LoginFailedException e) {
			assertTrue(false);
		}

		// incorrect login
		PatientData fail = null;
		try {
			fail = LoginController.getInstance()
					.loginPatient("tgeiger", "fail");
			assertTrue(true);
		} catch (LoginFailedException e) {
			assertNull(fail);
		}
	}

	/**
	 * Test method for
	 * {@link at.easydiet.teamc.web.WebController#createNewProtocol()}.
	 */
	@Test
	public void testCreateNewProtocol() {
		assertNotNull(_protocolCtrl);
		assertNotNull(_protocolCtrl.getActualProtocol());
		assertNotNull(_protocolCtrl.getMealCodes());
		assertNotNull(_protocolCtrl.getPlanTypes());
		assertNotNull(_protocolCtrl.getRecipes());
	}
}
