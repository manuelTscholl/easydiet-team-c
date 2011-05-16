package at.easydiet.teamb.domain.util;

import org.apache.log4j.Logger;

import at.easydiet.model.ParameterDefinitionDataType;

/**
 * Represents a ParameterDefinitionDataType in the domain layer
 */
public enum ParameterDefinitionDataTypeEnum{
	
	/** Enumeration for the ParameterDefinitionDataType */
	ENUM("Enumeration"),
	/** Numbers for the ParameterDefinitionDataType */
	NUMBERS("Numbers"),
	/** String for the ParameterDefinitionDataType */
	STRING("String");
	
	
	private static Logger LOGGER = Logger.getLogger(ParameterDefinitionDataTypeEnum.class);

	private ParameterDefinitionDataType _parameterDefinitionDataType;

	private ParameterDefinitionDataTypeEnum(String parameterDefinitionDataType) {
		_parameterDefinitionDataType = new ParameterDefinitionDataType(parameterDefinitionDataType);
	}
	
	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public String getName() {
		return _parameterDefinitionDataType.getName();
	}

	/**
	 * Sets the name of this instance.
	 * 
	 * @param name
	 *            the new name of this instance.
	 */
	public void setName(String name) {
		_parameterDefinitionDataType.setName(name);
	}
	
	/**
	 * Converts a normal {@link ParameterDefinitionDataType} into a {@link ParameterDefinitionDataTypeEnum}
	 * @param parameterDefinitionDataType which you want to know as {@link ParameterDefinitionDataTypeEnum}
	 * @return the enum of the {@link ParameterDefinitionDataTypeEnum}
	 */
	public static ParameterDefinitionDataTypeEnum getCheckOperatorDO(ParameterDefinitionDataType parameterDefinitionDataType) {
		if (parameterDefinitionDataType == null) {
			return null;
		}
		
		for (ParameterDefinitionDataTypeEnum definitionDataTypeDO : ParameterDefinitionDataTypeEnum.values()) {
			if(definitionDataTypeDO.getName().equals(parameterDefinitionDataType.getName())) {
				return definitionDataTypeDO;
			}
		}
		LOGGER.warn(String.format("Couldn't find the CheckOperator: '%s' in the list", parameterDefinitionDataType.getName()));
		return STRING;
	}
}
