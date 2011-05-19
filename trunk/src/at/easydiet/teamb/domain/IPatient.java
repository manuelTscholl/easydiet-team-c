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
import java.util.Set;

import at.easydiet.model.Illness;
import at.easydiet.model.Patient;
import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.viewobject.DietTreatmentViewable;
import at.easydiet.teamb.application.viewobject.FamilyAnamnesisViewable;
import at.easydiet.teamb.application.viewobject.IllnessViewable;
import at.easydiet.teamb.application.viewobject.LaborReportViewable;
import at.easydiet.teamb.application.viewobject.PatientLikeViewable;
import at.easydiet.teamb.application.viewobject.PatientStateViewable;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.domain.object.DietParameterDO;
import at.easydiet.teamb.domain.object.DietTreatmentDO;
import at.easydiet.teamb.domain.object.FamilyAnamnesisDO;
import at.easydiet.teamb.domain.object.PatientStateDO;
import at.easydiet.teamb.domain.util.FamilyStatusEnum;
import at.easydiet.teamb.domain.util.GenderEnum;

/**
 * The Interface IPatient.
 */
public interface IPatient extends PatientViewable {

	/**
	 * Gets the patientId of this instance.
	 * 
	 * @return the patientId currently set for this instance.
	 */
	@Override
	public abstract long getPatientId();

	/**
	 * Sets the patientId of this instance.
	 * 
	 * @param patientId the new patientId of this instance.
	 */
	public abstract void setPatientId(long patientId);

	/**
	 * Gets the insuranceNumber of this instance.
	 * 
	 * @return the insuranceNumber currently set for this instance.
	 */
	@Override
	public abstract String getInsuranceNumber();

	/**
	 * Sets the insuranceNumber of this instance.
	 * 
	 * @param insuranceNumber the new insuranceNumber of this instance.
	 */
	public abstract void setInsuranceNumber(String insuranceNumber);

	/**
	 * Gets the forename of this instance.
	 * 
	 * @return the forename currently set for this instance.
	 */
	@Override
	public abstract String getForename();

	/**
	 * Sets the forename of this instance.
	 * 
	 * @param forename the new forename of this instance.
	 */
	public abstract void setForename(String forename);

	/**
	 * Gets the lastname of this instance.
	 * 
	 * @return the lastname currently set for this instance.
	 */
	@Override
	public abstract String getLastname();

	/**
	 * Sets the lastname of this instance.
	 * 
	 * @param lastname the new lastname of this instance.
	 */
	public abstract void setLastname(String lastname);

	/**
	 * Gets the title of this instance.
	 * 
	 * @return the title currently set for this instance.
	 */
	@Override
	public abstract String getTitle();

	/**
	 * Sets the title of this instance.
	 * 
	 * @param title the new title of this instance.
	 */
	public abstract void setTitle(String title);

	/**
	 * Sets the family status.
	 * 
	 * @param status the new family status
	 */
	public abstract void setFamilyStatus(FamilyStatusEnum status);

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.application.viewobject.PatientViewable#getFamilyStatus()
	 */
	@Override
	public abstract FamilyStatusEnum getFamilyStatus();

	/**
	 * Gets the street of this instance.
	 * 
	 * @return the street currently set for this instance.
	 */
	@Override
	public abstract String getStreet();

	/**
	 * Sets the street of this instance.
	 * 
	 * @param street the new street of this instance.
	 */
	public abstract void setStreet(String street);

	/**
	 * Gets the zip of this instance.
	 * 
	 * @return the zip currently set for this instance.
	 */
	@Override
	public abstract String getZip();

	/**
	 * Sets the zip of this instance.
	 * 
	 * @param zip the new zip of this instance.
	 */
	public abstract void setZip(String zip);

	/**
	 * Gets the place of this instance.
	 * 
	 * @return the place currently set for this instance.
	 */
	@Override
	public abstract String getPlace();

	/**
	 * Sets the place of this instance.
	 * 
	 * @param place the new place of this instance.
	 */
	public abstract void setPlace(String place);

	/**
	 * Gets the country of this instance.
	 * 
	 * @return the country currently set for this instance.
	 */
	@Override
	public abstract String getCountry();

	/**
	 * Sets the country of this instance.
	 * 
	 * @param country the new country of this instance.
	 */
	public abstract void setCountry(String country);

	/**
	 * Gets the birthday of this instance.
	 * 
	 * @return the birthday currently set for this instance.
	 */
	@Override
	public abstract GregorianCalendar getBirthday();

	/**
	 * Sets the birthday of this instance.
	 * 
	 * @param birthday the new birthday of this instance.
	 */
	public abstract void setBirthday(GregorianCalendar birthday);

	/**
	 * Gets the job of this instance.
	 * 
	 * @return the job currently set for this instance.
	 */
	@Override
	public abstract String getJob();

	/**
	 * Sets the job of this instance.
	 * 
	 * @param job the new job of this instance.
	 */
	public abstract void setJob(String job);

	/**
	 * Gets the religion of this instance.
	 * 
	 * @return the religion currently set for this instance.
	 */
	@Override
	public abstract String getReligion();

	/**
	 * Sets the religion of this instance.
	 * 
	 * @param religion the new religion of this instance.
	 */
	public abstract void setReligion(String religion);

	/**
	 * Gets the regime of this instance.
	 * 
	 * @return the regime currently set for this instance.
	 */
	@Override
	public abstract String getRegime();

	/**
	 * Sets the regime of this instance.
	 * 
	 * @param regime the new regime of this instance.
	 */
	public abstract void setRegime(String regime);

	/**
	 * Gets the notice of this instance.
	 * 
	 * @return the notice currently set for this instance.
	 */
	@Override
	public abstract String getNotice();

	/**
	 * Sets the notice of this instance.
	 * 
	 * @param notice the new notice of this instance.
	 */
	public abstract void setNotice(String notice);

	/**
	 * Gets the gender of this instance.
	 * 
	 * @return the gender currently set for this instance.
	 */
	@Override
	public abstract GenderEnum getGender();

	/**
	 * Sets the gender of this instance.
	 * 
	 * @param gender the new gender of this instance.
	 */
	public abstract void setGender(GenderEnum gender);

	/**
	 * Gets the illnesses of this instance.
	 * 
	 * @return the illnesses currently set for this instance.
	 */
	@Override
	public abstract IllnessViewable[] getIllnesses();

	/**
	 * Adds the illness.
	 * 
	 * @param illness the illness
	 */
	public abstract void addIllness(IllnessViewable illness);

	/**
	 * Removes the illness.
	 * 
	 * @param illness the illness
	 */
	public abstract void removeIllness(IllnessViewable illness);

	/**
	 * Sets the illnesses of this instance.
	 * 
	 * @param illnesses the new illnesses of this instance.
	 */
	public abstract void setIllnesses(Set<Illness> illnesses);

	/**
	 * Gets the familyanamnesis of this instance.
	 * 
	 * @return the familyanamnesis currently set for this instance.
	 */
	@Override
	public abstract FamilyAnamnesisViewable[] getFamilyanamnesis();

	/**
	 * Adds the family anamnesis.
	 * 
	 * @param familyAnamnesis the family anamnesis
	 */
	public abstract void addFamilyAnamnesis(FamilyAnamnesisDO familyAnamnesis);

	/**
	 * Removes the family anamnesis.
	 * 
	 * @param familyAnamnesis the family anamnesis
	 */
	public abstract void removeFamilyAnamnesis(FamilyAnamnesisDO familyAnamnesis);

	/**
	 * Gets the patientstate of this instance.
	 * 
	 * @return the patientstate currently set for this instance.
	 */
	@Override
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
	 * Gets the laborReports of this instance.
	 * 
	 * @return the laborReports currently set for this instance.
	 */
	@Override
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

	/**
	 * Gets the treatments of this instance.
	 * 
	 * @return the treatments currently set for this instance.
	 */
	@Override
	public abstract DietTreatmentViewable[] getTreatments();

	/**
	 * Adds the diet treatment.
	 * 
	 * @param dietTreatment the diet treatment
	 */
	public abstract void addDietTreatment(DietTreatmentDO dietTreatment);

	/**
	 * Removes the diet treatment.
	 * 
	 * @param dietParameter the diet parameter
	 */
	public abstract void removeDietTreatment(DietParameterDO dietParameter);

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.application.viewobject.PatientViewable#getPatientLikes()
	 */
	@Override
	public abstract PatientLikeViewable[] getPatientLikes();

	/**
	 * Adds the like.
	 * 
	 * @param like the like
	 */
	public abstract void addLike(PatientLikeViewable like);

	/**
	 * Removes the like.
	 * 
	 * @param like the like
	 */
	public abstract void removeLike(PatientLikeViewable like);

	/**
	 * Save patient.
	 * 
	 * @throws DatabaseException the database exception
	 */
	public abstract void savePatient() throws DatabaseException;

	/**
	 * Delete patient.
	 * 
	 * @throws DatabaseException the database exception
	 */
	public abstract void deletePatient() throws DatabaseException;

	/**
	 * Checks if the {@link Patient} is set.
	 * 
	 * @return true, if the {@link Patient} is set
	 */
	public abstract boolean isSet();

	/**
	 * Discard.
	 */
	public abstract void discard();

	/**
	 * @return true if this patient was once saved in the database
	 */
	public abstract boolean isInDatabase();

	public abstract PatientViewable[] getDuplicates();

}