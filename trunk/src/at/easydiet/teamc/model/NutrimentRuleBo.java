/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.easydiet.teamc.model;

import at.easydiet.teamc.model.data.CheckOperatorData;
import at.easydiet.teamc.model.data.NutrimentParameterRuleData;

/**
 *
 * @author Stephan Svoboda
 */
public class NutrimentRuleBo implements NutrimentParameterRuleData {
    private ParameterDefinitionBo _parameterDefintionBo;
    private CheckOperatorBo _checkOperatorBo;
    private double _value;
    private boolean _isViolated;

    public NutrimentRuleBo(ParameterDefinitionBo parameterdefinition, CheckOperatorBo checkOperator) {
        this._parameterDefintionBo=parameterdefinition;
        this._checkOperatorBo=checkOperator;
        this._value=0;
    }

    public String getName(){
        return this._parameterDefintionBo.getName();
    }

    /**
     * @return the _parameterDefintion
     */
    public ParameterDefinitionBo getParameterDefintionBo() {
        return _parameterDefintionBo;
    }

    /**
     * @param parameterDefintion the _parameterDefintion to set
     */
    protected void setParameterDefintionBo (ParameterDefinitionBo parameterDefintion) {
        this._parameterDefintionBo = parameterDefintion;
    }

    /**
     * @return the _checkOperatorBo
     */
    public CheckOperatorBo getCheckOperatorBo() {
        return _checkOperatorBo;
    }

    /**
     * @param checkOperatorBo the _checkOperatorBo to set
     */
    public void setCheckOperatorBo(CheckOperatorBo checkOperatorBo) {
        this._checkOperatorBo = checkOperatorBo;
    }

    /**
     * @return the _value
     */
    public double getValue() {
        return _value;
    }

    /**
     * @param value the _value to set
     */
    public void setValue(double value) {
        this._value = value;
    }

    public boolean IsViolated() {
        return _isViolated;
    }

     /**
     * @param isViolated the _isViolated to set
     */
    protected void setIsViolated(boolean isViolated) {
        this._isViolated = isViolated;
    }

    public CheckOperatorData getCheckOperator() {
        return (CheckOperatorData) this._checkOperatorBo;
    }

   
}
