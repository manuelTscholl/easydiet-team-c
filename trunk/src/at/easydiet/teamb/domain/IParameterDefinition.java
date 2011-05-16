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
import at.easydiet.teamb.application.viewobject.ParameterDefinitionViewable;
import at.easydiet.teamb.domain.object.ParameterDefinitionUnitDO;
import at.easydiet.model.ParameterDefinition;

/**
 * The Interface IParameterDefinition.
 */
public interface IParameterDefinition extends ParameterDefinitionViewable {

	/**
	 * Gets the parameterDefinitionId of this instance.
	 * 
	 * @return the parameterDefinitionId currently set for this instance.
	 */
	public abstract long getParameterDefinitionId();

	/**
	 * Sets the parameterDefinitionId of this instance.
	 * 
	 * @param parameterDefinitionId
	 *            the new parameterDefinitionId of this instance.
	 */
	public abstract void setParameterDefinitionId(long parameterDefinitionId);

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
	 * Gets the checkPattern of this instance.
	 * 
	 * @return the checkPattern currently set for this instance.
	 */
	public abstract String getCheckPattern();

	/**
	 * Sets the checkPattern of this instance.
	 * 
	 * @param checkPattern
	 *            the new checkPattern of this instance.
	 */
	public abstract void setCheckPattern(String checkPattern);

	/**
	 * Gets the units of this instance.
	 * 
	 * @return the units currently set for this instance.
	 */
	public abstract ParameterDefinitionUnitViewable[] getUnits();

	/**
	 * Adds the parameter definition unit.
	 *
	 * @param unit the unit
	 */
	public abstract void addParameterDefinitionUnit(ParameterDefinitionUnitDO unit);

	/**
	 * Removes the parameter definition unit.
	 *
	 * @param unit the unit
	 */
	public abstract void removeParameterDefinitionUnit(ParameterDefinitionUnitDO unit);

	/**
	 * Checks if the {@link ParameterDefinition} is set.
	 *
	 * @return true, if the {@link ParameterDefinition} is set
	 */
	public abstract boolean isSet();

}