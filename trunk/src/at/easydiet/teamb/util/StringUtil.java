/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	24.04.2011
 */

package at.easydiet.teamb.util;

/**
 * The Class StringUtil.
 */
public class StringUtil {
	
	/**
	 * Checks if is empty.
	 *
	 * @param str the str
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().isEmpty());
	}

	/**
	 * Convert to null if possible.
	 *
	 * @param string the string
	 * @return the string
	 */
	public static String convertToNullIfPossible(String string) {
		if (string != null && string.isEmpty()) {
			return null;
		} else {
			return string;
		}
	}

	/**
	 * Checks for changed.
	 *
	 * @param oldValue the old value
	 * @param newValue the new value
	 * @return true, if successful
	 */
	public static boolean hasChanged(String oldValue, String newValue) {
		return (newValue != oldValue && (newValue == null || !newValue.equals(oldValue)));
	}
}
