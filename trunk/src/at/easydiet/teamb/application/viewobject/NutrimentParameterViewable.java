package at.easydiet.teamb.application.viewobject;

/**
 * Represents a NutrimentParameter
 */
public interface NutrimentParameterViewable {

	/**
	 * Gets the nutrimentParameterId of this instance.
	 * 
	 * @return the nutrimentParameterId currently set for this instance.
	 */
	public long getNutrimentParameterId();

	/**
	 * Gets the value of this instance.
	 * 
	 * @return the value currently set for this instance.
	 */
	public String getValue();

	/**
	 * Gets the parameterDefinition of this instance.
	 * 
	 * @return the parameterDefinition currently set for this instance.
	 */
	public ParameterDefinitionViewable getParameterDefinition();

	/**
	 * Gets the unit of this instance.
	 * 
	 * @return the unit currently set for this instance.
	 */
	public ParameterDefinitionUnitViewable getUnit();
}
