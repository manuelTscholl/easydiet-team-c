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
	

    /**
     *
     * @return
     */
    public String getName() {
		return _name;
	}

    /**
     *
     * @param _name
     */
    public void setName(String _name) {
		this._name = _name;
	}

        /**
         *
         * @return
         */
        public double getMinValue() {
		return _minValue;
	}

        /**
         *
         * @param _minValue
         */
        public void setMinValue(double _minValue) {
		this._minValue = _minValue;
	}

        /**
         *
         * @return
         */
        public double getMaxValue() {
		return _maxValue;
	}

        /**
         *
         * @param _maxValue
         */
        public void setMaxValue(double _maxValue) {
		this._maxValue = _maxValue;
	}

        /**
         * 
         * @return
         */
        public double getCurrValue() {
		return _currValue;
	}

        /**
         *
         * @param _currValue
         */
        public void setCurrValue(double _currValue) {
		this._currValue = _currValue;
	}

	// instance variables
	private String _name;
	private double _minValue;
	private double _maxValue;
	private double _currValue;
	
        /**
         *
         * @param _name
         * @param _minValue
         * @param _maxValue
         * @param _currValue
         */
        public DietPlanParameterCollectionVo(String _name, double _minValue,
			double _maxValue, double _currValue) {
		
		this._name = _name;
		this._minValue = _minValue;
		this._maxValue = _maxValue;
		this._currValue = _currValue;
	}

	private DietPlanParameterCollectionVo() {
		
	}

	
	
	
	
	
	
}
