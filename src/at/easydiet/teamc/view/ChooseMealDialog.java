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
        _recipeTablePane = (TablePane)namespace.get("recipeTablePane");
        _searchTextInput = (TextInput)namespace.get("searchtTextInput");
        _searchButton = (PushButton)namespace.get("searchButton");
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

        initRecipeMainCategories();

        // add button listeners
        _chooseMealButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {

                //TODO activate and add mealCode Object
                //GUIController.getInstance().addMealCode(null, _day);

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
                Set<CheckedRecipeVo> checkedRecipes = 
                        GUIController.getInstance().searchRecipe(branch.getRecipeData().getName(), null);
                
                // add recipes
                for(CheckedRecipeVo checked:checkedRecipes){
                    RecipeTreeNode r = new RecipeTreeNode(checked);
                }
            }

            public void branchCollapsed(TreeView treeView, Path path) {
                // nothing to do here
            }
        });

    }
}
