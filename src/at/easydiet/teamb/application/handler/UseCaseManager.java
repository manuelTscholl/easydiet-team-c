/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	18.04.2011
 */

package at.easydiet.teamb.application.handler;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.handler.exception.ExitNotPermittedException;
import at.easydiet.teamb.application.handler.exception.PatientChangeNotPermittedException;
import at.easydiet.teamb.application.util.Event;
import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.util.ExitOptions;
import at.easydiet.teamb.application.util.PatientChangedEventArg;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.teamb.domain.IPatient;
import at.easydiet.teamb.domain.ISystemUser;
import at.easydiet.teamb.domain.object.PatientDO;
import at.easydiet.teamb.domain.object.SystemUserDO;
import at.easydiet.dao.DAOFactory;

/**
 * The Class UseCaseManager.
 */
public class UseCaseManager {

	private static final Logger LOGGER = Logger.getLogger(UseCaseManager.class);

	private static UseCaseManager _useCaseManager;

	private IPatient _patient;
	private ISystemUser _creator;
	private AbstractUseCaseHandler _currentHandler;

	private Event<PatientChangedEventArg> _patientChanged;

	private UseCaseManager() {
		_patientChanged = new Event<PatientChangedEventArg>(this);
		
		//TODO as the user management isn't included in timebox1 & 2, this fix is needed
		_creator = new SystemUserDO(DAOFactory.getInstance().getSystemUserDAO().findById(1l, false));
	}

	// TODO: remove singleton
	/**
	 * Gets the window handler.
	 *
	 * @return the window handler
	 */
	public static UseCaseManager getWindowHandler() {
		if (_useCaseManager == null) {
			_useCaseManager = new UseCaseManager();
		}

		return _useCaseManager;
	}

	/**
	 * Gets the selected patient.
	 *
	 * @return the selected patient
	 */
	public PatientViewable getSelectedPatient() {
		return _patient;
	}
	
	public SystemUserViewable getCreator(){
		return _creator;
	}

	/**
	 * Change patient.
	 *
	 * @param patient the patient
	 * @throws PatientChangeNotPermittedException the patient change not permitted exception
	 */
	public void changePatient(PatientViewable patient) throws PatientChangeNotPermittedException {
		if (!changeAllowed()) {
			// current use case is in a state that doesn't allow to change the patient
			throw new PatientChangeNotPermittedException(getExitOptions());
		} else {
			LOGGER.debug("new Patient: " + patient);

			PatientChangedEventArg pcea = new PatientChangedEventArg(_patient);
			_patient = (PatientDO) patient;
			_patientChanged.fireEvent(pcea);
		}
	}

	/**
	 * Change handler.
	 *
	 * @param handler the handler
	 * @throws ExitNotPermittedException the exit not permitted exception
	 */
	public void changeHandler(AbstractUseCaseHandler handler) throws ExitNotPermittedException {
		if (_currentHandler == handler) {
			LOGGER.debug("currentHandler is the same Object as newHandler");
			return;
		}
		
		if (handler == null) {
			LOGGER.info("new content can not be managed with an AbstractUseCaseHandler");
		}

		if (_currentHandler != null) {
			_currentHandler.exit();
		}

		_currentHandler = handler;

		LOGGER.debug("handler changed to " + _currentHandler);
	}

	/**
	 * Determines if the Handler is able to Handle a change of either the Handler itself or the patient
	 * 
	 * @return true if Handler is in a state where the content can be changed, otherwise false
	 */
	private boolean changeAllowed() {
		if (_currentHandler != null && !_currentHandler.isExitable()) {
			// current use case is in a state that doesn't allow to change the use case or the patient
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Change handler allowed.
	 *
	 * @param newHandler the new handler
	 * @return true, if successful
	 */
	public boolean changeHandlerAllowed(AbstractUseCaseHandler newHandler) {
		if (_currentHandler != newHandler) {
			return changeAllowed();
		} else {
			// it isn't a real change, but it is necessary that is allowed to clear possible sideeffects in the gui
			return true;
		}
	}

	/**
	 * Gets the exit options.
	 *
	 * @return the exit options
	 */
	public ExitOptions getExitOptions() {
		if (_currentHandler != null) {
			return _currentHandler.getExitOptions();
		} else {
			return ExitOptions.None;
		}
	}

	/**
	 * Adds the patient listener.
	 *
	 * @param handler the handler
	 */
	public void addPatientListener(EventListener<PatientChangedEventArg> handler) {
		_patientChanged.addHandler(handler);
	}

	/**
	 * Removes the patient listener.
	 *
	 * @param handler the handler
	 */
	public void removePatientListener(EventListener<PatientChangedEventArg> handler) {
		_patientChanged.removeHandler(handler);
	}

	/**
	 * Show no patient error.
	 *
	 * @return true, if successful
	 */
	public boolean showNoPatientError() {
		return (_patient == null);
	}
}
