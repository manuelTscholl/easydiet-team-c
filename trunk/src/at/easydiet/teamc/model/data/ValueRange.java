package at.easydiet.teamc.model.data;

/**
 * Represents a value range for parameters.
 * 
 * @author Michael
 */
public class ValueRange {
	private double _min;
	private double _max;
	private double _current;

	/**
	 * Instantiates a new value range.
	 * 
	 * @param _min the min value
	 * @param _max the max value
	 * @param _current the current value
	 */
	public ValueRange(double _min, double _max, double _current) {
		this._min = _min;
		this._max = _max;
		this._current = _current;
	}

	/**
	 * Gets the min value.
	 * 
	 * @return the min
	 */
	public double getMin() {
		return _min;
	}

	/**
	 * Sets the min value.
	 * 
	 * @param _min the new min
	 */
	public void setMin(double _min) {
		this._min = _min;
	}

	/**
	 * Gets the max value.
	 * 
	 * @return the max
	 */
	public double getMax() {
		return _max;
	}

	/**
	 * Sets the max value.
	 * 
	 * @param _max the new max
	 */
	public void setMax(double _max) {
		this._max = _max;
	}

	/**
	 * Gets the current value.
	 * 
	 * @return the current
	 */
	public double getCurrent() {
		return _current;
	}

	/**
	 * Sets the current value.
	 * 
	 * @param _current the new current
	 */
	public void setCurrent(double _current) {
		this._current = _current;
	}

}
