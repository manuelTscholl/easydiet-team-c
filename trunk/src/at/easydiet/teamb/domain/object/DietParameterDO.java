package at.easydiet.teamb.domain.object;

import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.util.Event;
import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.domain.IDietParameter;
import at.easydiet.teamb.domain.util.CalendarUtil;
import at.easydiet.model.DietParameter;
import at.easydiet.teamb.presentation.util.DietParameterChangedEventArg;

/**
 * Represents a DietParameterDomainObject in the domain layer
 */
public class DietParameterDO extends DietParameterTemplateDO implements IDietParameter {
	private static Logger LOGGER = Logger.getLogger(DietParameterDO.class);

	private DietParameter _dietParameter;
	
	private Event<DietParameterChangedEventArg> _possibleChanged;
	
	private String _actual;
	private boolean _possible;

	/**
	 * Instantiates a new diet parameter do.
	 * 
	 * @param dietParameter
	 *            the diet parameter
	 */
	public DietParameterDO(DietParameter dietParameter) {
		super(dietParameter);
		
		if (dietParameter == null) {
			LOGGER.debug("DietParameter is null");
		}

		_dietParameter = dietParameter;
		
		_possibleChanged = new Event<DietParameterChangedEventArg>(this);
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameter#getStart()
	 */
	@Override
	public GregorianCalendar getStart() {
		return CalendarUtil.ConvertDateToGregorianCalendar(_dietParameter.getStart());
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameter#setStart(java.util.GregorianCalendar)
	 */
	@Override
	public void setStart(GregorianCalendar start) {
		_dietParameter.setStart(CalendarUtil.ConvertGregorianCalendarToDate(start));
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameter#getActual()
	 */
	@Override
	public String getActual() {
		return _actual;
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameter#setActual(java.lang.String)
	 */
	@Override
	public void setActual(String actual) {
		_actual = actual;
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameter#isPossible()
	 */
	@Override
	public boolean isPossible() {
		return _possible;
	}

	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameter#setPossible(boolean)
	 */
	@Override
	public void setPossible(boolean possible) {
		if (_possible == (_possible = possible)) {
			_possibleChanged.fireEvent(new DietParameterChangedEventArg());
		}
	}
	
	@Override
	public DietParameter getModel() {
		return _dietParameter;
	}
	
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameter#addPossibleChangeListener(at.easydiet.application.util.EventListener)
	 */
	@Override
	public void addPossibleChangeListener(EventListener<DietParameterChangedEventArg> handler) {
		_possibleChanged.addHandler(handler);
	}
	
	/* (non-Javadoc)
	 * @see at.easydiet.domain.object.IDietParameter#removePossibleChangeListener(at.easydiet.application.util.EventListener)
	 */
	@Override
	public void removePossibleChangeListener(EventListener<DietParameterChangedEventArg> handler) {
		_possibleChanged.removeHandler(handler);
	}
}
