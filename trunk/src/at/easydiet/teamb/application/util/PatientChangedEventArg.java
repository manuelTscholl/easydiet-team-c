/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	19.04.2011
 */

package at.easydiet.teamb.application.util;

import at.easydiet.teamb.domain.IPatient;

/**
 * The Class PatientChangedEventArg.
 */
public class PatientChangedEventArg extends EventArgs {
	private IPatient _previousPatient;

	/**
	 * Instantiates a new patient changed event arg.
	 * 
	 * @param previousPatient
	 *            the previous patient
	 */
	public PatientChangedEventArg(IPatient previousPatient) {
		_previousPatient = previousPatient;
	}

	/**
	 * Gets the previous patient.
	 * 
	 * @return the previous patient
	 */
	public IPatient getPreviousPatient() {
		return _previousPatient;
	}

}
