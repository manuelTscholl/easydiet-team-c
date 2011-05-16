/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.domain;

import java.util.GregorianCalendar;

import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.viewobject.DietTreatmentViewable;
import at.easydiet.teamb.application.viewobject.LaborReportViewable;
import at.easydiet.teamb.application.viewobject.PatientStateTypeViewable;
import at.easydiet.teamb.application.viewobject.PatientStateViewable;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.teamb.domain.object.LaborReportDO;

/**
 * The Interface IPatientState.
 */
public interface IPatientState extends PatientStateViewable {
	
	/**
	 * Gets the patientStateId of this instance.
	 * 
	 * @return the patientStateId currently set for this instance.
	 */
	public abstract long getPatientStateId();

	/**
	 * Sets the patientStateId of this instance.
	 * 
	 * @param patientStateId
	 *            the new patientStateId of this instance.
	 */
	public abstract void setPatientStateId(long patientStateId);

	/**
	 * Gets the date of this instance.
	 * 
	 * @return the date currently set for this instance.
	 */
	public abstract GregorianCalendar getDate();

	/**
	 * Sets the date of this instance.
	 * 
	 * @param date
	 *            the new date of this instance.
	 */
	public abstract void setDate(GregorianCalendar date);

	/**
	 * Gets the anamnesis of this instance.
	 * 
	 * @return the anamnesis currently set for this instance.
	 */
	public abstract String getAnamnesis();

	/**
	 * Sets the anamnesis of this instance.
	 * 
	 * @param anamnesis
	 *            the new anamnesis of this instance.
	 */
	public abstract void setAnamnesis(String anamnesis);

	/**
	 * Gets the weight of this instance.
	 * 
	 * @return the weight currently set for this instance.
	 */
	public abstract Integer getWeight();

	/**
	 * Sets the weight of this instance.
	 * 
	 * @param weight
	 *            the new weight of this instance.
	 */
	public abstract void setWeight(Integer weight);

	/**
	 * Gets the weightPercentile of this instance.
	 * 
	 * @return the weightPercentile currently set for this instance.
	 */
	public abstract float getWeightPercentile();

	/**
	 * Sets the weightPercentile of this instance.
	 * 
	 * @param weightPercentile
	 *            the new weightPercentile of this instance.
	 */
	public abstract void setWeightPercentile(float weightPercentile);

	/**
	 * Gets the height of this instance.
	 * 
	 * @return the height currently set for this instance.
	 */
	public abstract Integer getHeight();

	/**
	 * Sets the height of this instance.
	 * 
	 * @param height
	 *            the new height of this instance.
	 */
	public abstract void setHeight(Integer height);

	/**
	 * Gets the heightPercentile of this instance.
	 * 
	 * @return the heightPercentile currently set for this instance.
	 */
	public abstract float getHeightPercentile();

	/**
	 * Sets the heightPercentile of this instance.
	 * 
	 * @param heightPercentile
	 *            the new heightPercentile of this instance.
	 */
	public abstract void setHeightPercentile(float heightPercentile);

	/**
	 * Gets the compliance of this instance.
	 * 
	 * @return the compliance currently set for this instance.
	 */
	public abstract int getCompliance();

	/**
	 * Sets the compliance of this instance.
	 * 
	 * @param compliance
	 *            the new compliance of this instance.
	 */
	public abstract void setCompliance(int compliance);

	/**
	 * Gets the motivation of this instance.
	 * 
	 * @return the motivation currently set for this instance.
	 */
	public abstract int getMotivation();

	/**
	 * Sets the motivation of this instance.
	 * 
	 * @param motivation
	 *            the new motivation of this instance.
	 */
	public abstract void setMotivation(int motivation);

	/**
	 * Gets the type of this instance.
	 * 
	 * @return the type currently set for this instance.
	 */
	public abstract PatientStateTypeViewable getType();

	/**
	 * Sets the type of this instance.
	 * 
	 * @param type
	 *            the new type of this instance.
	 */
	public abstract void setType(PatientStateTypeViewable type);

	/**
	 * Gets the creator of this instance.
	 * 
	 * @return the creator currently set for this instance.
	 */
	public abstract SystemUserViewable getCreator();

	/**
	 * Sets the creator of this instance.
	 * 
	 * @param creator
	 *            the new creator of this instance.
	 */
	public abstract void setCreator(SystemUserViewable creator);

	/**
	 * Gets the laborReports of this instance.
	 * 
	 * @return the laborReports currently set for this instance.
	 */
	public abstract LaborReportViewable[] getLaborReports();

	/**
	 * Adds the labor report.
	 *
	 * @param laborReport the labor report
	 */
	public abstract void addLaborReport(LaborReportViewable laborReport);

	/**
	 * Removes the labor report.
	 *
	 * @param laborReport the labor report
	 */
	public abstract void removeLaborReport(LaborReportViewable laborReport);

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.PatientStateViewable#getDietTreatments()
	 */
	public abstract DietTreatmentViewable[] getDietTreatments();
	
	/**
	 * Adds the diet treatment.
	 *
	 * @param dietTreatment the diet treatment
	 */
	public abstract void addDietTreatment(DietTreatmentViewable dietTreatment);
	
	/**
	 * Removes the diet treatment.
	 *
	 * @param dietTreatment the diet treatment
	 */
	public abstract void removeDietTreatment(DietTreatmentViewable dietTreatment);
	
	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.PatientStateViewable#getPatient()
	 */
	public abstract PatientViewable getPatient();

	/**
	 * Sets the patient.
	 * 
	 * @param Patient
	 *            the new patient
	 */
	public abstract void setPatient(PatientViewable Patient);
	
	/**
	 * Save.
	 *
	 * @throws DatabaseException the database exception
	 */
	public abstract void save() throws DatabaseException;
	
	/**
	 * Removes the.
	 *
	 * @throws DatabaseException the database exception
	 */
	public abstract void remove() throws DatabaseException;
	
	/**
	 * Discard.
	 */
	public abstract void discard();
	
	/**
	 * Checks if is in database.
	 *
	 * @return true, if is in database
	 */
	public abstract boolean isInDatabase();
}