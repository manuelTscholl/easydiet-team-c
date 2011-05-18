package at.easydiet.teamb.application.viewobject;

import java.util.GregorianCalendar;

import at.easydiet.model.Patient;
import at.easydiet.teamb.domain.util.FamilyStatusEnum;
import at.easydiet.teamb.domain.util.GenderEnum;

/**
 * Represents a Patient
 */
public interface PatientViewable {

	/**
	 * Gets the patientId of this instance.
	 * 
	 * @return the patientId currently set for this instance.
	 */
	public long getPatientId();

	/**
	 * Gets the insuranceNumber of this instance.
	 * 
	 * @return the insuranceNumber currently set for this instance.
	 */
	public String getInsuranceNumber();

	/**
	 * Gets the forename of this instance.
	 * 
	 * @return the forename currently set for this instance.
	 */
	public String getForename();

	/**
	 * Gets the lastname of this instance.
	 * 
	 * @return the lastname currently set for this instance.
	 */
	public String getLastname();

	/**
	 * Gets the title of this instance.
	 * 
	 * @return the title currently set for this instance.
	 */
	public String getTitle();

	/**
	 * Gets the family status.
	 *
	 * @return the family status
	 */
	public FamilyStatusEnum getFamilyStatus();
	/**
	 * Gets the street of this instance.
	 * 
	 * @return the street currently set for this instance.
	 */
	public String getStreet();

	/**
	 * Gets the zip of this instance.
	 * 
	 * @return the zip currently set for this instance.
	 */
	public String getZip();

	/**
	 * Gets the place of this instance.
	 * 
	 * @return the place currently set for this instance.
	 */
	public String getPlace();

	/**
	 * Gets the country of this instance.
	 * 
	 * @return the country currently set for this instance.
	 */
	public String getCountry();

	/**
	 * Gets the birthday of this instance.
	 * 
	 * @return the birthday currently set for this instance.
	 */
	public GregorianCalendar getBirthday();

	/**
	 * Gets the job of this instance.
	 * 
	 * @return the job currently set for this instance.
	 */
	public String getJob();

	/**
	 * Gets the religion of this instance.
	 * 
	 * @return the religion currently set for this instance.
	 */
	public String getReligion();

	/**
	 * Gets the regime of this instance.
	 * 
	 * @return the regime currently set for this instance.
	 */
	public String getRegime();

	/**
	 * Gets the notice of this instance.
	 * 
	 * @return the notice currently set for this instance.
	 */
	public String getNotice();

	/**
	 * Gets the gender of this instance.
	 * 
	 * @return the gender currently set for this instance.
	 */
	public GenderEnum getGender();

	/**
	 * Gets the illnesses of this instance.
	 * 
	 * @return the illnesses currently set for this instance.
	 */
	public IllnessViewable[] getIllnesses();

	/**
	 * Gets the familyanamnesis of this instance.
	 * 
	 * @return the familyanamnesis currently set for this instance.
	 */
	public FamilyAnamnesisViewable[] getFamilyanamnesis();

	/**
	 * Gets the patientstate of this instance.
	 * 
	 * @return the patientstate currently set for this instance.
	 */
	public PatientStateViewable[] getPatientStates();

	/**
	 * Gets the laborReports of this instance.
	 * 
	 * @return the laborReports currently set for this instance.
	 */
	public LaborReportViewable[] getLaborReports();

	/**
	 * Gets the treatments of this instance.
	 * 
	 * @return the treatments currently set for this instance.
	 */
	public DietTreatmentViewable[] getTreatments();

	/**
	 * Gets the disfavors of this instance.
	 * 
	 * @return the disfavors currently set for this instance.
	 */
	public PatientLikeViewable[] getPatientLikes();

    /**
     * Returns the Patient model of this object
     * @return
     */
    public Patient getModel();

}
