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
 * Main entry point for web modules
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
	private String _username;
	private String _passwort;


	/**
	 * Login a patient
	 * @username Login name
	 * @password User password
	 */
	public boolean getLoginPatient() {
		_loggedInUser = LoginController.getInstance().loginPatient(_username, _passwort);

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

	public String getUsername() {
		return _username;
	}

	public void setUsername(String _username) {
		this._username = _username;
	}

	public String getPasswort() {
		return _passwort;
	}

	public void setPasswort(String _passwort) {
		this._passwort = _passwort;
	}
}
