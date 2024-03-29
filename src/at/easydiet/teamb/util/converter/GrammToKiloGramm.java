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
public class GrammToKiloGramm implements Convertable {

	/** The Constant CONVERT_STRING. */
	public static final String CONVERT_STRING = "GrammToKiloGramm";

	public double convert(double value) {
		return value / 1000.0;
	}


	public String getConvertString() {
		return CONVERT_STRING;
	}

}
