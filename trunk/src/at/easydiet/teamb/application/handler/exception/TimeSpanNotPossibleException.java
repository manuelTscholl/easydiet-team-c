/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	24.04.2011
 */

package at.easydiet.teamb.application.handler.exception;

import java.util.List;

import at.easydiet.teamb.application.util.ValidatorArgs.TimeSpanErrorField;

/**
 * Will be thrown when a Timespan isn't possible.
 */
public class TimeSpanNotPossibleException extends Exception {

	private static final long serialVersionUID = -5436532561001831953L;

	private List<TimeSpanErrorField> _errorFields;

	/**
	 * Instantiates a new time span not possible exception.
	 *
	 * @param errorFields the error fields
	 */
	public TimeSpanNotPossibleException(List<TimeSpanErrorField> errorFields) {
		_errorFields = errorFields;
	}

	/**
	 * Gets the error fields.
	 *
	 * @return the error fields
	 */
	public List<TimeSpanErrorField> getErrorFields() {
		return _errorFields;
	}

}
