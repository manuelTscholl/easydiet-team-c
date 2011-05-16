/**
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	19.04.2011
 */
package at.easydiet.teamb.util.converter;

/**
 * @author TeamB
 */
public class MilligramToKilogram implements Convertable {
	
	/** The Constant CONVERT_STRING. */
	public static final String CONVERT_STRING = "MilligramToKilogram";

	
	public double convert(double value) {
		return value / 1000000.0;
	}

	
	public String getConvertString() {
		return CONVERT_STRING;
	}

}
