package at.easydiet.teamb.application.handler;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.util.Event;
import at.easydiet.teamb.application.util.ValidatorArgs.PatientDataErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.application.viewobject.IllnessViewable;
import at.easydiet.teamb.application.viewobject.PatientLikeViewable;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.domain.IPatient;
import at.easydiet.teamb.domain.util.FamilyStatusEnum;
import at.easydiet.teamb.domain.util.GenderEnum;
import at.easydiet.teamb.util.StringUtil;

/**
 * A DietPlanHandler.
 * 
 * @author TeamB
 */
public class PatientDataHandler extends AbstractHandler<PatientDataErrorField> {

	/** The LOGGER. */
	private static Logger LOGGER = Logger.getLogger(PatientDataHandler.class);

	/** The _patient. */
	private IPatient _patient;

	/** The _unsaved. */
	private boolean _unsaved;
	
	private List<IllnessHandler> _illnessHandlers;

	/**
	 * Instantiates a new patient data handler.
	 * 
	 * @param patient
	 *            the patient
	 */
	public PatientDataHandler(PatientViewable patient) {
		if (patient == null) {
			LOGGER.warn("Patient is null");
			throw new NullPointerException("Patient is null");
		}
		_patient = (IPatient) patient;

		_validaded = new Event<ValidatorArgs<PatientDataErrorField>>(this);
		_errorFields = new ArrayList<PatientDataErrorField>();

		IllnessViewable[] illnesses = _patient.getIllnesses();
		_illnessHandlers = new ArrayList<IllnessHandler>();
		for (IllnessViewable illness : illnesses) {
			addIllness(new IllnessHandler(this, illness));
		}
		
		validate();
		
		_unsaved = false;
	}

	/**
	 * Sets the insurance number.
	 * 
	 * @param number
	 *            the new insurance number
	 */
	public void setInsuranceNumber(String number) {
		number = StringUtil.convertToNullIfPossible(number);

		if (StringUtil.hasChanged(_patient.getInsuranceNumber(), number)) {
			_unsaved = true;
			_patient.setInsuranceNumber(number);
		}
	}

	/**
	 * Sets the forename.
	 * 
	 * @param forename
	 *            the new forename
	 */
	public void setForename(String forename) {
		if (StringUtil.hasChanged(_patient.getForename(), forename)) {
			if (_patient.getForename() != null) {
				_unsaved = true;
			}

			_patient.setForename(forename);
			validate();
		}
	}

	/**
	 * Sets the lastname.
	 * 
	 * @param lastname
	 *            the new lastname
	 */
	public void setLastname(String lastname) {
		if (StringUtil.hasChanged(_patient.getLastname(), lastname)) {
			if (_patient.getLastname() != null) {
				_unsaved = true;
			}
			_patient.setLastname(lastname);
			validate();
		}
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		title = StringUtil.convertToNullIfPossible(title);

		if (StringUtil.hasChanged(_patient.getTitle(), title)) {
			_unsaved = true;
			_patient.setTitle(title);
		}
	}

	/**
	 * Sets the street.
	 * 
	 * @param street
	 *            the new street
	 */
	public void setStreet(String street) {
		street = StringUtil.convertToNullIfPossible(street);

		if (StringUtil.hasChanged(_patient.getStreet(), street)) {
			_unsaved = true;
			_patient.setStreet(street);
		}
	}

	/**
	 * Sets the zip.
	 * 
	 * @param zip
	 *            the new zip
	 */
	public void setZip(String zip) {
		zip = StringUtil.convertToNullIfPossible(zip);

		if (StringUtil.hasChanged(_patient.getZip(), zip)) {
			_unsaved = true;
			_patient.setZip(zip);
		}
	}

	/**
	 * Sets the place.
	 * 
	 * @param place
	 *            the new place
	 */
	public void setPlace(String place) {
		place = StringUtil.convertToNullIfPossible(place);

		if (StringUtil.hasChanged(_patient.getPlace(), place)) {
			_unsaved = true;
			_patient.setPlace(place);
		}
	}

	/**
	 * Sets the country.
	 * 
	 * @param country
	 *            the new country
	 */
	public void setCountry(String country) {
		country = StringUtil.convertToNullIfPossible(country);

		if (StringUtil.hasChanged(_patient.getCountry(), country)) {
			_unsaved = true;
			_patient.setCountry(country);
		}
	}

	/**
	 * Sets the birthday.
	 * 
	 * @param birthday
	 *            the new birthday
	 */
	public void setBirthday(GregorianCalendar birthday) {
		if (birthday != _patient.getBirthday() && (birthday == null || _patient.getBirthday() == null || birthday.compareTo(_patient.getBirthday()) != 0)) {
			if (_patient.getBirthday() != null) {
				_unsaved = true;
			}

			_patient.setBirthday(birthday);
		}
	}

	/**
	 * Sets the job.
	 * 
	 * @param job
	 *            the new job
	 */
	public void setJob(String job) {
		job = StringUtil.convertToNullIfPossible(job);

		if (StringUtil.hasChanged(_patient.getJob(), job)) {
			_unsaved = true;
			_patient.setJob(job);
		}
	}

	/**
	 * Sets the religion.
	 * 
	 * @param religion
	 *            the new religion
	 */
	public void setReligion(String religion) {
		religion = StringUtil.convertToNullIfPossible(religion);

		if (StringUtil.hasChanged(_patient.getReligion(), religion)) {
			_unsaved = true;
			_patient.setReligion(religion);
		}
	}

	/**
	 * Sets the regime.
	 * 
	 * @param regime
	 *            the new regime
	 */
	public void setRegime(String regime) {
		regime = StringUtil.convertToNullIfPossible(regime);

		if (StringUtil.hasChanged(_patient.getRegime(), regime)) {
			_unsaved = true;
			_patient.setRegime(regime);
		}
	}

	/**
	 * Sets the notice.
	 * 
	 * @param notice
	 *            the new notice
	 */
	public void setNotice(String notice) {
		notice = StringUtil.convertToNullIfPossible(notice);

		if (StringUtil.hasChanged(_patient.getNotice(), notice)) {
			_unsaved = true;
			_patient.setNotice(notice);
		}
	}

	/**
	 * Sets the gender.
	 * 
	 * @param gender
	 *            the new gender
	 */
	public void setGender(GenderEnum gender) {
		if (gender != _patient.getGender()) {
			_unsaved = true;
			_patient.setGender(gender);
			validate();
		}
	}

	/**
	 * Sets the family status.
	 * 
	 * @param familyStatus
	 *            the new family status
	 */
	public void setFamilyStatus(FamilyStatusEnum familyStatus) {
		if (familyStatus != _patient.getFamilyStatus()) {
			_unsaved = true;
			_patient.setFamilyStatus(familyStatus);
		}
	}

	/**
	 * Adds the illness.
	 * 
	 * @return the illness handler
	 */
	public IllnessHandler addIllness() {
		return addIllness(new IllnessHandler(this, null));
	}

	/**
	 * Adds the illness.
	 *
	 * @param illnessHandler the illness handler
	 * @return the illness handler
	 */
	public IllnessHandler addIllness(IllnessHandler illnessHandler) {
		_unsaved = true;

		if (!_illnessHandlers.contains(illnessHandler)) {
			_handlers.add(illnessHandler);
			_illnessHandlers.add(illnessHandler);
		}

		if (illnessHandler.getIllness() != null) {
			_patient.addIllness(illnessHandler.getIllness());
		}

		return illnessHandler;
	}

	/**
	 * Removes the illness.
	 * 
	 * @param illnessHandler
	 *            the illness handler
	 */
	public void removeIllness(IllnessHandler illnessHandler) {
		_unsaved = true;

		_handlers.remove(illnessHandler);

		if (illnessHandler.getIllness() != null) {
			_patient.removeIllness(illnessHandler.getIllness());
		}
	}
	
	public void addLike(PatientLikeViewable like){
		_patient.addLike(like);
		_unsaved = true;
	}
	
	public void removeLike(PatientLikeViewable like){
		_patient.removeLike(like);
		_unsaved = true;
	}

	/**
	 * Save.
	 * 
	 * @throws DatabaseException
	 *             the database exception
	 * @throws ErrorInFormException
	 *             the error in form exception
	 */
	public void save() throws DatabaseException, ErrorInFormException {
		if (!isValid()) {
			throw new ErrorInFormException();
		}

		_patient.savePatient();
		_unsaved = false;
	}

	/**
	 * Validate.
	 */
	private void validate() {
		_errorFields.clear();

		if (StringUtil.isEmpty(_patient.getForename())) {
			_errorFields.add(PatientDataErrorField.FORENAME);
		}
		if (StringUtil.isEmpty(_patient.getLastname())) {
			_errorFields.add(PatientDataErrorField.LASTNAME);
		}
		if (_patient.getGender() == null) {
			_errorFields.add(PatientDataErrorField.GENDER);
		}

		_validaded.fireEvent(new ValidatorArgs<PatientDataErrorField>(_errorFields));
	}

	/**
	 * Gets the patient.
	 * 
	 * @return the patient
	 */
	public PatientViewable getPatient() {
		return _patient;
	}

	public void setUnsaved(){
		_unsaved = true;
	}
	
	/**
	 * Gets the illnesses.
	 * 
	 * @return the illnesses
	 */
	public IllnessHandler[] getIllnesses() {
		return _illnessHandlers.toArray(new IllnessHandler[0]);
	}

	/**
	 * Checks if patient is dirty.
	 * 
	 * @return true, if the patient object has changed through this handler
	 */
	public boolean isDirty() {
		return _unsaved;
	}

	/**
	 * Discard.
	 */
	public void discard() {
		_patient.discard();
		_unsaved = false;
	}

	/**
	 * @return true if this patient was once saved in the database
	 */
	public boolean isInDatabase() {
		return _patient.isInDatabase();
	}
}