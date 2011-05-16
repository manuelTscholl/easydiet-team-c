package at.easydiet.teamb.application.viewobject;

import java.util.GregorianCalendar;

/**
 * Represents a TimeSpan
 */
public interface TimeSpanViewable {

	/**
	 * Gets the timeSpanId of this instance.
	 * 
	 * @return the timeSpanId currently set for this instance.
	 */
	public long getTimeSpanId();

	/**
	 * Gets the start of this instance.
	 * 
	 * @return the start currently set for this instance.
	 */
	public GregorianCalendar getStart();

	/**
	 * Gets the duration of this instance.
	 * 
	 * @return the duration currently set for this instance.
	 */
	public int getDuration();

	/**
	 * Gets the dietParameters of this instance.
	 * 
	 * @return the dietParameters currently set for this instance.
	 */
	public DietParameterViewable[] getDietParameters();

	/**
	 * Gets the meals of this instance.
	 * 
	 * @return the meals currently set for this instance.
	 */
	public MealViewable[] getMeals();

	/**
	 * Gets the endDate
	 *
	 * @return the end
	 */
	public GregorianCalendar getEnd();

	/**
	 * Gets the dietPlan
	 *
	 * @return the dietPlan
	 */
	public DietPlanViewable getDietPlan();
}
