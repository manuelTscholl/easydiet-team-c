/**
 * This File is part of Easy Diet
 * created on: 17.04.2011
 * created by: Michael
 * file: ChooseMealDialog.java
 */
package at.easydiet.teamc.view;

import at.easydiet.teamc.controller.GUIController;
import at.easydiet.teamc.model.data.CheckedRecipeVo;
import at.easydiet.teamc.model.data.MealCodeData;
import at.easydiet.teamc.model.data.RecipeData;
import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence.Tree.Path;
import org.apache.pivot.collections.Set;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TreeView;
import org.apache.pivot.wtk.TreeViewBranchListener;
import org.apache.pivot.wtk.content.TreeBranch;

/**
 * Represents the ChooseMealDialog.bxml
 * @author Michael
 */
public class ChooseMealDialog extends Dialog implements Bindable {

    // instance variables
    private PushButton _chooseMealButton;
    private TablePane _recipeTablePane;
    private TextInput _searchTextInput;
    private PushButton _searchButton;
    private ListButton _mealChooserListButton;
    private PushButton _addRecipeButton;
    private PushButton _removeRecipeButton;
    private PushButton _addAlternativeButton;
    private PushButton _finishButton;
    private TreeView _recipeTreeView;
    private TreeView _chosenRecipeTreeView;
    private List<MealCodeData> _mealCodes;
    private int _day;

    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {

        // get GUI components
        _chooseMealButton = (PushButton) namespace.get("chooseMealButton");
        _recipeTablePane = (TablePane) namespace.get("recipeTablePane");
        _searchTextInput = (TextInput) namespace.get("searchtTextInput");
        _searchButton = (PushButton) namespace.get("searchButton");
        _mealChooserListButton = (ListButton) namespace.get("mealChooserListButton");
        _addRecipeButton = (PushButton) namespace.get("addRecipeButton");
        _removeRecipeButton = (PushButton) namespace.get("removeRecipeButton");
        _addAlternativeButton = (PushButton) namespace.get("addAlternativeButton");
        _finishButton = (PushButton) namespace.get("finishButton");
        _recipeTreeView = (TreeView) namespace.get("recipeTreeView");
        _chosenRecipeTreeView = (TreeView) namespace.get("chosenRecipeTreeView");

        // get meal codes
        _mealCodes = GUIController.getInstance().getAllMealCodes();
        _mealChooserListButton.setListData(_mealCodes);
        _mealChooserListButton.setSelectedIndex(0);

        initRecipeMainCategories();

        // add button listeners
        _chooseMealButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {

                // get and add mealCode Object
                MealCodeData m = (MealCodeData) _mealChooserListButton.getSelectedItem();
                GUIController.getInstance().addMealCode(m, _day);

                // activate context
                _recipeTablePane.setVisible(true);
                _chosenRecipeTreeView.setVisible(true);
                _addAlternativeButton.setEnabled(true);
                _finishButton.setEnabled(true);
                _addRecipeButton.setEnabled(true);
                _removeRecipeButton.setEnabled(true);
            }
        });
        _finishButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {
                GUIController.getInstance().saveDietryPlan();
                ((ContentDietryPlanScrollPane) GUIComponents.get("contentDietryPlanScrollPane")).updateDietryPlan();
                close();
            }
        });
        _searchButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {

                // check if search string is available
                if (_searchTextInput.getText() != "") {
                    Set<CheckedRecipeVo> checkedRecipes =
                            GUIController.getInstance().searchRecipe(null, _searchTextInput.getText());
                    //TODO add these recipes to the corresponding main categories
                }
            }
        });
    }

    /**
     * Add day for which this meal is for
     * @param day 
     */
    public void setDay(int day) {
        _day = day;
    }

    private void initRecipeMainCategories() {

        // get recipe main categories
        Set<RecipeData> categories = GUIController.getInstance().getRecipeMainCategories();

        TreeBranch startBranch = new TreeBranch();
        for (RecipeData r : categories) {
            RecipeTreeBranch recipe = new RecipeTreeBranch(r);
            startBranch.add(recipe);
        }
        _recipeTreeView.setTreeData(startBranch);

        // add listener for opening main categories
        _recipeTreeView.getTreeViewBranchListeners().add(new TreeViewBranchListener() {

            public void branchExpanded(TreeView treeView, Path path) {

                // get selected branch
                RecipeTreeBranch branch = ((RecipeTreeBranch) treeView.getTreeData().get(path.toArray()[0]));
                String blsSearch = branch.getRecipeData().getBlsCode().substring(0, 2);
                Set<CheckedRecipeVo> checkedRecipes =
                        GUIController.getInstance().searchRecipe(blsSearch, null);

                // add recipes
                for (CheckedRecipeVo c : checkedRecipes) {
                    System.out.println(c.getRecipeData().getBlsCode());
                }
                addRecipesToCategory(checkedRecipes);

            }

            public void branchCollapsed(TreeView treeView, Path path) {
                // nothing to do here
            }
        });

    }

    /**
     * Classifies recipes and put them into the correct category
     * @param recipes Recipes to insert
     */
    private void addRecipesToCategory(Set<CheckedRecipeVo> recipes) {
        for (CheckedRecipeVo c : recipes) {

            // main category 0
            final String mainCategory = "00000";

            // get bls code
            String blsCode = c.getRecipeData().getBlsCode();

            // filter main categories
            if (!blsCode.contains(mainCategory)) {
                RecipeTreeBranch recipeBranch = getBranchByBLSCode(blsCode.substring(0, 1));
                RecipeTreeNode r = new RecipeTreeNode(c);
                recipeBranch.add(r);
            }
        }
    }

    /**
     * Get a recipe tree branch by his bls code
     * @param blsCode
     * @return 
     */
    private RecipeTreeBranch getBranchByBLSCode(String blsCode) {
        for (RecipeTreeBranch t : (List<RecipeTreeBranch>) _recipeTreeView.getTreeData()) {
            if (t.getRecipeData().getBlsCode().contains(blsCode)) {
                return t;
            }
        }

        return null;
    }
}
