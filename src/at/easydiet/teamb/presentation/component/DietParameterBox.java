/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 * 	Martin Balter, Bernhard Breuß, Lukas Ender, Stefan Mayer and Hubert Rall
 * 
 * Created:	21.04.2011
 */

package at.easydiet.teamb.presentation.component;

import org.apache.log4j.Logger;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.util.CalendarDate;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.Button.State;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.ButtonStateListener;
import org.apache.pivot.wtk.CalendarButton;
import org.apache.pivot.wtk.CalendarButtonSelectionListener;
import org.apache.pivot.wtk.Checkbox;
import org.apache.pivot.wtk.Label;
import org.apache.pivot.wtk.LinkButton;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListButtonSelectionListener;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.ScrollPane;
import org.apache.pivot.wtk.ScrollPane.ScrollBarPolicy;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;
import org.apache.pivot.wtk.content.ButtonData;
import org.apache.pivot.wtk.media.Image;
import org.apache.pivot.wtk.validation.IntRangeValidator;

import at.easydiet.teamb.application.handler.ParameterHandler;
import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.util.ValidatorArgs.ParameterErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.application.viewobject.DietParameterViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.domain.util.CheckOperatorEnum;
import at.easydiet.teamb.presentation.util.DietParameterChangedEventArg;
import at.easydiet.teamb.presentation.util.Parameterizable;
import at.easydiet.teamb.presentation.util.Renderer;

/**
 * The Class ParameterBox.
 */
public class DietParameterBox extends BoxPane {
	private static final Logger LOGGER = Logger.getLogger(DietParameterBox.class);

	private BoxPane _container;
	private Label _noParameter;

	private Parameterizable _parameterizable;

	/**
	 * Instantiates a new parameter box.
	 * 
	 * @param parameterizable
	 *            the parameterizable
	 */
	public DietParameterBox(Parameterizable parameterizable) {
		_parameterizable = parameterizable;

		setOrientation(Orientation.VERTICAL);
		getStyles().put("fill", true);
		getStyles().put("spacing", 15);

		_container = new BoxPane(Orientation.VERTICAL);
		_container.getStyles().put("fill", true);

		ScrollPane scrollPane = new ScrollPane(ScrollBarPolicy.FILL_TO_CAPACITY, ScrollBarPolicy.FILL);
		scrollPane.setView(_container);
		add(scrollPane);

		_noParameter = new Label("Es sind keine Parameter vorhanden");
		add(_noParameter);

		LinkButton addButton;
		try {
			addButton = new LinkButton(new ButtonData(Image.load(DietParameterBox.class.getResource("/gfx/icon/16x16px/add.png")), "neuen Parameter anlegen"));
		} catch (TaskExecutionException ex) {
			LOGGER.warn("Unable to load add image", ex);

			addButton = new LinkButton(new ButtonData("neuen Parameter anlegen"));
		}
		add(addButton);
	}

	/**
	 * Instantiates a new parameter box.
	 *
	 * @param parameterizable the parameterizable
	 * @param parameterHandlers the parameter handlers
	 */
	public DietParameterBox(Parameterizable parameterizable, ParameterHandler[] parameterHandlers) {
		this(parameterizable);

		addParameters(parameterHandlers);
	}

	/**
	 * Adds the parameters.
	 *
	 * @param parameterHandlers the parameter handlers
	 */
	public void addParameters(ParameterHandler[] parameterHandlers) {
		for (ParameterHandler parameterHandler : parameterHandlers) {
			addParameter(parameterHandler);
		}
	}

	/**
	 * Adds the parameter.
	 *
	 * @param parameterHandler the parameter handler
	 */
	public void addParameter(ParameterHandler parameterHandler) {
		_container.add(new ParameterLine(parameterHandler));
		_noParameter.setVisible(false);
	}

	/**
	 * Sets the text of the no parameter label.
	 * 
	 * @param text
	 *            the new no parameter text
	 */
	public void setNoParameterText(String text) {
		_noParameter.setText(text);
	}

	/**
	 * Gets the no parameter label.
	 * 
	 * @return the no parameter label
	 */
	public Label getNoParameterLabel() {
		return _noParameter;
	}

	private void removeParameter(ParameterLine parameterLine) {
		_container.remove(parameterLine);

		if (_container.getLength() == 0) {
			_noParameter.setVisible(true);
		}
	}

	/**
	 * The Class ParameterLine.
	 */
	private class ParameterLine extends BoxPane implements EventListener<ValidatorArgs<ParameterErrorField>> {
		// TODO: gui-group possible

		private static final String DEFAULT_DURATION = "1";

		private DietParameterViewable _dietParameter;
		private ParameterHandler _parameterHandler;

		private Label _typ;
		private Label _definition;
		private ListButton _checkOperator;
		private TextInput _value;
		private ListButton _unit;
		private CalendarButton _startDate;
		private TextInput _duration;
		private Checkbox _noDuration;
		private LinkButton _remove;

		private PossibleChangeListener _possibleChangeListener;

		/**
		 * Instantiates a new parameter line.
		 *
		 * @param parameterHandler the parameter handler
		 */
		public ParameterLine(ParameterHandler parameterHandler) {
			super(Orientation.HORIZONTAL);

			_parameterHandler = parameterHandler;
			_dietParameter = parameterHandler.getDietParameter();

			_typ = new Label();
			_definition = new Label();
			_checkOperator = new ListButton();
			_value = new TextInput();
			_unit = new ListButton();
			_noDuration = new Checkbox(new ButtonData("gesamter Zeitraum"));
			_startDate = new CalendarButton();
			_duration = new TextInput();
			try {
				_remove = new LinkButton(new ButtonData(Image.load(DietParameterBox.class.getResource("/gfx/icon/16x16px/remove.png"))));
			} catch (TaskExecutionException ex) {
				LOGGER.warn("Unable to load remove image", ex);

				_remove = new LinkButton(new ButtonData("löschen"));
			}

			// fill in static values
			_typ.setText(_dietParameter.getDietParameterType().getName());
			_definition.setText(_dietParameter.getParameterDefinition().getName());

			List<CheckOperatorEnum> checkOperatorsList = new ArrayList<CheckOperatorEnum>(CheckOperatorEnum.values(), 0, CheckOperatorEnum.values().length);
			_checkOperator.setListData(checkOperatorsList);
			_checkOperator.setSelectedItem(_dietParameter.getCheckOperator());
			_checkOperator.setDataRenderer(new Renderer.ParameterListButtonDataRenderer());
			_checkOperator.setItemRenderer(new Renderer.ParameterListViewItemRenderer());
			_checkOperator.getListButtonSelectionListeners().add(new ListButtonSelectionListener.Adapter() {
				@Override
				public void selectedItemChanged(ListButton listButton, Object previousSelectedItem) {
					Object item = listButton.getSelectedItem();
					if (item instanceof CheckOperatorEnum) {
						_parameterHandler.setCheckOperator((CheckOperatorEnum) item);
					} else {
						LOGGER.warn("expected datatype CheckOperatorEnum, found " + item.getClass());
					}
				}
			});

			_value.setTextSize(4);
			_value.setText(_dietParameter.getValue());
			_value.getTextInputContentListeners().add(new TextInputContentListener.Adapter() {
				@Override
				public void textChanged(TextInput textInput) {
					_parameterHandler.setValue(textInput.getText());
				}
			});

			// fill in possible units (because definition can't be changed, this is static)
			ParameterDefinitionUnitViewable[] units = _dietParameter.getParameterDefinition().getUnits();
			_unit.setListData(new ArrayList<ParameterDefinitionUnitViewable>(units, 0, units.length));
			_unit.setSelectedItem(_dietParameter.getParameterDefinitionUnit());
			_unit.setDataRenderer(new Renderer.UnitListButtonDataRenderer());
			_unit.setItemRenderer(new Renderer.UnitListViewItemRenderer());
			_unit.getListButtonSelectionListeners().add(new ListButtonSelectionListener.Adapter() {
				@Override
				public void selectedItemChanged(ListButton listButton, Object previousSelectedItem) {
					Object item = listButton.getSelectedItem();
					if (item != null) {
						if (item instanceof ParameterDefinitionUnitViewable) {
							_parameterHandler.setParameterDefinitionUnit((ParameterDefinitionUnitViewable) item);
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
						_parameterHandler.setStart(_startDate.getSelectedDate().toCalendar());
						updateDuration();
					} else {
						_parameterHandler.setStart(null);
						_parameterHandler.setDuration(0);
					}
				}
			});
			if (_dietParameter.getStart() != null) {
				_startDate.setSelectedDate(new CalendarDate(_dietParameter.getStart()));
			} else {
				_noDuration.setSelected(true);
			}

			if (_dietParameter.getStart() != null) {
				_startDate.setSelectedDate(new CalendarDate(_dietParameter.getStart()));
			}
			_startDate.getCalendarButtonSelectionListeners().add(new CalendarButtonSelectionListener() {
				@Override
				public void selectedDateChanged(CalendarButton calendarButton, CalendarDate previousSelectedDate) {
					_parameterHandler.setStart(calendarButton.getSelectedDate().toCalendar());
				}
			});

			// ensure only positive integer values in duration (days)
			_duration.setValidator(new IntRangeValidator(0, Integer.MAX_VALUE));
			_duration.setText(DEFAULT_DURATION);
			_duration.setTextSize(3);
			_duration.setText(Integer.toString(_dietParameter.getDuration()));
			_duration.getTextInputContentListeners().add(new TextInputContentListener.Adapter() {
				@Override
				public void textChanged(TextInput textInput) {
					updateDuration();
				}
			});

			// recognize remove button click
			_remove.getButtonPressListeners().add(new ButtonPressListener() {

				@Override
				public void buttonPressed(Button button) {
					_parameterizable.removeParameter(_parameterHandler);
					_parameterHandler.removeValidadedListener(ParameterLine.this);
					_dietParameter.removePossibleChangeListener(_possibleChangeListener);
					removeParameter(ParameterLine.this);
				}
			});

			// register to parameter events
			_parameterHandler.addValidadedListener(this);
			_dietParameter.addPossibleChangeListener((_possibleChangeListener = new PossibleChangeListener()));

			// styles
			getStyles().put("verticalAlignment", "center");

			add(_typ);
			add(_definition);
			add(_checkOperator);
			add(_value);
			add(_unit);
			add(_noDuration);
			add(_startDate);
			add(_duration);
			add(_remove);
		}

		@Override
		public void fired(Object sender, ValidatorArgs<ParameterErrorField> eventObject) {
			LOGGER.debug("parameterbox errors: " + eventObject.getErrorFields());

			if (eventObject.getErrorFields().isEmpty()) {
				getStyles().put("backgroundColor", "#ffffff");
			} else {
				getStyles().put("backgroundColor", "#fef7b2");
			}
		}

		private void updateDuration() {
			int duration;
			try {
				duration = Integer.parseInt(_duration.getText());
			} catch (NumberFormatException e) {
				LOGGER.debug(e);
				duration = -1;
			}
			_parameterHandler.setDuration(duration);
		}

		private class PossibleChangeListener implements EventListener<DietParameterChangedEventArg> {

			@Override
			public void fired(Object sender, DietParameterChangedEventArg eventObject) {
				if (_dietParameter.isPossible()) {
					getStyles().put("backgroundColor", "#ffffff");
				} else {
					getStyles().put("backgroundColor", "#ffa800");
				}
			}
		}
	}
}
