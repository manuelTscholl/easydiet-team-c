/**
 * This File is part of Easy Diet
 * created on: 12.04.2011
 * created by: Michael
 * file: PatientData.java
 */

package at.easydiet.teamc.model.data;

import java.util.Date;

/**
 * Encapsulation of a PatientBo.
 * 
 * @author Michael
 */
public interface PatientData {

	/**
	 * Gets the patient id.
	 * 
	 * @return the patient id
	 */
	public long getPatientId();

	/**
	 * Gets the forename.
	 * 
	 * @return the forename
	 */
	public String getForename();

	/**
	 * Gets the lastname.
	 * 
	 * @return the lastname
	 */
	public String getLastname();

	/**
	 * Gets the birthday.
	 * 
	 * @return the birthday
	 */
	public Date getBirthday();

	/**
	 * Gets the insurance number.
	 * 
	 * @return the insurance number
	 */
	public String getInsuranceNumber();
}
