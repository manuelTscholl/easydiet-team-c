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
import at.easydiet.teamb.application.viewobject.LaborReportViewable;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.teamb.domain.ISystemUser;
import at.easydiet.teamb.domain.object.SystemUserDO;
import at.easydiet.dao.DAOFactory;

/**
 * The Class UseCaseLaborReportHandler.
 */
public class UseCaseLaborReportHandler extends AbstractUseCaseHandler {
	private LaborReportHandler _handler;
	
	private boolean _discard;

	/**
	 * Instantiates a new use case labor report handler.
	 *
	 * @param report the report
	 */
	public UseCaseLaborReportHandler(LaborReportViewable report) {
		_handler = new LaborReportHandler(report);
		_discard = false;
	}

	/**
	 * Instantiates a new use case labor report handler.
	 *
	 * @param creator the creator
	 * @param patient the patient
	 */
	public UseCaseLaborReportHandler(SystemUserViewable creator, PatientViewable patient) {
		_handler = new LaborReportHandler(creator, patient);
	}

	/**
	 * Gets the labor report handler.
	 *
	 * @return the labor report handler
	 */
	public LaborReportHandler getLaborReportHandler() {
		return _handler;
	}

	/* (non-Javadoc)
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

	/* (non-Javadoc)
	 * @see at.easydiet.application.handler.AbstractUseCaseHandler#save()
	 */
	@Override
	public void save() throws DatabaseException, ErrorInFormException {
		_handler.save();
	}

	/* (non-Javadoc)
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

