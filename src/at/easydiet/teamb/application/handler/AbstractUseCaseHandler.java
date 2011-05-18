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

import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.handler.exception.ExitNotPermittedException;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;
import at.easydiet.teamb.application.util.ExitOptions;

/**
 * An abstract Handler for a uscecasehandler
 * 
 * @author TeamB
 */
public abstract class AbstractUseCaseHandler {
	private static Logger LOGGER = Logger.getLogger(AbstractUseCaseHandler.class);

	protected UseCaseManager _useCaseManager;

	/**
	 * Instantiates a new abstract usecasehandler
	 */
	public AbstractUseCaseHandler() {
		_useCaseManager = UseCaseManager.getWindowHandler();
	}

	/**
	 * Determines that there are Unsaved Data hold by this Handler
	 */
	private boolean _unsavedData;

	// TODO: error message list

	/**
	 * Checks if is exitable.
	 * 
	 * @return true, if is exitable
	 */
	public abstract boolean isExitable();

	/**
	 * Checks for unsaved data.
	 * 
	 * @return true, if successful
	 */
	public boolean hasUnsavedData() {
		return _unsavedData;
	}

	protected void setUnsavedData(boolean unsavedData) {
		_unsavedData = unsavedData;
	}

	/**
	 * Gets the exit options.
	 * 
	 * @return The options that are Available to exit this Handler
	 */
	public ExitOptions getExitOptions() {
		return ExitOptions.None;
	}

	/**
	 * Save.
	 * 
	 * @throws DatabaseException
	 *             the database exception
	 * @throws ErrorInFormException
	 *             the error in form exception
	 */
	public abstract void save() throws DatabaseException, ErrorInFormException;

	/**
	 * Discard.
	 * 
	 * @throws OperationNotPermittedException
	 *             the operation not permitted exception
	 */
	public abstract void discard() throws OperationNotPermittedException;

	/**
	 * Called before the handler gets invisible.
	 * 
	 * @throws ExitNotPermittedException
	 *             when {@link #isExitable()} returns false
	 */
	public void exit() throws ExitNotPermittedException {
		LOGGER.debug("exiting handler " + this);

		if (!isExitable()) {
			throw new ExitNotPermittedException(getExitOptions());
		}
	}

	// TODO: print, create new, delete
}
