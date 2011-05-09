/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.easydiet.teamc.model;

/**
 *
 * @author Stephan Svoboda
 */
public class NutrimentRuleBo {
    private ParameterDefinitionBo _parameterDefintion;
    private double _min;
    private double _max;

    public NutrimentRuleBo(ParameterDefinitionBo parameterdefinition) {
        this._parameterDefintion=parameterdefinition;
        this._min=0;
        this._max=0;
    }

    public String getName(){
        return this._parameterDefintion.getName();
    }

    /**
     * @return the _parameterDefintion
     */
    public ParameterDefinitionBo getParameterDefintionBo() {
        return _parameterDefintion;
    }

    /**
     * @param parameterDefintion the _parameterDefintion to set
     */
    protected void setParameterDefintionBo (ParameterDefinitionBo parameterDefintion) {
        this._parameterDefintion = parameterDefintion;
    }

    /**
     * @return the _min
     */
    public double getMin() {
        return _min;
    }

    /**
     * @param min the _min to set
     */
    public void setMin(double min) {
        this._min = min;
    }

    /**
     * @return the _max
     */
    public double getMax() {
        return _max;
    }

    /**
     * @param max the _max to set
     */
    public void setMax(double max) {
        this._max = max;
    }

}
