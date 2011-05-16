/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	21.04.2011
 */

package at.easydiet.teamb.application.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

import at.easydiet.teamb.domain.IDietParameter;
import at.easydiet.teamb.domain.IDietTreatment;
import at.easydiet.teamb.domain.ITimeSpan;
import at.easydiet.teamb.domain.object.DietParameterDO;
import at.easydiet.teamb.domain.object.DietTreatmentDO;
import at.easydiet.model.TimeSpan;

/**
 * Utility Class to check Date and Duration and TimeSpans
 * 
 * @author TeamB
 */
public class TimeSpanUtil {

	/**
	 * Possible time span.
	 * 
	 * @param dietTreatment
	 *            the diet treatment to check the date with the {@link DietParameterDO}
	 * @param dietParameter
	 *            the diet parameter to check the date with the {@link DietTreatmentDO}
	 * @return true, if successful that the {@link DietParameterDO} is between the {@link DietTreatmentDO}
	 */
	public static boolean possibleTimeSpan(IDietTreatment dietTreatment, IDietParameter dietParameter) {

		return between(dietTreatment.getStart(), dietTreatment.getDuration(), dietParameter.getStart(), dietParameter.getDuration());
	}

	/**
	 * Check if the secound TimeSpan starts not before the first timespan
	 * 
	 * @param timeSpan1
	 *            the time span1
	 * @param timeSpan2
	 *            the time span2
	 * @return true, the timeSpan dont come in conflict
	 */
	public static boolean possibleTimeSpan(ITimeSpan timeSpan1, ITimeSpan timeSpan2) {
		// t2011_03_01, 20, t2011_04_01, 0
		// return endsBefore(timeSpan2.getStart(), timeSpan2.getDuration(), timeSpan1.getStart(), 0);
		return !between(timeSpan1.getStart(), 0, timeSpan2.getStart(), timeSpan2.getDuration());
	}

	/**
	 * Checks if the dietParameter is during a TimeSpan
	 * 
	 * @param timeSpan
	 *            which you want to check
	 * @param dietParameter
	 *            which you want to check in the timeSpan
	 * @return if the dietParameter is during the timeSpan
	 */
	public static boolean possibleTimeSpan(ITimeSpan timeSpan, IDietParameter dietParameter) {
		// The dietParameter has to be in a TimeSpan
		return between(timeSpan.getStart(), timeSpan.getDuration(), dietParameter.getStart(), dietParameter.getDuration());
	}

	/**
	 * Calculates if the secound Date is between the first date
	 * 
	 * @param cal1
	 *            the first {@link Calendar} with the start date
	 * @param duration1
	 *            the duration of the first {@link Calendar}
	 * @param cal2
	 *            the secound {@link Calendar} with the start date
	 * @param duration2
	 *            the duration if the secound {@link Calendar}
	 * @return true if the Secound {@link Calendar} is between the first {@link Calendar}
	 */
	public static boolean between(Calendar cal1, int duration1, Calendar cal2, int duration2) {

		if (before(cal1, cal2)) {

			Calendar cal1end = new GregorianCalendar();
			cal1end.setTime(cal1.getTime());
			Calendar cal2end = new GregorianCalendar();
			cal2end.setTime(cal1.getTime());

			cal1end.add(Calendar.DAY_OF_MONTH, duration1);
			cal2end.add(Calendar.DAY_OF_MONTH, duration2);

			// A DietParameter has to be finished before a Treatment ends
			if (before(cal2end, cal1end)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * Caluculates if the first Date is before the secound Date
	 * 
	 * @param cal1
	 *            first {@link Calendar} with the first date
	 * @param cal2
	 *            secound {@link Calendar} with the secound date
	 * @return true if the First {@link Calendar} is first
	 */
	public static boolean before(Calendar cal1, Calendar cal2) {
		return cal1.getTime().getTime() <= cal2.getTime().getTime();
	}

	/**
	 * Checks if the first {@link Calendar} ends before the secound {@link Calendar}. "Cal1 ends before Cal2"
	 * 
	 * @param cal1
	 *            first {@link Calendar}
	 * @param duration1
	 *            of the first {@link Calendar}
	 * @param cal2
	 *            secound {@link Calendar}
	 * @param duration2
	 *            of the secound {@link Calendar}
	 * @return if the first {@link Calendar} ends before the secound {@link Calendar}
	 */
	public static boolean endsBefore(Calendar cal1, int duration1, Calendar cal2, int duration2) {

		if (cal1.getTimeInMillis() == cal2.getTimeInMillis())
			return false;

		Calendar cal1end = new GregorianCalendar();
		cal1end.setTime(cal1.getTime());

		Calendar cal2end = new GregorianCalendar();
		cal2end.setTime(cal2.getTime());

		cal1end.add(Calendar.DAY_OF_MONTH, duration1);
		cal2end.add(Calendar.DAY_OF_MONTH, duration2);

		return before(cal1end, cal2end);
	}

	/**
	 * Checks that the DietTreatment is Possible with the TimeSpan
	 *
	 * @param treament the treament
	 * @param timespan the timespan
	 * @return true, if successful
	 */
	public static boolean possibleTimeSpan(IDietTreatment treament, ITimeSpan timespan) {
		return between(treament.getStart(), treament.getDuration(), timespan.getStart(), timespan.getDuration());
	}

	/**
	 * Checks that the {@link TimeSpan} is Possible with the {@link TimeSpan}
	 *
	 * @param timespan1 the timespan1
	 * @param timespan2 the timespan2
	 * @return true, if successful
	 */
	public static boolean timespanIntersection(ITimeSpan timespan1, ITimeSpan timespan2) {
		long start1 = timespan1.getStart().getTimeInMillis();
		long end1 = timespan1.getEnd().getTimeInMillis();
		long start2 = timespan2.getStart().getTimeInMillis();
		long end2 = timespan2.getEnd().getTimeInMillis();
		
		//special cases
		if (start1 == start2 || end1 == end2) {
			return true;
		}

		return (start1 <= end2 && start2 <= end1);
	}
}
