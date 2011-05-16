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

import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.viewobject.LaborParameterViewable;
import at.easydiet.teamb.application.viewobject.LaborReportTypeViewable;
import at.easydiet.teamb.application.viewobject.LaborReportViewable;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;

/**
 * The Interface ILaborReport.
 */
public interface ILaborReport extends LaborReportViewable {

	/**
	 * Gets the laborReportId of this instance.
	 * 
	 * @return the laborReportId currently set for this instance.
	 */
	public abstract long getLaborReportId();

	/**
	 * Sets the laborReportId of this instance.
	 * 
	 * @param laborReportId
	 *            the new laborReportId of this instance.
	 */
	public abstract void setLaborReportId(long laborReportId);

	/**
	 * Gets the date of this instance.
	 * 
	 * @return the date currently set for this instance.
	 */
	public abstract GregorianCalendar getDate();

	/**
	 * Sets the date of this instance.
	 * 
	 * @param date
	 *            the new date of this instance.
	 */
	public abstract void setDate(GregorianCalendar date);

	/**
	 * Gets the notice of this instance.
	 * 
	 * @return the notice currently set for this instance.
	 */
	public abstract String getNotice();

	/**
	 * Sets the notice of this instance.
	 * 
	 * @param notice
	 *            the new notice of this instance.
	 */
	public abstract void setNotice(String notice);

	/**
	 * Gets the creator of this instance.
	 * 
	 * @return the creator currently set for this instance.
	 */
	public abstract SystemUserViewable getCreator();

	/**
	 * Sets the creator of this instance.
	 * 
	 * @param creator
	 *            the new creator of this instance.
	 */
	public abstract void setCreator(SystemUserViewable creator);

	/**
	 * Gets the dietParameters of this instance.
	 * 
	 * @return the dietParameters currently set for this instance.
	 */
	public abstract LaborParameterViewable[] getLaborParameters();

	/**
	 * Adds the labor parameter.
	 *
	 * @param dietParameter the diet parameter
	 */
	public abstract void addLaborParameter(LaborParameterViewable dietParameter);

	/**
	 * Removes the labor parameter.
	 *
	 * @param dietParameter the diet parameter
	 */
	public abstract void removeLaborParameter(LaborParameterViewable dietParameter);

	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.LaborReportViewable#getPatient()
	 */
	public abstract PatientViewable getPatient();

	/**
	 * Sets the patient.
	 * 
	 * @param patient
	 *            the new patient
	 */
	public abstract void setPatient(PatientViewable patient);
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public abstract void setType(LaborReportTypeViewable type);
	
	/* (non-Javadoc)
	 * @see at.easydiet.application.viewobject.LaborReportViewable#getType()
	 */
	public abstract LaborReportTypeViewable getType();
	
	/**
	 * Save.
	 *
	 * @throws DatabaseException the database exception
	 */
	public abstract void save() throws DatabaseException;
	
	/**
	 * Delete.
	 *
	 * @throws DatabaseException the database exception
	 */
	public abstract void delete() throws DatabaseException;
	
	/**
	 * Discard.
	 */
	public abstract void discard();
	

	/**
	 * @return true if this labor report was once saved in the database
	 */
	public abstract boolean isInDatabase();
}