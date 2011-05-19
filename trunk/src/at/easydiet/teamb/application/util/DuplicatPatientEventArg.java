/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	16.05.2011
 */

package at.easydiet.teamb.application.util;

import at.easydiet.teamb.application.viewobject.PatientViewable;

public class DuplicatPatientEventArg extends EventArgs {

	private PatientViewable[] _duplicates;

	public DuplicatPatientEventArg(PatientViewable[] duplicates) {
		_duplicates = duplicates;
	}

	public PatientViewable[] getDuplicates() {
		return _duplicates;
	}
}
