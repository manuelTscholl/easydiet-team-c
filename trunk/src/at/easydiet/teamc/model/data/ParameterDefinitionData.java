/**
 * This File is part of Easy Diet
 * created on: 07.05.2011
 * created by: Friedrich B�sch
 * file: ParameterDefinitionData
 */
package at.easydiet.teamc.model.data;

import java.util.List;

import at.easydiet.teamc.exception.ParameterWithoutUnitException;

// TODO: Auto-generated Javadoc
/**
 * Encapsulation of a ParameterDefinitionBo.
 * 
 * @author Michael
 */
public interface ParameterDefinitionData {

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	String getName();

	/**
	 * Gets the units.
	 * 
	 * @return the units
	 */
	List<ParameterDefinitionUnitData> getUnits();

	/**
	 * Gets the unit data.
	 * 
	 * @return the unit data
	 * @throws ParameterWithoutUnitException the parameter without unit
	 *             exception
	 */
	ParameterDefinitionUnitData getUnitData()
			throws ParameterWithoutUnitException;

}
