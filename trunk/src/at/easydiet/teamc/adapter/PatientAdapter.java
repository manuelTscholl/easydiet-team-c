/**
 * This file is part of Easy Diet.
 *   created on: 18.05.2011
 *   created by: Manuel
 *   file: PatientAdapter.java
 */
package at.easydiet.teamc.adapter;

import java.util.GregorianCalendar;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import at.easydiet.model.Patient;
import at.easydiet.teamb.application.viewobject.DietTreatmentViewable;
import at.easydiet.teamb.application.viewobject.FamilyAnamnesisViewable;
import at.easydiet.teamb.application.viewobject.IllnessViewable;
import at.easydiet.teamb.application.viewobject.LaborReportViewable;
import at.easydiet.teamb.application.viewobject.PatientLikeViewable;
import at.easydiet.teamb.application.viewobject.PatientStateViewable;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.domain.object.PatientDO;
import at.easydiet.teamb.domain.util.FamilyStatusEnum;
import at.easydiet.teamb.domain.util.GenderEnum;
import at.easydiet.teamc.model.PatientBo;
import at.easydiet.teamc.util.ClobConverter;

/**
 * @author Manuel
 * 
 */
public class PatientAdapter implements PatientViewable {
	public static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
			.getLogger(PatientAdapter.class);

	PatientBo _activePatient;

	/**
	 * Initializes a new instance of the {@link PatientAdapter} class.
	 * 
	 * @param activePatient
	 */
	public PatientAdapter(PatientBo activePatient) {
		_activePatient = activePatient;
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getPatientId()
	 */
	@Override
	public long getPatientId() {
		return _activePatient.getPatientId();
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getInsuranceNumber()
	 */
	@Override
	public String getInsuranceNumber() {
		return _activePatient.getInsuranceNumber();
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getForename()
	 */
	@Override
	public String getForename() {
		return _activePatient.getForename();
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getLastname()
	 */
	@Override
	public String getLastname() {
		return _activePatient.getLastname();
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getTitle()
	 */
	@Override
	public String getTitle() {
		return _activePatient.getTitle();
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getFamilyStatus()
	 */
	@Override
	public FamilyStatusEnum getFamilyStatus() {
		throw new NotImplementedException();
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getStreet()
	 */
	@Override
	public String getStreet() {
		return _activePatient.getStreet();
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getZip()
	 */
	@Override
	public String getZip() {
		return _activePatient.getZip();
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getPlace()
	 */
	@Override
	public String getPlace() {
		return _activePatient.getPlace();
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getCountry()
	 */
	@Override
	public String getCountry() {
		return _activePatient.getCountry();
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getBirthday()
	 */
	@Override
	public GregorianCalendar getBirthday() {
		GregorianCalendar temp = new GregorianCalendar();
		temp.setGregorianChange(_activePatient.getBirthday());
		return temp;
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getJob()
	 */
	@Override
	public String getJob() {
		return _activePatient.getJob();
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getReligion()
	 */
	@Override
	public String getReligion() {
		return _activePatient.getReligion();
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getRegime()
	 */
	@Override
	public String getRegime() {
		return ClobConverter.ClobToString(_activePatient.getRegime());
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getNotice()
	 */
	@Override
	public String getNotice() {
		return ClobConverter.ClobToString(_activePatient.getNotice());
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getGender()
	 */
	@Override
	public GenderEnum getGender() {

		return GenderEnum.valueOf(_activePatient.getGender().getName());
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getIllnesses()
	 */
	@Override
	public IllnessViewable[] getIllnesses() {
		throw new NotImplementedException();
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getFamilyanamnesis()
	 */
	@Override
	public FamilyAnamnesisViewable[] getFamilyanamnesis() {
		throw new NotImplementedException();
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getPatientStates()
	 */
	@Override
	public PatientStateViewable[] getPatientStates() {
		return null;
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getLaborReports()
	 */
	@Override
	public LaborReportViewable[] getLaborReports() {
		return null;
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getTreatments()
	 */
	@Override
	public DietTreatmentViewable[] getTreatments() {
		return null;
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getPatientLikes()
	 */
	@Override
	public PatientLikeViewable[] getPatientLikes() {
		return null;
	}

	/**
	 * @see at.easydiet.teamb.application.viewobject.PatientViewable#getModel()
	 */
	@Override
	public Patient getModel() {
		if (_activePatient == null)
			return null;
		return _activePatient.getModel();
	}

	public PatientDO getPatientDO() {
		return new PatientDO(getModel());
	}
}
