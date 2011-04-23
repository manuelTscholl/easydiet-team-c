/**
 * This File is part of Easy Diet
 * created on: 17.04.2011
 * created by: Michael
 * file: ChooseMealDialog.java
 */
package at.easydiet.teamc.view;

import at.easydiet.teamc.controller.GUIController;
import at.easydiet.teamc.model.data.MealCodeData;
import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TreeView;

/**
 * Represents the ChooseMealDialog.bxml
 * @author Michael
 */
public class ChooseMealDialog extends Dialog implements Bindable {

    // instance variables
    private ListButton _mealChooserListButton;
    private PushButton _addRecipeButton;
    private PushButton _removeRecipeButton;
    private PushButton _addAlternativButton;
    private PushButton _finishButton;
    private TreeView _recipeTreeView;
    private TreeView _chosenRecipeTreeView;
    private List<MealCodeData> _mealCodes;

    private int _day;

    public void initialize(Map<String, Object> namespace, URL location, Resources resources) {

        // get GUI components
        _mealChooserListButton = (ListButton) namespace.get("mealChooserListButton");
        _addRecipeButton = (PushButton) namespace.get("addRecipeButton");
        _removeRecipeButton = (PushButton) namespace.get("removeRecipeButton");
        _addAlternativButton = (PushButton) namespace.get("addAlternativeButton");
        _finishButton = (PushButton) namespace.get("finishButton");
        _recipeTreeView = (TreeView) namespace.get("recipeTreeView");
        _chosenRecipeTreeView = (TreeView) namespace.get("chosenRecipeTreeView");

        // get meal codes
        _mealCodes = GUIController.getInstance().getAllMealCodes(); 
        _mealChooserListButton.setListData(_mealCodes);
        
        // get recipe main categories
        GUIController.getInstance().getRecipeMainCategories();
        
        // add button listeners
        _finishButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {
                GUIController.getInstance().saveDietryPlan();
                ((ContentDietryPlanScrollPane)GUIComponents.get("contentDietryPlanScrollPane")).updateDietryPlan();
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
}
