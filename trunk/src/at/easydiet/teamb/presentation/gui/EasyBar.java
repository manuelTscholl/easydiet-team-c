/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	12.04.2011
 */

package at.easydiet.teamb.presentation.gui;

import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.SuggestionPopup;
import org.apache.pivot.wtk.SuggestionPopupCloseListener;
import org.apache.pivot.wtk.TablePane;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;
import org.apache.pivot.wtk.Mouse.Button;

import at.easydiet.teamb.application.handler.PatientSearchHandler;
import at.easydiet.teamb.application.handler.WindowHandler;
import at.easydiet.teamb.application.handler.exception.ExitNotPermittedException;
import at.easydiet.teamb.application.handler.exception.PatientChangeNotPermittedException;
import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.util.PatientChangedEventArg;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.presentation.component.AutoSuggestListViewItemRenderer;
import at.easydiet.teamb.presentation.component.MessageBar;
import at.easydiet.teamb.presentation.util.MessageType;

/**
 * The Class EasyBar.
 */
public class EasyBar extends TablePane implements Bindable, SuggestionPopupCloseListener, EventListener<PatientChangedEventArg> {

	private static final Logger LOGGER = Logger.getLogger(EasyBar.class);

	// Buttons
	@BXML
	private PushButton _saveButton = null;
	@BXML
	private PushButton _cancelButton = null;
	@BXML
	private PushButton _addButton = null;
	@BXML
	private PushButton _removeButton = null;
	@BXML
	private PushButton _printButton = null;
	@BXML
	private MessageBar _messageBar = null;

	// Inputs
	@BXML
	private TextInput _searchPatientInput = null;

	// Labels
	@BXML
	private Label _selectedPatientLabel = null;

	// Helper for AutoSuggest
	private static final int MAX_POPUP_ENTRIES = 10;
	private final SuggestionPopup _suggestionPopup;

	private PatientSearchHandler _patientSearchHandler;

	private WindowHandler _windowHandler;

	private static EasyBar _easyBar = null;

	/**
	 * Instantiates a new easy bar.
	 */
	// TODO: change to singleton - take care of bxml serializer...
	public EasyBar() {
		_windowHandler = WindowHandler.getWindowHandler();
		_windowHandler.addPatientListener(this);

		_suggestionPopup = new SuggestionPopup();

		_patientSearchHandler = new PatientSearchHandler();

		_easyBar = this;
	}

	/**
	 * Gets the current instance.
	 * 
	 * @return the current instance
	 */
	public static EasyBar getCurrentInstance() {
		if (_easyBar == null) {
			_easyBar = new EasyBar();
		}

		return _easyBar;
	}

	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {

		// apply our own custom ListView.ItemRenderer
		_suggestionPopup.setSuggestionRenderer(new AutoSuggestListViewItemRenderer());

		initAutoSuggestInput();
		
		_messageBar.setVisible(false);
	}
	
	public void setMessage(String message, MessageType type) {
		_messageBar.setMessage(message, type);
	}
	
	public void showMessageBar() {
		_messageBar.start();
	}
	
	public MessageBar getMessageBar() {
		return _messageBar;
	}

	/**
	 * Initialize auto suggest for searchPatientInput
	 * 
	 * @param namespace
	 */
	private void initAutoSuggestInput() {
		// AutoSuggest
		_searchPatientInput.getTextInputContentListeners().add(new TextInputContentListener.Adapter() {
			@Override
			public void textInserted(TextInput textInput, int index, int count) {
				updateSuggestion(textInput);
			}

			@Override
			public void textRemoved(TextInput textInput, int index, int count) {
				updateSuggestion(textInput);
			}
		});

		_suggestionPopup.setListSize(MAX_POPUP_ENTRIES);
	}

	protected void updateSuggestion(TextInput textInput) {
		LOGGER.debug("Updateing Patient suggestions");

		// Search for patients
		List<PatientViewable> patientsList = _patientSearchHandler.searchPatients(textInput.getText(), (MAX_POPUP_ENTRIES * 2));

		// Update suggestion pop up
		if (patientsList.size() > 0) {

			// TODO: sort patient List

			// create new Pivot-ArrayList
			ArrayList<PatientViewable> suggestions = new ArrayList<PatientViewable>();
			for (PatientViewable patient : patientsList) {
				suggestions.add(patient);
			}

			// open pop up
			_suggestionPopup.setSuggestionData(suggestions);
			_suggestionPopup.open(textInput, this);
		} else {
			_suggestionPopup.close();

			// TODO: if there are no search hits show a notification
		}
	}

	/**
	 * Updates the name of the Patient and the Diagnose
	 */
	private void updatePatientHeader() {
		PatientViewable patient = _windowHandler.getSelectedPatient();

		if (patient != null) {
			if (patient.getForename() == null && patient.getLastname() == null) {
				_selectedPatientLabel.setText("neuen Patienten anlegen");
			} else {
				_selectedPatientLabel.setText(patient.getLastname() + " " + patient.getForename());
			}
		} else {
			_selectedPatientLabel.setText("");
		}
	}

	@Override
	public void suggestionPopupClosed(final SuggestionPopup suggestionpopup) {
		// open patient details when a suggestion is selected
		if (suggestionpopup.getResult()) {
			PatientViewable patient;

			try {
				patient = (PatientViewable) suggestionpopup.getSelectedSuggestion();
			} catch (ClassCastException e) {
				LOGGER.error("Expected Patient object but found " + suggestionpopup.getSelectedSuggestion(), e);
				throw e;
			}

			try {
				WindowHandler.getWindowHandler().changePatient(patient);
			} catch (PatientChangeNotPermittedException ex) {
				YesNoAbortSheet yesNoAbortSheet = new YesNoAbortSheet(EasyBar.getCurrentInstance(), _windowHandler.getExitOptions(), new YesNoAbortSheet.Redo() {
					
					@Override
					public void redo() throws ExitNotPermittedException {
						suggestionPopupClosed(suggestionpopup);
					}
				});
				yesNoAbortSheet.open(getWindow());
				// TODO: handle error of changing patient (prompt, alert, ...)
			}

			_searchPatientInput.setText("");
		}
	}

	@Override
	public void fired(Object sender, PatientChangedEventArg eventObject) {
		updatePatientHeader();
	}

	/**
	 * Gets the search text input.
	 *
	 * @return the search text input
	 */
	public TextInput getSearchTextInput() {
		return _searchPatientInput;
	}

	/**
	 * Gets the adds the button.
	 *
	 * @return the adds the button
	 */
	public PushButton getAddButton() {
		return _addButton;
	}

	/**
	 * Gets the removes the button.
	 *
	 * @return the removes the button
	 */
	public PushButton getRemoveButton() {
		return _removeButton;
	}

	/**
	 * Gets the prints the button.
	 *
	 * @return the prints the button
	 */
	public PushButton getPrintButton() {
		return _printButton;

	}

	/**
	 * Gets the save button.
	 *
	 * @return the save button
	 */
	public PushButton getSaveButton() {
		return _saveButton;
	}

	/**
	 * Gets the cancel button.
	 *
	 * @return the cancel button
	 */
	public PushButton getCancelButton() {
		return _cancelButton;
	}
}
