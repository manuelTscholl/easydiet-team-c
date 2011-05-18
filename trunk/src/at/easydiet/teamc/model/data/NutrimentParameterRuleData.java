/**
 * This File is part of EasyDiet
 * Created on: 06.05.2011
 * Created by: Michael
 * File: NutrimentParameterRule.java
 */
package at.easydiet.teamc.model.data;

/**
 * Encapsulation of a NutrimentParameterRuleBo.
 * 
 * @author Michael
 */
public interface NutrimentParameterRuleData {

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public double getValue();

	/**
	 * Checks if is violated.
	 * 
	 * @return true, if successful
	 */
	public boolean IsViolated();

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName();

	/**
	 * Gets the check operator.
	 * 
	 * @return the check operator
	 */
	public CheckOperatorData getCheckOperator();

	/**
	 * Gets the row.
	 * 
	 * @return the row
	 */
	public int getRow();

	/**
	 * Gets the parameter definition data.
	 * 
	 * @return the parameter definition data
	 */
	public ParameterDefinitionData getParameterDefinitionData();

	/**
	 * Gets the unit.
	 * 
	 * @return the unit
	 */
	public ParameterDefinitionUnitData getUnit();
}
