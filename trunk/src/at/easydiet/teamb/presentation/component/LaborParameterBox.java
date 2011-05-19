/**********************************
 * EasyDiet
 * --------
 * 
 * TeamB:
 *  Martin Balter, Bernhard Breuß, Lukas Ender and Stefan Mayer
 * 
 * Created: 21.04.2011
 */

package at.easydiet.teamb.presentation.component;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.util.concurrent.TaskExecutionException;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Component;
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

import at.easydiet.teamb.application.handler.LaborParameterHandler;
import at.easydiet.teamb.application.handler.LaborReportHandler;
import at.easydiet.teamb.application.handler.ParameterDefinitionSearchHandler.Excluder;
import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.util.ValidatorArgs.LaborParameterErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.application.viewobject.LaborParameterViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.domain.util.CheckOperatorEnum;
import at.easydiet.teamb.presentation.gui.NewLaborParameterSheet;
import at.easydiet.teamb.presentation.gui.tab.AbstractTab;
import at.easydiet.teamb.presentation.util.Message;
import at.easydiet.teamb.presentation.util.MessageType;
import at.easydiet.teamb.presentation.util.Renderer;

/**
 * The Class ParameterBox.
 */
public class LaborParameterBox extends BoxPane {
    private static final Logger LOGGER = Logger.getLogger(LaborParameterBox.class);

    private AbstractTab _errorBoxTab;

    private BoxPane _container;
    private Label _noParameter;
    private LaborReportHandler _handler;
    private boolean _readOnly;

    public LaborParameterBox(AbstractTab errorBoxTab, LaborReportHandler reportHandler) {
        this(errorBoxTab, reportHandler, false);
    }

    /**
     * Instantiates a new parameter box.
     * 
     * @param errorBoxTab
     *            the error box tab
     * @param handler
     *            the handler
     * @param readOnly
     *            the read only
     */
    public LaborParameterBox(AbstractTab errorBoxTab, LaborReportHandler handler, boolean readOnly) {
        _errorBoxTab = errorBoxTab;

        _handler = handler;
        _readOnly = readOnly;

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
            addButton = new LinkButton(new ButtonData(Image.load(LaborParameterBox.class.getResource("/gfx/icon/16x16px/add.png")), "neuen Parameter anlegen"));
        } catch (TaskExecutionException ex) {
            LOGGER.warn("Unable to load add image", ex);

            addButton = new LinkButton(new ButtonData("neuen Parameter anlegen"));
        }
        addButton.getButtonPressListeners().add(new ButtonPressListener() {
            @Override
            public void buttonPressed(Button button) {
                new NewLaborParameterSheet(LaborParameterBox.this).open(getWindow());
            }
        });

        if (!_readOnly) {
            add(addButton);
        }

        addParameters(_handler.getLaborReportParameters());

    }

    /**
     * Sets the handler.
     * 
     * @param handler
     *            the new handler
     */
    public void setHandler(LaborReportHandler handler) {
        _handler = handler;
    }

    /**
     * Adds the parameters.
     * 
     * @param parameterHandlers
     *            the parameter handlers
     */
    public void addParameters(LaborParameterHandler[] parameterHandlers) {
        for (LaborParameterHandler parameterHandler : parameterHandlers) {
            addParameter(parameterHandler);
        }
    }

    /**
     * Adds the parameter.
     * 
     * @param parameterHandler
     *            the parameter handler
     */
    public void addParameter(LaborParameterHandler parameterHandler) {
        _handler.addParameter(parameterHandler);
        _container.add(new LaborParameterLine(parameterHandler));
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

    private void removeParameter(LaborParameterLine parameterLine) {
        _handler.removeParameter(parameterLine.getParameterHandler());
        _container.remove(parameterLine);

        if (_container.getLength() == 0) {
            _noParameter.setVisible(true);
        }
    }

    public void remove() {
        for (Component c : _container) {
            if (c instanceof LaborParameterLine) {
                ((LaborParameterLine) c).remove();
            }
        }
    }

    /**
     * The Class ParameterLine.
     */
    private class LaborParameterLine extends BoxPane implements EventListener<ValidatorArgs<LaborParameterErrorField>> {

        private LaborParameterViewable _laborParameter;
        private LaborParameterHandler _parameterHandler;

        private Label _definition;
        private ListButton _checkOperator;
        private TextInput _value;
        private ListButton _unit;
        private LinkButton _remove;

        private HashMap<LaborParameterErrorField, Message> _lastErrors;

        /**
         * Instantiates a new parameter line.
         * 
         * @param parameterHandler
         *            the parameter handler
         */
        public LaborParameterLine(LaborParameterHandler parameterHandler) {
            super(Orientation.HORIZONTAL);

            _lastErrors = new HashMap<LaborParameterErrorField, Message>();

            _parameterHandler = parameterHandler;
            _laborParameter = parameterHandler.getLaborParameter();

            _definition = new Label();
            _checkOperator = new ListButton();
            _value = new NullableTextInput();
            _unit = new ListButton();
            try {
                _remove = new LinkButton(new ButtonData(Image.load(LaborParameterBox.class.getResource("/gfx/icon/16x16px/remove.png"))));
            } catch (TaskExecutionException ex) {
                LOGGER.warn("Unable to load remove image", ex);

                _remove = new LinkButton(new ButtonData("löschen"));
            }

            // fill in static values
            _definition.setText(_laborParameter.getParameterDefinition().getName());

            List<CheckOperatorEnum> checkOperatorsList = new ArrayList<CheckOperatorEnum>(CheckOperatorEnum.values(), 0, CheckOperatorEnum.values().length);
            _checkOperator.setListData(checkOperatorsList);
            _checkOperator.setSelectedItem(_laborParameter.getCheckOperator());
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
            _value.setText(_laborParameter.getValue());
            _value.getTextInputContentListeners().add(new TextInputContentListener.Adapter() {
                @Override
                public void textChanged(TextInput textInput) {
                    _parameterHandler.setValue(textInput.getText());
                }
            });

            // fill in possible units (because definition can't be changed, this is static)
            ParameterDefinitionUnitViewable[] units = _laborParameter.getParameterDefinition().getUnits();
            _unit.setListData(new ArrayList<ParameterDefinitionUnitViewable>(units, 0, units.length));
            _unit.setSelectedItem(_laborParameter.getParameterDefinitionUnit());
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

            // recognize remove button click
            _remove.getButtonPressListeners().add(new ButtonPressListener() {

                @Override
                public void buttonPressed(Button button) {
                    _parameterHandler.removeValidadedListener(LaborParameterLine.this);

                    removeParameter(LaborParameterLine.this);
                }
            });

            // register to parameter events
            _parameterHandler.addValidadedListener(this);

            // styles
            getStyles().put("verticalAlignment", "center");

            add(_definition);
            add(_checkOperator);
            add(_value);
            add(_unit);
            if (!_readOnly) {
                add(_remove);
            }

            if (_readOnly) {
                for (Component c : this) {
                    c.setEnabled(false);
                }
            }
        }

        public void remove() {
            _parameterHandler.removeValidadedListener(this);
            for (Message message : _lastErrors.values()) {
                _errorBoxTab.removeMessage(message);
            }
            
            _lastErrors = null;
        }

        public LaborParameterHandler getParameterHandler() {
            return _parameterHandler;
        }

        @Override
        public void fired(Object sender, ValidatorArgs<LaborParameterErrorField> eventObject) {
            LOGGER.debug("parameterbox errors: " + eventObject.getErrorFields());

            HashMap<LaborParameterErrorField, Message> newErrors = new HashMap<LaborParameterErrorField, Message>();

            for (LaborParameterErrorField laborParameterErrorField : eventObject.getErrorFields()) {
                Component component = null;
                StringBuilder message = new StringBuilder("Das Feld ");
                switch (laborParameterErrorField) {
                    case CHECKOPERATOR:
                        component = _checkOperator;
                        message.append("Vergleichsoperator");
                        break;

                    case DEFINITION:
                        component = _definition;
                        message.append("Name");
                        break;

                    case UNIT:
                        component = _unit;
                        message.append("Einheit");
                        break;

                    case VALUE:
                        component = _value;
                        message.append("Wert");
                        break;
                        
                    case DUPLICATE:
                        component = this;
                        break;

                    default:
                        LOGGER.error("unknown patient error field");
                        message.append(laborParameterErrorField);
                        break;
                }

                if (component != null) {
                    if (_lastErrors.containsKey(laborParameterErrorField)) {
                        newErrors.put(laborParameterErrorField, _lastErrors.get(laborParameterErrorField));
                        _lastErrors.remove(laborParameterErrorField);
                    } else {
                        if (component != this) {
                        message.append(" hat einen ungültigen Wert");
                        } else {
                            message = new StringBuilder("Der Parameter ");
                            message.append(_laborParameter.getParameterDefinition().getName());
                            message.append(" ist doppelt enthalten");
                        }
                        Message m = new Message(MessageType.Error, component, message.toString());
                        _errorBoxTab.putMessage(m);
                        newErrors.put(laborParameterErrorField, m);
                    }
                }
            }

            // remove old errors
            for (Message message : _lastErrors.values()) {
                _errorBoxTab.removeMessage(message);
            }

            _lastErrors = newErrors;
        }
    }

    public Excluder getLaborParameterExcluder() {
        return _handler.getLaborParameterExcluder();
    }
}
