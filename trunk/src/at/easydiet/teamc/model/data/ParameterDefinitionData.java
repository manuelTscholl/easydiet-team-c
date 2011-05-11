/**
 * This File is part of Easy Diet
 * created on: 07.05.2011
 * created by: Friedrich B�sch
 * file: ParameterDefinitionData
 */
package at.easydiet.teamc.model.data;

import java.util.List;

public interface ParameterDefinitionData {

	String getName();

	List<ParameterDefinitionUnitData> getUnits();

}
