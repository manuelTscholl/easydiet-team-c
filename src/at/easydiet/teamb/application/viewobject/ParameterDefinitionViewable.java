package at.easydiet.teamb.application.viewobject;

/**
 * Represents a ParameterDefinition
 */
public interface ParameterDefinitionViewable {

	/**
	 * Gets the parameterDefinitionId of this instance.
	 * 
	 * @return the parameterDefinitionId currently set for this instance.
	 */
	public long getParameterDefinitionId();

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public String getName();

	/**
	 * Gets the checkPattern of this instance.
	 * 
	 * @return the checkPattern currently set for this instance.
	 */
	public String getCheckPattern();

	/**
	 * Gets the units of this instance.
	 * 
	 * @return the units currently set for this instance.
	 */
	public ParameterDefinitionUnitViewable[] getUnits();
}
