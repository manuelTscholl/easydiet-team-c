package at.easydiet.teamb.domain.util;

import org.apache.log4j.Logger;

import at.easydiet.model.CheckOperator;
import at.easydiet.model.DietPlan;
import at.easydiet.model.PlanType;

/**
 * Represents a PlanType in the domain layer
 */
public enum PlanTypeEnum{
	
	/** Dietplan type for the {@link DietPlan} */
	DIETPLAN("Diätplan"),
	/** Diet Recommendation type for the {@link DietPlan} */
	DIET_RECOMMENDATION("Ernährungsempfehlung"),
	/** Nutrition Protocol type for the {@link DietPlan} */
	NUTRITION_PROTOCOL("Ernährungsprotokoll"),
	/** Meal plan type for the {@link DietPlan} */
	MEAL_PLAN("Speiseplan"),
	/** Weight protocol type for the {@link DietPlan} */
	WEIGHT_PROTOCOL("Wägeprotokoll");
	
	
	private static Logger LOGGER = Logger.getLogger(PlanTypeEnum.class);

	private PlanType _planType;
	
	private PlanTypeEnum(String name) {
		_planType = new PlanType(name);
	}

	/**
	 * Gets the planType object
	 * 
	 * @return PlanType
	 */
	public PlanType getPlanType(){
		return _planType;
	}
	
	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public String getName() {
		return _planType.getName();
	}

	/**
	 * Sets the name of this instance.
	 * 
	 * @param name
	 *            the new name of this instance.
	 */
	public void setName(String name) {
		_planType.setName(name);
	}
	
	/**
	 * Converts a normal {@link CheckOperator} into a {@link PlanTypeEnum}
	 * @param planType which you want to know as {@link PlanTypeEnum}
	 * @return the enum of the {@link PlanTypeEnum}
	 */
	public static PlanTypeEnum getPlanTypeDO(PlanType planType) {
		if(planType != null){
			for (PlanTypeEnum planTypeDO : PlanTypeEnum.values()) {
				if(planTypeDO.getName().equals(planType.getName())) {
					return planTypeDO;
				}
			}
			LOGGER.warn(String.format("Couldn't find the PlanType: '%s' in the list", planType.getName()));
			return DIETPLAN;
		} 
		LOGGER.warn(String.format("PlanType is null"));
		return null;
	}
}
