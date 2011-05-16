/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.application.viewobject;

import at.easydiet.model.Patient;

/**
 * The Interface PatientLikeViewable.
 */
public interface PatientLikeViewable {

	/**       
	 * Gets the patient of this instance. 
	 * @return the patient currently set for this instance.
	 */
	public abstract Patient getPatient();

	/**       
	 * Gets the blsPattern of this instance. 
	 * @return the blsPattern currently set for this instance.
	 */
	public abstract String getBlsPattern();

	/**       
	 * Gets the grade of this instance. 
	 * @return the grade currently set for this instance.
	 */
	public abstract PatientLikeGradeViewable getGrade();

	/**       
	 * Gets the notice of this instance. 
	 * @return the notice currently set for this instance.
	 */
	public abstract String getNotice();

}