/**
 * This File is part of EasyDiet
 * Created on: 25.05.2011
 * Created by: Michael
 * File: WebController.java
 */
package at.easydiet.teamc.web;

import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.context.RequestContext;

import at.easydiet.teamc.controller.LoginController;
import at.easydiet.teamc.controller.usecase.CreateNutritionProtocolController;
import at.easydiet.teamc.model.PatientBo;
import at.easydiet.teamc.model.data.MealCodeData;
import at.easydiet.teamc.model.data.PlanTypeData;
import at.easydiet.teamc.model.data.RecipeData;

/**
 * Main entry point for web modules This controller is unique for each session
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
	private String _username = "";
	private String _password = "";
	private boolean _loggedIn = false;
	private CreateNutritionProtocolController _protocolController;

	/**
	 * Login a patient
	 * @username Login name
	 * @password User password
	 */
	public void loginPatient() {

		// check if name is set
		if (_username != null && !_username.equals("") && _password != null
				&& !_password.equals("")) {
			_loggedInUser = LoginController.getInstance().loginPatient(
					_username, _password);

			if (_loggedInUser != null) {
				_loggedIn = true;
			} else {
				_loggedIn = false;
			}
		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("loggedIn", _loggedIn);
	}

	/**
	 * Create a new dietry protocol.
	 */
	public void createNewProtocol() {
		_protocolController = new CreateNutritionProtocolController();
	}

	/**
	 * Gets the username.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return _username;
	}

	/**
	 * Sets the username.
	 * 
	 * @param username the new username
	 */
	public void setUsername(String username) {
		_username = username;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return _password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password the new password
	 */
	public void setPassword(String password) {
		_password = password;
	}

	/**
	 * Gets the plan types.
	 * 
	 * @return the plan types
	 */
	public List<PlanTypeData> getPlanTypes() {
		return _protocolController.getPlanTypes();
	}

	/**
	 * Gets the meal codes.
	 * 
	 * @return the meal codes
	 */
	public Set<MealCodeData> getMealCodes() {
		return _protocolController.getMealCodes();
	}

	/**
	 * Gets the recipes.
	 * 
	 * @return the recipes
	 */
	public List<RecipeData> getRecipes() {
		return _protocolController.getRecipes();
	}

	/**
	 * Save the nutrition protocol
	 */
	public void saveNutritionProtocol() {
		_protocolController.save();
	}
}
