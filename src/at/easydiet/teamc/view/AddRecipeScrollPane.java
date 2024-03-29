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
import org.apache.pivot.wtk.Action;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.Button.DataRenderer;
import org.apache.pivot.wtk.ButtonGroup;
import org.apache.pivot.wtk.ButtonListener;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.ComponentMouseListener;
import org.apache.pivot.wtk.ComponentStateListener;
import org.apache.pivot.wtk.Keyboard.KeyLocation;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Prompt;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TableViewRowListener;
import org.apache.pivot.wtk.TextArea;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.Tooltip;
import org.apache.pivot.wtk.TreeView;
import org.apache.pivot.wtk.TreeViewBranchListener;

import at.easydiet.teamc.controller.GUIController;
import at.easydiet.teamc.exception.NutrimentRuleException;
import at.easydiet.teamc.exception.ParameterWithoutUnitException;
import at.easydiet.teamc.model.data.CheckOperatorData;
import at.easydiet.teamc.model.data.NutrimentParameterData;
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
	private PushButton _savePushButton;

	// instance variables | datas
	private List<RecipeData> _recipesMainCategories;
	private List<CheckOperatorData> _checkOperators;
	private List<ParameterDefinitionUnitData> _units;

	{
		_recipesMainCategories = new ArrayList<RecipeData>();
	}

	/**
	 * Initialize all GUI components
	 * 
	 * @param map Contains all components
	 * @param url to the bxml file
	 * @param resources
	 */
	@Override
	public void initialize(Map<String, Object> map, URL url, Resources resources) {

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

		_savePushButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {

						// validate inputs
						if (validateTextInputs() && validateTextAreas()
								&& validateParameters()) {
							setCursor(getCursor().WAIT);
							GUIController
									.getInstance()
									.saveRecipe(
											_recipeNameTextInput.getText(),
											_preparationTextArea.getText(),
											_descriptionTextArea.getText(),
											_benefitsTextArea.getText(),
											Double.parseDouble(_preparationTimeTextInput
													.getText()),
											Integer.parseInt(_difficultyListButton
													.getSelectedItem()
													.toString()));

							Prompt success = new Prompt(MessageType.INFO,
									"Das Rezept wurde erfolgreich gespeichert",
									null);
							success.open(getWindow());
							getWindow().close();

							setCursor(getCursor().DEFAULT);

						} else {
							setCursor(getCursor().DEFAULT);

							Prompt error = new Prompt(
									MessageType.WARNING,
									"Das Rezept konnte nicht gespeichert werden",
									null);
							error.open(getWindow());
						}
					}
				});
	}

	/**
	 * Add parameter
	 * 
	 * @param parameter to add to the recipe
	 * @param unit of the parameter
	 * @param operator for the parameter
	 * @param value of the parameter
	 */
	private void addParameter(ParameterDefinitionData parameter,
			ParameterDefinitionUnitData unit, CheckOperatorData operator,
			double value) {
		ValidatedRecipeVo validated;
		try {
			validated = GUIController.getInstance().addParameter(parameter,
					unit, operator, value);

			for (NutrimentParameterRuleData n : validated
					.getNutrimentParameterRulesData()) {
				float totalAmount = validated
						.getRecipe()
						.getNutrimentParameterData(
								n.getParameterDefinitionData()).getAmount();
				_parameterTableView.setParameterData(n, totalAmount);
			}
		} catch (NutrimentRuleException e) {

			// change selected parameter and retry
			int index = _parameterListButton.getSelectedIndex() + 1;
			_parameterListButton.setSelectedIndex(index);
			ParameterDefinitionData newParameter = (ParameterDefinitionData) _parameterListButton
					.getListData().get(index);
			try {
				addParameter(newParameter, newParameter.getUnitData(),
						operator, value);
			} catch (ParameterWithoutUnitException e1) {
				addParameter(newParameter, null, operator, value);
			}
		}
	}

	/**
	 * Check if all parameters are not violated
	 * 
	 * @return true if all parameters are not violated
	 */
	private boolean validateParameters() {
		for (int i = 0; i < _parameterTableView.getTableData().getLength(); i++) {
			if (_parameterTableView.getParameter(i).IsViolated()) {
				return false;
			}
		}

		return true;
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
						NutrimentParameterRuleData param = _parameterTableView
								.getParameter(_parameterTableView
										.getSelectedIndex());
						GUIController.getInstance().removeParameter(param);
						_parameterTableView.removeParameter(_parameterTableView
								.getSelectedIndex());

					}
				});

		_parameterTableView.getTableViewRowListeners().add(
				new TableViewRowListener() {

					@Override
					public void rowInserted(TableView arg0, int arg1) {
						// not necessary in this context
					}

					@Override
					public void rowsCleared(TableView arg0) {
						// not necessary in this context

					}

					@Override
					public void rowsRemoved(TableView arg0, int arg1, int arg2) {
						// not necessary in this context

					}

					@Override
					public void rowsSorted(TableView arg0) {
						// not necessary in this context

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

						ValidatedRecipeVo validated;
						try {
							validated = GUIController
									.getInstance()
									.changeParameter(
											parameter,
											operator,
											value,
											unit,
											(ParameterDefinitionData) _parameterListButton
													.getSelectedItem());

							for (NutrimentParameterRuleData n : validated
									.getNutrimentParameterRulesData()) {
								float totalAmount = validated
										.getRecipe()
										.getNutrimentParameterData(
												n.getParameterDefinitionData())
										.getAmount();
								changeParameter(n, n.getUnit(),
										n.getCheckOperator(), n.getValue(),
										totalAmount);
							}
						} catch (NutrimentRuleException e) {
							Alert.alert(MessageType.ERROR, e.getMessage(),
									getWindow());
						}
					}
				});

		_parameterListButton.getButtonListeners().add(new ButtonListener() {

			@Override
			public void actionChanged(Button arg0, Action arg1) {
				// not necessary in this context
			}

			@SuppressWarnings("unchecked")
			@Override
			public void buttonDataChanged(Button arg0, Object arg1) {

				// parameter has so changed therefore it is necessary to change
				// the parameter units
				ParameterDefinitionData parameter = (ParameterDefinitionData) _parameterListButton
						.getSelectedItem();
				List<ParameterDefinitionUnitData> units = (List<ParameterDefinitionUnitData>) CollectionConverter
						.convertToPivotList(parameter.getUnits());

				// remove old entries
				_paramUnitListButton.clear();
				_paramUnitListButton.setButtonData(null);

				// set new data
				_paramUnitListButton.setListData(units);

				if (!units.isEmpty()) {
					_paramUnitListButton.setSelectedIndex(0);
				}

				// parameter is already set
				NutrimentParameterRuleData param = _parameterTableView
						.getParameter(_parameterTableView.getSelectedIndex());
				if (param != null) {

					// set parameter unit in dropdown box for this parameter
					for (ParameterDefinitionUnitData p : (List<ParameterDefinitionUnitData>) _paramUnitListButton
							.getListData()) {
						if (p.getName().equals(param.getUnit().getName())) {
							_paramUnitListButton.setSelectedItem(p);
						}
					}
				}
			}

			@Override
			public void buttonGroupChanged(Button arg0, ButtonGroup arg1) {
				// not necessary in this context

			}

			@Override
			public void dataRendererChanged(Button arg0, DataRenderer arg1) {
				// / not necessary in this context

			}

			@Override
			public void toggleButtonChanged(Button arg0) {
				// not necessary in this context

			}

			@Override
			public void triStateChanged(Button arg0) {
				// not necessary in this context

			}
		});

		_parameterTableView.getComponentStateListeners().add(
				new ComponentStateListener() {

					@Override
					public void enabledChanged(Component arg0) {
						// not necessary in this context

					}

					@Override
					public void focusedChanged(Component arg0, Component arg1) {

						// check if editor mode is active
						if (_parameterTableView.getRowEditor().isEditing()) {

							// set list data
							NutrimentParameterRuleData param = _parameterTableView
									.getParameter(_parameterTableView
											.getSelectedIndex());

							_parameterListButton.setSelectedItem(param
									.getParameterDefinitionData());
							_checkOperatorListButton.setSelectedItem(param
									.getCheckOperator());

						}

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

						// check if a node is selected and not a branch
						if (_recipeTreeView.getSelectedNode() instanceof RecipeTreeNode) {

							// check if recipe is already added
							if (!_chosenRecipeTableView
									.containsRecipeData(((RecipeTreeNode) _recipeTreeView
											.getSelectedNode()).getRecipeData())) {

								RecipeTreeNode node = (RecipeTreeNode) _recipeTreeView
										.getSelectedNode();
								RecipeData rData = node.getRecipeData();

								// get selected recipe
								_chosenRecipeTableView.addRecipe(rData);

								int index = _chosenRecipeTableView
										.getTableData().getLength() - 1;
								ParameterDefinitionUnitData unit = (ParameterDefinitionUnitData) _unitListButton
										.getSelectedItem();
								_chosenRecipeTableView.setUnit(unit, index);

								ValidatedRecipeVo validated = GUIController
										.getInstance().addRecipeIngredient(
												_chosenRecipeTableView
														.getRecipe(index),
												_chosenRecipeTableView
														.getUnit(index),
												_chosenRecipeTableView
														.getQuantity(index));

								// update parameters
								for (NutrimentParameterRuleData n : validated
										.getNutrimentParameterRulesData()) {
									float totalAmount = validated
											.getRecipe()
											.getNutrimentParameterData(
													n.getParameterDefinitionData())
											.getAmount();
									_parameterTableView.setParameterData(n,
											totalAmount);
								}
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

						ValidatedRecipeVo validated = GUIController
								.getInstance().removeRecipe(remove);

						// update parameters
						for (NutrimentParameterRuleData n : validated
								.getNutrimentParameterRulesData()) {
							float totalAmount = validated
									.getRecipe()
									.getNutrimentParameterData(
											n.getParameterDefinitionData())
									.getAmount();
							_parameterTableView
									.setParameterData(n, totalAmount);
						}

						_chosenRecipeTableView.removeRecipe(remove);
					}
				});
	}

	/**
	 * Add listeners for recipe components
	 */
	private void addRecipeListeners() {
		_recipeSearchTextInput.getComponentKeyListeners().add(
				new ComponentKeyListener() {

					@Override
					public boolean keyPressed(Component arg0, int arg1,
							KeyLocation arg2) {
						// not neccessary in this context
						return false;
					}

					@Override
					public boolean keyReleased(Component arg0, int arg1,
							KeyLocation arg2) {
						// not neccessary in this context
						return false;
					}

					@Override
					public boolean keyTyped(Component arg0, char arg1) {
						if (_recipeSearchTextInput.getText().isEmpty()) {
							_recipeTreeView.clearSearchResult();
						}
						return true;
					}
				});

		// add listener for opening tree branch
		_recipeTreeView.getTreeViewBranchListeners().add(
				new TreeViewBranchListener() {

					@Override
					public void branchCollapsed(TreeView arg0, Path arg1) {
						// not neccessary in this context
					}

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
				});

		_chosenRecipeTableView.getTableViewRowListeners().add(
				new TableViewRowListener() {

					@Override
					public void rowInserted(TableView arg0, int arg1) {
						// not neccessary in this context
					}

					@Override
					public void rowsCleared(TableView arg0) {
						// not neccessary in this context

					}

					@Override
					public void rowsRemoved(TableView arg0, int arg1, int arg2) {
						// not neccessary in this context

					}

					@Override
					public void rowsSorted(TableView arg0) {
						// not neccessary in this context

					}

					@Override
					public void rowUpdated(TableView arg0, int index) {
						ParameterDefinitionUnitData selected = (ParameterDefinitionUnitData) _unitListButton
								.getSelectedItem();
						_chosenRecipeTableView.setUnit(selected, index);
						ValidatedRecipeVo validated = GUIController
								.getInstance()
								.changeRecipeIngredient(
										_chosenRecipeTableView.getRecipe(index),
										_chosenRecipeTableView.getUnit(index),
										_chosenRecipeTableView
												.getQuantity(index));

						// update parameters
						for (NutrimentParameterRuleData n : validated
								.getNutrimentParameterRulesData()) {
							float totalAmount = validated
									.getRecipe()
									.getNutrimentParameterData(
											n.getParameterDefinitionData())
									.getAmount();
							_parameterTableView
									.setParameterData(n, totalAmount);
						}

					}
				});

		_chosenRecipeTableView.getComponentStateListeners().add(
				new ComponentStateListener() {

					@Override
					public void enabledChanged(Component arg0) {
						// not necessary in this context

					}

					@Override
					public void focusedChanged(Component arg0, Component arg1) {

						// check if editor mode is active
						if (_chosenRecipeTableView.getRowEditor().isEditing()) {

							// set list data
							ParameterDefinitionUnitData unit = _chosenRecipeTableView
									.getUnit(_chosenRecipeTableView
											.getSelectedIndex());
							_unitListButton.setSelectedItem(unit);

						}

					}
				});

		_chosenRecipeTableView.getComponentMouseListeners().add(
				new ComponentMouseListener() {

					@Override
					public void mouseOver(Component arg0) {
						// not necessary in this context
					}

					@Override
					public void mouseOut(Component arg0) {
						// not necessary in this context

					}

					@Override
					public boolean mouseMove(Component arg0, int x, int y) {

						StringBuilder tooltip = new StringBuilder();

						RecipeData r = _chosenRecipeTableView
								.getRecipe(_chosenRecipeTableView.getRowAt(y));
						tooltip.append(r.getName()).append("\n\n");

						// show parameters
						for (NutrimentParameterData n : r
								.getNutrimentParametersData()) {
							if (_parameterTableView.containsParameter(n)) {

								if (!tooltip.toString().contains(n.getName())) {
									tooltip.append(n.getName() + ": "
											+ n.getAmount() + "\n");
								}
							}
						}

						Tooltip t = new Tooltip();
						TextArea text = new TextArea();
						text.setText(tooltip.toString());
						t.setLocation(getWindow().getMouseLocation());
						t.setContent(text);
						t.open(getWindow());
						return false;
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
					public boolean mouseClick(Component arg0,
							org.apache.pivot.wtk.Mouse.Button arg1, int arg2,
							int arg3, int arg4) {
						_preparationTextArea.getStyles().put("backgroundColor",
								"#FFFFFF");
						return true;
					}

					@Override
					public boolean mouseDown(Component arg0,
							org.apache.pivot.wtk.Mouse.Button arg1, int arg2,
							int arg3) {
						return true;
					}

					@Override
					public boolean mouseUp(Component arg0,
							org.apache.pivot.wtk.Mouse.Button arg1, int arg2,
							int arg3) {
						return true;
					}
				});

		// benefits
		_benefitsTextArea.getComponentMouseButtonListeners().add(
				new ComponentMouseButtonListener() {

					@Override
					public boolean mouseClick(Component arg0,
							org.apache.pivot.wtk.Mouse.Button arg1, int arg2,
							int arg3, int arg4) {
						_benefitsTextArea.getStyles().put("backgroundColor",
								"#FFFFFF");
						return true;
					}

					@Override
					public boolean mouseDown(Component arg0,
							org.apache.pivot.wtk.Mouse.Button arg1, int arg2,
							int arg3) {
						return true;
					}

					@Override
					public boolean mouseUp(Component arg0,
							org.apache.pivot.wtk.Mouse.Button arg1, int arg2,
							int arg3) {
						return true;
					}
				});
	}

	/**
	 * Change a parameter
	 * 
	 * @param parameter to change
	 * @param unit the changed unit
	 * @param operator the changed operator
	 * @param value the changed valu
	 */
	private void changeParameter(NutrimentParameterRuleData parameter,
			ParameterDefinitionUnitData unit, CheckOperatorData operator,
			double value, float amount) {
		_parameterTableView.setParameterData(parameter, amount);
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
	 * Validate all textareas
	 * 
	 * @return true if all textareas are valid
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

	/**
	 * Validate all textinputs
	 * 
	 * @return true if all textinputs are valid
	 */
	private boolean validateTextInputs() {
		return _recipeNameTextInput.isTextValid()
				&& _preparationTimeTextInput.isTextValid();
	}
}
