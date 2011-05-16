/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	19.04.2011
 */

package at.easydiet.teamb.application.util;

import java.util.LinkedList;

import org.apache.log4j.Logger;

/**
 * This class is used to create a new event which notifies a list of {@link EventListener} objects.
 * 
 * @author TeamB
 * @param <T>
 *            The type of {@link EventArgs} passed to the {@link EventListener}s.
 */
public class Event<T extends EventArgs> {
	private static final Logger LOGGER = Logger.getLogger(Event.class);
	
	private LinkedList<EventListener<T>> _listeners;
	private Object _sender;

	/**
	 * Initializes a new instance of the {@link Event} class.
	 * 
	 * @param sender
	 *            The owner or sender of this event.
	 */
	public Event(Object sender) {
		_sender = sender;
		_listeners = new LinkedList<EventListener<T>>();
	}

	/**
	 * Adds a new {@link EventListener} to the list of items which will be notified as the event get's fired.
	 * 
	 * @param handler
	 *            the listener to be added
	 */
	public void addHandler(EventListener<T> handler) {
		LOGGER.debug("add handler " + handler);
		_listeners.add(handler);
	}

	/**
	 * Removes the specified {@link EventListener} from the list of items which will be notified as the event get's fired.
	 * 
	 * @param handler
	 *            the listener to be removed
	 */
	public void removeHandler(EventListener<T> handler) {
		LOGGER.debug("remove handler " + handler);
		_listeners.remove(handler);
	}

	/**
	 * Fires the event and notifies all registered {@link EventListener}s using the specified {@link EventArgs} instance.
	 * 
	 * @param e
	 *            The {@link EventArgs} instance to send to all items.
	 */
	public void fireEvent(T e) {
		LOGGER.debug("calling all listeners");
		
		// call all event handlers
		for (EventListener<T> handler : _listeners) {
			handler.fired(_sender, e);
		}
		
		LOGGER.debug("finished calling all listeners");
	}

}