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
 * {@link EventArgs} is the base class for classes containing event data. This class contains no event data. Derived types are used to pass meta data to an
 * {@link EventListener} as an {@link Event} is raised.
 * 
 * @author TeamB
 */
public class EventArgs {
	/**
	 * An empty {@link EventArgs} object containing no event data.
	 */
	public static final EventArgs Empty = new EventArgs();

}
