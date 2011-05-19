package at.easydiet.teamb.application.handler;

import org.apache.log4j.Logger;

import at.easydiet.model.LaborParameter;
import at.easydiet.teamb.application.util.ValidatorArgs.LaborParameterErrorField;
import at.easydiet.teamb.application.util.ValidatorArgs.ValidatorArgs;
import at.easydiet.teamb.application.viewobject.LaborParameterViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionViewable;
import at.easydiet.teamb.domain.ILaborParameter;
import at.easydiet.teamb.domain.object.LaborParameterDO;
import at.easydiet.teamb.domain.util.CheckOperatorEnum;
import at.easydiet.teamb.util.StringUtil;

/**
 * The Class LaborParameterHandler.
 * 
 * @author TeamB
 */
public class LaborParameterHandler extends AbstractHandler<LaborParameterErrorField> {

    /** The LOGGER. */
    private static Logger LOGGER = Logger.getLogger(LaborParameterHandler.class);

    /** The _labor parameter. */
    private ILaborParameter _laborParameter;

    private boolean _duplicateParameterDefinition;

    /**
     * Instantiates a new labor parameter handler.
     */
    public LaborParameterHandler() {
        _laborParameter = new LaborParameterDO(new LaborParameter());
        
        _duplicateParameterDefinition = false;

        validateLaborParameter();
    }

    /**
     * Instantiates a new labor parameter handler.
     * 
     * @param laborParameter
     *            the labor parameter
     */
    public LaborParameterHandler(LaborParameterViewable laborParameter) {
        if (laborParameter == null) {
            LOGGER.warn("LaborParameter is null");
            throw new NullPointerException("LaborParameter is null");
        }
        _laborParameter = (ILaborParameter) laborParameter;

        validateLaborParameter();
    }

    /**
     * Gets the labor parameter.
     *
     * @return the labor parameter
     */
    public LaborParameterViewable getLaborParameter() {
        return _laborParameter;
    }

    /**
     * Sets the value.
     * 
     * @param Value
     *            the new value
     * @see at.easydiet.domain.ILaborParameter#setValue(java.lang.String)
     */
    public void setValue(String Value) {
        _laborParameter.setValue(Value);

        validateLaborParameter();
    }

    
    /**
     * Sets the parameter definition unit.
     *
     * @param parameterDefinitionUnit the new parameter definition unit
     */
    public void setParameterDefinitionUnit(ParameterDefinitionUnitViewable parameterDefinitionUnit) {
        _laborParameter.setParameterDefinitionUnit(parameterDefinitionUnit);

        validateLaborParameter();
    }

    
    /**
     * Sets the check operator.
     *
     * @param checkOperator the new check operator
     */
    public void setCheckOperator(CheckOperatorEnum checkOperator) {
        _laborParameter.setCheckOperator(checkOperator);

        validateLaborParameter();
    }

    
    /**
     * Sets the parameter definition.
     *
     * @param parameterDefinition the new parameter definition
     */
    public void setParameterDefinition(ParameterDefinitionViewable parameterDefinition) {
        _laborParameter.setParameterDefinition(parameterDefinition);

        validateLaborParameter();
    }
    
    /**
     * Validate the labor parameter.
     */
    private void validateLaborParameter() {
        _errorFields.clear();

        if (StringUtil.isEmpty(_laborParameter.getValue())) {
            _errorFields.add(LaborParameterErrorField.VALUE);
        }

        if (_laborParameter.getCheckOperator() == null) {
            _errorFields.add(LaborParameterErrorField.CHECKOPERATOR);
        }
        if (_laborParameter.getParameterDefinition() == null) {
            _errorFields.add(LaborParameterErrorField.DEFINITION);
        }
        if (_laborParameter.getParameterDefinitionUnit() == null) {
            _errorFields.add(LaborParameterErrorField.UNIT);
        }
        if (_duplicateParameterDefinition) {
            _errorFields.add(LaborParameterErrorField.DUPLICATE);
        }
        
        _validaded.fireEvent(new ValidatorArgs<LaborParameterErrorField>(_errorFields));
    }

    public void setDuplicateParemeterDefinition(boolean b) {
        _duplicateParameterDefinition = b;
        validateLaborParameter();
    }
}
