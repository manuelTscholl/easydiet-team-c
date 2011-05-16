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

import at.easydiet.teamb.application.viewobject.DietParameterViewable;
import at.easydiet.teamb.application.viewobject.MealLineViewable;
import at.easydiet.teamb.application.viewobject.MealViewable;
import at.easydiet.teamb.application.viewobject.TimeSpanViewable;
import at.easydiet.model.Meal;

/**
 * The Interface IMeal.
 */
public interface IMeal extends MealViewable {

	/**
	 * Gets the mealId of this instance.
	 * 
	 * @return the mealId currently set for this instance.
	 */
	public abstract long getMealId();

	/**
	 * Sets the mealId of this instance.
	 * 
	 * @param mealId
	 *            the new mealId of this instance.
	 */
	public abstract void setMealId(long mealId);

	/**
	 * Gets the code of this instance.
	 * 
	 * @return the code currently set for this instance.
	 */
	public abstract String getCode();

	/**
	 * Sets the code of this instance.
	 * 
	 * @param code
	 *            the new code of this instance.
	 */
	public abstract void setCode(String code);

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
	 * Gets the dietParameters of this instance.
	 * 
	 * @return the dietParameters currently set for this instance.
	 */
	public abstract DietParameterViewable[] getDietParameters();

	/**
	 * Adds the diet parameter.
	 *
	 * @param dietParameter the diet parameter
	 */
	public abstract void addDietParameter(IDietParameter dietParameter);

	/**
	 * Removes the diet parameter.
	 *
	 * @param dietParameter the diet parameter
	 */
	public abstract void removeDietParameter(IDietParameter dietParameter);

	/**
	 * Gets the mealLines of this instance.
	 * 
	 * @return the mealLines currently set for this instance.
	 */
	public abstract MealLineViewable[] getMealLines();

	/**
	 * Adds the meal line.
	 *
	 * @param mealLine the meal line
	 */
	public abstract void addMealLine(IMealLine mealLine);

	/**
	 * Removes the meal line.
	 *
	 * @param mealLine the meal line
	 */
	public abstract void removeMealLine(IMealLine mealLine);

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.MealViewable#getTimeSpan()
	 */
	public abstract TimeSpanViewable getTimeSpan();

	/**
	 * Sets the time span.
	 * 
	 * @param timespan
	 *            the new time span
	 */
	public abstract void setTimeSpan(TimeSpanViewable timespan);

	/**
	 * Checks if the {@link Meal} is set.
	 *
	 * @return true, if the {@link Meal} is set
	 */
	public abstract boolean isSet();

}