/**
 * This File is part of EasyDiet
 * Created on: 25.05.2011
 * Created by: Michael
 * File: WebController.java
 */
package at.easydiet.teamc.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import at.easydiet.teamc.controller.usecase.CreateNutritionProtocolController;
import at.easydiet.teamc.model.PatientBo;

/**
 * 
 * @author Michael
 */
@ManagedBean
@SessionScoped
public class WebController {

	// class variables
	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(WebController.class);

	// instance variables
	private PatientBo _loggedInUser;
	private CreateNutritionProtocolController _protocolController;

	/**
	 * Login a patient
	 * @username Login name
	 * @password User password
	 */
	public boolean loginPatient(String username, String password) {
		_loggedInUser = LoginController.getInstance().loginPatient(username,
				password);

		if (_loggedInUser != null) {
			return true;
		}
		return false;
	}

	/**
	 * Create a new dietry protocol
	 */
	public void createNewProtocol() {
		_protocolController = new CreateNutritionProtocolController();
	}
}
