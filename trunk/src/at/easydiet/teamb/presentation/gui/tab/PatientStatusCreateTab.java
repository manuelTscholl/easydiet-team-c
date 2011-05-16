/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	13.04.2011
 */

package at.easydiet.teamb.presentation.gui.tab;

import java.net.URL;
import java.util.LinkedList;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.ComponentMouseButtonListener;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListButtonSelectionListener;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.ListViewItemStateListener;
import org.apache.pivot.wtk.ListViewSelectionListener;
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.TextArea;
import org.apache.pivot.wtk.TextAreaContentListener;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;
import org.apache.pivot.wtk.content.ListButtonDataRenderer;
import org.apache.pivot.wtk.content.ListViewItemRenderer;
import org.apache.pivot.wtk.media.Image;

import at.easydiet.teamb.application.handler.AbstractUseCaseHandler;
import at.easydiet.teamb.application.handler.LaborReportHandler;
import at.easydiet.teamb.application.handler.PatientStateHandler;
import at.easydiet.teamb.application.handler.UseCasePatientStateHandler;
import at.easydiet.teamb.application.handler.WindowHandler;
import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.handler.exception.OperationNotPermittedException;
import at.easydiet.teamb.application.util.BMIUtil.BMIEvaluationEnum;
import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.util.ValidatorArgs.PatientStateErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.application.viewobject.DietTreatmentViewable;
import at.easydiet.teamb.application.viewobject.LaborReportViewable;
import at.easydiet.teamb.application.viewobject.PatientStateTypeViewable;
import at.easydiet.teamb.application.viewobject.PatientStateViewable;
import at.easydiet.teamb.presentation.component.DateTimeInput;
import at.easydiet.teamb.presentation.component.DateTimeInputSelectionListener;
import at.easydiet.teamb.presentation.component.LaborParameterBox;
import at.easydiet.teamb.presentation.component.RatingBoxPane;
import at.easydiet.teamb.presentation.exception.NoPatientSelectedException;
import at.easydiet.teamb.presentation.gui.AbstractLazyTab;
import at.easydiet.teamb.presentation.util.Message;
import at.easydiet.teamb.presentation.util.MessageType;
import at.easydiet.teamb.util.DateUtil;
import at.easydiet.teamb.util.StringUtil;

/**
 * @author TeamB
 */
public class PatientStatusCreateTab extends AbstractLazyTab implements Bindable, EventListener<ValidatorArgs<PatientStateErrorField>> {

	private static final Logger LOGGER = Logger.getLogger(PatientStatusCreateTab.class);

	@BXML
	private ListButton _patientStateTypeListButton = null;
	@BXML
	private ListView _selectDietTreatmentsList = null;
	@BXML
	private ListView _selectLaborReports = null;
	@BXML
	private BoxPane _dateTimeContainerBoxPane = null;
	@BXML
	private TextInput _weightTextInput = null;
	@BXML
	private TextInput _heightTextInput = null;
	@BXML
	private TextArea _noticeTextArea = null;
	@BXML
	private Label _bmi = null;
	@BXML
	private Label _bmiEvaluationLabel = null;

	@BXML
	private Label _motivationLabel = null;
	@BXML
	private Label _complianceLabel = null;

	@BXML
	private BoxPane _motivation = null;
	@BXML
	private BoxPane _compliance = null;

	@BXML
	private ScrollPane _laborParameterScrollPane = null;

	private RatingBoxPane _motivationRating = null;
	private RatingBoxPane _complianceRating = null;
	private static final int MAX_RATING_ELEMENTS = 5;

	private UseCasePatientStateHandler _useCasePatientStateHandler;
	private PatientStateHandler _patientStateHandler;

	private DateTimeInput _dateTimeBoxPane = null;

	private boolean _updating;

	@BXML
	private TextArea _anamnesisTextArea = null;

	private LinkedList<Component> _lastErrors;

	private PatientStateViewable _patientState;

	/**
	 * Instantiates a new treatment patient status tab.
	 */
	public PatientStatusCreateTab() {
		_lastErrors = new LinkedList<Component>();
	}

	public void setPatientState(PatientStateViewable state) {
		_useCasePatientStateHandler = new UseCasePatientStateHandler(state);
		_patientStateHandler = _useCasePatientStateHandler.getPatientStateHandler();
		_patientState = _patientStateHandler.getPatientState();
	}

	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {

		Image icon = null;
		Image iconHover = null;
		Image iconHighlighted = null;

		try {
			icon = Image.load(DietPlanTab.class.getResource("/gfx/icon/24x24px/favorite_inactive.png"));
			iconHover = Image.load(DietPlanTab.class.getResource("/gfx/icon/24x24px/favorite_hover.png"));
			iconHighlighted = Image.load(DietPlanTab.class.getResource("/gfx/icon/24x24px/favorite.png"));

			_motivationRating = new RatingBoxPane(MAX_RATING_ELEMENTS, icon, iconHover, iconHighlighted);
			_complianceRating = new RatingBoxPane(MAX_RATING_ELEMENTS, icon, iconHover, iconHighlighted);
		} catch (TaskExecutionException ex) {
			LOGGER.warn("Can not load button data icon", ex);
		}

		_selectDietTreatmentsList.getListViewItemStateListeners().add(new ListViewItemStateListener() {
			@Override
			public void itemCheckedChanged(ListView listView, int selectedIndex) {
				if (!_updating) {
					List<?> items = listView.getListData();
					Object item = items.get(selectedIndex);
					if (item instanceof DietTreatmentViewable) {
						if (listView.isItemChecked(selectedIndex)) {
							_patientStateHandler.addDietTreatment((DietTreatmentViewable) item);
						} else {
							_patientStateHandler.removeDietTreatment((DietTreatmentViewable) item);
						}
					} else {
						LOGGER.warn("expected datatype DietTreatmentViewable, found " + item.getClass());
					}
				}
			}
		});

		_selectDietTreatmentsList.setItemRenderer(new ListViewItemRenderer() {
			@Override
			public void render(Object item, int index, ListView listView, boolean selected, boolean checked, boolean highlighted, boolean disabled) {
				if (item instanceof DietTreatmentViewable) {
					DietTreatmentViewable treatment = (DietTreatmentViewable) item;
					super.render(treatment.getName(), index, listView, selected, checked, highlighted, disabled);
				} else {
					super.render(item, index, listView, selected, checked, highlighted, disabled);
				}
			}
		});

		_selectLaborReports.getListViewItemStateListeners().add(new ListViewItemStateListener() {
			@Override
			public void itemCheckedChanged(ListView listView, int selectedIndex) {
				if (!_updating) {
					List<?> items = listView.getListData();
					Object item = items.get(selectedIndex);

					if (item instanceof LaborReportViewable) {
						if (listView.isItemChecked(selectedIndex)) {
							_patientStateHandler.addLaborReport((LaborReportViewable) item);
						} else {
							_patientStateHandler.removeLaborReport((LaborReportViewable) item);
						}
					} else {
						LOGGER.warn("expected datatype LaborReportViewable, found " + item.getClass());
					}
				}
			}
		});

		_selectLaborReports.setItemRenderer(new ListViewItemRenderer() {
			@Override
			public void render(Object item, int index, ListView listView, boolean selected, boolean checked, boolean highlighted, boolean disabled) {
				if (item instanceof LaborReportViewable) {
					LaborReportViewable laborreport = (LaborReportViewable) item;
					super.render(DateUtil.CalendarToString(laborreport.getDate(), "dd.MM.yy"), index, listView, selected, checked, highlighted, disabled);
				} else {
					super.render(item, index, listView, selected, checked, highlighted, disabled);
				}
			}
		});

		_selectLaborReports.getListViewSelectionListeners().add(new ListViewSelectionListener.Adapter() {

			@Override
			public void selectedItemChanged(ListView listView, Object previewObject) {
				if (!_updating) {
					Object item = listView.getSelectedItem();
					if (item instanceof LaborReportViewable) {
						LaborReportViewable report = (LaborReportViewable) item;
						_noticeTextArea.setText(report.getNotice());
						LaborParameterBox box = new LaborParameterBox(null, new LaborReportHandler(report), true);
						_laborParameterScrollPane.setView(box);
					} else {
						LOGGER.warn("expected datatype LaborReportViewable, found " + item.getClass());
					}
				}
			}
		});

		_motivationRating.getComponentMouseButtonListeners().add(new ComponentMouseButtonListener.Adapter() {

			@Override
			public boolean mouseClick(Component component, org.apache.pivot.wtk.Mouse.Button button, int x, int y, int count) {
				if (!_updating) {
					_motivationLabel.setText("" + _motivationRating.getSelectedIndex());
					_patientStateHandler.setMotivation(_motivationRating.getSelectedIndex());
				}
				return false;
			}
		});

		_complianceRating.getComponentMouseButtonListeners().add(new ComponentMouseButtonListener.Adapter() {

			@Override
			public boolean mouseClick(Component component, org.apache.pivot.wtk.Mouse.Button button, int x, int y, int count) {
				if (!_updating) {
					_complianceLabel.setText("" + _complianceRating.getSelectedIndex());
					_patientStateHandler.setCompliance(_complianceRating.getSelectedIndex());
				}
				return false;
			}
		});

		_anamnesisTextArea.getTextAreaContentListeners().add(new TextAreaContentListener.Adapter() {

			@Override
			public void textChanged(TextArea textArea) {
				if (!_updating) {
					_patientStateHandler.setAnamnesis(textArea.getText());
				}
			}
		});

		_patientStateTypeListButton.setDataRenderer(new ListButtonDataRenderer() {
			@Override
			public void render(Object data, Button button, boolean highlight) {
				if (data instanceof PatientStateTypeViewable) {
					PatientStateTypeViewable stateType = (PatientStateTypeViewable) data;
					super.render(stateType.getName(), button, highlight);
				} else {
					super.render(data, button, highlight);
				}
			}
		});
		_patientStateTypeListButton.setItemRenderer(new ListViewItemRenderer() {
			@Override
			public void render(Object item, int index, ListView listView, boolean selected, boolean checked, boolean highlighted, boolean disabled) {
				if (item instanceof PatientStateTypeViewable) {
					PatientStateTypeViewable stateType = (PatientStateTypeViewable) item;
					super.render(stateType.getName(), index, listView, selected, checked, highlighted, disabled);
				} else {
					super.render(item, index, listView, selected, checked, highlighted, disabled);
				}
			}
		});

		_patientStateTypeListButton.getListButtonSelectionListeners().add(new ListButtonSelectionListener.Adapter() {
			@Override
			public void selectedItemChanged(ListButton listButton, Object previousSelectedItem) {
				if (!_updating) {
					Object item = listButton.getSelectedItem();
					if (item instanceof PatientStateTypeViewable) {
						_patientStateHandler.setType((PatientStateTypeViewable) item);
					} else {
						LOGGER.warn("expected datatype PatientStateTypeViewable, found " + item.getClass());
					}
				}
			}
		});

		_weightTextInput.getTextInputContentListeners().add(new InputListener() {
			@Override
			public void textEdited(TextInput textInput) {
				if (!_updating) {
					try {
						_patientStateHandler.setWeight(Integer.parseInt(textInput.getText()));
						updateCalculation();
					} catch (NumberFormatException e) {

					}
				}
			}
		});

		_heightTextInput.getTextInputContentListeners().add(new InputListener() {
			@Override
			public void textEdited(TextInput textInput) {
				if (!_updating) {
					try {
						_patientStateHandler.setHeight(Integer.parseInt(textInput.getText()));
						updateCalculation();
					} catch (NumberFormatException e) {

					}
				}
			}
		});

		_dateTimeBoxPane = new DateTimeInput();
		_dateTimeBoxPane.getDateTimeInputSelectionListeners().add(new DateTimeInputSelectionListener() {

			@Override
			public void dateTimeChanged(DateTimeInput dateTimeInput) {
				if (!_updating) {
					_patientStateHandler.setDate(dateTimeInput.getDate());
				}
			}
		});
		_dateTimeContainerBoxPane.add(_dateTimeBoxPane);
		
		_motivation.insert(_motivationRating, 0);
		_compliance.insert(_complianceRating, 0);
	}

	private void updateCalculation() {
		if (!StringUtil.isEmpty(_heightTextInput.getText()) && !StringUtil.isEmpty(_weightTextInput.getText())) {
			int weight = Integer.parseInt(_heightTextInput.getText());
			int height = Integer.parseInt(_weightTextInput.getText());
			double bmi = _patientStateHandler.getBMI(weight, height);
			_bmi.setText("BMI = " + Double.toString(bmi));
			BMIEvaluationEnum ev = _patientStateHandler.getBMIEvaluation(_patientStateHandler.getPatientState().getPatient().getGender(), _patientStateHandler
					.getPatientState().getPatient().getBirthday(), bmi);
			_bmiEvaluationLabel.setText(ev.getText() + " (ausgewertet mit " + ev.getEvaluatedWith() + ")");
		}
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

	@Override
	public AbstractUseCaseHandler getHandler() {
		return _useCasePatientStateHandler;
	}

	@Override
	public void create() throws OperationNotPermittedException {
		throw new OperationNotPermittedException();
	}

	@Override
	public void save() throws DatabaseException, ErrorInFormException, OperationNotPermittedException {
		_useCasePatientStateHandler.save();
		showInfoBar("Erfolgreich gespeichert!", MessageType.Info);
		getLazyTab().display(_windowHandler);
	}

	@Override
	public void discard() throws OperationNotPermittedException {
		if (_useCasePatientStateHandler != null) {
			_useCasePatientStateHandler.discard();
		} else {
			throw new OperationNotPermittedException();
		}
	}

	@Override
	public void display(WindowHandler windowHandler) throws NoPatientSelectedException {
		super.display(windowHandler);

		if (_windowHandler.getSelectedPatient() == null) {
			throw new NoPatientSelectedException();
		}

		if (_useCasePatientStateHandler == null) {
			_useCasePatientStateHandler = new UseCasePatientStateHandler(_windowHandler.getCreator(), _windowHandler.getSelectedPatient());
		}

		_patientStateHandler = _useCasePatientStateHandler.getPatientStateHandler();
		_patientState = _patientStateHandler.getPatientState();

		_patientStateHandler.addValidadedListener(this);

		update();
	}

	@Override
	public void remove() {
		super.remove();

		if (_useCasePatientStateHandler != null) {
			_patientStateHandler.removeValidadedListener(this);
			_patientStateHandler = null;
			_useCasePatientStateHandler = null;
		}
	}

	/**
	 * Update.
	 */
	public void update() {
		_updating = true;
		PatientStateTypeViewable[] types = _patientStateHandler.getStateTypes();
		_patientStateTypeListButton.setListData(new ArrayList<PatientStateTypeViewable>(types, 0, types.length));

		DietTreatmentViewable[] diettreatments = _patientState.getPatient().getTreatments();
		_selectDietTreatmentsList.setListData(new ArrayList<DietTreatmentViewable>(diettreatments, 0, diettreatments.length));

		LaborReportViewable[] laborreports = _patientState.getPatient().getLaborReports();
		_selectLaborReports.setListData(new ArrayList<LaborReportViewable>(laborreports, 0, laborreports.length));

		_patientStateTypeListButton.setSelectedItem(_patientState.getType());

		ArrayList<Integer> indexes = new ArrayList<Integer>();
		DietTreatmentViewable[] treatmentsChecked = _patientState.getDietTreatments();

		for (int i = 0; i < treatmentsChecked.length; i++) {
			for (int j = 0; j < diettreatments.length; j++) {
				if (treatmentsChecked[i].equals(diettreatments[j])) {
					indexes.add(j);
				}
			}
		}
		for (int i : indexes) {
			_selectDietTreatmentsList.setItemChecked(i, true);
		}

		indexes.clear();
		LaborReportViewable[] laborreportsChecked = _patientState.getLaborReports();
		for (int i = 0; i < laborreportsChecked.length; i++) {
			for (int j = 0; j < laborreports.length; j++) {
				if (laborreportsChecked[i].equals(laborreports[j])) {
					indexes.add(j);
				}
			}
		}
		for (int i : indexes) {
			_selectLaborReports.setItemChecked(i, true);
		}

		_dateTimeBoxPane.setDate(_patientState.getDate());

		_weightTextInput.setText(Integer.toString(_patientState.getWeight()));
		_heightTextInput.setText(Integer.toString(_patientState.getHeight()));
		_motivationRating.setSelectedIndex(_patientState.getMotivation());
		_complianceRating.setSelectedIndex(_patientState.getCompliance());

		_anamnesisTextArea.setText(_patientState.getAnamnesis());
		_updating = false;
	}

	@Override
	public void fired(Object sender, ValidatorArgs<PatientStateErrorField> eventObject) {
		LinkedList<Component> newErrors = new LinkedList<Component>();
		for (PatientStateErrorField patientStateErrorField : eventObject.getErrorFields()) {
			Component component = null;
			StringBuilder message = new StringBuilder("Das Feld ");
			switch (patientStateErrorField) {
				case DATETIME:
					component = _dateTimeBoxPane;
					message.append("Datum oder Uhrzeit");
					break;
				case TYPE:
					component = _patientStateTypeListButton;
					message.append("Patientstatustyp");
					break;
				default:
					LOGGER.error("unknown labor report error field");
					message.append(patientStateErrorField);
					break;
			}

			if (component != null) {
				_lastErrors.remove(component);
				newErrors.add(component);
				if(component == _dateTimeBoxPane){
					message.append(" ist nicht gültig");
				} else {
					message.append(" darf nicht leer sein");
				}
				putMessage(new Message(MessageType.Error, component, message.toString()));
			}
		}

		// remove old errors for
		for (Component component : _lastErrors) {
			removeMessages(component);
		}

		_lastErrors = newErrors;
	}
}