/**
 * This File is part of EasyDiet
 * Created on: 26.05.2011
 * Created by: Michael
 * File: CreateNutritionProtocolController.java
 */
package at.easydiet.teamc.controller.usecase;

import java.util.List;
import java.util.Set;

import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.model.NutritionProtocolBo;
import at.easydiet.teamc.model.data.MealCodeData;
import at.easydiet.teamc.model.data.PlanTypeData;
import at.easydiet.teamc.model.data.RecipeData;

/**
 * Controller for processing nutrition protocols Every user gets his own
 * controller.
 * 
 * @author Michael
 */
public class CreateNutritionProtocolController {

	// class variables
	private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(CreateNutritionProtocolController.class);
	// instance variables
	private NutritionProtocolBo _actualProtocol;
	private List<PlanTypeData> _planTypes;
	private Set<MealCodeData> _mealCodes;
	private List<RecipeData> _recipes;

	/**
	 * Instantiates a new creates the nutrition protocol controller.
	 */
	public CreateNutritionProtocolController() {

		// init necessary protocol data
		_planTypes = BusinessLogicDelegationController.getInstance()
				.getAllPlanTypes();
		_mealCodes = BusinessLogicDelegationController.getInstance()
				.getAllMealCodes();
		_recipes = BusinessLogicDelegationController.getInstance()
				.recipeSearch(null, null);
	}

	/**
	 * Gets the actual protocol.
	 * 
	 * @return the actual protocol
	 */
	public NutritionProtocolBo getActualProtocol() {
		return _actualProtocol;
	}

	/**
	 * Gets the plan types.
	 * 
	 * @return the plan types
	 */
	public List<PlanTypeData> getPlanTypes() {
		return _planTypes;
	}

	/**
	 * Gets the meal codes.
	 * 
	 * @return the meal codes
	 */
	public Set<MealCodeData> getMealCodes() {
		return _mealCodes;
	}

	/**
	 * Gets the recipes.
	 * 
	 * @return the recipes
	 */
	public List<RecipeData> getRecipes() {
		return _recipes;
	}

}
