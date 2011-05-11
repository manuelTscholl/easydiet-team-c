/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package at.easydiet.teamc.model;

import at.easydiet.teamc.exception.ParameterWithoutUnitException;
import at.easydiet.teamc.model.data.CheckOperatorData;
import at.easydiet.teamc.model.data.NutrimentParameterRuleData;
import at.easydiet.teamc.model.data.ParameterDefinitionData;
import at.easydiet.teamc.model.data.ParameterDefinitionUnitData;

/**
 * 
 * @author Stephan Svoboda
 */
public class NutrimentRuleBo implements NutrimentParameterRuleData {
	private ParameterDefinitionBo _parameterDefintionBo;
	private CheckOperatorBo _checkOperatorBo;
	private double _value;
	private boolean _isViolated;
	private int _row;
	private ParameterDefinitionUnitBo _parameterdef;

	public NutrimentRuleBo(ParameterDefinitionBo parameterdefinition,
			CheckOperatorBo checkOperator) {
		this._parameterDefintionBo = parameterdefinition;
		this._checkOperatorBo = checkOperator;
		this._value = 0;
	}

	public NutrimentRuleBo(ParameterDefinitionBo parameterdefinition,
			CheckOperatorBo checkOperator, double val,
			ParameterDefinitionUnitBo pdubo) {
		this._parameterDefintionBo = parameterdefinition;
		this._checkOperatorBo = checkOperator;
		this._value = val;
		
		_parameterdef = pdubo;
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
	 * @param parameterDefintion
	 *            the _parameterDefintion to set
	 */
	protected void setParameterDefintionBo(
			ParameterDefinitionBo parameterDefintion) {
		this._parameterDefintionBo = parameterDefintion;
	}

	/**
	 * @return the _checkOperatorBo
	 */
	public CheckOperatorBo getCheckOperatorBo() {
		return _checkOperatorBo;
	}

	/**
	 * @param checkOperatorBo
	 *            the _checkOperatorBo to set
	 */
	public void setCheckOperatorBo(CheckOperatorBo checkOperatorBo) {
		this._checkOperatorBo = checkOperatorBo;
	}

	/**
	 * @return the _value
	 */
	@Override
	public double getValue() {
		return _value;
	}

	/**
	 * @param value
	 *            the _value to set
	 */
	public void setValue(double value) {
		this._value = value;
	}

	@Override
	public boolean IsViolated() {
		return _isViolated;
	}

	/**
	 * @param isViolated
	 *            the _isViolated to set
	 */
	protected void setIsViolated(boolean isViolated) {
		this._isViolated = isViolated;
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.teamc.model.data.NutrimentParameterRuleData#
	 * getParameterDefinitionData()
	 */
	@Override
	public ParameterDefinitionData getParameterDefinitionData() {
		return _parameterDefintionBo;
	}

	public ParameterDefinitionUnitData getUnit()
			throws ParameterWithoutUnitException {
		if (this._parameterDefintionBo.getUnits().size() > 0) {
			return this._parameterDefintionBo.getUnits().get(0);
		}
		throw new ParameterWithoutUnitException();
	}

	public ParameterDefinitionUnitBo getParameterdefinition() {
		return _parameterdef;
	}

	public void setParameterdefinition(ParameterDefinitionUnitBo parameterdef) {
		_parameterdef = parameterdef;
	}

}
