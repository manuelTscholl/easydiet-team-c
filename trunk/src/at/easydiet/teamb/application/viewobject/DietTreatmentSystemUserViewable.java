package at.easydiet.teamb.application.viewobject;

/**
 * Represents a DietTreatmentSystemUser
 */
public interface DietTreatmentSystemUserViewable {

	/**
	 * Gets the systemUser of this instance.
	 * 
	 * @return the systemUser currently set for this instance.
	 */
	public SystemUserViewable getSystemUser();

	/**
	 * Gets the function of this instance.
	 * 
	 * @return the function currently set for this instance.
	 */
	public SystemUserFunctionViewable getFunction();
}
