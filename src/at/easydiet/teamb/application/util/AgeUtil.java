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

import java.util.GregorianCalendar;

//TODO Alter auf halbes jahr berechnen
/**
 * The Class AgeUtil.
 */
public class AgeUtil {
	
	/**
	 * Calculate age.
	 *
	 * @param gebDatum the geb datum
	 * @param aktDatum the akt datum
	 * @return the int
	 */
	public static int calculateAge (GregorianCalendar gebDatum, GregorianCalendar aktDatum) {
        int alter = 0;
        
        if (aktDatum.before(gebDatum))
            return -1;
 
        alter = aktDatum.get(GregorianCalendar.YEAR) - gebDatum.get(GregorianCalendar.YEAR);
        
        if ( (aktDatum.get(GregorianCalendar.MONTH) < gebDatum.get(GregorianCalendar.MONTH))
            ||(aktDatum.get(GregorianCalendar.MONTH) == gebDatum.get(GregorianCalendar.MONTH)
                && aktDatum.get(GregorianCalendar.DAY_OF_MONTH) < gebDatum.get(GregorianCalendar.DAY_OF_MONTH)))
            --alter;
        
        return alter;
        
    }
}
