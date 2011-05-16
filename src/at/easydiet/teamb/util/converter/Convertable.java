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
public interface Convertable {
	/**
	 * @param value
	 *            The value in the source unit
	 * @return The value in the destination unit
	 */
	public double convert(double value);

	/**
	 * @return A String indicating the source and destination unit used for identifying the converter.
	 */
	public String getConvertString();

}
