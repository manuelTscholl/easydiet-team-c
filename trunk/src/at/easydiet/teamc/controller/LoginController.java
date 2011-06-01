/**
 * This file is part of Easy Diet.
 *   created on: 25.04.2011
 *   created by: Manuel
 *   file: LoginController.java
 */
package at.easydiet.teamc.controller;

import java.util.List;

import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.SystemUserDAO;
import at.easydiet.model.SystemUser;
import at.easydiet.teamc.controller.usecase.SearchPatientController;
import at.easydiet.teamc.exception.LoginFailedException;
import at.easydiet.teamc.model.PatientBo;
import at.easydiet.teamc.model.SystemUserBo;
import at.easydiet.teamc.model.data.PatientData;

/**
 * Handels the logins of the systemusers
 * 
 * @author Manuel
 * 
 */
public class LoginController {
	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(LoginController.class);

	private static LoginController _loginController;
	private SystemUserBo _actualUser;

	/**
	 * Singelton: Constructor
	 * 
	 * @return
	 */
	public static LoginController getInstance() {
		if (_loginController == null) {
			_loginController = new LoginController();
		}

		return _loginController;
	}

	/**
	 * Gets the actualUser or null if no one exists
	 * 
	 * @return the actualUser
	 */
	public SystemUserBo getActualUser() {
		// FIXME only for testing there is no user logined in the system
		SystemUserDAO getUserDao = DAOFactory.getInstance().getSystemUserDAO();
		List<SystemUser> users = getUserDao.findAll();
		if (users.size() >= 1) {
			return new SystemUserBo(users.get(0));
		}
		return null;
	}

	/**
	 * Sets the actualUser.
	 * 
	 * @param actualUser the actualUser to set
	 */
	public void setActualUser(SystemUserBo actualUser) {
		_actualUser = actualUser;
	}

	/**
	 * Login a patient
	 * @param username of the patient
	 * @param password of the patient
	 * @return Logged in patient or null if no patient whit this login
	 *         informations is found
	 */
	public PatientData loginPatient(String username, String password)
			throws LoginFailedException {
		PatientBo user = SearchPatientController.getInstance().loginPatient(
				username);

		if (user.getModel() != null) {

			// check for correct password
			if (user.getPassword().equals(password)) {
				return user;
			} else {
				throw new LoginFailedException("Wrong password or username.");
			}
		} else {
			throw new LoginFailedException("Username not known.");
		}

	}

}
