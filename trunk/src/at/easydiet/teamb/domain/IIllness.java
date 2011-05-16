/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.domain;

import at.easydiet.teamb.application.viewobject.IllnessViewable;

/**
 * The Interface IIllness.
 */
public interface IIllness extends IllnessViewable{

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public abstract void setName(String name);

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IllnessViewable#getName()
	 */
	public abstract String getName();

}