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
import at.easydiet.teamb.application.viewobject.PatientLikeViewable;
import at.easydiet.model.Patient;

/**
 * The Interface IPatientLike.
 */
public interface IPatientLike extends PatientLikeViewable {

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.PatientLikeViewable#getPatient()
	 */
	public abstract Patient getPatient();

	/**       
	 * Sets the patient of this instance. 
	 * @param patient the new patient of this instance.
	 */
	public abstract void setPatient(Patient patient);

	/* (non-Javadoc)
	 * @see at.easydiet.model.PatientLikeViewable#getBlsPattern()
	 */
	public abstract String getBlsPattern();

	/**       
	 * Sets the blsPattern of this instance. 
	 * @param blsPattern the new blsPattern of this instance.
	 */
	public abstract void setBlsPattern(String blsPattern);

	/* (non-Javadoc)
	 * @see at.easydiet.model.PatientLikeViewable#getGrade()
	 */
	public abstract PatientLikeGradeViewable getGrade();

	/**       
	 * Sets the grade of this instance. 
	 * @param grade the new grade of this instance.
	 */
	public abstract void setGrade(PatientLikeGradeViewable grade);

	/* (non-Javadoc)
	 * @see at.easydiet.model.PatientLikeViewable#getNotice()
	 */
	public abstract String getNotice();

	/**       
	 * Sets the notice of this instance. 
	 * @param notice the new notice of this instance.
	 */
	public abstract void setNotice(String notice);

}