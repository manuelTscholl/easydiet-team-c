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
import at.easydiet.teamb.domain.object.PatientDO;

/**
 * {@link PatientChangeNotPermittedException} is used to signal that the {@link PatientDO} can not be changed without further user interaction
 * 
 * @author TeamB
 */
public class PatientChangeNotPermittedException extends OperationNotPermittedException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5829479611134427004L;

	private ExitOptions _exitOptions;

	/**
	 * @param exitOptions the possibilities a user has to change the {@link PatientDO}
	 */
	public PatientChangeNotPermittedException(ExitOptions exitOptions) {
		_exitOptions = exitOptions;
	}

	/**
	 * @return the possibilities a user has to change the {@link PatientDO}
	 */
	public ExitOptions getExitOptions() {
		return _exitOptions;
	}

}
