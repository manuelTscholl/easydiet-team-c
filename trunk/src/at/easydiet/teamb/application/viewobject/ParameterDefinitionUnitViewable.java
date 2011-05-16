package at.easydiet.teamb.application.viewobject;

import at.easydiet.teamb.domain.util.ParameterDefinitionDataTypeEnum;

/**
 * Represents a ParameterDefinitionUnit
 */
public interface ParameterDefinitionUnitViewable {

	/**
	 * Gets the parameterDefinitionUnitId of this instance.
	 * 
	 * @return the parameterDefinitionUnitId currently set for this instance.
	 */
	public long getParameterDefinitionUnitId();

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public String getName();

	/**
	 * Gets the type of this instance.
	 * 
	 * @return the type currently set for this instance.
	 */
	public ParameterDefinitionDataTypeEnum getType();
}
