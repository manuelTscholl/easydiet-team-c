/**
 * This File is part of Easy Diet
 * created on: 05.04.2011
 * created by: Ali Gümüs
 * file: BusinesLogicDelegationController.java
 */
package at.easydiet.teamc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import at.easydiet.dao.RecipeDAO;
import at.easydiet.teamc.controller.usecase.CreateRecipeController;
import at.easydiet.teamc.controller.usecase.DietryPlanController;
import at.easydiet.teamc.controller.usecase.SearchParameterController;
import at.easydiet.teamc.controller.usecase.SearchPatientController;
import at.easydiet.teamc.controller.usecase.SearchRecipeController;
import at.easydiet.teamc.exception.NoDateException;
import at.easydiet.teamc.exception.NoDietTreatmentException;
import at.easydiet.teamc.exception.NoPatientException;
import at.easydiet.teamc.exception.TimeIntersectionException;
import at.easydiet.teamc.model.DietParameterBo;
import at.easydiet.teamc.model.PatientBo;
import at.easydiet.teamc.model.RecipeBo;
import at.easydiet.teamc.model.SystemUserBo;
import at.easydiet.teamc.model.data.CheckOperatorData;
import at.easydiet.teamc.model.data.CheckedRecipeVo;
import at.easydiet.teamc.model.data.DietParameterData;
import at.easydiet.teamc.model.data.DietryPlanData;
import at.easydiet.teamc.model.data.MealCodeData;
import at.easydiet.teamc.model.data.MealData;
import at.easydiet.teamc.model.data.NutrimentParameterData;
import at.easydiet.teamc.model.data.ParameterDefinitionData;
import at.easydiet.teamc.model.data.ParameterDefinitionUnitData;
import at.easydiet.teamc.model.data.PatientData;
import at.easydiet.teamc.model.data.RecipeData;
import at.easydiet.teamc.model.data.ValidatedRecipeVo;
import at.easydiet.teamc.util.EventArgs;
import at.easydiet.teamc.util.IEventHandler;

/**
 * Controller for business logic
 * 
 */
public class BusinessLogicDelegationController {

	// class variables
	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(BusinessLogicDelegationController.class);
	private static volatile BusinessLogicDelegationController _businessLogicDelegationController;
	// instance variables
	private PatientBo _activePatient;
	private final SearchPatientController _searchPatientController;
	private final SearchParameterController _searchParameterController;
	private Thread _patientSearchThread;
	private DietryPlanController _dietryPlanController;
	private LoginController _loginController;
	private CreateRecipeController _createRecipeController;

	/**
	 * Initializes a new instance of the
	 * {@link BusinessLogicDelegationController} class.
	 * 
	 * @param sender
	 */
	private BusinessLogicDelegationController() {
		_searchPatientController = SearchPatientController.getInstance();
		_searchParameterController = SearchParameterController.getInstance();

		// TODO ONLY FOR TESTING!!!!
		_dietryPlanController = DietryPlanController.getInstance();
	}

	/**
	 * {@link LoginController#getActualUser()}
	 */
	public SystemUserBo getActualUser() {
		_loginController = LoginController.getInstance();
		return _loginController.getActualUser();
	}

	/**
	 * Gets the lastSearchResult.
	 * 
	 * @return the lastSearchResult
	 */
	public synchronized Set<PatientData> getLastPatientSearchResult() {
		return _searchPatientController.getLastSearchResult();
	}

	/**
	 * Singelton: creates or returns the last created instance
	 * 
	 * @return
	 */
	public static BusinessLogicDelegationController getInstance() {
		if (_businessLogicDelegationController == null) {
			_businessLogicDelegationController = new BusinessLogicDelegationController();
		}

		return _businessLogicDelegationController;
	}

	/**
	 * Searches patients in database, will start an asyncron searching Thread
	 * 
	 * @param search
	 * @return The last list of Patients which where found in database, may not
	 *         the patients from the actual search for actual search use the
	 *         events
	 */
	public Set<PatientData> searchPatient(String search) {

		// start search only if at least 2 characters are available
		if (search.length() >= 2) {
			_searchPatientController.addNewPatientSearchString(search);

			// starting the search loop if not already started
			if (_patientSearchThread == null || !_patientSearchThread.isAlive()) {
				_patientSearchThread = new Thread(_searchPatientController);
				_patientSearchThread.setName("PatientSearchThread");
				_patientSearchThread.start();
			}
			// _searchParameterController.notify();
			LOGGER.info("notified _searchPatientController");
			// let the gui layer know that something updated

			return getLastPatientSearchResult();
		}

		return null;
	}

	/**
	 * Sets the active Patient
	 * 
	 * @param p the patient which should be set to active
	 */
	public void setActivePatient(PatientData p) {
		_activePatient = (PatientBo) p;
		_searchPatientController.stop();
	}

	/**
	 * Gets the active Patient which was set from the GUI Controller before *
	 * 
	 * @param p
	 */
	public PatientBo getActivePatient() {
		return _activePatient;
	}

	public void addNewPatientSearchHandler(IEventHandler<EventArgs> handler) {
		_searchPatientController.addHandler(handler);
	}

	/**
	 * Return a set of DietParameterData to supply the GUI Controller with data
	 * 
	 * @return All available parameters
	 */
	public List<DietParameterData> getAllParameters() {

		// get all Business Objects of DietParameters
		List<DietParameterBo> parameterBo = _searchParameterController
				.getAllParameters();

		List<DietParameterData> parameterData = new ArrayList<DietParameterData>();

		// encapsulate the bo's with putting the interface objects around
		for (DietParameterBo dpBo : parameterBo) {
			parameterData.add(dpBo);
		}
		return parameterData;
	}

	/**
	 * returns a list of all ParameterDefinitions
	 */
	public List<ParameterDefinitionData> getAllParameterDefinitions() {
		return _searchParameterController.getAllParameterDefinitions();

	}

	/**
	 * Create a new dietry plan
	 * 
	 * @param startDate Plan start date
	 * @param endDate Plan end date
	 * @param params List of parameters for this plan
	 * @param parameterMaxValues Max Values for the chosen parameters
	 * @param parameterMinValues Min Values for the chosen parameters
	 * @throws NoDietTreatmentException
	 * @throws TimeIntersectionException
	 * @throws NoDateException
	 * @throws NoPatientException
	 */
	public DietryPlanData newDietryPlan(Date startDate, Date endDate,
			List<DietParameterData> params, List<Double> parameterMaxValues,
			List<Double> parameterMinValues) throws NoPatientException,
			NoDateException, TimeIntersectionException,
			NoDietTreatmentException {
		_dietryPlanController = DietryPlanController.getInstance();
		try {
			_dietryPlanController.newDietryPlan(startDate, endDate, params,
					parameterMaxValues, parameterMinValues,
					this._activePatient, getActualUser());
		} catch (NoPatientException e) {
			LOGGER.debug(e);
		} catch (NoDateException e) {
			LOGGER.debug(e);
		} catch (TimeIntersectionException e) {
			LOGGER.debug(e);
		} catch (NoDietTreatmentException e) {
			LOGGER.debug(e);
		}

		// return the DietryPlanData
		return _dietryPlanController.getDietryPlan();
	}

	/**
	 * Returns all meals with the given codes
	 * 
	 * @return
	 */
	public Set<MealCodeData> getAllMealCodes() {
		return _dietryPlanController.getAllMealCodes();
	}

	/**
	 * Adds MealData to the DietPlan in progress
	 * 
	 * @param day Defines exact day in TimeSpan
	 * @param mcd MealCode to add
	 * @return
	 */
	public void addMealCode(MealCodeData mcd, int day) {
		_dietryPlanController.addMealCode(mcd, day);
	}

	/**
	 * Add MealLineData to the Meal
	 * 
	 * @return Index of the MealLine
	 */
	public int addMealLine() {
		return _dietryPlanController.addMealLine();
	}

	/**
	 * Return a list of Recipe-Categories
	 * 
	 * @return Main Categories of Recipes
	 */
	public List<RecipeData> getRecipeMainCategories() {
		return _dietryPlanController.getRecipeMainCategories();
	}

	/**
	 * {@link RecipeDAO#searchRecipe(String blsCategorie,String name)}
	 * 
	 * @param search Search string
	 * @return Set of Recipes which are checked corresponding to the active
	 *         parameters
	 */
	public Set<CheckedRecipeVo> searchRecipe(String mainCategory, String search) {
		return _dietryPlanController.searchRecipe(mainCategory, search);
	}

	/**
	 * Add a Recipe to the Meal in progress
	 * 
	 * @param mealLineID MealLine to whick the Recipe belongs
	 * @param day to add this meal
	 * @param quantity amount of Recipe in gramm.
	 * @param rd Recipe which will be added
	 * @param mealCode actual mealcode
	 * @return
	 */
	public MealData addRecipetoMeal(RecipeData rd, int day, float quantity,
			int mealLineID, MealCodeData mealCode) {
		return _dietryPlanController.addRecipeToMeal(rd, day, quantity,
				mealLineID, mealCode);
	}

	/**
	 * Returns DietPlan in progress
	 * 
	 * @return
	 */
	public void saveDietryPlan() {
		_dietryPlanController.saveDietryPlan();
	}

	/**
	 * Returns DietPlan in progress
	 * 
	 * @return
	 */
	public DietryPlanData getDietryPlan() {
		return _dietryPlanController.getDietryPlan();
	}

	public List<RecipeData> recipeSearch(String mainCategory, String search) {
		List<RecipeBo> recipes = SearchRecipeController.getInstance()
				.searchRecipe(mainCategory, search);

		// convert to data
		List<RecipeData> recipeDatas = new ArrayList<RecipeData>();
		for (RecipeBo r : recipes) {
			recipeDatas.add(r);
		}

		return recipeDatas;
	}

	/**
	 * Creates a new Recipe by initalizing a new instance of a
	 * createRecipeController and delegating
	 */
	public void createNewRecipe() {
		_createRecipeController = new CreateRecipeController();
	}

	public ValidatedRecipeVo addParameter(ParameterDefinitionData pd,
			CheckOperatorData cod, double value) {
		return _createRecipeController.addParameter(pd, cod, value);
	}

	public CheckedRecipeVo addRecipeIngredient(RecipeData d, float amount) {
		return _createRecipeController.addRecipeIngredient(d, amount);
	}

	public List<ParameterDefinitionUnitData> getAllParameterDefinitionUnits() {
		return new ArrayList<ParameterDefinitionUnitData>(DatabaseController
				.getInstance().getAllParameterDefinitionUnits());
	}

	/**
	 * 
	 * @return
	 */
	public List<CheckOperatorData> getAllCheckoperators() {

		return new ArrayList<CheckOperatorData>(DatabaseController
				.getInstance().getAllCheckoperators());
	}

	/**
	 * 
	 * @return
	 */
	public List<ParameterDefinitionUnitData> getAllParameterDefinitionUnit() {
		return new ArrayList<ParameterDefinitionUnitData>(DatabaseController
				.getInstance().getAllParameterDefinitionUnit());
	}

	public void changeParameter(NutrimentParameterData ndr,
			CheckOperatorData checkOperator, double value) {

		// TODO
	}

	public void changeRecipeIngredient(float amount, RecipeData rd) {

		// TODO
	}

}
