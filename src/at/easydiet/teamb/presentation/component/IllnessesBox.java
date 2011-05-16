/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created:	07.05.2011
 */

package at.easydiet.teamb.presentation.component;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.FocusTraversalDirection;
import org.apache.pivot.wtk.LinkButton;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.SuggestionPopup;
import org.apache.pivot.wtk.SuggestionPopupCloseListener;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;
import org.apache.pivot.wtk.content.ButtonData;
import org.apache.pivot.wtk.content.ListViewItemRenderer;
import org.apache.pivot.wtk.media.Image;

import at.easydiet.teamb.application.handler.IllnessHandler;
import at.easydiet.teamb.application.handler.IllnessSearchHandler;
import at.easydiet.teamb.application.handler.PatientDataHandler;
import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.util.ValidatorArgs.IllnessErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.application.viewobject.IllnessViewable;
import at.easydiet.teamb.presentation.gui.tab.AbstractTab;
import at.easydiet.teamb.presentation.util.Message;
import at.easydiet.teamb.presentation.util.MessageType;

/**
 * The Class IllnessesBox.
 */
public class IllnessesBox extends BoxPane {
	private static final Logger LOGGER = Logger.getLogger(IllnessesBox.class);

	private static final int MAX_POPUP_ENTRIES = 10;

	private AbstractTab _errorBoxTab;

	private PatientDataHandler _patientDataHandler;

	private BoxPane _container;

	private IllnessSearchHandler _illnessSearchHandler;

	private SuggestionPopup _suggestionPopup;

	/**
	 * Instantiates a new illnesses box.
	 *
	 * @param errorBoxTab the error box tab
	 * @param patientDataHandler the patient data handler
	 */
	public IllnessesBox(AbstractTab errorBoxTab, PatientDataHandler patientDataHandler) {
		super(Orientation.VERTICAL);

		_errorBoxTab = errorBoxTab;

		_patientDataHandler = patientDataHandler;

		_container = new BoxPane(Orientation.VERTICAL);

		_illnessSearchHandler = new IllnessSearchHandler();

		LinkButton addIllness;
		try {
			addIllness = new LinkButton(new ButtonData(Image.load(DietParameterBox.class.getResource("/gfx/icon/16x16px/add.png")),
					"Zusätzliche Krankheit angeben"));
		} catch (TaskExecutionException ex) {
			LOGGER.warn("Unable to load add image", ex);

			addIllness = new LinkButton(new ButtonData("Zusätzliche Krankheit angeben"));
		}
		addIllness.getButtonPressListeners().add(new ButtonPressListener() {
			public void buttonPressed(Button button) {
				addIllness(_patientDataHandler.addIllness());
			}
		});

		addIllnesses(_patientDataHandler.getIllnesses());

		_suggestionPopup = new SuggestionPopup();
		_suggestionPopup.setListSize(MAX_POPUP_ENTRIES);
		_suggestionPopup.setSuggestionRenderer(new ListViewItemRenderer() {
			@Override
			public void render(Object item, int index, ListView listView, boolean selected, boolean checked, boolean highlighted, boolean disabled) {
				if (item instanceof IllnessViewable) {
					IllnessViewable illness = (IllnessViewable) item;
					super.render(illness.getName(), index, listView, selected, checked, highlighted, disabled);
				} else {
					super.render(item, index, listView, selected, checked, highlighted, disabled);
				}
			}

			@Override
			public String toString(Object item) {
				if (item instanceof IllnessViewable) {
					IllnessViewable illness = (IllnessViewable) item;
					return super.toString(illness.getName());
				} else {
					return super.toString(item);
				}
			}
		});

		add(_container);
		add(addIllness);
	}

	/**
	 * Adds the illnesses.
	 * 
	 * @param illnesses
	 *            the illnesses
	 */
	public void addIllnesses(IllnessHandler[] illnesses) {
		for (IllnessHandler illnessHandler : illnesses) {
			addIllness(illnessHandler);
		}
	}

	/**
	 * Adds the illness.
	 * 
	 * @param illness
	 *            the illness
	 */
	public void addIllness(IllnessHandler illness) {
		_container.add(new IllnessLine(illness));
	}

	/**
	 * Removes the illness line.
	 * 
	 * @param illnessLine
	 *            the illness line
	 */
	private void removeIllnessLine(IllnessLine illnessLine) {
		_patientDataHandler.removeIllness(illnessLine.getIllnessHandler());
		_container.remove(illnessLine);
	}

	/**
	 * The Class IllnessLine.
	 */
	public class IllnessLine extends BoxPane implements SuggestionPopupCloseListener, EventListener<ValidatorArgs<IllnessErrorField>> {
		private IllnessHandler _illnessHandler;

		private TextInput _illnessTextInput;

		/**
		 * Instantiates a new illness line.
		 * 
		 * @param illnessHandler
		 *            the illness handler
		 */
		public IllnessLine(IllnessHandler illnessHandler) {
			_illnessHandler = illnessHandler;

			_illnessTextInput = new TextInput();

			_illnessHandler.addValidadedListener(this);

			_illnessTextInput.setText((_illnessHandler.getIllness() != null) ? _illnessHandler.getIllness().getName() : "");
			_illnessTextInput.getTextInputContentListeners().add(new TextInputContentListener.Adapter() {
				@Override
				public void textInserted(TextInput textInput, int index, int count) {
					updateSelectedIllness(null);
					updateSuggestion(textInput);
				}

				@Override
				public void textRemoved(TextInput textInput, int index, int count) {
					updateSelectedIllness(null);
					updateSuggestion(textInput);
				}
			});

			LinkButton remove;
			try {
				remove = new LinkButton(new ButtonData(Image.load(IllnessesBox.class.getResource("/gfx/icon/16x16px/remove.png"))));
			} catch (TaskExecutionException ex) {
				LOGGER.warn("Unable to load remove image", ex);

				remove = new LinkButton(new ButtonData("löschen"));
			}

			remove.getButtonPressListeners().add(new ButtonPressListener() {

				public void buttonPressed(Button button) {
					removeIllnessLine(IllnessLine.this);
				}
			});

			add(_illnessTextInput);
			add(remove);
		}

		/**
		 * Gets the illnessHandler.
		 * 
		 * @return the illnessHandle
		 */
		public IllnessHandler getIllnessHandler() {
			return _illnessHandler;
		}

		private void updateSuggestion(TextInput textInput) {
			List<IllnessViewable> illnessList = _illnessSearchHandler.searchIllnesses(textInput.getText(), (MAX_POPUP_ENTRIES * 2));

			if (illnessList.size() > 0) {
				// create new Pivot-ArrayList
				ArrayList<IllnessViewable> suggestions = new ArrayList<IllnessViewable>(illnessList.size());
				for (IllnessViewable illnessViewable : illnessList) {
					suggestions.add(illnessViewable);
				}

				// open pop up
				_suggestionPopup.setSuggestionData(suggestions);
				_suggestionPopup.open(textInput, this);
			} else {
				_suggestionPopup.close();

				// TODO: if there are no search hits show a notification
			}
		}

		@Override
		public void suggestionPopupClosed(SuggestionPopup suggestionpopup) {
			if (suggestionpopup.getResult()) {
				Object selected = suggestionpopup.getSelectedSuggestion();

				if (selected instanceof IllnessViewable) {
					updateSelectedIllness((IllnessViewable) selected);
					_illnessTextInput.transferFocus(FocusTraversalDirection.FORWARD);
				} else {
					updateSelectedIllness(null);
				}
			}
		}

		private void updateSelectedIllness(IllnessViewable illness) {
			_illnessHandler.setIllness(illness);
		}

		@Override
		public void fired(Object sender, ValidatorArgs<IllnessErrorField> eventObject) {
			if (eventObject.getErrorFields().isEmpty()) {
				_errorBoxTab.removeMessages(_illnessTextInput);
			} else {
				_errorBoxTab.putMessage(new Message(MessageType.Error, _illnessTextInput, "Keine Krankheit ausgewählt"));
			}
		}
	}
}
