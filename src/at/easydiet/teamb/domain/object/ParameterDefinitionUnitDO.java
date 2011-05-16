package at.easydiet.teamb.domain.object;

import org.apache.log4j.Logger;

import at.easydiet.teamb.domain.IParameterDefinitionUnit;
import at.easydiet.teamb.domain.util.AbstractDO;
import at.easydiet.teamb.domain.util.ParameterDefinitionDataTypeEnum;
import at.easydiet.model.ParameterDefinitionDataType;
import at.easydiet.model.ParameterDefinitionUnit;

/**
 * Represents a ParameterDefinitionUnit in the domain layer
 */
public class ParameterDefinitionUnitDO extends AbstractDO<ParameterDefinitionUnit> implements IParameterDefinitionUnit {
	private static Logger LOGGER = Logger.getLogger(ParameterDefinitionUnitDO.class);

	private ParameterDefinitionUnit _parameterDefinitionUnit;

	/**
	 * Instantiates a new parameterDefinitionUnit .
	 * 
	 * @param parameterDefinitionUnit
	 *            the parameterdefinition unit
	 */
	public ParameterDefinitionUnitDO(ParameterDefinitionUnit parameterDefinitionUnit) {
		if (parameterDefinitionUnit == null) {
			LOGGER.debug("ParameterDefinitionUnit is null");
		}

		_parameterDefinitionUnit = parameterDefinitionUnit;
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinitionUnit#getParameterDefinitionUnitId()
	 */
	@Override
	public long getParameterDefinitionUnitId() {
		return _parameterDefinitionUnit.getParameterDefinitionUnitId();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinitionUnit#setParameterDefinitionUnitId(long)
	 */
	@Override
	public void setParameterDefinitionUnitId(long parameterDefinitionUnitId) {
		_parameterDefinitionUnit.setParameterDefinitionUnitId(parameterDefinitionUnitId);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinitionUnit#getName()
	 */
	@Override
	public String getName() {
		return _parameterDefinitionUnit.getName();
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinitionUnit#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		_parameterDefinitionUnit.setName(name);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinitionUnit#getType()
	 */
	@Override
	public ParameterDefinitionDataTypeEnum getType() {
		return ParameterDefinitionDataTypeEnum.getCheckOperatorDO(_parameterDefinitionUnit.getType());
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinitionUnit#setType(at.easydiet.model.ParameterDefinitionDataType)
	 */
	@Override
	public void setType(ParameterDefinitionDataType type) {
		_parameterDefinitionUnit.setType(type);
	}

	/*
	 * (non-Javadoc)
	 * @see at.easydiet.domain.object.IParameterDefinitionUnit#isSet()
	 */
	@Override
	public boolean isSet() {
		return !(_parameterDefinitionUnit == null);
	}

	@Override
	public ParameterDefinitionUnit getModel() {
		return _parameterDefinitionUnit;
	}
}
