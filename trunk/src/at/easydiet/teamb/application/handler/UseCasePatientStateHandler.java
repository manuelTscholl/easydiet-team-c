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

import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;
import at.easydiet.teamb.application.util.ExitOptions;
import at.easydiet.teamb.application.viewobject.PatientStateViewable;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.teamb.domain.object.PatientStateDO;
import at.easydiet.model.PatientState;

/**
 * The Class UseCasePatientStateHandler.
 */
public class UseCasePatientStateHandler extends AbstractUseCaseHandler {
	private PatientStateHandler _handler;
	private boolean _discard;
	
	
	/**
	 * Instantiates a new use case patient state handler.
	 */
	public UseCasePatientStateHandler(PatientStateViewable patientState){
		_handler = new PatientStateHandler(patientState);
		_discard = false;
	}
	
	/**
	 * Instantiates a new use case patient state handler.
	 */
	public UseCasePatientStateHandler(SystemUserViewable creator, PatientViewable patient){
		this(new PatientStateDO(new PatientState()));
		_handler.setCreator(creator);
		_handler.setPatient(patient);
	}
	
	/**
	 * Gets the patient data handler.
	 *
	 * @return the patient data handler
	 */
	public PatientStateHandler getPatientStateHandler() {
		return _handler;
	}
	
	@Override
	public boolean isExitable() {
		if (_handler.isInDatabase()) {
			return (!_handler.isDirty() || (!_handler.isDirty() && _handler.isValid()));
		} else {
			return _discard;
		}
	}

	@Override
	public void save() throws DatabaseException, ErrorInFormException {
		_handler.saveState();
	}

	@Override
	public void discard() throws OperationNotPermittedException {
		_handler.discard();
		_discard = true;
	}

	@Override
	public ExitOptions getExitOptions() {
		if (_handler.isValid()) {
			return ExitOptions.DiscardOrSave;
		} else {
			return ExitOptions.Discard;
		}
	}
}
