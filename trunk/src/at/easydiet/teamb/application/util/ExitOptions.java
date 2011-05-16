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
 * Possibilities to Exit a Handler
 * 
 * @author TeamB
 */
public enum ExitOptions {
	/**
	 * There are no options for exit. (Usually an exit should be possible without user interaction)
	 */
	None,
	
	/**
	 * Only possibility to exit the Handler is to discard all changes.
	 */
	Discard,
	
	/**
	 * Only possibility to exit the Handler is to save all changes.
	 */
	Save,
	
	/**
	 * Exit of Handler is possible when saving or discarding all changes
	 */
	DiscardOrSave
}
