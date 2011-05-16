/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.domain;

import at.easydiet.teamb.application.viewobject.LaborParameterViewable;
import at.easydiet.teamb.application.viewobject.LaborReportViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionViewable;
import at.easydiet.teamb.domain.util.CheckOperatorEnum;

/**
 * The Interface ILaborParameter.
 */
public interface ILaborParameter extends LaborParameterViewable {

	/* (non-Javadoc)
	 * @see at.easydiet.model.LaborParameterViewable#getLaborParameterId()
	 */
	long getLaborParameterId();

	/**       
	 * Sets the laborParameterId of this instance. 
	 * @param laborParameterId the new laborParameterId of this instance.
	 */
	void setLaborParameterId(long laborParameterId);

	/* (non-Javadoc)
	 * @see at.easydiet.model.LaborParameterViewable#getValue()
	 */
	String getValue();

	/**       
	 * Sets the Value of this instance. 
	 * @param Value the new Value of this instance.
	 */
	void setValue(String Value);

	/* (non-Javadoc)
	 * @see at.easydiet.model.LaborParameterViewable#getParameterDefinitionUnit()
	 */
	ParameterDefinitionUnitViewable getParameterDefinitionUnit();

	/**       
	 * Sets the parameterDefinitionUnit of this instance. 
	 * @param parameterDefinitionUnit the new parameterDefinitionUnit of this instance.
	 */
	void setParameterDefinitionUnit(ParameterDefinitionUnitViewable parameterDefinitionUnit);

	/* (non-Javadoc)
	 * @see at.easydiet.model.LaborParameterViewable#getCheckOperator()
	 */
	CheckOperatorEnum getCheckOperator();

	/**       
	 * Sets the checkOperator of this instance. 
	 * @param checkOperator the new checkOperator of this instance.
	 */
	void setCheckOperator(CheckOperatorEnum checkOperator);

	/* (non-Javadoc)
	 * @see at.easydiet.model.LaborParameterViewable#getParameterDefinition()
	 */
	ParameterDefinitionViewable getParameterDefinition();

	/**       
	 * Sets the parameterDefinition of this instance. 
	 * @param parameterDefinition the new parameterDefinition of this instance.
	 */
	void setParameterDefinition(ParameterDefinitionViewable parameterDefinition);
	
	LaborReportViewable getLaborReport();
	
	/**
	 * Sets the labor report.
	 *
	 * @param laborReport the new labor report
	 */
	void setLaborReport(LaborReportViewable laborReport); 
}