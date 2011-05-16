package at.easydiet.teamb.application.viewobject;

import at.easydiet.teamb.domain.util.CheckOperatorEnum;

/**
 * Represents a DietParameterTemplate
 */
public interface DietParameterTemplateViewable {

	/**
	 * Gets the dietParameterTemplateId of this instance.
	 * 
	 * @return the dietParameterTemplateId currently set for this instance.
	 */
	public long getDietParameterTemplateId();

	/**
	 * Gets the checkOperator of this instance.
	 * 
	 * @return the checkOperator currently set for this instance.
	 */
	public CheckOperatorEnum getCheckOperator();

	/**
	 * Gets the duration of this instance.
	 * 
	 * @return the duration currently set for this instance.
	 */
	public int getDuration();

	/**
	 * Gets the value of this instance.
	 * 
	 * @return the value currently set for this instance.
	 */
	public String getValue();

	/**
	 * Gets the dietParameterType of this instance.
	 * 
	 * @return the dietParameterType currently set for this instance.
	 */
	public DietParameterTypeViewable getDietParameterType();

	/**
	 * Gets the parameterDefinition of this instance.
	 * 
	 * @return the parameterDefinition currently set for this instance.
	 */
	public ParameterDefinitionViewable getParameterDefinition();

	/**
	 * Gets the parameterDefinitionUnit of this instance.
	 * 
	 * @return the parameterDefinitionUnit currently set for this instance.
	 */
	public ParameterDefinitionUnitViewable getParameterDefinitionUnit();
}
