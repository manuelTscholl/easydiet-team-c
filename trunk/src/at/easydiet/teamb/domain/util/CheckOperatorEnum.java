package at.easydiet.teamb.domain.util;

import org.apache.log4j.Logger;

import at.easydiet.model.CheckOperator;

/**
 * Represents a CheckOperatorDomainObject in the domain layer
 */
public enum CheckOperatorEnum{
	
	/** Equals with the other */
	EQUALS("="), 
	/** Not equals with the other */
	NOT_EQUALS("!="), 
	/** lesser with the other */
	LESSER("<"), 
	/** Lesser equals with the other */
	LESSER_EQUALS("<="), 
	/** bigger with the other */
	BIGGER(">"), 
	/** bigger equals with the other */
	BIGGER_EQUALS(">=");
	
	private static Logger LOGGER = Logger.getLogger(CheckOperatorEnum.class);
	
	CheckOperator _checkOperator;
	
	/**
	 * Instantiates a new {@link CheckOperatorEnum}.
	 *
	 * @param checkOperator the {@link CheckOperator}
	 */
	private CheckOperatorEnum (String checkOperator)
	{
		this._checkOperator = new CheckOperator(checkOperator);
	}
	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return _checkOperator.getName();
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name)
	{
		_checkOperator.setName(name);
	}
	
	/**
	 * Converts a normal {@link CheckOperator} into a {@link CheckOperatorEnum}
	 * @param checkOperator which you want to know as {@link CheckOperatorEnum}
	 * @return the enum of the {@link CheckOperatorEnum}
	 */
	public static CheckOperatorEnum getCheckOperatorDO(CheckOperator checkOperator) {
		if (checkOperator == null) {
			return null;
		}
		
		for (CheckOperatorEnum checkOperatorEnum : CheckOperatorEnum.values()) {
			if(checkOperatorEnum.getName().equals(checkOperator.getName())) {
				return checkOperatorEnum;
			}
		}
		LOGGER.warn(String.format("Couldn't find the CheckOperator: '%s' in the list", checkOperator.getName()));
		return EQUALS;
	}
}
