/**
 * This File is part of EasyDiet
 * Created on: 26.04.2011
 * Created by: Friedrich Bösch
 * File: DietPlanParameterCollectionVo.java
 */
package at.easydiet.teamc.model;

/**
 *
 * @author Michael Sieber
 */
public class DietPlanParameterCollectionVo {

    // class variables
	

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
