/**
 * This File is part of EasyDiet
 * Created on: 06.05.2011
 * Created by: Michael
 * File: AddRecipeDialog.java
 */
package at.easydiet.teamc.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TextArea;
import org.apache.pivot.wtk.TextInput;

import at.easydiet.teamc.controller.GUIController;
import at.easydiet.teamc.model.data.NutrimentParameterRuleVo;
import at.easydiet.teamc.model.data.RecipeData;

/**
 * This class represents the dialog for creating a new recipe (addRecipe.bxml)
 * 
 * @author Michael
 */
public class AddRecipeDialog extends Dialog implements Bindable {

	// class variables
	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(AddRecipeDialog.class);

	// instance variables | GUI components
	private TextInput _recipeNameTextInput;
	private PushButton _addParameterButton;
	private PushButton _removeParameterButton;
	private ParameterTableView _parameterTableView;
	private TextInput _recipeSearchTextInput;
	private PushButton _searchRecipeButton;
	private RecipeTreeView _recipeTreeView;
	private PushButton _addRecipeButton;
	private PushButton _removeRecipeButton;
	private TableView _chosenRecipeTableView;
	private TextArea _preparationTextArea;
	private TextArea _descriptionTextArea;
	private TextArea _benefitsTextArea;
	private TextInput _preparationTimeTextInput;
	private ListButton _difficultyListButton;
	private PushButton _clearPushButton;
	private PushButton _savePushButton;

	// instance variables | datas
	private List<NutrimentParameterRuleVo> _parameters;
	private List<RecipeData> _recipesMainCategories;

	{
		_parameters = new ArrayList<NutrimentParameterRuleVo>();
		_recipesMainCategories = new ArrayList<RecipeData>();
	}

	@Override
	public void initialize(Map<String, Object> map, URL url, Resources resources) {

		// TODO todo for add recipe gui
		/*
		 * Länge für Rezeptname überprüfen, Rezeptname erlaubte Zeichen
		 * validieren, Überprüfen ob Rezeptname bereits vergeben ist,
		 * Parameterwerte validieren, Menge validieren, Zubereitungszeit
		 * validieren
		 */

		// TODO eigene Komponente für parameter tableview, rezept treeview und
		// chosenrezept tableview

		// TODO height is for testing only
		// set window height
		setPreferredHeight(getWindow().getPreferredHeight() - 150);

		// set dialog title
		setTitle("Neues Rezept erstellen");

		// get GUI components
		_recipeNameTextInput = (TextInput) map.get("nameTextInput");
		_addParameterButton = (PushButton) map.get("addParameterButton");
		_removeParameterButton = (PushButton) map.get("removeParameterButton");
		_parameterTableView = (ParameterTableView) map
				.get("parameterTableView");
		_recipeSearchTextInput = (TextInput) map.get("recipeSearchTextInput");
		_searchRecipeButton = (PushButton) map.get("searchRecipeButton");
		_recipeTreeView = (RecipeTreeView) map.get("recipeTreeView");
		_addRecipeButton = (PushButton) map.get("addRecipeButton");
		_removeRecipeButton = (PushButton) map.get("removeRecipeButton");
		_chosenRecipeTableView = (TableView) map.get("chosenRecipeTableView");
		_preparationTextArea = (TextArea) map.get("preparationTextArea");
		_descriptionTextArea = (TextArea) map.get("descriptionTextArea");
		_benefitsTextArea = (TextArea) map.get("benefitsTextArea");
		_preparationTimeTextInput = (TextInput) map
				.get("preparationTimeTextInput");
		_difficultyListButton = (ListButton) map.get("difficultyListButton");
		_clearPushButton = (PushButton) map.get("clearButton");
		_savePushButton = (PushButton) map.get("saveButton");

		// set and cache data
		initDataLists();
		_recipeTreeView.setMainCategories(_recipesMainCategories);

		// listeners
		addParameterButtonListeners();
		addRecipeButtonListeners();
		addFunctionButtonListerners();
		addTextAreaListeners();
	}

	/**
	 * Initialize data lists
	 */
	private void initDataLists() {
		// TODO getAllNutrimentParameters -> cachen
		_recipesMainCategories = GUIController.getInstance().getRecipeMainCategories();
	}

	/**
	 * Add listeners for adding and removing parameters
	 */
	private void addParameterButtonListeners() {

		// listener for adding parameters
		_addParameterButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {
						// TODO Auto-generated method stub

					}
				});

		// listener for removing parameters
		_removeParameterButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {
						// TODO Auto-generated method stub

					}
				});
	}

	/**
	 * Add listeners for adding and removing recipe
	 */
	private void addRecipeButtonListeners() {

		_addRecipeButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {
						// TODO Auto-generated method stub

					}
				});

		_removeRecipeButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {
						// TODO Auto-generated method stub

					}
				});
	}

	/**
	 * Add button listeners for functional buttons
	 */
	private void addFunctionButtonListerners() {
		_clearPushButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {
						// TODO Auto-generated method stub
					}
				});

		_savePushButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {

						// validate textareas
						if (validateTextAreas()) {
							GUIController.getInstance().saveRecipe();
						}
					}
				});
	}

	/**
	 * Add listeners for textAreas
	 */
	private void addTextAreaListeners() {

		// preparation
		_preparationTextArea.getComponentMouseButtonListeners().add(
				new ComponentMouseButtonListener() {

					@Override
					public boolean mouseUp(Component arg0,
							org.apache.pivot.wtk.Mouse.Button arg1, int arg2,
							int arg3) {
						return true;
					}

					@Override
					public boolean mouseDown(Component arg0,
							org.apache.pivot.wtk.Mouse.Button arg1, int arg2,
							int arg3) {
						return true;
					}

					@Override
					public boolean mouseClick(Component arg0,
							org.apache.pivot.wtk.Mouse.Button arg1, int arg2,
							int arg3, int arg4) {
						_preparationTextArea.getStyles().put("backgroundColor",
								"#FFFFFF");
						return true;
					}
				});

		// benefits
		_benefitsTextArea.getComponentMouseButtonListeners().add(
				new ComponentMouseButtonListener() {

					@Override
					public boolean mouseUp(Component arg0,
							org.apache.pivot.wtk.Mouse.Button arg1, int arg2,
							int arg3) {
						return true;
					}

					@Override
					public boolean mouseDown(Component arg0,
							org.apache.pivot.wtk.Mouse.Button arg1, int arg2,
							int arg3) {
						return true;
					}

					@Override
					public boolean mouseClick(Component arg0,
							org.apache.pivot.wtk.Mouse.Button arg1, int arg2,
							int arg3, int arg4) {
						_benefitsTextArea.getStyles().put("backgroundColor",
								"#FFFFFF");
						return true;
					}
				});
	}

	/**
	 * Validate all textareas
	 * 
	 * @return
	 */
	private boolean validateTextAreas() {

		// check if not empty
		if ((_preparationTextArea.getText().length() > 0)
				&& (_benefitsTextArea.getText().length() > 0)) {
			return true;
		}

		// mark corresponding textareas as not valid
		_preparationTextArea.getStyles().put("backgroundColor", "#FF0000");
		_benefitsTextArea.getStyles().put("backgroundColor", "#FF0000");

		return false;
	}
}
