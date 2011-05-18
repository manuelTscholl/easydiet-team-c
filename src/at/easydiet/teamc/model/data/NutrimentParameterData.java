/**
 * This File is part of Easy Diet
 * created on: 07.05.2011
 * created by: Friedrich Bösch
 * file: NutrimentParameterData
 */
package at.easydiet.teamc.model.data;

import at.easydiet.teamc.exception.ParameterWithoutUnitException;

/**
 *
 * @author Stephan Svoboda
 */
public interface NutrimentParameterData {

    /**
     *
     * @return
     */
    public String getName();

    /**
     *
     * @return
     */
    public float getAmount();

    /**
     *
     * @return
     * @throws ParameterWithoutUnitException
     */
    public ParameterDefinitionUnitData getUnit() throws ParameterWithoutUnitException;

}
