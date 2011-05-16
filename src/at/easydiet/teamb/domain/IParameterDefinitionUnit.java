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

import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.domain.util.ParameterDefinitionDataTypeEnum;
import at.easydiet.model.ParameterDefinitionDataType;
import at.easydiet.model.ParameterDefinitionUnit;

/**
 * The Interface IParameterDefinitionUnit.
 */
public interface IParameterDefinitionUnit extends ParameterDefinitionUnitViewable {

	/**
	 * Gets the parameterDefinitionUnitId of this instance.
	 * 
	 * @return the parameterDefinitionUnitId currently set for this instance.
	 */
	public abstract long getParameterDefinitionUnitId();

	/**
	 * Sets the parameterDefinitionUnitId of this instance.
	 * 
	 * @param parameterDefinitionUnitId
	 *            the new parameterDefinitionUnitId of this instance.
	 */
	public abstract void setParameterDefinitionUnitId(long parameterDefinitionUnitId);

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public abstract String getName();

	/**
	 * Sets the name of this instance.
	 * 
	 * @param name
	 *            the new name of this instance.
	 */
	public abstract void setName(String name);

	/**
	 * Gets the type of this instance.
	 * 
	 * @return the type currently set for this instance.
	 */
	public abstract ParameterDefinitionDataTypeEnum getType();

	/**
	 * Sets the type of this instance.
	 * 
	 * @param type
	 *            the new type of this instance.
	 */
	public abstract void setType(ParameterDefinitionDataType type);

	/**
	 * Checks if the {@link ParameterDefinitionUnit} is set.
	 *
	 * @return true, if the {@link ParameterDefinitionUnit} is set
	 */
	public abstract boolean isSet();

}