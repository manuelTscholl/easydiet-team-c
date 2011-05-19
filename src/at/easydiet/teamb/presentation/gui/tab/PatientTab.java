/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 *  Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created: 13.04.2011
 */

package at.easydiet.teamb.presentation.gui.tab;

import java.net.URL;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.CalendarDate;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.CalendarButton;
import org.apache.pivot.wtk.CalendarButtonSelectionListener;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListButtonSelectionListener;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.PushButton;
import org.apache.pivot.wtk.TextArea;
import org.apache.pivot.wtk.TextAreaContentListener;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;
import org.apache.pivot.wtk.content.ListButtonDataRenderer;
import org.apache.pivot.wtk.content.ListViewItemRenderer;

import at.easydiet.teamb.application.handler.AbstractUseCaseHandler;
import at.easydiet.teamb.application.handler.UseCaseManager;
import at.easydiet.teamb.application.handler.UseCasePatientHandler;
import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.handler.exception.ExitNotPermittedException;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;
import at.easydiet.teamb.application.handler.exception.PatientChangeNotPermittedException;
import at.easydiet.teamb.application.util.DuplicatPatientEventArg;
import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.util.ValidatorArgs.PatientDataErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.domain.util.FamilyStatusEnum;
import at.easydiet.teamb.domain.util.GenderEnum;
import at.easydiet.teamb.presentation.component.IllnessesBox;
import at.easydiet.teamb.presentation.exception.NoPatientSelectedException;
import at.easydiet.teamb.presentation.gui.AbstractLazyTab;
import at.easydiet.teamb.presentation.util.Message;
import at.easydiet.teamb.presentation.util.MessageType;
import at.easydiet.teamb.view.PatientLikeManagementView;

/**
 * @author TeamB
 */
public class PatientTab extends AbstractLazyTab implements Bindable,
		EventListener<ValidatorArgs<PatientDataErrorField>> {
	private static final Logger LOGGER = Logger.getLogger(PatientTab.class);

	@BXML
	private ListButton _genderListButton = null;
	@BXML
	private TextInput _titleTextInput = null;
	@BXML
	private TextInput _lastNameTextInput = null;
	@BXML
	private TextInput _foreNameTextInput = null;
	@BXML
	private TextInput _jobTextInput = null;
	@BXML
	private CalendarButton _birthdayCalendarButton = null;
	@BXML
	private TextInput _insuranceNumberTextInput = null;
	@BXML
	private TextInput _religionTextInput = null;
	@BXML
	private TextArea _regimeTextArea = null;
	@BXML
	private TextArea _noticeTextArea = null;
	@BXML
	private TextInput _streetTextInput = null;
	@BXML
	private TextInput _zipTextInput = null;
	@BXML
	private TextInput _placeTextInput = null;
	@BXML
	private TextInput _countryTextInput = null;
	@BXML
	private ListButton _familyStateListButton = null;
	@BXML
	private Border _illnessesBorder = null;
	@BXML
	private PatientLikeManagementView _patientLike = null;
	@BXML
	private PushButton _save = null;

	private UseCasePatientHandler _useCasePatientHandler;

	private HashMap<PatientDataErrorField, Message> _lastErrors;

	private boolean _updating;

	private DuplicatePatientListener _duplicatePatientListener;

	/**
	 * Instantiates a new patient tab.
	 */
	public PatientTab() {
		_lastErrors = new HashMap<PatientDataErrorField, Message>();
		_useCaseManager = UseCaseManager.getWindowHandler();
		_useCasePatientHandler = new UseCasePatientHandler();
	}

	@Override
	public void initialize(Map<String, Object> namespace, URL location,
			Resources resources) {
		ArrayList<Title> titles = new ArrayList<Title>();
		for (GenderEnum genderEnum : GenderEnum.values()) {
			String title;

			switch (genderEnum) {
			case FEMALE:
				title = "Frau";
				break;
			case MALE:
				title = "Herr";
				break;
			default:
				title = "";
				break;
			}

			titles.add(Title.getInstance(genderEnum, title));
		}
		_genderListButton.setListData(titles);
		_genderListButton.setDataRenderer(new ListButtonDataRenderer() {
			@Override
			public void render(Object data, Button button, boolean highlight) {
				if (data instanceof Title) {
					Title title = (Title) data;
					super.render(title.getTitle(), button, highlight);
				} else {
					super.render(data, button, highlight);
				}
			}
		});
		_genderListButton.setItemRenderer(new ListViewItemRenderer() {
			@Override
			public void render(Object item, int index, ListView listView,
					boolean selected, boolean checked, boolean highlighted,
					boolean disabled) {
				if (item instanceof Title) {
					Title title = (Title) item;
					super.render(title.getTitle(), index, listView, selected,
							checked, highlighted, disabled);
				} else {
					super.render(item, index, listView, selected, checked,
							highlighted, disabled);
				}
			}
		});
		_genderListButton.getListButtonSelectionListeners().add(
				new ListButtonSelectionListener.Adapter() {
					@Override
					public void selectedItemChanged(ListButton listButton,
							Object previousSelectedItem) {
						if (!_updating) {
							if (listButton.getSelectedItem() instanceof Title) {
								_useCasePatientHandler.getPatientDataHandler()
										.setGender(
												((Title) listButton
														.getSelectedItem())
														.getGenderEnum());
							} else {
								listButton.setSelectedIndex(-1);
								_useCasePatientHandler.getPatientDataHandler()
										.setGender(null);
							}
						}
					}
				});

		_titleTextInput.getTextInputContentListeners().add(new InputListener() {
			@Override
			public void textEdited(TextInput textInput) {
				_useCasePatientHandler.getPatientDataHandler().setTitle(
						textInput.getText());
			}
		});

		_lastNameTextInput.getTextInputContentListeners().add(
				new InputListener() {
					@Override
					public void textEdited(TextInput textInput) {
						_useCasePatientHandler.getPatientDataHandler()
								.setLastname(textInput.getText());
					}
				});

		_foreNameTextInput.getTextInputContentListeners().add(
				new InputListener() {
					@Override
					public void textEdited(TextInput textInput) {
						_useCasePatientHandler.getPatientDataHandler()
								.setForename(textInput.getText());
					}
				});

		_jobTextInput.getTextInputContentListeners().add(new InputListener() {
			@Override
			public void textEdited(TextInput textInput) {
				_useCasePatientHandler.getPatientDataHandler().setJob(
						textInput.getText());
			}
		});

		_birthdayCalendarButton.getCalendarButtonSelectionListeners().add(
				new CalendarButtonSelectionListener() {

					@Override
					public void selectedDateChanged(
							CalendarButton calendarButton,
							CalendarDate previousSelectedDate) {
						if (!_updating) {
							_useCasePatientHandler.getPatientDataHandler()
									.setBirthday(
											calendarButton.getSelectedDate()
													.toCalendar());
						}
					}
				});

		_insuranceNumberTextInput.getTextInputContentListeners().add(
				new InputListener() {
					@Override
					public void textEdited(TextInput textInput) {
						_useCasePatientHandler.getPatientDataHandler()
								.setInsuranceNumber(textInput.getText());
					}
				});

		_familyStateListButton.setListData(new ArrayList<FamilyStatusEnum>(
				FamilyStatusEnum.values()));
		_familyStateListButton.setDataRenderer(new ListButtonDataRenderer() {
			@Override
			public void render(Object data, Button button, boolean highlight) {
				if (data instanceof FamilyStatusEnum) {
					FamilyStatusEnum familyStatusEnum = (FamilyStatusEnum) data;
					super.render(familyStatusEnum.getStatus(), button,
							highlight);
				} else {
					super.render(data, button, highlight);
				}
			}
		});
		_familyStateListButton.setItemRenderer(new ListViewItemRenderer() {
			@Override
			public void render(Object item, int index, ListView listView,
					boolean selected, boolean checked, boolean highlighted,
					boolean disabled) {
				if (item instanceof FamilyStatusEnum) {
					FamilyStatusEnum familyStatusEnum = (FamilyStatusEnum) item;
					super.render(familyStatusEnum.getStatus(), index, listView,
							selected, checked, highlighted, disabled);
				} else {
					super.render(item, index, listView, selected, checked,
							highlighted, disabled);
				}
			}
		});
		_familyStateListButton.getListButtonSelectionListeners().add(
				new ListButtonSelectionListener.Adapter() {
					@Override
					public void selectedItemChanged(ListButton listButton,
							Object previousSelectedItem) {
						if (!_updating) {
							if (listButton.getSelectedItem() instanceof FamilyStatusEnum) {
								_useCasePatientHandler.getPatientDataHandler()
										.setFamilyStatus(
												(FamilyStatusEnum) listButton
														.getSelectedItem());
							} else {
								listButton.setSelectedIndex(-1);
								_useCasePatientHandler.getPatientDataHandler()
										.setFamilyStatus(null);
							}
						}
					}
				});

		_religionTextInput.getTextInputContentListeners().add(
				new InputListener() {
					@Override
					public void textEdited(TextInput textInput) {
						_useCasePatientHandler.getPatientDataHandler()
								.setReligion(textInput.getText());
					}
				});

		_regimeTextArea.getTextAreaContentListeners().add(
				new TextAreaContentListener.Adapter() {
					@Override
					public void textChanged(TextArea textArea) {
						if (!_updating) {
							if (textArea.getCharacterCount() <= 0) {
								_useCasePatientHandler.getPatientDataHandler()
										.setRegime(null);
							} else {
								_useCasePatientHandler.getPatientDataHandler()
										.setRegime(textArea.getText());
							}
						}
					}
				});

		_noticeTextArea.getTextAreaContentListeners().add(
				new TextAreaContentListener.Adapter() {
					@Override
					public void textChanged(TextArea textArea) {
						if (!_updating) {
							if (textArea.getCharacterCount() <= 0) {
								_useCasePatientHandler.getPatientDataHandler()
										.setNotice(null);
							} else {
								_useCasePatientHandler.getPatientDataHandler()
										.setNotice(textArea.getText());
							}
						}
					}
				});

		_streetTextInput.getTextInputContentListeners().add(
				new InputListener() {
					@Override
					public void textEdited(TextInput textInput) {
						_useCasePatientHandler.getPatientDataHandler()
								.setStreet(textInput.getText());
					}
				});

		_zipTextInput.getTextInputContentListeners().add(new InputListener() {
			@Override
			public void textEdited(TextInput textInput) {
				_useCasePatientHandler.getPatientDataHandler().setZip(
						textInput.getText());
			}
		});

		_placeTextInput.getTextInputContentListeners().add(new InputListener() {
			@Override
			public void textEdited(TextInput textInput) {
				_useCasePatientHandler.getPatientDataHandler().setPlace(
						textInput.getText());
			}
		});

		_countryTextInput.getTextInputContentListeners().add(
				new InputListener() {
					@Override
					public void textEdited(TextInput textInput) {
						_useCasePatientHandler.getPatientDataHandler()
								.setCountry(textInput.getText());
					}
				});

		_save.getButtonPressListeners().add(new ButtonPressListener() {

			@Override
			public void buttonPressed(Button arg0) {
				try {
					save();
					getWindow().close();
				} catch (DatabaseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ErrorInFormException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public AbstractUseCaseHandler getHandler() {
		return _useCasePatientHandler;
	}

	@Override
	public void create() throws ExitNotPermittedException {
		UseCasePatientHandler useCasePatientHandler = new UseCasePatientHandler();

		try {
			_useCaseManager.changePatient(useCasePatientHandler
					.getPatientDataHandler().getPatient());
			_useCasePatientHandler = useCasePatientHandler;
		} catch (PatientChangeNotPermittedException ex) {
		}
	}

	@Override
	public void save() throws DatabaseException, ErrorInFormException {
		_useCasePatientHandler.save();
		showInfoBar("Erfolgreich gespeichert!", MessageType.Info);

		try {
			_useCaseManager.changePatient(_useCasePatientHandler
					.getPatientDataHandler().getPatient());
		} catch (PatientChangeNotPermittedException ex) {
			LOGGER.error(
					"uncaught PatientChangeNotPermittedException. After saving, chaning patient should be no problem",
					ex);
			Alert.alert(org.apache.pivot.wtk.MessageType.ERROR,
					"Es ist ein unbekannter Fehler aufgetreten", getWindow());
		}

	}

	@Override
	public void discard() throws OperationNotPermittedException {
		if (_useCasePatientHandler != null) {
			_useCasePatientHandler.discard();

			if (!_useCasePatientHandler.getPatientDataHandler().isInDatabase()) {
				_useCaseManager.changePatient(null);
			}
			showInfoBar("Verworfen!", MessageType.Info);
		} else {
			throw new OperationNotPermittedException();
		}
	}

	@Override
	public void display(UseCaseManager useCaseManager)
			throws NoPatientSelectedException {
		super.display(useCaseManager);

		if (_useCaseManager.getSelectedPatient() == null) {
			throw new NoPatientSelectedException();
		}

		if (_useCasePatientHandler == null) {
			_useCasePatientHandler = new UseCasePatientHandler(
					_useCaseManager.getSelectedPatient());
		}

		_useCasePatientHandler.getPatientDataHandler().addValidadedListener(
				this);
		_useCasePatientHandler
				.getPatientDataHandler()
				.addDuplicatePatientListener(
						(_duplicatePatientListener = new DuplicatePatientListener()));

		updatePatient();

		// including use case of team-a
		// _patientLike.onLoad(_useCasePatientHandler.getPatientDataHandler());
	}

	@Override
	public void remove() {
		super.remove();

		if (_useCasePatientHandler != null) {
			_useCasePatientHandler.getPatientDataHandler()
					.removeValidadedListener(this);
			_useCasePatientHandler.getPatientDataHandler()
					.removeDuplciatePatientListener(_duplicatePatientListener);
			_duplicatePatientListener = null;
			_useCasePatientHandler = null;
		}
	}

	private void updatePatient() {
		_updating = true;

		PatientViewable patient = _useCasePatientHandler
				.getPatientDataHandler().getPatient();

		_genderListButton
				.setSelectedItem(Title.getInstance(patient.getGender()));
		_titleTextInput.setText(patient.getTitle());
		_lastNameTextInput.setText(patient.getLastname());
		_foreNameTextInput.setText(patient.getForename());
		_jobTextInput.setText(patient.getJob());
		GregorianCalendar birthdate = ((patient.getBirthday() == null) ? new GregorianCalendar()
				: patient.getBirthday());
		try {
			_birthdayCalendarButton
					.setSelectedDate(new CalendarDate(birthdate));
		} catch (IndexOutOfBoundsException ex) {
			// TODO: remove this try-catch-block when stefan did his bug fix for
			// pivot
			LOGGER.warn("got pivot bug", ex);
		}
		_insuranceNumberTextInput.setText(patient.getInsuranceNumber());
		_familyStateListButton.setSelectedItem(patient.getFamilyStatus());
		_religionTextInput.setText(patient.getReligion());
		_regimeTextArea.setText(patient.getRegime());

		_illnessesBorder.setContent(new IllnessesBox(this,
				_useCasePatientHandler.getPatientDataHandler()));

		_noticeTextArea.setText(patient.getNotice());
		_streetTextInput.setText(patient.getStreet());
		_zipTextInput.setText(patient.getZip());
		_placeTextInput.setText(patient.getPlace());
		_countryTextInput.setText(patient.getCountry());

		_updating = false;
	}

	@Override
	public void fired(Object sender,
			ValidatorArgs<PatientDataErrorField> eventObject) {
		HashMap<PatientDataErrorField, Message> newErrors = new HashMap<PatientDataErrorField, Message>();

		for (PatientDataErrorField patientDataErrorField : eventObject
				.getErrorFields()) {
			Component component = null;
			StringBuilder message = new StringBuilder("Das Feld ");

			switch (patientDataErrorField) {
			case FORENAME:
				component = _foreNameTextInput;
				message.append("Vorname");
				break;

			case LASTNAME:
				component = _lastNameTextInput;
				message.append("Nachname");
				break;

			case GENDER:
				component = _genderListButton;
				message.append("Geschlechtsspezifische Anrede");
				break;

			default:
				LOGGER.error("unknown patient error field");
				message.append(patientDataErrorField);
				break;
			}

			if (component != null) {
				if (_lastErrors.containsKey(patientDataErrorField)) {
					newErrors.put(patientDataErrorField,
							_lastErrors.get(patientDataErrorField));
					_lastErrors.remove(patientDataErrorField);
				} else {
					message.append(" darf nicht leer sein");
					Message m = new Message(MessageType.Error, component,
							message.toString());
					putMessage(m);
					newErrors.put(patientDataErrorField, m);
				}
			}
		}

		// remove old errors
		for (Message message : _lastErrors.values()) {
			removeMessage(message);
		}

		_lastErrors = newErrors;
	}

	/**
	 * Adds the illness.
	 */
	public void addIllness() {
		_useCasePatientHandler.getPatientDataHandler().addIllness();
	}

	private abstract class InputListener extends
			TextInputContentListener.Adapter {
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

	private static class Title {
		private static final java.util.HashMap<GenderEnum, Title> _map = new java.util.HashMap<GenderEnum, PatientTab.Title>();

		private GenderEnum _genderEnum;
		private String _title;

		private Title(GenderEnum genderEnum, String title) {
			_genderEnum = genderEnum;
			_title = title;
		}

		/**
		 * Gets the gender enum.
		 * 
		 * @return the gender enum
		 */
		public GenderEnum getGenderEnum() {
			return _genderEnum;
		}

		/**
		 * Gets the title.
		 * 
		 * @return the title
		 */
		public String getTitle() {
			return _title;
		}

		private static Title getInstance(GenderEnum genderEnum) {
			if (_map.containsKey(genderEnum)) {
				return _map.get(genderEnum);
			} else {
				return null;
			}
		}

		private static Title getInstance(GenderEnum genderEnum, String title) {
			if (_map.containsKey(genderEnum)) {
				return _map.get(genderEnum);
			} else {
				Title t = new Title(genderEnum, title);
				_map.put(genderEnum, t);
				return t;
			}

		}
	}

	private class DuplicatePatientListener implements
			EventListener<DuplicatPatientEventArg> {
		private Message _message;

		public DuplicatePatientListener() {
			_message = new Message(MessageType.Info,
					"Es gibt bereits einen Patienten, der diesem gleicht.");
		}

		@Override
		public void fired(Object sender, DuplicatPatientEventArg eventObject) {
			if (eventObject.getDuplicates().length == 0) {
				removeMessage(_message);
			} else {
				putMessage(_message);
			}
		}

	}
}
