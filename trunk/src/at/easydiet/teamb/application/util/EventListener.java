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

/**
 * An {@link EventListener} represents an {@link java.util.EventListener} which can receive an event notification fired by an {@link Event}.
 * {@link EventListener} listeners receive the sender of an event and a {@link EventArgs} object containing the Event MetaData.
 * 
 * @param <T>
 *            Type of the {@link EventArgs}
 * @author TeamB
 */
public interface EventListener<T extends EventArgs> extends java.util.EventListener {

	/**
	 * This method is called as the event is fired.
	 * 
	 * @param sender
	 *            The sender of the event.
	 * @param eventObject
	 *            The {@link EventArgs} instance containing the event's meta data.
	 */
	public void fired(Object sender, T eventObject);
}
