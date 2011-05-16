/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	23.04.2011
 */

package at.easydiet.teamb.presentation.component.sheet;

import java.net.URL;

import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.CalendarDate;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.Calendar;

/**
 * The Class NewTimeSpanContentBorder.
 */
public class NewTimeSpanContentBorder extends Border implements Bindable {

	@BXML private Calendar _timeSpanStartCalendar = null;
	@BXML private Calendar _timeSpanEndCalendar = null;
	
	
	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		// define selection on start up to current date.
		_timeSpanStartCalendar.setSelectedDate(new CalendarDate());
		_timeSpanEndCalendar.setSelectedDate(new CalendarDate());
	}

	/**
	 * Gets the start calendar.
	 *
	 * @return the start calendar
	 */
	public Calendar getStartCalendar() {
		return _timeSpanStartCalendar;
	}
	
	/**
	 * Gets the end calendar.
	 *
	 * @return the end calendar
	 */
	public Calendar getEndCalendar() {
		return _timeSpanEndCalendar;
	}
}
