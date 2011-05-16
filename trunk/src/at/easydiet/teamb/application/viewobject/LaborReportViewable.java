package at.easydiet.teamb.application.viewobject;

import java.util.GregorianCalendar;


/**
 * Represents a LaborReport
 */
public interface LaborReportViewable {

	/**
	 * Gets the laborReportId of this instance.
	 * 
	 * @return the laborReportId currently set for this instance.
	 */
	public long getLaborReportId();

	/**
	 * Gets the date of this instance.
	 * 
	 * @return the date currently set for this instance.
	 */
	public GregorianCalendar getDate();

	/**
	 * Gets the notice of this instance.
	 * 
	 * @return the notice currently set for this instance.
	 */
	public String getNotice();

	/**
	 * Gets the creator of this instance.
	 * 
	 * @return the creator currently set for this instance.
	 */
	public SystemUserViewable getCreator();

	/**
	 * Gets the dietParameters of this instance.
	 * 
	 * @return the dietParameters currently set for this instance.
	 */
	public LaborParameterViewable[] getLaborParameters();

	
	/**
	 * Gets the patient.
	 *
	 * @return the patient
	 */
	public PatientViewable getPatient();
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public LaborReportTypeViewable getType();
}
