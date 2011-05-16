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
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.CalendarDate;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.Button.State;
import org.apache.pivot.wtk.ButtonStateListener;
import org.apache.pivot.wtk.CalendarButton;
import org.apache.pivot.wtk.CalendarButtonSelectionListener;
import org.apache.pivot.wtk.Checkbox;
import org.apache.pivot.wtk.FocusTraversalDirection;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListButtonSelectionListener;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.SuggestionPopup;
import org.apache.pivot.wtk.SuggestionPopupCloseListener;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;
import org.apache.pivot.wtk.content.ListButtonDataRenderer;
import org.apache.pivot.wtk.content.ListViewItemRenderer;
import org.apache.pivot.wtk.validation.IntRangeValidator;

import at.easydiet.teamb.application.handler.ParameterDefinitionSearchHandler;
import at.easydiet.teamb.application.handler.ParameterHandler;
import at.easydiet.teamb.application.viewobject.DietParameterTypeViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionViewable;
import at.easydiet.teamb.domain.util.CheckOperatorEnum;
import at.easydiet.teamb.presentation.util.Renderer;

/**
 * The Class NewParameterContentBorder.
 */
public class NewParameterContentBorder extends Border implements Bindable, SuggestionPopupCloseListener {
	private static Logger LOGGER = Logger.getLogger(NewParameterContentBorder.class);

	private static final int MAX_POPUP_ENTRIES = 10;

	@BXML
	private ListButton _typ;
	@BXML
	private TextInput _definition;
	@BXML
	private ListButton _checkOperator;
	@BXML
	private TextInput _value;
	@BXML
	private ListButton _unit;
	@BXML
	private Checkbox _noDuration;
	@BXML
	private CalendarButton _startDate;
	@BXML
	private TextInput _duration;

	private final SuggestionPopup _suggestionPopup;

	private ParameterDefinitionSearchHandler _parameterDefinitionSearchHandler;

	private ParameterDefinitionViewable _selectedParameterDefinition;
	private ParameterHandler _handler;

	/**
	 * Instantiates a new new parameter content border.
	 */
	public NewParameterContentBorder() {
		_suggestionPopup = new SuggestionPopup();
		_suggestionPopup.setListSize(MAX_POPUP_ENTRIES);

		_parameterDefinitionSearchHandler = new ParameterDefinitionSearchHandler();
		_handler = new ParameterHandler();
	}

	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		// TODO: startdate should be the last selected date

		DietParameterTypeViewable[] types = _handler.getParameterTypes();
		_typ.setListData(new ArrayList<DietParameterTypeViewable>(types, 0, types.length));
		_typ.getListButtonSelectionListeners().add(new ListButtonSelectionListener.Adapter() {
			@Override
			public void selectedItemChanged(ListButton listButton, Object previousSelectedItem) {
				Object item = listButton.getSelectedItem();
				if (item instanceof DietParameterTypeViewable) {
					_handler.setDietParameterType((DietParameterTypeViewable) item);
				} else {
					LOGGER.warn("expected datatype DietParameterTypeViewable, found " + item.getClass());
				}
			}
		});
		_typ.setDataRenderer(new ListButtonDataRenderer() {
			@Override
			public void render(Object data, Button button, boolean highlight) {
				if (data instanceof DietParameterTypeViewable) {
					DietParameterTypeViewable dietParameterTypeViewable = (DietParameterTypeViewable) data;
					super.render(dietParameterTypeViewable.getName(), button, highlight);
				} else {
					super.render(data, button, highlight);
				}
			}
		});
		_typ.setItemRenderer(new ListViewItemRenderer() {
			@Override
			public void render(Object item, int index, ListView listView, boolean selected, boolean checked, boolean highlighted, boolean disabled) {
				if (item instanceof DietParameterTypeViewable) {
					DietParameterTypeViewable dietParameterTypeViewable = (DietParameterTypeViewable) item;
					super.render(dietParameterTypeViewable.getName(), index, listView, selected, checked, highlighted, disabled);
				} else {
					super.render(item, index, listView, selected, checked, highlighted, disabled);
				}
			}
		});

		_definition.getTextInputContentListeners().add(new TextInputContentListener.Adapter() {
			@Override
			public void textInserted(TextInput textInput, int index, int count) {
				updateSelectedParameterDefinition(null);
				updateSuggestion(textInput);
			}

			@Override
			public void textRemoved(TextInput textInput, int index, int count) {
				updateSelectedParameterDefinition(null);
				updateSuggestion(textInput);
			}
		});

		_suggestionPopup.setSuggestionRenderer(new ListViewItemRenderer() {
			@Override
			public void render(Object item, int index, ListView listView, boolean selected, boolean checked, boolean highlighted, boolean disabled) {
				if (item instanceof ParameterDefinitionViewable) {
					ParameterDefinitionViewable parameterDefinition = (ParameterDefinitionViewable) item;
					super.render(parameterDefinition.getName(), index, listView, selected, checked, highlighted, disabled);
				} else {
					super.render(item, index, listView, selected, checked, highlighted, disabled);
				}
			}

			@Override
			public String toString(Object item) {
				if (item instanceof ParameterDefinitionViewable) {
					ParameterDefinitionViewable parameterDefinition = (ParameterDefinitionViewable) item;
					return super.toString(parameterDefinition.getName());
				} else {
					return super.toString(item);
				}
			}
		});

		CheckOperatorEnum[] checkOperators = CheckOperatorEnum.values();
		_checkOperator.setListData(new ArrayList<CheckOperatorEnum>(checkOperators, 0, checkOperators.length));
		_checkOperator.setDataRenderer(new Renderer.ParameterListButtonDataRenderer());
		_checkOperator.setItemRenderer(new Renderer.ParameterListViewItemRenderer());
		_checkOperator.getListButtonSelectionListeners().add(new ListButtonSelectionListener.Adapter() {
			@Override
			public void selectedItemChanged(ListButton listButton, Object previousSelectedItem) {
				Object item = listButton.getSelectedItem();
				if (item instanceof CheckOperatorEnum) {
					_handler.setCheckOperator((CheckOperatorEnum) item);
				} else {
					LOGGER.warn("expected datatype CheckOperatorEnum, found " + item.getClass());
				}
			}
		});

		_value.getTextInputContentListeners().add(new TextInputContentListener.Adapter() {
			@Override
			public void textChanged(TextInput textInput) {
				_handler.setValue(textInput.getText());
			}
		});

		_unit.setDataRenderer(new Renderer.UnitListButtonDataRenderer());
		_unit.setItemRenderer(new Renderer.UnitListViewItemRenderer());
		_unit.getListButtonSelectionListeners().add(new ListButtonSelectionListener.Adapter() {
			@Override
			public void selectedItemChanged(ListButton listButton, Object previousSelectedItem) {
				Object item = listButton.getSelectedItem();
				if (item != null) {
					if (item instanceof ParameterDefinitionUnitViewable) {
						_handler.setParameterDefinitionUnit((ParameterDefinitionUnitViewable) item);
					} else {
						LOGGER.warn("expected datatype ParameterDefinitionUnitViewable, found " + item.getClass());
					}
				}
			}
		});

		_noDuration.getButtonStateListeners().add(new ButtonStateListener() {

			@Override
			public void stateChanged(Button button, State arg1) {
				_startDate.setEnabled(!button.isSelected());
				_duration.setEnabled(!button.isSelected());

				if (!button.isSelected()) {
					_handler.setStart(_startDate.getSelectedDate().toCalendar());
					updateDuration();
				} else {
					_handler.setStart(null);
					_handler.setDuration(0);
				}
			}
		});
		if (!_noDuration.isSelected()) {
			_startDate.setSelectedDate(new CalendarDate());
		}

		_startDate.getCalendarButtonSelectionListeners().add(new CalendarButtonSelectionListener() {
			@Override
			public void selectedDateChanged(CalendarButton calendarButton, CalendarDate previousSelectedDate) {
				_handler.setStart(calendarButton.getSelectedDate().toCalendar());
			}
		});

		_duration.setValidator(new IntRangeValidator(0, Integer.MAX_VALUE));
		_duration.setText("1");
		_duration.getTextInputContentListeners().add(new TextInputContentListener.Adapter() {
			@Override
			public void textChanged(TextInput textInput) {
				updateDuration();
			}
		});
	}

	protected void updateSuggestion(TextInput textInput) {
		List<ParameterDefinitionViewable> definitionList = _parameterDefinitionSearchHandler.searchParameterDefinitions(textInput.getText(),
				(MAX_POPUP_ENTRIES * 2));

		if (definitionList.size() > 0) {
			// create new Pivot-ArrayList
			ArrayList<ParameterDefinitionViewable> suggestions = new ArrayList<ParameterDefinitionViewable>(definitionList.size());
			for (ParameterDefinitionViewable parameterDefinitionViewable : definitionList) {
				suggestions.add(parameterDefinitionViewable);
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

			if (selected instanceof ParameterDefinitionViewable) {
				updateSelectedParameterDefinition((ParameterDefinitionViewable) selected);
				_definition.transferFocus(FocusTraversalDirection.FORWARD);
			} else {
				updateSelectedParameterDefinition(null);
			}
		}
	}

	private void updateSelectedParameterDefinition(ParameterDefinitionViewable parameterDefinitionViewable) {
		_selectedParameterDefinition = parameterDefinitionViewable;
		_handler.setParameterDefinition(parameterDefinitionViewable);

		if (_selectedParameterDefinition == null) {
			// reset listbutton
			_unit.setSelectedIndex(-1);
			_unit.setListData(new ArrayList<Object>());
		} else {
			ParameterDefinitionUnitViewable[] definitions = _selectedParameterDefinition.getUnits();
			_unit.setListData(new ArrayList<ParameterDefinitionUnitViewable>(definitions, 0, definitions.length));
		}

	}

	/**
	 * Update duration.
	 */
	private void updateDuration() {
		int duration;
		try {
			duration = Integer.parseInt(_duration.getText());
		} catch (NumberFormatException e) {
			LOGGER.debug(e);
			duration = -1;
		}
		_handler.setDuration(duration);
	}

	/**
	 * Gets the parameter handler.
	 *
	 * @return the parameter handler
	 */
	public ParameterHandler getParameterHandler() {
		return _handler;
	}
}
