/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.domain;

import at.easydiet.teamb.application.viewobject.PatientLikeGradeViewable;

/**
 * The Interface IPatientLikeGrade.
 */
public interface IPatientLikeGrade extends PatientLikeGradeViewable {

	/**       
	 * Sets the patientLikeGradeId of this instance. 
	 * @param patientLikeGradeId the new patientLikeGradeId of this instance.
	 */
	public abstract void setPatientLikeGradeId(long patientLikeGradeId);

	/**       
	 * Sets the name of this instance. 
	 * @param name the new name of this instance.
	 */
	public abstract void setName(String name);

}