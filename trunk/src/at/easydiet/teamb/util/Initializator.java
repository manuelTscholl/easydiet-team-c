/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.util;

/**
 * The Interface Initializator.
 *
 * @param <T> the generic type
 */
public interface Initializator<T> {
	
	/**
	 * Creates the instance.
	 *
	 * @return the t
	 */
	public T createInstance();
}
