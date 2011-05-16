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

import at.easydiet.teamb.application.viewobject.UserRightViewable;
import at.easydiet.model.UserRight;

// TODO: Auto-generated Javadoc
/**
 * The Interface IUserRight.
 */
public interface IUserRight extends UserRightViewable {

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public abstract String getName();

	/**
	 * Sets the name of this instance.
	 * 
	 * @param name the new name of this instance.
	 */
	public abstract void setName(String name);

	/**
	 * Checks if the {@link UserRight} is set.
	 *
	 * @return true, if the {@link UserRight} is set
	 */
	public abstract boolean isSet();
}