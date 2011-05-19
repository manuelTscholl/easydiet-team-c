/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	19.04.2011
 */

package at.easydiet.teamb.domain.object;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.dao.PatientDAO;
import at.easydiet.model.DietTreatment;
import at.easydiet.model.FamilyAnamnesis;
import at.easydiet.model.Illness;
import at.easydiet.model.LaborReport;
import at.easydiet.model.Patient;
import at.easydiet.model.PatientLike;
import at.easydiet.model.PatientState;
import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.viewobject.DietTreatmentViewable;
import at.easydiet.teamb.application.viewobject.FamilyAnamnesisViewable;
import at.easydiet.teamb.application.viewobject.IllnessViewable;
import at.easydiet.teamb.application.viewobject.LaborReportViewable;
import at.easydiet.teamb.application.viewobject.PatientLikeViewable;
import at.easydiet.teamb.application.viewobject.PatientStateViewable;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.domain.IPatient;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.CalendarUtil;
import at.easydiet.teamb.domain.util.ClobConverter;
import at.easydiet.teamb.domain.util.FamilyStatusEnum;
import at.easydiet.teamb.domain.util.GenderEnum;
import at.easydiet.teamb.domain.util.ListConverter;

/**
 * Represents a Patient in the domain layer
 */
public class PatientDO extends AbstractDO<Patient> implements IPatient {
	private static Logger LOGGER = Logger.getLogger(PatientDO.class);

	private Patient _patient;

	/**
	 * Instantiates a new patient.
	 * 
	 * @param patient the patient
	 */
	public PatientDO(Patient patient) {
		if (patient == null) {
			LOGGER.debug("patient is null");
		}

		_patient = patient;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getPatientId()
	 */
	@Override
	public long getPatientId() {
		return _patient.getPatientId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#setPatientId(long)
	 */
	@Override
	public void setPatientId(long patientId) {
		_patient.setPatientId(patientId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getInsuranceNumber()
	 */
	@Override
	public String getInsuranceNumber() {
		return _patient.getInsuranceNumber();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.easydiet.domain.object.IPatient#setInsuranceNumber(java.lang.String)
	 */
	@Override
	public void setInsuranceNumber(String insuranceNumber) {
		_patient.setInsuranceNumber(insuranceNumber);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getForename()
	 */
	@Override
	public String getForename() {
		return _patient.getForename();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#setForename(java.lang.String)
	 */
	@Override
	public void setForename(String forename) {
		_patient.setForename(forename);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getLastname()
	 */
	@Override
	public String getLastname() {
		return _patient.getLastname();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#setLastname(java.lang.String)
	 */
	@Override
	public void setLastname(String lastname) {
		_patient.setLastname(lastname);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getTitle()
	 */
	@Override
	public String getTitle() {
		return _patient.getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		_patient.setTitle(title);
	}

	@Override
	public void setFamilyStatus(FamilyStatusEnum status) {
		_patient.setFamilyStatus(status.getFamilyStatus());
	}

	@Override
	public FamilyStatusEnum getFamilyStatus() {
		return FamilyStatusEnum.getFamilyStatusDO(_patient.getFamilyStatus());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getStreet()
	 */
	@Override
	public String getStreet() {
		return _patient.getStreet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#setStreet(java.lang.String)
	 */
	@Override
	public void setStreet(String street) {
		_patient.setStreet(street);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getZip()
	 */
	@Override
	public String getZip() {
		return _patient.getZip();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#setZip(java.lang.String)
	 */
	@Override
	public void setZip(String zip) {
		_patient.setZip(zip);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getPlace()
	 */
	@Override
	public String getPlace() {
		return _patient.getPlace();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#setPlace(java.lang.String)
	 */
	@Override
	public void setPlace(String place) {
		_patient.setPlace(place);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getCountry()
	 */
	@Override
	public String getCountry() {
		return _patient.getCountry();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#setCountry(java.lang.String)
	 */
	@Override
	public void setCountry(String country) {
		_patient.setCountry(country);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getBirthday()
	 */
	@Override
	public GregorianCalendar getBirthday() {
		return CalendarUtil.ConvertDateToGregorianCalendar(_patient
				.getBirthday());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.easydiet.domain.object.IPatient#setBirthday(java.util.GregorianCalendar
	 * )
	 */
	@Override
	public void setBirthday(GregorianCalendar birthday) {
		_patient.setBirthday(CalendarUtil
				.ConvertGregorianCalendarToDate(birthday));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getJob()
	 */
	@Override
	public String getJob() {
		return _patient.getJob();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#setJob(java.lang.String)
	 */
	@Override
	public void setJob(String job) {
		_patient.setJob(job);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getReligion()
	 */
	@Override
	public String getReligion() {
		return _patient.getReligion();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#setReligion(java.lang.String)
	 */
	@Override
	public void setReligion(String religion) {
		_patient.setReligion(religion);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getRegime()
	 */
	@Override
	public String getRegime() {
		return ClobConverter.ClobToString(_patient.getRegime());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#setRegime(java.lang.String)
	 */
	@Override
	public void setRegime(String regime) {
		_patient.setRegime(ClobConverter.StringToClob(regime));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getNotice()
	 */
	@Override
	public String getNotice() {
		return ClobConverter.ClobToString(_patient.getNotice());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#setNotice(java.lang.String)
	 */
	@Override
	public void setNotice(String notice) {
		_patient.setNotice(ClobConverter.StringToClob(notice));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getGender()
	 */
	@Override
	public GenderEnum getGender() {
		return GenderEnum.getGenderDO(_patient.getGender());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.easydiet.domain.object.IPatient#setGender(at.easydiet.domain.util.
	 * GenderEnum)
	 */
	@Override
	public void setGender(GenderEnum gender) {
		_patient.setGender(gender.getGender());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getIllnesses()
	 */
	@Override
	public IllnessViewable[] getIllnesses() {
		return ListConverter.toDOArray(_patient.getIllnesses(),
				new ListConverter.ModelToDO<IllnessDO, Illness>() {
					@Override
					public IllnessDO convert(Illness model) {
						return new IllnessDO(model);
					}
				}).toArray(new IllnessViewable[0]);
	}

	@Override
	public void addIllness(IllnessViewable illness) {
		_patient.getIllnesses().add(((IllnessDO) illness).getModel());
	}

	@Override
	public void removeIllness(IllnessViewable illness) {
		_patient.getIllnesses().remove(((IllnessDO) illness).getModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#setIllnesses(java.util.Set)
	 */
	@Override
	public void setIllnesses(Set<Illness> illnesses) {
		_patient.setIllnesses(illnesses);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getFamilyanamnesis()
	 */
	@Override
	public FamilyAnamnesisViewable[] getFamilyanamnesis() {
		return ListConverter
				.toDOArray(
						_patient.getFamilyanamnesis(),
						new ListConverter.ModelToDO<FamilyAnamnesisDO, FamilyAnamnesis>() {
							@Override
							public FamilyAnamnesisDO convert(
									FamilyAnamnesis model) {
								return new FamilyAnamnesisDO(model);
							}
						}).toArray(new FamilyAnamnesisViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.easydiet.domain.object.IPatient#addFamilyAnamnesis(at.easydiet.domain
	 * .object.FamilyAnamnesisDO)
	 */
	@Override
	public void addFamilyAnamnesis(FamilyAnamnesisDO familyAnamnesis) {
		_patient.getFamilyanamnesis().add(familyAnamnesis.getModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.easydiet.domain.object.IPatient#removeFamilyAnamnesis(at.easydiet.
	 * domain.object.FamilyAnamnesisDO)
	 */
	@Override
	public void removeFamilyAnamnesis(FamilyAnamnesisDO familyAnamnesis) {
		_patient.getFamilyanamnesis().remove(familyAnamnesis.getModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getPatientStates()
	 */
	@Override
	public PatientStateViewable[] getPatientStates() {
		return ListConverter.toDOArray(_patient.getPatientStates(),
				new ListConverter.ModelToDO<PatientStateDO, PatientState>() {
					@Override
					public PatientStateDO convert(PatientState model) {
						return new PatientStateDO(model);
					}
				}).toArray(new PatientStateViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.easydiet.domain.object.IPatient#addPatientState(at.easydiet.domain
	 * .object.PatientStateDO)
	 */
	@Override
	public void addPatientState(PatientStateDO patientState) {
		_patient.getPatientStates().add(patientState.getModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.easydiet.domain.object.IPatient#removePatientState(at.easydiet.domain
	 * .object.PatientStateDO)
	 */
	@Override
	public void removePatientState(PatientStateDO patientState) {
		_patient.getPatientStates().remove(patientState.getModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getLaborReports()
	 */
	@Override
	public LaborReportViewable[] getLaborReports() {
		return ListConverter.toDOArray(_patient.getLaborReports(),
				new ListConverter.ModelToDO<LaborReportDO, LaborReport>() {
					@Override
					public LaborReportDO convert(LaborReport model) {
						return new LaborReportDO(model);
					}
				}).toArray(new LaborReportViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.easydiet.domain.object.IPatient#addLaborReport(at.easydiet.domain.
	 * object.LaborReportDO)
	 */
	@Override
	public void addLaborReport(LaborReportViewable laborReport) {
		_patient.getLaborReports()
				.add(((LaborReportDO) laborReport).getModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.easydiet.domain.object.IPatient#removeLaborReport(at.easydiet.domain
	 * .object.LaborReportDO)
	 */
	@Override
	public void removeLaborReport(LaborReportViewable laborReport) {
		_patient.getLaborReports().remove(
				((LaborReportDO) laborReport).getModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getTreatments()
	 */
	@Override
	public DietTreatmentViewable[] getTreatments() {
		return ListConverter.toDOArray(_patient.getTreatments(),
				new ListConverter.ModelToDO<DietTreatmentDO, DietTreatment>() {
					@Override
					public DietTreatmentDO convert(DietTreatment model) {
						return new DietTreatmentDO(model);
					}
				}).toArray(new DietTreatmentViewable[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.easydiet.domain.object.IPatient#addDietTreatment(at.easydiet.domain
	 * .object.DietTreatmentDO)
	 */
	@Override
	public void addDietTreatment(DietTreatmentDO dietTreatment) {
		_patient.getTreatments().add(dietTreatment.getModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.easydiet.domain.object.IPatient#removeDietTreatment(at.easydiet.domain
	 * .object.DietParameterDO)
	 */
	@Override
	public void removeDietTreatment(DietParameterDO dietParameter) {
		_patient.getTreatments().remove(dietParameter.getModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#getDisfavors()
	 */
	@Override
	public PatientLikeViewable[] getPatientLikes() {
		return ListConverter.toDOArray(_patient.getLikes(),
				new ListConverter.ModelToDO<PatientLikeDO, PatientLike>() {
					@Override
					public PatientLikeDO convert(PatientLike model) {
						return new PatientLikeDO(model);
					}
				}).toArray(new PatientLikeDO[0]);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.easydiet.domain.object.IPatient#addDisfavors(at.easydiet.domain.object
	 * .RecipeDO)
	 */
	@Override
	public void addLike(PatientLikeViewable like) {
		PatientLikeDO likeDO = (PatientLikeDO) like;
		_patient.getLikes().add(likeDO.getModel());
		likeDO.setPatient(_patient);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * at.easydiet.domain.object.IPatient#removeDisfavors(at.easydiet.domain
	 * .object.RecipeDO)
	 */
	@Override
	public void removeLike(PatientLikeViewable like) {
		_patient.getLikes().remove(((PatientLikeDO) like).getModel());
	}

	@Override
	public void savePatient() throws DatabaseException {
		PatientDAO dao = DAOFactory.getInstance().getPatientDAO();
		dao.makePersistent(_patient);
	}

	@Override
	public void deletePatient() throws DatabaseException {
		PatientDAO dao = DAOFactory.getInstance().getPatientDAO();
		dao.makeTransient(_patient);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see at.easydiet.domain.object.IPatient#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_patient == null);
	}

	@Override
	public Patient getModel() {
		return _patient;
	}

	@Override
	public void discard() {
		LOGGER.debug("discarding changes of " + _patient);
		if (isInDatabase()) {
			HibernateUtil.currentSession().evict(_patient);
			_patient = DAOFactory.getInstance().getPatientDAO()
					.findById(_patient.getPatientId(), false);
		} else {
			_patient = new Patient();
		}
	}

	@Override
	public boolean isInDatabase() {
		return HibernateUtil.currentSession().contains(_patient);
	}

	@Override
	public PatientViewable[] getDuplicates() {
		List<Patient> duplicates = DAOFactory.getInstance().getPatientDAO()
				.findByExample(getModel(), new String[] { "PatientId" });

		// remove this patient from list. it is the original and not a copy.
		Iterator<Patient> iter = duplicates.iterator();
		while (iter.hasNext()) {
			Patient p = iter.next();
			if (p.getPatientId() == getModel().getPatientId()) {
				iter.remove();
			}
		}

		return ListConverter.toDOArray(duplicates,
				new ListConverter.ModelToDO<PatientDO, Patient>() {

					@Override
					public PatientDO convert(Patient model) {
						return new PatientDO(model);
					}
				}).toArray(new PatientViewable[0]);
	}
}
