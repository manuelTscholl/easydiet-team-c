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

import at.easydiet.teamb.application.viewobject.LaborReportTypeViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionViewable;

/**
 * The Interface ILaborReportType.
 */
public interface ILaborReportType extends LaborReportTypeViewable {

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.LaborReportTypeViewable#getName()
	 */
	public abstract String getName();

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.LaborReportTypeViewable#getParameterDefinitions()
	 */
	public abstract ParameterDefinitionViewable[] getParameterDefinitions();
	
	/**
	 * Sets the name.
	 *
	 * @param Name the new name
	 */
	public abstract void setName(String Name);

	/**
	 * Adds the parameter defintion.
	 *
	 * @param def the def
	 */
	public abstract void addParameterDefintion(ParameterDefinitionViewable def);
	
	/**
	 * Removes the parameter defintion.
	 *
	 * @param def the def
	 */
	public abstract void removeParameterDefintion(ParameterDefinitionViewable def);

}