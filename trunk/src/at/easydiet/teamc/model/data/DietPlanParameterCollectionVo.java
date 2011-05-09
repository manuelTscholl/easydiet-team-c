/**
 * This File is part of EasyDiet
 * Created on: 26.04.2011
 * Created by: Friedrich Bösch
 * File: DietPlanParameterCollectionVo.java
 */
package at.easydiet.teamc.model.data;

import at.easydiet.model.DietParameterTemplate;

/**
 *
 * @author Michael Sieber
 */
public class DietPlanParameterCollectionVo {

    // class variables
	

    public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}

	public double getMinValue() {
		return _minValue;
	}

	public void setMinValue(double _minValue) {
		this._minValue = _minValue;
	}

	public double getMaxValue() {
		return _maxValue;
	}

	public void setMaxValue(double _maxValue) {
		this._maxValue = _maxValue;
	}

	public double getCurrValue() {
		return _currValue;
	}

	public void setCurrValue(double _currValue) {
		this._currValue = _currValue;
	}

	// instance variables
	private String _name;
	private double _minValue;
	private double _maxValue;
	private double _currValue;
	
	public DietPlanParameterCollectionVo(String _name, double _minValue,
			double _maxValue, double _currValue) {
		
		this._name = _name;
		this._minValue = _minValue;
		this._maxValue = _maxValue;
		this._currValue = _currValue;
	}

	public DietPlanParameterCollectionVo() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	
}
