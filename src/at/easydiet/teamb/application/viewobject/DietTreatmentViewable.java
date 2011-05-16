package at.easydiet.teamb.application.viewobject;

import java.util.GregorianCalendar;

/**
 * Represents a DietTreatment
 */
public interface DietTreatmentViewable {

	/**
	 * Gets the dietTreatmentId of this instance.
	 * 
	 * @return the dietTreatmentId currently set for this instance.
	 */
	public long getDietTreatmentId();

	/**
	 * Gets the start of this instance.
	 * 
	 * @return the start currently set for this instance.
	 */
	public GregorianCalendar getStart();

	/**
	 * Gets the duration of this instance.
	 * 
	 * @return the duration currently set for this instance.
	 */
	public int getDuration();

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public String getName();

	/**
	 * Gets the nutritionProtocols of this instance.
	 * 
	 * @return the nutritionProtocols currently set for this instance.
	 */
	public NutritionProtocolViewable[] getNutritionProtocols();

	/**
	 * Gets the dietPlans of this instance.
	 * 
	 * @return the dietPlans currently set for this instance.
	 */
	public DietPlanViewable[] getDietPlans();

	/**
	 * Gets the dietParameters of this instance.
	 * 
	 * @return the dietParameters currently set for this instance.
	 */
	public DietParameterViewable[] getDietParameters();

	/**
	 * Gets the patientStates of this instance.
	 * 
	 * @return the patientStates currently set for this instance.
	 */
	public PatientStateViewable[] getPatientStates();

	/**
	 * Gets the systemUsers of this instance.
	 * 
	 * @return the systemUsers currently set for this instance.
	 */
	public DietTreatmentSystemUserViewable[] getSystemUsers();

	/**
	 * Gets the contactJournals of this instance.
	 * 
	 * @return the contactJournals currently set for this instance.
	 */
	public ContactJournalViewable[] getContactJournals();

	/**
	 * Gets the treatmentState of this instance.
	 * 
	 * @return the treatmentState currently set for this instance.
	 */
	public TreatmentStateViewable getTreatmentState();

	/**
	 * Gets the patient.
	 * 
	 * @return the patient
	 */
	public PatientViewable getPatient();
}
