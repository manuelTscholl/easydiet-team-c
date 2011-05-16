/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	21.04.2011
 */

package at.easydiet.teamb.domain.util;

import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author TeamB
 */
public class CalendarUtil {
	/**
	 * converts a date into a gregorianCalendar
	 * 
	 * @param date
	 *            the date
	 * @return GregorianCalendar
	 */
	public static GregorianCalendar ConvertDateToGregorianCalendar(Date date) {
		GregorianCalendar cal = null;
		if (date != null) {
			cal = new GregorianCalendar();
			cal.setTime(date);
		}
		return cal;
	}

	/**
	 * Converts a GregorianCalendarObject into a date
	 * 
	 * @param cal
	 *            the GregorianCalendar
	 * @return Date
	 */
	public static Date ConvertGregorianCalendarToDate(GregorianCalendar cal) {
		if (cal == null) {
			return null;
		} else {
			return cal.getTime();
		}
	}

	/**
	 * Calculates the days between two Dates (duration)
	 * 
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @return int
	 */
	public static int CalculateDuration(GregorianCalendar startDate, GregorianCalendar endDate) {
		Date start = ConvertGregorianCalendarToDate(startDate);
		Date end = ConvertGregorianCalendarToDate(endDate);
		long days = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
		return (int) days;
	}

	/**
	 * Calculates the days between two Dates (duration)
	 * 
	 * @param startDate
	 *            the start date
	 * @param endDate
	 *            the end date
	 * @return int
	 */
	public static int CalculateDuration(Date startDate, Date endDate) {
		long days = (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
		return (int) days;
	}
}
