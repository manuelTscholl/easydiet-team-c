package at.easydiet.teamb.application.viewobject;

/**
 * Represents a DietParameterSet
 */
public interface DietParameterSetViewable {

	/**
	 * Gets the dietParameterSetId of this instance.
	 * 
	 * @return the dietParameterSetId currently set for this instance.
	 */
	public long getDietParameterSetId();

	/**
	 * Gets the name of this instance.
	 * 
	 * @return the name currently set for this instance.
	 */
	public String getName();

	/**
	 * Gets the dietParameterTemplates of this instance.
	 * 
	 * @return the dietParameterTemplates currently set for this instance.
	 */
	public DietParameterTemplateViewable[] getDietParameterTemplates();
}
