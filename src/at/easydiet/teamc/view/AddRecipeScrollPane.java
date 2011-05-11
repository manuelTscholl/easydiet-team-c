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
import org.apache.pivot.collections.Sequence.Tree.Path;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.Keyboard.KeyLocation;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TableViewRowListener;
import org.apache.pivot.wtk.TextArea;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TreeView;
import org.apache.pivot.wtk.TreeViewBranchListener;

import at.easydiet.teamc.controller.GUIController;
import at.easydiet.teamc.model.data.CheckOperatorData;
import at.easydiet.teamc.model.data.NutrimentParameterRuleData;
import at.easydiet.teamc.model.data.ParameterDefinitionData;
import at.easydiet.teamc.model.data.ParameterDefinitionUnitData;
import at.easydiet.teamc.model.data.RecipeData;
import at.easydiet.teamc.model.data.ValidatedRecipeVo;
import at.easydiet.teamc.util.CollectionConverter;

/**
 * This class represents the scrollpane for creating a new recipe
 * (addRecipe.bxml)
 * 
 * @author Michael
 */
public class AddRecipeScrollPane extends ScrollPane implements Bindable {

	// class variables
	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(AddRecipeScrollPane.class);

	// instance variables | GUI components
	private TextInput _recipeNameTextInput;
	private PushButton _addParameterButton;
	private PushButton _removeParameterButton;
	private ListButton _checkOperatorListButton;
	private ListButton _paramUnitListButton;
	private ParameterTableView _parameterTableView;
	private ListButton _parameterListButton;
	private TextInput _recipeSearchTextInput;
	private PushButton _searchRecipeButton;
	private RecipeTreeView _recipeTreeView;
	private PushButton _addRecipeButton;
	private PushButton _removeRecipeButton;
	private RecipeTableView _chosenRecipeTableView;
	private ListButton _unitListButton;
	private TextArea _preparationTextArea;
	private TextArea _descriptionTextArea;
	private TextArea _benefitsTextArea;
	private TextInput _preparationTimeTextInput;
	private ListButton _difficultyListButton;
	private PushButton _clearPushButton;
	private PushButton _savePushButton;

	// instance variables | datas
	private List<RecipeData> _recipesMainCategories;
	private List<CheckOperatorData> _checkOperators;
	private List<ParameterDefinitionUnitData> _units;

	{
		_recipesMainCategories = new ArrayList<RecipeData>();
	}

	@Override
	public void initialize(Map<String, Object> map, URL url, Resources resources) {

		// TODO todo for add recipe gui
		/*
		 * Überprüfen ob Rezeptname bereits vergeben ist, Parameterwerte
		 * validieren, Menge validieren
		 */
		System.out.println(map);

		// get GUI components
		_recipeNameTextInput = (TextInput) map.get("nameTextInput");
		_addParameterButton = (PushButton) map.get("addParameterButton");
		_checkOperatorListButton = (ListButton) map
				.get("checkOperatorListButton");
		_removeParameterButton = (PushButton) map.get("removeParameterButton");
		_paramUnitListButton = (ListButton) map.get("paramUnitListButton");
		_parameterTableView = (ParameterTableView) map
				.get("parameterTableView");
		_parameterListButton = (ListButton) map.get("parameterListButton");
		_recipeSearchTextInput = (TextInput) map.get("recipeSearchTextInput");
		_searchRecipeButton = (PushButton) map.get("searchRecipeButton");
		_recipeTreeView = (RecipeTreeView) map.get("recipeTreeView");
		_addRecipeButton = (PushButton) map.get("addRecipeButton");
		_removeRecipeButton = (PushButton) map.get("removeRecipeButton");
		_chosenRecipeTableView = (RecipeTableView) map
				.get("chosenRecipeTableView");
		_unitListButton = (ListButton) map.get("unitListButton");
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
		_recipeTreeView.setCategories(_recipesMainCategories);

		// listeners
		addParameterButtonListeners();
		addRecipeButtonListeners();
		addFunctionButtonListerners();
		addTextAreaListeners();
		addRecipeListeners();
	}

	/**
	 * Initialize data lists
	 */
	private void initDataLists() {

		// set data for parameter list
		_parameterListButton.setListData(GUIController.getInstance()
				.getAllParameterDefinitions());

		// set default
		_parameterListButton.setSelectedIndex(0);
		_addParameterButton.press();

		_recipesMainCategories = GUIController.getInstance()
				.getRecipeMainCategories();

		// units
		_units = GUIController.getInstance().getAllParameterDefinitionUnits();
		_unitListButton.setListData(_units);

		// set default
		_unitListButton.setSelectedIndex(0);

		// checkoperator
		_checkOperators = GUIController.getInstance().getAllCheckoperators();
		_checkOperatorListButton.setListData(_checkOperators);

		// set default
		_checkOperatorListButton.setSelectedIndex(0);
	}

	/**
	 * Add listeners for recipe components
	 */
	private void addRecipeListeners() {
		_recipeSearchTextInput.getComponentKeyListeners().add(
				new ComponentKeyListener() {

					@Override
					public boolean keyTyped(Component arg0, char arg1) {
						if (_recipeSearchTextInput.getText().isEmpty()) {
							_recipeTreeView.clearSearchResult();
						}
						return true;
					}

					@Override
					public boolean keyReleased(Component arg0, int arg1,
							KeyLocation arg2) {
						// not neccessary in this context
						return false;
					}

					@Override
					public boolean keyPressed(Component arg0, int arg1,
							KeyLocation arg2) {
						// not neccessary in this context
						return false;
					}
				});

		// add listener for opening tree branch
		_recipeTreeView.getTreeViewBranchListeners().add(
				new TreeViewBranchListener() {

					@Override
					public void branchExpanded(TreeView treeView, Path path) {

						if (!_recipeTreeView.isSearchMode()) {
							final int mainCat = 0;
							final int subCat = 1;

							// check if subcategory is opened
							if (path.getLength() > 1) {

								// get main category branch
								RecipeTreeBranch mainCategoryBranch = ((RecipeTreeBranch) treeView
										.getTreeData().get(
												path.toArray()[mainCat]));

								RecipeTreeBranch subCategoryBranch = (RecipeTreeBranch) mainCategoryBranch
										.get(path.toArray()[subCat]);

								// check if tree branch is already in cache
								if (!_recipeTreeView
										.cacheContains(subCategoryBranch)) {

									// get recipes for subCategoryBranch
									String blsSearch = subCategoryBranch
											.getRecipeData().getBlsCode();
									List<RecipeData> recipes = GUIController
											.getInstance().recipeSearch(
													blsSearch, null);

									_recipeTreeView.addRecipesToOpenedBranch(
											subCategoryBranch, recipes);
								}
							}
						}
					}

					@Override
					public void branchCollapsed(TreeView arg0, Path arg1) {
						// not neccessary in this context
					}
				});

		_chosenRecipeTableView.getTableViewRowListeners().add(
				new TableViewRowListener() {

					@Override
					public void rowsSorted(TableView arg0) {
						// not neccessary in this context

					}

					@Override
					public void rowsRemoved(TableView arg0, int arg1, int arg2) {
						// not neccessary in this context

					}

					@Override
					public void rowsCleared(TableView arg0) {
						// not neccessary in this context

					}

					@Override
					public void rowUpdated(TableView arg0, int index) {
						ParameterDefinitionUnitData selected = (ParameterDefinitionUnitData) _unitListButton
								.getSelectedItem();
						_chosenRecipeTableView.setUnit(selected, index);

					}

					@Override
					public void rowInserted(TableView arg0, int arg1) {
						// not neccessary in this context
					}
				});
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
						_parameterTableView.addParameter();

						// set default values
						int index = _parameterTableView.getTableData()
								.getLength() - 1;
						ParameterDefinitionData param = (ParameterDefinitionData) _parameterListButton
								.getListData().get(0);
						CheckOperatorData checkoperator = (CheckOperatorData) _checkOperatorListButton
								.getListData().get(0);

						// set listbox default value
						_parameterListButton.setSelectedIndex(0);
						_checkOperatorListButton.setSelectedIndex(0);

						// set unit listbox for this parameter
						List<ParameterDefinitionUnitData> paramUnit = (List<ParameterDefinitionUnitData>) CollectionConverter
								.convertToPivotList(((ParameterDefinitionData) _parameterListButton
										.getSelectedItem()).getUnits());
						_paramUnitListButton.setListData(paramUnit);
						_paramUnitListButton.setSelectedIndex(0);

						ParameterDefinitionUnitData unit = (ParameterDefinitionUnitData) _paramUnitListButton
								.getSelectedItem();

						// add parameter
						double value = _parameterTableView.getValue(index);
						addParameter(param, unit, checkoperator, value);
					}
				});

		// listener for removing parameters
		_removeParameterButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {
						_parameterTableView.removeParameter(_parameterTableView
								.getSelectedIndex());

					}
				});

		_parameterTableView.getTableViewRowListeners().add(
				new TableViewRowListener() {

					@Override
					public void rowsSorted(TableView arg0) {
						// not neccessary in this context

					}

					@Override
					public void rowsRemoved(TableView arg0, int arg1, int arg2) {
						// not neccessary in this context

					}

					@Override
					public void rowsCleared(TableView arg0) {
						// not neccessary in this context

					}

					@Override
					public void rowUpdated(TableView arg0, int index) {
						NutrimentParameterRuleData parameter = _parameterTableView
								.getParameter(index);
						CheckOperatorData operator = (CheckOperatorData) _checkOperatorListButton
								.getSelectedItem();
						double value = _parameterTableView.getValue(index);
						ParameterDefinitionUnitData unit = (ParameterDefinitionUnitData) _paramUnitListButton
								.getSelectedItem();
						GUIController.getInstance().changeParameter(parameter,
								operator, value, unit);
						changeParameter(parameter, unit, operator, value);
					}

					@Override
					public void rowInserted(TableView arg0, int arg1) {
						// not neccessary in this context
					}
				});
	}

	/**
	 * Add a parameter
	 * 
	 * @param parameter
	 * @param operator
	 * @param value
	 * @param row
	 */
	private void addParameter(ParameterDefinitionData parameter,
			ParameterDefinitionUnitData unit, CheckOperatorData operator,
			double value) {
		ValidatedRecipeVo validated = GUIController.getInstance().addParameter(
				parameter, unit, operator, value);

		for (NutrimentParameterRuleData n : validated
				.getNutrimentParameterRulesData()) {
			_parameterTableView.setParameterData(n);
		}
	}

	/**
	 * Change a parameter
	 * 
	 * @param parameter
	 * @param unit
	 * @param operator
	 * @param value
	 */
	private void changeParameter(NutrimentParameterRuleData parameter,
			ParameterDefinitionUnitData unit, CheckOperatorData operator,
			double value) {

	}

	/**
	 * Add listeners for adding and removing recipe
	 */
	private void addRecipeButtonListeners() {

		_addRecipeButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {

						// check if a node is selected and not a branch
						if (_recipeTreeView.getSelectedNode() instanceof RecipeTreeNode) {

							// check if recipe is already added
							if (!_chosenRecipeTableView
									.containsRecipeData(((RecipeTreeNode) _recipeTreeView
											.getSelectedNode()).getRecipeData())) {

								// get selected recipe
								_chosenRecipeTableView
										.addRecipe(((RecipeTreeNode) _recipeTreeView
												.getSelectedNode())
												.getRecipeData());

								int index = _chosenRecipeTableView
										.getTableData().getLength() - 1;
								ParameterDefinitionUnitData selected = (ParameterDefinitionUnitData) _unitListButton
										.getSelectedItem();
								_chosenRecipeTableView.setUnit(selected, index);
							}
						}
					}
				});

		_removeRecipeButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {

						// get selected recipe
						RecipeData remove = (RecipeData) _chosenRecipeTableView
								.getTableData()
								.get(_chosenRecipeTableView.getSelectedIndex())
								.get("recipe");

						_chosenRecipeTableView.removeRecipe(remove);
					}
				});
	}

	/**
	 * Add button listeners for functional buttons
	 */
	private void addFunctionButtonListerners() {
		_searchRecipeButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {

						// check for existing search string
						if (!_recipeSearchTextInput.getText().isEmpty()) {
							_recipeTreeView.setSearchedRecipes(GUIController
									.getInstance().recipeSearch(null,
											_recipeSearchTextInput.getText()));
						}
					}
				});

		_clearPushButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {
						// not neccessary in this context
					}
				});

		_savePushButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {

						// validate textareas
						if (validateTextInputs() && validateTextAreas()) {
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
		if (_preparationTextArea.getText().length() == 0) {
			_preparationTextArea.getStyles().put("backgroundColor", "#FF0000");
		}
		if (_benefitsTextArea.getText().length() == 0) {
			_benefitsTextArea.getStyles().put("backgroundColor", "#FF0000");
		}

		return false;
	}

	private boolean validateTextInputs() {
		return _recipeNameTextInput.isTextValid()
				&& _preparationTimeTextInput.isTextValid();
	}
}
