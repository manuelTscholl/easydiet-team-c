/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	25.04.2011
 */

package at.easydiet.teamb.application.util.ValidatorArgs;

import java.util.List;

import at.easydiet.teamb.application.util.EventArgs;

/**
 * The Class ValidatorArgs.
 *
 * @param <T> the generic type
 */
public class ValidatorArgs<T> extends EventArgs {

	private List<T> _errorFields;

	/**
	 * Instantiates a new validator args.
	 *
	 * @param errorFields the error fields
	 */
	public ValidatorArgs(List<T> errorFields) {
		_errorFields = errorFields;
	}

	/**
	 * Gets the error fields.
	 *
	 * @return the error fields
	 */
	public List<T> getErrorFields() {
		return _errorFields;
	}
}
