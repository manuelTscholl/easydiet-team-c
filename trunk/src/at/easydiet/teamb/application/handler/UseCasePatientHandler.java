/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	21.04.2011
 */

package at.easydiet.teamb.application.handler;

import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;
import at.easydiet.teamb.application.util.ExitOptions;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.domain.object.PatientDO;
import at.easydiet.model.Patient;

/**
 * @author TeamB
 */
public class UseCasePatientHandler extends AbstractUseCaseHandler {
	private PatientDataHandler _handler;
	
	private boolean _discard;

	/**
	 * Instantiates a new use UseCaseCreateDietPlanHandler
	 * 
	 * @param patient
	 *            the patient
	 */
	public UseCasePatientHandler(PatientViewable patient) {
		_handler = new PatientDataHandler((PatientDO) patient);
		_discard = false;
	}

	/**
	 * Instantiates a new use UseCaseCreateDietPlanHandler.
	 */
	public UseCasePatientHandler() {
		this(new PatientDO(new Patient()));
	}

	/**
	 * Gets the patient data handler.
	 * 
	 * @return the patient data handler
	 */
	public PatientDataHandler getPatientDataHandler() {
		return _handler;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.application.handler.AbstractUseCaseHandler#isExitable()
	 */
	@Override
	public boolean isExitable() {
		if (_handler.isInDatabase()) {
			return (!_handler.isDirty() || (!_handler.isDirty() && _handler.isValid()));
		} else {
			return _discard;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.application.handler.AbstractUseCaseHandler#save()
	 */
	@Override
	public void save() throws DatabaseException, ErrorInFormException {
		_handler.save();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.application.handler.AbstractUseCaseHandler#discard()
	 */
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
