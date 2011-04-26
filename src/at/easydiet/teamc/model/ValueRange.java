package at.easydiet.teamc.model;

public class ValueRange {
	private double _min;
	private double _max;
	private double _current;
	
	public ValueRange(double _min, double _max, double _current) {		
		this._min = _min;
		this._max = _max;
		this._current = _current;
	}

	public double getMin() {
		return _min;
	}

	public void setMin(double _min) {
		this._min = _min;
	}

	public double getMax() {
		return _max;
	}

	public void setMax(double _max) {
		this._max = _max;
	}

	public double getCurrent() {
		return _current;
	}

	public void setCurrent(double _current) {
		this._current = _current;
	}
	

}
