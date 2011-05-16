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

import java.util.GregorianCalendar;

import at.easydiet.teamb.application.viewobject.NutritionProtocolViewable;

/**
 * The Interface INutritionProtocol.
 */
public interface INutritionProtocol extends NutritionProtocolViewable {

	/**
	 * Gets the date of this instance.
	 * 
	 * @return the date currently set for this instance.
	 */
	public abstract GregorianCalendar getDate();

	/**
	 * Sets the date of this instance.
	 * 
	 * @param date
	 *            the new date of this instance.
	 */
	public abstract void setDate(GregorianCalendar date);

	/**
	 * Gets the contact of this instance.
	 * 
	 * @return the contact currently set for this instance.
	 */
	public abstract String getContact();

	/**
	 * Sets the contact of this instance.
	 * 
	 * @param contact
	 *            the new contact of this instance.
	 */
	public abstract void setContact(String contact);

	/**
	 * Gets the notice of this instance.
	 * 
	 * @return the notice currently set for this instance.
	 */
	public abstract String getNotice();

	/**
	 * Sets the notice of this instance.
	 * 
	 * @param notice
	 *            the new notice of this instance.
	 */
	public abstract void setNotice(String notice);

	/**
	 * Checks if is sets the.
	 *
	 * @return true, if is sets the
	 */
	public abstract boolean isSet();

}