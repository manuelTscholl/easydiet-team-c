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

import java.util.Set;

import at.easydiet.teamb.application.viewobject.FamilyAnamnesisViewable;
import at.easydiet.teamb.application.viewobject.IllnessViewable;
import at.easydiet.model.FamilyAnamnesis;
import at.easydiet.model.Illness;

/**
 * The Interface IFamilyAnamnesis.
 */
public interface IFamilyAnamnesis extends FamilyAnamnesisViewable {

	/**
	 * Gets the familyAnamnesisId of this instance.
	 * 
	 * @return the familyAnamnesisId currently set for this instance.
	 */
	public abstract long getFamilyAnamnesisId();

	/**
	 * Sets the familyAnamnesisId of this instance.
	 * 
	 * @param familyAnamnesisId
	 *            the new familyAnamnesisId of this instance.
	 */
	public abstract void setFamilyAnamnesisId(long familyAnamnesisId);

	/**
	 * Gets the person of this instance.
	 * 
	 * @return the person currently set for this instance.
	 */
	public abstract String getPerson();

	/**
	 * Sets the person of this instance.
	 * 
	 * @param person
	 *            the new person of this instance.
	 */
	public abstract void setPerson(String person);

	/**
	 * Gets the illnesses of this instance.
	 * 
	 * @return the illnesses currently set for this instance.
	 */
	public abstract IllnessViewable[] getIllnesses();

	/**
	 * Sets the illnesses of this instance.
	 * 
	 * @param illnesses
	 *            the new illnesses of this instance.
	 */
	public abstract void setIllnesses(Set<Illness> illnesses);

	/**
	 * Checks if the {@link FamilyAnamnesis} is set.
	 *
	 * @return true, if the {@link FamilyAnamnesis} is set
	 */
	public abstract boolean isSet();

}