/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.domain;

import at.easydiet.teamb.application.viewobject.TreatmentStateViewable;
import at.easydiet.model.TreatmentState;

/**
 * The Interface ITreatmentState.
 */
public interface ITreatmentState extends TreatmentStateViewable{

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.TreatmentStateViewable#getName()
	 */
	public abstract String getName();

	/**
	 * Sets the name of this instance.
	 * 
	 * @param name the new name of this instance.
	 */
	public abstract void setName(String name);

	/**
	 * Checks if the {@link TreatmentState} is set.
	 *
	 * @return true, if the {@link TreatmentState} is set
	 */
	public abstract boolean isSet();

}