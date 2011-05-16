/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	06.05.2011
 */

package at.easydiet.teamb.domain;

import at.easydiet.teamb.application.viewobject.DietParameterSetViewable;
import at.easydiet.teamb.application.viewobject.DietParameterTemplateViewable;
import at.easydiet.teamb.domain.object.DietParameterTemplateDO;
import at.easydiet.model.DietParameterSet;

/**
 * The Interface IDietParameterSet.
 */
public interface IDietParameterSet extends DietParameterSetViewable {

	/**
	 * Gets the dietParameterSetId of this instance.
	 * 
	 * @return the dietParameterSetId currently set for this instance.
	 */
	public abstract long getDietParameterSetId();

	/**
	 * Sets the dietParameterSetId of this instance.
	 * 
	 * @param dietParameterSetId
	 *            the new dietParameterSetId of this instance.
	 */
	public abstract void setDietParameterSetId(long dietParameterSetId);

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public abstract String getName();

	/**
	 * Sets the name of this instance.
	 * 
	 * @param name
	 *            the new name of this instance.
	 */
	public abstract void setName(String name);

	/**
	 * Gets the dietParameterTemplates of this instance.
	 * 
	 * @return the dietParameterTemplates currently set for this instance.
	 */
	public abstract DietParameterTemplateViewable[] getDietParameterTemplates();

	/**
	 * Adds a DietParamaterTemplate.
	 *
	 * @param dietParameterTemplate the template to add
	 */
	public abstract void addDietParameterTemplate(DietParameterTemplateDO dietParameterTemplate);

	/**
	 * Removes a DietParameterTemplate.
	 *
	 * @param dietParameterTemplate the template to remove
	 */
	public abstract void removeDietParameterTemplate(DietParameterTemplateDO dietParameterTemplate);

	/**
	 * Checks if is sets the {@link DietParameterSet}.
	 * 
	 * @return true, if is sets the
	 */
	public abstract boolean isSet();

}