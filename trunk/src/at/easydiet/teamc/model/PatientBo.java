package at.easydiet.teamc.model;

// Generated 02.04.2011 00:41:04 by Hibernate Tools 3.4.0.CR1

import at.easydiet.teamc.model.data.Saveable;
import java.sql.Clob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import at.easydiet.dao.DAOFactory;
import at.easydiet.model.DietTreatment;
import at.easydiet.model.FamilyAnamnesis;
import at.easydiet.model.LaborReport;
import at.easydiet.model.Patient;
import at.easydiet.model.PatientState;
import at.easydiet.model.Recipe;
import at.easydiet.teamc.exception.NoDietTreatmentException;
import at.easydiet.teamc.model.data.PatientData;
import java.util.Calendar;

/**
 * PatientBo generated by hbm2java
 */
public class PatientBo implements java.io.Serializable, Saveable, PatientData {

	private GenderBo _GenderBo;

	private Patient _Patient;

        /**
     * Constant factor for millisecond/day converting
     */
    private static final long MILLISECONDS_TO_DAY_FACTOR = 86400000;

	private PatientBo() {
	}

	public PatientBo(Patient patient) {
		this._Patient = patient;
	}

	public PatientBo(String insuranceNumber, String forename, String lastname,
			String title, String street, String zip, String place,
			String country, Date birthday, GenderBo genderBo) {
		this(new Patient(insuranceNumber, forename, lastname, title, street,
				zip, place, country, birthday, genderBo.getGender()));
	}

	public PatientBo(String insuranceNumber, String forename, String lastname,
			String title, String street, String zip, String place,
			String country, Date birthday, String job, String religion,
			Set<String> illnesses, String regime, Clob notice,
			GenderBo genderBo, Set<FamilyAnamnesisBo> familyanamnesisBo,
			Set<PatientStateBo> patientstatesBo,
			Set<LaborReportBo> laborReportsBo,
			Set<DietTreatmentBo> treatmentsBo, Set<RecipeBo> disfavorsBo) {
		this(insuranceNumber, forename, lastname, title, street, zip, place,
				country, birthday, genderBo);
		this._Patient.setJob(job);
		this._Patient.setReligion(religion);
		this._Patient.setIllnesses(illnesses);
		this._Patient.setRegime(regime);
		this._Patient.setNotice(notice);

		for (FamilyAnamnesisBo familyAnamnesisBo : familyanamnesisBo) {
			this._Patient.getFamilyanamnesis().add(
					familyAnamnesisBo.getFamilyAnamnesis());
		}

		for (PatientStateBo patientStateBo : patientstatesBo) {
			this._Patient.getPatientStates().add(
					patientStateBo.getPatientState());
		}

		for (LaborReportBo laborReportBo : laborReportsBo) {
			this._Patient.getLaborReports().add(laborReportBo.getLaborReport());
		}

		for (DietTreatmentBo dietTreatmentBo : treatmentsBo) {
			this._Patient.getTreatments().add(
					dietTreatmentBo.getDietTreatment());
		}

		for (RecipeBo recipeBo : disfavorsBo) {
			this._Patient.getDisfavors().add(recipeBo.getRecipe());
		}
	}

	public long getPatientId() {
		return this.getPatient().getPatientId();
	}

	public void setPatientId(long patientId) {
		this.getPatient().setPatientId(patientId);
	}

	@Override
	public String getInsuranceNumber() {
		return this.getPatient().getInsuranceNumber();
	}

	public void setInsuranceNumber(String insuranceNumber) {
		this.getPatient().setInsuranceNumber(insuranceNumber);
	}

	@Override
	public String getForename() {
		return this.getPatient().getForename();
	}

	public void setForename(String forename) {
		this.getPatient().setForename(forename);
	}

	@Override
	public String getLastname() {
		return this.getPatient().getLastname();
	}

	public void setLastname(String lastname) {
		this.getPatient().setLastname(lastname);
	}

	public String getTitle() {
		return this.getPatient().getTitle();
	}

	public void setTitle(String title) {
		this.getPatient().setTitle(title);
	}

	public String getStreet() {
		return this.getPatient().getStreet();
	}

	public void setStreet(String street) {
		this.getPatient().setStreet(street);
	}

	public String getZip() {
		return this.getPatient().getZip();
	}

	public void setZip(String zip) {
		this.getPatient().setZip(zip);
	}

	public String getPlace() {
		return this.getPatient().getPlace();
	}

	public void setPlace(String place) {
		this.getPatient().setPlace(place);
	}

	public String getCountry() {
		return this.getPatient().getCountry();
	}

	public void setCountry(String country) {
		this.getPatient().setCountry(country);
	}

	@Override
	public Date getBirthday() {
		return this.getPatient().getBirthday();
	}

	public void setBirthday(Date birthday) {
		this.getPatient().setBirthday(birthday);
	}

	public String getJob() {
		return this.getPatient().getJob();
	}

	public void setJob(String job) {
		this.getPatient().setJob(job);
	}

	public String getReligion() {
		return this.getPatient().getReligion();
	}

	public void setReligion(String religion) {
		this.getPatient().setReligion(religion);
	}

	public Set<String> getIllnesses() {
		return this.getPatient().getIllnesses();
	}

	public void setIllnesses(Set<String> illnesses) {
		this.getPatient().setIllnesses(illnesses);
	}

	public String getRegime() {
		return this.getPatient().getRegime();
	}

	public void setRegime(String regime) {
		this.getPatient().setRegime(regime);
	}

	public Clob getNotice() {
		return this.getPatient().getNotice();
	}

	public void setNotice(Clob notice) {
		this.getPatient().setNotice(notice);
	}

	public GenderBo getGender() {
		return this._GenderBo;
	}

	public void setGender(GenderBo gender) {
		this.getPatient().setGender(gender.getGender());
		this._GenderBo.setGender(getPatient().getGender());
	}

	public Set<FamilyAnamnesisBo> getFamilyanamnesis() {
		Set<FamilyAnamnesisBo> temp = new HashSet<FamilyAnamnesisBo>(
				this._Patient.getFamilyanamnesis().size());
		for (FamilyAnamnesis familyAnamnesis : this._Patient
				.getFamilyanamnesis()) {
			temp.add(new FamilyAnamnesisBo(familyAnamnesis));
		}
		return temp;
	}

	public void setFamilyanamnesis(Set<FamilyAnamnesisBo> familyanamnesisBo) {
		for (FamilyAnamnesisBo familyAnamnesisBo : familyanamnesisBo) {
			this._Patient.getFamilyanamnesis().add(
					familyAnamnesisBo.getFamilyAnamnesis());
		}
	}

	public Set<PatientStateBo> getPatientstates() {
		Set<PatientStateBo> temp = new HashSet<PatientStateBo>(this._Patient
				.getPatientStates().size());
		for (PatientState patientState : this._Patient.getPatientStates()) {
			temp.add(new PatientStateBo(patientState));
		}
		return temp;
	}

	public void setPatientstates(Set<PatientStateBo> patientstatesBo) {
		for (PatientStateBo patientStateBo : patientstatesBo) {
			this._Patient.getPatientStates().add(
					patientStateBo.getPatientState());
		}
	}

	public Set<LaborReportBo> getLaborReports() {
		Set<LaborReportBo> temp = new HashSet<LaborReportBo>(this._Patient
				.getLaborReports().size());
		for (LaborReport laborReport : this._Patient.getLaborReports()) {
			temp.add(new LaborReportBo(laborReport));
		}
		return temp;
	}

	public void setLaborReports(Set<LaborReportBo> laborReportsBo) {
		for (LaborReportBo laborReportBo : laborReportsBo) {
			this._Patient.getLaborReports().add(laborReportBo.getLaborReport());
		}
	}

	public Set<DietTreatmentBo> getTreatments() {
		Set<DietTreatmentBo> temp = new HashSet<DietTreatmentBo>(this._Patient
				.getTreatments().size());
		for (DietTreatment dietTreatment : this._Patient.getTreatments()) {
			temp.add(new DietTreatmentBo(dietTreatment));
		}
		return temp;
	}

	public void setTreatments(Set<DietTreatmentBo> treatmentsBo) {
		for (DietTreatmentBo dietTreatmentBo : treatmentsBo) {
			this._Patient.getTreatments().add(
					dietTreatmentBo.getDietTreatment());
		}
	}

	public Set<RecipeBo> getDisfavors() {
		Set<RecipeBo> temp = new HashSet<RecipeBo>(this._Patient.getDisfavors()
				.size());
		for (Recipe recipe : this._Patient.getDisfavors()) {
			temp.add(new RecipeBo(recipe));
		}
		return temp;
	}

	public void setDisfavors(Set<RecipeBo> disfavorsBo) {
		for (RecipeBo recipeBo : disfavorsBo) {
			this._Patient.getDisfavors().add(recipeBo.getRecipe());
		}
	}

	/**
	 * @return the _Patient
	 */
	protected Patient getPatient() {
		return _Patient;
	}

	/**
	 * @param Patient
	 *            the _Patient to set
	 */
	public void setPatient(Patient Patient) {
		this._Patient = Patient;
	}

	@Override
	public boolean save() {
	    
	    if(DAOFactory.getInstance().getPatientDAO().makePersistent(this._Patient)!=null)
	        return true;
	    return false;
	}

        public DietTreatmentBo searchDietTreatmentBo(Date startDate, Date endDate) throws NoDietTreatmentException{
            Date tempenddate;
            if(getTreatments().size()>0){
                for(DietTreatmentBo dietTreatmentBo: this.getTreatments()){
                    tempenddate=new Date(dietTreatmentBo.getStart().getTime()+(dietTreatmentBo.getDuration()*MILLISECONDS_TO_DAY_FACTOR));
                    if(dietTreatmentBo.getStart().getTime()<=startDate.getTime()&&tempenddate.getTime()>=endDate.getTime()){
                        return dietTreatmentBo;
                    }
                }
            }
            throw new NoDietTreatmentException();
        }

}
