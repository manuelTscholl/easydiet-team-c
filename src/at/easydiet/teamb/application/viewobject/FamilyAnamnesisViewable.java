package at.easydiet.teamb.application.viewobject;

/**
 * Represents a FamilyAnamnesis
 */
public interface FamilyAnamnesisViewable {

	/**
	 * Gets the familyAnamnesisId of this instance.
	 * 
	 * @return the familyAnamnesisId currently set for this instance.
	 */
	public long getFamilyAnamnesisId();

	/**
	 * Gets the person of this instance.
	 * 
	 * @return the person currently set for this instance.
	 */
	public String getPerson();

	/**
	 * Gets the illnesses of this instance.
	 * 
	 * @return the illnesses currently set for this instance.
	 */
	public IllnessViewable[] getIllnesses();
}
