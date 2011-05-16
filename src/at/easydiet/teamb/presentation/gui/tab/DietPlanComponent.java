/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	25.04.2011
 */

package at.easydiet.teamb.presentation.gui.tab;

import org.apache.pivot.wtk.BoxPane;

import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.model.DietPlan;

/**
 * The Class DietPlanComponent.
 */
public abstract class DietPlanComponent extends BoxPane {

	/**
	 * Save the {@link DietPlan}.
	 *
	 * @throws DatabaseException the database exception
	 * @throws ErrorInFormException the error in form exception
	 */
	public abstract void save() throws DatabaseException, ErrorInFormException;

}
