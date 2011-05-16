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

import at.easydiet.teamb.application.viewobject.DietParameterViewable;
import at.easydiet.teamb.application.viewobject.DietPlanViewable;
import at.easydiet.teamb.application.viewobject.MealViewable;
import at.easydiet.teamb.application.viewobject.TimeSpanViewable;
import at.easydiet.model.TimeSpan;

/**
 * The Interface ITimeSpan.
 */
public interface ITimeSpan extends TimeSpanViewable{

	/**
	 * Gets the timeSpanId of this instance.
	 * 
	 * @return the timeSpanId currently set for this instance.
	 */
	public abstract long getTimeSpanId();

	/**
	 * Sets the timeSpanId of this instance.
	 * 
	 * @param timeSpanId
	 *            the new timeSpanId of this instance.
	 */
	public abstract void setTimeSpanId(long timeSpanId);

	/**
	 * Gets the start of this instance.
	 * 
	 * @return the start currently set for this instance.
	 */
	public abstract GregorianCalendar getStart();

	/**
	 * Sets the start of this instance.
	 * 
	 * @param start
	 *            the new start of this instance.
	 */
	public abstract void setStart(GregorianCalendar start);

	/**
	 * Gets the duration of this instance.
	 * 
	 * @return the duration currently set for this instance.
	 */
	public abstract int getDuration();

	/**
	 * Sets the duration of this instance.
	 * 
	 * @param duration
	 *            the new duration of this instance.
	 */
	public abstract void setDuration(int duration);

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
	 * Gets the meals of this instance.
	 * 
	 * @return the meals currently set for this instance.
	 */
	public abstract MealViewable[] getMeals();

	/**
	 * Adds the meal.
	 *
	 * @param meal the meal
	 */
	public abstract void addMeal(IMeal meal);

	/**
	 * Removes the meal.
	 *
	 * @param meal the meal
	 */
	public abstract void removeMeal(IMeal meal);

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.TimeSpanViewable#getEnd()
	 */
	public abstract GregorianCalendar getEnd();

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.TimeSpanViewable#getDietPlan()
	 */
	public abstract DietPlanViewable getDietPlan();

	/**
	 * Sets the diet plan.
	 *
	 * @param DietPlan the new diet plan
	 */
	public abstract void setDietPlan(DietPlanViewable DietPlan);

	/**
	 * Checks if the {@link TimeSpan} is set.
	 *
	 * @return true, if the {@link TimeSpan} is set
	 */
	public abstract boolean isSet();

}