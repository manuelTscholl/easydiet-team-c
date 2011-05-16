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

import java.util.GregorianCalendar;

import at.easydiet.teamb.application.viewobject.ContactJournalViewable;
import at.easydiet.teamb.application.viewobject.ContactTypeViewable;
import at.easydiet.teamb.application.viewobject.DietTreatmentViewable;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.model.ContactJournal;

/**
 * The Interface IContactJournal.
 */
public interface IContactJournal extends ContactJournalViewable {

	/**
	 * Gets the contactJournalId of this instance.
	 * 
	 * @return the contactJournalId currently set for this instance.
	 */
	public abstract long getContactJournalId();

	/**
	 * Sets the contactJournalId of this instance.
	 * 
	 * @param contactJournalId
	 *            the new contactJournalId of this instance.
	 */
	public abstract void setContactJournalId(long contactJournalId);

	/**
	 * Gets the date of this instance.
	 * 
	 * @return the date currently set for this instance.
	 */
	public abstract GregorianCalendar getDate();

	/**
	 * Sets the date of this instance.
	 * 
	 * @param cal
	 *            the new date of this instance.
	 */
	public abstract void setDate(GregorianCalendar cal);

	/**
	 * Gets the description of this instance.
	 * 
	 * @return the description currently set for this instance.
	 */
	public abstract String getDescription();

	/**
	 * Sets the description of this instance.
	 * 
	 * @param description
	 *            the new description of this instance.
	 */
	public abstract void setDescription(String description);

	/**
	 * Gets the createdBy of this instance.
	 * 
	 * @return the createdBy currently set for this instance.
	 */
	public abstract SystemUserViewable getCreatedBy();

	/**
	 * Sets the createdBy of this instance.
	 * 
	 * @param createdBy
	 *            the new createdBy of this instance.
	 */
	public abstract void setCreatedBy(SystemUserViewable createdBy);

	/**
	 * Gets the contactType of this instance.
	 * 
	 * @return the contactType currently set for this instance.
	 */
	public abstract ContactTypeViewable getContactType();

	/**
	 * Sets the contactType of this instance.
	 * 
	 * @param contactType
	 *            the new contactType of this instance.
	 */
	public abstract void setContactType(ContactTypeViewable contactType);

	public abstract DietTreatmentViewable getDietTreatment();

	/**       
	 * Sets the dietTreatment of this instance. 
	 * @param dietTreatment the new dietTreatment of this instance.
	 */
	public abstract void setDietTreatment(DietTreatmentViewable dietTreatment);

	/**
	 * Checks if is {@link ContactJournal} the.
	 *
	 * @return true, if is sets the
	 */
	public abstract boolean isSet();
}