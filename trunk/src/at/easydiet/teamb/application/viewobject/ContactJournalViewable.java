package at.easydiet.teamb.application.viewobject;

import java.util.GregorianCalendar;

/**
 * Represents a ContactJournal
 */
public interface ContactJournalViewable {

	/**
	 * Gets the contactJournalId of this instance.
	 * 
	 * @return the contactJournalId currently set for this instance.
	 */
	public long getContactJournalId();

	/**
	 * Gets the date of this instance.
	 * 
	 * @return the date currently set for this instance.
	 */
	public GregorianCalendar getDate();

	/**
	 * Gets the description of this instance.
	 * 
	 * @return the description currently set for this instance.
	 */
	public String getDescription();

	/**
	 * Gets the createdBy of this instance.
	 * 
	 * @return the createdBy currently set for this instance.
	 */
	public SystemUserViewable getCreatedBy();

	/**
	 * Gets the contactType of this instance.
	 * 
	 * @return the contactType currently set for this instance.
	 */
	public ContactTypeViewable getContactType();

	/**
	 * Gets the dietTreatment
	 * 
	 * @return the dietTreatment
	 */
	public DietTreatmentViewable getDietTreatment();
}
