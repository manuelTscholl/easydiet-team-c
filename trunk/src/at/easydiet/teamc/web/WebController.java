/**
 * This File is part of EasyDiet
 * Created on: 25.05.2011
 * Created by: Michael
 * File: WebController.java
 */
package at.easydiet.teamc.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.hibernate.mapping.Array;
import org.primefaces.context.RequestContext;
import org.primefaces.event.DateSelectEvent;

import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.controller.LoginController;
import at.easydiet.teamc.controller.usecase.CreateNutritionProtocolController;
import at.easydiet.teamc.controller.usecase.SearchRecipeController;
import at.easydiet.teamc.exception.LoginFailedException;
import at.easydiet.teamc.exception.NoDietPlanException;
import at.easydiet.teamc.model.RecipeBo;
import at.easydiet.teamc.model.data.DietryPlanData;
import at.easydiet.teamc.model.data.MealCodeData;
import at.easydiet.teamc.model.data.PatientData;
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
	private PatientData _loggedInUser;
	private String _username = "";
	private String _password = "";
	private boolean _loggedIn = false;
	private CreateNutritionProtocolController _protocolController;
	private String _exception = "";
	private DietryPlanData _selectedPlan;
	
	private String _chosenRecipe;

	public String getChosenRecipe() {
		return _chosenRecipe;
	}

	public void setChosenRecipe(String chosenRecipe) {
		_chosenRecipe=chosenRecipe;
	}

	{

		_protocolController = new CreateNutritionProtocolController();
	}

	/**
	 * Login a patient
	 * @username Login name
	 * @password User password
	 */
	public void loginPatient() {

		// check if name is set
		if (_username != null && !_username.equals("") && _password != null
				&& !_password.equals("")) {
			try {
				_loggedInUser = LoginController.getInstance().loginPatient(
						_username, _password);
				_loggedIn = true;
			} catch (LoginFailedException e) {
				_loggedIn = false;
				_exception = e.toString();
			}
		}

		RequestContext context = RequestContext.getCurrentInstance();
		context.addCallbackParam("loggedIn", _loggedIn);
		context.addCallbackParam("exception", _exception);
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

	/**
	 * Get all dietry plans
	 */
	public List<DietryPlanData> getAllDietryPlans() {
		try {
			return _protocolController.getAllDietryPlans(_loggedInUser);
		} catch (NoDietPlanException e) {
			LOGGER.error(e.getMessage(), e);
			e.printStackTrace();
			return new ArrayList<DietryPlanData>();
		}
	}

	/**
	 * Sets the dietry plan.
	 * 
	 * @param d the new dietry plan
	 */
	public void setDietryPlan(DietryPlanData d) {
		_selectedPlan = d;
	}

	/**
	 * Is fired when a date from nutrimentprotocolis selected
	 * @param event
	 */
	public void nutrimentProtocolDateSelect(DateSelectEvent event) {
		_protocolController.NutrimentProtocolDateSelect();
		LOGGER.info("nutrimentProtocolDateSelect event");
	}
	
	public List<String> completeRecipeSearch(String query){
		ArrayList<String> s=new ArrayList<String>();
		for(RecipeData rd:searchRecipes(query)){
			s.add(rd.getName());
		}
		return s;
		
	}

	private List<RecipeData> searchRecipes(String query) {
		SearchRecipeController src=SearchRecipeController.getInstance();
		ArrayList<RecipeData> recipes=new ArrayList<RecipeData>();
		for(RecipeBo b: src.searchRecipe(null, query)){
			recipes.add((RecipeData)b);
		}
		return recipes;
		
	}

}
