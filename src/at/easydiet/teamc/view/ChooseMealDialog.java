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
import at.easydiet.teamc.view.util.QuantityListener;
import java.io.IOException;
import java.net.URL;
import org.apache.pivot.beans.BXMLSerializer;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence.Tree.Path;
import org.apache.pivot.collections.Set;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Accordion;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentKeyListener;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.Keyboard.KeyLocation;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TreeView;
import org.apache.pivot.wtk.TreeViewBranchListener;
import org.apache.pivot.wtk.content.TreeBranch;

/**
 * Represents the ChooseMealDialog.bxml
 * @author Michael
 */
public class ChooseMealDialog extends Dialog implements Bindable, QuantityListener {

    // class variables
    public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ChooseMealDialog.class);
    // instance variables
    private PushButton _chooseMealButton;
    private ScrollPane _recipeScrollPane;
    private TablePane _recipeTablePane;
    private TextInput _searchTextInput;
    private PushButton _searchButton;
    private ListButton _mealChooserListButton;
    private PushButton _addRecipeButton;
    private PushButton _removeRecipeButton;
    private PushButton _addAlternativeButton;
    private PushButton _finishButton;
    private TreeView _recipeTreeView;
    private Accordion _chosenRecipeAccordion;
    private ScrollPane _chosenRecipeScrollPane;
    private List<MealCodeData> _mealCodes;
    private int _day;
    private List<MealLineBoxPane> _mealLines;
    private RecipeData _actualRecipeData;
    private MealLineBoxPane _actualMealLineBoxPane;
    private ChooseMealDialog _chooseMealDialog;

    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
        _mealLines = new ArrayList<MealLineBoxPane>();
        _chooseMealDialog = this;

        // get GUI components
        _chooseMealButton = (PushButton) namespace.get("chooseMealButton");
        _recipeScrollPane = (ScrollPane) namespace.get("recipeScrollPane");
        _recipeTablePane = (TablePane) namespace.get("recipeTablePane");
        _searchTextInput = (TextInput) namespace.get("recipeSearchTextInput");
        _searchButton = (PushButton) namespace.get("searchButton");
        _mealChooserListButton = (ListButton) namespace.get("mealChooserListButton");
        _addRecipeButton = (PushButton) namespace.get("addRecipeButton");
        _removeRecipeButton = (PushButton) namespace.get("removeRecipeButton");
        _addAlternativeButton = (PushButton) namespace.get("addAlternativeButton");
        _finishButton = (PushButton) namespace.get("finishButton");
        _recipeTreeView = (TreeView) namespace.get("recipeTreeView");
        _chosenRecipeScrollPane = (ScrollPane) namespace.get("chosenRecipeScrollPane");
        _chosenRecipeAccordion = (Accordion) namespace.get("chosenMealAccordion");

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
                _recipeScrollPane.setVisible(true);
                _chosenRecipeScrollPane.setVisible(true);
                _addAlternativeButton.setEnabled(true);
                _finishButton.setEnabled(true);
                _addRecipeButton.setEnabled(true);
                _removeRecipeButton.setEnabled(true);

                // add first meal
                addMealLine();
            }
        });
        _finishButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {
                GUIController.getInstance().saveDietryPlan();         
                close();
            }
        });
        getComponentKeyListeners().add(new ComponentKeyListener() {

            public boolean keyTyped(Component component, char character) {
                return true;
            }

            public boolean keyPressed(Component component, int keyCode, KeyLocation keyLocation) {
                final int enterKey = 10;
                
                // check if enter button is pressed
                if(keyCode == 10){
                    _finishButton.press();
                }
                return true;
            }

            public boolean keyReleased(Component component, int keyCode, KeyLocation keyLocation) {
                return true;
            }
        });

        // listener for additional ingredients
        _addAlternativeButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {
                addMealLine();
            }
        });

        // add listener for add recipe button
        _addRecipeButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {

                // check if a node is selected and not a branch
                if (_recipeTreeView.getSelectedNode() instanceof RecipeTreeNode) {

                    // get selected recipe
                    _actualRecipeData = ((RecipeTreeNode) _recipeTreeView.getSelectedNode()).getRecipeData();
                    _actualMealLineBoxPane = (MealLineBoxPane) _chosenRecipeAccordion.getSelectedPanel();
                    _actualMealLineBoxPane.addRecipe(_actualRecipeData);

                    // load dialog for creating a new dietplan
                    BXMLSerializer bxml = new BXMLSerializer();
                    QuantityDialog quantityDialog;
                 
                    try {
                        quantityDialog = (QuantityDialog) bxml.readObject(ChooseMealDialog.class,
                                "bxml/quantityPopup.bxml");
                        quantityDialog.open(getWindow());
                        quantityDialog.addQuantityListener(_chooseMealDialog);
                    } catch (IOException ex) {
                        LOGGER.error(ex);
                    } catch (SerializationException ex) {
                        LOGGER.error(ex);
                    }

                }
            }
        });

        // add listener for removing recipes
        _removeRecipeButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {

                int selected = _chosenRecipeAccordion.getSelectedIndex();
                MealLineBoxPane m = _mealLines.get(selected);
                RecipeData r = m.getSelectedRecipe();
                m.removeRecipe(r);
            }
        });

        // listener for searching
        _searchButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {

                // get search string
                String search = _searchTextInput.getText();

                if (!search.equals("")) {
                    Set<CheckedRecipeVo> checkedRecipes =
                            GUIController.getInstance().searchRecipe(null, search);

                    // clean tree view
                    _recipeTreeView.getTreeData().remove(0, _recipeTreeView.getTreeData().getLength());

                    // add search results
                    TreeBranch tree = new TreeBranch();
                    for (CheckedRecipeVo c : checkedRecipes) {
                        RecipeTreeNode node = new RecipeTreeNode(c);
                        tree.add(node);
                    }

                    _recipeTreeView.setTreeData(tree);

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
        List<RecipeData> categories = GUIController.getInstance().getRecipeMainCategories();

        TreeBranch startBranch = new TreeBranch();
        for (RecipeData r : categories) {

            // create main categories
            if (r.getBlsCode().length() == 1) {
                RecipeTreeBranch recipe = new RecipeTreeBranch(r, true);
                startBranch.add(recipe);
            }
        }

        _recipeTreeView.setTreeData(startBranch);
        addSubToMainCategory(categories);

        // style tree view
        _recipeTreeView.getStyles().put("spacing", "1");

        // add listener for opening main categories
        _recipeTreeView.getTreeViewBranchListeners().add(new TreeViewBranchListener() {

            public void branchExpanded(TreeView treeView, Path path) {

                // get main category branch
                RecipeTreeBranch branch = ((RecipeTreeBranch) treeView.getTreeData().get(path.toArray()[0]));

                // get sub category
                if (path.getLength() > 1) { //TODO schöner machen :P
                    RecipeTreeBranch sub = (RecipeTreeBranch) branch.get(path.toArray()[1]);

                    String blsSearch = sub.getRecipeData().getBlsCode();
                    Set<CheckedRecipeVo> checkedRecipes =
                            GUIController.getInstance().searchRecipe(blsSearch, null);

                    // check if already loaded
                    if (sub.getLength() <= 0) {

                        // add recipes
                        for (CheckedRecipeVo c : checkedRecipes) {
                            RecipeTreeNode r = new RecipeTreeNode(c);
                            sub.add(r);
                        }
                    }
                }

            }

            public void branchCollapsed(TreeView treeView, Path path) {
                // nothing to do here
            }
        });

    }

    /**
     * Add sub categories to the corresponding main categories
     * @param recipes Recipes to insert
     */
    private void addSubToMainCategory(List<RecipeData> recipes) {
        final int mainCategoryLength = 1;
        final int subCategoryLength = 2;

        for (RecipeData c : recipes) {

            // get bls code
            String blsCode = c.getBlsCode();

            // filter main categories
            if (blsCode.length() > mainCategoryLength) {
                RecipeTreeBranch recipeBranch = getBranchByBLSCode(blsCode.substring(0, 1));
                RecipeTreeBranch r = new RecipeTreeBranch(c, false);
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

    /**
     * Add a new meal line
     */
    private void addMealLine() {
        MealLineBoxPane m = new MealLineBoxPane();
        Accordion.setHeaderData(m, "Bestandteil " + (_mealLines.getLength() + 1));
        _chosenRecipeAccordion.getPanels().add(m);
        _mealLines.add(m);

        _chosenRecipeAccordion.setSelectedIndex(_mealLines.getLength() - 1);

        m.setMealLineIndex(GUIController.getInstance().addMealLine());
    }

    /**
     * Update quantity
     * @param quantity 
     */
    public void updateQuantity(float quantity) {

        // add recipe
        GUIController.getInstance().addRecipeToMeal(_actualRecipeData, quantity, _actualMealLineBoxPane.getMealLineIndex());

        // reload recipe list
        initRecipeMainCategories();
    }
}
