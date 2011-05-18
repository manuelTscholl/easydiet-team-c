/**
 * This File is part of EasyDiet
 * Created on: 23.04.2011
 * Created by: Michael Sieber
 * File: DietryPlanNavigationMode.java
 */
package at.easydiet.teamc.view.util;

import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TablePane;

import at.easydiet.teamc.controller.GUIController;
import at.easydiet.teamc.view.NavigationTabPane;

/**
 * Mode for dietry plan
 * 
 * @author Michael Sieber
 */
public class DietryPlanNavigationMode extends NavigationMode {

	/**
	 * Create new DietryPlanNavigationMode.
	 * 
	 * @param navTab the nav tab
	 */
	public DietryPlanNavigationMode(NavigationTabPane navTab) {
		_navTab = navTab;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.teamc.view.util.NavigationMode#draw()
	 */
	@Override
	public void draw() {

		// clean navigation before entering
		clean();

		// set start tab
		_navTab.setSelectedTabByName("editTab");

		// add button for creating a new dietry plan dialog
		TablePane.Row addDietPlanRow = new TablePane.Row();
		Button openNewDietPlanDialogButton = new PushButton(
				"Neuen Diätplan erstellen");
		addDietPlanRow.add(openNewDietPlanDialogButton);
		_navTab.getEditTablePane().getRows().add(addDietPlanRow);

		openNewDietPlanDialogButton.getButtonPressListeners().add(
				new ButtonPressListener() {

					@Override
					public void buttonPressed(Button button) {
						GUIController.getInstance().createDietryPlan();
					}
				});

		// add all available diet plans
		// TODO show all dietry plans
		// List<DietryPlanData> dietPlans =
		// GUIController.getInstance().getAllDietryPlans();
		// for(DietryPlanData d: dietPlans){
		// //TODO add dietry plan name
		// TablePane.Row dpRow = new TablePane.Row();
		// ButtonData data = new ButtonData();
		// LinkButton planLink = new LinkButton(data);
		// dpRow.add(planLink);
		// _navTab.getEditTablePane().getRows().add(dpRow);
		// }
	}
}
