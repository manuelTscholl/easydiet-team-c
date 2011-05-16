/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.domain;

import at.easydiet.teamb.application.viewobject.DietParameterTemplateViewable;
import at.easydiet.teamb.application.viewobject.DietParameterTypeViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionViewable;
import at.easydiet.teamb.domain.util.CheckOperatorEnum;

/**
 * The Interface IDietParameterTemplate.
 */
public interface IDietParameterTemplate extends DietParameterTemplateViewable {

	/**
	 * Gets the dietParameterTemplateId of this instance.
	 * 
	 * @return the dietParameterTemplateId currently set for this instance.
	 */
	public abstract long getDietParameterTemplateId();

	/**
	 * Sets the dietParameterTemplateId of this instance.
	 * 
	 * @param dietParameterTemplateId
	 *            the new dietParameterTemplateId of this instance.
	 */
	public abstract void setDietParameterTemplateId(long dietParameterTemplateId);

	/**
	 * Gets the checkOperator of this instance.
	 * 
	 * @return the checkOperator currently set for this instance.
	 */
	public abstract CheckOperatorEnum getCheckOperator();

	/**
	 * Sets the checkOperator of this instance.
	 * 
	 * @param checkOperator
	 *            the new checkOperator of this instance.
	 */
	public abstract void setCheckOperator(CheckOperatorEnum checkOperator);

	/**
	 * Gets the duration of this instance.
	 * 
	 * @return the duration currently set for this instance.
	 */
	public abstract int getDuration();

	/**
	 * Sets the duration of this instance.
	 * 
	 * @param duration
	 *            the new duration of this instance.
	 */
	public abstract void setDuration(int duration);

	/**
	 * Gets the value of this instance.
	 * 
	 * @return the value currently set for this instance.
	 */
	public abstract String getValue();

	/**
	 * Sets the value of this instance.
	 * 
	 * @param value
	 *            the new value of this instance.
	 */
	public abstract void setValue(String value);

	/**
	 * Gets the dietParameterType of this instance.
	 * 
	 * @return the dietParameterType currently set for this instance.
	 */
	public abstract DietParameterTypeViewable getDietParameterType();

	/**
	 * Sets the dietParameterType of this instance.
	 * 
	 * @param dietParameterType
	 *            the new dietParameterType of this instance.
	 */
	public abstract void setDietParameterType(DietParameterTypeViewable dietParameterType);

	/**
	 * Gets the parameterDefinition of this instance.
	 * 
	 * @return the parameterDefinition currently set for this instance.
	 */
	public abstract ParameterDefinitionViewable getParameterDefinition();

	/**
	 * Sets the parameterDefinition of this instance.
	 * 
	 * @param parameterDefinition
	 *            the new parameterDefinition of this instance.
	 */
	public abstract void setParameterDefinition(ParameterDefinitionViewable parameterDefinition);

	/**
	 * Gets the parameterDefinitionUnit of this instance.
	 * 
	 * @return the parameterDefinitionUnit currently set for this instance.
	 */
	public abstract ParameterDefinitionUnitViewable getParameterDefinitionUnit();

	/**
	 * Sets the parameterDefinitionUnit of this instance.
	 * 
	 * @param parameterDefinitionUnit
	 *            the new parameterDefinitionUnit of this instance.
	 */
	public abstract void setParameterDefinitionUnit(ParameterDefinitionUnitViewable parameterDefinitionUnit);

	/**
	 * Checks if is sets the.
	 * 
	 * @return true, if is sets the
	 */
	public abstract boolean isSet();

}