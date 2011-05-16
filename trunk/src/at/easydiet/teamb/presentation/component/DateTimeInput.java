/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	11.05.2011
 */

package at.easydiet.teamb.presentation.component;

import java.util.GregorianCalendar;

import org.apache.pivot.util.CalendarDate;
import org.apache.pivot.util.ListenerList;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.CalendarButton;
import org.apache.pivot.wtk.CalendarButtonSelectionListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;
import org.apache.pivot.wtk.validation.IntRangeValidator;

/**
 * The Class DateTimeInput.
 */
public class DateTimeInput extends BoxPane {

	protected CalendarButton _date = null;
	protected Label _atLabel = null;
	protected Label _separator = null;
	protected Label _clockLabel = null;
	protected TextInput _hours = null;
	protected TextInput _minutes = null;

	protected boolean _updating;

	protected DateTimeInputSelectionListenerList _listeners;

	/**
	 * Instantiates a new date time box pane.
	 */
	public DateTimeInput() {
		_listeners = new DateTimeInputSelectionListenerList();

		getStyles().put("verticalAlignment", "center");

		_date = new CalendarButton();
		_atLabel = new Label("um");
		_hours = new TextInput();
		_hours.setPrompt("13");
		_hours.setTextSize(2);
		_separator = new Label(":");
		_minutes = new TextInput();
		_minutes.setPrompt("38");
		_minutes.setTextSize(2);
		_clockLabel = new Label("Uhr");

		_date.getCalendarButtonSelectionListeners().add(new CalendarButtonSelectionListener() {


			public void selectedDateChanged(CalendarButton calendarButton, CalendarDate previousDate) {
				callListeners();
			}
		});

		_hours.setValidator(new IntRangeValidator(0, 23));
		_hours.getTextInputContentListeners().add(new InputListener() {

			@Override
			public void textEdited(TextInput textInput) {
				callListeners();
			}
		});

		_minutes.setValidator(new IntRangeValidator(0, 59));
		_minutes.getTextInputContentListeners().add(new InputListener() {

			@Override
			public void textEdited(TextInput textInput) {
				callListeners();
			}
		});

		add(_date);
		add(_atLabel);
		add(_hours);
		add(_separator);
		add(_minutes);
		add(_clockLabel);
		
		callListeners();
	}

	private void callListeners() {
		if (!_updating) {
			_listeners.dateTimeChanged(this);
		}
	}

	/**
	 * Set labels
	 */
	public void setLabelText(String atLabel, String separator, String clockLabel) {
		_atLabel.setText(atLabel);
		_separator.setText(separator);
		_clockLabel.setText(clockLabel);
	}

	/**
	 * Set date
	 * 
	 * @param date
	 *            which is set.
	 */
	public void setDate(GregorianCalendar date) {
		GregorianCalendar oldDate = getDate();
		
		_updating = true;

		if (date != null) {
			_date.setSelectedDate(new CalendarDate(date));
			_hours.setText(Integer.toString(date.get(GregorianCalendar.HOUR_OF_DAY)));
			_minutes.setText(Integer.toString(date.get(GregorianCalendar.MINUTE)));
		} else {
			_date.setSelectedDate(new CalendarDate(new GregorianCalendar()));
			_hours.setText("");
			_minutes.setText("");
		}

		_updating = false;
		
		GregorianCalendar newDate = getDate();
		if (oldDate != newDate && (oldDate == null || !oldDate.equals(newDate))) {
			callListeners();
		}
	}

	public GregorianCalendar getDate() {
		GregorianCalendar date = _date.getSelectedDate().toCalendar();

		if (date != null) {
			try {
				int hour = Integer.parseInt(_hours.getText());
				int minutes = Integer.parseInt(_minutes.getText());
				if(hour < 0 || hour > 23 || minutes < 0 || minutes >59){
					return null;
				}
				date.set(GregorianCalendar.HOUR_OF_DAY, hour);
				date.set(GregorianCalendar.MINUTE, minutes);
			} catch (NumberFormatException e) {
				date = null;
			}
		}

		return date;
	}

	public ListenerList<DateTimeInputSelectionListener> getDateTimeInputSelectionListeners() {
		return _listeners;
	}

	private abstract class InputListener extends TextInputContentListener.Adapter {
		public abstract void textEdited(TextInput textInput);

		@Override
		public void textInserted(TextInput textInput, int index, int count) {
			if (!_updating) {
				textEdited(textInput);
			}
		}

		@Override
		public void textRemoved(TextInput textInput, int index, int count) {
			if (!_updating) {
				textEdited(textInput);
			}
		}
	}

	private static class DateTimeInputSelectionListenerList extends ListenerList<DateTimeInputSelectionListener> implements DateTimeInputSelectionListener {


		public void dateTimeChanged(DateTimeInput dateTimeInput) {
			for (DateTimeInputSelectionListener listener : this) {
				listener.dateTimeChanged(dateTimeInput);
			}
		}

	}
}
