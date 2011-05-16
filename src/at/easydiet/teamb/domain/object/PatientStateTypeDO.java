package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.domain.IPatientStateType;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.model.PatientStateType;

/**
 * Represents a PatientStateType in the domain layer
 */
public class PatientStateTypeDO extends AbstractDO<PatientStateType> implements IPatientStateType {
	private static Logger LOGGER = Logger.getLogger(PatientStateTypeDO.class);

	private PatientStateType _patientStateType;

	/**
	 * Instantiates a new patientstate type.
	 * 
	 * @param patientStateType
	 *            the patientstate type
	 */
	public PatientStateTypeDO(PatientStateType patientStateType) {
		if (patientStateType == null) {
			LOGGER.debug("PatientStateType is null");
		}

		_patientStateType = patientStateType;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientStateType#getName()
	 */
	@Override
	public String getName() {
		return _patientStateType.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientStateType#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		_patientStateType.setName(name);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IPatientStateType#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_patientStateType == null);
	}

	@Override
	public PatientStateType getModel() {
		return _patientStateType;
	}
}
