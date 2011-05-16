/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	06.05.2011
 */

/**
 * 
 */
package at.easydiet.teamb.domain.util;

import org.apache.log4j.Logger;

import at.easydiet.model.CheckOperator;
import at.easydiet.model.FamilyStatus;

/**
 * @author TeamB
 */
public enum FamilyStatusEnum {

	/**  */
	NONE(null),
	/**	 */
	SINGLE("Ledig"),
	/**	 */
	MARRIED("Verheiratet");

	private static Logger LOGGER = Logger.getLogger(GenderEnum.class);

	private FamilyStatus _familyStatus;

	/**
	 * Instantiates a new gender do.
	 * 
	 * @param familyStatus
	 *            the familyStatus
	 */
	FamilyStatusEnum(String familyStatus) {
		_familyStatus = new FamilyStatus(familyStatus);
	}

	/**
	 * Gets the Gender
	 * 
	 * @return the gender
	 */
	public FamilyStatus getFamilyStatus() {
		return _familyStatus;
	}

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public String getStatus() {
		return _familyStatus.getStatus();
	}

	/**
	 * Sets the name of this instance.
	 * 
	 * @param name
	 *            the new name of this instance.
	 */
	public void setStatus(String name) {
		_familyStatus.setStatus(name);
	}

	/**
	 * Converts a normal {@link CheckOperator} into a {@link GenderEnum}
	 * 
	 * @param status
	 *            which you want to know as {@link GenderEnum}
	 * @return the enum of the {@link GenderEnum}
	 */
	public static FamilyStatusEnum getFamilyStatusDO(FamilyStatus status) {
		if (status != null) {
			for (FamilyStatusEnum planTypeDO : FamilyStatusEnum.values()) {
				if (status != null && status.getStatus().equals(planTypeDO.getStatus())) {
					return planTypeDO;
				}
			}
			LOGGER.debug(String.format("Couldn't find the PlanType: '%s' in the list", status.getStatus()));
			return FamilyStatusEnum.NONE;
		}
		
		LOGGER.debug(String.format("PlanType is null"));
		return FamilyStatusEnum.NONE;
	}

}
