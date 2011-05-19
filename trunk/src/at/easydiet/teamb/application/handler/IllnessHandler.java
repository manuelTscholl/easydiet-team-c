/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	07.05.2011
 */

/**
 * 
 */
package at.easydiet.teamb.application.handler;

import org.apache.log4j.Logger;

import at.easydiet.model.Patient;
import at.easydiet.teamb.application.util.ValidatorArgs.IllnessErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.application.viewobject.IllnessViewable;
import at.easydiet.teamb.util.StringUtil;

/**
 * @author TeamB
 */
public class IllnessHandler extends AbstractHandler<IllnessErrorField> {

	private static Logger LOGGER = Logger.getLogger(IllnessHandler.class);

	IllnessViewable _illness;
	PatientDataHandler _patientDataHandler;

	/**
	 * Instantiates a new illness handler.
	 * 
	 * @param patientDataHandler the patient data handler
	 */
	public IllnessHandler(PatientDataHandler patientDataHandler) {
		this(patientDataHandler, null);
	}

	/**
	 * Instantiates a new illness handler.
	 * 
	 * @param patientDataHandler the patient data handler
	 * @param illness the illness which the {@link Patient} had
	 */
	public IllnessHandler(PatientDataHandler patientDataHandler,
			IllnessViewable illness) {
		_patientDataHandler = patientDataHandler;
		_illness = illness;

		validateIllness();
	}

	/**
	 * Gets the illness.
	 * 
	 * @return the illness
	 */
	public IllnessViewable getIllness() {
		return _illness;
	}

	/**
	 * Sets the name.
	 * 
	 * @param illness the new illness of the {@link Patient}
	 * @see at.easydiet.domain.IIllness#setName(java.lang.String)
	 */
	public void setIllness(IllnessViewable illness) {
		if (_illness != null) {
			_patientDataHandler.removeIllness(this);
		}

		_illness = illness;

		if (illness != null) {
			_patientDataHandler.addIllness(this);
		}

		validateIllness();
	}

	public void removeErrors() {
		_errorFields.clear();
		_validaded
				.fireEvent(new ValidatorArgs<IllnessErrorField>(_errorFields));
	}

	/**
	 * Validate the illness.
	 */
	private void validateIllness() {
		_errorFields.clear();

		if (_illness == null || StringUtil.isEmpty(_illness.getName())) {
			LOGGER.warn("Illness is Empty or Null");
			_errorFields.add(IllnessErrorField.EMPTY_NAME);
		}

		_validaded
				.fireEvent(new ValidatorArgs<IllnessErrorField>(_errorFields));
	}
}
