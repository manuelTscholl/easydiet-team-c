/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	25.04.2011
 */

package at.easydiet.teamb.application.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.util.Event;
import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;

/**
 * A Abstract Handler
 * @param <T> The ErrorField
 * @author TeamB
 */
public abstract class AbstractHandler<T> {
	private static final Logger LOGGER = Logger.getLogger(AbstractHandler.class);
	
	protected Event<ValidatorArgs<T>> _validaded;
	
	protected List<T> _errorFields;
	
	protected List<AbstractHandler<?>> _handlers;
	
	/**
	 * Instantiates a new abstract handler.
	 */
	public AbstractHandler() {
		_validaded = new Event<ValidatorArgs<T>>(this);
		
		_errorFields = new ArrayList<T>();
		
		_handlers = new ArrayList<AbstractHandler<?>>();
	}
	
	/**
	 * Adds a ValidadedListener
	 *
	 * @param handler the handler
	 */
	public void addValidadedListener(EventListener<ValidatorArgs<T>> handler) {
		_validaded.addHandler(handler);
		handler.fired(this, new ValidatorArgs<T>(_errorFields));
	}
	
	/**
	 * Removes a ValidadedListener
	 *
	 * @param handler the handler
	 */
	public void removeValidadedListener(EventListener<ValidatorArgs<T>> handler) {
		_validaded.removeHandler(handler);
	}
	
	/**
	 * Checks if no errors exists
	 *
	 * @return true, if no errors
	 */
	public boolean isValid() {
		if (_errorFields.isEmpty()) {
			for (AbstractHandler<?> handler : _handlers) {
				if (!handler.isValid()) {
					return false;
				}
			}
			
			return true;
		} else {
			LOGGER.debug(this + " found errors " + _errorFields);
			return false;
		}
	}
}
