package at.easydiet.teamb.presentation.gui;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.util.CalendarDate;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Calendar;
import org.apache.pivot.wtk.CalendarSelectionListener;
import org.apache.pivot.wtk.Label;

import at.easydiet.teamb.presentation.component.sheet.NewTimeSpanContentBorder;
import at.easydiet.teamb.presentation.gui.tab.DietPlanCreateTab;

/**
 * @author TeamB
 */
public class NewTimeSpanSheet extends AbstractSheet {

	private static Logger LOGGER = Logger.getLogger(NewTimeSpanSheet.class);

	private Calendar _timeSpanStartCalendar = null;
	private Calendar _timeSpanEndCalendar = null;

	private Label _messageLabel = null;

	private final String ERROR_TEXT = "Auswahl nicht korrekt! Bitte überprüfen.";
	private String _choice = "";

	private DietPlanCreateTab _dietPlanCreateTab;

	/**
	 * Lets you create a new time code.
	 *
	 * @param dietPlanCreateTab the diet plan create tab
	 */
	public NewTimeSpanSheet(DietPlanCreateTab dietPlanCreateTab) {
		_dietPlanCreateTab = dietPlanCreateTab;

		// get content for abstract sheet
		NewTimeSpanContentBorder content = null;
		try {
			content = (NewTimeSpanContentBorder) new BXMLSerializer().readObject(NewTimeSpanSheet.class, "easydiet_popup_new_timespan.bxml");
		} catch (IOException ex) {
			LOGGER.fatal("Can not read 'easydiet_popup_new_timespan.bxml'", ex);
		} catch (SerializationException ex) {
			LOGGER.fatal("Can not read 'easydiet_popup_new_timespan.bxml'", ex);
		}

		// set content
		setMainContent(content);
		_timeSpanStartCalendar = content.getStartCalendar();
		_timeSpanEndCalendar = content.getEndCalendar();
		_messageLabel = getMessage();
		initCalendarListener();
		initButtonListener();
	}

	private void initButtonListener() {
		addButtonPressListenerToSaveButton(new ButtonPressListener() {
			
			@Override
			public void buttonPressed(Button button) {
				_dietPlanCreateTab.newTimeSpan(NewTimeSpanSheet.this, _timeSpanStartCalendar.getSelectedDate().toCalendar(), _timeSpanEndCalendar.getSelectedDate().toCalendar());
			}
		});
		
		addButtonPressListenerToDiscardButton(new ButtonPressListener() {
			
			@Override
			public void buttonPressed(Button button) {
				_isAllowedToClose = true;
				close();
			}
		});
	}

	/**
	 * Initialize ClandarListener. Listens to selectedDateChanged and validates the selected time span.
	 */
	private void initCalendarListener() {
		_timeSpanStartCalendar.getCalendarSelectionListeners().add(new CalendarSelectionListener() {
			@Override
			public void selectedDateChanged(Calendar calendar, CalendarDate previousSelectedDate) {
				LOGGER.debug("start date changed");
				updateMessageLabel();
			}
		});

		_timeSpanEndCalendar.getCalendarSelectionListeners().add(new CalendarSelectionListener() {
			@Override
			public void selectedDateChanged(Calendar calendar, CalendarDate previousSelectedDate) {
				LOGGER.debug("end date changed");
				updateMessageLabel();
			}
		});
	}

	private void updateMessageLabel() {
		if (!validateTimeSpan(_timeSpanStartCalendar.getSelectedDate(), _timeSpanEndCalendar.getSelectedDate())) {
			// set error text
			_messageLabel.setText(ERROR_TEXT);
			_messageLabel.getStyles().put("color", "#ff0000");
		} else {
			DateFormat dateFormat = DateFormat.getDateInstance(SimpleDateFormat.SHORT);

			_choice = "Auswahl von " + dateFormat.format(_timeSpanStartCalendar.getSelectedDate().toCalendar().getTime()) + " bis "
					+ dateFormat.format(_timeSpanEndCalendar.getSelectedDate().toCalendar().getTime()) + ".";
			_messageLabel.setText(_choice);
			_messageLabel.getStyles().put("color", "#000000");
		}
	}

	// nifty little helpers

	/**
	 * Compares the given dates. Returns false, if startDate is behind endDate.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return false, if startDate is behind endDate.
	 */
	private boolean validateTimeSpan(CalendarDate startDate, CalendarDate endDate) {
		return (startDate.compareTo(endDate) <= 0);
	}
}