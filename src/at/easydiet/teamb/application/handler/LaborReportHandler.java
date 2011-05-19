package at.easydiet.teamb.application.handler;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import at.easydiet.dao.DAOFactory;
import at.easydiet.model.LaborReport;
import at.easydiet.teamb.application.handler.ParameterDefinitionSearchHandler.Excluder;
import at.easydiet.teamb.application.handler.exception.DatabaseException;
import at.easydiet.teamb.application.handler.exception.ErrorInFormException;
import at.easydiet.teamb.application.util.Event;
import at.easydiet.teamb.application.util.EventArgs;
import at.easydiet.teamb.application.util.EventListener;
import at.easydiet.teamb.application.util.ValidatorArgs.LaborReportErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.application.viewobject.LaborParameterViewable;
import at.easydiet.teamb.application.viewobject.LaborReportTypeViewable;
import at.easydiet.teamb.application.viewobject.LaborReportViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionViewable;
import at.easydiet.teamb.application.viewobject.PatientViewable;
import at.easydiet.teamb.application.viewobject.SystemUserViewable;
import at.easydiet.teamb.domain.ILaborParameter;
import at.easydiet.teamb.domain.ILaborReport;
import at.easydiet.teamb.domain.IParameterDefinition;
import at.easydiet.teamb.domain.object.LaborReportDO;
import at.easydiet.teamb.util.StringUtil;

/**
 * A DietPlanHandler
 * 
 * @author TeamB
 */
public class LaborReportHandler extends AbstractHandler<LaborReportErrorField> implements Excluder {
    private static Logger LOGGER = Logger.getLogger(LaborReportHandler.class);

    private ILaborReport _laborreport;

    private boolean _unsaved;

    private List<LaborParameterHandler> _laborParameterHandlers;

    private Event<EventArgs> _laborParameterChanged;

    /**
     * Instantiates a new labor report handler.
     * 
     * @param creator
     *            the creator of the {@link LaborReport}
     * @param patient
     *            the patient which the {@link LaborReport} references on
     */
    public LaborReportHandler(SystemUserViewable creator, PatientViewable patient) {
        this(new LaborReportDO(new LaborReport()));
        _laborreport.setCreator(creator);
        _laborreport.setPatient(patient);
    }

    /**
     * Instantiates a new labor report handler.
     * 
     * @param laborReport
     *            the labor report
     */
    public LaborReportHandler(LaborReportViewable laborReport) {
        if (laborReport == null) {
            LOGGER.warn("LaborReport is null");
            throw new NullPointerException("LaborReport is null");
        }

        _laborParameterChanged = new Event<EventArgs>(this);

        _laborreport = (ILaborReport) laborReport;

        _validaded = new Event<ValidatorArgs<LaborReportErrorField>>(this);
        _errorFields = new ArrayList<LaborReportErrorField>();

        LaborParameterViewable[] parameters = _laborreport.getLaborParameters();
        _laborParameterHandlers = new ArrayList<LaborParameterHandler>();
        for (LaborParameterViewable laborParameterViewable : parameters) {
            LaborParameterHandler laborParameterHandler = new LaborParameterHandler(laborParameterViewable);
            _handlers.add(laborParameterHandler);
            _laborParameterHandlers.add(laborParameterHandler);
        }

        validate();

        _unsaved = false;
    }

    /**
     * Sets the date.
     * 
     * @param date
     *            the new date
     */
    public void setDate(GregorianCalendar date) {
        _unsaved = true;
        _laborreport.setDate(date);
        validate();
    }

    /**
     * Sets the type.
     * 
     * @param type
     *            the new type
     */
    public void setLaborReportType(LaborReportTypeViewable type) {
        if (!type.equals(_laborreport)) {
            _unsaved = true;
            _laborreport.setType(type);

            LinkedList<LaborParameterHandler> remove = new LinkedList<LaborParameterHandler>();
            for (LaborParameterHandler laborParameterHandler : _laborParameterHandlers) {
                if (!laborParameterHandler.isValid()) {
                    remove.add(laborParameterHandler);
                }
            }
            for (LaborParameterHandler laborParameterHandler : remove) {
                removeParameter(laborParameterHandler);
            }

            HashSet<ParameterDefinitionViewable> currentParameters = new HashSet<ParameterDefinitionViewable>();
            for (LaborParameterHandler laborParameterHandler : _laborParameterHandlers) {
                currentParameters.add(laborParameterHandler.getLaborParameter().getParameterDefinition());
            }

            for (ParameterDefinitionViewable parameterDefinitionViewable : _laborreport.getType().getParameterDefinitions()) {
                if (!currentParameters.contains(parameterDefinitionViewable)) {
                    LaborParameterHandler laborParameterHandler = new LaborParameterHandler();
                    laborParameterHandler.setParameterDefinition(parameterDefinitionViewable);
                    addParameter(laborParameterHandler);
                }
            }

            _laborParameterChanged.fireEvent(EventArgs.Empty);
        }
    }

    /**
     * Sets the notice.
     * 
     * @param notice
     *            the new notice
     */
    public void setNotice(String notice) {
        notice = StringUtil.convertToNullIfPossible(notice);

        if (StringUtil.hasChanged(_laborreport.getNotice(), notice)) {
            _unsaved = true;
            _laborreport.setNotice(notice);
        }
    }

    /**
     * Adds the parameter.
     * 
     * @param handler
     *            the handler
     */
    public void addParameter(LaborParameterHandler handler) {
        _unsaved = true;

        if (!_laborParameterHandlers.contains(handler)) {
            _handlers.add(handler);
            _laborParameterHandlers.add(handler);

            ILaborParameter p = (ILaborParameter) handler.getLaborParameter();
            _laborreport.addLaborParameter(p);
            p.setLaborReport(_laborreport);
            validate();
        }
    }

    /**
     * Removes the parameter.
     * 
     * @param handler
     *            the handler
     */
    public void removeParameter(LaborParameterHandler handler) {
        _unsaved = true;

        _laborreport.removeLaborParameter(handler.getLaborParameter());
        _handlers.remove(handler);
        _laborParameterHandlers.remove(handler);

        validate();
    }

    /**
     * Gets the parameters.
     * 
     * @return the parameters
     */
    public LaborParameterHandler[] getLaborReportParameters() {
        return _laborParameterHandlers.toArray(new LaborParameterHandler[0]);
    }

    public void setUnsaved(boolean unsaved) {
        _unsaved = unsaved;
    }

    /**
     * Save.
     * 
     * @throws DatabaseException
     *             the database exception
     * @throws ErrorInFormException
     *             the error in form exception
     */
    public void save() throws DatabaseException, ErrorInFormException {
        if (!isValid()) {
            throw new ErrorInFormException();
        }
        _laborreport.save();
        _unsaved = false;
    }

    /**
     * Discard.
     */
    public void discard() {
        _laborreport.discard();
        _unsaved = false;
    }

    /**
     * Checks if patient is dirty.
     * 
     * @return true, if the patient object has changed through this handler
     */
    public boolean isDirty() {
        return _unsaved;
    }

    /**
     * Gets the parameter types.
     * 
     * @return the parameter types
     */
    public LaborReportTypeViewable[] getParameterTypes() {
        return DAOFactory.getInstance().getLaborReportTypeDAO().getParameterTypes();
    }

    /**
     * Validate.
     */
    private void validate() {
        _errorFields.clear();

        if (_laborreport.getDate() == null) {
            _errorFields.add(LaborReportErrorField.DATETIME);
        }

        if (_laborreport.getLaborParameters().length <= 0) {
            _errorFields.add(LaborReportErrorField.NOPARAMETERS);
        }
        
        // Searching for duplicated paramaterEntries
        HashMap<ParameterDefinitionViewable, LaborParameterHandler> definitionToParameter = new HashMap<ParameterDefinitionViewable, LaborParameterHandler>();
        for (LaborParameterHandler laborParameterHandler : _laborParameterHandlers) {
            ParameterDefinitionViewable def = laborParameterHandler.getLaborParameter().getParameterDefinition();
            if(definitionToParameter.containsKey(def)){
                definitionToParameter.get(def).setDuplicateParemeterDefinition(true);
                laborParameterHandler.setDuplicateParemeterDefinition(true);
            } else {
                definitionToParameter.put(def, laborParameterHandler);
                laborParameterHandler.setDuplicateParemeterDefinition(false);
            }
        }
        
        _validaded.fireEvent(new ValidatorArgs<LaborReportErrorField>(_errorFields));
    }

    public LaborReportViewable getLaborReport() {
        return _laborreport;
    }

    /**
     * @return true if this patient was once saved in the database
     */
    public boolean isInDatabase() {
        return _laborreport.isInDatabase();
    }

    public void addLaborReportChangedListener(EventListener<EventArgs> handler) {
        _laborParameterChanged.addHandler(handler);
    }

    public void removeLaborReportChangedListener(EventListener<EventArgs> handler) {
        _laborParameterChanged.removeHandler(handler);
    }

    public Excluder getLaborParameterExcluder() {
        return this;
    }

    @Override
    public boolean exclude(IParameterDefinition parameterDefinition) {
        for (LaborParameterViewable laborParameterViewable : _laborreport.getLaborParameters()) {
            if (laborParameterViewable.getParameterDefinition().equals(parameterDefinition)) {
                return true;
            }
        }

        return false;
    }
}
