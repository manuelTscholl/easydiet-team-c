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

import at.easydiet.teamb.application.viewobject.ContactJournalViewable;
import at.easydiet.teamb.application.viewobject.DietParameterViewable;
import at.easydiet.teamb.application.viewobject.DietPlanViewable;
import at.easydiet.teamb.application.viewobject.DietTreatmentSystemUserViewable;
import at.easydiet.teamb.application.viewobject.DietTreatmentViewable;
import at.easydiet.teamb.application.viewobject.NutritionProtocolViewable;
import at.easydiet.teamb.application.viewobject.PatientStateViewable;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.application.viewobject.TreatmentStateViewable;
import at.easydiet.teamb.domain.object.DietParameterDO;
import at.easydiet.teamb.domain.object.DietPlanDO;
import at.easydiet.teamb.domain.object.DietTreatmentSystemUserDO;
import at.easydiet.teamb.domain.object.NutritionProtocolDO;
import at.easydiet.teamb.domain.object.PatientStateDO;
import at.easydiet.model.DietTreatment;
import at.easydiet.model.TreatmentState;

/**
 * The Interface IDietTreatment.
 */
public interface IDietTreatment extends DietTreatmentViewable {

	/**
	 * Gets the dietTreatmentId of this instance.
	 * 
	 * @return the dietTreatmentId currently set for this instance.
	 */
	public abstract long getDietTreatmentId();

	/**
	 * Sets the dietTreatmentId of this instance.
	 * 
	 * @param dietTreatmentId
	 *            the new dietTreatmentId of this instance.
	 */
	public abstract void setDietTreatmentId(long dietTreatmentId);

	/**
	 * Gets the start of this instance.
	 * 
	 * @return the start currently set for this instance.
	 */
	public abstract GregorianCalendar getStart();

	/**
	 * Sets the start of this instance.
	 * 
	 * @param start
	 *            the new start of this instance.
	 */
	public abstract void setStart(GregorianCalendar start);

	/**
	 * Gets the duration of this instance.
	 * 
	 * @return the duration currently set for this instance.
	 */
	public abstract int getDuration();

	/**
	 * Sets the duration of this instance.
	 * 
	 * @param duration
	 *            the new duration of this instance.
	 */
	public abstract void setDuration(int duration);

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public abstract String getName();

	/**
	 * Sets the name of this instance.
	 * 
	 * @param name
	 *            the new name of this instance.
	 */
	public abstract void setName(String name);

	/**
	 * Gets the nutritionProtocols of this instance.
	 * 
	 * @return the nutritionProtocols currently set for this instance.
	 */
	public abstract NutritionProtocolViewable[] getNutritionProtocols();

	/**
	 * Adds the nutrition protocol.
	 *
	 * @param nutritionProtocol the nutrition protocol
	 */
	public abstract void addNutritionProtocol(NutritionProtocolDO nutritionProtocol);

	/**
	 * Removes the nutrition protocol.
	 *
	 * @param nutritionProtocol the nutrition protocol
	 */
	public abstract void removeNutritionProtocol(NutritionProtocolDO nutritionProtocol);

	/**
	 * Gets the dietPlans of this instance.
	 * 
	 * @return the dietPlans currently set for this instance.
	 */
	public abstract DietPlanViewable[] getDietPlans();

	/**
	 * Adds the diet plan.
	 *
	 * @param dietPlan the diet plan
	 */
	public abstract void addDietPlan(DietPlanDO dietPlan);

	/**
	 * Removes the diet plans.
	 *
	 * @param dietPlans the diet plans
	 */
	public abstract void removeDietPlans(DietPlanDO dietPlans);

	/**
	 * Gets the dietParameters of this instance.
	 * 
	 * @return the dietParameters currently set for this instance.
	 */
	public abstract DietParameterViewable[] getDietParameters();

	/**
	 * Adds the diet parameter.
	 *
	 * @param dietParameter the diet parameter
	 */
	public abstract void addDietParameter(DietParameterDO dietParameter);

	/**
	 * Removes the diet parameter.
	 *
	 * @param dietParameter the diet parameter
	 */
	public abstract void removeDietParameter(DietParameterDO dietParameter);

	/**
	 * Gets the patientStates of this instance.
	 * 
	 * @return the patientStates currently set for this instance.
	 */
	public abstract PatientStateViewable[] getPatientStates();

	/**
	 * Adds the patient state.
	 *
	 * @param patientState the patient state
	 */
	public abstract void addPatientState(PatientStateDO patientState);

	/**
	 * Removes the patient state.
	 *
	 * @param patientState the patient state
	 */
	public abstract void removePatientState(PatientStateDO patientState);

	/**
	 * Gets the systemUsers of this instance.
	 * 
	 * @return the systemUsers currently set for this instance.
	 */
	public abstract DietTreatmentSystemUserViewable[] getSystemUsers();

	/**
	 * Adds the system user.
	 *
	 * @param dietTreatmentSystemUser the diet treatment system user
	 */
	public abstract void addSystemUser(DietTreatmentSystemUserDO dietTreatmentSystemUser);

	/**
	 * Removes the system user.
	 *
	 * @param dietTreatmentSystemUser the diet treatment system user
	 */
	public abstract void removeSystemUser(DietTreatmentSystemUserDO dietTreatmentSystemUser);

	/**
	 * Gets the contactJournals of this instance.
	 * 
	 * @return the contactJournals currently set for this instance.
	 */
	public abstract ContactJournalViewable[] getContactJournals();

	/**
	 * Adds the contact journal.
	 *
	 * @param contactJournal the contact journal
	 */
	public abstract void addContactJournal(IContactJournal contactJournal);

	/**
	 * Removes the contact journal.
	 *
	 * @param contactJournal the contact journal
	 */
	public abstract void removeContactJournal(IContactJournal contactJournal);

	/**
	 * Gets the treatmentState of this instance.
	 * 
	 * @return the treatmentState currently set for this instance.
	 */
	public abstract TreatmentStateViewable getTreatmentState();

	/**
	 * Sets the treatmentState of this instance.
	 * 
	 * @param treatmentState
	 *            the new treatmentState of this instance.
	 */
	public abstract void setTreatmentState(TreatmentState treatmentState);

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.DietTreatmentViewable#getPatient()
	 */
	public abstract PatientViewable getPatient();

	/**
	 * Sets the patient.
	 * 
	 * @param patient
	 *            the new patient
	 */
	public abstract void setPatient(PatientViewable patient);

	/**
	 * Checks if the {@link DietTreatment} is set.
	 *
	 * @return true, if the {@link DietTreatment} is set
	 */
	public abstract boolean isSet();

}