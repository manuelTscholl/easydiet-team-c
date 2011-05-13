/**
 * This File is part of Easy Diet
 * created on: 07.05.2011
 * created by: Friedrich Bösch
 * file: NutrimentParameterData
 */
package at.easydiet.teamc.model.data;

import at.easydiet.teamc.exception.ParameterWithoutUnitException;

public interface NutrimentParameterData {

    public String getName();

    public float getAmount();

    public ParameterDefinitionUnitData getUnit() throws ParameterWithoutUnitException;

}
