/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	10.05.2011
 */

package at.easydiet.teamb.application.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * The Class RoundUtil.
 */
public class RoundUtil {
	
	/**
	 * Round.
	 *
	 * @param value the value
	 * @param decimalPlace the decimal place
	 * @param mode the mode
	 * @return the double
	 */
	
	/**
	 * Round.
	 *
	 * @param value the value
	 * @param decimalPlace the decimal place
	 * @param mode the mode
	 * @return the double
	 */
	public static double round(double value, int decimalPlace, RoundingMode mode){
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(decimalPlace,mode);
	    return bd.doubleValue();
	}
}
