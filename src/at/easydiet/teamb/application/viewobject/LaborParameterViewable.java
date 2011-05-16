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

import at.easydiet.teamb.domain.util.CheckOperatorEnum;

/**
 * The Interface LaborParameterViewable.
 */
public interface LaborParameterViewable {

	/**       
	 * Gets the laborParameterId of this instance. 
	 * @return the laborParameterId currently set for this instance.
	 */
	public abstract long getLaborParameterId();

	/**       
	 * Gets the Value of this instance. 
	 * @return the Value currently set for this instance.
	 */
	public abstract String getValue();

	/**       
	 * Gets the parameterDefinitionUnit of this instance. 
	 * @return the parameterDefinitionUnit currently set for this instance.
	 */
	public abstract ParameterDefinitionUnitViewable getParameterDefinitionUnit();

	/**       
	 * Gets the checkOperator of this instance. 
	 * @return the checkOperator currently set for this instance.
	 */
	public abstract CheckOperatorEnum getCheckOperator();

	/**       
	 * Gets the parameterDefinition of this instance. 
	 * @return the parameterDefinition currently set for this instance.
	 */
	public abstract ParameterDefinitionViewable getParameterDefinition();
	
	/**
	 * Gets the labor report.
	 *
	 * @return the labor report
	 */
	public abstract LaborReportViewable getLaborReport();
}