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

import at.easydiet.teamb.application.viewobject.NutrimentParameterViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionViewable;
import at.easydiet.model.NutrimentParameter;
import at.easydiet.model.ParameterDefinitionUnit;

/**
 * The Interface INutrimentParameter.
 */
public interface INutrimentParameter extends NutrimentParameterViewable {

	/**
	 * Gets the nutrimentParameterId of this instance.
	 * 
	 * @return the nutrimentParameterId currently set for this instance.
	 */
	public abstract long getNutrimentParameterId();

	/**
	 * Sets the nutrimentParameterId of this instance.
	 * 
	 * @param nutrimentParameterId
	 *            the new nutrimentParameterId of this instance.
	 */
	public abstract void setNutrimentParameterId(long nutrimentParameterId);

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
	 * Gets the unit of this instance.
	 * 
	 * @return the unit currently set for this instance.
	 */
	public abstract ParameterDefinitionUnitViewable getUnit();

	/**
	 * Sets the unit of this instance.
	 * 
	 * @param unit
	 *            the new unit of this instance.
	 */
	public abstract void setUnit(ParameterDefinitionUnit unit);

	/**
	 * Checks if the {@link NutrimentParameter} is set.
	 *
	 * @return true, if the {@link NutrimentParameter} is set
	 */
	public abstract boolean isSet();

}