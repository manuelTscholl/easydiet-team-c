package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.application.util.Event;
import at.easydiet.teamb.application.viewobject.LaborReportViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionUnitViewable;
import at.easydiet.teamb.application.viewobject.ParameterDefinitionViewable;
import at.easydiet.teamb.domain.ILaborParameter;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.CheckOperatorEnum;
import at.easydiet.model.CheckOperator;
import at.easydiet.model.LaborParameter;
import at.easydiet.model.LaborReport;
import at.easydiet.teamb.presentation.util.LaborParameterChangedEventArg;

/**
 * Represents a LaborParameter
 */
public class LaborParameterDO extends AbstractDO<LaborParameter> implements ILaborParameter {
	private static Logger LOGGER = Logger.getLogger(LaborParameterDO.class);

	private LaborParameter _laborParameter;

	/**
	 * Initializes a new instance of the {@link LaborParameterDO} class.
	 *
	 * @param parameter the parameter
	 */
	public LaborParameterDO(LaborParameter parameter) {
		if (parameter == null) {
			LOGGER.debug("parameter is null");
		}

		_laborParameter = parameter;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.model.LaborParameterViewable#getLaborParameterId()
	 */
	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborParameter#getLaborParameterId()
	 */
	public long getLaborParameterId() {
		return _laborParameter.getLaborParameterId();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborParameter#setLaborParameterId(long)
	 */
	public void setLaborParameterId(long laborParameterId) {
		_laborParameter.setLaborParameterId(laborParameterId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.model.LaborParameterViewable#getValue()
	 */
	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborParameter#getValue()
	 */
	public String getValue() {
		return _laborParameter.getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborParameter#setValue(java.lang.String)
	 */
	public void setValue(String Value) {
		_laborParameter.setValue(Value);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.model.LaborParameterViewable#getParameterDefinitionUnit()
	 */
	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborParameter#getParameterDefinitionUnit()
	 */
	public ParameterDefinitionUnitViewable getParameterDefinitionUnit() {
		return (_laborParameter.getParameterDefinitionUnit() == null) ? null : new ParameterDefinitionUnitDO(_laborParameter.getParameterDefinitionUnit());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborParameter#setParameterDefinitionUnit(at.easydiet.model.ParameterDefinitionUnit)
	 */
	public void setParameterDefinitionUnit(ParameterDefinitionUnitViewable parameterDefinitionUnit) {
		_laborParameter.setParameterDefinitionUnit(((ParameterDefinitionUnitDO) parameterDefinitionUnit).getModel());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.model.LaborParameterViewable#getCheckOperator()
	 */
	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborParameter#getCheckOperator()
	 */
	public CheckOperatorEnum getCheckOperator() {
		return CheckOperatorEnum.getCheckOperatorDO(_laborParameter.getCheckOperator());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborParameter#setCheckOperator(at.easydiet.model.CheckOperator)
	 */
	public void setCheckOperator(CheckOperatorEnum checkOperator) {
		_laborParameter.setCheckOperator(new CheckOperator(checkOperator.getName()));
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.model.LaborParameterViewable#getParameterDefinition()
	 */
	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborParameter#getParameterDefinition()
	 */
	public ParameterDefinitionViewable getParameterDefinition() {
		return new ParameterDefinitionDO(_laborParameter.getParameterDefinition());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.ILaborParameter#setParameterDefinition(at.easydiet.model.ParameterDefinition)
	 */
	public void setParameterDefinition(ParameterDefinitionViewable parameterDefinition) {
		if (parameterDefinition != null) {
			_laborParameter.setParameterDefinition(((ParameterDefinitionDO) parameterDefinition).getModel());
		} else {
			_laborParameter.setParameterDefinition(null);
		}
	}

	/**
	 * Gets the labor report.
	 *
	 * @return the labor report
	 */
	public LaborReportViewable getLaborReport() 
    {
        return new LaborReportDO(_laborParameter.getLaborReport());
    }
    
    /**
     * Sets the labor report of this instance.
     *
     * @param laborReport the new labor report
     */    
    public void setLaborReport(LaborReportViewable laborReport) 
    {
    	_laborParameter.setLaborReport(((LaborReportDO)laborReport).getModel());
    }
	
	/**
	 * Returns a string representation of this instance.
	 * 
	 * @return a string
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		// interesting values
		builder.append("laborParameterId").append("='").append(getLaborParameterId()).append("' ");
		builder.append("Value").append("='").append(getValue()).append("' ");
		builder.append("parameterDefinitionUnit").append("='").append(getParameterDefinitionUnit()).append("' ");
		builder.append("checkOperator").append("='").append(getCheckOperator()).append("' ");
		builder.append("parameterDefinition").append("='").append(getParameterDefinition()).append("' ");
		builder.append("]");

		return builder.toString();
	}

	@Override
	public LaborParameter getModel() {
		return _laborParameter;
	}
}
