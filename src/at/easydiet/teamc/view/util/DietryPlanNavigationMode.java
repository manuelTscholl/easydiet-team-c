/**
 * This File is part of EasyDiet
 * Created on: 23.04.2011
 * Created by: Michael Sieber
 * File: DietryPlanNavigationMode.java
 */
package at.easydiet.teamc.view.util;

import at.easydiet.teamc.controller.GUIController;
import at.easydiet.teamc.view.NavigationTabPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;

/**
 * Mode for dietry plan
 * @author Michael Sieber
 */
public class DietryPlanNavigationMode extends NavigationMode {

    // instance variables
    /**
     * Create new DietryPlanNavigationMode
     * @param navTab 
     */
    public DietryPlanNavigationMode(NavigationTabPane navTab) {
        _navTab = navTab;
    }

    @Override
    public void draw() {

        // clean navigation before entering
        clean();

        // set start tab
        _navTab.setSelectedTabByName("editTab");

        // add button for creating a new dietry plan dialog
        TablePane.Row addDietPlanRow = new TablePane.Row();
        Button openNewDietPlanDialogButton = new PushButton("Neuen Diätplan erstellen");
        addDietPlanRow.add(openNewDietPlanDialogButton);
        _navTab.getEditTablePane().getRows().add(addDietPlanRow);

        openNewDietPlanDialogButton.getButtonPressListeners().add(new ButtonPressListener() {

            public void buttonPressed(Button button) {
                GUIController.getInstance().createDietryPlan();
            }
        });
    }
}
