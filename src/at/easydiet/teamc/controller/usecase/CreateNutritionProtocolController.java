/**
 * This File is part of EasyDiet
 * Created on: 26.05.2011
 * Created by: Michael
 * File: CreateNutritionProtocolController.java
 */
package at.easydiet.teamc.controller.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import at.easydiet.dao.DAOFactory;
import at.easydiet.teamc.controller.BusinessLogicDelegationController;
import at.easydiet.teamc.model.DietPlanBo;
import at.easydiet.teamc.model.NutritionProtocolBo;
import at.easydiet.teamc.model.PatientBo;
import at.easydiet.teamc.model.PlanTypeBo;
import at.easydiet.teamc.model.data.DietryPlanData;
import at.easydiet.teamc.model.data.MealCodeData;
import at.easydiet.teamc.model.data.PatientData;
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
	private PlanTypeData _selectedPlanType;
	private static final String NUTRIMENTBEANNAME = "nutrimentProtocolBean";

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

	// TODO wie bekommen wir rezepte und mengen von jsf vereint
	public void addRecipesToProtocoll(Map<RecipeData, Float> recipes) {

	}

	private void addRecipeToProtocoll(RecipeData recipe) {

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
		_actualProtocol.setPlanType((PlanTypeBo) _selectedPlanType);
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

	/**
	 * Get all dietry plans from patient
	 * @param patient
	 * @return
	 */
	public List<DietryPlanData> getAllDietryPlans(PatientData patient) {
		PatientBo p = (PatientBo) patient;
		List<DietryPlanData> plans = new ArrayList<DietryPlanData>();
		for (DietPlanBo d : p.getAllDietPlans()) {
			plans.add(d);
		}

		return plans;
	}

	/**
     * 
     */
	public void nutrimentProtocolDateSelect() {
		// FacesContext context = FacesContext.getCurrentInstance();
		// NutrimentProtocolBean bean = context.getApplication()
		// .evaluateExpressionGet(context, "#{" + NUTRIMENTBEANNAME + "}",
		// NutrimentProtocolBean.class);
		// if (bean == null) {
		// LOGGER.info("context is null");
		// return;
		// }
		// LOGGER.info("setting date equal");
		// Date test = new Date();
		// bean.setEndDate(test);
		// bean.setStartDate(test);
	}

	public void searchRecipes(String query) {
		
	}
}
