/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	09.05.2011
 */

package at.easydiet.teamb.application.handler;

import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;
import at.easydiet.teamb.application.util.ExitOptions;

/**
 * The Class ProxyUseCaseHandler.
 */
public class ProxyUseCaseHandler extends AbstractUseCaseHandler {

	/** The handler. */
	private AbstractUseCaseHandler _handler;

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.application.handler.AbstractUseCaseHandler#isExitable()
	 */
	@Override
	public boolean isExitable() {
		if (_handler != null) {
			return _handler.isExitable();
		} else {
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.application.handler.AbstractUseCaseHandler#save()
	 */
	@Override
	public void save() throws DatabaseException, ErrorInFormException {
		if (_handler != null) {
			_handler.save();
		} else {
			// TODO: change to operation not permitted exception
			throw new ErrorInFormException();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.application.handler.AbstractUseCaseHandler#discard()
	 */
	@Override
	public void discard() throws OperationNotPermittedException {
		if (_handler != null) {
			_handler.discard();
		} else {
			throw new OperationNotPermittedException();
		}
	}

	/**
	 * Sets the handler.
	 * 
	 * @param handler
	 *            the new handler
	 */
	public void setHandler(AbstractUseCaseHandler handler) {
		_handler = handler;
	}

	@Override
	public ExitOptions getExitOptions() {
		if (_handler != null) {
			return _handler.getExitOptions();
		} else {
			return super.getExitOptions();
		}
	}

}
