/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	07.05.2011
 */

package at.easydiet.teamb.application.handler;

import java.math.RoundingMode;
import java.util.GregorianCalendar;

import at.easydiet.teamb.application.handler.AbstractHandler;
import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.util.AgeUtil;
import at.easydiet.teamb.application.util.BMIUtil;
import at.easydiet.teamb.application.util.BMIUtil.BMIEvaluationEnum;
import at.easydiet.teamb.application.util.RoundUtil;
import at.easydiet.teamb.application.util.ValidatorArgs.PatientStateErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.application.viewobject.DietTreatmentViewable;
import at.easydiet.teamb.application.viewobject.LaborReportViewable;
import at.easydiet.teamb.application.viewobject.PatientStateTypeViewable;
import at.easydiet.teamb.application.viewobject.PatientStateViewable;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.teamb.domain.IPatientState;
import at.easydiet.teamb.domain.object.PatientStateDO;
import at.easydiet.teamb.domain.util.GenderEnum;
import at.easydiet.model.PatientState;
import at.easydiet.dao.DAOFactory;

/**
 * The Class PatientStateHandler.
 */

	/** The _state. */
	private IPatientState _state;
	private boolean _unsaved;
	
	/**
	 * Instantiates a new patient state handler.
	 */
	public PatientStateHandler(SystemUserViewable creator, PatientViewable patient) {
		this(new PatientStateDO(new PatientState()));
		_state.setCreator(creator);
		_state.setPatient(patient);
		validatePatientState();
	}
	
	/**
	 * Instantiates a new patient state handler.
	 *
	 * @param state the state
	 */
	public PatientStateHandler(PatientStateViewable state) {
		_state = (IPatientState)state;
		_unsaved = false;
		validatePatientState();
	}
	
	/**
	 * Gets the patient state.
	 *
	 * @return the patient state
	 */
	public PatientStateViewable getPatientState(){
		return _state;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date
	 *            the new date
	 */
	public void setDate(GregorianCalendar date) {
		_state.setDate(date);
		_unsaved = true;
		validatePatientState();
	}

	/**
	 * Sets the anamnesis.
	 * 
	 * @param anamnesis
	 *            the new anamnesis
	 */
	public void setAnamnesis(String anamnesis) {
		_state.setAnamnesis(anamnesis);
		_unsaved = true;
	}

	/**
	 * Sets the weight.
	 * 
	 * @param weight
	 *            the new weight
	 */
	public void setWeight(Integer weight) {
		_state.setWeight(weight);
		_unsaved = true;
	}

	/**
	 * Sets the height.
	 * 
	 * @param height
	 *            the new height
	 */
	public void setHeight(Integer height) {
		_state.setHeight(height);
		_unsaved = true;
	}

	/**
	 * Sets the compliance.
	 * 
	 * @param compliance
	 *            the new compliance
	 */
	public void setCompliance(int compliance) {
		_state.setCompliance(compliance);
		_unsaved = true;
	}

	/**
	 * Sets the motivation.
	 * 
	 * @param motivation
	 *            the new motivation
	 */
	public void setMotivation(int motivation) {
		_state.setMotivation(motivation);
		_unsaved = true;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(PatientStateTypeViewable type) {
		_state.setType(type);
		_unsaved = true;
		validatePatientState();
	}

	/**
	 * Sets the creator.
	 * 
	 * @param creator
	 *            the new creator
	 */
	public void setCreator(SystemUserViewable creator) {
		_state.setCreator(creator);
		_unsaved = true;
		validatePatientState();
	}

	/**
	 * Adds the labpor report.
	 * 
	 * @param laborReport
	 *            the labor report
	 */
	public void addLaborReport(LaborReportViewable laborReport) {
		_state.addLaborReport(laborReport);
		_unsaved = true;
	}

	/**
	 * Removes the labpor report.
	 * 
	 * @param laborReport
	 *            the labor report
	 */
	public void removeLaborReport(LaborReportViewable laborReport) {
		_state.removeLaborReport(laborReport);
		_unsaved = true;
	}
	
	/**
	 * Sets the patient.
	 * 
	 * @param Patient
	 *            the new patient
	 */
	public void setPatient(PatientViewable Patient) {
		_state.setPatient(Patient);
		_unsaved = true;
		validatePatientState();
	}
	
	/**
	 * Adds the diet treatment.
	 *
	 * @param dietTreatment the diet treatment
	 */
	public void addDietTreatment(DietTreatmentViewable dietTreatment) {
		_state.addDietTreatment(dietTreatment);
		_unsaved = true;
	}
	
	/**
	 * Removes the diet treatment.
	 *
	 * @param dietTreatment the diet treatment
	 */
	public void removeDietTreatment(DietTreatmentViewable dietTreatment) {
		_state.removeDietTreatment(dietTreatment);
		_unsaved = true;
	}
	
	/**
	 * Gets the bMI.
	 *
	 * @param weight the weight
	 * @param height the height
	 * @return the bMI
	 */
	public double getBMI(double weight, double height){
		return RoundUtil.round(BMIUtil.getBMI(height, weight),2,RoundingMode.UP);
	}
	
	/**
	 * Gets the bMI evaluation.
	 *
	 * @param gender the gender
	 * @param birthday the birthday
	 * @param bmi the bmi
	 * @return the bMI evaluation
	 */
	public BMIEvaluationEnum getBMIEvaluation(GenderEnum gender, GregorianCalendar birthday, double bmi ){
		double age = AgeUtil.calculateAge(birthday, new GregorianCalendar());
		return BMIUtil.getBMIEvaluation(gender, age, bmi);
	}

	/**
	 * Save state.
	 *
	 * @throws DatabaseException the database exception
	 * @throws ErrorInFormException the error in form exception
	 */
	public void saveState() throws DatabaseException, ErrorInFormException {
		if (!isValid()) {
			throw new ErrorInFormException();
		}
		_state.save();
		_unsaved = false;
	}

	/**
	 * Removes the State.
	 *
	 * @throws DatabaseException the database exception
	 */
	public void removeState() throws DatabaseException {
		_state.remove();
		_state = null;
	}
	
	/**
	 * Gets the state types.
	 *
	 * @return the state types
	 */
	public PatientStateTypeViewable[] getStateTypes(){
		return DAOFactory.getInstance().getPatientStateTypeDAO().getAllPatientStateTypes(); 
	}

	/**
	 * Checks if patientstate is dirty.
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
		_state.discard();
		_unsaved = false;
	}
	
	/**
	 * Checks if is in database.
	 *
	 * @return true, if is in database
	 */
	public boolean isInDatabase(){
		return _state.isInDatabase();
	}
	
	/**
	 * Validate patient state.
	 */
	public void validatePatientState() {
		_errorFields.clear();
		
		if (_state.getDate() == null) {
			_errorFields.add(PatientStateErrorField.DATETIME);
		}

		if (_state.getType() == null) {
			_errorFields.add(PatientStateErrorField.TYPE);
		}

		if (_state.getCreator() == null) {
			_errorFields.add(PatientStateErrorField.CREATOR);
		}
		
		if (_state.getPatient() == null) {
			_errorFields.add(PatientStateErrorField.PATIENT);
		}
		
		_validaded.fireEvent(new ValidatorArgs<PatientStateErrorField>(_errorFields));
	}

}
