/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	19.04.2011
 */

package at.easydiet.teamb.application.handler.exception;

import at.easydiet.teamb.application.util.ExitOptions;

/**
 * The Class ExitNotPermittedException. {@link ExitNotPermittedException} is used to signal that the Handler can not be closed without further user interaction
 * 
 * @author TeamB
 */
public class ExitNotPermittedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1674022276031253523L;
	private ExitOptions _exitOptions;

	/**
	 * Instantiates a new exit not permitted exception.
	 * 
	 * @param exitOptions
	 *            the possibilities a user has to exit
	 */
	public ExitNotPermittedException(ExitOptions exitOptions) {
		_exitOptions = exitOptions;
	}

	/**
	 * Gets the exit options.
	 * 
	 * @return the possibilities a user has to exit
	 */
	public ExitOptions getExitOptions() {
		return _exitOptions;
	}
}
