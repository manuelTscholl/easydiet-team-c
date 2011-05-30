/**
 * This File is part of EasyDiet
 * Created on: 26.05.2011
 * Created by: Michael
 * File: CreateNutritionProtocolController.java
 */
package at.easydiet.teamc.controller.usecase;

import java.util.List;
import java.util.Set;

import at.easydiet.dao.DAOFactory;
import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.model.NutritionProtocolBo;
import at.easydiet.teamc.model.data.MealCodeData;
import at.easydiet.teamc.model.data.PlanTypeData;
import at.easydiet.teamc.model.data.RecipeData;
import java.util.Map;

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
	private PlanTypeData _selectedPlanType;

	/**
	 * Instantiates a new creates the nutrition protocol controller.
	 */
	public CreateNutritionProtocolController() {
		_actualProtocol = new NutritionProtocolBo();

		// load necessary protocol data
		_planTypes = BusinessLogicDelegationController.getInstance()
				.getAllPlanTypes();
		_mealCodes = BusinessLogicDelegationController.getInstance()
				.getAllMealCodes();
		_recipes = BusinessLogicDelegationController.getInstance()
				.recipeSearch(null, null);
		_selectedPlanType = null;
	}
        //TODO wie bekommen wir rezepte und mengen von jsf vereint
        public void addRecipesToProtocoll(Map<RecipeData, Float> recipes){
            
        }

        private void addRecipeToProtocoll(RecipeData recipe){

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

	/**
	 * Save the protocol
	 */
	public void save() {
		DAOFactory.getInstance().getNutritionProtocolDAO()
				.makePersistent(_actualProtocol.getModel());
	}

	/**
	 * Sets the plan types.
	 * 
	 * @param planTypes the new plan types
	 */
	public void setPlanType(PlanTypeData planTypes) {
		_selectedPlanType = planTypes;
	}

	/**
	 * Sets the meal codes.
	 * 
	 * @param mealCodes the new meal codes
	 */
	public void setMealCodes(Set<MealCodeData> mealCodes) {
		_mealCodes = mealCodes;
	}

	/**
	 * Sets the recipes.
	 * 
	 * @param recipes the new recipes
	 */
	public void setRecipes(List<RecipeData> recipes) {
		_recipes = recipes;
	}

}
