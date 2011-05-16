/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	21.04.2011
 */

package at.easydiet.teamb.presentation.util;

import at.easydiet.teamb.application.handler.ParameterHandler;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;

/**
 * The Interface Parameterizable.
 */
public interface Parameterizable {

	/**
	 * Removes the parameter.
	 *
	 * @param parameterHandler the parameter handler
	 */
	public void removeParameter(ParameterHandler parameterHandler);
	
	/**
	 * Adds the parameter.
	 *
	 * @param parameterHandler the parameter handler
	 * @throws ErrorInFormException the error in form exception
	 */
	public void addParameter(ParameterHandler parameterHandler) throws ErrorInFormException;
}
