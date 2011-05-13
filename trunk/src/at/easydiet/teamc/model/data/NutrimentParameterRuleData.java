/**
 * This File is part of EasyDiet
 * Created on: 06.05.2011
 * Created by: Michael
 * File: NutrimentParameterRule.java
 */
package at.easydiet.teamc.model.data;

/**
 * 
 * @author Michael
 */
public interface NutrimentParameterRuleData {

	public double getValue();

	public boolean IsViolated();

	public String getName();

	public CheckOperatorData getCheckOperator();

	public int getRow();

	public ParameterDefinitionData getParameterDefinitionData();

	public ParameterDefinitionUnitData getUnit();
}
