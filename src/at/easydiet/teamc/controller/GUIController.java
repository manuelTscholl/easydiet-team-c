/**
 * This File is part of Easy Diet
 * created on: 04.04.2011
 * created by: Michael
 * file: GUIController.java
 */
package at.easydiet.teamc.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.TextInput;

import at.easydiet.teamc.exception.GeneratingDietryPlanException;
import at.easydiet.teamc.exception.NoDateException;
import at.easydiet.teamc.exception.NoDietTreatmentException;
import at.easydiet.teamc.exception.NoPatientException;
import at.easydiet.teamc.exception.NutrimentRuleException;
import at.easydiet.teamc.exception.TimeIntersectionException;
import at.easydiet.teamc.model.data.CheckOperatorData;
import at.easydiet.teamc.model.data.CheckedRecipeVo;
import at.easydiet.teamc.model.data.DietParameterData;
import at.easydiet.teamc.model.data.DietryPlanData;
import at.easydiet.teamc.model.data.MealCodeData;
import at.easydiet.teamc.model.data.MealData;
import at.easydiet.teamc.model.data.NutrimentParameterRuleData;
import at.easydiet.teamc.model.data.ParameterDefinitionData;
import at.easydiet.teamc.model.data.ParameterDefinitionUnitData;
import at.easydiet.teamc.model.data.PatientData;
import at.easydiet.teamc.model.data.RecipeData;
import at.easydiet.teamc.model.data.ValidatedRecipeVo;
import at.easydiet.teamc.util.CollectionConverter;
import at.easydiet.teamc.util.EventArgs;
import at.easydiet.teamc.util.IEventHandler;
import at.easydiet.teamc.view.AddRecipeScrollPane;
import at.easydiet.teamc.view.ChooseMealDialog;
import at.easydiet.teamc.view.ContentAnamnesisScrollPane;
import at.easydiet.teamc.view.ContentTabPane;
import at.easydiet.teamc.view.DietPlanDialog;
import at.easydiet.teamc.view.EasyDietMenuBar;
import at.easydiet.teamc.view.EasyDietWindow;
import at.easydiet.teamc.view.GUIComponents;
import at.easydiet.teamc.view.KeyAdapter;
import at.easydiet.teamc.view.NavigationTabPane;
import at.easydiet.teamc.view.PatientListener;

/**
 * Controls and knows all GUI elements
 * 
 * @author Michael
 */
public class GUIController implements PatientListener {

	// class variables
	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(GUIController.class);
	private static GUIController GUI_CONTROLLER;
	// instance variables
	private EasyDietWindow _easyDietWindow;
	private Map<String, Object> _guiNamespaces;
	private NavigationTabPane _navTab;
	private EasyDietMenuBar _menuBar;
	private ContentTabPane _contentTab;
	private BusinessLogicDelegationController _businessLogicDelegationController;

	/**
	 * Singelton creates a new instance of this class or returns the existing
	 * 
	 * @return
	 */
	public static GUIController getInstance() {

		if (GUI_CONTROLLER == null) {
			GUI_CONTROLLER = new GUIController();
		}

		return GUI_CONTROLLER;
	}

	/**
	 * Constructor
	 * 
	 * @param eadw Window with all gui ressources
	 */
	private GUIController() {

		_easyDietWindow = (EasyDietWindow) GUIComponents.get("easyDietWindow");
		_guiNamespaces = _easyDietWindow.getRessources();

		_businessLogicDelegationController = BusinessLogicDelegationController
				.getInstance();

		// get gui containers
		_navTab = (NavigationTabPane) _guiNamespaces.get("navTab");
		_menuBar = (EasyDietMenuBar) _guiNamespaces.get("menuBar");
		_contentTab = (ContentTabPane) _guiNamespaces.get("contentTab");

		// register for patient events
		_navTab.registerListener(this);

		// add key event for search textinput field
		_navTab.getSearchTextInput().getComponentKeyListeners()
				.add(new KeyAdapter() {

					@Override
					public boolean keyTyped(Component component, char character) {

						// check if activity indicator is already active
						if (!_navTab.getActivityIndicator().isActive()) {
							_navTab.getActivityIndicator().setActive(true);
						}

						// asyncron search that the gui is not blocked while
						// searching in database
						String searchText = ((TextInput) component).getText();

						_businessLogicDelegationController
								.searchPatient(searchText);

						// check if text field is empty, if empty stop activity
						// indicator
						if (_navTab.getSearchTextInput().getText().equals("")) {
							_navTab.getActivityIndicator().setActive(false);
						}

						return true;
					}
				});

		// updates the gui when event is fired
		_businessLogicDelegationController
				.addNewPatientSearchHandler(new IEventHandler<EventArgs>() {

					@Override
					public void fired(Object sender, EventArgs e) {
						updateSearchResult(_businessLogicDelegationController
								.getLastPatientSearchResult());
					}
				});

	}

	/**
	 * Update search results in navigation search tab with received set
	 * 
	 * @param patients
	 */
	public void updateSearchResult(Set<PatientData> patients) {
		_navTab.updateSearchResult(CollectionConverter
				.convertToPivotSet(patients));
	}

	/**
	 * Set a new active patient
	 * 
	 * @param p
	 */
	@Override
	public void chooseActivePatient(PatientData p) {
		_contentTab.setSelectedTabByName("overviewTab");
		_businessLogicDelegationController.setActivePatient(p);
		_navTab.unlockPatientSpecificItems();
		_contentTab.unlockPatientSpecificItems();
		updatePatientData(p);
	}

	/**
	 * Update patient data in content tabs
	 * 
	 * @param p
	 */
	public void updatePatientData(PatientData p) {
		_contentTab.updatePatientData(p);

		// update patient name in window bar
		_easyDietWindow.setTitle("EasyDiet - Patient: " + p.getForename() + " "
				+ p.getLastname().toUpperCase());
	}

	/**
	 * Open a new dialog for creating a new dietry plan
	 */
	public void createDietryPlan() {

		// load dialog for creating a new dietplan
		BXMLSerializer bxml = new BXMLSerializer();
		DietPlanDialog dietryPlanDialog;
		try {
			dietryPlanDialog = (DietPlanDialog) bxml.readObject(
					DietPlanDialog.class, "bxml/diet_plan_dialog.bxml");
			dietryPlanDialog.open(_easyDietWindow);
		} catch (IOException ex) {
			LOGGER.error(ex);
		} catch (SerializationException ex) {
			LOGGER.error(ex);
		}
	}

	/**
	 * Get all parameters
	 * 
	 * @return All available parameters
	 */
	public List<DietParameterData> getAllParameters() {
		java.util.List<DietParameterData> parameters = _businessLogicDelegationController
				.getAllParameters();
		List<DietParameterData> pivotList = (List<DietParameterData>) CollectionConverter
				.convertToPivotList(parameters);

		return pivotList;
	}

	/**
	 * Create a new dietry plan
	 * 
	 * @param start Plan start date
	 * @param end Plan end date
	 * @param dpList List of parameters for this plan
	 * @param parameterMaxValues Max Values for the chosen parameters
	 * @param parameterMinValues Min Values for the chosen parameters
	 * @throws NoDietTreatmentException
	 * @throws TimeIntersectionException
	 * @throws NoDateException
	 * @throws NoPatientException
	 * @throws GeneratingDietryPlanException
	 */
	public void newDietryPlan(Date start, Date end,
			List<DietParameterData> dpList, List<Double> parameterMaxValues,
			List<Double> parameterMinValues) throws NoPatientException,
			NoDateException, TimeIntersectionException,
			NoDietTreatmentException, GeneratingDietryPlanException {

		// convert pivot list to java list
		java.util.List<DietParameterData> list = CollectionConverter
				.convertToJavaList(dpList);
		java.util.List<Double> maxValueList = CollectionConverter
				.convertToJavaList(parameterMaxValues);
		java.util.List<Double> minValueList = CollectionConverter
				.convertToJavaList(parameterMinValues);

		DietryPlanData plan = _businessLogicDelegationController.newDietryPlan(
				start, end, list, maxValueList, minValueList);
		if (plan == null) {
			throw new GeneratingDietryPlanException();
		}
		_navTab.drawDietryPlanMenu(plan);
		_contentTab.drawDietryPlan(plan);

	}

	/**
	 * Opens the dialog for creating a new meal
	 * 
	 * @param day to add a meal
	 */
	public void addMeal(int day) {

		// load dialog for creating a new dietplan
		BXMLSerializer bxml = new BXMLSerializer();
		ChooseMealDialog chooseMealDialog;
		try {
			chooseMealDialog = (ChooseMealDialog) bxml.readObject(
					ChooseMealDialog.class, "bxml/choose_meal_dialog.bxml");
			chooseMealDialog.setDay(day);
			chooseMealDialog.open(_easyDietWindow);
		} catch (IOException ex) {
			LOGGER.error(ex);
		} catch (SerializationException ex) {
			LOGGER.error(ex);
		}
	}

	/**
	 * Returns all meals with the given codes
	 * 
	 * @return
	 */
	public List<MealCodeData> getAllMealCodes() {
		return (List<MealCodeData>) CollectionConverter
				.convertToPivotList(_businessLogicDelegationController
						.getAllMealCodes());
	}

	/**
	 * Add a new meal code
	 * 
	 * @param md Meal code to add
	 * @param day Day for this meal
	 */
	public void addMealCode(MealCodeData md, int day) {
		_businessLogicDelegationController.addMealCode(md, day);
	}

	/**
	 * Add a new MealLine
	 * 
	 * @return Index of this meal line
	 */
	public int addMealLine() {
		return _businessLogicDelegationController.addMealLine();
	}

	/**
	 * Get all recipe main categories
	 */
	public org.apache.pivot.collections.List<RecipeData> getRecipeMainCategories() {
		return (org.apache.pivot.collections.List<RecipeData>) CollectionConverter
				.convertToPivotList(_businessLogicDelegationController
						.getRecipeMainCategories());
	}

	/**
	 * 
	 * 
	 * @param mainCategory the BLS Code of this categorie
	 * @param search
	 * @return All matched recipe with parameter check
	 */
	public org.apache.pivot.collections.Set<CheckedRecipeVo> searchRecipe(
			String mainCategory, String search) {
		return CollectionConverter
				.convertToPivotSet(_businessLogicDelegationController
						.searchRecipe(mainCategory, search));
	}

	/**
	 * Add a recipe to a meal
	 * 
	 * @param rd Recipe to add
	 * @param day to add this meal
	 * @param quantity Quantity of the recipe
	 * @param mealLineId MealLine to add this recipe
	 * @param mealCode actual meal code
	 * @return
	 */
	public MealData addRecipeToMeal(RecipeData rd, int day, float quantity,
			int mealLineId, MealCodeData mealCode) {
		return _businessLogicDelegationController.addRecipetoMeal(rd, day,
				quantity, mealLineId, mealCode);
	}

	/**
	 * Save the actual dietry plan
	 */
	public void saveDietryPlan() {
		_businessLogicDelegationController.saveDietryPlan();
		_contentTab.drawDietryPlan(_businessLogicDelegationController
				.getDietryPlan());
	}

	/**
	 * Get the actual dietry plan
	 * 
	 * @return actual dietry plan
	 */
	public DietryPlanData getDietryPlan() {
		return _businessLogicDelegationController.getDietryPlan();
	}

	/**
	 * Open dialog for adding a new recipes
	 */
	public void openAddRecipeDialog() {

		// load dialog for creating a new recipe
		BXMLSerializer bxml = new BXMLSerializer();
		Component component;
		Dialog dialog = new Dialog();
		try {
			component = (Component) bxml.readObject(AddRecipeScrollPane.class
					.getResource("bxml/addRecipe.bxml"));
			dialog.setContent(component);
			dialog.open(_easyDietWindow);

			// set window height
			dialog.setPreferredHeight(dialog.getWindow().getPreferredHeight() - 300);
			dialog.setPreferredWidth(600);

			// set dialog title
			dialog.setTitle("Neues Rezept erstellen");

		} catch (IOException ex) {
			LOGGER.error(ex);
		} catch (SerializationException ex) {
			LOGGER.error(ex);
		}
		_businessLogicDelegationController.createNewRecipe();
	}

	/**
	 * Open dialog for adding new patients
	 */
	public void openAddPatientDialog() {

		// load dialog for creating a new recipe
		BXMLSerializer bxml = new BXMLSerializer();
		Component component;
		Dialog dialog = new Dialog();
		try {
			component = (Component) bxml
					.readObject(ContentAnamnesisScrollPane.class
							.getResource("bxml/teamb/easydiet_tab_patient.bxml"));
			dialog.setContent(component);
			dialog.open(_easyDietWindow);

			// set window height
			dialog.setPreferredHeight(dialog.getWindow().getPreferredHeight() - 300);
			dialog.setPreferredWidth(600);

			// set dialog title
			dialog.setTitle("Patient hinzufügen");

		} catch (IOException ex) {
			LOGGER.error(ex);
		} catch (SerializationException ex) {
			LOGGER.error(ex);
		}
	}

	/**
	 * Do dietplan independet recipe search
	 * 
	 * @param mainCategory
	 * @param search
	 * @return
	 */
	public org.apache.pivot.collections.List<RecipeData> recipeSearch(
			String mainCategory, String search) {
		return (List<RecipeData>) CollectionConverter
				.convertToPivotList(_businessLogicDelegationController
						.recipeSearch(mainCategory, search));
	}

	public org.apache.pivot.collections.List<ParameterDefinitionUnitData> getAllParameterDefinitionUnits() {
		return (List<ParameterDefinitionUnitData>) CollectionConverter
				.convertToPivotList(_businessLogicDelegationController
						.getAllParameterDefinitionUnits());
	}

	public org.apache.pivot.collections.List<ParameterDefinitionData> getAllParameterDefinitions() {
		return (List<ParameterDefinitionData>) CollectionConverter
				.convertToPivotList(_businessLogicDelegationController
						.getAllParameterDefinitions());
	}

	/**
	 * Get all check operators
	 * 
	 * @return
	 */
	public org.apache.pivot.collections.List<CheckOperatorData> getAllCheckoperators() {
		return (List<CheckOperatorData>) CollectionConverter
				.convertToPivotList(_businessLogicDelegationController
						.getAllCheckoperators());
	}

	/**
	 * Get all parameter definition units
	 * 
	 * @return
	 */
	public org.apache.pivot.collections.List<ParameterDefinitionUnitData> getAllParameterDefinitionUnit() {
		return (List<ParameterDefinitionUnitData>) CollectionConverter
				.convertToPivotList(_businessLogicDelegationController
						.getAllParameterDefinitionUnit());
	}

	/**
	 * Add a parameter
	 * 
	 * @param pd to add to the recipe
	 * @param unit of the parameter
	 * @param cod for the parameter
	 * @param value of the parameter
	 * @return
	 */
	public ValidatedRecipeVo addParameter(ParameterDefinitionData pd,
			ParameterDefinitionUnitData unit, CheckOperatorData cod,
			double value) throws NutrimentRuleException {
		return _businessLogicDelegationController.addParameter(pd, cod, value,
				unit);
	}

	/**
	 * Change a parameter
	 * 
	 * @param param to change
	 * @param unit the changed unit
	 * @param checkoperator the changed operator
	 * @param value the changed value
	 */
	public ValidatedRecipeVo changeParameter(NutrimentParameterRuleData param,
			CheckOperatorData check, double value,
			ParameterDefinitionUnitData pdud, ParameterDefinitionData pdd)
			throws NutrimentRuleException {
		return _businessLogicDelegationController.changeParameter(param, check,
				value, pdud, pdd);
	}

	/**
	 * Change a recipe ingredient
	 * 
	 * @param rd to change
	 * @param unit the changed unit
	 * @param amount the changed amount
	 * 
	 */
	public ValidatedRecipeVo changeRecipeIngredient(RecipeData rd,
			ParameterDefinitionUnitData unit, float amount) {
		return _businessLogicDelegationController.changeRecipeIngredient(
				amount, rd, unit);
	}

	/*
	 * Add a Recipe Ingredient
	 * 
	 * @param rd to add
	 * 
	 * @param unit the add unit
	 * 
	 * @param amount of the recipe
	 */
	public ValidatedRecipeVo addRecipeIngredient(RecipeData rd,
			ParameterDefinitionUnitData unit, float amount) {
		return _businessLogicDelegationController.addRecipeIngredient(rd, unit,
				amount);
	}

	/**
	 * Remove a parameter
	 * 
	 * @param param to remove
	 * @return
	 */
	public ValidatedRecipeVo removeParameter(NutrimentParameterRuleData param) {
		return _businessLogicDelegationController.removeParameter(param);
	}

	/**
	 * Remove a recipes
	 * 
	 * @param remove to remove
	 * @return
	 */
	public ValidatedRecipeVo removeRecipe(RecipeData remove) {
		return _businessLogicDelegationController.removeRecipe(remove);
	}

	/**
	 * Save the recipe
	 * 
	 * @param recipeName to save
	 * @param preparation
	 * @param description
	 * @param benefits
	 * @param prepartionTime
	 * @param difficulty
	 */
	public void saveRecipe(String recipeName, String preparation,
			String description, String benefits, double prepartionTime,
			int difficulty) {
		_businessLogicDelegationController.saveRecipe(recipeName, preparation,
				description, benefits, prepartionTime, difficulty);
	}

}
