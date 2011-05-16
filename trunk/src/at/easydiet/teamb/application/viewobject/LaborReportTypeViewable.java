/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.application.viewobject;


/**
 * The Interface LaborReportTypeViewable.
 */
public interface LaborReportTypeViewable {

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();

	/**
	 * Gets the parameter definitions.
	 *
	 * @return the parameter definitions
	 */
	public ParameterDefinitionViewable[] getParameterDefinitions();

}