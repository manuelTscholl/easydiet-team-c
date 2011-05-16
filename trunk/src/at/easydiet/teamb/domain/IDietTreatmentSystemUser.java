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

import at.easydiet.teamb.application.viewobject.DietTreatmentSystemUserViewable;
import at.easydiet.teamb.application.viewobject.SystemUserFunctionViewable;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.model.DietTreatmentSystemUser;

/**
 * The Interface IDietTreatmentSystemUser.
 */
public interface IDietTreatmentSystemUser extends DietTreatmentSystemUserViewable {

	/**
	 * Sets the dietTreatmentSystemUserId of this instance.
	 * 
	 * @param dietTreatmentSystemUserId
	 *            the new dietTreatmentSystemUserId of this instance.
	 */
	public abstract void setDietTreatmentSystemUserId(int dietTreatmentSystemUserId);

	/**
	 * Gets the systemUser of this instance.
	 * 
	 * @return the systemUser currently set for this instance.
	 */
	public abstract SystemUserViewable getSystemUser();

	/**
	 * Sets the systemUser of this instance.
	 * 
	 * @param systemUser
	 *            the new systemUser of this instance.
	 */
	public abstract void setSystemUser(SystemUserViewable systemUser);

	/**
	 * Gets the function of this instance.
	 * 
	 * @return the function currently set for this instance.
	 */
	public abstract SystemUserFunctionViewable getFunction();

	/**
	 * Sets the function of this instance.
	 * 
	 * @param function
	 *            the new function of this instance.
	 */
	public abstract void setFunction(SystemUserFunctionViewable function);

	/**
	 * Checks if the {@link DietTreatmentSystemUser} is set.
	 *
	 * @return true, if the {@link DietTreatmentSystemUser} is set
	 */
	public abstract boolean isSet();

}