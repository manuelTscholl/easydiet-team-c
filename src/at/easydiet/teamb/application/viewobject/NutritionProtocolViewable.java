package at.easydiet.teamb.application.viewobject;

import java.util.GregorianCalendar;

/**
 * Represents a NutritionProtocol
 */
public interface NutritionProtocolViewable extends DietPlanViewable {

	/**
	 * Gets the date of this instance.
	 * 
	 * @return the date currently set for this instance.
	 */
	public GregorianCalendar getDate();

	/**
	 * Gets the contact of this instance.
	 * 
	 * @return the contact currently set for this instance.
	 */
	public String getContact();

	/**
	 * Gets the notice of this instance.
	 * 
	 * @return the notice currently set for this instance.
	 */
	public String getNotice();
}
