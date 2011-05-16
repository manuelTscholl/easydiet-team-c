package at.easydiet.teamb.application.viewobject;

import java.util.GregorianCalendar;

/**
 * Represents a PatientState
 */
public interface PatientStateViewable {

	/**
	 * Gets the patientStateId of this instance.
	 * 
	 * @return the patientStateId currently set for this instance.
	 */
	public long getPatientStateId();

	/**
	 * Gets the date of this instance.
	 * 
	 * @return the date currently set for this instance.
	 */
	public GregorianCalendar getDate();

	/**
	 * Gets the anamnesis of this instance.
	 * 
	 * @return the anamnesis currently set for this instance.
	 */
	public String getAnamnesis();

	/**
	 * Gets the weight of this instance.
	 * 
	 * @return the weight currently set for this instance.
	 */
	public Integer getWeight();

	/**
	 * Gets the weightPercentile of this instance.
	 * 
	 * @return the weightPercentile currently set for this instance.
	 */
	public float getWeightPercentile();

	/**
	 * Gets the height of this instance.
	 * 
	 * @return the height currently set for this instance.
	 */
	public Integer getHeight();

	/**
	 * Gets the heightPercentile of this instance.
	 * 
	 * @return the heightPercentile currently set for this instance.
	 */
	public float getHeightPercentile();

	/**
	 * Gets the compliance of this instance.
	 * 
	 * @return the compliance currently set for this instance.
	 */
	public int getCompliance();

	/**
	 * Gets the motivation of this instance.
	 * 
	 * @return the motivation currently set for this instance.
	 */
	public int getMotivation();

	/**
	 * Gets the type of this instance.
	 * 
	 * @return the type currently set for this instance.
	 */
	public PatientStateTypeViewable getType();

	/**
	 * Gets the creator of this instance.
	 * 
	 * @return the creator currently set for this instance.
	 */
	public SystemUserViewable getCreator();

	/**
	 * Gets the laborReports of this instance.
	 * 
	 * @return the laborReports currently set for this instance.
	 */
	public LaborReportViewable[] getLaborReports();

	/**
	 * Gets the patient.
	 *
	 * @return the patient
	 */
	public PatientViewable getPatient();
	
	/**
	 * Gets the diet treatments.
	 *
	 * @return the diet treatments
	 */
	public DietTreatmentViewable[] getDietTreatments();
}
