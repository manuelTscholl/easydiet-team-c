package at.easydiet.teamb.domain.util;

import org.apache.log4j.Logger;

import at.easydiet.model.CheckOperator;
import at.easydiet.model.Gender;

/**
 * Represents a GenderDomainObject
 */
public enum GenderEnum {
	/** The Male. */
	MALE("Männlich"),
	/** The Female. */
	FEMALE("Weiblich");
	
	private static Logger LOGGER = Logger.getLogger(GenderEnum.class);

	private Gender _gender;

	/**
	 * Instantiates a new gender do.
	 *
	 * @param gender the gender
	 */
	GenderEnum(String gender) {
		_gender = new Gender(gender);
	}

	/**
	 * Gets the Gender
	 * 
	 * @return the gender
	 */
	public Gender getGender() {
		return _gender;
	}
	
	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public String getName() {
		return _gender.getName();
	}

	/**
	 * Sets the name of this instance.
	 * 
	 * @param name
	 *            the new name of this instance.
	 */
	public void setName(String name) {
		_gender.setName(name);
	}
	
	/**
	 * Converts a normal {@link CheckOperator} into a {@link GenderEnum}
	 * @param gender which you want to know as {@link GenderEnum}
	 * @return the enum of the {@link GenderEnum}
	 */
	public static GenderEnum getGenderDO(Gender gender) {
		if(gender != null){
			for (GenderEnum planTypeDO : GenderEnum.values()) {
				if(planTypeDO.getName().equals(gender.getName())) {
					return planTypeDO;
				}
			}
			LOGGER.warn(String.format("Couldn't find the PlanType: '%s' in the list", gender.getName()));
			return MALE;
		}
		
		LOGGER.debug(String.format("Gender is null"));
		return null;
	}
}
