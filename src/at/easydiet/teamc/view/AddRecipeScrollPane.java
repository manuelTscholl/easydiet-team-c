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
import org.apache.pivot.wtk.TextArea;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TreeView;
import org.apache.pivot.wtk.TreeViewBranchListener;

import at.easydiet.teamc.controller.GUIController;
import at.easydiet.teamc.model.data.NutrimentParameterRuleVo;
import at.easydiet.teamc.model.data.RecipeData;

/**
 * This class represents the dialog for creating a new recipe (addRecipe.bxml)
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
	private ParameterTableView _parameterTableView;
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
		 * L�nge f�r Rezeptname �berpr�fen, Rezeptname erlaubte Zeichen
		 * validieren, �berpr�fen ob Rezeptname bereits vergeben ist,
		 * Parameterwerte validieren, Menge validieren, Zubereitungszeit
		 * validieren
		 */

		// TODO eigene Komponente f�r parameter tableview und
		// chosenrezept tableview

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
		// TODO getAllNutrimentParameters -> cachen
		_recipesMainCategories = GUIController.getInstance()
				.getRecipeMainCategories();

		// TODO set unit list button data
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
							}
						}
					}
				});

		_removeRecipeButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button arg0) {

						RecipeData remove = _chosenRecipeTableView
								.getTableData()
								.get(_chosenRecipeTableView.getSelectedIndex())
								.get("recipe");

						// get selected recipe
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
						// TODO Auto-generated method stub
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