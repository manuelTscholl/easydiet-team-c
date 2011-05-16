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

import at.easydiet.teamb.application.viewobject.PatientStateTypeViewable;
import at.easydiet.model.PatientStateType;

/**
 * The Interface IPatientStateType.
 */
public interface IPatientStateType extends PatientStateTypeViewable {
	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public abstract String getName();

	/**
	 * Sets the name of this instance.
	 * 
	 * @param name
	 *            the new name of this instance.
	 */
	public abstract void setName(String name);

	/**
	 * Checks if the {@link PatientStateType} is set.
	 *
	 * @return true, if the {@link PatientStateType} is set
	 */
	public abstract boolean isSet();

}