/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.easydiet.teamc.model;

import at.easydiet.teamc.model.data.CheckOperatorData;
import at.easydiet.teamc.model.data.NutrimentParameterRuleData;
import at.easydiet.teamc.model.data.ParameterDefinitionData;
import at.easydiet.teamc.model.data.ParameterDefinitionUnitData;

/**
 * This class represents a specific rule, which is managed and manipulated by the NutrimentRulesBo class.
 * @author Friedrich Bösch
 */
public class NutrimentRuleBo implements NutrimentParameterRuleData {
	/**
	 * The parameterdefinition which belongs to this Nutriment Rule
	 */
    private ParameterDefinitionBo _parameterDefintionBo;
    /**
     * The checkoperator which belongs to this nutriment rule
     */
    private CheckOperatorBo _checkOperatorBo;
    /**
     * The current value this nutriment rule is holding
     */
    private double _value;
    /**
     * Indicates whether this nutrimentrule is violated or not
     */
    private boolean _isViolated;
   
    private int _row; //TODO delete this variable. not needed anymore
    /**
     * The unit which this nutriment rule corresponds to
     */
    private ParameterDefinitionUnitBo _unit;

    public NutrimentRuleBo(ParameterDefinitionBo parameterdefinition,
            CheckOperatorBo checkOperator, ParameterDefinitionUnitBo pdub) {
        this._parameterDefintionBo = parameterdefinition;
        this._checkOperatorBo = checkOperator;
        this._unit = pdub;
        this._value = 0;
    }

    public NutrimentRuleBo(ParameterDefinitionBo parameterdefinition,
            CheckOperatorBo checkOperator, double val,
            ParameterDefinitionUnitBo pdubo) {
        this._parameterDefintionBo = parameterdefinition;
        this._checkOperatorBo = checkOperator;
        this._value = val;

        _unit = pdubo;
    }

    @Override
    public String getName() {
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
    protected void setParameterDefintionBo(
            ParameterDefinitionBo parameterDefintion) {
        this._parameterDefintionBo = parameterDefintion;
    }

    /**
     * @return the current checkOperatorBo
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
     * @return the current value
     */
    @Override
    public double getValue() {
        return _value;
    }

    /**
     * Sets the current value for this nutriment rule
     * @param value the _value to set
     */
    public void setValue(double value) {
        this._value = value;
    }

    @Override
    public boolean IsViolated() {
        return _isViolated;
    }

    /**
     * Sets the indication whether this rule is violated or not
     * @param isViolated the _isViolated to set
     */
    protected void setIsViolated(boolean isViolated) {
        this._isViolated = isViolated;
    }
    /**
     * Gets the current checkoperator
     */
    @Override
    public CheckOperatorData getCheckOperator() {
        return this._checkOperatorBo;
    }

    /*
     * (non-Javadoc)
     *
     * @see at.easydiet.teamc.model.data.NutrimentParameterRuleData#getRow()
     */
    @Override
    public int getRow() {
        return _row;
    }

    /**
     * returns an encapsulated Parameterdefinition business object with the parameterdefinition data
     */
    @Override
    public ParameterDefinitionData getParameterDefinitionData() {
        return _parameterDefintionBo;
    }
    /**
     * 
     */
    @Override
    public ParameterDefinitionUnitData getUnit() {
        return this._unit;

    }
    /**
     * Gets the current parameterdefinition
     * @return
     */
    public ParameterDefinitionUnitBo getParameterdefinition() {
        return _unit;
    }
    
    /**
     * Sets the parameterdefinition unit for this nutriment rule
     * @param parameterdef
     */
    public void setParameterdefinitionUnit(
            ParameterDefinitionUnitBo parameterdef) {
        _unit = parameterdef;
    }

    @Override
    public String toString() {
        return getName();
    }
}
