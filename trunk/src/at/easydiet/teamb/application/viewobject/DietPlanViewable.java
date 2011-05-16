package at.easydiet.teamb.application.viewobject;

import java.util.GregorianCalendar;

import at.easydiet.teamb.domain.util.PlanTypeEnum;

/**
 * Represents a DietPlan
 */
public interface DietPlanViewable {

	/**
	 * Gets the dietPlanId of this instance.
	 * 
	 * @return the dietPlanId currently set for this instance.
	 */
	public long getDietPlanId();

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public String getName();

	/**
	 * Gets the createdOn of this instance.
	 * 
	 * @return the createdOn currently set for this instance.
	 */
	public GregorianCalendar getCreatedOn();

	/**
	 * Gets the planType of this instance.
	 * 
	 * @return the planType currently set for this instance.
	 */
	public PlanTypeEnum getPlanType();

	/**
	 * Gets the dietParameters of this instance.
	 * 
	 * @return the dietParameters currently set for this instance.
	 */
	public DietParameterViewable[] getDietParameters();

	/**
	 * Gets the creator of this instance.
	 * 
	 * @return the creator currently set for this instance.
	 */
	public SystemUserViewable getCreator();

	/**
	 * Gets the timeSpans of this instance.
	 * 
	 * @return the timeSpans currently set for this instance.
	 */
	public TimeSpanViewable[] getTimeSpans();

	/**
	 * Gets the DietTreatment
	 * 
	 * @return the DietTreatment
	 */
	public DietTreatmentViewable getDietTreatment();
}
