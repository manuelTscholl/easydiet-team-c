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

import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.viewobject.DietParameterViewable;
import at.easydiet.teamb.application.viewobject.DietPlanViewable;
import at.easydiet.teamb.application.viewobject.DietTreatmentViewable;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.teamb.application.viewobject.TimeSpanViewable;
import at.easydiet.teamb.domain.util.PlanTypeEnum;
import at.easydiet.model.DietPlan;

/**
 * The Interface IDietPlan.
 */
public interface IDietPlan extends DietPlanViewable {

	/**
	 * Gets the DietPlan object.
	 *
	 * @return DietPlan
	 * @see at.easydiet.model.DietPlan
	 */
	public abstract DietPlan getDietPlan();

	/**
	 * Gets the dietPlanId of this instance.
	 * 
	 * @return the dietPlanId currently set for this instance.
	 */
	public abstract long getDietPlanId();

	/**
	 * Sets the dietPlanId of this instance.
	 * 
	 * @param dietPlanId
	 *            the new dietPlanId of this instance.
	 */
	public abstract void setDietPlanId(long dietPlanId);

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
	 * Gets the createdOn of this instance.
	 * 
	 * @return the createdOn currently set for this instance.
	 */
	public abstract GregorianCalendar getCreatedOn();

	/**
	 * Sets the createdOn of this instance.
	 * 
	 * @param createdOn
	 *            the new createdOn of this instance.
	 */
	public abstract void setCreatedOn(GregorianCalendar createdOn);

	/**
	 * Gets the planType of this instance.
	 * 
	 * @return the planType currently set for this instance.
	 */
	public abstract PlanTypeEnum getPlanType();

	/**
	 * Sets the planType of this instance.
	 * 
	 * @param planType
	 *            the new planType of this instance.
	 */
	public abstract void setPlanType(PlanTypeEnum planType);

	/**
	 * Gets the dietParameters of this instance.
	 * 
	 * @return the dietParameters currently set for this instance.
	 */
	public abstract DietParameterViewable[] getDietParameters();

	/**
	 * Adds a parameter.
	 *
	 * @param dietParameter the parameter to add
	 */
	public abstract void addDietParameter(IDietParameter dietParameter);

	/**
	 * Removes a parameter.
	 *
	 * @param dietParameter the parameter to remove
	 */
	public abstract void removeDietParameter(IDietParameter dietParameter);

	/**
	 * Gets the creator of this instance.
	 * 
	 * @return the creator currently set for this instance.
	 */
	public abstract SystemUserViewable getCreator();

	/**
	 * Sets the creator of this instance.
	 * 
	 * @param creator
	 *            the new creator of this instance.
	 */
	public abstract void setCreator(SystemUserViewable creator);

	/**
	 * Gets the timeSpans of this instance.
	 * 
	 * @return the timeSpans currently set for this instance.
	 */
	public abstract TimeSpanViewable[] getTimeSpans();

	/**
	 * Adds a TimeSpan.
	 *
	 * @param timespan the timeSpan to add
	 */
	public abstract void addTimeSpan(ITimeSpan timespan);

	/**
	 * Remove a TimeSpan.
	 *
	 * @param timespan the timeSpan to remove
	 */
	public abstract void removeTimeSpan(ITimeSpan timespan);

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.DietPlanViewable#getDietTreatment()
	 */
	public abstract DietTreatmentViewable getDietTreatment();

	/**
	 * Sets the dietTreatment of this instance.
	 * 
	 * @param dietTreatment
	 *            the new dietTreatment of this instance.
	 */
	public abstract void setDietTreatment(DietTreatmentViewable dietTreatment);

	/**
	 * Checks if the {@link DietPlan} is set.
	 *
	 * @return true, if the {@link DietPlan} is set
	 */
	public abstract boolean isSet();

	/**
	 * Save diet plan.
	 *
	 * @throws DatabaseException the database exception
	 */
	public abstract void saveDietPlan() throws DatabaseException;
	
	/**
	 * Delete diet plan.
	 *
	 * @throws DatabaseException the database exception
	 */
	public abstract void deleteDietPlan() throws DatabaseException;
}